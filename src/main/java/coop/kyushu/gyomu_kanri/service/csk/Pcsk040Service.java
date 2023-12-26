//================================================================================
// ID: PCSK040
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pcsk040Service
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/17 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.service.csk;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import coop.kyushu.common.entity.ServerErrorList;
import coop.kyushu.common.exception.ValidationException;
import coop.kyushu.common.util.BeanUtil;
import coop.kyushu.common.util.DateUtil;
import coop.kyushu.common.util.TantoshaInfoUtil;
import coop.kyushu.gyomu_kanri.entity.csk.Pcsk040Entity;
import coop.kyushu.gyomu_kanri.entity.csk.Pcsk040Item;
import coop.kyushu.gyomu_kanri.form.csk.Pcsk040Form;
import coop.kyushu.gyomu_kanri.mapper.csk.Pcsk040Mapper;

/**
 * <pre>
 * 【コープ九州商管】締日登録サービス
 * </pre>
 */
@Service
public class Pcsk040Service {
	/** 【コープ九州商管】締日登録 */
	@Autowired
	private Pcsk040Mapper pcsk040Mapper;
	/** 利用者情報管理 */
	@Autowired
	private TantoshaInfoUtil tantoshaInfoUtil;

	/**
	 * 【コープ九州商管】締日登録の検索処理
	 * @param form 入力内容
	 * @return 初期データ
	 */
	public Pcsk040Entity search(Pcsk040Form form) {
		Pcsk040Entity entity = new Pcsk040Entity();
		ServerErrorList errorList = new ServerErrorList();

		//営業日を取得する
		String ymd = pcsk040Mapper.getDateByPcsk040();
		if (ObjectUtils.isEmpty(ymd)) {
			errorList.add("CSK0008E", "営業日取得", null);
			throw new ValidationException(errorList);
		}
		entity.setYmd(ymd);
		entity.setNedo(form.getNedo());

		//計上年月を取得する
		List<Pcsk040Item> skbtgflg = pcsk040Mapper.getSKBTGFLGByPcsk040();
		entity.setCloseDayList(skbtgflg);

		//計上年月を取得する
		List<Pcsk040Item> krbtgflg = pcsk040Mapper.getKRBTGFLGByPcsk040();
		entity.setCloseDayList(krbtgflg);

		//計上年月、商品管理部締日、商品管理部当月度フラグ、経理部締日、経理部当月度フラグを取得する。
		Map<String, Object> mapParams = BeanUtil.toMap(form);
		List<Pcsk040Item> enlist = pcsk040Mapper.getKYMFByPcsk040(mapParams);
		if (ObjectUtils.isEmpty(enlist)) {
			errorList.add("CSK0002E", "該当データ", null);
			throw new ValidationException(errorList);
		}
		entity.setCloseDayList(enlist);
		//請求支払_消込照合処理区分データ型変換
		for (Pcsk040Item item : enlist) {
			String keshikmShogoProcsDayProcsKbn = item.getKeshikmShogoProcsDayProcsKbn();
			switch (keshikmShogoProcsDayProcsKbn) {
			case "0":
				item.setKeshikmShogoProcsDayProcsKbn("未");
				break;
			case "1":
				item.setKeshikmShogoProcsDayProcsKbn("中");
				break;
			case "2":
				item.setKeshikmShogoProcsDayProcsKbn("完");
				break;
			default:
				break;
			}
		}
		return entity;
	}

	/**
	 * 【コープ九州商管】締日登録の保存処理
	 * @param form 入力内容
	 * @return 初期データ
	 */
	@Transactional
	public void save(Pcsk040Form form) {
		// システム日時を取得する
		Timestamp nowDate = DateUtil.getCurrentTimestamp();

		List<Pcsk040Item> closeDayList = form.getCloseDayList();
		ServerErrorList errorList = new ServerErrorList();
		if (closeDayList != null && !closeDayList.isEmpty()) {
			for (Pcsk040Item item : closeDayList) {
				Map<String, Object> mapParams = new HashMap<>();
				mapParams.put("usr", tantoshaInfoUtil.getTansCd());
				mapParams.put("prgrm", "PCSK040");
				mapParams.put("nchj", nowDate);
				setParam(mapParams, "keijoYm", item.getKeijoYm());
				setParam(mapParams, "cpkyushuMonthCloseDay", item.getCpkyushuMonthCloseDay());
				setParam(mapParams, "shinMngHyoCloseDayShinMng", item.getShinMngHyoCloseDayShinMng());
				setParam(mapParams, "shinMngHyoCloseDayKeiri", item.getShinMngHyoCloseDayKeiri());
				setParam(mapParams, "keshikmShogoProcsDayProcsDay", item.getKeshikmShogoProcsDayProcsDay());
				mapParams.put("nedo", Integer.parseInt(form.getNedo()));
				int backData = pcsk040Mapper.updateAll(mapParams);
				if (backData == 0) {
					errorList.add("CSK0005E", "日付の順番", null);
					throw new ValidationException(errorList);
				}
			}
		}
	}

	private void setParam(Map<String, Object> map, String key, String value) {
		if (value != null && !value.isEmpty()) {
			map.put(key, Integer.parseInt(value));
		} else {
			map.put(key, value);
		}
	}
}
