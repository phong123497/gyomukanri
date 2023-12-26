//================================================================================
// ID: PMSK050
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ProgramName: 【マスタ】取引先マスタ一覧
// ClassName: Pmsk050Entity
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/09/25 | ＡＭＣ               | 新規作成
//================================================================================

package coop.kyushu.gyomu_kanri.entity.msk;

import java.io.Serializable;
import java.util.List;

import coop.kyushu.gyomu_kanri.form.msk.Pmsk050Form;
import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【マスタ】取引先マスタ一覧用エンティティ
 * </pre>
 */
@Getter
@Setter
public class Pmsk050Entity extends Pmsk050Form implements Serializable {
  
  private static final long serialVersionUID = 1L;

  // 取引先リスト
  private List<Pmsk050ListItem> torihikisakiList;

  // 件数
  private int cnt;
}
