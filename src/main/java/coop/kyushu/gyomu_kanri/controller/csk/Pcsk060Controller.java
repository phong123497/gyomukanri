//================================================================================
// ID: PCKS060
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pcsk060Controller
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

import coop.kyushu.gyomu_kanri.entity.csk.Pcsk06001Entity;
import coop.kyushu.gyomu_kanri.entity.csk.Pcsk060Entity;
import coop.kyushu.gyomu_kanri.form.csk.Pcsk060Form;
import coop.kyushu.gyomu_kanri.service.csk.Pcsk06001Service;
import coop.kyushu.gyomu_kanri.service.csk.Pcsk060Service;

@RestController
@RequestMapping("csk/pcsk060")
public class Pcsk060Controller {
	@Autowired
	Pcsk060Service service;
	@Autowired
	Pcsk06001Service outService;

	/**
	 * 粗利益管理表用データの初期化処理
	 * @param form 入力内容
	 * @param bindingResult カスタムバリデーションチェック
	 * @throws Exception 
	 */
	@RequestMapping(value = "/search", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Pcsk060Entity getInitialData(@Validated @RequestBody Pcsk060Form form) throws Exception {
		return service.getInitialData(form);
	}

	/**
	 * 粗利益管理表用データの初期化処理
	 * @param form 入力内容
	 * @param bindingResult カスタムバリデーションチェック
	 * @throws Exception 
	 */
	@RequestMapping(value = "/change_nendo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Pcsk060Entity changeNendo(@RequestBody Pcsk060Form form) throws Exception {
		return service.changeNendo(form);
	}

	/**
	 * 粗利益管理表用データのCSV出力処理
	 * @param form 入力内容
	 * @param bindingResult カスタムバリデーションチェック
	 * @throws Exception 
	 */
	@RequestMapping(value = "/pcsk06001printcsv", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public void pcsk06001PrintCsv(@Validated @RequestBody Pcsk060Form form, BindingResult bindingResult)
			throws Exception {
		if (bindingResult.hasErrors()) {
			throw new RuntimeException();
		}
		outService.pcsk06001PrintCsv(form);
	}

	/**
	 * 粗利益管理表用データのPDF出力処理
	 * @param form 入力内容
	 * @param bindingResult カスタムバリデーションチェック
	 * @throws Exception 
	 */
	@RequestMapping(value = "/pcsk06001printpdf", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Pcsk06001Entity pcsk06001PrintPdf(@Validated @RequestBody Pcsk060Form form, BindingResult bindingResult)
			throws Exception {
		if (bindingResult.hasErrors()) {
			throw new RuntimeException();
		}
		return outService.pcsk06001PrintPdf(form);
	}
}
