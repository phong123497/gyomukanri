/**
//================================================================================
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// JavaScriptName:env
//
// <<Modification History>>
// Version  | Date       | Updated By            | Content
// ---------+------------+-----------------------+--------------------------------
// 01.00.00 | 2022/10/28 |  AMC                  | 新規作成
//================================================================================
 */

export const ajax = {
	namespaced: true,
	state: {
		ajaxCount: 0
	},
	getters: {
		// Ajax通信の完了結果を取得
		// ※Ajax通信が、すべて完了(true)、１つでも通信中(false)を返却
		hasAjaxCompleted(state) {
			return state.ajaxCount == 0;
		},
		// Ajax通信のカウントを取得
		getAjaxCount(state) {
			return state.ajaxCount;
		}
	},
	mutations: {
		// Ajax通信のカウントクリア
		clearAjaxCount(state) {
			state.ajaxCount = 0;
		},
		// Ajax通信のカウントアップ
		// ※異常カウントを考慮
		incrementAjaxCount(state) {
			if (state.ajaxCount < 0) {
				state.ajaxCount = 1;
			} else {
				state.ajaxCount += 1;
			}
		},
		// Ajax通信のカウントダウン
		// ※異常カウントを考慮
		decrementAjaxCount(state) {
			if (state.ajaxCount > 1) {
				state.ajaxCount -= 1;
			} else {
				state.ajaxCount = 0;
			}
		}
	},
	actions: {
	},
	modules: {
	}
}