/**
//================================================================================
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// JavaScriptName:messageUtility
//
// <<Modification History>>
// Version  | Date       | Updated By            | Content
// ---------+------------+-----------------------+--------------------------------
// 01.00.00 | 2022/10/28 |  AMC                  | 新規作成
//================================================================================
 */

import store from '@/store/store.js';

import { STORE_MSG_GET_MSG } from '@/const/store.js';

/*
 * メッセージIDからメッセージ文字列を取得
 * @param {String} msgId メッセージID
 * @param {String | Array} 置換文字
 * @return {String} メッセージ
 */
export function co_getMessage(msgId) {

	let msg = store.getters[STORE_MSG_GET_MSG](msgId);

	if ( arguments.length == 2 && Array.isArray(arguments[1]) ) {
		// 第2引数が配列の場合
		for (let i = 0; i < arguments[1].length; i++) {
			msg = msg.replace(new RegExp('[@]' + (i+1), 'g'), arguments[1][i]);
		}

	} else {
		// 第2引数以降の引数が置換文字の場合
		for (let i = 1; i < arguments.length; i++) {
			msg = msg.replace(new RegExp('[@]' + i, 'g'), arguments[i]);
		}
	}

	return msg;
}


