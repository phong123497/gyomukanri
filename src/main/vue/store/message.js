/**
//================================================================================
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// JavaScriptName:message
//
// <<Modification History>>
// Version  | Date       | Updated By            | Content
// ---------+------------+-----------------------+--------------------------------
// 01.00.00 | 2020/10/02 | AMC                   | 新規作成
//================================================================================
 */

// メッセージ一覧
export const message = {
	namespaced: true,
	state: {
		msgMap: {}
	},
	getters: {
		// メッセージを取得
		getMessage(state) { return function(id) {
				if ( state.msgMap[id] ) {
					return state.msgMap[id];
				}
				return id;
			}
		}
	},
	mutations: {
		// メッセージ情報を設定
		setMessageInfo(state, msgMap) {
			state.msgMap = msgMap;
		}
	},
	actions: {
	},
	modules: {
	}
}