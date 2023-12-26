//================================================================================
// ID: PCSK050
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pcsk050Entity
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/18 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.entity.csk;

import java.io.Serializable;
import java.util.List;

import coop.kyushu.gyomu_kanri.form.csk.Pcsk050Form;
import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【コープ九州商管】センター商管表出力エンティティ
 * </pre>
 */
@Getter
@Setter
public class Pcsk050Entity extends Pcsk050Form implements Serializable {
	private static final long serialVersionUID = 1L;
	/** ファイル名 */
	private String fileName;

	/** メーカーコンボボックスリスト */
	private List<Pcsk05001Entity> detailList;

}
