//================================================================================
// ID: PCSK040
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pcsk040Item
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/17 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.entity.csk;

import java.io.Serializable;

import javax.validation.Valid;

import coop.kyushu.common.validation.Length;
import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【コープ九州商管】締日登録エンティティ
 * </pre>
 */
@Getter
@Setter
public class Pcsk040Item implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 計上年月 */
	private String keijoYm;

	/** コープ九州月次締日 */
	@Valid
	@Length(max = 8)
	private String cpkyushuMonthCloseDay;

	/** 商品管理表締日_商品管理 */
	@Valid
	@Length(max = 8)
	private String shinMngHyoCloseDayShinMng;

	/** 商品管理表締日_経理 */
	@Valid
	@Length(max = 8)
	private String shinMngHyoCloseDayKeiri;

	/** 消込照合処理日_処理日 */
	@Valid
	@Length(max = 8)
	private String keshikmShogoProcsDayProcsDay;

	/** 消込照合処理日_処理区分 */
	private String keshikmShogoProcsDayProcsKbn;

	/** 請求支払_月次処理区分 */
	private String MoprcKbn;
}
