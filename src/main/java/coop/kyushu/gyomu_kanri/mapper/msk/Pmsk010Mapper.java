//================================================================================
// ID: PMSK010
// ProjectName: 【マスタ】名称／区分管理マスタ区分一覧
// SystemName: 名称／区分管理マスタ区分一覧
// ClassName: PMSK010Mapper
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// 01.00.00 | 2023/09/14 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.mapper.msk;

import java.util.List;
import java.util.Map;

import coop.kyushu.gyomu_kanri.entity.msk.Pmsk010ListItem;

/**
 * <pre>
 * 【マスタ】名称／区分管理マスタ区分一覧
 * </pre>
 */
public interface Pmsk010Mapper {
	/**
	 * 名称マスタ件数取得
	 * @param params 入力内容
	 * @return データ件数
	 */
    int getCountByPmsk010(Map<String, Object> params);
	/**
	 * 名称マスタ件数取得
	 * @param params 入力内容
	 * @return 初期ページ(Pnks010Entity)
	 */
	 List<Pmsk010ListItem> getMeishoKubunListByPmsk010(Map<String,Object> params);
}
