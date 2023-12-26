/**
//================================================================================
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// JavaScriptName:data
//
// <<Modification History>>
// Version  | Date       | Updated By            | Content
// ---------+------------+-----------------------+--------------------------------
// 01.00.00 | 2020/10/02 | AMC                   | 新規作成
//================================================================================
 */

// 画面データ
export const data = {
	namespaced: true,
	state: {
		dataArray: [{}],
		inheritData: {},
		shibuCdOfDashboard: ''
	},
	getters: {
		// 画面IDごとにデータObjectを保持します
		getData(state) { return function(id) {
				for ( var i = 0; i < state.dataArray.length; i++ ) {
					if ( state.dataArray[i].id === id ) {
						return JSON.parse(JSON.stringify(state.dataArray[i].data));
					}
				}
				return null;
			}
		},
		// 指定した引き継ぎデータを取得します
		getInheritData(state) {
			return function(item) {
				let clone = JSON.parse(JSON.stringify(state.inheritData));
				return clone[item];
			}
		},
	},
	mutations: {
		// 全てのデータを削除
		deleteAll(state) {
			state.dataArray.splice(0);
		},
		// 指定された画面IDのデータを削除
		deleteAt(state, id) {
			for ( var i = 0; i < state.dataArray.length; i++ ) {
				if ( state.dataArray[i].id === id ) {
					state.dataArray.splice(i, 1);
				}
			}
		},
		// データを保持
		addData(state, {id, data}) {
			var hasData = false;
			let clone = JSON.parse(JSON.stringify(data));

			for ( var i = 0; i < state.dataArray.length; i++ ) {
				if ( state.dataArray[i].id === id ) {
					state.dataArray[i].data = clone;
					hasData = true;
				}
			}
			if ( !hasData ) {
				state.dataArray.push({id:id, data:clone});
			}
		},
		/** 全ての引き継ぎデータを削除 */
		deleteInheritDataAll(state) {
			state.inheritData = {};
		},
		/**
		 * 指定された項目のデータを削除
		 * @param state 状態
		 * @param item 項目
		 */
		deleteInheritDataAt(state, item) {
			if(state.inheritData) {
				delete state.inheritData[item];
			}
		},
		/**
		 * 引き継ぎデータを追加する
		 * @param state 状態
		 * @param data 引き継ぎデータ
		 */
		addInheritData(state, data) {
			let clone = JSON.parse(JSON.stringify(data));
			for(let item in clone) {
				state.inheritData[item] = clone[item];
			}
		},
	},
	actions: {
	},
	modules: {
	}
}