/**
//================================================================================
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// JavaScriptName: cn
//
// <<Modification History>>
// Version  | Date       | Updated By            | Content
// ---------+------------+-----------------------+--------------------------------
// 01.00.00 | 2022/10/28 |  AMC                  | 新規作成
//================================================================================
 */
import * as Const from '@/const/route.js';

import Olcn01020m from '@/views/cn/Olcn01020m.vue';

// Route定義
export const cnRoutes = [
	{
		path: Const.PATH_OLCN01020M,
		name: 'OLCN01020M',
		component: Olcn01020m,
		//props: true
	},
]

