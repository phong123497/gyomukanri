/**
//================================================================================
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// JavaScriptName: sys
//
// <<Modification History>>
// Version  | Date       | Updated By            | Content
// ---------+------------+-----------------------+--------------------------------
// 01.00.00 | 2023/01/20 |  AMC                  | 新規作成
//================================================================================
 */
import * as Const from '@/const/route.js';

import PSYS020 from '@/views/sys/Psys020.vue';
import PSYS030 from '@/views/sys/Psys030.vue';

// Route定義
export const sysRoutes = [
	{
		path: Const.PATH_PSYS020,
		name: 'PSYS020',
		component: PSYS020,
	},
	{
		path: Const.PATH_PSYS030,
		name: 'PSYS030',
		component: PSYS030,
	},

]
