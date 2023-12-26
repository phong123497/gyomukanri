//================================================================================
// ID: PCSK060
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pcsk060Mapper
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/18 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.mapper.csk;

import java.util.List;
import java.util.Map;

import coop.kyushu.gyomu_kanri.entity.csk.Pcsk06001ListItem;
import coop.kyushu.gyomu_kanri.entity.csk.Pcsk06001PdfListItem;
import coop.kyushu.gyomu_kanri.entity.csk.Pcsk060YmItemEntity;

/**
 * 【コープ九州商管】PCSK060_粗利益管理表出力
 */
public interface Pcsk060Mapper {
	/**
	 * 粗利益管理表CSV出力用データを取得する
	 * @param params 検索条件
	 * @return List<Pcsk06001ListItem>出力データリスト
	 */
	List<Pcsk06001ListItem> getPrintDataByPcsk060(Map<String, Object> params);

	/**
	 * 粗利益管理表PDF出力用データを取得する
	 * @param params 検索条件
	 * @return List<Pcsk06001ListItem>出力データリスト
	 */
	List<Pcsk06001PdfListItem> getPrintPdfDataByPcsk060(Map<String, Object> params);

	/**
	 * 運用日の年月度を取得
	 * @param params 検索条件
	 * @return List<SKTMM0001>運用日の年月度データ
	 */
	List<Pcsk060YmItemEntity> getNendoByPcsk060();

	/**
	 * 運用日以前の年度を取得
	 * @param params 検索条件
	 * @return List<SKTMM0001>運用日以前の年度データ
	 */
	List<Pcsk060YmItemEntity> getBeforeNendoByPcsk060(Map<String, Object> params);

	/**
	 * 運用日の月度を取得
	 * @param params 検索条件
	 * @return List<SKTMM0001>運用日の月度を取得データ
	 */
	List<Pcsk060YmItemEntity> getGetsudoByPcsk060();

	/**
	 * 運用日以前の月度取得
	 * @param params 検索条件
	 * @return List<Pcsk06001ListItem>運用日以前の月度データ
	 */
	List<Pcsk060YmItemEntity> getBeforeGetsudoByPcsk060(Map<String, Object> params);
}
