//================================================================================
// ID: PNKS030
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks030Entity
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/10 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.entity.nks;

import java.io.Serializable;
import java.util.List;

import coop.kyushu.gyomu_kanri.entity.com.Nsmm060Entity;
import coop.kyushu.gyomu_kanri.form.nks.Pnks030Form;
import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【農産企画支援】農産企画情報入力エンティティ
 * </pre>
 */
@Getter
@Setter
public class Pnks030Entity extends Pnks030Form implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 商品合計明細リスト */
	private List<Pnks030TotalItem> shohinList;

	/** メーカーコンボボックスリスト */
	private List<Nsmm060Entity> makerCboxList;
}
