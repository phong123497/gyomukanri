//================================================================================
// ID: PSKC020
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pskc020Mapper
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/11 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.mapper.skc;

import java.util.List;
import java.util.Map;

import coop.kyushu.gyomu_kanri.entity.skc.Pskc020Entity;

/**
 * 【惣菜加工センター】製造計画数取込・出力マッパー
 */
public interface Pskc020Mapper {

	/**
	 * 店舗情報件数取得
	 * @param shopCd 店舗コード
	 * @return 店舗情報件数
	 */
	int getTempoInfoKensuByPskc020(String shopCd);

	/**
	 * 商品情報件数取得
	 * @param cqProjNo CQ商品コード
	 * @return 商品情報件数
	 */
	int getShinInfoKensuByPskc020(String cqProjNo);

	/**
	 * ＣＳＶ出力データ取得
	 * @param param 納品日From 納品日To
	 * @return ＣＳＶ出力データ
	 */
	List<Pskc020Entity> getCsvDateByPskc020(Map<String, Object> param);

	/**
	 * 惣菜製造計画ワーク削除
	 * @param userId ログインユーザID
	 */
	void deleteSozaiSeizoPlanWkByPskc020(String userId);

	/**
	 * 惣菜製造計画ワーク登録
	 * @param param 惣菜製造計画データ
	 */
	void insertSozaiSeizoPlanWkByPskc020(Map<String, Object> param);

	/**
	 * 惣菜製造計画データを登録・更新 
	 * @param param 惣菜製造計画データ
	 */
	void mergeToSozaiSeizoPlanDateByPskc020(Map<String, Object> param);

	/**
	 * 惣菜製造計画データを削除
	 * @param param 納品日From月曜日 納品日To日曜日
	 */
	void deleteSozaiSeizoPlanDateByPskc020(Map<String, Object> param);

	/**
	 * 惣菜対象商品登録
	 * @param param 惣菜対象商品データ
	 */
	void insertSozaiTaishoShohinByPskc020(Map<String, Object> param);

	/**
	 * 惣菜対象店舗登録
	 * @param param 惣菜対象店舗データ
	 */
	void insertSozaiTaishoTenPoByPskc020(Map<String, Object> param);

	/**
	 * 惣菜対象商品ワーク削除
	 * @param param  ログインユーザID
	 */
	void deleteSozaiTaishoShohinWkByPskc020(String userId);

	/**
	 * 惣菜製造商品ワーク登録
	 * @param param 惣菜製造商品ワークデータ
	 */
	void insertSozaiTaishoShohinWkByPskc020(Map<String, Object> param);

	/**
	 * 惣菜対象商品登録・更新
	 * @param param 惣菜製造商品データ
	 */
	void updateSozaiTaishoShohinByPskc020(Map<String, Object> param);

}
