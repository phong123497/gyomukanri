//================================================================================
// ID: PNKS150
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks15001Item
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/01 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.entity.nks;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【農産企画支援】納品指示書発行指示エンティティ
 * </pre>
 */
@Getter
@Setter
public class Pnks15001Item implements Serializable {
	private static final long serialVersionUID = 1L;
	/** システム時間 */
	private String sysDate;

	/** 配達年月回 */
	private String haitatuyMt;

	/** 納品先ＦＡＸ */
	private String nohinFax;

	/** 納品先ＴＥＬ */
	private String nohinTel;

	/** 納品先漢字 */
	private String nohinKj;

	/** 納品先場所 */
	private String nohinBasho;

	/** 連絡先ＦＡＸ */
	private String renrakuFax;

	/** 連絡先ＴＥＬ */
	private String renrakuTel;

	/** 連絡先名称 */
	private String renrakuKj;

	/** メーカー名称Disp */
	private String makerKjDisp;

	/** 入荷時間DISP */
	private String nyukhmDISP;

	/** センタ事業所コード */
	private String cntjigCd;

	/** 共同購入商品コード */
	private String kyshinCd;

	/** 産地コード */
	private String santiCd;

	/** 等階級コード */
	private String tokaikyuCd;

	/** 入荷日 */
	private String nyukyMd;

	/** ＪＡＮコード */
	private String janCd;

	/** 商品名漢字 */
	private String shinKj;

	/** 規格カナ（明細） */
	private String kikakKn;

	/** 産地名称漢字 */
	private String santiKj;

	/** 等階級名称漢字 */
	private String tokaikyuKj;

	/** 加工区分 */
	private String kakokBn;

	/** 納品先倉庫名称 */
	private String nouhinNm;

	/** ｴﾌ */
	private String ehu;

	/** ｻｶ */
	private String saga;

	/** ﾗﾗ */
	private String rara;

	/** ｵｵｲﾀ */
	private String oita;

	/** 水光社 */
	private String suikosya;

	/** ﾐﾔｻﾞｷ */
	private String miyazaki;

	/** ｶｺﾞｼﾏ */
	private String kagosima;

	/** ｵｷﾅﾜ */
	private String okinawa;

	/** 数量1 */
	private BigDecimal suryo1;

	/** 有償予備数1 */
	private BigDecimal yobisu1;

	/** 五島数量1 */
	private BigDecimal gotosuryo1;

	/** 五島予備数1 */
	private BigDecimal gotoyobisu1;

	/** 数量2 */
	private BigDecimal suryo2;

	/** 有償予備数2 */
	private BigDecimal yobisu2;

	/** 五島数量2 */
	private BigDecimal gotosuryo2;

	/** 五島予備数2 */
	private BigDecimal gotoyobisu2;

	/** 数量3 */
	private BigDecimal suryo3;

	/** 有償予備数3 */
	private BigDecimal yobisu3;

	/** 五島数量3 */
	private BigDecimal gotosuryo3;

	/** 五島予備数3 */
	private BigDecimal gotoyobisu3;

	/** 数量4 */
	private BigDecimal suryo4;

	/** 有償予備数4 */
	private BigDecimal yobisu4;

	/** 五島数量4 */
	private BigDecimal gotosuryo4;

	/** 五島予備数4 */
	private BigDecimal gotoyobisu4;

	/** 数量5 */
	private BigDecimal suryo5;

	/** 有償予備数5 */
	private BigDecimal yobisu5;

	/** 数量合計1 */
	private BigDecimal suryoSum1;

	/** 数量オール1 */
	private BigDecimal suryoAll1;

	/** 五島数量合計1 */
	private BigDecimal gotoSuryoSum1;

	/** 五島数量オール1 */
	private BigDecimal gotoSuryoAll1;

	/** 数量合計2 */
	private BigDecimal suryoSum2;

	/** 数量オール2 */
	private BigDecimal suryoAll2;

	/** 五島数量合計2 */
	private BigDecimal gotoSuryoSum2;

	/** 五島数量オール2 */
	private BigDecimal gotoSuryoAll2;

	/** 数量合計3 */
	private BigDecimal suryoSum3;

	/** 数量オール3 */
	private BigDecimal suryoAll3;

	/** 五島数量合計3 */
	private BigDecimal gotoSuryoSum3;

	/** 五島数量オール3 */
	private BigDecimal gotoSuryoAll3;

	/** 数量合計4 */
	private BigDecimal suryoSum4;

	/** 数量オール4 */
	private BigDecimal suryoAll4;

	/** 五島数量合計4 */
	private BigDecimal gotoSuryoSum4;

	/** 五島数量オール4 */
	private BigDecimal gotoSuryoAll4;

	/** 数量合計5 */
	private BigDecimal suryoSum5;

	/** 数量オール5 */
	private BigDecimal suryoAll5;

	/** 入荷日1 */
	private String nyukYmd1;

	/** 入荷日2 */
	private String nyukYmd2;

	/** 入荷日3 */
	private String nyukYmd3;

	/** 入荷日4 */
	private String nyukYmd4;

	/** 入荷日5 */
	private String nyukYmd5;

	/** メーカーコード */
	private String makerCd;

	/** センタ区分 */
	private String cntkb;

	/** メーカー名称 */
	private String makerKj;

	/** 入荷時間 */
	private String nyukhm;

	/** BKEY */
	private String bkey;
}
