//================================================================================
// ID: PNKS100
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks100Service
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/27 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.service.nks;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import coop.kyushu.common.entity.ServerErrorList;
import coop.kyushu.common.exception.ValidationException;
import coop.kyushu.gyomu_kanri.entity.nks.Pnks10001Entity;
import coop.kyushu.gyomu_kanri.entity.nks.Pnks10001ListItem;
import coop.kyushu.gyomu_kanri.form.nks.Pnks100Form;
import coop.kyushu.gyomu_kanri.mapper.nks.Pnks1001Mapper;

/**
 * <pre>
 * 【農産企画支援】バッチ計画表印刷サービス
 * </pre>
 */
@Service
public class Pnks1001Service {

	// バッチ計画表印刷マッパー
	@Autowired
	private Pnks1001Mapper pnks1001Mapper;

	/**
	 * 【農産企画支援】バッチ計画表印刷の印刷
	 * @param form 入力内容
	 * @return バッチ計画表データ
	 */
	public Pnks10001Entity pnks10001Print(Pnks100Form form) {

		// 配達年月回を構成する
		String haitatYm = form.getHaitatYm();
		String haitatYmTimes = haitatYm + form.getHaitatTimes();

		// バッチNo情報を検索する
		List<String> batchNoList = pnks1001Mapper.getBatchNoByPnks100(haitatYmTimes);
		if (ObjectUtils.isEmpty(batchNoList)) {
			ServerErrorList errorList = new ServerErrorList();
			errorList.add("NKS0009E", "該当", null);
			throw new ValidationException(errorList);
		}

		// バッチ計画表の明細データを取得する
		List<Pnks10001ListItem> batchNoInfo = pnks1001Mapper.getBatchInfoByPnks10001(haitatYmTimes);

		// 帳票定義体エンティティを宣言する
		Pnks10001Entity choHyoEntity = new Pnks10001Entity();

		// 配達年と配達月を切り出す
		String haitatY = haitatYm.substring(0, 4);
		String haitatM = haitatYm.substring(4, haitatYm.length());

		// ヘッダー部を設定する
		choHyoEntity.setHaitatuymtY(haitatY);
		choHyoEntity.setHaitatuymtM(haitatM);
		choHyoEntity.setHaitatuymtT(form.getHaitatTimes());

		// 明細部を設定する
		choHyoEntity.setDetail(batchNoInfo);

		return choHyoEntity;
	}
}
