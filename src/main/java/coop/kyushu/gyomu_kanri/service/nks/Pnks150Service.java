//================================================================================
// ID: PNKS150
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks150Service
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/01 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.service.nks;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coop.kyushu.common.util.BeanUtil;
import coop.kyushu.gyomu_kanri.entity.com.Nsmm060Entity;
import coop.kyushu.gyomu_kanri.entity.nks.Pnks150Entity;
import coop.kyushu.gyomu_kanri.form.nks.Pnks150Form;
import coop.kyushu.gyomu_kanri.mapper.com.Nsmm060Mapper;

/**
 * <pre>
 * 【農産企画支援】納品指示書発行指示サービス
 * </pre>
 */
@Service
public class Pnks150Service {

	/** 共通_メーカーマスタ マッパー */
	@Autowired
	private Nsmm060Mapper nsmm060Mapper;

	/**
	 * 納品指示書発行指示画面の初期表示
	 * @param form 入力内容
	 * @return 初期データ
	 */
	public Pnks150Entity getInitialData(Pnks150Form form) {
		Map<String, Object> param = BeanUtil.toMap(form);
		param.put("delFlg", "0");
		Pnks150Entity pnks150Entity = new Pnks150Entity();
		// メーカーコンボボックスリスト取得する
		List<Nsmm060Entity> nsmm060Data = nsmm060Mapper.getMakerList(param);
		pnks150Entity.setMakerComboxList(nsmm060Data);
		return pnks150Entity;
	}
}
