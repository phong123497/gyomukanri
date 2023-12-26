//================================================================================
// ID: PMSK051
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理システム
// ProgramName: 仕入・訂正入力
// ClassName: Pmsk051Service
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/09/26 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.service.msk;

import java.io.File;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import coop.kyushu.common.util.BeanUtil;
import coop.kyushu.common.util.DateUtil;
import coop.kyushu.common.util.MessageUtil;
import coop.kyushu.common.util.TantoshaInfoUtil;
import coop.kyushu.gyomu_kanri.entity.msk.Pmsk051Entity;
import coop.kyushu.gyomu_kanri.entity.msk.Pmsk051EnttityCsv;
import coop.kyushu.gyomu_kanri.entity.msk.Pmsk051ListItem;
import coop.kyushu.gyomu_kanri.form.msk.Pmsk051Form;
import coop.kyushu.gyomu_kanri.mapper.msk.Pmsk051Mapper;

@Service
public class Pmsk051Service {

	@Autowired
	private Pmsk051Mapper pmsk051Mapper;

	/** 共通_利用者情報 */
	@Autowired
	private TantoshaInfoUtil tantoshaInfoUtil;

	@Autowired
	private MessageUtil messageUtil;

	/**
	 * 取引先マスタCSV取込
	 * 
	 * @param form 入力内容
	 * @return 取込結果リスト
	 * @throws Exception
	 */
	public Pmsk051Entity readcsv(Pmsk051Form form) throws Exception {
		// 件数
		int kensu = 0;
		// 登録件数
		int toroken = 0;
		int temToro = 0;
		// 取込結果リスト
		List<Pmsk051ListItem> listError = new ArrayList<>();
		Pmsk051Entity readCsvResult = new Pmsk051Entity();

		// CSVのMultipartFileをList<List<String>>に変更する
		MultipartFile csvMultipartFile = form.getCsvFile();
		List<List<String>> csvTwoDimensionalList = this.multipartFileToList(csvMultipartFile);
		long csvFileSize = csvMultipartFile.getSize();
		String filename = csvMultipartFile.getOriginalFilename();
		// CSVデータリストの長さが0の場合
		if (csvFileSize == 0) {
			listError.add(showError(filename, "-", "empty", "CMS0035E", filename));
			readCsvResult.setCnt(kensu);
			readCsvResult.setResultList(listError);
		} else {
			DateTimeFormatter ymdFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
			for (int i = 1; i < csvTwoDimensionalList.size(); i++) {
				// CSV列
				List<String> csvRow = csvTwoDimensionalList.get(i);

				Pmsk051EnttityCsv csvRowData = new Pmsk051EnttityCsv(csvRow);
				String atRow = String.valueOf(i + 1);
				if (csvRow.size() >= 45) {
					// 桁数以下であるかどうかチェック
					this.checkValidLength(listError, csvRow, filename, atRow);
					// ID チェック存在するかどうか
					this.checkID(listError, csvRow, filename, atRow);

					// 物流ＥＤＩ伝票発行区分
					if (csvRowData.getEdi_denhatkb() != "0" && csvRowData.getEdi_denhatkb() != "1") {
						listError.add(showError(filename, atRow, "other", "CMS0015E", "物流ＥＤＩ伝票発行区分",
								"0", "1"));
					}
					// 物流ＥＤＩ仕入計上送信区分
					if (csvRowData.getEdi_ssosinkb() != "0" && csvRowData.getEdi_ssosinkb() != "1") {
						listError.add(showError(filename, atRow, "other", "CMS0015E",
								"物流ＥＤＩ仕入計上送信区分", "0", "1"));
					}
					// 営業倉庫区分と出庫指示作成区分
					if (csvRowData.getEsokkb() == "00" && csvRowData.getSksijikb() == "1"
							|| csvRowData.getEsokkb() != "00" && csvRowData.getSksijikb() == "0") {
						listError.add(showError(filename, atRow, "other", "MST0019E",
								"営業倉庫、出庫指示作成関連エラーです。"));
					}
					// 6 取引先コードと発注フォーマット（冷蔵＋ＭＤ）
					if (csvRowData.getToricd() != "8"
							&& (csvRowData.getWebedi_hatufmkb() == "6" || csvRowData.getWebedi_hatufmkb() == "7" ||
									csvRowData.getWebedi_hatufmkb() == "8" || csvRowData.getWebedi_hatufmkb() == "9")) {
						listError.add(showError(filename, atRow, "other", "CMS0049E", "取引先コード",
								"8'以外", "発注フォーマット（冷蔵＋ＭＤ）",
								"'6'、'7'、'8'、'9'以外'"));
					}
					// 7 送信先コード（冷蔵＋ＭＤ）と発注フォーマット（冷蔵＋ＭＤ)
					if (csvRowData.getWebedi_sosncd() == "6" && csvRowData.getWebedi_hatufmkb() == "0") {
						listError
								.add(showError(filename, atRow, "other", "CMS0049E", "発注フォーマット（冷蔵＋ＭＤ）", "6",
										"送信先コード（冷蔵＋ＭＤ）", "0'以外"));
					}

					// 8 センタフィー（冷凍）についてチェックする。
					if (Integer.parseInt(csvRowData.getCentfee_rt()) >= 100) {
						listError.add(showError(filename, atRow, "other", "CMS0048E", "センタフィー（冷凍）",
								"99以下"));
					}
					// 9 センタフィー（冷凍）についてチェックする。
					if (Integer.parseInt(csvRowData.getCentfee_rz()) >= 100) {
						listError.add(showError(filename, atRow, "other", "CMS0048E", "センタフィー（冷蔵）",
								"99以下"));
					}

					// 10 センタフィー（ドライ )についてチェックする。
					if (Integer.parseInt(csvRowData.getCentfee_dr()) >= 100) {
						listError.add(showError(filename, atRow, "other", "CMS0048E", "センタフィー（ドライ）",
								"99以下"));
					}
					// 11 取引先コードと仕入会員識別 「入会員識別なかった」
					// 12発注フォーマット（冷蔵＋ＭＤ）とEDI-GLN
					if (csvRowData.getWebedi_hatufmkb() == "4" && csvRowData.getEdi_gln() == "") {
						listError.add(showError(filename, atRow, "other", "CMS0049E",
								"発注フォーマット（冷蔵＋ＭＤ）", "4", "EDI-GLN", "値"));
					}
					// 13 EDI開始日とEDI終了日
					LocalDate ediStr = LocalDate.parse(csvRowData.getEdi_strdate(),
							ymdFormatter);
					LocalDate ediEnd = LocalDate.parse(csvRowData.getEdi_enddate(),
							ymdFormatter);
					if (ediEnd.isBefore(ediStr)) {
						listError.add(showError(filename, atRow, "other", "MST0022E",
								"開始日、終了日関連エラーです。"));
					}
					// 14 取引先コードと発注フォーマット（冷蔵＋ＭＤ）予約
					if (csvRowData.getToricd() != "8"
							&& (csvRowData.getWebedi_hatufmkb_y() == "6" ||
									csvRowData.getWebedi_hatufmkb_y() == "7" ||
									csvRowData.getWebedi_hatufmkb_y() == "8"
									|| csvRowData.getWebedi_hatufmkb_y() == "9")) {
						listError.add(showError(filename, atRow, "other", "CMS0049E", "取引先コード",
								"8'以外", "発注フォーマット（冷蔵＋ＭＤ）予約",
								"'6'、'7'、'8'、'9'以外'"));
					}
					// 15 送信先コード（冷蔵＋ＭＤ）予約と発注フォーマット（冷蔵＋ＭＤ）予約
					if (csvRowData.getWebedi_sosncd() != "0" && csvRowData.getWebedi_hatufmkb_y() == "6") {
						listError.add(showError(filename, atRow, "other", "CMS0049E",
								"発注フォーマット（冷蔵＋ＭＤ）予約", "6", "送信先コード（冷蔵＋ＭＤ）予約",
								"'0'以外'"));
					}
					// 16 発注フォーマット_予約とEDI-GLNに
					if (csvRowData.getWebedi_hatufmkb_y() == "4" && csvRowData.getEdi_gln() == "") {
						listError.add(showError(filename, atRow, "other", "CMS0049E",
								"発注フォーマット（冷蔵＋ＭＤ）予約", "4", "EDI-GLN",
								"値"));
					}
					// 17 データが存在して、かつ削除フラグが"0"
					Map<String, Object> params = new HashMap<>();
					params.put("toricd", csvRowData.getToricd());
					String getData = pmsk051Mapper.checkTorihikisakiByPmsk051(params);
					if (!StringUtils.hasLength(getData)) {
						temToro = this.insertByPmsk051(csvRowData, listError, filename, atRow, toroken);
					} else {
						if ("0".equals(getData)) {
							temToro = this.updateByPmsk051(csvRowData, listError, filename, atRow, toroken);
						} else if ("1".equals(getData)) {
							temToro = this.reinsertByPmsk051(csvRowData, listError, filename, atRow, toroken);
						}
					}
					readCsvResult.setCnt(temToro);
					readCsvResult.setResultList(listError);
				}
			}
		}
		return readCsvResult;

	}

	/**
	 * 取引先マスタ追加
	 * 
	 * @param Pmsk051EnttityCsv,List<Pmsk051ListItem>,fileName,atRow,toroken
	 * @return DBへの登録データ
	 */
	public int insertByPmsk051(Pmsk051EnttityCsv csvRow, List<Pmsk051ListItem> listError, String fileName,
			String atRow, int toroken) {
		try {
			Map<String, Object> params = BeanUtil.toMap(csvRow);
			// システム日時取得する
			Timestamp systemDate = DateUtil.getCurrentTimestamp();
			Date date = DateUtil.getCurrentDate();
			BigDecimal toroKoshin = DateUtil.toBigDecimal(date);
			params.put("toroKoshin", toroKoshin); // 登録日 ,更新日
			params.put("userId", tantoshaInfoUtil.getTansCd());
			params.put("sakuj", systemDate); // 作成日時
			pmsk051Mapper.insertByPmsk051(params);
			toroken++;
		} catch (Exception e) {
			listError.add(showError(fileName, atRow, "other", "CMS0035E", "SQLエラーメッセージ"));
		}
		return toroken;
	}

	/**
	 * 取引先マスタ更新
	 * 
	 * @param Pmsk051EnttityCsv,List<Pmsk051ListItem>,fileName,atRow,toroken
	 * @return DBへの登録データ
	 */
	private int updateByPmsk051(Pmsk051EnttityCsv csvRow, List<Pmsk051ListItem> listError, String fileName,
			String atRow, int toroken) {
		try {
			Map<String, Object> params = BeanUtil.toMap(csvRow);
			// システム日時取得する
			Timestamp systemDate = DateUtil.getCurrentTimestamp();
			Date date = DateUtil.getCurrentDate();
			BigDecimal toroKoshin = DateUtil.toBigDecimal(date);

			csvRow.setWebedi_hatukb(csvRow.getWebedi_hatukb_y());
			csvRow.setWebedi_hatufmkb(csvRow.getWebedi_hatufmkb_y());
			csvRow.setWebedi_sosncd(csvRow.getWebedi_sosncd_y());
			csvRow.setWebedi_syukhflg(csvRow.getWebedi_syukhflg_y());
			params.put("toroKoshin", toroKoshin); // 登録日 ,更新日
			params.put("userId", tantoshaInfoUtil.getTansCd());
			params.put("sakuj", systemDate); // 作成日時
			pmsk051Mapper.updateByPmsk051(params);
			toroken++;
		} catch (Exception e) {
			listError.add(showError(fileName, atRow, "other", "CMS0035E", "SQLエラーメッセージ"));
		}
		return toroken;
	}

	/**
	 * 取引先マスタ追加（更新）
	 * 
	 * @param Pmsk051EnttityCsv,List<Pmsk051ListItem>,fileName,atRow,toroken
	 * @return DBへの登録データ
	 */
	private int reinsertByPmsk051(Pmsk051EnttityCsv csvRow, List<Pmsk051ListItem> listError, String fileName,
			String atRow, int toroken) {

		try {
			Map<String, Object> params = BeanUtil.toMap(csvRow);
			// システム日時取得する
			Timestamp systemDate = DateUtil.getCurrentTimestamp();
			Date date = DateUtil.getCurrentDate();
			BigDecimal toroKoshin = DateUtil.toBigDecimal(date);

			LocalDate currentDate = LocalDate.now();
			DateTimeFormatter ymdFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
			LocalDate yoyakuDate = LocalDate.parse(csvRow.getYoyak_tekiymd(),
			ymdFormatter);
			// CSVデータの予約適用開始日が運用日(システム日付)より後でない場合
			if (yoyakuDate.isBefore(currentDate)) {
			listError.add(showError(fileName, atRow, "other", "MST0023E",
			yoyakuDate.toString()));
			}
			// 予約項目の相関チェック
			if (csvRow.getWebedi_hatukb().equals(csvRow.getWebedi_hatukb_y())
			&& csvRow.getWebedi_hatufmkb().equals(csvRow.getWebedi_hatufmkb_y())
			&& csvRow.getWebedi_sosncd().equals(csvRow.getWebedi_sosncd_y())
			&& csvRow.getWebedi_syukhflg().equals(csvRow.getWebedi_syukhflg_y())) {
			listError.add(showError(fileName, atRow, "other", "MST0024E",
			"現在の設定値と全て同じ値は設定できません。"));
			}
			params.put("toroKoshin", toroKoshin); // 登録日 ,更新日
			params.put("userId", tantoshaInfoUtil.getTansCd());
			params.put("sakuj", systemDate); // 作成日時
			pmsk051Mapper.reinsertByPmsk051(params);
			toroken++;
		} catch (Exception e) {
			listError.add(showError(fileName, atRow, "other", "CMS0035E", "SQLエラーメッセージ"));
		}
		return toroken;
	}

	/**
	 * IDを存在するかどうかチェック
	 * 
	 * @throws Exception
	 * @param List<Pmsk051ListItem>,csvRow,fileName,errorAtRow
	 */
	private void checkID(List<Pmsk051ListItem> listItemsError, List<String> csvRow, String fileName,
			String errorAtRow) throws IllegalAccessException {
		Pmsk051EnttityCsv checkId = new Pmsk051EnttityCsv(csvRow);
		String errorNum = "CMS0020E";
		String mode = "checkId";
		List<String> codoAtaiList = checkId.getvalue(csvRow);
		Map<String, Object> params = new HashMap<>();
		List<String> kubunKoteiList = Arrays.asList("67", "MM", "75", "76", "E2", "77", "75", "76", "E2", "77", "G1",
				"A4", "G1", "G2", "G9", "98");
		List<String> codoMeiList = Arrays.asList(" 店別明細区分", " 現金仕入区分", " 発注区分（冷蔵＋ＭＤ）", " 発注フォーマット区分（冷蔵＋ＭＤ）",
				" 送信先コード（冷蔵＋ＭＤ）", " 出荷報告対象フラグ（冷蔵＋ＭＤ）", " 発注区分（冷蔵＋ＭＤ）予約", " 発注フォーマット区分（冷蔵＋ＭＤ）予約",
				" 送信先コード（冷蔵＋ＭＤ）予約", " 出荷報告対象フラグ（冷蔵＋ＭＤ）予約", " 仕入データ自動確定区分", " 請求対象会員生協識別区分", " 営業倉庫区分",
				" 出庫指示作成区分", " 最終発注単位区分", " 取引停止区分");
		for (int i = 0; i < 16; i++) {
			String codoAtai = codoAtaiList.get(i);
			String kubunKotei = kubunKoteiList.get(i);
			String codoMei = codoMeiList.get(i);
			params.put("meicd", codoAtai);
			params.put("reckb", kubunKotei);
			Integer id = pmsk051Mapper.checkMeishoByPmsk051(params);
			if (id == null) {
				listItemsError.add(showError(fileName, errorAtRow, mode, errorNum, codoMei));
			}
		}
	}

	/**
	 * IDを存在するかどうかチェック
	 * @param fileName,errorAtRow,mode,...error
	 * @return Pmsk051ListItem
	 */
	private Pmsk051ListItem showError(String fileName, String errorAtRow, String mode, String... error) {
		String errorNum = error[0];
		String itemName = error[1];
		// パラメータによりケースを分割
		Pmsk051ListItem pmsk051ListItem = new Pmsk051ListItem();
		switch (mode) {
		case "empty":
			pmsk051ListItem.setErrContents(messageUtil.getMessage("CMS0035E", fileName));
			break;
		case "checkId":
			String errorMsgId = messageUtil.getMessage(errorNum, itemName, "名称");
			pmsk051ListItem.setErrContents(errorMsgId);
			break;
		case "checkLength":
			String errorMsgLength = messageUtil.getMessage(errorNum, itemName, error[2]);
			pmsk051ListItem.setErrContents(errorMsgLength);
			break;
		case "other":
			if (error.length == 2) {
				String errorMsg2 = messageUtil.getMessage(errorNum, itemName);
				pmsk051ListItem.setErrContents(errorMsg2);
			} else if (error.length == 3) {
				String errorMsg3 = messageUtil.getMessage(errorNum, itemName, error[2]);
				pmsk051ListItem.setErrContents(errorMsg3);
			} else if (error.length == 4) {
				String errorMsg4 = messageUtil.getMessage(errorNum, itemName, error[2], error[3]);
				pmsk051ListItem.setErrContents(errorMsg4);
			} else if (error.length == 5) {
				String errorMsg5 = messageUtil.getMessage(errorNum, itemName, error[2], error[3], error[4]);
				pmsk051ListItem.setErrContents(errorMsg5);
			}
			break;
		}
		pmsk051ListItem.setFileNm(fileName);
		pmsk051ListItem.setTorikmReslt("エラー");
		pmsk051ListItem.setGyoNo(errorAtRow);
		return pmsk051ListItem;

	}

	/**
	 * fliedd ごとの長さをチェック
	 * 
	 * @param listItemError,csvRow,fileName,errorAtRow
	 * @throws Exception
	 */
	private void checkValidLength(List<Pmsk051ListItem> listItemError, List<String> csvRow, String fileName,
			String errorAtRow) throws IllegalAccessException {
		Pmsk051EnttityCsv pmsk051 = new Pmsk051EnttityCsv();
		String[] arrNameItems = { "取引先コード", " 取引先名称（漢字）", " 取引先名称（カナ）", " 取引先略称（漢字）", " 取引先略称（カナ）",
				" 郵便番号", " 郵便ＮＯ", " 住所１（市・郡）", " 住所２（町・番地）", " 住所３（方書）", " 電話番号", " 電話ＮＯ", " ＦＡＸ番号",
				" ＦＡＸＮＯ", " 取引先種別", " 支払締日", " 支払月数", " 支払日（日）", " 請求締日", " 請求月数", " 請求日（日）", " センタフィー（冷凍）",
				" センタフィー（冷蔵）", " センタフィー（ドライ）", " 物流ＥＤＩ伝票発行区分", " 物流ＥＤＩ仕入計上送信区分", " 物流ＥＤＩーＧＬＮ",
				" 物流ＥＤＩ開始日", " 物流ＥＤＩ終了日", " 店別明細区分", " 現金仕入区分", " 発注区分（冷蔵＋ＭＤ）", " 発注フォーマット区分（冷蔵＋ＭＤ）",
				" 送信先コード（冷蔵＋ＭＤ）", " 出荷報告対象フラグ（冷蔵＋ＭＤ）", " 予約適用開始日", " 発注区分（冷蔵＋ＭＤ）予約", " 発注フォーマット区分（冷蔵＋ＭＤ）予約",
				" 送信先コード（冷蔵＋ＭＤ）予約", " 出荷報告対象フラグ（冷蔵＋ＭＤ）予約", " 仕入データ自動確定区分", " 請求対象会員生協識別区分", " 営業倉庫区分", " 出庫指示作成区分",
				" 最終発注単位区分",
				" 取引停止区分" };
		Integer[] arrLengthItems = { 6, 20, 30, 10, 20, 10, 10, 20, 20, 20, 20, 20, 20, 20, 1, 2, 2, 2, 2, 2, 2, 3, 3,
				3, 1,
				1, 13, 10, 10, 1, 1,
				1, 1, 4, 1, 10, 1, 1, 4, 1, 1, 1, 2, 1, 1, 1 };

		String errorNum = "CMS0047E";
		List<Integer> listFieldsLength = pmsk051.getValueFieldsLength();

		for (int i = 0; i <= 45; i++) {
			if (listFieldsLength.get(i) >= arrLengthItems[i]) {
				String itemName = arrNameItems[i];
				String itemLength = arrLengthItems[i].toString();
				listItemError.add(showError(fileName, errorAtRow, errorNum, itemName, itemLength));
			}
		}

	}

	/**
	 * MultipartFileデータからList<List<String>>データへの変換
	 * 
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

}
