//================================================================================
// ID: PNKS030
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks030Mapper
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/10 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.mapper.nks;

import java.util.List;
import java.util.Map;

import coop.kyushu.gyomu_kanri.entity.com.Nsmt020Entity;
import coop.kyushu.gyomu_kanri.entity.com.Nsmt030Entity;
import coop.kyushu.gyomu_kanri.entity.nks.Pnks030Entity;
import coop.kyushu.gyomu_kanri.entity.nks.Pnks030ShohinInfoItem;
import coop.kyushu.gyomu_kanri.entity.nks.Pnks030TotalItem;

/**
 * 【農産企画支援】農産企画情報入力マッパー
 */
public interface Pnks030Mapper {

	/**
	 * 企画番号と共同購入商品コードより、農産企画商品マスタを取得する。
	 * @param params リクエストデータ
	 * @return Pnks030Entity
	 */
	Pnks030Entity getNousanProjinShinByPnks030(Map<String, Object> params);

	/**
	 * 企画番号と共同購入商品コードより、農産企画商品情報マスタを取得する。
	 * @param params リクエストデータ
	 * @return Pnks030TotalItem
	 */
	List<Pnks030TotalItem> getNousanProjinShinInfoForHeaderByPnks030(Map<String, Object> params);

	/**
	 * 企画番号と共同購入商品コードより、農産企画商品情報マスタを取得する。
	 * @param params リクエストデータ
	 * @return Pnks030ShohinInfoItem
	 */
	List<Pnks030ShohinInfoItem> getNousanProjinShinInfoForIchiranByPnks030(Map<String, Object> params);

	/**
	 * 農産企画商品マスタから検索する。
	 * @param params リクエストデータ
	 * @return Pnks020NousanProjinShin
	 */
	List<Pnks030Entity> getNousanProjinShinListByPnks030(Map<String, Object> params);

	/**
	 * 産地コンボボックス取得。
	 * @param params リクエストデータ
	 * @return NSMT020Entity
	 */
	List<Nsmt020Entity> getSantiCboxByPnks030(Map<String, Object> params);

	/**
	 * 等階級コンボボックス取得。
	 * @param params リクエストデータ
	 * @return NSMT030Entity
	 */
	List<Nsmt030Entity> getTokaikyCboxByPnks030(Map<String, Object> params);

	/**
	 * 画面項目値で農産企画商品更新ワークを登録する。
	 * @param params リクエストデータ
	 */
	void saveNousanProjinShinByPnks030(Map<String, Object> params);

	/**
	 * 画面項目値で農産企画商品更新ワークを削除する。
	 * @param params リクエストデータ
	 */
	void deleteNousanProjinShinByPnks030(Map<String, Object> params);

	/**
	 * 画面項目値でプロシージャ「NSSP020」を呼び出す。
	 * @param params リクエストデータ
	 */
	void nssp020ByPnks030(Map<String, Object> params);
}
