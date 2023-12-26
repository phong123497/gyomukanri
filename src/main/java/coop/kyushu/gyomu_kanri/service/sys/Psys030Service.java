//================================================================================
// ID: PSYS030
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ProgramName: パスワード変更
// ClassName: Psys030Service
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/01 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.service.sys;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import coop.kyushu.common.entity.ServerErrorEntity;
import coop.kyushu.common.exception.ValidationException;
import coop.kyushu.common.util.EncryptUtil;
import coop.kyushu.common.util.TantoshaInfoUtil;
import coop.kyushu.gyomu_kanri.entity.sys.Psys030Entity;
import coop.kyushu.gyomu_kanri.entity.sys.Psys030PasswordInfo;
import coop.kyushu.gyomu_kanri.form.sys.Psys030Form;
import coop.kyushu.gyomu_kanri.mapper.sys.Psys030Mapper;

/**
 * <pre>
 * パスワード変更サービス
 * </pre>
 */
@Service
public class Psys030Service {

	/**
	 * パスワード変更マッパー
	 */
	@Autowired
	private Psys030Mapper psys030Mapper;

	/** 担当者情報管理  */
	@Autowired
	private TantoshaInfoUtil tantoshaInfoUtil;

	/**
	 * ユーザID、および更新日時取得
	 * @return ユーザID、および更新日時
	 */
	public Psys030Entity getInitialData() {

		Psys030Entity ent = psys030Mapper.getTantoshaInfo(tantoshaInfoUtil.getTansCd());

		// 取得できなかった場合
		if (ent == null) {
			throw new RuntimeException("「担当者情報」が「担当者情報マスタ」に登録されていません。");
		}
		return ent;
	}

	/**
	 * パスワード変更前確認処理
	 * @param フォーム
	 * @return パスワード履歴
	 * @throws NoSuchAlgorithmException
	 */
	public void checkPassword(Psys030Form form) throws NoSuchAlgorithmException {

		String tansCd = tantoshaInfoUtil.getTansCd().trim();

		// パスワード情報を取得する。
		Psys030PasswordInfo psdInf = psys030Mapper.getPassInfo(tansCd);

		String currentPass = psdInf.getPass();
		List<String> msgParams = new ArrayList<String>();

		// 現在のパスワードをチェックする
		if (!isSamePassword(form.getCurrentPassWord() + tansCd, currentPass)) {

			msgParams.add("現在のパスワード");
			throw new ValidationException(new ServerErrorEntity("CMS0046E", msgParams, "currentPassWord"));
		}

		// 新しいパスワードをチェックする
		if (isSamePassword(form.getNewPassWord() + tansCd, currentPass)) {

			msgParams.add("新しいパスワード");
			throw new ValidationException(new ServerErrorEntity("CMS0046E", msgParams, "newPassWord"));
		}

		return;

	}

	/**
	 * パスワード変更処理
	 * @param フォーム
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void changePassword(Psys030Form form) throws NoSuchAlgorithmException {

		// パスワード変更前確認処理を実行する
		checkPassword(form);

		String tansCd = tantoshaInfoUtil.getTansCd().trim();

		Map<String, Object> map = new HashMap<>();
		map.put("tansCd", tansCd);
		map.put("pass", EncryptUtil.hashedPassword(form.getNewPassWord() + tansCd));
		map.put("kushnUsr", tantoshaInfoUtil.getTansNm().trim());
		map.put("kushnPrgrm", "PSYS030");

		// 担当マスタのパスワード、パスワード変更日、ログインエラー回数を更新する
		psys030Mapper.putPassYmdInfo(map);

	}

	/**
	 * パスワードが同じかをチェックする
	 * @param psd			パスワード
	 * @param hashedPsd	パスワードのハッシュ値
	 * @return	True:同じ、False:違う
	 * @throws NoSuchAlgorithmException
	 */
	private boolean isSamePassword(String psd, String hashedPsd) throws NoSuchAlgorithmException {
		return hashedPsd.equals(EncryptUtil.hashedPassword(psd));
	}

}
