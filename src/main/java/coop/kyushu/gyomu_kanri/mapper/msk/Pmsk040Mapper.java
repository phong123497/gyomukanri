//================================================================================
// ID: Pmsk040
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pmsk040Mapper
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/09/14 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.mapper.msk;

import java.util.List;
import java.util.Map;

import coop.kyushu.gyomu_kanri.entity.msk.Pmsk040ListItem;

/**
 * 【マスク】企画商品マスタ一覧マッパー
 */
public interface Pmsk040Mapper {

	/* 企画商品一覧取得
	 * @param param リクエストデータ
	 * return List<Pmsk040ListItem>
	 */
	List<Pmsk040ListItem> getKikakuShohinListByPmsk040(Map<String, Object> param);

	/* 企画商品検索画面の検索前処理
	 * @param param リクエストデータ
	 * @return データ件数
	 */
	int getCountByPmsk040(Map<String, Object> map);

}
