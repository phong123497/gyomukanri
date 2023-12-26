package coop.kyushu.gyomu_kanri.entity.msk;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pmsk051EnttityCsv {

	String toricd;

	String torikjnm;

	String toriknnm;

	String torikjrm;

	String toriknrm;

	String yubin;

	String yubinno;

	String jusyo1;

	String jusyo2;

	String jusyo3;

	String tel;

	String telno;

	String fax;

	String faxno;

	String toristb;

	String sihasday;

	String sihagesu;

	String sihaday;

	String sekysday;

	String sekygesu;

	String sekyday;

	String centfee_rt;

	String centfee_rz;

	String centfee_dr;

	String edi_denhatkb;

	String edi_ssosinkb;

	String edi_gln;

	String edi_strdate;

	String edi_enddate;

	String ten_meikb;

	String gensirkb;
	// 発注区分（冷蔵＋ＭＤ）
	String webedi_hatukb;
	// 発注フォーマット区分（冷蔵＋ＭＤ）
	String webedi_hatufmkb;
	// 送信先コード（冷蔵＋ＭＤ）
	String webedi_sosncd;

	String webedi_syukhflg;
	// 予約適用開始日
	String yoyak_tekiymd;

	String webedi_hatukb_y;

	String webedi_hatufmkb_y;

	String webedi_sosncd_y;

	String webedi_syukhflg_y;
	// 仕入データ自動確定区分
	String sirjktkb;
	// 請求対象会員生協識別区分
	String sekksskb;
	//営業倉庫区分
	String esokkb;
	// 出庫指示作成区分
	String sksijikb;
	// 最終発注単位区分
	String saihatkb;
	// 取引停止区分
	String toritskb;

	public Pmsk051EnttityCsv(List<String> csvRow) {
		System.out.println("csvRow" + csvRow.size());
		this.toricd = csvRow.get(0);
		this.torikjnm = csvRow.get(1);
		this.toriknnm = csvRow.get(2);
		this.torikjrm = csvRow.get(3);
		this.toriknrm = csvRow.get(4);
		this.yubin = csvRow.get(5);
		this.yubinno = csvRow.get(6);
		this.jusyo1 = csvRow.get(7);
		this.jusyo2 = csvRow.get(8);
		this.jusyo3 = csvRow.get(9);
		this.tel = csvRow.get(10);
		this.telno = csvRow.get(11);
		this.fax = csvRow.get(12);
		this.faxno = csvRow.get(13);
		this.toristb = csvRow.get(14);
		this.sihasday = csvRow.get(15);
		this.sihagesu = csvRow.get(16);
		this.sihaday = csvRow.get(17);
		this.sekysday = csvRow.get(18);
		this.sekygesu = csvRow.get(19);
		this.sekyday = csvRow.get(20);
		this.centfee_rt = csvRow.get(21);
		this.centfee_rz = csvRow.get(22);
		this.centfee_dr = csvRow.get(23);
		this.edi_denhatkb = csvRow.get(24);
		this.edi_ssosinkb = csvRow.get(25);
		this.edi_gln = csvRow.get(26);
		this.edi_strdate = csvRow.get(27);
		this.edi_enddate = csvRow.get(28);
		this.ten_meikb = csvRow.get(29);
		this.gensirkb = csvRow.get(30);
		this.webedi_hatukb = csvRow.get(31);
		this.webedi_hatufmkb = csvRow.get(32);
		this.webedi_sosncd = csvRow.get(33);
		this.webedi_syukhflg = csvRow.get(34);
		this.yoyak_tekiymd = csvRow.get(35);
		this.webedi_hatukb_y = csvRow.get(36);
		this.webedi_hatufmkb_y = csvRow.get(37);
		this.webedi_sosncd_y = csvRow.get(38);
		this.webedi_syukhflg_y = csvRow.get(39);
		this.sirjktkb = csvRow.get(40);
		this.sekksskb = csvRow.get(41);
		this.esokkb = csvRow.get(42);
		this.sksijikb = csvRow.get(43);
		this.saihatkb = csvRow.get(44);
		this.toritskb = csvRow.get(45);
	}

	public List<Integer> getValueFieldsLength() throws IllegalAccessException {
		Field[] fields = this.getClass().getDeclaredFields();
		List<Integer> lengthFields = new ArrayList<>();
		for (Field field : fields) {
			field.setAccessible(true);
			String fieldValue = (String) field.get(this);

			if (fieldValue != null && !fieldValue.isEmpty()) {
				lengthFields.add(fieldValue.length());
			} else {
				lengthFields.add(0);
			}

			field.setAccessible(false);
		}
		return lengthFields;
	}

	public List<String> getvalueFields(List<String> csvRow) throws IllegalAccessException {
		Field[] fields = this.getClass().getDeclaredFields();
		List<String> listValueFields = new ArrayList<>();
		if (csvRow.size() >= 45) {
			for (Field field : fields) {
				field.setAccessible(true);
				String fieldValue = (String) field.get(this);

				listValueFields.add(fieldValue);
				field.setAccessible(false);
			}
		}
		return listValueFields;
	}

	public List<String> getvalue(List<String> csvRow) throws IllegalAccessException {
		Pmsk051EnttityCsv checkId = new Pmsk051EnttityCsv(csvRow);
		List<String> codoAtaiList = new ArrayList<>();
		codoAtaiList.add(checkId.getTen_meikb());
		codoAtaiList.add(checkId.getGensirkb());
		codoAtaiList.add(checkId.getWebedi_hatukb());
		codoAtaiList.add(checkId.getWebedi_hatufmkb());
		codoAtaiList.add(checkId.getWebedi_sosncd());
		codoAtaiList.add(checkId.getWebedi_syukhflg());
		codoAtaiList.add(checkId.getWebedi_hatukb_y());
		codoAtaiList.add(checkId.getWebedi_hatufmkb_y());
		codoAtaiList.add(checkId.getWebedi_sosncd_y());
		codoAtaiList.add(checkId.getWebedi_syukhflg_y());
		codoAtaiList.add(checkId.getSirjktkb());
		codoAtaiList.add(checkId.getSekksskb());
		codoAtaiList.add(checkId.getEsokkb());
		codoAtaiList.add(checkId.getSksijikb());
		codoAtaiList.add(checkId.getSaihatkb());
		codoAtaiList.add(checkId.getToritskb());
		return codoAtaiList;
	}

}
