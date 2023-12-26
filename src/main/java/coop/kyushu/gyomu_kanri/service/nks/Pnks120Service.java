//================================================================================
// ID: PNKS120
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks120Service
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/02 | ＡＭＣ               | 新規作成
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

import coop.kyushu.common.entity.ServerErrorEntity;
import coop.kyushu.common.exception.ValidationException;
import coop.kyushu.common.util.BeanUtil;
import coop.kyushu.common.util.CsvUtil;
import coop.kyushu.common.util.FileDownloadUtil;
import coop.kyushu.common.util.TantoshaInfoUtil;
import coop.kyushu.gyomu_kanri.entity.nks.Pnks120CntrHikiwtsSEntity;
import coop.kyushu.gyomu_kanri.entity.nks.Pnks120CntrKakoShijiEntity;
import coop.kyushu.gyomu_kanri.entity.nks.Pnks120CntrSireKeijEntity;
import coop.kyushu.gyomu_kanri.form.nks.Pnks120Form;
import coop.kyushu.gyomu_kanri.mapper.nks.Pnks120Mapper;

/**
 * <pre>
 * 【農産企画支援】各種データ出力サービス
 * </pre>
 */
@Service
public class Pnks120Service {

	/* 各種データ出力マッパー */
	@Autowired
	private Pnks120Mapper pnks120Mapper;

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
	private final static String CHOHYO_ID = "PNKS120";

	/**
	 * 各種データ出力処理
	 * @param form 入力内容
	 */
	public void csvOutput(Pnks120Form form) throws Exception {

		// 出力用csvデータ宣言です
		List<List<String>> csvItemList = new ArrayList<>();

		// CSV名
		String csvNm = "";

		switch (form.getOutputData()) {
		case "1":

			// 出力データが"加工指示"の場合
			List<Pnks120CntrKakoShijiEntity> kakoShijiData = this.getKakoShijiData(form);

			// 取得データをList<List<String>>に変換します
			csvItemList = this.handleListEntityData(kakoShijiData);
			csvNm = "加工指示データ_";

			break;
		case "2":

			// 出力データが"引渡指示"の場合
			List<Pnks120CntrHikiwtsSEntity> hikiwtsData = this.getHikiwtsData(form);

			// 取得データをList<List<String>>に変換します
			csvItemList = this.handleListEntityData(hikiwtsData);
			csvNm = "引渡指示データ_";

			break;
		case "3":

			// 出力データが"仕入計上"の場合
			List<Pnks120CntrSireKeijEntity> sireKeijoData = this.getSireKeijoData(form);

			// 取得データをList<List<String>>に変換します
			csvItemList = this.handleListEntityData(sireKeijoData);
			csvNm = "仕入計上データ_";

			break;
		}

		// CSVファイルを作成する
		String loginUserId = StringUtils.trim(tantoshaInfoUtil.getTansCd());
		String serverFilePath = CsvUtil.createCsv(tempCsvFilePath, loginUserId, CHOHYO_ID, csvItemList,
				new String("SJIS"));

		// システム時間を取得する
		String systemDataStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

		// CSV名を作成する
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(csvNm);
		stringBuffer.append(systemDataStr);
		stringBuffer.append(".csv");

		// CSVファイルをダウンロードする
		fileDownloadUtil.download(serverFilePath, stringBuffer.toString());
	}

	/**
	 * 出力データが"加工指示"の場合, 加工指示データを出力します。
	 * @param form 入力内容
	 * @return 加工指示データ
	 */
	private List<Pnks120CntrKakoShijiEntity> getKakoShijiData(Pnks120Form form) {

		if (ObjectUtils.isEmpty(form.getJuchuDayStart()) && ObjectUtils.isEmpty(form.getJuchuDayEnd())
				&& ObjectUtils.isEmpty(form.getScDelvDayStart()) && ObjectUtils.isEmpty(form.getScDelvDayEnd())) {
			// 入力用Form.受注日開始、入力用Form.受注日終了、入力用Form.SC納品日開始と入力用Form.SC納品日終了が全て未入力の場合
			throw new ValidationException(new ServerErrorEntity("NKS0004E"));
		}

		Map<String, Object> kakoShijiParam = BeanUtil.toMap(form);
		// 加工指示データ取得
		List<Pnks120CntrKakoShijiEntity> kakoShijiData = pnks120Mapper
				.getCntrKakoShijiDataByPnks120(kakoShijiParam);

		// データが取得できなかった場合
		if (kakoShijiData.size() == 0) {
			throw new ValidationException(new ServerErrorEntity("NKS0009E", List.of("加工指示")));
		}

		return kakoShijiData;
	}

	/**
	 * 出力データが"引渡指示"の場合, 引渡指示データを出力します。
	 * @param form 入力内容
	 * @return 引渡指示データ
	 */
	private List<Pnks120CntrHikiwtsSEntity> getHikiwtsData(Pnks120Form form) {

		if (ObjectUtils.isEmpty(form.getJuchuDayStart()) && ObjectUtils.isEmpty(form.getJuchuDayEnd())
				&& ObjectUtils.isEmpty(form.getScDelvDayStart()) && ObjectUtils.isEmpty(form.getScDelvDayEnd())) {
			// 入力用Form.受注日開始、入力用Form.受注日終了、入力用Form.SC納品日開始と入力用Form.SC納品日終了が全て未入力の場合
			throw new ValidationException(new ServerErrorEntity("NKS0004E"));
		}

		// 引渡指示データ取得
		Map<String, Object> hikiwtsParam = BeanUtil.toMap(form);
		List<Pnks120CntrHikiwtsSEntity> hikiwtsData = pnks120Mapper.getCntrHikiwtsShijiDataByPnks120(hikiwtsParam);

		// データが取得できなかった場合
		if (hikiwtsData.size() == 0) {
			throw new ValidationException(new ServerErrorEntity("NKS0009E", List.of("引渡指示")));
		}

		return hikiwtsData;
	}

	/**
	 * 出力データが"仕入計上"の場合, 引渡指示データを出力します。
	 * @param form 入力内容
	 * @return 引渡指示データ
	 */
	private List<Pnks120CntrSireKeijEntity> getSireKeijoData(Pnks120Form form) {

		if (ObjectUtils.isEmpty(form.getNonyuSiteiYearMonthDayStart())
				&& ObjectUtils.isEmpty(form.getNonyuSiteiYearMonthDayEnd())) {
			//納入指定年月日開始と納入指定年月日終了が全て未入力の場合
			throw new ValidationException(new ServerErrorEntity("NKS0005E", List.of("納入指定年月日")));
		}

		// 仕入計上データ取得
		Map<String, Object> sireKeijoParam = BeanUtil.toMap(form);
		List<Pnks120CntrSireKeijEntity> sireKeijoData = pnks120Mapper.getCntrSireKeijoDataByPnks120(sireKeijoParam);

		// データが取得できなかった場合
		if (sireKeijoData.size() == 0) {
			throw new ValidationException(new ServerErrorEntity("NKS0009E", List.of("仕入計上")));
		}

		return sireKeijoData;
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
