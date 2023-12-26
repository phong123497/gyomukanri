/**
//================================================================================
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// JavaScriptName:const
//
// <<Modification History>>
// Version  | Date       | Updated By            | Content
// ---------+------------+-----------------------+--------------------------------
// 01.00.00 | 2022/10/28 |  AMC                  | 新規作成
//================================================================================
 */

export const CD_CONTENT_TYPE = {
	CONTENT : 1,		// コード内容
	SHORTENED_NAME : 2,	// コード略称
	CODENAME : 3		// コード付名称
};

// TEXT INPUT
export const TEXTINPUT_TYPE = {
	ALL : 1,			// 全て
	FULL: 2,			// 全角
	HIRAGANA: 3,		// 全角ひらがな
	HALF: 4,			// 半角
	HALF_ALPHA_NUM: 5,	// 半角英数
	HALF_NUM: 6,		// 半角数字
	HALF_KATAKANA: 7, 	// 半角カタカナ
};

// 数値入力項目の書式
export const NUMBERINPUT_TYPE = {
	INTEGER: 'n',						// 整数値
	NON_COMMA_FULL_INDICATE: 'f',		// 桁区切りなし、かつ指定された小数桁数フル表示（例：1234.50）
	COMMA_FULL_INDICATE: 'F',			// 桁区切りあり、かつ指定された小数桁数フル表示（例：1,234.50）
	NON_COMMA_ZERO_DECIMAL_OMMIT: 'g',	// 桁区切りなし、かつ指定された小数値の末尾が0の場合、省略（例：1234.5）
	COMMA_ZERO_DECIMAL_OMMIT: 'G'		// 桁区切りあり、かつ指定された小数値の末尾が0の場合、省略（例：1,234.5）
}

// DATE INPUT
export const DATEINPUT_TYPE = {
	YMD: 1,				// 年月日
	YM: 2,				// 年月
	DATE_YMD: 3,		// Date型 年月日
	DATE_YM: 4,			// Date型 年月
};

export const DATE_FORMAT = {
	YMD: "YYYYMMDD",
	YMD_OMIT: "YYMMDD",
	YMD_SLASH: "YYYY/MM/DD",
	YM: "YYYYMM",
	YM_OMIT: "YYMM",
	YM_SLASH: "YYYY/MM",
}

export const DATE_PARSE_ERROR = "Invalid Date";

// DIALOG
export const DIALOG_BUTTON_LIST_KEY = {
	OK : 'ok',			// OK
	CANCEL: 'cancel',	// CANCEL
	ESC: 'esc',			// ESC
};

//ROLE BTN
export const ROLEBTN_TYPE = {
	UPD : 1,			// 更新
	GAMEN_PRT: 2,		// 画面印刷
	CSV_OUT: 3,			// CSV出力
};

// ↓ TODO:（削除予定）state.evn を使用ください
// 警告表示する一覧件数
export const LIST_WARN_LIMIT = 5000;
// 検索中断する一覧件数
export const LIST_STOP_LIMIT = 20000;
// ↑ TODO:（削除予定）state.evn を使用ください

// 検索項目型区分
export const KATA_CD = {
	TEXT_PERFECT_MATCH: '01',	// テキストボックス(完全一致)
	TEXT_PARTIAL_MATCH:'02',	// テキストボックス(部分一致)
	LIST_BOX: '03',				// リストボックス
	NUMBER_SINGLE: '04',		// 数値(単一)
	NUMBER_RANGE: '05',			// 数値(範囲)
	DATE_SINGLE: '06',			// 日付(単一)
	DATE_RANGE: '07',			// 日付(範囲)
	RADIO_BUTTON: '08',			// ラジオボタン
	CHECK_BOX: '09',			// チェックボックス
	TREE_VIEW: '10',			// ツリービュー
	HOJIN_NUMBER: '11',			// 法人番号
	TEKIYO_JIGYOSHO_NUM: '12',	// 雇用保険適用事業所番号
	POSTAL_NUM: '13',			// 郵便番号
	PHONE_NUM: '14',			// 電話番号
	FUSEN: '15',				// 付箋
	ADVISOR_SELECT: '16',		// アドバイザー選択
	TEXT_PREFIX_MATCH: '17',	// テキストボックス(前方一致)
	ADV_CODE: '18',				// アドバイザーコード
}

// 項目型区分
export const KOMOKU_KATA_CD = {
	TEXT_ALL: '01',					// 全て
	TEXT_HALF: '02',				// 半角のみ
	TEXT_HALF_ALPHA_NUM: '03',		// 半角英数字のみ
	TEXT_FULL: '04',				// 全角のみ
	NUM_NOT_COMMA_INTEGER: '05',	// 数値（カンマ編集なし）（整数のみ）
	NUM_EXIST_COMMA_INTEGER: '06',	// 数値（カンマ編集あり）（整数のみ）
	NUM_NOT_COMMA_DECIMAL: '07',	// 数値（カンマ編集なし）（小数あり）
	NUM_EXIST_COMMA_DECIMAL: '08',	// 数値（カンマ編集あり）（小数あり）
	DATE_YMD: '09',					// 日付（年月日）
	DATE_YM: '10',					// 日付（年月）
	CODE: '11',						// コード(数字のみ)
	TEXT_HALF_NUM: '12',			// 半角数字のみ
	ANOTHER: '99'					// その他
}

// 桁数
//--アドバイザーコード
export const MAX_LENGTH_ADVISOR_CODE = 4;
//--郵便番号
export const MAX_LENGTH_POSTAL_CODE = 8;
//--電話番号
export const MAX_LENGTH_PHONE_NUMBER = 15;

// HTTPステータスコード
export const HTTP_STATUS = {
	OK: '200',						// リクエストは成功しレスポンスとともに要求に応じたリソースが返される
	CREATED: '201',					// リクエストは完了し新たにリソースが作成、更新された
	NO_CONTENT: '204',				// リクエストを受理したが、返すべきレスポンスエンティティが存在しない場合に返される
	BAD_REQUEST: '400',				// バリデーションエラー、存在確認チェックのエラーとなった場合に返される
	UNAUTHORIZED: '401',			// 認証時に認証エラーとなった場合に返される
	FORBIDDEN: '403',				// リソースへのアクセス権限がない場合に返される
	NOT_FOUND: '404',				// リソースが見つからなかった (ページが見つからなかった)
	CONFLICT: '409',				// 作成しようとしたリソースが既にある場合に返される。削除しようとしたリソースがロック中(悲観)の場合に返される
	INTERNAL_SERVER_ERROR: '500'	// サーバ内部にエラーが発生した場合に返される。主にシステムエラーとなる
}

// 必須チェックの種類
export const REQUIRED_TYPE = {
	ALL : 1,			// すべて必須  (From、To)
	ONE : 2				// 片方のみ必須(From、To)
};

// 範囲チェックの種類
export const RANGE_TYPE = {
	NONE : 1,			// 範囲チェックなし
	EQUAL_OK : 2,		// 同値をエラーとしない
	EQUAL_NG : 3		// 同値をエラーとする
};

export const FILE_SIZE = {
	ONE_MB : 1048576	// 1MByte
};

// 業務定数↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
// レコード区分
export const REC_KB = {
	BUMON: 			'E7',					// 部門コード
	TEMPOBUNRUI:	'E8',					// 店舗分類コード
	TEMPOBUNRUIDAI: 'E81',					// 店舗分類コード大分類
	TEMPOBUNRUICHU: 'E82',					// 店舗分類コード中分類
	TEMPOBUNRUISHO: 'E83',					// 店舗分類コード小分類
	SHOMIKIGEN:		'S4',					// 賞味期間区分
	BUMONBETU: 		'Z7',					// 部門別管理
};

// 区分コード
export const CD_SBT = {
	BUNRUI: 'E7',							// 分類
	TORIHIKIKBN: '93',						// 伝票取引区分
};
// 処理種別
export const SHORISBT = {
	ADD_CD   : '1',							// 追加CD
	ADD_NAME : '1:追加',					// 追加名称
	UPD_CD   : '2',							// 変更
	UPD_NAME : '2:変更',					// 変更名称
	DEL_CD   : '3',							// 削除
	DEL_NAME : '3:削除',					// 削除名称
	REF_CD   : '4',							// 索引
	REF_NAME : '4:索引',					// 索引名称

	TEI_CD : '2',							// 訂正CD
	TEI_NAME : '2:訂正',					// 訂正名称
	TAX_CD : '6',							// 税率変更CD
	TAX_NAME : '6:税率変更',				// 税率変更名称

	UPDATE_CD : '2',					// 訂正CD
	TAXUPDATE_CD : '6',					// 税率変更CD
	UPDATE_NAME : '2:訂正',				// 訂正名称
	TAXUPDATE_NAME : '6:税率変更'		// 税率変更名称
};
// 取引区分
export const TORIKBN = {
	SHIIRE : '20',								// 仕入れ
	HENPIN : '21',								// 返品
	SHIIRENEMASHI:'22',							// 仕入値増
	SHIIRENEBIKI:'23',							// 仕入値引
	REBATE:'24',								// リベート
	GENKINSHIIREOK:'26',						// 現金仕入が入力可
	SHIIRE_NAME : '仕入',						// 仕入れ名
	HENPIN_NAME : '返品',						// 返品名
	SHIIRENEMASHI_NAME:'仕入値増',				// 仕入値増名
	SHIIRENEBIKI_NAME:'仕入値引',				// 仕入値引名
	REBATE_NAME:'リベート',						// リベート
	GENKINSHIIREOK_NAME:'現金仕入が入力可'		// 現金仕入が入力可名
	
};
// 訂正区分
export const TEISEIKUBUN = {
	TA_CD : '0',						// TACD
	NOTA_CD : '1',						// 非TACD
	TA_NAME : '0:ＴＡ',					// TA名称
	NOTA_NAME : '1:非ＴＡ'				// 非TA名称
	
};
// 伝票即時発行区分
export const DEMPYOHAKKOKBN = {
	HAKO_CD : '0',								// 発行CD
	NOHAKO_CD : '1',							// 発行なしCD
	HAKO_NAME : '0:発行する',						// 発行名称
	NOHAKO_NAME : '1:発行しない'					// 発行なし名称
};

// 権限区分
export const KENGENKBN = {
	NONE : 0,					// 無
	READ : 1,					// 参照
	UPDATE : 2,					// 更新
	DELETE : 3					// 削除
};

// 編集画面に処理種別
export const CNS_SHORISBT = {
	CNS_SHORISBT_ADD : '1',							// 追加
	CNS_SHORISBT_UPD : '2',							// 変更
	CNS_SHORISBT_DEL : '3',							// 削除
	CNS_SHORISBT_SEL : '4',							// 索引
	CNS_SHORISBT_ADD_NAME : '追加',					// 追加名
	CNS_SHORISBT_UPD_NAME : '変更',					// 変更名
	CNS_SHORISBT_DEL_NAME : '削除',					// 削除名
	CNS_SHORISBT_SEL_NAME : '索引',					// 索引名
};

// 【農産企画支援】業務定数↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
//納品時容器(02)
export const CNS_NKS_NOHINYOKI = {
	CNS_NKS_NOHINYOKI_KOTENA : '1',							// 納品時容器_コンテナ
	CNS_NKS_NOHINYOKI_DANBORU : '2',						// 納品時容器_ダンボール
	CNS_NKS_NOHINYOKI_EPS : '3',							// 納品時容器_発泡スチロール
	CNS_NKS_NOHINYOKI_KOTENA_NAME : 'コンテナ',				// 納品時容器_コンテナ名
	CNS_NKS_NOHINYOKI_DANBORU_NAME : 'ダンボール',			// 納品時容器_ダンボール名
	CNS_NKS_NOHINYOKI_EPS_NAME : '発泡スチロール',			// 納品時容器_発泡スチロール名
};
// 印刷帳票（企画案内書）(03)
export const CNS_NKS_PRINT_KBN = {
	CNS_NKS_PRINT_KBN_KIKAKU : '1',						 		// 印刷帳票_企画案内書（計画）
	CNS_NKS_PRINT_KBN_KAKAKU : '2',						 		// 印刷帳票_価格決定依頼書（確定）
	CNS_NKS_PRINT_KBN_KIKAKU_NAME : '企画案内書（計画）',		// 印刷帳票_企画案内書（計画）名
	CNS_NKS_PRINT_KBN_KAKAKU_NAME : '価格決定依頼書（確定）',	// 印刷帳票_価格決定依頼書（確定）名
	CNS_NKS_PRINT_KBN_KIKAKU_RM : '計画',						// 印刷帳票_企画案内書（計画）略名
	CNS_NKS_PRINT_KBN_KAKAKU_RM : '確定',						// 印刷帳票_価格決定依頼書（確定）略名
};
// 加工区分(05)
export const CNS_NKS_KAKO_KBN = {
	CNS_NKS_KAKO_KBN_ARI : '0', 								// 加工区分_加工無し
	CNS_NKS_KAKO_KBN_NASHI : '1',								// 加工区分_加工有り
	CNS_NKS_KAKO_KBN_ARI_NAME : '加工無し',						// 加工区分_加工無し名
	CNS_NKS_KAKO_KBN_NASHI_NAME : '加工有り',					// 加工区分_加工有り名
};
// 週・特価フラグ (10)
export const CNS_NKS_TOKUKA_KBN = {
	CNS_NKS_TOKUKA_KBN_TSUJYO : '0',							// 週・特価フラグ_通常
	CNS_NKS_TOKUKA_KBN_TOKUKA : '1',							// 週・特価フラグ_特価
	CNS_NKS_TOKUKA_KBN_TSUJYO_NAME : '通常',					// 週・特価フラグ_通常名
	CNS_NKS_TOKUKA_KBN_TOKUKA_NAME : '特価',					// 週・特価フラグ_特価名
};
// 権限(21)
export const CNS_NKS_KENGEN = {
	CNS_NKS_KENGEN_HONSYA : '0',								// 権限_本社
	CNS_NKS_KENGEN_FUKUREN : '1',								// 権限_ふくれん
	CNS_NKS_KENGEN_ZENTSU : '2',								// 権限_全通
	CNS_NKS_KENGEN_HONSYA_NAME : '本部',						// 権限_本社名
	CNS_NKS_KENGEN_FUKUREN_NAME : 'ふくれん',					// 権限_ふくれん名
	CNS_NKS_KENGEN_ZENTSU_NAME : '全通',						// 権限_全通名
};
// センター区分(22)
export const CNS_NKS_SENTA_KBN = {
	CNS_NKS_SENTA_KBN_SASAKURI : '1',							// センター区分_篠栗
	CNS_NKS_SENTA_KBN_AIRA : '2',								// センター区分_姶良
	CNS_NKS_SENTA_KBN_SASAKURI_NAME : '篠栗農産加工センター',	// センター区分_篠栗名
	CNS_NKS_SENTA_KBN_AIRA_NAME : '姶良農産加工センター',		// センター区分_姶良名
	CNS_NKS_SENTA_KBN_SASAKURI_CODE : '9530',					// センター区分_篠栗センターコード
	CNS_NKS_SENTA_KBN_AIRA_CODE : '9531',						// センター区分_姶良センターコード
};
