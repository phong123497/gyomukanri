//================================================================================
// ID: PEXT020
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ProgramName: 仕入・訂正入力
// ClassName: Pext020Items
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2022/11/28 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.entity.ext;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 仕入・訂正入力 商品情報用エンティティ
 * </pre>
 */
@Getter
@Setter
public class Pext020Items implements Serializable {

	private static final long serialVersionUID = 1L;

	/** コープ九州商品コード */
	private String cqshincd;
	/** 商品名カナ */
	private String shinkn;
	/** 商品名漢字 */
	private String shinkj;
	/** 規格カナ */
	private String kikakn;
	/** 原単価 */
	private BigDecimal gentan;
	/** 売単価 */
	private BigDecimal baitan;
	/** 入数 */
	private BigDecimal nyuirisu;
}
