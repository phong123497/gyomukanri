<!--
//================================================================================
// ID: PSKC020
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// VueName: Pskc020
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/11 | ＡＭＣ               | 新規作成
//================================================================================
 -->
<template>
	<div>
		<main id="PSKC020">
			<div class="contents__block--exist_return">
				<!-- タイトル -->
				<h1 class="header__block-title">製造計画数取込・出力</h1>

				<!-- エラー情報 -->
				<error-message />

				<div class="contents__wrap--scroll_vertical">
					<div class="contents__view--bg_gray">
						<table class="tbl-item--upload width-1800px">
							<tbody>
								<tr class="border-b">
									<td>
										<span>処理区分</span>
									</td>
									<td>
										<div class="contents__block-radio">
											<radio-button
												ref="procsKbn"
												v-bind:listItems="disp.radioButtonListItems"
												v-bind:name="'処理区分'"
												v-bind:radioGroupName="'procsKbn'"
												v-bind:requireFlg="true"
												v-bind:tabindex="'1'"
												v-model="form.procsKbn"
												v-on:change="changeProcsKbn()"
											/>
										</div>
									</td>
								</tr>
								<tr class="border-b">
									<td>
										<span>納品日</span>
									</td>
									<td>
										<date-input
											class="width-150 txt--align_l"
											ref="delvDayFrom"
											v-bind:maxLength="10"
											v-bind:name="'納品日（自）'"
											v-bind:requireFlg="true"
											v-bind:style="{ float: 'left' }"
											v-bind:tabindex="'5'"
											v-model="form.delvDayFrom"
										></date-input>
										<span style="float: left; margin: 8px 15px">～</span>
										<date-input
											class="width-150 txt--align_l"
											ref="delvDayTo"
											v-bind:disabledFlg="disabledFlg.delvDayToDisabled"
											v-bind:maxLength="10"
											v-bind:name="'納品日（至）'"
											v-bind:requireFlg="true"
											v-bind:tabindex="'6'"
											v-model="form.delvDayTo"
										></date-input>
									</td>
								</tr>
								<tr>
									<td>
										<span>取込元</span>
									</td>
									<td>
										<file-upload
											ref="torikmMoto"
											v-bind:disabledFlg="disabledFlg.fileDisabled"
											v-bind:fileExtensions="disp.fileExtensionList"
											v-bind:fileSize="2"
											v-bind:messageType="'CSV'"
											v-bind:name="'取込元'"
											v-bind:requireFlg="true"
											v-bind:tabindex="'7'"
											v-model="form.csvFile"
										/>
									</td>
								</tr>
							</tbody>
						</table>
						<section v-if="showFlag.tableShowFlag">
							<div class="contents__block-top">
								<div class="contents__block-top__area--float_l">
									<h2 class="contents__h2">取込結果</h2>
								</div>
								<div class="contents__block-top__area--float_r" style="padding-top: 10px">
									<span class="margin-r-790px">{{ tableListCount }}件</span>
								</div>
							</div>
							<div class="tbl-list__wrap--small">
								<table class="tbl-list--border-all_exist tbl-sticky-head wrap--overflow_hidden">
									<colgroup>
										<col style="width: 140px" />
										<col style="width: 290px" />
										<col style="width: 66px" />
										<col style="width: 540px" />
									</colgroup>
									<thead style="text-align:left;">
										<tr>
											<th>ファイル名</th>
											<th>取込結果</th>
											<th>行番号</th>
											<th>エラー内容</th>
										</tr>
									</thead>
								</table>
								<table class="tbl-list--border-all_exist wrap--overflow_hidden">
									<colgroup>
										<col style="width: 140px" />
										<col style="width: 290px" />
										<col style="width: 66px" />
										<col style="width: 540px" />
									</colgroup>
									<tbody>
										<tr v-for="(row, index) in this.disp.tableList" v-bind:key="index">
											<td class="border--top_none">{{ row.fileNm }}</td>
											<td class="border--top_none">{{ row.torikmReslt }}</td>
											<td class="border--top_none">{{ row.gyoNo }}</td>
											<td class="border--top_none">{{ row.errContents }}</td>
										</tr>
									</tbody>
								</table>
							</div>
						</section>
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
								v-bind:name="'returnBtn'"
								v-bind:style="{ 'margin-right': '30px' }"
								v-bind:tabindex="'12'"
								v-bind:value="'戻る'"
								v-on:click="clickReturnBtn()"
							/>
							<button-input
								class="contents__btn-blue"
								v-bind:name="'cancelBtn'"
								v-bind:tabindex="'13'"
								v-bind:value="'取消'"
								v-on:click="clickCancelBtn()"
							/>
						</div>
					</div>
					<div class="center-block"></div>
					<div class="right-block">
						<div class="button-group">
							<button-input
								class="contents__btn-blue"
								v-bind:name="'execBtn'"
								v-bind:tabindex="'11'"
								v-bind:value="'実行'"
								v-on:click="clickExecBtn()"
							/>
						</div>
					</div>
				</div>
			</div>
		</footer>
	</div>
</template>

<script>
import ButtonInput from '@/components/ButtonInput.vue'
import DateInput from '@/components/DateInput'
import RadioButton from '@/components/RadioButton.vue'
import FileUpload from '@/components/FileUpload.vue'
import ErrorMessage from '@/components/ErrorMessage.vue'

import { co_postFile, co_downloadFile, co_setError } from '@/common/ajaxUtility.js'
import { STORE_ERROR_CLEAR, STORE_ERROR_ADDMSG, STORE_VIEW_PROGRESS, STORE_ERROR_IS_ERRFLG } from '@/const/store.js'

import { HTTP_STATUS } from '@/const/const.js'

import { co_getMessage } from '@/common/messageUtility.js'
import { co_isEmpty, co_checkHalfNumber } from '@/common/checkUtility.js'
import { PATH_PSKC020_OUTPUT_KKNSITEI, PATH_PSKC020_OUTPUT_WEEKSITEI, PATH_PSKC020_IMPORT_PLANSU, PATH_PSKC020_IMPORT_SHINKAIHAI } from '@/const/route.js'

export default {
	components: {
		'button-input': ButtonInput,
		'date-input': DateInput,
		'radio-button': RadioButton,
		'file-upload': FileUpload,
		'error-message': ErrorMessage
	},
	data: function () {
		return {
			form: {
				procsKbn: '', // 処理区分
				delvDayFrom: '', // 納品日Ｆｒｏｍ
				delvDayTo: '', // 納品日Ｔｏ
				csvFile: null // 取込CSVファイル
			},
			disp: {
				// 処理区分
				radioButtonListItems: [
					{ code: '1', name: '出力(期間指定)' },
					{ code: '2', name: '出力(週指定)' },
					{ code: '3', name: '取込(計画数)' },
					{ code: '4', name: '取込(商品改廃)' }
				],
				// アップロード許可拡張子リスト
				fileExtensionList: ['csv'],
				// 一覧リスト
				tableData: []
			},
			showFlag: {
				// 一覧表示フラグ
				tableShowFlag: false
			},
			disabledFlg: {
				fileDisabled: true,
				delvDayToDisabled: false
			},
			outPath: '',
			importPath: '',
		}
	},
	created: function () {
		// 処理区分-出力(期間指定)を選択する
		this.form.procsKbn = '1'
		//  プロテクト項目
		this.disabledFlg.delvDayToDisabled = false
		this.disabledFlg.fileDisabled = true
	},
	mounted: function () {
		this.$$nextTick(function () {
			this.$refs.procsKbn.focus()
		})
	},
	computed: {
		// 一覧リストのカウントを計算する
		tableListCount: function () {
			const tableListCount = this.disp.tableList?.length ?? 0
			// コンマ編集
			const reg = /(\d)(?=(\d{3})+$)/g
			return String(tableListCount).replace(reg, '$1,')
		}
	},
	methods: {
		// 処理区分変更
		changeProcsKbn: async function () {
			await this.$nextTick()
			switch (this.form.procsKbn) {
				case '1':
					this.disabledFlg.delvDayToDisabled = false
					this.disabledFlg.fileDisabled = true
					break
				case '2':
					this.disabledFlg.delvDayToDisabled = true
					this.disabledFlg.fileDisabled = true
					break
				case '3':
					this.disabledFlg.delvDayToDisabled = false
					this.disabledFlg.fileDisabled = false
					break
				case '4':
					this.disabledFlg.delvDayToDisabled = true
					this.disabledFlg.fileDisabled = false
					break
			}
			// エラーメッセージをクリアする
			this.$store.commit(STORE_ERROR_CLEAR)
			
			// フォームのデータをクリアする。
			this.form.delvDayFrom = ''
			this.form.delvDayTo = ''
			this.form.csvFile = null

			this.$refs.torikmMoto.clearSelectedFile()
			// 一覧表示フラグをクリアする。
			this.showFlag.tableShowFlag = false
			this.tableData = []
		},
		// 戻るボタンクリック
		clickReturnBtn: function () {
			// エラーメッセージをクリアする
			this.$store.commit(STORE_ERROR_CLEAR)
			// メニュー画面へ遷移する
			this.$router.back()
		},
		// 取消ボタンクリック
		clickCancelBtn: async function () {
			// エラーメッセージをクリアする
			this.$store.commit(STORE_ERROR_CLEAR)

			// フォームのデータをクリアする。
			this.form.procsKbn = '1'
			this.form.delvDayFrom = ''
			this.form.delvDayTo = ''
			this.form.csvFile = null

			this.$refs.torikmMoto.clearSelectedFile()
			
			// 処理区分変更
			this.disabledFlg.delvDayToDisabled = false
			this.disabledFlg.fileDisabled = true

			// 一覧表示フラグをクリアする。
			this.showFlag.tableShowFlag = false
			this.tableData = []
			await this.$nextTick()
			this.$refs.procsKbn.focus()
		},
		// 実行ボタン
		clickExecBtn: function () {
			// エラーメッセージをクリアする
			this.$store.commit(STORE_ERROR_CLEAR)
			this.$refs.delvDayTo.isError = false
			this.$refs.delvDayFrom.isError = false
			this.$refs.torikmMoto.isError = false
			// チェック
			this.$refs.procsKbn.validate()
			this.$refs.delvDayFrom.validate()

			// 出力（週指定）の場合＞また＜取込（商品改廃）の場合
			if (this.form.procsKbn == '2' || this.form.procsKbn == '4') {
				if (this.isMonday(this.form.delvDayFrom)) {
					this.$store.commit(STORE_ERROR_ADDMSG, co_getMessage('SKC0010E', ['納品日（自）', '月曜日']))
					this.$refs.delvDayFrom.isError = true
				}
			}
			// 出力（期間指定）の場合＞また＜取込（計画数）の場合
			if (this.form.procsKbn == '1' || this.form.procsKbn == '3') {
				this.$refs.delvDayTo.validate()
				if (this.form.delvDayFrom > this.form.delvDayTo) {
					this.$store.commit(STORE_ERROR_ADDMSG, co_getMessage('SKC0003E', '納品日（至）'))
					this.$refs.delvDayTo.isError = true
				}
			}

			// 取り込んだCSVファイルを取得する
			this.form.csvFile = this.$refs.torikmMoto.getFile()

			// 取込（商品改廃）の場合＞また＜取込（計画数）の場合
			if (this.form.procsKbn == '4' || this.form.procsKbn == '3') {
				if (this.form.csvFile == null) {
					this.$store.commit(STORE_ERROR_ADDMSG, co_getMessage('CMS0014E', 'ファイル'))
					this.$refs.torikmMoto.isError = true
				}
			}

			// ** エラー表示制御 **
			if (this.$store.getters[STORE_ERROR_IS_ERRFLG]) {
				this.$store.commit(STORE_VIEW_PROGRESS, false)
				return
			}
			// プログレスバー表示
			this.$store.commit(STORE_VIEW_PROGRESS, true)
			// エラーメッセージをクリアする
			this.$store.commit(STORE_ERROR_CLEAR)

			// 画面.一覧部をクリアする。
			this.showFlag.tableShowFlag = false
			this.disp.tableList = []

			switch (this.form.procsKbn) {
				case '1':
					this.outPath = PATH_PSKC020_OUTPUT_KKNSITEI
					this.outPut()
					break
				case '2':
					this.outPath = PATH_PSKC020_OUTPUT_WEEKSITEI
					this.outPut()
					break
				case '3':
					this.importPath = PATH_PSKC020_IMPORT_PLANSU
					this.importData()
					break
				case '4':
					this.importPath = PATH_PSKC020_IMPORT_SHINKAIHAI
					this.importData()
					break
			}
		},
		// 製造計画数取込・出力の出力処理
		outPut: function () {
			const self = this
			co_downloadFile(this.outPath, this.form, function (res, downloadHandle) {
				// 画面表示にプログレスバーの非表示を設定する。
				self.$store.commit(STORE_VIEW_PROGRESS, false)

				if (res.status != HTTP_STATUS.OK) {
					// 一覧表示フラグ
					self.showFlag.tableShowFlag = false
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
		// 製造計画数取込・出力の取込処理
		importData: function () {
			const self = this
			co_postFile(this.importPath, this.form, function (res) {
				// 画面表示にプログレスバーの非表示を設定する。
				self.$store.commit(STORE_VIEW_PROGRESS, false)

				if (res.status != HTTP_STATUS.OK) {
					// 一覧表示フラグ
					self.showFlag.tableShowFlag = false
					// 異常終了
					co_setError(res.data, self.$refs)
					if (!co_isEmpty(self.$refs[res.data[0].errorItems[0]])) {
						self.$refs[res.data[0].errorItems[0]].focus()
					}
					return
				}
				// 一覧表示フラグ
				self.showFlag.tableShowFlag = true

				// 一覧リストを設定する
				self.disp.tableList = res.data
			})
		},
		// 時間が月曜日かどうか判断する
		isMonday: function (strDate) {
			if (strDate.length != 8 && !co_checkHalfNumber(strDate)) {
				return true
			}
			let y = strDate.substring(0, 4)
			let m = strDate.substring(4, 6)
			let d = strDate.substring(6)
			let formatData = new Date()
			formatData.setFullYear(y, m - 1, d)
			if (formatData.getDay() != 1) {
				return true
			} else {
				return false
			}
		}
	}
}
</script>

<style scoped>
.width-1800 {
	width: 1800px;
}

.fontsize-14px {
	font-size: 14px;
}

.file-box {
	background-color: #eeffff;
	padding: 0px 0px;
	appearance: none;
}

.tbl-sticky-head {
	position: -webkit-sticky;
	position: sticky;
	top: 0;
	z-index: 1;
}

.tbl-list__wrap--small {
	width: 63%;
	margin: 0 1%;
}
.margin-r-790px {
	margin-right: 790px;
}

.tbl-list--border-all_exist {
	overflow: hidden;
}

.height-40px {
	height: 40px;
}

tr.border-b {
	border-bottom: 2px solid #dedfe8;
}

.tbl-item--upload td:nth-of-type(1) {
	width: 250px;
}

.tbl-item--upload td:nth-of-type(2) {
	width: 1550px;
}

.border--top_none {
	border-top: none !important;
}

.padding-left-6 {
	padding-left: 6px;
}

.tbl-list__wrap--small {
	width: 63%;
	margin: 0 1%;
}

.ellipsis {
	width: 140px;
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
}
</style>
