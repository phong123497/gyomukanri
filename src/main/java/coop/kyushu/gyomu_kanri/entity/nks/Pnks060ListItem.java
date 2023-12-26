//================================================================================
// ID: PNKS060
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks060ListItem
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/06 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.entity.nks;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【農産企画支援】シミュレーション用データ出力エンティティ
 * </pre>
 */
@Getter
@Setter
public class Pnks060ListItem implements Serializable {

	@JsonIgnore
	private static final long serialVersionUID = 1L;

	/** ＣＱ企画番号 */
	private String cqProjNo;

	/** 共同購入商品コード */
	private String kydKonyShinCd;

	/** バッチＮｏ */
	private String batchNo;

	/** 会員生協コード */
	private String kaiinSeikyCd;

	/** コープ九州商品コード */
	private String cpkyushuShinCd;

	/** ＪＡＮコード */
	private String janCd;

	/** 商品名 */
	private String shinNm;

	/** 規格 */
	private String stdd;

	/** メーカーコード */
	private String makerCd;

	/** メーカー名 */
	private String makerNm;

	/** 産地 */
	private String santiNm;

	/** 等階級 */
	private String touClassNm;

	/** 税抜組合員単価 */
	private BigDecimal zeinukiKuminKakaku;

	/** 税込組合員単価 */
	private BigDecimal zeikomiKuminKakaku;

	/** コープ九州供給売単価 */
	private BigDecimal cpkyushuSuplyUritan;

	/** コープ九州仕入原単価 */
	private BigDecimal cpkyushuShiireGentan;

	/** 原料単価 */
	private BigDecimal genryoTan;

	/** 加工単価 */
	private BigDecimal kakoTan;

	/** 資材単価 */
	private BigDecimal shizaiTan;

	/** 委託単価 */
	private BigDecimal itakuTan;

	/** 計画点数 */
	private BigDecimal planPoint;

	/** 大分類 */
	private String daiCatg;

	/** 中分類 */
	private String chuCatg;

	/** 小分類 */
	private String shoCatg;

	/** 集品区分 */
	private String collectKbnNm;

	/** 特価フラグ */
	private String tokkaFlag;

	/** 納品時容器 */
	private String nohinYouki;
}
