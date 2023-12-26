<template>
	<div id="test">
		<!-- メインコンテンツ -->
		<div class="contents__block--exist_return">
			<div class="contents__wrap--scroll_vertical">
				<!-- エラーメッセージ -->
				<error-message />
				<!-- 画面 -->
				<section>
					<h2 class="contents__h2">ＵＲＬ（例：./oi/offices/list）</h2>
					<input
						type="text"
						class="input--jigyosyo-name"
						v-model="url"
						v-on:keydown.delete.stop=""
					>
					<h2 class="contents__h2">リクエスト【Form】</h2>
					<table class="tbl-item--border-row_exist">
						<tbody>
							<tr>
								<td class="tbl-label__main-grid2">
								※JSON形式のデータ<br />
									<b>
<pre>
(例)：
{
	shibuCd: '12'
	,nendo: '2020'
	,koyoNo: ''
	,jigyosNo: ''
	,keyword: ''
}
</pre>
									</b>
								</td>
								<td>
									<textarea
										rows="9"
										style="width: 700px;"
										v-model="form"
										v-on:keydown.delete.stop=""
										v-on:drop.prevent="dropFile"
										placeholder="ファイルをドラッグ＆ドロップするか、JSON形式のデータを入力してください。"
									>
									</textarea>
								</td>
								<input
									type="button"
									class="contents__btn-blue"
									value="JSON整形"
									v-on:click="clickJson"
								>
							</tr>
						</tbody>
					</table>
					<div style="text-align: left;">
						<input type="button" class="contents__btn-blue" value="POST" v-on:click="clickPostBtn">&nbsp;
					</div>
					<h2 class="contents__h2">ステータスコード</h2>
					<input
						type="text"
						class="input--jigyosyo-name"
						v-bind:readonly="true"
						v-bind:value=status
						v-on:keydown.delete.stop=""
					>
					<h2 class="contents__h2">レスポンス【Entity】</h2>
					<table class="tbl-item--border-row_exist">
						<tbody>
							<tr>
								<td class="tbl-label__main-grid2" v-if="entityDisplayFlg">
									結果
								</td>
								<td v-if="entityDisplayFlg">
									<textarea
										rows="9"
										style="width: 700px;"
										v-bind:readonly="true"
										v-bind:value="entity"
									>
									</textarea>
								</td>
								<input
									type="button"
									class="contents__btn-blue"
									value="非表示"
									v-on:click="entityDisplayFlg=false;"
									v-if="entityDisplayFlg"
								>
								<input
									type="button"
									class="contents__btn-blue"
									value="表示"
									v-on:click="entityDisplayFlg=true;"
									v-if="!entityDisplayFlg"
								>
							</tr>
						</tbody>
					</table>
				</section>
				<br />
			</div>
		</div>
	</div>
</template>

<script>
import ErrorMessage from '@/components/ErrorMessage.vue';

import { co_downloadFile } from '@/common/ajaxUtility.js';
import { STORE_VIEW_TITLE, STORE_VIEW_PROGRESS } from '@/const/store.js';

export default {
	data() {
		return {
			url: 'test/reporttest',
			form: null,
			entity: null,
			status: null,
			formDisplayFlg: true,
			entityDisplayFlg: false,
		}
	},
	components: {
		'error-message': ErrorMessage
	},
	created: function () {
		// 初期化
		this.$store.commit(STORE_VIEW_TITLE, 'サーバテスト画面');
	},
	methods: {
		// POST送信ボタン押下時処理
		clickPostBtn: async function () {
			this.$store.commit(STORE_VIEW_PROGRESS, true);
			const json = await co_downloadFile(this.url, eval('(' + this.form + ')'), this.callback);
			if (json != null) {
				this.entity = JSON.stringify(json, null, '\t');
			}
			this.entityDisplayFlg = true;
			this.$store.commit(STORE_VIEW_PROGRESS, false);
		},
		// ファイルドラッグ時処理
		dropFile: function () {
			const reader = new FileReader();
			const that = this;
			reader.onload = function() {
				that.form = reader.result;
			}
			reader.readAsText(event.dataTransfer.files[0], "UTF-8");
		},
		// JSON整形ボタン押下時処理
		clickJson: function () {
			const json = eval('(' + this.form + ')');
			if (json != null) {
				this.form = JSON.stringify(json, null, '\t');
			}
		},
		// コールバック処理
		callback: function (response, handle) {
			this.status = response.status;
			const json = response.data;
			try {
				this.entity = JSON.stringify(json, null, '\t');
			} catch (error) {
				this.entity = json;
			}
			handle(response);
		}
	}
}
</script>

<style scoped>
</style>