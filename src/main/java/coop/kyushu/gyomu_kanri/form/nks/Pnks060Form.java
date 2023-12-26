//================================================================================
// ID: PNKS060
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks060Form
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/06 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.form.nks;

import java.io.Serializable;

import coop.kyushu.common.validation.Length;
import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【農産企画支援】シミュレーション用データ出力フォーム
 * </pre>
 */
@Getter
@Setter
public class Pnks060Form implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 企画番号開始 */
	@Length(max = 3)
	private String projNoStart;

	/** 企画番号終了 */
	@Length(max = 3)
	private String projNoEnd;
}
