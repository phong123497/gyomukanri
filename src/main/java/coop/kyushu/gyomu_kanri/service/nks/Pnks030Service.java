//================================================================================
// ID: PNKS030
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks030Service
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/10 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.service.nks;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microsoft.sqlserver.jdbc.StringUtils;

import coop.kyushu.common.entity.ServerErrorEntity;
import coop.kyushu.common.exception.ValidationException;
import coop.kyushu.common.util.BeanUtil;
import coop.kyushu.common.util.DateUtil;
import coop.kyushu.common.util.TantoshaInfoUtil;
import coop.kyushu.gyomu_kanri.entity.com.Nsmm060Entity;
import coop.kyushu.gyomu_kanri.entity.com.Nsmt020Entity;
import coop.kyushu.gyomu_kanri.entity.com.Nsmt030Entity;
import coop.kyushu.gyomu_kanri.entity.nks.Pnks030Entity;
import coop.kyushu.gyomu_kanri.entity.nks.Pnks030ShohinInfoItem;
import coop.kyushu.gyomu_kanri.entity.nks.Pnks030TotalItem;
import coop.kyushu.gyomu_kanri.form.nks.Pnks030Form;
import coop.kyushu.gyomu_kanri.mapper.com.Nsmm060Mapper;
import coop.kyushu.gyomu_kanri.mapper.nks.Pnks030Mapper;
import coop.kyushu.gyomu_kanri.service.com.OSBG010;

/**
 * <pre>
 * 【農産企画支援】農産企画情報入力サービス
 * </pre>
 */
@Service
public class Pnks030Service {

	// 共同購入商品コード
	private static final String KYDKONYSHINCD = "kydKonyShinCd";

	// メーカーコード
	private static final String MAKERCD = "makerCd";

	// メーカーコード
	private static final String MAKERCD_NSMM060 = "makercd";

	/** 農産企画情報入力マッパー */
	@Autowired
	private Pnks030Mapper mapper;

	/** 共通_メーカーマスタ マッパー */
	@Autowired
	private Nsmm060Mapper nsmm060Mapper;

	/** 共通_担当者 ツール */
	@Autowired
	private TantoshaInfoUtil tantoshaInfoUtil;

	/**
	 * 農産企画情報入力画面の初期表示
	 * @param form 入力内容
	 * @return 初期データ
	 */
	public Pnks030Entity getInitialData(Pnks030Form form) {
		// SKU
		BigDecimal sku = BigDecimal.ZERO;
		// 計画点数
		BigDecimal planPoint = BigDecimal.ZERO;
		// CQ供給額
		BigDecimal cqSuplyGaku = BigDecimal.ZERO;
		// エフ供給額
		BigDecimal fcpSuplyGaku = BigDecimal.ZERO;
		// SKU2
		BigDecimal sku2 = BigDecimal.ZERO;
		// 計画点数2
		BigDecimal planPoint2 = BigDecimal.ZERO;
		// CQ供給額2
		BigDecimal cqSuplyGaku2 = BigDecimal.ZERO;
		// エフ供給額2
		BigDecimal fcpSuplyGaku2 = BigDecimal.ZERO;
		// 野菜
		Pnks030TotalItem item01 = new Pnks030TotalItem();
		// 果物
		Pnks030TotalItem item02 = new Pnks030TotalItem();
		// エフ供給額
		BigDecimal fcpSuplyGakuSum = BigDecimal.ZERO;
		// CQ売価
		BigDecimal cqUriSum = BigDecimal.ZERO;
		// CQ原価
		BigDecimal cqGenSum = BigDecimal.ZERO;

		BigDecimal baiTanDivisorCount = BigDecimal.ZERO;
		BigDecimal fcpSuplyGakuDivisorCount = BigDecimal.ZERO;
		// TOTAL
		Pnks030TotalItem item03 = new Pnks030TotalItem();
		List<Pnks030TotalItem> backTotalItem = new ArrayList<>();

		// 伝票ヘッダ部1部用の農産企画商品取得する。
		Map<String, Object> param = BeanUtil.toMap(form);
		// 共同購入商品コード=入力用Form.共同購入商品コード
		param.put("kyshincd", form.getKydKonyShinCd());
		Pnks030Entity nousanProjinShinByPnks030 = mapper.getNousanProjinShinByPnks030(param);

		if (nousanProjinShinByPnks030 == null) {
			// データが取得できなかった場合
			throw new ValidationException(new ServerErrorEntity("NKS0009E", List.of("農産企画商品マスタ")));
		}

		// 一覧明細を取得する。
		List<Pnks030ShohinInfoItem> nousanProjinShinInfoForHeaderList = mapper
				.getNousanProjinShinInfoForIchiranByPnks030(param);
		nousanProjinShinByPnks030.setShohinInfoList(nousanProjinShinInfoForHeaderList);

		// 伝票ヘッダ部２部用の農産企画商品情報取得する。
		List<Pnks030TotalItem> totalItemList = mapper.getNousanProjinShinInfoForHeaderByPnks030(param);
		// 初期　データを設定する。
		item01.setSku(BigDecimal.ZERO);
		item01.setPlanPoint(BigDecimal.ZERO);
		item01.setCqSuplyGaku(BigDecimal.ZERO);
		item01.setCqGrp(BigDecimal.ZERO);
		item01.setFcpSuplyGaku(BigDecimal.ZERO);
		item01.setFcpGrp(BigDecimal.ZERO);
		item02.setSku(BigDecimal.ZERO);
		item02.setPlanPoint(BigDecimal.ZERO);
		item02.setCqSuplyGaku(BigDecimal.ZERO);
		item02.setCqGrp(BigDecimal.ZERO);
		item02.setFcpSuplyGaku(BigDecimal.ZERO);
		item02.setFcpGrp(BigDecimal.ZERO);
		// 伝票ヘッダ部２を再集計する。 
		for (Pnks030ShohinInfoItem item : nousanProjinShinInfoForHeaderList) {
			if ("8792".equals(item.getKaiinSeikyCd())) {
				if ("01".equals(nousanProjinShinByPnks030.getBumonCd())) {
					// ローカル変数.SKU=1
					sku = new BigDecimal(1);
					// ローカル変数.計画点数=計画数合計の合計
					planPoint = planPoint.add(item.getPlanSu());
					// ローカル変数.CQ供給額=計画数×商品一覧明細.供給単価の合計
					cqSuplyGaku = cqSuplyGaku.add(item.getPlanSu().multiply(item.getSuplyTan()));
					// ローカル変数.エフ供給額=計画数×商品一覧明細.本体価格の合計
					fcpSuplyGaku = fcpSuplyGaku.add(item.getPlanSu().multiply(item.getHontaiKakaku()));
					item01.setBmnNm("野菜");
					item01.setSku(sku);
					item01.setPlanPoint(planPoint);
					item01.setCqSuplyGaku(cqSuplyGaku);
					item01.setCqGrp(BigDecimal.ZERO);
					item01.setFcpSuplyGaku(fcpSuplyGaku);
					item01.setFcpGrp(BigDecimal.ZERO);
				} else if ("02".equals(nousanProjinShinByPnks030.getBumonCd())) {
					// ローカル変数.SKU2=1
					sku2 = new BigDecimal(1);
					// ローカル変数.計画点数2=計画数合計の合計
					planPoint2 = planPoint2.add(item.getPlanSu());
					// ローカル変数.CQ供給額2=計画数×商品一覧明細.供給単価の合計
					cqSuplyGaku2 = cqSuplyGaku2.add(item.getPlanSu().multiply(item.getSuplyTan()));
					// ローカル変数.エフ供給額2=計画数×商品一覧明細.本体価格の合計
					fcpSuplyGaku2 = fcpSuplyGaku2.add(item.getPlanSu().multiply(item.getHontaiKakaku()));
					item02.setBmnNm("果物");
					item02.setSku(sku2);
					item02.setPlanPoint(planPoint2);
					item02.setCqSuplyGaku(cqSuplyGaku2);
					item02.setCqGrp(BigDecimal.ZERO);
					item02.setFcpSuplyGaku(fcpSuplyGaku2);
					item02.setFcpGrp(BigDecimal.ZERO);
				}
			}
		}
		if (ObjectUtils.isNotEmpty(totalItemList)) {

			for (Pnks030TotalItem pcyu030TotalItem : totalItemList) {
				if (pcyu030TotalItem.getBaiTan().compareTo(BigDecimal.ZERO) == 0) {
					baiTanDivisorCount = BigDecimal.ONE;
				} else {
					baiTanDivisorCount = pcyu030TotalItem.getBaiTan();
				}

				if (pcyu030TotalItem.getFcpSuplyGaku().compareTo(BigDecimal.ZERO) == 0) {
					fcpSuplyGakuDivisorCount = BigDecimal.ONE;
				} else {
					fcpSuplyGakuDivisorCount = pcyu030TotalItem.getFcpSuplyGaku();
				}

				fcpSuplyGakuSum = fcpSuplyGakuSum.add(pcyu030TotalItem.getFcpSuplyGaku());
				cqUriSum = cqUriSum.add(pcyu030TotalItem.getBaiTan());
				cqGenSum = cqGenSum.add(pcyu030TotalItem.getGenTan());

				if ("01".equals(pcyu030TotalItem.getBmnCd())) {
					// 再集計後商品合計明細リスト.部門名='野菜'
					item01.setBmnNm("野菜");
					// 再集計後商品合計明細リスト.一件目.SKU=商品合計明細リスト(n).SKU + ローカル変数.SKU
					item01.setSku(sku.add(pcyu030TotalItem.getSku()));
					// 再集計後商品合計明細リスト.一件目.計画点数=商品合計明細リスト(n).計画数 + ローカル変数.計画点数
					item01.setPlanPoint(planPoint.add(pcyu030TotalItem.getPlanPoint()));
					// 再集計後商品合計明細リスト.一件目.CQ供給額=商品合計明細リスト(n).CQ売価 + ローカル変数.CQ供給額
					item01.setCqSuplyGaku(cqSuplyGaku.add(pcyu030TotalItem.getBaiTan()));
					// 再集計後商品合計明細リスト.一件目.CQGRP=(商品合計明細リスト(n).CQ売価 - 商品合計明細リスト(n).CQ原価) /商品合計明細リスト(n). CQ売価
					item01.setCqGrp((pcyu030TotalItem.getBaiTan().subtract(pcyu030TotalItem.getGenTan())
							.divide(baiTanDivisorCount, 5, RoundingMode.HALF_UP)));
					// 再集計後商品合計明細リスト.一件目.エフ供給額=商品合計明細リスト(n).エフ供給額 + ローカル変数.エフ供給額
					item01.setFcpSuplyGaku(fcpSuplyGaku.add(pcyu030TotalItem.getFcpSuplyGaku()));
					// 再集計後商品合計明細リスト.一件目.エフGRP=(商品合計明細リスト(n).エフ供給額 - 商品合計明細リスト(n).CQ売価) /商品合計明細リスト(n). エフ供給額
					item01.setFcpGrp(pcyu030TotalItem.getFcpSuplyGaku()
							.subtract(pcyu030TotalItem.getBaiTan())
							.divide(fcpSuplyGakuDivisorCount, 5, RoundingMode.HALF_UP));
				} else if ("02".equals(pcyu030TotalItem.getBmnCd())) {
					// 再集計後商品合計明細リスト.部門名='果物'
					item02.setBmnNm("果物");
					// 再集計後商品合計明細リスト.二件目.SKU=商品合計明細リスト(n).SKU + ローカル変数SKU2
					item02.setSku(sku2.add(pcyu030TotalItem.getSku()));
					// 再集計後商品合計明細リスト.二件目.計画点数=商品合計明細リスト(n).計画数 + ローカル変数.計画点数2
					item02.setPlanPoint(planPoint2.add(pcyu030TotalItem.getPlanPoint()));
					// 再集計後商品合計明細リスト.二件目.CQ供給額=商品合計明細リスト(n).CQ売価 + ローカル変数.CQ供給額2
					item02.setCqSuplyGaku(cqSuplyGaku2.add(pcyu030TotalItem.getBaiTan()));
					// 再集計後商品合計明細リスト.二件目.CQGRP=(商品合計明細リスト(n).CQ売価 - 商品合計明細リスト(n).CQ原価) /商品合計明細リスト(n). CQ売価
					item02.setCqGrp((pcyu030TotalItem.getBaiTan().subtract(pcyu030TotalItem.getGenTan())
							.divide(baiTanDivisorCount, 5, RoundingMode.HALF_UP)));
					// 再集計後商品合計明細リスト.二件目.エフ供給額=商品合計明細リスト(n).エフ供給額 + ローカル変数.エフ供給額2
					item02.setFcpSuplyGaku(fcpSuplyGaku2.add(pcyu030TotalItem.getFcpSuplyGaku()));
					// 再集計後商品合計明細リスト.二件目.エフGRP=(商品合計明細リスト(n).エフ供給額 - 商品合計明細リスト(n).CQ売価) /商品合計明細リスト(n). エフ供給額
					item02.setFcpGrp(pcyu030TotalItem.getFcpSuplyGaku()
							.subtract(pcyu030TotalItem.getBaiTan())
							.divide(fcpSuplyGakuDivisorCount, 5, RoundingMode.HALF_UP));

				}
			}
		}
		if (cqUriSum.compareTo(BigDecimal.ZERO) == 0) {
			baiTanDivisorCount = BigDecimal.ONE;
		} else {
			baiTanDivisorCount = cqUriSum;
		}

		if (fcpSuplyGakuSum.compareTo(BigDecimal.ZERO) == 0) {
			fcpSuplyGakuDivisorCount = BigDecimal.ONE;
		} else {
			fcpSuplyGakuDivisorCount = fcpSuplyGakuSum;
		}
		// 再集計後商品合計明細リスト.合計.部門名='TOTAL'
		item03.setBmnNm("TOTAL");
		// 再集計後商品合計明細リスト.合計.SKU=一件目.SKU + 二件目.SKU
		item03.setSku(item01.getSku().add(item02.getSku()));
		// 再集計後商品合計明細リスト.合計.計画点数=一件目.計画点数 + 二件目.計画点数
		item03.setPlanPoint(item01.getPlanPoint().add(item02.getPlanPoint()));
		// 再集計後商品合計明細リスト.合計.CQ供給額=一件目.CQ供給額 + 二件目.CQ供給額
		item03.setCqSuplyGaku(item01.getCqSuplyGaku().add(item02.getCqSuplyGaku()));
		// 再集計後商品合計明細リスト.合計.CQGRP=一件目.CQGRP + 二件目.CQGRP
		item03.setCqGrp((cqUriSum.subtract(cqGenSum)
				.divide(baiTanDivisorCount, 5, RoundingMode.HALF_UP)));
		// 再集計後商品合計明細リスト.合計.エフ供給額=一件目.エフ供給額 + 二件目.エフ供給額
		item03.setFcpSuplyGaku(item01.getFcpSuplyGaku().add(item02.getFcpSuplyGaku()));
		// 再集計後商品合計明細リスト.合計.エフGRP=一件目.エフGRP + 二件目.エフGRP
		item03.setFcpGrp(fcpSuplyGakuSum
				.subtract(cqUriSum)
				.divide(fcpSuplyGakuDivisorCount, 5, RoundingMode.HALF_UP));

		if (StringUtil.isNotBlank(item01.getBmnNm())) {
			backTotalItem.add(item01);
		}
		if (StringUtil.isNotBlank(item02.getBmnNm())) {
			backTotalItem.add(item02);
		}
		if (backTotalItem.size() > 0) {
			backTotalItem.add(item03);
		}

		nousanProjinShinByPnks030.setShohinList(backTotalItem);

		// メーカーコンボボックスリスト取得する
		param.put("delFlg", "0");
		List<Nsmm060Entity> nsmm060Data = nsmm060Mapper.getMakerList(param);

		nousanProjinShinByPnks030.setMakerCboxList(nsmm060Data);

		// 一覧明細を編集する。
		for (int i = 0; i < nousanProjinShinInfoForHeaderList.size(); i++) {
			if (nousanProjinShinInfoForHeaderList.get(i).getMaker() != null) {

				Map<String, Object> cboxParam = new HashMap<String, Object>();
				cboxParam.put(KYDKONYSHINCD, form.getKydKonyShinCd());
				cboxParam.put(MAKERCD, nousanProjinShinInfoForHeaderList.get(i).getMaker());

				// 産地コンボボックス取得する
				List<Nsmt020Entity> santiList = mapper.getSantiCboxByPnks030(cboxParam);
				nousanProjinShinInfoForHeaderList.get(i).setSantiCboxList(santiList);

				// 等階級コンボボックス取得する
				List<Nsmt030Entity> tokaikyList = mapper.getTokaikyCboxByPnks030(cboxParam);
				nousanProjinShinInfoForHeaderList.get(i).setTokaikyCboxList(tokaikyList);
			}
		}
		nousanProjinShinByPnks030.setShohinInfoList(nousanProjinShinInfoForHeaderList);

		return nousanProjinShinByPnks030;
	}

	/**
	 * 農産企画情報入力画面の産地コンボ取得処理
	 * @param form 入力内容
	 * @return 産地コンボ
	 */
	public List<Nsmt020Entity> getSantiCboxCbox(Pnks030Form form) {
		List<Nsmt020Entity> santiCboxCbox = new ArrayList<Nsmt020Entity>();
		if (form.getMakerCd() != null && form.getMakerCd() != "") {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put(KYDKONYSHINCD, form.getKydKonyShinCd());
			param.put(MAKERCD, form.getMakerCd());
			santiCboxCbox = mapper.getSantiCboxByPnks030(param);
		}

		return santiCboxCbox;
	}

	/**
	 * 農産企画情報入力画面の等階級コンボ取得処理
	 * @param form 入力内容
	 * @return 等階級コンボ
	 */
	public List<Nsmt030Entity> getTokaikyCbox(Pnks030Form form) {
		List<Nsmt030Entity> tokaikyCbox = new ArrayList<Nsmt030Entity>();
		if (form.getMakerCd() != null && form.getMakerCd() != "") {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put(KYDKONYSHINCD, form.getKydKonyShinCd());
			param.put(MAKERCD, form.getMakerCd());
			tokaikyCbox = mapper.getTokaikyCboxByPnks030(param);
		}

		return tokaikyCbox;
	}

	/**
	 * 農産企画情報入力画面の保存処理
	 * @param form 入力内容
	 * @return 保存後データ
	 */
	public Pnks030Entity save(Pnks030Form form) {
		// チェックを行う
		// 組合単価チェック
		// 税抜組価MAX
		BigDecimal taxPriceMax = BigDecimal.ZERO;
		// 変数税抜組価MIN
		BigDecimal taxPriceMin = BigDecimal.ZERO;
		Map<String, Object> param = BeanUtil.toMap(form);
		taxPriceMax = form.getShohinInfoList().get(0).getZeikomiKakaku();
		taxPriceMin = form.getShohinInfoList().get(0).getZeikomiKakaku();
		for (int i = 0; i < form.getShohinInfoList().size(); i++) {
			if (form.getShohinInfoList().get(i).getZeikomiKakaku().compareTo(taxPriceMax) == 1) {
				// 税抜組価MAX=入力用Form.商品一覧明細リストの中で一番大きい税抜組価
				taxPriceMax = form.getShohinInfoList().get(i).getZeikomiKakaku();
			}
			if (form.getShohinInfoList().get(i).getZeikomiKakaku().compareTo(taxPriceMin) == -1) {
				// 税抜組価MIN=入力用Form.商品一覧明細リストの中で一番小さい税抜組価
				taxPriceMin = form.getShohinInfoList().get(i).getZeikomiKakaku();
			}
		}
		if (taxPriceMax.compareTo(taxPriceMin) != 0) {
			throw new ValidationException(new ServerErrorEntity("NKS0002E"));
		}

		// 禁則チェック
		if ("1".equals(form.getKinsokuChkKbn())) {
			for (int i = 0; i < form.getShohinInfoList().size(); i++) {
				// 税込組価
				BigDecimal zekomi = OSBG010.calTax(form.getShohinInfoList().get(i).getHontaiKakaku(), 2,
						new BigDecimal(form.getShohziRate()), new BigDecimal(form.getZeinukiCalcKbn()),
						new BigDecimal(form.getZeikomiCalcKbn()));
				// 税抜組価
				BigDecimal zenuki = OSBG010.calTax(form.getShohinInfoList().get(i).getZeikomiKakaku(), 1,
						new BigDecimal(form.getShohziRate()), new BigDecimal(form.getZeinukiCalcKbn()),
						new BigDecimal(form.getZeikomiCalcKbn()));
				// 商品一覧明細リスト(i).本体価格と税抜組価」と一致しない場合場合
				if (zenuki.compareTo(form.getShohinInfoList().get(i).getHontaiKakaku()) != 0) {
					throw new ValidationException(new ServerErrorEntity("NKS0003E"));
				}

				// 商品一覧明細リスト(i).税込組価と税込組価」と一致しない場合場合
				if (zekomi.compareTo(form.getShohinInfoList().get(i).getZeikomiKakaku()) != 0) {
					throw new ValidationException(new ServerErrorEntity("NKS0003E"));
				}
			}
		}
		param.put("delFlg", "0");
		for (int i = 0; i < form.getShohinInfoList().size(); i++) {
			// メーカーマスタ存在チェック
			param.put(MAKERCD_NSMM060, form.getShohinInfoList().get(i).getMaker());
			StringBuilder makerRef = new StringBuilder();
			List<Nsmm060Entity> nsmm060Data = nsmm060Mapper.getMakerList(param);
			// データが取得できた場合
			if (nsmm060Data.size() == 0) {
				// メッセージ：NKS0009E：　農産企画商品マスタデータがありません。
				makerRef.append("maker").append(1001 + i);
				throw new ValidationException(
						new ServerErrorEntity("NKS0009E", Arrays.asList("メーカー"), makerRef.toString()));
			}
		}

		Map<String, Object> saveParam = new HashMap<>();
		// ユーザーコード
		saveParam.put("userid", tantoshaInfoUtil.getTansCd());
		// ＣＱ企画番号
		saveParam.put("cqkikano", form.getCqkikano());
		// 共同購入商品コード
		saveParam.put("kyshincd", form.getKydKonyShinCd());
		// 納品時容器区分
		saveParam.put("delvJiYokiKbn", form.getDelvJiYokiKbn());
		// シール貼付有無区分
		saveParam.put("sealPasteUmuKbn", form.getSealPasteUmuKbn());
		// 資材名
		saveParam.put("shizaiNm", form.getShizaiNm());
		// プログラム
		saveParam.put("prgrm", "PNKS030");
		// システム日時
		saveParam.put("nchj", DateUtil.getCurrentTimestamp());
		// 農産企画商品更新ワークを削除
		mapper.deleteNousanProjinShinByPnks030(saveParam);

		// 農産企画商品更新ワークを登録
		for (int i = 0; i < form.getShohinInfoList().size(); i++) {
			Pnks030ShohinInfoItem listItem = form.getShohinInfoList().get(i);
			// バッチＮｏ
			saveParam.put("batchno", listItem.getBatchNo());
			// 週・特価
			saveParam.put("tokka", listItem.getTokka());
			// メーカーコード
			saveParam.put("maker", listItem.getMaker());
			// 産地コード
			if (StringUtils.isEmpty(listItem.getSantiCd())) {
				saveParam.put("santiCd", null);
			} else {
				saveParam.put("santiCd", listItem.getSantiCd());
			}
			// 産地名称
			saveParam.put("santi", listItem.getSanti());
			// 等階級コード
			if (StringUtils.isEmpty(listItem.getTouClassCd())) {
				saveParam.put("touClassCd", null);
			} else {
				saveParam.put("touClassCd", listItem.getTouClassCd());
			}
			// 等階級名称
			saveParam.put("touClass", listItem.getTouClass());
			// 加工区分
			saveParam.put("kako", listItem.getKako());
			// 計画数
			saveParam.put("planSu", listItem.getPlanSu());
			// 税抜組合員単価
			saveParam.put("hontaiKakaku", listItem.getHontaiKakaku());
			// 税込組合員単価
			saveParam.put("zeikomiKakaku", listItem.getZeikomiKakaku());
			// ＣＱ仕入原単価
			saveParam.put("shiireTan", listItem.getShiireTan());
			// ＣＱ供給売単価
			saveParam.put("suplyTan", listItem.getSuplyTan());
			// 加工単価
			saveParam.put("kakoTan", listItem.getKakoTan());
			// 資材単価
			saveParam.put("shizaiTan", listItem.getShizaiTan());
			// 原料単価
			saveParam.put("genryoTan", listItem.getGenryoTan());
			// 委託単価
			saveParam.put("itakTan", listItem.getItakTan());

			mapper.saveNousanProjinShinByPnks030(saveParam);
		}
		// プロシージャ「NSSP020」を呼び出し
		Map<String, Object> storageParam = new HashMap<String, Object>();
		storageParam.put("userid", tantoshaInfoUtil.getTansCd());
		storageParam.put("cqkikano", form.getCqkikano());
		storageParam.put("kyshincd", form.getKydKonyShinCd());
		storageParam.put("prgrm", "PNKS030");
		mapper.nssp020ByPnks030(storageParam);

		return getInitialData(form);
	}

	/**
	 * 農産企画情報入力画面の次の商品処理
	 * @param form 入力内容
	 * @return 次の商品処理データ
	 */
	public Pnks030Entity getNextShin(Pnks030Form form) {
		List<Pnks030Entity> list = mapper.getNousanProjinShinListByPnks030(BeanUtil.toMap(form));

		String nextCqkikano = form.getCqkikano();
		String nextKydKonyShinCd = form.getKydKonyShinCd();

		if (list.size() == 0) {
			throw new ValidationException(new ServerErrorEntity("NKS0001I", List.of("最後")));
		}

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getKydKonyShinCd().equals(form.getKydKonyShinCd())) {
				if (i == (list.size() - 1)) {
					throw new ValidationException(new ServerErrorEntity("NKS0001I", List.of("最後")));
				}
				nextCqkikano = list.get(i + 1).getCqkikano();
				nextKydKonyShinCd = list.get(i + 1).getKydKonyShinCd();
			}
		}

		form.setCqkikano(nextCqkikano);
		form.setKydKonyShinCd(nextKydKonyShinCd);

		return getInitialData(form);
	}

	/**
	 * 農産企画情報入力画面の前の商品処理
	 * @param form 入力内容
	 * @return 前の商品処理データ
	 */
	public Pnks030Entity getBfShin(Pnks030Form form) {
		List<Pnks030Entity> list = mapper.getNousanProjinShinListByPnks030(BeanUtil.toMap(form));

		String bfCqkikano = form.getCqkikano();
		String bfKydKonyShinCd = form.getKydKonyShinCd();

		if (list.size() == 0) {
			throw new ValidationException(new ServerErrorEntity("NKS0001I", List.of("先頭")));
		}

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getKydKonyShinCd().equals(form.getKydKonyShinCd())) {
				if (i == 0) {
					throw new ValidationException(new ServerErrorEntity("NKS0001I", List.of("先頭")));
				}
				bfCqkikano = list.get(i - 1).getCqkikano();
				bfKydKonyShinCd = list.get(i - 1).getKydKonyShinCd();
			}
		}

		form.setCqkikano(bfCqkikano);
		form.setKydKonyShinCd(bfKydKonyShinCd);

		return getInitialData(form);
	}
}
