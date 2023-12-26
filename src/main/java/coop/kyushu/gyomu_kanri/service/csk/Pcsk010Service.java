//================================================================================
// ID: PCSK010
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pcsk010Service
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/22 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.service.csk;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import coop.kyushu.common.entity.ServerErrorEntity;
import coop.kyushu.common.exception.ValidationException;
import coop.kyushu.common.util.BeanUtil;
import coop.kyushu.common.util.DateUtil;
import coop.kyushu.common.util.TantoshaInfoUtil;
import coop.kyushu.gyomu_kanri.entity.csk.Pcsk010ListItem;
import coop.kyushu.gyomu_kanri.form.csk.Pcsk010Form;
import coop.kyushu.gyomu_kanri.mapper.csk.Pcsk010Mapper;

/**
 * <pre>
 * 【コープ九州商管】CSV取込サービス
 * </pre>
 */
@Service
public class Pcsk010Service {

	// Core
	private final static String CORE = "0";

	// 使用中フラグ
	private final static String IS_USE_FLAG_1 = "1";

	// 画面ID
	private final static String VIEW_ID = "PCSK010";

	// エラー文言
	private final static String READ_CSV_ERROR_RESULT = "エラー";

	/**
	 * 担当者情報
	 */
	@Autowired
	private TantoshaInfoUtil tantoshaInfoUtil;

	/**
	 * 【コープ九州商管】CSV取込マッパー
	 */
	@Autowired
	private Pcsk010Mapper mapper;

	/**
	 * CSV取込処理
	 * @param form 入力内容
	 * @return 取込結果リスト
	 * @throws Exception 
	 */
	public List<Pcsk010ListItem> readcsv(Pcsk010Form form) throws Exception {
		// CSVファイルを読み込む。
		MultipartFile csvMultipartFile = form.getCsvFile();
		List<List<String>> csvTwoDimensionalList = this.multipartFileToList(csvMultipartFile);
		// ステータス
		String outStatus = "";
		// CSVファイルチェックを実行する。
		if (csvTwoDimensionalList.size() == 0) {
			// CSVデータリストの長さが 0 の場合:SKC0001E 取込対象の行がありません。
			throw new ValidationException(new ServerErrorEntity("SKC0001E", List.of("取込対象の行")));
		}

		// 入力Form.取込対象が対するマスタ識別ID
		Map<String, Object> param = BeanUtil.toMap(form);
		// マスタ識別ＩＤ
		String masterIdentId = "";
		switch (form.getTorikmTarget()) {
		case "F":
			masterIdentId = "SKWK0001";
			break;
		case "H":
			masterIdentId = "SKWK0002";
			break;
		case "Z":
			masterIdentId = "SKWK0003";
			break;
		case "S":
			if (CORE.equals(form.getVarDempyo())) {
				masterIdentId = "SKWK0004";
			} else {
				masterIdentId = "SKWK0005";
			}
			break;
		case "Y":
			masterIdentId = "SKWK0008";
			break;
		}

		// ユーザーコード
		param.put("masterIdentId", masterIdentId);

		// 排他制御処理を実行する。
		String exclusiveControlFlg = mapper.selectControlStatusByPcsk010(param);

		// 排他チェックをする。
		if (ObjectUtils.isEmpty(exclusiveControlFlg) || IS_USE_FLAG_1.equals(exclusiveControlFlg)) {
			// 排他エラーを返却して処理を終了する。CSK0001E 他のユーザーが登録処理中です。
			throw new ValidationException(new ServerErrorEntity("CSK0001E"));
		}
		// 排他制御テーブルを更新する。
		param.put("status", "1");
		mapper.updateControlStatusByPskc010(param);

		// ワークテーブルのデータをクリアする。
		mapper.deleteWorkTableByPskc010(param);

		// リストを宣言する。
		List<Pcsk010ListItem> readCsvResultList = new ArrayList<>();

		// DBへの登録データを編集する
		List<Map<String, Object>> editCsvList = this.editCsvList(csvTwoDimensionalList, form);

		// 取り込んだCSVデータをPEACEデータ取込ワークに登録する
		Pcsk010ListItem readCsvResult;
		String filename = csvMultipartFile.getOriginalFilename();
		for (int i = 0; i < editCsvList.size(); i++) {

			try {
				// ワークテーブルにデータを登録する。
				switch (form.getTorikmTarget()) {
				case "F":
					mapper.insertSKWK0001ByPskc010(editCsvList.get(i));
					break;
				case "H":
					mapper.insertSKWK0002ByPskc010(editCsvList.get(i));
					break;
				case "Z":
					mapper.insertSKWK0003ByPskc010(editCsvList.get(i));
					break;
				case "S":
					if (CORE.equals(form.getVarDempyo())) {
						mapper.insertSKWK0004ByPskc010(editCsvList.get(i));
					} else {
						mapper.insertSKWK0005ByPskc010(editCsvList.get(i));
					}
					break;
				case "Y":
					mapper.insertSKWK0008ByPskc010(editCsvList.get(i));
					break;
				}
			} catch (Exception e) {

				// エラーから重要な内容を切り出す
				String message = e.getMessage();
				List<String> errorMsgArray = Arrays.asList(message.split("\n"));
				String errorMsg = errorMsgArray.get(1).replaceAll("###", "");

				readCsvResult = new Pcsk010ListItem();
				// ファイル名
				readCsvResult.setFileNm(filename);
				// 取込結果
				readCsvResult.setTorikmReslt(READ_CSV_ERROR_RESULT);
				// 行番号
				readCsvResult.setGyoNo(String.valueOf(i + 1));
				// エラー内容
				readCsvResult.setErrContents(errorMsg);

				readCsvResultList.add(readCsvResult);
			}
		}

		// CSV取込んでから、エラーがある場合
		if (readCsvResultList.size() > 0) {
			// ワークテーブルのデータをクリアする。
			mapper.deleteWorkTableByPskc010(param);

			// 排他制御テーブルを更新する。
			param.put("status", "0");
			mapper.updateControlStatusByPskc010(param);
			return readCsvResultList;
		}

		// ワークテーブルのデータをテーブルに登録する。
		Map<String, Object> procedureParamMap = new HashMap<String, Object>() {
			{
				// ユーザーID
				put("inUSERID", StringUtils.trimAllWhitespace(tantoshaInfoUtil.getTansCd()));
				// ステータス
				put("outSTATUS", "");
				// メッセージ
				put("outMSG", "");
			}
		};
		switch (form.getTorikmTarget()) {
		case "F":
			mapper.createSKWK0001ByPskc010(procedureParamMap);
			break;
		case "H":
			mapper.createSKWK0002ByPskc010(procedureParamMap);
			break;
		case "Z":
			mapper.createSKWK0003ByPskc010(procedureParamMap);
			break;
		case "S":
			if (CORE.equals(form.getVarDempyo())) {
				mapper.createSKWK0004ByPskc010(procedureParamMap);
			} else {
				mapper.createSKWK0005ByPskc010(procedureParamMap);
			}
			break;
		case "Y":
			mapper.createSKWK0008ByPskc010(procedureParamMap);
			break;
		}

		// 排他制御テーブルを更新する。
		param.put("status", "0");
		mapper.updateControlStatusByPskc010(param);

		// プロシージャ呼び出し結果によりの処理を実行する。
		outStatus = (String) procedureParamMap.get("outSTATUS");
		if (!"0".equals(outStatus)) {
			throw new ValidationException(new ServerErrorEntity("CSK0008E", List.of("CSVファイルの取込")));
		}

		Pcsk010ListItem pcsk010ListItem = new Pcsk010ListItem();
		pcsk010ListItem.setFileNm(filename);
		pcsk010ListItem.setTorikmReslt("取込処理正常終了(取込件数:" + csvTwoDimensionalList.size() + "件)");
		readCsvResultList.add(pcsk010ListItem);

		return readCsvResultList;
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
		// CSVファイルのタイトルを削除する
		if (csvList.size() != 0) {
			csvList.remove(0);
		}

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
	 * DBへの登録データを編集する
	 * @param csvTwoDimensionalList CSVファイル
	 * @param form 入力内容
	 * @return DBへの登録データ
	 */
	private List<Map<String, Object>> editCsvList(List<List<String>> csvTwoDimensionalList, Pcsk010Form form) {

		// ログインユーザーID
		String userId = StringUtils.trimAllWhitespace(tantoshaInfoUtil.getTansCd());

		// システム時間を取得する
		Timestamp currentDate = DateUtil.getCurrentTimestamp();

		List<Map<String, Object>> csvEditList = new ArrayList<Map<String, Object>>();
		Map<String, Object> csvRowMap;

		for (int i = 0; i < csvTwoDimensionalList.size(); i++) {
			List<String> csvRowDataList = csvTwoDimensionalList.get(i);
			csvRowMap = new HashMap<String, Object>();

			switch (form.getTorikmTarget()) {
			case "F":
				csvRowMap = editCsvSkwk0001List(csvRowMap, csvRowDataList, i);
				break;
			case "H":
				csvRowMap = editCsvSkwk0002List(csvRowMap, csvRowDataList, i);
				break;
			case "Z":
				csvRowMap = editCsvSkwk0003List(csvRowMap, csvRowDataList, i);
				break;
			case "S":
				if (CORE.equals(form.getVarDempyo())) {
					csvRowMap = editCsvSkwk0004List(csvRowMap, csvRowDataList, i);
				} else {
					csvRowMap = editCsvSkwk0005List(csvRowMap, csvRowDataList, i);
				}
				break;
			case "Y":
				csvRowMap = editCsvSkwk0008List(csvRowMap, csvRowDataList, i);
				break;
			}

			// 作成ユーザー
			csvRowMap.put("SKSI_USR", userId);
			// 作成プログラム
			csvRowMap.put("SKSI_PRGRM", VIEW_ID);
			// 作成日時
			csvRowMap.put("SKSI_NCHJ", currentDate);
			// 更新ユーザー
			csvRowMap.put("KUSHN_USR", userId);
			// 更新プログラム
			csvRowMap.put("KUSHN_PRGRM", VIEW_ID);
			// 更新日時
			csvRowMap.put("KUSHN_NCHJ", currentDate);

			csvEditList.add(csvRowMap);
		}

		return csvEditList;
	}

	/**
	 * 振替伝票登録への登録データを編集する
	 * @param csvRowMap マップ
	 * @param csvRowDataList 取込したCSVファイル
	 * @return 振替伝票登録への登録データ
	 */
	private Map<String, Object> editCsvSkwk0001List(Map<String, Object> csvRowMap,
			List<String> csvRowDataList, int index) {
		// シーケンス番号
		csvRowMap.put("SEQNO", index + 1);
		// 伝票番号
		csvRowMap.put("DENNO", csvRowDataList.get(0));
		// 行番号
		csvRowMap.put("GYONO", csvRowDataList.get(1));
		// 伝票日付 書式：YYYYMMDD。
		csvRowMap.put("DENDATE", csvRowDataList.get(2));
		// 出庫側センタコード
		csvRowMap.put("SCENTCD", csvRowDataList.get(3));
		// 入庫側センタコード
		csvRowMap.put("NCENTCD", csvRowDataList.get(4));
		// 商品コード
		csvRowMap.put("CQSHINCD", csvRowDataList.get(5));
		// 数量
		csvRowMap.put("SURYO", csvRowDataList.get(6));
		// 金額
		csvRowMap.put("KINGAKU", csvRowDataList.get(7));

		return csvRowMap;
	}

	/**
	 * 廃棄伝票登録への登録データを編集する
	 * @param csvRowMap マップ
	 * @param csvRowDataList 取込したCSVファイル
	 * @return 廃棄伝票登録への登録データ
	 */
	private Map<String, Object> editCsvSkwk0002List(Map<String, Object> csvRowMap,
			List<String> csvRowDataList, int index) {
		// シーケンス番号
		csvRowMap.put("SEQNO", index + 1);
		// 伝票番号
		csvRowMap.put("DENNO", csvRowDataList.get(0));
		// 行番号
		csvRowMap.put("GYONO", csvRowDataList.get(1));
		// 伝票日付 書式：YYYYMMDD。
		csvRowMap.put("DENDATE", csvRowDataList.get(2));
		// センタコード
		csvRowMap.put("SCENTCD", csvRowDataList.get(3));
		// 商品コード
		csvRowMap.put("CQSHINCD", csvRowDataList.get(4));
		// 数量
		csvRowMap.put("SURYO", csvRowDataList.get(5));
		// 金額
		csvRowMap.put("KINGAKU", csvRowDataList.get(6));

		return csvRowMap;
	}

	/**
	 * 在庫調整登録への登録データを編集する
	 * @param csvRowMap マップ
	 * @param csvRowDataList 取込したCSVファイル
	 * @return 在庫調整登録への登録データ
	 */
	private Map<String, Object> editCsvSkwk0003List(Map<String, Object> csvRowMap,
			List<String> csvRowDataList, int index) {
		// シーケンス番号
		csvRowMap.put("SEQNO", index + 1);
		// 伝票番号
		csvRowMap.put("DENNO", csvRowDataList.get(0));
		// 行番号
		csvRowMap.put("GYONO", csvRowDataList.get(1));
		// 対象月度
		csvRowMap.put("KYM", csvRowDataList.get(2));
		// センタコード
		csvRowMap.put("SCENTCD", csvRowDataList.get(3));
		// 事業所区分
		csvRowMap.put("JIGYOSKB", csvRowDataList.get(4));
		// 無店舗大分類
		csvRowMap.put("DAIBUNCD", csvRowDataList.get(5));
		// 商品コード
		csvRowMap.put("CQSHINCD", csvRowDataList.get(6));
		// 最終仕入価
		csvRowMap.put("SIRE_TANK", csvRowDataList.get(7));
		// 月末在庫数量
		csvRowMap.put("HZAI_SU", csvRowDataList.get(8));
		// 月末在庫金額
		csvRowMap.put("HZAI_GAK", csvRowDataList.get(9));
		// 積送中在庫数量
		csvRowMap.put("SEKI_SU", csvRowDataList.get(10));
		// 積送中在庫金額
		csvRowMap.put("SEKI_GAK", csvRowDataList.get(11));

		return csvRowMap;
	}

	/**
	 * 諸伝票登録-Coreへの登録データを編集する
	 * @param csvRowMap マップ
	 * @param csvRowDataList 取込したCSVファイル
	 * @return 諸伝票登録-Coreへの登録データ
	 */
	private Map<String, Object> editCsvSkwk0004List(Map<String, Object> csvRowMap,
			List<String> csvRowDataList, int index) {
		// シーケンス番号
		csvRowMap.put("SEQNO", index + 1);
		// 粗利表分類
		csvRowMap.put("ARABUNCD", csvRowDataList.get(0));
		// 伝票日付
		csvRowMap.put("DENDATE", csvRowDataList.get(1));
		// 伝票番号
		csvRowMap.put("DENNO", csvRowDataList.get(2));
		// 行番号
		csvRowMap.put("GYONO", csvRowDataList.get(3));
		// 借方科目コード
		csvRowMap.put("KRKMKCD", csvRowDataList.get(4));
		// 借方補助コード
		csvRowMap.put("KRHKMCD", csvRowDataList.get(5));
		// 借方部門コード
		csvRowMap.put("KRBMNCD", csvRowDataList.get(6));
		// 借方取引先区分
		csvRowMap.put("KRTORIKB", csvRowDataList.get(7));
		// 借方取引先コード
		csvRowMap.put("KRTORICD", csvRowDataList.get(8));
		// 借方機能コード１
		csvRowMap.put("KRKNCD1", csvRowDataList.get(9));
		// 借方機能コード２
		csvRowMap.put("KRKNCD2", csvRowDataList.get(10));
		// 借方機能コード３
		csvRowMap.put("KRKNCD3", csvRowDataList.get(11));
		// 借方機能コード４
		csvRowMap.put("KRKNCD4", csvRowDataList.get(12));
		// 借方税処理コード
		csvRowMap.put("KRZEICD", csvRowDataList.get(13));
		// 借方税入力区分
		csvRowMap.put("KRZEINKB", csvRowDataList.get(14));
		// 借方仕訳金額税抜
		csvRowMap.put("KRSIWGKN", csvRowDataList.get(15));
		// 借方税額
		csvRowMap.put("KRZEIGK", csvRowDataList.get(16));
		// 借方仕訳金額税込
		csvRowMap.put("KRSIWGKK", csvRowDataList.get(17));
		// 借方摘要１
		csvRowMap.put("KRTEKIY1", csvRowDataList.get(18));
		// 借方摘要２
		csvRowMap.put("KRTEKIY2", csvRowDataList.get(19));
		// 貸方科目コード
		csvRowMap.put("KSKMKCD", csvRowDataList.get(20));
		// 貸方補助コード
		csvRowMap.put("KSHKMCD", csvRowDataList.get(21));
		// 貸方部門コード
		csvRowMap.put("KSBMNCD", csvRowDataList.get(22));
		// 貸方取引先区分
		csvRowMap.put("KSTORIKB", csvRowDataList.get(23));
		// 貸方取引先コード
		csvRowMap.put("KSTORICD", csvRowDataList.get(24));
		// 貸方機能コード１
		csvRowMap.put("KSKNCD1", csvRowDataList.get(25));
		// 貸方機能コード２
		csvRowMap.put("KSKNCD2", csvRowDataList.get(26));
		// 貸方機能コード３
		csvRowMap.put("KSKNCD3", csvRowDataList.get(27));
		// 貸方機能コード４
		csvRowMap.put("KSKNCD4", csvRowDataList.get(28));
		// 貸方税処理コード
		csvRowMap.put("KSZEICD", csvRowDataList.get(29));
		// 貸方税入力区分
		csvRowMap.put("KSZEINKB", csvRowDataList.get(30));
		// 貸方仕訳金額税抜
		csvRowMap.put("KSSIWGKN", csvRowDataList.get(31));
		// 貸方税額
		csvRowMap.put("KSZEIGK", csvRowDataList.get(32));
		// 貸方仕訳金額税込
		csvRowMap.put("KSSIWGKK", csvRowDataList.get(33));
		// 貸方摘要１
		csvRowMap.put("KSTEKIY1", csvRowDataList.get(34));
		// 貸方摘要２
		csvRowMap.put("KSTEKIY2", csvRowDataList.get(35));

		return csvRowMap;

	}

	/**
	 * 諸伝票登録-APへの登録データを編集する
	 * @param csvRowMap マップ
	 * @param csvRowDataList 取込したCSVファイル
	 * @return 諸伝票登録-APへの登録データ
	 */
	private Map<String, Object> editCsvSkwk0005List(Map<String, Object> csvRowMap,
			List<String> csvRowDataList, int index) {
		// シーケンス番号
		csvRowMap.put("SEQNO", index + 1);
		// 粗利表分類
		csvRowMap.put("ARABUNCD", csvRowDataList.get(0));
		// 伝票日付
		csvRowMap.put("DENDATE", csvRowDataList.get(1));
		// 伝票区分
		csvRowMap.put("DENKB", csvRowDataList.get(2));
		// 伝票番号
		csvRowMap.put("DENNO", csvRowDataList.get(3));
		// 行番号
		csvRowMap.put("GYONO", csvRowDataList.get(4));
		// 貸借反転区分
		csvRowMap.put("TSHNTKB", csvRowDataList.get(5));
		// 借方科目コード
		csvRowMap.put("KRKMKCD", csvRowDataList.get(6));
		// 借方補助コード
		csvRowMap.put("KRHKMCD", csvRowDataList.get(7));
		// 借方部門コード
		csvRowMap.put("KRBMNCD", csvRowDataList.get(8));
		// 借方取引先区分
		csvRowMap.put("KRTORIKB", csvRowDataList.get(9));
		// 借方取引先コード
		csvRowMap.put("KRTORICD", csvRowDataList.get(10));
		// 借方機能コード１
		csvRowMap.put("KRKNCD1", csvRowDataList.get(11));
		// 借方機能コード２
		csvRowMap.put("KRKNCD2", csvRowDataList.get(12));
		// 借方機能コード３
		csvRowMap.put("KRKNCD3", csvRowDataList.get(13));
		// 借方機能コード４
		csvRowMap.put("KRKNCD4", csvRowDataList.get(14));
		// 借方税処理コード
		csvRowMap.put("KRZEICD", csvRowDataList.get(15));
		// 借方税入力区分
		csvRowMap.put("KRZEINKB", csvRowDataList.get(16));
		// 借方仕訳金額税抜
		csvRowMap.put("KRSIWGKN", csvRowDataList.get(17));
		// 借方税額
		csvRowMap.put("KRZEIGK", csvRowDataList.get(18));
		// 借方仕訳金額税込
		csvRowMap.put("KRSIWGKK", csvRowDataList.get(19));
		// 借方摘要
		csvRowMap.put("KRTEKIY", csvRowDataList.get(20));
		// 貸方科目コード
		csvRowMap.put("KSKMKCD", csvRowDataList.get(21));
		// 貸方補助コード
		csvRowMap.put("KSHKMCD", csvRowDataList.get(22));
		// 貸方部門コード
		csvRowMap.put("KSBMNCD", csvRowDataList.get(23));
		// 貸方取引先区分
		csvRowMap.put("KSTORIKB", csvRowDataList.get(24));
		// 貸方取引先コード
		csvRowMap.put("KSTORICD", csvRowDataList.get(25));
		// 貸方機能コード１
		csvRowMap.put("KSKNCD1", csvRowDataList.get(26));
		// 貸方機能コード２
		csvRowMap.put("KSKNCD2", csvRowDataList.get(27));
		// 貸方機能コード３
		csvRowMap.put("KSKNCD3", csvRowDataList.get(28));
		// 貸方機能コード４
		csvRowMap.put("KSKNCD4", csvRowDataList.get(29));
		// 貸方税処理コード
		csvRowMap.put("KSZEICD", csvRowDataList.get(30));
		// 貸方税入力区分
		csvRowMap.put("KSZEINKB", csvRowDataList.get(31));
		// 貸方仕訳金額税抜
		csvRowMap.put("KSSIWGKN", csvRowDataList.get(32));
		// 貸方税額
		csvRowMap.put("KSZEIGK", csvRowDataList.get(33));
		// 貸方仕訳金額税込
		csvRowMap.put("KSSIWGKK", csvRowDataList.get(34));
		// 貸方摘要
		csvRowMap.put("KSTEKIY", csvRowDataList.get(35));

		return csvRowMap;
	}

	/**
	 * 予算登録への登録データを編集する
	 * @param csvRowMap マップ
	 * @param csvRowDataList 取込したCSVファイル
	 * @return 予算登録への登録データ
	 */
	private Map<String, Object> editCsvSkwk0008List(Map<String, Object> csvRowMap,
			List<String> csvRowDataList, int index) {
		// シーケンス番号
		csvRowMap.put("SEQNO", index + 1);
		// 計上年月
		csvRowMap.put("KYM", csvRowDataList.get(0));
		// 会計部門コード
		csvRowMap.put("KAIKEBMN", csvRowDataList.get(1));
		// 大分類コード
		csvRowMap.put("DAIBNCD", csvRowDataList.get(2));
		// 予算
		csvRowMap.put("YOSAN", csvRowDataList.get(3));

		return csvRowMap;
	}
}
