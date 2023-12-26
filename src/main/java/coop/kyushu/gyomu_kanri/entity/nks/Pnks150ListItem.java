//================================================================================
// ID: PNKS150
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks150ListItem
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/01 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.entity.nks;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【農産企画支援】納品指示書発行指示エンティティ
 * </pre>
 */
@Getter
@Setter
public class Pnks150ListItem implements Serializable {
	private static final long serialVersionUID = 1L;

	/** メーカーコード */
	private String makerCd;

	/** 配達年月回 */
	private String haitatYearMonthTimes;

	/** センタ事業所コード */
	private String cntrJigyoCd;

	/** 共同購入商品コード */
	private String kydKonyShinCd;

	/** 産地コード */
	private String santiCd;

	/** 等階級コード */
	private String touClassCd;

	/** 配達次数 */
	private String haitatJisu;

	/** 五島フラグ */
	private String gotoFlag;

	/** ＪＡＮコード */
	private String janCd;

	/** 規格名カナ */
	private String stddNmKn;

	/** 商品名漢字 */
	private String shinNmKnji;

	/** メーカー名称 */
	private String makerNm;

	/** 産地名称 */
	private String santiNm;

	/** 等階級名称 */
	private String tokaikyNm;

	/** 納品形態名称 */
	private String delvFormNm;

	/** 入荷時間 */
	private String nyukaTime;

	/** 加工区分 */
	private String kakoKbn;

	/** 企画単協　エフ */
	private String projTankyoFcp;

	/** 企画単協　さが */
	private String projTankyoSag;

	/** 企画単協　ララ */
	private String projTankyoLala;

	/** 企画単協　おおいた */
	private String projTankyoOit;

	/** 企画単協　水光社 */
	private String projTankyoSuiko;

	/** 企画単協　みやざき */
	private String projTankyoMyz;

	/** 企画単協　かごしま */
	private String projTankyoKag;

	/** 企画単協　おきなわ */
	private String projTankyoOkn;

	/** 入荷日 */
	private String nyukaDay;

	/** 数量 */
	private String su;

	/** 有償予備数 */
	private String paidSprSu;

	/** 削除フラグ */
	private String delFlag;
}
