//================================================================================
// ID: PEXT020
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ProgramName: 仕入・訂正入力
// ClassName: Pext020Service
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2022/11/28 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.service.ext;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coop.kyushu.common.entity.MstmeismEntity;
import coop.kyushu.common.entity.ServerErrorList;
import coop.kyushu.common.exception.ValidationException;
import coop.kyushu.common.mapper.MstmeismMapper;
import coop.kyushu.common.util.BeanUtil;
import coop.kyushu.common.util.MessageUtil;
import coop.kyushu.common.util.TantoshaInfoUtil;
import coop.kyushu.gyomu_kanri.entity.ext.Pext020Entity;
import coop.kyushu.gyomu_kanri.entity.ext.Pext020Items;
import coop.kyushu.gyomu_kanri.entity.ext.Pext020JigyoshoItem;
import coop.kyushu.gyomu_kanri.entity.ext.Pext020ListItem;
import coop.kyushu.gyomu_kanri.entity.ext.Pext020TorihikisakiItem;
import coop.kyushu.gyomu_kanri.form.ext.Pext020Form;
import coop.kyushu.gyomu_kanri.mapper.ext.Pext020Mapper;

/**
 * <pre>
 * 仕入・訂正入力サービス
 * </pre>
 */
@Service
public class Pext020Service {

	/** 店舗発注累積ファイルマッパー */
	@Autowired
	private Pext020Mapper pext020Mapper;

	/** 名称マスタマッパー */
	@Autowired
	private MstmeismMapper codeMapper;

	/**
	 * メッセージ管理Util
	 */
	@Autowired
	private MessageUtil messageUtil;

	@Autowired
	private TantoshaInfoUtil tantoshaInfoUtil;

	private static final String SHUBETSU_ADD = "1";
	private static final String SHUBETSU_TAX_UPDATE = "6";

	/**
	 * 伝票情報取得
	 * @param form 入力内容
	 * @return 伝票情報
	 */
	public Pext020Entity getDempyo(Pext020Form form) {

		// チェック処理
		ServerErrorList errorList = new ServerErrorList();
		List<String> warningList = new ArrayList<String>();

		// ヘッダ部１ チェック
		checkHeader1(form, errorList, warningList);

		if (errorList.hasError()) {
			throw new ValidationException(errorList);
		}

		// 伝票ヘッダ情報取得
		Pext020Entity entity = new Pext020Entity();
		Pext020Entity dempyoHead = getDempyoHead(form);
		switch (form.getProcsShubt()) {
		case SHUBETSU_ADD:
			if (dempyoHead != null) {
				errorList.add("EXT0017E");
				throw new ValidationException(errorList);
			}
			break;
		default:
			if (dempyoHead == null) {
				errorList.add("EXT0016E");
				throw new ValidationException(errorList);
			}
			entity = dempyoHead;
		}

		// 伝票情報取得
		Map<String, Object> params = BeanUtil.toMap(form);
		List<Pext020ListItem> shohinList = pext020Mapper.getDempyo(params);
		entity.setShohinList(shohinList);

		if (warningList.size() != 0) {
			entity.setWarningList(warningList);
		}

		return entity;
	}

	/**
	 * 伝票更新チェック
	 * @param form 入力内容
	 * @return チェック結果(エラ―リスト、警告リスト)
	 */
	public Pext020Entity checkDempyoUpdate(Pext020Form form) {

		// チェック処理
		ServerErrorList errorList = new ServerErrorList();
		List<String> warningList = new ArrayList<String>();

		// ヘッダ部２チェック
		checkHeader2(form, errorList);

		// 処理種別が税率変更(=6)以外 
		if (!SHUBETSU_TAX_UPDATE.equals(form.getProcsShubt())) {
			checkListItem(form.getShohinList(), errorList, warningList);
		}

		if (errorList.hasError()) {
			throw new ValidationException(errorList);
		}

		Pext020Entity entity = new Pext020Entity();
		if (warningList.size() != 0) {
			entity.setWarningList(warningList);
		}

		return entity;
	}

	/**
	 * 伝票情報追加
	 * @param form 入力内容
	 * @return 更新結果
	 */
	public Pext020Entity add(Pext020Form form) {

		// チェック処理
		checkDempyoUpdate(form);

		// 店舗発注累積ファイル情報削除
		// NOTE: 物理削除により更新と追加を共存せず、更新と追加の処理は分ける方が望ましいと思うため、プロト用の実装と思われる。
		deleteDempyo(form);

		// 追加モードの保存
		for (int i = 0; i < form.getShohinList().size(); i++) {
			if (form.getShohinList().get(i).getJanShinCd() != null) {
				insertDempyo(form, form.getShohinList().get(i));
			}
		}

		// TODO:プロト用の実装
		Pext020Entity entity = new Pext020Entity();
		entity.setDempyoNo(form.getDempyoNo());

		return entity;
	}

	/**
	 * 仕入・訂正入力一覧商品情報取得
	 * @param form 入力内容
	 * @return 商品情報
	 */
	public Pext020ListItem getShohin(Pext020Form form) {

		int targetIndex = form.getItemIndex();
		Pext020ListItem shohin = form.getShohinList().get(targetIndex);

		if (shohin.getJanShinCd() == null) {
			return shohin;
		}
		// 商品情報取得
		Map<String, Object> params = new HashMap<>();
		params.put("cqshincd", shohin.getJanShinCd());
		Pext020Items shohinInfo = pext020Mapper.getShohinSingle(params);
		if (shohinInfo == null) {
			shohin.setShinNm(null);
			shohin.setKikaku(null);
			shohin.setIrisu(null);
			shohin.setSuryo(null);
			shohin.setSireTan(null);
			shohin.setSireGaku(null);
			shohin.setGenkaTanka(null);
			shohin.setGenkaGaku(null);
			shohin.setUriTanka(null);
			shohin.setBaikaGaku(null);
		} else {
			shohin.setShinNm(shohinInfo.getShinkj());
			shohin.setKikaku(shohinInfo.getKikakn());
			shohin.setIrisu(shohinInfo.getNyuirisu());
			shohin.setSuryo(null);
			shohin.setSireTan(shohinInfo.getGentan());
			shohin.setSireGaku(null);
			shohin.setGenkaTanka(shohinInfo.getGentan());
			shohin.setGenkaGaku(null);
			shohin.setUriTanka(shohinInfo.getBaitan());
			shohin.setBaikaGaku(null);
		}

		return shohin;
	}

	/**
	 * 仕入・訂正入力事業所名取得
	 * @param form 入力内容
	 * @return 事業所情報
	 */
	public Pext020JigyoshoItem getJigyosho(Pext020Form form) {

		// 名称マスタ(A2)より事業所情報を取得する
		MstmeismEntity jigyosyoInfo = codeMapper.getCodeInfoSingle("A2", form.getJigyoCd());
		if (jigyosyoInfo == null) {
			return null;
		}

		// 会員生協マスタより対象の会員生協情報を取得する
		Map<String, Object> params = new HashMap<>();
		params.put("jigyoCd", form.getJigyoCd());
		params.put("tenCd", form.getJigyoCd());

		Pext020JigyoshoItem JigyoshoItem = pext020Mapper.getJigyosho(params);

		return JigyoshoItem;
	}

	/**
	 * 仕入・訂正入力取引先取得
	 * @param form 入力内容
	 * @return 取引先情報
	 */
	public Pext020TorihikisakiItem getTorihikisaki(Pext020Form form) {

		// 取引先マスタより取引先情報を取得する
		Map<String, Object> params = new HashMap<>();
		params.put("toricd", form.getToriskCd());
		Pext020TorihikisakiItem torihikisakiItem = pext020Mapper.getTorihikisaki(params);

		return torihikisakiItem;
	}

	/**
	 * 仕入・訂正入力 通過区分名取得
	 * @param form 入力内容
	 * @return 通過区分名
	 */
	public Pext020Entity getTsukaNohimBasho(Pext020Form form) {
		Pext020Entity entity = new Pext020Entity();

		// TODO:テストコ―ド（本来は通過区分名をDBアクセスして取得する処理が必要）
		entity.setTukaDelvPlaceNm("ﾃｽﾄ ﾂｳｶｸﾌﾞﾝﾒｲ");

		return entity;
	}

	/**
	 * 日数計算
	 * @param Date fromDate
	 * @param Date toDate
	 * @return fromDataからtoDateの日数
	 */
	// TODO:共通化する
	private int calcNissu(Date from, Date to) {
		return (int) TimeUnit.DAYS.convert(to.getTime() - from.getTime(), TimeUnit.MILLISECONDS);
	}

	/**
	 * ヘッダ部１ チェック
	 * @param form
	 * @param errorList
	 * @param warningList
	 */
	private void checkHeader1(Pext020Form form, ServerErrorList errorList, List<String> warningList) {
		// 事業所
		Pext020JigyoshoItem jigyoshoItem = getJigyosho(form);
		if (jigyoshoItem == null) {
			String[] msgParams = { "事業所", "会員生協" };
			errorList.add("CMS0020E", msgParams, "jigyoCd");
		}

		// 分類コ―ド
		if (codeMapper.getCodeInfoSingle("E7", form.getCatgCd()) == null) {
			errorList.add("EXT0080E", "分類コード", "catgCd");
		}

		switch (form.getProcsShubt()) {
		case SHUBETSU_ADD:
			// 取引先区分チェック
			if (codeMapper.getCodeInfoSingle("93", "19" + form.getToriKbn()) == null) {
				errorList.add("EXT0080E", "取引区分", "toriKbn");
			} else if (!isTaishoToriKbn(form.getToriKbn())) {
				errorList.add("EXT0010E", "toriKbn");
			}
			break;
		}

		// 取引先チェック
		Pext020TorihikisakiItem torihikisakiInfo = getTorihikisaki(form);
		if (torihikisakiInfo == null) {
			String[] msgParams = { "取引先", "取引先" };
			errorList.add("CMS0020E", msgParams, "toriskCd");
		} else {
			if ("1".equals(torihikisakiInfo.getToritskb())) {
				warningList.add(messageUtil.getMessage("EXT0001W"));
			}
			if (form.getToriKbn() != null
					&& "26".equals(form.getToriKbn()) && !"1".equals(torihikisakiInfo.getGensirkb())) {
				warningList.add(messageUtil.getMessage("EXT0002W"));
			}
		}

		// 納品日チェック
		if (SHUBETSU_ADD.equals(form.getProcsShubt())) {
			Date fromDate = toDateObj(form.getHachuDay().toString());
			Date toDate = toDateObj(form.getDelvDay().toString());
			if (fromDate != null && toDate != null) {
				int nissu = calcNissu(fromDate, toDate);
				if (nissu < 0) {
					errorList.add("EXT0014E", "delvDay");
				}
				if (nissu > 30) {
					errorList.add("EXT0013E", "delvDay");
				}
			}
		}
	}

	/**
	 * ヘッダ部２チェック
	 * @param form
	 * @param errorList
	 */
	private void checkHeader2(Pext020Form form, ServerErrorList errorList) {
		//　訂正区分チェック
		if (!isTeiseiKbn(form.getTeiseiKbn())) {
			errorList.add("EXT0080E", "訂正区分", "teiseiKbn");
		}

		// 伝票発行区分チェック
		if (!isDempyoIssKbn(form.getDempyoIssKbn())) {
			errorList.add("EXT0080E", "伝票発行区分", "dempyoIssKbn");
		}

		// 通過納品場所コードチェック
		if (codeMapper.getCodeInfoSingle("A2", form.getTukaDelvPlaceCd()) == null) {
			String[] msgParams = { "通過納品場所コード", "名称" };
			errorList.add("CMS0020E", msgParams, "tukaDelvPlaceCd");
		}
	}

	/**
	 * 単価ｘ数量は金額と一致するかチェック
	 * @param tanka 単価
	 * @param suryo 数量
	 * @param kingaku 金額
	 * @return True 一致、False 一致しない
	 */
	private boolean checkKingaku(BigDecimal tanka, BigDecimal suryo, BigDecimal kingaku) {

		if (nullIsZero(kingaku).compareTo(nullIsZero(tanka).multiply(nullIsZero(suryo))) != 0) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 商品金額チェック(検算処理)
	 * @param item 商品情報
	 * @param warningList 警告リスト
	 */
	private void checkKingaku(Pext020ListItem item, List<String> warningList) {
		List<String> warnParams = null;

		// 仕入金額チェック
		if (!checkKingaku(item.getSireTan(), item.getSuryo(), item.getSireGaku())) {
			warnParams = new ArrayList<String>();
			warnParams.add(item.getNo().toString());
			warnParams.add("仕入金額");
			warningList.add(messageUtil.getMessage("EXT0080W", warnParams));
		}
		// 原価金額チェック
		if (!checkKingaku(item.getGenkaTanka(), item.getSuryo(), item.getGenkaGaku())) {
			warnParams = new ArrayList<String>();
			warnParams.add(item.getNo().toString());
			warnParams.add("原価金額");
			warningList.add(messageUtil.getMessage("EXT0080W", warnParams));
		}
		// 売価金額チェック
		if (!checkKingaku(item.getUriTanka(), item.getSuryo(), item.getBaikaGaku())) {
			warnParams = new ArrayList<String>();
			warnParams.add(item.getNo().toString());
			warnParams.add("売価金額");
			warningList.add(messageUtil.getMessage("EXT0080W", warnParams));
		}
	}

	/**
	 * 仕入・訂正入力一覧チェック
	 * @param shohinList 商品リスト
	 * @param errorList エラ―リスト
	 * @param warningList 警告リスト
	 */
	private void checkListItem(List<Pext020ListItem> shohinList, ServerErrorList errorList, List<String> warningList) {

		for (int i = 0; i < shohinList.size(); i++) {
			if (shohinList.get(i).getJanShinCd() == null) {
				continue;
			}

			// 商品情報 存在チェック
			Map<String, Object> params = new HashMap<>();
			params.put("cqshincd", shohinList.get(i).getJanShinCd());
			Pext020Items shohinInfo = pext020Mapper.getShohinSingle(params);
			if (shohinInfo == null) {
				String[] msgParams = { shohinList.get(i).getNo().toString(), "商品コ―ド" };
				errorList.add("EXT0081E", msgParams, "janShinCd" + i);
				continue;
			}

			// 各金額(仕入金額、原価金額、売価金額)を検算する
			checkKingaku(shohinList.get(i), warningList);
		}
	}

	/**
	 * 伝票情報 削除
	 * @param form 入力内容
	 */
	private void deleteDempyo(Pext020Form form) {

		Map<String, Object> params = BeanUtil.toMap(form);

		// 店舗発注累積ファイル情報削除
		pext020Mapper.deleteDempyo(params);
	}

	/**
	 * 伝票ヘッダ情報取得
	 * @param Pext020Form
	 * @return コード情報
	 */
	private Pext020Entity getDempyoHead(Pext020Form form) {

		if (StringUtils.isEmpty(form.getToriKbn())) {
			form.setToriKbn("20");
		}

		Map<String, Object> params = BeanUtil.toMap(form);
		return pext020Mapper.getDempyoHead(params);
	}

	/**
	 * 伝票登録
	 * @param form 入力内容
	 */
	private void insertDempyo(Pext020Form form, Pext020ListItem item) {

		Map<String, Object> params = new HashMap<>();
		params.put("kseicd", form.getJigyoCd()); // 会員生協コード
		params.put("tencd", form.getTukaDelvPlaceCd()); // 店・支部コ―ド
		params.put("htkbn", BigDecimal.ONE); // 発注納品区分
		params.put("denno", form.getDempyoNo()); // 伝票番号
		params.put("sircd", form.getToriskCd()); // コープ仕入先コード
		params.put("nodate", form.getDelvDay()); // 指定納品日
		params.put("gyono", item.getNo()); // 行番号
		params.put("kbunrui", form.getCatgCd()); // 生協分類コード
		params.put("cbunrui", null); // コープ分類コード
		params.put("kdenkbn", form.getToriKbn()); // 伝票区分
		params.put("khadate", form.getHachuDay()); // 生協発注日
		params.put("jhkbn", BigDecimal.ONE); // 受発注区分
		params.put("skcd", BigDecimal.ZERO); // 倉庫コード
		params.put("jdate", form.getDelvDay()); // コープ受注日
		params.put("ktoricd", form.getToriskCd()); // 生協取引先コ―ド
		params.put("ctoricd", form.getToriskCd()); // コープ発注先コ―ド
		params.put("kseinma", form.getJigyoNm()); // 会員生協名（カナ）
		params.put("tennma", form.getTenNm()); // 店・支部名（カナ）
		params.put("hatnma", form.getToriskNm()); // 発注先名（カナ）
		params.put("sirnma", form.getToriskNm()); // 発注先名（カナ）
		params.put("sknma", null); // 倉庫名
		params.put("ttencd", form.getTukaDelvPlaceCd()); // 通過納品場所支部CD
		params.put("ttennma", form.getTukaDelvPlaceNm()); // 通過納品場所名
		params.put("jankik", BigDecimal.ONE); // ＪＡＮ・企画区分
		params.put("rootkbn", null); // ルート区分
		params.put("catno", BigDecimal.ZERO); // カタログ№
		params.put("hincd", item.getJanShinCd()); // ＪＡＮコード
		params.put("khincd", item.getJanShinCd().toString()); // 会員内部商品コード
		params.put("tanaban", null); // 棚番
		params.put("chincd", item.getJanShinCd()); // コープ内部商品コード
		params.put("hinna", item.getShinNm()); // 商品名（カナ）
		params.put("irisu", item.getIrisu()); // 入数
		params.put("hcssu", null); // 発注数（Ｃ／Ｓ）
		params.put("bandoru", null); // バンドル数
		params.put("pssu", item.getSuryo()); // 数量（Ｐ／Ｓ）
		params.put("tpssu", null); // 訂正後Ｐ／Ｓ数量
		params.put("gtanka", item.getGenkaTanka()); // 原単価
		params.put("utanka", item.getUriTanka()); // 売単価
		params.put("csirtan", item.getSireTan()); // コープ九州仕入単価
		params.put("gkingaku", item.getGenkaGaku()); // 原価金額
		params.put("ukingaku", item.getBaikaGaku()); // 売価金額
		params.put("csirkin", item.getSireGaku()); // コープ九州仕入金額
		params.put("sihsaito", null); // 支払サイト
		params.put("nyusaito", null); // 請求サイト
		params.put("taxkbn", null); // 税区分
		params.put("tax", form.getZeiRate()); // 税率
		params.put("turnkbn", null); // ターンアラウンド区分
		params.put("odenno", null); // 旧伝票番号
		params.put("hatkbn", null); // 発注区分
		params.put("takukbn", null); // 宅配区分
		params.put("gyoerr", null); // 行エラー
		params.put("torerr", null); // 重複エラー
		params.put("hinerr", null); // 商品エラー
		params.put("tanerr", null); // 単価エラー
		params.put("rdterr", null); // リードタイムエラー
		params.put("denerr", null); // 伝票崩れ
		params.put("irierr", null); // 入数エラー
		params.put("toserr", null); // ＣＸ商品エラー
		params.put("staerr", null); // 仕入単価エラー
		params.put("ehtflg", null); // 発注済みＦＬＧ
		params.put("esyflg", null); // 出荷済みＦＬＧ
		params.put("kyodate", null); // 供給計上年月
		params.put("sirdate", null); // 仕入計上年月
		params.put("hatform", null); // 発注フォーマット区分
		params.put("gupflg", null); // 月次更新済みＦＬＧ
		params.put("hatuno", null); // ＦＡＸ発注番号
		params.put("jgykbn", null); // 事業所区分
		params.put("sosincd", null); // 送信先コード
		params.put("message", form.getMessage()); // メッセージ
		params.put("syudate", null); // 出荷日
		params.put("nkakflg", null); // 入庫確定フラグ
		params.put("hsouflg", null); // 返品報告書送信済みフラグ
		params.put("hdenflg", null); // 返品伝票発行済みフラグ
		params.put("sccd", null); // Ｓ／ＣロケーションＣＤ
		params.put("fcpkbn", null); // Ｆコープ区分
		params.put("nouzkbn", null); // 納品済区分
		params.put("oflinkb", null); // オフライン区分
		params.put("tesikb", form.getTeiseiKbn()); // 訂正区分
		params.put("tokbaikb", null); // 特売区分
		params.put("qrctukkb", null); // ＱＲＣ通過区分
		params.put("update", null); // 更新日
		params.put("torikbn", form.getToriKbn()); // 取引区分
		params.put("sosin", null); // 送信相手先コード
		params.put("denhkkbn", form.getDempyoIssKbn()); // 伝票発行区分
		params.put("jtyouaicd", null); // 受注帳合企業コード
		params.put("jjigyoscd", null); // 受注帳合事業所コード
		params.put("jtyouaikj", null); // 受注企業名（漢字）
		params.put("hkseikj", null); // 発注生協名称（漢字）
		params.put("hjgyoskj", null); // 発注事業所名称（漢字）
		params.put("njgyoscd", null); // 内部事業所コード
		params.put("hikanrkb", null); // 日付管理区分
		params.put("shinkj", null); // 商品名（漢字）
		params.put("ukegnisu", null); // 受入限界日数
		params.put("cskb", null); // 発注単位区分
		params.put("ukgszymd", null); // 受入限界製造日
		params.put("ukgsyymd", null); // 受入限界賞味期限日
		params.put("brkkbn", null); // ばらけ区分
		params.put("syukhflg", null); // 出荷報告対象フラグ（ＥＸＰ）
		params.put("jnodate", form.getJitDelvDay()); // 実績納品日
		params.put("brnsgkbn", null); // 物流荷姿区分

		// MEMO：WHOカラムの値設定をしている
		params.put("sksi_usr", tantoshaInfoUtil.getTansCd());
		params.put("sksi_prgrm", "Pext020");
		params.put("kushn_usr", tantoshaInfoUtil.getTansCd());
		params.put("kushn_prgrm", "Pext020");

		pext020Mapper.insertDempyo(params);
	}

	/**
	 * 伝票発行区分チェック
	 * @param dempyoIssKbn 伝票発行区分
	 * @return true:伝票発行区分である false:伝票発行区分ではない
	 */
	private boolean isDempyoIssKbn(String dempyoIssKbn) {
		return "0".equals(dempyoIssKbn) || "1".equals(dempyoIssKbn);
	}

	/**
	 * 取引先区分 対象チェック
	 * @param toriKbn 取引先区分
	 * @return true:対象 false:対象外
	 */
	private boolean isTaishoToriKbn(String toriKbn) {
		if ("20".equals(toriKbn)
				|| "21".equals(toriKbn)
				|| "22".equals(toriKbn)
				|| "23".equals(toriKbn)
				|| "24".equals(toriKbn)
				|| "26".equals(toriKbn)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 訂正区分チェック
	 * @param teiseiKbn 訂正区分
	 * @return true: 訂正区分である false:訂正区分ではない
	 */
	private boolean isTeiseiKbn(String teiseiKbn) {
		return "0".equals(teiseiKbn) || "1".equals(teiseiKbn);
	}

	/* 金額のNullをZero変換
	 * @param bigKingaku 金額
	 * @return BigDecimal 変換値
	 */
	// TODO:共通化する
	// FIXME:規約"2.4.メソッド名"に違反している
	private BigDecimal nullIsZero(BigDecimal bigKingaku) {

		if (bigKingaku == null) {
			return BigDecimal.ZERO;
		} else {
			return bigKingaku;
		}
	}

	/**
	 * 日付文字列をDate型に変換する
	 * @param ymd 日付文字列(YYYYMMDD)
	 * @return Dateオブジェクト
	 */
	// TODO:共通化する
	// FIXME:規約"2.4.メソッド名"に違反している
	private Date toDateObj(String ymd) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			sdf.setLenient(false);
			return sdf.parse(ymd);
		} catch (Exception ex) {
			return null;
		}
	}
}
