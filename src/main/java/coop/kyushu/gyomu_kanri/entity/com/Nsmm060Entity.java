//================================================================================
// ID: 共通
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Nsmm060Entity
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
 * 【農産企画支援】メーカーマスタ エンティティ
 * </pre>
 */
@Getter
@Setter
public class Nsmm060Entity implements Serializable {
	private static final long serialVersionUID = 1L;

	/** メーカーコード */
	private String makercd;

	/** メーカー名称 */
	private String makerkj;

	/** メーカー略称 */
	private String makerrkj;

	/** ＴＥＬ */
	private String telno;

	/** ＦＡＸ */
	private String faxno;

	/** 入荷時間 */
	private String nyukhm;

	/** パスワード */
	private String password;

	/** 取扱停止フラグ */
	private String toriteiflg;

	/** 仕入先コード */
	private String sirecd;

	/** 納品先区分 */
	private String nouhinsakikbn;

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
	private Integer id;
}
