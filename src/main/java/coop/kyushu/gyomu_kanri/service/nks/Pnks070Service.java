//================================================================================
// ID: Pnks070
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks070Service
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/25 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.service.nks;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import coop.kyushu.common.entity.ServerErrorList;
import coop.kyushu.common.exception.ValidationException;
import coop.kyushu.common.util.BeanUtil;
import coop.kyushu.common.util.CsvUtil;
import coop.kyushu.common.util.FileDownloadUtil;
import coop.kyushu.common.util.TantoshaInfoUtil;
import coop.kyushu.gyomu_kanri.entity.nks.Pnks070NousanProjInfShinList;
import coop.kyushu.gyomu_kanri.form.nks.Pnks070Form;
import coop.kyushu.gyomu_kanri.mapper.nks.Pnks070Mapper;

/**
 * <pre>
 * 【農産企画支援】価格取込用データ出力サービス
 * </pre>
 */
@Service
public class Pnks070Service {

	/* 一時的にCSVファイルの保存パス */
	@Value("${GYOMU_REPORT_TEMP_PATH}")
	private String tempCsvFilePath;

	@Autowired
	private Pnks070Mapper mapper;

	/* 担当者情報 */
	@Autowired
	private TantoshaInfoUtil tantoshaInfoUtil;

	/* ファイルをダウンロードする共通部品 */
	@Autowired
	private FileDownloadUtil fileDownloadUtil;

	/* 空白文字列 */
	private final static String BLANK_MARK = "";

	/* 帳票ID */
	private final static String CHOHYO_ID = "PNKS070";

	/* CSVファイルタイトル */
	private final static List<String> CSV_FILE_TITLE = List.of("会員法人コード", "CQ企画番号", "共同購入商品コード", "コープ九州商品コード",
			"JANコード1_現", "CQ分類コード", "表示商品名", "表示規格", "価格表記種別", "今回原単価", "今回売単価", "今回本体価格", "今回組価税込", "計画数", "ブランド区分");

	/**
	 * 価格取込用データ出力画面の産企画商品禁則価格チェック
	 * @param form 入力内容
	 * @return 産企画商品件数
	 */
	public Integer csvOutputCheck(Pnks070Form form) {
		// CSVデータを検索する
		Map<String, Object> mapParams = BeanUtil.toMap(form);
		return mapper.getNousanProjInfShinByPnks070(mapParams);
	}

	/**
	 * 価格取込用データ出力画面のCSV出力処理
	 * @param form 入力内容
	 * @return 初期データ
	 * @throws IllegalAccessException 
	 */
	public void csvOutput(Pnks070Form form) throws IllegalAccessException {
		// CSVデータを検索する
		Map<String, Object> mapParams = BeanUtil.toMap(form);

		List<Pnks070NousanProjInfShinList> csvDataList = mapper.getNousanProjInfShinDataByPnks070(mapParams);

		// CSVデータがない場合
		if (ObjectUtils.isEmpty(csvDataList)) {
			ServerErrorList errorList = new ServerErrorList();
			errorList.add("NKS0009E", "該当", null);
			throw new ValidationException(errorList);
		}

		// List<Entity>からList<List<String>>への変換
		List<List<String>> csvItemList = this.handleListEntityData(csvDataList);

		// CSVタイトルを追加する
		csvItemList.add(0, CSV_FILE_TITLE);

		// CSVファイルを作成する
		String loginUserId = StringUtils.trimAllWhitespace(tantoshaInfoUtil.getTansCd());
		String serverFilePath = CsvUtil.createCsv(tempCsvFilePath, loginUserId, CHOHYO_ID, csvItemList,
				new String("SJIS"));

		// システム時間を取得する
		String systemDataStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

		// CSV名を作成する
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("価格取込用データ_");
		stringBuffer.append(systemDataStr);
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
}
