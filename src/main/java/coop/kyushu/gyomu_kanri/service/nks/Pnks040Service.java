//================================================================================
// ID: PNKS040
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks040Service
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/13 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.service.nks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coop.kyushu.gyomu_kanri.entity.com.Nsmm060Entity;
import coop.kyushu.gyomu_kanri.entity.nks.Pnks040Entity;
import coop.kyushu.gyomu_kanri.form.nks.Pnks040Form;
import coop.kyushu.gyomu_kanri.mapper.com.Nsmm060Mapper;
import coop.kyushu.gyomu_kanri.mapper.nks.Pnks040Mapper;

/**
 * <pre>
 * 【農産企画支援】企画案内書印刷サービス
 * </pre>
 */
@Service
public class Pnks040Service {

	/** 企画案内書印刷マッパー */
	@Autowired
	private Pnks040Mapper pnks040Mapper;

	/** メーカーマスタ マッパー */
	@Autowired
	private Nsmm060Mapper nsmm060Mapper;

	/**
	 * 【農産企画支援】企画案内書印刷の初期処理
	 * @param form 入力内容
	 * @return 初期データ
	 */
	public Pnks040Entity getInitialData() {

		// メーカーマスタより、対象のメーカーリスト情報を取得する。
		Map<String, Object> makerParam = new HashMap<String, Object>();
		makerParam.put("delFlg", "0");
		List<Nsmm060Entity> makerList = nsmm060Mapper.getMakerList(makerParam);

		// 管理マスタより、連絡事項～納品先ＦＡＸ（南部）項目の情報を取得
		Pnks040Entity backData = pnks040Mapper.getKanriMstByPnks040();
		if (backData == null) {
			backData = new Pnks040Entity();
		}
		backData.setMakerComboxList(makerList);

		return backData;
	}

	/**
	 * 【農産企画支援】企画案内書印刷のCQ企画番号開始変更
	 * @param form 入力内容
	 * @return 企画案内情報マスタデータ
	 */
	public Pnks040Entity changeCqProjNoStart(Pnks040Form form) {

		// 企画案内情報マスタより、企画案内情報を取得する。
		Pnks040Entity backData = pnks040Mapper.getProjAnnaiInfByPnks040(form.getCqProjNoStart());

		// データが取得できなかった場合
		if (backData == null) {
			backData = new Pnks040Entity();
			backData.setProjKknStart("");
			backData.setProjKknStart("");
			backData.setKakakuAnsKgn("");
		}

		return backData;
	}

}
