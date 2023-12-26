//================================================================================
// ID: Pmsk051
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ProgramName:マスタ
// ClassName:Pmsk051ListItem
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/09/15 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.entity.msk;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Pmsk051ListItem {
  
  public Pmsk051ListItem(List<String> csvRow) {
  }
  // ファイル名
  private String  fileNm;
  // 取込結果
  private String  torikmReslt;
  // 行番号
  private String  gyoNo;
  //エラー内容
  private String  errContents;
}
