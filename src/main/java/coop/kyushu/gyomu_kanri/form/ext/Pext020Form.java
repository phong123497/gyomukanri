//================================================================================
// ID: PEXT020
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ProgramName: 仕入・訂正入力
// ClassName: Pext020Form
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2022/11/28 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.form.ext;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import coop.kyushu.common.validation.Code;
import coop.kyushu.common.validation.Date;
import coop.kyushu.common.validation.Digits;
import coop.kyushu.common.validation.Length;
import coop.kyushu.common.validation.NotEmpty;
import coop.kyushu.common.validation.NotSelect;
import coop.kyushu.gyomu_kanri.entity.ext.Pext020ListItem;
import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 仕入・訂正入力フォーム
 * </pre> 
 */
@Getter
@Setter
public class Pext020Form implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 処理種別 */
	private String procsShubt;

	/** 事業所コード */
	@NotEmpty(groups = { Group.Head1.class, Group.Head1toroku.class })
	@Code(length = 4, groups = { Group.Head1.class, Group.Head1toroku.class })
	private String jigyoCd;

	/** 事業所名 */
	private String jigyoNm;

	/** 店・支部コード */
	private String tenCd;

	/** 店・支部名 */
	private String tenNm;

	/** 分類コード */
	@NotSelect(groups = { Group.Head1.class, Group.Head1toroku.class })
	@Code(length = 2, groups = { Group.Head1.class, Group.Head1toroku.class })
	private String catgCd;

	/** 取引区分 */
	@NotSelect(groups = { Group.Head1toroku.class })
	@Code(length = 2, groups = { Group.Head1toroku.class })
	private String toriKbn;

	/** 伝票番号 */
	@Length(max = 6, groups = { Group.Head1.class, Group.Head1toroku.class })
	private String dempyoNo;

	/** 取引先コード */
	@NotEmpty(groups = { Group.Head1.class, Group.Head1toroku.class })
	@Code(length = 6, groups = { Group.Head1.class, Group.Head1toroku.class })
	private String toriskCd;

	/** 取引先名 */
	private String toriskNm;

	/** 発注日 */
	@NotEmpty(groups = { Group.Head1toroku.class })
	@Date(groups = { Group.Head1toroku.class })
	private BigDecimal hachuDay;

	/** 納品日 */
	@NotEmpty(groups = { Group.Head1.class, Group.Head1toroku.class })
	@Date(groups = { Group.Head1.class, Group.Head1toroku.class })
	private BigDecimal delvDay;

	/** 訂正区分 */
	@NotSelect(groups = { Group.Head2.class })
	@Code(length = 1, groups = { Group.Head2.class })
	private String teiseiKbn;

	/** 伝票発行区分 */
	@NotSelect(groups = { Group.Head2.class })
	@Code(length = 1, groups = { Group.Head2.class })
	private String dempyoIssKbn;

	/** メッセージ */
	@Length(max = 12, groups = { Group.Head2.class })
	private String message;

	/** 実納品日 */
	@NotEmpty(groups = { Group.Head2.class })
	@Date(groups = { Group.Head2.class })
	private BigDecimal jitDelvDay;

	/** 税率 */
	@Digits(integer = 2, groups = { Group.Head2.class })
	private BigDecimal zeiRate;

	/** 通過納品場所コード */
	@NotEmpty(groups = { Group.Head2.class })
	@Code(length = 4, groups = { Group.Head2.class })
	private String tukaDelvPlaceCd;

	/** 通過納品場所名 */
	private String tukaDelvPlaceNm;

	/** 商品明細 */
	@Valid
	private List<Pext020ListItem> shohinList;

	/** 取引先名称（漢字） */
	private String torikjnm;

	/** 取引先名称（カナ） */
	private String toriknnm;

	/** 現金仕入区分 */
	private String gensirkb;

	/** 削除フラグ */
	private String delflg;

	/** 生協名称（漢字） */
	private String seikjnm;

	/** 生協名称（カナ） */
	private String seiknnm;

	/** 店・支部略称（漢字） */
	private String tsbkjrm;

	/** 店・支部略称（カナ） */
	private String tsbknrm;

	/** index */
	private int itemIndex;

	/** 警告リスト */
	private List<String> warningList;

	/* 検証グループ */
	public interface Group {

		interface Head1 {
		}

		interface Head1toroku {
		}

		interface Head2 {
		}
	}
}
