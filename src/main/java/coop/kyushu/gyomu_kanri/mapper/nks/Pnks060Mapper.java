//================================================================================
// ID: PNKS060
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks060Mapper
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/06 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.mapper.nks;

import java.util.List;
import java.util.Map;

import coop.kyushu.gyomu_kanri.entity.nks.Pnks060ListItem;

/**
 * 【農産企画支援】シミュレーション用データ出力マッパー
 */
public interface Pnks060Mapper {

	/**
	 * シミュレーション用データを取得する
	 * @param params 検索条件
	 * @return List<Pnks060ListItem> CSVデータリスト
	 */
	List<Pnks060ListItem> getSimulationDataByPnks060(Map<String, Object> params);
}
