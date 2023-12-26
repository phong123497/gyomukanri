<!--
//================================================================================
// ID: PSKC010
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// VueName: Pskc010
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/10 | ＡＭＣ               | 新規作成
//================================================================================
 -->
<template>
	<div>
		<main id="PSKC010">
			<div class="contents__block--exist_return">
				<h1 class="header__block-title">値付実績データ取込</h1>

				<!-- エラーメッセージ -->
				<error-message />

				<div class="contents__wrap--scroll_vertical">
					<div class="contents__view--bg_gray">
						<table class="tbl-item--upload border-inline">
							<colgroup>
								<col style="width:1550px">
							</colgroup>
							<tr>
								<td>
									取込元
								</td>
								<td>
									<file-upload
										ref="torikmMoto"
										v-bind:clickUploadOrSaveBtn="clickFileSentakuBtn"
										v-bind:fileExtensions="disp.fileExtensionList"
										v-bind:fileSize="2"
										v-bind:name="'取込元'"
										v-bind:requireFlg="true"
										v-bind:tabindex="'1'"
									/>
								</td>
							</tr>
						</table>
						
						<section v-if="tableShowFlag">
							<div class="contents__block-top">
								<div class="contents__block-top__area--float_l">
									<h2 class="contents__h2">取込結果</h2>
								</div>
								<div class="contents__block-top__area--float_r" style="padding-top:10px">
									<span style="margin-right: 785px;">{{tableListCount}}件</span>
								</div>
							</div>
							<div class="tbl-list__wrap--small">
								<table class="tbl-list--border-all_exist tbl-sticky-head wrap--overflow_hidden">
									<colgroup>
										<col style="width:140px">
										<col style="width:290px">
										<col style="width:66px">
										<col style="width:540px">
									</colgroup>
									<thead>
										<tr>
											<th>
												ファイル名
											</th>
											<th>
												取込結果
											</th>
											<th>
												行番号
											</th>
											<th>
												エラー内容
											</th>
										</tr>
									</thead>
								</table>
								<table class="tbl-list--border-all_exist wrap--overflow_hidden">
									<colgroup>
										<col style="width:140px">
										<col style="width:290px">
										<col style="width:66px">
										<col style="width:540px">
									</colgroup>
									<tbody>
										<tr v-for="(row, index) in this.disp.tableList" v-bind:key="index">
											<td class="border--top_none padding-left-6">{{row.fileNm}}</td>
											<td class="border--top_none padding-left-6">{{row.torikmReslt}}</td>
											<td class="border--top_none padding-left-6">{{row.gyoNo}}</td>
											<td class="border--top_none padding-left-6">{{row.errContents}}</td>
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
				<div class="footer-button__block btn-display-change">
					<div class="left-block right-adjustment">
						<div class="button-group">
							<button-input
								class="contents__btn-blue margin-right-30"
								v-bind:name="'backButton'"
								v-bind:tabindex="'5'"
								v-bind:value="'戻る'"
								v-on:click="clickReturnBtn"
							/>
							<button-input
								class="contents__btn-blue"
								v-bind:name="'cancelButton'"
								v-bind:tabindex="'6'"
								v-bind:value="'取消'"
								v-on:click="clickCancelBtn"
							/>
						</div>
					</div>
					<div class="center-block">
					</div>
					<div class="right-block">
						<div class="button-group">
							<button-input
								class="contents__btn-blue"
								v-bind:name="'torikmButton'"
								v-bind:tabindex="'4'"
								v-bind:value="'取込'"
								v-on:click="clickTorikmBtn"
							/>
						</div>
					</div>
				</div>
			</div>
		</footer>
	</div>
</template>

<script>
import ButtonInput from '@/components/ButtonInput.vue';
import FileUpload from '@/components/FileUpload.vue';
import ErrorMessage from '@/components/ErrorMessage.vue';

import { co_postFile, co_setError } from '@/common/ajaxUtility.js';
import { co_isEmpty } from '@/common/checkUtility.js';

import {
	HTTP_STATUS,
} from '@/const/const.js';

import {
	PATH_PSKC010_READCSV
} from "@/const/route.js";

import {
	STORE_INFO_CLEAR,
	STORE_ERROR_CLEAR,
	STORE_ERROR_IS_ERRFLG,
	STORE_VIEW_PROGRESS,
} from '@/const/store.js';

export default {
	components: {
		'button-input': ButtonInput,
		'file-upload': FileUpload,
		'error-message': ErrorMessage,
	},
	data: function() {
		return {
			form: {
				// CSVファイル
				csvFile: null,
			},
			disp: {
				// 一覧リスト
				tableList: [],
				// アップロード許可拡張子リスト
				fileExtensionList: ["csv"],
			},
			// 一覧部表示フラグ
			tableShowFlag: false,
		}
	},
	mounted: async function() {
		// フォーカス設定
		await this.$nextTick();
		this.$refs.torikmMoto.focus();
	},
	computed: {
		// 一覧リストのカウントを計算する
		tableListCount: function() {
			const tableListCount = this.disp.tableList?.length ?? 0;
			// コンマ編集
			const reg = /(\d)(?=(\d{3})+$)/g;
			return String(tableListCount).replace(reg, "$1,");
		}
	},
	methods: {
		// ファイルを選択ボタンクリック
		clickFileSentakuBtn: function() {
			this.form.csvFile = this.$refs.torikmMoto.getFile();

		},
		// 戻るボタンクリック
		clickReturnBtn: function() {
			this.$store.commit(STORE_INFO_CLEAR);
			this.$router.back();
		},
		// 取消ボタンクリック
		clickCancelBtn: async function() {
			// 一覧部をクリアします
			this.tableShowFlag = false;

			// エラーメッセージをクリアする
			this.$store.commit(STORE_ERROR_CLEAR);

			// 選択済みのファイルをクリアする
			this.$refs.torikmMoto.clearSelectedFile();

			// フォーカス設定
			await this.$nextTick();
			this.$refs.torikmMoto.focus();
		},
		// 取込ボタンクリック
		clickTorikmBtn: function() {

			// エラーメッセージをクリアする
			this.$store.commit(STORE_ERROR_CLEAR);
			
			// プログレスバー表示
			this.$store.commit(STORE_VIEW_PROGRESS, true);

			// ** チェック処理 **
			this.$refs.torikmMoto.validate();
			
			// ** エラー表示制御 **
			if (this.$store.getters[STORE_ERROR_IS_ERRFLG]) {
				this.$store.commit(STORE_VIEW_PROGRESS, false);
				return;
			}

			this.disp.tableList = [];
			this.form.csvFile = this.$refs.torikmMoto.getFile();
			
			const self = this;
			co_postFile(PATH_PSKC010_READCSV, this.form, function(res) {
				if (res.status == HTTP_STATUS.OK) {
					self.disp.tableList = res.data;
					
					// 一覧部表示
					self.tableShowFlag = true;
					// プログレスバー非表示
					self.$store.commit(STORE_VIEW_PROGRESS, false);
				} else {
					// 一覧部非表示
					self.tableShowFlag = false;
					// プログレスバー非表示
					self.$store.commit(STORE_VIEW_PROGRESS, false);

					co_setError(res.data, self.$refs);
					if (!co_isEmpty(self.$refs[res.data[0].errorItems[0]])) {
						self.$refs[res.data[0].errorItems[0]].focus();
					}
				}
			})
		}
	}
}
</script>

<style scoped>
	.tbl-item--upload td:nth-of-type(1) {
		width: 8%;
	}
	.tbl-sticky-head {
		position: -webkit-sticky;
		position: sticky;
		top: 0;
		z-index: 1
	}
</style>