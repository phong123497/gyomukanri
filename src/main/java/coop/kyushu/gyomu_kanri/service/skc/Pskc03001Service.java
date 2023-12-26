//================================================================================
// ID: PSKC030
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pskc030Service
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/11 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.service.skc;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coop.kyushu.common.entity.ServerErrorEntity;
import coop.kyushu.common.exception.ValidationException;
import coop.kyushu.common.util.BeanUtil;
import coop.kyushu.gyomu_kanri.entity.com.Sztr102Entity;
import coop.kyushu.gyomu_kanri.entity.com.Szvw102Entity;
import coop.kyushu.gyomu_kanri.entity.skc.Rskc03001Entity;
import coop.kyushu.gyomu_kanri.entity.skc.Rskc03001ListItem;
import coop.kyushu.gyomu_kanri.form.skc.Pskc030Form;
import coop.kyushu.gyomu_kanri.mapper.skc.Pskc030Mapper;

/**
 * <pre>
 * 【惣菜加工センター】製造計画数入力サービス
 * </pre>
 */
@Service
public class Pskc03001Service {

	/** 【惣菜加工センター】製造計画数入力マッパー */
	@Autowired
	private Pskc030Mapper mapper;

	/**
	 * 製造計画数入力画面の印刷処理
	 * @param form 入力内容
	 * @return 製造計画数入力Entity
	 */
	public Rskc03001Entity rskc03001Print(Pskc030Form form) {
		// 変数を宣言する。
		Rskc03001Entity pskc030Entity = new Rskc03001Entity();
		Map<String, Object> param = BeanUtil.toMap(form);
		// 時間書式設定
		DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("M/d");
		// 検索条件部用の店舗名取得する。
		List<Szvw102Entity> miseListByPskc030 = mapper.getMiseListByPskc030();
		for (Szvw102Entity item : miseListByPskc030) {
			if (item.getMisecd().equals(form.getTenpo())) {
				pskc030Entity.setTenpoNm(item.getMisenm());
				break;
			}
		}

		// 検索条件部用の表示週名取得する。
		List<Sztr102Entity> nhinymdListByPskc030 = mapper.getNhinymdListByPskc030();
		for (Sztr102Entity item : nhinymdListByPskc030) {
			if (item.getNhinymd().equals(form.getHyojiWeek())) {
				pskc030Entity.setHyojiWeekNm(item.getNhinnm());
				break;
			}
		}

		// 「検索データ取得」サービスとして、以下の内容を実行する。
		//店舗=入力用Form.店舗
		param.put("tenpo", form.getTenpo());
		//表示週=入力用Form.表示週.開始日
		param.put("hyojiWeek", form.getHyojiWeek());
		//表示順=入力用Form.表示順
		param.put("hyojiRank", form.getHyojiRank());
		//昇順降順=入力用Form.昇順降順
		param.put("ascDesc", form.getAscDesc());
		List<Rskc03001ListItem> sosaiTaishoTenpoByPskc030 = mapper.getSosaiTaishoTenpoByPskc030(param);

		// データが取得できた場合
		if (sosaiTaishoTenpoByPskc030.size() == 0) {
			// メッセージ：SKC0001E：　対象の商品データがありません。
			if (!form.getIsHonsyaKengen()) {
				throw new ValidationException(new ServerErrorEntity("SKC0001E", Arrays.asList("対象の商品"), "hyojiWeek"));
			} else {
				throw new ValidationException(new ServerErrorEntity("SKC0001E", Arrays.asList("対象の商品"), "tenpo"));
			}
		}
		pskc030Entity.setHeaderLbl(
				new StringBuffer().append(pskc030Entity.getTenpoNm()).append("　").append(pskc030Entity.getHyojiWeekNm())
						.toString());
		// 納品日（月）
		pskc030Entity.setDelvdayMon(LocalDate.parse(sosaiTaishoTenpoByPskc030.get(0).getDelvdayMon(), inputFormatter)
				.format(outputFormatter).concat("(月)"));
		// 納品日（火）
		pskc030Entity.setDelvdayTue(LocalDate.parse(sosaiTaishoTenpoByPskc030.get(0).getDelvdayTue(), inputFormatter)
				.format(outputFormatter).concat("(火)"));
		// 納品日（水）
		pskc030Entity.setDelvdayWed(LocalDate.parse(sosaiTaishoTenpoByPskc030.get(0).getDelvdayWed(), inputFormatter)
				.format(outputFormatter).concat("(水)"));
		// 納品日（木）
		pskc030Entity.setDelvdayThu(LocalDate.parse(sosaiTaishoTenpoByPskc030.get(0).getDelvdayThu(), inputFormatter)
				.format(outputFormatter).concat("(木)"));
		// 納品日（金）
		pskc030Entity.setDelvdayFri(LocalDate.parse(sosaiTaishoTenpoByPskc030.get(0).getDelvdayFri(), inputFormatter)
				.format(outputFormatter).concat("(金)"));
		// 納品日（土）
		pskc030Entity.setDelvdaySat(LocalDate.parse(sosaiTaishoTenpoByPskc030.get(0).getDelvdaySat(), inputFormatter)
				.format(outputFormatter).concat("(土)"));
		// 納品日（日）
		pskc030Entity.setDelvdaySun(LocalDate.parse(sosaiTaishoTenpoByPskc030.get(0).getDelvdaySun(), inputFormatter)
				.format(outputFormatter).concat("(日)"));

		pskc030Entity.setMeisaiList(sosaiTaishoTenpoByPskc030);
		return pskc030Entity;
	}
}
