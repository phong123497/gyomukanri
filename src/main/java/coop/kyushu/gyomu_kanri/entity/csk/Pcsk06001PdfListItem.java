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

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【コープ九州商管】粗利益管理表出力
 * </pre>
 */
@Getter
@Setter
public class Pcsk06001PdfListItem implements Serializable {
	@JsonIgnore
	private static final long serialVersionUID = 1L;
	/** 分類CD1 */
	private String bunruicd1;
	/** 分類CD2 */
	private String bunruicd2;
	/** 分類CD3 */
	private String bunruicd3;
	/** 分類名1 */
	private String bunruiName1;
	/** 分類名2 */
	private String bunruiName2;
	/** 分類名3 */
	private String bunruiName3;
	/** 分類 */
	private String catg;
	/** 表示順 */
	private String dispord;
	/** 分類Lvl */
	private String bunruilvl;
	/** 供給高 */
	private String gokyuuKou;
	/** 前年比 */
	private String zenNenHi;
	/** 月初棚卸高 */
	private String tsukiHatsuTanaoroshiKou;
	/** 仕入高 */
	private String shiireKou;
	/** 仕入割引 */
	private String shiireGari;
	/** 他勘定振替高 */
	private String taKanjouFurikaeKou;
	/** 月末棚卸在庫 */
	private String tsukiSueTanaoroshiZaiko;
	/** 供給原価 */
	private String kyoukyuuGenka;
	/** 粗利益高 */
	private String soriRieKou;
	/** 粗利益高GPR(%) */
	private String soriRieKouGPR;
	/** 予算差 */
	private String yosanCha;
	/** 前年差 */
	private String zenNenCha;
	/** 前年差(%) */
	private String zenNenChaGPR;
	/** 年契 */
	private String nenkei;
	/** 供給剰余金 */
	private String kyoukyuuJouryoKin;
	/** 供給剰余金GPR(%) */
	private String kyoukyuuJouryoKinGPR;
	/** 供給高2 */
	private String gokyuuKou2;
	/** 前年比2 */
	private String zenNenHi2;
	/** 月初棚卸高2 */
	private String tsukiHatsuTanaoroshiKou2;
	/** 仕入高2 */
	private String shiireKou2;
	/** 仕入割引2 */
	private String shiireGari2;
	/** 他勘定振替高2 */
	private String taKanjouFurikaeKou2;
	/** 月末棚卸在庫2 */
	private String tsukiSueTanaoroshiZaiko2;
	/** 供給原価2 */
	private String kyoukyuuGenka2;
	/** 粗利益高2 */
	private String soriRieKou2;
	/** 粗利益高GPR(%)2 */
	private String soriRieKouGPR2;
	/** 予算差2 */
	private String yosanCha2;
	/** 前年差2 */
	private String zenNenCha2;
	/** 前年差(%)2 */
	private String zenNenChaGPR2;
	/** 年契2 */
	private String nenkei2;
	/** 供給剰余金2 */
	private String kyoukyuuJouryoKin2;
	/** 供給剰余金GPR(%)2 */
	private String kyoukyuuJouryoKinGPR2;
	/** 供給高3 */
	private String gokyuuKou3;
	/** 前年比3 */
	private String zenNenHi3;
	/** 月初棚卸高3 */
	private String tsukiHatsuTanaoroshiKou3;
	/** 仕入高3 */
	private String shiireKou3;
	/** 仕入割引3 */
	private String shiireGari3;
	/** 他勘定振替高3 */
	private String taKanjouFurikaeKou3;
	/** 月末棚卸在庫3 */
	private String tsukiSueTanaoroshiZaiko3;
	/** 供給原価3 */
	private String kyoukyuuGenka3;
	/** 粗利益高3 */
	private String soriRieKou3;
	/** 粗利益高GPR(%)3 */
	private String soriRieKouGPR3;
	/** 予算差3 */
	private String yosanCha3;
	/** 前年差3 */
	private String zenNenCha3;
	/** 前年差(%)3 */
	private String zenNenChaGPR3;
	/** 年契3 */
	private String nenkei3;
	/** 供給剰余金3 */
	private String kyoukyuuJouryoKin3;
	/** 供給剰余金GPR(%)3 */
	private String kyoukyuuJouryoKinGPR3;
}
