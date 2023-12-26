//================================================================================
// ID: PCSK010
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pcsk010Controller
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/22 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.controller.csk;

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

import coop.kyushu.gyomu_kanri.entity.csk.Pcsk010ListItem;
import coop.kyushu.gyomu_kanri.form.csk.Pcsk010Form;
import coop.kyushu.gyomu_kanri.service.csk.Pcsk010Service;

/**
 * <pre>
 * 【コープ九州商管】CSV取込コントローラ
 * </pre>
 */
@RestController
@RequestMapping("csk/pcsk010")
public class Pcsk010Controller {

	@Autowired
	Pcsk010Service service;

	/**
	 * CSV取込処理の取込処理
	 * @param form 入力内容
	 * @param bindingResult カスタムバリデーションチェック
	 * @return 取込結果リスト
	 * @throws Exception 
	 */
	@RequestMapping(value = "/readcsv", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<Pcsk010ListItem> readcsv(@Validated Pcsk010Form form, BindingResult bindingResult) throws Exception {
		if (bindingResult.hasErrors()) {
			throw new RuntimeException();
		}
		return service.readcsv(form);
	}
}
