
//================================================================================
// ID: Pmsk051
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理システム
// ClassName: Pmsk051Mapper
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/09/26 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.mapper.msk;

import java.util.Map;

public interface Pmsk051Mapper {

	/**
	 * 名称マスタチェック
	 * @param  
	*  @return String 
	 */
	Integer checkMeishoByPmsk051(Map<String, Object> params);

	/**
	 * 取引先マスタチェック
	 * @param  
	*  @return String
	 */
	String checkTorihikisakiByPmsk051(Map<String, Object> params);

	/**
	 * 取引先マスタに情報を登録する。
	 * @param params
	 */
	void insertByPmsk051(Map<String, Object> params);

	/**
	 * 取引先マスタで削除済データを再更新する。
	 * @param params
	 */

	void reinsertByPmsk051(Map<String, Object> params);

	/**
	 * 取引先マスタを更新する。
	 * @param params
	 */
	void updateByPmsk051(Map<String, Object> params);

}
