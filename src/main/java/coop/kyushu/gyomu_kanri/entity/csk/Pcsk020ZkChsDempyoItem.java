//================================================================================
// ID: PCSK020
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pcsk020ZkChsDempyoItem
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/18 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.entity.csk;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【コープ九州商管】CSV出力エンティティ
 * </pre>
 */
@Getter
@Setter
public class Pcsk020ZkChsDempyoItem implements Serializable {

	@JsonIgnore
	private static final long serialVersionUID = 1L;

	/** 伝票番号 */
	private String denno;

	/** 行番号 */
	private String gyono;

	/** 対象月度 */
	private String kym;

	/** センタコード */
	private String centcd;

	/** 事業所区分 */
	private String jigyoskb;

	/** 無店舗大分類 */
	private String daibuncd;

	/** 商品コード */
	private String cqshincd;

	/** 最終仕入価 */
	private String sireTank;

	/** 月末在庫数量 */
	private String hzaiSu;

	/** 月末在庫金額 */
	private String hzaiGak;

	/** 積送中在庫数量 */
	private String sekiSu;

	/** 積送中在庫金額 */
	private String sekiGak;

	/** 登録日 */
	private String addymd;

	/** 登録時間 */
	private String addtime;

	/** 登録者ＩＤ */
	private String userid;
}
