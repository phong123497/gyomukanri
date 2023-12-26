//================================================================================
// ID: PCSK050
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pcsk050Service
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/18 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.service.csk;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Service;

import coop.kyushu.common.util.DateUtil;
import coop.kyushu.gyomu_kanri.entity.csk.Pcsk050Entity;
import coop.kyushu.gyomu_kanri.form.csk.Pcsk050Form;

/**
 * <pre>
 * 【コープ九州商管】センター商管表出力サービス
 * </pre>
 */
@Service
public class Pcsk050Service {

	/**
	 * センター商管表出力画面の初期表示
	 * @param form 入力内容
	 * @return 初期データ
	 */
	public Pcsk050Entity getInitialData(Pcsk050Form form) {
		// 初期値を設定する
		Pcsk050Entity pcsk050Entity = new Pcsk050Entity();
		// 時間書式設定
		SimpleDateFormat yyyymmFormat = new SimpleDateFormat("yyyyMM");
		SimpleDateFormat yyyyFormat = new SimpleDateFormat("yyyy");
		Timestamp currentTimestamp = DateUtil.getCurrentTimestamp();
		// 種別ラジオボタン=「管理」を選択
		pcsk050Entity.setShubt(BigDecimal.ZERO);
		// 対象期間区分ラジオボタン=「単月指定」を選択
		pcsk050Entity.setTargetKkn(BigDecimal.ZERO);
		// 対象年月=現在日の年月（YYYY/MM）
		pcsk050Entity.setTargetYm(yyyymmFormat.format(currentTimestamp));
		// 対象年度=現在日の年（YYYY）
		pcsk050Entity.setTargetNendo(yyyyFormat.format(currentTimestamp));
		// 対象年月_FROM=現在日の年月（YYYY/MM）
		pcsk050Entity.setTargetYmFrom(yyyymmFormat.format(currentTimestamp));
		// 対象年月_TO=現在日の年月（YYYY/MM）
		pcsk050Entity.setTargetYmTo(yyyymmFormat.format(currentTimestamp));
		// 管理単位区分ラジオボタン=「コープ九州全体」を選択
		pcsk050Entity.setMngTani(BigDecimal.ZERO);
		// 事業所コード=空白
		pcsk050Entity.setCntrCd("");
		// 集計単位区分ラジオボタン=「単品別」を選択
		pcsk050Entity.setSkeiTani(BigDecimal.ZERO);
		return pcsk050Entity;
	}
}
