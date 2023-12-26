//================================================================================
// ID: PNKS010
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks010Form
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/10 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.form.nks;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import coop.kyushu.common.validation.NotEmpty;
import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【農産企画支援】PEACEデータ取込フォーム
 * </pre>
 */
@Getter
@Setter
public class Pnks010Form implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 取込対象区分 */
	@NotEmpty
	private String torikmTargetKbn;

	/** 取込CSVファイル */
	@NotEmpty
	private MultipartFile csvFile;
}
