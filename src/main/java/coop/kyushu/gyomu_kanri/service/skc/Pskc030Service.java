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

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import coop.kyushu.common.entity.ServerErrorEntity;
import coop.kyushu.common.exception.ValidationException;
import coop.kyushu.common.util.BeanUtil;
import coop.kyushu.common.util.DateUtil;
import coop.kyushu.common.util.TantoshaInfoUtil;
import coop.kyushu.gyomu_kanri.entity.com.Sztr102Entity;
import coop.kyushu.gyomu_kanri.entity.com.Szvw102Entity;
import coop.kyushu.gyomu_kanri.entity.skc.Pskc030Entity;
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
public class Pskc030Service {

	/** 【惣菜加工センター】製造計画数入力マッパー */
	@Autowired
	private Pskc030Mapper mapper;

	/**
	 * 担当者情報
	 */
	@Autowired
	private TantoshaInfoUtil tantoshaInfoUtil;

	/**
	 * 製造計画数入力画面の初期表示
	 * @param form 入力内容
	 * @return 初期データ
	 */
	public Pskc030Entity getInitialData(Pskc030Form form) {
		// 変数を宣言する。
		Pskc030Entity pskc030Entity = new Pskc030Entity();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		// 検索条件部用の店舗プルダウン取得する。
		List<Szvw102Entity> miseListByPskc030 = mapper.getMiseListByPskc030();

		// 自店舗のみ表示可能
		if (!form.getIsHonsyaKengen()) {
			pskc030Entity.setMise(form.getLoginShop());
		}
		// 表示週の初期表示は翌々週
		Date currentDate = new Date(DateUtil.getCurrentTimestamp().getTime());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);
		calendar.add(Calendar.WEEK_OF_YEAR, 2);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		pskc030Entity.setNhinymd(sdf.format(calendar.getTime()));

		pskc030Entity.setMiseList(miseListByPskc030);

		// 検索条件部用の表示週プルダウン取得する。
		List<Sztr102Entity> nhinymdListByPskc030 = mapper.getNhinymdListByPskc030();
		pskc030Entity.setNhinymdList(nhinymdListByPskc030);

		return pskc030Entity;
	}

	/**
	 * 製造計画数入力画面の検索処理
	 * @param form 入力内容
	 * @return 製造計画数入力画面の検索データ
	 */
	public Rskc03001Entity getSousaiSeizouKeikakuData(Pskc030Form form) {
		// 変数を宣言する。
		Rskc03001Entity pskc030Entity = new Rskc03001Entity();
		Map<String, Object> param = BeanUtil.toMap(form);
		// 時間書式設定
		DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("M/d");
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
			if (form.getIsHonsyaKengen()) {
				throw new ValidationException(new ServerErrorEntity("SKC0001E", Arrays.asList("対象の商品"), "tenpo"));
			} else {
				throw new ValidationException(new ServerErrorEntity("SKC0001E", Arrays.asList("対象の商品"), "hyojiWeek"));
			}
		}

		// 納品日（月）
		pskc030Entity.setDelvdayMon(LocalDate.parse(sosaiTaishoTenpoByPskc030.get(0).getDelvdayMon(), inputFormatter)
				.format(outputFormatter));
		// 納品日（火）
		pskc030Entity.setDelvdayTue(LocalDate.parse(sosaiTaishoTenpoByPskc030.get(0).getDelvdayTue(), inputFormatter)
				.format(outputFormatter));
		// 納品日（水）
		pskc030Entity.setDelvdayWed(LocalDate.parse(sosaiTaishoTenpoByPskc030.get(0).getDelvdayWed(), inputFormatter)
				.format(outputFormatter));
		// 納品日（木）
		pskc030Entity.setDelvdayThu(LocalDate.parse(sosaiTaishoTenpoByPskc030.get(0).getDelvdayThu(), inputFormatter)
				.format(outputFormatter));
		// 納品日（金）
		pskc030Entity.setDelvdayFri(LocalDate.parse(sosaiTaishoTenpoByPskc030.get(0).getDelvdayFri(), inputFormatter)
				.format(outputFormatter));
		// 納品日（土）
		pskc030Entity.setDelvdaySat(LocalDate.parse(sosaiTaishoTenpoByPskc030.get(0).getDelvdaySat(), inputFormatter)
				.format(outputFormatter));
		// 納品日（日）
		pskc030Entity.setDelvdaySun(LocalDate.parse(sosaiTaishoTenpoByPskc030.get(0).getDelvdaySun(), inputFormatter)
				.format(outputFormatter));

		pskc030Entity.setMeisaiList(sosaiTaishoTenpoByPskc030);
		return pskc030Entity;
	}

	/**
	 * 製造計画数入力画面の保存処理
	 * @param form 入力内容
	 */
	@Transactional(rollbackFor = Exception.class)
	public Rskc03001Entity save(Pskc030Form form) {
		Map<String, Object> param = BeanUtil.toMap(form);
		// 惣菜製造計画データ登録する、または更新する。
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		// 入力用Formの商品明細リストの件数分、以下の処理を繰り返する
		param.put("usrid", StringUtils.trimAllWhitespace(tantoshaInfoUtil.getTansCd()));
		param.put("nchj", DateUtil.getCurrentTimestamp());
		for (Rskc03001ListItem item : form.getMeisaiList()) {
			// 入力用Formの商品明細リスト(n).月曜日から日曜日までのデータを反復処理します。
			//店コード=商品明細リスト(n).店コード
			param.put("tenpo", form.getTenpo());
			//CQ商品コード=商品明細リスト(n).CQ商品コード
			param.put("cqshincd", item.getShin());
			//月曜日
			//納品日=商品明細リスト(n).(k)曜日.納品日
			param.put("nhinymd", item.getDelvdayMon());
			//計画数=商品明細リスト(n).(k)曜日.計画数
			param.put("plansu", item.getPlanSuMon());
			//納品数=商品明細リスト(n).(k)曜日.納品数
			param.put("nhinsu", item.getDelvSuMon());
			mapper.insertUpdateSousaiSeizouKeikakuDataByPskc030(param);

			//火曜日
			//納品日=商品明細リスト(n).(k)曜日.納品日
			param.put("nhinymd", item.getDelvdayTue());
			//計画数=商品明細リスト(n).(k)曜日.計画数
			param.put("plansu", item.getPlanSuTue());
			//納品数=商品明細リスト(n).(k)曜日.納品数
			param.put("nhinsu", item.getDelvSuTue());
			mapper.insertUpdateSousaiSeizouKeikakuDataByPskc030(param);

			//水曜日
			//納品日=商品明細リスト(n).(k)曜日.納品日
			param.put("nhinymd", item.getDelvdayWed());
			//計画数=商品明細リスト(n).(k)曜日.計画数
			param.put("plansu", item.getPlanSuWed());
			//納品数=商品明細リスト(n).(k)曜日.納品数
			param.put("nhinsu", item.getDelvSuWed());
			mapper.insertUpdateSousaiSeizouKeikakuDataByPskc030(param);

			//木曜日
			//納品日=商品明細リスト(n).(k)曜日.納品日
			param.put("nhinymd", item.getDelvdayThu());
			//計画数=商品明細リスト(n).(k)曜日.計画数
			param.put("plansu", item.getPlanSuThu());
			//納品数=商品明細リスト(n).(k)曜日.納品数
			param.put("nhinsu", item.getDelvSuThu());
			mapper.insertUpdateSousaiSeizouKeikakuDataByPskc030(param);

			//金曜日
			//納品日=商品明細リスト(n).(k)曜日.納品日
			param.put("nhinymd", item.getDelvdayFri());
			//計画数=商品明細リスト(n).(k)曜日.計画数
			param.put("plansu", item.getPlanSuFri());
			//納品数=商品明細リスト(n).(k)曜日.納品数
			param.put("nhinsu", item.getDelvSuFri());
			mapper.insertUpdateSousaiSeizouKeikakuDataByPskc030(param);

			//土曜日
			//納品日=商品明細リスト(n).(k)曜日.納品日
			param.put("nhinymd", item.getDelvdaySat());
			//計画数=商品明細リスト(n).(k)曜日.計画数
			param.put("plansu", item.getPlanSuSat());
			//納品数=商品明細リスト(n).(k)曜日.納品数
			param.put("nhinsu", item.getDelvSuSat());
			mapper.insertUpdateSousaiSeizouKeikakuDataByPskc030(param);

			//日曜日
			//納品日=商品明細リスト(n).(k)曜日.納品日
			param.put("nhinymd", item.getDelvdaySun());
			//計画数=商品明細リスト(n).(k)曜日.計画数
			param.put("plansu", item.getPlanSuSun());
			//納品数=商品明細リスト(n).(k)曜日.納品数
			param.put("nhinsu", item.getDelvSuSun());
			mapper.insertUpdateSousaiSeizouKeikakuDataByPskc030(param);
		}

		// 製造計画データ削除する
		// 納品日_from=入力用Form.表示週.開始日
		param.put("nhinymdFrom", form.getHyojiWeek());
		// 納品日_to=入力用Form.表示週.終了日
		LocalDate date = LocalDate.parse(form.getHyojiWeek(), formatter);
		param.put("nhinymdTo", date.plusDays(6).format(formatter));
		mapper.deleteSousaiSeizouKeikakuDataByPskc030(param);

		// 対象店舗登録する。
		mapper.insertSosaiTaishoTenpoByPskc030(param);
		return getSousaiSeizouKeikakuData(form);

	}
}
