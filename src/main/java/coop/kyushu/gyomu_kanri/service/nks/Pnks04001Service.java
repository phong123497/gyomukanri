//================================================================================
// ID: PNKS040
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks04001Service
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/13 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.service.nks;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microsoft.sqlserver.jdbc.StringUtils;

import coop.kyushu.common.entity.ServerErrorEntity;
import coop.kyushu.common.exception.ValidationException;
import coop.kyushu.common.util.BeanUtil;
import coop.kyushu.common.util.DateUtil;
import coop.kyushu.common.util.TantoshaInfoUtil;
import coop.kyushu.gyomu_kanri.entity.nks.Pnks04001Item;
import coop.kyushu.gyomu_kanri.entity.nks.Pnks040Entity;
import coop.kyushu.gyomu_kanri.form.nks.Pnks040Form;
import coop.kyushu.gyomu_kanri.mapper.nks.Pnks040Mapper;

/**
 * <pre>
 * 【農産企画支援】企画案内書印刷サービス
 * </pre>
 */
@Service
public class Pnks04001Service {

	/** 企画案内書印刷マッパー */
	@Autowired
	private Pnks040Mapper pnks040Mapper;

	/** 利用者情報管理 */
	@Autowired
	private TantoshaInfoUtil tantoshaInfoUtil;

	/**
	 * 【農産企画支援】企画案内書印刷の印刷データ処理
	 * @param form 入力内容
	 * @return 印刷データ
	 */
	@Transactional
	public List<Pnks04001Item> pnks04001Print(Pnks040Form form) {

		// システム日時を取得する
		Timestamp nowDate = DateUtil.getCurrentTimestamp();

		// 企画回マスタ存在チェック
		int checkNum = pnks040Mapper.getCqkikanoByPnks040(form.getCqProjNoStart());
		if (checkNum == 0) {
			throw new ValidationException(new ServerErrorEntity("NKS0009E", List.of("企画")));
		}

		// 管理マスタを更新する
		Map<String, Object> formParam = BeanUtil.toMap(form);
		formParam.put("usr", tantoshaInfoUtil.getTansCd());
		formParam.put("prgrm", "PNKS040");
		formParam.put("nchj", nowDate);
		// 更新データ
		pnks040Mapper.updateKanriMstByPnks040(formParam);

		// 企画案内情報マスタを更新する
		pnks040Mapper.updateProjAnnaiInfByPnks040(formParam);

		// 企画案内書印刷用データ取得する
		List<Pnks04001Item> backItems = pnks040Mapper.getProjAnnaiInfByPnks04001(formParam);

		if (backItems.size() == 0) {
			throw new ValidationException(new ServerErrorEntity("NKS0009E", List.of("農産企画商品情報マスタ")));
		}

		// 時間書式設定
		DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		// データ編集
		

		// ヘッダー名
		String printKj = "";
		String setCqKikanoTo = "";
		String setKikaymdf = "";
		String setKikaymdt = "";
		String setKakakKigen = "";
		
		String setKikayminterval = "";
		String setSekiKanohide = "";
		String setKakakKigenhide = "";
		String setEnd = "";
		String setInevitable = "";

		// ヘッダ部時間項目編集
		// 入力用Form.帳票区分が価格決定依頼書(確定)の場合
		if ("2".equals(form.getPrtChohyo())) {

			// ヘッダー名を設定する
			printKj = "価格決定依頼書(確定)";
			

			if (form.getCqProjNoStart() != null && form.getCqProjNoStart() != "") {
				// 企画回マスタデータ取得
				Pnks040Entity kikanoData = pnks040Mapper.getProjAnnaiInfByPnks040(form.getCqProjNoStart());

				// 取得したデータを編集
				setCqKikanoTo = null;
				// 取得企画回マスタデータ.企画期間（自）が空白以外の場合
				if (!StringUtils.isEmpty(kikanoData.getProjKknStart())) {
					setKikaymdf = LocalDate.parse(kikanoData.getProjKknStart(), inputFormatter).format(outputFormatter);
				} else {
					setKikaymdf=null;
				}
				// 取得企画回マスタデータ.企画期間（至）が空白以外の場合
				if (!StringUtils.isEmpty(kikanoData.getProjKknEnd())) {
					setKikaymdt = LocalDate.parse(kikanoData.getProjKknEnd(), inputFormatter).format(outputFormatter);
				} else {
					setKikaymdt = null;
				}
				// 取得企画回マスタデータ.価格回答期限が空白以外の場合
				if (!StringUtils.isEmpty(kikanoData.getKakakuAnsKgn())) {
					setKakakKigen = LocalDate.parse(kikanoData.getKakakuAnsKgn(), inputFormatter).format(outputFormatter);
				} else {
					setKakakKigen=null;
				}
			}
			setKikayminterval =("("+setKikaymdf+" ～ "+setKikaymdt+")");
			setKakakKigenhide =(setKakakKigen);
			setEnd =("締切");
			setInevitable =("必着");
			setSekiKanohide = (backItems.get(0).getSekiKano().substring(4, 6));
			
			
		} else if ("1".equals(form.getPrtChohyo())) {
			// 入力用Form.帳票区分が企画案内書（計画）の場合

			// ヘッダー名を設定する
			printKj = "企画案内書（計画）";
			setKikaymdf=null;
			setKikaymdf = null;
			setKakakKigen=null;
			
			setKikayminterval =null;
			setKakakKigenhide =null;
			setEnd =null;
			setInevitable =null;
			setSekiKanohide = null;
		}
		// ヘッダ部その他項目編集
		// 企画案内書印刷用データを取得できる場合
		for (Pnks04001Item item : backItems) {  
		    item.setCqKikanoFrom(form.getCqProjNoStart());
		    item.setCqKikanoTo(form.getCqProjNoEnd());
		    item.setRenrakuJiko(form.getContactJiko());
		    item.setRenrakuKj(form.getNohinskNmHokubu());
		    item.setRenrakuTel(form.getNohinskTelHokubu());
		    item.setRenrakuFax(form.getNohinskFaxHokubu());
		    
		    item.setSekiKanoWeek(item.getSekiKano().substring(4, 6)+"月"+item.getSekiKano().substring(6, 7)+"週");
		    item.setSekiKano(item.getSekiKano().substring(4, 6));
		    item.setPrintKj(printKj);
		    item.setSystemTime(nowDate.toString());
		    item.setKakakKigen(setKakakKigen);
		    item.setKikaymdt(setKikaymdt);
		    item.setKikaymdf(setKikaymdf);
		    
		    item.setKikayminterval(setKikayminterval);
		    item.setKakakKigenhide(setKakakKigenhide);
		    item.setEnd(setEnd);
		    item.setInevitable(setInevitable);
		    
		    item.setSekiKanohide(setSekiKanohide);
		    
		}

		return backItems;
	}

}
