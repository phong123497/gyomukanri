//================================================================================
// ID: PMSK050
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ProgramName: 【マスタ】取引先マスタ一覧
// ClassName: Pmsk050ListItem
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/09/25 | ＡＭＣ               | 新規作成
//================================================================================

package coop.kyushu.gyomu_kanri.entity.msk;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【マスタ】取引先マスタ一覧用エンティティ
 * </pre>
 */
@Getter
@Setter
public class Pmsk050ListItem implements Serializable {

  private static final long serialVersionUID = 1L;

  // 取引先コード
  private String toricd;

  // 取引先名称漢字
  private String torikjnm;

  // 取引先名称カナ
  private String toriknnm;

  // 電話番号
  private String tel;

  // ＦＡＸ番号
  private String fax;

  // 発注区分（現在）
  private String hatukb;

  // 発注区分（予約）
  private String hatukbY;

  // 発注フォーマット（現在）
  private String hatufmkb;

  // 発注フォーマット（予約）
  private String hatufmkbY;

  // 送信先コード（現在）
  private String sosncd;

  // 送信先コード（予約）
  private String sosncdY;

  // 適用開始日（予約）
  private String tekiymd;

}
