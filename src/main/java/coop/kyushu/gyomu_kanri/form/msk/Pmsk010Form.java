//================================================================================
// ID: PMSK010
// ProjectName: 【マスタ】名称／区分管理マスタ区分一覧
// SystemName: 名称／区分管理マスタ区分一覧
// ClassName: PMSK010Form
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// 01.00.00 | 2023/09/14 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.form.msk;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
/**
 * <pre>
 * 【マスタ】名称／区分管理マスタ区分一覧
 * </pre>
 */
@Getter
@Setter
public class Pmsk010Form implements Serializable {
	
	private static final long serialVersionUID = 1L;
    // 名称区分
    private String meishoKubun; 
    // 名称区分名
    private String meishoKubunNm;
}
