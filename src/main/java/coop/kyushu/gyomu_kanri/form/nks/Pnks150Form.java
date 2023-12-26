//================================================================================
// ID: PNKS150
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks150Form
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/01 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.form.nks;

import java.io.Serializable;

import coop.kyushu.common.validation.Code;
import coop.kyushu.common.validation.NotEmpty;
import coop.kyushu.common.validation.YearMonth;
import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【農産企画支援】納品指示書発行指示フォーム
 * </pre>
 */
@Getter
@Setter
public class Pnks150Form implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 配達年月 */
	@NotEmpty
	@YearMonth
	private String haitatYm;

	/** 配達回 */
	@NotEmpty
	@Code(length = 1)
	private String haitatTimes;

	/** メーカー */
	private String maker;
}
