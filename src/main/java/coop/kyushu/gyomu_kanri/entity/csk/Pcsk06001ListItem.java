//================================================================================
// ID: PCSK060
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pcsk06001ListItem
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/18 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.entity.csk;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import coop.kyushu.common.validation.Digits;
import coop.kyushu.common.validation.Length;
import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【コープ九州商管】粗利益管理表出力
 * </pre>
 */
@Getter
@Setter
public class Pcsk06001ListItem implements Serializable {
	@JsonIgnore
	private static final long serialVersionUID = 1L;
	/** 期間（自） */
	private String projKknStart;
	/** 期間（至） */
	private String projKknEnd;
	/** 分類 */
	private String catg;
	/** 供給高 */
	@Length(max = 12)
	private String gokyuuKou;
	/** 前年比 */
	@Digits(integer = 6, fraction = 2)
	private String zenNenHi;
	/** 月初棚卸高 */
	@Length(max = 12)
	private String tsukiHatsuTanaoroshiKou;
	/** 仕入高 */
	@Length(max = 12)
	private String shiireKou;
	/** 仕入割引 */
	@Length(max = 12)
	private String shiireGari;
	/** 他勘定振替高 */
	@Length(max = 12)
	private String taKanjouFurikaeKou;
	/** 月末棚卸在庫 */
	@Length(max = 12)
	private String tsukiSueTanaoroshiZaiko;
	/** 供給原価 */
	@Length(max = 12)
	private String kyoukyuuGenka;
	/** 粗利益高 */
	@Length(max = 12)
	private String soriRieKou;
	/** 粗利益高GPR(%) */
	@Digits(integer = 6, fraction = 2)
	private String soriRieKouGPR;
	/** 予算差 */
	@Length(max = 12)
	private String yosanCha;
	/** 前年差 */
	@Length(max = 12)
	private String zenNenCha;
	/** 前年差(%) */
	@Digits(integer = 6, fraction = 2)
	private String zenNenChaGPR;
	/** 年契 */
	@Length(max = 12)
	private String nenkei;
	/** 供給剰余金 */
	@Length(max = 12)
	private String kyoukyuuJouryoKin;
	/** 供給剰余金GPR(%) */
	@Digits(integer = 6, fraction = 2)
	private String kyoukyuuJouryoKinGPR;
}
