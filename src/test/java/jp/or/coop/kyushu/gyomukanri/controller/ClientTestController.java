package jp.or.coop.kyushu.gyomukanri.controller;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import coop.kyushu.gyomu_kanri.entity.ext.Pext020JigyoshoItem;
import coop.kyushu.gyomu_kanri.form.ext.Pext020Form;

@RestController
@RequestMapping("ext/pext020")
public class ClientTestController {
	/** ロガー */
	private static final Logger LOGGER = LoggerFactory.getLogger(ClientTestController.class);

	@RequestMapping(value = "/search/jigyosho", method = RequestMethod.POST)
	public Pext020JigyoshoItem getJigyoshoList(Pext020Form form) throws Exception {

		LOGGER.debug("form = " + PropertyUtils.describe(form));

		Pext020JigyoshoItem ent = new Pext020JigyoshoItem();

		// テスト用に一覧のデータを設定
		// 複数件表示するのであればループなどで作成
		ent.setJigyoNm("テスト事業所名");

		return ent;

	}

}
