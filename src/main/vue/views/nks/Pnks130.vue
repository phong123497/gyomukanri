<!--
//================================================================================
// ID: PNKS130
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// VueName: Pnks130
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// 01.00.00 | 2023/07/06 | ＡＭＣ               | 新規作成
//================================================================================
 -->
<template>
	<div class="view-area">
		<main id="PNKS130">
			<div class="contents__block--exist_return">
				<h1 class="header__block-title">
					<table>
						<colgroup>
							<col style="width: 210px" />
							<col style="width: 1330px" />
						</colgroup>
						<tbody>
							<tr>
								<td>メーカーマスタ一覧</td>
								<!-- 操作ページにメッセージを返します -->
								<td>
									<info-message />
								</td>
							</tr>
						</tbody>
					</table>
				</h1>
				<!-- エラー情報 -->
				<div id="error">
					<error-message />
				</div>
				<div class="contents__wrap--scroll_vertical">
					<div class="contents__block-search padding-t-b">
						<div class="contents__block-search__main">
							<table>
								<colgroup>
									<col style="width: 180px" />
									<col style="width: 1000px" />
									<col style="width: 250px" />
								</colgroup>
								<tbody>
									<tr>
										<td class="txt--align_r">
											<span class="contents__block-search__label txt--align_r">
												メーカー
											</span>
										</td>
										<td>
											<!-- メーカー開始 -->
											<list-box
												class="width-450"
												ref="makerStart"
												v-bind:blankFlg="true"
												v-bind:cdContentType="CD_CONTENT_TYPE.CODENAME"
												v-bind:disabledFlg="false"
												v-bind:listItems="makerCdList"
												v-bind:name="'メーカー開始'"
												v-bind:tabindex="'1'"
												v-model="form.makerStart"
											></list-box>
											<span class="contents__block-search__label">
												&nbsp;～&nbsp;
											</span>
											<!-- メーカー終了 -->
											<list-box
												class="width-450"
												ref="makerEnd"
												v-bind:blankFlg="true"
												v-bind:cdContentType="CD_CONTENT_TYPE.CODENAME"
												v-bind:disabledFlg="false"
												v-bind:listItems="makerCdList"
												v-bind:name="'メーカー終了'"
												v-bind:tabindex="'2'"
												v-model="form.makerEnd"
											></list-box>
										</td>
										<td>
											<!-- 検索ボタンです -->
											<button-input
												class="contents__btn-blue"
												v-bind:tabindex="'3'"
												v-bind:value="'検索'"
												v-on:click="clickSearchBtn()"
											>
												検索
											</button-input>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<div class="contents__view--bg_gray">
						<div class="contents__block-top"  v-if="isInitFlg" style="height:23px; width: 1835px">
							<!-- 行った件数です -->
							<div class="contents__block-top__area--float_r kensu-margin-right">
								<span>{{ amount }}</span>
							</div>
						</div>
						<div class="tbl-list__wrap">
							<table class="tbl-list--border-all_exist tbl-sticky-head wrap--overflow_hidden"
								>
								<colgroup>
									<col style="width: 140px;">
									<col style="width: 380px;">
									<col style="width: 220px;">
									<col style="width: 200px;">
									<col style="width: 200px;">
									<col style="width: 120px;">
									<col style="width: 200px;">
									<col style="width: 120px;">
									<col style="width: 90px;">
									<col style="width: 145px;">
								</colgroup>
								<thead>
									<tr>
										<!-- データの列名が表示されます -->
										<th class="txt--align_l"> メーカーコード </th>
										<th class="txt--align_l"> メーカー名称 </th>
										<th class="txt--align_l"> メーカー略称 </th>
										<th class="txt--align_l"> TEL </th>
										<th class="txt--align_l"> FAX </th>
										<th class="txt--align_l"> 納品時間 </th>
										<th class="txt--align_l"> パスワード </th>
										<th class="txt--align_l"> 取引先コード </th>
										<th class="txt--align_l"> 取扱停止 </th>
										<th class="txt--align_l">
											削除対象
											<!-- 対象を削除します -->
											<button-input
												class="contents__btn-blue2"
												ref="allSelectBtn"
												v-bind:tabindex="'4'"
												v-bind:value="allSelectBtnText"
												v-on:click="clickAllSelectBtn()"
											></button-input>
										</th>
									</tr>
								</thead>
							</table>
							<!-- データを取得します -->
							<table id="NSKC0130sub" class="tbl-list--border-all_exist wrap--overflow_hidden" 
								style="margin-top: -2px;">
								<colgroup>
									<col style="width: 140px;">
									<col style="width: 380px;">
									<col style="width: 220px;">
									<col style="width: 200px;">
									<col style="width: 200px;">
									<col style="width: 120px;">
									<col style="width: 200px;">
									<col style="width: 120px;">
									<col style="width: 90px;">
									<col style="width: 145px;">
								</colgroup>
								<tbody>
									<tr
										style="height:45px; !important;"
										v-for="(item, index) in tableData"
										:key="item"
									>
										<!-- メーカーコードをクリックしてメーカーに移動します -->
										<td class="border--top_none">
											<a
												v-bind:tabindex="'5'"
												v-bind:makerCd="item.makerCd"
												v-on:click="clickMakerCodeLink(item.makerCd)"
											>
												{{ item.makerCd }}
											</a>
										</td>
										<!-- メーカー名を取得します -->
										<td class="border--top_none" v-bind:title="item.makerNm"
										>
											<div class="ellipsis">
												{{ item.makerNm }}
											</div>
										</td>
										<!-- メーカーの略称を取得します。 -->
										<td class="border--top_none">
											{{ item.makerRyakusho }}
										</td>
										<!-- メーカーの電話番号を取得します -->
										<td class="border--top_none">
											{{ item.tel }}
										</td>
										<!-- 製造元のファックスを入手します -->
										<td class="border--top_none">
											{{ item.fax }}
										</td>
										<!-- メーカーの納入時間を取得します。 -->
										<td class="border--top_none">
											{{ item.delvTime }}
										</td>
										<!-- パスワード -->
										<td class="border--top_none">
											{{ item.pass }}
										</td>
										<!-- 取引先コード -->
										<td class="border--top_none">
											{{ item.toriskCd }}
										</td>
										<!-- 取引停止 -->
										<td class="border--top_none txt--align_c">
											<div id="toriatStop" class="contents__block-checkbox">
												<check-box
													style="display:none; !important;"
													v-bind:checked="item.toriatStop == '1'"
													v-bind:cdContentType="CD_CONTENT_TYPE.CONTENT"
													v-bind:listItems="checkboxlistItems"
													v-bind:name="'取引停止' + index"
													v-bind:ref="'toriatStop' + index"
													v-bind:tabindex="'6'"
													v-on:click="clickToraiStop(index)"
													v-model="item.toriatStop"
												/>
											</div>
										</td>
										<td class="border--top_none">
											<!--削除対象 -->
											<div id="delTarget" class="contents__block-checkbox">
												<check-box
													style="display: none !important;"
													v-bind:checked="item.delTarget == '1'"
													v-bind:cdContentType="CD_CONTENT_TYPE.CONTENT"
													v-bind:listItems="checkboxlistItems"
													v-bind:name="'削除対象' + index"
													v-bind:ref="'delTarget' + index"
													v-bind:tabindex="'7'"
													v-on:click="clickTargetCheckBox(index)"
													v-model="item.delTarget"
												/>
											</div>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</main>
		<footer>
			<div class="contents__block-command-btn--fullsize">
				<div class="footer-button__block">
					<div class="left-block">
						<!-- 戻る -->
						<div class="button-group" style="float: left">
							<button-input
								class="contents__btn-blue"
								style="margin-right: 30px"
								v-bind:tabindex="'11'"
								v-bind:value="'戻る'"
								v-on:click="clickReturnBtn()"
							></button-input>
							<!-- 取消 -->
							<button-input
								class="contents__btn-blue"
								v-bind:tabindex="'12'"
								v-bind:value="'取消'"
								v-on:click="clickCancelBtn()"
							></button-input>
						</div>
					</div>
					<div class="center-block">
						<div
							class="button-group"
							style="float: left; margin-left: 177px"
						>
							<!-- 削除 -->
							<button-input
								class="contents__btn-blue"
								style="margin-right: 30px"
								v-bind:tabindex="'9'"
								v-bind:value="'削除'"
								v-on:click="clickDelBtn()"
							></button-input>
							<!-- 追加 -->
							<button-input
								class="contents__btn-blue"
								v-bind:tabindex="'10'"
								v-bind:to="PATH_PNKS140"
								v-bind:value="'追加'"
								v-on:click="clickAddBtn()"
							></button-input>
						</div>
					</div>
					<div class="right-block">
						<div
							class="button-group"
							style="float: right; margin-right: 52px"
						>
							<!-- 保存 -->
							<button-input
								class="contents__btn-blue"
								v-bind:tabindex="'8'"
								v-bind:value="'保存'"
								v-on:click="clickSaveBtn()"
							>
							</button-input>
						</div>
					</div>
				</div>
			</div>
		</footer>
	</div>
</template>

<script>
import ButtonInput from "@/components/ButtonInput.vue";
import ErrorMessage from "@/components/ErrorMessage.vue";
import ListBox from "@/components/ListBox";
import InfoMessage from "@/components/InfoMessage.vue";
import CheckBox from "@/components/CheckBox.vue";

import { co_confirm, co_alert } from "@/common/dialogUtility.js";
import { co_getMessage } from "@/common/messageUtility.js";
import { co_isEmpty } from "@/common/checkUtility.js";
import { co_post, co_put, co_setError } from "@/common/ajaxUtility.js";
import {co_backView, co_nextView} from "@/common/viewUtility.js"

import {
	CD_CONTENT_TYPE,
	DIALOG_BUTTON_LIST_KEY,
	HTTP_STATUS,
	CNS_SHORISBT,
} from "@/const/const.js";

import {
	STORE_DATA_ADD_DATA,
	STORE_DATA_DELETE_AT,
	STORE_DATA_GETDATA,
	STORE_ERROR_ADDMSG,
	STORE_ERROR_CLEAR,
	STORE_INFO_CLEAR,
	STORE_INFO_SET_MSG,
	STORE_VIEW_PROGRESS,
} from "@/const/store.js";

import {
	PATH_PNKS130,
	PATH_PNKS130_DELETE,
	PATH_PNKS130_SAVE,
	PATH_PNKS130_SEARCH,
	PATH_PNKS130_SEARCH_CHECK,
	PATH_PNKS140
} from "@/const/route.js";

export default {
	components: {
		"button-input":ButtonInput,
		"error-message":ErrorMessage,
		"list-box":ListBox,
		"info-message": InfoMessage,
		"check-box": CheckBox
	},
	data: function () {
		return {
			form: {
				makerEnd: "",       // メーカー終了
				makerStart: "",		// メーカー開始
			},
			saveSearchData: {
				// データを検索
				searchKbn: false,
				searchMakerEnd: "",
				searchMakerStart: ""
			},
			checkboxlistItems:[
				{
					code: 1, 
					name:'' 
				},
			],
			allDelete: "全解除",	// 全解除
			allSelect: "全選択",	// 全選択
			allSelectBtnText: "全選択",	// 全選択
			CD_CONTENT_TYPE: CD_CONTENT_TYPE,
			makerCdList: [],
			PATH_PNKS140: PATH_PNKS140,
			tableData: [],
			isInitFlg: false
		};
	},
	computed: {
		amount: function () {
			const reg = /(\d)(?=(\d{3})+$)/g;
			const quantity = this.tableData.length.toString().replace(reg, "$1L,");
			return quantity + "件";
		}
	},
	created: function () {
		let self = this;
		co_post(PATH_PNKS130, this.form, function (res) {
			// 状態がいい場合です
			if (res.status == HTTP_STATUS.OK) {
				const newList = [];
				const itemList = res.data.makerComboxList;
				itemList.map(function ({makercd, makerkj}) {
					newList.push({code: makercd, name: makerkj});
				})
				self.makerCdList = newList;
			}
		});
		this.allSelectBtnText = this.allSelect;

		// 保存した検索データを状態管理から取得します
		const searchData = this.$store.getters[STORE_DATA_GETDATA]("pnks130");

		// データがある場合です
		if (searchData?.searchKbn) {
			this.saveSearchData.searchKbn = searchData.searchKbn;
			this.form.makerEnd = searchData.makerEnd;
			this.form.makerStart = searchData.makerStart;
			this.$store.commit(STORE_VIEW_PROGRESS, true);
			// クライアント処理の『検索実行』を呼出する
			this.doSearchMakerList();
		}
	},
	mounted: async function() {
		await this.$nextTick();
		// フォーカス設定
		this.$refs.makerStart.focus();	
	},
	methods: {
		// 検索ボタン方式を実装します
		clickSearchBtn: async function () {
			// 状態管理(ストア)の画面表示にプログレスバーの表示を設定する
			this.$store.commit(STORE_VIEW_PROGRESS, true);

			this.saveSearchData.searchKbn = true;
			this.saveSearchData.makerStart = this.form.makerStart;
			this.saveSearchData.searchMakerEnd = this.form.makerEnd;
			this.$store.commit(STORE_ERROR_CLEAR);
			this.$store.commit(STORE_INFO_CLEAR);

			const self = this;
			this.tableData = [];
			const dataQuantity = await this.getDataQuantity();
			// 検索結果が1000件を超えています
			if (dataQuantity > 1000) {
				this.$store.commit(STORE_ERROR_ADDMSG, co_getMessage('CMS0032E', 1000));
				self.$store.commit(STORE_VIEW_PROGRESS, false);
			} else if (dataQuantity > 500) {
				// 検索結果が500件を超えています
				co_alert(co_getMessage('CMS0015W', 500), function (clickValue) {
					if (clickValue == DIALOG_BUTTON_LIST_KEY.CANCEL) {
						self.$store.commit(STORE_VIEW_PROGRESS, false);
						return;
					}
					// クライアント処理の『検索実行』を呼出する
					co_post(PATH_PNKS130_SEARCH, self.form, self.doSearchMakerList);
					self.$store.commit(STORE_VIEW_PROGRESS, false);
				});
			} else {
				// クライアント処理の『検索実行』を呼出する
				co_post(PATH_PNKS130_SEARCH, self.form, self.doSearchMakerList);
			}
		},
		// 検索実行
		doSearchMakerList: function () {

			const self = this;
			// サーバ処理の『一覧検索』を呼出する
			co_post(PATH_PNKS130_SEARCH, this.form, function (res) {
				if (res.status == HTTP_STATUS.OK) {
					// データを取り
					self.tableData = res.data;
					// 状態管理(ストア)の画面表示にプログレスバーの非表示を設定する
					self.$store.commit(STORE_VIEW_PROGRESS, false);
				} else {
					self.saveSearchData.searchKbn = false;
					self.$store.commit(STORE_VIEW_PROGRESS, false);

					co_setError(res.data, self.$refs);
					const errorItem = res.data[0].errorItems[0];
					if (!co_isEmpty(self.$refs[errorItem])) {
						const self = this;
						this.$nextTick(function () {
							self.$refs[errorItem].focus();
						})
					}
				}
				self.isInitFlg = true
			});
			this.allSelectBtnText = this.allSelect
		},
		// 全選択ボタンをクリック
		clickAllSelectBtn: function () {
			// データがなければ実行しません
			if (this.tableData.length == 0) {
				return;
			}

			// 一覧の削除対象チェックボックスを全部チェック入れる
			if (this.allSelectBtnText == this.allSelect) {
				this.allSelectBtnText = this.allDelete;
				for (let i = 0; i < this.tableData.length; i++) {
					this.tableData[i].delTarget = '1';
				}
			} else {
				// ボタン名を「全解除」の場合
				this.allSelectBtnText = this.allSelect;
				for (let i = 0; i < this.tableData.length; i++) {
					this.tableData[i].delTarget = '0';
				}
			}
			
			

		},
		//メーカーコードリンククリック
		clickMakerCodeLink: function (makerCd) {
			// 状態管理(ストア)のエラー情報をクリアする
			this.$store.commit(STORE_DATA_ADD_DATA, {
				id: "pnks130",
				data: {
					makerStart: this.form.makerStart,
					makerEnd: this.form.makerEnd,
					searchKbn: this.saveSearchData.searchKbn,
				},
			});
			// PNKS140_【農産企画支援】メーカーマスタメンテナンス」に遷移する
			co_nextView(
				PATH_PNKS140,
				{
					makerCd: makerCd,
					procsShubt: CNS_SHORISBT.CNS_SHORISBT_UPD,
				},
			);
		},
		// 削除対象チェックボックスをクリック
		clickTargetCheckBox: function (index) {
			// 選択ボックスにチェックが入っていない場合です
			if (this.tableData[index].delTarget == '0') {
				this.tableData[index].delTarget = '1';
			} else {
				this.tableData[index].delTarget = '0';
			}

			// 選択されたデータの一覧をトラバースします
			let delTargetCnt = 0;
			for (let i = 0; i < this.tableData.length; i++){
				if (this.tableData[i].delTarget == '1'){
					delTargetCnt += 1
				}
			}

			// 一覧データの長さに等しい場合、ボタンは「全解除」となります。
			if ( delTargetCnt == this.tableData.length ) {
				this.allSelectBtnText = this.allDelete;
			} else if (delTargetCnt == 0) {
				this.allSelectBtnText = this.allSelect;
			} else {
				// 等しくない場合、ボタンは「全選択」となります。
				this.allSelectBtnText = this.allSelect;
			}
			
		},
		// 保存ボタンクリック
		clickSaveBtn: function () {
			
			// 状態管理(ストア)の画面表示にプログレスバーの表示を設定する
			this.$store.commit(STORE_VIEW_PROGRESS, true);
			// 状態管理(ストア)のエラー情報をクリアする
			this.$store.commit(STORE_ERROR_CLEAR);
			this.$store.commit(STORE_INFO_CLEAR);
			
			// 入力Formの取扱停止メーカー情報リストを設定する
			const toriatStopList = [];
			for (let i = 0; i < this.tableData.length; i++) {
				// データを追加します
				if (this.tableData[i].toriatStop != this.tableData[i].toriatStopFlag) {
					toriatStopList.push(this.tableData[i]);
				}
			}
			
			// 更新対象データがありません
			if (toriatStopList.length == 0) {
				this.$store.commit(STORE_ERROR_ADDMSG, co_getMessage("NKS0009E", "更新対象"));
				// 状態管理(ストア)にサーバから返却されたエラーメッセージを設定し、以降の処理を中断する
				this.$store.commit(STORE_VIEW_PROGRESS, false);
				return;
			}
			
			//  一覧の取引停止の更新対象有無をチェックする
			co_put(PATH_PNKS130_SAVE, { makerInfList: toriatStopList }, this.saveBack);
		},
		// サーバ処理の『保存』を呼出する
		saveBack: function (res) {
			if (res.status == HTTP_STATUS.CREATED) {
				// 検索実行を呼び出
				this.doSearchMakerList();
				// 保存成功を促すメッセージが表示されます
				this.$store.commit(STORE_INFO_SET_MSG, co_getMessage("CMS0001I"));
				// プログレスバー非表示
				this.$store.commit(STORE_VIEW_PROGRESS, false);
			} else {
				// プログレスバー非表示
				this.$store.commit(STORE_VIEW_PROGRESS, false);
				// 業務エラー表示
				co_setError(res.data, this.$refs);
				if (!co_isEmpty(this.$refs[res.data[0].errorItems[0]])) {
					this.$nextTick(function () {
						this.$refs[res.data[0].errorItems[0]].focus();
					});
				}
			}
		},
		// 削除ボタンクリック
		clickDelBtn: function () {
			
			// 状態管理(ストア)の画面表示にプログレスバーの表示を設定する
			this.$store.commit(STORE_VIEW_PROGRESS, true);
			// 状態管理(ストア)のエラー情報をクリアする
			this.$store.commit(STORE_ERROR_CLEAR);
			this.$store.commit(STORE_INFO_CLEAR);
			let backMakerCdList = [];
			
			// 選択したデータを取得します
			for (let i = 0; i < this.tableData.length; i++) {
				if (this.tableData[i].delTarget == "1") {
					backMakerCdList.push(this.tableData[i].makerCd);
				}
			}
			
			// 明細の削除対象は1件も選択されていない場合はエラー
			if (backMakerCdList.length == 0) {
				// エラー情報を提示
				this.$store.commit(STORE_ERROR_ADDMSG, co_getMessage("CMS0037E", "削除"));
				//  状態管理(ストア)の画面表示にプログレスバーの非表示を設定する
				this.$store.commit(STORE_VIEW_PROGRESS, false);
				return;
			}
			
			//  確認メッセージのダイアログを表示する
			let self = this;
			co_confirm(co_getMessage("CMS0005W"), function (flg) {
				if (flg == DIALOG_BUTTON_LIST_KEY.OK) {
					// バックグラウンド・インターフェースを呼び出します
					co_put(PATH_PNKS130_DELETE, { delMakerCdList: backMakerCdList }, self.deleteBack);
				}
			});
			
			//  状態管理(ストア)の画面表示にプログレスバーの非表示を設定する
			this.$store.commit(STORE_VIEW_PROGRESS, false);
		},
		// サーバ処理の『削除』を呼出する
		deleteBack: function (res) {
			// サーバ処理が正常終了した場合
			if (res.status == HTTP_STATUS.CREATED) {
				// 検索実行を呼び出
				this.doSearchMakerList();
				// 状態管理(ストア)の画面表示にプログレスバーの非表示を設定する
				this.$store.commit(STORE_VIEW_PROGRESS, false);
				// 削除成功のヒントボックスです
				this.$store.commit(STORE_INFO_SET_MSG, co_getMessage("CMS0003I"));

				let self = this;
				co_post(PATH_PNKS130, this.form, function (res) {
					// 状態がいい場合です
					if (res.status == HTTP_STATUS.OK) {
						const newList = [];
						const itemList = res.data.makerComboxList;
						itemList.map(function ({makercd, makerkj}) {
							newList.push({code: makercd, name: makerkj});
						})
						self.makerCdList = newList;
					}
				});
			} else {
				// 状態管理(ストア)の画面表示にプログレスバーの非表示を設定する
				this.$store.commit(STORE_VIEW_PROGRESS, false);

				// サーバー処理が異常に終了した場合です
				co_setError(res.data, this.$refs);
				if (!co_isEmpty(this.$refs[res.data[0].errorItems[0]])) {
					this.$nextTick(function () {
						this.$refs[res.data[0].errorItems[0]].focus();
					});
				}
			}
		},
		// 追加ボタンクリック
		clickAddBtn: function () {
			//  指定した画面IDで画面データを保持する
			this.$store.commit(STORE_DATA_ADD_DATA, {
				id: "pnks130",
				data: {
					makerStart: this.form.makerStart,
					makerEnd: this.form.makerEnd,
					searchKbn: this.saveSearchData.searchKbn,
				},
			});
			// 「PNKS140_【農産企画支援】メーカーマスタメンテナンス」に遷移する
			co_nextView(
				PATH_PNKS140,
				{makerCd: null, procsShubt: CNS_SHORISBT.CNS_SHORISBT_ADD}
			);
		},
		// 取消ボタンクリック
		clickCancelBtn: async function () {
			
			// 初期状態にする。
			this.cnt = "";
			this.tableData = [];
			this.form.makerStart = "";
			this.form.makerEnd = "";
			this.saveSearchData.searchKbn = false;
			this.isInitFlg = false

			await this.$nextTick();
			// フォーカス設定
			this.$refs.makerStart.focus();
			
			// エラーメッセージをクリアする
			this.$store.commit(STORE_INFO_CLEAR);
			this.$store.commit(STORE_ERROR_CLEAR);
		},
		// 戻るボタンクリック
		clickReturnBtn: function () {
			// 指定した画面IDのデータを削除する
			this.$store.commit(STORE_DATA_DELETE_AT, "pnks130");
			// PMNU100_業務管理メニューに遷移する
			co_backView();
		},
		clickToraiStop: function (index) {
			// 選択ボックスが選択されたかどうかを判断します
			if (this.tableData[index].toriatStop == '0') {
				// 選択ボックスが選択されなかった場合です
				this.tableData[index].toriatStop = '1';
			} else {
				// 選択枠が既に選択されている場合です
				this.tableData[index].toriatStop = '0';
			}
		},
		getDataQuantity: function () {
			return co_post(PATH_PNKS130_SEARCH_CHECK, this.form);
		}
	},
};
</script>

<style type="text/css">
#PNKS130 .tbl-sticky-head {
	position: -webkit-sticky;
	position: sticky;
	top: 0;
	z-index: 1;
}

#PNKS130 .contents__block--exist_return {
	padding-top: 60px;
}

#PNKS130 .contents__btn-blue2 {
	background: linear-gradient(90deg, #5e6adc 0%, #6e5bcf 100%);
	box-sizing: border-box;
	border: 1px solid transparent;
	border-radius: 22px;
	color: #fff;
	display: inline-block;
	height: 30px;
	min-width: 50px;
	padding: 5px 8px;
	vertical-align: middle;
	font-size: 12px !important;
}

#PNKS130 .contents__btn-blue2:hover {
	background: #fff;
	border: 1px solid #4158c6;
	color: #6167da;
}

#PNKS130 #toriatStop .checkbox-label[data-v-1ac2b216]{
	background: transparent !important;
	border-right: 0px !important;
}

#PNKS130 #toriatStop .checkbox-label[data-v-1ac2b216]::before {
    top: -9px !important;
    left: -5px !important;
}

#PNKS130 #toriatStop .checkbox:checked + .checkbox-label[data-v-1ac2b216]::after {
    top: -7px !important;
    left: 4px !important;
}

#PNKS130 #delTarget .checkbox-label[data-v-1ac2b216]{
	background: transparent !important;
	border-right: 0px !important;
}

#PNKS130 #delTarget .checkbox-label[data-v-1ac2b216]::before {
	top: -9px !important;
	left: 50px !important;
}

#PNKS130 #delTarget .checkbox:checked + .checkbox-label[data-v-1ac2b216]::after {
	top: -7px !important;
	left: 59px !important;
}

#PNKS130 .ellipsis {
	width: 360px;
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
}

#PNKS130 .contents__block-top {
	margin: 8px 0 4px;
	overflow: visible;
}

#PNKS130 .kensu-margin-right {
	margin-right: 17px;
}

</style>
