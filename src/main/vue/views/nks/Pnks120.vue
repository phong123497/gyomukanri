<!--
//================================================================================
// ID: PNKS120
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// VueName: Pnks120
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/02 | ＡＭＣ               | 新規作成
//================================================================================
 -->
<template>
	<div class="view-area">
		<!-- メインコンテンツ -->
		<main id="PNKS120">
			<div class="contents__block--exist_return">
				<h1 class="header__block-title">各種データ出力</h1>
				<div class="contents__wrap--scroll_vertical">
					<!-- エラーメッセージ -->
					<div id="error">
						<error-message />
					</div>
					<div class="padding-15px">
						<table>
							<colgroup>
								<col style="width: 150px" />
								<col style="width: 750px" />
							</colgroup>
							<tbody>
								<tr>
									<td class="txt--align_r">出力データ&nbsp;</td>
									<td>
										<list-box
											class="width-220"
											ref="outputData"
											v-bind:blankFlg="false"
											v-bind:cdContentType="CD_CONTENT_TYPE.SHORTENED_NAME"
											v-bind:listItems="disp.outputData"
											v-bind:maxLength="7"
											v-bind:name="'出力データ'"
											v-bind:requireFlg="true"
											v-bind:tabindex="'1'"
											v-on:change="onchgOutPutDataChng()"
											v-model="form.outputData"
										></list-box>
									</td>
								</tr>
							</tbody>
						</table>
						<table id="PCYU310" v-if="disp.outputShow.cntrKakoShijiData">
							<colgroup>
								<col style="width: 150px" />
								<col style="width: 750px" />
							</colgroup>
							<tbody>
								<tr v-show="disp.showFlg">
									<td class="txt--align_r">
										<span class="contents__block-search__label">センター</span>
									</td>
									<td>
										<list-box
											class="width-220"
											ref="cntrKakoShijiData"
											v-bind:blankFlg="false"
											v-bind:cdContentType="CD_CONTENT_TYPE.SHORTENED_NAME"
											v-bind:listItems="disp.sentaData"
											v-bind:maxLength="5"
											v-bind:name="'センター（加工指示データ）'"
											v-bind:requireFlg="true"
											v-bind:tabindex="'2'"
											v-model="form.cntr"
										></list-box>
									</td>
								</tr>
								<tr>
									<td class="txt--align_r">
										<span class="contents__block-search__label">受注日</span>
									</td>
									<td>
										<date-input
											class="width-155 txt--align_l"
											ref="juchuDayStart"
											v-bind:maxLength="10"
											v-bind:name="'受注日開始'"
											v-bind:tabindex="'3'"
											v-bind:style="{ float: 'left' }"
											v-model="form.juchuDayStart"
										></date-input>
										<span class="contents__block-search__label" style="float: left; margin: 8px 15px">～</span>
										<date-input
											class="width-155 txt--align_l"
											ref="juchuDayEnd"
											v-bind:maxLength="10"
											v-bind:name="'受注日終了'"
											v-bind:tabindex="'4'"
											v-model="form.juchuDayEnd"
										></date-input>
									</td>
								</tr>
								<tr>
									<td class="txt--align_r">
										<span class="contents__block-search__label">SC納品日</span>
									</td>
									<td>
										<date-input
											class="width-155 txt--align_l"
											ref="scDelvDayStart"
											v-bind:maxLength="10"
											v-bind:name="'SC納品日開始'"
											v-bind:tabindex="'5'"
											v-bind:style="{ float: 'left' }"
											v-model="form.scDelvDayStart"
										></date-input>
										<span class="contents__block-search__label" style="float: left; margin: 8px 15px">～</span>
										<date-input
											class="width-155 txt--align_l"
											ref="scDelvDayEnd"
											v-bind:maxLength="10"
											v-bind:name="'SC納品日終了'"
											v-bind:tabindex="'6'"
											v-model="form.scDelvDayEnd"
										></date-input>
									</td>
								</tr>
							</tbody>
						</table>
						<table id="PCYU320" v-if="disp.outputShow.cntrHikiwtsShijiData">
							<colgroup>
								<col style="width: 150px" />
								<col style="width: 750px" />
							</colgroup>
							<tbody>
								<tr v-show="disp.showFlg">
									<td class="txt--align_r">
										<span class="contents__block-search__label">センター</span>
									</td>
									<td>
										<list-box
											class="width-220"
											ref="cntrHikiwtsShijiData"
											v-bind:blankFlg="false"
											v-bind:cdContentType="CD_CONTENT_TYPE.SHORTENED_NAME"
											v-bind:listItems="disp.sentaData"
											v-bind:maxLength="5"
											v-bind:name="'センター（引渡指示データ）'"
											v-bind:requireFlg="true"
											v-bind:tabindex="'2'"
											v-model="form.cntr"
										></list-box>
									</td>
								</tr>
								<tr>
									<td class="txt--align_r">
										<span class="contents__block-search__label">受注日</span>
									</td>
									<td>
										<date-input
											class="width-155 txt--align_l"
											ref="juchuDayStart"
											v-bind:maxLength="10"
											v-bind:name="'受注日開始'"
											v-bind:style="{ float: 'left' }"
											v-bind:tabindex="'3'"
											v-model="form.juchuDayStart"
										></date-input>
										<span class="contents__block-search__label" style="float: left; margin: 8px 15px">～</span>
										<date-input
											class="width-155 txt--align_l"
											ref="juchuDayEnd"
											v-bind:maxLength="10"
											v-bind:name="'受注日終了'"
											v-bind:tabindex="'4'"
											v-model="form.juchuDayEnd"
										></date-input>
									</td>
								</tr>
								<tr>
									<td class="txt--align_r">
										<span class="contents__block-search__label">SC納品日</span>
									</td>
									<td>
										<date-input
											class="width-155 txt--align_l"
											ref="scDelvDayStart"
											v-bind:maxLength="10"
											v-bind:name="'SC納品日開始'"
											v-bind:style="{ float: 'left' }"
											v-bind:tabindex="'5'"
											v-model="form.scDelvDayStart"
										></date-input>
										<span class="contents__block-search__label" style="float: left; margin: 8px 15px">～</span>
										<date-input
											class="width-155 txt--align_l"
											ref="scDelvDayEnd"
											v-bind:maxLength="10"
											v-bind:name="'SC納品日終了'"
											v-bind:tabindex="'6'"
											v-model="form.scDelvDayEnd"
										></date-input>
									</td>
								</tr>
							</tbody>
						</table>
						<table id="PCYU330" v-if="disp.outputShow.cntrSireKeijoData">
							<colgroup>
								<col style="width: 150px" />
								<col style="width: 750px" />
							</colgroup>
							<tbody>
								<tr v-show="disp.showFlg">
									<td class="txt--align_r">
										<span class="contents__block-search__label">センター</span>
									</td>
									<td>
										<list-box
											class="width-220"
											ref="cntrSireKeijoData"
											v-bind:blankFlg="false"
											v-bind:cdContentType="CD_CONTENT_TYPE.SHORTENED_NAME"
											v-bind:listItems="disp.sentaData"
											v-bind:maxLength="5"
											v-bind:name="'センター（仕入計上データ）'"
											v-bind:requireFlg="true"
											v-bind:tabindex="'2'"
											v-model="form.cntr"
										></list-box>
									</td>
								</tr>
								<tr>
									<td class="txt--align_r">
										<span class="contents__block-search__label">納入指定年月日</span>
									</td>
									<td>
										<date-input
											class="width-155 txt--align_l"
											ref="nonyuSiteiYearMonthDayStart"
											v-bind:maxLength="10"
											v-bind:name="'納入指定年月日開始'"
											v-bind:style="{ float: 'left' }"
											v-model="form.nonyuSiteiYearMonthDayStart"
										></date-input>
										<span class="contents__block-search__label" style="float: left; margin: 8px 15px">～</span>
										<date-input
											class="width-155 txt--align_l"
											ref="nonyuSiteiYearMonthDayEnd"
											v-bind:maxLength="10"
											v-bind:name="'納入指定年月日終了'"
											v-model="form.nonyuSiteiYearMonthDayEnd"
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
					<div class="left-block">
						<div class="button-group" style="float: left">
							<button-input
								class="contents__btn-blue"
								v-bind:style="{ 'margin-right': '30px' }"
								v-bind:tabindex="'8'"
								v-bind:value="'戻る'"
								v-on:click="clickReturnBtn()"
							></button-input>
							<button-input
								class="contents__btn-blue"
								v-bind:tabindex="'9'"
								v-bind:value="'取消'"
								v-on:click="clickCancelBtn()"
							></button-input>
						</div>
					</div>
					<div class="center-block" style="float: left">
						<button-input
							class="contents__btn-blue"
							v-bind:style="{ 'margin-left': '192px' }"
							v-bind:tabindex="'7'"
							v-bind:value="'CSV出力'"
							v-on:click="clickCsvOutputBtn()"
						></button-input>
					</div>
				</div>
			</div>
		</footer>
	</div>
</template>

<script>
import ButtonInput from '@/components/ButtonInput'
import DateInput from '@/components/DateInput'
import ListBox from '@/components/ListBox.vue'
import ErrorMessage from '@/components/ErrorMessage.vue'

import { co_downloadFile, co_setError } from '@/common/ajaxUtility.js'
import { co_isEmpty } from '@/common/checkUtility.js'
import { co_getMessage } from '@/common/messageUtility.js'

import { 
	CNS_NKS_KENGEN, 
	CD_CONTENT_TYPE, 
	CNS_NKS_SENTA_KBN, 
	HTTP_STATUS 
} from '@/const/const.js'

import { 
	STORE_ERROR_CLEAR, 
	STORE_ERROR_IS_ERRFLG, 
	STORE_VIEW_PROGRESS,
	STORE_USER_GET_INFO,
	STORE_ERROR_ADDMSG
} from '@/const/store.js'

import { PATH_PNKS120_OUTPUT } from '@/const/route.js'

export default {
	components: {
		'button-input': ButtonInput,
		'date-input': DateInput,
		'list-box': ListBox,
		'error-message': ErrorMessage
	},
	data: function () {
		return {
			form: {
				outputData: '1', 					//出力データ
				cntr: CNS_NKS_SENTA_KBN.CNS_NKS_SENTA_KBN_SASAKURI_CODE,//センター
				juchuDayStart: '', 					// 受注日開始
				juchuDayEnd: '', 					// 受注日終了
				scDelvDayStart: '', 				// SC納品日開始
				scDelvDayEnd: '', 					// SC納品日終了
				nonyuSiteiYearMonthDayStart: '', 	// 納入指定年月日開始
				nonyuSiteiYearMonthDayEnd: '' 		// 納入指定年月日終了
			},
			disp: {
				// 出力データリスト
				outputData: [
					{ code: '1', ryakuName: '加工指示データ' },
					{ code: '2', ryakuName: '引渡指示データ' },
					{ code: '3', ryakuName: '仕入計上データ' }
				],
				// 入力ボックスの表示を制御する
				outputShow: {
					cntrKakoShijiData: false,
					cntrHikiwtsShijiData: false,
					cntrSireKeijoData: false
				},
				// センターリスト
				sentaData: [
					{ code: CNS_NKS_SENTA_KBN.CNS_NKS_SENTA_KBN_SASAKURI_CODE, ryakuName: CNS_NKS_SENTA_KBN.CNS_NKS_SENTA_KBN_SASAKURI_NAME },
					{ code: CNS_NKS_SENTA_KBN.CNS_NKS_SENTA_KBN_AIRA_CODE, ryakuName: CNS_NKS_SENTA_KBN.CNS_NKS_SENTA_KBN_AIRA_NAME }
				],
				showFlg: true, // 本部の場合、センターを表示する
			},
			kengen: {}, // ログインユーザーの権限
			tantoshaInfo : '',
			CD_CONTENT_TYPE: CD_CONTENT_TYPE
		}
	},
	created: function () {
		// ログインユーザーの権限を取得する
		this.tantoshaInfo = this.$store.getters[STORE_USER_GET_INFO]
		this.kengen = this.tantoshaInfo.ctrlKbn

		this.form.cntr = this.tantoshaInfo.tempoCd

		// ページの項目制御
		if (this.kengen == CNS_NKS_KENGEN.CNS_NKS_KENGEN_HONSYA) {
			this.disp.showFlg = true
		} else {
			this.disp.showFlg = false
		}

		// 出力データ変更オンチェンジ
		this.onchgOutPutDataChng()
	},
	mounted: function () {
		// フォーカスを設定
		this.$nextTick(function () {
			this.$refs.outputData.focus()
		})
	},
	methods: {
		// 戻るボタンクリック
		clickReturnBtn: function () {
			this.$router.back()
		},
		// 取消ボタンクリック
		clickCancelBtn: function () {
			// 状態管理(ストア)のエラー情報をクリアする。
			this.$store.commit(STORE_ERROR_CLEAR)

			this.form.outputData = '1'

			// 出力データ変更オンチェンジ
			this.onchgOutPutDataChng()
			// フォーカスを設定
			this.$nextTick(function () {
				this.$refs.outputData.focus()
			})
		},
		// CSV出力ボタンクリック
		clickCsvOutputBtn: function () {
			// 状態管理(ストア)のエラー情報をクリアする。
			this.$store.commit(STORE_ERROR_CLEAR)
			// プログレスバー表示
			this.$store.commit(STORE_VIEW_PROGRESS, true)
			console.log(this.$refs.juchuDayStart)
			// ** チェック処理 **
			this.$refs.outputData.validate()
			if (this.kengen == CNS_NKS_KENGEN.CNS_NKS_KENGEN_HONSYA) {
				switch (this.form.outputData) {
					case '2':
						this.$refs.cntrHikiwtsShijiData.validate()
						break
					case '3':
						this.$refs.cntrSireKeijoData.validate()
						break
					default:
						this.$refs.cntrKakoShijiData.validate()
						break
				}
			}
			switch (this.form.outputData) {
				case '2':
					this.$refs.juchuDayStart.validate()
					this.$refs.juchuDayEnd.validate()
					this.$refs.scDelvDayStart.validate()
					this.$refs.scDelvDayEnd.validate()
					if (!this.form.juchuDayStart && !this.form.juchuDayEnd && !this.form.scDelvDayStart && !this.form.scDelvDayEnd) {
						this.$store.commit(STORE_ERROR_ADDMSG, co_getMessage('NKS0004E'))
							this.$refs.juchuDayStart.isError = true
							this.$refs.juchuDayEnd.isError = true
							this.$refs.scDelvDayStart.isError = true
							this.$refs.scDelvDayEnd.isError = true
					}
					break
				case '3':
					this.$refs.nonyuSiteiYearMonthDayStart.validate()
					this.$refs.nonyuSiteiYearMonthDayEnd.validate()
					if (!this.form.nonyuSiteiYearMonthDayStart && !this.form.nonyuSiteiYearMonthDayEnd) {
						this.$store.commit(STORE_ERROR_ADDMSG, co_getMessage('NKS0005E', '納入指定年月日'))
						this.$refs.nonyuSiteiYearMonthDayStart.isError = true
						this.$refs.nonyuSiteiYearMonthDayEnd.isError = true
					}
					break
				default:
					this.$refs.juchuDayStart.validate()
					this.$refs.juchuDayEnd.validate()
					this.$refs.scDelvDayStart.validate()
					this.$refs.scDelvDayEnd.validate()
					if (!this.form.juchuDayStart && !this.form.juchuDayEnd && !this.form.scDelvDayStart && !this.form.scDelvDayEnd) {
						this.$store.commit(STORE_ERROR_ADDMSG, co_getMessage('NKS0004E'))
						this.$refs.juchuDayStart.isError = true
						this.$refs.juchuDayEnd.isError = true
						this.$refs.scDelvDayStart.isError = true
						this.$refs.scDelvDayEnd.isError = true
					}
					break
			}

			// ** エラー表示制御 **
			if (this.$store.getters[STORE_ERROR_IS_ERRFLG]) {
				this.$store.commit(STORE_VIEW_PROGRESS, false)
				return
			}

			// CSVファイルをダウンロードするリクエスト
			let self = this
			co_downloadFile(PATH_PNKS120_OUTPUT, this.form, function (res, downloadHandle) {
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
		// 出力データ変更オンチェンジ
		onchgOutPutDataChng: function () {
			this.$nextTick(function(){
				switch (this.form.outputData) {
					case '2':
						this.disp.outputShow.cntrKakoShijiData = false
						this.disp.outputShow.cntrHikiwtsShijiData = true
						this.disp.outputShow.cntrSireKeijoData = false
						break
					case '3':
						this.disp.outputShow.cntrKakoShijiData = false
						this.disp.outputShow.cntrHikiwtsShijiData = false
						this.disp.outputShow.cntrSireKeijoData = true
						break
					default:
						this.disp.outputShow.cntrKakoShijiData = true
						this.disp.outputShow.cntrHikiwtsShijiData = false
						this.disp.outputShow.cntrSireKeijoData = false
						break
				}
				// 状態管理(ストア)のエラー情報をクリアする。
				this.$store.commit(STORE_ERROR_CLEAR)
				this.clearFormData()
			})
		},
		// フォームのデータをクリアする
		clearFormData: function(){
			this.form.cntr = CNS_NKS_SENTA_KBN.CNS_NKS_SENTA_KBN_SASAKURI_CODE
			this.form.juchuDayStart = ''
			this.form.juchuDayEnd = ''
			this.form.scDelvDayStart = ''
			this.form.scDelvDayEnd = ''
			this.form.nonyuSiteiYearMonthDayStart = ''
			this.form.nonyuSiteiYearMonthDayEnd = ''
		}
	}
}
</script>
<style scoped>
.padding-15px {
	padding: 15px;
}
.width-220 {
	width:220px;
}
</style>
