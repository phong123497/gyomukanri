//================================================================================
// ID: PNKS100
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks10001Entity
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/27 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.entity.nks;

import java.io.Serializable;
import java.util.List;

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
public class Pnks10001Entity implements Serializable {

	@JsonIgnore
	private static final long serialVersionUID = 1L;

	/** 配達年 */
	private String haitatuymtY;

	/** 配達月 */
	private String haitatuymtM;

	/** 配達回 */
	private String haitatuymtT;

	/** 明細部 */
	private List<Pnks10001ListItem> detail;
}
