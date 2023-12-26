//================================================================================
// ID: PCSK040
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pcsk040Controller
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/17 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.controller.csk;

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

import coop.kyushu.gyomu_kanri.entity.csk.Pcsk040Entity;
import coop.kyushu.gyomu_kanri.form.csk.Pcsk040Form;
import coop.kyushu.gyomu_kanri.service.csk.Pcsk040Service;

/**
 * <pre>
 * 【コープ九州商管】締日登録コントローラ
 * </pre>
 */
@RestController
@RequestMapping("csk/pcsk040")
public class Pcsk040Controller {

	@Autowired
	Pcsk040Service service;

	/**
	 * 【コープ九州商管】締日登録の検索処理
	 * @param form 入力内容
	 * @return 画面から返されたデータ
	 */
	@RequestMapping(value = "/search", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Pcsk040Entity search(@Validated @RequestBody Pcsk040Form form) throws Exception {
		return service.search(form);
	}

	/**
	 * 【コープ九州商管】締日登録の保存処理
	 * @param form 入力内容
	 * @param bindingResult カスタムバリデーションチェック結果
	 * @throws Exception 
	 */
	@Transactional
	@RequestMapping(value = "/updata", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@Validated @RequestBody Pcsk040Form form, BindingResult bindingResult) throws Exception {
		if (bindingResult.hasErrors()) {
			throw new RuntimeException();
		}
		service.save(form);
	}
}
