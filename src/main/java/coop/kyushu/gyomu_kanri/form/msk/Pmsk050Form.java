//================================================================================
// ID: PMSK050
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ProgramName: 【マスタ】取引先マスタ一覧
// ClassName: Pmsk050Form
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/09/25 | ＡＭＣ               | 新規作成
//================================================================================

package coop.kyushu.gyomu_kanri.form.msk;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【マスタ】取引先マスタ一覧フォーム
 * </pre> 
 */
@Getter
@Setter
public class Pmsk050Form implements Serializable {

  private static final long serialVersionUID = 1L;

  // 取引先コード開始
  private String toricdStart;
  
  // 取引先コード終了
  private String toricdEnd;

  // 名称漢字
  private String torikjnm;

  // 名称カナ
  private String toriknnm;

  // 発注区分
  private String hatukb;

  // 発注フォーマット
  private String hatufmkb;

  // 送信先コード
  private String sosncd;
}
