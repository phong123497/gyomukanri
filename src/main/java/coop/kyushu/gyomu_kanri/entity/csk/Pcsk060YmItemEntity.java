//================================================================================
// ID: PCSK060
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: SKTMM0001
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/18 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.entity.csk;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【コープ九州商管】粗利益管理表出力
 * </pre>
 */
@Getter
@Setter
public class Pcsk060YmItemEntity implements Serializable {
	@JsonIgnore
	private static final long serialVersionUID = 1L;
	/** 計上年月 */
	private String kym;

	/** 年度 */
	private String nendo;

}
