<!--
//================================================================================
// ID: -
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// VueName: GlobalHeader
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2022/10/28 |  AMC                 | 新規作成
//================================================================================
 -->
<template>
	<header>
		<div class="header__block-info">
			<div class="header__block-info--left">
				<img src="img/icon_logo.png" class="header__block-info--left__icon">
				<div v-for="(route, index) in headList" v-bind:key="route" v-bind:index="index" class="header_nav">
					<link-input
						v-if="index !== headList.length -1 "
						v-bind:class="{'root': index == 0, 'child': index != 0}"
						v-bind:label="route.pageName" 
						v-bind:href="route.path" />
					<div v-if="index ===  headList.length -1"
						v-bind:class="[{'root': index == 0, 'child': index != 0}, 'child-end']">{{route.pageName}}</div>
					<span v-if="index+1 != headList.length">＞</span>
				</div>
			</div>
			<div class="header__block-info--right">
				<img src="img/icon_personal.png" class="header__block-info--right__icon">
				<span>{{userInfo.tansNm}}</span>
				<span>｜</span>
				<span>
					<link-input
						style="font-size:100%"
						v-bind:label="'ログアウト'"
						v-on:click="this.clickLogout" />
				</span>
			</div>
		</div>
	</header>
</template>

<script>
import LinkInput from '@/components/LinkInput.vue'

import { co_isEmpty } from '@/common/checkUtility.js';
import { co_doLogout } from '@/common/unloadEventUtility.js';
import { co_confirm } from '@/common/dialogUtility.js';
import { co_getMessage } from '@/common/messageUtility';

import { DIALOG_BUTTON_LIST_KEY }  from '@/const/const.js';
import { 
	STORE_USER_GET_INFO,
	STORE_MENUPTN_GET_MENU_PATTERN_INFO
} from '@/const/store.js';
import { PATH_LOGIN } from '@/const/route.js';

export default {
	components: {
		'link-input':LinkInput
	},
	data: function() {
		return {
			userInfo: this.$store.getters[STORE_USER_GET_INFO], // warning対策:Objectの型を複製するためのsotre.getters
			headList: [],
		}
	},
	created: function() {
		// 担当者情報を設定
		this.userInfo = this.$store.getters[STORE_USER_GET_INFO];
	},
	watch: {
		$route:function(newRoute) {
			// ページ名称取得
			let pageName = null;
			if (!co_isEmpty(newRoute.meta.group)) {
				// メニューのみ
				pageName = newRoute.meta.group;
			} else {
				// メニュー以外
				// パス情報を元に機能IDを取得
				let routes = this.$router.getRoutes();
				let funcId = null;
				for (let route of routes) {
					if (newRoute.path === route.path){
						funcId = route.name;
						break;
					}
				}
				
				// メニューパターンからページ名を取得
				let menuPattern = this.$store.getters[STORE_MENUPTN_GET_MENU_PATTERN_INFO]();
				if (co_isEmpty(menuPattern)) {
					return;
				}
				
				Object.keys(menuPattern).forEach( function(page) {
					let funcMap = menuPattern[page].funcMap;
					if (co_isEmpty(funcMap[funcId])) {
						return;
					}

					pageName = menuPattern[page].name;
				});
			}

			let routeIndex = this.headList.findIndex((x)=>x.pageName===pageName);
			if (routeIndex === -1) {
				this.headList.push({pageName:pageName, path:newRoute.path});
			} else {
				this.headList.splice(routeIndex+1);
			}
		},
	},
	methods: {
		clickLogout: function() {
			this.executeLogout();
		},
		executeLogout: function() {
			// ダイアログに設定するコールバック関数を定義
			let callBack = async function(val) {
				if(val === DIALOG_BUTTON_LIST_KEY.OK) {
					// ログアウト
					co_doLogout();
					window.removeEventListener('beforeunload', co_doLogout);
					window.location.href = PATH_LOGIN;
				}
			};
			
			// ダイアログの表示
			co_confirm(co_getMessage('CMS0001W'), callBack);
		}
	},
}

</script>

<style scoped>
.header_nav {
	margin-top: 10px;
}

.header_nav > .root {
	font-size: 24px;
	text-decoration: underline;
}

.header_nav > .child {
	font-size: 18px;
	text-decoration: underline;
	margin-top: 5px;
}
.header_nav > .child-end {
	padding-top: 1px;
	padding-left: 6px;
	text-decoration: none;
}
</style>
