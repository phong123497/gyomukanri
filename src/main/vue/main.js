import { createApp } from "vue";
import axios from 'axios';
import VueAxios from 'vue-axios';
import App from './App.vue';
import router from '@/router/router.js';
import store from '@/store/store.js';
import VueShortkey from 'vue3-shortkey';
import { co_outputErrorLog } from '@/common/ajaxUtility.js';
import { STORE_AJAX_INCREMENT_AJAX_COUNT, STORE_AJAX_DECREMENT_AJAX_COUNT } from '@/const/store.js';
import { SetupCalendar } from 'v-calendar';

const vm = createApp(App);
vm.use(router);
vm.use(store);
vm.use(VueShortkey);
vm.config.productionTip = false;
vm.use(VueAxios, axios);
vm.use(SetupCalendar, {})

// キーコード設定（※IE11用）
vm.config.keyCodes = {
	f1: 112,
	f2: 113,
	f3: 114,
	f4: 115,
	f5: 116,
	f6: 117,
	f7: 118,
	f8: 119,
	f9: 120,
	f10: 121,
	f11: 122,
	f12: 123
}

// error handling
vm.config.errorHandler = async function(err, info) {
	try {
		// ログ出力＆画面遷移
		await co_outputErrorLog(info + '\n' + err.stack);
	} catch (e) {
		// 処理なし
	}
};
// javaScriptコード内で発生したエラー
window.addEventListener('error', async function(event) {
	try {
		// ログ出力＆画面遷移;
		await co_outputErrorLog(event.error.stack);
	} catch (e) {
		// 処理なし
	}
});
// promiseの処理で発生したエラー
window.addEventListener('unhandledrejection', async function(event) {
	try {
		// ログ出力＆画面遷移
		await co_outputErrorLog(event.reason);
	} catch (e) {
		// 処理なし
	}
});

//ブラウザのヘルプ機能無効化
if(window.onhelp !== undefined) {
	window.onhelp = function(){return false;}
}

// development 向けの処理
if(process.env.NODE_ENV=='development'){

	vm.config.devtools = true;

	axios.interceptors.request.use(function(request) {
		store.commit(STORE_AJAX_INCREMENT_AJAX_COUNT);
		if(console){
			console.log('Request: ', JSON.stringify(request));
		}
		return request;
	});

	axios.interceptors.response.use(function(response) {
		store.commit(STORE_AJAX_DECREMENT_AJAX_COUNT);
		if(console){
			console.log('Response: ', JSON.stringify(response));
		}
		return response;
	}, function(error) {
		store.commit(STORE_AJAX_DECREMENT_AJAX_COUNT);
		throw error;
	});

} else {
	axios.interceptors.request.use(function(request) {
		// リクエスト
		store.commit(STORE_AJAX_INCREMENT_AJAX_COUNT);
		return request;
	});

	axios.interceptors.response.use(function(response) {
		// レスポンス(成功時)
		store.commit(STORE_AJAX_DECREMENT_AJAX_COUNT);
		return response;
	}, function(error) {
		// レスポンス(失敗時)
		store.commit(STORE_AJAX_DECREMENT_AJAX_COUNT);
		throw error;
	});
}


vm.mount('#app');
export default vm;
