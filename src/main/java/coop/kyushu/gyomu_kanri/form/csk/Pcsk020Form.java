//================================================================================
// ID: PCSK020
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pcsk020Form
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/18 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.form.csk;

import java.io.Serializable;

import coop.kyushu.common.validation.HalfWidth;
import coop.kyushu.common.validation.Length;
import coop.kyushu.common.validation.NotEmpty;
import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【コープ九州商管】CSV出力フォーム
 * </pre>
 */
@Getter
@Setter
public class Pcsk020Form implements Serializable {
	private static final long serialVersionUID = 1L;

	/** ファイル選択 */
	@NotEmpty
	@HalfWidth
	@Length(max = 4)
	private String fileSelect;

	/** 諸伝票区分 */
	private String varDempyoKbn;

	/** 伝票日付_FROM */
	@Length(max = 10)
	private String dempyoHizukeFrom;

	/** 伝票日付_TO */
	@Length(max = 10)
	private String dempyoHizukeTo;

	/** 月度 */
	@Length(max = 7)
	private String monthly;

	/** センター_FROM */
	@Length(max = 5)
	private String cntrFrom;

	/** センター_TO */
	@Length(max = 5)
	private String cntrTo;

	/** 出庫センター_FROM */
	@Length(max = 5)
	private String shukkoCntrFrom;

	/** 出庫センター_TO */
	@Length(max = 5)
	private String shukkoCntrTo;

	/** 入庫センター_FROM */
	@Length(max = 5)
	private String nyukoCntrFrom;

	/** 入庫センター_TO */
	@Length(max = 5)
	private String nyukoCntrTo;

	/** 事業所区分 */
	@Length(max = 1)
	private String jigyoKbn;

	/** 分類_FROM */
	@Length(max = 2)
	private String catgFrom;

	/** 分類_TO */
	@Length(max = 2)
	private String catgTo;

	/** 商品コード_FROM */
	@Length(max = 9)
	private String shinCdFrom;

	/** 商品コード_TO */
	@Length(max = 9)
	private String shinCdTo;

	/** 登録日_FROM */
	@Length(max = 10)
	private String insDayFrom;

	/** 登録日_TO */
	@Length(max = 10)
	private String insDayTo;

	/** 登録者_FROM */
	@Length(max = 20)
	private String insUserFrom;

	/** 登録者_TO */
	@Length(max = 20)
	private String insUserTo;
}
