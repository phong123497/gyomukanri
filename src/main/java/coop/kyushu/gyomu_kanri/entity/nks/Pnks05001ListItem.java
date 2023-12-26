//================================================================================
// ID: PNKS050
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks05001ListItem
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/26 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.entity.nks;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【農産企画支援】価格表印刷エンティティ
 * </pre>
 */
@Getter
@Setter
public class Pnks05001ListItem implements Serializable {
	private static final long serialVersionUID = 1L;
	/**  ヘーダ部*/
	/** 企画年月回_年 */
	private String sekikanoY;

	/** 企画年月回_月 */
	private String sekikanoM;

	/** 企画年月回_周 */
	private String sekikanoT;

	/** 部門名 */
	private String bumonkj;

	/** 部門コード */
	private String bumonCd;
	/** 明細部*/

	/** 通番 */
	private String seicd;

	/** 企画週 */
	private String cqkikano;

	/** 企画 Ｎｏ */
	private String kyshinCd;

	/** 商品コード */
	private String cqshinCd;

	/** ＪＡＮコード */
	private String janCd;

	/** 表示規格 */
	private String kikakkj;

	/** 商品名 */
	private String shinkj;

	/** 加工区分 */
	private String kakoKbn;

	/** 原単価 */
	private BigDecimal gentan;

	/** 売単価 */
	private BigDecimal baitan;

	/** 価格 */
	private BigDecimal zenukumi;

	/** 税込価 */
	private BigDecimal kumitan;

	/** ＧＰＲ */
	private BigDecimal gpr;

	/** 特価フラグ */
	private String kikakb;

	/** メーカーコード */
	private String makerCd;

	/** メーカー */
	private String makerkj;

	/** 集品区分 */
	private String syuhinkb;

	/** 産地コード */
	private String santiCd;

	/** 産地名 */
	private String santikj;

	/** 入数 */
	private String irisu;

	/** エフ */
	private String ehu;

	/** さが */
	private String saga;

	/** ララ */
	private String rara;

	/** おおいた */
	private String oita;

	/** くまもと */
	private String suikosya;

	/** みやざき */
	private String miyazaki;

	/** かごしま */
	private String kagosima;

	/** おきなわ */
	private String okinawa;

	/** 金額表示フラグ */
	private String tankflg;

	/** 備考 */
	private String biko;
}
