//================================================================================
// ID: PMSK010
// ProjectName: 【マスタ】名称／区分管理マスタ区分一覧
// SystemName: 名称／区分管理マスタ区分一覧
// ClassName: PMSK010Service
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// 01.00.00 | 2023/09/14 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.service.msk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coop.kyushu.gyomu_kanri.entity.msk.Pmsk010Entity;
import coop.kyushu.gyomu_kanri.entity.msk.Pmsk010ListItem;
import coop.kyushu.gyomu_kanri.form.msk.Pmsk010Form;
import coop.kyushu.gyomu_kanri.mapper.msk.Pmsk010Mapper;

/**
 * <pre>
 * 【マスタ】名称／区分管理マスタ区分一覧
 * </pre>
 */
@Service
public class Pmsk010Service {
    /**
	 * サービスクラス
	 */
    @Autowired
    private Pmsk010Mapper pmsk010Mapper;
    /**
	 * 件数チェック
	 * @param form 入力内容
	 * @return データ件数
	 */
    public Pmsk010Entity getCount(Pmsk010Form form){
        Pmsk010Entity pmsk010Entity = new Pmsk010Entity();
        Map<String, Object> param = new HashMap<>();
        param.put("meishoKubun", form.getMeishoKubun()); 
        param.put("meishoKubunNm", form.getMeishoKubunNm()); 
        int count = pmsk010Mapper.getCountByPmsk010(param);
        pmsk010Entity.setCnt(count);
        return pmsk010Entity;
    }
     /**
	 * 一覧検索
	 * @param form 入力内容
	 * @return 初期ページ(Pnks010Entity)
	 */
    public Pmsk010Entity getMeishoKubunList(Pmsk010Form form){
        Pmsk010Entity pmsk010Entity = new Pmsk010Entity();
        Map<String, Object> param = new HashMap<>();
        param.put("meishoKubun", form.getMeishoKubun());
        param.put("meishoKubunNm", form.getMeishoKubunNm());
        List<Pmsk010ListItem> listItems = new ArrayList<>();
        listItems= pmsk010Mapper.getMeishoKubunListByPmsk010(param);
        pmsk010Entity.setMeishoKubunList(listItems);
        return pmsk010Entity;
    }
}

   

