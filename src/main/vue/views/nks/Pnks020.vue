<!--
//================================================================================
// ID: PNKS020
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// VueName: Pnks020
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/07 | ＡＭＣ               | 新規作成
//================================================================================
 -->
<template>
	<div>
		<!-- メインコンテンツ -->
		<main id="PNKS020">
			<div class="contents__block--exist_return">
				<h1 class="header__block-title">
					<table>
						<tbody>
							<tr>
								<td>
									農産企画情報 商品検索
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
					<div class="contents__block-search">
						<div class="contents__block-search__main">
							<div class="tbl-list__wrap">
								<table>
									<colgroup>
										<col style="width:180px">
										<col style="width:120px">
										<col style="width:180px">
										<col style="width:300px">
										<col style="width:120px">
									</colgroup>
									<tbody>
										<tr>
											<td class="txt--align_r">
												<span class="contents__block-search__label">企画番号</span>
											</td>
											<td>
												<code-input 
													class="width-60 txt--align_l" 
													ref="projNo" 
													v-bind:disabledFlg="searchDisabledFlg"
													v-bind:maxLength="3"
													v-bind:name="'企画番号'" 
													v-bind:requireFlg="true" 
													v-bind:tabindex="'1'"
													v-model="form.cqkikano" />
											</td>
											<td class="txt--align_r">
												<span class="contents__block-search__label">共同購入商品コード</span>
											</td>
											<td>
												<code-input 
													class="width-120 txt--align_l" 
													ref="kydKonyShincdStart"
													v-bind:disabledFlg="searchDisabledFlg"
													v-bind:maxLength="7" 
													v-bind:name="'共同購入商品コード開始'"
													v-bind:tabindex="'2'" 
													v-model="form.kyshincdStart" />
												<span class="contents__block-search__label">～</span>
												<code-input 
													class="width-120 txt--align_l" 
													ref="kydKonyShincdEnd"
													v-bind:disabledFlg="searchDisabledFlg"
													v-bind:maxLength="7"
													v-bind:name="'共同購入商品コード終了'"
													v-bind:tabindex="'3'"
													v-model="form.kyshincdEnd"  />
											</td>
											<td>
												<button-input
													class="contents__btn-blue"
													v-bind:disabledFlg="searchDisabledFlg"
													v-bind:name="'SearchBtn'"
													v-bind:tabindex="'4'"
													v-bind:value="'検索'"
													v-on:click="clickSearchBtn" />
											</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<div class="contents__view--bg_gray">
						<div class="contents__block-top">
							<div class="contents__block-top__area--float_r kensu-margin-right" v-if="isInitFlg">
								<span>{{ kensuCount }}</span>
							</div>
						</div>
						<div id="M280MList" class="tbl-list__wrap">
							<table class="tbl-list--border-all_exist tbl-sticky-head wrap--overflow_hidden">
								<colgroup>
									<col style="width:120px">
									<col style="width:180px">
									<col style="width:600px">
									<col style="width:220px">
									<col style="width:50px">
									<col style="width:50px">
									<col style="width:50px">
									<col style="width:50px">
									<col style="width:50px">
									<col style="width:50px">
									<col style="width:50px">
									<col style="width:50px">
									<col style="width:120px">
								</colgroup>
								<thead>
									<tr>
										<th>企画番号</th>
										<th>共同購入商品コード</th>
										<th>商品名</th>
										<th>規格</th>
										<th>エフ</th>
										<th>サガ</th>
										<th>ララ</th>
										<th>オオ</th>
										<th>クマ</th>
										<th>ミヤ</th>
										<th>カゴ</th>
										<th>オキ</th>
										<th>
										</th>
									</tr>
								</thead>
							</table>
							<table class="tbl-list--border-all_exist wrap--overflow_hidden no-border">
								<colgroup>
									<col style="width:120px">
									<col style="width:180px">
									<col style="width:600px">
									<col style="width:220px">
									<col style="width:50px">
									<col style="width:50px">
									<col style="width:50px">
									<col style="width:50px">
									<col style="width:50px">
									<col style="width:50px">
									<col style="width:50px">
									<col style="width:50px">
									<col style="width:120px">
								</colgroup>
								<tbody>
									<tr v-for="(item, index) in tableData" :key="item" style="height:54px; !important">
										<td class="border--top_none">&nbsp;{{ item.cqProjNo }}</td>
										<td class="border--top_none">
											&nbsp;{{ item.kydKonyShinCd }}
										</td>
										<td class="border--top_none">&nbsp;{{ item.shinNm }}</td>
										<td class="border--top_none">&nbsp;{{ item.stdd }}</td>
										<td class="border--top_none txt--align_c">
											{{ item.fcp }}
										</td>
										<td class="border--top_none txt--align_c">
											{{ item.sag }}
										</td>
										<td class="border--top_none txt--align_c">
											{{ item.lala }}
										</td>
										<td class="border--top_none txt--align_c">
											{{ item.oit }}
										</td>
										<td class="border--top_none txt--align_c">
											{{ item.kmt }}
										</td>
										<td class="border--top_none txt--align_c">
											{{ item.myz }}
										</td>
										<td class="border--top_none txt--align_c">
											{{ item.kag }}
										</td>
										<td class="border--top_none txt--align_c">
											{{ item.okn }}
										</td>
										<td class="border--top_none txt--align_c">
											<button-input 
												class="contents__btn-blue"
												v-bind:tabindex="'5'"
												v-bind:value="'詳細'"
												v-on:click="clickDtlBtn(index)" />
										</td>
									</tr>
								</tbody>
							</table>
						</div>
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
									:style="{ 'margin-right': '30px' }"
									class="contents__btn-blue"
									v-bind:name="'backButton'"
									v-bind:tabindex="'6'"
									v-bind:value="'戻る'"
									v-on:click="clickReturnBtn" />
							<button-input
									class="contents__btn-blue"
									v-bind:name="'cancelBtn'"
									v-bind:tabindex="'7'"
									v-bind:value="'取消'"
									v-on:click="clickCancelBtn" />
						</div>
					</div>
					<div class="center-block">
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
	co_isEmpty,
} from '@/common/checkUtility.js';

import {
	co_post,
	co_setError,
} from '@/common/ajaxUtility.js';

import { 
	DIALOG_BUTTON_LIST_KEY,
	HTTP_STATUS 
} from '@/const/const.js'

import { co_alert } from '@/common/dialogUtility.js';

import { co_getMessage } from '@/common/messageUtility.js';

import {
	STORE_INFO_CLEAR,
	STORE_VIEW_PROGRESS,
	STORE_ERROR_IS_ERRFLG,
	STORE_DATA_ADD_DATA,
	STORE_DATA_GETDATA,
	STORE_DATA_DELETE_AT,
	STORE_ERROR_CLEAR,
	STORE_ERROR_ADDMSG,
	STORE_DATA_ADD_INHERIT_DATA,
	STORE_DATA_DELETE_INHERIT_DATA_AT,
} from '@/const/store.js'

import {
	PATH_PNKS020_SEARCH,
	PATH_PNKS020_SEARCH_CHECK,
	PATH_PNKS030
} from '@/const/route.js'

export default {
	props: {
	},
	components: {
		'button-input': ButtonInput,
		'code-input': CodeInput,
		'error-message': ErrorMessage,
	},
	data: function () {
		return {
			form: {
				cqkikano: '',
				kyshincdStart: '',
				kyshincdEnd: ''
			},
			tableData: [],
			errorMessage: '',
			isShowError: false,
			searchDisabledFlg: false,
			isInitFlg: false
		}
	},
	created: function () {
		let saveSearchData = this.$store.getters[STORE_DATA_GETDATA]('pnks020')
		if (saveSearchData != null) {
			this.form.cqkikano = saveSearchData.cqkikano
			this.form.kyshincdStart = saveSearchData.kyshincdStart
			this.form.kyshincdEnd = saveSearchData.kyshincdEnd
			this.$nextTick(function() {
				this.$refs.projNo.focus();
				this.clickSearchBtn();
			});
		}
	},
	mounted: async function () {
		await this.$nextTick();
		// フォーカス設定
		this.$refs.projNo.focus();
	},
	beforeDestroy: function () {
	},
	computed: {
		kensuCount() {
			const reg = /(\d)(?=(\d{3})+$)/g;
			const kensures = this.tableData.length.toString().replace(reg, "$1,");
			return kensures + ' 件';
		}
	},
	watch: {
	},
	methods: {
		// ▼ --------------------------------------- 各入力イベント --------------------------------------- ▼
		// click - 検索ボタン
		clickSearchBtn: async function () {
			// プログレスバー表示
			this.$store.commit(STORE_VIEW_PROGRESS, true)
			// エラーをクリア
			this.$store.commit(STORE_INFO_CLEAR);
			this.$store.commit(STORE_ERROR_CLEAR)
			// 入力チェック
			this.$refs.projNo.validate()
			this.$refs.kydKonyShincdStart.validate()
			this.$refs.kydKonyShincdEnd.validate()

			if (this.$store.getters[STORE_ERROR_IS_ERRFLG]) {
				// プログレスバー非表示
				this.$store.commit(STORE_VIEW_PROGRESS, false)
				return
			}
			const self = this
			this.tableData = []
			const dataCount = await this.getDataCount()
			// 検索結果が1000件を超えています
			if(dataCount > 1000){
				this.$store.commit(STORE_ERROR_ADDMSG,
						co_getMessage('CMS0032E', 1000));
			}
			// 検索結果が500件を超えています
			else if(dataCount > 500){
				co_alert(co_getMessage('CMS0015W', 500), function(btnValue){
					if (btnValue == DIALOG_BUTTON_LIST_KEY.CANCEL) {
						self.$store.commit(STORE_VIEW_PROGRESS, false);
						return;
					}
					co_post(PATH_PNKS020_SEARCH, self.form, self.searchBackFun)
				});
			}
			else{
				co_post(PATH_PNKS020_SEARCH, self.form, self.searchBackFun)
			}
		},
		searchBackFun: function (res) {
			if (res.status == HTTP_STATUS.OK) {
				this.searchDisabledFlg = true
				this.tableData = res.data.nousanProjinShinList
			} else {
				this.searchDisabledFlg = false
				// 業務エラー表示
				co_setError(res.data, this.$refs)
				const errorItem = res.data[0].errorItems[0];
				if (!co_isEmpty(this.$refs[errorItem])) {
					const self = this;
					this.$nextTick(function() {
							self.$refs[errorItem].focus();
					});
				}
			}
			this.isInitFlg = true
			// プログレスバー非表示
			this.$store.commit(STORE_VIEW_PROGRESS, false)
		},
		// click - 詳細ボタン
		clickDtlBtn: function (index) {
			this.$store.commit(STORE_DATA_ADD_DATA, {
				id: 'pnks020',
				data: {
					cqkikano: this.form.cqkikano,
					kyshincdStart: this.form.kyshincdStart,
					kyshincdEnd: this.form.kyshincdEnd
				}
			})
			// 継承データ保存
			this.$store.commit(STORE_DATA_ADD_INHERIT_DATA, [
				{
					cqkikano: this.tableData[index].cqProjNo,
					kydKonyShinCd: this.tableData[index].kydKonyShinCd,
					kyshincdStart: this.form.kyshincdStart,
					kyshincdEnd: this.form.kyshincdEnd
				}
			])
			this.$router.push({
				path: PATH_PNKS030
			})
		},
		// click - 戻るボタン
		clickReturnBtn: function () {
			this.$store.commit(STORE_DATA_DELETE_AT, 'pnks020')
			this.$store.commit(STORE_DATA_DELETE_INHERIT_DATA_AT, 0)
			this.$router.back()
		},
		// click - 取消ボタン
		clickCancelBtn: function () {
			this.$store.commit(STORE_INFO_CLEAR);
			this.$store.commit(STORE_ERROR_CLEAR);
			this.form = {
				cqkikano: '',
				kyshincdStart: '',
				kyshincdEnd: ''
			}
			this.tableData = []
			this.searchDisabledFlg = false
			this.isInitFlg = false
			this.$nextTick(function() {
				this.$refs.projNo.focus();
			});
		},
		
		// 検索前処理 件数取得
		getDataCount: function() {
			// ajaxにてサーバーと通信
			return co_post( PATH_PNKS020_SEARCH_CHECK, this.form );
		},
	}
}
</script>

<style scoped>
.tbl-sticky-head {
	overflow: hidden;
	position: -webkit-sticky;
	position: sticky;
	top: 0;
	z-index: 1
}

.tbl-list--border-all_exist th {
    padding: 8px 5px !important;
}
.no-border {
	margin-top: -2px;
}
.kensu-margin-right {
	margin-right: 190px;
}
</style>
