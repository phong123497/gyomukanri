//================================================================================
// ID: PCSK060
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pcsk060Form
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/18 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.form.csk;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import coop.kyushu.common.validation.NotEmpty;
import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【コープ九州商管】粗利益管理表出力
 * </pre>
 */
@Getter
@Setter
public class Pcsk060Form implements Serializable {
	@JsonIgnore
	private static final long serialVersionUID = 1L;

	/** 種別 */
	@NotEmpty
	private String shubt;
	/** 管理体系区分 */
	@NotEmpty
	private String mngTaikeiKbn;

	/** 無店舗食品 */
	private String mutempoFood;

	/** 農産商品部 */
	private String nosanShinDept;

	/** 水産商品部 */
	private String suisanShinDept;

	/** 畜産商品部 */
	private String chikusanShinDept;

	/** 日配商品部 */
	private String nippaiShinDept;

	/** 食品商品部 */
	private String foodShinDept;

	/** 商品本部 */
	private String shinHombu;

	/** 無店舗非食品 */
	private String mutempoHifood;

	/** くらしと生協 */
	private String lifeAndSeiky;

	/** スクロール */
	private String scroll;

	/** クリム */
	private String klim;

	/** 店舗 */
	private String tempo;

	/** 店舗用度品 */
	private String tempoYodohin;

	/** 年度 */
	private String nendo;

	/** 月_FROM */
	private String monthlyFrom;

	/** 月_TO */
	private String monthlyTo;

}
