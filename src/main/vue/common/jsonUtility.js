/**
//================================================================================
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// JavaScriptName:jsonUtility
//
// <<Modification History>>
// Version  | Date       | Updated By            | Content
// ---------+------------+-----------------------+--------------------------------
// 01.00.00 | 2022/12/28 |  AMC                  | 新規作成
//================================================================================
 */

/*
 * コピー先(to)にあるプロパティのみコピー元からコピー先のフォームにコピーする
 * ※注意事項：深い階層のコピーには、対応していない
 * @param {Object} from コピー元オブジェクト
 * @param {Object} to   コピー先オブジェクト
 */
export function co_copyToForm(from, to) {
	for (let toKey in to) {
		to[toKey] = from[toKey];
	}
}

/*
 * コピー元(from)のすべてのプロパティをコピー先のフォームデータにコピーする
 * ※注意事項：深い階層のコピーには、対応していない
 * @param {Object} from コピー元オブジェクト
 * @param {Object} to   コピー先フォームデータ(FormData)
 */
export function co_copyToFormData(from, to) {
	for (let fromKey in from) {
		to.append(fromKey, from[fromKey]);
	}
}

/*
 * コピー元(source)のすべてのプロパティを新しいオブジェクトにコピーする
 * ※注意事項：深い階層のコピーには、対応していない
 * @param {Object} source コピー元オブジェクト(配列または連想配列)
 */
export function co_cloneObject(source) {

	if (Array.isArray(source)) {

		let clone = [];
		for (let i = 0; i < source.length; i++) {
			clone.push(co_cloneObject(source[i]));
		}
		return clone;

	} else {

		let clone = {};
		for (let key in source) {
			if (Array.isArray(source[key])) {
				clone[key] = co_cloneObject(source[key]);
			} else {
				clone[key] = source[key];
			}
		}
		return clone;
	}
}
