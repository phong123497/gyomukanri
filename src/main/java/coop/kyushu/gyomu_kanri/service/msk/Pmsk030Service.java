//================================================================================
// ID: PMSK030
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理システム
// ProgramName: 仕入・訂正入力
// ClassName: Pmsk030Service
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/09/15 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.service.msk;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coop.kyushu.common.entity.ServerErrorList;
import coop.kyushu.common.exception.ValidationException;
import coop.kyushu.common.util.BeanUtil;
import coop.kyushu.common.util.DateUtil;
import coop.kyushu.common.util.TantoshaInfoUtil;
import coop.kyushu.gyomu_kanri.entity.msk.Pmsk030Entity;
import coop.kyushu.gyomu_kanri.form.msk.Pmsk030Form;
import coop.kyushu.gyomu_kanri.mapper.msk.Pmsk030Mapper;

@Service
public class Pmsk030Service {

	@Autowired
	private Pmsk030Mapper pmsk030Mapper;

	/** 共通_利用者情報 */
	@Autowired
	private TantoshaInfoUtil tantoshaInfoUtil;

	/**
	 * 区分管理マスタ取得
	 * @param Pmsk030Form
	 * @return Pmsk030Entity
	 */
	public Pmsk030Entity getKbn(Pmsk030Form form) {
		Map<String, Object> params = new HashMap<>();
		params.put("reckb", form.getReckb());
		params.put("meicd", form.getMeicd());
		params.put("delflg", form.getDelflg());
		return pmsk030Mapper.getKubunByPmsk030(params);
	}

	/**
	 * 名称マスタ(VIEW TABLE)取得
	 * @param Pmsk030Form
	 * @return Pmsk030Entity
	 */
	public Pmsk030Entity getMeisho(Pmsk030Form form) {
		Map<String, Object> params = new HashMap<>();
		params.put("reckb", form.getReckb());
		params.put("meicd", form.getMeicd());
		return pmsk030Mapper.getMeishoByPmsk030(params);
	}

	/**
	 * 名称マスタ登録
	 * @param Pmsk030Form
	 */
	public void insertMeisho(Pmsk030Form form) {
		ServerErrorList errorList = new ServerErrorList();
		Map<String, Object> params = BeanUtil.toMap(form);
		// システム日時取得する
		Timestamp systemDate = DateUtil.getCurrentTimestamp();
		Date date = DateUtil.getCurrentDate();
		BigDecimal toroKoshin = DateUtil.toBigDecimal(date);
		boolean IsHasMeisho = checkGetMeisho(form);
		if (IsHasMeisho == true) {
			errorList.add("CMS0034E", "名称区分",null);
			throw new ValidationException(errorList);
		}else{
			params.put("delflg", "0"); // 削除フラグ
			params.put("toroKoshin", toroKoshin); // 登録日 ,更新日 int
			params.put("userId", tantoshaInfoUtil.getTansCd());// 作成ユーザー/ 更新ユーザー
			params.put("prgrm", "PMSK030"); // 作成プログラム//更新プログラム
			params.put("sakuKoshinj", systemDate); // 作成日時 //更新日時
			pmsk030Mapper.insertMeishoByPmsk030(params);
			}
		}

	/**
	 * 名称マスタ更新
	 * @param Pmsk030Form
	 */
	public void updateMeisho(Pmsk030Form form) {
		ServerErrorList errorList = new ServerErrorList();
		Map<String, Object> params = BeanUtil.toMap(form);
		// システム日時取得するe
		Timestamp systemDate = DateUtil.getCurrentTimestamp();
		Date date = DateUtil.getCurrentDate();
		BigDecimal kosinDay = DateUtil.toBigDecimal(date);
		boolean IsHasMeisho = checkGetMeisho(form);
		if (IsHasMeisho == false) {
			errorList.add("CMS0034E", "名称区分", null);
			throw new ValidationException(errorList);
		} else {
			params.put("kosinDay", kosinDay); // 更新日 int
			params.put("userId", tantoshaInfoUtil.getTansCd()); // 更新ユーザー
			params.put("prgrm", "PMSK030"); // 更新プログラム
			params.put("koshinj", systemDate); // 更新日時 date
			pmsk030Mapper.updateMeishoByPmsk030(params);
		}
	}

	/**
	 * 区分管理マスタ登録
	 * @param Pmsk030Form
	 */
	public void insertKubun(Pmsk030Form form) {
		ServerErrorList errorList = new ServerErrorList();
		// システム日時取得する
		Timestamp systemDate = DateUtil.getCurrentTimestamp();
		Date date = DateUtil.getCurrentDate();

		BigDecimal toroKoshin = DateUtil.toBigDecimal(date);
		Map<String, Object> params = BeanUtil.toMap(form);
		boolean isHasKubun = checkGetKubun(form);
		if (isHasKubun == true) {
			errorList.add("CMS0034E", "名称区分",null);
			throw new ValidationException(errorList);
		} else {
			params.put("delflg", "0"); // 削除フラグ
			params.put("toroKoshin", toroKoshin); // 登録日 ,更新日 int
			params.put("userId", tantoshaInfoUtil.getTansCd());// 作成ユーザー/ 更新ユーザー
			params.put("sakuKoshinj", systemDate); // 作成日時 //更新日時
			if (form.getYobidashi().equals("PMSK010")) {
				params.put("reckb", "00"); // 名称区分
				params.put("meicd", form.getReckb()); // 名称コード
			}
			pmsk030Mapper.insertKubunByPmsk030(params);
		}
	}

	/**
	 * 区分管理マスタ更新
	 * @param Pmsk030Form
	 */
	public void updateKubun(Pmsk030Form form) {
		ServerErrorList errorList = new ServerErrorList();
		boolean isHasKubun = checkGetKubun(form) ;
		if (isHasKubun == false) {
			errorList.add("CMS0034E", "名称区分");
			throw new ValidationException(errorList);
		} else {
			// システム日時取得するe
			Timestamp systemDate = DateUtil.getCurrentTimestamp();
			Date date = DateUtil.getCurrentDate();
			BigDecimal kosinDay = DateUtil.toBigDecimal(date);
			Map<String, Object> params = BeanUtil.toMap(form);
			params.put("kosinDay", kosinDay); // 更新日 int
			params.put("userId", tantoshaInfoUtil.getTansCd()); // 更新ユーザー
			params.put("prgrm", "PMSK030"); // 更新プログラム
			params.put("koshinj", systemDate); // 更新日時 date
			if (form.getYobidashi().equals("PMSK010")) {
				params.put("reckb", "00"); // 名称区分
				params.put("meicd", form.getMeicd()); // 名称コード
				params.put("delflg", "0"); // 削除フラグ
			}
			pmsk030Mapper.updateKubunByPmsk030(params);
		}
	}

	/**
	 * 区分管理マスタ存在チェック
	 * @param Pmsk030Form
	 * @return boolean
	 */
	public boolean checkGetKubun(Pmsk030Form form) {
		Map<String, Object> params = new HashMap<>();
		params.put("reckb", form.getReckb());
		params.put("meicd", form.getMeicd());
		Pmsk030Entity pmsk030Entity = pmsk030Mapper.getKubunByPmsk030(params);
		if (pmsk030Entity != null) {
			return true;
		}
		return false;
	}

	/**
	 * 名称マスタ存在チェック
	 * @param Pmsk030Form
	 * @return boolean
	 */
	public boolean checkGetMeisho(Pmsk030Form form) {
		Map<String, Object> params = new HashMap<>();
		params.put("reckb", form.getReckb());
		params.put("meicd", form.getMeicd());
		Pmsk030Entity pmsk030Entity = pmsk030Mapper.getMeishoByPmsk030(params);
		if (pmsk030Entity != null) {
			return true;
		}
		return false;
	}

}
