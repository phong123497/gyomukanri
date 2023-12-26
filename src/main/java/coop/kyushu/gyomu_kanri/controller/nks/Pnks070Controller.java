//================================================================================
// ID: Pnks070
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks070Controller
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/25 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.controller.nks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import coop.kyushu.gyomu_kanri.form.nks.Pnks070Form;
import coop.kyushu.gyomu_kanri.service.nks.Pnks070Service;

/**
 * <pre>
 * 【農産企画支援】価格取込用データ出力コントローラ
 * </pre>
 */
@RestController
@RequestMapping("nks/pnks070")
public class Pnks070Controller {

	@Autowired
	Pnks070Service service;

	/**
	 * 価格取込用データ出力画面の産企画商品禁則価格チェック
	 * @param form 入力内容
	 * @return 産企画商品件数 
	 */
	@RequestMapping(value = "/outputcheck", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Integer csvOutputCheck(@RequestBody Pnks070Form form) {
		return service.csvOutputCheck(form);
	}

	/**
	 * 価格取込用データ出力画面のCSV出力処理
	 * @param form 入力内容
	 * @throws IllegalAccessException 
	 */
	@RequestMapping(value = "/output", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public void csvOutput(@RequestBody Pnks070Form form) throws IllegalAccessException {
		service.csvOutput(form);
	}
}
