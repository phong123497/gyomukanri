//================================================================================
// ID: PNKS120
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks120CntrKakoShijiEntity
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/02 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.entity.nks;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【農産企画支援】各種データ出力エンティティ
 * </pre>
 */
@Getter
@Setter
public class Pnks120CntrSireKeijEntity implements Serializable {

	@JsonIgnore
	private static final long serialVersionUID = 1L;

	/** コープ九州伝票番号 */
	private String cpkyushuDenpyoNo;

	/** 発注伝票行番号 */
	private String hachuDenpyoGyoNo;

	/** データ作成年月日 */
	private String dataCreYearMonthDay;

	/** 発注年月日 */
	private String hachuYearMonthDay;

	/** 納入指定年月日 */
	private String nonyuSiteiYearMonthDay;

	/** 発注生協コード */
	private String hachuSeikyCd;

	/** 発注生協名称（カナ） */
	private String hachuSeikyNmKn;

	/** 発注事業所コード */
	private String hachuJigyoCd;

	/** 発注事業所名称（カナ） */
	private String hachuJigyoNmKn;

	/** 取引先コード */
	private String toriskCd;

	/** 取引先名称（カナ） */
	private String toriskNmKn;

	/** ＣＱ企画番号 */
	private String cqProjNo;

	/** 共同購入商品コード */
	private String kydKonyShinCd;

	/** コープ九州商品コード */
	private String cpkyushuShinCd;

	/** ＪＡＮコード */
	private String janCd;

	/** 商品名称（漢字） */
	private String shinNmKnji;

	/** 規格名（カナ） */
	private String stddNmKn;

	/** メーカーコード */
	private String makerCd;

	/** メーカー名称（漢字） */
	private String makerNmKnji;

	/** メーカーＴＥＬ */
	private String makerTel;

	/** メーカーＦＡＸ */
	private String makerFax;

	/** 産地名称 */
	private String santiNm;

	/** 等階級 */
	private String touClass;

	/** 資材名 */
	private String shizaikj;

	/** 集品区分 */
	private String collectKbnNm;

	/** 納品形態 */
	private String delvForm;

	/** 数量 */
	private String su;

	/** コープ九州仕入原単価 */
	private String cpkyushuSireGentan;

	/** 原料単価 */
	private String genryoTan;

	/** 加工単価 */
	private String kakoTan;

	/** 資材単価 */
	private String shizaiTan;

	/** 委託単価 */
	private String itakuTan;

	/** コープ九州仕入金額 */
	private String cpkyushuSireGaku;

	/** 原料金額 */
	private String genryoGaku;

	/** 加工金額 */
	private String kakoGaku;

	/** 資材金額 */
	private String shizaiGaku;

	/** 委託金額 */
	private String itakuGaku;

	/** 税率 */
	private String zeiritu;
}
