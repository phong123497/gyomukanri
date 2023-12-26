//================================================================================
// ID: PNKS040
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks040Form
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/13 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.form.nks;

import java.io.Serializable;

import coop.kyushu.common.validation.Code;
import coop.kyushu.common.validation.FullWidth;
import coop.kyushu.common.validation.HalfWidth;
import coop.kyushu.common.validation.Length;
import coop.kyushu.common.validation.NotEmpty;
import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【農産企画支援】企画案内書印刷フォーム
 * </pre>
 */
@Getter
@Setter
public class Pnks040Form implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 印刷帳票 */
	@NotEmpty(groups = Pnks040Group.OtherGroup.class)
	@Code(length = 1, groups = Pnks040Group.OtherGroup.class)
	@Length(max = 15, groups = Pnks040Group.OtherGroup.class)
	private String prtChohyo;

	/** CQ企画番号開始 */
	@NotEmpty(groups = Pnks040Group.OtherGroup.class)
	@Code(length = 3, groups = Pnks040Group.OtherGroup.class)
	private String cqProjNoStart;

	/** CQ企画番号終了 */
	@NotEmpty(groups = Pnks040Group.KekakuGroup.class)
	@Code(length = 3, groups = Pnks040Group.KekakuGroup.class)
	private String cqProjNoEnd;

	/** メーカー開始 */
	@NotEmpty(groups = Pnks040Group.OtherGroup.class)
	@Code(length = 4, groups = Pnks040Group.OtherGroup.class)
	private String makerStart;

	/** メーカー終了 */
	@Code(length = 4, groups = Pnks040Group.OtherGroup.class)
	private String makerEnd;

	/** 連絡事項 */
	@NotEmpty(groups = Pnks040Group.OtherGroup.class)
	@Length(max = 128, groups = Pnks040Group.OtherGroup.class)
	private String contactJiko;

	/** 連絡先名称（北部） */
	@NotEmpty(groups = Pnks040Group.OtherGroup.class)
	@FullWidth(groups = Pnks040Group.OtherGroup.class)
	@Length(max = 30, groups = Pnks040Group.OtherGroup.class)
	private String contactaddNmHokubu;

	/** 連絡先ＴＥＬ（北部） */
	@NotEmpty(groups = Pnks040Group.OtherGroup.class)
	@HalfWidth(groups = Pnks040Group.OtherGroup.class)
	@Length(max = 15, groups = Pnks040Group.OtherGroup.class)
	private String contactaddTelHokubu;

	/** 連絡先ＦＡＸ（北部） */
	@NotEmpty(groups = Pnks040Group.OtherGroup.class)
	@HalfWidth(groups = Pnks040Group.OtherGroup.class)
	@Length(max = 15, groups = Pnks040Group.OtherGroup.class)
	private String contactaddFaxHokubu;

	/** 連絡先名称（南部） */
	@NotEmpty(groups = Pnks040Group.OtherGroup.class)
	@FullWidth(groups = Pnks040Group.OtherGroup.class)
	@Length(max = 30, groups = Pnks040Group.OtherGroup.class)
	private String contactaddNmNanbu;

	/** 連絡先ＴＥＬ（南部） */
	@NotEmpty(groups = Pnks040Group.OtherGroup.class)
	@HalfWidth(groups = Pnks040Group.OtherGroup.class)
	@Length(max = 15, groups = Pnks040Group.OtherGroup.class)
	private String contactaddTelNanbu;

	/** 連絡先ＦＡＸ（南部） */
	@NotEmpty(groups = Pnks040Group.OtherGroup.class)
	@HalfWidth(groups = Pnks040Group.OtherGroup.class)
	@Length(max = 15, groups = Pnks040Group.OtherGroup.class)
	private String contactaddFaxNanbu;

	/** 納品場所（北部） */
	@NotEmpty(groups = Pnks040Group.OtherGroup.class)
	@FullWidth(groups = Pnks040Group.OtherGroup.class)
	@Length(max = 30, groups = Pnks040Group.OtherGroup.class)
	private String delvPlaceHokubu;

	/** 納品先名称（北部） */
	@NotEmpty(groups = Pnks040Group.OtherGroup.class)
	@FullWidth(groups = Pnks040Group.OtherGroup.class)
	@Length(max = 30, groups = Pnks040Group.OtherGroup.class)
	private String nohinskNmHokubu;

	/** 納品先ＴＥＬ（北部） */
	@NotEmpty(groups = Pnks040Group.OtherGroup.class)
	@HalfWidth(groups = Pnks040Group.OtherGroup.class)
	@Length(max = 15, groups = Pnks040Group.OtherGroup.class)
	private String nohinskTelHokubu;

	/** 納品先ＦＡＸ（北部） */
	@NotEmpty(groups = Pnks040Group.OtherGroup.class)
	@HalfWidth(groups = Pnks040Group.OtherGroup.class)
	@Length(max = 15, groups = Pnks040Group.OtherGroup.class)
	private String nohinskFaxHokubu;

	/** 納品場所（南部） */
	@NotEmpty(groups = Pnks040Group.OtherGroup.class)
	@FullWidth(groups = Pnks040Group.OtherGroup.class)
	@Length(max = 30, groups = Pnks040Group.OtherGroup.class)
	private String delvPlaceNanbu;

	/** 納品先名称（南部） */
	@NotEmpty(groups = Pnks040Group.OtherGroup.class)
	@FullWidth(groups = Pnks040Group.OtherGroup.class)
	@Length(max = 30, groups = Pnks040Group.OtherGroup.class)
	private String nohinskNmNanbu;

	/** 納品先ＴＥＬ（南部） */
	@NotEmpty(groups = Pnks040Group.OtherGroup.class)
	@HalfWidth(groups = Pnks040Group.OtherGroup.class)
	@Length(max = 15, groups = Pnks040Group.OtherGroup.class)
	private String nohinskTelNanbu;

	/** 納品先ＦＡＸ（南部） */
	@NotEmpty(groups = Pnks040Group.OtherGroup.class)
	@HalfWidth(groups = Pnks040Group.OtherGroup.class)
	@Length(max = 15, groups = Pnks040Group.OtherGroup.class)
	private String nohinskFaxNanbu;

	/** 企画期間開始 */
	@HalfWidth(groups = Pnks040Group.KakuteGroup.class)
	@Length(max = 10, groups = Pnks040Group.KakuteGroup.class)
	private String projKknStart;

	/** 企画期間終了 */
	@HalfWidth(groups = Pnks040Group.KakuteGroup.class)
	@Length(max = 10, groups = Pnks040Group.KakuteGroup.class)
	private String projKknEnd;

	/** 価格回答期限 */
	@HalfWidth(groups = Pnks040Group.KakuteGroup.class)
	@Length(max = 10, groups = Pnks040Group.KakuteGroup.class)
	private String kakakuAnsKgn;

	/* 検証グループ */
	public interface Pnks040Group {
		/* 印刷帳票は企画案内書（計画）場合 */
		interface KekakuGroup {

		}

		/* 印刷帳票は価格決定依頼書（確定）場合 */
		interface KakuteGroup {

		}

		/* 検査が必要な項目 */
		interface OtherGroup {

		}
	};
}
