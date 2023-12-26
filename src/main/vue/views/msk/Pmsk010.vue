<!-- eslint-disable no-unused-vars -->
<!--
//================================================================================
// ID: PMSK010
// ProjectName: 【マスタ】名称／区分管理マスタ区分一覧
// SystemName: 名称／区分管理マスタ区分一覧
// VueName: PMSK010
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/09/13 | ＡＭＣ               | 新規作成
//================================================================================
 -->
<template>
  <div>
    <!-- 名称／区分管理マスタ区分一覧 -->
    <main id="PMSK010">
      <div class="contents__block--exist_return">
        <!-- タイトル -->
        <h1 class="header__block-title">名称／区分管理マスタ区分一覧</h1>
        <!-- エラーメッセージ -->
        <error-message />
        <div class="contents__wrap--scroll_vertical">
          <div class="contents__view--bg_gray">
            <table class="contents__block-search ">
              <colgroup>
                <col style="width: 200px" />
                <col style="width: 1550px" />
              </colgroup>
              <tr>
                <td style="padding:10px 0 5px 75px;">名称区分</td>
                <td>
                  <div style="padding: 10px 0 5px 0;">
                    <code-input
                      class="width-50"
                      ref="meishoKubun"
                      v-bind:name="'名称区分'"
                      v-bind:maxLength="2"
                      v-bind:tabindex="'1'"
                      v-bind:disabledFlg="disabledFlg.header1"
                      v-model="form.meishoKubun"
                    />
                  </div>
                </td>
              </tr>
              <tr>
                <td style="padding:0 0 10px 60px;">名称区分名</td>
                <td style="padding-bottom: 10px;">
                  <text-input
                    class="width-340"
                    v-bind:name="'名称区分名'"
                    v-bind:maxLength="20"
                    ref="meishoKubunNm"
                    v-bind:textType="TEXTINPUT_TYPE.FULL"
                    v-bind:tabindex="'2'"
                    v-bind:disabledFlg="disabledFlg.header1"
                    v-model="form.meishoKubunNm"
                  />
                  <button-input
                    v-bind:name="'検索'"
                    v-bind:value="'検索'"
                    v-bind:tabindex="'3'"
                    v-bind:disabledFlg="disabledFlg.header1"
                    class="contents__btn-blue mgn-l-25px"
                    v-on:click="clickSearchBtn"
                  />
                </td>
              </tr>
            </table>
            <section>
              <div class="contents__block-top" style="width: 520px">
                <div class="contents__block-top__area--float_l">
                  <h2 class="contents__h2">区分一覧</h2>
                </div>
                <div class="contents__block-top__area--float_r">
                  <span>{{ amount }}件</span>
                </div>
              </div>
              <div class="tbl-list__wrap--small">
                <table
                  class="tbl-list--border-all_exist tbl-sticky-head wrap--overflow_hidden"
                >
                  <colgroup>
                    <col style="width: 100px" />
                    <col style="width: 400px" />
                  </colgroup>
                  <thead>
                    <tr>
                      <th style="padding-right: 20px">名称区分</th>
                      <th style="padding-right: 295px">名称区分名</th>
                    </tr>
                  </thead>
                  <colgroup>
                    <col style="width: 100px" />
                    <col style="width: 400px" />
                  </colgroup>
                  <tbody>
                    <tr v-for="item in tableData" :key="item">
                      <td class="border--top_none padding-left-6">
                        <a
                          v-bind:meishoKubun="item.meishoKubun"
                          v-on:click="
                            clickMeishoKubunLink(
                              item.meishoKubun,
                              item.meishoKubunNm
                            )
                          "
                        >
                          {{ item.meishoKubun }}</a
                        >
                      </td>
                      <td
                        class="border--top_none padding-left-6"
                        v-bind:title="item.meishoKubunNm"
                      >
                        {{ item.meishoKubunNm }}
                      </td>
                    </tr>
                  </tbody>
                </table>
              </div>
            </section>
          </div>
        </div>
      </div>
    </main>
    <footer>
      <div class="contents__block-command-btn--fullsize">
        <div class="footer-button__block btn-display-change">
          <div class="left-block right-adjustment">
            <div>
              <button-input
                v-bind:name="'戻るボタン'"
                v-bind:value="'戻る'"
                v-bind:tabindex="'5'"
                class="contents__btn-blue"
                v-on:click="clickReturnBtn()"
              />
              <button-input
                v-bind:name="'取消ボタン'"
                v-bind:value="'取消'"
                v-bind:tabindex="'6'"
                class="contents__btn-blue"
                v-on:click="clickCancelBtn"
              />
            </div>
          </div>
          <div>
            <div>
              <button-input
                v-bind:name="'追加ボタン'"
                v-bind:value="'追加'"
                v-bind:tabindex="'4'"
                class="contents__btn-blue"
                v-on:click="clickAddBtn"
              />
            </div>
          </div>
        </div>
      </div>
    </footer>
  </div>
</template>
<script>
import ButtonInput from "@/components/ButtonInput.vue";
import ErrorMessage from "@/components/ErrorMessage.vue";
import CodeInput from "@/components/CodeInput.vue";
import TextInput from "@/components/TextInput.vue";
import { co_confirm } from "@/common/dialogUtility.js";
import { co_getMessage } from "@/common/messageUtility.js";
import { co_post, co_setError } from "@/common/ajaxUtility.js";
import {
  TEXTINPUT_TYPE,
  DIALOG_BUTTON_LIST_KEY,
  HTTP_STATUS,
} from "@/const/const.js";
import {
  PATH_PMSK010_SEARCH,
  PATH_PMSK010_SEARCH_CHECK,
} from "@/const/route.js";
import { co_nextView } from "@/common/viewUtility.js";
import { co_isEmpty } from "@/common/checkUtility.js";
import {
  STORE_VIEW_PROGRESS,
  STORE_ERROR_ADDMSG,
  STORE_ERROR_CLEAR,
  STORE_INFO_CLEAR,
  STORE_DATA_ADD_DATA,
  STORE_DATA_GETDATA,
} from "@/const/store.js";
import { PATH_PMSK020, PATH_PMSK030 } from "@/const/route.js";
export default {
  components: {
    "code-input": CodeInput,
    "button-input": ButtonInput,
    "error-message": ErrorMessage,
    "text-input": TextInput,
  },
  computed: {
    amount: function () {
      const reg = /(\d)(?=(\d{3})+$)/g;
      const quantity = this.tableData.length.toString().replace(reg, "$1L,");
      return quantity;
    },
  },
  data: function () {
    return {
      disabledFlg: {
        header1: false,
      },
      TEXTINPUT_TYPE: TEXTINPUT_TYPE,
      form: {
        // 名称区分
        meishoKubun: "",
        // 名称区分名
        meishoKubunNm: "",
      },
      saveSearchData: {
        // データを検索
        searchKbn: false,
        searchMeishoKubun: "",
        searchMeishoKubunNm: "",
      },
      tableData: [],
    };
  },
  created: function () {
    let savedData = this.$store.getters[STORE_DATA_GETDATA]("PMSK010");
    if (savedData == null) {
      // 検索条件部の「名称区分」、「名称区分名」の値を空白にする。
      this.clearHeader1();
      //一覧部の一覧にヘッダーのみを表示し、「件数」を0件にする。
      this.tableData = [];
       // ** フォーカス設定 **
      this.$nextTick(function () {
        this.$refs.meishoKubun.focus();
      });
    } else {
      // 状態管理(ストア)の画面データの検索条件(前回検索時)を検索条件部の「名称区分」、「名称区分名」に設定する。
      this.form.meishoKubun = savedData.meishoKubun;
      this.form.meishoKubunNm = savedData.meishoKubunNm;
      this.doSearchMeishoKubun();
     
    }
  },

  methods: {
    // PMSK020に遷移する
    clickMeishoKubunLink: function (meishoKubun, meishoKubunNm) {
      co_nextView(PATH_PMSK020, {
        id: "PMSK010",
        reckb: meishoKubun,
        reckbNm: meishoKubunNm,
      });
    },
    // PMSK030に遷移する
    clickAddBtn: function () {
      co_nextView(PATH_PMSK030, {
        id: "PMSK010",
        procsShubt: "1",
        reckb: "00",
        meicd: "",
      });
    },
    //検索ボタンクリック
    clickSearchBtn: function () {
      // 検索条件非活性
      this.disabledFlg.header1 = true;
      this.doSearchMeishoKubun();
    },
    // 検索実行
    doSearchMeishoKubun: async function () {
      this.$store.commit(STORE_VIEW_PROGRESS, true);
      this.$store.commit(STORE_ERROR_CLEAR);
      this.$store.commit(STORE_INFO_CLEAR);
      const self = this;
      const dataQuantity = await this.getDataQuantity();
      // 検索結果が0件を超えています
      if (dataQuantity.cnt == 0) {
        this.tableData = [];
        self.$store.commit(STORE_VIEW_PROGRESS, false);
        this.$store.commit(STORE_ERROR_ADDMSG, co_getMessage("CMS0033E"));
        // 検索結果が500件を超えています
      } else if (dataQuantity.cnt > 1000) {
        self.$store.commit(STORE_VIEW_PROGRESS, false);
        this.$store.commit(STORE_ERROR_ADDMSG, co_getMessage("CMS0032E", 1000));
        self.$store.commit(STORE_VIEW_PROGRESS, false);
      } else if (dataQuantity.cnt > 500) {
        // 検索結果が500件を超えています
        co_confirm(co_getMessage("CMS0015W", 500), function (clickValue) {
          if (clickValue == DIALOG_BUTTON_LIST_KEY.CANCEL) {
            self.$store.commit(STORE_VIEW_PROGRESS, false);
            return;
          }
          // クライアント処理の『検索実行』を呼出する
          co_post(PATH_PMSK010_SEARCH, self.form, function (res) {
            if (res.status == HTTP_STATUS.OK) {
              self.tableData = res.data.meishoKubunList;
            } else {
              self.saveSearchData.searchKbn = false;
              self.$store.commit(STORE_VIEW_PROGRESS, false);

              co_setError(res.data, self.$refs);
              const errorItem = res.data[0].errorItems[0];
              if (!co_isEmpty(self.$refs[errorItem])) {
                const self = this;
                this.$nextTick(function () {
                  self.$refs[errorItem].focus();
                });
              }
            }
          });
          self.$store.commit(STORE_VIEW_PROGRESS, false);
        });
      } else {
        co_post(PATH_PMSK010_SEARCH, self.form, function (res) {
          if (res.status == HTTP_STATUS.OK) {
            self.tableData = res.data.meishoKubunList;
          } else {
            self.saveSearchData.searchKbn = false;
            self.$store.commit(STORE_VIEW_PROGRESS, false);

            co_setError(res.data, self.$refs);
            const errorItem = res.data[0].errorItems[0];
            if (!co_isEmpty(self.$refs[errorItem])) {
              const self = this;
              this.$nextTick(function () {
                self.$refs[errorItem].focus();
              });
            }
          }
        });
        self.$store.commit(STORE_VIEW_PROGRESS, false);
      }
      //  検索条件部の「名称区分」、「名称区分名」を状態管理(ストア)の画面データの検索条件に保持する
      self.$store.commit(STORE_DATA_ADD_DATA, {
        id: "PMSK010",
        data: {
          meishoKubun: self.form.meishoKubun,
          meishoKubunNm: self.form.meishoKubunNm,
        },
      });
    },
    //データ量を取得する
    getDataQuantity: function () {
      return co_post(PATH_PMSK010_SEARCH_CHECK, this.form);
    },
    // click - メニュー画面へ遷移する
    clickReturnBtn: function () {
      this.$router.back();
    },
    //検索条件部の「名称区分」、「名称区分名」の値を空白にする。
    clearHeader1: function () {
      this.form.meishoKubun = "";
      this.form.meishoKubunNm = "";
    },
    // click - 画面初期化する
    clickCancelBtn: async function () {
      // 検索条件活性
      this.disabledFlg.header1 = false;
      //検索条件部の「名称区分」、「名称区分名」の値を空白にする。
      this.clearHeader1();
      //一覧部の一覧にヘッダーのみを表示し、「件数」を0件にする。
      this.tableData = [];
      // ** フォーカス設定 **
      await this.$nextTick();
      this.$refs.meishoKubun.focus();
    },
  },
};
</script>

<style scoped>
.border-inline {
  border-inline: 2px solid #dedfe8;
}

.btn-display-change {
  display: flex;
  justify-content: flex-start;
}

.right-adjustment {
  margin-right: 7%;
}

.margin-right-30 {
  margin-right: 30px;
}

.tbl-list__wrap--small {
  width: 90%;
  margin: 0 1%;
}

.tbl-sticky-head {
  position: -webkit-sticky;
  position: sticky;
  top: 0;
  z-index: 1;
}

.margin-r-200px {
  margin-right: 200px;
}

tr.border-b {
  border-bottom: 2px solid #dedfe8;
}

.padding-left-6 {
  padding-left: 6px;
}
</style>
