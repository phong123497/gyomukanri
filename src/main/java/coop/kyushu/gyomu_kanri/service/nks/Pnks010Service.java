//================================================================================
// ID: PNKS010
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks010Service
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/10 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.service.nks;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import coop.kyushu.common.entity.ServerErrorList;
import coop.kyushu.common.exception.ValidationException;
import coop.kyushu.common.util.DateUtil;
import coop.kyushu.common.util.TantoshaInfoUtil;
import coop.kyushu.gyomu_kanri.entity.nks.Pnks010ListItem;
import coop.kyushu.gyomu_kanri.form.nks.Pnks010Form;
import coop.kyushu.gyomu_kanri.mapper.nks.Pnks010Mapper;

/**
 * <pre>
 * 【農産企画支援】PEACEデータ取込サービス
 * </pre>
 */
@Service
public class Pnks010Service {

	/**
	 * PEACEデータ取込に関わるプロシージャサービス
	 */
	@Autowired
	private Pnks010ProcedureService procedureService;

	/**
	 * PEACEデータ取込マップ
	 */
	@Autowired
	private Pnks010Mapper mapper;

	/**
	 * 担当者情報
	 */
	@Autowired
	private TantoshaInfoUtil tantoshaInfoUtil;

	/**
	 * エラー文言
	 */
	private final static String READ_CSV_ERROR_RESULT = "エラー";

	/**
	 * 画面ID
	 */
	private final static String VIEW_ID = "PNKS010";

	/**
	 * 取込対象区分定数内部クラス
	 */
	private final static class TorikmTargetKbnConst {

		// 全て
		private final static String all = "0";

		// 定番
		private final static String teiban = "1";

		// 催事
		private final static String saiji = "2";

		// 取込対象区分マップ
		private final static Map<String, String> torikmTargetMap = new HashMap<>() {
			{
				// 1
				put("定番", teiban);
				// 2
				put("催事", saiji);
			}
		};
	}

	/**
	 * PEACEデータ取込画面の取込処理
	 * @param form 入力内容
	 * @return 取込結果リスト
	 * @throws Exception 
	 */
	public List<Pnks010ListItem> readcsv(Pnks010Form form) throws Exception {

		// PEACEデータ取込ワークを削除する
		String userId = StringUtils.trimAllWhitespace(tantoshaInfoUtil.getTansCd());
		mapper.deletePeaceDataWorkByPnks010(userId);

		// CSVのMultipartFileをList<List<String>>に変更する
		MultipartFile csvMultipartFile = form.getCsvFile();
		List<List<String>> csvTwoDimensionalList = this.multipartFileToList(csvMultipartFile);

		// 共同購入商品コードリストを取得する
		List<String> shoHinCodeList = csvTwoDimensionalList.stream().map(csvRowList -> csvRowList.get(3)).toList();

		// 共同購入商品マスタデータを取得する
		Map<String, Map<String, Object>> dwhMasterMap = mapper.getDWHInfoByPnks010(shoHinCodeList);

		// DBへの登録データを編集する
		List<Map<String, Object>> editCsvList = this.editCsvList(dwhMasterMap, csvTwoDimensionalList);

		// 取込結果リスト
		List<Pnks010ListItem> readCsvResultList = new ArrayList<Pnks010ListItem>();

		// 取り込んだCSVデータをPEACEデータ取込ワークに登録する
		Pnks010ListItem readCsvResult;
		String filename = csvMultipartFile.getOriginalFilename();
		for (int i = 0; i < editCsvList.size(); i++) {

			try {
				mapper.insertPeaceDataWorkByPnks010(editCsvList.get(i));
			} catch (Exception e) {

				// エラーから重要な内容を切り出す
				String message = e.getMessage();
				List<String> errorMsgArray = Arrays.asList(message.split("\n"));
				String errorMsg = errorMsgArray.get(1).replaceAll("###", "");

				readCsvResult = new Pnks010ListItem();
				readCsvResult
						// ファイル名
						.setFileNm(filename)
						// 取込結果
						.setTorikmReslt(READ_CSV_ERROR_RESULT)
						// 行番号
						.setGyoNo(String.valueOf(i + 1))
						// エラー内容
						.setErrContents(errorMsg);

				readCsvResultList.add(readCsvResult);
			}
		}

		// CSV取込んでから、エラーがある場合
		if (readCsvResultList.size() > 0) {
			// PEACEデータ取込ワークを削除する
			mapper.deletePeaceDataWorkByPnks010(userId);
			return readCsvResultList;
		}

		// 定催区分のチェックを実行する
		this.torikmTargetKbnCheck(form.getTorikmTargetKbn());

		// プロシージャを呼び出して、農産企画商品マスタを作成する
		Pnks010ListItem procedureExecResultEntity = procedureService.execProcedureNSSP010(userId, VIEW_ID);
		// エラーメッセージがある場合
		if (!ObjectUtils.isEmpty(procedureExecResultEntity.getErrContents())) {
			// ファイル名と実行失敗の文言を設定する
			procedureExecResultEntity.setFileNm(filename).setTorikmReslt(READ_CSV_ERROR_RESULT);
			return List.of(procedureExecResultEntity);
		}

		// 取込成功の結果を返却する
		Pnks010ListItem pnks010ListItem = new Pnks010ListItem();
		pnks010ListItem
				.setFileNm(filename)
				.setTorikmReslt("取込処理正常終了(取込件数:" + csvTwoDimensionalList.size() + "件)");
		readCsvResultList.add(pnks010ListItem);

		return readCsvResultList;
	}

	/**
	 * MultipartFileデータからList<List<String>>データへの変換
	 * @param multipartFile CSVファイル
	 * @return List<List<String>>データ
	 * @throws Exception 
	 */
	private List<List<String>> multipartFileToList(MultipartFile multipartFile) throws Exception {

		ServerErrorList serverErrorList = new ServerErrorList();

		// CSVファイルの大きさを取得してチェックする
		long csvFileSize = multipartFile.getSize();
		if (csvFileSize == 0) {
			serverErrorList.add("NKS0014E", "取込内容", null);
			throw new ValidationException(serverErrorList);
		}

		// CSVオリジナルファイル名を取得する
		String originalFilename = multipartFile.getOriginalFilename();

		// CSVファイル名と拡張子を取得する
		String fileName = StringUtils.stripFilenameExtension(originalFilename) + UUID.randomUUID().toString();
		String filenameExtension = StringUtils.getFilenameExtension(originalFilename);

		// 一時的ファイルを作成する
		File tempFile = File.createTempFile(fileName, filenameExtension);
		multipartFile.transferTo(tempFile);

		// CSVファイルからデータを取得する
		List<String> csvList = Files.readAllLines(tempFile.toPath(), Charset.forName("Shift_JIS"));

		// CSVファイルにタイトルだけの場合
		if (csvList.size() <= 1) {
			serverErrorList.add("NKS0014E", "取込内容", null);
			throw new ValidationException(serverErrorList);
		}

		// CSVファイルのタイトルを削除する
		csvList.remove(0);

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
	 * @param dwhMasterMap CSVファイル
	 * @param csvTwoDimensionalList 共同購入商品マスタデータ
	 * @return DBへの登録データ
	 */
	private List<Map<String, Object>> editCsvList(Map<String, Map<String, Object>> dwhMasterMap,
			List<List<String>> csvTwoDimensionalList) {

		// ログインユーザーID
		String userId = StringUtils.trimAllWhitespace(tantoshaInfoUtil.getTansCd());

		// システム時間を取得する
		Timestamp currentDate = DateUtil.getCurrentTimestamp();

		List<Map<String, Object>> csvEditList = new ArrayList<Map<String, Object>>();
		Map<String, Object> csvRowMap;
		for (List<String> csvRowDataList : csvTwoDimensionalList) {

			// 共同購入商品コードを取得する
			String cqkikano = csvRowDataList.get(3);

			// 共同購入商品コードより、共同購入商品マスタを取得する
			Map<String, Object> dwhMaster = Optional.ofNullable(dwhMasterMap.get(cqkikano)).orElse(new HashMap<>());

			csvRowMap = new HashMap<String, Object>();
			// ユーザーコード
			csvRowMap.put("USERID", userId);
			// ＣＱ企画番号
			csvRowMap.put("CQKIKANO", csvRowDataList.get(2));
			// 共同購入商品コード
			csvRowMap.put("KYSHINCD", cqkikano);
			// ＣＱ商品コード
			csvRowMap.put("CQSHINCD", csvRowDataList.get(4));
			// ＪＡＮコード
			csvRowMap.put("JANCD", csvRowDataList.get(5));

			// 企画小枝番(KIKAEDA)取得
			String KIKAEDA = csvRowDataList.get(37).trim();
			// 定催区分
			csvRowMap.put("TESAIKB", KIKAEDA.length() > 10 ? TorikmTargetKbnConst.torikmTargetMap.get("催事")
					: TorikmTargetKbnConst.torikmTargetMap.get("定番"));
			// 部門コード
			csvRowMap.put("BUMONCD", dwhMaster.get("BUMONCD"));
			// 大分類
			csvRowMap.put("DAIBNCD", dwhMaster.get("DAIBNCD"));
			// 中分類
			csvRowMap.put("TYUBNCD", dwhMaster.get("TYUBNCD"));
			// 小分類
			csvRowMap.put("SYOBNCD", dwhMaster.get("SYOBNCD"));
			// 集品区分
			csvRowMap.put("SYUHINKB", dwhMaster.get("SYUHINKB"));
			// 商品名漢字
			csvRowMap.put("SHINKJ", csvRowDataList.get(6));
			// 規格名漢字
			csvRowMap.put("KIKAKKJ", csvRowDataList.get(7));
			// 入数
			csvRowMap.put("IRISU", csvRowDataList.get(8));
			// 週・特価
			csvRowMap.put("KIKAKB", csvRowDataList.get(14));
			// ブランド区分
			csvRowMap.put("BRKB", dwhMaster.get("BRKB"));
			// 企画単協　エフ
			csvRowMap.put("EHU", csvRowDataList.get(26));
			// 企画単協　さが
			csvRowMap.put("SAGA", csvRowDataList.get(27));
			// 企画単協　ララ
			csvRowMap.put("RARA", csvRowDataList.get(28));
			// 企画単協　おおいた
			csvRowMap.put("OITA", csvRowDataList.get(29));
			// 企画単協　水光社
			csvRowMap.put("SUIKOSYA", csvRowDataList.get(30));
			// 企画単協　みやざき
			csvRowMap.put("MIYAZAKI", csvRowDataList.get(31));
			// 企画単協　かごしま
			csvRowMap.put("KAGOSIMA", csvRowDataList.get(32));
			// 企画単協　おきなわ
			csvRowMap.put("OKINAWA", csvRowDataList.get(33));
			// 仕入先コード
			csvRowMap.put("SIRECD", csvRowDataList.get(36));
			// 二次帳合先コード
			csvRowMap.put("HATUCD", csvRowDataList.get(9));
			// 産地名
			csvRowMap.put("SANTIKJ", csvRowDataList.get(11));
			// 企画小枝番
			csvRowMap.put("KIKAEDA", csvRowDataList.get(37));
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
	 * 取込対象区分チェック
	 * @param torikmTargetKbn 取込対象区分
	 */
	private void torikmTargetKbnCheck(String torikmTargetKbn) {

		// 取込対象区分が全ての場合
		if (TorikmTargetKbnConst.all.equals(torikmTargetKbn)) {
			return;
		}

		// ログインユーザーID
		String userId = StringUtils.trimAllWhitespace(tantoshaInfoUtil.getTansCd());

		// 企画小枝番リストを取得する
		List<String> kikaEdaList = mapper.getKikaEdaListByPnks010(userId);

		ServerErrorList serverErrorList = new ServerErrorList();
		for (String kikaEda : kikaEdaList) {

			// 定番の場合 且つ 企画小枝番 > 10 の場合
			if (TorikmTargetKbnConst.teiban.equals(torikmTargetKbn) && Integer.valueOf(kikaEda) > 10) {

				// PEACEデータ取込ワークを削除する
				mapper.deletePeaceDataWorkByPnks010(userId);

				// 業務エラー例外を返却して処理を終了する
				serverErrorList.add("NKS0007E", "「定番」", null);
				throw new ValidationException(serverErrorList);

			}
			// 催事の場合 且つ 企画小枝番 <= 10 の場合
			else if (TorikmTargetKbnConst.saiji.equals(torikmTargetKbn) && Integer.valueOf(kikaEda) <= 10) {

				// PEACEデータ取込ワークを削除する
				mapper.deletePeaceDataWorkByPnks010(userId);

				// 業務エラー例外を返却して処理を終了する
				serverErrorList.add("NKS0007E", "「催事」", null);
				throw new ValidationException(serverErrorList);
			}
		}

	}
}
