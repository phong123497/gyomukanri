//================================================================================
// ID: PNKS080
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks080ListItem
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/17 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.entity.nks;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【農産企画支援】バッチ情報一覧エンティティ
 * </pre>
 */
@Getter
@Setter
public class Pnks080ListItem implements Serializable {
	private static final long serialVersionUID = 1L;

	/** バッチNO */
	private String batchNo;

	/** バッチ名称 */
	private String batchNm;

	/** バッチ略称 */
	private String batchRyakusho;

	/** 対象会員生協 */
	private String targetKaiinSeiky;

	/** バッチ計画係数 */
	private BigDecimal batchPlanKeisu;

	/** 週間予測係数 */
	private BigDecimal weekYosokuKeisu;

	/** 削除対象 */
	private String delTarget;

	/** バッチ計画対象 */
	private String batchPlanTarget;
}
