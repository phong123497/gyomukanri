//================================================================================
// ID: PMSK050
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ProgramName: 【マスタ】取引先マスタ一覧
// ClassName: Pmsk050Service
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/09/25 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.service.msk;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import coop.kyushu.common.util.TantoshaInfoUtil;
import coop.kyushu.gyomu_kanri.entity.msk.Pmsk050CsvExportItem;
import coop.kyushu.gyomu_kanri.entity.msk.Pmsk050Entity;
import coop.kyushu.gyomu_kanri.form.msk.Pmsk050Form;
import coop.kyushu.gyomu_kanri.mapper.msk.Pmsk050Mapper;
import coop.kyushu.common.util.CsvUtil;
import coop.kyushu.common.util.FileDownloadUtil;

@Service
public class Pmsk050Service {
  /* 一時的にCSVファイルの保存パス */
  @Value("${GYOMU_REPORT_TEMP_PATH}")
  private String tempCsvFilePath;

  // 【マスタ】取引先マスタ一覧マッパー
  @Autowired
  private Pmsk050Mapper pmsk050Mapper;

  /* 空白文字列 */
  private final static String BLANK_MARK = "";

  /* 帳票ID */
  private final static String CHOHYO_ID = "PMSK050";

  /* 担当者情報 */
  @Autowired
  private TantoshaInfoUtil tantoshaInfoUtil;

  /* ファイルをダウンロードする共通部品 */
	@Autowired
	private FileDownloadUtil fileDownloadUtil;

  /* CSVタイトル */
  private final static List<String> CSV_TITLE = List.of("取引先コード", "取引先名称（漢字）", "取引先名称（カナ）", "取引先略称（漢字）", "取引先略称（カナ）",
      "郵便番号", "郵便ＮＯ",
      "住所１（市・郡）", "住所２（町・番地）", "住所３（方書）", "電話番号", "電話ＮＯ", "ＦＡＸ番号", "ＦＡＸＮＯ", "取引先種別", "支払締日", "支払月数", "支払日（日）", "請求締日",
      "請求月数", "請求日（日）",
      "センタフィー（冷凍）", "センタフィー（冷蔵）", "センタフィー（ドライ）", "物流ＥＤＩ伝票発行区分", "物流ＥＤＩ仕入計上送信区分", "物流ＥＤＩーＧＬＮ", "物流ＥＤＩ開始日", "物流ＥＤＩ終了日",
      "店別明細区分", "現金仕入区分", "発注区分（冷蔵＋ＭＤ）", "発注フォーマット区分（冷蔵＋ＭＤ）", "送信先コード（冷蔵＋ＭＤ）", "出荷報告対象フラグ（冷蔵＋ＭＤ）", "発注区分_予約",
      "発注フォーマット区分_予約",
      "送信先コード_予約", "出荷報告対象フラグ_予約", "適用開始日＿予約", "仕入データ自動確定区分", "請求対象会員生協識別区分", "営業倉庫区分", "出庫指示作成区分", "最終発注単位区分",
      "取引停止区分");

  /**
   * 取引先マスタ件数取得
   * 
   * @return Pmsk050Entity
   */
  public Pmsk050Entity getCount(Pmsk050Form pmsk050Form) {
    Pmsk050Entity pmsk050Entity = new Pmsk050Entity();
    // 取引先マスタの件数を取得する。
    Map<String, Object> params = new HashMap<>();
    params.put("toricdStart", pmsk050Form.getToricdStart());
    params.put("toricdEnd", pmsk050Form.getToricdEnd());
    params.put("torikjnm", pmsk050Form.getTorikjnm());
    params.put("toriknnm", pmsk050Form.getToriknnm());
    params.put("hatukb", pmsk050Form.getHatukb());
    params.put("hatufmkb", pmsk050Form.getHatufmkb());
    params.put("sosncd", pmsk050Form.getSosncd());
    // サービスの戻り値に以下を設定して処理を終了する。
    pmsk050Entity.setCnt(pmsk050Mapper.getCountByPmsk050(params));
    return pmsk050Entity;
  }

  /**
   * 取引先マスタ一覧取得
   * 
   * @return Pmsk050Entity
   */
  public Pmsk050Entity getTorihikisakiList(Pmsk050Form pmsk050Form) {
    Pmsk050Entity pmsk050Entity = new Pmsk050Entity();
    // 「一覧検索」サービスとして、以下の内容を実行する。
    Map<String, Object> params = new HashMap<>();
    params.put("toricdStart", pmsk050Form.getToricdStart());
    params.put("toricdEnd", pmsk050Form.getToricdEnd());
    params.put("torikjnm", pmsk050Form.getTorikjnm());
    params.put("toriknnm", pmsk050Form.getToriknnm());
    params.put("hatukb", pmsk050Form.getHatukb());
    params.put("hatufmkb", pmsk050Form.getHatufmkb());
    params.put("sosncd", pmsk050Form.getSosncd());
    // サービスの戻り値に以下を設定して処理を終了する。
    pmsk050Entity.setTorihikisakiList(pmsk050Mapper.getTorihikisakiListByPmsk050(params));
    return pmsk050Entity;
  }

  /**
   * CSV出力情報取得
   * 
   * @return -
   */
  public void outputCsv(Pmsk050Form pmsk050Form) throws IllegalAccessException {
    // 取引先マスタよりCSV出力情報を取得する。
    Map<String, Object> params = new HashMap<>();
    params.put("toricdStart", pmsk050Form.getToricdStart());
    params.put("toricdEnd", pmsk050Form.getToricdEnd());
    params.put("torikjnm", pmsk050Form.getTorikjnm());
    params.put("toriknnm", pmsk050Form.getToriknnm());
    params.put("hatukb", pmsk050Form.getHatukb());
    params.put("hatufmkb", pmsk050Form.getHatufmkb());
    params.put("sosncd", pmsk050Form.getSosncd());
    List<Pmsk050CsvExportItem> exportItems = pmsk050Mapper.getCsvDataByPmsk050(params);
    // CSV出力用に以下の型の変数を宣言する。
    List<List<String>> csvItemList = this.handleListEntityData(exportItems);
    // CSVのタイトル部を設定する。
    csvItemList.add(0, CSV_TITLE);
    // CSVファイルを作成する
    String loginUserId = StringUtils.trimAllWhitespace(tantoshaInfoUtil.getTansCd());
    String serverFilePath = CsvUtil.createCsv(tempCsvFilePath, loginUserId, CHOHYO_ID, csvItemList, new String("SJIS"));
    // システム時間を取得する
		String systemDataStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    // CSV名を作成する
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("取引先データ_");
		stringBuffer.append(systemDataStr);
		stringBuffer.append(".csv");
    // CSVファイルをダウンロードする
		fileDownloadUtil.download(serverFilePath, stringBuffer.toString());
  }

  /**
   * List<Entity>からList<List<String>>への変換ツールメソッド
   * 
   * @param dataList List<Entity>型のデータ
   * @return List<List<String>>型のデータ
   * @throws IllegalAccessException
   */
  private List<List<String>> handleListEntityData(List<?> dataList) throws IllegalAccessException {

    List<List<String>> strList = new ArrayList<>();
    List<String> valueList;
    Field[] fields;

    for (Object data : dataList) {

      valueList = new ArrayList<>();
      // 全てのフィールドを取得する
      fields = data.getClass().getDeclaredFields();

      for (Field field : fields) {

        // @JsonIgnoreアノテーションがある場合、処理しない
        if (field.isAnnotationPresent(JsonIgnore.class)) {
          continue;
        }

        // 強制的にフィールドをアクセスする
        field.setAccessible(true);
        // フィールドの値を取得する
        valueList.add(Optional.ofNullable(field.get(data)).orElse(BLANK_MARK).toString());
      }
      strList.add(valueList);
    }

    return strList;
  }
}
