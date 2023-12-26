//================================================================================
// ID: Pmsk051
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理システム
// ProgramName:マスタ
// ClassName: Pmsk051Entity
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/09/15 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.entity.msk;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pmsk051Entity {
  
  // 件数
  private int cnt;
  // 取込結果リスト
  private List<Pmsk051ListItem> resultList;
   
}
