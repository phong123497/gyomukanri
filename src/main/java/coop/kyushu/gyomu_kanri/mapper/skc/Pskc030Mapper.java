//================================================================================
// ID: PSKC030
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pskc030Mapper
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/11 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.mapper.skc;

import java.util.List;
import java.util.Map;

import coop.kyushu.gyomu_kanri.entity.com.Sztr102Entity;
import coop.kyushu.gyomu_kanri.entity.com.Szvw102Entity;
import coop.kyushu.gyomu_kanri.entity.skc.Rskc03001ListItem;

/**
 * 【惣菜加工センター】製造計画数入力マッパー
 */
public interface Pskc030Mapper {

	/**
	 * 初期処理でプルダウン店リストを取得する。
	 * @return 店リスト
	 */
	List<Szvw102Entity> getMiseListByPskc030();

	/**
	 * 初期処理でプルダウン納品日リストを取得する。
	 * @return 納品日リスト
	 */
	List<Sztr102Entity> getNhinymdListByPskc030();

	/**
	 * 店舗、表示週、表示順と昇順降順より、対象の製造計画数情報を取得する。
	 * @param params リクエストデータ
	 * @return 製造計画数情報リスト
	 */
	List<Rskc03001ListItem> getSosaiTaishoTenpoByPskc030(Map<String, Object> param);

	/**
	 * 店コード、納品日、CQ商品コード、計画数、納品数とユーザーIDより、製造計画数情報を更新、または登録する。
	 * @param params リクエストデータ
	 * @return 更新、または登録件数 
	 */
	int insertUpdateSousaiSeizouKeikakuDataByPskc030(Map<String, Object> param);

	/**
	 * 製造計画データ削除する。
	 * @param params リクエストデータ
	 * @return 削除件数 
	 */
	int deleteSousaiSeizouKeikakuDataByPskc030(Map<String, Object> param);

	/**
	 * 対象店舗登録する。
	 * @param params リクエストデータ
	 * @return 登録件数 
	 */
	int insertSosaiTaishoTenpoByPskc030(Map<String, Object> param);

}
