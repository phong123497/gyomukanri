package coop.kyushu.gyomu_kanri.service.com;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * <pre>
 * 【共通部品】消費税計算
 * </pre>
 */
public class OSBG010 {

	/**
	 * 消費税計算
	 * @param tank 単価
	 * @param kbn  区分
	 * @param zeiritu 消費税率
	 * @param zeinkkeikb 税抜計算区分
	 * @param zeikmkeikb 税込計算区分
	 * @return 初期データ
	 */
	public static BigDecimal calTax(BigDecimal tank, int kbn, BigDecimal zeiritu, BigDecimal zeinkkeikb,
			BigDecimal zeikmkeikb) {
		BigDecimal keikbn = BigDecimal.ZERO;
		BigDecimal tmpTank = BigDecimal.ZERO;

		if (kbn == 2) {
			keikbn = zeikmkeikb;
			tmpTank = tank.multiply((zeiritu.add(BigDecimal.ONE)));
		} else {
			keikbn = zeinkkeikb;
			tmpTank = tank.divide((zeiritu.add(BigDecimal.ONE)), 10, RoundingMode.HALF_UP);
		}

		BigDecimal taxResult;
		if (keikbn.compareTo(BigDecimal.ONE) == 0) {
			taxResult = tmpTank.setScale(0, RoundingMode.FLOOR);
		} else if (keikbn.compareTo(new BigDecimal(2)) == 0) {
			taxResult = tmpTank.add(BigDecimal.valueOf(0.999999999)).setScale(0, RoundingMode.FLOOR);
		} else {
			taxResult = tmpTank.add(BigDecimal.valueOf(0.5)).setScale(0, RoundingMode.FLOOR);
		}
		return taxResult;
	}
}
