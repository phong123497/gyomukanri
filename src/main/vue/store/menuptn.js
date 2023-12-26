/**
//================================================================================
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// JavaScriptName:menuptn
//
// <<Modification History>>
// Version  | Date       | Updated By            | Content
// ---------+------------+-----------------------+--------------------------------
// 01.00.00 | 2023/6/29 |  AMC                  | 新規作成
//================================================================================
 */

import { co_isEmpty } from '@/common/checkUtility.js';
 
import * as Const from '@/const/route.js';

import router from '@/router/router.js';

export const menuptn= {
	namespaced: true,
	state: {
		menuPattern:null,
		rootFuncInfo:null,
	},
	getters: {
		getMenuPatternInfo(state) {
			return function() {
				let clone = JSON.parse(JSON.stringify(state.menuPattern));
				return clone;
			}
		},
		getRootFuncInfo(state) {
			return state.rootFuncInfo;
		},
		getKengenKbn(state) { return function() {
			let kengenKbn = null;
			for (let page in state.menuPattern) {
				let funcID = router.currentRoute._value.name;
				if (!state.menuPattern[page].funcMap[funcID]) {
					
					continue;
				}
				kengenKbn = state.menuPattern[page].funcMap[funcID].kengenKbn
				break;
			}
			return kengenKbn;
		}},
	},
	mutations: {
		setMenuPatternInfo(state,menu) {
			let menuPtn = {};
			for (let i = 0; i < menu.length; i++) {
				let pageGroup = menu[i].page
				let menus = {};
				while (pageGroup == menu[i].page) {
					menus[menu[i].funcId] = {
						name: menu[i].funcName,
						path: Const['PATH_' + menu[i].funcId],
						displayMenuFlg: menu[i].displayMenuFlg,
						kengenKbn: menu[i].kengenKbn,
					}
					i += 1;
					if (i == menu.length) {
						break;
					}
				}
				i -= 1;
				menuPtn[pageGroup] = {
					name: menu[i].pageName,
					funcMap: menus,
				};
			}
			state.menuPattern = menuPtn
		},
		setRootFuncInfo(state,rootFuncInfo) {
			if (!co_isEmpty(state.rootFuncInfo)) {
				return;
			}
			state.rootFuncInfo = rootFuncInfo;
		},
		clearRootFuncInfo(state){
			state.rootFuncInfo = null;
		},
	},
	actions: {
	},
	modules: {
	}
}