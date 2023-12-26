//================================================================================
// ID: PNKS150
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks150Controller
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/01 | ＡＭＣ               | 新規作成
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

import coop.kyushu.gyomu_kanri.entity.nks.Pnks15001Item;
import coop.kyushu.gyomu_kanri.entity.nks.Pnks150Entity;
import coop.kyushu.gyomu_kanri.form.nks.Pnks150Form;
import coop.kyushu.gyomu_kanri.service.nks.Pnks15001Service;
import coop.kyushu.gyomu_kanri.service.nks.Pnks150Service;

/**
 * <pre>
 * 【農産企画支援】納品指示書発行指示コントローラ
 * </pre>
 */
@RestController
@RequestMapping("nks/pnks150")
public class Pnks150Controller {

	@Autowired
	Pnks150Service service;

	@Autowired
	Pnks15001Service pnks15001Service;

	/**
	 * 納品指示書発行指示画面の初期表示
	 * @param form 入力内容
	 * @return 初期データ
	 */
	@RequestMapping(value = "/init", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Pnks150Entity getInitialData(@RequestBody Pnks150Form form) {
		return service.getInitialData(form);
	}

	/**
	 * 納品指示書発行指示画面の印刷
	 * @param form 入力内容
	 * @param bindingResult カスタムバリデーションチェック結果
	 * @return 納品指示書発行指示画面JSON
	 */
	@RequestMapping(value = "/pnks15001print", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<Pnks15001Item> pnks15001Print(@RequestBody @Validated Pnks150Form form, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new RuntimeException();
		}
		return pnks15001Service.pnks15001Print(form);
	}
}
