//================================================================================
// ID: PNKS090
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks090Service
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/14 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.service.nks;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import coop.kyushu.common.entity.ServerErrorEntity;
import coop.kyushu.common.entity.ServerErrorList;
import coop.kyushu.common.exception.ExclusiveException;
import coop.kyushu.common.exception.ValidationException;
import coop.kyushu.common.util.BeanUtil;
import coop.kyushu.common.util.DateUtil;
import coop.kyushu.common.util.TantoshaInfoUtil;
import coop.kyushu.gyomu_kanri.entity.com.Nsmm030Entity;
import coop.kyushu.gyomu_kanri.entity.nks.Pnks090Entity;
import coop.kyushu.gyomu_kanri.form.nks.Pnks090Form;
import coop.kyushu.gyomu_kanri.mapper.com.Nsmm030Mapper;
import coop.kyushu.gyomu_kanri.mapper.nks.Pnks090Mapper;

/**
 * <pre>
 * 【農産企画支援】バッチ情報メンテナンスサービス
 * </pre>
 */
@Service
public class Pnks090Service {

	/** バッチ情報メンテナンス マッパー */
	@Autowired
	private Pnks090Mapper mapper;
	/** 共通_会員生協マスタ マッパー */
	@Autowired
	private Nsmm030Mapper nsmm030Mapper;

	/** 共通_利用者情報 */
	@Autowired
	private TantoshaInfoUtil tantoshaInfoUtil;

	/**
	 * バッチ情報メンテナンス画面の初期表示
	 * @param form 入力内容
	 * @return 初期データ
	 */
	public Pnks090Entity getInitialData(Pnks090Form form) {

		Pnks090Entity resultEntity = new Pnks090Entity();
		// 種別　＝　2: 変更の場合
		if ("2".equals(form.getShubt())) {
			Map<String, Object> pnks090Param = BeanUtil.toMap(form);
			// バッチマスタ情報取得する
			resultEntity = mapper.getBatchInfByPnks090(pnks090Param);
			if (ObjectUtils.isEmpty(resultEntity)) {
				throw new ValidationException(new ServerErrorEntity("NKS0009E", Arrays.asList("バッチマスタ")));
			}
		}

		// 対象会員生協コードのコンボボックスを取得する
		Map<String, Object> mapParams = new HashMap<String, Object>();
		mapParams.put("delFlg", "0");
		resultEntity.setTargetKaiinSeikyComboxList(nsmm030Mapper.getkaiinSeikyList(mapParams));

		return resultEntity;
	}

	/**
	 * バッチ情報メンテナンス画面の保存処理
	 * @param form 入力内容
	 * @return 初期データ
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void save(Pnks090Form form) {
		if (StringUtil.isNotBlank(form.getTargetKaiinSeiky())) {
			Map<String, Object> nsmm030Param = new HashMap<>();
			nsmm030Param.put("seicd", form.getTargetKaiinSeiky());
			nsmm030Param.put("delFlg", "0");
			// 対象会員生協コードのコンボボックスを取得する
			List<Nsmm030Entity> list = nsmm030Mapper.getkaiinSeikyList(nsmm030Param);

			// 対象会員生協コード存在チェック
			if (list.size() == 0) {
				// 業務管理チェック処理
				ServerErrorList errorList = new ServerErrorList();
				String[] msgParams = { "対象会員生協", "会員生協" };
				errorList.add("CMS0020E", msgParams, "targetKaiinSeiky");
				throw new ValidationException(errorList);
			}
		}
		// システム日時取得する
		Timestamp systemDate = DateUtil.getCurrentTimestamp();
		Map<String, Object> pnks090Param = BeanUtil.toMap(form);
		pnks090Param.put("userId", tantoshaInfoUtil.getTansCd());
		pnks090Param.put("prgrm", "PNKS090");
		pnks090Param.put("nchj", systemDate);

		if (!"1".equals(form.getBatchPlanTarget())) {
			pnks090Param.put("batchPlanTarget", "0");
		}

		// バッチマスタ情報取得する
		Pnks090Entity oldEntity = mapper.getBatchInfByPnks090(pnks090Param);
		// 種別　＝　1: 追加の場合
		if ("1".equals(form.getShubt())) {
			// バッチNOの存在をチェクする
			if (oldEntity != null) {
				// 既に該当データが存在しています。
				throw new ExclusiveException(new ServerErrorEntity("NKS0008E"));
			}
			mapper.insertBatchInfByPnks090(pnks090Param);
			// 種別　＝　2: 変更の場合
		} else if ("2".equals(form.getShubt())) {
			// 更新日時が取得できなかった場合
			if (oldEntity == null) {
				// 他のユーザーによって削除されています。
				throw new ExclusiveException(new ServerErrorEntity("NKS0011E"));
				// 更新日時不一致の場合
			} else if (!oldEntity.getUpdDatm().equals(form.getUpdDatm())) {
				// 他のユーザーによって変更されています。
				throw new ExclusiveException(new ServerErrorEntity("NKS0010E"));
			}
			mapper.updateBatchInfByPnks090(pnks090Param);
		}

	}
}
