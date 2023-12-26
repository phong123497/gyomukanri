//================================================================================
// ID: 共通
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Nstf020Entity
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/10 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.entity.com;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【農産企画支援】惣菜店舗ビュー エンティティ
 * </pre>
 */
@Getter
@Setter
public class Szvw102Entity implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 店舗コード */
	private String misecd;

	/** 店舗名 */
	private String misenm;

	/** 会員生協コード */
	private String kseicd;

	/** 店支部コード */
	private String tsbcd;

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
	private Integer id;
}
