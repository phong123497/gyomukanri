<!--
//================================================================================
// ID: PNKS100
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// VueName: Pnks100
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/27 | ＡＭＣ               | 新規作成
//================================================================================
 -->
<template>
	<div>
		<!-- メインコンテンツ -->
		<main id="PNKS100">
			<div class="contents__block--exist_return">
			
				<h1 class="header__block-title">バッチ計画表印刷</h1>
				
				<!-- エラーメッセージ -->
				<error-message />
				
				<div class="contents__wrap--scroll_vertical">
					<div class="padding-15px">
						<table>
							<colgroup>
								<col style="width:150px">
								<col style="width:700px">
							</colgroup>
							<tbody>
								<tr>
									<td class="txt--align_r">
										<span class="contents__block-search__label">配達年月回</span>
									</td>
									<td>
										<div class="display-style">
											<date-input
												class="width-150"
												ref="haitatYm"
												v-bind:allowOmitedValue="true"
												v-bind:dateType="DATEINPUT_TYPE.YM"
												v-bind:maxLength="7"
												v-bind:name="'配達年月'"
												v-bind:requireFlg="true"
												v-bind:tabindex="'1'"
												v-model="form.haitatYm"
											/>
											<div class="margin-left-18">
												<number-input
													class="width-40"
													ref="haitatTimes"
													v-bind:maxLength="1"
													v-bind:name="'配達回'"
													v-bind:numberType="NUMBERINPUT_TYPE.INTEGER"
													v-bind:requireFlg="true"
													v-bind:tabindex="'2'"
													v-model="form.haitatTimes"
												/>
											</div>
											<span class="contents__block-search__label margin-left-5">回</span>
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
				<div class="footer-button__block btn-display-change">
					<div class="left-block right-adjustment">
						<div class="button-group">
							<button-input
								class="contents__btn-blue margin-right-30"
								v-bind:name="'backButton'"
								v-bind:tabindex="'4'"
								v-bind:value="'戻る'"
								v-on:click="clickReturnBtn" 
							/>
							<button-input
								class="contents__btn-blue"
								v-bind:name="'cancelButton'"
								v-bind:tabindex="'5'"
								v-bind:value="'取消'"
								v-on:click="clickCancelBtn"
							/>
						</div>
					</div>
					<div class="center-block">
						<div class="button-group">
							<button-input
								class="contents__btn-blue"
								v-bind:value="'印刷'"
								v-bind:name="'printButton'"
								v-bind:tabindex="'3'"
								v-on:click="clickprintBtn"
							/>
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
import DateInput from '@/components/DateInput.vue';
import NumberInput from '@/components/NumberInput.vue';
import ErrorMessage from '@/components/ErrorMessage.vue';

import {
	co_post,
	co_setError
} from '@/common/ajaxUtility.js';

import { co_isEmpty } from '@/common/checkUtility.js';

import {
	HTTP_STATUS,
	DATEINPUT_TYPE,
	NUMBERINPUT_TYPE
} from '@/const/const.js';

import { PATH_PNKS100_PNKS10001PRINT } from '@/const/route.js';

import {
	STORE_INFO_CLEAR,
	STORE_ERROR_CLEAR,
	STORE_ERROR_IS_ERRFLG,
	STORE_VIEW_PROGRESS,
} from '@/const/store.js';

export default {
	components: {
		'button-input': ButtonInput,
		'date-input': DateInput,
		'number-input': NumberInput,
		'error-message': ErrorMessage,
	},
	data: function () {
		return {
			form: {
				// 配達年月
				haitatYm: '',
				// 配達回
				haitatTimes: '',
			},
			// 配達年月日付タイプ
			DATEINPUT_TYPE: DATEINPUT_TYPE,
			// 配達回数字タイプ
			NUMBERINPUT_TYPE: NUMBERINPUT_TYPE,
		}
	},
	mounted: async function() {
		await this.$nextTick();
		// ** フォーカス設定 **
		this.$refs.haitatYm.focus();
	},
	methods: {
		// click - 戻るボタン押下
		clickReturnBtn: function() {
			
			this.$store.commit(STORE_INFO_CLEAR);
			this.$router.back();
		},
		// click - 取消ボタン押下
		clickCancelBtn: async function() {
			
			// エラーメッセージをクリアする
			this.$store.commit(STORE_ERROR_CLEAR);
			
			// 配達年月
			this.form.haitatYm = '';
			// 配達回
			this.form.haitatTimes = '';
			
			await this.$nextTick();
			// ** フォーカス設定 **
			this.$refs.haitatYm.focus();
		},
		// click - 印刷ボタン押下
		clickprintBtn: function() {
			
			this.$store.commit(STORE_ERROR_CLEAR);
			this.$store.commit(STORE_VIEW_PROGRESS, true);
			
			// ** チェック処理 **
			this.$refs.haitatYm.validate();
			this.$refs.haitatTimes.validate();
			
			// ** エラー表示制御 **
			if (this.$store.getters[STORE_ERROR_IS_ERRFLG]) {
				this.$store.commit(STORE_VIEW_PROGRESS, false);
				return;
			}
			
			// 帳票をダウンロードするリクエスト
			const self = this;
			co_post(PATH_PNKS100_PNKS10001PRINT, this.form, function(res) {
				if (res.status == HTTP_STATUS.OK) {
					// 企画案内書を印刷する。
					self.outPutPDF(res.data);
				} else {
				self.$store.commit(STORE_VIEW_PROGRESS, false);
				if (res.status != HTTP_STATUS.OK) {
					// 異常終了
					co_setError(res.data, self.$refs);
					if (!co_isEmpty(self.$refs[res.data[0].errorItems[0]])) {
						self.$refs[res.data[0].errorItems[0]].focus();
					}
					return;
				}
				}
				
			});
		},
		// PDF帳票印刷
		outPutPDF: async function(excelJsonData) {
			
						
			// 帳票定義体JSONデータ取得
			
			const reportResponse = await fetch("reports/nks/PNKS10001.rdlx-json");
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
			
			const choHyoFileName = `バッチ計画表_${yyyyMMddHHmmss}`;
			
			// ダウンロードする 
			result.download(choHyoFileName);
			this.$store.commit(STORE_VIEW_PROGRESS, false);
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
	.padding-15px {
		padding: 15px;
	}
	.display-style {
		display: flex;
		align-items: center;
	}
	.btn-display-change {
		display: flex;
	}
	.right-adjustment {
		margin-right: 11%;
	}
	.margin-left-5 {
		margin-left: 5px;
	}
	.margin-left-18 {
		margin-left: 18px;
	}
	.margin-right-30 {
		margin-right: 30px;
	}
</style>