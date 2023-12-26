//================================================================================
// ID: PSKC020
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pskc020Entity
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/11 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.entity.skc;

import java.io.Serializable;

import coop.kyushu.gyomu_kanri.form.skc.Pskc020Form;
import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【惣菜加工センター】製造計画数取込・出力エンティティ
 * </pre>
 */
@Getter
@Setter
public class Pskc020Entity extends Pskc020Form implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 店コード */
	private String shopCd;

	/** 店名 */
	private String shopNm;

	/** 品目コード */
	private String hinmokuCd;

	/** 品名 */
	private String hinNm;

	/** 納品日 */
	private String delvDay;

	/** 受注数 */
	private String juchuSu;

	/** 合計受注数 */
	private String gkeiJuchuSu;
}
