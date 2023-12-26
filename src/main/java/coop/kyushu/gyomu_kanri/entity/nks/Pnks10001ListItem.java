//================================================================================
// ID: PNKS100
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks10001ListItem
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/27 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.entity.nks;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【農産企画支援】バッチ計画表印刷エンティティ
 * </pre>
 */
@Getter
@Setter
public class Pnks10001ListItem implements Serializable {

	@JsonIgnore
	private static final long serialVersionUID = 1L;

	/** バッチＮｏ */
	private String batchNo;

	/** アイテム数 */
	private String itemSu;

	/** 合計数量 */
	private String suRyo;
}
