//================================================================================
// ID: PNKS150
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks15001MeisaiItem
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
public class Pnks15001MeisaiItem implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 共同購入商品コード */
	private String kyshinCd;

	/** 等階級 */
	private String tokaikyuCd;

	/** 入荷日 */
	private String nyukyMd;

	/** ＪＡＮコード */
	private String janCd;

	/** 商品名漢字 */
	private String shinKj;

	/** 規格名 */
	private String kikakKn;

	/** 産地名称漢字 */
	private String santiKj;

	/** 等階級名称漢字 */
	private String tokaikyuKj;

	/** 納品形態 */
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
	private String suryo1;

	/** 有償予備数1 */
	private String yobisu1;

	/** 五島数量1 */
	private String gotosuryo1;

	/** 五島予備数1 */
	private String gotoyobisu1;

	/** 数量2 */
	private String suryo2;

	/** 有償予備数2 */
	private String yobisu2;

	/** 五島数量2 */
	private String gotosuryo2;

	/** 五島予備数2 */
	private String gotoyobisu2;

	/** 数量3 */
	private String suryo3;

	/** 有償予備数3 */
	private String yobisu3;

	/** 五島数量3 */
	private String gotosuryo3;

	/** 五島予備数3 */
	private String gotoyobisu3;

	/** 数量4 */
	private String suryo4;

	/** 有償予備数4 */
	private String yobisu4;

	/** 五島数量4 */
	private String gotosuryo4;

	/** 五島予備数4 */
	private String gotoyobisu4;

	/** 数量5 */
	private String suryo5;

	/** 有償予備数5 */
	private String yobisu5;

	/** 数量合計1 */
	private String suryoSum1;

	/** 数量オール1 */
	private String suryoAll1;

	/** 五島数量合計1 */
	private String gotoSuryoSum1;

	/** 五島数量オール1 */
	private String gotoSuryoAll1;

	/** 数量合計2 */
	private String suryoSum2;

	/** 数量オール2 */
	private String suryoAll2;

	/** 五島数量合計2 */
	private String gotoSuryoSum2;

	/** 五島数量オール2 */
	private String gotoSuryoAll2;

	/** 数量合計3 */
	private String suryoSum3;

	/** 数量オール3 */
	private String suryoAll3;

	/** 五島数量合計3 */
	private String gotoSuryoSum3;

	/** 五島数量オール3 */
	private String gotoSuryoAll3;

	/** 数量合計4 */
	private String suryoSum4;

	/** 数量オール4 */
	private String suryoAll4;

	/** 五島数量合計4 */
	private String gotoSuryoSum4;

	/** 五島数量オール4 */
	private String gotoSuryoAll4;

	/** 数量合計5 */
	private String suryoSum5;

	/** 数量オール5 */
	private String suryoAll5;

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
}
