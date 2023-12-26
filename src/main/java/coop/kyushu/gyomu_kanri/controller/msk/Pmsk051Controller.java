
//================================================================================
// ID: PMSK051
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: PMSK051Controller
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/09/35 | ＡＭＣ               | 新規作成
//================================================================================

package coop.kyushu.gyomu_kanri.controller.msk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import coop.kyushu.gyomu_kanri.entity.msk.Pmsk051Entity;
import coop.kyushu.gyomu_kanri.form.msk.Pmsk051Form;
import coop.kyushu.gyomu_kanri.service.msk.Pmsk051Service;

@RestController
@RequestMapping("msk/pmsk051")
public class Pmsk051Controller {
	@Autowired
	Pmsk051Service pmsk051Service;

	/**
	 * PEACEデータ取込画面の取込処理
	 * @param form 入力内容
	 * @param bindingResult カスタムバリデーションチェック
	 * @return 取込結果リスト
	 * @throws Exception 
	 */
	@RequestMapping(value = "/readcsv", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Pmsk051Entity readcsv(@Validated Pmsk051Form form, BindingResult bindingResult) throws Exception {
			if (bindingResult.hasErrors()) {
			throw new RuntimeException();
		}
		return pmsk051Service.readcsv(form);
	}

}
