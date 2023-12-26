//================================================================================
// ID: 共通
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Nstf020Entity
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/10 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.entity.com;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【農産企画支援】納品指示データ エンティティ
 * </pre>
 */
@Getter
@Setter
public class Nstf020Entity implements Serializable {
	private static final long serialVersionUID = 1L;

	/** メーカーコード */
	private String makercd;

	/** 配達年月回 */
	private String haitatuymt;

	/** センタ事業所コード */
	private String cntjigcd;

	/** 共同購入商品コード */
	private String kyshincd;

	/** 産地コード */
	private String santicd;

	/** 等階級コード */
	private String tokaiyucd;

	/** 配達次数 */
	private String haijisu;

	/** 五島フラグ */
	private String gotoFlg;

	/** ＪＡＮコード */
	private String jancd;

	/** 規格名カナ */
	private String kikakkn;

	/** 商品名漢字 */
	private String shinkj;

	/** メーカー名称 */
	private String makerkj;

	/** 産地名称 */
	private String santikj;

	/** 等階級名称 */
	private String tokaiyukj;

	/** 納品形態名称 */
	private String nouhinnm;

	/** 入荷時間 */
	private String nyukhm;

	/** 加工区分 */
	private String kakokbn;

	/** 企画単協　エフ */
	private String ehu;

	/** 企画単協　さが */
	private String saga;

	/** 企画単協　ララ */
	private String rara;

	/** 企画単協　おおいた */
	private String oita;

	/** 企画単協　水光社 */
	private String suikosya;

	/** 企画単協　みやざき */
	private String miyazaki;

	/** 企画単協　かごしま */
	private String kagosima;

	/** 企画単協　おきなわ */
	private String okinawa;

	/** 入荷日 */
	private String nyukymd;

	/** 数量 */
	private String suryo;

	/** 有償予備数 */
	private String yobisu;

	/** 削除フラグ */
	private String delFlg;

	/** 作成ユーザー */
	private String sksiUsr;

	/** 作成プログラム */
	private String sksiPrgrm;

	/** 作成日時 */
	private String sksiNchj;

	/** 更新ユーザー */
	private String kushnUsr;

	/** 更新プログラム */
	private String kushnPrgrm;

	/** 更新日時 */
	private String kushnNchj;

	/** ID */
	private Integer id;
}
