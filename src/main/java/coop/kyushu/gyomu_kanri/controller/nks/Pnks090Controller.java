//================================================================================
// ID: PNKS090
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks090Controller
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/14 | ＡＭＣ               | 新規作成
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

import coop.kyushu.gyomu_kanri.entity.nks.Pnks090Entity;
import coop.kyushu.gyomu_kanri.form.nks.Pnks090Form;
import coop.kyushu.gyomu_kanri.service.nks.Pnks090Service;

/**
 * <pre>
 * 【農産企画支援】バッチ情報メンテナンスコントローラ
 * </pre>
 */
@RestController
@RequestMapping("nks/pnks090")
public class Pnks090Controller {

	@Autowired
	Pnks090Service service;

	/**
	 * バッチ情報メンテナンス画面の初期表示
	 * @param form 入力内容
	 * @return 初期データ
	 */
	@RequestMapping(value = "/init", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Pnks090Entity getInitialData(@RequestBody Pnks090Form form) {
		return service.getInitialData(form);
	}

	/**
	 * バッチ情報メンテナンス画面の保存処理
	 * @param form 入力内容
	 */
	@RequestMapping(value = "/save", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@Validated @RequestBody Pnks090Form form, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new RuntimeException();
		}
		service.save(form);
	}
}
