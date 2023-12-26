/**
//================================================================================
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// JavaScriptName:dialog
//
// <<Modification History>>
// Version  | Date       | Updated By            | Content
// ---------+------------+-----------------------+--------------------------------
// 01.00.00 | 2020/10/02 | AMC                   | 新規作成
//================================================================================
 */

export const dialog = {
	namespaced: true,
	state: {
		dialogFlg: false,
		callBack: null,
		message: '',
		buttonList: [],
		focusKey: ''
	},
	getters: {
		// ダイアログフラグを取得します
		isDialogFlg(state) {
			return state.dialogFlg;
		},
		// コールバックを取得します
		getCallBack(state) {
			return state.callBack;
		},
		// メッセージを取得します
		getMessage(state) {
			return state.message;
		},
		// ボタンリストを取得します
		getButtonList(state) {
			return state.buttonList;
		},
		// フォーカスキーを取得します
		getFocusKey(state) {
			return state.focusKey;
		}
	},
	mutations: {
		// ダイアログフラグを設定します
		setDialogFlg(state, flg) {
			state.dialogFlg = flg;
		},
		// コールバックを設定します
		setCallBack(state, callBack) {
			state.callBack = callBack;
		},
		// メッセージを設定します
		setMessage(state, message) {
			state.message = message;
		},
		// ボタンリストを設定します
		setButtonList(state, buttonList) {
			state.buttonList = buttonList;
		},
		// フォーカスキーを設定します
		setFocusKey(state, focusKey) {
			state.focusKey = focusKey;
		}
	},
	actions: {
	},
	modules: {
	}
}