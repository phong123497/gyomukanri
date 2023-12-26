/**
//================================================================================
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// JavaScriptName:user
//
// <<Modification History>>
// Version  | Date       | Updated By            | Content
// ---------+------------+-----------------------+--------------------------------
// 01.00.00 | 2022/10/28 |  AMC                  | 新規作成
//================================================================================
 */

// 利用者情報
export const user = {
	namespaced: true,
	state: {
		tansCd: null,
		tansNm: null,
		ctrlKbn: null,
		seikyCd: null,
		tempoCd: null,
		toriCd: null,
		menuPtnCd: null,
	},
	getters: {
		// 利用者情報を取得
		getInfo(state) {
			return state;
		}
	},
	mutations: {
		// 利用者情報を設定
		setInfo(state, info) {
			state.tansCd = info.tansCd;
			state.tansNm = info.tansNm;
			state.ctrlKbn = info.ctrlKbn;
			state.seikyCd = info.seikyCd;
			state.tempoCd = info.tempoCd;
			state.toriCd = info.toriCd;
			state.menuPtnCd = info.menuPtnCd;
		},
	},
	actions: {
	},
	modules: {
	}
}