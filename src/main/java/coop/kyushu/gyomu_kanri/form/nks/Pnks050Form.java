//================================================================================
// ID: PNKS050
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks050Form
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/26 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.form.nks;

import java.io.Serializable;

import coop.kyushu.common.validation.NotEmpty;
import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【農産企画支援】価格表印刷フォーム
 * </pre>
 */
@Getter
@Setter
public class Pnks050Form implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 企画番号 */
	@NotEmpty
	private String projNo;

	/** 金額表示あり */
	private String gakuHyojiAri;
}
