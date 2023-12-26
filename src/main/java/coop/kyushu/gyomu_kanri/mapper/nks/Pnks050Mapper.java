//================================================================================
// ID: PNKS050
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks050Mapper
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/26 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.mapper.nks;

import java.util.List;
import java.util.Map;

import coop.kyushu.gyomu_kanri.entity.nks.Pnks05001ListItem;

/**
 * 【農産企画支援】価格表印刷マッパー
 */
public interface Pnks050Mapper {
	/**
	 * 農産企画商品情報マスタ情報取得
	 * @param param リクエストデータ
	 * @return 農産企画商品情報マスタ情報件数
	 */
	Integer getCommodityInfoByPnks050(Map<String, Object> param);

	/**
	 * 価格表印刷用のデータ取得
	 * @param param リクエストデータ
	 * @return 価格表印刷用のデータ
	 */
	List<Pnks05001ListItem> getPrintInfoByPnks05001(Map<String, Object> param);
}
