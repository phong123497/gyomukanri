//================================================================================
// ID: PMSK010
// ProjectName: 【マスタ】名称／区分管理マスタ区分一覧
// SystemName: 名称／区分管理マスタ区分一覧
// ClassName: PMSK010Entity
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// 01.00.00 | 2023/09/14 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.entity.msk;

import java.io.Serializable;
import java.util.List;

import coop.kyushu.gyomu_kanri.form.msk.Pmsk010Form;
import lombok.Getter;
import lombok.Setter;
/**
 * <pre>
 * 【マスタ】名称／区分管理マスタ区分一覧
 * </pre>
 */
@Getter
@Setter
public class Pmsk010Entity extends Pmsk010Form implements Serializable{
	private static final long serialVersionUID = 1L;
    // 名称区分リスト
   private List<Pmsk010ListItem> meishoKubunList;
    // 件数
    private int cnt;
}
 