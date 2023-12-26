/**
//================================================================================
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// JavaScriptName:shoriShubetsuUtility
//
// <<Modification History>>
// Version  | Date       | Updated By            | Content
// ---------+------------+-----------------------+--------------------------------
// 01.00.00 | 2023/08/01 |  AMC                  | 新規作成
//================================================================================
 */
import { KENGENKBN, SHORISBT } from '@/const/const.js';

/*
 * 処理種別リスト生成  ※Default
 *   1:追加
 *   2:変更
 *   3:削除
 *   4:索引
 * @param {String} kengen 権限
 * @return {List} shoriShubetsuList：処理種別リスト
 */
export function co_getShoriShubetsu(kengen) {

	let shoriShubetsuList = [];
	
	if (kengen >= KENGENKBN.UPDATE) {
		
		shoriShubetsuList.push({code: SHORISBT.ADD_CD, name: SHORISBT.ADD_NAME});
		shoriShubetsuList.push({code: SHORISBT.UPD_CD, name: SHORISBT.UPD_NAME});

	}
	if (kengen >= KENGENKBN.DELETE) {
		
		shoriShubetsuList.push({code: SHORISBT.DEL_CD, name: SHORISBT.DEL_NAME});
	}
	if (kengen >= KENGENKBN.READ) {
		
		shoriShubetsuList.push({code: SHORISBT.REF_CD, name: SHORISBT.REF_NAME});
	}

	return shoriShubetsuList;

}

/*
 * 処理種別リスト生成  ※追加・変更のみ
 *   1:追加
 *   2:変更
 * @param {String} kengen 権限
 * @return {List} shoriShubetsuList：処理種別リスト
 */
export function co_getShoriShubetsuAU(kengen) {

	let shoriShubetsuList = [];
	
	if (kengen >= KENGENKBN.UPDATE) {
		
		shoriShubetsuList.push({code: SHORISBT.ADD_CD, name: SHORISBT.ADD_NAME});
		shoriShubetsuList.push({code: SHORISBT.UPD_CD, name: SHORISBT.UPD_NAME});

	}

	return shoriShubetsuList;

}

/*
 * 処理種別リスト生成  ※変更・索引のみ
 *   2:変更
 *   4:索引
 * @param {String} kengen 権限
 * @return {List} shoriShubetsuList：処理種別リスト
 */
export function co_getShoriShubetsuUR(kengen) {

	let shoriShubetsuList = [];
	
	if (kengen >= KENGENKBN.UPDATE) {
		
		shoriShubetsuList.push({code: SHORISBT.UPD_CD, name: SHORISBT.UPD_NAME});

	}
	if (kengen >= KENGENKBN.READ) {
		
		shoriShubetsuList.push({code: SHORISBT.REF_CD, name: SHORISBT.REF_NAME});
	}

	return shoriShubetsuList;

}

/*
 * 処理種別リスト生成  ※変更のみ
 *   2:変更
 * @param {String} kengen 権限
 * @return {List} shoriShubetsuList：処理種別リスト
 */
export function co_getShoriShubetsuU(kengen) {

	let shoriShubetsuList = [];
	
	if (kengen >= KENGENKBN.UPDATE) {
		
		shoriShubetsuList.push({code: SHORISBT.UPD_CD, name: SHORISBT.UPD_NAME});

	}

	return shoriShubetsuList;

}

/*
 * 処理種別リスト生成（AUT）
 *   1:追加
 *   2:訂正
 *   6:税率変更
 * @param {String} kengen 権限
 * @return {List} shoriShubetsuList：処理種別リスト
 */
export function co_getShoriShubetsuAUT(kengen) {

	let shoriShubetsuList = [];
	
	if (kengen >= KENGENKBN.UPDATE) {
		
		shoriShubetsuList.push({code: SHORISBT.ADD_CD, name: SHORISBT.ADD_NAME});
		shoriShubetsuList.push({code: SHORISBT.TEI_CD, name: SHORISBT.TEI_NAME});
		shoriShubetsuList.push({code: SHORISBT.TAX_CD, name: SHORISBT.TAX_NAME});

	}

	return shoriShubetsuList;

}



