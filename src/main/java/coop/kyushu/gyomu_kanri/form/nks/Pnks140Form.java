//================================================================================
// ID: PNKS140
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks140Form
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/06 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.form.nks;

import java.io.Serializable;

import coop.kyushu.common.validation.Code;
import coop.kyushu.common.validation.FullWidth;
import coop.kyushu.common.validation.HalfWidth;
import coop.kyushu.common.validation.Length;
import coop.kyushu.common.validation.NotEmpty;
import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【農産企画支援】メーカーマスタメンテナンスフォーム
 * </pre>
 */
@Getter
@Setter
public class Pnks140Form implements Serializable {
	private static final long serialVersionUID = 1L;

	/** メーカーコード */
	@NotEmpty
	@Code(length = 4)
	private String makerCd;

	/** メーカー名称 */
	@NotEmpty
	@FullWidth
	@Length(max = 30)
	private String makerNm;

	/** メーカー略称 */
	@NotEmpty
	@FullWidth
	@Length(max = 10)
	private String makerRyakusho;

	/** TEL */
	@HalfWidth
	@Length(max = 15)
	private String telNo;

	/** FAX */
	@HalfWidth
	@Length(max = 15)
	private String faxNo;

	/** 納品時間 */
	@NotEmpty
	@HalfWidth
	@Length(max = 5)
	private String delvTime;

	/** パスワード */
	@NotEmpty
	@HalfWidth
	@Length(max = 15)
	private String password;

	/** 取引先コード */
	private String toriskCd;

	/** 更新日時 */
	private String updDatm;
}
