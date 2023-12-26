//================================================================================
// ID: 共通
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: MstkyshmMapper
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/09/12 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.mapper.com;

import java.util.List;
import java.util.Map;

import coop.kyushu.gyomu_kanri.entity.com.MstkyshmEntity;

/**
 * <pre>
 * 【農産企画支援】共通_共同購入商品マスタ マッパー
 * </pre>
 */
public interface MstkyshmMapper {

	/**
	 * 共同購入商品マスタリスト情報取得
	 * @param params リクエストデータ     
	 * @return MstkyshmEntity
	 */
	List<MstkyshmEntity> getKyshmList(Map<String, Object> params);

}
