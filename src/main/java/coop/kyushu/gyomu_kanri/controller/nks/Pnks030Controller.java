//================================================================================
// ID: PNKS030
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks030Controller
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/10 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.controller.nks;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import coop.kyushu.gyomu_kanri.entity.com.Nsmt020Entity;
import coop.kyushu.gyomu_kanri.entity.com.Nsmt030Entity;
import coop.kyushu.gyomu_kanri.entity.nks.Pnks030Entity;
import coop.kyushu.gyomu_kanri.form.nks.Pnks030Form;
import coop.kyushu.gyomu_kanri.service.nks.Pnks030Service;

/**
 * <pre>
 * 【農産企画支援】農産企画情報入力コントローラ
 * </pre>
 */
@RestController
@RequestMapping("nks/pnks030")
public class Pnks030Controller {

	@Autowired
	Pnks030Service service;

	/**
	 * 農産企画情報入力画面の初期表示
	 * @param form 入力内容
	 * @return 初期データ
	 */
	@RequestMapping(value = "/init", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Pnks030Entity getInitialData(@RequestBody Pnks030Form form) {
		return service.getInitialData(form);
	}

	/**
	 * 農産企画情報入力画面の産地コンボ取得処理
	 * @param form 入力内容
	 * @return 産地コンボ
	 */
	@RequestMapping(value = "/search/get-santi-cbox", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<Nsmt020Entity> getSantiCboxCbox(@RequestBody Pnks030Form form) {
		return service.getSantiCboxCbox(form);
	}

	/**
	 * 農産企画情報入力画面の等階級コンボ取得処理
	 * @param form 入力内容
	 * @return 等階級コンボ
	 */
	@RequestMapping(value = "/search/get-tokaiky-cbox", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<Nsmt030Entity> getTokaikyCbox(@RequestBody Pnks030Form form) {
		return service.getTokaikyCbox(form);
	}

	/**
	 * 農産企画情報入力画面の保存処理
	 * @param form 入力内容
	 * @param bindingResult カスタムバリデーションチェック結果
	 * @return 保存後データ
	 */
	@Transactional
	@RequestMapping(value = "/save", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Pnks030Entity save(@Validated @RequestBody Pnks030Form form, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new RuntimeException();
		}

		return service.save(form);
	}

	/**
	 * 農産企画情報入力画面の次の商品処理
	 * @param form 入力内容
	 * @return 次の商品処理データ
	 */
	@RequestMapping(value = "/search/get-next-shin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Pnks030Entity getNextShin(@RequestBody Pnks030Form form) {
		return service.getNextShin(form);
	}

	/**
	 * 農産企画情報入力画面の前の商品処理
	 * @param form 入力内容
	 * @return 前の商品処理データ
	 */
	@RequestMapping(value = "/search/get-bf-shin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Pnks030Entity getBfShin(@RequestBody Pnks030Form form) {
		return service.getBfShin(form);
	}
}
