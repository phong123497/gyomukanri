//================================================================================
// ID: PCYU400
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Nsmt020Entity
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/25 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.entity.com;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【農産企画支援】産地マスタ エンティティ
 * </pre>
 */
@Getter
@Setter
public class Nsmt020Entity implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 共同購入商品コード */
	private String kyshincd;

	/** メーカーコード */
	private String makercd;

	/** 産地コード */
	private String santicd;

	/** 産地名称 */
	private String santikj;

	/** 削除フラグ */
	private String delFlg;

	/** 作成ユーザー */
	private String sksiUsr;

	/** 作成プログラム */
	private String sksiPrgrm;

	/** 作成日時 */
	private String sksiNchj;

	/** 更新ユーザー */
	private String kushnUsr;

	/** 更新プログラム */
	private String kushnPrgrm;

	/** 更新日時 */
	private String kushnNchj;

	/** ID */
	private String id;
}
