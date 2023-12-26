//================================================================================
// ID: PSKC020
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pskc020Form
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/11 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.form.skc;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import coop.kyushu.common.validation.Code;
import coop.kyushu.common.validation.Length;
import coop.kyushu.common.validation.NotEmpty;
import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【惣菜加工センター】製造計画数取込・出力フォーム
 * </pre>
 */
@Getter
@Setter
public class Pskc020Form implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 処理区分 */
	@NotEmpty(groups = Pskc020Group.OutputGroup.class)
	private String procsKbn;

	/** 納品日Ｆｒｏｍ */
	@NotEmpty(groups = Pskc020Group.OutputGroup.class)
	@Length(max = 8, groups = Pskc020Group.OutputGroup.class)
	@Code
	private String delvDayFrom;

	/** 納品日Ｔｏ */
	@NotEmpty(groups = { Pskc020Group.PlansuGroup.class, Pskc020Group.WeeksiteiGroup.class })
	@Length(max = 8, groups = { Pskc020Group.PlansuGroup.class, Pskc020Group.WeeksiteiGroup.class })
	@Code
	private String delvDayTo;

	/** 取込CSVファイル */
	@NotEmpty(groups = { Pskc020Group.PlansuGroup.class, Pskc020Group.ShinkaihaiGroup.class })
	private MultipartFile csvFile;

	/* 検証グループ */
	public interface Pskc020Group {
		/* 検査が必要な項目 */
		interface OutputGroup {

		}

		/* 製造計画数取込・出力の出力（週指定）処理 */
		interface WeeksiteiGroup {

		}

		/* 製造計画数取込・出力の取込（計画数）処理 */
		interface PlansuGroup {

		}

		/* 製造計画数取込・出力の取込（商品改廃）処理 */
		interface ShinkaihaiGroup {

		}
	};
}
