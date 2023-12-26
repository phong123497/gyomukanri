//================================================================================
// ID: PSKC030
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Rskc03001ListItem
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/11 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.entity.skc;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import coop.kyushu.common.validation.Digits;
import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【惣菜加工センター】製造計画数入力出力エンティティ
 * </pre>
 */
@Getter
@Setter
public class Rskc03001ListItem implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 商品 */
	private String shin;

	/** 商品名 */
	private String shinNm;

	/** 規格 */
	private String stdd;

	/** 納品日（月） */
	private String delvdayMon;

	/** 計画数（月） */
	@Digits(integer = 3)
	@Max(value = 500)
	@Min(value = 0)
	private BigDecimal planSuMon;

	/** 納品数（月） */
	@Digits(integer = 3)
	@Max(value = 500)
	@Min(value = 0)
	private BigDecimal delvSuMon;

	/** 納品日（火） */
	private String delvdayTue;

	/** 計画数（火） */
	@Digits(integer = 3)
	@Max(value = 500)
	@Min(value = 0)
	private BigDecimal planSuTue;

	/** 納品数（火） */
	@Digits(integer = 3)
	@Max(value = 500)
	@Min(value = 0)
	private BigDecimal delvSuTue;

	/** 納品日（水） */
	private String delvdayWed;

	/** 計画数（水） */
	@Digits(integer = 3)
	@Max(value = 500)
	@Min(value = 0)
	private BigDecimal planSuWed;

	/** 納品数（水） */
	@Digits(integer = 3)
	@Max(value = 500)
	@Min(value = 0)
	private BigDecimal delvSuWed;

	/** 納品日（木） */
	private String delvdayThu;

	/** 計画数（木） */
	@Digits(integer = 3)
	@Max(value = 500)
	@Min(value = 0)
	private BigDecimal planSuThu;

	/** 納品数（木） */
	@Digits(integer = 3)
	@Max(value = 500)
	@Min(value = 0)
	private BigDecimal delvSuThu;

	/** 納品日（金） */
	private String delvdayFri;

	/** 計画数（金） */
	@Digits(integer = 3)
	@Max(value = 500)
	@Min(value = 0)
	private BigDecimal planSuFri;

	/** 納品数（金） */
	@Digits(integer = 3)
	@Max(value = 500)
	@Min(value = 0)
	private BigDecimal delvSuFri;

	/** 納品日（土） */
	private String delvdaySat;

	/** 計画数（土） */
	@Digits(integer = 3)
	@Max(value = 500)
	@Min(value = 0)
	private BigDecimal planSuSat;

	/** 納品数（土） */
	@Digits(integer = 3)
	@Max(value = 500)
	@Min(value = 0)
	private BigDecimal delvSuSat;

	/** 納品日（日） */
	private String delvdaySun;

	/** 計画数（日） */
	@Digits(integer = 3)
	@Max(value = 500)
	@Min(value = 0)
	private BigDecimal planSuSun;

	/** 納品数（日） */
	@Digits(integer = 3)
	@Max(value = 500)
	@Min(value = 0)
	private BigDecimal delvSuSun;
}
