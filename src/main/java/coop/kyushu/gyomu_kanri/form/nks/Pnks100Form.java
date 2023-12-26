//================================================================================
// ID: PNKS100
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks100Form
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/27 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.form.nks;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import coop.kyushu.common.validation.Length;
import coop.kyushu.common.validation.NotEmpty;
import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【農産企画支援】バッチ計画表印刷フォーム
 * </pre>
 */
@Getter
@Setter
public class Pnks100Form implements Serializable {

	@JsonIgnore
	private static final long serialVersionUID = 1L;

	/** 配達年月 */
	@NotEmpty
	@Length(max = 6)
	private String haitatYm;

	/** 配達回 */
	@NotEmpty
	@Length(max = 1)
	private String haitatTimes;
}
