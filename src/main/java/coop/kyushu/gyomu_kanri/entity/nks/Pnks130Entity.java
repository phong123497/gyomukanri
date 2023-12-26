//================================================================================
// ID: PNKS130
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks130Entity
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/06 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.entity.nks;

import java.io.Serializable;
import java.util.List;

import coop.kyushu.gyomu_kanri.entity.com.Nsmm060Entity;
import coop.kyushu.gyomu_kanri.form.nks.Pnks130Form;
import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【農産企画支援】メーカーマスタ一覧エンティティ
 * </pre>
 */
@Getter
@Setter
public class Pnks130Entity extends Pnks130Form implements Serializable {
	private static final long serialVersionUID = 1L;

	/** メーカーコンボボックスリスト */
	private List<Nsmm060Entity> makerComboxList;
}
