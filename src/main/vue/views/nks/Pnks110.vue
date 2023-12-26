<!--
//================================================================================
// ID: PNKS110
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// VueName: Pnks110
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/02 | ＡＭＣ               | 新規作成
//================================================================================
 -->
 <template>
	<div>
		<main id="PNKS110">
			<div class="contents__block--exist_return">
				<h1 class="header__block-title-with-input">
					<table>
						<tbody>
							<tr>
								<td>
									調達加工計画書印刷
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
								<tr v-if="isVisible">
									<td class="txt--align_r">
										<span class="contents__block-search__label">センター</span>
									</td>
									<td colspan="2">
										<list-box 
											class="width-130"
											ref="cntr"
											v-bind:cdContentType="CD_CONTENT_TYPE.SHORTENED_NAME"
											v-bind:cdSbt="CNS_NKS_SENTA_KBN"
											v-bind:listItems="disp.shoriSbtList"
											v-bind:name="'センター'"
											v-bind:requireFlg="true"
											v-bind:tabindex="'1'"
											v-model="form.cntr">
										</list-box>
									</td>
								</tr>
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
											v-bind:tabindex="'2'" 
											v-model="form.haitatYm"/>
									</td>
									<td>
										<code-input  
											class="width-40"
											ref="haitatTimes"
											v-bind:name="'配達回'"
											v-bind:maxLength="1"
											v-bind:requireFlg="true"
											v-bind:tabindex="'3'" 
											v-model="form.haitatTimes"/>
										<span>&nbsp;回</span>
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
								v-bind:name="'backButton'"
								v-bind:tabindex="'5'"
								v-bind:style="{ 'margin-right': '30px' }"
								v-bind:value="'戻る'"
								v-on:click="clickReturnBtn" />
							<button-input
								class="contents__btn-blue"
								v-bind:name="'cancelBtn'"
								v-bind:tabindex="'6'"
								v-bind:value="'取消'"
								v-on:click="clickCancelBtn" />
						</div>
					</div>
					<div class="center-block">
						<div class="button-group">
							<button-input
								class="contents__btn-blue"
								v-bind:name="'printButton'"
								v-bind:tabindex="'4'"
								v-bind:value="'印刷'"
								v-on:click="clickPrtBtn" />
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

import { co_isEmpty } from '@/common/checkUtility.js';

import { 
	co_post,
	co_setError,
} from '@/common/ajaxUtility.js';

import {
	CD_CONTENT_TYPE,
	DATEINPUT_TYPE,
	HTTP_STATUS,
	CNS_NKS_SENTA_KBN,
	CNS_NKS_KENGEN
} from '@/const/const.js';

import {
	PATH_PNKS110_PNKS11001PRINT,
} from '@/const/route.js'

import {
	STORE_ERROR_CLEAR,
	STORE_ERROR_IS_ERRFLG,
	STORE_VIEW_PROGRESS,
	STORE_USER_GET_INFO,
} from '@/const/store.js';

export default {
	components: {
		'button-input': ButtonInput,
		'date-input': DateInput,
		'error-message': ErrorMessage,
		'list-box': ListBox,
		'code-input': CodeInput,
	},
	data: function () {
		return {
			form: {
				haitatYm: '',		// 配達年月
				haitatTimes: '',	// 配達回
				cntr: '',			// センター
			},
			disp: {
				shoriSbtList: [
					{ code: CNS_NKS_SENTA_KBN.CNS_NKS_SENTA_KBN_SASAKURI_CODE, ryakuName: CNS_NKS_SENTA_KBN.CNS_NKS_SENTA_KBN_SASAKURI_NAME },
					{ code: CNS_NKS_SENTA_KBN.CNS_NKS_SENTA_KBN_AIRA_CODE, ryakuName: CNS_NKS_SENTA_KBN.CNS_NKS_SENTA_KBN_AIRA_NAME }
				],
			},
			isVisible: false,
			permissions:null, // 権限
			CD_CONTENT_TYPE: CD_CONTENT_TYPE,
			DATEINPUT_TYPE: DATEINPUT_TYPE,
			CNS_NKS_SENTA_KBN: CNS_NKS_SENTA_KBN,
		}
	},
	created: async function() {
		// 権限の取得
		this.permissions = this.$store.getters[STORE_USER_GET_INFO];

		// センター制御
		if (this.permissions.ctrlKbn == CNS_NKS_KENGEN.CNS_NKS_KENGEN_HONSYA) {
			this.isVisible = true;
		} else {
			this.isVisible = false;
		}
	},
	mounted: async function() {
		await this.$nextTick();
		// フォーカス設定
		if (this.permissions.ctrlKbn == CNS_NKS_KENGEN.CNS_NKS_KENGEN_HONSYA) {
			this.$refs.cntr.focus();
		} else {
			this.$refs.haitatYm.focus();
		}		
	},

	methods: {
		// 取消ボタンクリック
		clickCancelBtn: async function() {
			// エラーメッセージをクリアする
			this.$store.commit(STORE_ERROR_CLEAR);
			// 配達年月
			this.form.haitatYm = '';
			// 配達回
			this.form.haitatTimes = '';
			// センター
			this.form.cntr = '';
			
			await this.$nextTick();
			// フォーカス設定
			if (this.permissions.ctrlKbn == CNS_NKS_KENGEN.CNS_NKS_KENGEN_HONSYA) {
				this.$refs.cntr.focus();
			} else {
				this.$refs.haitatYm.focus();
			}
		},

		// 戻るボタンクリック
		clickReturnBtn: function() {
			this.$router.back();
		},
		
		// 印刷ボタンクリック
		clickPrtBtn: function() {
			
			// エラーをクリア
			this.$store.commit(STORE_ERROR_CLEAR)
			
			// 入力チェック処理
			if (this.permissions.ctrlKbn == CNS_NKS_KENGEN.CNS_NKS_KENGEN_HONSYA) {
				this.$refs.cntr.validate();
				this.$refs.haitatYm.validate();
				this.$refs.haitatTimes.validate();
			} else {
				this.$refs.haitatYm.validate();
				this.$refs.haitatTimes.validate();
			}

			// プログレスバー表示
			this.$store.commit(STORE_VIEW_PROGRESS, true)
			
			// エラー表示制御
			if ( this.$store.getters[STORE_ERROR_IS_ERRFLG]) {
				this.$store.commit(STORE_VIEW_PROGRESS, false);
				return;
			}

			if (this.permissions.ctrlKbn != CNS_NKS_KENGEN.CNS_NKS_KENGEN_HONSYA) {
				this.form.cntr = this.permissions.tempoCd;
			}

			const self = this;
			co_post(PATH_PNKS110_PNKS11001PRINT, this.form, function(res) {
				if (res.status == HTTP_STATUS.OK) {
					// TODO 帳票の実装方法はまだ確認中、保留
					// プログレスバー非表示
					self.outPutPDF(res.data.meiSaiList);
				} else {
					// プログレスバー非表示
					self.$store.commit(STORE_VIEW_PROGRESS, false);

					co_setError(res.data, self.$refs)
					if (!co_isEmpty(self.$refs[res.data[0].errorItems[0]])) {
						self.$nextTick(function () {
							self.$refs[res.data[0].errorItems[0]].focus()
						})
					}
				}
			})
		},
        outPutPDF: async function(excelJsonData) {
			
			
			// 帳票定義体JSONデータ取得
			
			const reportResponse = await fetch("reports/nks/PNKS11001.rdlx-json");
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
			
			const choHyoFileName = `調達加工計画書_${yyyyMMddHHmmss}`;
			
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

<style type="text/css">
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