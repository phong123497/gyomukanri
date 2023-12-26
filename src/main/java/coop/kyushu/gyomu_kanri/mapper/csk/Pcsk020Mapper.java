//================================================================================
// ID: PCSK020
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pcsk020Mapper
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/18 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.mapper.csk;

import java.util.List;
import java.util.Map;

import coop.kyushu.gyomu_kanri.entity.csk.Pcsk020FurikaeDempyoItem;
import coop.kyushu.gyomu_kanri.entity.csk.Pcsk020HaikiDempyoItem;
import coop.kyushu.gyomu_kanri.entity.csk.Pcsk020VarDempyoApItem;
import coop.kyushu.gyomu_kanri.entity.csk.Pcsk020VarDempyoCoreItem;
import coop.kyushu.gyomu_kanri.entity.csk.Pcsk020ZkChsDempyoItem;

/**
 * 【コープ九州商管】CSV出力マッパー
 */
public interface Pcsk020Mapper {

	/**
	 * 諸伝票（CORE分）データを取得する
	 * @param params リクエストデータ
	 * @return 諸伝票（CORE分）データ
	 */
	List<Pcsk020VarDempyoCoreItem> getPcsk020VarDempyoCoreItem(Map<String, Object> params);

	/**
	 * 諸伝票（AP分）データを取得する
	 * @param params リクエストデータ
	 * @return 諸伝票（AP分）データ
	 */
	List<Pcsk020VarDempyoApItem> getPcsk020VarDempyoApItem(Map<String, Object> params);

	/**
	 * 在庫調整データを取得する
	 * @param params リクエストデータ
	 * @return 在庫調整データ
	 */
	List<Pcsk020ZkChsDempyoItem> getPcsk020ZkChsDempyoItem(Map<String, Object> params);

	/**
	 * 振替伝票データを取得する
	 * @param params リクエストデータ
	 * @return 振替伝票データ
	 */
	List<Pcsk020FurikaeDempyoItem> getPcsk020FurikaeDempyoItem(Map<String, Object> params);

	/**
	 * 廃棄伝票データを取得する
	 * @param params リクエストデータ
	 * @return 廃棄伝票データ
	 */
	List<Pcsk020HaikiDempyoItem> getPcsk020HaikiDempyoItem(Map<String, Object> params);
}
