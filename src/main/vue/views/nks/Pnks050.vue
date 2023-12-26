<!--
//================================================================================
// ID: PNKS050
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// VueName: Pnks050
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/26 | ＡＭＣ               | 新規作成
//================================================================================
 -->
<template>
	<div>
		<!-- メインコンテンツ -->
		<main id="PNKS050">
			<div class="contents__block--exist_return">
			
				<h1 class="header__block-title">価格表印刷</h1>
				
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
										<span class="contents__block-search__label">企画番号</span>
									</td>
									<td>
										<code-input
											class="width-80"
											ref="projNo"
											v-bind:activeZeroPadding="true"
											v-bind:maxLength="3"
											v-bind:name="'企画番号'"
											v-bind:requireFlg="true"
											v-bind:tabindex="'1'"
											v-model="form.projNo"
										/>
									</td>
								</tr>
								<tr>
									<td class="txt--align_r">
										<span class="contents__block-search__label">金額表示あり</span>
									</td>
									<td>
										<check-box  class="contents__block-checkbox"
											ref="gakuHyojiAri"
											v-bind:listItems="disp.gakuHyojiAriList"
											v-bind:maxLength="1"
											v-bind:name="'金額表示あり'"
											v-bind:tabindex="'2'"
											v-model="form.gakuHyojiAri" />
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
import CheckBox from '@/components/CheckBox.vue';
import CodeInput from '@/components/CodeInput.vue';
import ErrorMessage from '@/components/ErrorMessage.vue';

import {
	co_post,
	co_setError, } from '@/common/ajaxUtility.js';
import {
	HTTP_STATUS
} from '@/const/const.js';

import {
	PATH_PNKS050_PNKS05001PRINT,
} from '@/const/route.js';

import {
	STORE_INFO_CLEAR,
	STORE_ERROR_CLEAR,
	STORE_ERROR_IS_ERRFLG,
	STORE_VIEW_PROGRESS,
} from '@/const/store.js';

export default {
	components: {
		'button-input': ButtonInput,
		'check-box': CheckBox,
		'code-input': CodeInput,
		'error-message': ErrorMessage,
	},
	data: function () {
		return {
			form: {
				// 企画番号
				projNo: '',
				// 金額表示あり
				gakuHyojiAri: '1',
			},
			disp: {
				gakuHyojiAriList: [{code: "1", name: "　"},], 		// 金額表示あり
			},
		}
	},
	mounted: async function() {
		await this.$nextTick();
		// ** フォーカス設定 **
		this.$refs.projNo.focus();
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
			
			// 企画番号
			this.form.projNo = '';
			// 金額表示あり
			this.form.gakuHyojiAri = '';
			
			await this.$nextTick();
			// ** フォーカス設定 **
			this.$refs.projNo.focus();
		},
		// click - 印刷ボタン押下
		clickprintBtn: function() {
			
			this.$store.commit(STORE_ERROR_CLEAR);
			this.$store.commit(STORE_VIEW_PROGRESS, true);
			
			// ** チェック処理 **
			this.$refs.projNo.validate();
			this.$refs.gakuHyojiAri.validate();

			// ** エラー表示制御 **
			if (this.$store.getters[STORE_ERROR_IS_ERRFLG]) {
				this.$store.commit(STORE_VIEW_PROGRESS, false);
				return;
			}
			
			// 帳票をダウンロードするリクエスト
			const self = this;
			co_post(PATH_PNKS050_PNKS05001PRINT, this.form, function(res) {
				if (res.status == HTTP_STATUS.OK) {
					// 企画案内書を印刷する。
					self.outPutPDF(res.data);
				} else {
				self.$store.commit(STORE_VIEW_PROGRESS, false);
				// TODO 帳票の実装方法はまだ確認中、保留
				if (res.status != HTTP_STATUS.OK) {
					// 異常終了
					co_setError(res.data, self.$refs);
					return;
				}
				}
				
			});
		},
		outPutPDF: async function(excelJsonData) {
			
						
			// 帳票定義体JSONデータ取得
			
			const reportResponse = await fetch("reports/nks/PNKS05001.rdlx-json");
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
			
			const choHyoFileName = `価格表_${yyyyMMddHHmmss}`;
			
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
</style>
