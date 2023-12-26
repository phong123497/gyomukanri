//================================================================================
// ID: PNKS130
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks130Service
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/06 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.service.nks;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import coop.kyushu.common.entity.ServerErrorEntity;
import coop.kyushu.common.exception.ExclusiveException;
import coop.kyushu.common.util.BeanUtil;
import coop.kyushu.common.util.DateUtil;
import coop.kyushu.common.util.TantoshaInfoUtil;
import coop.kyushu.gyomu_kanri.entity.com.Nsmm060Entity;
import coop.kyushu.gyomu_kanri.entity.nks.Pnks130Entity;
import coop.kyushu.gyomu_kanri.entity.nks.Pnks130ListItem;
import coop.kyushu.gyomu_kanri.form.nks.Pnks130Form;
import coop.kyushu.gyomu_kanri.mapper.com.Nsmm060Mapper;
import coop.kyushu.gyomu_kanri.mapper.nks.Pnks130Mapper;

/**
 * <pre>
 * 【農産企画支援】メーカーマスタ一覧サービス
 * </pre>
 */
@Service
public class Pnks130Service {

	// 農産企画支援】メーカーマスタ一覧マッパー 
	@Autowired
	private Pnks130Mapper pnks130Mapper;

	// 利用者情報管理 
	@Autowired
	private TantoshaInfoUtil tantoshaInfoUtil;

	// 初期情報管理 
	@Autowired
	private Nsmm060Mapper nsmm060Mapper;

	/**
	 * 【農産企画支援】メーカーマスタ一覧の初期表示
	 * @param form 入力内容
	 * @return Pnks130Entity	初期データ
	 */
	@Transactional(rollbackFor = Exception.class)
	public Pnks130Entity getInitialData(Pnks130Form form) {

		Map<String, Object> param = BeanUtil.toMap(form);
		param.put("delFlg", "0");

		// メーカーマスタより、対象のメーカーリスト情報を取得する。
		List<Nsmm060Entity> backlist = nsmm060Mapper.getMakerList(param);
		Pnks130Entity backData = new Pnks130Entity();
		backData.setMakerComboxList(backlist);
		return backData;
	}

	/**
	 * 【農産企画支援】メーカーマスタ一覧の検索処理
	 * @param form 入力内容
	 * @return メーカー一覧のデータ	
	 */
	public List<Pnks130ListItem> search(Pnks130Form form) {

		// 検索されたデータを保存します
		List<Pnks130ListItem> backList = pnks130Mapper.getMakerListByPnks130(BeanUtil.toMap(form));
		// 納品時間フォーマットは"MM:dd"に設定します。
		for (Pnks130ListItem backDataList : backList) {

			StringBuilder delvDate = new StringBuilder(backDataList.getDelvTime());
			delvDate.insert(2, ":");
			backDataList.setDelvTime(delvDate.toString());
		}

		return backList;
	}

	/**
	 * 【農産企画支援】メーカー検索画面の検索前処理
	 * @param form 入力内容
	 * @return カウント
	 */
	public int getInitialDataCnt(Pnks130Form form) {

		return pnks130Mapper.getInitialDataCntByPnks130(BeanUtil.toMap(form));
	}

	/**
	 * 【農産企画支援】メーカーマスタ一覧の保存処理
	 * @param form 入力内容
	 */
	@Transactional(rollbackFor = Exception.class)
	public void save(Pnks130Form form) {

		// データを取得
		List<Pnks130ListItem> list = form.getMakerInfList();

		// システム時間の獲得
		Date systemTime = DateUtil.getCurrentDate();

		for (int i = 0; i < list.size(); i++) {

			// 情報を取得します更新
			String updateTime = pnks130Mapper.getMakerUpdateTimeByPnks130(list.get(i).getMakerCd());
			if (updateTime == null) {
				// データが取得できなかった場合
				throw new ExclusiveException(new ServerErrorEntity("NKS0011E"));
			} else if (!list.get(i).getUpdDatm().equals(updateTime)) {
				throw new ExclusiveException(new ServerErrorEntity("NKS0010E"));
			}

			// データを入れます。
			Map<String, Object> saveParam = BeanUtil.toMap(list.get(i));
			saveParam.put("userId", tantoshaInfoUtil.getTansCd());
			saveParam.put("prgrm", "PNKS130");
			saveParam.put("nchj", systemTime);

			pnks130Mapper.updateToriteiFlgByPnks130(saveParam);
		}

	}

	/**
	 * 【農産企画支援】メーカーマスタ一覧の削除処理
	 * @param form 入力内容
	 */
	@Transactional(rollbackFor = Exception.class)
	public void delete(Pnks130Form form) {

		// 削除すべきセットを取得します
		List<String> list = form.getDelMakerCdList();

		for (int i = 0; i < list.size(); i++) {

			// 更新日の照会に必要なキー値のペアです
			String updateTime = pnks130Mapper.getMakerUpdateTimeByPnks130(list.get(i));

			// データが取得できなかった場合
			if (updateTime == null) {
				throw new ExclusiveException(new ServerErrorEntity("NKS0011E"));
			}
		}

		// deleteMakerByPnks130メソッドを呼び出して選択された項目を削除します。
		pnks130Mapper.deleteMakerByPnks130(list);
	}

}
