/**
//================================================================================
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// JavaScriptName:keyEventUtility
//
// <<Modification History>>
// Version  | Date       | Updated By            | Content
// ---------+------------+-----------------------+--------------------------------
// 01.00.00 | 2022/10/28 |  AMC                  | 新規作成
//================================================================================
 */

/*
 * 親への伝播を阻止する
 * @param {Object} e イベント対象要素
 * @param {Object} keyCodes 対象となるキーコード（配列）
 */
export function co_stopPropagation(e, keyCodes) {
	for(let i = 0; i < keyCodes.length; i++) {
		if(e.keyCode == keyCodes[i]) {
			e.stopPropagation();
		}
	}
}


