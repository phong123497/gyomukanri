//================================================================================
// ID: PMSK020
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ProgramName: 【マスタ】名称／区分管理マスタコード一覧
// ClassName: Pmsk020Service
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/09/13 | AMC                  | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.service.msk;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coop.kyushu.common.util.DateUtil;
import coop.kyushu.common.util.TantoshaInfoUtil;
import coop.kyushu.gyomu_kanri.entity.msk.Pmsk020Entity;
import coop.kyushu.gyomu_kanri.form.msk.Pmsk020Form;
import coop.kyushu.gyomu_kanri.mapper.msk.Pmsk020Mapper;

@Service
public class Pmsk020Service {

  // 【マスタ】名称／区分管理マスタコード一覧マッパー
  @Autowired
  private Pmsk020Mapper pmsk020Mapper;

  @Autowired
  private TantoshaInfoUtil tantoshaInfoUtil;

  /**
   * 件数チェック
   * 
   * @param params リクエストデータ
   * @return Pmsk020Entity
   */
  public Pmsk020Entity getCountByPmsk020(Pmsk020Form pmsk020Form) {
    Pmsk020Entity pmsk020Entity = new Pmsk020Entity();
    // 名称マスタ(VIEW TABLE)の件数を取得する。
    Map<String, Object> params = new HashMap<>();
    params.put("meishoKubun", pmsk020Form.getMeishoKubun());
    params.put("meishoCd", pmsk020Form.getMeishoCd());
    params.put("meishoCdNm", pmsk020Form.getMeishoCdNm());

    // サービスの戻り値に以下を設定して処理を終了する。
    pmsk020Entity.setCnt(pmsk020Mapper.getCountByPmsk020(params));
    return pmsk020Entity;
  }

  /**
   * 一覧検索
   * 
   * @param params リクエストデータ
   * @return Pmsk020Entity
   */
  public Pmsk020Entity getMeishoCdList(Pmsk020Form pmsk020Form) {
    Pmsk020Entity pmsk020Entity = new Pmsk020Entity();
    // 名称マスタ(VIEW TABLE)よりコード一覧情報を取得する。
    Map<String, Object> params = new HashMap<>();
    params.put("meishoKubun", pmsk020Form.getMeishoKubun());
    params.put("meishoCd", pmsk020Form.getMeishoCd());
    params.put("meishoCdNm", pmsk020Form.getMeishoCdNm());

    // サービスの戻り値に以下を設定して処理を終了する。
    // ** TODO CHECK Pmsk020ListItems **
    pmsk020Entity.setPmsk020ListItems(pmsk020Mapper.getMeishoCdListByPmsk020(params));
    return pmsk020Entity;
  }

  /**
   * 存在チェック
   * 
   * @param params リクエストデータ
   * @return Pmsk020Entity
   */
  public Pmsk020Entity checkExist(Pmsk020Form pmsk020Form) {
    Pmsk020Entity pmsk020Entity = new Pmsk020Entity();
    // 「存在チェック」サービスとして、以下の内容を実行する。
    Map<String, Object> params = new HashMap<>();
    params.put("meishoKubun", pmsk020Form.getMeishoKubun());
    params.put("meishoCd", pmsk020Form.getMeishoCd());

    // サービスの戻り値に以下を設定して処理を終了する。
    pmsk020Entity.setExists(pmsk020Mapper.getCountDeleteTargetByPmsk020(params) == 0 ? false : true);
    return pmsk020Entity;
  }

  /**
   * 削除
   * 
   * @param params リクエストデータ
   */
  public void delete(Pmsk020Form pmsk020Form) {
    Map<String, Object> params = new HashMap<>();
    params.put("meishoCd", pmsk020Form.getMeishoKubun());
    // 名称データを取得する。
    int kanriKbn = pmsk020Mapper.getKanriKbnByPmsk020(params);
    // 削除実行
    Map<String, Object> deleteParams = new HashMap<>();
    deleteParams.put("updymd", DateUtil.toBigDecimal(DateUtil.getCurrentDate()));
    deleteParams.put("kushn_usr", tantoshaInfoUtil.getTansCd());
    deleteParams.put("kushn_prgrm", "PMSK020");
    deleteParams.put("kushn_nchj", DateUtil.getCurrentTimestamp());
    deleteParams.put("meishoKubun", pmsk020Form.getMeishoKubun());
    if (kanriKbn == 0) {
      // 入力用Formの名称コードリストから名称コードを1件ずつ取り出し反復する。
      for (int i = 0; i < pmsk020Form.getMeishoCdList().size(); i++) {
        
        deleteParams.put("meishoCd", pmsk020Form.getMeishoCdList().get(i));
        // 名称マスタを削除する。
        pmsk020Mapper.deleteMstmeismByPmsk020(deleteParams);
      }
    } else {
      for (int i = 0; i < pmsk020Form.getMeishoCdList().size(); i++) {
        deleteParams.put("meishoCd", pmsk020Form.getMeishoCdList().get(i));
        // 名称マスタを削除する。
        pmsk020Mapper.deleteMskkbnkrbyPmsk020(deleteParams);
      }
    }
  }
}
