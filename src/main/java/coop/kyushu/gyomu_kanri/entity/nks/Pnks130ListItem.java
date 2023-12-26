//================================================================================
// ID: PNKS130
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks130ListItem
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/06 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.entity.nks;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【農産企画支援】メーカーマスタ一覧エンティティ
 * </pre>
 */
@Setter
@Getter
public class Pnks130ListItem implements Serializable {
	private static final long serialVersionUID = 1L;

	/** メーカーコード */
	private String makerCd;

	/** メーカー名称 */
	private String makerNm;

	/** メーカー略称 */
	private String makerRyakusho;

	/** TEL */
	private String tel;

	/** FAX */
	private String fax;

	/** 納品時間 */
	private String delvTime;

	/** パスワード */
	private String pass;

	/** 取引先コード */
	private String toriskCd;

	/** 取扱停止 */
	private String toriatStop;

	/** 取扱停止フラグ */
	private String toriatStopFlag;

	/** 削除対象 */
	private String delTarget;

	/** 更新日時 */
	private String updDatm;
}
