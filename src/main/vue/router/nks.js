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
import * as Const from '@/const/route.js';

import PNKS010 from '@/views/nks/Pnks010.vue';
import PNKS020 from '@/views/nks/Pnks020.vue';
import PNKS030 from '@/views/nks/Pnks030.vue';
import PNKS040 from '@/views/nks/Pnks040.vue';
import PNKS050 from '@/views/nks/Pnks050.vue';
import PNKS060 from '@/views/nks/Pnks060.vue';
import PNKS070 from '@/views/nks/Pnks070.vue';
import PNKS080 from '@/views/nks/Pnks080.vue';
import PNKS090 from '@/views/nks/Pnks090.vue';
import PNKS100 from '@/views/nks/Pnks100.vue';
import PNKS110 from '@/views/nks/Pnks110.vue';
import PNKS120 from '@/views/nks/Pnks120.vue';
import PNKS130 from '@/views/nks/Pnks130.vue';
import PNKS140 from '@/views/nks/Pnks140.vue';
import PNKS150 from '@/views/nks/Pnks150.vue';

// Route定義
export const nksRoutes = [
	{
		path:Const.PATH_PNKS010,
		name:'PNKS010',
		component:PNKS010,
	},
	{
		path:Const.PATH_PNKS020,
		name:'PNKS020',
		component:PNKS020,
	},
	{
		path:Const.PATH_PNKS030,
		name:'PNKS030',
		component:PNKS030,
	},
	{
		path:Const.PATH_PNKS040,
		name:'PNKS040',
		component:PNKS040,
	},
	{
		path:Const.PATH_PNKS050,
		name:'PNKS050',
		component:PNKS050,
	},
	{
		path:Const.PATH_PNKS060,
		name:'PNKS060',
		component:PNKS060,
	},
	{
		path:Const.PATH_PNKS070,
		name:'PNKS070',
		component:PNKS070,
	},
	{
		path:Const.PATH_PNKS080,
		name:'PNKS080',
		component:PNKS080,
	},
	{
		path:Const.PATH_PNKS090,
		name:'PNKS090',
		component:PNKS090,
	},
	{
		path:Const.PATH_PNKS100,
		name:'PNKS100',
		component:PNKS100,
	},
	{
		path:Const.PATH_PNKS110,
		name:'PNKS110',
		component:PNKS110,
	},
	{
		path:Const.PATH_PNKS120,
		name:'PNKS120',
		component:PNKS120,
	},
	{
		path:Const.PATH_PNKS130,
		name:'PNKS130',
		component:PNKS130,
	},
	{
		path:Const.PATH_PNKS140,
		name:'PNKS140',
		component:PNKS140,
	},
	{
		path:Const.PATH_PNKS150,
		name:'PNKS150',
		component:PNKS150,
	},
]

