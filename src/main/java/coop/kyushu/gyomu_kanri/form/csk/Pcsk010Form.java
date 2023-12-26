//================================================================================
// ID: PCSK010
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pcsk010Form
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/22 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.form.csk;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import coop.kyushu.common.validation.NotEmpty;
import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【コープ九州商管】CSV取込フォーム
 * </pre>
 */
@Getter
@Setter
public class Pcsk010Form implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 取込対象 */
	@NotEmpty
	private String torikmTarget;

	/** 諸伝票 */
	private String varDempyo;

	/** 取込CSVファイル */
	@NotEmpty
	private MultipartFile csvFile;
}
