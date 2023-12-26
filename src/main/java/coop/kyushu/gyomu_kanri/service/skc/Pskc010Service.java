//================================================================================
//ID: PSKC010
//ProjectName: コープ九州事業連合様  ホストシステムリプレイス
//SystemName: 業務管理
//ClassName: Pskc010Servie
//
//<<Modification History>>
//Version  | Date       | Updated By           | Content
//---------+------------+----------------------+---------------------------------
//01.00.00 | 2023/08/11 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.service.skc;

import java.io.File;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import coop.kyushu.common.entity.ServerErrorEntity;
import coop.kyushu.common.exception.ValidationException;
import coop.kyushu.common.util.DateUtil;
import coop.kyushu.common.util.MessageUtil;
import coop.kyushu.common.util.TantoshaInfoUtil;
import coop.kyushu.gyomu_kanri.entity.skc.Pskc010ListItem;
import coop.kyushu.gyomu_kanri.form.skc.Pskc010Form;
import coop.kyushu.gyomu_kanri.mapper.skc.Pskc010Mapper;

/**
 * <pre>
 * 【惣菜加工センター】値付実績データ取込画面
 * </pre>
 */
@Service
public class Pskc010Service {

	@Autowired
	Pskc010Mapper pskc010Mapper;

	@Autowired
	private TantoshaInfoUtil tantoshaInfoUtil;

	@Autowired
	private MessageUtil messageUtil;

	/**
	 * CSVファイル取込処理
	 * 
	 * @param form 入力内容
	 * @return 取込結果リスト
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	public List<Pskc010ListItem> readcsv(Pskc010Form form) throws Exception {

		// CSVのMultipartFileをList<List<String>>に変更する
		MultipartFile csvMultipartFile = form.getCsvFile();
		List<List<String>> csvTwoDimensionalList = this.multipartFileToList(csvMultipartFile);

		// CSV内容存在チェック
		if (csvTwoDimensionalList.size() - 1 == 0) {
			throw new ValidationException(new ServerErrorEntity("SKC0001E", List.of("取込対象の行")));
		}

		// 取込結果リスト
		List<Pskc010ListItem> readCsvResultList = new ArrayList<Pskc010ListItem>();

		// 取込CSVファイルファイル名
		String filename = csvMultipartFile.getOriginalFilename();
		for (int i = 0; i < csvTwoDimensionalList.size(); i++) {
			// CSV列
			List<String> csvList = csvTwoDimensionalList.get(i);
			// CSV列チェック
			if (csvList.size() < 15) {
				throw new ValidationException(new ServerErrorEntity("SKC0009E", List.of("CSVデータの列数")));
			}

			for (int y = 0; y < 15; y++) {
				// エラー内容
				String errorMsg = messageUtil.getMessage("SKC0007E", List.of("CSVデータにNULL"));

				if ("".equals(csvTwoDimensionalList.get(i).get(y))) {

					readCsvResultList.add(this.editReadCsvResult(filename, "エラー", String.valueOf(i), errorMsg));
				}
			}

		}

		if (readCsvResultList.size() > 0) {
			return readCsvResultList;
		}

		// 「値付実績取込ワーク」テーブルを削除する
		String userId = StringUtils.trimAllWhitespace(tantoshaInfoUtil.getTansCd());
		pskc010Mapper.deleteWorkTableByPskc010(userId);

		// システム時間を取得する
		Timestamp currentDate = DateUtil.getCurrentTimestamp();
		// システム日付
		SimpleDateFormat ymdFormat = new SimpleDateFormat("yyyyMMdd");
		String date = ymdFormat.format(currentDate);
		// システム時刻
		SimpleDateFormat hmsFormat = new SimpleDateFormat("HHmmss");
		String time = hmsFormat.format(currentDate);

		// 値付実績取込ワーク編集用のリスト
		List<Map<String, Object>> editCsvList = new ArrayList<Map<String, Object>>();

		editCsvList = this.editCsvList(csvTwoDimensionalList);

		// 受付番号を取得する
		BigDecimal ukeNo = pskc010Mapper.selectUkeNoByPskc010(date);

		for (int i = 0; i < editCsvList.size(); i++) {
			// 店舗コードを取得する
			String tsbcd = editCsvList.get(i).get("TSBCD").toString();
			String shopId = tsbcd.substring(tsbcd.length() - 4, tsbcd.length());

			// 名称マスタ存在チェック
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("RECKB", "A2");
			param.put("MEICD", shopId);

			int mstMeismCount = pskc010Mapper.selectMstmeismCountByPskc010(param);

			if (mstMeismCount <= 0) {
				// 受付日時
				String ukeDate = date + time + "　　";
				// エラー内容
				String errorMsg = messageUtil.getMessage("SKC0006E", List.of("店舗コード"));

				readCsvResultList.add(this.editReadCsvResult(filename, "エラー", String.valueOf(i + 1),
						ukeDate + ukeNo + errorMsg));

				// 「値付実績チェック結果」テーブルに登録する
				Map<String, Object> checkParam = new HashMap<String, Object>();
				checkParam.put("HATDATE", date);
				checkParam.put("HATTIME", time);
				checkParam.put("USERID", editCsvList.get(i).get("UUID"));
				checkParam.put("UKENO", ukeNo);
				checkParam.put("NODATE", editCsvList.get(i).get("NODATE"));
				checkParam.put("TSBCD", editCsvList.get(i).get("TSBCD"));
				checkParam.put("HINCD", editCsvList.get(i).get("HINCD"));
				checkParam.put("PSSU", editCsvList.get(i).get("PSSU"));
				checkParam.put("ERRMSG", errorMsg);
				checkParam.put("SKSI_USR", editCsvList.get(i).get("SKSI_USR"));
				checkParam.put("SKSI_PRGRM", editCsvList.get(i).get("SKSI_PRGRM"));
				checkParam.put("SKSI_NCHJ", editCsvList.get(i).get("SKSI_NCHJ"));
				checkParam.put("KUSHN_USR", editCsvList.get(i).get("KUSHN_USR"));
				checkParam.put("KUSHN_PRGRM", editCsvList.get(i).get("KUSHN_PRGRM"));
				checkParam.put("KUSHN_NCHJ", editCsvList.get(i).get("KUSHN_NCHJ"));

				pskc010Mapper.insertCheckTableByPskc010(checkParam);
				continue;
			}

			// 店舗商品マスタを検索する
			Map<String, Object> shopParam = new HashMap<String, Object>();
			shopParam.put("CQSHINCD", editCsvList.get(i).get("HINCD").toString());
			shopParam.put("JIGYOCD", 0);

			int shopCount = pskc010Mapper.selectTenpoShoHinCountByPskc010(shopParam);

			if (shopCount <= 0) {
				// 受付日時
				String ukeDate = date + time + "　　";
				// エラー内容
				String error = messageUtil.getMessage("SKC0006E", List.of("商品コード"));

				readCsvResultList.add(
						this.editReadCsvResult(filename, "エラー", String.valueOf(i + 1), ukeDate + ukeNo + error));

				// 「値付実績チェック結果」テーブルに登録する
				Map<String, Object> checkParams = new HashMap<String, Object>();
				checkParams.put("HATDATE", date);
				checkParams.put("HATTIME", time);
				checkParams.put("USERID", editCsvList.get(i).get("UUID"));
				checkParams.put("UKENO", ukeNo);
				checkParams.put("NODATE", editCsvList.get(i).get("NODATE"));
				checkParams.put("TSBCD", editCsvList.get(i).get("TSBCD"));
				checkParams.put("HINCD", editCsvList.get(i).get("HINCD"));
				checkParams.put("PSSU", editCsvList.get(i).get("PSSU"));
				checkParams.put("ERRMSG", error);
				checkParams.put("SKSI_USR", editCsvList.get(i).get("SKSI_USR"));
				checkParams.put("SKSI_PRGRM", editCsvList.get(i).get("SKSI_PRGRM"));
				checkParams.put("SKSI_NCHJ", editCsvList.get(i).get("SKSI_NCHJ"));
				checkParams.put("KUSHN_USR", editCsvList.get(i).get("KUSHN_USR"));
				checkParams.put("KUSHN_PRGRM", editCsvList.get(i).get("KUSHN_PRGRM"));
				checkParams.put("KUSHN_NCHJ", editCsvList.get(i).get("KUSHN_NCHJ"));

				pskc010Mapper.insertCheckTableByPskc010(checkParams);
				continue;
			}

			// 値付累積データ重複チェック

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("NODATE", editCsvList.get(i).get("NODATE"));
			params.put("TSBCD", editCsvList.get(i).get("TSBCD"));
			params.put("HINCD", editCsvList.get(i).get("HINCD"));

			int accruedData = pskc010Mapper.selectRzsouzaiRuiCountByPskc010(params);

			if (accruedData > 0) {
				// 受付日時
				String ukeDate = date + time + "　　";

				// エラー内容
				String errorMessage = messageUtil.getMessage("SKC0010E");

				readCsvResultList.add(this.editReadCsvResult(filename, "エラー", String.valueOf(i + 1),
						ukeDate + ukeNo + errorMessage));

				// 「値付実績チェック結果」テーブルに登録する
				Map<String, Object> checkMap = new HashMap<String, Object>();
				checkMap.put("HATDATE", date);
				checkMap.put("HATTIME", time);
				checkMap.put("USERID", editCsvList.get(i).get("UUID"));
				checkMap.put("UKENO", ukeNo);
				checkMap.put("NODATE", editCsvList.get(i).get("NODATE"));
				checkMap.put("TSBCD", editCsvList.get(i).get("TSBCD"));
				checkMap.put("HINCD", editCsvList.get(i).get("HINCD"));
				checkMap.put("PSSU", editCsvList.get(i).get("PSSU"));
				checkMap.put("ERRMSG", errorMessage);
				checkMap.put("SKSI_USR", editCsvList.get(i).get("SKSI_USR"));
				checkMap.put("SKSI_PRGRM", editCsvList.get(i).get("SKSI_PRGRM"));
				checkMap.put("SKSI_NCHJ", editCsvList.get(i).get("SKSI_NCHJ"));
				checkMap.put("KUSHN_USR", editCsvList.get(i).get("KUSHN_USR"));
				checkMap.put("KUSHN_PRGRM", editCsvList.get(i).get("KUSHN_PRGRM"));
				checkMap.put("KUSHN_NCHJ", editCsvList.get(i).get("KUSHN_NCHJ"));

				pskc010Mapper.insertCheckTableByPskc010(checkMap);
				continue;
			}

			try {
				pskc010Mapper.insertWorkTableByPskc010(editCsvList.get(i));
			} catch (Exception e) {

				// 受付日時
				String ukeDate = date + time + "　　";

				// エラーから重要な内容を切り出す
				String message = e.getMessage();
				List<String> errorMsgArray = Arrays.asList(message.split("\n"));
				String errorMsg = errorMsgArray.get(1).replaceAll("###", "");

				readCsvResultList.add(
						this.editReadCsvResult(filename, "エラー", String.valueOf(i + 1), ukeDate + ukeNo + errorMsg));
			}
		}

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("HATDATE", date);
		params.put("HATTIME", time);
		params.put("USERID", userId);
		params.put("UKENO", ukeNo);

		//「値付実績データ」テーブルに登録する
		pskc010Mapper.insertSouzaiJdByPskc010(params);

		//「値付実績当日累積」テーブルに登録する
		pskc010Mapper.insertRzsouzaiTouByPskc010(params);

		// 取込成功の件数を取得する
		int readSucceedCount = pskc010Mapper.selectSouzaiJdCountByPskc010(params);

		readCsvResultList.add(
				this.editReadCsvResult(filename, "取込処理正常終了(取込件数:" + String.valueOf(readSucceedCount) + "件)", "", ""));

		return readCsvResultList;
	}

	/**
	 * 取込結果一覧データを編集する 
	 * 
	 * @param filename  ファイル名
	 * @param error 取込結果
	 * @param gyono 行番号
	 * @param contents エラー内容
	 * @return 取込結果
	 */
	private Pskc010ListItem editReadCsvResult(String filename, String result, String gyono, String contents) {
		Pskc010ListItem readCsvResult = new Pskc010ListItem();
		// ファイル名
		readCsvResult.setFileNm(filename);
		// 取込結果
		readCsvResult.setTorikmReslt(result);
		// 行番号
		readCsvResult.setGyoNo(gyono);
		// エラー内容
		readCsvResult.setErrContents(contents);

		return readCsvResult;
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

	/**
	 * 「値付実績取込ワーク」テーブルへの登録処理データを編集する。
	 * 
	 * @param csvTwoDimensionalList
	 * @return 取込結果リスト
	 */
	private List<Map<String, Object>> editCsvList(List<List<String>> csvTwoDimensionalList) {

		// ログインユーザーID
		String userId = StringUtils.trimAllWhitespace(tantoshaInfoUtil.getTansCd());

		// システム時間を取得する
		Timestamp currentDate = DateUtil.getCurrentTimestamp();

		List<Map<String, Object>> csvEditList = new ArrayList<Map<String, Object>>();

		for (int i = 1; i < csvTwoDimensionalList.size(); i++) {
			Map<String, Object> csvMap = new HashMap<String, Object>();

			// UUID
			csvMap.put("UUID", userId);
			// 取込連番
			csvMap.put("SEQNO", i);
			// 納品日
			csvMap.put("NODATE", csvTwoDimensionalList.get(i).get(0).replace("/", ""));
			// 得意先コード
			csvMap.put("SEICD", csvTwoDimensionalList.get(i).get(1));
			// 得意先略称
			csvMap.put("SEIKNRM", csvTwoDimensionalList.get(i).get(2));
			// 納入先コード
			csvMap.put("TSBCD", csvTwoDimensionalList.get(i).get(3));
			// 納入先略称
			csvMap.put("TSBKNRM", csvTwoDimensionalList.get(i).get(4));
			// 品目分類コード
			csvMap.put("HBUNCD", csvTwoDimensionalList.get(i).get(5));
			// 品目分類名
			csvMap.put("HBUNKNM", csvTwoDimensionalList.get(i).get(6));
			// 品目コード
			csvMap.put("HINCD", csvTwoDimensionalList.get(i).get(7));
			// 品目名
			csvMap.put("HINKNM", csvTwoDimensionalList.get(i).get(8));
			// 計量区分
			csvMap.put("KIRYOKB", csvTwoDimensionalList.get(i).get(9));
			// 計量区分名
			csvMap.put("KIRYOKNM", csvTwoDimensionalList.get(i).get(10));
			// 実績数
			csvMap.put("PSSU", csvTwoDimensionalList.get(i).get(11));
			// 実績重量
			csvMap.put("JYURYO", csvTwoDimensionalList.get(i).get(12));
			// 実売金額
			csvMap.put("UKINGK", csvTwoDimensionalList.get(i).get(13));
			// 納入金額
			csvMap.put("GKINGK", csvTwoDimensionalList.get(i).get(14));
			// 作成ユーザー
			csvMap.put("SKSI_USR", userId);
			// 作成プログラム
			csvMap.put("SKSI_PRGRM", "PSKC010");
			// 作成日時
			csvMap.put("SKSI_NCHJ", currentDate);
			// 更新ユーザー
			csvMap.put("KUSHN_USR", userId);
			// 更新プログラム
			csvMap.put("KUSHN_PRGRM", "PSKC010");
			// 更新日時
			csvMap.put("KUSHN_NCHJ", currentDate);

			csvEditList.add(csvMap);
		}

		return csvEditList;

	}

}
