//================================================================================
// ID: PNKS080
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks080Service
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// 01.00.00 | 2023/07/17 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.service.nks;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import coop.kyushu.common.entity.ServerErrorEntity;
import coop.kyushu.common.exception.ExclusiveException;
import coop.kyushu.gyomu_kanri.entity.nks.Pnks080ListItem;
import coop.kyushu.gyomu_kanri.form.nks.Pnks080Form;
import coop.kyushu.gyomu_kanri.mapper.nks.Pnks080Mapper;

/**
 * <pre>
 * 【農産企画支援】バッチ情報一覧サービス
 * </pre>
 */
@Service
public class Pnks080Service {

	@Autowired
	private Pnks080Mapper pnks080Mapper;

	/**
	 * バッチ情報一覧画面の初期表示
	 * @param form 入力内容
	 * @return Pnks080ListItem 初期データ
	 */
	public List<Pnks080ListItem> getInitialData() {
		// メーカーマスタより、対象のメーカーリスト情報を取得する。
		List<Pnks080ListItem> listItems = pnks080Mapper.getBatchInfListByPnks080();
		return listItems;
	}

	/**
	 * バッチ情報一覧画面の削除処理
	 * @param form 入力内容
	 */
	@Transactional(rollbackFor = Exception.class)
	public void delete(Pnks080Form form) {

		List<String> list = form.getDelBatchNoList();

		// クエリ画面で選択したデータが存在するかどう
		for (int i = 0; i < list.size(); i++) {

			List<Pnks080ListItem> backData = pnks080Mapper.getBatchInfByPnks080(list.get(i));
			if (backData.isEmpty()) {
				throw new ExclusiveException(new ServerErrorEntity("NKS0011E"));
			}
		}

		pnks080Mapper.deleteBatchInfListByPnks080(list);
	}

}
