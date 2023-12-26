<!--
//================================================================================
// ID: -
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// VueName: App
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2022/10/28 |  AMC                 | 新規作成
//================================================================================
 -->
<template>
	<div
		class="view-area"
		v-on:keydown.delete.prevent.exact="keyControl"
		v-on:keydown.alt.left.prevent.exact="keyControl"
		v-on:keydown.f1.f2.f3.f4.f5.f6.f7.f8.f9.f10.f11.f12.prevent="keyControl"
		v-on:keydown.enter.exact="enterControl"
		v-on:keydown.shift.tab.exact="shiftTabControl"
		v-on:keydown.tab.exact="tabControl"
		v-on:dragover.prevent="mouseControl"
		v-on:drop.prevent="mouseControl"
	>
		<!-- 共通ヘッダ -->
		<global-header />
		<!-- メインコンテンツ -->
		<router-view v-bind:key="$route.fullPath"/>
		<!-- プログレスゲージ -->
		<template v-if="isProgressFlg">
			<progress-view/>
		</template>
		<!-- メッセージダイアログ -->
		<template v-if="isDialogFlg">
			<message-dialog
				v-bind:message="getMessage"
				v-bind:buttonList="getButtonList"
				v-bind:focusKey="getFocusKey"
			/>
		</template>
	</div>
</template>
 <script>
import { co_doLogout } from '@/common/unloadEventUtility.js';
import GlobalHeader from '@/components/GlobalHeader.vue';
import Progress from "@/components/Progress.vue";
import MessageDialog from '@/components/MessageDialog.vue';
import {
			API_MESSAGE_INFO,
			API_USER_INFO,
			API_MENUPATTERN_INFO
		} from '@/const/route.js';
import { co_post } from '@/common/ajaxUtility.js';
import {
			co_getTabList, co_getFirstTabItem, co_getLastTabItem, co_focusTabItem,
			co_getAfterTabList, co_getBeforeTabList, co_getRangeTabList
		} from '@/common/domUtility.js';
import {
			STORE_VIEW_IS_PROGRESS,
			STORE_DIALOG_IS_DIALOG_FLG,
			STORE_DIALOG_GET_MESSAGE,
			STORE_DIALOG_GET_BUTTON_LIST,
			STORE_DIALOG_GET_FOCUS_KEY,
			STORE_MSG_SET_MSG_INFO,
			STORE_USER_SET_INFO,
			STORE_MENUPTN_SET_MENU_PATTERN_INFO
		} from '@/const/store.js';

export default {
	props: {
		toppage: {type: String, default: ''}
	},
	components: {
		'global-header': GlobalHeader,
		'progress-view': Progress,
		'message-dialog': MessageDialog,
	},
	watch: {
		'$route.path': function() {
			this.$el.focus();
		}
	},
	computed: {
		isProgressFlg: function() {
			return this.$store.getters[STORE_VIEW_IS_PROGRESS];
		},
		isDialogFlg: function() {
			return this.$store.getters[STORE_DIALOG_IS_DIALOG_FLG];
		},
		getMessage: function() {
			return this.$store.getters[STORE_DIALOG_GET_MESSAGE];
		},
		getButtonList: function() {
			return this.$store.getters[STORE_DIALOG_GET_BUTTON_LIST];
		},
		getFocusKey: function() {
			return this.$store.getters[STORE_DIALOG_GET_FOCUS_KEY];
		}
	},
	created: async function() {

		// 利用者情報取得
		let user = await co_post(API_USER_INFO);
		this.$store.commit(STORE_USER_SET_INFO, user);
		
		// メニューパターン情報取得
		let menu = await co_post(API_MENUPATTERN_INFO);
		this.$store.commit(STORE_MENUPTN_SET_MENU_PATTERN_INFO, menu);
		
		// ブラウザの閉じるボタンでログアウト
		window.addEventListener('beforeunload', co_doLogout);
		
		
		// メッセージ情報取得
		let messageInfo = await co_post(API_MESSAGE_INFO);
		this.$store.commit(STORE_MSG_SET_MSG_INFO, messageInfo);
		
		// 指定のトップページを表示
		this.$router.push({ name:document.getElementsByTagName("div")[0].getAttribute("toppage")});
	},
	beforeUnmount:async function() {
		window.removeEventListener('beforeunload',  co_doLogout);
	},
	methods: {
		keyControl: function() {
			// キーイベントを無効化するためのメソッドのため、処理なし
		},
		mouseControl: function() {
			// キーイベントを無効化するためのメソッドのため、処理なし
		},
		// Tabキーのフォーカス移動を制御
		tabControl: function(event) {
			let tabIndex = event.target.tabIndex;
			let tabList = co_getTabList(tabIndex);
			let lastTabItem = co_getLastTabItem(tabList);
			if (lastTabItem !== null && event.target === lastTabItem) {
				let firstTabItem = co_getFirstTabItem(tabList);
				if (co_focusTabItem(firstTabItem)) {
					event.preventDefault();
				}
			} else {
				tabList = co_getRangeTabList(tabIndex, tabIndex);
				lastTabItem = co_getLastTabItem(tabList);
				if (lastTabItem !== null && event.target === lastTabItem) {
					let afterTabItem = co_getFirstTabItem(co_getAfterTabList(tabIndex));
					if (co_focusTabItem(afterTabItem)) {
						event.preventDefault();
					}
				}
			}
		},
		// Shift+Tabキーのフォーカス移動を制御
		shiftTabControl: function(event) {
			let tabIndex = event.target.tabIndex;
			let tabList = co_getTabList(tabIndex);
			let firstTabItem = co_getFirstTabItem(tabList);
			if (firstTabItem !== null && event.target === firstTabItem) {
				let lastTabItem = co_getLastTabItem(tabList);
				if (co_focusTabItem(lastTabItem)) {
					event.preventDefault();
				}
			} else {
				tabList = co_getRangeTabList(tabIndex, tabIndex);
				firstTabItem = co_getFirstTabItem(tabList);
				if (firstTabItem !== null && event.target === firstTabItem) {
					let beforeTabItem = co_getLastTabItem(co_getBeforeTabList(tabIndex));
					if (co_focusTabItem(beforeTabItem)) {
						event.preventDefault();
					}
				}
			}
		},
		// Enterキーのフォーカス移動を制御
		enterControl: function(event) {
			let tabMode = true;
			
			// ボタンにフォーカスがある時はデフォルト通り
			switch ( event.target.tagName ){
				case 'INPUT': 
					if ( event.target.type == 'button' ) {
						tabMode = false;
					}
					break;
				case 'BUTTON': 
					tabMode = false;
					break;
			}
			
			if ( tabMode ) {
				// Enterキーのデフォルト動作無効化
				event.preventDefault();
			
				// tabと同じ制御を行う
				this.tabControl(event);
			}
		}
	}
}
</script>
<style>
@import '@/assets/style.css'
</style>
