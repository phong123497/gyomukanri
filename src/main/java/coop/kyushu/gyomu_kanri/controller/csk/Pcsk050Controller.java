//================================================================================
// ID: PCSK050
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pcsk050Controller
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

import coop.kyushu.gyomu_kanri.entity.csk.Pcsk050Entity;
import coop.kyushu.gyomu_kanri.form.csk.Pcsk050Form;
import coop.kyushu.gyomu_kanri.service.csk.Pcsk05001Service;
import coop.kyushu.gyomu_kanri.service.csk.Pcsk050Service;

/**
 * <pre>
 * 【コープ九州商管】センター商管表出力コントローラ
 * </pre>
 */
@RestController
@RequestMapping("csk/pcsk050")
public class Pcsk050Controller {

	@Autowired
	Pcsk050Service service;

	@Autowired
	Pcsk05001Service pcsk05001Service;

	/**
	 * センター商管表出力画面の初期表示
	 * @param form 入力内容
	 * @return 初期データ
	 */
	@RequestMapping(value = "/init", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Pcsk050Entity getInitialData(@RequestBody Pcsk050Form form) {
		return service.getInitialData(form);
	}

	/**
	 * センター商管表出力画面のExcel出力処理
	 * @param form 入力内容
	 * @param bindingResult カスタムバリデーションチェック結果
	 * @return センター商管表JSONデータ
	 */
	@RequestMapping(value = "/pcsk05001print", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Pcsk050Entity pcsk05001Print(@Validated @RequestBody Pcsk050Form form, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new RuntimeException();
		}
		return pcsk05001Service.pcsk05001Print(form);
	}
}
