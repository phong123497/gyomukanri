//================================================================================
// ID: PNKS030
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks030ShohinInfoItem
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/10 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.entity.nks;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import coop.kyushu.common.validation.Digits;
import coop.kyushu.gyomu_kanri.entity.com.Nsmt020Entity;
import coop.kyushu.gyomu_kanri.entity.com.Nsmt030Entity;
import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【農産企画支援】農産企画情報入力エンティティ
 * </pre>
 */
@Getter
@Setter
public class Pnks030ShohinInfoItem implements Serializable {
	private static final long serialVersionUID = 1L;

	/** バッチＮｏ */
	private String batchNo;

	/** バッチ名称 */
	private String batchNm;

	/** メーカー */
	private BigDecimal maker;

	/** メーカー名称 */
	private String makerNm;

	/** 産地 */
	private String santiCd;

	/** 産地名称 */
	private String santi;

	/** 等階級 */
	private String touClassCd;

	/** 等階級 */
	private String touClass;

	/** 加工 */
	private String kako;

	/** 特価 */
	private String tokka;

	/** バッチ計画係数 */
	private String keikakuKeisu;

	/** 税込み価格 */
	@Digits(integer = 7)
	private BigDecimal zeikomiKakaku;

	/** 本体価格 */
	@Digits(integer = 7)
	private BigDecimal hontaiKakaku;

	/** 供給単価 */
	@Digits(integer = 7)
	private BigDecimal suplyTan;

	/** 仕入単価 */
	@Digits(integer = 7)
	private BigDecimal shiireTan;

	/** 原料単価 */
	@Digits(integer = 7)
	private BigDecimal genryoTan;

	/** 加工単価 */
	@Digits(integer = 7)
	private BigDecimal kakoTan;

	/** 資材単価 */
	@Digits(integer = 7)
	private BigDecimal shizaiTan;

	/** 委託単価 */
	@Digits(integer = 7)
	private BigDecimal itakTan;

	/** 計画数 */
	@Digits(integer = 7)
	private BigDecimal planSu;

	/** ログインユーザID */
	private String userId;

	/** 会員生協コード */
	private String kaiinSeikyCd;

	/** 産地コンボボックスリスト */
	private List<Nsmt020Entity> santiCboxList;

	/** 等階級コンボボックスリスト */
	private List<Nsmt030Entity> tokaikyCboxList;
}
