//================================================================================
// ID: PNKS010
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks010Service
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/10 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.service.nks;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import coop.kyushu.gyomu_kanri.entity.nks.Pnks010ListItem;
import coop.kyushu.gyomu_kanri.mapper.nks.Pnks010Mapper;

/**
 * <pre>
 * 【農産企画支援】PEACEデータ取込に関わるプロシージャサービス
 * </pre>
 */
@Service
public class Pnks010ProcedureService {

	/**
	 * PEACEデータ取込マップ
	 */
	@Autowired
	private Pnks010Mapper mapper;

	/**
	 * プロシージャNSSP010を実行する
	 * @param userId ユーザーID
	 * @param viewId 実行する画面ID
	 * @return プロシージャ呼び出し結果エンティティ
	 */
	@Transactional(rollbackFor = Exception.class)
	public Pnks010ListItem execProcedureNSSP010(String userId, String viewId) {

		// プロシージャを呼び出して、農産企画商品マスタを作成する
		Map<String, Object> procedureParamMap = new HashMap<String, Object>() {
			{
				// ユーザーID
				put("inUSERID", userId);
				// プログラムID
				put("inPRGRM", viewId);
			}
		};

		// プロシージャ呼び出し結果エンティティ
		Pnks010ListItem resultEntity = new Pnks010ListItem();

		// ロールバックポイントを設定する
		Object savePoint = TransactionAspectSupport.currentTransactionStatus().createSavepoint();
		try {
			// プロシージャを実行する
			mapper.createGoodsMasterByPnks010(procedureParamMap);
		} catch (Exception e) {

			// 異常が発生する場合、ロールバックポイントにロールバックする
			TransactionAspectSupport.currentTransactionStatus().rollbackToSavepoint(savePoint);

			// エラーから重要な内容を切り出す
			String message = e.getMessage();
			List<String> errorMsgArray = Arrays.asList(message.split("\n"));
			String errorMsg = errorMsgArray.get(1).replaceAll("###", "");

			// プロシージャ呼び出し結果エンティティにエラーメッセージを設定する
			resultEntity.setErrContents(errorMsg);
		}

		return resultEntity;
	}
}
