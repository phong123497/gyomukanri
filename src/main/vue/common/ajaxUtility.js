/**
//================================================================================
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// JavaScriptName:ajaxUtility
//
// <<Modification History>>
// Version  | Date       | Updated By            | Content
// ---------+------------+-----------------------+--------------------------------
// 01.00.00 | 2022/10/28 | AMC                   | 新規作成
//================================================================================
 */
import axios from 'axios';
import router from '@/router/router.js';
import store from '@/store/store.js';
import { co_getMessage } from '@/common/messageUtility';
import { co_isEmpty } from '@/common/checkUtility';

import { API_ERROR_LOG, PATH_SYSTEMERROR } from '@/const/route.js';
import { STORE_ERROR_ADDMSG, STORE_ERROR_GET_ERRMSG,STORE_ERROR_CLEAR, STORE_AJAX_HAS_AJAX_COMPLETED, STORE_AJAX_CLEAR_AJAX_COUNT, STORE_ERROR_SET_POPUP_FLG } from '@/const/store.js';
import { CMS0038E, CMS0039E, CMS0043E, CMS0044E, CMS0045E } from '@/const/messageId.js';

/*
 * GET（取得）通信処理
 * @param {String} url 通信先URL
 * @param {Object} data リクエストデータ
 * @param {Object} callback コールバック関数（option）
 * @return {Object} レスポンスデータ（エラーの場合は、null）
 */
export async function co_get(url, data, callback) {

	try {
		
		let config = co_getConfig();
		config['params'] = data;
		const res = await axios.get(url, config);

		return co_handleOk(res, callback);

	} catch (error) {

		return co_handleError(error, callback);

	}

}

/*
 * POST（登録）通信処理
 * @param {String} url 通信先URL
 * @param {Object} data リクエストデータ
 * @param {Object} callback コールバック関数（option）
 * @return {Object} レスポンスデータ（エラーの場合は、null）
 */
export async function co_post(url, data, callback) {

		
		
	try {
		let config = co_getConfig();
		const res = await axios.post(url, data, config);

		return co_handleOk(res, callback);

	} catch (error) {

		return co_handleError(error, callback);

	}

}

/*
 * POST（登録）通信処理（ファイル送信）
 * @param {String} url 通信先URL
 * @param {Object} data リクエストデータ
 * @param {Object} callback コールバック関数（option）
 * @return {Object} レスポンスデータ（エラーの場合は、null）
 */
export async function co_postFile(url, data, callback) {

	try {
		let config = co_getConfig();
		config.headers['content-type'] = 'multipart/form-data';

		const res = await axios.post(url, data, config);

		return co_handleOk(res, callback);

	} catch (error) {

		return co_handleError(error, callback);

	}

}

/*
 * PUT（更新）通信処理
 * @param {String} url 通信先URL
 * @param {Object} data リクエストデータ
 * @param {Object} callback コールバック関数（option）
 * @return {Object} レスポンスデータ（エラーの場合は、null）
 */
export async function co_put(url, data, callback) {



	try {
		let config = co_getConfig();
		const res = await axios.put(url, data, config);

		return co_handleOk(res, callback);

	} catch (error) {

		return co_handleError(error, callback);

	}

}

/*
 * DELET（削除）通信処理
 * @param {String} url 通信先URL
 * @param {Object} callback コールバック関数（option）
 * @return {Object} レスポンスデータ（エラーの場合は、null）
 */
export async function co_delete(url, callback) {



	try {
		let config = co_getConfig();
		const res = await axios.delete(url, config);

		return co_handleOk(res, callback);

	} catch (error) {

		return co_handleError(error, callback);

	}

}

/*
 * ファイルダウンロード通信処理
 * @param {String} url 通信先URL
 * @param {Object} data リクエストデータ
 * @param {Object} callback コールバック関数（option）
 * @param {String} fileName ファイル名（option）
 */
export async function co_downloadFile(url, data, callback, fileName) {


	try {
		let config = co_getConfig();
		config.responseType = 'blob';


		const downloadHandle = function(res2) {

			// 出力ファイル名
			if ( co_isEmpty(fileName) ) {
				const filenameRegex = /filename[^;=\n]*=((['"]).*?\2|[^;\n]*)/; // 正規表現でfilenameを抜き出す
				const matches = filenameRegex.exec(res2.headers['content-disposition']);
				if (matches != null && matches[1]) {
					const name = matches[1].replace(/['"]/g, '');
					fileName = decodeURI(name) // 日本語対応
				}
			}

			// 保存 OR 開く
			let blob = new Blob( [res.data], {type: res.headers['content-type']});
			// IE
			if (window.navigator.msSaveBlob) {
				navigator.msSaveBlob(blob, fileName);
			}
			// Chrome & Edge
			if (window.webkitURL && window.webkitURL.createObjectURL) {
				let a = document.createElement('a');
				a.download = fileName;
				a.href = window.webkitURL.createObjectURL(blob);
				a.click();
				a.remove();
			}
		};

		const res = await axios.post(url, data, config);

		if ( callback ) {

			return callback(res, downloadHandle);

		} else {

			downloadHandle(res);
		}

	} catch (error) {

		// BlobのエラーメッセージをJSONに変換
		if ( error.response && error.response.data instanceof Blob ) {

			error.response.data = await new Promise(function(resolve) {
				const reader = new FileReader();
				reader.onload = function() {
					resolve(JSON.parse(reader.result));
				}
				reader.readAsText(error.response.data);
			});
		}

		return co_handleError(error, callback);

	}

}

/*
 * ajax通信時に設定するconfig情報の取得
 */
function co_getConfig() {

	let headers = new Object();
	let config = new Object();
	
	const csrfToken = document.getElementsByName('_csrf')[0].content;
	const csrfHeader = document.getElementsByName('_csrf_header')[0].content;

	config['headers'] = headers;
	config['withCredentials'] = true;
	headers[csrfHeader] = csrfToken;
	headers['SPA-URL'] = router.fullPath;
	
	return config;
}

/*
 * ステータスOK時処理
 */
function co_handleOk(res, callback) {

	const okHandle = function(res2) {

		return res2.data;
	};

	if ( callback ) {

		return callback(res, okHandle);

	} else {

		return okHandle(res);
	}
}

/*
 * ステータスエラー時処理
 */
function co_handleError(error, callback) {

	let status = NaN;
	if ( error.response ) {
		status = parseInt(error.response.status, 10);
	} else {
		throw error;
	}

	const errorHandle = function() {

		let msgList = [];
		if ( error.response ) {
			if (Array.isArray(error.response.data)) {
				for ( let i = 0; i < error.response.data.length; i++ ) {
					if (error.response.data[i].msgId == "CMS0038E" && co_getMessage(error.response.data[i].msgId, error.response.data[i].msgParams) == "CMS0038E") {
						msgList.push(CMS0038E);
					} else if (error.response.data[i].msgId == "CMS0039E" && co_getMessage(error.response.data[i].msgId, error.response.data[i].msgParams) == "CMS0039E") {
						msgList.push(CMS0039E);
					} else {
						msgList.push(co_getMessage(error.response.data[i].msgId, error.response.data[i].msgParams));
					}
				}
			} else {
				// 予期しないエラー(※レスポンスが想定外なHTMLなども含む)
				msgList.push(CMS0043E);
			}

		} else if ( error.code === 'ECONNABORTED' ) {
			// タイムアウト
			msgList.push(CMS0044E);

		} else {
			// ネットワークエラー
			msgList.push(CMS0045E);

		}

		// エラー画面に遷移
		co_moveSystemError(msgList);

		// 例外にて処理終了とする
		throw new Error();
	};

	if ( callback && !isNaN(status) && status != 403 && status < 500 ) {

		return callback(error.response, errorHandle);

	} else {

		errorHandle();
	}

	return null;

}

/*
 * システムエラー画面へ遷移
 */
function co_moveSystemError(msgList) {

	// エラーメッセージ設定
	store.commit(STORE_ERROR_SET_POPUP_FLG, false);
	store.commit(STORE_ERROR_CLEAR);
	for ( let i = 0; i < msgList.length; i++ ) {
		store.commit(STORE_ERROR_ADDMSG, msgList[i]);
	}

	// エラー画面に遷移
	router.push({ path: PATH_SYSTEMERROR });

}

/*
 * エラーログ出力処理
 * @param {Object} err エラー内容
 */
export async function co_outputErrorLog(err) {
	
	if (co_isEmpty(store.getters[STORE_ERROR_GET_ERRMSG])){
	
		let errInfo = {
			'errorMessage' : err
		};

		// エラーログ出力
		await co_post(API_ERROR_LOG, errInfo);

		// エラー画面に遷移
		co_moveSystemError([co_getMessage('CMS0043E')]);
	}

}

/*
 * 業務エラーとなったコンポーネントのエラーを設定
 * @param {Object} errorItems 業務エラー項目のリスト
 * @param {Object} refs ref属性によって登録されたDOM要素や子コンポーネントの参照
 */
export function co_setError(errorItems, refs) {

	if ( Array.isArray(errorItems) ) {

		for ( let i = 0; i < errorItems.length; i++ ) {
			// エラーメッセージ設定
			store.commit(STORE_ERROR_ADDMSG, co_getMessage(errorItems[i].msgId, errorItems[i].msgParams));
			// エラーフラグ設定
			if (errorItems[i].errorItems) {
				for ( let j = 0; j < errorItems[i].errorItems.length; j++ ) {
					if (Object.keys(refs[errorItems[i].errorItems[j]]).indexOf("setError") != -1) {
						refs[errorItems[i].errorItems[j]].setError();
					} else {
						refs[errorItems[i].errorItems[j]][0].setError();
					}
					
				}
			}
		}

	}

}

/*
 * すべてのAjax通信が完了するまで待機処理
 * ※子コンポーネントのレンダリングが始まった後の使用
 *   を想定している
 *   無限待機とならないよう最大10分間までの待機とする
 */
export async function co_waitAjaxAll() {
	const msec = 100;
	const maxCount = (10 * 60 * 1000) / msec;
	for ( let i = 1; i < maxCount; i++ ) {
		if (store.getters[STORE_AJAX_HAS_AJAX_COMPLETED]) {
			break;
		} else {
			await co_sleep(msec);
		}
	}
	store.commit(STORE_AJAX_CLEAR_AJAX_COUNT);
}

/*
 * スリープ処理
 * @param {Number} msec 待機時間(ミリ秒)
 * @return {Object} プロミス
 */
function co_sleep(msec) {
	return new Promise(function(resolve) {
			setTimeout(resolve, msec);
		});
}
