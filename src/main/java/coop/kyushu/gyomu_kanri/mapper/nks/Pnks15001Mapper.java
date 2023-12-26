//================================================================================
// ID: PNKS150
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks150Mapper
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/01 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.mapper.nks;

import java.util.List;
import java.util.Map;

import coop.kyushu.gyomu_kanri.entity.com.Nsmm010Entity;
import coop.kyushu.gyomu_kanri.entity.com.Nstf020Entity;
import coop.kyushu.gyomu_kanri.entity.nks.Pnks15001Item;

/**
 * 【農産企画支援】納品指示書発行指示マッパー
 */
public interface Pnks15001Mapper {

	/**
	 * メーカーコード、配達年月回より、納品指示データ情報取得
	 * @param params リクエストデータ
	 * @return 納品指示データ情報リスト
	 */
	List<Nstf020Entity> getNonyuSiteiDataByPnks150(Map<String, Object> params);

	/**
	 * 管理マスタ取得
	 * @param params リクエストデータ
	 * @return 管理マスタ
	 */
	Nsmm010Entity getkanriInfoByPnks15001(Map<String, Object> params);

	/**
	 * 納品指示データ情報取得
	 * @param params リクエストデータ
	 * @return 納品指示データ情報
	 */
	List<Pnks15001Item> getInfoByPnks15001(Map<String, Object> params);
}
