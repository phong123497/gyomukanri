//================================================================================
// ID: Pmsk040
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pmsk040Service
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/09/14 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.service.msk;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coop.kyushu.common.util.BeanUtil;
import coop.kyushu.gyomu_kanri.entity.msk.Pmsk040ListItem;
import coop.kyushu.gyomu_kanri.form.msk.Pmsk040Form;
import coop.kyushu.gyomu_kanri.mapper.msk.Pmsk040Mapper;

/**
 * <pre>
 * 【マスク】企画商品マスタ一覧サービス
 * </pre>
 */
@Service
public class Pmsk040Service {

	// 【マスク】企画商品マスタ覧マッパー
	@Autowired
	private Pmsk040Mapper Pmsk040Mapper;

	/**
	 * 【マスク】企画商品マスタ一覧の検索処理
	 * @param form 入力内容
	 * @return 企画商品一覧のデータ
	 */
	public List<Pmsk040ListItem> getKikakuShohinList(Pmsk040Form form) {

		// 検索されたデータを保存します
		List<Pmsk040ListItem> backList = Pmsk040Mapper.getKikakuShohinListByPmsk040(BeanUtil.toMap(form));

		return backList;
	}

	/**
	 * 【マスク】企画商品検索画面の検索前処理
	 * @param form 入力内容
	 * @return カウント
	 */
	public int getCount(Pmsk040Form form) {

		return Pmsk040Mapper.getCountByPmsk040(BeanUtil.toMap(form));
	}

}
