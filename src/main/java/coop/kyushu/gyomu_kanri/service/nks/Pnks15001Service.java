//================================================================================
// ID: PNKS150
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks150Service
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/01 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.service.nks;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coop.kyushu.common.entity.ServerErrorEntity;
import coop.kyushu.common.exception.ValidationException;
import coop.kyushu.common.util.BeanUtil;
import coop.kyushu.common.util.DateUtil;
import coop.kyushu.gyomu_kanri.entity.com.Nsmm010Entity;
import coop.kyushu.gyomu_kanri.entity.com.Nsmm060Entity;
import coop.kyushu.gyomu_kanri.entity.com.Nstf020Entity;
import coop.kyushu.gyomu_kanri.entity.nks.Pnks15001Item;
import coop.kyushu.gyomu_kanri.form.nks.Pnks150Form;
import coop.kyushu.gyomu_kanri.mapper.com.Nsmm060Mapper;
import coop.kyushu.gyomu_kanri.mapper.nks.Pnks15001Mapper;

/**
 * <pre>
 * 【農産企画支援】納品指示書出力サービス
 * </pre>
 */
@Service
public class Pnks15001Service {

	/** 共通_メーカーマスタ マッパー */
	@Autowired
	private Nsmm060Mapper nsmm060Mapper;

	/** 農産企画支援_納品指示書発行指示マッパー */
	@Autowired
	private Pnks15001Mapper pnks150Mapper;

	/**
	 * 納品指示書発行指示画面の印刷
	 * @param form 入力内容
	 * @return 納品指示書発行指示画面JSON
	 */
	public List<Pnks15001Item> pnks15001Print(Pnks150Form form) {
		// 配達年月回
		StringBuilder haitatYmTimes = new StringBuilder();
		// BKEY
		haitatYmTimes.append(form.getHaitatYm()).append(form.getHaitatTimes());

		Map<String, Object> param = BeanUtil.toMap(form);
		// メーカーマスタ存在チェック
		param.put("makercd", form.getMaker());
		List<Nsmm060Entity> nsmm060Data = nsmm060Mapper.getMakerList(param);
		// データが取得できた場合
		if (nsmm060Data.size() == 0) {
			// メッセージ：NKS0009E：　農産企画商品マスタデータがありません。
			throw new ValidationException(new ServerErrorEntity("NKS0009E", Arrays.asList("メーカー"), "maker"));
		}

		// 納品指示データの取得する
		param.put("haitatYmTimes", haitatYmTimes.toString());
		List<Nstf020Entity> nonyuSiteiDataByPnks150 = pnks150Mapper.getNonyuSiteiDataByPnks150(param);
		// データが取得できた場合
		if (nonyuSiteiDataByPnks150.size() == 0) {
			// メッセージ：NKS0009E：　農産企画商品マスタデータがありません。
			throw new ValidationException(new ServerErrorEntity("NKS0009E", Arrays.asList("納品指示データ"), "haitatYm"));
		}

		// 納品指示書情報データ取得
		// 管理マスタ取得する。
		Nsmm010Entity kanriInfoByPnks15001 = pnks150Mapper.getkanriInfoByPnks15001(param);

		// データ取得
		List<Pnks15001Item> resultList = pnks150Mapper.getInfoByPnks15001(param);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		StringBuilder haitatYmTimesString = new StringBuilder().append(form.getHaitatYm().substring(0, 4)).append("年")
				.append(form.getHaitatYm().substring(4, 6))
				.append("月").append(form.getHaitatTimes()).append("週");
		String sysDate = sdf.format(DateUtil.getCurrentTimestamp());
		// 明細部編集
		for (Pnks15001Item item : resultList) {
			// 納品指示情報.センタ区分が2の場合
			if ("2".equals(item.getCntkb())) {
				// 変数.納品指示書印刷用Entity.連絡先名称=管理マスタデータ.連絡先名称（南部）
				item.setRenrakuKj(kanriInfoByPnks15001.getRenrakukj2());
				// 変数.納品指示書印刷用Entity.連絡先ＴＥＬ=管理マスタデータ.連絡先ＴＥＬ（南部）
				item.setRenrakuTel(kanriInfoByPnks15001.getRenrakutel2());
				// 変数.納品指示書印刷用Entity.連絡先ＦＡＸ=管理マスタデータ.連絡先ＦＡＸ（南部）
				item.setRenrakuFax(kanriInfoByPnks15001.getRenrakufax2());
				// 変数.納品指示書印刷用Entity.納品先場所=管理マスタデータ.納品先場所（南部）
				item.setNohinBasho(kanriInfoByPnks15001.getNohinbasho2());
				// 変数.納品指示書印刷用Entity.納品先漢字=管理マスタデータ.納品先漢字（南部）
				item.setNohinKj(kanriInfoByPnks15001.getNohinkj2());
				// 変数.納品指示書印刷用Entity.納品先ＴＥＬ=管理マスタデータ.納品先ＴＥＬ（南部）
				item.setNohinTel(kanriInfoByPnks15001.getNohintel2());
				// 変数.納品指示書印刷用Entity.納品先ＦＡＸ=管理マスタデータ.納品先ＦＡＸ（南部）
				item.setNohinFax(kanriInfoByPnks15001.getNohinfax2());
			} else {
				// 変数.納品指示書印刷用Entity.連絡先名称=管理マスタデータ.連絡先名称（北部）
				item.setRenrakuKj(kanriInfoByPnks15001.getRenrakukj());
				// 変数.納品指示書印刷用Entity.連絡先ＴＥＬ=管理マスタデータ.連絡先ＴＥＬ（北部）
				item.setRenrakuTel(kanriInfoByPnks15001.getRenrakutel());
				// 変数.納品指示書印刷用Entity.連絡先ＦＡＸ=管理マスタデータ.連絡先ＦＡＸ（北部）
				item.setRenrakuFax(kanriInfoByPnks15001.getRenrakufax());
				// 変数.納品指示書印刷用Entity.納品先場所=管理マスタデータ.納品先場所（北部）
				item.setNohinBasho(kanriInfoByPnks15001.getNohinbasho());
				// 変数.納品指示書印刷用Entity.納品先漢字=管理マスタデータ.納品先漢字（北部）
				item.setNohinKj(kanriInfoByPnks15001.getNohinkj());
				// 変数.納品指示書印刷用Entity.納品先ＴＥＬ=管理マスタデータ.納品先ＴＥＬ（北部）
				item.setNohinTel(kanriInfoByPnks15001.getNohintel());
				// 変数.納品指示書印刷用Entity.納品先ＦＡＸ=管理マスタデータ.納品先ＦＡＸ（北部）
				item.setNohinFax(kanriInfoByPnks15001.getNohinfax());
			}
			// 変数.納品指示書印刷用Entity.メーカー名称Disp=納品指示情報.メーカー名称+"　御中"
			item.setMakerKjDisp(item.getMakerKj());

			if (StringUtils.isNoneBlank(item.getNyukhm())) {
				StringBuilder nyukhmDISP = new StringBuilder()
						.append(Integer.valueOf(item.getNyukhm().substring(0, 2)).toString())
						.append("時").append(item.getNyukhm().substring(2, 4))
						.append("分まで");
				// 変数.納品指示書印刷用Entity.入荷時間DISP=納品指示情報.一件目.入荷時間(H時mm分)
				item.setNyukhmDISP(nyukhmDISP.toString());
			}
			// 変数.納品指示書印刷用Entity.システム時間=システム日時(yyyy年MM月dd日)
			item.setSysDate(sysDate);
			// 変数.納品指示書印刷用Entity.配達年月回=入力Form.配達年月+入力Form.配達回
			item.setHaitatuyMt(haitatYmTimesString.toString());
			// 変数.納品指示書印刷用Entity.BKEY=納品指示情報.一件目.BKEY
			item.setBkey(new StringBuilder().append(item.getMakerCd())
					.append(StringUtils.leftPad(item.getCntjigCd(), 5, "0")).toString());
			// 納品指示情報(n).数量合計1=納品指示情報(n).数量1
			item.setSuryoSum1(item.getSuryo1());
			// 納品指示情報(n).数量オール1=納品指示情報(n).数量1+納品指示情報(n).有償予備数1
			item.setSuryoAll1(item.getSuryo1().add(item.getYobisu1()));
			// 納品指示情報(n).五島数量合計1=納品指示情報(n).五島数量1
			item.setGotoSuryoSum1(item.getGotosuryo1());
			// 納品指示情報(n).五島数量オール1=納品指示情報(n).五島数量1+納品指示情報(n).五島予備数1
			item.setGotoSuryoAll1(item.getGotosuryo1().add(item.getGotoyobisu1()));
			// 納品指示情報(n).数量合計2=数量合計1+納品指示情報(n).数量2
			item.setSuryoSum2(item.getSuryoSum1().add(item.getSuryo2()));
			// 納品指示情報(n).数量オール2=数量オール1+納品指示情報(n).数量2+納品指示情報(n).有償予備数2
			item.setSuryoAll2(item.getSuryoAll1().add(item.getSuryo2()).add(item.getYobisu2()));
			// 納品指示情報(n).五島数量合計2=五島数量合計1+納品指示情報(n).五島数量2
			item.setGotoSuryoSum2(item.getGotoSuryoSum1().add(item.getGotosuryo2()));
			// 納品指示情報(n).五島数量オール2=五島数量オール1+納品指示情報(n).五島数量2+納品指示情報(n).有償予備数2
			item.setGotoSuryoAll2(item.getGotoSuryoAll1().add(item.getGotosuryo2().add(item.getGotoyobisu2())));
			// 納品指示情報(n).数量合計3=数量合計2+納品指示情報(n).数量3
			item.setSuryoSum3(item.getSuryoSum2().add(item.getSuryo3()));
			// 納品指示情報(n).数量オール3=数量オール2+納品指示情報(n).数量3+納品指示情報(n).有償予備数3
			item.setSuryoAll3(item.getSuryoAll2().add(item.getSuryo3()).add(item.getYobisu3()));
			// 納品指示情報(n).五島数量合計3=五島数量合計2+納品指示情報(n).五島数量3
			item.setGotoSuryoSum3(item.getGotoSuryoSum2().add(item.getGotosuryo3()));
			// 納品指示情報(n).五島数量オール3=五島数量オール2+納品指示情報(n).五島数量3+納品指示情報(n).有償予備数3
			item.setGotoSuryoAll3(item.getGotoSuryoAll2().add(item.getGotosuryo3().add(item.getGotoyobisu3())));
			// 納品指示情報(n).数量合計4=数量合計3+納品指示情報(n).数量4
			item.setSuryoSum4(item.getSuryoSum3().add(item.getSuryo4()));
			// 納品指示情報(n).数量オール4=数量オール3+納品指示情報(n).数量4+納品指示情報(n).有償予備数4
			item.setSuryoAll4(item.getSuryoAll3().add(item.getSuryo4()).add(item.getYobisu4()));
			// 納品指示情報(n).五島数量合計4=五島数量合計3+納品指示情報(n).五島数量4
			item.setGotoSuryoSum4(item.getGotoSuryoSum3().add(item.getGotosuryo4()));
			// 納品指示情報(n).五島数量オール4=五島数量オール3+納品指示情報(n).五島数量4+納品指示情報(n).有償予備数4
			item.setGotoSuryoAll4(item.getGotoSuryoAll3().add(item.getGotosuryo4().add(item.getGotoyobisu4())));
			// 納品指示情報(n).数量合計5=数量合計3+納品指示情報(n).数量4
			item.setSuryoSum5(item.getSuryoSum4().add(item.getSuryo5()));
			// 納品指示情報(n).数量オール5=数量オール4+納品指示情報(n).数量5+納品指示情報(n).有償予備数5
			item.setSuryoAll5(item.getSuryoAll4().add(item.getSuryo5()).add(item.getYobisu5()));

			// 納品指示情報(n).入荷日1=納品指示情報(n).入荷日
			item.setNyukYmd1(this.getNyukYmdAddDay(item.getNyukyMd(), 0));
			// 納品指示情報(n).入荷日2=納品指示情報(n).入荷日+1
			item.setNyukYmd2(this.getNyukYmdAddDay(item.getNyukyMd(), 1));
			// 納品指示情報(n).入荷日3=納品指示情報(n).入荷日+2
			item.setNyukYmd3(this.getNyukYmdAddDay(item.getNyukyMd(), 2));
			// 納品指示情報(n).入荷日4=納品指示情報(n).入荷日+3
			item.setNyukYmd4(this.getNyukYmdAddDay(item.getNyukyMd(), 3));
			// 納品指示情報(n).入荷日5=納品指示情報(n).入荷日+4
			item.setNyukYmd5(this.getNyukYmdAddDay(item.getNyukyMd(), 4));
		}

		return resultList;
	}

	/**
	 * 入荷日のyyyymmdd →yyyy年MM月dd日(曜日)
	 * @param date 入荷日
	 * @param addDays 日数
	 * @return 入荷日yyyy年MM月dd日(曜日)
	 */
	private String getNyukYmdAddDay(String date, int addDays) {
		if (StringUtils.isEmpty(date)) {
			return date;
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		LocalDate nyukYmd = LocalDate.parse(date, formatter).plusDays(addDays);

		String nyukYmdString = new StringBuilder().append(nyukYmd.getMonthValue()).append("月")
				.append(nyukYmd.getDayOfMonth()).append("日").append("(")
				.append(nyukYmd.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.JAPAN))
				.append(")").toString();
		return nyukYmdString;
	}
}
