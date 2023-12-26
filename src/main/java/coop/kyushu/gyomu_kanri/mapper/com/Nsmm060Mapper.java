//================================================================================
// ID: 共通
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Nsmm060Mapper
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/10 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.mapper.com;

import java.util.List;
import java.util.Map;

import coop.kyushu.gyomu_kanri.entity.com.Nsmm060Entity;

/**
 * <pre>
 * 【農産企画支援】共通_メーカーマスタ マッパー
 * </pre>
 */
public interface Nsmm060Mapper {

	/**
	 * メーカーリスト情報取得
	 * @param params リクエストデータ     
	 * @return Nsmm060Entity
	 */
	List<Nsmm060Entity> getMakerList(Map<String, Object> params);

}
