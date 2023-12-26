//================================================================================
// ID: PMSK020
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ProgramName: 【マスタ】名称／区分管理マスタコード一覧
// ClassName: Pmsk020Controller
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/09/13 | AMC                  | 新規作成
//================================================================================

package coop.kyushu.gyomu_kanri.controller.msk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import coop.kyushu.gyomu_kanri.entity.msk.Pmsk020Entity;
import coop.kyushu.gyomu_kanri.form.msk.Pmsk020Form;
import coop.kyushu.gyomu_kanri.service.msk.Pmsk020Service;

/**
 * <pre>
 * 【マスタ】名称／区分管理マスタコード一覧
 * </pre>
 */
@RestController
@RequestMapping("msk/pmsk020")
public class Pmsk020Controller {

  /**
	 * サービスクラス
	 */
	@Autowired
	Pmsk020Service service;

  /**
	 * 【マスタ】名称／区分管理マスタコード一覧の検索前件数チェック
	 * @param params リクエストデータ
	 * @return Pmsk020Entity
	 */
  @RequestMapping(value="/check", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public Pmsk020Entity getCountByPmsk020(@RequestBody Pmsk020Form pmsk020Form) {
    return service.getCountByPmsk020(pmsk020Form);
  }

  /**
	 * 【マスタ】名称／区分管理マスタコード一覧の検索処理
	 * @param params リクエストデータ
	 * @return Pmsk020Entity
	 */
  @RequestMapping(value="/search", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public Pmsk020Entity getMeishoCdListByPmsk020(@RequestBody Pmsk020Form pmsk020Form) {
    return service.getMeishoCdList(pmsk020Form);
  }

  /**
	 * 【マスタ】名称／区分管理マスタコード一覧の存在チェック
	 * @param params リクエストデータ
	 * @return Pmsk020Entity
	 */
  @RequestMapping(value="/check-exist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public Pmsk020Entity checkExist(@RequestBody Pmsk020Form pmsk020Form) {
    return service.checkExist(pmsk020Form);
  }

  /**
	 * 【マスタ】名称／区分管理マスタコード一覧の削除処理
	 * @param params リクエストデータ
	 */
  // TODO CHECK 
  @RequestMapping(value="/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
  public void delete(@RequestBody Pmsk020Form pmsk020Form) {
    service.delete(pmsk020Form);
  }
}
