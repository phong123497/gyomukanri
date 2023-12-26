//================================================================================
// ID: PNKS010
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks010Mapper
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/10 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.mapper.nks;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

/**
 * 【農産企画支援】PEACEデータ取込マッパー
 */
public interface Pnks010Mapper {

	/**
	 * PEACEデータ取込ワークを削除する
	 * @param loginUserId ログインユーザーID
	 */
	void deletePeaceDataWorkByPnks010(String loginUserId);

	/**
	 * 共同購入商品マスタデータを取得する
	 * @param shoHinCodeList 共同購入商品コードリスト
	 * @return 共同購入商品マスタデータ
	 */
	@MapKey("KYSHINCD")
	Map<String, Map<String, Object>> getDWHInfoByPnks010(@Param("shoHinCodeList") List<String> shoHinCodeList);

	/**
	 * PEACEデータ取込ワークにデータを登録する
	 * @param editCsvMap 編集済みのCSVファイルから取り込んだデータ
	 */
	void insertPeaceDataWorkByPnks010(Map<String, Object> editCsvMap);

	/**
	 * 企画小枝番リストを取得する
	 * @param loginUserId ログインユーザーID
	 * @return 企画小枝番リスト
	 */
	List<String> getKikaEdaListByPnks010(String loginUserId);

	/**
	 * 農産企画商品マスタを作成する
	 * @param procedureParamMap プロシージャ用のパラメータ
	 */
	void createGoodsMasterByPnks010(Map<String, Object> procedureParamMap);
}
