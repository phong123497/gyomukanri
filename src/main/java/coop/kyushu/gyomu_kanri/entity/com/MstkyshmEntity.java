//================================================================================
// ID: 共通
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// ClassName: MstkyshmEntity
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/09/12 | ＡＭＣ               | 新規作成
//================================================================================
package coop.kyushu.gyomu_kanri.entity.com;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 【農産企画支援】共同購入商品マスタ エンティティ
 * </pre>
 */
@Getter
@Setter
public class MstkyshmEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**	共同購入商品コード*/
	private String kyshincd;
	/**	コープ九州商品コード*/
	private String cqshincd;
	/**	商品名カナ*/
	private String shinkn;
	/**	商品名漢字*/
	private String shinkj;
	/**	商品略称漢字*/
	private String shinrkj;
	/**	規格カナ*/
	private String kikakkn;
	/**	ブランド区分*/
	private String brkb;
	/**	ＮＢメーカー区分*/
	private String nbmkkb;
	/**	日生協コード*/
	private String nskcd;
	/**	ＩＴＦコード*/
	private String itfcd;
	/**	ＪＡＮコード１*/
	private String jancd1;
	/**	ＪＡＮコード２*/
	private String jancd2;
	/**	大分類*/
	private String daibncd;
	/**	中分類*/
	private String tyubncd;
	/**	小分類*/
	private String syobncd;
	/**	品種分類コード*/
	private String hinbncd;
	/**	商担コード*/
	private String syotncd;
	/**	日生協商社コード*/
	private String nsksyocd;
	/**	独自企画区分*/
	private String dokikakb;
	/**	企画区分*/
	private String kikakb;
	/**	適用開始日１*/
	private Integer tekiymd1;
	/**	税区分１*/
	private String zeikb1;
	/**	税率１*/
	private BigDecimal zeiritu1;
	/**	原単価１*/
	private BigDecimal gentan1;
	/**	売単価１*/
	private BigDecimal baitan1;
	/**	組合員単価１*/
	private BigDecimal kumitan1;
	/**	税抜組合員単価１*/
	private BigDecimal zenukumi1;
	/**	適用開始日２*/
	private Integer tekiymd2;
	/**	税区分２*/
	private String zeikb2;
	/**	税率２*/
	private BigDecimal zeiritu2;
	/**	原単価２*/
	private BigDecimal gentan2;
	/**	売単価２*/
	private BigDecimal baitan2;
	/**	組合員単価２*/
	private BigDecimal kumitan2;
	/**	税抜組合員単価２*/
	private BigDecimal zenukumi2;
	/**	参考価格*/
	private BigDecimal sankakak;
	/**	沖縄運賃*/
	private BigDecimal okiuntn;
	/**	沖縄ルート区分*/
	private String okiruto;
	/**	ＣＱ－ＧＰＲ*/
	private BigDecimal cqgpr;
	/**	商品ルート*/
	private String shinruto;
	/**	ＱＲＣ通過区分*/
	private String qrctukkb;
	/**	集品区分*/
	private String syuhinkb;
	/**	ＣＱ直送区分*/
	private String tyokuskb;
	/**	定共区分*/
	private String tekyokb;
	/**	宅配区分*/
	private String takhaikb;
	/**	宅配会社区分*/
	private String thkaiskb;
	/**	宅配送料区分*/
	private String thsorykb;
	/**	伝票発行区分*/
	private String denpyokb;
	/**	取扱会員生協（エフ）*/
	private String ehu;
	/**	取扱会員生協（さが）*/
	private String saga;
	/**	取扱会員生協（ララ）*/
	private String rara;
	/**	取扱会員生協（おおいた）*/
	private String oita;
	/**	取扱会員生協（水光社）*/
	private String suikosya;
	/**	取扱会員生協（みやざき）*/
	private String miyazaki;
	/**	取扱会員生協（かごしま）*/
	private String kagosima;
	/**	取扱会員生協（沖縄）*/
	private String okinawa;
	/**	取扱会員生協（店舗）*/
	private String tenpo;
	/**	仕入先コード*/
	private String sirecd;
	/**	発注先コード*/
	private String hatucd;
	/**	取扱開始日*/
	private Integer toriymd;
	/**	計画数*/
	private Integer kekaksu;
	/**	確保数*/
	private Integer kakhosu;
	/**	上限数*/
	private Integer jyognsu;
	/**	返品不可区分*/
	private String henphkkb;
	/**	発注不可区分*/
	private String hatuhkkb;
	/**	発注パターン*/
	private String hatupatn;
	/**	発注区分*/
	private String hatukb;
	/**	ケース区分*/
	private String cskb;
	/**	ケース入数*/
	private Integer csirisu;
	/**	ボール入数*/
	private Integer blirisu;
	/**	リードＴＩＭＥ*/
	private Integer ldtime;
	/**	入荷入数*/
	private Integer nyuirisu;
	/**	供給単位数*/
	private Integer kyotansu;
	/**	エリア区分*/
	private String areakb;
	/**	養生区分*/
	private String yojokb;
	/**	ケース出し区分*/
	private String csdakb;
	/**	オリコン区分*/
	private String oriconkb;
	/**	箱入数*/
	private Integer hakirisu;
	/**	容積率*/
	private Integer yoskritu;
	/**	トレー区分*/
	private String torekb;
	/**	トレー入数*/
	private Integer toririsu;
	/**	商品特性*/
	private String shintoku;
	/**	類似品識別コード*/
	private String ruijhncd;
	/**	難易度*/
	private String nanido;
	/**	入荷パレット積付張数*/
	private Integer paharisu;
	/**	入荷パレット積付段数*/
	private Integer padansu;
	/**	ゾーン*/
	private String zone;
	/**	アドレス*/
	private String address;
	/**	段*/
	private Integer dan;
	/**	列*/
	private Integer retu;
	/**	受注生産区分*/
	private String jseikb;
	/**	受注生産日数*/
	private Integer jseinisu;
	/**	商品形態区分*/
	private String shinktkb;
	/**	日生協カタログ商品分類*/
	private String nskkatab;
	/**	カタログ区分*/
	private String katakb;
	/**	別冊カタログ区分*/
	private String bekatakb;
	/**	供給パターン*/
	private String kyokyupa;
	/**	全カタメッセージ区分*/
	private String zkamsgkb;
	/**	有効期間（自）*/
	private Integer yukiknF;
	/**	有効期間（至）*/
	private Integer yukiknT;
	/**	生産国ＣＤ*/
	private String seikokcd;
	/**	シーズンパックフラグ*/
	private String spakflg;
	/**	日付管理区分*/
	private String hikanrkb;
	/**	入庫限界日数*/
	private Integer nyugnisu;
	/**	出庫限界日数*/
	private Integer syugnisu;
	/**	賞味期限日数*/
	private Integer syoknisu;
	/**	製造日限界日付*/
	private Integer sezogymd;
	/**	商品形状－縦*/
	private Integer tate;
	/**	商品形状－横*/
	private Integer yoko;
	/**	商品形状－高*/
	private Integer takasa;
	/**	商品形状－重量*/
	private Integer jyuryo;
	/**	商品形状－容量*/
	private Integer youryo;
	/**	最終企画番号*/
	private Integer lastkika;
	/**	取扱識別区分*/
	private String tsikikb;
	/**	更新区分*/
	private String updkb;
	/**	送付区分*/
	private String sohukb;
	/**	ピック単位*/
	private Integer picktani;
	/**	係数区分*/
	private String keisukb;
	/**	倉庫コード*/
	private String soukocd;
	/**	カタログ非掲載区分*/
	private String ckeisaikb;
	/**	予定変更企画番号*/
	private Integer henkikno;
	/**	ＥＸＰ倉庫コード*/
	private String expSoukocd;
	/**	ＥＸＰ商品コード*/
	private String expSyohincd;
	/**	ＥＸＰ企画No（企画商品コード）*/
	private String expKikakuno;
	/**	ＪＡＮ種別*/
	private String jansbt;
	/**	カゴパレ区分*/
	private String kagpltkb;
	/**	有償予備発注区分*/
	private String ysyobikb;
	/**	入荷日区分*/
	private String nyukhikb;
	/**	部門*/
	private String bumoncd;
	/**	配達手数料対象*/
	private String haitatsryo;
	/**	媒体識別番号*/
	private Integer baitaino;
	/**	供給計上区分*/
	private String kykejyokb;
	/**	利用割戻し区分*/
	private String wrmodskb;
	/**	期中割戻し区分*/
	private String kiwrmodskb;
	/**	事業区分*/
	private String jigyokb;
	/**	酒税区分*/
	private String syuzekb;
	/**	酒税商品容量*/
	private BigDecimal syuzeyoryo;
	/**	商品容量単位*/
	private String yoryotani;
	/**	更新システム区分*/
	private String updsyskb;
	/**	削除フラグ*/
	private String delflg;
	/**	登録日*/
	private Integer addymd;
	/**	変更日*/
	private Integer updymd;
	/**	変更項目*/
	private String updkomok;
	/**	作成ユーザー*/
	private String sksiUsr;
	/**	作成プログラム*/
	private String sksiPrgrm;
	/**	作成日時*/
	private String sksiNchj;
	/**	更新ユーザー*/
	private String kushnUsr;
	/**	更新プログラム*/
	private String kushnPrgrm;
	/**	更新日時*/
	private String kushnNchj;
	/**	ID*/
	private Integer id;

}
