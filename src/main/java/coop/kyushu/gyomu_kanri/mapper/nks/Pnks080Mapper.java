//================================================================================
// ID: PNKS080
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks080Mapper
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/17 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.mapper.nks;

import java.util.List;

import coop.kyushu.gyomu_kanri.entity.nks.Pnks080ListItem;

/**
 * 【農産企画支援】バッチ情報一覧マッパー
 */
public interface Pnks080Mapper {

	/**
	 * バッチ情報リスト取得
	 * @return バッチ情報リスト
	 */
	List<Pnks080ListItem> getBatchInfListByPnks080();

	/**
	 * バッチ情報取得
	 * @param backData
	 * @return バッチNO
	 */
	List<Pnks080ListItem> getBatchInfByPnks080(String backData);

	/**
	 * バッチ情報一括削除
	 * @param param バッチNOリスト
	 */
	void deleteBatchInfListByPnks080(List<String> param);

}
