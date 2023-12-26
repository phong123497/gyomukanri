
//================================================================================
// ID: PMSK030
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理システム
// ClassName: Pmsk030Form
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/09/15 | ＡＭＣ               | 新規作成
//================================================================================

package coop.kyushu.gyomu_kanri.form.msk;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import coop.kyushu.common.validation.Length;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pmsk030Form implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 名称区分 */
	@Length(max = 2)
	private String reckb;
	/** 取得元テーブル */
	private String tblId;
	/** 名称コード */
	@Length(max = 20)
	private String meicd;
	/** 削除フラグ */
	private String delflg;

	/** 漢字名称１ */
	@NotEmpty
	@Length(max = 20)
	private String kjnm;

	/** カナ名称１ */
	@NotEmpty
	@Length(max = 20)
	private String knnm;
	/** 漢字略称２ */
	private String kjrm;
	/** カナ略称２ */
	@Length(max = 20)
	private String knrm;

	/** 漢字名称３ */
	@Length(max = 20)
	private String kjrm3;
	/** カナ名称３ */
	@Length(max = 20)
	private String knrm3;
	/** 属性１ */
	private String zok01;
	/** 属性2 */
	private String zok02;
	/** 属性３ */
	private String zok03;
	/** 属性４ */
	private String zok04;
	/** 属性 5 */
	private String zok05;
	/** 属性６ */
	private String zok06;
	/** 属性７ */
	private String zok07;
	/** 属性８ */
	private String zok08;
	/** 属性９ */
	private String zok09;
	/** 属性１０ */
	private String zok10;
	/** 属性１１ */
	private String zok11;
	/** 属性１２ */
	private String zok12;
	/** 属性１３ */
	private String zok13;
	/** 属性１４ */
	private String zok14;
	/** 属性１５ */
	private String zok15;
	/** 属性１６ */
	private String zok16;
	/** 呼び出し元画面 */
	private String yobidashi;
	/** マスタチェック */
	private String meiCheck; 
}
