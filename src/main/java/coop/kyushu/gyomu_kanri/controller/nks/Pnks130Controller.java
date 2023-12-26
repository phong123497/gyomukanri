//================================================================================
// ID: PNKS130
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks130Controller
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/06 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.controller.nks;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import coop.kyushu.gyomu_kanri.entity.nks.Pnks130Entity;
import coop.kyushu.gyomu_kanri.entity.nks.Pnks130ListItem;
import coop.kyushu.gyomu_kanri.form.nks.Pnks130Form;
import coop.kyushu.gyomu_kanri.service.nks.Pnks130Service;

/**
 * <pre>
 * 【農産企画支援】メーカーマスタ一覧コントローラ
 * </pre>
 */
@RestController
@RequestMapping("nks/pnks130")
public class Pnks130Controller {

	@Autowired
	Pnks130Service service;

	/**
	 * 【農産企画支援】メーカーマスタ一覧の初期表示
	 * @return 初期ページ(Pnks130Entity)
	 */
	@RequestMapping(value = "/init", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Pnks130Entity getInitialData(@RequestBody Pnks130Form form) {

		return service.getInitialData(form);
	}

	/**
	 * 【農産企画支援】メーカーマスタ一覧の検索処理
	 * @param form 入力内容
	 * @return メーカーマスタ一覧(List<Pnks130ListItem>)
	 */
	@RequestMapping(value = "/search", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<Pnks130ListItem> search(@RequestBody Pnks130Form form) {

		return service.search(form);
	}

	/**
	 * 農産企画支援】メーカー検索画面の検索前処理
	 * @param form 入力内容
	 * @return データ件数
	 */
	@RequestMapping(value = "/search/check", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public int getInitialDataCnt(Pnks130Form form) {

		return service.getInitialDataCnt(form);
	}

	/**
	 * 【農産企画支援】メーカーマスタ一覧の保存処理
	 * @param form 入力内容
	 */
	@RequestMapping(value = "/save", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@RequestBody Pnks130Form form) {
		service.save(form);
	}

	/**
	 * 【農産企画支援】メーカーマスタ一覧の削除処理
	 * @param form 入力内容
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public void delete(@RequestBody Pnks130Form form) {

		service.delete(form);
	}
}
