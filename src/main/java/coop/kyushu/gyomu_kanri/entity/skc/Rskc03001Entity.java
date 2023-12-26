//================================================================================
// ID: PSKC030
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Rskc03001Entity
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/11 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.entity.skc;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【惣菜加工センター】製造計画数入力出力エンティティ
 * </pre>
 */
@Getter
@Setter
public class Rskc03001Entity implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 店舗 */
	private String tenpo;

	/** 店舗名 */
	private String tenpoNm;

	/** 表示週 */
	private String hyojiWeek;

	/** 帳票ヘーダ部 */
	private String headerLbl;
	/** 表示週名 */
	private String hyojiWeekNm;

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

	/** 商品明細リスト */
	private List<Rskc03001ListItem> meisaiList;
}
