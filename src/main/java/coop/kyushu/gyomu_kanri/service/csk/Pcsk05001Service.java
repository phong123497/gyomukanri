//================================================================================
// ID: PCSK050
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pcsk050Service
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/18 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.service.csk;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coop.kyushu.common.entity.ServerErrorEntity;
import coop.kyushu.common.entity.ServerErrorList;
import coop.kyushu.common.exception.ValidationException;
import coop.kyushu.common.util.BeanUtil;
import coop.kyushu.gyomu_kanri.entity.csk.Pcsk05001Entity;
import coop.kyushu.gyomu_kanri.entity.csk.Pcsk050Entity;
import coop.kyushu.gyomu_kanri.form.csk.Pcsk050Form;
import coop.kyushu.gyomu_kanri.mapper.csk.Pcsk050Mapper;

/**
 * <pre>
 * 【コープ九州商管】センター商管表出力サービス
 * </pre>
 */
@Service
public class Pcsk05001Service {

	// 商管単品別
	private static final String TANPIN = "0";

	// 商管単品別
	private static final String TENPOBUNRUI = "1";

	// 商管単品別
	private static final String MUTENPOBUNRUI = "2";

	// 商管単品別
	private static final String BUMONBETU = "3";

	// 空白文字列
	private final static String BLANK_MARK = "";

	/** センター商管表出力マッパー */
	@Autowired
	private Pcsk050Mapper mapper;

	/**
	 * センター商管表出力画面のExcel出力処理
	 * @param form 入力内容
	 * @return センター商管表JSONデータ
	 */
	public Pcsk050Entity pcsk05001Print(Pcsk050Form form) {
		// ファイル名
		StringBuffer fileName = new StringBuffer();
		// WK開始年月
		StringBuffer startKymWk = new StringBuffer();
		// WK終了年月
		StringBuffer endKymWk = new StringBuffer();

		// センター商管表用Entity宣言する。
		Pcsk050Entity pcsk050Entity = new Pcsk050Entity();

		Map<String, Object> param = BeanUtil.toMap(form);

		// 業務チェックを行う
		this.businessCheck(form);

		// 財務商管表出力時のファイル名変更処理
		if (BigDecimal.ONE.compareTo(form.getShubt()) == 0) {
			switch (form.getSkeiTani().toString()) {
			case TANPIN:
				// 商管単品別
				fileName.append("Zaimu_Tanpin");
				break;
			case TENPOBUNRUI:
				// 商管店舗分類別
				fileName.append("Zaimu_TenpoBunrui");
				break;
			case MUTENPOBUNRUI:
				// 商管無店舗分類別
				fileName.append("Zaimu_MutenpoBunrui");
				break;
			case BUMONBETU:
				// 商管部門別
				fileName.append("Zaimu_Bumonbetu");
				break;
			}
		} else {
			switch (form.getSkeiTani().toString()) {
			case TANPIN:
				// 商管単品別
				fileName.append("Tanpin");
				break;
			case TENPOBUNRUI:
				// 商管店舗分類別
				fileName.append("TenpoBunrui");
				break;
			case MUTENPOBUNRUI:
				// 商管無店舗分類別
				fileName.append("MutenpoBunrui");
				break;
			case BUMONBETU:
				// 商管部門別
				fileName.append("Bumonbetu");
				break;
			}
		}

		switch (form.getMngTani().toString()) {
		case TANPIN:
			// コープ九州
			fileName.append("_CQALL");
			break;
		case TENPOBUNRUI:
			// 店舗
			fileName.append("_Tenpo");
			break;
		case MUTENPOBUNRUI:
			// 無店舗
			fileName.append("_Mutenpo");
			break;
		case BUMONBETU:
			// センター
			fileName.append("_Center").append(form.getCntrCd());
			break;
		}

		// WK開始年月 = 画面.対象年月_FORM
		param.put("startKymWk", form.getTargetYmFrom().toString());
		// WK終了年月 = 画面.対象年月_TO
		param.put("endKymWk", form.getTargetYmTo().toString());

		switch (form.getTargetKkn().toString()) {
		case TANPIN:
			// 単月指定
			fileName.append("_").append(form.getTargetYm());
			break;
		case TENPOBUNRUI:
			// 年度指定
			fileName.append("_").append(form.getTargetNendo()).append("nendo");
			// WK開始年月 = 画面.対象年度 + '04'
			param.put("startKymWk", startKymWk.append(form.getTargetNendo()).append("04").toString());
			// WK終了年月 = (画面.対象年度 + 1) + '03'
			param.put("endKymWk", endKymWk.append(Integer.parseInt(form.getTargetNendo()) + 1).append("03").toString());
			break;
		case MUTENPOBUNRUI:
			// 範囲指定
			fileName.append("_").append(form.getTargetYmFrom()).append("~").append(form.getTargetYmTo());
			break;
		}
		pcsk050Entity.setFileName(fileName.toString());

		if (BigDecimal.ONE.compareTo(form.getShubt()) == 0) {
			param.put("tableType", "財務");
		}
		// 単品はレイアウト変更のため処理を分けた
		if (BigDecimal.ZERO.compareTo(form.getTargetKkn()) == 0) {
			if (BigDecimal.ZERO.compareTo(form.getSkeiTani()) == 0) {
				pcsk050Entity.setDetailList(tangetuMakeTanpin(param));
			} else {
				pcsk050Entity.setDetailList(tangetuMake(param, form));
			}
		} else {
			if (BigDecimal.ZERO.compareTo(form.getSkeiTani()) == 0) {
				pcsk050Entity.setDetailList(kikanMakeTanpin(param));
			} else {
				pcsk050Entity.setDetailList(kikanMake(param, form));
			}
		}

		return pcsk050Entity;
	}

	/**
	 * 単月単品集計取得する。
	 * @param form 入力内容
	 * @return 単月単品集計リスト
	 */
	private List<Pcsk05001Entity> tangetuMakeTanpin(Map<String, Object> param) {
		List<Pcsk05001Entity> tangetuMakeTanpinByPcsk050 = mapper.getTangetuMakeTanpinByPcsk050(param);
		// データが取得できた場合
		if (tangetuMakeTanpinByPcsk050.size() == 0) {
			// メッセージ：CSK0002E：　該当データが存在しません。
			throw new ValidationException(new ServerErrorEntity("CSK0002E", Arrays.asList("該当データ")));
		}
		// wk供給原価計
		BigDecimal kyokGenkaKei = BigDecimal.ZERO;
		for (Pcsk05001Entity item : tangetuMakeTanpinByPcsk050) {
			// 理論在庫数 = 月末在庫数-積送中在庫数
			item.setRironSu(item.getMzaiSu().subtract(item.getSekiSu()));
			// 理論在庫額 = 月末在庫金額-積送中在庫金額
			item.setRironGak(item.getMzaiGak().subtract(item.getSekiGak()));
			// 実月末在庫数 = 積送中在庫数+実棚数
			item.setJitsugetsumatsuSu(item.getSekiSu().add(item.getHzaiSu()));
			// 実月末在庫額 = 積送中在庫金額+実棚金額
			item.setJitsugetsumatsuGak(item.getSekiGak().add(item.getHzaiGak()));
			// 不明ロス数 = 実棚数-月末在庫数-積送中在庫数
			item.setFumeirosuSu(item.getHzaiSu().subtract(item.getMzaiSu()).subtract(item.getSekiSu()));
			// 不明ロス額 = 実棚金額-月末在庫金額-積送中在庫金額
			item.setFumeirosuGak(item.getHzaiGak().subtract(item.getMzaiGak()).subtract(item.getSekiGak()));
			// 供給原価計
			kyokGenkaKei = item.getSzaiGak().add(item.getSireGak().add(item.getKeihGak()).add(item.getFrinGak())
					.add(item.getFrisGak()).subtract(item.getSekiGak().add(item.getHzaiGak())));
			item.setKyokGenkaKei(kyokGenkaKei);
			// 供給剰余
			item.setKyokJoYo(item.getKyokGak().subtract(kyokGenkaKei));
			// 前年比(％) = 0
			item.setZenNenHi(BigDecimal.ZERO);
			// GPR(％)
			if (BigDecimal.ZERO.compareTo(item.getKyokGak()) == 0) {
				item.setGpr(BigDecimal.ZERO);
			} else {
				item.setGpr(item.getKyokGak().subtract(kyokGenkaKei).divide(item.getKyokGak(), 2, RoundingMode.HALF_UP)
						.multiply(new BigDecimal(100)));
			}
		}
		return tangetuMakeTanpinByPcsk050;

	}

	/**
	 * 単月集計取得する。
	 * @param form 入力内容
	 * @return 単月集計処理リスト
	 */
	private List<Pcsk05001Entity> tangetuMake(Map<String, Object> param, Pcsk050Form form) {
		List<Pcsk05001Entity> tangetuMakeByPcsk050 = mapper.getTangetuMakeByPcsk050(param);
		// データが取得できた場合
		if (tangetuMakeByPcsk050.size() == 0) {
			// メッセージ：CSK0002E：　該当データが存在しません。
			throw new ValidationException(new ServerErrorEntity("CSK0002E", Arrays.asList("該当データ")));
		}
		// wk供給原価計
		BigDecimal kyokGenkaKei = BigDecimal.ZERO;
		for (Pcsk05001Entity item : tangetuMakeByPcsk050) {
			switch (form.getSkeiTani().toString()) {
			case TANPIN:
				// 商管単品別
				item.setShinHead("商品CD");
				item.setShinNmHead("商品名");
				break;
			case TENPOBUNRUI:
				// 商管店舗分類別
				item.setShinHead("店舗分類CD");
				item.setShinNmHead("店舗分類名");
				break;
			case MUTENPOBUNRUI:
				// 商管無店舗分類別
				item.setShinHead("無店舗分類CD");
				item.setShinNmHead("無店舗分類名");
				break;
			case BUMONBETU:
				// 商管部門別
				item.setShinHead("部門CD");
				item.setShinNmHead("部門名");
				break;
			}

			// 理論在庫数 = 月末在庫数-積送中在庫数
			item.setRironSu(item.getMzaiSu().subtract(item.getSekiSu()));
			// 理論在庫額 = 月末在庫金額-積送中在庫金額
			item.setRironGak(item.getMzaiGak().subtract(item.getSekiGak()));
			// 実月末在庫数 = 積送中在庫数+実棚数
			item.setJitsugetsumatsuSu(item.getSekiSu().add(item.getHzaiSu()));
			// 実月末在庫額 = 積送中在庫金額+実棚金額
			item.setJitsugetsumatsuGak(item.getSekiGak().add(item.getHzaiGak()));
			// 不明ロス数 = 実棚数-月末在庫数-積送中在庫数
			item.setFumeirosuSu(item.getHzaiSu().subtract(item.getMzaiSu()).subtract(item.getSekiSu()));
			// 不明ロス額 = 実棚金額-月末在庫金額-積送中在庫金額
			item.setFumeirosuGak(item.getHzaiGak().subtract(item.getMzaiGak()).subtract(item.getSekiGak()));
			// 供給原価計
			kyokGenkaKei = item.getSzaiGak().add(item.getSireGak().add(item.getKeihGak()).add(item.getFrinGak())
					.add(item.getFrisGak()).subtract(item.getSekiGak().add(item.getHzaiGak())));
			item.setKyokGenkaKei(kyokGenkaKei);
			// 供給剰余
			item.setKyokJoYo(item.getKyokGak().subtract(kyokGenkaKei));
			// 前年比(％) = 0
			item.setZenNenHi(BigDecimal.ZERO);
			// GPR(％)
			if (BigDecimal.ZERO.compareTo(item.getKyokGak()) == 0) {
				item.setGpr(BigDecimal.ZERO);
			} else {
				item.setGpr(item.getKyokGak().subtract(kyokGenkaKei).divide(item.getKyokGak(), 2, RoundingMode.HALF_UP)
						.multiply(new BigDecimal(100)));
			}
		}
		return tangetuMakeByPcsk050;

	}

	/**
	 * 期間単品集計取得する。
	 * @param form 入力内容
	 * @return 期間単品集計処理リスト
	 */
	private List<Pcsk05001Entity> kikanMakeTanpin(Map<String, Object> param) {
		List<Pcsk05001Entity> kikanMakeTanpinByPcsk050 = mapper.getKikanMakeTanpinByPcsk050(param);
		if (kikanMakeTanpinByPcsk050.size() == 0) {
			// メッセージ：CSK0002E：　該当データが存在しません。
			throw new ValidationException(new ServerErrorEntity("CSK0002E", Arrays.asList("該当データ")));
		}
		// 開始年月
		String startKym = param.get("startKymWk").toString();
		// 終了年月
		String endKym = param.get("endKymWk").toString();
		// wk供給原価計
		BigDecimal kyokGenkaKei = BigDecimal.ZERO;
		for (Pcsk05001Entity item : kikanMakeTanpinByPcsk050) {
			// 開始年月
			item.setStartKym(startKym);
			// 終了年月
			item.setEndKym(endKym);
			// 理論在庫数 = 月末在庫数-積送中在庫数
			item.setRironSu(item.getMzaiSu().subtract(item.getSekiSu()));
			// 理論在庫額 = 月末在庫金額-積送中在庫金額
			item.setRironGak(item.getMzaiGak().subtract(item.getSekiGak()));
			// 実月末在庫数 = 積送中在庫数+実棚数
			item.setJitsugetsumatsuSu(item.getSekiSu().add(item.getHzaiSu()));
			// 実月末在庫額 = 積送中在庫金額+実棚金額
			item.setJitsugetsumatsuGak(item.getSekiGak().add(item.getHzaiGak()));
			// 不明ロス数 = 実棚数-月末在庫数-積送中在庫数
			item.setFumeirosuSu(item.getHzaiSu().subtract(item.getMzaiSu()).subtract(item.getSekiSu()));
			// 不明ロス額 = 実棚金額-月末在庫金額-積送中在庫金額
			item.setFumeirosuGak(item.getHzaiGak().subtract(item.getMzaiGak()).subtract(item.getSekiGak()));
			// 供給原価計
			kyokGenkaKei = item.getSzaiGak().add(item.getSireGak().add(item.getKeihGak()).add(item.getFrinGak())
					.add(item.getFrisGak()).subtract(item.getSekiGak().add(item.getHzaiGak())));
			item.setKyokGenkaKei(kyokGenkaKei);
			// 供給剰余
			item.setKyokJoYo(item.getKyokGak().subtract(kyokGenkaKei));
			// 前年比(％) = 0
			item.setZenNenHi(BigDecimal.ZERO);
			// GPR(％)
			if (BigDecimal.ZERO.compareTo(item.getKyokGak()) == 0) {
				item.setGpr(BigDecimal.ZERO);
			} else {
				item.setGpr(item.getKyokGak().subtract(kyokGenkaKei).divide(item.getKyokGak(), 2, RoundingMode.HALF_UP)
						.multiply(new BigDecimal(100)));
			}
		}
		return kikanMakeTanpinByPcsk050;
	}

	/**
	 * 期間集計取得する。
	 * @param form 入力内容
	 * @return 期間集計処理リスト
	 */
	private List<Pcsk05001Entity> kikanMake(Map<String, Object> param, Pcsk050Form form) {
		List<Pcsk05001Entity> kikanMakeByPcsk050 = mapper.getKikanMakeByPcsk050(param);
		if (kikanMakeByPcsk050.size() == 0) {
			// メッセージ：CSK0002E：　該当データが存在しません。
			throw new ValidationException(new ServerErrorEntity("CSK0002E", Arrays.asList("該当データ")));
		}
		// wk供給原価計
		BigDecimal kyokGenkaKei = BigDecimal.ZERO;
		// 開始年月
		String startKym = param.get("startKymWk").toString();
		// 終了年月
		String endKym = param.get("endKymWk").toString();
		for (Pcsk05001Entity item : kikanMakeByPcsk050) {
			switch (form.getSkeiTani().toString()) {
			case TANPIN:
				// 商管単品別
				item.setShinHead("商品CD");
				item.setShinNmHead("商品名");
				break;
			case TENPOBUNRUI:
				// 商管店舗分類別
				item.setShinHead("店舗分類CD");
				item.setShinNmHead("店舗分類名");
				break;
			case MUTENPOBUNRUI:
				// 商管無店舗分類別
				item.setShinHead("無店舗分類CD");
				item.setShinNmHead("無店舗分類名");
				break;
			case BUMONBETU:
				// 商管部門別
				item.setShinHead("部門CD");
				item.setShinNmHead("部門名");
				break;
			}
			// 開始年月
			item.setStartKym(startKym);
			// 終了年月
			item.setEndKym(endKym);
			// 理論在庫数 = 月末在庫数-積送中在庫数
			item.setRironSu(item.getMzaiSu().subtract(item.getSekiSu()));
			// 理論在庫額 = 月末在庫金額-積送中在庫金額
			item.setRironGak(item.getMzaiGak().subtract(item.getSekiGak()));
			// 実月末在庫数 = 積送中在庫数+実棚数
			item.setJitsugetsumatsuSu(item.getSekiSu().add(item.getHzaiSu()));
			// 実月末在庫額 = 積送中在庫金額+実棚金額
			item.setJitsugetsumatsuGak(item.getSekiGak().add(item.getHzaiGak()));
			// 不明ロス数 = 実棚数-月末在庫数-積送中在庫数
			item.setFumeirosuSu(item.getHzaiSu().subtract(item.getMzaiSu()).subtract(item.getSekiSu()));
			// 不明ロス額 = 実棚金額-月末在庫金額-積送中在庫金額
			item.setFumeirosuGak(item.getHzaiGak().subtract(item.getMzaiGak()).subtract(item.getSekiGak()));
			// 供給原価計
			kyokGenkaKei = item.getSzaiGak().add(item.getSireGak().add(item.getKeihGak()).add(item.getFrinGak())
					.add(item.getFrisGak()).subtract(item.getSekiGak().add(item.getHzaiGak())));
			item.setKyokGenkaKei(kyokGenkaKei);
			// 供給剰余
			item.setKyokJoYo(item.getKyokGak().subtract(kyokGenkaKei));
			// 前年比(％) = 0
			item.setZenNenHi(BigDecimal.ZERO);
			// GPR(％)
			if (BigDecimal.ZERO.compareTo(item.getKyokGak()) == 0) {
				item.setGpr(BigDecimal.ZERO);
			} else {
				item.setGpr(item.getKyokGak().subtract(kyokGenkaKei).divide(item.getKyokGak(), 2, RoundingMode.HALF_UP)
						.multiply(new BigDecimal(100)));
			}
		}
		return kikanMakeByPcsk050;
	}

	/**
	 * 業務チェックを行う
	 * @param form 入力内容
	 */
	private void businessCheck(Pcsk050Form form) {
		// 対象年月_FROMと対象年月_TO
		String startKym = form.getTargetYmFrom();
		String endKym = form.getTargetYmTo();
		// エラーメッセージリスト
		ServerErrorList errorList = new ServerErrorList();
		if (new BigDecimal(2).compareTo(form.getTargetKkn()) != 0) {
			return;
		}
		// 対象年月_FROM＞対象年月_TOの場合はエラーメッセージを表示する
		if (!BLANK_MARK.equals(StringUtils.trim(startKym))
				&& !BLANK_MARK.equals(StringUtils.trim(endKym))
				&& Integer.valueOf(startKym) > Integer.valueOf(endKym)) {

			List<String> errorItems = new ArrayList<String>();
			errorItems.add("targetYmFrom");
			errorItems.add("targetYmTo");
			ServerErrorEntity ServerErrorEntity = new ServerErrorEntity();
			ServerErrorEntity.setErrorItems(errorItems);
			ServerErrorEntity.setMsgId("NKS0006E");
			errorList.add(ServerErrorEntity);
			throw new ValidationException(errorList);
		}
	}
}
