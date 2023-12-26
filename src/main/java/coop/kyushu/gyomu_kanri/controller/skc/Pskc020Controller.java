//================================================================================
// ID: PSKC020
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pskc020Controller
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/11 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.controller.skc;

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

import coop.kyushu.gyomu_kanri.entity.skc.Pskc020ListItem;
import coop.kyushu.gyomu_kanri.form.skc.Pskc020Form;
import coop.kyushu.gyomu_kanri.service.skc.Pskc020Service;

/**
 * <pre>
 * 【惣菜加工センター】製造計画数取込・出力コントローラ
 * </pre>
 */
@RestController
@RequestMapping("skc/pskc020")
public class Pskc020Controller {

	/* 製造計画数取込・出力サービス */
	@Autowired
	private Pskc020Service service;

	/**
	 * 製造計画数取込・出力の出力（期間指定）処理
	 * @param form 入力内容
	 * @return CSVファイル
	 */
	@RequestMapping(value = "/output/kknsitei", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public void outputCsvForKknSitei(
			@Validated({ Pskc020Form.Pskc020Group.OutputGroup.class,
					Pskc020Form.Pskc020Group.WeeksiteiGroup.class }) @RequestBody Pskc020Form form,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			throw new RuntimeException();
		}

		service.outputCsvForKknSitei(form);
	}

	/**
	 * 製造計画数取込・出力の出力（週指定）処理
	 * @param form 入力内容
	 * @return CSVファイル
	 */
	@RequestMapping(value = "/output/weeksitei", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public void outputCsvForWeekSitei(
			@Validated(Pskc020Form.Pskc020Group.OutputGroup.class) @RequestBody Pskc020Form form,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			throw new RuntimeException();
		}

		service.outputCsvForWeekSitei(form);
	}

	/**
	 * 製造計画数取込・出力の取込（計画数）処理
	 * @param form 入力内容
	 * @return 取込結果
	 * @throws Exception 
	 */
	@RequestMapping(value = "/import/plansu", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<Pskc020ListItem> getKbnTorikmPlanSu(@Validated({ Pskc020Form.Pskc020Group.PlansuGroup.class,
			Pskc020Form.Pskc020Group.OutputGroup.class }) Pskc020Form form, BindingResult bindingResult)
			throws Exception {

		if (bindingResult.hasErrors()) {
			throw new RuntimeException();
		}

		return service.getKbnTorikmPlanSu(form);
	}

	/**
	 * 製造計画数取込・出力の取込（商品改廃）処理
	 * @param form 入力内容
	 * @return 取込結果
	 * @throws Exception 
	 */
	@RequestMapping(value = "/import/shinkaihai", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<Pskc020ListItem> getTorikmShinKaihai(@Validated({ Pskc020Form.Pskc020Group.ShinkaihaiGroup.class,
			Pskc020Form.Pskc020Group.OutputGroup.class }) Pskc020Form form, BindingResult bindingResult)
			throws Exception {

		if (bindingResult.hasErrors()) {
			throw new RuntimeException();
		}

		return service.getTorikmShinKaihai(form);
	}
}
