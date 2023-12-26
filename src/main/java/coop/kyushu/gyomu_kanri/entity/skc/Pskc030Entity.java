//================================================================================
// ID: PSKC030
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pskc030Entity
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/11 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.entity.skc;

import java.io.Serializable;
import java.util.List;

import coop.kyushu.gyomu_kanri.entity.com.Sztr102Entity;
import coop.kyushu.gyomu_kanri.entity.com.Szvw102Entity;
import coop.kyushu.gyomu_kanri.form.skc.Pskc030Form;
import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【惣菜加工センター】製造計画数入力エンティティ
 * </pre>
 */
@Getter
@Setter
public class Pskc030Entity extends Pskc030Form implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 店舗リスト */
	private List<Szvw102Entity> miseList;

	/** 表示週リスト */
	private List<Sztr102Entity> nhinymdList;

	/** 店舗 */
	private String mise;

	/** 表示週 */
	private String nhinymd;

	/** 納品日（月） */
	private String delvdayMon;

	/** 納品日（火） */
	private String delvdayTue;

	/** 納品日（水） */
	private String delvdayWed;

	/** 納品日（木） */
	private String delvdayThu;

	/** 納品日（金） */
	private String delvdayFri;

	/** 納品日（土） */
	private String delvdaySat;

	/** 納品日（日） */
	private String delvdaySun;
}
