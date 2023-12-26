//================================================================================
// ID: PMSK050
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pmsk050Mapper
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/09/25 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.mapper.msk;

import java.util.List;
import java.util.Map;

import coop.kyushu.gyomu_kanri.entity.msk.Pmsk050CsvExportItem;
import coop.kyushu.gyomu_kanri.entity.msk.Pmsk050ListItem;

/**
 * <pre>
 * 【マスタ】取引先マスタ一覧マッパー
 * </pre>
 */
public interface Pmsk050Mapper {
  /**
   * 取引先マスタ件数取得
   * 
   * @param params リクエストデータ
   * @return 取引先マスタ件数
   */
  int getCountByPmsk050(Map<String, Object> params);

  /**
   * 取引先マスタ一覧取得
   * 
   * @param params リクエストデータ
   * @return List<Pmsk050ListItem>
   */
  List<Pmsk050ListItem> getTorihikisakiListByPmsk050(Map<String, Object> params);

  /**
   * CSV出力情報取得
   * 
   * @param params リクエストデータ
   * @return List<Pmsk050CsvExportItem>
   */
  List<Pmsk050CsvExportItem> getCsvDataByPmsk050(Map<String, Object> params);
}
