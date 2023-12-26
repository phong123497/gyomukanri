//================================================================================
// ID: PNKS140
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks140Controller
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/06 | ＡＭＣ               | 新規作成
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

import coop.kyushu.gyomu_kanri.entity.nks.Pnks140Entity;
import coop.kyushu.gyomu_kanri.form.nks.Pnks140Form;
import coop.kyushu.gyomu_kanri.service.nks.Pnks140Service;

/**
 * <pre>
 * 【農産企画支援】メーカーマスタメンテナンスコントローラ
 * </pre>
 */
@RestController
@RequestMapping("nks/pnks140")
public class Pnks140Controller {

	@Autowired
	Pnks140Service service;

	/**
	 * メーカーマスタメンテナンス画面の初期表示
	 * @param form 入力内容
	 * @return 初期データ
	 */
	@RequestMapping(value = "/init", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Pnks140Entity getInitialData(@RequestBody Pnks140Form form) {
		return service.getInitialData(form);
	}

	/**
	 * メーカーマスタメンテナンス画面の保存処理
	 * @param form 入力内容
	 * @return 初期データ
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public Pnks140Entity save(@Validated @RequestBody Pnks140Form form, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			throw new RuntimeException();
		}

		return service.save(form);
	}

	/**
	 * メーカーマスタメンテナンス画面の更新処理
	 * @param form 入力内容
	 * @return 初期データ
	 */
	@RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public Pnks140Entity update(@Validated @RequestBody Pnks140Form form, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			throw new RuntimeException();
		}

		return service.update(form);
	}
}
