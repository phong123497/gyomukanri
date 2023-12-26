//================================================================================
// ID: PNKS020
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks020Entity
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/07 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.entity.nks;

import java.io.Serializable;
import java.util.List;

import coop.kyushu.gyomu_kanri.form.nks.Pnks020Form;
import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【農産企画支援】農産企画情報・商品検索エンティティ
 * </pre>
 */
@Getter
@Setter
public class Pnks020Entity extends Pnks020Form implements Serializable {
	private static final long serialVersionUID = 1L;


	/** 農産企画情報リスト */
	private List<Pnks020NousanProjinShin> nousanProjinShinList;
}
