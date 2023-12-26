/**
//================================================================================
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// JavaScriptName: csk
//
// <<Modification History>>
// Version  | Date       | Updated By            | Content
// ---------+------------+-----------------------+--------------------------------
// 01.00.00 | 2023/07/06 |  AMC                  | 新規作成
//================================================================================
 */
import * as Const from '@/const/route.js';

import PCSK010 from '@/views/csk/Pcsk010.vue';
import PCSK020 from '@/views/csk/Pcsk020.vue';
import PCSK040 from '@/views/csk/Pcsk040.vue';
import PCSK050 from '@/views/csk/Pcsk050.vue';
import PCSK060 from '@/views/csk/Pcsk060.vue';

// Route定義
export const cskRoutes = [
	{
		path: Const.PATH_PCSK010,
		name: 'PCSK010',
		component: PCSK010
	},
	{
		path: Const.PATH_PCSK020,
		name: 'PCSK020',
		component: PCSK020
	},
	{
		path: Const.PATH_PCSK040,
		name: 'PCSK040',
		component: PCSK040
	},
	{
		path: Const.PATH_PCSK050,
		name: 'PCSK050',
		component: PCSK050
	},
	{
		path: Const.PATH_PCSK060,
		name: 'PCSK060',
		component: PCSK060,
	},
]

