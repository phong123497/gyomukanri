//================================================================================
//ID: PNKS040
//ProjectName: コープ九州事業連合様  ホストシステムリプレイス
//SystemName: 業務管理
//ClassName: Pnks04001Item
//
//<<Modification History>>
//Version  | Date       | Updated By           | Content
//---------+------------+----------------------+---------------------------------
//01.00.00 | 2023/07/13 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.entity.nks;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【農産企画支援】企画案内書印刷エンティティ
 * </pre>
 */
@Getter
@Setter
public class Pnks04001Item implements Serializable {
	private static final long serialVersionUID = 1L;


	/** メーカーコード */
	private String makerCd;

	/** メーカー名称 */
	private String makerKj;

	/** ＦＡＸ */
	private String faxNo;

	/** ＣＱ企画番号_FROM */
	private String cqKikanoFrom;

	/** ＣＱ企画番号_TO */
	private String cqKikanoTo;

	/** 名称 */
	private String printKj;

	/** システム時間 */
	private String systemTime;

	/** 企画期間（自） */
	private String kikaymdf;

	/** 企画期間（至） */
	private String kikaymdt;
	
	private String kikayminterval;

	/** 価格回答期限 */
	private String kakakKigen;

	/** 納品先事項 */
	private String renrakuJiko;

	/** 納品先名称（北部） */
	private String renrakuKj;

	/** 納品先ＴＥＬ（北部） */
	private String renrakuTel;

	/** 納品先ＦＡＸ（北部） */
	private String renrakuFax;

	/** ＣＱ企画番号 */
	private String cqKikano;

	/** 共同購入商品コード */
	private String kyshinCd;

	/** 等階級コード */
	private String tokaiKyucd;

	/** 産地コード */
	private String santiCd;

	/** 企画年月回 */
	private String sekiKano;
	
	/** 企画年月回 */
	private String sekiKanohide;
	
	private String end;
	
	private String kakakKigenhide;
	
	private String inevitable;
	
	private String SekiKanoWeek;
	
	/** ＣＱ商品コード */
	private String cqShinCd;

	/** ＪＡＮコード */
	private String janCd;

	/** 商品名漢字 */
	private String shinKj;

	/** 規格名漢字 */
	private String kikakKj;

	/** 等階級名称 */
	private String tokaiKyukJ;

	/** 産地名称 */
	private String santiKj;

	/** 加工区分 */
	private String kakokBn;

	/** 加工区分名 */
	private String nohinNm;

	/** 週・特価 */
	private String kikakB;

	/** 集品区分 */
	private String hinonKb;

	/** 集品区分名 */
	private String hinonNm;

	/** エフ */
	private String ehu;

	/** さが */
	private String saga;

	/** ララ */
	private String rara;

	/** おおいた */
	private String oita;

	/** 水光社 */
	private String suikosya;

	/** みやざき */
	private String miyazaki;

	/** かごしま */
	private String kagosima;

	/** おきなわ */
	private String okinawa;

	/** CNTJIGKB */
	private String cntJigkb;



}
