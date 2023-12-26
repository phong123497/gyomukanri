//================================================================================
// ID: PNKS020
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks020Form
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/07 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.form.nks;

import java.io.Serializable;

import coop.kyushu.common.validation.Code;
import coop.kyushu.common.validation.Length;
import coop.kyushu.common.validation.NotEmpty;
import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【農産企画支援】農産企画情報・商品検索フォーム
 * </pre>
 */
@Getter
@Setter
public class Pnks020Form implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 企画番号 */
	@NotEmpty
	@Code(length = 3)
	private String cqkikano;

	/** 共同購入商品コード開始 */
	@Length(max = 7)
	private String kyshincdStart;

	/** 共同購入商品コード終了 */
	@Length(max = 7)
	private String kyshincdEnd;
}
