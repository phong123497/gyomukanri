<!--
//================================================================================
// ID: PNKS150
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// VueName: Pnks150
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/01 | ＡＭＣ               | 新規作成
//================================================================================
 -->
<template>
	<div>
		<!-- メインコンテンツ -->
		<main id="PNKS150">
			<div class="contents__block--exist_return">
				<h1 class="header__block-title-with-input">
					<table>
						<tbody>
							<tr>
								<td>
									納品指示書発行指示
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
								<col style="width: 170px;">
								<col style="width: 630px;">
							</colgroup>
							<tbody>
								<tr>
									<td class="txt--align_r">
										<span class="contents__block-search__label">配達年月回</span>
									</td>
									<td>
										<date-input
											class="width-150  txt--align_r"
											ref="haitatYm"
											v-bind:dateType="DATEINPUT_TYPE.YM"
											v-bind:name="'配達年月'"
											v-bind:maxLength="7"
											v-bind:requireFlg="true"
											v-bind:allowOmitedValue="true"
											v-bind:tabindex="'1'" 
											v-model="form.haitatYm"/>
									</td>
									<td>
										<code-input 
											class="width-40"
											ref="haitatTimes"
											v-bind:name="'配達回'"
											v-bind:maxLength="1"
											v-bind:requireFlg="true"
											v-bind:tabindex="'2'" 
											v-model="form.haitatTimes"/>
										<span style="padding-left: 3px;">回</span>
									</td>
								</tr>
								<tr>
									<td class="txt--align_r">
										<span class="contents__block-search__label">メーカーコード</span>
									</td>
									<td colspan="2">
										<list-box 
											class="width-350"
											ref="maker"
											v-bind:cdContentType="CD_CONTENT_TYPE.CODENAME"
											v-bind:listItems="disp.makerComboxList"
											v-bind:name="'メーカー'"
											v-bind:tabindex="3"
											v-model="form.maker">
										</list-box>
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
								v-bind:tabindex="'5'"
								v-bind:value="'戻る'"
								v-on:click="clickBack" />
							<button-input
								class="contents__btn-blue"
								v-bind:name="'clickClear'"
								v-bind:tabindex="'6'"
								v-bind:value="'取消'"
								v-on:click="clickClear" />
						</div>
					</div>
					<div class="center-block">
						<div class="button-group">
							<button-input
								class="contents__btn-blue"
								v-bind:name="'printButton'"
								v-bind:tabindex="'4'"
								v-bind:value="'印刷'"
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
import { Core, PdfExport } from "@grapecity/activereports";
import ButtonInput from '@/components/ButtonInput.vue';
import CodeInput from '@/components/CodeInput.vue';
import DateInput from '@/components/DateInput.vue';
import ErrorMessage from '@/components/ErrorMessage.vue';
import ListBox from '@/components/ListBox.vue';

import {
	co_isEmpty,
} from '@/common/checkUtility.js';

import { 
	co_post,
	co_setError,
} from '@/common/ajaxUtility.js';
import {
	CD_CONTENT_TYPE,
	DATEINPUT_TYPE,
	HTTP_STATUS,
} from '@/const/const.js';
import {
	PATH_PNKS150,
	PATH_PNKS150_PNKS15001PRINT,
} from '@/const/route.js'

import {
	STORE_ERROR_CLEAR,
	STORE_ERROR_IS_ERRFLG,
	STORE_VIEW_PROGRESS,
} from '@/const/store.js';

export default {
	props: {
	},
	components: {
		'button-input': ButtonInput,
		'code-input': CodeInput,
		'date-input': DateInput,
		'error-message': ErrorMessage,
		'list-box': ListBox,
	},
	data: function () {
		return {
			form: {
				haitatYm: '',		// 配達年月
				haitatTimes: '',	// 配達回
				maker: '',			// メーカー
			},
			disp: {
				makerComboxList: [],	//メーカーコンボボックスリスト
			},
			CD_CONTENT_TYPE: CD_CONTENT_TYPE,
			DATEINPUT_TYPE: DATEINPUT_TYPE,
		}
	},
	created: async function() {
		// プログレスバー表示
		this.$store.commit(STORE_VIEW_PROGRESS, true)
		co_post(PATH_PNKS150, this.form, this.createBackFunction)
	},
	mounted: async function() {
		await this.$nextTick();
		// フォーカス設定
		this.$refs.haitatYm.focus();
	},
	beforeDestroy: function() {
	},
	computed: {
	},
	watch: {
	},
	methods: {
		// 初期化
		createBackFunction:function(res){
			// エラーをクリア
			this.$store.commit(STORE_ERROR_CLEAR)
			if (res.status == HTTP_STATUS.OK) {
				// メーカーリストボックス
				let makerComboxList = []
				for (let j = 0; j < res.data.makerComboxList.length; j++){
					makerComboxList.push({
						code: res.data.makerComboxList[j].makercd,
						name: res.data.makerComboxList[j].makerkj
					})
				}
				this.disp.makerComboxList = makerComboxList
			}
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
			// 配達年月
			this.form.haitatYm = '';
			// 配達回
			this.form.haitatTimes = '';
			// メーカー
			this.form.maker = '';
			
			await this.$nextTick();
			// ** フォーカス設定 **
			this.$refs.haitatYm.focus();
		},
		
		// click - 印刷
		clickPrintBtn: function() {
			// プログレスバー表示
			this.$store.commit(STORE_VIEW_PROGRESS, true);
			
			// エラーをクリア
			this.$store.commit(STORE_ERROR_CLEAR)
			
			// ** チェック処理 **
			this.$refs.haitatYm.validate();
			this.$refs.haitatTimes.validate();
			// ** エラー表示制御 **
			if ( this.$store.getters[STORE_ERROR_IS_ERRFLG]) {
				this.$store.commit(STORE_VIEW_PROGRESS, false);
				return;
			}
			const self = this;
			co_post(PATH_PNKS150_PNKS15001PRINT, this.form, function(res) {
				if (res.status == HTTP_STATUS.OK) {
					self.outPutPDF(res.data);
				}else{
					co_setError(res.data, self.$refs)
					const errorItem = res.data[0].errorItems[0];
					if (!co_isEmpty(self.$refs[errorItem])) {
						self.$refs[errorItem].focus();
					}
				}
				// プログレスバー非表示
				self.$store.commit(STORE_VIEW_PROGRESS, false);
			})
		},
		outPutPDF: async function(excelJsonData) {
						
			// 帳票定義体JSONデータ取得
			const reportResponse = await fetch("reports/nks/PNKS15001.rdlx-json");
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
			ARJS.FontStore.registerFonts('fonts/fontsConfig.json');
			
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
			let choHyoFileName = "納品指示書_" ;
			
			if (this.form.maker){
				for (let j = 0; j < this.disp.makerComboxList.length; j++){
					if (this.disp.makerComboxList[j].code == this.form.maker){
						choHyoFileName = choHyoFileName + this.disp.makerComboxList[j].code 
						+ "_"  + this.disp.makerComboxList[j].name + "_" ;
						break;
					}
				}
			}
			choHyoFileName = choHyoFileName
				+ this.form.haitatYm.substring(0,4) + "年"
				+ this.form.haitatYm.substring(4,6) + "月"
				+ this.form.haitatTimes + "週_"
				+ `${yyyyMMddHHmmss}`;
			// ダウンロードする 
			result.download(choHyoFileName);
			this.$store.commit(STORE_VIEW_PROGRESS, false);
		},
	}
}
</script>

<style scoped>
.padding-15px {
	padding: 15px;
}
.footer-button__block {
    display: flex;
}
.footer-button__block .left-block {
    display: inline-flex;
    width: 25%;
}
.footer-button__block .center-block {
    display: inline-flex;
    width: 45%;
}
.footer-button__block .right-block {
    display: inline-flex;
    justify-content: flex-end;
    width: 30%;
}
</style>
