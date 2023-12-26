//================================================================================
// ID: PNKS020
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks020NousanProjinShin
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/07 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.entity.nks;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【農産企画支援】農産企画情報・商品検索明細エンティティ
 * </pre>
 */
@Getter
@Setter
public class Pnks020NousanProjinShin implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 企画番号 */
	private String cqProjNo;

	/** 共同購入商品コード */
	private String kydKonyShinCd;

	/** 商品名 */
	private String shinNm;

	/** 規格 */
	private String stdd;

	/** エフ */
	private String fcp;

	/** サガ */
	private String sag;

	/** ララ */
	private String lala;

	/** オオ */
	private String oit;

	/** クマ */
	private String kmt;

	/** ミヤ */
	private String myz;

	/** カゴ */
	private String kag;

	/** オキ */
	private String okn;
}
