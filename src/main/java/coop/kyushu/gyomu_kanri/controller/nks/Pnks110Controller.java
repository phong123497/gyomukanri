//================================================================================
//ID: PNKS110
//ProjectName: コープ九州事業連合様  ホストシステムリプレイス
//SystemName: 業務管理
//ClassName: Pnks110Controller
//
//<<Modification History>>
//Version  | Date       | Updated By           | Content
//---------+------------+----------------------+---------------------------------
//01.00.00 | 2023/08/03 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.controller.nks;

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

import coop.kyushu.gyomu_kanri.entity.nks.Pnks11001Entity;
import coop.kyushu.gyomu_kanri.form.nks.Pnks110Form;
import coop.kyushu.gyomu_kanri.service.nks.Pnks11001Service;

/**
 * <pre>
 * 【農産企画支援】調達加工計画書印刷
 * </pre>
 */
@RestController
@RequestMapping("nks/pnks110")
public class Pnks110Controller {

	@Autowired
	Pnks11001Service service;

	/**
	 * 調達加工計画出力データ取得
	 * 
	 * @param form 入力内容
	 * @return 調達加工計画出力データ
	 */
	@RequestMapping(value = "/pnks11001print", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Pnks11001Entity pnks11001Print(@Validated @RequestBody Pnks110Form form, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			throw new RuntimeException();
		}

		return service.pnks11001Print(form);

	}

}
