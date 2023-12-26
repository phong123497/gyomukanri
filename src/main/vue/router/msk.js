//================================================================================
// ID: PMSK010
// ProjectName: 【マスタ】名称／区分管理マスタ区分一覧
// SystemName: 名称／区分管理マスタ区分一覧
// ClassName: msk
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// 01.00.00 | 2023/09/14 | ＡＭＣ               | 新規作成
//================================================================================
import * as Const from "@/const/route.js";

import PMSK010 from "@/views/msk/Pmsk010.vue";
import PMSK020 from "@/views/msk/Pmsk020.vue";
import PMSK030 from "@/views/msk/Pmsk030.vue";
import Pmsk040 from "@/views/msk/Pmsk040.vue";
import PMSK050 from "@/views/msk/Pmsk050.vue";
import PMSK051 from "@/views/msk/Pmsk051.vue";

// Route定義
export const mskRoutes = [
  {
    path: Const.PATH_PMSK010,
    name: "PMSK010",
    component: PMSK010,
    //props: true
  },
  {
    path: Const.PATH_PMSK020,
    name: "PMSK020",
    component: PMSK020,
    //props: true
  },
  {
    path: Const.PATH_PMSK030,
    name: "PMSK030",
    component: PMSK030,
    //props: true
  },
  {
    path: Const.PATH_PMSK040,
    name: "Pmsk040",
    component: Pmsk040,
  },
  {
    path: Const.PATH_PMSK050,
    name: "PMSK050",
    component: PMSK050,
  },
  {
    path: Const.PATH_PMSK051,
    name: "PMSK051",
    component: PMSK051,
  },
];
