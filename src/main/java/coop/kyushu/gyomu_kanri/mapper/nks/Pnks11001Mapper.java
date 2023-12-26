package coop.kyushu.gyomu_kanri.mapper.nks;

import java.util.List;
import java.util.Map;

import coop.kyushu.gyomu_kanri.entity.nks.Pnks11001Item;

/**
 * <pre>
 * 【農産企画支援】調達加工計画書印刷
 * </pre>
 */
public interface Pnks11001Mapper {

	/**
	 * 調達加工計画出力データ取得
	 * 
	 * @param param センターと配達年月回
	 * @return 調達加工計画出力データ
	 */
	List<Pnks11001Item> getTyotatsuKakoPlanDataByPnks11001(Map<String, Object> param);

}
