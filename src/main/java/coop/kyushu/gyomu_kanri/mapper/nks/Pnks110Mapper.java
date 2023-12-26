//================================================================================
//ID: PNKS110
//ProjectName: コープ九州事業連合様  ホストシステムリプレイス
//SystemName: 業務管理
//ClassName: Pnks110Mapper
//
//<<Modification History>>
//Version  | Date       | Updated By           | Content
//---------+------------+----------------------+---------------------------------
//01.00.00 | 2023/08/03 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.mapper.nks;

import java.util.Map;

/**
 * <pre>
 * 【農産企画支援】調達加工計画書印刷
 * </pre>
 */
public interface Pnks110Mapper {

	/**
	 * 調達加工計画の存在チェックを行う
	 * 
	 * @param param センターと配達年月回
	 * @return 調達加工計画の件数
	 */
	int getTyotatsuKakoPlanByPnks110(Map<String, Object> param);

}
