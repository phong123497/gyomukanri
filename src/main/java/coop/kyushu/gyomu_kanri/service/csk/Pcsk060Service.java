//================================================================================
// ID: Pcsk060
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ProgramName: 粗利益管理表出力
// ClassName: Pcsk060Service
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/8/18 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.service.csk;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import coop.kyushu.common.entity.ServerErrorList;
import coop.kyushu.common.exception.ValidationException;
import coop.kyushu.gyomu_kanri.entity.csk.Pcsk060Entity;
import coop.kyushu.gyomu_kanri.entity.csk.Pcsk060YmItemEntity;
import coop.kyushu.gyomu_kanri.form.csk.Pcsk060Form;
import coop.kyushu.gyomu_kanri.mapper.csk.Pcsk060Mapper;

/**
 * <pre>
 * 【コープ九州商管】粗利益管理表出力
 * </pre>
 */
@Service
public class Pcsk060Service {
	/** 【コープ九州商管】粗利益管理表出力 */
	@Autowired
	private Pcsk060Mapper pcsk060Mapper;

	/**
	 * 初期データ取得
	 * @param form 入力内容
	 * @return 初期データ
	 */
	public Pcsk060Entity getInitialData(Pcsk060Form form) throws Exception {
		ServerErrorList errorList = new ServerErrorList();

		//運用日の年月度を取得
		List<Pcsk060YmItemEntity> kymlist = pcsk060Mapper.getNendoByPcsk060();
		if (ObjectUtils.isEmpty(kymlist)) {
			errorList.add("CSK0002E", "運用年度", null);
			throw new ValidationException(errorList);
		}

		//運用日以前の年度を取得
		Map<String, Object> mapParams = new HashMap<>();
		mapParams.put("kym", kymlist.get(0).getKym());
		List<Pcsk060YmItemEntity> nendoList = pcsk060Mapper.getBeforeNendoByPcsk060(mapParams);
		if (ObjectUtils.isEmpty(nendoList)) {
			errorList.add("CSK0002E", "運用年度", null);
			throw new ValidationException(errorList);
		}

		Pcsk060Entity entity = new Pcsk060Entity();
		entity.setNendo(kymlist.get(0).getNendo());
		entity.setNendoList(nendoList);
		getMontList(entity);
		return entity;
	}

	/**
	 * 年度変更
	 * @param form 入力内容
	 * @return 初期データ
	 */
	public Pcsk060Entity changeNendo(Pcsk060Form form) throws Exception {

		Pcsk060Entity entity = new Pcsk060Entity();
		entity.setNendo(form.getNendo());
		getMontList(entity);
		return entity;
	}

	/**
	 * 計上年月リストを取得
	 * @param form 入力内容
	 * @return 初期データ
	 */
	private void getMontList(Pcsk060Entity entity) throws Exception {
		ServerErrorList errorList = new ServerErrorList();
		//運用日の月度を取得
		List<Pcsk060YmItemEntity> getsudolist = pcsk060Mapper.getGetsudoByPcsk060();
		if (ObjectUtils.isEmpty(getsudolist)) {
			errorList.add("CSK0002E", "運用月度", null);
			throw new ValidationException(errorList);
		}
		Map<String, Object> mapParams = new HashMap<>();
		//運用日以前の月度取得
		mapParams.put("nendo", entity.getNendo());
		mapParams.put("kym", getsudolist.get(0).getKym());
		List<Pcsk060YmItemEntity> monthlyList = pcsk060Mapper.getBeforeGetsudoByPcsk060(mapParams);
		if (ObjectUtils.isEmpty(monthlyList)) {
			errorList.add("CSK0002E", "運用月度", null);
			throw new ValidationException(errorList);
		}

		entity.setMonthlyList(monthlyList);
		entity.setMonthlyFrom(monthlyList.get(0).getKym());
		entity.setMonthlyTo(monthlyList.get(0).getKym());
	}

}
