/**
//================================================================================
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// JavaScriptName: cn
//
// <<Modification History>>
// Version  | Date       | Updated By            | Content
// ---------+------------+-----------------------+--------------------------------
// 01.00.00 | 2022/11/25 |  AMC                  | 新規作成
//================================================================================
 */
import * as Const from '@/const/route.js';

import PEXT020 from '@/views/ext/Pext020.vue';

// Route定義
export const extRoutes = [
	{
		path: Const.PATH_PEXT020,
		name: 'PEXT020',
		component: PEXT020,
	},
]

