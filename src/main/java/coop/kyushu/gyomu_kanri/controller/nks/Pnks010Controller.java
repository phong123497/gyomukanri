//================================================================================
// ID: PNKS010
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks010Controller
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/10 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.controller.nks;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import coop.kyushu.gyomu_kanri.entity.nks.Pnks010ListItem;
import coop.kyushu.gyomu_kanri.form.nks.Pnks010Form;
import coop.kyushu.gyomu_kanri.service.nks.Pnks010Service;

/**
 * <pre>
 * 【農産企画支援】PEACEデータ取込コントローラ
 * </pre>
 */
@RestController
@RequestMapping("nks/pnks010")
public class Pnks010Controller {

	@Autowired
	Pnks010Service service;

	/**
	 * PEACEデータ取込画面の取込処理
	 * @param form 入力内容
	 * @param bindingResult カスタムバリデーションチェック
	 * @return 取込結果リスト
	 * @throws Exception 
	 */
	@RequestMapping(value = "/readcsv", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<Pnks010ListItem> readcsv(@Validated Pnks010Form form, BindingResult bindingResult) throws Exception {

		if (bindingResult.hasErrors()) {
			throw new RuntimeException();
		}

		return service.readcsv(form);
	}
}
