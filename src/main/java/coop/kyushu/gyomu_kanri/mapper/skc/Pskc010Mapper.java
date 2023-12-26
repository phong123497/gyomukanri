//================================================================================
// ID: PSKC010
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pskc010Mapper
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/11 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.mapper.skc;

import java.math.BigDecimal;
import java.util.Map;

/**
 * <pre>
 * 【惣菜加工センター】値付実績データ取込画面
 * </pre>
 */
public interface Pskc010Mapper {

	/**
	 * 値付実績取込ワークを削除する
	 * 
	 * @param userId ログインユーザーID
	 */
	void deleteWorkTableByPskc010(String userId);

	/**
	 * 受付番号を取得する
	 * 
	 * @param date システム日付
	 * @return 受付番号
	 */
	BigDecimal selectUkeNoByPskc010(String date);

	/**
	 * 名称マスタのカウントを検索する
	 * 
	 * @param param
	 * @return 名称マスタのカウント
	 */
	int selectMstmeismCountByPskc010(Map<String, Object> param);

	/**
	 * 値付実績チェック結果に登録する
	 * 
	 * @param checkParam
	 */
	void insertCheckTableByPskc010(Map<String, Object> checkParam);

	/**
	 * 店舗商品マスタを検索する
	 * 
	 * @param shopParam
	 * @return 店舗商品マスタカウント
	 */
	int selectTenpoShoHinCountByPskc010(Map<String, Object> shopParam);

	/**
	 * 値付累積データカウントを検索する
	 * 
	 * @param params
	 * @return 値付累積データのカウント
	 */
	int selectRzsouzaiRuiCountByPskc010(Map<String, Object> params);

	/**
	 * 値付実績データに登録する
	 * 
	 * @param params
	 */
	void insertSouzaiJdByPskc010(Map<String, Object> params);

	/**
	 * 値付実績当日累積に登録する
	 * 
	 * @param params
	 */
	void insertRzsouzaiTouByPskc010(Map<String, Object> params);

	/**
	 * 挿入済みの値付実績データ数を検索する
	 * 
	 * @param params
	 * @return 取込成功の件数
	 */
	int selectSouzaiJdCountByPskc010(Map<String, Object> params);

	/**
	 * 値付実績取込ワークにデータを登録する
	 * 
	 * @param map 値付実績取込ワーク編集用のデータ
	 */
	void insertWorkTableByPskc010(Map<String, Object> map);

}
