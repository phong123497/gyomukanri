//================================================================================
// ID: PMSK030
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理システム
// ClassName: Pmsk030Controller
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/09/15 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.controller.msk;

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

import coop.kyushu.gyomu_kanri.entity.msk.Pmsk030Entity;
import coop.kyushu.gyomu_kanri.form.msk.Pmsk030Form;
import coop.kyushu.gyomu_kanri.service.msk.Pmsk030Service;

/**
 * <pre>
 * 業務管理システム
 * </pre>
 */
@RestController
@RequestMapping("/msk/pmks030")
public class Pmsk030Controller {
	/**
	 * サービスクラス
	 */
	@Autowired
	private Pmsk030Service pmsk030Service;

	/**
	 * 区分管理マスタ取得
	 * @param form 入力内容
	 * @return Pmsk030Entity
	 */
	@RequestMapping(value = "/kubun", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Pmsk030Entity getKubun(@RequestBody Pmsk030Form form) {
		return pmsk030Service.getKbn(form);
	}

	/**
	 * 名称マスタ(VIEW TABLE)取得
	 * @param form 入力内容
	 * @return Pmsk030Entity
	 */
	@RequestMapping(value = "/meisho", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Pmsk030Entity getMeisho(@RequestBody Pmsk030Form form) {
		return pmsk030Service.getMeisho(form);
	}

	/**
	 * 名称マスタ登録
	 * @param form 入力内容
	 */
	@RequestMapping(value = "/insmeisho", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public void insertMeisho(@RequestBody @Validated Pmsk030Form form, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new RuntimeException();
		}
		pmsk030Service.insertMeisho(form);
	}

	/**
	 * 名称マスタ更新
	 * @param form 入力内容
	 */
	@RequestMapping(value = "/updmeisho", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public void updateMeisho(@RequestBody @Validated Pmsk030Form form, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new RuntimeException();
		}
		pmsk030Service.updateMeisho(form);

	}

	/**
	 * 区分管理マスタ登録
	 * @param form 入力内容
	 * 
	 */
	@RequestMapping(value = "/inskubun", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public void insertKubun(@RequestBody @Validated Pmsk030Form form, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new RuntimeException();
		}
		pmsk030Service.insertKubun(form);
	}

	/**
	 * 区分管理マスタ更新
	 * @param form 入力内容
	 *
	 */
	@RequestMapping(value = "/updkubun", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public void updateKubun(@RequestBody @Validated Pmsk030Form form, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new RuntimeException();
		}
		pmsk030Service.updateKubun(form);

	}
}
