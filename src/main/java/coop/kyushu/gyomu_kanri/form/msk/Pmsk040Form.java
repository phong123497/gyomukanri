//================================================================================
// ID: Pmsk040
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pmsk040Form
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/09/14 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.form.msk;

import java.io.Serializable;
import java.util.List;

import coop.kyushu.gyomu_kanri.entity.msk.Pmsk040ListItem;
import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【マスク】企画商品マスタ一覧フォーム
 * </pre>
 */
@Getter
@Setter
public class Pmsk040Form implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 企画番号開始 */
	private String kikakuNoStart;

	/** 企画番号終了 */
	private String kikakuNoEnd;

	/** 共同購入商品コード開始 */
	private String kyodoCdStart;

	/** 共同購入商品コード終了 */
	private String kyodoCdEnd;

	/** 会員生協コード開始 */
	private String seiCdStart;

	/** 会員生協コード終了 */
	private String seiCdEnd;

	/** 商品コード */
	private String shohinCd;

	/** JANコード */
	private String janCd;

	/** 大分類 */
	private String daibunrui;

	/** 商品ルート */
	private String shohinRoute;

	/** QRC通過区分 */
	private String tsukaKubun;

	/** 集品区分 */
	private String shuhinKubun;

	/** 発注先コード1 */
	private String hatyusakiCd1;

	/** 発注先コード2 */
	private String hatyusakiCd2;

	/** 仕入先コード1 */
	private String shiiresakiCd1;

	/** 仕入先コード2 */
	private String shiiresakiCd2;
}
