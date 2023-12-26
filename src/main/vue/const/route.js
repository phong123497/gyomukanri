/**
//================================================================================
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// JavaScriptName:route
//
// <<Modification History>>
// Version  | Date       | Updated By            | Content
// ---------+------------+-----------------------+--------------------------------
// 01.00.00 | 2022/10/28 |  AMC                  | 新規作成
//================================================================================
 */

// URL
// --ベース
export const BASE_URL = "/" + new String(location.pathname).split("/")[1];
// --共通処理
export const COMMON = "/common";
// --コンポーネント
export const COMPONENTS = "/components";

// -- システム共通
export const SYS = "/sys";

// --供給仕入
export const EXT = "/ext";
// --農産企画支援
export const NKS = "/nks";
// --惣菜加工センター
export const SKC = "/skc";
// --コープ九州商管
export const CSK = "/csk";
// -- 業務管理
export const MSK = "/msk";
//
export const MST = "/mst";

// 共通
export const API_ERROR_LOG = BASE_URL + COMMON + "/errorLogs";
export const API_LIST_ITEMS = BASE_URL + COMPONENTS + "/listItems";
export const API_RADIO_ITEMS = BASE_URL + COMPONENTS + "/radioItems";
export const API_CHECKBOX_ITEMS = BASE_URL + COMPONENTS + "/checkBoxItems";
export const API_TREE_VIEW_SHIBU_ITEMS =
  BASE_URL + COMPONENTS + "/treeViewShibuItems";
export const API_TREE_VIEW_SANGYO_ITEMS =
  BASE_URL + COMPONENTS + "/treeViewSangyoItems";
export const API_ADVISOR_NAME = BASE_URL + COMPONENTS + "/advisorName";
export const API_FUSEN_ITEMS = BASE_URL + COMPONENTS + "/fusenListItems";
export const API_SEARCH_CONDITIONS =
  BASE_URL + COMPONENTS + "/searchConditions";
export const API_SEARCH_ITEM_VALUES =
  BASE_URL + COMPONENTS + "/searchItemValues";
export const API_SEARCH_ITEMS = BASE_URL + COMPONENTS + "/searchItems";
export const API_MSTMEISM_INPUT = BASE_URL + COMPONENTS + "/mstmeismInput";
export const API_DAIMSTMEISM_INPUT =
  BASE_URL + COMPONENTS + "/daiMstmeismInput";

export const API_MESSAGE_INFO = BASE_URL + COMMON + "/messages";
export const API_USER_INFO = BASE_URL + COMMON + "/tantoshaInfo";
export const API_KENGEN_INFO = BASE_URL + COMMON + "/kengenInfo";
export const API_ENV_PARAMS = BASE_URL + COMMON + "/envParams";

export const API_MENUPATTERN_INFO = BASE_URL + COMMON + "/menuPatternInfo";

// ログイン
export const PATH_LOGIN = BASE_URL + "/login/init";
// ログアウト
export const PATH_LOGOUT = BASE_URL + "/logout";

// システムエラー
export const PATH_SYSTEMERROR = BASE_URL + "/systemError";

// メニュー
export const PATH_PSYS020 = BASE_URL + SYS + "/psys020/init";

//パスワード変更
export const PATH_PSYS030 = BASE_URL + SYS + "/psys030/init";
export const PATH_PSYS030_EDIT = BASE_URL + SYS + "/psys030/edit";
export const PATH_PSYS030_EDIT_CHECK = BASE_URL + SYS + "/psys030/edit/check";

// 仕入訂正入力
export const PATH_PEXT020 = BASE_URL + EXT + "/pext020/init";
export const PATH_PEXT020_SEARCH = BASE_URL + EXT + "/pext020/search/dempyo";
export const PATH_PEXT020_LIST = BASE_URL + EXT + "/pext020/list";
export const PATH_PEXT020_ADD = BASE_URL + EXT + "/pext020/add";
export const PATH_PEXT020_SEARCH_SHOHIN =
  BASE_URL + EXT + "/pext020/search/shohin";
export const PATH_PEXT020_TORIHIKISAKI =
  BASE_URL + EXT + "/pext020/search/torihikisaki";
export const PATH_PEXT020_JIGYOSHO =
  BASE_URL + EXT + "/pext020/search/jigyosho";
export const PATH_PEXT020_TUKADELVPLACE =
  BASE_URL + EXT + "/pext020/search/tsuka-nohin-basho";
export const PATH_PEXT020_UPDATEDEMPYOCHECK =
  BASE_URL + EXT + "/pext020/updateDempyoCheck";

// 農産企画支援
// PEACEデータ取込
export const PATH_PNKS010 = BASE_URL + NKS + "/pnks010/init";
export const PATH_PNKS010_READCSV = BASE_URL + NKS + "/pnks010/readcsv";
// 農産企画情報・商品検索
export const PATH_PNKS020 = BASE_URL + NKS + "/pnks020/init";
export const PATH_PNKS020_SEARCH = BASE_URL + NKS + "/pnks020/search";
export const PATH_PNKS020_SEARCH_CHECK =
  BASE_URL + NKS + "/pnks020/search/check";
// 農産企画情報入力
export const PATH_PNKS030 = BASE_URL + NKS + "/pnks030/init";
export const PATH_PNKS030_SAVE = BASE_URL + NKS + "/pnks030/save";
export const PATH_PNKS030_SEARCH_GETSANTICBOX =
  BASE_URL + NKS + "/pnks030/search/get-santi-cbox";
export const PATH_PNKS030_SEARCH_GETTOKAIKYCBOX =
  BASE_URL + NKS + "/pnks030/search/get-tokaiky-cbox";
export const PATH_PNKS030_SEARCH_GETNEXTSHIN =
  BASE_URL + NKS + "/pnks030/search/get-next-shin";
export const PATH_PNKS030_SEARCH_GETBFSHIN =
  BASE_URL + NKS + "/pnks030/search/get-bf-shin";
// 企画案内書印刷
export const PATH_PNKS040 = BASE_URL + NKS + "/pnks040/init";
export const PATH_PNKS040_SEARCH_CHANGECQSTART =
  BASE_URL + NKS + "/pnks040/search/change-cq-start";
export const PATH_PNKS040_PNKS04001PRINT_KEKAKU =
  BASE_URL + NKS + "/pnks040/pnks04001print/kekaku-print";
export const PATH_PNKS040_PNKS04001PRINT_KAKUTE =
  BASE_URL + NKS + "/pnks040/pnks04001print/kakute-print";
// 価格表印刷
export const PATH_PNKS050 = BASE_URL + NKS + "/pnks050/init";
export const PATH_PNKS050_PNKS05001PRINT =
  BASE_URL + NKS + "/pnks050/pnks05001print";
// シミュレーション用データ出力
export const PATH_PNKS060 = BASE_URL + NKS + "/pnks060/init";
export const PATH_PNKS060_OUTPUT = BASE_URL + NKS + "/pnks060/output";
// 価格取込用データ出力
export const PATH_PNKS070 = BASE_URL + NKS + "/pnks070/init";
export const PATH_PNKS070_OUTPUT = BASE_URL + NKS + "/pnks070/output";
export const PATH_PNKS070_OUTPUT_CHECK =
  BASE_URL + NKS + "/pnks070/outputcheck";
// バッチ情報一覧
export const PATH_PNKS080 = BASE_URL + NKS + "/pnks080/init";
export const PATH_PNKS080_DELETE = BASE_URL + NKS + "/pnks080/delete";
// バッチ情報メンテナンス
export const PATH_PNKS090 = BASE_URL + NKS + "/pnks090/init";
export const PATH_PNKS090_SAVE = BASE_URL + NKS + "/pnks090/save";
// バッチ計画表印刷
export const PATH_PNKS100 = BASE_URL + NKS + "/pnks100/init";
export const PATH_PNKS100_PNKS10001PRINT =
  BASE_URL + NKS + "/pnks100/pnks10001print";
// 調達加工計画書印刷
export const PATH_PNKS110 = BASE_URL + NKS + "/pnks110/init";
export const PATH_PNKS110_PNKS11001PRINT =
  BASE_URL + NKS + "/pnks110/pnks11001print";
// 各種データ出力
export const PATH_PNKS120 = BASE_URL + NKS + "/pnks120/init";
export const PATH_PNKS120_OUTPUT = BASE_URL + NKS + "/pnks120/output";
// メーカーマスタ一覧
export const PATH_PNKS130 = BASE_URL + NKS + "/pnks130/init";
export const PATH_PNKS130_SEARCH = BASE_URL + NKS + "/pnks130/search";
export const PATH_PNKS130_SEARCH_CHECK =
  BASE_URL + NKS + "/pnks130/search/check";
export const PATH_PNKS130_SAVE = BASE_URL + NKS + "/pnks130/save";
export const PATH_PNKS130_DELETE = BASE_URL + NKS + "/pnks130/delete";
// メーカーマスタメンテナンス
export const PATH_PNKS140 = BASE_URL + NKS + "/pnks140/init";
export const PATH_PNKS140_SAVE = BASE_URL + NKS + "/pnks140/save";
export const PATH_PNKS140_UPDATE = BASE_URL + NKS + "/pnks140/update";
// 納品指示書発行指示
export const PATH_PNKS150 = BASE_URL + NKS + "/pnks150/init";
export const PATH_PNKS150_PNKS15001PRINT =
  BASE_URL + NKS + "/pnks150/pnks15001print";

// 惣菜加工センター
// 値付実績データ取込
export const PATH_PSKC010 = BASE_URL + SKC + "/pskc010/init";
export const PATH_PSKC010_READCSV = BASE_URL + SKC + "/pskc010/readcsv";

// 製造計画数取込・出力
export const PATH_PSKC020 = BASE_URL + SKC + "/pskc020/init";
export const PATH_PSKC020_OUTPUT_KKNSITEI =
  BASE_URL + SKC + "/pskc020/output/kknsitei";
export const PATH_PSKC020_OUTPUT_WEEKSITEI =
  BASE_URL + SKC + "/pskc020/output/weeksitei";
export const PATH_PSKC020_IMPORT_PLANSU =
  BASE_URL + SKC + "/pskc020/import/plansu";
export const PATH_PSKC020_IMPORT_SHINKAIHAI =
  BASE_URL + SKC + "/pskc020/import/shinkaihai";
// 製造計画数入力
export const PATH_PSKC030 = BASE_URL + SKC + "/pskc030/init";
export const PATH_PSKC030_SEARCH = BASE_URL + SKC + "/pskc030/search";
export const PATH_PSKC030_SAVE = BASE_URL + SKC + "/pskc030/save";
export const PATH_PSKC030_PSKC03001PRINT =
  BASE_URL + SKC + "/pskc030/rskc03001print";

// --コープ九州商管
// CSV取込
export const PATH_PCSK010 = BASE_URL + CSK + "/pcsk040/init";
export const PATH_PCSK010_READCSV = BASE_URL + CSK + "/pcsk010/readcsv";
// CSV出力
export const PATH_PCSK020 = BASE_URL + CSK + "/pcsk020/init";
export const PATH_PCSK020_OUTPUT = BASE_URL + CSK + "/pcsk020/csvoutput";
// 締日登録
export const PATH_PCSK040 = BASE_URL + CSK + "/pcsk040/init";
export const PATH_PCSK040_SEARCH = BASE_URL + CSK + "/pcsk040/search";
export const PATH_PCSK040_UPDATA = BASE_URL + CSK + "/pcsk040/updata";
// センター商管表出力
export const PATH_PCSK050 = BASE_URL + CSK + "/pcsk050/init";
export const PATH_PCSK050_PSKC05001PRINT =
  BASE_URL + CSK + "/pcsk050/pcsk05001print";
// 粗利益管理表出力
export const PATH_PCSK060 = BASE_URL + CSK + "/pcsk060/init";
export const PATH_PCSK060_SEARCH = BASE_URL + CSK + "/pcsk060/search";
export const PATH_PCSK060_PCSK06001PRINTCSV =
  BASE_URL + CSK + "/pcsk060/pcsk06001printcsv";
export const PATH_PCSK060_PCSK06001PRINTPDF =
  BASE_URL + CSK + "/pcsk060/pcsk06001printpdf";
export const PATH_PCSK060_CHANGE_NENDO =
  BASE_URL + CSK + "/pcsk060/change_nendo";
// 画面名
export const VIEWS = [{ name: "SYSTEMERROR", title: "システムエラー" }];
//マスタ区分管理マスタ区分一覧
export const PATH_PMSK010 = BASE_URL + MSK + "/pmsk010/init";
export const PATH_PMSK010_SEARCH = BASE_URL + MSK + "/pmsk010/search";
export const PATH_PMSK010_SEARCH_CHECK = BASE_URL + MSK + "/pmsk010/check";
// 【マスタ】名称／区分管理マスタコード一覧
export const PATH_PMSK020 = BASE_URL + MSK + "/pmsk020/init";
export const PATH_PMSK020_CHECK = BASE_URL + MSK + "/pmsk020/check";
export const PATH_PMSK020_SEARCH = BASE_URL + MSK + "/pmsk020/search";
export const PATH_PMSK020_CHECK_EXIST = BASE_URL + MSK + "/pmsk020/check-exist";
export const PATH_PMSK020_DELETE = BASE_URL + MSK + "/pmsk020/delete";
// 名称／区分管理マスタメンテ画面
export const PATH_PMSK030 = BASE_URL + MSK + "/pmsk030/init";
export const PATH_PMSK030_KUBUN = BASE_URL + MSK + "/pmks030/kubun";
export const PATH_PMSK030_MEISHO = BASE_URL + MSK + "/pmks030/meisho";
export const PATH_PMSK030_ADD_MEISHO = BASE_URL + MSK + "/pmks030/insmeisho";
export const PATH_PMSK030_UPDATE_MEISHO = BASE_URL + MSK + "/pmks030/updmeisho";
export const PATH_PMSK030_ADD_KUBUN = BASE_URL + MSK + "/pmks030/inskubun";
export const PATH_PMSK030_UPDATE_KUBUN = BASE_URL + MSK + "/pmks030/updkubun";

// 企画商品マスタ一覧
export const PATH_PMSK040 = BASE_URL + MSK + "/pmsk040/init";
export const PATH_PMSK040_CHECK = BASE_URL + MSK + "/pmsk040/check";
export const PATH_PMSK040_SEARCH = BASE_URL + MSK + "/pmsk040/search";
// 取引先マスタ一覧画面
export const PATH_PMSK050 = BASE_URL + MSK + "/pmsk050/init";
export const PATH_PMSK050_CHECK = BASE_URL + MSK + "/pmsk050/check";
export const PATH_PMSK050_SEARCH = BASE_URL + MSK + "/pmsk050/search";
export const PATH_PMSK050_OUTPUT = BASE_URL + MSK + "/pmsk050/output";
// 取引先CSV取込
export const PATH_PMSK051 = BASE_URL + MSK + "/pmsk051/init";
export const PATH_PMSK051_READCSV = BASE_URL + MSK + "/pmsk051/readcsv";

// 企画商品マスタメンテ
export const PATH_PMST260 = BASE_URL + NKS + "/pmst260/init";
// 取引先マスタメンテ
export const PATH_PMST120 = BASE_URL + NKS + "/pmst120/init";
