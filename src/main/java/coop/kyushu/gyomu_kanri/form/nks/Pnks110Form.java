//================================================================================
//ID: PNKS110
//ProjectName: コープ九州事業連合様  ホストシステムリプレイス
//SystemName: 業務管理
//ClassName: Pnks110Form
//
//<<Modification History>>
//Version  | Date       | Updated By           | Content
//---------+------------+----------------------+---------------------------------
//01.00.00 | 2023/08/03 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.form.nks;

import java.io.Serializable;

import coop.kyushu.common.validation.Code;
import coop.kyushu.common.validation.NotEmpty;
import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【農産企画支援】調達加工計画書印刷
 * </pre>
 */
@Getter
@Setter
public class Pnks110Form implements Serializable {
	private static final long serialVersionUID = 1L;

	/** センター */
	private String cntr;

	/** 配達年月 */
	@NotEmpty
	private String haitatYm;

	/** 配達回 */
	@NotEmpty
	@Code(length = 1)
	private String haitatTimes;

}
