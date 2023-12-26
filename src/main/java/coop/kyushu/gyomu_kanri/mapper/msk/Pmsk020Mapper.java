//================================================================================
// ID: PMSK020
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pmsk020Mapper
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/09/13 | ＡＭＣ               | 新規作成
//================================================================================

package coop.kyushu.gyomu_kanri.mapper.msk;

import java.util.List;
import java.util.Map;

import coop.kyushu.gyomu_kanri.entity.msk.Pmsk020ListItem;

/**
 * <pre>
 * 【マスタ】名称／区分管理マスタコード一覧マッパー
 * </pre>
 */
public interface Pmsk020Mapper {
	/**
	 * 名称マスタ件数取得
	 * 
	 * @param params リクエストデータ
	 * @return 名称マスタ件数
	 */
	int getCountByPmsk020(Map<String, Object> params);

	/**
	 * 名称マスタコード一覧取得
	 * 
	 * @param params リクエストデータ
	 * @return Pmsk020Entity
	 */
	List<Pmsk020ListItem> getMeishoCdListByPmsk020(Map<String, Object> params);

	/**
	 * 名称マスタ削除対象件数取得
	 * 
	 * @param params リクエストデータ
	 * @return 名称マスタ削除対象件数
	 */
	
	int getCountDeleteTargetByPmsk020(Map<String, Object> params);

	/**
	 * 名称マスタ管理区分取得
	 * 
	 * @param params リクエストデータ
	 * @return 管理区分
	 */
	// TODO: QA & Check
	int getKanriKbnByPmsk020(Map<String, Object> params);

	/**
	 * 名称マスタ削除
	 * 
	 * @param params リクエストデータ
	 */
	void deleteMstmeismByPmsk020(Map<String, Object> params);

	/**
	 * 区分管理マスタ削除
	 * 
	 * @param params リクエストデータ
	 */
	void deleteMskkbnkrbyPmsk020(Map<String, Object> params);

}
