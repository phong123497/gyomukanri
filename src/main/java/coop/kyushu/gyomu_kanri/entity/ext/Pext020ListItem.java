//================================================================================
// ID: PEXT020
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ProgramName: 仕入・訂正入力
// ClassName: Pext020ListItem
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2022/11/28 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.entity.ext;

import java.io.Serializable;
import java.math.BigDecimal;

import coop.kyushu.common.validation.Code;
import coop.kyushu.common.validation.Digits;
import coop.kyushu.common.validation.NotEmpty;
import coop.kyushu.gyomu_kanri.form.ext.Pext020Form.Group;
import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 仕入・訂正入力 一覧用エンティティ
 * </pre>
 */
@Getter
@Setter
public class Pext020ListItem implements Serializable {

	private static final long serialVersionUID = 1L;

	/** NO */
	private BigDecimal no;

	/** ＪＡＮ／商品コード */
	@NotEmpty
	@Code(length = 9)
	private String janShinCd;

	/** 商品コード */
	private String shinCd;

	/** 商品名 */
	private String shinNm;

	/** 規格 */
	private String kikaku;

	/** 入数 */
	@NotEmpty(groups = { Group.Head2.class })
	@Digits(integer = 4, groups = { Group.Head2.class })
	private BigDecimal irisu;

	/** 数量 */
	@NotEmpty(groups = { Group.Head2.class })
	@Digits(integer = 6, fraction = 1)
	private BigDecimal suryo;

	/** 仕入単価 */
	@NotEmpty(groups = { Group.Head2.class })
	@Digits(integer = 9, fraction = 2)
	private BigDecimal sireTan;

	/** 仕入金額 */
	@NotEmpty(groups = { Group.Head2.class })
	@Digits(integer = 9)
	private BigDecimal sireGaku;

	/** 原単価 */
	@NotEmpty(groups = { Group.Head2.class })
	@Digits(integer = 9, fraction = 2)
	private BigDecimal genkaTanka;

	/** 原価金額 */
	@NotEmpty(groups = { Group.Head2.class })
	@Digits(integer = 9)
	private BigDecimal genkaGaku;

	/** 売単価 */
	@NotEmpty(groups = { Group.Head2.class })
	@Digits(integer = 9, fraction = 2)
	private BigDecimal uriTanka;

	/** 売価金額 */
	@NotEmpty(groups = { Group.Head2.class })
	@Digits(integer = 9)
	private BigDecimal baikaGaku;

	/** 税率 */
	@Digits(integer = 2)
	private BigDecimal zeiRate;
}
