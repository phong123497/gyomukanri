//================================================================================
//ID: PNKS110
//ProjectName: コープ九州事業連合様  ホストシステムリプレイス
//SystemName: 業務管理
//ClassName: Pnks11001Service
//
//<<Modification History>>
//Version  | Date       | Updated By           | Content
//---------+------------+----------------------+---------------------------------
//01.00.00 | 2023/08/03 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.service.nks;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coop.kyushu.common.entity.ServerErrorEntity;
import coop.kyushu.common.exception.ValidationException;
import coop.kyushu.common.util.BeanUtil;
import coop.kyushu.gyomu_kanri.entity.nks.Pnks11001Entity;
import coop.kyushu.gyomu_kanri.entity.nks.Pnks11001Item;
import coop.kyushu.gyomu_kanri.form.nks.Pnks110Form;
import coop.kyushu.gyomu_kanri.mapper.nks.Pnks11001Mapper;
import coop.kyushu.gyomu_kanri.mapper.nks.Pnks110Mapper;

/**
 * <pre>
 * 【農産企画支援】調達加工計画書印刷
 * </pre>
 */
@Service
public class Pnks11001Service {

	@Autowired
	private Pnks110Mapper pnks110Mapper;

	@Autowired
	private Pnks11001Mapper pnks11001Mapper;

	/**
	 * 調達加工計画データ件数の取得
	 * 
	 * @param form 入力内容
	 * @return 調達加工計画データ件数 
	 */
	public int searchData(Pnks110Form form) {

		Map<String, Object> param = BeanUtil.toMap(form);

		// 配達年
		String haitatuY = form.getHaitatYm().substring(0, 4);
		param.put("haitatuY", haitatuY);

		// 配達月
		String haitatuM = form.getHaitatYm().substring(4, 6);
		param.put("haitatuM", haitatuM);

		// 調達加工計画の件数を取得する。
		int searchData = pnks110Mapper.getTyotatsuKakoPlanByPnks110(param);

		return searchData;
	}

	/**
	 * 調達加工計画書印刷画面の印刷
	 * 
	 * @param form 入力内容
	 * @return pnks11001Entity 調達加工計画出力データ
	 */
	public Pnks11001Entity pnks11001Print(Pnks110Form form) {
		// 調達加工計画出力データ取得
		int searchData = this.searchData(form);

		// 調達加工計画の件数が0場合
		if (searchData == 0) {
			// メッセージ：CMS0094E： 該当データがありません。
			throw new ValidationException(new ServerErrorEntity("NKS0009E", List.of("該当")));
		}

		Map<String, Object> param = BeanUtil.toMap(form);

		// 配達年月回
		StringBuilder haitatYmTimes = new StringBuilder();
		haitatYmTimes.append(form.getHaitatYm()).append(form.getHaitatTimes());
		param.put("haitatYmt", haitatYmTimes.toString());

		// 調達加工計画出力データ取得
		List<Pnks11001Item> data = pnks11001Mapper.getTyotatsuKakoPlanDataByPnks11001(param);

		Pnks11001Entity pnks11001Entity = new Pnks11001Entity();
		// ヘッダ部
		pnks11001Entity.setHaitatuY(data.get(0).getHaitatuY());
		pnks11001Entity.setHaitatuM(data.get(0).getHaitatuM());
		pnks11001Entity.setHaitatuT(data.get(0).getHaitatuT());
		// 明細部
		pnks11001Entity.setMeiSaiList(data);

		return pnks11001Entity;
	}

}
