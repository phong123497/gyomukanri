//================================================================================
// ID: PNKS090
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks090Mapper
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/14 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.mapper.nks;

import java.util.Map;

import coop.kyushu.gyomu_kanri.entity.nks.Pnks090Entity;

/**
 * <pre>
 * 【農産企画支援】バッチ情報メンテナンスマッパー
 * </pre>
 */
public interface Pnks090Mapper {
	/**
	 * バッチマスタ情報取得
	 * @param params リクエストデータ
	 * @return Pnks090Entity
	 */
	Pnks090Entity getBatchInfByPnks090(Map<String, Object> param);

	/**
	 * バッチマスタ情報登録
	 * @param param リクエストデータ
	 */
	void insertBatchInfByPnks090(Map<String, Object> param);

	/**
	 * バッチマスタ情報更新
	 * @param param リクエストデータ
	 */
	void updateBatchInfByPnks090(Map<String, Object> param);
}
