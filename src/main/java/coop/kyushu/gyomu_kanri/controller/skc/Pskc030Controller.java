//================================================================================
// ID: PSKC030
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pskc030Controller
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/11 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.controller.skc;

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

import coop.kyushu.gyomu_kanri.entity.skc.Pskc030Entity;
import coop.kyushu.gyomu_kanri.entity.skc.Rskc03001Entity;
import coop.kyushu.gyomu_kanri.form.skc.Pskc030Form;
import coop.kyushu.gyomu_kanri.service.skc.Pskc03001Service;
import coop.kyushu.gyomu_kanri.service.skc.Pskc030Service;

/**
 * <pre>
 * 【惣菜加工センター】製造計画数入力コントローラ
 * </pre>
 */
@RestController
@RequestMapping("skc/pskc030")
public class Pskc030Controller {

	@Autowired
	Pskc030Service service;

	@Autowired
	Pskc03001Service pskc03001Service;

	/**
	 * 製造計画数入力画面の初期表示
	 * @param form 入力内容
	 * @return 初期データ
	 */
	@RequestMapping(value = "/init", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Pskc030Entity getInitialData(@RequestBody Pskc030Form form) {
		return service.getInitialData(form);
	}

	/**
	 * 製造計画数入力画面の検索処理
	 * @param form 入力内容
	 * @param bindingResult カスタムバリデーションチェック結果
	 * @return 製造計画数入力画面の検索データ
	 */
	@RequestMapping(value = "/search", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Rskc03001Entity getSousaiSeizouKeikakuData(@RequestBody @Validated Pskc030Form form,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new RuntimeException();
		}
		return service.getSousaiSeizouKeikakuData(form);
	}

	/**
	 * 製造計画数入力画面の保存処理
	 * @param form 入力内容
	 * @param bindingResult カスタムバリデーションチェック結果
	 */
	@RequestMapping(value = "/save", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Rskc03001Entity save(@RequestBody @Validated Pskc030Form form, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new RuntimeException();
		}
		return service.save(form);
	}

	/**
	 * 製造計画数入力画面の印刷処理
	 * @param form 入力内容
	 * @param bindingResult カスタムバリデーションチェック結果
	 * @return 製造計画数入力JSON
	 */
	@RequestMapping(value = "/rskc03001print", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Rskc03001Entity rskc03001Print(@RequestBody @Validated Pskc030Form form, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new RuntimeException();
		}
		return pskc03001Service.rskc03001Print(form);
	}
}
