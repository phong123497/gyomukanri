//================================================================================
// ID: PNKS020
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks020Controller
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/07 | ＡＭＣ               | 新規作成
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

import coop.kyushu.gyomu_kanri.entity.nks.Pnks020Entity;
import coop.kyushu.gyomu_kanri.form.nks.Pnks020Form;
import coop.kyushu.gyomu_kanri.service.nks.Pnks020Service;

/**
 * <pre>
 * 【農産企画支援】農産企画情報・商品検索コントローラ
 * </pre>
 */
@RestController
@RequestMapping("nks/pnks020")
public class Pnks020Controller {

	/**
	 * サービスクラス
	 */
	@Autowired
	Pnks020Service service;

	/**
	 * 農産企画情報・商品検索画面の検索処理
	 * @param form 入力内容
	 * @param bindingResult カスタムバリデーションチェック結果
	 * @return 農産企画情報・商品検索エンティティ(entity)
	 */
	@RequestMapping(value = "/search", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Pnks020Entity getNousanProjinShin(@RequestBody @Validated Pnks020Form form, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new RuntimeException();
		}
		return service.getNousanProjinShin(form);
	}

	/**
	 * 農産企画情報・商品検索画面の検索結果件数チェック
	 * @param form 入力内容
	 * @param bindingResult カスタムバリデーションチェック結果
	 * @return データ件数
	 */
	@RequestMapping(value = "/search/check", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public int getNousanProjinShinCnt(@RequestBody @Validated Pnks020Form form, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new RuntimeException();
		}
		return service.getNousanProjinShinCnt(form);
	}

}
