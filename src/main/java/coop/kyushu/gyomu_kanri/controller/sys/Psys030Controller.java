//================================================================================
// ID: PSYS030
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ProgramName: パスワード変更
// ClassName: Psys030Controller
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/01 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.controller.sys;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import coop.kyushu.gyomu_kanri.entity.sys.Psys030Entity;
import coop.kyushu.gyomu_kanri.form.sys.Psys030Form;
import coop.kyushu.gyomu_kanri.service.sys.Psys030Service;

/**
 * <pre>
 * パスワード変更コントローラ
 * </pre>
 */
@RestController
@RequestMapping("sys/psys030")
public class Psys030Controller {

	/**
	 * パスワード変更サービス
	 */
	@Autowired
	Psys030Service service;

	/**
	 * パスワード変更画面遷移
	 * @return ユーザID、および更新日
	 */
	@RequestMapping(value = "/init", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Psys030Entity getInitialData(@RequestBody Psys030Form form) {

		return service.getInitialData();
	}

	/**
	 * パスワード変更画面の保存前確認処理
	 * @throws NoSuchAlgorithmException
	 */
	@RequestMapping(value = "/edit/check", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public void checkPassword(@RequestBody Psys030Form form) throws NoSuchAlgorithmException {

		service.checkPassword(form);
	}

	/**
	 * パスワード変更画面の保存処理
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public void changePassword(@RequestBody Psys030Form form) throws NoSuchAlgorithmException {

		service.changePassword(form);
	}
}
