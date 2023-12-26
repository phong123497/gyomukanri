/**
//================================================================================
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// JavaScriptName: nks
//
// <<Modification History>>
// Version  | Date       | Updated By            | Content
// ---------+------------+-----------------------+--------------------------------
// 01.00.00 | 2023/07/06 |  AMC                  | 新規作成
//================================================================================
 */
import * as Const from '@/const/route.js'

import PSKC010 from '@/views/skc/Pskc010.vue';
import PSKC020 from '@/views/skc/Pskc020.vue'
import PSKC030 from '@/views/skc/Pskc030.vue';

export const skcRoutes = [
	{
		path:Const.PATH_PSKC010,
		name:'PSKC010',
		component:PSKC010,
	},
	{
		path: Const.PATH_PSKC020,
		name: 'PSKC020',
		component: PSKC020
	},
	{
		path:Const.PATH_PSKC030,
		name:'PSKC030',
		component:PSKC030,
	},
]
