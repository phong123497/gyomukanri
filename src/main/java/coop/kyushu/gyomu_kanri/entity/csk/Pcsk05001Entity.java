//================================================================================
// ID: PCSK050
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pcsk05001Entity
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/18 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.entity.csk;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【コープ九州商管】センター商管表出力エンティティ
 * </pre>
 */
@Getter
@Setter
public class Pcsk05001Entity implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 年月 */
	private String kym;

	/** 開始年月 */
	private String startKym;

	/** 終了年月 */
	private String endKym;

	/** 商品CDヘッダ */
	private String shinHead;

	/** 商品CD */
	private String shinCd;

	/** 商品CDヘッダ */
	private String shinNmHead;

	/** 商品名 */
	private String shinNm;

	/** 分類CD */
	private String catgCd;

	/** 月初棚卸数 */
	private BigDecimal szaiSu;

	/** 月初棚卸額 */
	private BigDecimal szaiGak;

	/** 仕入数 */
	private BigDecimal sireSu;

	/** 仕入額 */
	private BigDecimal sireGak;

	/** 供給数 */
	private BigDecimal kyokSu;

	/** 供給額 */
	private BigDecimal kyokGak;

	/** 廃棄ロス数 */
	private BigDecimal haikSu;

	/** 廃棄ロス額 */
	private BigDecimal haikGak;

	/** 経費振替数 */
	private BigDecimal keihSu;

	/** 経費振替額 */
	private BigDecimal keihGak;

	/** 在庫振替(入)数 */
	private BigDecimal frinSu;

	/** 在庫振替(入)額 */
	private BigDecimal frinGak;

	/** 在庫振替(出)数 */
	private BigDecimal frisSu;

	/** 在庫振替(出)額 */
	private BigDecimal frisGak;

	/** 最終仕入単価 */
	private BigDecimal sireTank;

	/** 理論月末在庫数 */
	private BigDecimal mzaiSu;

	/** 理論月末在庫額 */
	private BigDecimal mzaiGak;

	/** 積送中在庫数 */
	private BigDecimal sekiSu;

	/** 積送中在庫額 */
	private BigDecimal sekiGak;

	/** 理論在庫数 */
	private BigDecimal rironSu;

	/** 理論在庫額 */
	private BigDecimal rironGak;

	/** 実棚数 */
	private BigDecimal hzaiSu;

	/** 実棚額 */
	private BigDecimal hzaiGak;

	/** 実月末在庫数 */
	private BigDecimal jitsugetsumatsuSu;

	/** 実月末在庫額 */
	private BigDecimal jitsugetsumatsuGak;

	/** 不明ロス数 */
	private BigDecimal fumeirosuSu;

	/** 不明ロス額 */
	private BigDecimal fumeirosuGak;

	/** 供給原価計 */
	private BigDecimal kyokGenkaKei;

	/** 供給剰余 */
	private BigDecimal kyokJoYo;

	/** 前年比(％) */
	private BigDecimal zenNenHi;

	/** GPR(％) */
	private BigDecimal gpr;
}
