/**
//================================================================================
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// JavaScriptName:stringUtility
//
// <<Modification History>>
// Version  | Date       | Updated By            | Content
// ---------+------------+-----------------------+--------------------------------
// 01.00.00 | 2022/10/28 |  AMC                  | 新規作成
//================================================================================
 */
import { co_isEmpty } from '@/common/checkUtility';

const halfKatakana = new Array('ｧ','ｨ','ｩ','ｪ','ｫ','ｬ','ｭ','ｮ','ｯ','ｰ','ｳﾞ','ｶﾞ','ｷﾞ','ｸﾞ','ｹﾞ','ｺﾞ','ｻﾞ','ｼﾞ','ｽﾞ','ｾﾞ','ｿﾞ','ﾀﾞ','ﾁﾞ','ﾂﾞ','ﾃﾞ','ﾄﾞ','ﾊﾞ','ﾋﾞ','ﾌﾞ','ﾍﾞ','ﾎﾞ','ﾊﾟ','ﾋﾟ','ﾌﾟ','ﾍﾟ','ﾎﾟ','ｱ','ｲ','ｳ','ｴ','ｵ','ｶ','ｷ','ｸ','ｹ','ｺ','ｻ','ｼ','ｽ','ｾ','ｿ','ﾀ','ﾁ','ﾂ','ﾃ','ﾄ','ﾅ','ﾆ','ﾇ','ﾈ','ﾉ','ﾊ','ﾋ','ﾌ','ﾍ','ﾎ','ﾏ','ﾐ','ﾑ','ﾒ','ﾓ','ﾔ','ﾕ','ﾖ','ﾗ','ﾘ','ﾙ','ﾚ','ﾛ','ﾜ','ｦ','ﾝ');
const fullKatakana = new Array('ァ','ィ','ゥ','ェ','ォ','ャ','ュ','ョ','ッ','ー','ヴ','ガ','ギ','グ','ゲ','ゴ','ザ','ジ','ズ','ゼ','ゾ','ダ','ヂ','ヅ','デ','ド','バ','ビ','ブ','ベ','ボ','パ','ピ','プ','ペ','ポ','ア','イ','ウ','エ','オ','カ','キ','ク','ケ','コ','サ','シ','ス','セ','ソ','タ','チ','ツ','テ','ト','ナ','ニ','ヌ','ネ','ノ','ハ','ヒ','フ','ヘ','ホ','マ','ミ','ム','メ','モ','ヤ','ユ','ヨ','ラ','リ','ル','レ','ロ','ワ','ヲ','ン');

/*
 * 前後の半角空白、全角空白を削除
 * @param {String} value 対象の文字列
 * @return {String} true：トリム処理した文字列
 */
export function co_trim(value) {
	if (co_isEmpty(value)) {
		return value;
	} else {
		return value.replace(/(^[\x20\u3000}]+)|([\x20\u3000]+$)/g, "");
	}
}

/*
 * 半角⇒全角に変換する
 * @param {String} value 半角を含む文字列
 * @return {String} value 全角変換後文字列
 */
export function co_halfStrToFullStr(str) {
	let fullStr = str
	
	// カタカナ
	for (var i = 0; i < halfKatakana.length; i++){
		fullStr = fullStr.replace(new RegExp(halfKatakana[i], 'g'), fullKatakana[i]);
	}

	// 英数記号
	fullStr = fullStr.replace(/[!-~]/g, function(s) {
			return String.fromCharCode(s.charCodeAt(0) + 0xFEE0);});
	fullStr = fullStr.replace(/"/g, "”")
			.replace(/'/g, "’")
			.replace(/`/g, "‘")
			.replace(/\\/g, "￥")
			.replace(/ /g, "　")
			.replace(/~/g, "〜");

	return fullStr
}
 
 
/*
 * 全角⇒半角に変換する
 * @param {String} value 全角を含む文字列
 * @return {String} value 半角変換後文字列
 */
export function co_fullStrToHalfStr(str) {
	let halfStr = str
	
	// カタカナ
	for (var i = 0; i < fullKatakana.length; i++){
		halfStr = halfStr.replace(new RegExp(fullKatakana[i], 'g'), halfKatakana[i]);
	}

	// 英数記号
	halfStr = halfStr.replace(/[！-～]/g, function(s) {
			return String.fromCharCode(s.charCodeAt(0) - 0xFEE0);});
	halfStr = halfStr.replace(/”/g, "\"")
			.replace(/’/g, "'")
			.replace(/‘/g, "`")
			.replace(/￥/g, "\\")
			.replace(/\u{3000}/gu, " ")
			.replace(/〜/g, "~");

	return halfStr;
}