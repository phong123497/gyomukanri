//================================================================================
// ID: PSYS030
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: Psys030Mapper
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/01 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.mapper.sys;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import coop.kyushu.gyomu_kanri.entity.sys.Psys030Entity;
import coop.kyushu.gyomu_kanri.entity.sys.Psys030PasswordInfo;

/**
 * <pre>
 * パスワード変更マッパー
 * </pre>
 */
public interface Psys030Mapper {

	/**
	 * 担当者IDを取得する。
	 * @param tansCd	担当者CD
	 * @return
	 */
	Psys030Entity getTantoshaInfo(@Param("tansCd") String tansCd);

	/**
	 * パスワードを取得する。
	 * @param tansCd	担当者CD
	 * @return
	 */
	Psys030PasswordInfo getPassInfo(@Param("tansCd") String tansCd);

	/**
	 * パスワード、パスワード変更日、ログインエラー回数を更新する。
	 * @param tansCd	担当者CD
	 * @param pass		パスワード
	 * @param 更新件数
	 */
	int putPassYmdInfo(Map<String, Object> map);

}
