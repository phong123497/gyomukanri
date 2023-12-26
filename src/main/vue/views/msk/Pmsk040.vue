<!--
//================================================================================
// ID: PMSK040
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// VueName: PMSK040
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/09/13 | ＡＭＣ               | 新規作成
//================================================================================
 -->
<template>
  <div class="view-area">
    <main>
      <div class="contents__block--exist_return">
        <h1 class="header__block-title">企画商品マスタ一覧</h1>
        <!-- エラーメッセージ -->
        <error-message />
        <div class="contents__wrap--scroll_vertical">
          <div class="contents__block-search">
            <div class="display--flex mgn-b-4px">
              <div class="display--flex align-items-center">
                <div class="txt--align_r">
                  <span class="contents__block-search__label">企画番号</span>
                </div>
                <div class="display--flex align-items-center">
                  <!-- 企画番号開始 -->
                  <number-input
                      class="width-80"
                      ref="kikakuNoStart"
                      v-bind:numberType="NUMBERINPUT_TYPE.NON_COMMA_ZERO_DECIMAL_OMMIT"
                      v-bind:maxLength="3"
                      v-bind:name="'企画番号開始'"
                      v-bind:tabindex="'1'"
                      v-model="form.kikakuNoStart"
                      v-bind:disabledFlg="disabledFlg"
                  ></number-input>
                  <span class="mgn-r-10px mgn-l-10px">～</span>
                  <!-- 企画番号終了 -->
                  <number-input
                      class="width-80"
                      ref="kikakuNoEnd"
                      v-bind:numberType="NUMBERINPUT_TYPE.NON_COMMA_ZERO_DECIMAL_OMMIT"
                      v-bind:maxLength="3"
                      v-bind:name="'企画番号終了'"
                      v-bind:tabindex="'2'"
                      v-model="form.kikakuNoEnd"
                      v-bind:disabledFlg="disabledFlg"
                  ></number-input>
                </div>
              </div>
              <div class="display--flex align-items-center">
                <div class="txt--align_r mgn-l-40px">
                  <span class="contents__block-search__label">共同購入商品コード</span>
                </div>
                <div class="display--flex align-items-center">
                  <!-- 共同購入商品コード開始 -->
                  <number-input
                      class="width-110"
                      ref="kyodoCdStart"
                      v-bind:maxLength="7"
                      v-bind:name="'共同購入商品コード開始'"
                      v-bind:tabindex="'3'"
                      v-model="form.kyodoCdStart"
                      v-bind:activeZeroPadding="false"
                      v-bind:disabledFlg="disabledFlg"
                      v-bind:numberType="NUMBERINPUT_TYPE.NON_COMMA_FULL_INDICATE"
                  />
                  <span class="mgn-r-10px mgn-l-10px">～</span>
                  <!-- 共同購入商品コード終了 -->
                  <number-input
                      class="width-110"
                      ref="kyodoCdEnd"
                      v-bind:maxLength="7"
                      v-bind:name="'共同購入商品コード終了'"
                      v-bind:tabindex="'4'"
                      v-model="form.kyodoCdEnd"
                      v-bind:activeZeroPadding="false"
                      v-bind:disabledFlg="disabledFlg"
                      v-bind:numberType="NUMBERINPUT_TYPE.NON_COMMA_FULL_INDICATE"
                  />
                </div>
              </div>
              <div class="display--flex align-items-center">
                <div class="txt--align_r mgn-l-40px">
                  <span class="contents__block-search__label">会員生協コード</span>
                </div>
                <div class="display--flex align-items-center">
                  <!-- 会員生協コード開始 -->
                  <number-input
                      class="width-80"
                      ref="seiCdStart"
                      v-bind:maxLength="4"
                      v-bind:name="'会員生協コード開始'"
                      v-bind:tabindex="'5'"
                      v-model="form.seiCdStart"
                      v-bind:activeZeroPadding="false"
                      v-bind:disabledFlg="disabledFlg"
                      v-bind:numberType="NUMBERINPUT_TYPE.NON_COMMA_FULL_INDICATE"
                  ></number-input>
                  <span class="mgn-r-10px mgn-l-10px">～</span>
                  <!-- 会員生協コード終了 -->
                  <number-input
                      class="width-80"
                      ref="seiCdEnd"
                      v-bind:maxLength="4"
                      v-bind:name="'会員生協コード終了'"
                      v-bind:tabindex="'6'"
                      v-model="form.seiCdEnd"
                      v-bind:activeZeroPadding="false"
                      v-bind:disabledFlg="disabledFlg"
                      v-bind:numberType="NUMBERINPUT_TYPE.NON_COMMA_FULL_INDICATE"
                  ></number-input>
                </div>
              </div>
            </div>

            <div class="display--flex mgn-b-4px" style="margin-left: -18px">
              <div class="display--flex align-items-center">
                <div class="txt--align_r">
                  <span class="contents__block-search__label">商品コード</span>
                </div>
                <div>
                  <!-- 商品コード -->
                  <number-input
                      class="width-130"
                      ref="shohinCd"
                      v-bind:maxLength="9"
                      v-bind:name="'商品コード'"
                      v-bind:tabindex="'7'"
                      v-model="form.shohinCd"
                      v-bind:activeZeroPadding="false"
                      v-bind:disabledFlg="disabledFlg"
                      v-bind:numberType="NUMBERINPUT_TYPE.NON_COMMA_FULL_INDICATE"
                  ></number-input>
                </div>
                <div class="txt--align_r mgn-l-40px">
                  <span class="contents__block-search__label">JANコード</span>
                </div>
                <div>
                  <!-- JANコード -->
                  <text-input
                      class="width-180"
                      ref="janCd"
                      v-bind:maxLength="13"
                      v-bind:name="'JANコード'"
                      v-bind:tabindex="'8'"
                      v-model="form.janCd"
                      v-bind:activeZeroPadding="false"
                      v-bind:disabledFlg="disabledFlg"
                      v-bind:textType="TEXTINPUT_TYPE.HALF"
                 />
                </div>
                <div class="txt--align_r mgn-l-40px">
                  <span class="contents__block-search__label">大分類</span>
                </div>
                <div>
                  <!-- 大分類 -->
                  <text-input
                      class="width-80"
                      ref="daibunrui"
                      v-bind:maxLength="2"
                      v-bind:name="'大分類'"
                      v-bind:tabindex="'9'"
                      v-model="form.daibunrui"
                      v-bind:activeZeroPadding="false"
                      v-bind:disabledFlg="disabledFlg"
                      v-bind:textType="TEXTINPUT_TYPE.HALF"
                  ></text-input>
                </div>
                <div class="txt--align_r mgn-l-40px">
                  <span class="contents__block-search__label">商品ルート</span>
                </div>
                <div>
                  <!-- 商品ルート -->
                  <text-input
                      class="width-80"
                      ref="shohinRoute"
                      v-bind:maxLength="2"
                      v-bind:name="'商品ルート'"
                      v-bind:tabindex="'10'"
                      v-model="form.shohinRoute"
                      v-bind:activeZeroPadding="false"
                      v-bind:disabledFlg="disabledFlg"
                      v-bind:textType="TEXTINPUT_TYPE.HALF"
                  ></text-input>
                </div>
                <div class="txt--align_r mgn-l-40px">
                  <span class="contents__block-search__label">QRC通過区分</span>
                </div>
                <div>
                  <!-- QRC通過区分 -->
                  <text-input
                      class="width-50"
                      ref="tsukaKubun"
                      v-bind:maxLength="1"
                      v-bind:name="'QRC通過区分'"
                      v-bind:tabindex="'11'"
                      v-model="form.tsukaKubun"
                      v-bind:disabledFlg="disabledFlg"
                      v-bind:textType="TEXTINPUT_TYPE.HALF"
                  ></text-input>
                </div>
              </div>
            </div>

            <div class="display--flex mgn-b-4px">
              <div class="display--flex align-items-center">
                <div class="txt--align_r">
                  <span class="contents__block-search__label">集品区分</span>
                </div>
                <div>
                  <!-- 集品区分 -->
                  <text-input
                      class="width-70"
                      ref="shuhinKubun"
                      v-bind:maxLength="1"
                      v-bind:name="'集品区分'"
                      v-bind:tabindex="'12'"
                      v-model="form.shuhinKubun"
                      v-bind:disabledFlg="disabledFlg"
                      v-bind:textType="TEXTINPUT_TYPE.HALF"
                  ></text-input>
                </div>
                <div class="txt--align_r mgn-l-40px">
                  <span class="contents__block-search__label">発注先コード</span>
                </div>
                <div>
                  <!-- 発注先コード1 -->
                  <span>
                    <code-input
                        class="width-90"
                        ref="hatyusakiCd1"
                        v-bind:maxLength="6"
                        v-bind:name="'発注先コード1'"
                        v-bind:tabindex="'13'"
                        v-model="form.hatyusakiCd1"
                        v-bind:activeZeroPadding="false"
                        v-bind:disabledFlg="disabledFlg"
                    ></code-input>
                  </span>
                  <!-- 発注先コード2 -->
                  <span class="mgn-l-5px">
                    <code-input
                        class="width-90"
                        ref="hatyusakiCd2"
                        v-bind:maxLength="6"
                        v-bind:name="'発注先コード2'"
                        v-bind:tabindex="'14'"
                        v-model="form.hatyusakiCd2"
                        v-bind:activeZeroPadding="false"
                        v-bind:disabledFlg="disabledFlg"
                    ></code-input>
                  </span>

                </div>
                <div class="txt--align_r mgn-l-40px">
                  <span class="contents__block-search__label">仕入先コード</span>
                </div>
                <div>
                  <!-- 仕入先コード1 -->
                  <span>
                    <code-input
                        class="width-90"
                        ref="shiiresakiCd1"
                        v-bind:maxLength="6"
                        v-bind:name="'仕入先コード1'"
                        v-bind:tabindex="'15'"
                        v-model="form.shiiresakiCd1"
                        v-bind:activeZeroPadding="false"
                        v-bind:disabledFlg="disabledFlg"
                    ></code-input>
                  </span>
                  <!-- 仕入先コード2 -->
                  <span class="mgn-l-5px"  v-if="showShiiresakiCd2">
                    <code-input
                        class="width-90"
                        ref="shiiresakiCd2"
                        v-bind:maxLength="6"
                        v-bind:name="'仕入先コード2'"
                        v-bind:tabindex="'16'"
                        v-model="form.shiiresakiCd2"
                        v-bind:activeZeroPadding="false"
                        v-bind:disabledFlg="disabledFlg"
                    ></code-input>
                  </span>
                  <button-input
                      class="contents__btn-blue-circle"
                      v-if="!showShiiresakiCd2"
                      v-bind:value="'+'"
                      v-on:click="addShiiresakiCd2()"
                      v-bind:disabledFlg="disabledFlg"
                  />
                </div>
                <div class="mgn-l-40px">
                  <!-- 検索ボタンです -->
                  <button-input
                      class="contents__btn-blue"
                      v-bind:tabindex="'17'"
                      v-bind:name="'検索ボタン'"
                      v-bind:value="'検索'"
                      v-on:click="clickSearchBtn()"
                      v-bind:disabledFlg="disabledFlg"
                  />
                </div>
              </div>
            </div>
          </div>
          <div class="contents__view--bg_gray">
            <div class="contents__block-top">
              <div class="contents__block-top__area--float_r">
                <span ref="cnt">{{ amount }}</span>
              </div>
            </div>
            <div id="PMSK040List" class="tbl-list__wrap">
              <table class="tbl-list--border-all_exist tbl-sticky-head wrap--overflow_hidden txt--align_l">
                <colgroup>
                  <col style="width:120px">
                  <col style="width:180px">
                  <col style="width:145px">
                  <col style="width:170px">
                  <col style="width:100px">
                  <col style="width:100px">
                  <col style="width:100px">
                  <col style="width:100px">
                  <col style="width:100px">
                  <col style="width:110px">
                  <col style="width:125px">
                  <col style="width:120px">
                  <col style="width:120px">
                  <col style="width:120px">
                </colgroup>
                <thead>
                <tr>
                  <th>企画番号</th>
                  <th>共同購入商品コード</th>
                  <th>会員生協コード</th>
                  <th>商品コード<br/>商品名漢字</th>
                  <th>規格カナ</th>
                  <th>JANコード</th>
                  <th>原単価</th>
                  <th>売単価</th>
                  <th>大分類</th>
                  <th>商品ルート</th>
                  <th>QRC通過区分</th>
                  <th>集品区分</th>
                  <th>仕入先コード</th>
                  <th>発注先コード</th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="(item) in tableData" :key="item" style="height:54px; !important">
                  <td class="border--top_none">
                    <link-input
                        v-bind:tabindex="'18'"
                        v-bind:class="'kikaku-link'"
                        v-bind:label="item.kikakuNo"
                        v-on:click="clickKikakuNoLink(item.kikakuNo, item.kyodoCd, item.seiCd)" />
                  </td>
                  <td class="border--top_none">
                    &nbsp;{{ item.kyodoCd }}
                  </td>
                  <td class="border--top_none">{{ item.seiCd }}</td>
                  <td class="border--top_none">{{ item.shohinCd }}<br/>{{ item.shohinNm }}</td>
                  <td class="border--top_none">
                    {{ item.kikakukana }}
                  </td>
                  <td class="border--top_none">
                    {{ item.janCd }}
                  </td>
                  <td class="border--top_none">
                    {{ formatPrice(item.gentanka) }}
                  </td>
                  <td class="border--top_none">
                    {{ formatPrice(item.uritanka) }}
                  </td>
                  <td class="border--top_none">
                    {{ item.daibunrui }}
                  </td>
                  <td class="border--top_none">
                    {{ item.shohinRoute }}
                  </td>
                  <td class="border--top_none">
                    {{ item.tsukaKubun }}
                  </td>
                  <td class="border--top_none">
                    {{ item.shuhinKubun }}
                  </td>
                  <td class="border--top_none">
                    {{ item.shiiresakiCd }}
                  </td>
                  <td class="border--top_none">
                    {{ item.hatyusakiCd }}
                  </td>
                </tr>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </main>
    <footer>
      <div class="contents__block-command-btn--fullsize">
        <div class="footer-button__block">
          <div class="left-block">
            <div class="button-group">
              <button-input
                  style="margin-right: 30px"
                  class="contents__btn-blue"
                  v-bind:tabindex="'19'"
                  v-bind:value="'戻る'"
                  v-on:click="clickReturnBtn()"
              ></button-input>
              <button-input
                  class="contents__btn-blue"
                  v-bind:tabindex="'20'"
                  v-bind:value="'取消'"
                  v-on:click="clickCancelBtn()"
              ></button-input>
            </div>
          </div>
        </div>
      </div>
    </footer>
  </div>
</template>

<script>
import ErrorMessage from '@/components/ErrorMessage.vue'
import NumberInput from '@/components/NumberInput.vue'
import CodeInput from '@/components/CodeInput'
import ButtonInput from '@/components/ButtonInput'
import {
  TEXTINPUT_TYPE,
  DIALOG_BUTTON_LIST_KEY,
  NUMBERINPUT_TYPE,
  HTTP_STATUS
} from '@/const/const.js'
import {STORE_ERROR_ADDMSG, STORE_ERROR_CLEAR, STORE_ERROR_IS_ERRFLG, STORE_VIEW_PROGRESS} from '@/const/store.js'
import {PATH_PMSK040_SEARCH, PATH_PMSK040_CHECK, PATH_PMST260} from "@/const/route";
import LinkInput from "@/components/LinkInput.vue";
import {co_getMessage} from "@/common/messageUtility";
import {co_backView, co_nextView} from "@/common/viewUtility.js"
import {co_alert} from "@/common/dialogUtility";
import { co_isEmpty } from "@/common/checkUtility.js";
import {co_post, co_setError} from "@/common/ajaxUtility";
import TextInput from "@/components/TextInput.vue";

export default {
  components: {
    'link-input':LinkInput,
    'error-message': ErrorMessage,
    'number-input': NumberInput,
    'code-input': CodeInput,
    'button-input': ButtonInput,
    'text-input': TextInput,
  },
  data: function () {
    return {
      NUMBERINPUT_TYPE: NUMBERINPUT_TYPE,
      tableData: [],
      showShiiresakiCd2: false,
      form: {
        kikakuNoStart: '', // 企画番号開始
        kikakuNoEnd: '', // 企画番号終了
        kyodoCdStart: '', // 共同購入商品コード開始
        kyodoCdEnd: '', // 共同購入商品コード終了
        seiCdStart: '', // 会員生協コード開始
        seiCdEnd: '', // 会員生協コード終了
        shohinCd: '', // 商品コード
        janCd: '', // JANコード
        daibunrui: '', // 大分類
        shohinRoute: '', // 商品ルート
        tsukaKubun: '', // QRC通過区分
        shuhinKubun: '', // 集品区分
        hatyusakiCd1: '', // 発注先コード1
        hatyusakiCd2: '', // 発注先コード2
        shiiresakiCd1: '', // 仕入先コード1
        shiiresakiCd2: '', // 仕入先コード2
      },
      TEXTINPUT_TYPE: TEXTINPUT_TYPE,
      // 入力エリア
      disabledFlg: false
    }
  },
  mounted: function () {
    this.$nextTick(function () {
      this.$refs.kikakuNoStart.focus()
    })
  },
  computed: {
    // 一覧リストのカウントを計算する
    amount: function () {
      const reg = /(\d)(?=(\d{3})+$)/g;
      const quantity = this.tableData.length.toString().replace(reg, "$1L,");
      return quantity + "件";
    }
  },
  methods: {
    // 仕入先コード2追加
    addShiiresakiCd2: function () {
      this.showShiiresakiCd2 = true;
    },

    // 検索ボタン方式を実装します
    clickSearchBtn: async function () {
      // 状態管理(ストア)の画面表示にプログレスバーの表示を設定する
      this.$store.commit(STORE_VIEW_PROGRESS, true);
      this.$store.commit(STORE_ERROR_CLEAR);

      // ** チェック処理 **
      this.$refs.janCd.validate();
      this.$refs.daibunrui.validate();
      this.$refs.shohinRoute.validate();
      this.$refs.tsukaKubun.validate();
      this.$refs.shuhinKubun.validate();

      // ** エラー表示制御 **
      if ( this.$store.getters[STORE_ERROR_IS_ERRFLG]) {
        this.$store.commit(STORE_VIEW_PROGRESS, false);
        return;
      }

      const self = this;
      this.tableData = [];
      let dataQuantity = await this.getDataQuantity();
      // 検索結果が1000件を超えています
      if (dataQuantity > 1000) {
        this.$store.commit(STORE_ERROR_ADDMSG, co_getMessage('CMS0032E', 1000));
        self.$store.commit(STORE_VIEW_PROGRESS, false);
      } else if (dataQuantity > 500) {
        // 検索結果が500件を超えています
        co_alert(co_getMessage('CMS0015W', 500), function (clickValue) {
          if (clickValue == DIALOG_BUTTON_LIST_KEY.CANCEL) {
            self.$store.commit(STORE_VIEW_PROGRESS, false);
            return;
          }
          // クライアント処理の『検索実行』を呼出する
          co_post(PATH_PMSK040_SEARCH, self.form, self.doSearchKikakuShohin);
        });
      } else if (dataQuantity == 0) {
        this.$store.commit(STORE_ERROR_ADDMSG, co_getMessage('CMS0033E'));
        self.$store.commit(STORE_VIEW_PROGRESS, false);
      } else {
        // クライアント処理の『検索実行』を呼出する
        co_post(PATH_PMSK040_SEARCH, self.form, self.doSearchKikakuShohin);
      }
    },

    // 企画番号リンククリック
    clickKikakuNoLink: function (batchNo, kyodoCd, seiCd) {
      co_nextView(
          PATH_PMST260,
          {
            batchNo: batchNo,
            kyodoCd: kyodoCd,
            seiCd: seiCd,
          },
      )
    },

    // 検索実行
    doSearchKikakuShohin: function (res) {
      if (res.status == HTTP_STATUS.OK) {
        // データを取り
        this.tableData = res.data;
        // 状態管理(ストア)の画面表示にプログレスバーの非表示を設定する
        this.$store.commit(STORE_VIEW_PROGRESS, false);

        this.disabledFlg = true;
      } else {
        this.$store.commit(STORE_VIEW_PROGRESS, false);

        co_setError(res.data, this.$refs);
        const errorItem = res.data[0].errorItems[0];
        if (!co_isEmpty(this.$refs[errorItem])) {
          const self = this;
          this.$nextTick(function () {
            self.$refs[errorItem].focus();
          })
        }
      }
    },

    // 取消ボタンクリック
    clickCancelBtn: function () {
      // 状態管理(ストア)のエラー情報をクリアする。
      this.$store.commit(STORE_ERROR_CLEAR)

      // フォームのデータをクリアします
      this.dataClear()

      // 一覧リスト
      this.tableData = [];

      // 入力エリア
      this.disabledFlg = false;

      // フォーカスを設定
      this.$nextTick(function () {
        this.$refs.kikakuNoStart.focus()
      })
    },

    // 戻るボタンクリック
    clickReturnBtn: function () {
      co_backView();
    },

    // フォームのデータをクリアします
    dataClear: function () {
      this.form.kikakuNoStart = '' // 企画番号開始
      this.form.kikakuNoEnd = '' // 企画番号終了
      this.form.kyodoCdStart = '' // 共同購入商品コード開始
      this.form.kyodoCdEnd = '' // 共同購入商品コード終了
      this.form.seiCdStart = '' // 会員生協コード開始
      this.form.seiCdEnd = '' // 会員生協コード終了
      this.form.shohinCd = '' // 商品コード
      this.form.janCd = '' // JANコード
      this.form.daibunrui = '' // 大分類
      this.form.shohinRoute = '' // 商品ルート
      this.form.tsukaKubun = '' // QRC通過区分
      this.form.shuhinKubun = '' // 集品区分
      this.form.hatyusakiCd1 = '' // 発注先コード1
      this.form.hatyusakiCd2 = '' // 発注先コード2
      this.form.shiiresakiCd1 = '' // 仕入先コード1
      this.form.shiiresakiCd2 = '' // 仕入先コード2
    },

    getDataQuantity: function () {
      return co_post(PATH_PMSK040_CHECK, this.form);
    },

    formatPrice(value) {
      return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")
    }
  }
}
</script>

<style scoped>
.align-items-center {
  align-items: center;
}
.contents__block-search {
  padding: 25px 130px;
}
.kikaku-link {
  color: blue;
  text-decoration: underline;
}
</style>
