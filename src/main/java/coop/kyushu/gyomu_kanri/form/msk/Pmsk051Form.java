//================================================================================
// ID: PMSK051
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pmsk051Form
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/09/26 | ＡＭＣ               | 新規作成
//================================================================================


package coop.kyushu.gyomu_kanri.form.msk;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import coop.kyushu.common.validation.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pmsk051Form implements Serializable{
  private static final long serialVersionUID = 1L;
  
  // ** 取込CSVファイル */
  @NotEmpty
  private MultipartFile csvFile;
} 
