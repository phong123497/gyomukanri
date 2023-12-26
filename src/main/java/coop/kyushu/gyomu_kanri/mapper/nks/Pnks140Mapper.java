//================================================================================
// ID: PNKS140
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Pnks140Mapper
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/06 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.mapper.nks;

import java.util.Map;

import coop.kyushu.gyomu_kanri.entity.nks.Pnks140Entity;

/**
 * 【農産企画支援】メーカーマスタメンテナンスマッパー
 */
public interface Pnks140Mapper {

	/**
	 * メーカーマスタ情報取得
	 * @param makerCd メーカーコード
	 * @return Pnks140Entity 返されたデータ
	 */
	Pnks140Entity getMakerInfoByPnks140(String makerCd);

	/**
	 * メーカーマスタ情報登録
	 * @param param 挿入されたデータ
	 */
	void insertMakerInfoByPnks140(Map<String, Object> param);

	/**
	 * メーカーマスタ情報更新
	 * @param param 更新されたデータ
	 */
	void updateMakerByPnks140(Map<String, Object> param);
}
