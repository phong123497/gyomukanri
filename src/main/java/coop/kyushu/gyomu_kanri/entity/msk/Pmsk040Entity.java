//================================================================================
// ID: Pmsk040
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pmsk040Entity
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/09/21 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.entity.msk;

import java.io.Serializable;

import coop.kyushu.gyomu_kanri.form.msk.Pmsk040Form;
import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【マスク】企画商品マスタ一覧エンティティ
 * </pre>
 */
@Getter
@Setter
public class Pmsk040Entity extends Pmsk040Form implements Serializable {
	private static final long serialVersionUID = 1L;
}
