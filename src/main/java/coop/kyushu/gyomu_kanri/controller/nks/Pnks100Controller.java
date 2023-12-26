//================================================================================
// ID: PNKS100
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks100Controller
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/27 | ＡＭＣ               | 新規作成
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

import coop.kyushu.gyomu_kanri.entity.nks.Pnks10001Entity;
import coop.kyushu.gyomu_kanri.form.nks.Pnks100Form;
import coop.kyushu.gyomu_kanri.service.nks.Pnks1001Service;

/**
 * <pre>
 * 【農産企画支援】バッチ計画表印刷コントローラ
 * </pre>
 */
@RestController
@RequestMapping("nks/pnks100")
public class Pnks100Controller {

	@Autowired
	Pnks1001Service service;

	/**
	 * 【農産企画支援】バッチ計画表印刷の印刷
	 * @param form 入力内容
	 * @param bindingResult カスタムバリデーションチェック
	 * @return バッチ計画表データ
	 */
	@RequestMapping(value = "/pnks10001print", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Pnks10001Entity pnks10001Print(@Validated @RequestBody Pnks100Form form, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			throw new RuntimeException();
		}

		return service.pnks10001Print(form);
	}
}
