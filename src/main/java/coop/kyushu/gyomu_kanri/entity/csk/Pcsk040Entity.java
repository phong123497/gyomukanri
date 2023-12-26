//================================================================================
// ID: PCSK040
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pcsk040Entity
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/17 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.entity.csk;

import java.io.Serializable;

import coop.kyushu.gyomu_kanri.form.csk.Pcsk040Form;
import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【コープ九州商管】締日登録エンティティ
 * </pre>
 */
@Getter
@Setter
public class Pcsk040Entity extends Pcsk040Form implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 営業日 */
	private String ymd;
}
