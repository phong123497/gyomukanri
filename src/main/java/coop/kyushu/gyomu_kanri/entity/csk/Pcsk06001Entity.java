//================================================================================
// ID: PCSK060
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pcsk06001Entity
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/18 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.entity.csk;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import coop.kyushu.common.validation.Length;
import coop.kyushu.common.validation.NotEmpty;
import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【コープ九州商管】粗利益管理表出力
 * </pre>
 */
@Getter
@Setter
public class Pcsk06001Entity implements Serializable {
	@JsonIgnore
	private static final long serialVersionUID = 1L;

	/** 年度 */
	@NotEmpty
	@Length(max = 4)
	private String nendo;

	/** 月_FROM */
	@NotEmpty
	@Length(max = 2)
	private String monthlyFrom;

	/** 月_TO */
	@NotEmpty
	@Length(max = 2)
	private String monthlyTo;

	/** 作成日 */
	@Length(max = 10)
	private String creDay;

	/** 管理体系区分 */
	private String mngTaikeiKbn;

	/** TOP明細リスト */
	private List<Pcsk06001PdfListItem> topData;
	/** CENTER明細リスト */
	private List<Pcsk06001PdfListItem> centerData;
	/** BOTTOM明細リスト */
	private List<Pcsk06001PdfListItem> bottomData;
}