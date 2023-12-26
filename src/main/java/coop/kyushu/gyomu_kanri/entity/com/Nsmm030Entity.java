//================================================================================
// ID: 共通
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Nsmm030Entity
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
 * 【農産企画支援】会員生協マスタ エンティティ
 * </pre>
 */
@Getter
@Setter
public class Nsmm030Entity implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 会員生協コード */
	private String seicd;

	/** 店・支部コード */
	private String tsbcd;

	/** 生協名称（漢字） */
	private String seikjnm;

	/** 生協名称（カナ） */
	private String seiknnm;

	/** 生協略称（漢字） */
	private String seikjrm;

	/** 生協略称（カナ） */
	private String seiknrm;

	/** 店・支部略称（漢字） */
	private String tsbkjrm;

	/** 店・支部略称（カナ） */
	private String tsbknrm;

	/** 日生協の会員生協コード */
	private String nskseicd;

	/** 日生協の店・支部コード */
	private String nsktsbcd;

	/** 日生協の単協コード */
	private String nsktancd;

	/** 使用区分 */
	private String siyoukb;

	/** 事業所区分 */
	private String jigyoskb;

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
