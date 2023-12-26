//================================================================================
// ID: PNKS020
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks020Service
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/07 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.service.nks;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coop.kyushu.common.entity.ServerErrorEntity;
import coop.kyushu.common.exception.ValidationException;
import coop.kyushu.common.util.BeanUtil;
import coop.kyushu.gyomu_kanri.entity.nks.Pnks020Entity;
import coop.kyushu.gyomu_kanri.entity.nks.Pnks020NousanProjinShin;
import coop.kyushu.gyomu_kanri.form.nks.Pnks020Form;
import coop.kyushu.gyomu_kanri.mapper.nks.Pnks020Mapper;

/**
 * <pre>
 * 【農産企画支援】農産企画情報・商品検索サービス
 * </pre>
 */
@Service
public class Pnks020Service {

	/** 農産企画情報・商品検索マッパー */
	@Autowired
	private Pnks020Mapper mapper;

	/**
	 * 農産企画情報・商品検索画面の検索処理
	 * @param form 入力内容
	 * @return 農産企画情報・商品検索エンティティ(entity)
	 */
	public Pnks020Entity getNousanProjinShin(Pnks020Form form) {
		Pnks020Entity pnks020Entity = new Pnks020Entity();
		List<Pnks020NousanProjinShin> pcyu020NousanProjinShinList = mapper
				.getNousanProjinShinByPnks020(BeanUtil.toMap(form));
		// データが取得できた場合
		if (pcyu020NousanProjinShinList.size() == 0) {
			// メッセージ：NKS0009E：　農産企画商品マスタデータがありません。
			throw new ValidationException(new ServerErrorEntity("NKS0009E", Arrays.asList("農産企画商品マスタ"), "projNo"));
		}
		// データ件数1000以上の場合
		if (pcyu020NousanProjinShinList.size() > 1000) {
			// メッセージ：CMS0032E：　検索結果が1000件を超えています。抽出条件を変更して再検索してください。
			throw new ValidationException(new ServerErrorEntity("CMS0032E", Arrays.asList("1000"), "projNo"));
		}

		pnks020Entity.setNousanProjinShinList(pcyu020NousanProjinShinList);
		return pnks020Entity;
	}

	/**
	 * 農産企画情報・商品検索画面の検索結果件数チェック
	 * @param form 入力内容
	 * @return データ件数
	 */
	public int getNousanProjinShinCnt(Pnks020Form form) {
		int getNousanProjinShinCnt = mapper
				.getNousanProjinShinCntByPnks020(BeanUtil.toMap(form));
		return getNousanProjinShinCnt;
	}

}
