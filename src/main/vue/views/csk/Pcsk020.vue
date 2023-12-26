<!--
//================================================================================
// ID: PCSK020
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// VueName: Pcsk020
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/18 | ＡＭＣ               | 新規作成
//================================================================================
 -->
<template>
	<div class="view-area">
		<main>
			<div class="contents__block--exist_return">
				<h1 class="header__block-title">CSV出力</h1>
				<!-- エラーメッセージ -->
				<error-message />
				<div class="contents__wrap--scroll_vertical">
					<div class="padding-15px">
						<table>
							<colgroup>
								<col style="width: 150px" />
								<col style="width: 750px" />
							</colgroup>
							<tbody>
								<tr>
									<td class="txt--align_r">ファイル選択：</td>
									<td>
										<list-box
											class="width-200"
											ref="fileSelect"
											v-bind:blankFlg="false"
											v-bind:cdContentType="CD_CONTENT_TYPE.SHORTENED_NAME"
											v-bind:listItems="disp.fileSelectList"
											v-bind:maxLength="4"
											v-bind:name="'ファイル選択'"
											v-bind:requireFlg="true"
											v-bind:tabindex="'1'"
											v-on:change="onchgFileSelectDataChange()"
											v-model="form.fileSelect"
										></list-box>
									</td>
								</tr>
							</tbody>
						</table>
						<table v-if="showFlg.zaikoChose">
							<colgroup>
								<col style="width: 150px" />
								<col style="width: 230px" />
								<col style="width: 300px" />
							</colgroup>
							<tbody>
								<tr>
									<td class="txt--align_r">
										<span class="contents__block-search__label">月度</span>
									</td>
									<td colspan="3">
										<date-input
											class="txt--align_l"
											ref="monthly"
											v-bind:dateType="DATEINPUT_TYPE.YM"
											v-bind:maxLength="7"
											v-bind:name="'月度'"
											v-bind:style="{ width: '150px' }"
											v-bind:tabindex="'6'"
											v-model="form.monthly"
										></date-input>
									</td>
								</tr>
								<tr>
									<td class="txt--align_r">
										<span class="contents__block-search__label">センター</span>
									</td>
									<td>
										<code-input
											class="width-150 txt--align_l"
											ref="cntrFrom"
											v-bind:maxLength="5"
											v-bind:name="'センター_FROM'"
											v-bind:tabindex="'7'"
											v-model="form.cntrFrom"
										></code-input>
									</td>
									<td>
										<span class="unit">～</span>
										<code-input
											class="width-150 txt--align_l"
											ref="cntrTo"
											v-bind:maxLength="5"
											v-bind:name="'センター_TO'"
											v-bind:tabindex="'8'"
											v-model="form.cntrTo"
										></code-input>
									</td>
								</tr>
								<tr>
									<td class="txt--align_r">
										<span class="contents__block-search__label">事業所区分</span>
									</td>
									<td colspan="2">
										<radio-button
											ref="jigyoKbn"
											v-bind:listItems="disp.jigyoKbnList"
											v-bind:name="'事業所区分'"
											v-bind:radioGroupName="'jigyoKbn'"
											v-bind:tabindex="'13'"
											v-model="form.jigyoKbn"
										/>
									</td>
								</tr>
								<tr>
									<td class="txt--align_r">
										<span class="contents__block-search__label">分類</span>
									</td>
									<td>
										<code-input
											class="width-150 txt--align_l"
											ref="catgFrom"
											v-bind:maxLength="2"
											v-bind:name="'分類_FROM'"
											v-bind:tabindex="'16'"
											v-model="form.catgFrom"
										></code-input>
									</td>
									<td>
										<span class="unit">～</span>
										<code-input
											class="width-150 txt--align_l"
											ref="catgTo"
											v-bind:maxLength="2"
											v-bind:name="'分類_TO'"
											v-bind:tabindex="'17'"
											v-model="form.catgTo"
										></code-input>
									</td>
								</tr>
								<tr>
									<td class="txt--align_r">
										<span class="contents__block-search__label">商品コード</span>
									</td>
									<td>
										<code-input
											class="width-150 txt--align_l"
											ref="shinCdFrom"
											v-bind:maxLength="9"
											v-bind:name="'商品コード_FROM'"
											v-bind:tabindex="'18'"
											v-model="form.shinCdFrom"
										></code-input>
									</td>
									<td>
										<span class="unit">～</span>
										<code-input
											class="width-150 txt--align_l"
											ref="shinCdTo"
											v-bind:maxLength="9"
											v-bind:name="'商品コード_TO'"
											v-bind:tabindex="'19'"
											v-model="form.shinCdTo"
										></code-input>
									</td>
								</tr>
								<tr>
									<td class="txt--align_r">
										<span class="contents__block-search__label">登録日</span>
									</td>
									<td>
										<date-input
											class="txt--align_l"
											ref="insDayFrom"
											v-bind:dateType="DATEINPUT_TYPE.YMD"
											v-bind:maxLength="10"
											v-bind:name="'登録日_FROM'"
											v-bind:style="{ width: '150px' }"
											v-bind:tabindex="'20'"
											v-model="form.insDayFrom"
										></date-input>
									</td>
									<td>
										<span class="contents__block-search__label" style="float: left; margin: 9px 5px">～</span>

										<date-input
											class="txt--align_l"
											ref="insDayTo"
											v-bind:dateType="DATEINPUT_TYPE.YMD"
											v-bind:maxLength="10"
											v-bind:name="'登録日_TO'"
											v-bind:style="{ width: '150px' }"
											v-bind:tabindex="'21'"
											v-model="form.insDayTo"
										></date-input>
									</td>
								</tr>
								<tr>
									<td class="txt--align_r">
										<span class="contents__block-search__label">登録者</span>
									</td>
									<td>
										<code-input
											class="width-150 txt--align_l"
											ref="insUserFrom"
											v-bind:activeZeroPadding="false"
											v-bind:maxLength="20"
											v-bind:name="'登録者_FROM'"
											v-bind:tabindex="'22'"
											v-model="form.insUserFrom"
										></code-input>
									</td>
									<td>
										<span class="unit">～</span>
										<code-input
											class="width-150 txt--align_l"
											ref="insUserTo"
											v-bind:activeZeroPadding="false"
											v-bind:maxLength="20"
											v-bind:name="'登録者_TO'"
											v-bind:tabindex="'23'"
											v-model="form.insUserTo"
										></code-input>
									</td>
								</tr>
							</tbody>
						</table>
						<table v-if="showFlg.furikaeDenpyo">
							<colgroup>
								<col style="width: 150px" />
								<col style="width: 230px" />
								<col style="width: 300px" />
							</colgroup>
							<tbody>
								<tr>
									<td class="txt--align_r">
										<span class="contents__block-search__label">伝票日付</span>
									</td>
									<td>
										<date-input
											class="txt--align_l"
											ref="dempyoHizukeFrom"
											v-bind:dateType="DATEINPUT_TYPE.YMD"
											v-bind:maxLength="10"
											v-bind:name="'伝票日付_FROM'"
											v-bind:style="{ width: '150px' }"
											v-bind:tabindex="'4'"
											v-model="form.dempyoHizukeFrom"
										></date-input>
									</td>
									<td>
										<span class="contents__block-search__label" style="float: left; margin: 9px 5px">～</span>
										<date-input
											class="txt--align_l"
											ref="dempyoHizukeTo"
											v-bind:dateType="DATEINPUT_TYPE.YMD"
											v-bind:maxLength="10"
											v-bind:name="'伝票日付_TO'"
											v-bind:style="{ width: '150px' }"
											v-bind:tabindex="'5'"
											v-model="form.dempyoHizukeTo"
										></date-input>
									</td>
								</tr>
								<tr>
									<td class="txt--align_r">
										<span class="contents__block-search__label">月度</span>
									</td>
									<td>
										<date-input
											ref="monthly"
											class="txt--align_l"
											v-bind:dateType="DATEINPUT_TYPE.YM"
											v-bind:maxLength="7"
											v-bind:name="'月度'"
											v-bind:style="{ width: '150px' }"
											v-bind:tabindex="'6'"
											v-model="form.monthly"
										></date-input>
									</td>
								</tr>
								<tr>
									<td class="txt--align_r">
										<span class="contents__block-search__label">出庫センター</span>
									</td>
									<td>
										<code-input
											class="width-150 txt--align_l"
											ref="shukkoCntrFrom"
											v-bind:maxLength="5"
											v-bind:name="'出庫センター_FROM'"
											v-bind:tabindex="'9'"
											v-model="form.shukkoCntrFrom"
										></code-input>
									</td>
									<td>
										<span class="unit">～</span>
										<code-input
											class="width-150 txt--align_l"
											ref="shukkoCntrTo"
											v-bind:maxLength="5"
											v-bind:name="'出庫センター_TO'"
											v-bind:tabindex="'10'"
											v-model="form.shukkoCntrTo"
										></code-input>
									</td>
								</tr>
								<tr>
									<td class="txt--align_r">
										<span class="contents__block-search__label">入庫センター</span>
									</td>
									<td>
										<code-input
											class="width-150 txt--align_l"
											ref="nyukoCntrFrom"
											v-bind:maxLength="5"
											v-bind:name="'入庫センター_FROM'"
											v-bind:tabindex="'11'"
											v-model="form.nyukoCntrFrom"
										></code-input>
									</td>
									<td>
										<span class="unit">～</span>
										<code-input
											class="width-150 txt--align_l"
											ref="nyukoCntrTo"
											v-bind:maxLength="5"
											v-bind:name="'入庫センター_TO'"
											v-bind:tabindex="'12'"
											v-model="form.nyukoCntrTo"
										></code-input>
									</td>
								</tr>
								<tr>
									<td class="txt--align_r">
										<span class="contents__block-search__label">商品コード</span>
									</td>
									<td>
										<code-input
											class="width-150 txt--align_l"
											ref="shinCdFrom"
											v-bind:maxLength="9"
											v-bind:name="'商品コード_FROM'"
											v-bind:tabindex="'18'"
											v-model="form.shinCdFrom"
										></code-input>
									</td>
									<td>
										<span class="unit">～</span>
										<code-input
											class="width-150 txt--align_l"
											ref="shinCdTo"
											v-bind:maxLength="9"
											v-bind:name="'商品コード_TO'"
											v-bind:tabindex="'19'"
											v-model="form.shinCdTo"
										></code-input>
									</td>
								</tr>
								<tr>
									<td class="txt--align_r">
										<span class="contents__block-search__label">登録日</span>
									</td>
									<td>
										<date-input
											class="txt--align_l"
											ref="insDayFrom"
											v-bind:dateType="DATEINPUT_TYPE.YMD"
											v-bind:maxLength="10"
											v-bind:name="'登録日_FROM'"
											v-bind:style="{ width: '150px' }"
											v-bind:tabindex="'20'"
											v-model="form.insDayFrom"
										></date-input>
									</td>
									<td>
										<span class="contents__block-search__label" style="float: left; margin: 9px 5px">～</span>
										<date-input
											class="txt--align_l"
											ref="insDayTo"
											v-bind:dateType="DATEINPUT_TYPE.YMD"
											v-bind:maxLength="10"
											v-bind:name="'登録日_TO'"
											v-bind:style="{ width: '150px' }"
											v-bind:tabindex="'21'"
											v-model="form.insDayTo"
										></date-input>
									</td>
								</tr>
								<tr>
									<td class="txt--align_r">
										<span class="contents__block-search__label">登録者</span>
									</td>
									<td>
										<code-input
											class="width-150 txt--align_l"
											ref="insUserFrom"
											v-bind:activeZeroPadding="false"
											v-bind:maxLength="20"
											v-bind:name="'登録者_FROM'"
											v-bind:tabindex="'22'"
											v-model="form.insUserFrom"
										></code-input>
									</td>
									<td>
										<span class="unit">～</span>
										<code-input
											class="width-150 txt--align_l"
											ref="insUserTo"
											v-bind:activeZeroPadding="false"
											v-bind:maxLength="20"
											v-bind:name="'登録者_TO'"
											v-bind:tabindex="'23'"
											v-model="form.insUserTo"
										></code-input>
									</td>
								</tr>
							</tbody>
						</table>
						<table v-if="showFlg.haikiDenpyo">
							<colgroup>
								<col style="width: 150px" />
								<col style="width: 230px" />
								<col style="width: 300px" />
							</colgroup>
							<tbody>
								<tr>
									<td class="txt--align_r">
										<span class="contents__block-search__label">伝票日付</span>
									</td>
									<td>
										<date-input
											class="txt--align_l"
											ref="dempyoHizukeFrom"
											v-bind:dateType="DATEINPUT_TYPE.YMD"
											v-bind:maxLength="10"
											v-bind:name="'伝票日付_FROM'"
											v-bind:style="{ width: '150px' }"
											v-bind:tabindex="'4'"
											v-model="form.dempyoHizukeFrom"
										></date-input>
									</td>
									<td>
										<span class="contents__block-search__label" style="float: left; margin: 9px 5px">～</span>
										<date-input
											class="txt--align_l"
											ref="dempyoHizukeTo"
											v-bind:dateType="DATEINPUT_TYPE.YMD"
											v-bind:maxLength="10"
											v-bind:name="'伝票日付_TO'"
											v-bind:style="{ width: '150px' }"
											v-bind:tabindex="'5'"
											v-model="form.dempyoHizukeTo"
										></date-input>
									</td>
								</tr>
								<tr>
									<td class="txt--align_r">
										<span class="contents__block-search__label">月度</span>
									</td>
									<td>
										<date-input
											ref="monthly"
											class="width-150 txt--align_l"
											v-bind:dateType="DATEINPUT_TYPE.YM"
											v-bind:maxLength="7"
											v-bind:name="'月度'"
											v-bind:style="{ width: '150px' }"
											v-bind:tabindex="'6'"
											v-model="form.monthly"
										></date-input>
									</td>
								</tr>
								<tr>
									<td class="txt--align_r">
										<span class="contents__block-search__label">センター</span>
									</td>
									<td>
										<code-input
											class="width-150 txt--align_l"
											ref="cntrFrom"
											v-bind:maxLength="5"
											v-bind:name="'センター_FROM'"
											v-bind:tabindex="'7'"
											v-model="form.cntrFrom"
										></code-input>
									</td>
									<td>
										<span class="unit">～</span>
										<code-input
											class="width-150 txt--align_l"
											ref="cntrTo"
											v-bind:maxLength="5"
											v-bind:name="'センター_TO'"
											v-bind:tabindex="'8'"
											v-model="form.cntrTo"
										></code-input>
									</td>
								</tr>
								<tr>
									<td class="txt--align_r">
										<span class="contents__block-search__label">商品コード</span>
									</td>
									<td>
										<code-input
											class="width-150 txt--align_l"
											ref="shinCdFrom"
											v-bind:maxLength="9"
											v-bind:name="'商品コード_FROM'"
											v-bind:tabindex="'18'"
											v-model="form.shinCdFrom"
										></code-input>
									</td>
									<td>
										<span class="unit">～</span>
										<code-input
											class="width-150 txt--align_l"
											ref="shinCdTo"
											v-bind:maxLength="9"
											v-bind:name="'商品コード_TO'"
											v-bind:tabindex="'19'"
											v-model="form.shinCdTo"
										></code-input>
									</td>
								</tr>
								<tr>
									<td class="txt--align_r">
										<span class="contents__block-search__label">登録日</span>
									</td>
									<td>
										<date-input
											class="txt--align_l"
											ref="insDayFrom"
											v-bind:dateType="DATEINPUT_TYPE.YMD"
											v-bind:maxLength="10"
											v-bind:name="'登録日_FROM'"
											v-bind:style="{ width: '150px' }"
											v-bind:tabindex="'20'"
											v-model="form.insDayFrom"
										></date-input>
									</td>
									<td>
										<span class="contents__block-search__label" style="float: left; margin: 9px 5px">～</span>
										<date-input
											class="txt--align_l"
											ref="insDayTo"
											v-bind:dateType="DATEINPUT_TYPE.YMD"
											v-bind:maxLength="10"
											v-bind:name="'登録日_TO'"
											v-bind:style="{ width: '150px' }"
											v-bind:tabindex="'21'"
											v-model="form.insDayTo"
										></date-input>
									</td>
								</tr>
								<tr>
									<td class="txt--align_r">
										<span class="contents__block-search__label">登録者</span>
									</td>
									<td>
										<code-input
											class="width-150 txt--align_l"
											ref="insUserFrom"
											v-bind:activeZeroPadding="false"
											v-bind:maxLength="20"
											v-bind:name="'登録者_FROM'"
											v-bind:tabindex="'22'"
											v-model="form.insUserFrom"
										></code-input>
									</td>
									<td>
										<span class="unit">～</span>
										<code-input
											class="width-150 txt--align_l"
											ref="insUserTo"
											v-bind:activeZeroPadding="false"
											v-bind:maxLength="20"
											v-bind:name="'登録者_TO'"
											v-bind:tabindex="'23'"
											v-model="form.insUserTo"
										></code-input>
									</td>
								</tr>
							</tbody>
						</table>
						<table v-if="showFlg.shoDenpyo">
							<colgroup>
								<col style="width: 150px" />
								<col style="width: 230px" />
								<col style="width: 300px" />
							</colgroup>
							<tbody>
								<tr>
									<td></td>
									<td class="height-45px">
										<radio-button
											ref="varDempyoKbn"
											v-bind:listItems="disp.radioButtonListItems"
											v-bind:name="'諸伝票区分'"
											v-bind:radioGroupName="'varDempyoKbn'"
											v-bind:requireFlg="true"
											v-bind:tabindex="'1'"
											v-model="form.varDempyoKbn"
										/>
									</td>
								</tr>
								<tr>
									<td class="txt--align_r">
										<span class="contents__block-search__label">伝票日付</span>
									</td>
									<td>
										<date-input
											class="txt--align_l"
											ref="dempyoHizukeFrom"
											v-bind:dateType="DATEINPUT_TYPE.YMD"
											v-bind:maxLength="10"
											v-bind:name="'伝票日付_FROM'"
											v-bind:style="{ width: '150px' }"
											v-bind:tabindex="'4'"
											v-model="form.dempyoHizukeFrom"
										></date-input>
									</td>
									<td>
										<span class="contents__block-search__label" style="float: left; margin: 9px 5px">～</span>
										<date-input
											class="txt--align_l"
											ref="dempyoHizukeTo"
											v-bind:dateType="DATEINPUT_TYPE.YMD"
											v-bind:maxLength="10"
											v-bind:name="'伝票日付_TO'"
											v-bind:style="{ width: '150px' }"
											v-bind:tabindex="'5'"
											v-model="form.dempyoHizukeTo"
										></date-input>
									</td>
								</tr>
								<tr>
									<td class="txt--align_r">
										<span class="contents__block-search__label">登録日</span>
									</td>
									<td>
										<date-input
											class="txt--align_l"
											ref="insDayFrom"
											v-bind:dateType="DATEINPUT_TYPE.YMD"
											v-bind:maxLength="10"
											v-bind:name="'登録日_FROM'"
											v-bind:style="{ width: '150px' }"
											v-bind:tabindex="'20'"
											v-model="form.insDayFrom"
										></date-input>
									</td>
									<td>
										<span class="unit" style="float: left; margin: 9px 5px">～</span>
										<date-input
											class="txt--align_l"
											ref="insDayTo"
											v-bind:dateType="DATEINPUT_TYPE.YMD"
											v-bind:maxLength="10"
											v-bind:name="'登録日_TO'"
											v-bind:style="{ width: '150px' }"
											v-bind:tabindex="'21'"
											v-model="form.insDayTo"
										></date-input>
									</td>
								</tr>
								<tr>
									<td class="txt--align_r">
										<span class="contents__block-search__label">登録者</span>
									</td>
									<td>
										<code-input
											class="width-150 txt--align_l"
											ref="insUserFrom"
											v-bind:activeZeroPadding="false"
											v-bind:maxLength="20"
											v-bind:name="'登録者_FROM'"
											v-bind:tabindex="'22'"
											v-model="form.insUserFrom"
										></code-input>
									</td>
									<td>
										<span class="unit">～</span>
										<code-input
											class="width-150 txt--align_l"
											ref="insUserTo"
											v-bind:activeZeroPadding="false"
											v-bind:maxLength="20"
											v-bind:name="'登録者_TO'"
											v-bind:tabindex="'23'"
											v-model="form.insUserTo"
										></code-input>
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
					<div class="left-block">
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
					<div class="center-block">
						<div class="button-group">
							<button-input
								class="contents__btn-blue"
								v-bind:tabindex="'24'"
								v-bind:value="'CSV出力'"
								v-on:click="clickCsvOutputBtn()"
							></button-input>
						</div>
					</div>
				</div>
			</div>
		</footer>
	</div>
</template>

<script>
import ButtonInput from '@/components/ButtonInput'
import CodeInput from '@/components/CodeInput'
import DateInput from '@/components/DateInput'
import ErrorMessage from '@/components/ErrorMessage.vue'
import ListBox from '@/components/ListBox.vue'
import RadioButton from '@/components/RadioButton.vue'

import { co_downloadFile, co_setError } from '@/common/ajaxUtility.js'
import { co_isEmpty } from '@/common/checkUtility.js'
import { CD_CONTENT_TYPE, DATEINPUT_TYPE, HTTP_STATUS } from '@/const/const.js'
import { PATH_PCSK020_OUTPUT } from '@/const/route.js'
import { STORE_ERROR_CLEAR, STORE_ERROR_IS_ERRFLG, STORE_VIEW_PROGRESS } from '@/const/store.js'

export default {
	components: {
		'button-input': ButtonInput,
		'code-input': CodeInput,
		'date-input': DateInput,
		'error-message': ErrorMessage,
		'list-box': ListBox,
		'radio-button': RadioButton
	},
	data: function () {
		return {
			form: {
				fileSelect: 'Z', // ファイル選択
				varDempyoKbn: 'C', // 諸伝票区分
				dempyoHizukeFrom: '', // 伝票日付_FROM
				dempyoHizukeTo: '', // 伝票日付_TO
				monthly: '', // 月度
				cntrFrom: '', // センター_FROM
				cntrTo: '', // センター_TO
				shukkoCntrFrom: '', // 出庫センター_FROM
				shukkoCntrTo: '', // 出庫センター_TO
				nyukoCntrFrom: '', // 入庫センター_FROM
				nyukoCntrTo: '', // 入庫センター_TO
				jigyoKbn: '9', // 事業所区分
				catgFrom: '', // 分類_FROM
				catgTo: '', // 分類_TO
				shinCdFrom: '', // 商品コード_FROM
				shinCdTo: '', // 商品コード_TO
				insDayFrom: '', // 登録日_FROM
				insDayTo: '', // 登録日_TO
				insUserFrom: '', // 登録者_FROM
				insUserTo: '' // 登録者_TO
			},
			disp: {
				fileSelectList: [
					{ code: 'Z', ryakuName: '在庫調整照会' },
					{ code: 'F', ryakuName: '振替伝票照会' },
					{ code: 'H', ryakuName: '廃棄伝票照会' },
					{ code: 'S', ryakuName: '諸伝票照会' }
				],
				jigyoKbnList: [
					{ code: '9', name: '全て' },
					{ code: '0', name: '店舗' },
					{ code: '1', name: '無店舗' }
				],
				radioButtonListItems: [
					{ code: 'C', name: 'Core' },
					{ code: 'A', name: 'AP' }
				]
			},
			showFlg: {
				zaikoChose: true, // 在庫調整照会
				furikaeDenpyo: false, // 振替伝票照会
				haikiDenpyo: false, // 廃棄伝票照会
				shoDenpyo: false // 諸伝票照会
			},
			CD_CONTENT_TYPE: CD_CONTENT_TYPE,
			DATEINPUT_TYPE: DATEINPUT_TYPE
		}
	},
	mounted: function () {
		this.$nextTick(function () {
			this.$refs.fileSelect.focus()
		})
	},
	methods: {
		// ファイル選択クリック
		onchgFileSelectDataChange: function () {
			this.$nextTick(function () {
				switch (this.form.fileSelect) {
					case 'Z':
						this.showFlg.zaikoChose = true
						this.showFlg.furikaeDenpyo = false
						this.showFlg.haikiDenpyo = false
						this.showFlg.shoDenpyo = false
						break
					case 'F':
						this.showFlg.zaikoChose = false
						this.showFlg.furikaeDenpyo = true
						this.showFlg.haikiDenpyo = false
						this.showFlg.shoDenpyo = false
						break
					case 'H':
						this.showFlg.zaikoChose = false
						this.showFlg.furikaeDenpyo = false
						this.showFlg.haikiDenpyo = true
						this.showFlg.shoDenpyo = false
						break
					case 'S':
						this.showFlg.zaikoChose = false
						this.showFlg.furikaeDenpyo = false
						this.showFlg.haikiDenpyo = false
						this.showFlg.shoDenpyo = true
						break
				}
				// 状態管理(ストア)のエラー情報をクリアする。
				this.$store.commit(STORE_ERROR_CLEAR)
				// フォームのデータをクリアします
				this.dataClear()
			})
		},
		// 取消ボタンクリック
		clickCancelBtn: function () {
			// 状態管理(ストア)のエラー情報をクリアする。
			this.$store.commit(STORE_ERROR_CLEAR)

			// フォームのデータをクリアします
			this.dataClear()
			this.form.fileSelect = 'Z'

			// ファイル選択変更オンチェンジ
			this.showFlg.zaikoChose = true
			this.showFlg.furikaeDenpyo = false
			this.showFlg.haikiDenpyo = false
			this.showFlg.shoDenpyo = false

			// フォーカスを設定
			this.$nextTick(function () {
				this.$refs.fileSelect.focus()
			})
		},
		// 戻るボタンクリック
		clickReturnBtn: function () {
			this.$router.back()
		},
		// CSV出力ボタンクリック
		clickCsvOutputBtn: function () {
			// 状態管理(ストア)のエラー情報をクリアする。
			this.$store.commit(STORE_ERROR_CLEAR)
			// プログレスバー表示
			this.$store.commit(STORE_VIEW_PROGRESS, true)
			// ** チェック処理 **
			this.$refs.fileSelect.validate()
			switch (this.form.fileSelect) {
				case 'S':
					this.$refs.varDempyoKbn.validate()
					this.$refs.dempyoHizukeFrom.validate()
					this.$refs.dempyoHizukeTo.validate()
					this.$refs.insDayFrom.validate()
					this.$refs.insDayTo.validate()
					this.$refs.insUserFrom.validate()
					this.$refs.insUserTo.validate()
					break
				case 'F':
					this.$refs.dempyoHizukeFrom.validate()
					this.$refs.dempyoHizukeTo.validate()
					this.$refs.monthly.validate()
					this.$refs.shukkoCntrFrom.validate()
					this.$refs.shukkoCntrTo.validate()
					this.$refs.nyukoCntrFrom.validate()
					this.$refs.nyukoCntrTo.validate()
					this.$refs.shinCdFrom.validate()
					this.$refs.shinCdTo.validate()
					this.$refs.insDayFrom.validate()
					this.$refs.insDayTo.validate()
					this.$refs.insUserFrom.validate()
					this.$refs.insUserTo.validate()
					break
				case 'H':
					this.$refs.dempyoHizukeFrom.validate()
					this.$refs.dempyoHizukeTo.validate()
					this.$refs.monthly.validate()
					this.$refs.cntrFrom.validate()
					this.$refs.cntrTo.validate()
					this.$refs.shinCdFrom.validate()
					this.$refs.shinCdTo.validate()
					this.$refs.insDayFrom.validate()
					this.$refs.insDayTo.validate()
					this.$refs.insUserFrom.validate()
					this.$refs.insUserTo.validate()
					break
				case 'Z':
					this.$refs.monthly.validate()
					this.$refs.cntrFrom.validate()
					this.$refs.cntrTo.validate()
					this.$refs.jigyoKbn.validate()
					this.$refs.catgFrom.validate()
					this.$refs.catgTo.validate()
					this.$refs.shinCdFrom.validate()
					this.$refs.shinCdTo.validate()
					this.$refs.insDayFrom.validate()
					this.$refs.insDayTo.validate()
					this.$refs.insUserFrom.validate()
					this.$refs.insUserTo.validate()
					break
			}

			// ** エラー表示制御 **
			if (this.$store.getters[STORE_ERROR_IS_ERRFLG]) {
				this.$store.commit(STORE_VIEW_PROGRESS, false)
				return
			}

			// CSVファイルをダウンロードするリクエスト
			let self = this
			co_downloadFile(PATH_PCSK020_OUTPUT, this.form, function (res, downloadHandle) {
				// プログレスバー非表示
				self.$store.commit(STORE_VIEW_PROGRESS, false)

				if (res.status != HTTP_STATUS.OK) {
					// 異常終了
					co_setError(res.data, self.$refs)
					if (!co_isEmpty(self.$refs[res.data[0].errorItems[0]])) {
						self.$refs[res.data[0].errorItems[0]].focus()
					}
					return
				}

				// ダウンロード処理
				downloadHandle(res)
			})
		},
		// フォームのデータをクリアします
		dataClear: function () {
			this.form.varDempyoKbn = 'C' // 諸伝票区分
			this.form.dempyoHizukeFrom = '' // 伝票日付_FROM
			this.form.dempyoHizukeTo = '' // 伝票日付_TO
			this.form.monthly = '' // 月度
			this.form.cntrFrom = '' // センター_FROM
			this.form.cntrTo = '' // センター_TO
			this.form.shukkoCntrFrom = '' // 出庫センター_FROM
			this.form.shukkoCntrTo = '' // 出庫センター_TO
			this.form.nyukoCntrFrom = '' // 入庫センター_FROM
			this.form.nyukoCntrTo = '' // 入庫センター_TO
			this.form.jigyoKbn = '9' // 事業所区分
			this.form.catgFrom = '' // 分類_FROM
			this.form.catgTo = '' // 分類_TO
			this.form.shinCdFrom = '' // 商品コード_FROM
			this.form.shinCdTo = '' // 商品コード_TO
			this.form.insDayFrom = '' // 登録日_FROM
			this.form.insDayTo = '' // 登録日_TO
			this.form.insUserFrom = '' // 登録者_FROM
			this.form.insUserTo = '' // 登録者_TO
		}
	}
}
</script>

<style scoped>
.height-45px {
	height: 45px;
}

span + select {
	margin: 0px;
}

:root {
	--display1: block;
	--display2: none;
	--display3: none;
	--display4: none;
}

.display1 {
	display: var(--display1);
}

.display2 {
	display: var(--display2);
}

.display3 {
	display: var(--display3);
}

.display4 {
	display: var(--display4);
}

.padding-15px {
	padding: 15px;
}
</style>
