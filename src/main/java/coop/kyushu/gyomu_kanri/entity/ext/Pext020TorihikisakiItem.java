//================================================================================
// ID: PEXT020
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ProgramName: 仕入・訂正入力
// ClassName: Pext020TorihikisakiItem
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
 * 仕入・訂正入力 取引先情報エンティティ
 * </pre>
 */
@Getter
@Setter
public class Pext020TorihikisakiItem implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 取引先コード */
	private String toricd;
	/** 取引先名 */
	private String toriskNm;
	/** 取引先名称（漢字） */
	private String torikjnm;
	/** 現金仕入区分 */
	private String gensirkb;
	/** 取引停止区分 */
	private String toritskb;
}
