/**
//================================================================================
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// JavaScriptName:dateUtility
//
// <<Modification History>>
// Version  | Date       | Updated By            | Content
// ---------+------------+-----------------------+--------------------------------
// 01.00.00 | 2022/10/28 |  AMC                  | 新規作成
//================================================================================
 */

/*
 * 日付文字列('yyyy/MM/dd')、または、タイムスタンプ文字列('yyyy/MM/dd HH:mm:ss.SSS')を
 * 日付オブジェクトに変換する
 * @param {String} dateString 日付文字列 or タイムスタンプ文字列
 * @return {Object} 日付オブジェクト(Date)
 */
export function co_toDate(dateString) {
	let date;
	if (dateString.length == 10) {
		// 日付文字列の場合
		date = new Date(dateString);
	} else {
		// 上記以外(タイムスタンプ文字列)の場合
		let dateStrings = dateString.split('.');
		date = new Date(dateStrings[0]);
		date.setMilliseconds(dateStrings[1]);
	}
	return date;
}

/*
 * 日付オブジェクトを指定フォーマットの日付文字列に変換する
 * @param {Object} date   日付オブジェクト(Date)
 * @param {String} format フォーマット(yyyy/MM/dd HH:mm:ss.SSS)
 * @return {String} 日付文字列
 */
export function co_formatDate(date, format) {
	let dateString;
	dateString = format.replace(/yyyy/g, date.getFullYear());
	dateString = dateString.replace(/MM/g, ('0' + (date.getMonth() + 1)).slice(-2));
	dateString = dateString.replace(/dd/g, ('0' + date.getDate()).slice(-2));
	dateString = dateString.replace(/HH/g, ('0' + date.getHours()).slice(-2));
	dateString = dateString.replace(/mm/g, ('0' + date.getMinutes()).slice(-2));
	dateString = dateString.replace(/ss/g, ('0' + date.getSeconds()).slice(-2));
	dateString = dateString.replace(/SSS/g, ('00' + date.getMilliseconds()).slice(-3));
	return dateString;
}

/*
 * 日付オブジェクトを日付文字列に変換する
 * @param {Object} date 日付オブジェクト(Date)
 * @return {String} 日付文字列
 */
export function co_toDateString(date) {
	return co_formatDate(date, 'yyyy/MM/dd');
}

/*
 * 日付オブジェクトをタイムスタンプ文字列に変換する
 * @param {Object} date 日付オブジェクト(Date)
 * @return {String} タイムスタンプ文字列
 */
export function co_toTimestampString(date) {
	return co_formatDate(date, 'yyyy/MM/dd HH:mm:ss.SSS');
}
