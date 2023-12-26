//================================================================================
// ID: PEXT020
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pext020Mapper
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2022/11/28 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.mapper.ext;

import java.util.List;
import java.util.Map;

import coop.kyushu.gyomu_kanri.entity.ext.Pext020Entity;
import coop.kyushu.gyomu_kanri.entity.ext.Pext020Items;
import coop.kyushu.gyomu_kanri.entity.ext.Pext020JigyoshoItem;
import coop.kyushu.gyomu_kanri.entity.ext.Pext020ListItem;
import coop.kyushu.gyomu_kanri.entity.ext.Pext020TorihikisakiItem;

/**
 * <pre>
 * 仕入訂正入力マッパー
 * </pre>
 */
public interface Pext020Mapper {

	/**
	 * 店舗発注累積ファイルヘッダ情報取得
	 * @param params リクエストデータ
	 * @return Pext020Entity
	 */
	Pext020Entity getDempyoHead(Map<String, Object> params);

	/**
	 * 店舗発注累積ファイル明細情報取得
	 * @param params リクエストデータ
	 * @return List<Pext020ListItem>
	 */
	List<Pext020ListItem> getDempyo(Map<String, Object> params);

	/**
	 * 店舗発注累積ファイル明細情報登録
	 * @param params リクエストデータ
	 */
	void insertDempyo(Map<String, Object> params);

	/**
	 * 店舗発注累積ファイル明細情報取得
	 * @param params リクエストデータ
	 */
	void deleteDempyo(Map<String, Object> params);

	/**
	 * 取引先名称取得
	 * @param params リクエストデータ
	 * @return Pext020TorihikisakiItem
	 */
	Pext020TorihikisakiItem getTorihikisaki(Map<String, Object> params);

	/**
	 * 事業所名称取得
	 * @param params リクエストデータ
	 * @return Pext020JigyoshoItem
	 */
	Pext020JigyoshoItem getJigyosho(Map<String, Object> params);

	/**
	 * 商品名称取得
	 * @param params リクエストデータ
	 * @return Pext020Items
	 */
	Pext020Items getShohinSingle(Map<String, Object> params);

}
