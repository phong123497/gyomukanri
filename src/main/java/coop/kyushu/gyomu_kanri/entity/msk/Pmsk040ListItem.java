//================================================================================
// ID: Pmsk040
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pmsk040ListItem
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/09/14 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.entity.msk;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【マスク】企画商品マスタ一覧エンティティ
 * </pre>
 */
@Setter
@Getter
public class Pmsk040ListItem implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 企画番号 */
	private String kikakuNo;

	/** 共同購入商品コード */
	private String kyodoCd;

	/** 会員生協コード */
	private String seiCd;

	/** 商品コード */
	private String shohinCd;

	/** JANコード */
	private String janCd;

	/** 商品名漢字 */
	private String shohinNm;

	/** 規格カナ */
	private String kikakukana;

	/** 原単価 */
	private String gentanka;

	/** 取引先コード */
	private String uritanka;

	/** 大分類 */
	private String daibunrui;

	/** QRC通過区分 */
	private String tsukaKubun;

	/** 集品区分 */
	private String shuhinKubun;

	/** 仕入先コード */
	private String shiiresakiCd;

	/** 発注先コード */
	private String hatyusakiCd;

	/** 商品ルート */
	private String shohinRoute;
}
