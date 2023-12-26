<!--
//================================================================================
// ID: PSYS030
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// VueName: パスワード変更
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/01 |  AMC                 | 新規作成
//================================================================================
 -->
<template>
<div>
	<!-- メインコンテンツ -->
	<main id="PSYS030">
		<div class="contents__block--exist_return">
			<div class="contents__wrap--scroll_vertical">
				<div class="contents__view--bg_white">
					<div class="contents-wrap--bg-w--pad-3per">
						<!-- エラーメッセージ -->
						<error-message />
						<section>
							<table class="tbl-item--border-row_exist">
								<tbody>
									<tr>
										<td>担当者コード</td>
										<td>{{form.tansCd}}</td>
									</tr>
									<tr>
										<td>現在のパスワード</td>
									<td>
										<input
												type="password"
												maxlength="64"
												v-bind:class="[{'contents--error-item': disp.currentErrorFlg},'input--password' ]"
												v-model="form.currentPassWord"
												ref="currentPassWord"
												tabindex="1"
												v-on:keydown.delete.stop="keyControl">
										</td>
									</tr>
									<tr>
										<td>新しいパスワード</td>
										<td>
											<input
												type="password"
												maxlength="64"
												v-bind:class="[{'contents--error-item': disp.newErrorFlg},'input--password' ]"
												v-model="form.newPassWord"
												ref="newPassWord"
												tabindex="2"
												v-on:keydown.delete.stop="keyControl"
												pattern="">
										</td>
									</tr>
									<tr>
										<td>新しいパスワード(確認用)</td>
										<td>
											<input
												type="password"
												maxlength="64"
												v-bind:class="[{'contents--error-item': disp.confErrorFlg},'input--password' ]"
												v-model="disp.confPassWord"
												ref="confPassWord"
												tabindex="3"
												v-on:keydown.delete.stop="keyControl">
										</td>
									</tr>
								</tbody>
							</table>
							<p class="annotation">
								※「初回ログイン」及び「パスワードの有効期限切れ」の場合は、パスワードの変更が必須となっております。
								<br>
								<span class="mgr-left-15">パスワード変更後、この画面は表示されません。</span>
							</p>
						</section>
					</div>
				</div>
			</div>
		</div>
	</main>
	<!-- コマンドボタンエリア -->
	<footer>
		<div class="contents__block-command-btn--fullsize">
			<div class="footer-button__block">
				<div class="left-block">
					<div class="button-group">
						<button-input
							type="button"
							class="contents__btn-blue"
							v-bind:value="'戻る'"
							v-on:click="clickCancelBtn"
							tabindex="5"/>
					</div>
				</div>
				<div class="center-block">
				</div>
				<div class="right-block">
					<div class="button-group">
						<button-input
							class="contents__btn-blue"
							v-bind:value="'保存'"
							v-on:click="clickSaveBtn"
							tabindex="4"/>
					</div>
				</div>
			</div>
		</div>
	</footer>
</div>

</template>

<script>
import ButtonInput from '@/components/ButtonInput.vue';
import ErrorMessage from '@/components/ErrorMessage.vue';

import { co_post, co_put } from '@/common/ajaxUtility.js';
import { co_notice, co_confirm } from '@/common/dialogUtility.js';
import { co_getMessage } from '@/common/messageUtility.js';
import { co_doLogout } from '@/common/unloadEventUtility.js';
import { co_isEmpty } from '@/common/checkUtility.js';

import { DIALOG_BUTTON_LIST_KEY, HTTP_STATUS, } from '@/const/const.js';
import { PSYS030_HALF_STR, }from '@/const/messageId.js';
import {
	PATH_PSYS030, PATH_PSYS030_EDIT, PATH_PSYS030_EDIT_CHECK, PATH_LOGIN, PATH_PSYS020,
} from '@/const/route.js';
import {
	STORE_VIEW_CHANGED, STORE_VIEW_PROGRESS, STORE_VIEW_GETBACKURL,
	STORE_VIEW_HAS_CHANGED, STORE_ERROR_CLEAR, STORE_ERROR_IS_ERRFLG,
	STORE_ERROR_ADDMSG,
} from '@/const/store.js';

//const PASSWORD_MIN_LENGTH = 8;
export default {
	props: {
		isLogin: {type: Boolean, default: false},	//ログイン画面から来たかの判定フラグ（true:ログイン画面からの遷移）
	},
	components: {
		'button-input': ButtonInput,
		'error-message': ErrorMessage,
	},
	data: function () {
		return {
			form: {
				tansCd: '',				//担当者CD
				currentPassWord: '',	//現在のパスワード
				newPassWord: '',		//新しいパスワード
//				updDate: Date,			//更新日時（排他チェック用、非表示項目）
			},
			disp: {
				confPassWord: '',		//新しいパスワード（確認用）
				currentErrorFlg: false,
				newErrorFlg: false,
				confErrorFlg: false,
			},
		}
	},
	created: async function() {

		// プログレスバー表示
		this.$store.commit(STORE_VIEW_PROGRESS, true);
		// 初期データ取得 呼出
		let data = await co_post(PATH_PSYS030,this.form);
		// 取得した情報の保存
		this.form.tansCd = data.tansCd;
//		this.form.updDate = data.updDate;



		// 入力値変更状態を変更なしに設定
		this.$nextTick(function() {
			this.$store.commit(STORE_VIEW_CHANGED, false);
		});
		// プログレスバー非表示
		this.$store.commit(STORE_VIEW_PROGRESS, false);

	},
	mounted: async function() {
		await this.$nextTick();
		// フォーカス設定（現在のパスワード）
		this.$refs.currentPassWord.focus();
	},
	beforeUnmount: function() {
	},
	computed: {
	},
	watch: {
		// formオブジェクト内のすべての変更を監視する
        form: {
          handler: function (/* val, old */) {
              this.$store.commit(STORE_VIEW_CHANGED, true);
          },
          deep: true
        },
	},
	methods: {
		keyControl: function () {
			// キーイベントを無効化するためのメソッドのため、処理なし
		},
		// 保存ボタンクリック
		clickSaveBtn: async function() {
			//プログレスバー表示
			this.$store.commit(STORE_VIEW_PROGRESS, true);
			//エラー情報をクリア
			this.$store.commit(STORE_ERROR_CLEAR);

			//パスワードの入力チェック
			this.checkPassword();

			//入力チェックでエラーの場合
			if (this.$store.getters[STORE_ERROR_IS_ERRFLG] == true) {
				//プログレスバー非表示
				this.$store.commit(STORE_VIEW_PROGRESS, false);
				//新しいパスワード、新しいパスワード（確認用）の入力値をクリア
				this.form.newPassWord = '';
				this.disp.confPassWord = '';
			}
			//入力チェックでエラーがない場合
			else {
				//パスワード更新前確認処理 呼出
				await co_post(PATH_PSYS030_EDIT_CHECK, this.form, this.callbackEditCheck);
			}

		},
		// パスワードチェック
		checkPassword: function(){
			this.disp.currentErrorFlg = false ;
			this.disp.newErrorFlg = false ;
			this.disp.confErrorFlg = false ;

			//現在のパスワード 必須チェック
			if (co_isEmpty(this.form.currentPassWord)) {
				this.$store.commit(STORE_ERROR_ADDMSG,
					co_getMessage('CMS0013E', '現在のパスワード'));
				this.disp.currentErrorFlg = true ;
			}
			else{
				// 「半角英数記号」以外が設定されていた場合エラー
				if ( !this.isMatchHalf(this.form.currentPassWord)) {
					this.$store.commit(STORE_ERROR_ADDMSG,
							co_getMessage('SYS0001E', '現在のパスワード', PSYS030_HALF_STR));
					this.disp.currentErrorFlg = true ;
				}
			}
			//新しいパスワード 必須チェック
			if (co_isEmpty(this.form.newPassWord)) {
				this.$store.commit(STORE_ERROR_ADDMSG, co_getMessage('CMS0013E', '新しいパスワード'));
				this.disp.newErrorFlg = true ;
			}
			//新しいパスワードが入力されている場合
//			else{
//
//				// 「半角英数記号」以外が設定されていた場合エラー
//				if ( !this.isMatchHalf(this.form.newPassWord) ) {
//					this.$store.commit(STORE_ERROR_ADDMSG, 	co_getMessage('SYS0001E', '新しいパスワード', PSYS030_HALF_STR));
//					this.disp.newErrorFlg = true ;
//				}
//
//				//画面から入力された新しいパスワードに、英字、数字、記号のうち、2種類以上の文字種を含まない場合は、エラー
//				if(this.checkPasswordString(this.form.newPassWord) == false){
//					this.$store.commit(STORE_ERROR_ADDMSG, 	co_getMessage('CMS0018E', '新しいパスワード', '英字、数字、記号のうち、2種類以上の'));
//					this.disp.newErrorFlg = true ;
//				}
//
//				//画面から入力された新しいパスワードが8文字未満の場合は、エラー
//				if(this.form.newPassWord.length < PASSWORD_MIN_LENGTH){
//					this.$store.commit(STORE_ERROR_ADDMSG, 	co_getMessage('CMS0013E', '新しいパスワードは8文字以上', String(PASSWORD_MIN_LENGTH)));
//					this.disp.newErrorFlg = true ;
//				}
//			}

			//画面から入力された新しいパスワードと新しいパスワード(確認用)が異なる場合は、エラー
			if(this.form.newPassWord != this.disp.confPassWord){
				this.$store.commit(STORE_ERROR_ADDMSG, 	co_getMessage('SYS0002E', '新しいパスワードが新しいパスワード(確認用)'));
				this.disp.newErrorFlg = true ;
			}

		},
		// 半角のみか
		isMatchHalf : function (str) {
			return str.match(/^[\x20-\x7e\uFF61-\uFF9F]+$/);
		},
		// パスワード文字列チェック
		//文字列に、英字、数字、記号のうち、2種類以上の文字種を含まない場合は、falseを返す
		checkPasswordString: function(passwordString){

			//数値のみを抽出
			let numberStr = passwordString.replace(/[^0-9]/g, '');
			//英字のみを抽出
			let alphabetStr = passwordString.replace(/[^a-zA-Z]/g, '');

			let allLength =passwordString.length;
			let numberLength =numberStr.length;
			let alphabetLength =alphabetStr.length;

			//数値のみ判定
			if(allLength == numberLength){ return false; }
			//英字のみ判定
			if(allLength == alphabetLength){ return false; }
			//記号のみ判定
			if((numberLength + alphabetLength) == 0){ return false; }

			return true;
		},
		//サーバ処理の『パスワード更新前確認処理』のコールバック
		callbackEditCheck: function (res) {
			//処理が正常の場合
			if(res.status == HTTP_STATUS.OK) {
				//確認メッセージのダイアログを表示 コールバック関数:保存実行
				co_confirm(co_getMessage('CMS0004W'), this.doSave);
			}
			//上記以外の場合
			else {
				//エラーメッセージを設定
				this.$store.commit(STORE_ERROR_ADDMSG, co_getMessage(res.data[0].msgId, res.data[0].msgParams));
				this.setErrorFlg(res.data[0].errorItems);
				this.$store.commit(STORE_VIEW_PROGRESS, false);
				//新しいパスワード、新しいパスワード（確認用）の入力値をクリア
				this.form.newPassWord = '';
				this.disp.confPassWord = '';
			}

		},
		//エラー設定
		setErrorFlg: function(errorItems){

			//nullチェック
			if(errorItems == null){
				return;
			}

			// エラーフラグ設定
			for ( let i = 0; i < errorItems.length; i++ ) {
				switch(errorItems[i]){
				case 'currentPassWord':
					this.disp.currentErrorFlg = true;
					break;
				case 'newPassWord':
					this.disp.newErrorFlg = true;
					break;
				case 'confPassWord':
					this.disp.confErrorFlg = true;
					break;
				}
			}
		},
		// 保存実行
		doSave: async function(resultSelect) {
			//「はい」押下
			if(resultSelect == DIALOG_BUTTON_LIST_KEY.OK){
				//パスワード更新処理 呼出
				await co_put(PATH_PSYS030_EDIT, this.form, this.callbackSave);

			}
			//「いいえ」押下
			else{
				// プログレスバー非表示
				this.$store.commit(STORE_VIEW_PROGRESS, false);
			}
		},
		//サーバ処理の『パスワード更新処理』のコールバック
		callbackSave: function (res) {

			// 正常終了
			if(res.status == HTTP_STATUS.CREATED) {

				// プログレスバー非表示
				this.$store.commit(STORE_VIEW_PROGRESS, false);
				// 編集状態をクリア
				this.$store.commit(STORE_VIEW_CHANGED, false);
				// 通知メッセージのダイアログを表示
				co_notice(co_getMessage('CMS0001I'), this.doDashboard);

			}
			else {
				// エラーメッセージを設定
				this.$store.commit(STORE_ERROR_ADDMSG, co_getMessage(res.data[0].msgId, res.data[0].msgParams));
				this.setErrorFlg(res.data[0].errorItems);

			}

			// プログレスバー非表示
			this.$store.commit(STORE_VIEW_PROGRESS, false);

		},
		// キャンセルボタン押下イベント
		clickCancelBtn: function() {
			//入力内容に変更がある場合
			if(this.$store.getters[STORE_VIEW_HAS_CHANGED] == true){
				//ダイアログの表示 コールバック関数:キャンセル実行
				co_confirm(co_getMessage('CMS0002W'), this.doCancel);
			}
			//上記以外の場合
			else{
				// 「はい」を押下した状態でキャンセル実行を呼出
				this.doCancel(DIALOG_BUTTON_LIST_KEY.OK);
			}

		},
		// キャンセル実行
		doCancel: function(resultSelect) {

			//「はい」押下の場合
			if(resultSelect == DIALOG_BUTTON_LIST_KEY.OK){
				
				//状態管理(ストア)より画面表示の戻り先のURLを取得し、画面遷移する。
				let previousUrl = this.$store.getters[STORE_VIEW_GETBACKURL];

				//戻り先がログイン画面の場合
				if(previousUrl.path == '/'){
					//ダイアログの表示 コールバック関数:ログアウト実行
					co_confirm(co_getMessage('CMS0001W'), this.doLogout);
				}
				else{
					//入力内容の変更状態をクリア
					this.$store.commit(STORE_VIEW_CHANGED, false);

					this.$router.push(previousUrl.path);
				}

			}
		},
		// ログアウト実行
		doLogout: function(result) {
			//「はい」押下の場合
			if(result == DIALOG_BUTTON_LIST_KEY.OK){
				//利用者情報の破棄
				window.removeEventListener('beforeunload', co_doLogout);
				//ログイン画面に遷移
				window.location.href = PATH_LOGIN;

			}
		},
		// メニュー画面へ遷移
		doDashboard: function() {
			this.$router.push(PATH_PSYS020);

		}
	}
}
</script>

<style scoped>
.tbl-item--border-row_exist td:first-of-type {
	width: 290px;
}

.contents-wrap--bg-w--pad-3per {
	height: calc(100vh - 217.28px - 10px - 69px);
}

.mgr-left-15 {
	margin-left: 15px;
}

@media print {
	.contents__view--bg_white
	, .contents-wrap--bg-w--pad-3per {
		height: auto;
	}
}
</style>
