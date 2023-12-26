//================================================================================
// ID: PNKS120
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks120Controller
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/02 | ＡＭＣ               | 新規作成
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

import coop.kyushu.gyomu_kanri.form.nks.Pnks120Form;
import coop.kyushu.gyomu_kanri.service.nks.Pnks120Service;

/**
 * <pre>
 * 【農産企画支援】各種データ出力コントローラ
 * </pre>
 */
@RestController
@RequestMapping("nks/pnks120")
public class Pnks120Controller {

	/** 各種データ出力サービス */
	@Autowired
	Pnks120Service service;

	/**
	 * 各種データ出力処理
	 * @param form 入力内容
	 * @param bindingResult カスタムバリデーションチェック結果
	 * @throws Exception 
	 */
	@RequestMapping(value = "/output", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public void csvOutput(@Validated @RequestBody Pnks120Form form, BindingResult bindingResult) throws Exception {

		if (bindingResult.hasErrors()) {
			throw new RuntimeException();
		}

		service.csvOutput(form);
	}
}
