<!--
//================================================================================
// ID: PNKS140
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// VueName: Pnks140
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/06 | ＡＭＣ               | 新規作成
//================================================================================
 -->
<template>
	<div class="view-area">
		<main id="PNKS140">
			<div class="contents__block--exist_return">
				<h1 class="header__block-title">
					<table>
						<colgroup>
							<col style="width: 460px" />
							<col style="width: 1330px" />
						</colgroup>
						<tbody>
							<tr>
								<td>
									メーカーマスタメンテナンス
									<list-box
										class="width-150 mgn-l-25px"
										ref="procsShubt"
										v-model="pageData.procsShubt"
										v-bind:cdContentType="CD_CONTENT_TYPE.CODENAME"
										v-bind:disabledFlg="disabledFlg.shubt"
										v-bind:listItems="disp.shoriSbtList"
										v-bind:name="'処理種別'"
									></list-box>
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
					<div class="contents__view--bg_white">
						<div class="contents-wrap--bg-w--pad-3per">
							<section>
								<table class="tbl-item--border-row_exist">
									<tbody>
										<tr>
											<td>
												<span class="contents__block-search__label">メーカーコード</span>
											</td>
											<td>
												<code-input
													class="width-100 txt--align_l"
													ref="makerCd"
													v-model="form.makerCd"
													v-bind:activeZeroPadding="true"
													v-bind:disabledFlg="disabledFlg.makerCd"
													v-bind:maxLength="4"
													v-bind:name="'メーカーコード'"
													v-bind:requireFlg="true"
													v-bind:tabindex="'1'"
												></code-input>
											</td>
										</tr>
										<tr>
											<td>
												<span class="contents__block-search__label">メーカー名称</span>
											</td>
											<td>
												<text-input
													class="txt--align_l width-385"
													ref="makerNm"
													v-model="form.makerNm"
													v-bind:disabledFlg="disabledFlg.makerNm"
													v-bind:maxLength="30"
													v-bind:name="'メーカー名称'"
													v-bind:requireFlg="true"
													v-bind:tabindex="'2'"
													v-bind:textType="TEXTINPUT_TYPE.FULL"
												></text-input>
											</td>
										</tr>
										<tr>
											<td>
												<span class="contents__block-search__label">メーカー略称</span>
											</td>
											<td>
												<text-input
													class="txt--align_l width-300"
													ref="makerRyakusho"
													v-model="form.makerRyakusho"
													v-bind:disabledFlg="disabledFlg.makerRyakusho"
													v-bind:maxLength="10"
													v-bind:name="'メーカー略称'"
													v-bind:requireFlg="true"
													v-bind:tabindex="'3'"
													v-bind:textType="TEXTINPUT_TYPE.FULL"
												></text-input>
											</td>
										</tr>
										<tr>
											<td>
												<span class="contents__block-search__label">TEL</span>
											</td>
											<td>
												<phone-number-input
													class="txt--align_l width-220"
													ref="telNo"
													v-model="form.telNo"
													v-bind:disabledFlg="disabledFlg.telNo"
													v-bind:maxLength="15"
													v-bind:name="'TEL'"
													v-bind:tabindex="'4'"
													v-bind:textType="TEXTINPUT_TYPE.HALF"
												></phone-number-input>
											</td>
										</tr>
										<tr>
											<td>
												<span class="contents__block-search__label">FAX</span>
											</td>
											<td>
												<phone-number-input
													class="txt--align_l width-220"
													ref="faxNo"
													v-model="form.faxNo"
													v-bind:disabledFlg="disabledFlg.faxNo"
													v-bind:maxLength="15"
													v-bind:name="'FAX'"
													v-bind:tabindex="'5'"
													v-bind:textType="TEXTINPUT_TYPE.HALF"
												></phone-number-input>
											</td>
										</tr>
										<tr>
											<td>
												<span class="contents__block-search__label">納品時間</span>
											</td>
											<td>
												<text-input
													class="txt--align_l width-100"
													ref="delvTime"
													v-model="disp.delvTime"
													v-bind:disabledFlg="disabledFlg.delvTime"
													v-bind:maxLength="4"
													v-bind:name="'納品時間'"
													v-bind:requireFlg="true"
													v-bind:tabindex="'6'"
													v-bind:textType="TEXTINPUT_TYPE.HALF"
													v-on:blur.stop="delvTimeFormat(disp.delvTime)"
												></text-input>
											</td>
										</tr>
										<tr>
											<td>
												<span class="contents__block-search__label">パスワード</span>
											</td>
											<td>
												<text-input
													class="txt--align_l width-250"
													ref="password"
													v-model="form.password"
													v-bind:disabledFlg="disabledFlg.password"
													v-bind:maxLength="15"
													v-bind:name="'パスワード'"
													v-bind:requireFlg="true"
													v-bind:tabindex="'7'"
													v-bind:textType="TEXTINPUT_TYPE.HALF"
												></text-input>
											</td>
										</tr>
										<tr>
											<td>
												<span class="contents__block-search__label">取引先コード</span>
											</td>
											<td colspan="2">
												<code-input
													class="width-100"
													ref="toriskCd"
													v-model="form.toriskCd"
													v-bind:activeZeroPadding="true"
													v-bind:disabledFlg="disabledFlg.toriskCd"
													v-bind:maxLength="6"
													v-bind:name="'取引先コード'"
													v-bind:tabindex="'8'"
												></code-input>
											</td>
										</tr>
									</tbody>
								</table>
							</section>
						</div>
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
								v-bind:tabindex="'10'"
								v-bind:value="'戻る'"
								v-on:click="clickReturnBtn()"
							></button-input>
							<button-input
								class="contents__btn-blue"
								v-bind:disabledFlg="disabledFlg.cancelBtn"
								v-bind:tabindex="'11'"
								v-bind:value="'取消'"
								v-on:click="clickCancelBtn()"
							></button-input>
						</div>
					</div>
					<div class="center-block"></div>
					<div class="right-block">
						<div class="button-group" style="float: right">
							<button-input
								class="contents__btn-blue"
								v-bind:disabledFlg="disabledFlg.saveBtn"
								v-bind:tabindex="'9'"
								v-bind:value="'保存'"
								v-on:click="clickSaveBtn()"
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
import ErrorMessage from '@/components/ErrorMessage.vue'
import InfoMessage from '@/components/InfoMessage'
import ListBox from '@/components/ListBox'
import PhoneNumberInput from '@/components/PhoneNumberInput'
import TextInput from '@/components/TextInput'

import { co_post, co_put, co_setError } from '@/common/ajaxUtility.js'
import { co_isEmpty, co_checkHalfNumber } from '@/common/checkUtility.js'
import { co_confirm } from '@/common/dialogUtility.js'
import { co_getMessage } from '@/common/messageUtility.js'

import { co_backView } from "@/common/viewUtility.js";

import { 
	CD_CONTENT_TYPE,
	CNS_SHORISBT, 
	DIALOG_BUTTON_LIST_KEY, 
	HTTP_STATUS, 
	TEXTINPUT_TYPE 
} from '@/const/const.js'

import { 
	PATH_PNKS140, 
	PATH_PNKS140_SAVE, 
	PATH_PNKS140_UPDATE 
} from '@/const/route.js'

import {
	STORE_ERROR_ADDMSG,
	STORE_ERROR_CLEAR,
	STORE_ERROR_IS_ERRFLG,
	STORE_VIEW_CHANGED,
	STORE_VIEW_HAS_CHANGED,
	STORE_VIEW_PROGRESS,
	STORE_INFO_CLEAR,
	STORE_INFO_SET_MSG,
	STORE_VIEW_GET_PARAMS
} from '@/const/store.js'

export default {
	components: {
		'button-input': ButtonInput,
		'code-input': CodeInput,
		'error-message': ErrorMessage,
		'info-message': InfoMessage,
		'list-box': ListBox,
		'text-input': TextInput,
		'phone-number-input': PhoneNumberInput
	},
	data: function () {
		return {
			form: {
				makerCd: '', 			// メーカーコード
				makerNm: '', 			// メーカー名称
				makerRyakusho: '', 		// メーカー略称
				telNo: '', 				// TEL
				faxNo: '', 				// FAX
				delvTime: '', 			// 納品時間
				password: '', 			// パスワード
				toriskCd: '', 			// 取引先コード
				updDatm: '' 			// 更新日時
			},
			pageData: {
				// 処理種別
				procsShubt: '',
				// メーカーコード
				makerCd: ''
			},
			disp: {
				// 処理種別リスト
				shoriSbtList: [
					{ code: CNS_SHORISBT.CNS_SHORISBT_ADD, name: CNS_SHORISBT.CNS_SHORISBT_ADD_NAME },
					{ code: CNS_SHORISBT.CNS_SHORISBT_UPD, name: CNS_SHORISBT.CNS_SHORISBT_UPD_NAME }
				],
				// 納品時間表示值
				delvTime: ''
			},
			// 画面項目活性・非活性フラグ
			disabledFlg: {
				// 処理種別制御
				shubt: true,
				makerCd: false,
				// エラー発生時の他のプロジェクト制御
				makerNm: false,
				makerRyakusho: false,
				telNo: false,
				faxNo: false,
				delvTime: false,
				password: false,
				toriskCd: false,
				cancelBtn: false,
				saveBtn: false
			},
			CD_CONTENT_TYPE: CD_CONTENT_TYPE,
			TEXTINPUT_TYPE: TEXTINPUT_TYPE
		}
	},
	created: function () {
		// 前のページから渡されたデータを取得する
		let viewData = this.$store.getters[STORE_VIEW_GET_PARAMS]("PNKS140")

		this.pageData.procsShubt = viewData.procsShubt
		this.pageData.makerCd = viewData.makerCd

		// 種類が空の場合、他の項目は使用できません
		if (!this.pageData.procsShubt) {
			this.setDisableFlg()
			return
		}

		// 遷移パラメータ.種別 = 変更の場合
		if (this.pageData.procsShubt == CNS_SHORISBT.CNS_SHORISBT_UPD) {
			// プログレスバー表示
			this.$store.commit(STORE_VIEW_PROGRESS, true)

			// メーカーコードの設定
			this.form.makerCd = this.pageData.makerCd
			this.disabledFlg.makerCd = true
			this.createFunction()
		}

		// 編集状態をクリア
		this.$nextTick(function () {
			this.$store.commit(STORE_VIEW_CHANGED, false)
		})
	},
	mounted: function () {
		this.$nextTick(function () {
			// フォーカス設定
			if (this.pageData.procsShubt == CNS_SHORISBT.CNS_SHORISBT_ADD) {
				this.$refs.makerCd.focus()
			} else {
				this.$refs.makerNm.focus()
			}
		})
	},
	watch: {
		// formオブジェクト内のすべての変更を監視する
		form: {
			handler: function () {
				this.$store.commit(STORE_VIEW_CHANGED, true)
			},
			deep: true
		}
	},
	methods: {
		// 初期表示データ取得
		createFunction: function () {
			let self = this
			co_post(PATH_PNKS140, this.form, function (res) {
				if (res.status == HTTP_STATUS.OK) {
					// プログレスバー非表示
					self.$store.commit(STORE_VIEW_PROGRESS, false)

					self.form = res.data

					// delvtimeフォーマット
					self.delvTimeFormat(self.form.delvTime)

					// 編集状態をクリア
					self.$nextTick(function () {
						self.$store.commit(STORE_VIEW_CHANGED, false)
					})

				} else {
					// プログレスバー非表示
					self.$store.commit(STORE_VIEW_PROGRESS, false)
					
					// 編集不可にする
					self.setDisableFlg()
					// 業務エラー表示
					co_setError(res.data, self.$refs)
					if (!co_isEmpty(self.$refs[res.data[0].errorItems[0]])) {
						self.$nextTick(function () {
							self.$refs[res.data[0].errorItems[0]].focus()
						})
					}

				}
			})

			// 編集状態をクリア
			this.$nextTick(function () {
				this.$store.commit(STORE_VIEW_CHANGED, false)
			})
		},
		// 戻るボタンクリック
		clickReturnBtn: function () {
			if (this.$store.getters[STORE_VIEW_HAS_CHANGED]) {
				// 確認ダイアログを表示
				co_confirm(co_getMessage('CMS0003W'), function (btnValue) {
					if (btnValue == DIALOG_BUTTON_LIST_KEY.OK) {
						co_backView()
					}
				})
			} else {
				co_backView()
			}
		},
		// 取消ボタンクリック
		clickCancelBtn: function () {
			if (!this.$store.getters[STORE_VIEW_HAS_CHANGED]) {
				// 状態管理(ストア)のエラー情報をクリアする。
				this.$store.commit(STORE_ERROR_CLEAR)
				// MSG クリア
				this.$store.commit(STORE_INFO_CLEAR)
				return
			}
			// 確認ダイアログを表示
			let self = this
			co_confirm(co_getMessage('CMS0003W'), function (btnValue) {
				if (btnValue == DIALOG_BUTTON_LIST_KEY.OK) {
					// 状態管理(ストア)のエラー情報をクリアする。
					self.$store.commit(STORE_ERROR_CLEAR)
					// MSG クリア
					self.$store.commit(STORE_INFO_CLEAR)

					if (self.pageData.procsShubt == CNS_SHORISBT.CNS_SHORISBT_ADD) {
						// 入力データをクリア
						self.dataClear()

						self.$nextTick(function () {
							// フォーカス設定
							self.$refs.makerCd.focus()

							// 編集状態をクリア
							self.$store.commit(STORE_VIEW_CHANGED, false)
						})
					} else {
						// メーカーコードの設定
						self.form.makerCd = self.pageData.makerCd
						// 画面内容の初期化
						self.createFunction()

						self.$nextTick(function () {
							// フォーカス設定
							self.$refs.makerNm.focus()

							// 編集状態をクリア
							self.$store.commit(STORE_VIEW_CHANGED, false)
						})
					}
				}
			})
		},
		// 保存ボタンクリック
		clickSaveBtn: function () {
			// 状態管理(ストア)のエラー情報をクリアする。
			this.$store.commit(STORE_ERROR_CLEAR)
			// 納品時間のエラー情報をクリアする。
			this.$refs.delvTime.errorFlg = false
			// MSG クリア
			this.$store.commit(STORE_INFO_CLEAR)
			// プログレスバー表示
			this.$store.commit(STORE_VIEW_PROGRESS, true)

			// 入力チェック
			this.$refs.makerCd.validate()
			this.$refs.makerNm.validate()
			this.$refs.makerRyakusho.validate()
			this.$refs.telNo.validate()
			this.$refs.faxNo.validate()

			// 納品時間のチェック
			if (co_isEmpty(this.form.delvTime) && this.$refs.delvTime.requireFlg) {
				// 必須チェック
				this.$store.commit(STORE_ERROR_ADDMSG, co_getMessage('CMS0013E', '納品時間'))
				this.$refs.delvTime.errorFlg = true
			} else if (this.form.delvTime.length != this.$refs.delvTime.maxLength) {
				// 桁数検査
				this.$store.commit(STORE_ERROR_ADDMSG, co_getMessage('CMS0016E', '納品時間', String(this.$refs.delvTime.maxLength)))
				this.$refs.delvTime.errorFlg = true
			} else if (!co_checkHalfNumber(this.form.delvTime)) {
				// 半角数字チェック
				this.$store.commit(STORE_ERROR_ADDMSG, co_getMessage('CMS0010E', '納品時間'))
				this.$refs.delvTime.errorFlg = true
			}

			this.$refs.password.validate()
			this.$refs.toriskCd.validate()


			// ** エラー表示制御 **
			if (this.$store.getters[STORE_ERROR_IS_ERRFLG]) {
				this.$store.commit(STORE_VIEW_PROGRESS, false)
				return
			}

			// 保存処理
			if (this.pageData.procsShubt == CNS_SHORISBT.CNS_SHORISBT_ADD) {
				this.saveFunction()
			} else {
				this.updateFunction()
			}
		},
		// データ処理の追加
		saveFunction: function () {
			let self = this
			co_post(PATH_PNKS140_SAVE, this.form, function (res) {
				if (res.status == HTTP_STATUS.CREATED) {
					// 入力データをクリア
					self.dataClear()
					self.$nextTick(function () {
						// フォーカス設定
						self.$refs.makerCd.focus()

						// メッセージ設定&表示
						self.$store.commit(STORE_INFO_SET_MSG, co_getMessage('CMS0001I'))

						// 編集クリア
						self.$store.commit(STORE_VIEW_PROGRESS, false)
						self.$store.commit(STORE_VIEW_CHANGED, false)
					})
				} else {
					// プログレスバー非表示
					self.$store.commit(STORE_VIEW_PROGRESS, false)
					// 業務エラー表示
					co_setError(res.data, self.$refs)
					if (!co_isEmpty(self.$refs[res.data[0].errorItems[0]])) {
						self.$nextTick(function () {
							self.$refs[res.data[0].errorItems[0]].focus()
						})
					}

				}
			})
		},
		// データ処理の更新
		updateFunction: function () {
			let self = this
			co_put(PATH_PNKS140_UPDATE, this.form, function (res) {
				if (res.status == HTTP_STATUS.CREATED) {
					// 保存が完了したら再取得
					self.createFunction()
					self.$nextTick(function () {
						// フォーカス設定
						self.$refs.makerNm.focus()

						// メッセージ設定&表示
						self.$store.commit(STORE_INFO_SET_MSG, co_getMessage('CMS0001I'))

						// 編集クリア
						self.$store.commit(STORE_VIEW_PROGRESS, false)
						self.$store.commit(STORE_VIEW_CHANGED, false)
					})
				} else {
					// プログレスバー非表示
					self.$store.commit(STORE_VIEW_PROGRESS, false)
					// 業務エラー表示
					co_setError(res.data, self.$refs)
					if (!co_isEmpty(self.$refs[res.data[0].errorItems[0]])) {
						self.$nextTick(function () {
							self.$refs[res.data[0].errorItems[0]].focus()
						})
					}
				}
			})
		},
		// delvTimeを00:00フォーマットにフォーマットします
		delvTimeFormat: function (val) {
			// 入力データの「：」を削除
			if (val.indexOf(':') != -1) {
				val = val.replace(/:/g, '')
			}

			// delvTimeをフォーマットし、表示変数disp.delvTimeに追加
			switch (val.length) {
				case 1:
					this.disp.delvTime = '00:0' + val
					break
				case 2:
					this.disp.delvTime = '00:' + val
					break
				case 3:
					this.disp.delvTime = '0' + val.substring(0, 1) + ':' + val.substring(1, 3)
					break
				case 4:
					this.disp.delvTime = val.substring(0, 2) + ':' + val.substring(2, 4)
					break
			}

			this.form.delvTime = this.disp.delvTime.replace(/:/g, '')
		},
		// formデータクリア
		dataClear: function () {
			this.form.makerCd = ''
			this.form.makerNm = ''
			this.form.makerRyakusho = ''
			this.form.telNo = ''
			this.form.faxNo = ''
			this.form.delvTime = ''
			this.disp.delvTime = ''
			this.form.password = ''
			this.form.toriskCd = ''
			this.form.updDatm = ''
		},
		// 画面項目制御
		setDisableFlg: function () {
			this.disabledFlg.makerNm = true
			this.disabledFlg.makerRyakusho = true
			this.disabledFlg.telNo = true
			this.disabledFlg.faxNo = true
			this.disabledFlg.delvTime = true
			this.disabledFlg.password = true
			this.disabledFlg.toriskCd = true
			this.disabledFlg.cancelBtn = true
			this.disabledFlg.saveBtn = true
		}
	}
}
</script>

<style scoped>
table.tbl-list--border-row_not-exist.wrap--overflow_hidden {
	margin-left: 15px;
}

.tbl-item--border-row_exist .contents--error-item{
  border    : 2px solid #dc8585;
}

.width-385{
	width:385px;
}
</style>
