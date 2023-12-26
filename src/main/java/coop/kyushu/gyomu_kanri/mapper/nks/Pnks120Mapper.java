//================================================================================
// ID: PNKS120
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks120Mapper
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/02 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.mapper.nks;

import java.util.List;
import java.util.Map;

import coop.kyushu.gyomu_kanri.entity.nks.Pnks120CntrHikiwtsSEntity;
import coop.kyushu.gyomu_kanri.entity.nks.Pnks120CntrKakoShijiEntity;
import coop.kyushu.gyomu_kanri.entity.nks.Pnks120CntrSireKeijEntity;

/**
 * 【農産企画支援】各種データ出力マッパー
 */
public interface Pnks120Mapper {

	/**
	 * 加工指示データを取得する
	 * @param param リクエストデータ
	 * @return 加工指示データ
	 */
	List<Pnks120CntrKakoShijiEntity> getCntrKakoShijiDataByPnks120(Map<String, Object> param);

	/**
	 * 引渡指示データを取得する
	 * @param param リクエストデータ
	 * @return 引渡指示データ
	 */
	List<Pnks120CntrHikiwtsSEntity> getCntrHikiwtsShijiDataByPnks120(Map<String, Object> param);

	/**
	 * 仕入計上データを取得する
	 * @param param リクエストデータ
	 * @return 仕入計上データ
	 */
	List<Pnks120CntrSireKeijEntity> getCntrSireKeijoDataByPnks120(Map<String, Object> param);

}
