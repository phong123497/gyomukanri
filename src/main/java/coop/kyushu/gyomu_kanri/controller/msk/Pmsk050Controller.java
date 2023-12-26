//================================================================================
// ID: PMSK050
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ProgramName: 【マスタ】取引先マスタ一覧
// ClassName: Pmsk050Controller
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/09/25 | ＡＭＣ               | 新規作成
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

import coop.kyushu.gyomu_kanri.entity.msk.Pmsk050Entity;
import coop.kyushu.gyomu_kanri.form.msk.Pmsk050Form;
import coop.kyushu.gyomu_kanri.service.msk.Pmsk050Service;

/**
 * <pre>
 * 【マスタ】取引先マスタ一覧
 * </pre>
 */
@RestController
@RequestMapping("msk/pmsk050")
public class Pmsk050Controller {
  /**
   * サービスクラス
   */
  @Autowired
  Pmsk050Service pmsk050Service;

  /**
   * 取引先マスタ件数取得
   * 
   * @return Pmsk050Entity
   */
  @RequestMapping(value = "/check", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public Pmsk050Entity getCount(@RequestBody Pmsk050Form pmsk050Form) {
    return pmsk050Service.getCount(pmsk050Form);
  }

  /**
   * 取引先マスタ一覧取得
   * 
   * @return Pmsk050Entity
   */
  @RequestMapping(value = "/search", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public Pmsk050Entity getTorihikisakiList(@RequestBody Pmsk050Form pmsk050Form) {
    return pmsk050Service.getTorihikisakiList(pmsk050Form);
  }

  /**
   * CSV出力情報取得
   * 
   * @return -
   */
  @RequestMapping(value = "/output", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public void outputCsv(@RequestBody Pmsk050Form pmsk050Form) throws IllegalAccessException {
    pmsk050Service.outputCsv(pmsk050Form);
  }
}
