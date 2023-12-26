//================================================================================
// ID: PNKS050
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks050Service
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/26 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.service.nks;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coop.kyushu.common.entity.ServerErrorList;
import coop.kyushu.common.exception.ValidationException;
import coop.kyushu.common.util.BeanUtil;
import coop.kyushu.gyomu_kanri.entity.nks.Pnks05001ListItem;
import coop.kyushu.gyomu_kanri.form.nks.Pnks050Form;
import coop.kyushu.gyomu_kanri.mapper.nks.Pnks050Mapper;

/**
 * <pre>
 * 【農産企画支援】価格表印刷サービス
 * </pre>
 */
@Service
public class Pnks050Service {

	@Autowired
	private Pnks050Mapper mapper;
	private final static Integer CYU0009E_INT_BLANK_SU = 5;

	/**
	 * 価格表印刷画面の印刷処理
	 * @param form 入力内容
	 * @return 価格表印刷用のデータ
	 */
	public List<Pnks05001ListItem> pnks05001Print(Pnks050Form form) {
		Map<String, Object> param = BeanUtil.toMap(form);
		// 金額表示あり == flase
		if (StringUtil.isBlank(form.getGakuHyojiAri())) {
			param.put("tankflg", 0);
		} else {
			param.put("tankflg", 1);
		}
		// 企画案内書印刷用のデータ取得する
		Integer count = mapper.getCommodityInfoByPnks050(param);
		if (count == 0) {
			// 業務管理チェック処理
			ServerErrorList errorList = new ServerErrorList();
			String[] msgParams = { "農産企画商品情報マスタ" };
			errorList.add("NKS0009E", msgParams, null);
			throw new ValidationException(errorList);
		}
		List<Pnks05001ListItem> reslutist = mapper.getPrintInfoByPnks05001(param);
		if (form.getGakuHyojiAri().isEmpty()) {
			for (Pnks05001ListItem item : reslutist) {  
				item.setGentan(null);
				item.setBaitan(null);
				item.setZenukumi(null);
				item.setKumitan(null);
				item.setGpr(null); 
			}
		};
		
		Map<String, List<Pnks05001ListItem>> blanlRowist = reslutist.stream().collect(
				Collectors.groupingBy(h -> h.getCqkikano()
						+ "," + h.getSekikanoY()
						+ "," + h.getSekikanoM()
						+ "," + h.getSekikanoT()
						+ "," + h.getSeicd()
						+ "," + h.getBumonCd()
						+ "," + h.getBumonkj()));
		for (int i = 0; i < CYU0009E_INT_BLANK_SU; i++) {
			for (String key : blanlRowist.keySet()) {
				Pnks05001ListItem item = new Pnks05001ListItem();
				String[] keys = key.split(",");
				item.setCqkikano(keys[0]);
				item.setSekikanoY(keys[1]);
				item.setSekikanoM(keys[2]);
				item.setSekikanoT(keys[3]);
				item.setSeicd(keys[4]);
				item.setBumonCd(keys[5]);
				item.setBumonkj(keys[6]);
				item.setKyshinCd("");
				item.setCqshinCd("");
				item.setJanCd("");
				item.setKikakkj("");
				item.setShinkj("");
				item.setKakoKbn("");
				item.setGentan(null);
				item.setBaitan(null);
				item.setZenukumi(null);
				item.setKumitan(null);
				item.setGpr(null);
				item.setKikakb("");
				item.setMakerCd("9999");
				item.setMakerkj("");
				item.setSyuhinkb("");
				item.setSantiCd("");
				item.setSantikj("");
				item.setIrisu(null);
				item.setEhu("");
				item.setSaga("");
				item.setRara("");
				item.setOita("");
				item.setSuikosya("");
				item.setMiyazaki("");
				item.setKagosima("");
				item.setOkinawa("");
				item.setTankflg("0");
				item.setBiko("");
				
				
				reslutist.add(item);
			}
		}
		return reslutist;

	}
}
