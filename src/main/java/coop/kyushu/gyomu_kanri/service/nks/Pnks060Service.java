//================================================================================
// ID: PNKS060
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks060Service
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/06 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.service.nks;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
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
import coop.kyushu.gyomu_kanri.entity.nks.Pnks060ListItem;
import coop.kyushu.gyomu_kanri.form.nks.Pnks060Form;
import coop.kyushu.gyomu_kanri.mapper.nks.Pnks060Mapper;

/**
 * <pre>
 * 【農産企画支援】シミュレーション用データ出力サービス
 * </pre>
 */
@Service
public class Pnks060Service {

	/* 一時的にCSVファイルの保存パス */
	@Value("${GYOMU_REPORT_TEMP_PATH}")
	private String tempCsvFilePath;

	@Autowired
	private Pnks060Mapper mapper;

	/* 担当者情報 */
	@Autowired
	private TantoshaInfoUtil tantoshaInfoUtil;

	/* ファイルをダウンロードする共通部品 */
	@Autowired
	private FileDownloadUtil fileDownloadUtil;

	/* 空白文字列 */
	private final static String BLANK_MARK = "";

	/* 半角スペース文字列 */
	private final static String SPACE_BLANK_MARK = " ";

	/* 帳票ID */
	private final static String CHOHYO_ID = "PNKS060";

	/* CSVファイルタイトル */
	private final static List<String> CSV_FILE_TITLE = List.of(
			"ＣＱ企画番号", "共同購入商品コード", "バッチＮｏ", "会員生協コード", "コープ九州商品コード", "ＪＡＮコード", "商品名", "規格", "メーカーコード", "メーカー名", "産地",
			"等階級", "税抜組合員単価", "税込組合員単価", "コープ九州供給売単価", "コープ九州仕入原単価", "原料単価", "加工単価", "資材単価", "委託単価", "計画点数", "大分類",
			"中分類", "小分類", "集品区分", "特価フラグ", "納品時容器");

	/* 
	 * NULLの場合は半角スペース1個に変換するフィールド名
	 * makerNm --> メーカー名
	 * santiNm --> 産地
	 * touClassNm --> 等階級
	 */
	private final static List<String> NULL_FIELD_HANDLE_LIST = List.of("makerNm", "santiNm", "touClassNm");

	/**
	 * シミュレーション用データの出力処理
	 * @param form 入力内容
	 * @throws Exception
	 */
	public void csvOutput(Pnks060Form form) throws Exception {

		// 業務チェックを行う
		this.businessCheck(form);

		// 企画番号開始と終了の0を除く処理
		if (!BLANK_MARK.equals(StringUtils.trim(form.getProjNoStart()))) {
			form.setProjNoStart(Integer.valueOf(form.getProjNoStart()).toString());
		}

		if (!BLANK_MARK.equals(StringUtils.trim(form.getProjNoEnd()))) {
			form.setProjNoEnd(Integer.valueOf(form.getProjNoEnd()).toString());
		}

		// CSVデータを検索する
		Map<String, Object> mapParams = BeanUtil.toMap(form);
		List<Pnks060ListItem> csvDataList = mapper.getSimulationDataByPnks060(mapParams);

		// 0の補足をする
		for (Pnks060ListItem pnks060Item : csvDataList) {
			// ＣＱ企画番号
			pnks060Item.setCqProjNo(StringUtils.leftPad(StringUtils.trim(pnks060Item.getCqProjNo()), 3, '0'));
			// 会員生協コード
			pnks060Item.setKaiinSeikyCd(StringUtils.leftPad(StringUtils.trim(pnks060Item.getKaiinSeikyCd()), 4, '0'));
			// メーカーコード
			pnks060Item.setMakerCd(StringUtils.leftPad(StringUtils.trim(pnks060Item.getMakerCd()), 4, '0'));
			// 大分類
			pnks060Item.setDaiCatg(StringUtils.leftPad(StringUtils.trim(pnks060Item.getDaiCatg()), 2, '0'));
			// 中分類
			pnks060Item.setChuCatg(StringUtils.leftPad(StringUtils.trim(pnks060Item.getChuCatg()), 2, '0'));
			// 小分類
			pnks060Item.setShoCatg(StringUtils.leftPad(StringUtils.trim(pnks060Item.getShoCatg()), 2, '0'));
		}

		// CSVデータがない場合
		if (ObjectUtils.isEmpty(csvDataList)) {
			ServerErrorList errorList = new ServerErrorList();
			errorList.add("NKS0009E", "マスタ", null);
			throw new ValidationException(errorList);
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
		String systemDataStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

		// CSV名を作成する
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("シミュレーション用データ_");
		stringBuffer.append(systemDataStr);
		stringBuffer.append(".csv");

		// CSVファイルをダウンロードする
		fileDownloadUtil.download(serverFilePath, stringBuffer.toString());
	}

	/**
	 * 業務チェックを行う
	 * @param form 入力内容
	 */
	private void businessCheck(Pnks060Form form) {

		// 企画番号開始と企画番号終了
		String projNoStart = form.getProjNoStart();
		String projNoEnd = form.getProjNoEnd();
		// エラーメッセージリスト
		ServerErrorList errorList = new ServerErrorList();

		// 企画番号開始と企画番号終了は全部空白の場合
		if (BLANK_MARK.equals(StringUtils.trim(projNoStart))
				&& BLANK_MARK.equals(StringUtils.trim(projNoEnd))) {
			errorList.add("CMS0013E", "企画番号", "projNoStart");
			throw new ValidationException(errorList);
		}
		// 企画番号開始＞企画番号終了の場合はエラーメッセージを表示する
		else if (!BLANK_MARK.equals(StringUtils.trim(projNoStart))
				&& !BLANK_MARK.equals(StringUtils.trim(projNoEnd))
				&& Integer.valueOf(projNoStart) > Integer.valueOf(projNoEnd)) {
			errorList.add("NKS0006E", "projNoStart");
			throw new ValidationException(errorList);
		}
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
				if (NULL_FIELD_HANDLE_LIST.contains(field.getName())) {
					valueList.add(Optional.ofNullable(field.get(data)).orElse(SPACE_BLANK_MARK).toString());
				} else {
					valueList.add(Optional.ofNullable(field.get(data)).orElse(BLANK_MARK).toString());
				}
			}
			strList.add(valueList);
		}

		return strList;
	}
}
