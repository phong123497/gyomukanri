//================================================================================
// ID: Pnks070
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks070Mapper
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/25 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.mapper.nks;

import java.util.List;
import java.util.Map;

import coop.kyushu.gyomu_kanri.entity.nks.Pnks070NousanProjInfShinList;

/**
 * 【農産企画支援】価格取込用データ出力マッパー
 */
public interface Pnks070Mapper {

	/**
	 * 農産企画商品禁則価格チェック
	 * @param params リクエストデータ
	 * @return 件数
	 */
	Integer getNousanProjInfShinByPnks070(Map<String, Object> params);

	/**
	 * 価格取込用データ取得
	 * @param params リクエストデータ
	 * @return 価格取込用データ
	 */
	List<Pnks070NousanProjInfShinList> getNousanProjInfShinDataByPnks070(Map<String, Object> params);
}
