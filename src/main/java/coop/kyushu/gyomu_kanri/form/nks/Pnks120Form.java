//================================================================================
// ID: PNKS120
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks120Form
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/02 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.form.nks;

import java.io.Serializable;

import coop.kyushu.common.validation.Length;
import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【農産企画支援】各種データ出力フォーム
 * </pre>
 */
@Getter
@Setter
public class Pnks120Form implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 出力データ */
	@Length(max = 7)
	private String outputData;

	/** センター */
	@Length(max = 7)
	private String cntr;

	/** 受注日開始 */
	@Length(max = 10)
	private String juchuDayStart;

	/** 受注日終了 */
	@Length(max = 10)
	private String juchuDayEnd;

	/** SC納品日開始 */
	@Length(max = 10)
	private String scDelvDayStart;

	/** SC納品日終了 */
	@Length(max = 10)
	private String scDelvDayEnd;

	/** 納入指定年月日開始 */
	@Length(max = 10)
	private String nonyuSiteiYearMonthDayStart;

	/** 納入指定年月日終了 */
	@Length(max = 10)
	private String nonyuSiteiYearMonthDayEnd;
}
