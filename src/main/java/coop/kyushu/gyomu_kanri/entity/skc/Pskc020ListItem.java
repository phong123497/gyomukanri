//================================================================================
// ID: PSKC020
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pskc020ListItem
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/11 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.entity.skc;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【惣菜加工センター】製造計画数取込・出力エンティティ
 * </pre>
 */
@Getter
@Setter
public class Pskc020ListItem implements Serializable {
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
