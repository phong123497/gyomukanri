//================================================================================
// ID: PNKS140
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks140Service
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/06 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.service.nks;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import coop.kyushu.common.entity.ServerErrorEntity;
import coop.kyushu.common.exception.ExclusiveException;
import coop.kyushu.common.exception.ValidationException;
import coop.kyushu.common.util.BeanUtil;
import coop.kyushu.common.util.DateUtil;
import coop.kyushu.common.util.TantoshaInfoUtil;
import coop.kyushu.gyomu_kanri.entity.nks.Pnks140Entity;
import coop.kyushu.gyomu_kanri.form.nks.Pnks140Form;
import coop.kyushu.gyomu_kanri.mapper.nks.Pnks140Mapper;

/**
 * <pre>
 * 【農産企画支援】メーカーマスタメンテナンスサービス
 * </pre>
 */
@Service
public class Pnks140Service {

	/** メーカーマスタメンテナンスマッパー */
	@Autowired
	private Pnks140Mapper pnks140Mapper;

	/** 利用者情報管理 */
	@Autowired
	private TantoshaInfoUtil tantoshaInfoUtil;

	/**
	 * メーカーマスタメンテナンス画面の初期表示
	 * @param form 入力内容
	 * @return 初期データ
	 */
	public Pnks140Entity getInitialData(Pnks140Form form) {

		// メーカーマスタの情報を取得する。
		Pnks140Entity backData = pnks140Mapper.getMakerInfoByPnks140(form.getMakerCd());

		// データが取得できなかった場合
		if (backData == null) {
			throw new ValidationException(new ServerErrorEntity("NKS0009E", List.of("メーカーマスタ")));
		}

		return backData;
	}

	/**
	 * メーカーマスタメンテナンス画面の保存処理
	 * @param form 入力内容
	 * @return 初期データ
	 */
	@Transactional
	public Pnks140Entity save(Pnks140Form form) {

		// メーカーマスタの情報を取得する。
		Pnks140Entity backData = pnks140Mapper.getMakerInfoByPnks140(form.getMakerCd());

		// データが取得できた場合
		if (backData != null) {
			throw new ValidationException(new ServerErrorEntity("NKS0008E"));
		}

		// フロントエンドからのデータ(Pnks140Form)をDBに保存する
		Map<String, Object> saveParam = BeanUtil.toMap(form);

		saveParam.replace("", form.getDelvTime().replaceAll(":", ""));
		saveParam.put("usr", tantoshaInfoUtil.getTansCd());
		saveParam.put("prgrm", "PNKS140");
		Timestamp systemDate = DateUtil.getCurrentTimestamp();
		saveParam.put("nchj", systemDate);

		// メーカーマスタ情報登録
		pnks140Mapper.insertMakerInfoByPnks140(saveParam);

		return backData;
	}

	/**
	 * メーカーマスタメンテナンス画面の更新処理
	 * @param form 入力内容
	 * @return 初期データ
	 */
	@Transactional
	public Pnks140Entity update(Pnks140Form form) {

		// メーカーコードより、メーカーマスタの情報を取得する。
		Pnks140Entity backData = pnks140Mapper.getMakerInfoByPnks140(form.getMakerCd());

		// データが取得できなかった場合
		if (backData == null) {
			// 業務エラー例外を返却して処理を終了する。
			throw new ExclusiveException(new ServerErrorEntity("NKS0011E"));
		}
		// 入力用Formの更新日時とメーカーマスタのMap.更新日時を比較して、一致しない場合
		if (!backData.getUpdDatm().equals(form.getUpdDatm())) {
			// 業務エラー例外を返却して処理を終了する。
			throw new ExclusiveException(new ServerErrorEntity("NKS0010E"));
		}

		// フロントエンドからのデータ(Pnks140Form)をDBに更新する
		Map<String, Object> updateParam = BeanUtil.toMap(form);

		updateParam.replace("", form.getDelvTime().replaceAll(":", ""));
		updateParam.put("usr", tantoshaInfoUtil.getTansCd());
		updateParam.put("prgrm", "PNKS140");
		Timestamp systemDate = DateUtil.getCurrentTimestamp();
		updateParam.put("nchj", systemDate);

		// メーカーマスタ情報更新
		pnks140Mapper.updateMakerByPnks140(updateParam);

		return backData;
	}
}
