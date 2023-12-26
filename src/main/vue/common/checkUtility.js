/**
//================================================================================
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// JavaScriptName:checkUtility
//
// <<Modification History>>
// Version  | Date       | Updated By            | Content
// ---------+------------+-----------------------+--------------------------------
// 01.00.00 | 2022/10/28 |  AMC                  | 新規作成
//================================================================================
 */
 import Encoding from 'encoding-japanese';
 

/*
 * 文字数チェック
 * @param {String} str 対象の文字列
 * @param {Number} limit 最大文字数
 * @return {Boolean} true：最大文字数以下／false：最大文字数オーバー
 */
export function co_checkLength(str, limit) {

	if ( co_stringToArray(str).length <= limit ) {
		return true;
	}
	return false;
}

/*
 * 桁数一致チェック
 * @param {String} str 対象文字列
 * @param {Number} maxLength 最大桁数
 * @return {Boolean} true：文字数が一致 / false：文字数が不一致
 */
export function co_checkDigitEqualed( str, maxLength ) {

	if( str != null ) {
		if ( co_stringToArray( str ).length == maxLength ) {
			return true;
		}
	}

	return false;

}

/*
 * 文字列をサロゲート文字の配列(Array)に変換
 * @param {String} str 対象の文字列
 * @return {Object} 変換後の文字配列
 */
function co_stringToArray (str) {
    return str.match(/[\uD800-\uDBFF][\uDC00-\uDFFF]|[^\uD800-\uDFFF]/g) || [];
}

/*
 * 文字列(String)、または、配列(Array)の空チェック
 * undefined、null、長さ0の文字列、配列を空と判断します
 * ※Boolean、Number、Symbol、Object、Date、Functionなどで使用してはいけません
 * @param {String | Array} val 対象の文字列、または、配列
 * @return {Boolean} true：空／false：空ではない
 */
export function co_isEmpty( val ) {

	if ( val === undefined || val === null || val.length === 0 ) {
		return true;
	}
	return false;
}

/*
 * 半角数字チェック
 * @param {String} str 対象の文字列
 * @return {Boolean} true：文字列が半角数字のみ／false：文字列が半角数字のみではない
 */
export function co_checkHalfNumber( str ) {

	if ( str.match(/^[0-9]+$/) ) {
		return true;
	}
	return false;
}

/*
 * SJIS変換可否判定(半角英数記号・全角英数記号・全角カタカナ・全角ひらがな・漢字（第一水準・第二水準）は変換可能する。)
 * @param {String} 文字列
 * @return {Boolean} true：SJIS変換可能／false:SJIS変換不可
 */
 export function co_canConvertSJIS( str ) {
	for (var i = 0; i < str.length; i++) {
		let char = str[i] 
		let orgcodeArray = Encoding.stringToCode(char);
		let targetCodeArray = Encoding.convert(orgcodeArray, {to:"SJIS", from:"UNICODE"});
		
		let targetCode = 0;
		if (targetCodeArray.length == 1 ) {
			// 半角文字
			targetCode = targetCodeArray[0].toString(16);
		} else {
			// 全角文字
			targetCode = (targetCodeArray[0].toString(16) + targetCodeArray[1].toString(16));
		}
		targetCode = "0x" + targetCode.toUpperCase()
		
		
		// 半角文字（『?』以外）
		if ((0x20 <= targetCode && targetCode <= 0x3D) || (0x40 <= targetCode && targetCode <= 0x7E)) {
			continue;
		}
		// 半角文字『?』について
		if (targetCode == 0x3F) {
			// 入力文字が『?』であればOK
			if (char == "?") {
				continue;
			} 
		}

		// 半角カナOK
		if (0xA1 <= targetCode && targetCode <= 0xDF) {
			continue;
		}
		// 全角非漢字OK
		if (0x8140 <= targetCode && targetCode <= 0x84BE) {
			continue;	
		}
		// 特殊文字OK(㈱、①、②...等々)
		if (0x8740 <= targetCode && targetCode <= 0x879C) {
			continue;	
		}
		// 第一水準OK
		if (0x889F <= targetCode && targetCode <= 0x9872) {
			continue;
		}
		// 第二水準OK
		if ((0x989F <= targetCode && targetCode <= 0x9FFC) || (0xE040 <= targetCode && targetCode <= 0xEAA4)) {
			continue;
		}

		// NG
		return false;
	}
	
	return true;
}