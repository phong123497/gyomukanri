//================================================================================
// ID: PSYS030
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ProgramName: パスワード変更
// ClassName: Psys030Entity
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/01 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.entity.sys;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * パスワード変更エンティティ
 * </pre>
 */
@Getter
@Setter
public class Psys030Entity implements Serializable {

	/** 担当者CD */
	private String tansCd;

}
