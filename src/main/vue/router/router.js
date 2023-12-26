/**
//================================================================================
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// JavaScriptName:router
//
// <<Modification History>>
// Version  | Date       | Updated By            | Content
// ---------+------------+-----------------------+--------------------------------
// 01.00.00 | 2022/10/28 |  AMC                  | 新規作成
//================================================================================
 */
import { createRouter, createWebHashHistory } from "vue-router";
import store from "@/store/store.js";
import SystemError from "@/views/common/SystemError.vue";

import { VIEWS, PATH_SYSTEMERROR } from "@/const/route.js";
import * as Store from "@/const/store.js";

import * as sys from "./sys.js";
import * as ext from "./ext.js";
import * as nks from "./nks.js";
import * as skc from "./skc.js";
import * as csk from "./csk.js";
import * as msk from "./msk.js";

// Route定義
let routes = new Array();

// sys メニュー
for (let i = 0; i < sys.sysRoutes.length; i++) {
  sys.sysRoutes[i].meta = { group: "業務管理" };
  routes.push(sys.sysRoutes[i]);
}

// ext 供給仕入
for (let i = 0; i < ext.extRoutes.length; i++) {
  ext.extRoutes[i].meta = { group: "供給仕入" };
  routes.push(ext.extRoutes[i]);
}

// nks 農産企画支援
for (let i = 0; i < nks.nksRoutes.length; i++) {
  nks.nksRoutes[i].meta = { group: "農産企画支援" };
  routes.push(nks.nksRoutes[i]);
}

// skc 惣菜加工センター
for (let i = 0; i < skc.skcRoutes.length; i++) {
  skc.skcRoutes[i].meta = { group: "惣菜加工センター" };
  routes.push(skc.skcRoutes[i]);
}

// csk コープ九州商管
for (let i = 0; i < csk.cskRoutes.length; i++) {
  csk.cskRoutes[i].meta = { group: "コープ九州商管" };
  routes.push(csk.cskRoutes[i]);
}

// msk 業務管理
for (let i = 0; i < msk.mskRoutes.length; i++) {
  msk.mskRoutes[i].meta = { group: "マスタ" };
  routes.push(msk.mskRoutes[i]);
}

// その他
routes.push({
  path: PATH_SYSTEMERROR,
  name: "SYSTEMERROR",
  component: SystemError,
});

// Create
const router = createRouter({
  history: createWebHashHistory(process.env.BASE_URL),
  routes,
});

//
router.beforeEach(function (to, from, next) {
  // 遷移先がシステムエラー画面の場合
  if (to.path === PATH_SYSTEMERROR) {
    // ダイアログフラグを非表示
    store.commit(Store.STORE_DIALOG_DIALOG_FLG, false);
  }

  // 画面遷移
  next();
});

// 画面遷移確定後処理
router.afterEach(function (to /*, from*/) {
  // url に対応する画面名を取得
  let title = "画面名";
  for (let i = 0; i < VIEWS.length; i++) {
    if (VIEWS[i].name == to.name) {
      title = VIEWS[i].title;
      break;
    }
  }

  // 初期化
  //-- 画面ID
  store.commit(Store.STORE_VIEW_NAME, to.name);
  //-- 画面名
  store.commit(Store.STORE_VIEW_TITLE, title);
  //-- 変更フラグ
  store.commit(Store.STORE_VIEW_CHANGED, false);
  //-- 処理中フラグ
  store.commit(Store.STORE_VIEW_PROGRESS, false);
  //-- エラー内容
  if (to.path != PATH_SYSTEMERROR) {
    // 遷移先がシステムエラー画面以外の場合のみ
    store.commit(Store.STORE_ERROR_CLEAR);
  }

  // 履歴更新
  store.commit(Store.STORE_VIEW_UPDATE_HISTORY, to);
});

export default router;
