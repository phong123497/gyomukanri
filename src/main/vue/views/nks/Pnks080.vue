<!--
//================================================================================
// ID: PNKS080
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// VueName: Pnks080
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/17 | ＡＭＣ               | 新規作成
//================================================================================
 -->
<template>
	<div class="view-area">
		<!-- メインコンテンツ -->
		<main id="PNKS080">
			<div class="contents__block--exist_return">
				<h1 class="header__block-title">
					<table>
						<colgroup>
							<col style="width: 210px" />
							<col style="width: 1330px" />
						</colgroup>
						<tbody>
							<tr>
								<td>バッチ情報一覧</td>
								<!-- 操作ページにメッセージを返します -->
								<td>
									<info-message />
								</td>
							</tr>
						</tbody>
					</table>
				</h1>
				<!-- エラーメッセージ -->
				<error-message />
				<div class="contents__wrap--scroll_vertical">
					<div class="contents__view--bg_gray">
						<div class="contents__block-top">
							<div style="margin-left:1540px">
								<span>{{ amount }}</span>
							</div>
							<table
								class="tbl-list--border-all_exist wrap--overflow_hidden tbl-sticky-head"
							>
								<colgroup>
									<col style="width: 105px" />
									<col style="width: 390px" />
									<col style="width: 220px" />
									<col style="width: 250px" />
									<col style="width: 155px" />
									<col style="width: 135px" />
									<col style="width: 155px" />
									<col style="width: 165px" />
								</colgroup>
								<thead>
									<tr>
										<th>バッチNo</th>
										<th>バッチ名称</th>
										<th>バッチ略称</th>
										<th>対象会員生協</th>
										<th>バッチ計画係数</th>
										<th>週間予測係数</th>
										<th>バッチ計画対象</th>
										<th>
											&nbsp;削除対象
											<!-- 全選択ボタン -->
											<button-input
												class="contents__btn-blue2"
												ref="allSelectBtn"
												v-bind:value="allSelectBtnText"
												v-on:click="clickAllSelectBtn()"
											></button-input>
										</th>
									</tr>
								</thead>
							</table>
							<table
								class="tbl-list--border-all_exist wrap--overflow_hidden"
								style="
									border-top: 0px !important;
								"
							>
								<colgroup>
									<col style="width: 105px" />
									<col style="width: 390px" />
									<col style="width: 220px" />
									<col style="width: 250px" />
									<col style="width: 155px" />
									<col style="width: 135px" />
									<col style="width: 155px" />
									<col style="width: 165px" />
								</colgroup>
								<!-- データを取得し表示します  -->
								<tbody>
									<tr style="height:45px; !important;"
										v-for="(item, index) in backData"
										:key="item"
									>
										<td class="border--top_none">
											<a
												v-bind:batchNo="'item.batchNo'" 
												v-bind:tabindex="'2'"
												v-on:click="clickMakerCodeLink(item.batchNo)"
											>
												&nbsp;{{ item.batchNo }}
											</a>
										</td>
										<td class="border--top_none">
											&nbsp;{{ item.batchNm }}
										</td>
										<td class="border--top_none">
											&nbsp;{{ item.batchRyakusho }}
										</td>
										<td class="border--top_none">
											&nbsp;{{ item.targetKaiinSeiky }}
										</td>
										<td
											class="border--top_none txt--align_r"
										>
											{{ item.batchPlanKeisu }}&nbsp;
										</td>
										<td
											class="border--top_none txt--align_r"
										>
											{{ item.weekYosokuKeisu }}&nbsp;
										</td>
										<td
											class="border--top_none txt--align_c"
										>
											{{ item.batchPlanTarget }}&nbsp;
										</td>
										<td class="border--top_none">
											<div id="delTarget" class="contents__block-checkbox ">
												<check-box
													style="display: none !important;"
													v-bind:checked="item.delTarget == '1'"
													v-bind:cdContentType="CD_CONTENT_TYPE.CONTENT"
													v-bind:listItems="checkboxlistItems"
													v-bind:name="'削除対象' + index"
													v-bind:ref="'delTarget' + index"
													v-bind:tabindex="'3'"
													v-model="item.delTarget"
													v-on:click="clickDelTarget(index)"
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
						<div class="button-group" style="float:left;">
							<!-- 戻りボタン -->
							<button-input
								class="contents__btn-blue"
								style="margin-right: 30px"
								v-bind:tabindex="'7'"
								v-bind:value="'戻る'"
								v-on:click="clickReturnBtn()"
							></button-input>
							<!-- キャンセルボタン -->
							<button-input
								class="contents__btn-blue"
								v-bind:tabindex="'8'"
								v-bind:value="'取消'"
								v-on:click="clickCancelBtn()"
							></button-input>
						</div>
					</div>
					<div class="center-block">
						<div class="button-group" style="float: left; margin-left: 177px">
							<!-- 削除ボタン -->
							<button-input
								class="contents__btn-blue"
								style="margin-right: 30px"
								v-bind:tabindex="'5'"
								v-bind:value="'削除'"
								v-on:click="clickDelBtn()"
							></button-input>
							<!-- 追加ボタン -->
							<button-input
								class="contents__btn-blue"
								v-bind:tabindex="'6'"
								v-bind:value="'追加'"
								v-on:click="clickAddBtn()"
							></button-input>
						</div>
					</div>
					<div class="right-block"></div>
				</div>
			</div>
		</footer>
	</div>
</template>

<script>
import ButtonInput from "@/components/ButtonInput.vue";
import CheckBox from "@/components/CheckBox.vue";
import InfoMessage from "@/components/InfoMessage.vue";
import ErrorMessage from "@/components/ErrorMessage.vue";

import { co_isEmpty } from "@/common/checkUtility.js";
import { co_post, co_setError } from "@/common/ajaxUtility.js";
import { co_confirm } from "@/common/dialogUtility.js";
import { co_getMessage } from "@/common/messageUtility.js";
import {co_backView, co_nextView} from "@/common/viewUtility.js"

import {
	PATH_PNKS080,
	PATH_PNKS080_DELETE,
	PATH_PNKS090
} from "@/const/route.js";

import {
	CNS_SHORISBT,
	CD_CONTENT_TYPE,
	DIALOG_BUTTON_LIST_KEY,
	HTTP_STATUS
} from "@/const/const.js";

import {
	STORE_ERROR_CLEAR,
	STORE_INFO_CLEAR,
	STORE_INFO_SET_MSG,
	STORE_VIEW_PROGRESS,
	STORE_ERROR_ADDMSG
} from "@/const/store.js";

export default {
	components: {
		"button-input": ButtonInput,
		"check-box": CheckBox,
		"error-message": ErrorMessage,
		"info-message": InfoMessage,
	},
	data: function () {
		return {
			backData: [],
			checkboxlistItems:[
				{
					code: 1, 
					name:'' 
				},
			],
			
			allSelectBtnText: "全選択", //全選択ボタン
			selectAll: "全選択",
			cancelAll: "全解除",
			CD_CONTENT_TYPE: CD_CONTENT_TYPE
		};
	},
	computed: {
		amount: function () {
			const reg = /(\d)(?=(\d{3})+$)/g;
			const quantity = this.backData.length.toString().replace(reg, "$1L,");
			return quantity + "件";
		}
	},
	created: function () {
		// プログレスバー表示
		this.$store.commit(STORE_VIEW_PROGRESS, true);
		co_post(PATH_PNKS080, null, this.createdBackCall);
	},
	methods: {
		createdBackCall: function (res) {
			// プログレスバー表示
			this.$store.commit(STORE_VIEW_PROGRESS, true);
			
			// 検索したデータを表示します
			if (res.status == HTTP_STATUS.OK) {
				if (res.data.length == 0) {
					this.allSelectBtnText = this.selectAll;
					this.backData = [];
				} else {
					this.allSelectBtnText = this.selectAll;
					this.backData = res.data;
				}
				// プログレスバー非表示
				this.$store.commit(STORE_VIEW_PROGRESS, false);
			}
			
		},
		
		// 全選択ボタンをクリック
		clickAllSelectBtn: function () {
			if (this.backData.length == 0) {
				return;
			}
			if (this.allSelectBtnText == this.selectAll) {
				this.allSelectBtnText = this.cancelAll;
				for (let i = 0; i < this.backData.length; i ++) {
					this.backData[i].delTarget = "1";
				}
			} else {
				this.allSelectBtnText = this.selectAll;
				for(let i = 0; i < this.backData.length; i++) {
					this.backData[i].delTarget = "0";
				}
			}
		},
		
		// 削除対象 
		clickDelTarget: function (index) {

			// 選択ボックスにチェックが入っていない場合です
			if (this.backData[index].delTarget == '' || this.backData[index].delTarget == '0') {
				this.backData[index].delTarget = '1';
			} else {
				this.backData[index].delTarget = '0';
			}

			// 選択されたデータの一覧をトラバースします
			let delTargetCnt = 0;
			for (let i = 0; i < this.backData.length; i++){
				if (this.backData[i].delTarget == '1'){
					delTargetCnt += 1
				}
			}

			// 一覧データの長さに等しい場合、ボタンは「全解除」となります。
			if ( delTargetCnt == this.backData.length ) {
				this.allSelectBtnText = this.cancelAll;
			} else if (delTargetCnt == 0) {
				this.allSelectBtnText = this.selectAll;
			} else {
				// 等しくない場合、ボタンは「全選択」となります
				this.allSelectBtnText = this.selectAll;
			}
		},
		
		// バッチNOリンククリック
		clickMakerCodeLink: function (batchNo) {
			// 状態管理(ストア)のエラー情報をクリアする
			this.$store.commit(STORE_ERROR_CLEAR);
			this.$store.commit(STORE_INFO_CLEAR);
			
			// バッチ情報メンテナンス画面へ遷移する
			co_nextView(
				PATH_PNKS090,
				{
					procsShubt: CNS_SHORISBT.CNS_SHORISBT_UPD,
					batchNo: batchNo,
				},
			)
		},
		
		// 削除ボタンクリック
		clickDelBtn: function () {
			// 状態管理(ストア)の画面表示にプログレスバーの表示を設定する
			this.$store.commit(STORE_VIEW_PROGRESS, true);
			// 状態管理(ストア)のエラー情報をクリアする
			this.$store.commit(STORE_ERROR_CLEAR);
			this.$store.commit(STORE_INFO_CLEAR);
			
			// 画面で選択データを取得します。
			let backBatchNoList = [];
			for (let i = 0; i < this.backData.length; i++) {
				if (this.backData[i].delTarget == "1") {
					backBatchNoList.push(this.backData[i].batchNo);
				}
			}
			
			if (backBatchNoList.length == 0) {
				// エラー情報を提示
				this.$store.commit(STORE_ERROR_ADDMSG, co_getMessage("CMS0037E", "削除"));
				// 状態管理(ストア)の画面表示にプログレスバーの表示を設定する
				this.$store.commit(STORE_VIEW_PROGRESS, false);
				return;
			}

			// 選択されたデータを削除します
			let self = this;
			co_confirm(co_getMessage("CMS0005W"), function (flg) {
				if (flg == DIALOG_BUTTON_LIST_KEY.OK) {
					co_post(
						PATH_PNKS080_DELETE,
						{ delBatchNoList: backBatchNoList },
						self.deleteBack
					);
				}
			});
			// 状態管理(ストア)の画面表示にプログレスバーの表示を設定する
			this.$store.commit(STORE_VIEW_PROGRESS, false);
		},
		
		deleteBack: function (res) {
			if (res.status == HTTP_STATUS.NO_CONTENT) {	
				// 削除成功のヒントボックスです
				this.$store.commit(STORE_INFO_SET_MSG, co_getMessage("CMS0003I"));
				co_post(PATH_PNKS080, null, this.createdBackCall);
			} else {
				// サーバー処理が異常に終了した場合です
				co_setError(res.data, this.$refs);
				if (!co_isEmpty(this.$refs[res.data[0].errorItems[0]])) {
					this.$nextTick(function () {
						this.$refs[res.data[0].errorItems[0]].focus();
					});
				}
				
				//  状態管理(ストア)の画面表示にプログレスバーの非表示を設定する
				this.$store.commit(STORE_VIEW_PROGRESS, false);
			}
		},
		
		// 追加ボタンクリック
		clickAddBtn: function () {
			co_nextView(
				PATH_PNKS090,
				{
					procsShubt: CNS_SHORISBT.CNS_SHORISBT_ADD,
					batchNo: null,
				},
			)
		},
		
		// キャンセルボタンクリック
		clickCancelBtn: function () {
			// 状態管理(ストア)の画面表示にプログレスバーの表示を設定する
			this.$store.commit(STORE_VIEW_PROGRESS, true);
			this.allSelectBtnText = this.selectAll;
			// エラーをクリア
			this.$store.commit(STORE_ERROR_CLEAR);
			this.$store.commit(STORE_INFO_CLEAR);
			co_post(PATH_PNKS080, null, this.createdBackCall);
		},
		
		// 戻るボタンクリック
		clickReturnBtn: function () {
			co_backView();
		},
	},
};
</script>

<style type="text/css">
#PNKS080 .tbl-sticky-head {
	overflow: hidden;
	position: -webkit-sticky;
	position: sticky;
	top: 0;
	z-index: 1;
}

#PNKS080 .contents__block--exist_return {
	padding-top: 60px;
}

#PNKS080 .contents__btn-blue2 {
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

#PNKS080 .contents__btn-blue2:hover {
	background: #fff;
	border: 1px solid #4158c6;
	color: #6167da;
}

#PNKS080 .checkbox--margin-l-10 {
	margin-left: 10px;
}

#PNKS080 #delTarget .checkbox-label[data-v-1ac2b216]{
	background: transparent !important;
	border-right: 0px !important;
}

#PNKS080 #delTarget .checkbox-label[data-v-1ac2b216]::before {
	top: -9px !important;
	left: 60px !important;
}

#PNKS080 #delTarget .checkbox:checked + .checkbox-label[data-v-1ac2b216]::after {
	top: -7px !important;
	left: 69px !important;
}

#PNKS080 .tbl-list--border-all_exist th {
	padding: 8px 5px !important;
	border: 2px solid #cbcdd9 !important;
}

#PNKS080 .contents__block-top {
	margin: 8px 0 4px;
	overflow: visible;
}

</style>
