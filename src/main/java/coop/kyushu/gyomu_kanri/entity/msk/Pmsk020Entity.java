//================================================================================
// ID: PMSK020
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ProgramName: 【マスタ】名称／区分管理マスタコード一覧
// ClassName: Pmsk020Entity
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/09/13 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.entity.msk;

import java.io.Serializable;
import java.util.List;

import coop.kyushu.gyomu_kanri.form.msk.Pmsk020Form;
import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【マスタ】名称／区分管理マスタコード一覧用エンティティ
 * </pre>
 */
@Getter
@Setter
public class Pmsk020Entity extends Pmsk020Form implements Serializable {

  private static final long serialVersionUID = 1L;

  // 名称コードリスト
  // TODO
  private List<Pmsk020ListItem> pmsk020ListItems;

  // 件数
  private int cnt;

  // 存在チェック
  private boolean exists;
  
}
