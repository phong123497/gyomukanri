<!--
//================================================================================
// ID: OLCN01020M
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// VueName: Olcn01020m
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2022/10/28 |  AMC                 | 新規作成
//================================================================================
 -->
<template>
<div>
	<!-- メインコンテンツ -->
	<main id="OLCN01020M">
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
										<td>ユーザID</td>
										<td>{{form.userId}}</td>
									</tr>
									<tr>
										<td>現在のパスワード <span class="require">必須</span></td>
									<td>
										<input
												type="password"
												maxlength="64"
												v-bind:class="[{'contents--error-item': disp.currentErrorFlg},'input--password' ]"
												v-model="form.currentPassWord"
												ref="currentPassWord"
												tabindex="1"
												v-on:keydown.delete.stop="keyControl">
			
<!--											<text-input
													v-model="form.currentPassWord"
													class="input--advisor-name"
													v-bind:width="300"
													v-bind:maxLength="10"
													v-bind:textType="TEXTINPUT_TYPE.HALF_NUM"
													tabindex="10" 
													:placeholder="'現在のパスワード'"
													:requireFlg="true" 
													ref="currentPassWord" />
												<div class="contents__block-checkbox">
													<check-box
														v-model="disp.tantoKeitaiCd"
														class="test-check-box"
														v-bind:name="'利用形態'"
														v-bind:reckb="'47'"
														v-bind:cdContentType="CD_CONTENT_TYPE.SHORTENED_NAME"
														:requireFlg="true" 
														ref="tantoKeitaiCd"
														tabindex="2" />
												</div>
												<list-box
														class="width-180"
														v-model="form.tantoKeitaiCd"
														v-bind:name="'利用形態'"
														v-bind:reckb="'47'"
														:requireFlg="true" 
														ref="tantoKeitaiCd"
														tabindex="6" />
														<radio-button
															class="contents__block-radio"
															v-model="form.dateDef"
															v-bind:name="'日付_デフォルト内容'"
															v-bind:listItems="disp.dateDefList"
															v-bind:registerScreenFlg="true"
															:requireFlg="true" 
															ref="dateDef"
														tabindex="15" />
-->		
												<code-input
													class="input--tekiyo-jigyosyo-num"
													v-model="form.koyoNo"
													v-bind:maxLength="8"
													v-bind:name="'コード入力'"
													ref="koyoNo"
													tabindex="4" />
										</td>
									</tr>
									<tr>
										<td>新しいパスワード <span class="require">必須</span></td>
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

										<td>新しいパスワード(確認用) <span class="require">必須</span></td>
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
								※「初回ログイン」、「パスワード初期化後の初回ログイン」及び「パスワードの有効期限切れ」の場合は、パスワードの変更が必須となっております。
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
		<div class="contents__block-command-btn--bg_white">
			<div class="float-r">
				<button-input
					class="contents__btn-blue"
					v-on:click="clickSaveBtn"
					tabindex="4"
					>
					保存
				</button-input>
				<button
					type="button"
					class="contents__btn-blue"
					v-on:click="clickCancelBtn"
					tabindex="5">
					キャンセル
				</button>
			</div>
		</div>
	</footer>
</div>

</template>

<script>
import ErrorMessage from '@/components/ErrorMessage.vue';
import ButtonInput from '@/components/ButtonInput.vue';
//import TextInput from '@/components/TextInput.vue';
//import CheckBox from '@/components/CheckBox.vue';
//import ListBox from '@/components/ListBox.vue';
//import RadioButton from '@/components/RadioButton.vue';
import CodeInput from '@/components/CodeInput.vue';

import { co_post, co_put } from '@/common/ajaxUtility.js';
import { co_notice, co_confirm } from '@/common/dialogUtility.js';
import { co_getMessage } from '@/common/messageUtility.js';
import { co_doLogout } from '@/common/unloadEventUtility.js';
import { co_isEmpty } from '@/common/checkUtility.js';

import { DIALOG_BUTTON_LIST_KEY, HTTP_STATUS, } from '@/const/const.js';
import { CD_CONTENT_TYPE, TEXTINPUT_TYPE } from '@/const/const.js';
import { OL2002E_HALF_STR, }from '@/const/messageId.js';
import {
	PATH_OLCN01020M, PATH_OLCN01020M_EDIT_CHECK, PATH_OLCN01020M_EDIT, PATH_LOGIN, PATH_OLCN02010M,
} from '@/const/route.js';
import {
	STORE_VIEW_CHANGED, STORE_VIEW_PROGRESS, STORE_VIEW_GETBACKURL,
	STORE_VIEW_HAS_CHANGED, STORE_ERROR_CLEAR, STORE_ERROR_IS_ERRFLG,
	STORE_ERROR_ADDMSG,
} from '@/const/store.js';

const PASSWORD_MIN_LENGTH = 8;
export default {
	props: {

	},
	components: {
		'error-message': ErrorMessage,
		'button-input': ButtonInput,
//		'text-input': TextInput,
//		'check-box': CheckBox,
//		'list-box': ListBox,
//		'radio-button': RadioButton,
		'code-input': CodeInput,
	},
	data: function() {
		return {
			form: {
				userId: '',				//ユーザID
				currentPassWord: '',	//現在のパスワード
				newPassWord: '',		//新しいパスワード
				updDate: Date,			//更新日時（排他チェック用、非表示項目
//				tantoKeitaiCd: null,		//TODO コンポーネントテスト用
				dateDef: '0',			//TODO コンポーネントテスト用
				koyoNo:''
			},
			disp: {
				confPassWord: '',		//新しいパスワード（確認用）
				currentErrorFlg: false,
				newErrorFlg: false,
				confErrorFlg: false,
//				tantoKeitaiCd: '',		//TODO コンポーネントテスト用
				ateDefList: '',			//TODO コンポーネントテスト用
				koyoNo:''
			},
			TEXTINPUT_TYPE: TEXTINPUT_TYPE,
			CD_CONTENT_TYPE: CD_CONTENT_TYPE,
		}
	},
	created: async function() {

		// 日付_デフォルト内容ラジオボタンリスト
		this.disp.dateDefList = [
			{"code":"0","name":"なし"},
			{"code":"1","name":"当日"}
		];
		this.form.dateDef = '1';
		
		// プログレスバー表示
		this.$store.commit(STORE_VIEW_PROGRESS, true);
		// 初期データ取得 呼出
		let data = await co_post(PATH_OLCN01020M,this.form);
		// 取得した情報の保存
		this.form.userId = data.userId;
		this.form.updDate = data.updDate;



		// 入力値変更状態を変更なしに設定
//		await this.$nextTick(function() {
//			this.$store.commit(STORE_VIEW_CHANGED, false);
//		});
		
		// プログレスバー非表示
		this.$store.commit(STORE_VIEW_PROGRESS, false);
//		this.disp.tantoKeitaiCd ="111";
//		this.form.tantoKeitaiCd ='111';

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
          handler: function(/* val, old */) {
              this.$store.commit(STORE_VIEW_CHANGED, true);
          },
          deep: true
        },
	},
	methods: {
		keyControl: function() {
			// キーイベントを無効化するためのメソッドのため、処理なし
		},
		// 保存ボタンクリック
		clickSaveBtn: async function() {
			//プログレスバー表示
			this.$store.commit(STORE_VIEW_PROGRESS, true);
			//エラー情報をクリア
			this.$store.commit(STORE_ERROR_CLEAR);
			//this.$refs.currentPassWord.validate();
			//this.$refs.dateDef.validate();
			//this.$refs.koyoNo.validate();
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
			else{
				//パスワード更新前確認処理 呼出
				await co_post(PATH_OLCN01020M_EDIT_CHECK, this.form, this.callbackEditCheck);
			}

		},
		// パスワードチェック
		checkPassword: function(){
			this.disp.currentErrorFlg = false ;
			this.disp.newErrorFlg = false ;
			this.disp.confErrorFlg = false ;

			//現在のパスワード 必須チェック
			if(co_isEmpty(this.form.currentPassWord) == true) {
				this.$store.commit(STORE_ERROR_ADDMSG,
					co_getMessage('CMS0090E', '現在のパスワード'));
				this.disp.currentErrorFlg = true ;
			}
			else{
				// 「半角英数記号」以外が設定されていた場合エラー
				if ( !this.isMatchHalf(this.form.currentPassWord) ) {
					this.$store.commit(STORE_ERROR_ADDMSG,
							co_getMessage('OL2002E', '現在のパスワード', OL2002E_HALF_STR));
					this.disp.currentErrorFlg = true ;
				}
			}
			//新しいパスワード 必須チェック
			if(co_isEmpty(this.form.newPassWord) == true) {
				this.$store.commit(STORE_ERROR_ADDMSG,
						co_getMessage('CMS0090E', '新しいパスワード'));
				this.disp.newErrorFlg = true ;
			}
			//新しいパスワードが入力されている場合
			else{

				// 「半角英数記号」以外が設定されていた場合エラー
				if ( !this.isMatchHalf(this.form.newPassWord) ) {
					this.$store.commit(STORE_ERROR_ADDMSG,
							co_getMessage('OL2002E', '新しいパスワード', OL2002E_HALF_STR));
					this.disp.newErrorFlg = true ;
				}

				//画面から入力された新しいパスワードに、英字、数字、記号のうち、2種類以上の文字種を含まない場合は、エラー
				if(this.checkPasswordString(this.form.newPassWord) == false){
					this.$store.commit(STORE_ERROR_ADDMSG,
							co_getMessage('OL1006E', '新しいパスワード'));
					this.disp.newErrorFlg = true ;
				}

				//画面から入力された新しいパスワードが8文字未満の場合は、エラー
				if(this.form.newPassWord.length < PASSWORD_MIN_LENGTH){
					this.$store.commit(STORE_ERROR_ADDMSG,
							co_getMessage('OL1007E', '新しいパスワード', String(PASSWORD_MIN_LENGTH)));
					this.disp.newErrorFlg = true ;
				}
			}

			//画面から入力された新しいパスワードと新しいパスワード(確認用)が異なる場合は、エラー
			if(this.form.newPassWord != this.disp.confPassWord){
				this.$store.commit(STORE_ERROR_ADDMSG,
						co_getMessage('OL1005E'));
				this.disp.newErrorFlg = true ;
			}

		},
		// 半角のみか
		isMatchHalf : function(str) {
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
		callbackEditCheck: function(res) {
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
				await co_put(PATH_OLCN01020M_EDIT, this.form, this.callbackSave);

			}
			//「いいえ」押下
			else{
				// プログレスバー非表示
				this.$store.commit(STORE_VIEW_PROGRESS, false);
			}
		},
		//サーバ処理の『パスワード更新処理』のコールバック
		callbackSave: function(res) {

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

				//戻り先がログイン画面の場合
				if(this.$route.params.isLogin == true){
					//ダイアログの表示 コールバック関数:ログアウト実行
					co_confirm(co_getMessage('CMS0001W'), this.doLogout);
				}
				else{
					//入力内容の変更状態をクリア
					this.$store.commit(STORE_VIEW_CHANGED, false);

					//状態管理(ストア)より画面表示の戻り先のURLを取得し、画面遷移する。
					let previousUrl = this.$store.getters[STORE_VIEW_GETBACKURL];
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
		// ダッシュボード画面へ遷移
		doDashboard: function() {
			this.$router.push(PATH_OLCN02010M);

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
