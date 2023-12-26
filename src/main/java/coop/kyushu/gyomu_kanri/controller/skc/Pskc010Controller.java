//================================================================================
//ID: PSKC010
//ProjectName: コープ九州事業連合様  ホストシステムリプレイス
//SystemName: 業務管理
//ClassName: Pskc010Controller
//
//<<Modification History>>
//Version  | Date       | Updated By           | Content
//---------+------------+----------------------+---------------------------------
//01.00.00 | 2023/08/11 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.controller.skc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import coop.kyushu.gyomu_kanri.entity.skc.Pskc010ListItem;
import coop.kyushu.gyomu_kanri.form.skc.Pskc010Form;
import coop.kyushu.gyomu_kanri.service.skc.Pskc010Service;

/**
 * <pre>
 * 【惣菜加工センター】値付実績データ取込画面
 * </pre>
 */
@RestController
@RequestMapping("skc/pskc010")
public class Pskc010Controller {

	@Autowired
	Pskc010Service service;

	/**
	 * 値付実績データ取込画面の取込処理
	 * 
	 * @param form 入力内容
	 * @return 取込結果リスト
	 * @throws Exception 
	 */
	@RequestMapping(value = "/readcsv", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<Pskc010ListItem> readcsv(@Validated Pskc010Form form, BindingResult bindingResult) throws Exception {

		if (bindingResult.hasErrors()) {
			throw new RuntimeException();
		}

		return service.readcsv(form);
	}

}
