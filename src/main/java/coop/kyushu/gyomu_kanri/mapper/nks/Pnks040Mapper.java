//================================================================================
// ID: PNKS040
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks040Mapper
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/13 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.mapper.nks;

import java.util.List;
import java.util.Map;

import coop.kyushu.gyomu_kanri.entity.nks.Pnks04001Item;
import coop.kyushu.gyomu_kanri.entity.nks.Pnks040Entity;

/**
 * 【農産企画支援】企画案内書印刷マッパー
 */
public interface Pnks040Mapper {

	/**
	 * 管理マスタ取得
	 * @return Pnks040Entity 初期データ
	 */
	Pnks040Entity getKanriMstByPnks040();

	/**
	 * 企画案内情報マスタ取得
	 * @param cqkikano  CQ企画番号
	 * @return Pnks040Entity 企画案内情報マスタデータ
	 */
	Pnks040Entity getProjAnnaiInfByPnks040(String cqkikano);

	/**
	 * 企画回マスタ存在チェック
	 * @param cqkikano CQ企画番号
	 * @return int 存在する件数
	 */
	int getCqkikanoByPnks040(String cqkikano);

	/**
	 * 管理マスタ更新
	 * @param param 更新用データ
	 */
	void updateKanriMstByPnks040(Map<String, Object> param);

	/**
	 * 企画案内情報マスタ更新
	 * @param param 更新用データ
	 */
	void updateProjAnnaiInfByPnks040(Map<String, Object> param);

	/**
	 * 企画案内書出力データ取得
	 * @param param 検索用データ
	 * @return List<Pnks04001Item> 企画案内書データ
	 */
	List<Pnks04001Item> getProjAnnaiInfByPnks04001(Map<String, Object> param);
}
