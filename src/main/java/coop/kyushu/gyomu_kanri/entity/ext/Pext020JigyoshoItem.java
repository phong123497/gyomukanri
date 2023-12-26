//================================================================================
// ID: PEXT020
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ProgramName: 仕入・訂正入力
// ClassName: Pext020JigyoshoItem
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2022/11/28 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.entity.ext;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 仕入・訂正入力 事業所情報エンティティ
 * </pre>
 */
@Getter
@Setter
public class Pext020JigyoshoItem implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 生協名 */
	private String seikyNm;
	/** 事業所名 */
	private String jigyoNm;
}
