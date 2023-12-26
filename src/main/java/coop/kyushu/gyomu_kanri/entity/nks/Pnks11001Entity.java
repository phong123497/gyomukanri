//================================================================================
// ID: PNKS110
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks11001Entity
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/03 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.entity.nks;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【農産企画支援】調達加工計画書印刷
 * </pre>
 */
@Getter
@Setter
public class Pnks11001Entity extends Pnks11001Item implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 配達年月回_年 */
	private String haitatuY;

	/** 配達年月回_月 */
	private String haitatuM;

	/** 配達年月回_週 */
	private String haitatuT;

	/** 明細リスト */
	private List<Pnks11001Item> meiSaiList;

}
