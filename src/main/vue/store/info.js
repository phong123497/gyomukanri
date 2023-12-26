/**
//================================================================================
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// JavaScriptName:info
//
// <<Modification History>>
// Version  | Date       | Updated By            | Content
// ---------+------------+-----------------------+--------------------------------
// 01.00.00 | 2023/05/26 | AMC                   | 新規作成
//================================================================================
 */

export const info = {
	namespaced: true,
	state: {
		InfoFlg: false,
		infoMsg: '',
		popupInfoFlg: false,
		popupInfoMsg: '',
		popupFlg: false
	},
	getters: {
		// 情報メッセージを取得します
		getMsg(state) {
			if (state.popupFlg) {
				return state.popupInfoMsg;
			} else {
				return state.infoMsg;
			}
		},
		// 情報フラグを取得します
		isInfoFlg(state) {
			if (state.popupFlg) {
				return state.popupInfoFlg;
			} else {
				return state.InfoFlg;
			}
		},
	},
	mutations: {		
		// メッセージを初期化します
		clear(state) {
			if (state.popupFlg) {
				state.popupInfoFlg = false;
				state.popupInfoMsg = '';
			} else {
				state.InfoFlg = false;
				state.infoMsg = '';
			}
		},
		// メッセージを設定&表示します
		setMsg(state, msg) {
			if (state.popupFlg) {
				state.popupInfoFlg = true;
				state.popupInfoMsg = msg;
			} else {
				state.InfoFlg = true;
				state.infoMsg = msg;
			}
		},
		// ポップアップフラグを設定します
		setPopupFlg(state, flg) {
			state.popupFlg = flg;
		},
	},
	actions: {
	},
	modules: {
	}
}