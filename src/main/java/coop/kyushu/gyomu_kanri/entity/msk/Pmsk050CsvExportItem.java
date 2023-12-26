//================================================================================
// ID: PMSK050
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ProgramName: 【マスタ】取引先マスタ一覧のCSV出力情報取得
// ClassName: Pmsk050CsvExportItem
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
 * 【マスタ】取引先マスタ一覧のCSV出力情報エンティティ
 * </pre>
 */
@Getter
@Setter
public class Pmsk050CsvExportItem implements Serializable {

  private static final long serialVersionUID = 1L;

  /** 取引先コード */
  private String toricd;

  /** 取引先名称（漢字） */
  private String torikjnm;

  /** 取引先名称（カナ） */
  private String toriknnm;

  /** 取引先略称（漢字） */
  private String torikjrm;

  /** 取引先略称（カナ） */
  private String toriknrm;

  /** 郵便番号 */
  private String yubin;

  /** 郵便ＮＯ */
  private String yubinno;

  /** 住所１（市・郡） */
  private String jusyo1;

  /** 住所２（町・番地） */
  private String jusyo2;

  /** 住所３（方書） */
  private String jusyo3;

  /** 電話番号 */
  private String tel;

  /** 電話ＮＯ */
  private String telno;

  /** ＦＡＸ番号 */
  private String fax;

  /** ＦＡＸＮＯ */
  private String faxno;

  /** 取引先種別 */
  private String toristb;

  /** 支払締日 */
  private String sihasday;

  /** 支払月数 */
  private String sihagesu;

  /** 支払日（日） */
  private String sihaday;

  /** 請求締日 */
  private String sekysday;

  /** 請求月数 */
  private String sekygesu;

  /** 請求日（日） */
  private String sekyday;

  /** センタフィー（冷凍） */
  private String centfee_rt;

  /** センタフィー（冷蔵） */
  private String centfee_rz;

  /** センタフィー（ドライ） */
  private String centfee_dr;

  /** 物流ＥＤＩ伝票発行区分 */
  private String edi_denhatkb;

  /** 物流ＥＤＩ仕入計上送信区分 */
  private String edi_ssosinkb;

  /** 物流ＥＤＩーＧＬＮ */
  private String edi_gln;

  /** 物流ＥＤＩ開始日 */
  private String edi_strdate;

  /** 物流ＥＤＩ終了日 */
  private String edi_enddate;

  /** 店別明細区分 */
  private String ten_meikb;

  /** 現金仕入区分 */
  private String gensirkb;

  /** 発注区分（冷蔵＋ＭＤ） */
  private String webedi_hatukb;

  /** 発注フォーマット区分（冷蔵＋ＭＤ） */
  private String webedi_hatufmkb;

  /** 送信先コード（冷蔵＋ＭＤ） */
  private String webedi_sosncd;

  /** 出荷報告対象フラグ（冷蔵＋ＭＤ） */
  private String webedi_syukhflg;

  /** 発注区分（冷蔵＋ＭＤ）予約 */
  private String tosu_hatukb;

  /** 発注フォーマット区分（冷蔵＋ＭＤ）予約 */
  private String tosu_hatufmkb;

  /** 送信先コード（冷蔵＋ＭＤ）予約 */
  private String tosu_sosncd;

  /** 出荷報告対象フラグ（冷蔵＋ＭＤ）予約 */
  private String tosu_syukhflg;

  /** 予約適用開始日 */
  private String sikjdayf;

  /** 仕入データ自動確定区分 */
  private String sirjktkb;

  /** 請求対象会員生協識別区分 */
  private String sekksskb;

  /** 営業倉庫区分 */
  private String esokkb;

  /** 出庫指示作成区分 */
  private String sksijikb;

  /** 最終発注単位区分 */
  private String saihatkb;

  /** 取引停止区分 */
  private String toritskb;

}
