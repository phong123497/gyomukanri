//================================================================================
// ID: PNKS040
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks040Controller
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/13 | ＡＭＣ               | 新規作成
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

import coop.kyushu.gyomu_kanri.entity.nks.Pnks04001Item;
import coop.kyushu.gyomu_kanri.entity.nks.Pnks040Entity;
import coop.kyushu.gyomu_kanri.form.nks.Pnks040Form;
import coop.kyushu.gyomu_kanri.service.nks.Pnks04001Service;
import coop.kyushu.gyomu_kanri.service.nks.Pnks040Service;

/**
 * <pre>
 * 【農産企画支援】企画案内書印刷コントローラ
 * </pre>
 */
@RestController
@RequestMapping("nks/pnks040")
public class Pnks040Controller {

	/** 企画案内書印刷サービス */
	@Autowired
	private Pnks040Service service;

	@Autowired
	private Pnks04001Service pnks04001Service;

	/**
	 * 【農産企画支援】企画案内書印刷の初期処理
	 * @param form 入力内容
	 * @return 初期データ
	 */
	@RequestMapping(value = "/init", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Pnks040Entity getInitialData() {
		return service.getInitialData();
	}

	/**
	 * 【農産企画支援】企画案内書印刷のCQ企画番号開始変更
	 * @param form 入力内容
	 * @return 初期データ
	 */
	@RequestMapping(value = "/search/change-cq-start", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Pnks040Entity search(@RequestBody Pnks040Form form) {
		return service.changeCqProjNoStart(form);
	}

	/**
	 * 【農産企画支援】企画案内書印刷の印刷  印刷帳票は企画案内書（計画）場合
	 * @param form 入力内容
	 * @param bindingResult
	 * @return 印刷データ
	 */
	@RequestMapping(value = "/pnks04001print/kekaku-print", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<Pnks04001Item> pnks04001KekakuPrint(@Validated({ Pnks040Form.Pnks040Group.KekakuGroup.class,
			Pnks040Form.Pnks040Group.OtherGroup.class }) @RequestBody Pnks040Form form, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			throw new RuntimeException();
		}

		return pnks04001Service.pnks04001Print(form);
	}

	/**
	 * 【農産企画支援】企画案内書印刷の印刷  印刷帳票は価格決定依頼書（確定）場合
	 * @param form 入力内容
	 * @param bindingResult
	 * @return 印刷データ
	 */
	@RequestMapping(value = "/pnks04001print/kakute-print", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<Pnks04001Item> pnks04001KakutePrint(@Validated({ Pnks040Form.Pnks040Group.KakuteGroup.class,
			Pnks040Form.Pnks040Group.OtherGroup.class }) @RequestBody Pnks040Form form, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			throw new RuntimeException();
		}

		return pnks04001Service.pnks04001Print(form);
	}
}
