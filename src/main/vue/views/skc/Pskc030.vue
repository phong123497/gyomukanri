<!--
//================================================================================
// ID: PSKC030
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// VueName: Pskc030
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/11 | ＡＭＣ               | 新規作成
//================================================================================
 -->
<template>
	<div id="PSKC030">
		<!-- メインコンテンツ -->
		<main>
			<div class="contents__block--exist_return">
				<h1 class="header__block-title">
					<table>
						<colgroup>
							<col style="width:200px;">
							<col style="width:1330px;">
						</colgroup>
						<tbody>
							<tr>
								<td>
									製造計画数入力
								</td>
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
					<div class="contents__block-search">
						<div class="contents__block-search__main">
							<div id="RS080Search" class="tbl-list__wrap">
								<table>
									<colgroup>
										<col style="width: 180px;">
										<col style="width: 300px;">
										<col style="width: 100px;">
										<col style="width: 300px;">
										<col style="width: 100px;">
										<col style="width: 180px;">
										<col style="width: 220px;">
										<col style="width: 120px;">
									</colgroup>
									<tbody>
										<tr>
											<td class="txt--align_r">
												<span class="contents__block-search__label">店舗</span>
											</td>
											<td>
												<list-box 
													class="width-300"
													ref="tenpo"
													v-bind:blankFlg="isPlanSuVisible"
													v-bind:cdContentType="CD_CONTENT_TYPE.CONTENT"
													v-bind:disabledFlg="!isHonsyaKengen || searchDisabledFlg"
													v-bind:listItems="disp.miseList"
													v-bind:name="'店舗'"
													v-bind:requireFlg="true"
													v-bind:tabindex="1"
													v-model="form.tenpo">
												</list-box>
											</td>
											<td class="txt--align_r">
												<span class="contents__block-search__label">表示週</span>
											</td>
											<td>
												<list-box 
													class="width-300"
													ref="hyojiWeek"
													v-bind:cdContentType="CD_CONTENT_TYPE.CONTENT"
													v-bind:disabledFlg="searchDisabledFlg"
													v-bind:listItems="disp.nhinymdList"
													v-bind:name="'表示週'"
													v-bind:requireFlg="true"
													v-bind:tabindex="2"
													v-model="form.hyojiWeek">
												</list-box>
											</td>
											<td class="txt--align_r">
												<span class="contents__block-search__label">表示順</span>
											</td>
											<td>
												<list-box 
													class="width-150"
													ref="hyojiRank"
													v-bind:blankFlg="false"
													v-bind:cdContentType="CD_CONTENT_TYPE.CONTENT"
													v-bind:disabledFlg="searchDisabledFlg"
													v-bind:listItems="hyojiRankListItems"
													v-bind:name="'表示順'"
													v-bind:tabindex="3"
													v-model="form.hyojiRank">
												</list-box>
											</td>
											<td>
												<div class="contents__block-radio">
													<radio-button
														ref="ascDesc"
														v-bind:disabledFlg="searchDisabledFlg"
														v-bind:listItems="ascDescListItems"
														v-bind:name="'昇順降順'"
														v-bind:radioGroupName="'ascDesc'"
														v-bind:tabindex="'4'"
														v-model="form.ascDesc"
													/>
												</div>
											</td>
											<td>
												<button-input
													class="contents__btn-blue float-l"
													v-bind:disabledFlg="searchDisabledFlg"
													v-bind:name="'SearchBtn'"
													v-bind:tabindex="'5'"
													v-bind:value="'検索'"
													v-on:click="clickSearchBtn" />
											</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<div class="margin-l-670px" v-if="tableData.length > 0">
						<span class="font-size-23px margin-l-600px">※数量は左「事業部計画数量」、右「店舗入力数量」</span>
					</div>
					<div class="contents__view--bg_gray">
						<div id="RS080List" class="tbl-list__wrap">
							<table class="tbl-list--border-all_exist wrap--overflow_hidden tbl-sticky-head">
								<colgroup>
									<col style="width: 40px;">
									<col style="width: 120px;">
									<col style="width: 400px;">
									<col style="width: 100px;">
									<col style="width: 80px;">
									<col style="width: 80px;">
									<col style="width: 80px;">
									<col style="width: 80px;">
									<col style="width: 80px;">
									<col style="width: 80px;">
									<col style="width: 80px;">
									<col style="width: 80px;">
									<col style="width: 80px;">
									<col style="width: 80px;">
									<col style="width: 80px;">
									<col style="width: 80px;">
									<col style="width: 80px;">
									<col style="width: 80px;">
								</colgroup>
								<thead v-if="tableData.length > 0">
									<tr>
										<th></th>
										<th colspan="3">商品</th>
										<th colspan="2">{{ tableheaderData.delvdayMon }}(月)</th>
										<th colspan="2">{{ tableheaderData.delvdayTue }}(火)</th>
										<th colspan="2">{{ tableheaderData.delvdayWed }}(水)</th>
										<th colspan="2">{{ tableheaderData.delvdayThu }}(木)</th>
										<th colspan="2">{{ tableheaderData.delvdayFri }}(金)</th>
										<th colspan="2">{{ tableheaderData.delvdaySat }}(土)</th>
										<th colspan="2">{{ tableheaderData.delvdaySun }}(日)</th>
									</tr>
								</thead>
							</table>
							<table class="tbl-list--border-all_exist wrap--overflow_hidden no-boder">
								<colgroup>
									<col style="width: 40px;">
									<col style="width: 120px;">
									<col style="width: 400px;">
									<col style="width: 100px;">
									<col style="width: 80px;">
									<col style="width: 80px;">
									<col style="width: 80px;">
									<col style="width: 80px;">
									<col style="width: 80px;">
									<col style="width: 80px;">
									<col style="width: 80px;">
									<col style="width: 80px;">
									<col style="width: 80px;">
									<col style="width: 80px;">
									<col style="width: 80px;">
									<col style="width: 80px;">
									<col style="width: 80px;">
									<col style="width: 80px;">
								</colgroup>
								<tbody v-for="(item, index) in tableData" :key="item">
									<tr :key="item" :class="index % 2 === 0 ? 'odd-row' : 'even-row'">
										<td class="txt--align_c border--top_none"><span>{{ index + 1 }}</span></td>
										<td class="border--top_none">
											<span>{{ item.shin }}</span>
										</td>
										<td class="border--top_none">
											<span>{{ item.shinNm }}</span>
										</td>
										<td class="border--top_none">
											<span>{{ item.stdd }}</span>
										</td>
										<td class="border--top_none">
											<number-input
												class="width-60 txt--align_r"
												v-bind:disabledFlg="!isPlanSuVisible || !isInputTimeVisibleMon"
												v-bind:maxVal="500"
												v-bind:maxLength="3"
												v-bind:minVal="0"
												v-bind:name="'計画数（月）'" 
												v-bind:ref="'planSuMon' + index"
												v-bind:tabindex="String((index + 1) * 100 + 1)"
												v-model="item.planSuMon">
											</number-input>
										</td>
										<td class="border--top_none">
											<number-input
												class="width-60 txt--align_r"
												v-bind:disabledFlg="!isInputTimeVisibleMon"
												v-bind:maxVal="500"
												v-bind:maxLength="3"
												v-bind:minVal="0"
												v-bind:name="'納品数（月）'" 
												v-bind:ref="'delvSuMon' + index"
												v-bind:tabindex="String((index + 1) * 100 + 2)"
												v-model="item.delvSuMon">
											</number-input>
										</td>
										<td class="border--top_none">
											<number-input
												class="width-60 txt--align_r"
												v-bind:disabledFlg="!isPlanSuVisible || !isInputTimeVisibleTue"
												v-bind:maxVal="500"
												v-bind:maxLength="3"
												v-bind:minVal="0"
												v-bind:name="'計画数（火）'" 
												v-bind:ref="'planSuTue' + index"
												v-bind:tabindex="String((index + 1) * 100 + 3)"
												v-model="item.planSuTue">
											</number-input>
										</td>
										<td class="border--top_none">
											<number-input
												class="width-60 txt--align_r"
												v-bind:disabledFlg="!isInputTimeVisibleTue"
												v-bind:maxVal="500"
												v-bind:maxLength="3"
												v-bind:minVal="0"
												v-bind:name="'納品数（火）'" 
												v-bind:ref="'delvSuTue' + index"
												v-bind:tabindex="String((index + 1) * 100 + 4)"
												v-model="item.delvSuTue">
											</number-input>
										</td>
										<td class="border--top_none">
											<number-input
												class="width-60 txt--align_r"
												v-bind:disabledFlg="!isPlanSuVisible || !isInputTimeVisibleWed"
												v-bind:maxVal="500"
												v-bind:maxLength="3"
												v-bind:minVal="0"
												v-bind:name="'計画数（水）'" 
												v-bind:ref="'planSuWed' + index"
												v-bind:tabindex="String((index + 1) * 100 + 5)"
												v-model="item.planSuWed">
											</number-input>
										</td>
										<td class="border--top_none">
											<number-input
												class="width-60 txt--align_r"
												v-bind:disabledFlg="!isInputTimeVisibleWed"
												v-bind:maxVal="500"
												v-bind:maxLength="3"
												v-bind:minVal="0"
												v-bind:name="'納品数（水）'" 
												v-bind:ref="'delvSuWed' + index"
												v-bind:tabindex="String((index + 1) * 100 + 6)"
												v-model="item.delvSuWed">
											</number-input>
										</td>
										<td class="border--top_none">
											<number-input
												class="width-60 txt--align_r"
												v-bind:disabledFlg="!isPlanSuVisible || !isInputTimeVisibleThu"
												v-bind:maxVal="500"
												v-bind:maxLength="3"
												v-bind:minVal="0"
												v-bind:name="'計画数（木）'" 
												v-bind:ref="'planSuThu' + index"
												v-bind:tabindex="String((index + 1) * 100 + 7)"
												v-model="item.planSuThu">
											</number-input>
										</td>
										<td class="border--top_none">
											<number-input
												class="width-60 txt--align_r"
												v-bind:disabledFlg="!isInputTimeVisibleThu"
												v-bind:maxVal="500"
												v-bind:maxLength="3"
												v-bind:minVal="0"
												v-bind:name="'納品数（木）'" 
												v-bind:ref="'delvSuThu' + index"
												v-bind:tabindex="String((index + 1) * 100 + 8)"
												v-model="item.delvSuThu">
											</number-input>
										</td>
										<td class="border--top_none">
											<number-input
												class="width-60 txt--align_r"
												v-bind:disabledFlg="!isPlanSuVisible || !isInputTimeVisibleFri"
												v-bind:maxVal="500"
												v-bind:maxLength="3"
												v-bind:minVal="0"
												v-bind:name="'計画数（金）'" 
												v-bind:ref="'planSuFri' + index"
												v-bind:tabindex="String((index + 1) * 100 + 9)"
												v-model="item.planSuFri">
											</number-input>
										</td>
										<td class="border--top_none">
											<number-input
												class="width-60 txt--align_r"
												v-bind:disabledFlg="!isInputTimeVisibleFri"
												v-bind:maxVal="500"
												v-bind:maxLength="3"
												v-bind:minVal="0"
												v-bind:name="'納品数（金）'" 
												v-bind:ref="'delvSuFri' + index"
												v-bind:tabindex="String((index + 1) * 100 + 10)"
												v-model="item.delvSuFri">
											</number-input>
										</td>
										<td class="border--top_none">
											<number-input
												class="width-60 txt--align_r"
												v-bind:disabledFlg="!isPlanSuVisible || !isInputTimeVisibleSat"
												v-bind:maxVal="500"
												v-bind:maxLength="3"
												v-bind:minVal="0"
												v-bind:name="'計画数（土）'" 
												v-bind:ref="'planSuSat' + index"
												v-bind:tabindex="String((index + 1) * 100 + 11)"
												v-model="item.planSuSat">
											</number-input>
										</td>
										<td class="border--top_none">
											<number-input
												class="width-60 txt--align_r"
												v-bind:disabledFlg="!isInputTimeVisibleSat"
												v-bind:maxVal="500"
												v-bind:maxLength="3"
												v-bind:minVal="0"
												v-bind:name="'納品数（土）'" 
												v-bind:ref="'delvSuSat' + index"
												v-bind:tabindex="String((index + 1) * 100 + 12)"
												v-model="item.delvSuSat">
											</number-input>
										</td>
										<td class="border--top_none">
											<number-input
												class="width-60 txt--align_r"
												v-bind:disabledFlg="!isPlanSuVisible || !isInputTimeVisibleSun"
												v-bind:maxVal="500"
												v-bind:maxLength="3"
												v-bind:minVal="0"
												v-bind:name="'計画数（日）'" 
												v-bind:ref="'planSuSun' + index"
												v-bind:tabindex="String((index + 1) * 100 + 13)"
												v-model="item.planSuSun">
											</number-input>
										</td>
										<td class="border--top_none">
											<number-input
												class="width-60 txt--align_r"
												v-bind:disabledFlg="!isInputTimeVisibleSun"
												v-bind:maxVal="500"
												v-bind:maxLength="3"
												v-bind:minVal="0"
												v-bind:name="'納品数（日）'" 
												v-bind:ref="'delvSuSun' + index"
												v-bind:tabindex="String((index + 1) * 100 + 14)"
												v-model="item.delvSuSun">
											</number-input>
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
						<div class="button-group">
							<button-input
								class="contents__btn-blue"
								style="{ 'margin-right': '30px' }"
								v-bind:name="'backButton'"
								v-bind:tabindex="'10003'"
								v-bind:value="'戻る'"
								v-on:click="clickReturnBtn" />
							<button-input
								class="contents__btn-blue"
								v-bind:name="'clickClear'"
								v-bind:tabindex="'10004'"
								v-bind:value="'取消'"
								v-on:click="clickCancelBtn" />
						</div>
					</div>
					<div class="center-block">
						<div class="button-group">
							<button-input
								class="contents__btn-blue"
								v-bind:disabledFlg="!searchDisabledFlg"
								v-bind:name="'printButton'"
								v-bind:tabindex="'10002'"
								v-bind:value="'印刷'"
								v-on:click="clickPrintBtn" />
						</div>
					</div>
					<div class="right-block">
						<div class="button-group">
							<button-input
								class="contents__btn-blue"
								v-bind:disabledFlg="!searchDisabledFlg || !canSaveVisible"
								v-bind:name="'printButton'"
								v-bind:tabindex="'10001'"
								v-bind:value="'保存'"
								v-on:click="clickSaveBtn" />
						</div>
					</div>
				</div>
			</div>
		</footer>
	</div>
</template>

<script>
import { Core, PdfExport } from "@grapecity/activereports";
import ButtonInput from '@/components/ButtonInput.vue';
import ErrorMessage from '@/components/ErrorMessage.vue';
import InfoMessage from '@/components/InfoMessage.vue';
import ListBox from '@/components/ListBox.vue';
import NumberInput from '@/components/NumberInput.vue';
import RadioButton from '@/components/RadioButton.vue';

import { 
	co_getMessage 
} from '@/common/messageUtility.js';

import { 
	co_confirm 
} from '@/common/dialogUtility.js';

import { 
	co_post,
	co_put,
	co_setError,
} from '@/common/ajaxUtility.js';

import {
	co_isEmpty,
} from '@/common/checkUtility.js';

import {
	STORE_DATA_DELETE_AT,
	STORE_DATA_DELETE_INHERIT_DATA_AT,
	STORE_ERROR_CLEAR,
	STORE_ERROR_IS_ERRFLG,
	STORE_INFO_CLEAR,
	STORE_INFO_SET_MSG,
	STORE_USER_GET_INFO,
	STORE_VIEW_CHANGED,
	STORE_VIEW_HAS_CHANGED,
	STORE_VIEW_PROGRESS,
} from '@/const/store.js';

import {
	PATH_PSKC030,
	PATH_PSKC030_PSKC03001PRINT,
	PATH_PSKC030_SAVE,
	PATH_PSKC030_SEARCH,
} from '@/const/route.js'

import {
	CD_CONTENT_TYPE,
	CNS_NKS_KENGEN,
	DIALOG_BUTTON_LIST_KEY,
	HTTP_STATUS,
} from '@/const/const.js';

export default {
	props: {
	},
	components: {
		'button-input': ButtonInput,
		'error-message': ErrorMessage,
		'info-message': InfoMessage,
		'list-box': ListBox,
		'number-input': NumberInput,
		'radio-button': RadioButton,
	},
	data: function () {
		return {
			form: {
				tenpo: '',			// 店舗
				hyojiWeek: '',		// 表示週
				hyojiRank: '',		// 表示順
				ascDesc: '',		// 昇順降順
				saveFlg: '',		// 保存Flg
				isHonsyaKengen: false,		// 本部権限
				loginShop: '',		// ログイン店 
			},
			nextNextMon: '',
			tableheaderData: {},
			tableData: [],
			disp: {
				miseList: [],	//店舗コンボボックスリスト
				nhinymdList: [],	//表示週コンボボックスリスト
			},
			hyojiRankListItems:[
				{code: 0, name:'商品コード' },
				{code: 1, name:'商品名' },
			], 
			ascDescListItems:[
				{code: 0, name:'昇順' },
				{code: 1, name:'降順' },
			], 
			CD_CONTENT_TYPE: CD_CONTENT_TYPE,
			permissions:null, 	// 権限
			isPlanSuVisible: false,	// 計画数入力フラグ
			isInputTimeVisibleMon: false,	// 入力可能期間フラグ（月）
			isInputTimeVisibleTue: false,	// 入力可能期間フラグ（火）
			isInputTimeVisibleWed: false,	// 入力可能期間フラグ（水）
			isInputTimeVisibleThu: false,	// 入力可能期間フラグ（木）
			isInputTimeVisibleFri: false,	// 入力可能期間フラグ（金）
			isInputTimeVisibleSat: false,	// 入力可能期間フラグ（土）
			isInputTimeVisibleSun: false,	// 入力可能期間フラグ（日）
			canSaveVisible: false,	// 保存ボタンクリック可能フラグ
			isFirstUserEdit: false,
			isHonsyaKengen: false,		// 本部権限
		}
	},
	created: async function() {
		// プログレスバー表示
		this.$store.commit(STORE_VIEW_PROGRESS, true)
		// 権限の取得
		this.permissions = this.$store.getters[STORE_USER_GET_INFO];
		
		// 権限_本社
		if (this.permissions.ctrlKbn == CNS_NKS_KENGEN.CNS_NKS_KENGEN_HONSYA) {
			this.form.isHonsyaKengen = true;
			this.isHonsyaKengen = true;
		}else {
			this.form.isHonsyaKengen = false;
			this.isHonsyaKengen = false;
		}
		this.isPlanSuVisible = true;
		this.form.loginShop = this.permissions.tempoCd;
		co_post(PATH_PSKC030, this.form, this.createBackFunction)
	},
	mounted: async function() {
		await this.$nextTick();
		if (this.permissions.ctrlKbn == CNS_NKS_KENGEN.CNS_NKS_KENGEN_HONSYA) {
			this.$refs.tenpo.focus();
		}else {
			this.$refs.hyojiWeek.focus();
		}
	},
	watch: {
		tableData: {
			handler: function () {
				if (this.isFirstUserEdit) {
					this.$store.commit(STORE_VIEW_CHANGED, true);
				} else {
					this.isFirstUserEdit = true;
				}
			},
			deep: true
		}
	},
	methods: {
		// 初期化
		createBackFunction:function(res){
			// エラーをクリア
			this.$store.commit(STORE_ERROR_CLEAR)
			if (res.status == HTTP_STATUS.OK) {
				// 店舗リストボックス
				let miseList = []
				for (let j = 0; j < res.data.miseList.length; j++){
					miseList.push({
						code: res.data.miseList[j].misecd,
						name: res.data.miseList[j].misenm
					})
				}
				this.disp.miseList = miseList
				// 表示週リストボックス
				let nhinymdList = []
				for (let j = 0; j < res.data.nhinymdList.length; j++){
					nhinymdList.push({
						code: res.data.nhinymdList[j].nhinymd,
						name: res.data.nhinymdList[j].nhinnm
					})
				}
				this.disp.nhinymdList = nhinymdList
				
				this.form.tenpo = res.data.mise;
				this.form.hyojiWeek = res.data.nhinymd;
				this.nextNextMon = res.data.nhinymd;
				this.form.hyojiRank = 0;
				this.form.ascDesc = 0;
			}
			// プログレスバー非表示
			this.$store.commit(STORE_ERROR_CLEAR);
			this.$store.commit(STORE_VIEW_PROGRESS, false)
		},
		
		// click - 検索ボタン
		clickSearchBtn: async function () {
			// プログレスバー表示
			this.$store.commit(STORE_VIEW_PROGRESS, true)
			// エラーをクリア
			this.$store.commit(STORE_INFO_CLEAR);
			this.$store.commit(STORE_ERROR_CLEAR)
			// 入力チェック
			this.$refs.tenpo.validate()
			this.$refs.hyojiWeek.validate()
			this.$refs.hyojiRank.validate()
			if (this.$store.getters[STORE_ERROR_IS_ERRFLG]) {
				// プログレスバー非表示
				this.$store.commit(STORE_VIEW_PROGRESS, false)
				return
			}
			// 権限の取得
			this.permissions = this.$store.getters[STORE_USER_GET_INFO];
		
			// 権限_本社
			if (this.permissions.ctrlKbn == CNS_NKS_KENGEN.CNS_NKS_KENGEN_HONSYA) {
				this.form.isHonsyaKengen = true;
				this.isHonsyaKengen = true;
			}else {
				this.form.isHonsyaKengen = false;
				this.isHonsyaKengen = false;
			}
			co_post(PATH_PSKC030_SEARCH, this.form, this.searchBackFun)
			this.$nextTick(function(){
				this.$store.commit(STORE_VIEW_CHANGED, false);
			});
		},
		
		searchBackFun: function (res) {
			if (res.status == HTTP_STATUS.OK) {
				this.searchDisabledFlg = true
				this.tableData = res.data.meisaiList
				this.tableheaderData = res.data
				this.canSaveVisible = false
				const daysOfWeek = ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"];
				// 入力可能期間チェック
				if (this.permissions.ctrlKbn == CNS_NKS_KENGEN.CNS_NKS_KENGEN_HONSYA) {
					// 翌日以降が入力可
					const today = new Date();
					today.setDate(today.getDate() + 1);
					const year = today.getFullYear();
					const month = String(today.getMonth() + 1).padStart(2, '0');
					const day = String(today.getDate()).padStart(2, '0');
					const todayDate = `${year}${month}${day}`;
					let visibleCount = 0;
					for (const day of daysOfWeek) {
						const delvDay = res.data.meisaiList[0][`delvday${day}`];
						if (todayDate <= delvDay) {
							this[`isInputTimeVisible${day}`] = true;
							visibleCount = visibleCount + 1;
						}else {
							this[`isInputTimeVisible${day}`] = false;
						}
					}
					if(visibleCount != 0){
						this.canSaveVisible = true
					}
				}else{
					// 翌々週の月曜日以降が入力可
					const delvDay = res.data.meisaiList[0].delvdayMon;
					if (this.nextNextMon <= delvDay) {
						for (const day of daysOfWeek) {
							this[`isInputTimeVisible${day}`] = true;
						}
						this.canSaveVisible = true
					}else{
						for (const day of daysOfWeek) {
							this[`isInputTimeVisible${day}`] = false;
						}
					}
				}
			} else {
				this.searchDisabledFlg = false
				// 業務エラー表示
				co_setError(res.data, this.$refs)
				const errorItem = res.data[0].errorItems[0];
				if (!co_isEmpty(this.$refs[errorItem])) {
					const self = this;
					this.$nextTick(function() {
						self.$refs[errorItem].focus();
						if (self.permissions.ctrlKbn == CNS_NKS_KENGEN.CNS_NKS_KENGEN_HONSYA) {
							self.$refs.tenpo.focus();
						}else {
							self.$refs.hyojiWeek.focus();
						}
					});
				}
			}
			// プログレスバー非表示
			this.$store.commit(STORE_VIEW_PROGRESS, false)
		},
		
		clickSaveBtn: function() {
			// 状態管理(ストア)のエラー情報をクリアする。
			this.$store.commit(STORE_ERROR_CLEAR)
			// MSG クリア
			this.$store.commit(STORE_INFO_CLEAR)
			this.$store.commit(STORE_VIEW_PROGRESS, true);
			// 入力チェック
			this.$refs.tenpo.validate()
			this.$refs.hyojiWeek.validate()
			this.$refs.hyojiRank.validate()
			
			const daysOfWeek = ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"];
			for (let index = 0; index < this.tableData.length; index++) {
				for (const day of daysOfWeek) {
					this.$refs[`planSu${day}${index}`][0].validate()
					this.$refs[`delvSu${day}${index}`][0].validate()
				}
			}
			if (this.$store.getters[STORE_ERROR_IS_ERRFLG]) {
				// プログレスバー非表示
				this.$store.commit(STORE_VIEW_PROGRESS, false)
				return
			}
			let self = this
			this.form.meisaiList = this.tableData;
			co_put(PATH_PSKC030_SAVE, this.form, function(res){
				if (res.status == HTTP_STATUS.OK) {
					// プログレスバー非表示
					self.$store.commit(STORE_VIEW_PROGRESS, false)
					// ** メッセージ設定&表示 ** //
					self.$store.commit(STORE_INFO_SET_MSG, '保存しました');
					// ** 編集クリア **
					self.$store.commit(STORE_VIEW_CHANGED, false);
					self.$store.commit(STORE_VIEW_PROGRESS, false);
					self.searchBackFun(res)
				} else {
					// 業務エラー表示
					co_setError(res.data, self.$refs);
					if (!co_isEmpty(res.data[0].errorItems) && !co_isEmpty(self.$refs[res.data[0].errorItems[0]])) {
						self.$refs['delvJiYokiKbn'].focus();
					} 
				}
				// プログレスバー非表示
				self.$nextTick(function() {
					self.$store.commit(STORE_VIEW_PROGRESS, false);
					self.$store.commit(STORE_VIEW_CHANGED, false);
				});
			})
		},
		
		// click - 印刷
		clickPrintBtn: function() {
			// プログレスバー表示
			this.$store.commit(STORE_VIEW_PROGRESS, true);
			
			// エラーをクリア
			this.$store.commit(STORE_ERROR_CLEAR)
			
			// ** チェック処理 **
			this.$refs.tenpo.validate()
			this.$refs.hyojiWeek.validate()
			this.$refs.hyojiRank.validate()
			// ** エラー表示制御 **
			if ( this.$store.getters[STORE_ERROR_IS_ERRFLG]) {
				this.$store.commit(STORE_VIEW_PROGRESS, false);
				return;
			}
			const self = this;
			// 印刷前保存処理
			if (this.$store.getters[STORE_VIEW_HAS_CHANGED] == true && this.canSaveVisible) {
				this.clickSaveBtn();
			}
			co_post(PATH_PSKC030_PSKC03001PRINT, this.form, function(res) {
				if (res.status == HTTP_STATUS.OK) {
					// 帳票の実装方法
					self.outPutPDF(res.data);
					
					// プログレスバー非表示
					self.$store.commit(STORE_VIEW_PROGRESS, false);
				}else{
					co_setError(res.data, self.$refs)
					const errorItem = res.data[0].errorItems[0];
					if (!co_isEmpty(self.$refs[errorItem])) {
						self.$refs[errorItem].focus();
					}
					// プログレスバー非表示
					self.$store.commit(STORE_VIEW_PROGRESS, false);
				}
			})
		},
		
		// click - 戻るボタン
		clickReturnBtn: function () {
			if (this.$store.getters[STORE_VIEW_HAS_CHANGED] == true) {
				co_confirm(co_getMessage('CMS0002W'), this.backFunc)
			} else {
				this.backFunc(DIALOG_BUTTON_LIST_KEY.OK)
			}
		},
		backFunc: function(flg){
			if (flg == DIALOG_BUTTON_LIST_KEY.OK) {
				this.$store.commit(STORE_DATA_DELETE_AT, 'pskc030')
				this.$store.commit(STORE_DATA_DELETE_INHERIT_DATA_AT, 0)
				this.$router.back()
			}
		},
		
		
		
		// click - 取消ボタン
		clickCancelBtn: function () {
			this.$store.commit(STORE_INFO_CLEAR);
			if (this.$store.getters[STORE_VIEW_HAS_CHANGED] == true) {
				co_confirm(co_getMessage('CMS0003W'), this.cancelFunc)
			} else {
				this.cancelFunc(DIALOG_BUTTON_LIST_KEY.OK)
			}
		},
		cancelFunc: async function(flg){
			if (flg == DIALOG_BUTTON_LIST_KEY.OK) {
				this.$store.commit(STORE_INFO_CLEAR);
				this.$store.commit(STORE_ERROR_CLEAR);
				this.form = {
					hyojiWeek: this.nextNextMon,
					hyojiRank: 0,
					ascDesc: 0
				}
				if (this.permissions.ctrlKbn == CNS_NKS_KENGEN.CNS_NKS_KENGEN_HONSYA) {
					this.form.tenpo = ''
				}else {
					this.form.tenpo = this.permissions.tempoCd
				}
				this.tableData = []
				this.searchDisabledFlg = false
				this.$nextTick(function() {
					if (this.permissions.ctrlKbn == CNS_NKS_KENGEN.CNS_NKS_KENGEN_HONSYA) {
						this.$refs.tenpo.focus();
					}else {
						this.$refs.hyojiWeek.focus();
					}
					this.$store.commit(STORE_VIEW_CHANGED, false);
					this.isFirstUserEdit = false;
				});
			}
		},
		outPutPDF: async function(excelJsonData) {
						
			// 帳票定義体JSONデータ取得
			const reportResponse = await fetch("reports/skc/PSKC03001.rdlx-json");
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
			const fileName = this.form.hyojiWeek.substring(0,4)
				+ "年" 
				+ this.form.hyojiWeek.substring(4,6)
				+ "月" 
				+this.form.hyojiWeek.substring(6,8)
				+ "日週" 
			// ダウンロードする 
			result.download("惣菜製造計画数（"+ fileName +"）_"+`${yyyyMMddHHmmss}`);
			this.$store.commit(STORE_VIEW_PROGRESS, false);
		},
	}
}
</script>

<style type="text/css">
	#PSKC030 .margin-l-50px {
		margin-left: 50px;
	}

	#PSKC030 .margin-l-670px {
		margin-left: 670px;
	}

	#PSKC030 .margin-l-600px {
		margin-left: 600px;
	}

	#PSKC030 .font-size-23px {
		font-size: 23px;
	}

	#PSKC030 .tbl-sticky-head {
		position: -webkit-sticky;
		position: sticky;
		top: 0;
		z-index: 1
	}
	#PSKC030 .tbl-list--border-all_exist th {
		text-align: left;
	}
	#PSKC030 .footer-button__block .left-block input[type="button"], #PSKC030 .footer-button__block .center-block input[type="button"] {
		margin-right: 30px;
	}
	#PSKC030 .tbl-list--border-all_exist td span {
		padding: 8px 5px !important;
	}
	#PSKC030 .tbl-list--border-all_exist td div {
		padding: 0px 3px !important;
	}
	#PSKC030 .no-boder {
		margin-top: -2px;
	}
</style>
