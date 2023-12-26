//================================================================================
// ID: PCSK020
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pcsk020Service
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/18 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.service.csk;

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

import com.fasterxml.jackson.annotation.JsonIgnore;

import coop.kyushu.common.entity.ServerErrorEntity;
import coop.kyushu.common.exception.ValidationException;
import coop.kyushu.common.util.BeanUtil;
import coop.kyushu.common.util.CsvUtil;
import coop.kyushu.common.util.FileDownloadUtil;
import coop.kyushu.common.util.TantoshaInfoUtil;
import coop.kyushu.gyomu_kanri.entity.csk.Pcsk020FurikaeDempyoItem;
import coop.kyushu.gyomu_kanri.entity.csk.Pcsk020HaikiDempyoItem;
import coop.kyushu.gyomu_kanri.entity.csk.Pcsk020VarDempyoApItem;
import coop.kyushu.gyomu_kanri.entity.csk.Pcsk020VarDempyoCoreItem;
import coop.kyushu.gyomu_kanri.entity.csk.Pcsk020ZkChsDempyoItem;
import coop.kyushu.gyomu_kanri.form.csk.Pcsk020Form;
import coop.kyushu.gyomu_kanri.mapper.csk.Pcsk020Mapper;

/**
 * <pre>
 * 【コープ九州商管】CSV出力サービス
 * </pre>
 */
@Service
public class Pcsk020Service {

	/* 各種データ出力マッパー */
	@Autowired
	private Pcsk020Mapper pcsk020Mapper;

	/* ファイルをダウンロードする共通部品 */
	@Autowired
	private FileDownloadUtil fileDownloadUtil;

	/* 担当者情報 */
	@Autowired
	private TantoshaInfoUtil tantoshaInfoUtil;

	/* 一時的にCSVファイルの保存パス */
	@Value("${GYOMU_REPORT_TEMP_PATH}")
	private String tempCsvFilePath;

	/* 空白文字列 */
	private final static String BLANK_MARK = "";

	/* 帳票ID */
	private final static String CHOHYO_ID = "PCSK020";

	/* 在庫調整タイトル */
	private final static List<String> ZK_CHS_DEMPYO_TITLE = List
			.of("伝票番号", "行番号", "対象月度", "センタコード", "事業所区分", "無店舗大分類", "商品コード", "最終仕入価", "月末在庫数量", "月末在庫金額", "積送中在庫数量",
					"積送中在庫金額", "登録日", "登録時間", "登録者ＩＤ");

	/* 振替伝票タイトル */
	private final static List<String> FURIKAE_DEMPYO_TITLE = List
			.of("計上年月", "伝票番号", "行番号", "伝票日付", "出庫側センタコード", "入庫側センタコード", "商品コード", "数量", "金額", "発生元区分", "登録日", "登録時間",
					"登録者ＩＤ");

	/* 在庫調整タイトル */
	private final static List<String> HAIKI_DEMPYO_TITLE = List
			.of("計上年月", "伝票番号", "行番号", "伝票日付", "センタコード", "商品コード", "数量", "金額", "発生元区分", "登録日", "登録時間", "登録者ＩＤ");

	/* 諸伝票（CORE分）タイトル */
	private final static List<String> VAR_DEMPYO_CORE_TITLE = List.of(
			"計上年月", "粗利表分類", "伝票日付", "伝票番号", "行番号", "借方科目コード", "借方補助コード", "借方部門コード", "借方取引先区分", "借方取引先コード", "借方機能コード１",
			"借方機能コード２", "借方機能コード３", "借方機能コード４", "借方税処理コード", "借方税入力区分", "借方仕訳金額税抜", "借方税額", "借方仕訳金額税込", "借方摘要１", "借方摘要２",
			"貸方科目コード", "貸方補助コード", "貸方部門コード", "貸方取引先区分", "貸方取引先コード", "貸方機能コード１", "貸方機能コード２", "貸方機能コード３", "貸方機能コード４",
			"貸方税処理コード", "貸方税入力区分", "貸方仕訳金額税抜", "貸方税額", "貸方仕訳金額税込", "貸方適用１", "貸方適用２", "登録日", "登録時間", "登録者ＩＤ");

	/* 諸伝票（AP分）タイトル */
	private final static List<String> VAR_DEMPYO_AP_TITLE = List.of("計上年月", "粗利表分類", "伝票日付", "伝票区分", "伝票番号", "行番号",
			"貸借反転区分", "借方科目コード", "借方補助コード", "借方部門コード", "借方取引先区分", "借方取引先コード", "借方機能コード１", "借方機能コード２", "借方機能コード３",
			"借方機能コード４", "借方税処理コード", "借方税入力区分", "借方仕訳金額税抜", "借方税額", "借方仕訳金額税込", "借方摘要", "貸方科目コード", "貸方補助コード", "貸方部門コード",
			"貸方取引先区分", "貸方取引先コード", "貸方機能コード１", "貸方機能コード２", "貸方機能コード３", "貸方機能コード４", "貸方税処理コード", "貸方税入力区分", "貸方仕訳金額税抜",
			"貸方税額", "貸方仕訳金額税込", "貸方適用", "登録日", "登録時間", "登録者ＩＤ");

	/**
	 * PCSK020_【コープ九州商管】のCSV出力処理
	 * @param form 入力内容
	 * @throws Exception 
	 */
	public void search(Pcsk020Form form) throws Exception {

		// 出力用csvデータ宣言です
		List<List<String>> csvItemList = new ArrayList<>();

		// CSV名
		String csvNm = "";

		Map<String, Object> formData = BeanUtil.toMap(form);

		switch (form.getFileSelect()) {
		case "Z":
			// ファイル選択が"在庫調整データ"の場合
			// 在庫調整データ取得
			List<Pcsk020ZkChsDempyoItem> zkChsDempyoItems = pcsk020Mapper.getPcsk020ZkChsDempyoItem(formData);

			// データが取得できなかった場合
			if (zkChsDempyoItems.size() == 0) {
				throw new ValidationException(new ServerErrorEntity("CSK0002E", List.of("伝票データ")));
			}

			// CSVタイトルを追加する
			csvItemList.add(0, ZK_CHS_DEMPYO_TITLE);

			// CSVデータを追加する
			csvItemList.addAll(this.handleListEntityData(zkChsDempyoItems));

			// CSV名
			csvNm = "在庫調整伝票_";
			break;
		case "F":
			// ファイル選択が"振替伝票照会"の場合
			// 振替伝票データ取得
			List<Pcsk020FurikaeDempyoItem> furikaeDempyoItems = pcsk020Mapper.getPcsk020FurikaeDempyoItem(formData);

			// データが取得できなかった場合
			if (furikaeDempyoItems.size() == 0) {
				throw new ValidationException(new ServerErrorEntity("CSK0002E", List.of("伝票データ")));
			}

			// CSVタイトルを追加する
			csvItemList.add(0, FURIKAE_DEMPYO_TITLE);

			// CSVデータを追加する
			csvItemList.addAll(this.handleListEntityData(furikaeDempyoItems));

			// CSV名
			csvNm = "振替伝票_";
			break;
		case "H":
			// ファイル選択が"廃棄伝票照会"の場合
			// 廃棄伝票データ取得
			List<Pcsk020HaikiDempyoItem> haikiDempyoItems = pcsk020Mapper.getPcsk020HaikiDempyoItem(formData);

			// データが取得できなかった場合
			if (haikiDempyoItems.size() == 0) {
				throw new ValidationException(new ServerErrorEntity("CSK0002E", List.of("伝票データ")));
			}

			// CSVタイトルを追加する
			csvItemList.add(0, HAIKI_DEMPYO_TITLE);

			// CSVデータを追加する
			csvItemList.addAll(this.handleListEntityData(haikiDempyoItems));

			// CSV名
			csvNm = "廃棄伝票_";
			break;
		case "S":
			// ファイル選択が"諸伝票照会"の場合
			if ("C".equals(form.getVarDempyoKbn())) {
				// 諸伝票（CORE分）データ取得
				List<Pcsk020VarDempyoCoreItem> varDempyoCoreItems = pcsk020Mapper.getPcsk020VarDempyoCoreItem(formData);

				// データが取得できなかった場合
				if (varDempyoCoreItems.size() == 0) {
					throw new ValidationException(new ServerErrorEntity("CSK0002E", List.of("伝票データ")));
				}

				// CSVタイトルを追加する
				csvItemList.add(0, VAR_DEMPYO_CORE_TITLE);

				// CSVデータを追加する
				csvItemList.addAll(this.handleListEntityData(varDempyoCoreItems));

				// CSV名
				csvNm = "諸伝票_会計CORE分_";
			} else if ("A".equals(form.getVarDempyoKbn())) {
				// 諸伝票（AP分）データ取得
				List<Pcsk020VarDempyoApItem> varDempyoApItems = pcsk020Mapper.getPcsk020VarDempyoApItem(formData);

				// データが取得できなかった場合
				if (varDempyoApItems.size() == 0) {
					throw new ValidationException(new ServerErrorEntity("CSK0002E", List.of("伝票データ")));
				}

				// CSVタイトルを追加する
				csvItemList.add(0, VAR_DEMPYO_AP_TITLE);

				// CSVデータを追加する
				csvItemList.addAll(this.handleListEntityData(varDempyoApItems));

				// CSV名
				csvNm = "諸伝票_会計AP分_";
			}
			break;
		}

		// CSVファイルを作成する
		String loginUserId = StringUtils.trim(tantoshaInfoUtil.getTansCd());
		String serverFilePath = CsvUtil.createCsv(tempCsvFilePath, loginUserId, CHOHYO_ID, csvItemList,
				new String("SJIS"));

		// システム時間を取得する
		String systemDataStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));

		// CSV名を作成する
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(csvNm);
		stringBuffer.append(loginUserId + "_");
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
