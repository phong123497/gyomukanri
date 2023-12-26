//================================================================================
// ID: PCSK020
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pcsk020VarDempyoCoreItem
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
public class Pcsk020VarDempyoApItem implements Serializable {

	@JsonIgnore
	private static final long serialVersionUID = 1L;

	/** 計上年月 */
	private String kym;

	/** 粗利表分類 */
	private String arabuncd;

	/** 伝票日付 */
	private String dendate;

	/** 伝票区分 */
	private String denkb;

	/** 伝票番号 */
	private String denno;

	/** 行番号 */
	private String gyono;

	/** 貸借反転区分 */
	private String tshntkb;

	/** 借方科目コード */
	private String krkmkcd;

	/** 借方補助コード */
	private String krhkmcd;

	/** 借方部門コード */
	private String krbmncd;

	/** 借方取引先区分 */
	private String krtorikb;

	/** 借方取引先コード */
	private String krtoricd;

	/** 借方機能コード１ */
	private String krkncd1;

	/** 借方機能コード２ */
	private String krkncd2;

	/** 借方機能コード３ */
	private String krkncd3;

	/** 借方機能コード４ */
	private String krkncd4;

	/** 借方税処理コード */
	private String krzeicd;

	/** 借方税入力区分 */
	private String krzeinkb;

	/** 借方仕訳金額税抜 */
	private String krsiwgkn;

	/** 借方税額 */
	private String krzeigk;

	/** 借方仕訳金額税込 */
	private String krsiwgkk;

	/** 借方摘要 */
	private String krtekiy;

	/** 貸方科目コード */
	private String kskmkcd;

	/** 貸方補助コード */
	private String kshkmcd;

	/** 貸方部門コード */
	private String ksbmncd;

	/** 貸方取引先区分 */
	private String kstorikb;

	/** 貸方取引先コード */
	private String kstoricd;

	/** 貸方機能コード１ */
	private String kskncd1;

	/** 貸方機能コード２ */
	private String kskncd2;

	/** 貸方機能コード３ */
	private String kskncd3;

	/** 貸方機能コード４ */
	private String kskncd4;

	/** 貸方税処理コード */
	private String kszeicd;

	/** 貸方税入力区分 */
	private String kszeinkb;

	/** 貸方仕訳金額税抜 */
	private String kssiwgkn;

	/** 貸方税額 */
	private String kszeigk;

	/** 貸方仕訳金額税込 */
	private String kssiwgkk;

	/** 貸方適用 */
	private String kstekiy;

	/** 登録日 */
	private String addymd;

	/** 登録時間 */
	private String addtime;

	/** 登録者ＩＤ */
	private String userid;
}
