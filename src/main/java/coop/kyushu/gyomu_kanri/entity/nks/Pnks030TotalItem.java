//================================================================================
// ID: PNKS030
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks030TotalItem
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/10 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.entity.nks;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【農産企画支援】農産企画情報入力エンティティ
 * </pre>
 */
@Getter
@Setter
public class Pnks030TotalItem implements Serializable {
	private static final long serialVersionUID = 1L;


	/** SKU */
	private BigDecimal sku;

	/** 計画点数 */
	private BigDecimal planPoint;

	/** CQ供給額 */
	private BigDecimal cqSuplyGaku;

	/** CQGRP */
	private BigDecimal cqGrp;

	/** エフ供給額 */
	private BigDecimal fcpSuplyGaku;

	/** エフGRP */
	private BigDecimal fcpGrp;

	/** 部門コード */
	private String bmnCd;

	/** 部門名 */
	private String bmnNm;

	/** CQ原価 */
	private BigDecimal genTan;

	/** CQ売価 */
	private BigDecimal baiTan;
}
