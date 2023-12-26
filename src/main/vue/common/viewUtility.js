/**
//================================================================================
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// JavaScriptName:viewUtility
//
// <<Modification History>>
// Version  | Date       | Updated By            | Content
// ---------+------------+-----------------------+--------------------------------
// 01.00.00 | 2023/07/31 |  AMC                  | 新規作成
//================================================================================
 */
import router from '@/router/router.js';
import store from '@/store/store.js';

import {
	STORE_VIEW_ADD_VIEW_PARAMS,
	STORE_VIEW_DELETE_PARAMS,
} from '@/const/store.js';


/*
 * 画面遷移(進む)
 * @param {String} path 画面URL
 * @param {Object} params 画面間受け渡しパラメータ
 */
export function co_nextView(path, params) {

	// 遷移先一覧を取得
	let routes = router.getRoutes();
	
	// 遷移先画面の機能ID取得
	let nextFuncId = null;
	for (let i=0; i <= routes.length; i++) {
		if (path === routes[i].path) {
			nextFuncId = routes[i].name;
			break;
		}
	}

	// 画面間パラメータを登録
	store.commit(STORE_VIEW_ADD_VIEW_PARAMS, {id:nextFuncId ,params: params});


	// 画面遷移
	router.push(path);
}

/*
 * 画面遷移(戻る)
 * @param {Object} params 画面間受け渡しパラメータ
 */
export function co_backView(){
	
	// 画面間パラメータを削除
	let funcId = router.currentRoute._value.name;
	store.commit(STORE_VIEW_DELETE_PARAMS, funcId);

	// 画面遷移
	router.back();
}

