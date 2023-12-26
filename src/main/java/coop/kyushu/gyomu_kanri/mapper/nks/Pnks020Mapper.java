//================================================================================
// ID: PNKS020
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks020Mapper
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/07 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.mapper.nks;

import java.util.List;
import java.util.Map;

import coop.kyushu.gyomu_kanri.entity.nks.Pnks020NousanProjinShin;

/**
 * 【農産企画支援】農産企画情報・商品検索マッパー
 */
public interface Pnks020Mapper {

	/**
	 * 入力された条件に合うデータをサーバーの農産企画商品マスタから検索し表示する。
	 * @param params リクエストデータ
	 * @return 農産企画商品リスト
	 */
	List<Pnks020NousanProjinShin> getNousanProjinShinByPnks020(Map<String, Object> params);

	/**
	 * 入力された条件に合うデータ件数を取得。
	 * @param params リクエストデータ
	 * @return データ件数
	 */
	int getNousanProjinShinCntByPnks020(Map<String, Object> params);
}
