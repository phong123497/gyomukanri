//================================================================================
// ID: PNKS040
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks040ListItem
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/13 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.entity.nks;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【農産企画支援】企画案内書印刷エンティティ
 * </pre>
 */
@Getter
@Setter
public class Pnks040ListItem implements Serializable {
	private static final long serialVersionUID = 1L;

	/** メーカーコード */
	private String makerCd;

	/** ＣＱ企画番号 */
	private String cqProjNo;

	/** 共同購入商品コード */
	private String kydKonyShinCd;

	/** 等階級コード */
	private String touClassCd;

	/** 産地コード */
	private String santiCd;

	/** 企画年月回 */
	private String projYearMonthTimes;

	/** ＣＱ商品コード */
	private String cqShinCd;

	/** ＪＡＮコード */
	private String janCd;

	/** 商品名漢字 */
	private String shinNmKnji;

	/** 規格名漢字 */
	private String stddNmKnji;

	/** メーカー名称 */
	private String makerNm;

	/** ＦＡＸ */
	private String fax;

	/** 等階級名称 */
	private String tokaikyNm;

	/** 産地名称 */
	private String santiNm;

	/** 加工区分 */
	private String kakoKbn;

	/** 加工区分名 */
	private String kakoKbnNm;

	/** 週・特価 */
	private String weekTokka;

	/** 集品区分 */
	private String collectKbn;

	/** 集品区分名 */
	private String collectKbnNm;

	/** エフ */
	private String fcp;

	/** さが */
	private String sag;

	/** ララ */
	private String lala;

	/** おおいた */
	private String oit;

	/** 水光社 */
	private String suiko;

	/** みやざき */
	private String myz;

	/** かごしま */
	private String kag;

	/** おきなわ */
	private String okn;

	/** CNTJIGKB */
	private String cntjigkb;
}
