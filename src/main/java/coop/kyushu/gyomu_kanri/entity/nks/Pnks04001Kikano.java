//================================================================================
// ID: PNKS040
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks04001Kikano
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/13 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.entity.nks;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【農産企画支援】企画案内書印刷エンティティ
 * </pre>
 */
@Getter
@Setter
public class Pnks04001Kikano implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 企画期間（自） */
	private String kikaymdf;

	/** 企画期間（至） */
	private String kikaymdt;

	/** 価格回答期限 */
	private String kakakKigen;
}
