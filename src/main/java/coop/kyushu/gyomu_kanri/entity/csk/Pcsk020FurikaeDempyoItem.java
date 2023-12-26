//================================================================================
// ID: PCSK020
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pcsk020FurikaeDempyoItem
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
public class Pcsk020FurikaeDempyoItem implements Serializable {

	@JsonIgnore
	private static final long serialVersionUID = 1L;

	/** 計上年月 */
	private String kym;

	/** 伝票番号 */
	private String denno;

	/** 行番号 */
	private String gyono;

	/** 伝票日付 */
	private String dendate;

	/** 出庫側センタコード */
	private String scentcd;

	/** 入庫側センタコード */
	private String ncentcd;

	/** 商品コード */
	private String cqshincd;

	/** 数量 */
	private String suryo;

	/** 金額 */
	private String kingaku;

	/** 発生元区分 */
	private String haseimkb;

	/** 登録日 */
	private String addymd;

	/** 登録時間 */
	private String addtime;

	/** 登録者ＩＤ */
	private String userid;
}
