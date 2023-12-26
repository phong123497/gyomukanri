//================================================================================
// ID: Pcsk060
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ProgramName: 粗利益管理表出力
// ClassName: Pcsk06001Service
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/8/18 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.service.csk;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import coop.kyushu.common.entity.ServerErrorList;
import coop.kyushu.common.exception.ValidationException;
import coop.kyushu.common.util.BeanUtil;
import coop.kyushu.common.util.CsvUtil;
import coop.kyushu.common.util.FileDownloadUtil;
import coop.kyushu.common.util.TantoshaInfoUtil;
import coop.kyushu.gyomu_kanri.entity.csk.Pcsk06001Entity;
import coop.kyushu.gyomu_kanri.entity.csk.Pcsk06001ListItem;
import coop.kyushu.gyomu_kanri.entity.csk.Pcsk06001PdfListItem;
import coop.kyushu.gyomu_kanri.form.csk.Pcsk060Form;
import coop.kyushu.gyomu_kanri.mapper.csk.Pcsk060Mapper;

/**
 * <pre>
 * 【コープ九州商管】粗利益管理表出力
 * </pre>
 */
@Service
public class Pcsk06001Service {
	/** 【コープ九州商管】粗利益管理表出力 */
	@Autowired
	private Pcsk060Mapper pcsk060Mapper;

	private final static String CHOHYO_ID = "PCSK060";

	private final Locale locale = new Locale("ja", "JP");

	/* 一時的にCSVファイルの保存パス */
	@Value("${GYOMU_REPORT_TEMP_PATH}")
	private String tempCsvFilePath;

	/* 担当者情報 */
	@Autowired
	private TantoshaInfoUtil tantoshaInfoUtil;

	/* 空白文字列 */
	private final static String BLANK_MARK = "";

	/* ファイルをダウンロードする共通部品 */
	@Autowired
	private FileDownloadUtil fileDownloadUtil;

	/* CSVファイルタイトル */
	private final static List<String> CSV_FILE_TITLE = List.of(
			"期間（自）", "期間（至）", "分類", "供給高", "前年比(%)", "月初棚卸高", "仕入高", "仕入値引", "他勘定振替高", "月末棚卸在庫",
			"供給原価", "粗利益高", "粗利益高GPR(%)", "予算差", "前年差", "前年差(%)", "年契", "供給剰余金", "GPR(%)");
	// 分類区分マップ
	private final static Map<String, String> bunruiMap = new HashMap<>() {
		{
			put("9501", "農産商品部");
			put("9502", "水産商品部");
			put("9503", "畜産商品部");
			put("9504", "日配商品部");
			put("9505", "食品商品部");
			put("9500", "商品本部");
			put("9380", "無店舗非食品");
			put("9385", "くらしと生協");
			put("9386", "スクロール");
			put("9331", "クリム");
			put("9410", "店舗");
			put("9612", "店舗用度品");
		}
	};

	/**
	 * 粗利益管理表出力用データの出力処理
	 * @param form 入力内容
	 * @throws Exception
	 * @return Pcsk06001Entity型のデータ
	 */
	public Pcsk06001Entity pcsk06001PrintPdf(Pcsk060Form form) throws Exception {
		ServerErrorList errorList = new ServerErrorList();
		Pcsk06001Entity entity = new Pcsk06001Entity();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String creday = sdf.format(date);
		//会計部門コードリスト
		Map<String, Object> mapParams = BeanUtil.toMap(form);

		if ("2".equals(form.getMngTaikeiKbn())) {
			List<String> bunruiCdList = new ArrayList<>();
			// 農産商品部
			bunruiCdList.add(form.getNosanShinDept());
			// 水産商品部
			bunruiCdList.add(form.getSuisanShinDept());
			// 畜産商品部
			bunruiCdList.add(form.getChikusanShinDept());
			// 日配商品部
			bunruiCdList.add(form.getNippaiShinDept());
			// 食品商品部 
			bunruiCdList.add(form.getFoodShinDept());
			// 商品本部
			bunruiCdList.add(form.getShinHombu());
			// 無店舗非食品
			bunruiCdList.add(form.getMutempoHifood());
			// くらしと生協
			bunruiCdList.add(form.getLifeAndSeiky());
			// スクロール
			bunruiCdList.add(form.getScroll());
			// クリム
			bunruiCdList.add(form.getKlim());
			// 店舗
			bunruiCdList.add(form.getTempo());
			// 店舗用度品
			bunruiCdList.add(form.getTempoYodohin());
			List<String> bunruiNameList = new ArrayList<>();
			List<String> bunruicdParamList = new ArrayList<>();
			for (String bunruicd : bunruiCdList) {
				if (StringUtils.isNotEmpty(bunruicd) && !"0".equals(bunruicd)) {
					bunruiNameList.add(bunruiMap.get(bunruicd));
					bunruicdParamList.add(bunruicd);
				}
			}
			entity.setMngTaikeiKbn(StringUtils.join(bunruiNameList, "、"));
			if (bunruicdParamList.isEmpty()) {
				bunruicdParamList.add("");
			}
			mapParams.put("cList", bunruicdParamList);
		} else {
			entity.setMngTaikeiKbn("全体");
		}
		//データを検索する
		List<Pcsk06001PdfListItem> pdfDataList = pcsk060Mapper.getPrintPdfDataByPcsk060(mapParams);
		if (ObjectUtils.isEmpty(pdfDataList)) {
			errorList.add("CSK0002E", "該当データ", null);
			throw new ValidationException(errorList);
		}
		Map<String, Pcsk06001PdfListItem> bunruicd2Map = new HashMap<>();
		Map<String, Pcsk06001PdfListItem> bunruicd3Map = new HashMap<>();
		for (Pcsk06001PdfListItem item : pdfDataList) {
			item.setGokyuuKou(formatWithComma(item.getGokyuuKou()));
			item.setYosanCha(formatWithComma(item.getYosanCha()));
			item.setTsukiHatsuTanaoroshiKou(formatWithComma(item.getTsukiHatsuTanaoroshiKou()));
			item.setShiireKou(formatWithComma(item.getShiireKou()));
			item.setShiireGari(formatWithComma(item.getShiireGari()));
			item.setTaKanjouFurikaeKou(formatWithComma(item.getTaKanjouFurikaeKou()));
			item.setTsukiSueTanaoroshiZaiko(formatWithComma(item.getTsukiSueTanaoroshiZaiko()));
			item.setZenNenCha(formatWithComma(item.getZenNenCha()));
			item.setKyoukyuuGenka(formatWithComma(item.getKyoukyuuGenka()));
			item.setSoriRieKou(formatWithComma(item.getSoriRieKou()));
			item.setNenkei(formatWithComma(item.getNenkei()));
			item.setKyoukyuuJouryoKin(formatWithComma(item.getKyoukyuuJouryoKin()));

			item.setZenNenHi(truncateToZeroIfZero(item.getZenNenHi()));
			item.setSoriRieKouGPR(truncateToZeroIfZero(item.getSoriRieKouGPR()));
			item.setZenNenChaGPR(truncateToZeroIfZero(item.getZenNenChaGPR()));
			item.setKyoukyuuJouryoKinGPR(truncateToZeroIfZero(item.getKyoukyuuJouryoKinGPR()));
			if (StringUtils.isBlank(item.getBunruicd3())) {
				if (!StringUtils.isBlank(item.getBunruicd2())) {
					bunruicd2Map.put(item.getBunruicd1() + item.getBunruicd2(), item);
				} else {
					bunruicd3Map.put(item.getBunruicd1(), item);
				}
			}
		}
		// OP明細リスト
		List<Pcsk06001PdfListItem> topData = new ArrayList<>();
		// CENTER明細リスト
		List<Pcsk06001PdfListItem> centerData = new ArrayList<>();
		// BOTTOM明細リスト
		List<Pcsk06001PdfListItem> bottomData = new ArrayList<>();
		for (Pcsk06001PdfListItem item : pdfDataList) {

			item.setBunruiName1(item.getCatg());
			Pcsk06001PdfListItem item2 = bunruicd2Map.get(item.getBunruicd1() + item.getBunruicd2());
			if (!ObjectUtils.isEmpty(item2)) {
				item.setBunruiName2(item2.getCatg());
				item.setGokyuuKou2(item2.getGokyuuKou());
				item.setYosanCha2(item2.getYosanCha());
				item.setTsukiHatsuTanaoroshiKou2(item2.getTsukiHatsuTanaoroshiKou());
				item.setShiireKou2(item2.getShiireKou());
				item.setShiireGari2(item2.getShiireGari());
				item.setTaKanjouFurikaeKou2(item2.getTaKanjouFurikaeKou());
				item.setTsukiSueTanaoroshiZaiko2(item2.getTsukiSueTanaoroshiZaiko());
				item.setZenNenCha2(item2.getZenNenCha());
				item.setKyoukyuuGenka2(item2.getKyoukyuuGenka());
				item.setSoriRieKou2(item2.getSoriRieKou());
				item.setSoriRieKouGPR2(item2.getSoriRieKouGPR());
				item.setNenkei2(item2.getNenkei());
				item.setKyoukyuuJouryoKin2(item2.getKyoukyuuJouryoKin());
				item.setZenNenHi2(item2.getZenNenHi());
				item.setSoriRieKouGPR2(item2.getSoriRieKouGPR());
				item.setZenNenChaGPR2(item2.getZenNenChaGPR());
				item.setKyoukyuuJouryoKinGPR2(item2.getKyoukyuuJouryoKinGPR());

			}
			Pcsk06001PdfListItem item3 = bunruicd3Map.get(item.getBunruicd1());
			if (!ObjectUtils.isEmpty(item3)) {
				item.setBunruiName3(item3.getCatg());
				item.setGokyuuKou3(item3.getGokyuuKou());
				item.setYosanCha3(item3.getYosanCha());
				item.setTsukiHatsuTanaoroshiKou3(item3.getTsukiHatsuTanaoroshiKou());
				item.setShiireKou3(item3.getShiireKou());
				item.setShiireGari3(item3.getShiireGari());
				item.setTaKanjouFurikaeKou3(item3.getTaKanjouFurikaeKou());
				item.setTsukiSueTanaoroshiZaiko3(item3.getTsukiSueTanaoroshiZaiko());
				item.setZenNenCha3(item3.getZenNenCha());
				item.setKyoukyuuGenka3(item3.getKyoukyuuGenka());
				item.setSoriRieKou3(item3.getSoriRieKou());
				item.setSoriRieKouGPR3(item3.getSoriRieKouGPR());
				item.setNenkei3(item3.getNenkei());
				item.setKyoukyuuJouryoKin3(item3.getKyoukyuuJouryoKin());
				item.setZenNenHi3(item3.getZenNenHi());
				item.setSoriRieKouGPR3(item3.getSoriRieKouGPR());
				item.setZenNenChaGPR3(item3.getZenNenChaGPR());
				item.setKyoukyuuJouryoKinGPR3(item3.getKyoukyuuJouryoKinGPR());
			}
			if ("10".equals(item.getBunruicd1()) || "20".equals(item.getBunruicd1())) {
				if (!StringUtils.isBlank(item.getBunruicd3())) {
					topData.add(item);
				}
			} else if ("30".equals(item.getBunruicd1())) {
				if (!StringUtils.isBlank(item.getBunruicd2())) {
					centerData.add(item);
				}
			} else {
				bottomData.add(item);
			}
		}
		entity.setTopData(topData);
		entity.setCenterData(centerData);
		entity.setBottomData(bottomData);
		entity.setNendo(form.getNendo());
		entity.setMonthlyFrom(Integer.valueOf(form.getMonthlyFrom().substring(4, 6)).toString());
		entity.setMonthlyTo(Integer.valueOf(form.getMonthlyTo().substring(4, 6)).toString());
		entity.setCreDay(creday);
		return entity;

	}

	/**
	 * 粗利益管理表出力用データの出力処理
	 * @param form 入力内容
	 * @throws Exception
	 */
	public void pcsk06001PrintCsv(Pcsk060Form form) throws Exception {
		ServerErrorList errorList = new ServerErrorList();

		Map<String, Object> mapParams = BeanUtil.toMap(form);
		if ("2".equals(form.getMngTaikeiKbn())) {
			List<String> bunruiCdList = new ArrayList<>();
			// 農産商品部
			bunruiCdList.add(form.getNosanShinDept());
			// 水産商品部
			bunruiCdList.add(form.getSuisanShinDept());
			// 畜産商品部
			bunruiCdList.add(form.getChikusanShinDept());
			// 日配商品部
			bunruiCdList.add(form.getNippaiShinDept());
			// 食品商品部 
			bunruiCdList.add(form.getFoodShinDept());
			// 商品本部
			bunruiCdList.add(form.getShinHombu());
			// 無店舗非食品
			bunruiCdList.add(form.getMutempoHifood());
			// くらしと生協
			bunruiCdList.add(form.getLifeAndSeiky());
			// スクロール
			bunruiCdList.add(form.getScroll());
			// クリム
			bunruiCdList.add(form.getKlim());
			// 店舗
			bunruiCdList.add(form.getTempo());
			// 店舗用度品
			bunruiCdList.add(form.getTempoYodohin());
			List<String> bunruicdParamList = new ArrayList<>();
			for (String bunruicd : bunruiCdList) {
				if (StringUtils.isNotEmpty(bunruicd) && !"0".equals(bunruicd)) {
					bunruicdParamList.add(bunruicd);
				}
			}
			if (bunruicdParamList.isEmpty()) {
				bunruicdParamList.add("");
			}
			mapParams.put("cList", bunruicdParamList);
		}
		//データを検索する
		List<Pcsk06001ListItem> csvDataList = pcsk060Mapper.getPrintDataByPcsk060(mapParams);
		if (ObjectUtils.isEmpty(csvDataList)) {
			errorList.add("CSK0002E", "該当データ", null);
			throw new ValidationException(errorList);
		}
		for (Pcsk06001ListItem item : csvDataList) {
			item.setGokyuuKou(formatWithComma(item.getGokyuuKou()));
			item.setYosanCha(formatWithComma(item.getYosanCha()));
			item.setTsukiHatsuTanaoroshiKou(formatWithComma(item.getTsukiHatsuTanaoroshiKou()));
			item.setShiireKou(formatWithComma(item.getShiireKou()));
			item.setShiireGari(formatWithComma(item.getShiireGari()));
			item.setTaKanjouFurikaeKou(formatWithComma(item.getTaKanjouFurikaeKou()));
			item.setTsukiSueTanaoroshiZaiko(formatWithComma(item.getTsukiSueTanaoroshiZaiko()));
			item.setZenNenCha(formatWithComma(item.getZenNenCha()));
			item.setKyoukyuuGenka(formatWithComma(item.getKyoukyuuGenka()));
			item.setSoriRieKou(formatWithComma(item.getSoriRieKou()));
			item.setNenkei(formatWithComma(item.getNenkei()));
			item.setKyoukyuuJouryoKin(formatWithComma(item.getKyoukyuuJouryoKin()));
			item.setZenNenHi(truncateToZeroIfZero(item.getZenNenHi()));
			item.setSoriRieKouGPR(truncateToZeroIfZero(item.getSoriRieKouGPR()));
			item.setZenNenChaGPR(truncateToZeroIfZero(item.getZenNenChaGPR()));
			item.setKyoukyuuJouryoKinGPR(truncateToZeroIfZero(item.getKyoukyuuJouryoKinGPR()));
		}

		// List<Entity>からList<List<String>>への変換
		List<List<String>> csvItemList = this.handleListEntityData(csvDataList);

		// CSVタイトルを追加する
		csvItemList.add(0, CSV_FILE_TITLE);
		// CSVファイルを作成する
		String loginUserId = StringUtils.trim(tantoshaInfoUtil.getTansCd());
		String serverFilePath = CsvUtil.createCsv(tempCsvFilePath, loginUserId, CHOHYO_ID, csvItemList,
				new String("SJIS"));

		// システム時間を取得する
		String systemDataStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));

		// CSV名を作成する
		StringBuffer stringBuffer = new StringBuffer();
		if ("1".equals(form.getShubt())) {
			stringBuffer.append("粗利益管理表（管理）_");
		} else {
			stringBuffer.append("粗利益管理表（財務）_");
		}
		stringBuffer.append(loginUserId);
		stringBuffer.append("_" + systemDataStr);
		stringBuffer.append(".csv");

		// CSVファイルをダウンロードする
		fileDownloadUtil.download(serverFilePath, stringBuffer.toString());

	}

	/**
	 * List<Entity>からList<List<String>>への変換ツールメソッド
	 * @param dataList List<Entity>型のデータ
	 * @return List<List<String>>型のデータ
	 * @throws IllegalAccessException
	 */
	private List<List<String>> handleListEntityData(List<?> dataList) throws IllegalAccessException {

		List<List<String>> strList = new ArrayList<>();
		List<String> valueList;
		Field[] fields;

		for (Object data : dataList) {
			valueList = new ArrayList<>();
			// 全てのフィールドを取得する
			fields = data.getClass().getDeclaredFields();

			for (Field field : fields) {

				// @JsonIgnoreアノテーションがある場合、処理しない
				if (field.isAnnotationPresent(JsonIgnore.class)) {
					continue;
				}

				// 強制的にフィールドをアクセスする
				field.setAccessible(true);
				// フィールドの値を取得する
				valueList.add(Optional.ofNullable(field.get(data)).orElse(BLANK_MARK).toString());
			}
			strList.add(valueList);
		}

		return strList;
	}

	//データ型変換
	private String truncateToZeroIfZero(String value) {
		if (value == null || value.equals("0.00")) {
			return "0";
		}
		return value;
	}

	//データ型変換
	private String formatWithComma(String input) {
		DecimalFormat decimalFormat = new DecimalFormat("#,##0", new DecimalFormatSymbols(locale));
		if (StringUtils.isEmpty(input)) {
			return input;
		}
		try {
			double doubleValue = Double.parseDouble(input);
			return decimalFormat.format(doubleValue);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return input;
		}
	}
}
