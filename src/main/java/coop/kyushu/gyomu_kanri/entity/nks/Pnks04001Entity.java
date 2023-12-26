//================================================================================
// ID: PNKS040
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks04001Entity
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/13 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.entity.nks;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【農産企画支援】企画案内書印刷エンティティ
 * </pre>
 */
@Getter
@Setter
public class Pnks04001Entity implements Serializable {
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

	/** 企画年月回 */
	private String seKikano;

	/** システム時間 */
	private String systemTime;

	/** 企画期間（自） */
	private String kikaymdf;

	/** 企画期間（至） */
	private String kikaymdt;

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

	/** 明細リスト */
	private List<Pnks04001Item> meisaiList;
}
