//================================================================================
// ID: PCSK010
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pcsk010Mapper
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/22 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.mapper.csk;

import java.util.Map;

/**
 * 【コープ九州商管】CSV取込マッパー
 */
public interface Pcsk010Mapper {

	/**
	 * 排他制御テーブルから使用中フラグを検索する。
	 * @param editCsvMap マスタ識別ＩＤ
	 * @return 使用中フラグ
	 */
	String selectControlStatusByPcsk010(Map<String, Object> editCsvMap);

	/**
	 * 排他制御テーブルから使用状態を更新する。
	 * @param editCsvMap リクエストデータ
	 */
	void updateControlStatusByPskc010(Map<String, Object> editCsvMap);

	/**
	 * ワークテーブルのデータをクリアする。
	 * @param editCsvMap リクエストデータ
	 */
	void deleteWorkTableByPskc010(Map<String, Object> editCsvMap);

	/**
	 * 振替調整データワークにデータを登録する。
	 * @param editCsvMap リクエストデータ
	 * @return 登録件数
	 */
	int insertSKWK0001ByPskc010(Map<String, Object> editCsvMap);

	/**
	 * 廃棄調整データワークにデータを登録する。
	 * @param editCsvMap リクエストデータ
	 * @return 登録件数
	 */
	int insertSKWK0002ByPskc010(Map<String, Object> editCsvMap);

	/**
	 * 在庫調整ワークにデータを登録する。
	 * @param editCsvMap リクエストデータ
	 * @return 登録件数
	 */
	int insertSKWK0003ByPskc010(Map<String, Object> editCsvMap);

	/**
	 * 諸伝票データCOREワークにデータを登録する。
	 * @param editCsvMap リクエストデータ
	 * @return 登録件数
	 */
	int insertSKWK0004ByPskc010(Map<String, Object> editCsvMap);

	/**
	 * 諸伝票データAPワークにデータを登録する。
	 * @param editCsvMap リクエストデータ
	 * @return 登録件数
	 */
	int insertSKWK0005ByPskc010(Map<String, Object> editCsvMap);

	/**
	 * 予算データワークにデータを登録する。
	 * @param editCsvMap リクエストデータ
	 * @return 登録件数
	 */
	int insertSKWK0008ByPskc010(Map<String, Object> editCsvMap);

	/**
	 * 振替調整ワークテーブルのデータをテーブルに登録する。
	 * @param procedureParamMap プロシージャ用のパラメータ
	 */
	void createSKWK0001ByPskc010(Map<String, Object> procedureParamMap);

	/**
	 * 廃棄調整ワークテーブルのデータをテーブルに登録する。
	 * @param procedureParamMap プロシージャ用のパラメータ
	 */
	void createSKWK0002ByPskc010(Map<String, Object> procedureParamMap);

	/**
	 * 在庫調整ワークテーブルのデータをテーブルに登録する。
	 * @param procedureParamMap プロシージャ用のパラメータ
	 */
	void createSKWK0003ByPskc010(Map<String, Object> procedureParamMap);

	/**
	 * 諸伝票データCOREワークテーブルのデータをテーブルに登録する。
	 * @param procedureParamMap プロシージャ用のパラメータ
	 */
	void createSKWK0004ByPskc010(Map<String, Object> procedureParamMap);

	/**
	 * 諸伝票データAPワークテーブルのデータをテーブルに登録する。
	 * @param procedureParamMap プロシージャ用のパラメータ
	 */
	void createSKWK0005ByPskc010(Map<String, Object> procedureParamMap);

	/**
	 * 予算ワークテーブルのデータをテーブルに登録する。
	 * @param procedureParamMap プロシージャ用のパラメータ
	 */
	void createSKWK0008ByPskc010(Map<String, Object> procedureParamMap);
}
