/**
//================================================================================
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// JavaScriptName:unloadEventUtility
//
// <<Modification History>>
// Version  | Date       | Updated By            | Content
// ---------+-----------+-----------------------+--------------------------------
// 01.00.00 | 2022/10/28 |  AMC                  | 新規作成
//================================================================================
 */

import { co_get } from '@/common/ajaxUtility.js';

import { PATH_LOGOUT } from '@/const/route.js';

/*
 * ログアウト処理を実行する
 */
export function co_doLogout() {

	// ブラウザの確認ダイアログを表示したくないため、
	// 同期をとらずにログアウト処理を実行する
	co_get(PATH_LOGOUT);

}


