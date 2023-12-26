<!--
//================================================================================
// ID: PNKS060
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// VueName: Pnks060
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/06 | ＡＭＣ               | 新規作成
//================================================================================
 -->
<template>
	<div>
		<!-- メインコンテンツ -->
		<main id="PNKS060">
			<div class="contents__block--exist_return">
			
				<h1 class="header__block-title">シミュレーション用データ出力</h1>
				
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
										<span class="contents__block-search__label">企画番号：</span>
									</td>
									<td>
										<code-input
											class="width-80"
											ref="projNoStart"
											v-bind:activeZeroPadding="true"
											v-bind:maxLength="3"
											v-bind:name="'企画番号開始'"
											v-bind:tabindex="'1'"
											v-model="form.projNoStart"
										/>
										<span class="contents__block-search__label wavy-line">～</span>
										<code-input
											class="width-80"
											ref="projNoEnd"
											v-bind:activeZeroPadding="true"
											v-bind:maxLength="3"
											v-bind:name="'企画番号終了'"
											v-bind:tabindex="'2'"
											v-model="form.projNoEnd"
										/>
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
								v-bind:value="'CSV出力'"
								v-bind:name="'csvOutputButton'"
								v-bind:tabindex="'3'"
								v-on:click="clickCsvOutputBtn"
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
import CodeInput from '@/components/CodeInput.vue';
import ErrorMessage from '@/components/ErrorMessage.vue';

import { 
	co_downloadFile, 
	co_setError
} from '@/common/ajaxUtility.js';

import { co_isEmpty } from '@/common/checkUtility.js';

import {
	HTTP_STATUS,
} from '@/const/const.js';

import { PATH_PNKS060_OUTPUT } from '@/const/route.js';

import {
	STORE_INFO_CLEAR,
	STORE_ERROR_CLEAR,
	STORE_ERROR_IS_ERRFLG,
	STORE_VIEW_PROGRESS,
} from '@/const/store.js';

export default {
	components: {
		'button-input': ButtonInput,
		'code-input': CodeInput,
		'error-message': ErrorMessage,
	},
	data: function () {
		return {
			form: {
				// 企画番号開始
				projNoStart: '',
				// 企画番号終了
				projNoEnd: '',
			},
		}
	},
	mounted: async function() {
		await this.$nextTick();
		// ** フォーカス設定 **
		this.$refs.projNoStart.focus();
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
			
			// 企画番号開始
			this.form.projNoStart = '';
			// 企画番号終了
			this.form.projNoEnd = '';
			
			await this.$nextTick();
			// ** フォーカス設定 **
			this.$refs.projNoStart.focus();
		},
		// click - CSV出力ボタン押下
		clickCsvOutputBtn: function() {
			
			this.$store.commit(STORE_ERROR_CLEAR);
			this.$store.commit(STORE_VIEW_PROGRESS, true);
			
			// ** チェック処理 **
			this.$refs.projNoStart.validate();
			this.$refs.projNoEnd.validate();

			// ** エラー表示制御 **
			if (this.$store.getters[STORE_ERROR_IS_ERRFLG]) {
				this.$store.commit(STORE_VIEW_PROGRESS, false);
				return;
			}
			
			// CSVファイルをダウンロードするリクエスト
			const self = this;
			co_downloadFile(PATH_PNKS060_OUTPUT, this.form, function(res, downloadHandle) {
				
				self.$store.commit(STORE_VIEW_PROGRESS, false);
				
				if (res.status != HTTP_STATUS.OK) {
					// 異常終了
					co_setError(res.data, self.$refs)
					if (!co_isEmpty(self.$refs[res.data[0].errorItems[0]])) {
						self.$refs[res.data[0].errorItems[0]].focus();
					}
					return;
				}
				
				// ダウンロード処理
				downloadHandle(res);
			});
		},
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
