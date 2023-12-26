//================================================================================
// ID: PNKS010
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks010ListItem
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/10 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.entity.nks;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <pre>
 * 【農産企画支援】PEACEデータ取込エンティティ
 * </pre>
 */
@Getter
@Setter
@Accessors(chain = true)
public class Pnks010ListItem implements Serializable {
	private static final long serialVersionUID = 1L;

	/** ファイル名 */
	private String fileNm;

	/** 取込結果 */
	private String torikmReslt;

	/** 行番号 */
	private String gyoNo;

	/** エラー内容 */
	private String errContents;
}
