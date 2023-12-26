//================================================================================
// ID: PSKC030
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pskc030Form
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/11 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.form.skc;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

import coop.kyushu.common.validation.NotSelect;
import coop.kyushu.gyomu_kanri.entity.skc.Rskc03001ListItem;
import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【惣菜加工センター】製造計画数入力フォーム
 * </pre>
 */
@Getter
@Setter
public class Pskc030Form implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 店舗 */
	@NotSelect
	private String tenpo;

	/** 表示週 */
	@NotSelect
	private String hyojiWeek;

	/** 表示順 */
	private String hyojiRank;

	/** 昇順降順 */
	private String ascDesc;

	/** 保存Flg */
	private String saveFlg;

	/** 本部権限 */
	private Boolean isHonsyaKengen;

	/** ログイン店 */
	private String loginShop;

	/** 商品明細リスト */
	@Valid
	private List<Rskc03001ListItem> meisaiList;
}
