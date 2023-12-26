//================================================================================
// ID: PNKS060
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks060Controller
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

import coop.kyushu.gyomu_kanri.form.nks.Pnks060Form;
import coop.kyushu.gyomu_kanri.service.nks.Pnks060Service;

/**
 * <pre>
 * 【農産企画支援】シミュレーション用データ出力コントローラ
 * </pre>
 */
@RestController
@RequestMapping("nks/pnks060")
public class Pnks060Controller {

	@Autowired
	Pnks060Service service;

	/**
	 * シミュレーション用データの出力処理
	 * @param form 入力内容
	 * @param bindingResult カスタムバリデーションチェック
	 * @throws Exception 
	 */
	@RequestMapping(value = "/output", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public void csvOutput(@Validated @RequestBody Pnks060Form form, BindingResult bindingResult) throws Exception {

		if (bindingResult.hasErrors()) {
			throw new RuntimeException();
		}

		service.csvOutput(form);
	}
}
