
//================================================================================
// ID: PMSK030
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理システム
// ClassName: Pmsk030Mapper
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/09/15 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.mapper.msk;

import java.util.Map;

import coop.kyushu.gyomu_kanri.entity.msk.Pmsk030Entity;

public interface Pmsk030Mapper {

	/**
	 * 区分管理マスタ取得
	 * @param params リクエストデータ
	 * @return Pmsk030Entity 
	 */

	Pmsk030Entity getKubunByPmsk030(Map<String, Object> params);

	/**
	 * 名称マスタ(VIEW TABLE)取得
	 * @param params リクエストデータ
	 * @return Pmsk030Entity 
	 */

	Pmsk030Entity getMeishoByPmsk030(Map<String, Object> params);

	/**
	 * 名称マスタ登録
	 * @param params リクエストデータ
	 * 
	 */

	void insertMeishoByPmsk030(Map<String, Object> params);

	/**
	 * 名称マスタ登録
	 * @param params リクエストデータ
	 * 
	 */
	/**
	 * 名称マスタ更新
	 * @param params リクエストデータ
	 * 
	 */
	void updateMeishoByPmsk030(Map<String, Object> params);

	/**
	* 名称マスタ登録
	* @param params リクエストデータ
	* 
	*/
	void insertKubunByPmsk030(Map<String, Object> params);

	/**
	 * 区分マスタ更新
	 * @param params リクエストデータ
	 * 
	 */
	void updateKubunByPmsk030(Map<String, Object> params);

}
