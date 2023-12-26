//================================================================================
// ID: PNKS150
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks150Entity
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/01 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.entity.nks;

import java.io.Serializable;
import java.util.List;

import coop.kyushu.gyomu_kanri.entity.com.Nsmm060Entity;
import coop.kyushu.gyomu_kanri.form.nks.Pnks150Form;
import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【農産企画支援】納品指示書発行指示エンティティ
 * </pre>
 */
@Getter
@Setter
public class Pnks150Entity extends Pnks150Form implements Serializable {
	private static final long serialVersionUID = 1L;

	/** メーカーコンボボックスリスト */
	private List<Nsmm060Entity> makerComboxList;
}
