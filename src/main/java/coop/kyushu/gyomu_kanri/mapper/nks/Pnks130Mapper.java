//================================================================================
// ID: PNKS130
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks130Mapper
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/06 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.mapper.nks;

import java.util.List;
import java.util.Map;

import coop.kyushu.gyomu_kanri.entity.nks.Pnks130ListItem;

/**
 * 【農産企画支援】メーカーマスタ一覧マッパー
 */
public interface Pnks130Mapper {

	/* メーカー一覧取得
	 * @param param リクエストデータ
	 * return List<Pnks130ListItem>
	 */
	List<Pnks130ListItem> getMakerListByPnks130(Map<String, Object> param);

	/* メーカー情報更新排他制御
	 * @param makerCd リクエストデータ
	 */
	String getMakerUpdateTimeByPnks130(String string);

	/* ーカー情報取扱停止フラグ更新
	 * @param param リクエストデータ
	 */
	void updateToriteiFlgByPnks130(Map<String, Object> param);

	/* メーカー情報削除
	 * @param param リクエストデータ
	 */
	void deleteMakerByPnks130(List<String> list);

	/* メーカー検索画面の検索前処理
	 * @param param リクエストデータ
	 * @return データ件数 
	 */
	int getInitialDataCntByPnks130(Map<String, Object> map);

}
