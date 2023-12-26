//================================================================================
// ID: PCSK020
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pcsk020Controller
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/18 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.controller.csk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import coop.kyushu.gyomu_kanri.form.csk.Pcsk020Form;
import coop.kyushu.gyomu_kanri.service.csk.Pcsk020Service;

/**
 * <pre>
 * 【コープ九州商管】CSV出力コントローラ
 * </pre>
 */
@RestController
@RequestMapping("csk/pcsk020")
public class Pcsk020Controller {

	/** CSV出力サービス */
	@Autowired
	Pcsk020Service service;

	/**
	 * PCSK020_【コープ九州商管】のCSV出力処理
	 * @param form 入力内容
	 * @throws Exception 
	 */
	@RequestMapping(value = "/csvoutput", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public void search(@Validated @RequestBody Pcsk020Form form, BindingResult bindingResult) throws Exception {

		if (bindingResult.hasErrors()) {
			throw new RuntimeException();
		}

		service.search(form);
	}
}
