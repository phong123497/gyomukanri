//================================================================================
// ID: PNKS150
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks15001MakerEntity
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/01 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.entity.nks;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【農産企画支援】納品指示書発行指示エンティティ
 * </pre>
 */
@Getter
@Setter
public class Pnks15001MakerEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 連絡先名称（北部） */
	private String renrakuKj;

	/** 連絡先ＴＥＬ（北部） */
	private String renrakuTel;

	/** 連絡先ＦＡＸ（北部） */
	private String renrakuFax;

	/** 納品場所（南部） */
	private String nohinBasho;

	/** 納品先名称（北部） */
	private String nohinKj;

	/** 納品先ＴＥＬ（北部） */
	private String nohinTel;

	/** 納品先ＦＡＸ（北部） */
	private String nohinFax;

	/** 連絡先名称（南部） */
	private String renrakuKj2;

	/** 連絡先ＴＥＬ（南部） */
	private String renrakuTel2;

	/** 連絡先ＦＡＸ（南部） */
	private String renrakuFax2;

	/** 納品場所（南部） */
	private String nohinBasho2;

	/** 納品先名称（南部） */
	private String nohinKj2;

	/** 納品先ＴＥＬ（南部） */
	private String nohinTel2;

	/** 納品先ＦＡＸ（南部） */
	private String nohinFax2;
}
