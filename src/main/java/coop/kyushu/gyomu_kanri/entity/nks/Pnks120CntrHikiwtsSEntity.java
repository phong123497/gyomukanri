//================================================================================
// ID: PNKS120
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks120CntrKakoShijiEntity1
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/02 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.entity.nks;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【農産企画支援】各種データ出力エンティティ
 * </pre>
 */
@Getter
@Setter
public class Pnks120CntrHikiwtsSEntity implements Serializable {

	@JsonIgnore
	private static final long serialVersionUID = 1L;

	/** 種別 */
	private String shubt;

	/** 受注日 */
	private String juchuDay;

	/** ＳＣ納品日 */
	private String scDelvDay;

	/** 共同購入商品コード */
	private String kydKonyShinCd;

	/** 配達企画回（年） */
	private String haitatProjTimesYear;

	/** 配達企画回（月） */
	private String haitatProjTimesMonth;

	/** 配達企画回（回） */
	private String haitatProjTimesTimes;

	/** 集品区分 */
	private String collectKbn;

	/** 納品形態 */
	private String delvForm;

	/** メーカーコード */
	private String makerCd;

	/** 発注先名 */
	private String hachuskNm;

	/** コープ九州商品コード */
	private String cpkyushuShinCd;

	/** 商品名 */
	private String shinNm;

	/** 規格名カナ */
	private String stddNmKn;

	/** バッチＮｏ */
	private String batchNo;

	/** 数量 */
	private String su;

	/** 予備数量 */
	private String sprSu;

	/** ライン */
	private String line;

	/** ゾーン */
	private String zone;

	/** アドレス */
	private String addrs;
}
