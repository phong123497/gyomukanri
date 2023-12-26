//================================================================================
// ID: PSKC010
//ProjectName: コープ九州事業連合様  ホストシステムリプレイス
//SystemName: 業務管理
//ClassName: Pskc010Form
//
//<<Modification History>>
//Version  | Date       | Updated By           | Content
//---------+------------+----------------------+---------------------------------
//01.00.00 | 2023/08/11 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.form.skc;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import coop.kyushu.common.validation.NotEmpty;
import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【惣菜加工センター】値付実績データ取込画面
 * </pre>
 */
@Getter
@Setter
public class Pskc010Form implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 取込CSVファイル */
	@NotEmpty
	private MultipartFile csvFile;

}
