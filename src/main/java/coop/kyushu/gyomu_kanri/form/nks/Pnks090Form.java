//================================================================================
// ID: PNKS090
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks090Form
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/14 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.form.nks;

import java.io.Serializable;
import java.math.BigDecimal;

import coop.kyushu.common.validation.Code;
import coop.kyushu.common.validation.Digits;
import coop.kyushu.common.validation.FullWidth;
import coop.kyushu.common.validation.Length;
import coop.kyushu.common.validation.NotEmpty;
import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【農産企画支援】バッチ情報メンテナンスフォーム
 * </pre>
 */
@Getter
@Setter
public class Pnks090Form implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 種別 */
	@NotEmpty
	private String shubt;

	/** バッチNO */
	@NotEmpty
	@Code(length = 2)
	private String batchNo;

	/** バッチ名称 */
	@NotEmpty
	@Length(max = 20)
	@FullWidth
	private String batchNm;

	/** バッチ略称 */
	@NotEmpty
	@Length(max = 10)
	@FullWidth
	private String batchRyakusho;

	/** 対象会員生協 */
	private String targetKaiinSeiky;

	/** バッチ計画対象 */
	private String batchPlanTarget;

	/** バッチ計画係数 */
	@NotEmpty
	@Digits(integer = 7)
	private BigDecimal batchPlanKeisu;

	/** 週間予測係数 */
	@NotEmpty
	@Digits(integer = 5, fraction = 2)
	private BigDecimal weekYosokuKeisu;

	/** 更新日時 */
	private String updDatm;
}
