<!--
//================================================================================
// ID: PEXT020
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// VueName: 仕入・訂正入力
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2022/11/25 |  AMC                 | 新規作成
//================================================================================
 -->
 
<template>
	<div>
		<main id="PEXT020">
			<div class="contents__block--exist_return">
				<h1 class="header__block-title-with-input">
					<table>
						<colgroup>
							<col style="width:460px;">
							<col style="width:1330px;">
						</colgroup>
						<tbody>
							<tr>
								<td>
									仕入・訂正入力
									<list-box
										class="width-150 mgn-l-25px"
										ref="procsShubt"
										v-if="disp.shoriSbtList !== null"
										v-model="form.procsShubt"
										v-bind:name="'処理種別'"
										v-bind:listItems="disp.shoriSbtList"
										v-bind:blankFlg="false"
										v-bind:disabledFlg="disabledFlg.title"
										v-bind:tabindex="'10'" />
									<next-button
										class="mgn-l-15px"
										v-bind:name="'titleButton'"
										v-bind:isNextButtonFlg="!disabledFlg.title"
										v-bind:clickNext="clickTitleNext"
										v-bind:clickReinput="clickTitleReinput"
										v-bind:tabindex="'20'"/>
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
					<div class="contents__block-search">
						<div class="contents__block-search__main">
							<div id="ET020Head01">
								<table>
									<colgroup>
										<col style="width:150px;">
										<col style="width:280px;">
										<col style="width:130px;">
										<col style="width:300px;">
										<col style="width:100px;">
										<col style="width:300px;">
										<col style="width:100px;">
										<col style="width:200px;">
										<col style="width:100px;">
									</colgroup>
									<tbody>
										<tr>
											<td class="txt--align_r">事業所コード</td>
											<td>
												<code-input
													class="width-80 txt--align_r"
													ref="jigyoCd"
													v-model="form.jigyoCd"
													v-bind:name="'事業所コード'"
													v-bind:requireFlg="true"
													v-bind:disabledFlg="disabledFlg.header1"
													v-bind:maxLength="4"
													v-bind:tabindex="'30'"
													v-on:change="changeJigyoCd" />
												<label>{{form.jigyoNm}}</label>
											</td>
											<td class="txt--align_r">分類</td>
											<td class="white-space">
												<code-selector
													ref="catgCd"
													v-model="form.catgCd"
													v-bind:name="'分類'"
													v-bind:reckb="CD_SBT.BUNRUI"
													v-bind:requireFlg="true"
													v-bind:disabledFlg="disabledFlg.header1"
													v-bind:codeShowLength="2"
													v-bind:codeMaxLength="2"
													v-bind:listnocShowLength="12"
													v-bind:cdContentType="CD_CONTENT_TYPE.CODENAME"
													v-bind:tabindex="'40'" />
											</td>
											<td class="txt--align_r">取引区分</td>
											<td class="white-space">
												<code-selector
													ref="toriKbn"
													v-if="disp.toriKbnList !== null"
													v-model="form.toriKbn"
													v-bind:name="'取引区分'"
													v-bind:listItems="disp.toriKbnList"
													v-bind:requireFlg="true"
													v-bind:disabledFlg="disabledFlg.header1 || disabledFlg.toriKbn"
													v-bind:codeMaxLength="2"
													v-bind:codeShowLength="2"
													v-bind:listnocShowLength="10"
													v-bind:cdContentType="CD_CONTENT_TYPE.CODENAME"
													v-bind:blankFlg="false"
													v-bind:tabindex="'50'" />
											</td>
											<td class="txt--align_r">伝票番号</td>
											<td>
												<code-input
													class="width-130 txt--align_r"
													ref="dempyoNo"
													v-model="form.dempyoNo"
													v-bind:name="'伝票番号'"
													v-bind:requireFlg="true"
													v-bind:disabledFlg="disabledFlg.header1"
													v-bind:maxLength="6"
													v-bind:activeZeroPadding="false"
													v-bind:tabindex="'60'" />
											</td>
											<td></td>
										</tr>
										<tr>
											<td>
											</td>
											<td>
												<label class="txt--align_l margin-left_80">{{form.tenNm}}</label>
											</td>
										</tr>
										<tr>
											<td class="txt--align_r">取引先コード</td>
											<td>
												<code-input
													class="width-100 txt--align_r"
													ref="toriskCd"
													v-model="form.toriskCd"
													v-bind:name="'取引先コード'"
													v-bind:requireFlg="true"
													v-bind:disabledFlg="disabledFlg.header1"
													v-bind:maxLength="6"
													v-bind:activeZeroPadding="true"
													v-bind:tabindex="'70'"
													v-on:change="changeToriskCd" />
												<label>{{form.toriskNm}}</label>
											</td>
											<td></td>
											<td></td>
											<td class="txt--align_r">発注日</td>
											<td>
												<date-input
													class="width-130 txt--align_r"
													ref="hachuDay"
													v-model="form.hachuDay"
													v-bind:name="'発注日'"
													v-bind:requireFlg="true"
													v-bind:disabledFlg="disabledFlg.header1 || disabledFlg.hachuDay"
													v-bind:allowOmitedValue="true"
													v-bind:tabindex="'80'" />
											</td>
											<td class="txt--align_r">納品日</td>
											<td>
												<date-input
													class="width-130 txt--align_r"
													ref="delvDay"
													v-model="form.delvDay"
													v-bind:name="'納品日'"
													v-bind:requireFlg="true"
													v-bind:disabledFlg="disabledFlg.header1"
													v-bind:allowOmitedValue="true"
													v-bind:tabindex="'90'" />
											</td>
											<td>
												<next-button
													v-bind:name="'header1Button'"
													v-bind:disabledFlg="disabledFlg.header1 && disabledFlg.header1Button"
													v-bind:isNextButtonFlg="!disabledFlg.header1 || disabledFlg.header1Button"
													v-bind:clickNext="clickHeader1Next"
													v-bind:clickReinput="clickHeader1Reinput"
													v-bind:tabindex="'100'"/>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
							<div id="ET020Head02" class="margin-top-15px">
								<table class="table-layout">
									<colgroup>
										<col style="width:150px;">
										<col style="width:280px;">
										<col style="width:130px;">
										<col style="width:300px;">
										<col style="width:100px;">
										<col style="width:300px;">
										<col style="width:100px;">
										<col style="width:200px;">
										<col style="width:50px;">
										<col style="width:100px;">
									</colgroup>
									<tbody>
										<tr>
											<td class="txt--align_r">訂正区分</td>
											<td class="white-space">
												<code-selector
													ref="teiseiKbn"
													v-if="disp.teiseiKubunList !== null"
													v-model="form.teiseiKbn"
													v-bind:name="'訂正区分'"
													v-bind:listItems="disp.teiseiKubunList"
													v-bind:requireFlg="true"
													v-bind:disabledFlg="disabledFlg.header2"
													v-bind:codeShowLength="2"
													v-bind:codeMaxLength="1"
													v-bind:listnocShowLength="8"
													v-bind:tabindex="'110'" />
											</td>
											<td class="txt--align_r">伝票即時発行</td>
											<td class="white-space">
												<code-selector
													ref="dempyoIssKbn"
													v-if="disp.dempyoHakkoKbnList !== null"
													v-model="form.dempyoIssKbn"
													v-bind:name="'伝票発行区分'"
													v-bind:listItems="disp.dempyoHakkoKbnList"
													v-bind:requireFlg="true"
													v-bind:disabledFlg="disabledFlg.header2"
													v-bind:codeShowLength="2"
													v-bind:codeMaxLength="1"
													v-bind:listnocShowLength="12"
													v-bind:tabindex="'120'" />
											</td>
											<td class="txt--align_r">メッセージ</td>
											<td>
												<text-input
													class="width-170 txt--align_l"
													ref="message"
													v-model="form.message"
													v-bind:name="'メッセージ'"
													v-bind:disabledFlg="disabledFlg.header2"
													v-bind:maxLength="16"
													v-bind:textType="TEXTINPUT_TYPE.HALF"
													v-bind:tabindex="'130'" />
											</td>
											<td class="txt--align_r">実納品日</td>
											<td>
												<date-input
													class="width-130 txt--align_r"
													ref="jitDelvDay"
													v-model="form.jitDelvDay"
													v-bind:name="'実納品日'"
													v-bind:requireFlg="true"
													v-bind:disabledFlg="disabledFlg.header2"
													v-bind:allowOmitedValue="true"
													v-bind:tabindex="'140'" />
											</td>
											<td class="txt--align_r">税率</td>
											<td>
												<number-input
													class="width-60 txt--align_r"
													ref="zeiRate"
													v-model="form.zeiRate"
													v-bind:name="'税率'"
													v-bind:disabledFlg="!disabledFlg.header2 || disabledFlg.zeiRate"
													v-bind:maxLength="2"
													v-bind:numberType="NUMBERINPUT_TYPE.INTEGER"
													v-bind:unit="'%'"
													v-bind:zeroReplaceBlank="true"
													v-bind:tabindex="'150'" />
											</td>
										</tr>
										<tr>
											<td class="txt--align_r">通過納場所コード</td>
											<td colspan="9">
												<code-input
													class="width-80 txt--align_r"
													ref="tukaDelvPlaceCd"
													v-model="form.tukaDelvPlaceCd"
													v-bind:name="'通過納場所コード'"
													v-bind:requireFlg="true"
													v-bind:disabledFlg="disabledFlg.header2"
													v-bind:maxLength="4"
													v-bind:tabindex="'160'"
													v-on:change="changeTukaDelvPlaceCd" />
												<label>{{form.tukaDelvPlaceNm}}</label>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<div class="contents__view--bg_gray">
						<div id="ET020List" class="tbl-list__wrap">
							<table class="tbl-list--border-all_exist wrap--overflow_hidden">
								<colgroup>
									<col style="width:43px" />
									<col style="width:180px" />
									<col style="width:260px" />
									<col style="width:150px" />
									<col style="width:90px" />
									<col style="width:110px" />
									<col style="width:160px" />
									<col style="width:165px" />
									<col style="width:160px" />
									<col style="width:165px" />
									<col style="width:165px" />
									<col style="width:165px" />
								</colgroup>
								<thead>
									<tr>
										<th>
											No.
										</th>
										<th>
											ＪＡＮ/商品コード
										</th>
										<th>
											商品名称
										</th>
										<th>
											規格
										</th>
										<th>
											入  数
										</th>
										<th>
											数  量
										</th>
										<th>
											仕入単価
										</th>
										<th>
											仕入金額
										</th>
										<th>
											原価単価
										</th>
										<th>
											原価金額
										</th>
										<th>
											売単価
										</th>
										<th>
											売価金額
										</th>
									</tr>
								</thead>
								<tbody>
									<tr v-for="(row, index) in this.disp.shohinList" v-bind:key="row.no" v-bind:index="index">
										<td id="listtd" >{{ row.no }}</td>
										<td>
											<code-input
													class="width-170 txt--align_r"
													v-model="row.janShinCd"
													v-bind:name="String(row.no) + '行目の商品コード'"
													v-bind:requireFlg="true"
													v-bind:disabledFlg="disabledFlg.body" 
													v-bind:maxLength="9"
													v-bind:activeZeroPadding="false"
													v-bind:tabindex="String(row.no * 1000 + 1)"
													v-bind:ref="'janShinCd' + index"
													v-on:change="changeJanShinCd(index)"
													v-on:keydown.enter="keydownEnterJanShinCd(index, $event)" />
										</td>
										<td>
											{{ row.shinNm }}
										</td>
										<td>
											{{ row.kikaku }}
										</td>
										<td>
											<number-input
													class="width-80 txt--align_r"
													v-model="row.irisu"
													v-bind:name="String(row.no) + '行目の入数'"
													v-bind:requireFlg="true"
													v-bind:disabledFlg="disabledFlg.body"
													v-bind:maxLength="4"
													v-bind:numberType="NUMBERINPUT_TYPE.INTEGER"
													v-bind:zeroReplaceBlank="true"
													v-bind:tabindex="String(row.no * 1000 + 2)"
													v-bind:ref="'irisu' + index" />
										</td>
										<td>
											<number-input
												class="width-100 txt--align_r"
												v-model="row.suryo"
												v-bind:name="String(row.no) + '行目の数量'"
												v-bind:requireFlg="true"
												v-bind:disabledFlg="disabledFlg.body"
												v-bind:maxLength="6"
												v-bind:decimalLength="1"
												v-bind:numberType="NUMBERINPUT_TYPE.COMMA_ZERO_DECIMAL_OMMIT"
												v-bind:zeroReplaceBlank="true"
												v-bind:tabindex="String(row.no * 1000 + 3)"
												v-bind:ref="'suryo' + index"
												v-on:change="changeSuryo(index)" />
										</td>
										<td>
											<number-input
												class="width-150 txt--align_r"
												v-model="row.sireTan"
												v-bind:name="String(row.no) + '行目の仕入単価'"
												v-bind:requireFlg="true"
												v-bind:disabledFlg="disabledFlg.body"
												v-bind:maxLength="9"
												v-bind:decimalLength="2"
												v-bind:numberType="NUMBERINPUT_TYPE.COMMA_ZERO_DECIMAL_OMMIT"
												v-bind:zeroReplaceBlank="true"
												v-bind:tabindex="String(row.no * 1000 + 4)"
												v-bind:ref="'sireTan' + index"
												v-on:change="changeSireTan(index)" />
										</td>
										<td>
											<number-input
												class="width-155 txt--align_r"
												v-model="row.sireGaku"
												v-bind:name="String(row.no) + '行目の仕入金額'"
												v-bind:requireFlg="true"
												v-bind:disabledFlg="disabledFlg.body"
												v-bind:maxLength="9"
												v-bind:numberType="NUMBERINPUT_TYPE.INTEGER"
												v-bind:zeroReplaceBlank="true"
												v-bind:tabindex="String(row.no * 1000 + 5)"
												v-bind:ref="'sireGaku' + index" />
										</td>
										<td>
											<number-input
												class="width-150 txt--align_r"
												v-model="row.genkaTanka"
												v-bind:name="String(row.no) + '行目の原単価'"
												v-bind:requireFlg="true"
												v-bind:disabledFlg="disabledFlg.body"
												v-bind:maxLength="9"
												v-bind:decimalLength="2"
												v-bind:numberType="NUMBERINPUT_TYPE.COMMA_ZERO_DECIMAL_OMMIT"
												v-bind:zeroReplaceBlank="true"
												v-bind:tabindex="String(row.no * 1000 + 6)"
												v-bind:ref="'genkaTanka' + index" 
												v-on:change="changeGenkaTanka(index)" />
										</td>
										<td>
											<number-input
												class="width-155 txt--align_r"
												v-model="row.genkaGaku"
												v-bind:name="String(row.no) + '行目の原価金額'"
												v-bind:requireFlg="true"
												v-bind:disabledFlg="disabledFlg.body"
												v-bind:maxLength="9"
												v-bind:numberType="NUMBERINPUT_TYPE.INTEGER"
												v-bind:zeroReplaceBlank="true"
												v-bind:tabindex="String(row.no * 1000 + 7)"
												v-bind:ref="'genkaGaku' + index" />
										</td>
										<td>
											<number-input
												class="width-150 txt--align_r"
												v-model="row.uriTanka"
												v-bind:name="String(row.no) + '行目の売単価'"
												v-bind:requireFlg="true"
												v-bind:disabledFlg="disabledFlg.body"
												v-bind:maxLength="9"
												v-bind:decimalLength="2"
												v-bind:numberType="NUMBERINPUT_TYPE.COMMA_ZERO_DECIMAL_OMMIT"
												v-bind:zeroReplaceBlank="true"
												v-bind:tabindex="String(row.no * 1000 + 8)"
												v-bind:ref="'uriTanka' + index"
												v-on:change="changeUriTanka(index)" />
										</td>
										<td>
											<number-input
												class="width-155 txt--align_r"
												v-model="row.baikaGaku"
												v-bind:name="String(row.no) + '行目の売価金額'"
												v-bind:requireFlg="true"
												v-bind:disabledFlg="disabledFlg.body"
												v-bind:maxLength="9"
												v-bind:numberType="NUMBERINPUT_TYPE.INTEGER"
												v-bind:zeroReplaceBlank="true"
												v-bind:tabindex="String(row.no * 1000 + 9)"
												v-bind:ref="'baikaGaku' + index" />
										</td>
									</tr>
									<tr>
										<td colspan="7" class="border-side width-155 txt--align_r">金額合計：</td>
										<td class="txt--align_r"><label class="padding-right"> {{kingakuGokeiSire}} </label></td>
										<td class="txt--align_r"></td>
										<td class="txt--align_r"><label class="padding-right">{{kingakuGokeiGenka}}</label></td>
										<td class="txt--align_r"></td>
										<td class="txt--align_r"><label class="padding-right">{{kingakuGokeiBaika}}</label></td>
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
						<div class="button-group">
							<button-input
									class="contents__btn-blue"
									v-bind:value="'戻る'"
									v-bind:name="'backButton'"
									v-bind:tabindex="'7030'"
									v-on:click="clickBack" />
							<button-input
									class="contents__btn-blue"
									v-bind:value="'クリア'"
									v-bind:name="'clearButton'"
									v-bind:tabindex="'7020'"
									v-on:click="clickClear" />
						</div>
					</div>
					<div class="center-block">
					</div>
					<div class="right-block">
						<div class="button-group">
							<button-input
									class="contents__btn-blue"
									ref="saveButton"
									v-bind:value="'保存'"
									v-bind:name="'saveButton'"
									v-bind:disabledFlg="disabledFlg.saveButton"
									v-bind:tabindex="'7010'"
									v-on:click="clickSave"
									v-shortkey.focus="['shift', 'enter']"/>
						</div>
					</div>
				</div>
			</div>
		</footer>
	</div>
</template>
<script>
import ButtonInput from '@/components/ButtonInput.vue';
import CodeInput from '@/components/CodeInput.vue';
import CodeSelector from '@/components/CodeSelector.vue';
import DateInput from '@/components/DateInput.vue';
import ErrorMessage from '@/components/ErrorMessage.vue';
import InfoMessage from '@/components/InfoMessage.vue';
import ListBox from '@/components/ListBox.vue';
import NextButton from '@/components/NextButton.vue';
import NumberInput from '@/components/NumberInput.vue';
import TextInput from '@/components/TextInput.vue';

import {
	co_post,
	co_put,
	co_setError,
} from '@/common/ajaxUtility.js';
import { co_isEmpty } from '@/common/checkUtility.js';
import { co_confirm } from '@/common/dialogUtility.js';
import { co_getMessage } from '@/common/messageUtility.js';
import { co_getShoriShubetsuAUT } from '@/common/shoriShubetsuUtility.js';

import {
	CD_CONTENT_TYPE,
	CD_SBT,
	DATEINPUT_TYPE,
	DEMPYOHAKKOKBN,
	DIALOG_BUTTON_LIST_KEY,
	HTTP_STATUS,
	NUMBERINPUT_TYPE,
	SHORISBT,
	TEISEIKUBUN,
	TEXTINPUT_TYPE,
	TORIKBN,	
} from '@/const/const.js';
import {
	PATH_PEXT020_ADD,
	PATH_PEXT020_JIGYOSHO,
	PATH_PEXT020_SEARCH,
	PATH_PEXT020_SEARCH_SHOHIN,
	PATH_PEXT020_TORIHIKISAKI,
	PATH_PEXT020_TUKADELVPLACE,
	PATH_PEXT020_UPDATEDEMPYOCHECK,
	PATH_PSYS020,
} from '@/const/route.js';
import {
	STORE_ERROR_CLEAR,
	STORE_ERROR_IS_ERRFLG,
	STORE_INFO_CLEAR,
	STORE_INFO_SET_MSG,
	STORE_VIEW_CHANGED,
	STORE_VIEW_HAS_CHANGED,
	STORE_VIEW_PROGRESS,
	STORE_MENUPTN_GET_KENGEN_KBN,
} from '@/const/store.js';

export default {
	components: {
		'button-input': ButtonInput,
		'code-input': CodeInput,
		'code-selector': CodeSelector,
		'date-input': DateInput,
		'error-message': ErrorMessage,
		'info-message': InfoMessage,
		'list-box': ListBox,
		'next-button': NextButton,
		'number-input': NumberInput,
		'text-input': TextInput,
	},
	data: function() {
		return {
			form: {
				procsShubt: '', 		// 処理種別
				jigyoCd:'', 			// 事業所コード
				jigyoNm:'', 			// 事業所名
				tenNm:'', 				// 事業所名
				catgCd:'',				// 分類コード
				toriKbn:'',				// 取引区分
				toriskCd:'',			// 取引先コード
				toriskNm:'',			// 取引先名
				dempyoNo:null,			// 伝票番号
				teiseiKbn:'',			// 訂正区分
				dempyoIssKbn:'',		// 伝票即時発行区分
				hachuDay:'',			// 発注日
				delvDay:'',				// 納品日
				jitDelvDay:'',			// 実納品日
				tukaDelvPlaceCd:'',		// 通過納場所コード
				tukaDelvPlaceNm:'',		// 通過納場所名
				message:'',				// メッセージ
				zeiRate:null,			// 税率
				shohinList: null,		// 商品明細
				itemIndex: null,		// 明細番号
				warningList:null,		// 警告リスト
			},
			disp: {
				shoriSbtList:null,
				toriKbnList: [
					// 取引区分リスト
					{code: TORIKBN.SHIIRE, name: TORIKBN.SHIIRE_NAME},
					{code: TORIKBN.HENPIN, name: TORIKBN.HENPIN_NAME},
					{code: TORIKBN.SHIIRENEMASHI, name: TORIKBN.SHIIRENEMASHI_NAME},
					{code: TORIKBN.SHIIRENEBIKI, name: TORIKBN.SHIIRENEBIKI_NAME},
					{code: TORIKBN.REBATE, name: TORIKBN.REBATE_NAME},
					{code: TORIKBN.GENKINSHIIREOK, name: TORIKBN.GENKINSHIIREOK_NAME},
				],

				teiseiKubunList: [
					// 訂正区分リスト
					{code: TEISEIKUBUN.TA_CD, name: TEISEIKUBUN.TA_NAME},
					{code: TEISEIKUBUN.NOTA_CD, name: TEISEIKUBUN.NOTA_NAME},
				],
				dempyoHakkoKbnList:[
					// 伝票発行区分リスト
					{code: DEMPYOHAKKOKBN.HAKO_CD, name: DEMPYOHAKKOKBN.HAKO_NAME},
					{code: DEMPYOHAKKOKBN.NOHAKO_CD, name: DEMPYOHAKKOKBN.NOHAKO_NAME},
				],
				shohinList: null,
			},
			disabledFlg: {
				// 入力エリア
				title: false,
				header1: true,
				haader2: true,
				body: true,
				// 個別項目指定
				toriKbn: true,
				hachuDay: true,
				header1Button: true,
				zeiRate: true,
				saveButton: true,
			},
			CD_CONTENT_TYPE: CD_CONTENT_TYPE,
			CD_SBT: CD_SBT,
			DATEINPUT_TYPE: DATEINPUT_TYPE,
			TEXTINPUT_TYPE: TEXTINPUT_TYPE,
			NUMBERINPUT_TYPE:NUMBERINPUT_TYPE,
		}
	},
	created: function() {
		this.$store.commit(STORE_VIEW_PROGRESS, true);

		// 処理種別リスト 追加・訂正・税率変更バージョン
		this.disp.shoriSbtList = co_getShoriShubetsuAUT(this.$store.getters[STORE_MENUPTN_GET_KENGEN_KBN]());

		// ** 画面項目初期化 **
		this.initHeader1();
		this.initHeader2();
		this.initBody();
			
		// ** 活性制御 **
		this.controlDisabledFlg('title');

		this.$nextTick(function() {
			// ** フォーカス設定 **
			this.$refs.procsShubt.focus();
			
			this.$store.commit(STORE_VIEW_CHANGED, false);
			this.$store.commit(STORE_VIEW_PROGRESS, false);
		});
	},
	computed: {
		kingakuGokeiSire() {
			let sum = 0;
			if (!co_isEmpty(this.disp.shohinList)) {
				for (const item of this.disp.shohinList) {
					if (co_isEmpty(item.janShinCd)) {
						continue;
					}
					sum += Number(item.sireGaku);
				}
			}

			if (isNaN(sum)) {
				return '';
			}
	
			sum = Math.round(sum);
			return sum.toLocaleString();
		},
		kingakuGokeiGenka() {
			let sum = 0;
			if (!co_isEmpty(this.disp.shohinList)) {
				for (const item of this.disp.shohinList) {
					if (co_isEmpty(item.janShinCd)) {
						continue;
					}
					sum += Number(item.genkaGaku);
				}
			}

			if (isNaN(sum)) {
				return '';
			}

			sum = Math.round(sum);
			return sum.toLocaleString();
		},
		kingakuGokeiBaika() {
			let sum = 0;
			if (!co_isEmpty(this.disp.shohinList)) {
				for (const item of this.disp.shohinList) {
					if (co_isEmpty(item.janShinCd)) {
						continue;
					}
					sum += Number(item.baikaGaku);
				}
			}

			if (isNaN(sum)) {
				return '';
			}

			sum = Math.round(sum);
			return sum.toLocaleString();
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
	methods: {
		// ▼ --------------------------------------- 各入力イベント --------------------------------------- ▼
		// click - 処理種別ボタン(次へ)
		clickTitleNext: function() {

			// ** 初期化処理 **
			this.$store.commit(STORE_INFO_CLEAR);

			// ** 活性制御 **
			this.controlDisabledFlg('header1');

			this.$nextTick(function(){
				// ** フォーカス設定 **
				this.$refs.jigyoCd.focus();
			});
		},
		// click - 処理種別ボタン(再入力)
		clickTitleReinput: function() {

			// ** 初期化処理 **
			this.$store.commit(STORE_INFO_CLEAR);

			if (this.$store.getters[STORE_VIEW_HAS_CHANGED]) {

				// 確認ダイアログを表示
				let self = this;
				co_confirm(co_getMessage('CMS0003W'), function(btnValue) {
					if (btnValue == DIALOG_BUTTON_LIST_KEY.CANCEL) {
						self.$store.commit(STORE_VIEW_PROGRESS, false);
						return;
					}

					// ** 初期化処理 **
					self.$store.commit(STORE_ERROR_CLEAR);
					self.initHeader1();
					self.initHeader2();
					self.initBody();

					// ** 活性制御 **
					self.controlDisabledFlg('title');

					self.$nextTick(function() {
						// ** フォーカス設定 **
						self.$refs.procsShubt.focus();

						self.$store.commit(STORE_VIEW_CHANGED, false);
					});
				});
			} else {
				// ** 初期化処理 **
				this.$store.commit(STORE_ERROR_CLEAR);
				this.initHeader1();
				this.initHeader2();
				this.initBody();
				
				// ** 活性制御 **
				this.controlDisabledFlg('title');

				this.$nextTick(function(){
					// ** フォーカス設定 **
					this.$refs.procsShubt.focus();

					this.$store.commit(STORE_VIEW_CHANGED, false);
				});
			}
		},
		// click - ヘッダ部1ボタン(次へ)
		clickHeader1Next: function(){
			this.$store.commit(STORE_VIEW_PROGRESS, true);

			// ** 初期化処理 **
			this.$store.commit(STORE_ERROR_CLEAR);
			this.form.warningList = null;
			this.$store.commit(STORE_INFO_CLEAR);

			// TODO: テストコ―ド
			this.form.tenCd = '1301';
			
			// ** チェック処理 **
			// ヘッダ部１
			this.$refs.jigyoCd.validate();
			this.$refs.catgCd.validate();
			if (this.form.procsShubt == SHORISBT.ADD_CD) {
				this.$refs.toriKbn.validate();
				this.$refs.hachuDay.validate();
			} else { 
				this.$refs.dempyoNo.validate();
			}
			this.$refs.toriskCd.validate();
			this.$refs.delvDay.validate();

			// ** エラー表示制御 **
			if (this.$store.getters[STORE_ERROR_IS_ERRFLG]) {
				this.$store.commit(STORE_VIEW_PROGRESS, false);
				return;
			}

			// ** 伝票取得処理 **
			let self = this;
			co_post(PATH_PEXT020_SEARCH, this.form, function(res) {
				// 正常終了
				if (res.status == HTTP_STATUS.OK) {
					if ( res.data != null ) {
						if (self.form.procsShubt == SHORISBT.ADD_CD) {
							self.form.warningList = res.data.warningList;
						} else if (self.form.procsShubt == SHORISBT.UPDATE_CD
								|| self.form.procsShubt == SHORISBT.TAXUPDATE_CD) {

							self.form.toriKbn =  res.data.toriKbn;
							self.form.teiseiKbn =  res.data.teiseiKbn;
							self.form.dempyoIssKbn = res.data.dempyoIssKbn;
							self.form.hachuDay = res.data.hachuDay.toString();
							self.form.jitDelvDay = res.data.jitDelvDay.toString();
							self.form.message = res.data.message;
							self.form.zeiRate = res.data.zeiRate;
							self.form.tukaDelvPlaceCd = res.data.tukaDelvPlaceCd;
							self.form.tukaDelvPlaceNm = res.data.tukaDelvPlaceNm;
							self.disp.shohinList = self.fillShohinList(res.data.shohinList);
						}

						// ** 確認ダイアログ制御 **
						if (!co_isEmpty(self.form.warningList) && self.form.warningList.length > 0) {
							// 確認メッセージ作成
							// FIXME: header1 → header2への遷移でこのメッセージはおかしい。
							let warningMessage = '保存前に以下の確認が必要です。\n';
							self.form.warningList.forEach( function( item ){
								warningMessage = warningMessage + '\n・' + item;
							});
							warningMessage = warningMessage + '\n続けますか？' 

							// 確認ダイアログ表示
							co_confirm(warningMessage, function(btnValue) {
								if (btnValue == DIALOG_BUTTON_LIST_KEY.OK) {

									// ** 活性制御 **
									self.controlDisabledFlg('header2');

									self.$nextTick(function() {
										// ** フォーカス設定 ** 
										if (self.form.procsShubt == SHORISBT.ADD_CD
												|| self.form.procsShubt == SHORISBT.UPDATE_CD ) {

											self.$refs.teiseiKbn.focus();
										} else if (self.form.procsShubt == SHORISBT.TAXUPDATE_CD) {
											self.$refs.zeiRate.focus();
										}

										self.$store.commit(STORE_VIEW_CHANGED, false)
										self.$store.commit(STORE_VIEW_PROGRESS, false);
									});
								} else {
									self.$store.commit(STORE_VIEW_PROGRESS, false);
								}
							});
						} else {

							// ** 活性制御 **
							self.controlDisabledFlg('header2');

							self.$nextTick(function() {
								// ** フォーカス設定 ** 
								if (self.form.procsShubt == SHORISBT.ADD_CD
										|| self.form.procsShubt == SHORISBT.UPDATE_CD ) {

									self.$refs.teiseiKbn.focus();
								} else if (self.form.procsShubt == SHORISBT.TAXUPDATE_CD) {
									self.$refs.zeiRate.focus();
								}

								self.$store.commit(STORE_VIEW_CHANGED, false)
								self.$store.commit(STORE_VIEW_PROGRESS, false);
							});
						}
					}
				} else {
					// 異常終了
					co_setError(res.data, self.$refs)

					// ** フォーカス設定 **
					if (!co_isEmpty(res.data[0].errorItems) && !co_isEmpty(self.$refs[res.data[0].errorItems[0]])) {
						self.$refs[res.data[0].errorItems[0]].focus();
					} else {
						self.$refs['delvDay'].focus();
					}

					self.$store.commit(STORE_VIEW_PROGRESS, false);
				}
			});
		},
		// click - ヘッダ部1ボタン(再入力)
		clickHeader1Reinput: function() {
			this.$store.commit(STORE_VIEW_PROGRESS, true);

			// ** 初期化処理 **
			this.$store.commit(STORE_INFO_CLEAR);

			// ** 確認ダイアログ制御 **
			if (this.$store.getters[STORE_VIEW_HAS_CHANGED]) {
				// 確認ダイアログを表示
				let self = this;
				co_confirm(co_getMessage('CMS0003W'), function(btnValue){
					if (btnValue == DIALOG_BUTTON_LIST_KEY.CANCEL) {
						self.$store.commit(STORE_VIEW_PROGRESS, false);
						return;
					}

					// ** 初期化処理 **
					self.$store.commit(STORE_ERROR_CLEAR);
					self.initHeader2();
					self.initBody();
					if (self.form.procsShubt != SHORISBT.ADD_CD) {
						self.form.toriKbn = '';
						self.form.hachuDay = '';
					}

					// ** 活性制御 **
					self.controlDisabledFlg('header1');

					self.$nextTick(function(){
						// ** フォーカス設定 **
						self.$refs.jigyoCd.focus();

						self.$store.commit(STORE_VIEW_PROGRESS, false);
					});

				});
			} else {
				// ** 初期化処理 **
				this.$store.commit(STORE_ERROR_CLEAR);
				this.initHeader2();
				this.initBody();
				if (this.form.procsShubt != SHORISBT.ADD_CD) {
					this.form.toriKbn = '';
					this.form.hachuDay = '';
				}

				// ** 活性制御 **
				this.controlDisabledFlg('header1');

				this.$nextTick(function() {
					// ** フォーカス設定 **
					this.$refs.jigyoCd.focus();

					this.$store.commit(STORE_VIEW_PROGRESS, false);
				});

			}
		},
		// click - 戻る
		clickBack: function() {
			this.$store.commit(STORE_INFO_CLEAR);
			
			if (this.$store.getters[STORE_VIEW_HAS_CHANGED]){
				// 確認ダイアログを表示
				let self = this;
				co_confirm(co_getMessage('CMS0002W'), function(btnValue){
					if (btnValue == DIALOG_BUTTON_LIST_KEY.OK) {
						self.$router.push(PATH_PSYS020);
					}
				});
			} else {
				this.$router.push(PATH_PSYS020);
			}
		},
		// click - クリア
		clickClear: function() {
			this.$store.commit(STORE_INFO_CLEAR);
			
			if (this.$store.getters[STORE_VIEW_HAS_CHANGED]){
				// 確認ダイアログを表示
				let self = this;
				co_confirm(co_getMessage('CMS0003W'), function(btnValue) {
					if (btnValue == DIALOG_BUTTON_LIST_KEY.OK) {
						// ** 初期化処理 **
						self.initHeader1();
						self.initHeader2();
						self.initBody();
						
						// ** 活性制御 **
						self.controlDisabledFlg('title');
						
						self.$nextTick(function() {
							// ** フォーカス設定 **
							self.$refs.procsShubt.focus();

							self.$store.commit(STORE_ERROR_CLEAR);
							self.$store.commit(STORE_VIEW_CHANGED, false);
						});
					}
				});
			} else {
				// ** 初期化処理 **
				this.initHeader1();
				this.initHeader2();
				this.initBody();
				
				// ** 活性制御 **
				this.controlDisabledFlg('title');
				
				this.$nextTick(function() {
					// ** フォーカス設定 **
					this.$refs.procsShubt.focus();
					
					this.$store.commit(STORE_ERROR_CLEAR);
					this.$store.commit(STORE_VIEW_CHANGED, false);
				});
			}
		},
		// click - 保存
		clickSave: function() {
			this.$store.commit(STORE_VIEW_PROGRESS, true);

			this.$store.commit(STORE_ERROR_CLEAR);

			// TODO: テストコ―ド
			if (co_isEmpty(this.form.dempyoNo)) {
				let dempyoNo = 999999 - Math.trunc(Math.random()*100);
				this.form.dempyoNo = String(dempyoNo);
			}

			// ** チェック処理 **
			// ヘッダ部２
			this.$refs.teiseiKbn.validate();
			this.$refs.dempyoIssKbn.validate();
			this.$refs.jitDelvDay.validate();
			this.$refs.tukaDelvPlaceCd.validate();
			this.$refs.zeiRate.validate();
			// ボディ部
			let checkNo = 0;
			for (let i = 0; i < this.disp.shohinList.length; i++) {
				if (!co_isEmpty(this.disp.shohinList[i].janShinCd)) {
					checkNo += 1;
					this.$refs['irisu'+i][0].validate();
					this.$refs['suryo'+i][0].validate();
					this.$refs['sireTan'+i][0].validate();
					this.$refs['sireGaku'+i][0].validate();
					this.$refs['genkaTanka'+i][0].validate();
					this.$refs['genkaGaku'+i][0].validate();
					this.$refs['uriTanka'+i][0].validate();
					this.$refs['baikaGaku'+i][0].validate();
				} else {
					this.clearShohin(i);
				}
			}
			if (checkNo == 0) {
				// 明細未入力の場合は必須チェックエラーを出す
				this.$refs.janShinCd0[0].validate();
			}

			// TODO: テストコ―ド
			this.form.tenCd = '1301';

			// ** エラー表示制御 **
			if ( this.$store.getters[STORE_ERROR_IS_ERRFLG]) {
				this.$store.commit(STORE_VIEW_PROGRESS, false);
				return;
			}

			// ** 明細空行除去処理 **
			let shohinList = new Array();
			for (let i = 0; i < this.disp.shohinList.length; i++) {
				if (co_isEmpty(this.disp.shohinList[i].janShinCd)) {
					continue;
				}	
				shohinList.push(this.disp.shohinList[i]);
				shohinList[shohinList.length-1].no = shohinList.length;
			}
			this.form.shohinList = [...shohinList];
			this.disp.shohinList = this.fillShohinList(shohinList);
			
			// ** 更新前チェック処理 **
			let self = this;
			co_post(PATH_PEXT020_UPDATEDEMPYOCHECK, this.form, function(res) {
				if (res.status == HTTP_STATUS.OK) {
					if (!co_isEmpty(res.data.warningList) && res.data.warningList.length > 0) {
						let warningMessage = '保存前に以下の確認が必要です。\n';
						res.data.warningList.forEach( function( item ){
							warningMessage = warningMessage + '\n・' + item;
						});
						warningMessage = warningMessage + '\n続けますか？' 
					
						// 警告・確認ダイアログの表示判定
						co_confirm(warningMessage, function(btnValue) {
							if (btnValue == DIALOG_BUTTON_LIST_KEY.CANCEL) {
								self.$store.commit(STORE_VIEW_PROGRESS, false);
								return;
							}

							// ** 保存処理 **
							co_put(PATH_PEXT020_ADD, self.form, function(res) {
								if (res.status == HTTP_STATUS.CREATED) {
									
									// ** 初期化処理 **
									self.initHeader1();
									self.initHeader2();
									self.initBody();

									// ** 活性制御 **
									self.controlDisabledFlg('header1');
									
									self.$nextTick(function() {
										// ** フォーカス設定 **
										self.$refs.jigyoCd.focus();

										// ** メッセージ設定&表示 ** //
										self.$store.commit(STORE_INFO_SET_MSG, '保存しました');
										
										// ** 編集クリア **
										self.$store.commit(STORE_VIEW_CHANGED, false);
										self.$store.commit(STORE_VIEW_PROGRESS, false);
									});
								} else {
									// 業務エラー表示
									co_setError(res.data, self.$refs);
									if (!co_isEmpty(self.$refs[res.data[0].errorItems[0]])) {
										self.$nextTick(function() {
											self.$refs[res.data[0].errorItems[0]].focus();
										});
									} 
									self.$store.commit(STORE_VIEW_PROGRESS, false);
								}
							});
						});
					} else {
						// ** 保存処理 **
						co_put(PATH_PEXT020_ADD, self.form, function(res) {
							if (res.status == HTTP_STATUS.CREATED) {

								// ** 初期化処理 **
								self.initHeader1();
								self.initHeader2();
								self.initBody();

								// ** 活性制御 **
								self.controlDisabledFlg('header1');

								self.$nextTick(function() {
									// ** フォーカス設定 **
									self.$refs.jigyoCd.focus();

									// ** メッセージ設定&表示 ** //
									self.$store.commit(STORE_INFO_SET_MSG, '保存しました');

									self.$store.commit(STORE_VIEW_CHANGED, false);
									self.$store.commit(STORE_VIEW_PROGRESS, false);
								});
							} else {
								// 業務エラー表示
								co_setError(res.data, self.$refs);
								if (!co_isEmpty(self.$refs[res.data[0].errorItems[0]])) {
									self.$refs[res.data[0].errorItems[0]].focus();
								}
								self.$store.commit(STORE_VIEW_PROGRESS, false);
							}

						});
					}
				} else {
					co_setError(res.data, self.$refs);
					if (!co_isEmpty(self.$refs[res.data[0].errorItems[0]])) {
						self.$refs[res.data[0].errorItems[0]].focus();
					}

					self.$store.commit(STORE_VIEW_PROGRESS, false);
				}
			});
		},
		// change - 事業所コード
		changeJigyoCd: function() {

			// ** 初期化処理 **
			this.form.jigyoNm = '';
			this.form.tenNm = '';

			if (co_isEmpty(this.form.jigyoCd)) {
				return;
			}

			// ** 事業所名取得 **
			let self = this;
			co_post(PATH_PEXT020_JIGYOSHO, this.form, function(res) {
				if (res.status == HTTP_STATUS.OK) {
					if (res.data != null) {
						self.form.jigyoNm = res.data.seikyNm;
						self.form.tenNm = res.data.JigyoNm;
					}
				}
			});
		},
		// change - 取引先コ―ド
		changeToriskCd: function() {

			// ** 初期化処理 **
			this.form.toriskNm = ''

			if (co_isEmpty(this.form.toriskCd)) {
				return;
			}

			// ** 取引先名取得 **
			let self = this;
			co_post(PATH_PEXT020_TORIHIKISAKI, this.form, function(res) {
				if (res.status == HTTP_STATUS.OK) {
					if (res.data != null) {
						self.form.toriskNm = res.data.toriskNm;
					}
				}
			});
		},
		// change - 通過納品場所CD
		changeTukaDelvPlaceCd: function() {
			
			// ** 初期化処理 **
			this.form.tukaDelvPlaceNm = '';
			
			if (co_isEmpty(this.form.tukaDelvPlaceCd)) {
				return;
			}

			// ** 通過納品場所取得 **
			let self = this;
			co_post(PATH_PEXT020_TUKADELVPLACE, this.form, function(res) {
				if (res.status == HTTP_STATUS.OK) {
					self.form.tukaDelvPlaceNm = res.data.tukaDelvPlaceNm;
				}
			});
		},
		// change - 商品コ―ド
		changeJanShinCd: function(index) {

			// ** 初期化処理 **
			this.clearShohin(index);

			if (co_isEmpty(this.disp.shohinList[index].janShinCd) ) {
				return;
			}

			// ** 商品情報取得 **
			this.form.itemIndex = index;
			this.form.shohinList = [...this.disp.shohinList];
			let self = this;
			co_post(PATH_PEXT020_SEARCH_SHOHIN, this.form, function(res) {
				if(res.status == HTTP_STATUS.OK) {
					// TODO プロトで固定値
					self.form.zeiRate = 8;

					if (res.data != null) {
						self.disp.shohinList[index] = res.data;
					} 
				}
			});
		},
		// keydown.Enter - 商品コ―ド
		keydownEnterJanShinCd: function(index, event) {
			if (co_isEmpty(this.disp.shohinList[index].janShinCd) ) {

				event.preventDefault();
				event.stopPropagation();
				this.$refs.saveButton.focus();

				return;
			}
		},
		// change - 数量
		changeSuryo: function(index) {
			if (co_isEmpty(this.disp.shohinList[index].janShinCd)) {
				return;
			}
			if (co_isEmpty(this.disp.shohinList[index].suryo)) {
				return;
			}

			// ** 金額計算処理 **
			// 仕入金額
			this.calcSireGaku(index);
			// 原価金額
			this.calcGenkaGaku(index);
			// 売価金額
			this.calcBaikaGaku(index);
		},
		// change - 仕入単価
		changeSireTan: function(index) {
			if (co_isEmpty(this.disp.shohinList[index].janShinCd)) {
				return;
			}

			// ** 金額計算処理 **
			// 仕入金額
			this.calcSireGaku(index)
		},
		// change - 原価単価
		changeGenkaTanka: function(index) {
			if (co_isEmpty(this.disp.shohinList[index].janShinCd)) {
				return;
			}

			// ** 金額計算処理 **
			// 原価金額
			this.calcGenkaGaku(index);
		},
		// change - 売単価
		changeUriTanka: function(index) {
			if (co_isEmpty(this.disp.shohinList[index].janShinCd)) {
				return;
			}

			// ** 金額計算処理 **
			// 売価金額
			this.calcBaikaGaku(index);
		},
		// ▼ ---------------------------------------- サブメソッド ---------------------------------------- ▼
		// 仕入金額計算
		calcSireGaku: function(index) {
			this.disp.shohinList[index].sireGaku = null;
			if (isNaN(this.disp.shohinList[index].suryo)) {
				return;
			}
			if (isNaN(this.disp.shohinList[index].sireTan)) {
				return;
			}

			this.disp.shohinList[index].sireGaku = this.disp.shohinList[index].suryo * this.disp.shohinList[index].sireTan;
		},
		// 原価金額計算
		calcGenkaGaku: function(index) {
			this.disp.shohinList[index].genkaGaku = null;
			if (isNaN(this.disp.shohinList[index].suryo)) {
				return; 
			}
			if (isNaN(this.disp.shohinList[index].genkaTanka)) {
				return;
			}

			this.disp.shohinList[index].genkaGaku = this.disp.shohinList[index].suryo * this.disp.shohinList[index].genkaTanka;
		},
		// 売価金額計算
		calcBaikaGaku: function(index) {
			this.disp.shohinList[index].baikaGaku = null;
			if (isNaN(this.disp.shohinList[index].suryo)) {
				return;
			}
			if (isNaN(this.disp.shohinList[index].uriTanka)) {
				return;
			}

			this.disp.shohinList[index].baikaGaku = this.disp.shohinList[index].suryo * this.disp.shohinList[index].uriTanka;
		},
		// 明細行クリア
		clearShohin: function(index) {
			// this.disp.shohinList[index].janShinCd = '';
			this.disp.shohinList[index].shinNm = '';
			this.disp.shohinList[index].kikaku = '';
			this.disp.shohinList[index].irisu = null;
			this.disp.shohinList[index].suryo = null;
			this.disp.shohinList[index].sireTan = null;
			this.disp.shohinList[index].sireGaku = null;
			this.disp.shohinList[index].genkaTanka = null;
			this.disp.shohinList[index].genkaGaku = null;
			this.disp.shohinList[index].uriTanka = null;
			this.disp.shohinList[index].baikaGaku = null;
			this.disp.shohinList[index].zeiRate = null;
		},
		// 活性制御
		controlDisabledFlg: function(nextArea) {
			switch (nextArea){
			case 'title':
				this.disabledFlg.title = false;
				this.disabledFlg.header1 = true;
				this.disabledFlg.header2 = true;
				this.disabledFlg.body = true;

				this.disabledFlg.toriKbn = true;
				this.disabledFlg.hachuDay = true;
				this.disabledFlg.header1Button = true;
				this.disabledFlg.zeiRate = true;
				this.disabledFlg.saveButton = true;
				break;
			case 'header1':
				this.disabledFlg.title = true;
				this.disabledFlg.header1 = false;
				this.disabledFlg.header2 = true;
				this.disabledFlg.body = true;

				this.disabledFlg.header1Button = false;
				this.disabledFlg.zeiRate = true;
				this.disabledFlg.saveButton = true;
				switch (this.form.procsShubt) {
				case SHORISBT.ADD_CD:
					this.disabledFlg.toriKbn = false;
					this.disabledFlg.hachuDay = false;
					break;
				case SHORISBT.UPDATE_CD:
					break;
				case SHORISBT.TAXUPDATE_CD:
					break;
				}

				break;
			case 'header2':
			case 'body':
				this.disabledFlg.title = true;
				this.disabledFlg.header1 = true;

				this.disabledFlg.toriKbn = true;
				this.disabledFlg.hachuDay = true;
				this.disabledFlg.header1Button = false;
				this.disabledFlg.saveButton = false;

				if (this.form.procsShubt == SHORISBT.ADD_CD
						|| this.form.procsShubt == SHORISBT.UPDATE_CD ) {
					
					this.disabledFlg.header2 = false;
					this.disabledFlg.body = false;
				} else if (this.form.procsShubt == SHORISBT.TAXUPDATE_CD) {
					this.disabledFlg.header2 = true;
					this.disabledFlg.body = true;

					this.disabledFlg.zeiRate = false;
				}

				break;
			case 'footer':
				break;
			}
		},
		// 商品明細部6行埋め処理
		fillShohinList: function(shohinList) {
			// 6行目まで空行で埋める
			let startNo = shohinList.length + 1;
			for (let i = startNo; i <= 6; i++) {
				shohinList.push({
					no: i 
					, janShinCd: ''
					, shinCd: ''
					, shinNm: ''
					, kikaku: ''
					, irisu: null
					, suryo: null
					, sireTan: null
					, sireGaku: null
					, genkaTanka: null
					, genkaGaku: null
					, uriTanka: null
					, baikaGaku: null
					, zeiRate: null
				})
			}
			return shohinList;
		},
		// ヘッダ部１初期化
		initHeader1: function() {
			this.form.jigyoCd = '';
			this.form.jigyoNm = '';
			this.form.tenCd = '';
			this.form.tenNm = '';
			this.form.catgCd = '';
			this.form.toriKbn = '';
			this.form.dempyoNo = '';
			this.form.toriskCd = '';
			this.form.toriskNm = '';
			this.form.hachuDay = '';
			this.form.delvDay = '';
		},
		// ヘッダ部２初期化
		initHeader2: function() {
			this.form.teiseiKbn = '';
			this.form.dempyoIssKbn = '';
			this.form.message = '';
			this.form.jitDelvDay = '';
			this.form.zeiRate = null;
			this.form.tukaDelvPlaceCd = '';
			this.form.tukaDelvPlaceNm = '';
		},
		// 伝票明細部初期化
		initBody: function() {
			this.disp.shohinList = this.fillShohinList(new Array());
		},
	},
}

</script>
<style type="text/css">
		.table-layout{
			table-layout: fixed;
		}
		.white-space{
			white-space: nowrap;
		}
		.margin-left_80{
			margin-left: 80px;
		}
		.margin-l-6px{
			margin-left: 6px;
		}
		.padding-right{
			padding-right: 10px;
		}
		.message-area{
			padding: 5px 10px; margin-bottom: 5px;width:99%; border: 1px solid #cbcdd9; border-radius: 5px;
		}
		.margin-top-15px{
			margin-top:15px;
		}
		.text-box {
			white-space: nowrap;
			overflow: hidden;
			text-overflow: ellipsis;
		}
		.text-box:hover {
			white-space: normal;
		}
		.max-width-310{
			max-width: 310px;
		}
		.border-side{
			border: 2px solid #dedfe8;
		}

</style>