<!--
//================================================================================
// ID: PCSK050
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// VueName: Pcsk050
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/18 | ＡＭＣ               | 新規作成
//================================================================================
 -->
<template>
	<div>
		<!-- メインコンテンツ -->
		<main id="PCSK050">
			<div class="contents__block--exist_return">
				<h1 class="header__block-title">
					<table>
						<tbody>
							<tr>
								<td>
									センター商管表出力
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
					<div class="padding-15px">
						<table>
							<colgroup>
								<col style="width: 150px;">
								<col style="width: 150px;">
								<col style="width: 170px;">
								<col style="width: 20px;">
								<col style="width: 170px;">
								<col style="width: 750px;">
							</colgroup>
							<tbody>
								<tr>
									<td class="txt--align_r height-43px">
										<span class="contents__block-search__label">種別</span>
									</td>
									<td colspan="5">
										<div class="contents__block-radio">
											<radio-button
												ref="shubt"
												v-bind:listItems="shubtlistItems"
												v-bind:name="'種別'"
												v-bind:radioGroupName="'shubt'"
												v-bind:tabindex="'1'"
												v-model="form.shubt"/>
										</div>
									</td>
								</tr>
								<tr>
									<td class="txt--align_r">
										<span class="contents__block-search__label">対象期間</span>
									</td>
									<td>
										<div class="contents__block-radio">
											<radio-button
												ref="targetKkn"
												v-bind:listItems="targetKknlistItems1"
												v-bind:name="'対象期間1'"
												v-bind:radioGroupName="'targetKkn'"
												v-bind:tabindex="'2'"
												v-model="form.targetKkn"/>
										</div>
									</td>
									<td>
										<date-input
											class="width-150 txt--align_l"
											ref="targetYm"
											v-bind:allowOmitedValue="true"
											v-bind:dateType="DATEINPUT_TYPE.YM"
											v-bind:disabledFlg="form.targetKkn != 0"
											v-bind:name="'対象年月'"
											v-bind:maxLength="7"
											v-bind:requireFlg="form.targetKkn == 0"
											v-bind:tabindex="'3'" 
											v-model="form.targetYm"/>
									</td>
								</tr>
								<tr>
									<td></td>
									<td>
										<div class="contents__block-radio">
											<radio-button
												ref="targetKkn"
												v-bind:listItems="targetKknlistItems2"
												v-bind:name="'対象期間2'"
												v-bind:radioGroupName="'targetKkn'"
												v-bind:tabindex="'4'"
												v-model="form.targetKkn"/>
										</div>
									</td>
									<td>
										<nendo-input
											class="width-150 txt--align_l"
											ref="targetNendo"
											v-bind:allowOmitedValue="true"
											v-bind:disabledFlg="form.targetKkn != 1"
											v-bind:name="'対象年度'"
											v-bind:maxLength="4"
											v-bind:tabindex="'5'" 
											v-bind:requireFlg="form.targetKkn == 1"
											v-model="form.targetNendo"/>
									</td>
								</tr>
								<tr>
									<td></td>
									<td>
										<div class="contents__block-radio">
											<radio-button
												ref="targetKkn"
												v-bind:listItems="targetKknlistItems3"
												v-bind:name="'対象期間3'"
												v-bind:radioGroupName="'targetKkn'"
												v-bind:tabindex="'7'"
												v-model="form.targetKkn"/>
										</div>
									</td>
									<td>
										<date-input
											class="width-150 txt--align_l"
											ref="targetYmFrom"
											v-bind:allowOmitedValue="true"
											v-bind:dateType="DATEINPUT_TYPE.YM"
											v-bind:disabledFlg="form.targetKkn != 2"
											v-bind:name="'対象年月_FROM'"
											v-bind:maxLength="7"
											v-bind:tabindex="'8'" 
											v-bind:requireFlg="form.targetKkn == 2"
											v-model="form.targetYmFrom"/>
									</td>
									<td>
										<span>～</span>
									</td>
									<td>
										<date-input
											class="width-150 txt--align_l"
											ref="targetYmTo"
											v-bind:allowOmitedValue="true"
											v-bind:dateType="DATEINPUT_TYPE.YM"
											v-bind:disabledFlg="form.targetKkn != 2"
											v-bind:name="'対象年月_TO'"
											v-bind:maxLength="7"
											v-bind:tabindex="'9'" 
											v-bind:requireFlg="form.targetKkn == 2"
											v-model="form.targetYmTo"/>
									</td>
								</tr>
								<tr>
									<td class="txt--align_r height-43px">
										<span class="contents__block-search__label">管理単位</span>
									</td>
									<td colspan="5">
										<div class="contents__block-radio">
											<radio-button
												ref="mngTani"
												v-bind:listItems="mngTanilistItems1"
												v-bind:name="'管理単位区分'"
												v-bind:radioGroupName="'mngTani'"
												v-bind:tabindex="'10'"
												v-model="form.mngTani"/>
										</div>
									</td>
								</tr>
								<tr>
									<td class="height-43px"></td>
									<td colspan="5">
										<div class="contents__block-radio">
											<radio-button
												ref="mngTani"
												v-bind:listItems="mngTanilistItems2"
												v-bind:name="'管理単位区分1'"
												v-bind:radioGroupName="'mngTani'"
												v-bind:tabindex="'10'"
												v-model="form.mngTani"/>
										</div>
									</td>
								</tr>
								<tr>
									<td class="height-43px"></td>
									<td colspan="5">
										<div class="contents__block-radio">
											<radio-button
												ref="mngTani"
												v-bind:listItems="mngTanilistItems3"
												v-bind:name="'管理単位区分2'"
												v-bind:radioGroupName="'mngTani'"
												v-bind:tabindex="'10'"
												v-model="form.mngTani"/>
										</div>
										<span style="margin-right: 10px">センターコード</span>
										<code-input 
											class="width-100 txt--align_l" 
											ref="cntrCd" 
											v-bind:disabledFlg="form.mngTani != 3"
											v-bind:maxLength="5"
											v-bind:name="'センターコード'" 
											v-bind:tabindex="'11'"
											v-bind:requireFlg="form.mngTani == 3"
											v-model="form.cntrCd" />
									</td>
								</tr>
								<tr>
									<td rowspan="3" class="txt--align_r height-43px">
										<span class="contents__block-search__label">集計単位</span>
									</td>
									<td colspan="5">
										<div class="contents__block-radio">
											<radio-button
												ref="skeiTani"
												v-bind:listItems="skeiTanilistItems"
												v-bind:name="'集計単位区分'"
												v-bind:radioGroupName="'skeiTani'"
												v-bind:tabindex="'12'"
												v-model="form.skeiTani"/>
										</div>
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
								:style="{ 'margin-right': '30px' }"
								class="contents__btn-blue"
								v-bind:name="'backButton'"
								v-bind:tabindex="'14'"
								v-bind:value="'戻る'"
								v-on:click="clickBack" />
							<button-input
								class="contents__btn-blue"
								v-bind:name="'clickClear'"
								v-bind:tabindex="'15'"
								v-bind:value="'取消'"
								v-on:click="clickClear" />
						</div>
					</div>
					<div class="center-block">
						<div class="button-group">
							<button-input
								class="contents__btn-blue"
								v-bind:name="'printButton'"
								v-bind:tabindex="'13'"
								v-bind:value="'Excel出力'"
								v-on:click="clickPrintBtn" />
						</div>
					</div>
					<div class="right-block">
					</div>
				</div>
			</div>
		</footer>
	</div>
</template>

<script>
import { Core, XlsxExport } from "@grapecity/activereports";

import ButtonInput from '@/components/ButtonInput.vue';
import CodeInput from '@/components/CodeInput.vue';
import DateInput from '@/components/DateInput.vue';
import ErrorMessage from '@/components/ErrorMessage.vue';
import RadioButton from '@/components/RadioButton.vue';
import NendoInput from '@/components/NendoInput.vue';

import {
	co_isEmpty,
} from '@/common/checkUtility.js';


import { 
	co_post,
	co_setError,
} from '@/common/ajaxUtility.js';

import {
	STORE_ERROR_CLEAR,
	STORE_ERROR_IS_ERRFLG,
	STORE_VIEW_PROGRESS,
} from '@/const/store.js';

import {
	DATEINPUT_TYPE,
	HTTP_STATUS,
} from '@/const/const.js';

import {
	PATH_PCSK050,
	PATH_PCSK050_PSKC05001PRINT,
} from '@/const/route.js'

export default {
	components: {
		'button-input': ButtonInput,
		'code-input': CodeInput,
		'date-input': DateInput,
		'error-message': ErrorMessage,
		'radio-button': RadioButton,
		'nendo-input': NendoInput,
	},
	data: function () {
		return {
			form: {
				shubt: '',			// 種別
				targetKkn: '',		// 対象期間区分
				targetYm: '',		// 対象年月
				targetNendo: '',	// 対象年度
				targetYmFrom: '',	// 対象年月_FROM
				targetYmTo: '',		// 対象年月_TO
				mngTani: '',		// 管理単位区分
				cntrCd: '',			// センターコード
				skeiTani: '',		// 集計単位区分
			},
			disp:{
				targetYm: '',		// 対象年月
				targetNendo: '',	// 対象年度
				targetYmFrom: '',	// 対象年月_FROM
				targetYmTo: '',		// 対象年月_TO
			},
			// 種別区分
			shubtlistItems:[
				{code: 0, name:'管理' },
				{code: 1, name:'財務' },
			], 
			// 対象期間区分
			targetKknlistItems1:[
				{code: 0, name:'単月指定' },
			], 
			targetKknlistItems2:[
				{code: 1, name:'年度指定' },
			], 
			targetKknlistItems3:[
				{code: 2, name:'範囲指定' },
			], 
			// 対象期間区分
			targetKknlistItems:[
				{code: 0, name:'単月指定' },
				{code: 1, name:'年度指定' },
				{code: 2, name:'範囲指定' },
			], 
			// 管理単位区分
			mngTanilistItems1:[
				{code: 0, name:'コープ九州全体' },
			], 
			mngTanilistItems2:[
				{code: 1, name:'業態別（店舗）' },
				{code: 2, name:'業態別（無店舗）' },
			], 
			mngTanilistItems3:[
				{code: 3, name:'センター別' },
			], 
			// 集計単位区分
			skeiTanilistItems:[
				{code: 0, name:'単品別' },
				{code: 1, name:'店舗分類別' },
				{code: 2, name:'無店舗分類別' },
				{code: 3, name:'部門別' },
			], 
			DATEINPUT_TYPE: DATEINPUT_TYPE,
		}
	},
	created: async function() {
		// プログレスバー表示
		this.$store.commit(STORE_VIEW_PROGRESS, true)
		co_post(PATH_PCSK050, this.form, this.createBackFunction)
	},
	mounted: async function() {
		await this.$nextTick();
		// フォーカス設定
		this.$refs.shubt.focus();
	},
	methods: {
		// 初期化
		createBackFunction:function(res){
			// エラーをクリア
			this.$store.commit(STORE_ERROR_CLEAR)
			// 初期値設定
			// 種別ラジオボタン
			this.form.shubt = res.data.shubt
			// 対象期間区分ラジオボタン
			this.form.targetKkn = res.data.targetKkn
			// 対象年月
			this.form.targetYm = res.data.targetYm
			this.disp.targetYm = res.data.targetYm
			// 対象年度
			this.form.targetNendo = res.data.targetNendo
			this.disp.targetNendo = res.data.targetNendo
			// 対象年月_FROM
			this.form.targetYmFrom = res.data.targetYmFrom
			this.disp.targetYmFrom = res.data.targetYmFrom
			// 対象年月_TO
			this.form.targetYmTo = res.data.targetYmTo
			this.disp.targetYmTo = res.data.targetYmTo
			// 管理単位区分ラジオボタン
			this.form.mngTani = res.data.mngTani
			// 事業所コード
			this.form.cntrCd = res.data.cntrCd
			// 集計単位区分ラジオボタン
			this.form.skeiTani = res.data.skeiTani
			
			// プログレスバー非表示
			this.$store.commit(STORE_ERROR_CLEAR);
			this.$store.commit(STORE_VIEW_PROGRESS, false)
		},
	
		// click - 戻る
		clickBack: function() {
			this.$router.back();
		},
		
		// click - クリア
		clickClear: async function() {
			// エラーメッセージをクリアする
			this.$store.commit(STORE_ERROR_CLEAR);
			// 初期値設定
			// 種別ラジオボタン
			this.form.shubt = 0
			// 対象期間区分ラジオボタン
			this.form.targetKkn = 0
			// 対象年月
			this.form.targetYm = this.disp.targetYm
			// 対象年度
			this.form.targetNendo = this.disp.targetNendo
			// 対象年月_FROM
			this.form.targetYmFrom = this.disp.targetYmFrom
			// 対象年月_TO
			this.form.targetYmTo = this.disp.targetYmTo
			// 管理単位区分ラジオボタン
			this.form.mngTani = 0
			// 事業所コード
			this.form.cntrCd = ""
			// 集計単位区分ラジオボタン
			this.form.skeiTani = 0
			
			await this.$nextTick();
			// ** フォーカス設定 **
			this.$refs.shubt.focus();
		},
		// click - 印刷
		clickPrintBtn: function() {
			// プログレスバー表示
			this.$store.commit(STORE_VIEW_PROGRESS, true);
			
			// エラーをクリア
			this.$store.commit(STORE_ERROR_CLEAR)
			
			// ** チェック処理 **
			this.$refs.targetYm.validate();
			this.$refs.targetNendo.validate();
			this.$refs.targetYmFrom.validate();
			this.$refs.targetYmTo.validate();
			this.$refs.cntrCd.validate();
			// ** エラー表示制御 **
			if ( this.$store.getters[STORE_ERROR_IS_ERRFLG]) {
				this.$store.commit(STORE_VIEW_PROGRESS, false);
				return;
			}
			const self = this;
			co_post(PATH_PCSK050_PSKC05001PRINT, this.form, function(res) {
				if (res.status == HTTP_STATUS.OK) {
					console.log(res.status);
					self.outPutEXCEL(res.data);
				}else{
					co_setError(res.data, self.$refs)
					// ** フォーカス設定 **
					if (!co_isEmpty(res.data[0].errorItems) && !co_isEmpty(self.$refs[res.data[0].errorItems[0]])) {
						self.$refs[res.data[0].errorItems[0]].focus();
					} else {
						self.$refs['shubt'].focus();
					}
				}
				// プログレスバー非表示
				self.$store.commit(STORE_VIEW_PROGRESS, false);
			})
		},
		// EXCEL帳票印刷
		outPutEXCEL: async function(excelJsonData) {
			let reportResponse;
			// 帳票定義体JSONデータ取得
			if (this.form.targetKkn == "0") {
				if (this.form.skeiTani == "0") {
					reportResponse = await fetch("reports/csk/PCSK05001_Tanpin.rdlx-json");
				} else {
					reportResponse = await fetch("reports/csk/PCSK05001_TenpoBunrui.rdlx-json");
				}
			} else {
				if (this.form.skeiTani == "0") {
					reportResponse = await fetch("reports/csk/PCSK05001_Bumonbetu.rdlx-json");
				} else {
					reportResponse = await fetch("reports/csk/PCSK05001_MutenpoBunrui.rdlx-json");
				}
			}
			const report = await reportResponse.json();
			
			// 帳票データを帳票定義体に入れる
			report.DataSources[0].ConnectionProperties.ConnectString = "jsondata=" + JSON.stringify(excelJsonData.detailList);
			
			// リネームする
			const ARJS = Core;
			const Excel = XlsxExport;
			
			// 帳票設定
			const settings = {
				sheetName: excelJsonData.fileName,
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
			const result = await Excel.exportDocument(pageDocument, settings);
			
			// 帳票の名を構成する
			const choHyoFileName = excelJsonData.fileName;
			
			// ダウンロードする 
			result.download(choHyoFileName);
			this.$store.commit(STORE_VIEW_PROGRESS, false);
		},
	}
}
</script>

<style scoped>
	.padding-t-b {
		padding-top: 20px;
		padding-bottom: 15px;
	}

	.margin-t-30px {
		margin-top: 30px;
	}

	.margin-l-200px {
		margin-left: 200px;
	}

	.margin-l-700px {
		margin-left: 700px;
	}

	.border-2px td {
		border: 2px solid #cbcdd9;
	}

	.padding-15px {
		padding: 15px;
	}

	.font-t-c {
		text-decoration: underline;
		color: blue
	}

	.height-43px {
		height: 43px;
	}
	
</style>
