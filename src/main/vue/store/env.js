/**
//================================================================================
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// JavaScriptName:env
//
// <<Modification History>>
// Version  | Date       | Updated By            | Content
// ---------+------------+-----------------------+--------------------------------
// 01.00.00 | 2020/10/23 | AMC                   | 新規作成
//================================================================================
 */

export const env = {
	namespaced: true,
	state: {
		listWarningLimit: 0,
		listStopLimit: 0
	},
	getters: {
		// 警告表示する一覧件数を取得します
		getListWarningLimit(state) {
			return state.listWarningLimit;
		},
		// 検索中断する一覧件数を取得します
		getListStopLimit(state) {
			return state.listStopLimit;
		}
	},
	mutations: {
		// 環境変数を設定します
		setEnvParams(state, params) {
			state.listWarningLimit = params.listWarningLimit;
			state.listStopLimit = params.listStopLimit;
		},
	},
	actions: {
	},
	modules: {
	}
}