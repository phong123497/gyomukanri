/**
//================================================================================
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// JavaScriptName:error
//
// <<Modification History>>
// Version  | Date       | Updated By            | Content
// ---------+------------+-----------------------+--------------------------------
// 01.00.00 | 2020/10/02 | AMC                   | 新規作成
//================================================================================
 */

export const error = {
	namespaced: true,
	state: {
		errFlg: false,
		errMsg: [],
		popupErrFlg: false,
		popupErrMsg: [],
		popupFlg: false
	},
	getters: {
		// エラーフラグを取得します
		isErrFlg(state) {
			return state.errFlg;
		},
		// エラーメッセージを取得します
		getErrMsg(state) {
			return state.errMsg;
		},
		// ポップアップ用のエラーフラグを取得します
		isPopupErrFlg(state) {
			return state.popupErrFlg;
		},
		// ポップアップ用のエラーメッセージを取得します
		getPopupErrMsg(state) {
			return state.popupErrMsg;
		},
		// ポップアップフラグを取得します
		isPopupFlg(state) {
			return state.popupFlg;
		}
	},
	mutations: {
		// メッセージを削除します
		clear(state) {
			if (state.popupFlg) {
				state.popupErrFlg = false;
				state.popupErrMsg = [];
			} else {
				state.errFlg = false;
				state.errMsg = [];
			}
		},
		// メッセージを追加します
		addMsg(state, msg) {
			if (state.popupFlg) {
				state.popupErrFlg = true;
				state.popupErrMsg.push(msg);
			} else {
				state.errFlg = true;
				state.errMsg.push(msg);
			}
		},
		// ポップアップフラグを設定します
		setPopupFlg(state, flg) {
			state.popupFlg = flg;
		},
		// 指定されたエラーメッセージを取り除きます。(後方一致)
		popErrMsg(state, message) {
			let messageList = [];
			if (state.popupFlg) {
				messageList = state.popupErrMsg;
			} else {
				messageList = state.errMsg;
			}
			
			let popIndex = null;
			for (let i = 0 ; i < messageList.length ; i++) {
				if (messageList[i] == message) {
					popIndex = i;
				}
			}
			
			if (popIndex === null) {
				return;	
			}
			
			// 破壊的変更によりmessageListの代入元となったリストの値を変更する
			messageList.splice(popIndex, 1);
			
			if (messageList.length > 0){
				return;
			}
			
			// エラーメッセージがないため非表示にする
			if (state.popupFlg) {
				state.popupErrFlg = false;
			} else {
				state.errFlg = false;
			}
		},
	},
	actions: {
	},
	modules: {
	}
}