//================================================================================
// ID: Pmsk040
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pmsk040Controller
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/09/14 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.controller.msk;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import coop.kyushu.gyomu_kanri.entity.msk.Pmsk040ListItem;
import coop.kyushu.gyomu_kanri.form.msk.Pmsk040Form;
import coop.kyushu.gyomu_kanri.service.msk.Pmsk040Service;

/**
 * <pre>
 * 【マスク】企画商品マスタ一覧コントローラ
 * </pre>
 */
@RestController
@RequestMapping("/msk/pmsk040")
public class Pmsk040Controller {

	/**
	 * サービスクラス
	 */
	@Autowired
	Pmsk040Service service;

	/**
	 * 【マスク】企画商品マスタ一覧の検索処理
	 * @param form 入力内容
	 * @return 企画商品マスタ一覧(List<Pmsk040ListItem>)
	 */
	@RequestMapping(value = "/search", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<Pmsk040ListItem> getKikakuShohinList(@RequestBody Pmsk040Form form) {

		return service.getKikakuShohinList(form);
	}

	/**
	 * 【マスク】企画商品検索画面の検索前処理
	 * @param form 入力内容
	 * @return データ件数
	 */
	@RequestMapping(value = "/check", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public int getCount(@RequestBody Pmsk040Form form) {

		return service.getCount(form);
	}
}
