//================================================================================
//ID: PNKS110
//ProjectName: コープ九州事業連合様  ホストシステムリプレイス
//SystemName: 業務管理
//ClassName: Pnks11001Item
//
//<<Modification History>>
//Version  | Date       | Updated By           | Content
//---------+------------+----------------------+---------------------------------
//01.00.00 | 2023/08/03 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.entity.nks;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【農産企画支援】調達加工計画書印刷
 * </pre>
 */
@Getter
@Setter
public class Pnks11001Item implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 配達年月回_年 */
	private String haitatuY;

	/** 配達年月回_月 */
	private String haitatuM;

	/** 配達年月回_週 */
	private String haitatuT;

	/** 品温区分 */
	private String hinonKb;

	/** 品温区分名称 */
	private String hinonNm;

	/** 集品区分 */
	private String syuhinKb;

	/** 集品区分名称 */
	private String syuhinNm;

	/** 加工区分 */
	private String kakokBn;

	/** 納品形態名称 */
	private String nohinK;

	/** 共同購入商品コード */
	private String kyshinCd;

	/** 商品名漢字 */
	private String shinKj;

	/** 規格名カナ */
	private String kikakKn;

	/** バッチグループＮｏ */
	private String batchGrpNo;

	/** 等階級名称 */
	private String tokaiKyukJ;

	/** メーカーコード */
	private String makerCd;

	/** メーカー名称 */
	private String makerKj;

	/** 産地名称 */
	private String santiKj;

	/** 納品時容器名称 */
	private String yokinM;

	/** 資材名 */
	private String shizaiKj;

	/** シール貼付有無表示名称 */
	private String sealNm;

	/** 予測点数 */
	private BigDecimal yosokuSu;

}
