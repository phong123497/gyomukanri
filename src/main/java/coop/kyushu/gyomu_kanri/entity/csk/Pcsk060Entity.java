//================================================================================
// ID: PCSK060
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pcsk060Entity
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/18 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.entity.csk;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import coop.kyushu.gyomu_kanri.form.csk.Pcsk060Form;
import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 仕入・訂正入力エンティティ
 * </pre>
 */
@Getter
@Setter
public class Pcsk060Entity extends Pcsk060Form implements Serializable {
	@JsonIgnore
	private static final long serialVersionUID = 1L;
	/** 年度リスト */
	private List<Pcsk060YmItemEntity> nendoList;

	/** 月度リスト */
	private List<Pcsk060YmItemEntity> monthlyList;

}