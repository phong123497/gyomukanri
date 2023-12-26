//================================================================================
// ID: PNKS100
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks100Mapper
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/27 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.mapper.nks;

import java.util.List;

import coop.kyushu.gyomu_kanri.entity.nks.Pnks10001ListItem;

/**
 * 【農産企画支援】バッチ計画表印刷マッパー
 */
public interface Pnks1001Mapper {

	/**
	 * 入力された配達年月回を条件に、バッチの存在検索を行う
	 * @param haitatuYmt 配達年月回
	 * @return バッチNoリスト
	 */
	List<String> getBatchNoByPnks100(String haitatuYmt);

	/**
	 * 配達年月と配達回より、バッチ計画表のデータを取得する
	 * @param haitatuYmt 配達年月回
	 * @return バッチ計画表のデータ
	 */
	List<Pnks10001ListItem> getBatchInfoByPnks10001(String haitatuYmt);
}
