//================================================================================
// ID: PNKS090
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks090Entity
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/14 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.entity.nks;

import java.io.Serializable;
import java.util.List;

import coop.kyushu.gyomu_kanri.entity.com.Nsmm030Entity;
import coop.kyushu.gyomu_kanri.form.nks.Pnks090Form;
import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【農産企画支援】バッチ情報メンテナンスエンティティ
 * </pre>
 */
@Getter
@Setter
public class Pnks090Entity extends Pnks090Form implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 会員生協コンボボックスリスト */
	private List<Nsmm030Entity> targetKaiinSeikyComboxList;
}
