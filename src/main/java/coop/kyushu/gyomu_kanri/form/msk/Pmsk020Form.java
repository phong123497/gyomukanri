//================================================================================
// ID: PMSK020
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ProgramName: 【マスタ】名称／区分管理マスタコード一覧
// ClassName: Pmsk020Form
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/09/13 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.form.msk;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【マスタ】名称／区分管理マスタコード一覧フォーム
 * </pre> 
 */
@Getter
@Setter
public class Pmsk020Form implements Serializable  {

  private static final long serialVersionUID = 1L;

  // 名称区分
  private String meishoKubun;

  // 名称コード
  private String meishoCd;

  // 名称コード名
  private String meishoCdNm;

  // 名称コードリスト - 削除用
  private List<String> meishoCdList;
}
