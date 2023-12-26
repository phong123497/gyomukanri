//================================================================================
// ID: 共通
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Nsmm010Entity
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
 * 【農産企画支援】管理マスタ エンティティ
 * </pre>
 */
@Getter
@Setter
public class Nsmm010Entity implements Serializable {
	private static final long serialVersionUID = 1L;

	/** システムコード */
	private String syscd;

	/** アプリバージョン */
	private String appversion;

	/** 連絡事項 */
	private String renrakujiko;

	/** 連絡先名称（北部） */
	private String renrakukj;

	/** 連絡先ＴＥＬ（北部） */
	private String renrakutel;

	/** 連絡先ＦＡＸ（北部） */
	private String renrakufax;

	/** 納品場所（北部） */
	private String nohinbasho;

	/** 納品先名称（北部） */
	private String nohinkj;

	/** 納品先ＴＥＬ（北部） */
	private String nohintel;

	/** 納品先ＦＡＸ（北部） */
	private String nohinfax;

	/** 連絡先名称（南部） */
	private String renrakukj2;

	/** 連絡先ＴＥＬ（南部） */
	private String renrakutel2;

	/** 連絡先ＦＡＸ（南部） */
	private String renrakufax2;

	/** 納品場所（南部） */
	private String nohinbasho2;

	/** 納品先名称（南部） */
	private String nohinkj2;

	/** 納品先ＴＥＬ（南部） */
	private String nohintel2;

	/** 納品先ＦＡＸ（南部） */
	private String nohinfax2;

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
