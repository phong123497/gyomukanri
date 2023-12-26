/**
//================================================================================
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// JavaScriptName:view
//
// <<Modification History>>
// Version  | Date       | Updated By            | Content
// ---------+------------+-----------------------+--------------------------------
// 01.00.00 | 2020/10/02 | AMC                   | 新規作成
//================================================================================
 */
import { PATH_PSYS020 } from '@/const/route.js';
import router from '@/router/router.js';

export const view = {
	namespaced: true,
	state: {
		name: '画面ID',
		title: '画面名',
		subtitle: null,
		changed: false,
		progressFlg: false,
		history: [{ path:PATH_PSYS020, name: 'PSYS020', params:{} }],
		backButtonFlg: true,
		printButtonFlg: true,
		customButtonFlg: false,
		customButtonTabindex: '',
		customButtonDisabledFlg: false,
		customCallBack: null,
		customFocusCallBack: null,
		customButtonTitle: '',
		globalMenuFlg: true,
		globalMenuChanged: false,
		backButtonClickFlg: false,
		viewParams: {},
	},
	getters: {
		// 戻り先のルート情報(URL含む)を取得
		getBackURL(state) {
			// 要素の最後はcurrentのルート情報(URL含む)
			return state.history[state.history.length - 2];
		},
		// 呼出元の画面のparamsを取得
		getParams(state) { return function() {
				let id = router.currentRoute._value.name;
				return state.viewParams[id];
			}
		},
		// 入力値変更フラグを取得
		hasChanged(state) {
			return state.changed;
		},
		// プログレス表示フラグを取得
		isProgressFlg(state) {
			return state.progressFlg;
		},
	},
	mutations: {
		/**
		 * 画面間パラメータ登録
		 * @param {Object} state 状態
		 * @param {Object} {id, params} 登録ID, パラメータ
		 */
		addViewParams(state, {id, params}) {
			state.viewParams[id] = params;
		},
		/**
		 * 画面間パラメータ初期化
		 * @param {Object} state 状態
		 */
		clearViewParams(state) {
			state.viewParams = {};
		},
		/**
		 * 画面間パラメータ削除
		 * @param {Object} state 状態
		 * @param {String} id 削除ID
		 */
		deleteParams(state, id){
			delete state.viewParams[id];
		},
		/**
		 * 画面ID設定
		 * @param {Object} state 状態
		 * @param {String} name 画面ID
		 */
		setName(state, name) {
			state.name = name;
		},
		/**
		 * 画面名設定
		 * @param {Object} state 状態
		 * @param {String} title 画面名
		 */
		setTitle(state, title) {
			state.title = title;
		},
		/**
		 * 入力値変更フラグ設定
		 * @param {Object} state 状態
		 * @param {Boolean} changed 入力値変更フラグ
		 */
		setChanged(state, changed) {
			state.changed = changed;
		},
		/**
		 * プログレス表示設定
		 * @param {Object} state 状態
		 * @param {Boolean} flg プログレス表示フラグ
		 */
		setProgressFlg(state, flg) {
			state.progressFlg = flg;
		},
		/**
		 * 履歴クリア
		 * @param {Object} state 状態
		 */
		clearHistory(state) {
			// 最初のダッシュボードは残す
			state.history.splice(1);
		},
		/**
		 * 履歴更新
		 * @param {Object} state 状態
		 * @param {Object} route ルート情報(URL含む)
		 */
		updateHistory(state, route) {
			let idx = state.history.findIndex(
						function(item) {
							return item.path === route.path;
						}
					);
			if ( 0 <= idx ) {
				state.history.splice(idx);
			}
			state.history.push(route);
		},
	},
	actions: {
	},
	modules: {
	}
}