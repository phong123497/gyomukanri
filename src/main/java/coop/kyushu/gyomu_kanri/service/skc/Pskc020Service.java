//================================================================================
// ID: PSKC020
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pskc020Service
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/11 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.service.skc;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import coop.kyushu.common.entity.ServerErrorEntity;
import coop.kyushu.common.exception.ValidationException;
import coop.kyushu.common.util.BeanUtil;
import coop.kyushu.common.util.CsvUtil;
import coop.kyushu.common.util.DateUtil;
import coop.kyushu.common.util.FileDownloadUtil;
import coop.kyushu.common.util.MessageUtil;
import coop.kyushu.common.util.TantoshaInfoUtil;
import coop.kyushu.gyomu_kanri.entity.skc.Pskc020Entity;
import coop.kyushu.gyomu_kanri.entity.skc.Pskc020ListItem;
import coop.kyushu.gyomu_kanri.form.skc.Pskc020Form;
import coop.kyushu.gyomu_kanri.mapper.skc.Pskc020Mapper;

/**
 * <pre>
 * 【惣菜加工センター】製造計画数取込・出力サービス
 * </pre>
 */
@Service
public class Pskc020Service {

	/* 製造計画数取込・出力マッパー */
	@Autowired
	private Pskc020Mapper pskc020Mapper;

	/* ファイルをダウンロードする共通部品 */
	@Autowired
	private FileDownloadUtil fileDownloadUtil;

	/* 担当者情報 */
	@Autowired
	private TantoshaInfoUtil tantoshaInfoUtil;

	/* メッセージ管理 */
	@Autowired
	private MessageUtil messageUtil;

	/* 一時的にCSVファイルの保存パス */
	@Value("${GYOMU_REPORT_TEMP_PATH}")
	private String tempCsvFilePath;

	/* 帳票ID */
	private final static String CHOHYO_ID = "PSKC020";

	/**
	 * 製造計画数取込・出力の出力（期間指定）処理
	 * @param form 入力内容
	 * @return CSVファイル
	 */
	public void outputCsvForKknSitei(Pskc020Form from) {
		this.output(from);
	}

	/**
	 * 製造計画数取込・出力の出力（週指定）処理
	 * @param form 入力内容
	 * @return CSVファイル
	 */
	public void outputCsvForWeekSitei(Pskc020Form from) {
		this.output(from);
	}

	/**
	 * 製造計画数取込・出力の出力処理
	 * @param form 入力内容
	 * @return CSVファイル
	 */
	public void output(Pskc020Form form) {

		// ＣＳＶ出力データリスト情報を取得する。
		Map<String, Object> csvParam = BeanUtil.toMap(form);
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");

		// 処理区分は出力（週指定）の場合
		if ("2".equals(form.getProcsKbn())) {

			// 納品日Ｆｒｏｍ+6日
			LocalDate date = LocalDate.parse(form.getDelvDayFrom(), dateFormatter);
			String delvDay = date.plusDays(6).format(dateFormatter);

			csvParam.replace("delvDayTo", delvDay);
		}

		// 出力CSVデータ取得
		List<Pskc020Entity> csvList = pskc020Mapper.getCsvDateByPskc020(csvParam);

		// 出力CSVデータを取得できなかった場合
		if (ObjectUtils.isEmpty(csvList)) {

			throw new ValidationException(new ServerErrorEntity("SKC0002E"));
		}

		// 変数を宣言する
		List<String> titalTop = new ArrayList<>();
		List<String> titalBottom = new ArrayList<>();
		List<String> meisaiList = new ArrayList<>();
		List<List<String>> csvMeisaiList = new ArrayList<>();
		String oldHinmokuCd = "";
		String oldDelvDay = "";
		Boolean headerFlg = true;

		titalTop.addAll(List.of("", "", "", "", "", "", "合計受注数"));
		titalBottom.addAll(List.of("品目コード", "品名", "納品日", "便", "売単価", "納入単価", ""));

		// ＣＳV出力データを編集する
		for (int i = 0; i < csvList.size(); i++) {
			if (!oldHinmokuCd.equals(csvList.get(i).getHinmokuCd())
					|| !oldDelvDay.equals(csvList.get(i).getDelvDay())) {

				// ヘッダー編集フラグの設定
				if (headerFlg && !ObjectUtils.isEmpty(oldHinmokuCd) && !ObjectUtils.isEmpty(oldDelvDay)) {
					headerFlg = false;
				}
				if (!headerFlg) {
					csvMeisaiList.add(meisaiList);
				}

				// 明細リストを編集する
				meisaiList = new ArrayList<>();
				meisaiList.add(csvList.get(i).getHinmokuCd());
				meisaiList.add(csvList.get(i).getHinNm());
				meisaiList.add(csvList.get(i).getDelvDay());
				meisaiList.add("1");
				meisaiList.add("0");
				meisaiList.add("0");
				meisaiList.add(csvList.get(i).getGkeiJuchuSu());

				// グループキーを更新する
				oldHinmokuCd = csvList.get(i).getHinmokuCd();
				oldDelvDay = csvList.get(i).getDelvDay();
			}

			// 明細リストの受注数を設定する
			if (csvList.get(i).getJuchuSu() == null) {
				csvList.get(i).setJuchuSu("");
			}
			meisaiList.add(csvList.get(i).getJuchuSu());

			// ヘッダー部編集する
			if (headerFlg) {
				titalTop.add(csvList.get(i).getShopNm());
				titalBottom.add(csvList.get(i).getShopCd());
			}
		}
		csvMeisaiList.add(meisaiList);

		// CSV出力データリスを編集する
		List<List<String>> csvDataList = new ArrayList<>();
		csvDataList.add(titalTop);
		csvDataList.add(titalBottom);
		csvDataList.addAll(csvMeisaiList);

		// CSVファイルを作成する
		String loginUserId = StringUtils.trimAllWhitespace(tantoshaInfoUtil.getTansCd());
		String serverFilePath = CsvUtil.createCsv(tempCsvFilePath, loginUserId, CHOHYO_ID, csvDataList,
				new String("SJIS"));

		// システム時間を取得する
		String systemDateStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

		// CSV名を作成する
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("juchu_");
		stringBuffer.append(systemDateStr);
		stringBuffer.append(".csv");

		// CSVファイルをダウンロードする
		fileDownloadUtil.download(serverFilePath, stringBuffer.toString());
	}

	/**
	 * 製造計画数取込・出力の取込（計画数）処理
	 * @param form 入力内容
	 * @return 取込結果
	 * @throws Exception 
	 */
	@Transactional
	public List<Pskc020ListItem> getKbnTorikmPlanSu(Pskc020Form form) throws Exception {

		// ログインユーザIDを取得する
		String loginUserId = StringUtils.trimAllWhitespace(tantoshaInfoUtil.getTansCd());

		// 惣菜製造計画ワークを削除する
		pskc020Mapper.deleteSozaiSeizoPlanWkByPskc020(loginUserId);

		//  CSVファイルのデータを取得する
		MultipartFile csvMultipartFile = form.getCsvFile();
		List<List<String>> csvGetDataList = this.multipartFileToList(csvMultipartFile);

		if (ObjectUtils.isEmpty(csvGetDataList) || csvGetDataList.size() < 3) {
			throw new ValidationException(new ServerErrorEntity("SKC0001E", List.of("取込内容")));
		}

		// 変数を宣言する
		List<Pskc020ListItem> readCsvResult = new ArrayList<>();

		// 現在のファイルのファイル名を取得する
		String filename = csvMultipartFile.getOriginalFilename();

		// 店コードを店舗存在チェックする
		List<String> shopCdList = new ArrayList<>(csvGetDataList.get(1).subList(7, csvGetDataList.get(1).size()));
		for (int i = 0; i < shopCdList.size(); i++) {
			int shopCount = pskc020Mapper.getTempoInfoKensuByPskc020(shopCdList.get(i));

			if (shopCount == 0) {
				String messageData = messageUtil.getMessage("SKC0004E",
						List.of("マスタ", "存在しない店舗(" + shopCdList.get(i) + ")"));

				readCsvResult.add(this.editReadCsvResult(filename, "エラー", Integer.toString(i + 1), messageData));
			}

		}

		// 明細データをチェックする、チェックなしのデータを惣菜製造計画ワークテーブルに登録する
		Timestamp systemDateTime = DateUtil.getCurrentTimestamp();
		// 時間のフォーマットに変換可能かどうか判断する
		DateTimeFormatter ymdSlashFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		DateTimeFormatter ymdFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");

		for (int m = 2; m < csvGetDataList.size(); m++) {
			// 納品日と品目コード取得する
			String delvDayParam = csvGetDataList.get(m).get(2); // 納品日
			String hinmokuCdParam = csvGetDataList.get(m).get(0); // 品目コード

			// 品目コードを商品存在チェックする
			int hinmokuCount = pskc020Mapper.getShinInfoKensuByPskc020(hinmokuCdParam);

			if (hinmokuCount == 0) {
				String messageData = messageUtil.getMessage("SKC0004E",
						List.of("マスタ", "存在しない商品"));

				readCsvResult.add(
						this.editReadCsvResult(filename, "エラー", Integer.toString(m + 1), messageData + hinmokuCdParam));
			}

			// 納品日を書式チェック
			if (this.isValidDateFormat(delvDayParam)) {
				String messageData = messageUtil.getMessage("SKC0004E",
						List.of("納品日の書式", "問題"));

				readCsvResult.add(this.editReadCsvResult(filename, "エラー", Integer.toString(m + 1), messageData));
			} else {
				// 納品日を範囲チェックする
				LocalDate formDelvFrom = LocalDate.parse(form.getDelvDayFrom(), ymdFormatter);
				LocalDate formDelvTo = LocalDate.parse(form.getDelvDayTo(), ymdFormatter);
				LocalDate checkDelvDay = LocalDate.parse(delvDayParam, ymdSlashFormatter);
				if (checkDelvDay.isBefore(formDelvFrom) || formDelvTo.isBefore(checkDelvDay)) {

					String messageData = messageUtil.getMessage("SKC0004E",
							List.of("納品日", "期間外の日付"));

					readCsvResult.add(this.editReadCsvResult(filename, "エラー", Integer.toString(m + 1), messageData));
				}
			}

			// 取込データをSZWK101_惣菜製造計画ワークテーブルに登録する　
			List<String> mesaiList = new ArrayList<>(csvGetDataList.get(m).subList(7, csvGetDataList.get(m).size()));
			String meisaiDelvDay = "";
			try {
				meisaiDelvDay = LocalDate.parse(delvDayParam, ymdSlashFormatter).format(ymdFormatter);
			} catch (Exception e) {
				meisaiDelvDay = delvDayParam;
			}

			for (int j = 0; j < mesaiList.size(); j++) {

				Map<String, Object> meisaiParam = new HashMap<>();

				meisaiParam.put("uuid", loginUserId); // セッションＩＤ
				meisaiParam.put("cqProjNo", hinmokuCdParam); // CQ商品コード
				meisaiParam.put("delvDay", meisaiDelvDay); // 納品日
				meisaiParam.put("shopCd", shopCdList.get(j)); // 店コード

				if (ObjectUtils.isEmpty(mesaiList.get(j))) {
					mesaiList.set(j, null);
				}
				meisaiParam.put("nhinsu", mesaiList.get(j)); // 数量
				meisaiParam.put("userId", loginUserId); // ログインユーザID
				meisaiParam.put("prgrm", "PSKC020"); // プログラム
				meisaiParam.put("nchj", systemDateTime); // システム日時

				try {
					// 惣菜製造計画ワーク登録
					pskc020Mapper.insertSozaiSeizoPlanWkByPskc020(meisaiParam);
				} catch (Exception e) {
					// 登録中、エラーが発生した場合、取込結果を変数.取込結果一覧リストに追加して処理を継続する。
					String message = e.getMessage();
					List<String> errorMsgArray = Arrays.asList(message.split("\n"));
					String messageData = errorMsgArray.get(1).replaceAll("###", "");

					readCsvResult.add(
							this.editReadCsvResult(filename, "エラー", Integer.toString(m + 1), messageData));
				}
			}

		}

		// 変数.取込結果一覧>0件の場合、処理終了
		if (readCsvResult.size() > 0) {

			//  惣菜製造計画ワークを削除する
			pskc020Mapper.deleteSozaiSeizoPlanWkByPskc020(loginUserId);

			return readCsvResult;
		}

		// 惣菜製造計画データを登録/更新する
		Map<String, Object> kekakuDataParam = new HashMap<>();
		kekakuDataParam.put("uuid", loginUserId);
		kekakuDataParam.put("userId", loginUserId);
		kekakuDataParam.put("prgrm", "PSKC020");
		kekakuDataParam.put("nchj", systemDateTime);

		pskc020Mapper.mergeToSozaiSeizoPlanDateByPskc020(kekakuDataParam);

		//  納品日Fromの月曜日と納品日Toの日曜日を取得する
		LocalDate delvDayFromGetsu = LocalDate.parse(form.getDelvDayFrom(), ymdFormatter)
				.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));

		LocalDate delvDayToNiti = LocalDate.parse(form.getDelvDayTo(), ymdFormatter)
				.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

		String delvDayGetsu = delvDayFromGetsu.format(ymdFormatter);
		String delvDayNiti = delvDayToNiti.format(ymdFormatter);

		// 惣菜製造計画データを削除する。
		Map<String, Object> kekakuDeleteParam = new HashMap<>();
		kekakuDeleteParam.put("delvDayGetsu", delvDayGetsu);
		kekakuDeleteParam.put("delvDayNiti", delvDayNiti);

		pskc020Mapper.deleteSozaiSeizoPlanDateByPskc020(kekakuDeleteParam);

		//  今回取込対象の商品を惣菜対象商品、惣菜対象店舗に登録する。
		LocalDate delvDay = LocalDate.parse(form.getDelvDayFrom(), ymdFormatter);

		// 納品日FROMの月曜日～納品日Toの日曜日の日数
		long daysBetween = ChronoUnit.DAYS.between(delvDayFromGetsu, delvDayToNiti) + 1;

		// 惣菜対象商品登録する。
		Map<String, Object> insertShohinParam = new HashMap<>();
		for (int n = 0; n < (int) daysBetween; n++) {
			insertShohinParam.put("delvDayFrom", this.getDaysWeek(delvDay, DayOfWeek.MONDAY));
			insertShohinParam.put("delvDayTo", this.getDaysWeek(delvDay, DayOfWeek.SUNDAY));
			insertShohinParam.put("uuid", loginUserId);
			insertShohinParam.put("delvDay", delvDay.format(ymdFormatter));
			insertShohinParam.put("userId", loginUserId);
			insertShohinParam.put("prgrm", "PSKC020");
			insertShohinParam.put("nchj", systemDateTime);

			// 惣菜対象商品登録
			pskc020Mapper.insertSozaiTaishoShohinByPskc020(insertShohinParam);

			// 納品日を１加算する
			delvDay = delvDay.plusDays(1);
		}

		// 惣菜対象店舗を登録する。
		Map<String, Object> insertTenpoParam = new HashMap<>();
		insertTenpoParam.put("delvDayFrom", delvDayGetsu);
		insertTenpoParam.put("delvDayTo", delvDayNiti);
		insertTenpoParam.put("uuid", loginUserId);
		insertTenpoParam.put("userId", loginUserId);
		insertTenpoParam.put("prgrm", "PSKC020");
		insertTenpoParam.put("nchj", systemDateTime);

		pskc020Mapper.insertSozaiTaishoTenPoByPskc020(insertTenpoParam);

		// 取込結果を変数.取込結果一覧リストに追加して処理を継続する。
		readCsvResult.add(this.editReadCsvResult(filename,
				"取込正常終了（取込件数：" + Integer.toString(csvGetDataList.size() - 2) + "件）", "", ""));

		return readCsvResult;
	}

	/**
	 * 製造計画数取込・出力の取込（商品改廃）処理
	 * @param form 入力内容
	 * @return 取込結果
	 * @throws Exception 
	 */
	@Transactional
	public List<Pskc020ListItem> getTorikmShinKaihai(Pskc020Form form) throws Exception {
		// ログインユーザIDを取得する
		String loginUserId = StringUtils.trimAllWhitespace(tantoshaInfoUtil.getTansCd());

		//  惣菜対象商品ワークを削除する
		pskc020Mapper.deleteSozaiTaishoShohinWkByPskc020(loginUserId);

		//  CSVファイルのデータを取得する
		MultipartFile csvMultipartFile = form.getCsvFile();
		List<List<String>> csvGetDataList = this.multipartFileToList(csvMultipartFile);

		if (ObjectUtils.isEmpty(csvGetDataList) || csvGetDataList.size() < 2) {
			throw new ValidationException(new ServerErrorEntity("SKC0001E", List.of("取込内容")));
		}

		// 変数を宣言する
		List<Pskc020ListItem> readCsvResult = new ArrayList<>();
		Timestamp systemDateTime = DateUtil.getCurrentTimestamp();

		// 現在のファイルのファイル名を取得する
		String filename = csvMultipartFile.getOriginalFilename();

		for (int i = 1; i < csvGetDataList.size(); i++) {
			// 明細リスト取得
			List<String> meisaiList = csvGetDataList.get(i);

			// 品目コードを商品存在チェックする
			int shohinjohoCount = pskc020Mapper.getShinInfoKensuByPskc020(meisaiList.get(0));

			// データが取得できなかった場合
			if (shohinjohoCount == 0) {
				String messageData = messageUtil.getMessage("SKC0004E",
						List.of("マスタ", "存在しない商品"));
				readCsvResult.add(this.editReadCsvResult(filename, "エラー", Integer.toString(i + 1), messageData));
			}

			// 取込データをSZWK102_惣菜対象商品ワークテーブルに登録する　
			Map<String, Object> insertShohinParam = new HashMap<>();
			insertShohinParam.put("uuid", loginUserId);
			insertShohinParam.put("cqProjNo", meisaiList.get(0));
			insertShohinParam.put("torikmFlg", meisaiList.get(2));
			insertShohinParam.put("userId", loginUserId);
			insertShohinParam.put("prgrm", "PSKC020");
			insertShohinParam.put("nchj", systemDateTime);

			try {
				pskc020Mapper.insertSozaiTaishoShohinWkByPskc020(insertShohinParam);

			} catch (Exception e) {
				// 登録中、エラーが発生した場合、取込結果を変数.取込結果一覧リストに追加して処理を継続する
				String message = e.getMessage();
				List<String> errorMsgArray = Arrays.asList(message.split("\n"));
				String messageData = errorMsgArray.get(1).replaceAll("###", "");

				readCsvResult.add(this.editReadCsvResult(filename, "エラー", Integer.toString(i + 1), messageData));

			}
		}

		// 取込結果一覧>0件の場合、処理終了 
		if (readCsvResult.size() > 0) {

			//  惣菜対象商品ワークを削除する
			pskc020Mapper.deleteSozaiTaishoShohinWkByPskc020(loginUserId);

			return readCsvResult;

		}

		DateTimeFormatter ymdFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		LocalDate delvDay = LocalDate.parse(form.getDelvDayFrom(), ymdFormatter);

		//納品日で指定した週の惣菜対象商品を登録/更新する。
		Map<String, Object> insertShohin = new HashMap<>();
		for (int i = 0; i <= 6; i++) {
			insertShohin.put("uuid", loginUserId);
			insertShohin.put("delvDay", delvDay.plusDays(i).format(ymdFormatter));
			insertShohin.put("userId", loginUserId);
			insertShohin.put("prgrm", "PSKC020");
			insertShohin.put("nchj", systemDateTime);

			pskc020Mapper.updateSozaiTaishoShohinByPskc020(insertShohin);

		}

		readCsvResult.add(this.editReadCsvResult(filename,
				"取込正常終了（取込件数：" + Integer.toString(csvGetDataList.size() - 1) + "件）", "", ""));

		return readCsvResult;
	}

	/**
	 * MultipartFileデータからList<List<String>>データへの変換
	 * @param multipartFile CSVファイル
	 * @return List<List<String>>データ
	 * @throws Exception 
	 */
	private List<List<String>> multipartFileToList(MultipartFile multipartFile) throws Exception {

		// CSVオリジナルファイル名を取得する
		String originalFilename = multipartFile.getOriginalFilename();

		// CSVファイル名と拡張子を取得する
		String fileName = StringUtils.stripFilenameExtension(originalFilename);
		String filenameExtension = StringUtils.getFilenameExtension(originalFilename);

		// 一時的ファイルを作成する
		File tempFile = File.createTempFile(fileName, filenameExtension);
		multipartFile.transferTo(tempFile);

		// CSVファイルからデータを取得する
		List<String> csvList = Files.readAllLines(tempFile.toPath(), Charset.forName("Shift_JIS"));

		// 一時的ファイルを削除する
		Files.delete(tempFile.toPath());

		// 変換する
		List<List<String>> csvTwoDimensionalList = new ArrayList<>();
		for (String csvRow : csvList) {

			List<String> csvRowList = new ArrayList<>();

			// コンマ分割
			List<String> csvItemList = Arrays.asList(csvRow.split(","));
			csvItemList.forEach(csvItem -> csvRowList.add(csvItem.replaceAll("\"", "").trim()));

			csvTwoDimensionalList.add(csvRowList);
		}

		return csvTwoDimensionalList;
	}

	/**
	 * 時間がyyyy/MM/ddの形式かどうかをチェックする
	 * @param input date
	 * @return boolean
	 */
	private boolean isValidDateFormat(String date) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			LocalDate.parse(date, formatter);
			return false;
		} catch (Exception e) {
			return true;
		}
	}

	/**
	 * 取込結果一覧データを編集する 
	 * @param filename  ファイル名
	 * @param error 取込結果
	 * @param gyono 行番号
	 * @param contents エラー内容
	 * @return 取込結果
	 */
	private Pskc020ListItem editReadCsvResult(String filename, String result, String gyono, String contents) {
		Pskc020ListItem readCsvResult = new Pskc020ListItem();

		readCsvResult.setFileNm(filename);
		readCsvResult.setTorikmReslt(result);
		readCsvResult.setGyoNo(gyono);
		readCsvResult.setErrContents(contents);

		return readCsvResult;

	}

	/**
	 * 指定された日付が含まれる週の月曜日または日曜日を取得する
	 * @param day 指定された日付
	 * @param week 月曜日または日曜日
	 * @return 日付
	 */
	private String getDaysWeek(LocalDate day, DayOfWeek week) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

		if (week == DayOfWeek.MONDAY) {
			LocalDate mondayDate = day
					.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
			return mondayDate.format(formatter);
		} else {
			LocalDate sundayDate = day
					.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

			return sundayDate.format(formatter);
		}
	}
}
