<!--
//================================================================================
// ID: PNKS090
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// VueName: Pnks090
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/14 | ＡＭＣ               | 新規作成
//================================================================================
 -->
<template>
	<div>
		<!-- メインコンテンツ -->
		<main id="PNKS090">
			<div class="contents__block--exist_return">
				<h1 class="header__block-title-with-input">
					<table>
						<colgroup>
							<col style="width:230px">
							<col style="width:200px">
							<col style="width:150px">
						</colgroup>
						<tbody>
							<tr>
								<td>
									バッチ情報メンテナンス
								</td>
								<td>
									<list-box
										class="width-150 mgn-l-25px"
										ref="shubt"
										v-bind:disabledFlg="true"
										v-bind:listItems="disp.shoriSbtList"
										v-bind:name="'種別'"
										v-bind:tabindex="'1'" 
										v-if="disp.shoriSbtList !== null"
										v-model="form.shubt"/>
								</td>
								<td>
									<info-message />
								</td>
							</tr>
						</tbody>
					</table>
				</h1>
				<!-- エラーメッセージ -->
				<div id="error">
					<error-message />
				</div>
				<div class="contents__wrap--scroll_vertical">
					<div class="contents__view--bg_white">
						<div class="contents-wrap--bg-w--pad-3per">
							<section>
								<table  class="tbl-item--border-row_exist">
									<tbody>
										<tr>
											<td class="txt--align_l">
												<span class="contents__block-search__label">バッチNo</span>
											</td>
											<td>
												<code-input
													class="width-60"
													ref="batchNo"
													v-bind:activeZeroPadding="true"
													v-bind:disabledFlg="form.shubt == '2'"
													v-bind:maxLength="2"
													v-bind:name="'バッチNo'"
													v-bind:requireFlg="true"
													v-bind:tabindex="'2'"
													v-model="form.batchNo" />
											</td>
										</tr>
										<tr>
											<td class="txt--align_l">
												<span class="contents__block-search__label">バッチ名称</span>
											</td>
											<td>
												<text-input
													class="width-300"
													ref="batchNm"
													v-bind:disabledFlg="isEditNoDateFlg"
													v-bind:maxLength="20"
													v-bind:name="'バッチ名称'"
													v-bind:requireFlg="true"
													v-bind:tabindex="'3'"
													v-bind:textType="TEXTINPUT_TYPE.FULL"
													v-model="form.batchNm" />
											</td>
										</tr>
										<tr>
											<td class="txt--align_l">
												<span class="contents__block-search__label">バッチ略称</span>
											</td>
											<td>
												<text-input
													class="width-300"
													ref="batchRyakusho"
													v-bind:disabledFlg="isEditNoDateFlg"
													v-bind:maxLength="10"
													v-bind:name="'バッチ略称'"
													v-bind:requireFlg="true"
													v-bind:tabindex="'4'"
													v-bind:textType="TEXTINPUT_TYPE.FULL"
													v-model="form.batchRyakusho" />
											</td>
										</tr>
										<tr>
											<td class="txt--align_l">
												<span class="contents__block-search__label">対象会員生協</span>
											</td>
											<td>
												<list-box
													class="width-300"
													ref="targetKaiinSeiky"
													v-bind:disabledFlg="isEditNoDateFlg"
													v-bind:listItems="disp.targetKaiinSeikyComboxList"
													v-bind:name="'対象会員生協'"
													v-bind:tabindex="'5'" 
													v-if="disp.targetKaiinSeikyComboxList !== null"
													v-model="form.targetKaiinSeiky"/>
											</td>
										</tr>
										<tr>
											<td class="txt--align_l">
												<span class="contents__block-search__label">バッチ計画対象</span>
											</td>
											<td>
												<check-box  class="contents__block-checkbox"
													ref="batchPlanTarget"
													v-bind:disabledFlg="isEditNoDateFlg"
													v-bind:listItems="disp.batchPlanTargetList"
													v-bind:maxLength="1"
													v-bind:name="'バッチ計画対象'"
													v-bind:tabindex="'6'"
													v-model="form.batchPlanTarget" />
											</td>
										</tr>
										<tr>
											<td class="txt--align_l">
												<span class="contents__block-search__label">バッチ計画係数</span>
											</td>
											<td>
												<number-input
													class="width-150  txt--align_r"
													ref="batchPlanKeisu"
													v-bind:disabledFlg="isEditNoDateFlg"
													v-bind:maxLength="7"
													v-bind:name="'バッチ計画係数'"
													v-bind:numberType="NUMBERINPUT_TYPE.INTEGER"
													v-bind:requireFlg="true"
													v-bind:tabindex="'7'"
													v-model="form.batchPlanKeisu" />
											</td>
										</tr>
										<tr>
											<td class="txt--align_l">
												<span class="contents__block-search__label">週間予測係数</span>
											</td>
											<td>
												<number-input
													class="width-150  txt--align_r"
													ref="weekYosokuKeisu"
													v-bind:disabledFlg="isEditNoDateFlg"
													v-model="form.weekYosokuKeisu"
													v-bind:decimalLength="2"
													v-bind:maxVal="99999.99"
													v-bind:minVal="-99999.99"
													v-bind:maxLength="8"
													v-bind:name="'週間予測係数'"
													v-bind:numberType="NUMBERINPUT_TYPE.COMMA_FULL_INDICATE"
													v-bind:requireFlg="true"
													v-bind:tabindex="'8'" />
											</td>
										</tr>
									</tbody>
								</table>
							</section>
						</div>
					</div>
				</div>
			</div>
		</main>
		<footer>
			<div class="contents__block-command-btn--fullsize">
				<div class="footer-button__block btn-display-change">
					<div class="left-block right-adjustment">
						<div class="button-group">
							<button-input
								class="contents__btn-blue margin-right-30"
								v-bind:value="'戻る'"
								v-bind:name="'backButton'"
								v-bind:tabindex="'10'"
								v-on:click="clickReturnBtn" />
							<button-input
								class="contents__btn-blue"
								v-bind:disabledFlg="isEditNoDateFlg"
								v-bind:value="'取消'"
								v-bind:name="'cancelButton'"
								v-bind:tabindex="'11'"
								v-on:click="clickCancelBtn" />
						</div>
					</div>
					<div style="width:70%"></div>
					<div class="right-block">
						<div class="button-group">
							<button-input
								class="contents__btn-blue"
								v-bind:disabledFlg="isEditNoDateFlg"
								v-bind:value="'保存'"
								v-bind:name="'csvOutputButton'"
								v-bind:tabindex="'9'"
								v-on:click="clickSaveBtn" />
						</div>
					</div>
				</div>
			</div>
		</footer>
	</div>
</template>

<script>
import ButtonInput from '@/components/ButtonInput.vue';
import CheckBox from '@/components/CheckBox.vue';
import CodeInput from '@/components/CodeInput.vue';
import ErrorMessage from '@/components/ErrorMessage.vue';
import InfoMessage from '@/components/InfoMessage.vue';
import ListBox from '@/components/ListBox.vue';
import NumberInput from '@/components/NumberInput.vue';
import TextInput from '@/components/TextInput.vue';

import { 
	co_post,
	co_put,
	co_setError,
	} from '@/common/ajaxUtility.js';
import { co_confirm } from '@/common/dialogUtility.js';
import { co_getMessage } from '@/common/messageUtility.js';
import { co_isEmpty } from '@/common/checkUtility.js';
import { co_backView } from '@/common/viewUtility.js';

import {
	CNS_SHORISBT,
	DIALOG_BUTTON_LIST_KEY,
	HTTP_STATUS,
	NUMBERINPUT_TYPE,
	TEXTINPUT_TYPE,
} from '@/const/const.js';

import {
	PATH_PNKS090,
	PATH_PNKS090_SAVE,
} from '@/const/route.js';

import {
	STORE_INFO_CLEAR,
	STORE_INFO_SET_MSG,
	STORE_ERROR_CLEAR,
	STORE_VIEW_CHANGED,
	STORE_VIEW_GET_PARAMS,
	STORE_VIEW_HAS_CHANGED,
	STORE_ERROR_IS_ERRFLG,
	STORE_VIEW_PROGRESS,
} from '@/const/store.js';

export default {
	components: {
		'button-input': ButtonInput,
		'check-box': CheckBox,
		'code-input': CodeInput,
		'error-message': ErrorMessage,
		'info-message': InfoMessage,
		'list-box': ListBox,
		'text-input': TextInput,
		'number-input': NumberInput,
	},
	data: function () {
		return {
			form: {
				shubt: '',				// 種別
				batchNo: '',			// バッチNO
				batchNm: '',			// バッチ名称
				batchRyakusho: '',		// バッチ略称
				targetKaiinSeiky: '',	// 対象会員生協
				batchPlanTarget: '',	// バッチ計画対象
				batchPlanKeisu: '',	// バッチ計画係数
				weekYosokuKeisu: '',	// 週間予測係数
				updDatm: '',			// 更新日時
			},
			isEditNoDateFlg: false,
			disp: {
				shoriSbtList:[
					// 処理種別リスト
					{code: CNS_SHORISBT.CNS_SHORISBT_ADD, name: CNS_SHORISBT.CNS_SHORISBT_ADD + ":" + CNS_SHORISBT.CNS_SHORISBT_ADD_NAME},
					{code: CNS_SHORISBT.CNS_SHORISBT_UPD, name: CNS_SHORISBT.CNS_SHORISBT_UPD + ":" + CNS_SHORISBT.CNS_SHORISBT_UPD_NAME},
				], 
				targetKaiinSeikyComboxList: [], 		// 対象会員生協コンボボックスList
				batchPlanTargetList: [{code: "1", name: "　"},], 		// バッチ計画対象List
			},
			TEXTINPUT_TYPE: TEXTINPUT_TYPE,
			NUMBERINPUT_TYPE:NUMBERINPUT_TYPE,
		}
	},
	created: function() {
		const beforeParam = this.$store.getters[STORE_VIEW_GET_PARAMS]("PNKS090") 
		this.form.shubt = beforeParam.procsShubt;
		this.form.batchNo = beforeParam.batchNo;
		// ** 画面項目初期化 **
		this.getInitData();
	},
	methods: {
		// 初期表示データ取得
		getInitData: function() {
			// 状態管理(ストア)のエラー情報をクリアする。
			this.$store.commit(STORE_ERROR_CLEAR);
			this.$store.commit(STORE_INFO_CLEAR);
			// プログレスバー表示
			this.$store.commit(STORE_VIEW_PROGRESS, true);
			this.isEditNoDateFlg = false;
			// サーバ処理の『画面項目初期化』を呼出する。
			let self = this;
			co_post(PATH_PNKS090, this.form, function (res) {
				// プログレスバー表示取消
				self.$store.commit(STORE_VIEW_PROGRESS, false);
				if (res.status == HTTP_STATUS.OK) { 
					// 初期値を設定する。
					if (self.form.shubt == CNS_SHORISBT.CNS_SHORISBT_UPD) {
						self.form.batchNo = res.data.batchNo;
						self.form.batchNm = res.data.batchNm;
						self.form.batchRyakusho = res.data.batchRyakusho;
						if (res.data.targetKaiinSeikyComboxList){
							self.form.targetKaiinSeiky = res.data.targetKaiinSeiky;
						} else {
							self.form.targetKaiinSeiky = '';
						}
						if (res.data.batchPlanTarget != 1) {
							self.form.batchPlanTarget = '';
						} else {
							self.form.batchPlanTarget = res.data.batchPlanTarget;
						}
						self.form.batchPlanKeisu = res.data.batchPlanKeisu.toString();
						self.form.weekYosokuKeisu = res.data.weekYosokuKeisu.toString();
						self.form.updDatm = res.data.updDatm;
					} else {
						self.form.batchNo = '';
						self.form.batchNm = '';
						self.form.batchRyakusho = '';
						self.form.targetKaiinSeiky = '';
						self.form.batchPlanTarget = '';
						self.form.batchPlanKeisu = '';
						self.form.weekYosokuKeisu = '';
						self.form.updDatm = '';
					}
					// 対象会員生協コンボボックスを設定する。
					let targetKaiinSeikyComboxList = [];
					res.data.targetKaiinSeikyComboxList.forEach(function ({seicd, seikjrm}) {
						targetKaiinSeikyComboxList.push({
							code: seicd , name: seicd + ":" + seikjrm
						})
					})
					self.disp.targetKaiinSeikyComboxList = targetKaiinSeikyComboxList;
					// フォーカス設定
					self.setFoucus();
					self.$nextTick(function () {
						// 変更なし設定
						self.$store.commit(STORE_VIEW_CHANGED, false);
					});
				} else {
					self.isEditNoDateFlg = true;
					// 変更なし設定
					self.$store.commit(STORE_VIEW_CHANGED, false);
					// 業務エラー表示
					co_setError(res.data, self.$refs)
					if (!co_isEmpty(self.$refs[res.data[0].errorItems[0]])) {
						self.$nextTick(function () {
							self.$refs[res.data[0].errorItems[0]].focus()
						});
					} else {
						this.$refs.backButton.focus();
					}
				}
			})
		},
		setFoucus: function() {
			let self = this;
			// フォーカス設定
			self.$nextTick(function () {
				if (self.form.shubt == CNS_SHORISBT.CNS_SHORISBT_ADD) {
					self.$refs.batchNo.focus();
				}else if (self.form.shubt == CNS_SHORISBT.CNS_SHORISBT_UPD) {
					self.$refs.batchNm.focus();
				}
			})
		},
		// click - 戻るボタンクリック
		clickReturnBtn: function() {

			// 画面項目変更の場合
			if (this.$store.getters[STORE_VIEW_HAS_CHANGED]){
				// 確認ダイアログを表示
				co_confirm(co_getMessage('CMS0002W'), function(btnValue){
					if (btnValue == DIALOG_BUTTON_LIST_KEY.OK) {
						co_backView();
					}
				});
			} else {
				co_backView();
			}
		},
		// click - 取消ボタンクリック
		clickCancelBtn: async function() {
			// 画面項目変更の場合
			if (!this.$store.getters[STORE_VIEW_HAS_CHANGED]) {
				this.getInitData();
				return;
			}
			let self = this;
			// 確認ダイアログを表示
			co_confirm(co_getMessage('CMS0003W'), function (btnValue) {
				if (btnValue == DIALOG_BUTTON_LIST_KEY.OK) {
					self.getInitData();
				}
			})			
		},
		// click -保存ボタンクリック
		clickSaveBtn: function() {
			// 状態管理(ストア)のエラー情報をクリアする。
			this.$store.commit(STORE_ERROR_CLEAR);
			this.$store.commit(STORE_INFO_CLEAR);
			// プログレスバー表示
			this.$store.commit(STORE_VIEW_PROGRESS, true);
			
			// ** チェック処理 **
			this.$refs.batchNo.validate();
			this.$refs.batchNm.validate();
			this.$refs.batchRyakusho.validate();
			this.$refs.targetKaiinSeiky.validate();
			this.$refs.batchPlanTarget.validate();
			this.$refs.batchPlanKeisu.validate();
			this.$refs.weekYosokuKeisu.validate();
			const self = this;
			// ** エラー表示制御 **
			if (this.$store.getters[STORE_ERROR_IS_ERRFLG]) {
				this.$store.commit(STORE_VIEW_PROGRESS, false);
				return;
			}
			// サーバ処理の『保存』を呼出する。
			co_put(PATH_PNKS090_SAVE, this.form, function(res) {
				self.$store.commit(STORE_VIEW_PROGRESS, false);
				// 保存成功の場合
				if (res.status == HTTP_STATUS.CREATED) {
					self.$nextTick(function () {
						// ** 画面項目初期化 **
						this.getInitData();
						// メッセージ設定&表示
						self.$store.commit(STORE_INFO_SET_MSG, co_getMessage('CMS0001I'));
					})
				} else {
					// 業務エラー表示
					co_setError(res.data, self.$refs)
					if (!co_isEmpty(res.data[0].errorItems) && !co_isEmpty(self.$refs[res.data[0].errorItems[0]])) {
						self.$nextTick(function () {
							self.$refs[res.data[0].errorItems[0]].focus()
						})
					} else {
						self.setFoucus();
					}
				}
			});
		},
	},
	watch: {
		// formオブジェクト内のすべての変更を監視する
		form: {
			handler: function() {
				this.$store.commit(STORE_VIEW_CHANGED, true);
			},
			deep: true
		},
	},
}
</script>

<style scoped>
	.wavy-line {
		margin-left: 6px;
	}
	.padding-15px {
		padding: 15px;
	}
	.btn-display-change {
		display: flex;
	}
	.right-adjustment {
		margin-right: 7%;
	}
	.margin-right-30 {
		margin-right: 30px;
	}
	.tbl-item--border-row_exist select {
		display: inline;
	}
	.tbl-item--border-row_exist .contents--error-item {
		border: 2px solid #dc8585;
	}
	.height-53px {
		height: 53px;
	}
</style>
