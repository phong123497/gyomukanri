//================================================================================
// ID: PNKS030
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks030Form
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/10 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.form.nks;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

import coop.kyushu.common.validation.Code;
import coop.kyushu.common.validation.FullWidth;
import coop.kyushu.common.validation.Length;
import coop.kyushu.common.validation.NotEmpty;
import coop.kyushu.gyomu_kanri.entity.nks.Pnks030ShohinInfoItem;
import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【農産企画支援】農産企画情報入力フォーム
 * </pre>
 */
@Getter
@Setter
public class Pnks030Form implements Serializable {
	private static final long serialVersionUID = 1L;

	/** ＣＱ企画番号 */
	@NotEmpty
	@Code(length = 3)
	private String cqkikano;

	/** 共同購入商品コード */
	@NotEmpty
	@Code(length = 7)
	private String kydKonyShinCd;

	/** 商品名 */
	private String shinNm;

	/** 規格 */
	private String stdd;

	/** 納品時容器区分 */
	private String delvJiYokiKbn;

	/** シール貼付有無区分 */
	private String sealPasteUmuKbn;

	/** 資材名 */
	@Length(max = 20)
	@FullWidth
	private String shizaiNm;

	/** 商品一覧明細リスト */
	@Valid
	private List<Pnks030ShohinInfoItem> shohinInfoList;

	/** 部門名 */
	private String bmnNm;

	/** 禁則チェック区分 */
	private String kinsokuChkKbn;

	/** 単価計算区分 */
	private String tananoCalcKbn;

	/** 税抜計算区分 */
	private String zeinukiCalcKbn;

	/** 税込計算区分 */
	private String zeikomiCalcKbn;

	/** 消費税率 */
	private String shohziRate;

	/** 共同購入商品コード開始 */
	private String kyshincdStart;

	/** 共同購入商品コード終了 */
	private String kyshincdEnd;

	/** 部門コード */
	private String bumonCd;

	/** メーカーコード */
	private String makerCd;
}
