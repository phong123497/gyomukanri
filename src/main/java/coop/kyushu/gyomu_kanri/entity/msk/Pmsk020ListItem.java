//================================================================================
// ID: PMSK020
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ProgramName: 【マスタ】名称／区分管理マスタコード一覧
// ClassName: Pmsk020ListItem
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/09/13 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.entity.msk;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【マスタ】名称／区分管理マスタコード一覧用エンティティ
 * </pre>
 */
@Getter
@Setter
public class Pmsk020ListItem implements Serializable{

  private static final long serialVersionUID = 1L;

  // 名称コード
  private String meishoCd;

  // 漢字名称
  private String kanjiMeisho;

  // カナ名称
  private String kanaMeisho;

  // 漢字略称
  private String kanjiRyakusho;

  // カナ略称
  private String kanaRyakusho;

  // 削除フラグ
  private String delFlg;
}
