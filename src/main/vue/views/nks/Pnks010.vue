<!--
//================================================================================
// ID: PNKS010
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// VueName: Pnks010
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/10 | ＡＭＣ               | 新規作成
//================================================================================
 -->
<template>
	<div>
		<!-- メインコンテンツ -->
		<main id="PNKS010">
			<div class="contents__block--exist_return">
				
				<!-- タイトル -->
				<h1 class="header__block-title">PEACEデータ取込</h1>
				
				<!-- エラーメッセージ -->
				<error-message />
				
				<div class="contents__wrap--scroll_vertical">
					<div class="contents__view--bg_gray">
						<table class="tbl-item--upload border-inline">
							<colgroup>
								<col style="width:250px">
								<col style="width:1550px">
							</colgroup>
							<tr class = "border-b">
								<td>
									取込対象
								</td>
								<td>
									<div class="contents__block-radio">
										<radio-button
											ref="torikmTargetKbn"
											v-bind:listItems="disp.radioButtonListItems"
											v-bind:name="'取込対象'"
											v-bind:radioGroupName="'torikmTargetKbn'"
											v-bind:requireFlg="true"
											v-bind:tabindex="'1'"
											v-model="form.torikmTargetKbn"
										/>
									</div>
								</td>
							</tr>
							<tr>
								<td>
									取込元
								</td>
								<td>
									<file-upload
										ref="torikmMoto"
										v-bind:fileExtensions="disp.fileExtensionList"
										v-bind:fileSize="2"
										v-bind:messageType="'CSV'"
										v-bind:name="'取込元'"
										v-bind:requireFlg="true"
										v-bind:tabindex="'2'"
									/>
								</td>
							</tr>
						</table>
						
						<section>
							<div class="contents__block-top">
								<div class="contents__block-top__area--float_l">
									<h2 class="contents__h2">取込結果</h2>
								</div>
								<div class="contents__block-top__area--float_r" style="padding-top:0px">
									<span class="margin-r-200px">{{tableListCount}}件</span>
								</div>
							</div>
							<div class="tbl-list__wrap--small">
								<table class="tbl-list--border-all_exist tbl-sticky-head wrap--overflow_hidden">
									<colgroup>
										<col style="width:249px">
										<col style="width:350px">
										<col style="width:130px">
										<col style="width:897px">
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
										<col style="width:249px">
										<col style="width:350px">
										<col style="width:130px">
										<col style="width:897px">
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
								v-bind:tabindex="'6'"
								v-bind:value="'戻る'"
								v-on:click="clickReturnBtn"
							/>
							<button-input
								class="contents__btn-blue"
								v-bind:name="'cancelButton'"
								v-bind:tabindex="'7'"
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
								v-bind:tabindex="'5'"
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
import RadioButton from '@/components/RadioButton.vue';
import FileUpload from '@/components/FileUpload.vue';
import ErrorMessage from '@/components/ErrorMessage.vue';

import {
	HTTP_STATUS,
} from '@/const/const.js';

import { PATH_PNKS010_READCSV } from '@/const/route.js';

import { 
	co_postFile, 
	co_setError
} from '@/common/ajaxUtility.js';

import { co_isEmpty } from '@/common/checkUtility.js';

import {
	STORE_INFO_CLEAR,
	STORE_ERROR_CLEAR,
	STORE_ERROR_IS_ERRFLG,
	STORE_VIEW_PROGRESS,
} from '@/const/store.js';

export default {
	components: {
		'button-input': ButtonInput,
		'radio-button': RadioButton,
		'file-upload': FileUpload,
		'error-message': ErrorMessage,
	},
	data: function () {
		return {
			form: {
				// 取込対象区分
				torikmTargetKbn: "",
				// 取込CSVファイル
				csvFile: null,
			},
			disp: {
				// ラジオボタンデータリスト
				radioButtonListItems: [],
				// 一覧リスト
				tableList: [],
				// アップロード許可拡張子リスト
				fileExtensionList: ["csv"],
			}
		}
	},
	created: function() {
		// 取込対象ラジオボタンを初期化する
		this.disp.radioButtonListItems = [
			{code: "0", name: "全て"},
			{code: "1", name: "定番"},
			{code: "2", name: "催事"},
		];
		// 取込対象-全てを選択する
		this.form.torikmTargetKbn = "0";
		
	},
	mounted: async function() {
		// ** フォーカス設定 **
		await this.$nextTick();
		this.$refs.torikmTargetKbn.focus();
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
		// click - メニュー画面へ遷移する
		clickReturnBtn: function() {
			this.$store.commit(STORE_INFO_CLEAR);
			this.$router.back();
		},
		// click - 画面初期化する
		clickCancelBtn: async function() {
			
			// 取込対象区分
			this.form.torikmTargetKbn = "0";
			// 取込CSVファイル
			this.form.csvFile = null;
			// 選択済みのファイルをクリアする
			this.$refs.torikmMoto.clearSelectedFile();
			
			// 一覧リスト
			this.disp.tableList = [];
			// エラーメッセージをクリアする
			this.$store.commit(STORE_ERROR_CLEAR);
			
			// ** フォーカス設定 **
			await this.$nextTick();
			this.$refs.torikmTargetKbn.focus();
		},
		// click - CSVファイルを取込する
		clickTorikmBtn: function() {
			
			// エラーメッセージをクリアする
			this.$store.commit(STORE_ERROR_CLEAR);
			
			this.$store.commit(STORE_VIEW_PROGRESS, true);
			
			// ** チェック処理 **
			this.$refs.torikmTargetKbn.validate();
			this.$refs.torikmMoto.validate();
			
			// ** エラー表示制御 **
			if (this.$store.getters[STORE_ERROR_IS_ERRFLG]) {
				this.$store.commit(STORE_VIEW_PROGRESS, false);
				return;
			}
			
			// 一覧リストをクリアする
			this.disp.tableList = [];
			
			// 取り込んだCSVファイルを取得する
			this.form.csvFile = this.$refs.torikmMoto.getFile();
			
			// CSVファイルをアップロードする
			const self = this;
			co_postFile(PATH_PNKS010_READCSV, this.form, function(res) {
				
				self.$store.commit(STORE_VIEW_PROGRESS, false);
				
				if (res.status != HTTP_STATUS.OK) {
					
					// 異常終了
					co_setError(res.data, self.$refs);
					if (!co_isEmpty(self.$refs[res.data[0].errorItems[0]])) {
						self.$refs[res.data[0].errorItems[0]].focus();
					}
					return;
				}
				
				// 一覧リストを設定する
				self.disp.tableList = res.data;
			});
		}
	}
}
</script>

<style scoped>
	.fontsize-14px {
		font-size: 14px;
	}
	
	.border-inline {
		border-inline: 2px solid #dedfe8;
	}
	
	.btn-display-change {
		display: flex;
		justify-content: space-between;
	}
	
	.right-adjustment {
		margin-right: 7%;
	}
	
	.margin-right-30 {
		margin-right: 30px;
	}

	.file-box {
		background-color: #EEFFFF;
		padding: 0px 0px;
		appearance: none;
	}
	
	.tbl-list__wrap--small {
		width: 90%;
		margin: 0 1%;
	}

	.tbl-item--upload td:nth-of-type(1) {
		width: 0px;
	}

	.tbl-item--upload td:nth-of-type(2) {
		width: 0px;
	}

	.tbl-sticky-head {
		position: -webkit-sticky;
		position: sticky;
		top: 0;
		z-index: 1
	}

	.margin-r-200px {
		margin-right: 200px;
	}

	tr.border-b {
		border-bottom: 2px solid #dedfe8;
	}
	
	.border--top_none {
		border-top: none !important;
	}
	
	.padding-left-6 {
		padding-left: 6px;
	}
</style>
