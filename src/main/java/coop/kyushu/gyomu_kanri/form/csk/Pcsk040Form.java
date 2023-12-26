//================================================================================
// ID: PCSK040
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pcsk040Form
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/17 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.form.csk;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

import coop.kyushu.common.validation.Length;
import coop.kyushu.common.validation.NotEmpty;
import coop.kyushu.gyomu_kanri.entity.csk.Pcsk040Item;
import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【コープ九州商管】締日登録フォーム
 * </pre>
 */
@Getter
@Setter
public class Pcsk040Form implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 年度 */
	@NotEmpty
	@Valid
	@Length(max = 4)
	private String nedo;

	/** 締日一覧 */
	private List<Pcsk040Item> closeDayList;
}
