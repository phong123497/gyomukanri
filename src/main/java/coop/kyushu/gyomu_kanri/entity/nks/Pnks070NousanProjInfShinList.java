//================================================================================
// ID: Pnks070
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks070NousanProjInfShinList
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/25 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.entity.nks;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【農産企画支援】価格取込用データ出力エンティティ
 * </pre>
 */
@Getter
@Setter
public class Pnks070NousanProjInfShinList implements Serializable {
	@JsonIgnore
	private static final long serialVersionUID = 1L;

	/** 会員法人コード */
	private String kaiinHojinCd;

	/** CQ企画番号 */
	private String cqProjNo;

	/** 共同購入商品コード */
	private String kydKonyShinCd;

	/** コープ九州商品コード */
	private String cpkyushuShinCd;

	/** JANコード1_現 */
	private String janCd1Gen;

	/** CQ分類コード */
	private String cqCatgCd;

	/** 表示商品名 */
	private String hyojiShinNm;

	/** 表示規格 */
	private String hyojiStdd;

	/** 価格表記種別 */
	private String kakakuHyojiShubt;

	/** 今回原単価 */
	private String konkaiGentan;

	/** 今回売単価 */
	private String konkaiUritan;

	/** 今回本体価格 */
	private String konkaiHontaiKakakui;

	/** 今回組価税込 */
	private String konkaiKumikaZeikomi;

	/** 計画数 */
	private String planSu;

	/** ブランド区分 */
	private String brandKbn;
}
