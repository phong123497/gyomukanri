//================================================================================
// ID: PNKS050
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks050Controller
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/26 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.controller.nks;

import java.util.List;

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

import coop.kyushu.gyomu_kanri.entity.nks.Pnks05001ListItem;
import coop.kyushu.gyomu_kanri.form.nks.Pnks050Form;
import coop.kyushu.gyomu_kanri.service.nks.Pnks050Service;

/**
 * <pre>
 * 【農産企画支援】価格表印刷コントローラ
 * </pre>
 */
@RestController
@RequestMapping("nks/pnks050")
public class Pnks050Controller {

	@Autowired
	Pnks050Service service;

	/**
	 * 価格表印刷画面の印刷処理
	 * @param form 入力内容
	 * @return 価格表印刷データ
	 */
	@RequestMapping(value = "/pnks05001print", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<Pnks05001ListItem> pnks05001Print(@RequestBody @Validated Pnks050Form form,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new RuntimeException();
		}
		return service.pnks05001Print(form);
	}
}
