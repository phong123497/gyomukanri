//================================================================================
// ID: PEXT020
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ProgramName: 仕入・訂正入力
// ClassName: Pext020Controller
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2022/11/28 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.controller.ext;

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

import coop.kyushu.gyomu_kanri.entity.ext.Pext020Entity;
import coop.kyushu.gyomu_kanri.entity.ext.Pext020JigyoshoItem;
import coop.kyushu.gyomu_kanri.entity.ext.Pext020ListItem;
import coop.kyushu.gyomu_kanri.entity.ext.Pext020TorihikisakiItem;
import coop.kyushu.gyomu_kanri.form.ext.Pext020Form;
import coop.kyushu.gyomu_kanri.service.ext.Pext020Service;

/**
 * <pre>
 * 仕入・訂正入力
 * </pre>
 */
@RestController
@RequestMapping("ext/pext020")
public class Pext020Controller {

	/**
	 * サービスクラス
	 */
	@Autowired
	Pext020Service service;

	/**
	 * 事業所名取得
	 * @param form 入力内容
	 * @return 事業所情報(entity)
	 */
	@RequestMapping(value = "/search/jigyosho", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Pext020JigyoshoItem getJigyosho(@RequestBody Pext020Form form) {

		return service.getJigyosho(form);
	}

	/**
	 * 取引先名取得
	 * @param form 入力内容
	 * @return 取引先名(entity)
	 */
	@RequestMapping(value = "/search/torihikisaki", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Pext020TorihikisakiItem getTorihikisaki(@RequestBody Pext020Form form) {

		return service.getTorihikisaki(form);
	}

	/**
	 * 仕入・訂正入力一覧取得
	 * @param form 入力内容
	 * @return 仕入・訂正入力一覧(entity)
	 */
	@RequestMapping(value = "/search/dempyo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Pext020Entity getDempyo(
			@Validated(Pext020Form.Group.Head1.class) @RequestBody Pext020Form form,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			throw new RuntimeException();
		}

		return service.getDempyo(form);
	}

	/**
	 * 通過区分名取得
	 * @param form 入力内容
	 * @return 通過区分名(entity)
	 */
	@RequestMapping(value = "/search/tsuka-nohin-basho", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Pext020Entity getTsukaNohimBasho(@RequestBody Pext020Form form) {

		return service.getTsukaNohimBasho(form);
	}

	/**
	 * 商品情報取得(選択行を対象とする)
	 * @param form 入力内容
	 * @return List<Pext020ListItem> 明細
	 */
	@RequestMapping(value = "/search/shohin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Pext020ListItem getShohin(@RequestBody Pext020Form form) {

		return service.getShohin(form);
	}

	/**
	 * 伝票更新チェック(訂正、税率変更モード)
	 * @param form 入力内容
	 * @return チェック結果(entity)
	 */
	@RequestMapping(value = "/updateDempyoCheck", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Pext020Entity checkDempyoUpdate(
			@Validated(Pext020Form.Group.Head2.class) @RequestBody Pext020Form form,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			throw new RuntimeException();
		}

		return service.checkDempyoUpdate(form);
	}

	/**
	 * 伝票登録処理
	 * @param form 入力内容
	 * @return 登録結果(entity)
	 */
	@RequestMapping(value = "/add", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public Pext020Entity add(@Validated({ Pext020Form.Group.Head1toroku.class,
			Pext020Form.Group.Head2.class }) @RequestBody Pext020Form form, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			throw new RuntimeException();
		}

		return service.add(form);
	}
}
