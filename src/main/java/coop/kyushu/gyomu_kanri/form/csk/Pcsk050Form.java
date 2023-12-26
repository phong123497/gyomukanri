//================================================================================
// ID: PCSK050
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pcsk050Form
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/18 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.form.csk;

import java.io.Serializable;
import java.math.BigDecimal;

import coop.kyushu.common.validation.Length;
import coop.kyushu.common.validation.YearMonth;
import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【コープ九州商管】センター商管表出力フォーム
 * </pre>
 */
@Getter
@Setter
public class Pcsk050Form implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 種別 */
	private BigDecimal shubt;

	/** 対象期間区分 */
	private BigDecimal targetKkn;

	/** 対象年月 */
	@YearMonth
	private String targetYm;

	/** 対象年度 */
	private String targetNendo;

	/** 対象年月_FROM */
	@YearMonth
	private String targetYmFrom;

	/** 対象年月_TO */
	@YearMonth
	private String targetYmTo;

	/** 管理単位区分 */
	private BigDecimal mngTani;

	/** センターコード */
	@Length(max = 5)
	private String cntrCd;

	/** 集計単位区分 */
	private BigDecimal skeiTani;
}
