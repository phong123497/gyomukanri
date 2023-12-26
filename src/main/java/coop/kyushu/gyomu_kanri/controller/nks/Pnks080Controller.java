//================================================================================
// ID: PNKS080
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks080Controller
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/17 | ＡＭＣ               | 新規作成
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

import coop.kyushu.gyomu_kanri.entity.nks.Pnks080ListItem;
import coop.kyushu.gyomu_kanri.form.nks.Pnks080Form;
import coop.kyushu.gyomu_kanri.service.nks.Pnks080Service;

/**
 * <pre>
 * 【農産企画支援】バッチ情報一覧コントローラ
 * </pre>
 */
@RestController
@RequestMapping("nks/pnks080")
public class Pnks080Controller {

	@Autowired
	Pnks080Service service;

	/**
	 * バッチ情報一覧画面の初期表示
	 * @param form 入力内容
	 * @return 初期データ
	 */
	@RequestMapping(value = "/init", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<Pnks080ListItem> getInitialData() {

		return service.getInitialData();
	}

	/**
	 * バッチ情報一覧画面の削除処理
	 * @param form 入力内容
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@RequestBody Pnks080Form form) {

		service.delete(form);
	}
}
