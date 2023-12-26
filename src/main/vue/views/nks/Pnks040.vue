<!--
//================================================================================
// ID: PNKS040
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// VueName: Pnks040
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/13 | ＡＭＣ               | 新規作成
//================================================================================
 -->
<template>
	<div class="view-area">
		<main>
			<div class="contents__block--exist_return">
				<h1 class="header__block-title">企画案内書印刷</h1>
				<div class="contents__wrap--scroll_vertical">
					<!-- エラーメッセージ -->
					<div id="error">
						<error-message />
					</div>
					<div class="padding-15px">
						<table>
							<colgroup>
								<col style="width: 180px" />
								<col style="width: 1500px" />
							</colgroup>
							<tbody>
								<tr>
									<td class="txt--align_r">
										<span class="contents__block-search__label">印刷帳票</span>
									</td>
									<td>
										<list-box
											ref="prtChohyo"
											v-bind:cdContentType="CD_CONTENT_TYPE.SHORTENED_NAME"
											v-bind:listItems="disp.prtChohyoComboxList"
											v-bind:maxLength="15"
											v-bind:name="'印刷帳票'"
											v-bind:requireFlg="true"
											v-bind:tabindex="'1'"
											v-on:blur="onChangePrtChohyo()"
											v-model="form.prtChohyo"
										></list-box>
									</td>
								</tr>
								<tr>
									<td class="txt--align_r">
										<span class="contents__block-search__label">企画番号</span>
									</td>
									<td>
										<code-input
											class="width-100"
											ref="cqProjNoStart"
											v-bind:activeZeroPadding="true"
											v-bind:maxLength="3"
											v-bind:name="'CQ企画番号開始'"
											v-bind:requireFlg="true"
											v-bind:tabindex="'2'"
											v-on:blur="onChangePrtChohyo()"
											v-model="form.cqProjNoStart"
										></code-input>
										<span class="contents__block-search__label">～</span>
										<code-input
											class="width-100"
											ref="cqProjNoEnd"
											v-bind:activeZeroPadding="true"
											v-bind:disabledFlg="disabledFlg.cqProjNoEnd"
											v-bind:maxLength="3"
											v-bind:name="'CQ企画番号終了'"
											v-bind:requireFlg="true"
											v-bind:tabindex="'3'"
											v-model="form.cqProjNoEnd"
										></code-input>
									</td>
								</tr>
								<tr>
									<td class="txt--align_r">
										<span class="contents__block-search__label">メーカー</span>
									</td>
									<td>
										<list-box
											class="width-500"
											ref="makerStart"
											v-bind:blankFlg="true"
											v-bind:cdContentType="CD_CONTENT_TYPE.CODENAME"
											v-bind:listItems="disp.makerComboxList"
											v-bind:maxLength="30"
											v-bind:name="'メーカー開始'"
											v-bind:requireFlg="true"
											v-bind:tabindex="'4'"
											v-model="form.makerStart"
										></list-box>
										<span class="contents__block-search__label">～</span>
										<list-box
											class="width-500"
											ref="makerEnd"
											v-bind:blankFlg="true"
											v-bind:cdContentType="CD_CONTENT_TYPE.CODENAME"
											v-bind:listItems="disp.makerComboxList"
											v-bind:maxLength="30"
											v-bind:name="'メーカー終了'"
											v-bind:requireFlg="false"
											v-bind:tabindex="'5'"
											v-model="form.makerEnd"
										></list-box>
									</td>
								</tr>
								<tr>
									<td class="float-r">
										<span class="contents__block-search__label">連絡事項</span>
									</td>
									<td>
										<text-area-input
											class="width-100per font-size-18px"
											ref="contactJiko"
											v-bind:maxLength="128"
											v-bind:name="'連絡事項'"
											v-bind:style="{ height: '151px' }"
											v-bind:tabindex="'6'"
											v-bind:textType="TEXTINPUT_TYPE.FULL"
											v-model="form.contactJiko"
										></text-area-input>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<div>
											<ul class="tab-list clearfloat width-100per height-300">
												<li class="tab-itom">
													<input
														type="radio"
														id="tabContactaddHokubu"
														name="tab"
														checked="checked"
														ref="tabContactaddHokubu"
													/>
													<label class="test-label width-200" for="tabContactaddHokubu">連絡先（北部）</label>
													<div class="tab-box width-100per tbl-list__wrap">
														<table class="tbl-list--transparent mgn-l-15px">
															<colgroup>
																<col class="width-10per" />
																<col class="width-90per" />
															</colgroup>
															<tbody>
																<br />
																<tr>
																	<td>
																		<span class="contents__block-search__label">連絡先名称</span>
																	</td>
																	<td>
																		<text-input
																			class="width-50per"
																			ref="contactaddNmHokubu"
																			v-bind:maxLength="30"
																			v-bind:name="'連絡先名称（北部）'"
																			v-bind:requireFlg="true"
																			v-bind:tabindex="'7'"
																			v-bind:textType="TEXTINPUT_TYPE.FULL"
																			v-model="form.contactaddNmHokubu"
																		></text-input>
																	</td>
																</tr>
																<tr>
																	<td>
																		<span class="contents__block-search__label">連絡先ＴＥＬ</span>
																	</td>
																	<td>
																		<phone-number-input
																			class="width-35per"
																			ref="contactaddTelHokubu"
																			v-bind:maxLength="15"
																			v-bind:name="'連絡先ＴＥＬ（北部）'"
																			v-bind:requireFlg="true"
																			v-bind:tabindex="'8'"
																			v-model="form.contactaddTelHokubu"
																		></phone-number-input>
																	</td>
																</tr>
																<tr>
																	<td>
																		<span class="contents__block-search__label">連絡先ＦＡＸ</span>
																	</td>
																	<td>
																		<phone-number-input
																			class="width-35per"
																			ref="contactaddFaxHokubu"
																			v-bind:name="'連絡先ＦＡＸ（北部）'"
																			v-bind:maxLength="15"
																			v-bind:requireFlg="true"
																			v-bind:tabindex="'9'"
																			v-model="form.contactaddFaxHokubu"
																		></phone-number-input>
																	</td>
																</tr>
															</tbody>
														</table>
													</div>
												</li>
												<li class="tab-itom">
													<input
														id="tabContactaddNanbu"
														name="tab"
														ref="tabContactaddNanbu"
														type="radio"
													/>
													<label class="test-label width-200" for="tabContactaddNanbu">連絡先（南部）</label>
													<div class="tab-box width-100per tbl-list__wrap">
														<table class="tbl-list--transparent mgn-l-15px">
															<colgroup>
																<col class="width-10per" />
																<col class="width-90per" />
															</colgroup>
															<tbody>
																<br />
																<tr>
																	<td>
																		<span class="contents__block-search__label">連絡先名称</span>
																	</td>
																	<td>
																		<text-input
																			class="width-50per"
																			ref="contactaddNmNanbu"
																			v-bind:maxLength="30"
																			v-bind:name="'連絡先名称（南部）'"
																			v-bind:requireFlg="true"
																			v-bind:tabindex="'10'"
																			v-bind:textType="TEXTINPUT_TYPE.FULL"
																			v-model="form.contactaddNmNanbu"
																		></text-input>
																	</td>
																</tr>
																<tr>
																	<td>
																		<span class="contents__block-search__label">連絡先ＴＥＬ</span>
																	</td>
																	<td>
																		<phone-number-input
																			class="width-35per"
																			ref="contactaddTelNanbu"
																			v-bind:maxLength="15"
																			v-bind:name="'連絡先ＴＥＬ（南部）'"
																			v-bind:requireFlg="true"
																			v-bind:tabindex="'11'"
																			v-model="form.contactaddTelNanbu"
																		></phone-number-input>
																	</td>
																</tr>
																<tr>
																	<td>
																		<span class="contents__block-search__label">連絡先ＦＡＸ</span>
																	</td>
																	<td>
																		<phone-number-input
																			class="width-35per"
																			ref="contactaddFaxNanbu"
																			v-bind:maxLength="15"
																			v-bind:name="'連絡先ＦＡＸ（南部）'"
																			v-bind:requireFlg="true"
																			v-bind:tabindex="'12'"
																			v-model="form.contactaddFaxNanbu"
																		></phone-number-input>
																	</td>
																</tr>
															</tbody>
														</table>
													</div>
												</li>
												<li class="tab-itom">
													<input type="radio" id="tabDelvHokubu" name="tab" ref="tabDelvHokubu" />
													<label class="test-label width-200" for="tabDelvHokubu">納品先（北部）</label>
													<div class="tab-box width-100per tbl-list__wrap">
														<table class="tbl-list--transparent mgn-l-15px">
															<colgroup>
																<col class="width-10per" />
																<col class="width-90per" />
															</colgroup>
															<tbody>
																<br />
																<tr>
																	<td>
																		<span class="contents__block-search__label">納品場所</span>
																	</td>
																	<td>
																		<text-input
																			class="width-50per"
																			ref="delvPlaceHokubu"
																			v-bind:maxLength="30"
																			v-bind:name="'納品場所（北部）'"
																			v-bind:requireFlg="true"
																			v-bind:tabindex="'13'"
																			v-bind:textType="TEXTINPUT_TYPE.FULL"
																			v-model="form.delvPlaceHokubu"
																		></text-input>
																	</td>
																</tr>
																<tr>
																	<td>
																		<span class="contents__block-search__label">納品先名称</span>
																	</td>
																	<td>
																		<text-input
																			class="width-50per"
																			ref="nohinskNmHokubu"
																			v-bind:maxLength="30"
																			v-bind:name="'納品先名称（北部）'"
																			v-bind:requireFlg="true"
																			v-bind:tabindex="'14'"
																			v-bind:textType="TEXTINPUT_TYPE.FULL"
																			v-model="form.nohinskNmHokubu"
																		></text-input>
																	</td>
																</tr>
																<tr>
																	<td>
																		<span class="contents__block-search__label">納品先ＴＥＬ</span>
																	</td>
																	<td>
																		<phone-number-input
																			class="width-35per"
																			ref="nohinskTelHokubu"
																			v-bind:maxLength="15"
																			v-bind:name="'納品先ＴＥＬ（北部）'"
																			v-bind:requireFlg="true"
																			v-bind:tabindex="'15'"
																			v-model="form.nohinskTelHokubu"
																		></phone-number-input>
																	</td>
																</tr>
																<tr>
																	<td>
																		<span class="contents__block-search__label">納品先ＦＡＸ</span>
																	</td>
																	<td>
																		<phone-number-input
																			class="width-35per"
																			ref="nohinskFaxHokubu"
																			v-bind:maxLength="15"
																			v-bind:name="'納品先ＦＡＸ（北部）'"
																			v-bind:requireFlg="true"
																			v-bind:tabindex="'16'"
																			v-model="form.nohinskFaxHokubu"
																		></phone-number-input>
																	</td>
																</tr>
															</tbody>
														</table>
													</div>
												</li>
												<li class="tab-itom">
													<input type="radio" id="tabDelvNanbu" name="tab" ref="tabDelvNanbu" />
													<label class="test-label width-200" for="tabDelvNanbu">納品先（南部）</label>
													<div class="tab-box width-100per tbl-list__wrap">
														<table class="tbl-list--transparent mgn-l-15px">
															<colgroup>
																<col class="width-10per" />
																<col class="width-90per" />
															</colgroup>
															<tbody>
																<br />
																<tr>
																	<td>
																		<span class="contents__block-search__label">納品場所</span>
																	</td>
																	<td>
																		<text-input
																			class="width-50per"
																			ref="delvPlaceNanbu"
																			v-bind:maxLength="30"
																			v-bind:name="'納品場所（南部）'"
																			v-bind:requireFlg="true"
																			v-bind:tabindex="'17'"
																			v-bind:textType="TEXTINPUT_TYPE.FULL"
																			v-model="form.delvPlaceNanbu"
																		></text-input>
																	</td>
																</tr>
																<tr>
																	<td>
																		<span class="contents__block-search__label">納品先名称</span>
																	</td>
																	<td>
																		<text-input
																			class="width-50per"
																			ref="nohinskNmNanbu"
																			v-bind:maxLength="30"
																			v-bind:name="'納品先名称（南部）'"
																			v-bind:requireFlg="true"
																			v-bind:tabindex="'18'"
																			v-bind:textType="TEXTINPUT_TYPE.FULL"
																			v-model="form.nohinskNmNanbu"
																		></text-input>
																	</td>
																</tr>
																<tr>
																	<td>
																		<span class="contents__block-search__label">納品先ＴＥＬ</span>
																	</td>
																	<td>
																		<phone-number-input
																			class="width-35per"
																			ref="nohinskTelNanbu"
																			v-bind:maxLength="15"
																			v-bind:name="'納品先ＴＥＬ（南部）'"
																			v-bind:requireFlg="true"
																			v-bind:tabindex="'19'"
																			v-model="form.nohinskTelNanbu"
																		></phone-number-input>
																	</td>
																</tr>
																<tr>
																	<td>
																		<span class="contents__block-search__label">納品先ＦＡＸ</span>
																	</td>
																	<td>
																		<phone-number-input
																			class="width-35per"
																			ref="nohinskFaxNanbu"
																			v-bind:maxLength="15"
																			v-bind:name="'納品先ＦＡＸ（南部）'"
																			v-bind:requireFlg="true"
																			v-bind:tabindex="'20'"
																			v-model="form.nohinskFaxNanbu"
																		></phone-number-input>
																	</td>
																</tr>
															</tbody>
														</table>
													</div>
												</li>
											</ul>
										</div>
									</td>
								</tr>
							</tbody>
						</table>
						<table>
							<colgroup>
								<col style="width: 180px" />
								<col style="width: 420px" />
								<col style="width: 180px" />
								<col style="width: 220px" />
							</colgroup>
							<tbody>
								<tr>
									<td class="txt--align_r">
										<span class="contents__block-search__label">企画期間</span>
									</td>
									<td style="display: flex">
										<date-input
											class="width-150 txt--align_l"
											ref="scDelvDayStart"
											v-bind:dateType="DATEINPUT_TYPE.YMD"
											v-bind:disabledFlg="disabledFlg.projKknStart"
											v-bind:maxLength="10"
											v-bind:name="'企画期間開始'"
											v-bind:requireFlg="true"
											v-bind:tabindex="'21'"
											v-model="form.projKknStart"
										></date-input>
										<span class="contents__block-search__label" style="padding-top: 8px; margin: 0 18px">～</span>
										<date-input
											class="width-150 txt--align_l"
											ref="scDelvDayEnd"
											v-bind:dateType="DATEINPUT_TYPE.YMD"
											v-bind:disabledFlg="disabledFlg.projKknEnd"
											v-bind:maxLength="10"
											v-bind:name="'企画期間終了'"
											v-bind:requireFlg="true"
											v-bind:tabindex="'22'"
											v-model="form.projKknEnd"
										></date-input>
									</td>
									<td class="txt--align_r">
										<span class="contents__block-search__label">価格回答期限</span>
									</td>
									<td>
										<date-input
											class="width-150 txt--align_l"
											ref="cntrSireKeijoData"
											v-bind:disabledFlg="disabledFlg.kakakuAnsKgn"
											v-bind:dateType="DATEINPUT_TYPE.YMD"
											v-bind:maxLength="10"
											v-bind:name="'価格回答期限'"
											v-bind:requireFlg="true"
											v-bind:tabindex="'23'"
											v-model="form.kakakuAnsKgn"
										></date-input>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</main>
		<footer>
			<div class="contents__block-command-btn--fullsize">
				<div class="footer-button__block">
					<div class="left-block" style="float: left">
						<div class="button-group">
							<button-input
								class="contents__btn-blue"
								v-bind:style="{ 'margin-right': '30px' }"
								v-bind:tabindex="'25'"
								v-bind:value="'戻る'"
								v-on:click="clickReturnBtn()"
							></button-input>
							<button-input
								class="contents__btn-blue"
								v-bind:tabindex="'26'"
								v-bind:value="'取消'"
								v-on:click="clickCancelBtn()"
							></button-input>
						</div>
					</div>
					<div class="center-block" style="float: left; margin-left: 192px">
						<div class="button-group">
							<button-input
								class="contents__btn-blue"
								v-bind:tabindex="'24'"
								v-bind:value="'印刷'"
								v-on:click="clickPrtBtn()"
							></button-input>
						</div>
					</div>
				</div>
			</div>
		</footer>
	</div>
</template>

<script>
import { Core, PdfExport } from "@grapecity/activereports";
import ButtonInput from '@/components/ButtonInput'
import CodeInput from '@/components/CodeInput.vue'
import DateInput from '@/components/DateInput.vue'
import ErrorMessage from '@/components/ErrorMessage.vue'
import ListBox from '@/components/ListBox.vue'
import PhoneNumberInput from '@/components/PhoneNumberInput.vue'
import TextInput from '@/components/TextInput.vue'
import TextAreaInput from '@/components/TextAreaInput'

import { co_post, co_setError } from '@/common/ajaxUtility.js'
import { co_isEmpty } from '@/common/checkUtility.js'

import { 
	CD_CONTENT_TYPE, 
	CNS_NKS_PRINT_KBN,
	DATE_FORMAT, 
	DATEINPUT_TYPE, 
	HTTP_STATUS,
	TEXTINPUT_TYPE
} from '@/const/const.js'

import {
	PATH_PNKS040,
	PATH_PNKS040_SEARCH_CHANGECQSTART,
	PATH_PNKS040_PNKS04001PRINT_KEKAKU,
	PATH_PNKS040_PNKS04001PRINT_KAKUTE
} from '@/const/route.js'

import { 
	STORE_ERROR_CLEAR, 
	STORE_ERROR_IS_ERRFLG, 
	STORE_VIEW_PROGRESS 
} from '@/const/store.js'

export default {
	components: {
		'list-box': ListBox,
		'code-input': CodeInput,
		'date-input': DateInput,
		'text-input': TextInput,
		'button-input': ButtonInput,
		'error-message': ErrorMessage,
		'text-area-input': TextAreaInput,
		'phone-number-input': PhoneNumberInput
	},
	data: function () {
		return {
			form: {
				prtChohyo: '', 				// 印刷帳票
				cqProjNoStart: '', 			// CQ企画番号開始
				cqProjNoEnd: '', 			// CQ企画番号終了
				makerStart: '', 			// メーカー開始
				makerEnd: '', 				// メーカー終了
				contactJiko: '', 			// 連絡事項
				contactaddNmHokubu: '', 	// 連絡先名称（北部）
				contactaddTelHokubu: '', 	// 連絡先ＴＥＬ（北部）
				contactaddFaxHokubu: '', 	// 連絡先ＦＡＸ（北部）
				contactaddNmNanbu: '', 		// 連絡先名称（南部）
				contactaddTelNanbu: '', 	// 連絡先ＴＥＬ（南部）
				contactaddFaxNanbu: '', 	// 連絡先ＦＡＸ（南部）
				delvPlaceHokubu: '', 		// 納品場所（北部）
				nohinskNmHokubu: '', 		// 納品先名称（北部）
				nohinskTelHokubu: '', 		// 納品先ＴＥＬ（北部）
				nohinskFaxHokubu: '', 		// 納品先ＦＡＸ（北部）
				delvPlaceNanbu: '', 		// 納品場所（南部）
				nohinskNmNanbu: '', 		// 納品先名称（南部）
				nohinskTelNanbu: '', 		// 納品先ＴＥＬ（南部）
				nohinskFaxNanbu: '', 		// 納品先ＦＡＸ（南部）
				projKknStart: '', 			// 企画期間開始
				projKknEnd: '', 			// 企画期間終了
				kakakuAnsKgn: '' 			// 価格回答期限
			},
			disp: {
				makerComboxList: [], 		// メーカーコンボボックスList
				prtChohyoComboxList: [
					// 印刷帳票List
					{ code: CNS_NKS_PRINT_KBN.CNS_NKS_PRINT_KBN_KIKAKU, ryakuName: CNS_NKS_PRINT_KBN.CNS_NKS_PRINT_KBN_KIKAKU_NAME },
					{ code: CNS_NKS_PRINT_KBN.CNS_NKS_PRINT_KBN_KAKAKU, ryakuName: CNS_NKS_PRINT_KBN.CNS_NKS_PRINT_KBN_KAKAKU_NAME }
				]
			},
			disabledFlg: {
				// 画面制御
				cqProjNoEnd: false,
				projKknStart: false,
				projKknEnd: false,
				kakakuAnsKgn: false
			},
			CD_CONTENT_TYPE: CD_CONTENT_TYPE,
			DATEINPUT_TYPE: DATEINPUT_TYPE,
			DATE_FORMAT: DATE_FORMAT,
			TEXTINPUT_TYPE: TEXTINPUT_TYPE
		}
	},
	created: function () {
		// 状態管理(ストア)の画面表示にプログレスバーの表示を設定する。
		this.$store.commit(STORE_VIEW_PROGRESS, true)
		this.createdFunction()
	},
	mounted: function () {
		// フォーカスを設定する。
		this.$nextTick(function () {
			this.$refs.prtChohyo.focus()
		})
	},
	methods: {
		createdFunction: function () {
			// エラークリア
			this.$store.commit(STORE_ERROR_CLEAR)
			let self = this
			co_post(PATH_PNKS040, this.form, function (res) {
				if (res.status == HTTP_STATUS.OK) {
					// コンボボックスリストを設定する
					let makerCombox = []
					res.data.makerComboxList.forEach(function (item) {
						makerCombox.push({
							code: item.makercd,
							name: item.makerkj
						})
					})
					self.disp.makerComboxList = makerCombox
					// 余分なデータを削除
					delete res.data.makerComboxList
					// フォームのデータを設定する
					self.form = res.data
					self.form.prtChohyo = CNS_NKS_PRINT_KBN.CNS_NKS_PRINT_KBN_KAKAKU
					self.form.cqProjNoStart = undefined
					self.form.cqProjNoEnd = undefined
					//  画面項目の非表示を設定する
					self.$refs.tabContactaddHokubu.checked = true
					self.$refs.tabContactaddNanbu.checked = false
					self.$refs.tabDelvHokubu.checked = false
					self.$refs.tabDelvNanbu.checked = false
					// 画面制御を設定する
					self.controllerDisabled(true)
					// 状態管理(ストア)の画面表示にプログレスバーの非表示を設定する。
					self.$store.commit(STORE_VIEW_PROGRESS, false)
				}
			})
		},
		// 印刷帳票オンチェンジ
		onChangePrtChohyo: function () {
			// 印刷帳票は企画案内書（計画）場合
			if (this.form.prtChohyo === CNS_NKS_PRINT_KBN.CNS_NKS_PRINT_KBN_KIKAKU) {
				// 画面項目設定する。
				this.form.projKknStart = ''
				this.form.projKknEnd = ''
				this.form.kakakuAnsKgn = ''
				// 画面制御を設定する
				this.controllerDisabled(false)
			} else {
				// 印刷帳票は価格決定依頼書（確定）場合
				// 画面制御を設定する
				this.controllerDisabled(true)
				// クライアント処理『CQ企画番号開始オンチェンジ』を呼出する。
				this.onChangeCqProjNoStart()
			}
		},
		// CQ企画番号開始オンチェンジ
		onChangeCqProjNoStart: function () {
			// CQ企画番号開始は空白の場合
			if (!this.form.cqProjNoStart) {
				this.form.cqProjNoEnd = ''
				this.form.projKknStart = ''
				this.form.projKknEnd = ''
				this.form.kakakuAnsKgn = ''
			} else {
				// 画面表示にプログレスバーの表示を設定する。
				this.$store.commit(STORE_VIEW_PROGRESS, true)
				let self = this
				co_post(PATH_PNKS040_SEARCH_CHANGECQSTART, this.form, function (res) {
					if (res.status == HTTP_STATUS.OK) {
						// 取得したデータを画面の同項目に設定する。
						self.form.cqProjNoEnd = ''
						self.form.projKknStart = res.data.projKknStart
						self.form.projKknEnd = res.data.projKknEnd
						self.form.kakakuAnsKgn = res.data.kakakuAnsKgn
					}
					// 画面表示にプログレスバーの非表示を設定する。
					self.$store.commit(STORE_VIEW_PROGRESS, false)
				})
			}
		},
		// 印刷ボタンクリック
		clickPrtBtn: function () {
			// 状態管理(ストア)の画面表示にプログレスバーの表示を設定する。
			this.$store.commit(STORE_VIEW_PROGRESS, true)
			// エラークリア
			this.$store.commit(STORE_ERROR_CLEAR)

			// 印刷パスを設定する。
			let printPath = ''

			this.$refs.prtChohyo.validate()
			this.$refs.cqProjNoStart.validate()
			// 印刷帳票は価格決定依頼書（確定）場合
			if (this.form.prtChohyo === CNS_NKS_PRINT_KBN.CNS_NKS_PRINT_KBN_KIKAKU) {
				// 印刷帳票は企画案内書（計画）場合
				this.$refs.cqProjNoEnd.validate()
				printPath = PATH_PNKS040_PNKS04001PRINT_KEKAKU
			}
			this.$refs.makerStart.validate()
			this.$refs.contactJiko.validate()
			this.$refs.contactaddNmHokubu.validate()
			this.$refs.contactaddTelHokubu.validate()
			this.$refs.contactaddFaxHokubu.validate()
			this.$refs.contactaddNmNanbu.validate()
			this.$refs.contactaddTelNanbu.validate()
			this.$refs.contactaddFaxNanbu.validate()
			this.$refs.delvPlaceHokubu.validate()
			this.$refs.nohinskNmHokubu.validate()
			this.$refs.nohinskTelHokubu.validate()
			this.$refs.nohinskFaxHokubu.validate()
			this.$refs.delvPlaceNanbu.validate()
			this.$refs.nohinskNmNanbu.validate()
			this.$refs.nohinskTelNanbu.validate()
			this.$refs.nohinskFaxNanbu.validate()

			if (this.form.prtChohyo === CNS_NKS_PRINT_KBN.CNS_NKS_PRINT_KBN_KAKAKU) {
				this.$refs.scDelvDayStart.validate()
				this.$refs.scDelvDayEnd.validate()
				this.$refs.cntrSireKeijoData.validate()
				// 印刷帳票は価格決定依頼書（確定）場合
				printPath = PATH_PNKS040_PNKS04001PRINT_KAKUTE
			}
			
			// エラー表示制御
			if (this.$store.getters[STORE_ERROR_IS_ERRFLG]) {
				this.$store.commit(STORE_VIEW_PROGRESS, false)
				return
			}

			let self = this
			// サーバ処理の『印刷帳票変更』を呼出する。
			co_post(printPath, this.form, function (res) {
				if (res.status == HTTP_STATUS.OK) {
					// 企画案内書を印刷する。
					self.outPutPDF(res.data);
				} else {
					// 状態管理(ストア)の画面表示にプログレスバーの非表示を設定する。
					self.$store.commit(STORE_VIEW_PROGRESS, false)
					// 業務エラー表示
					co_setError(res.data, self.$refs)
					if (!co_isEmpty(self.$refs[res.data[0].errorItems[0]])) {
						self.$nextTick(function () {
							self.$refs[res.data[0].errorItems[0]].focus()
						})
					}
				}
			})
		},
		// PDF帳票印刷
		outPutPDF: async function(excelJsonData) {
						
			// 帳票定義体JSONデータ取得
			
			const reportResponse = await fetch("reports/nks/PNKS04001.rdlx-json");
			const report = await reportResponse.json();
			
			// 帳票データを帳票定義体に入れる
			report.DataSources[0].ConnectionProperties.ConnectString = "jsondata=" + JSON.stringify(excelJsonData);
			
			// リネームする
			const ARJS = Core;
			const PDF = PdfExport;
			
			// 帳票設定
			const settings = {
				info:{
					title:'Invoice_green_ipa',
					author:'コープ九州',
				},
				pdfVersion:'1.7',
			};
			ARJS.FontStore.registerFonts('reports/fonts/fontsConfig.json');
			
			// 帳票定義体をロードする
			const pageReport = new ARJS.PageReport({ language: "ja" });
			await pageReport.load(report);
			
			// 帳票をダウンロードする
			const pageDocument = await pageReport.run();
			const result = await PDF.exportDocument(pageDocument, settings);
			
			// 帳票のyyyyMMddHHmmss日付を取得
			const opt = { 
				year: 'numeric', 
				month: '2-digit', 
				day: '2-digit',   
				hour: 'numeric',
				hour12: false,  
				minute: 'numeric', 
				second: 'numeric',
				fractionalSecondDigits: 3,
			}
			const yyyyMMddHHmmss = new Intl.DateTimeFormat('jp', opt).format(new Date())
				.replaceAll("/", "")
				.replaceAll(":", "")
				.replaceAll(" ", "")
				.replaceAll(".", "");
			
			// 帳票の名を構成する
			const choHyoKbmMap = new Map([
				[ CNS_NKS_PRINT_KBN.CNS_NKS_PRINT_KBN_KIKAKU, CNS_NKS_PRINT_KBN.CNS_NKS_PRINT_KBN_KIKAKU_NAME ],
				[ CNS_NKS_PRINT_KBN.CNS_NKS_PRINT_KBN_KAKAKU, CNS_NKS_PRINT_KBN.CNS_NKS_PRINT_KBN_KAKAKU_NAME ]
			]);
			const choHyoFileName = `${choHyoKbmMap.get(this.form.prtChohyo)}_${yyyyMMddHHmmss}`;
			
			// ダウンロードする 
			result.download(choHyoFileName);
			this.$store.commit(STORE_VIEW_PROGRESS, false);
		},
		// 取消ボタンクリック
		clickCancelBtn: function () {
			// 初期状態にする。
			this.createdFunction()
			// フォーカスを設定する。
			this.$nextTick(function () {
				this.$refs.prtChohyo.focus()
			})
		},
		// 戻るボタンクリック
		clickReturnBtn: function () {
			// エラークリア
			this.$store.commit(STORE_ERROR_CLEAR)
			// 業務管理メニューに遷移する。
			this.$router.back()
		},
		// 画面制御
		controllerDisabled: function (mode) {
			if (mode) {
				this.disabledFlg.cqProjNoEnd = true
				this.disabledFlg.projKknStart = false
				this.disabledFlg.projKknEnd = false
				this.disabledFlg.kakakuAnsKgn = false
			} else {
				this.disabledFlg.cqProjNoEnd = false
				this.disabledFlg.projKknStart = true
				this.disabledFlg.projKknEnd = true
				this.disabledFlg.kakakuAnsKgn = true
			}
		}
	}
}
</script>

<style scoped>
ul {
	padding: 0;
}

.clearfloat {
	zoom: 1;
}

.clearfloat::after {
	display: block;
	clear: both;
	content: '';
	visibility: hidden;
	height: 0;
}

.tab-list {
	position: relative;
}

.tab-list .tab-itom {
	float: left;
	list-style: none;
	margin-right: 4px;
}

.tab-itom .test-label {
	position: relative;
	display: block;
	height: 27px;
	border: 1px solid transparent;
	border-top-left-radius: 5px;
	border-top-right-radius: 5px;
	line-height: 27px;
	text-align: center;
	background: #e6e6e6;
}

.tab-itom .tab-box {
	position: absolute;
	left: 0;
	top: 28px;
	height: 248px;
	border: 1px solid #cbcccc;
	border-radius: 5px;
	border-top-left-radius: 0px;
	background: #f5f5f5;
	z-index: 0;
}

input[type='radio'] {
	position: absolute;
	opacity: 0;
}

input[type='radio']:checked + .test-label {
	border-color: #cbcccc;
	border-bottom-color: #f5f5f5;
	background: #f5f5f5;
	z-index: 10;
}

input[type='radio']:checked ~ .tab-box {
	z-index: 5;
}

.height-300 {
	height: 300px;
}

.font-size-18px {
	font-size: 18px;
}

.padding-15px {
	padding: 15px;
}
</style>
