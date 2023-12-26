//================================================================================
// ID: PCSK050
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pcsk050Mapper
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/18 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.mapper.csk;

import java.util.List;
import java.util.Map;

import coop.kyushu.gyomu_kanri.entity.csk.Pcsk05001Entity;

/**
 * 【コープ九州商管】センター商管表出力マッパー
 */
public interface Pcsk050Mapper {

	/**
	 * 単月単品集計取得する。
	 * @param param リクエストデータ
	 * @return 単月単品集計リスト
	 */
	List<Pcsk05001Entity> getTangetuMakeTanpinByPcsk050(Map<String, Object> params);

	/**
	 * 単月集計取得する。
	 * @param param リクエストデータ
	 * @return 単月集計処理リスト
	 */
	List<Pcsk05001Entity> getTangetuMakeByPcsk050(Map<String, Object> params);

	/**
	 * 期間単品集計取得する。
	 * @param param リクエストデータ
	 * @return 期間単品集計処理リスト
	 */
	List<Pcsk05001Entity> getKikanMakeTanpinByPcsk050(Map<String, Object> params);

	/**
	 * 期間集計取得する。
	 * @param param リクエストデータ
	 * @return 期間集計処理リスト
	 */
	List<Pcsk05001Entity> getKikanMakeByPcsk050(Map<String, Object> params);

}
