//================================================================================
// ID: PCSK040
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pcsk040Mapper
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/17 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.mapper.csk;

import java.util.List;
import java.util.Map;

import coop.kyushu.gyomu_kanri.entity.csk.Pcsk040Item;

/**
 * 【コープ九州商管】締日登録マッパー
 */
public interface Pcsk040Mapper {

	/**
	 * 営業日を取得する
	 * @param params 検索条件
	 * @return 営業日
	 */
	String getDateByPcsk040();

	/**
	 * 計上年月を取得する
	 * @param params 検索条件
	 * @return  List<Pcsk06001ListItem>計上年月
	 */
	List<Pcsk040Item> getSKBTGFLGByPcsk040();

	/**
	 * その他の条件計上年月を取得する
	 * @param params 検索条件
	 * @return List<Pcsk06001ListItem>計上年月
	 */
	List<Pcsk040Item> getKRBTGFLGByPcsk040();

	/**
	 * 計上年月、商品管理部締日、商品管理部当月度フラグ、経理部締日、経理部当月度フラグを取得する。
	 * @param params 検索条件
	 * @return List<Pcsk06001ListItem>検索用データリスト
	 */
	List<Pcsk040Item> getKYMFByPcsk040(Map<String, Object> params);

	/**
	 * データ更新
	 * @param params 検索条件
	 * @return 影響行数
	 */
	int updateAll(Map<String, Object> param);

}
