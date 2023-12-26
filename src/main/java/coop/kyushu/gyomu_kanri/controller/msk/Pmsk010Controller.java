
//================================================================================
// ID: PMSK010
// ProjectName: 【マスタ】名称／区分管理マスタ区分一覧
// SystemName: 名称／区分管理マスタ区分一覧
// ClassName: PMSK010Controller
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// 01.00.00 | 2023/09/14 | ＡＭＣ               | 新規作成
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
import coop.kyushu.gyomu_kanri.entity.msk.Pmsk010Entity;
import coop.kyushu.gyomu_kanri.form.msk.Pmsk010Form;
import coop.kyushu.gyomu_kanri.service.msk.Pmsk010Service;

/**
 * <pre>
【マスタ】名称／区分管理マスタ区分一覧
 * </pre>
 */
@RestController
@RequestMapping("msk/pmsk010")
public class Pmsk010Controller {
    /**
	 * サービスクラス
	 */
    @Autowired
    private Pmsk010Service pmsk010Service;

    /**
	 * 一覧の検索前件数チェック
	 * @param form 入力内容
	 * @return データ件数
	 */
    @RequestMapping(value="/check", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Pmsk010Entity getCount(@RequestBody Pmsk010Form form) {
        return  pmsk010Service.getCount(form);
    } 
   /**
	 * 一覧の検索処理
	 * @param form 入力内容
	 * @return 初期ページ(Pnks010Entity)
	 */
    @RequestMapping(value="/search", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Pmsk010Entity getMeishoKubunList(@RequestBody Pmsk010Form form) {
        return pmsk010Service.getMeishoKubunList(form);
    }
}
