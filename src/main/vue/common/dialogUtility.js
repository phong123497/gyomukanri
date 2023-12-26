/**
//================================================================================
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// JavaScriptName:dialogUtility
//
// <<Modification History>>
// Version  | Date       | Updated By            | Content
// ---------+------------+-----------------------+--------------------------------
// 01.00.00 | 2022/10/28 |  AMC                  | 新規作成
//================================================================================
 */

import store from '@/store/store.js';

import { DIALOG_BUTTON_LIST_KEY } from '@/const/const.js';
import { STORE_DIALOG_MESSAGE, STORE_DIALOG_BUTTON_LIST, STORE_DIALOG_FOCUS_KEY, STORE_DIALOG_CALL_BACK,
			STORE_DIALOG_DIALOG_FLG
		} from '@/const/store.js';

/* 通知用ダイアログ
 * @param {String} message 出力するメッセージ
 * @param {Object} callback コールバック関数
 */
export function co_notice(message, callback) {

	let buttonList = [
		{'key':DIALOG_BUTTON_LIST_KEY.OK, 'label':'はい'}
	];

	// メッセージ、ボタン、フォーカス、コールバック関数の設定
	store.commit(STORE_DIALOG_MESSAGE, message);
	store.commit(STORE_DIALOG_BUTTON_LIST, buttonList);
	store.commit(STORE_DIALOG_FOCUS_KEY, DIALOG_BUTTON_LIST_KEY.OK);
	store.commit(STORE_DIALOG_CALL_BACK, callback);

	// ダイアログを呼び出す
	store.commit(STORE_DIALOG_DIALOG_FLG, true);
}

/* 確認用ダイアログ
 * @param {String} message 出力するメッセージ
 * @param {Object} callback コールバック関数
 */
export function co_confirm(message, callback) {

	let buttonList = [
		{'key':DIALOG_BUTTON_LIST_KEY.OK, 'label':'はい'},
		{'key':DIALOG_BUTTON_LIST_KEY.CANCEL, 'label':'いいえ'}
	];

	// メッセージ、ボタン、フォーカス、コールバック関数の設定
	store.commit(STORE_DIALOG_MESSAGE, message);
	store.commit(STORE_DIALOG_BUTTON_LIST, buttonList);
	store.commit(STORE_DIALOG_FOCUS_KEY, DIALOG_BUTTON_LIST_KEY.OK);
	store.commit(STORE_DIALOG_CALL_BACK, callback);

	// ダイアログを呼び出す
	store.commit(STORE_DIALOG_DIALOG_FLG, true);
}

/* 警告用ダイアログ
 * @param {String} message 出力するメッセージ
 * @param {Object} callback コールバック関数
 */
export function co_alert(message, callback) {

	let buttonList = [
		{'key':DIALOG_BUTTON_LIST_KEY.OK, 'label':'はい'},
		{'key':DIALOG_BUTTON_LIST_KEY.CANCEL, 'label':'いいえ'}
	];

	// メッセージ、ボタン、フォーカス、コールバック関数の設定
	store.commit(STORE_DIALOG_MESSAGE, message);
	store.commit(STORE_DIALOG_BUTTON_LIST, buttonList);
	store.commit(STORE_DIALOG_FOCUS_KEY, DIALOG_BUTTON_LIST_KEY.OK);
	store.commit(STORE_DIALOG_CALL_BACK, callback);

	// ダイアログを呼び出す
	store.commit(STORE_DIALOG_DIALOG_FLG, true);
}

