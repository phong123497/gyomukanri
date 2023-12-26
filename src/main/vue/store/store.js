/**
//================================================================================
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// JavaScriptName:store
//
// <<Modification History>>
// Version  | Date       | Updated By            | Content
// ---------+------------+-----------------------+--------------------------------
// 01.00.00 | 202/10/28 |  AMC                  | 新規作成
//================================================================================
 */

import { createStore } from 'vuex';

import {error} from './error.js';
import {info} from './info.js';
import {view} from './view.js';
import {data} from './data.js';
import {dialog} from './dialog.js';
import {message} from './message.js';
import {user} from './user.js';
import {menuptn} from './menuptn.js';
import {env} from './env.js';
import {ajax} from './ajax.js';

export default createStore({
	state: {
		count: 0
	},
	getters: {
	},
	mutations: {
	},
	actions: {
	},
	modules: {
		error,
		info,
		view,
		data,
		dialog,
		message,
		user,
		menuptn,
		env,
		ajax
    }
})