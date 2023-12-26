//================================================================================
// ID: PNKS130
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks130Form
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/06 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.form.nks;

import java.io.Serializable;
import java.util.List;

import coop.kyushu.gyomu_kanri.entity.nks.Pnks130ListItem;
import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【農産企画支援】メーカーマスタ一覧フォーム
 * </pre>
 */
@Getter
@Setter
public class Pnks130Form implements Serializable {
	private static final long serialVersionUID = 1L;

	/** メーカー開始 */
	private String makerStart;

	/** メーカー終了 */
	private String makerEnd;

	/** 削除対象メーカーコードリスト */
	private List<String> delMakerCdList;

	/** メーカー情報リスト */
	private List<Pnks130ListItem> makerInfList;
}
