<!--
//================================================================================
// ID: PMSK050
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// VueName: 取引先マスタ一覧画面
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/09/25 |  AMC                 | 新規作成
//================================================================================
 -->

<template>
  <div>
    <main id="PMSK020">
      <div class="contents__block--exist_return">
        <h1 class="header__block-title-with-input">
          <table>
            <colgroup>
              <col style="width: 460px" />
              <col style="width: 1330px" />
            </colgroup>
            <tbody>
              <tr>
                <td>取引先マスタ一覧</td>
              </tr>
              <tr></tr>
            </tbody>
          </table>
        </h1>
        <!-- エラーメッセージ -->
        <div id="error">
          <error-message />
        </div>
        <div class="contents__wrap--scroll_vertical">
          <div class="contents__block-search">
            <div class="contents__block-search__main">
              <div id="MSK050Head">
                <table>
                  <colgroup>
                    <col style="width: 170px" />
                    <col style="width: 380px" />
                    <col style="width: 110px" />
                    <col style="width: 380px" />
                    <col style="width: 750px" />
                  </colgroup>
                  <tbody>
                    <tr>
                      <td class="txt--align_r">取引先コード</td>
                      <td class="txt--align_l" colspan="4">
                        <code-input
                          class="width-100 txt--align_l mgn-l-5px"
                          ref="toricdStart"
                          v-bind:name="'取引先コード開始'"
                          v-bind:maxLength="6"
                          v-bind:tabindex="'1'"
                          v-model="form.toricdStart"
                        />
                        <span class="txt--align_c mgn-l-10px mgn-r-10px"
                          >～</span
                        >
                        <code-input
                          class="width-100 txt--align_l"
                          ref="toricdEnd"
                          v-bind:name="'取引先コード終了'"
                          v-bind:maxLength="6"
                          v-bind:tabindex="'2'"
                          v-model="form.toricdEnd"
                        />
                      </td>
                    </tr>
                    <tr>
                      <td class="txt--align_r">名称漢字</td>
                      <td class="txt--align_l">
                        <text-input
                          class="width-370 mgn-l-5px"
                          ref="torikjnm"
                          v-bind:name="'名称漢字'"
                          v-bind:maxLength="20"
                          v-bind:tabindex="'3'"
                          v-bind:textType="TEXTINPUT_TYPE.FULL"
                          v-model="form.torikjnm"
                        />
                      </td>
                      <td class="txt--align_r">名称カナ</td>
                      <td class="txt--align_l" colspan="2">
                        <text-input
                          class="width-370 mgn-l-5px"
                          ref="toriknnm"
                          v-bind:name="'名称カナ'"
                          v-bind:maxLength="30"
                          v-bind:tabindex="'4'"
                          v-bind:textType="TEXTINPUT_TYPE.HALF"
                          v-model="form.toriknnm"
                        />
                      </td>
                    </tr>
                    <tr>
                      <td class="txt--align_r">発注区分</td>
                      <td colspan="3" class="txt--align_l">
                        <list-box
                          class="width-160 mgn-l-5px"
                          ref="hatukb"
                          v-bind:cdContentType="CD_CONTENT_TYPE.CODENAME"
                          v-bind:maxLength="7"
                          v-bind:name="'発注区分'"
                          v-bind:tabindex="'5'"
                          v-model="form.hatukb"
                        />
                        <span class="mgn-l-30px"> 発注フォーマット </span>
                        <list-box
                          class="width-160 mgn-l-0px"
                          ref="hatufmkb"
                          v-bind:cdContentType="CD_CONTENT_TYPE.CODENAME"
                          v-bind:maxLength="7"
                          v-bind:name="'発注フォーマット'"
                          v-bind:tabindex="'6'"
                          v-model="form.hatufmkb"
                        />
                        <span class="mgn-l-30px"> 送信先コード </span>
                        <list-box
                          class="width-180 mgn-l-0px"
                          ref="sosncd"
                          v-bind:cdContentType="CD_CONTENT_TYPE.CODENAME"
                          v-bind:maxLength="13"
                          v-bind:name="'送信先コード'"
                          v-bind:tabindex="'7'"
                          v-model="form.sosncd"
                        />
                      </td>
                      <td>
                        <button-input
                          class="contents__btn-blue mgn-l-30px"
                          v-bind:value="'検索'"
                          v-bind:name="'検索ボタン'"
                          v-bind:tabindex="'8'"
                          v-on:click="clickSearchBtn"
                        />
                      </td>
                    </tr>
                  </tbody>
                </table>
              </div>
            </div>
          </div>
          <div class="contents__view--bg_gray">
            <div class="contents__block-top">
              <div
                class="contents__block-top__area--float_r table-content-header"
              >
                <span> {{ tableListCount }}件</span>
              </div>
            </div>
            <div id="MSK050List" class="tbl-list__wrap">
              <table class="tbl-list--border-all_exist wrap--overflow_hidden">
                <colgroup>
                  <col style="width: 120px" />
                  <col style="width: 380px" />
                  <col style="width: 180px" />
                  <col style="width: 180px" />
                  <col style="width: 160px" />
                  <col style="width: 230px" />
                  <col style="width: 200px" />
                  <col style="width: 180px" />
                </colgroup>
                <thead class="tbl-head">
                  <tr>
                    <td>取引先コード</td>
                    <td>
                      <p class="mgn-horizontal-0px">取引先名称漢字</p>
                      <p class="mgn-horizontal-0px">取引先名称カナ</p>
                    </td>
                    <td>電話番号</td>
                    <td>ＦＡＸ番号</td>
                    <td>
                      <p class="mgn-horizontal-0px">発注区分（現在）</p>
                      <p class="mgn-horizontal-0px">発注区分（予約）</p>
                    </td>
                    <td>
                      <p class="mgn-horizontal-0px">発注フォーマット（現在）</p>
                      <p class="mgn-horizontal-0px">発注フォーマット（予約）</p>
                    </td>
                    <td>
                      <p class="mgn-horizontal-0px">送信先コード（現在）</p>
                      <p class="mgn-horizontal-0px">送信先コード（予約）</p>
                    </td>
                    <td>適用開始日（予約）</td>
                  </tr>
                </thead>
                <tbody>
                  <tr
                    v-for="(item, index) in this.disp.torihikisakiList"
                    v-bind:key="index"
                    v-bind:index="index"
                  >
                    <td>
                      <link-input
                        v-bind:value="item.toricd"
                        class="link-input"
                        v-on:click="clickToricdLink"
                      />
                    </td>
                    <td>
                      <p class="mgn-horizontal-0px">
                        {{ item.torikjnm }}
                      </p>
                      <p class="mgn-horizontal-0px">
                        {{ item.toriknnm }}
                      </p>
                    </td>
                    <td>{{ item.tel }}</td>
                    <td>{{ item.fax }}</td>
                    <td>
                      <p class="mgn-horizontal-0px">{{ item.hatukb }}</p>
                      <p class="mgn-horizontal-0px">{{ item.hatukbY }}</p>
                    </td>
                    <td>
                      <p class="mgn-horizontal-0px">{{ item.hatufmkb }}</p>
                      <p class="mgn-horizontal-0px">{{ item.hatufmkbY }}</p>
                    </td>
                    <td>
                      <p class="mgn-horizontal-0px">{{ item.sosncd }}</p>
                      <p class="mgn-horizontal-0px">{{ item.sosncdY }}</p>
                    </td>
                    <td>{{ item.tekiymd }}</td>
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
                :style="{ 'margin-right': '30px' }"
                class="contents__btn-blue"
                v-bind:name="'戻るボタン'"
                v-bind:tabindex="'10'"
                v-bind:value="'戻る'"
                v-on:click="clickReturnBtn"
              />
              <button-input
                :style="{ 'margin-right': '30px' }"
                class="contents__btn-blue"
                v-bind:name="'取消ボタン'"
                v-bind:tabindex="'11'"
                v-bind:value="'取消'"
                v-on:click="clickCancelBtn"
              />
            </div>
          </div>
          <div class="center-block">
            <div class="button-group">
              <button-input
                :style="{ 'margin-right': '60px' }"
                class="contents__btn-blue"
                v-bind:name="'追加ボタン'"
                v-bind:tabindex="'12'"
                v-bind:value="'追加'"
                v-on:click="clickAddBtn"
              />
              <button-input
                :style="{ 'margin-right': '30px' }"
                class="contents__btn-blue"
                v-bind:name="'CSV出力ボタン'"
                v-bind:tabindex="'13'"
                v-bind:value="'CSV出力'"
                v-on:click="clickCsvOutputBtn"
              />
              <button-input
                :style="{ 'margin-right': '30px' }"
                class="contents__btn-blue"
                v-bind:name="'CSV取込ボタン'"
                v-bind:tabindex="'14'"
                v-bind:value="'CSV取込'"
                v-on:click="clickCsvInputBtn"
              />
            </div>
          </div>
        </div>
      </div>
    </footer>
  </div>
</template>
<script>
import CodeInput from "@/components/CodeInput.vue";
import ErrorMessage from "@/components/ErrorMessage.vue";
import TextInput from "@/components/TextInput.vue";
import ListBox from "@/components/ListBox.vue";
import ButtonInput from "@/components/ButtonInput.vue";
import LinkInput from "@/components/LinkInput.vue";
import {
  TEXTINPUT_TYPE,
  CD_CONTENT_TYPE,
  HTTP_STATUS,
  DIALOG_BUTTON_LIST_KEY,
} from "@/const/const.js";
import {
  STORE_VIEW_GET_PARAMS,
  STORE_VIEW_PROGRESS,
  STORE_ERROR_ADDMSG,
  STORE_DATA_ADD_DATA,
  STORE_ERROR_CLEAR,
  STORE_VIEW_CHANGED,
  STORE_VIEW_HAS_CHANGED,
} from "@/const/store.js";
import { co_post, co_downloadFile, co_setError } from "@/common/ajaxUtility.js";
import { co_getMessage } from "@/common/messageUtility.js";
import {
  PATH_PMSK050_CHECK,
  PATH_PMSK050_SEARCH,
  PATH_PMSK050_OUTPUT,
  PATH_PMSK051,
  PATH_PMST120,
} from "@/const/route.js";
import { co_confirm } from "@/common/dialogUtility.js";
import { co_nextView, co_backView } from "@/common/viewUtility.js";

export default {
  components: {
    "code-input": CodeInput,
    "text-input": TextInput,
    "list-box": ListBox,
    "button-input": ButtonInput,
    "link-input": LinkInput,
    "error-message": ErrorMessage,
  },

  data: function () {
    return {
      form: {
        toricdStart: "", // 取引先コード開始
        toricdEnd: "", // 取引先コード終了
        torikjnm: "", // 名称漢字
        toriknnm: "", // 名称カナ
        hatukb: "", // 発注区分
        hatufmkb: "", // 発注フォーマット
        sosncd: "", // 送信先コード
      },
      disp: {
        torihikisakiList: [], // 取引先リスト
      },
      disabledFlg: {},
      TEXTINPUT_TYPE: TEXTINPUT_TYPE,
      CD_CONTENT_TYPE: CD_CONTENT_TYPE,
    };
  },

  created: function () {
    // 呼び出し画面に応じて処理する
    const beforeParam = this.$store.getters[STORE_VIEW_GET_PARAMS]("PMSK050");
    // 呼び出し元画面がメニュー画面の場合
    if (beforeParam) {
      // 検索条件部の全項目をブランクにする
      this.initSearch();
      // 一覧部の一覧にヘッダーのみを表示し、「件数」を0件にする
      this.initTable();
      // フォーカスを「取引先コード開始」に移動する
      this.$nextTick(function () {
        this.$refs.toricdStart.focus();
      });
    } else {
      // 状態管理(ストア)の画面データの検索条件(前回検索時)を検索条件部の全項目に設定する
      this.form.toricdStart = beforeParam.toricdStart;
      this.form.toricdEnd = beforeParam.toricdEnd;
      this.form.torikjnm = beforeParam.torikjnm;
      this.form.toriknnm = beforeParam.toriknnm;
      this.form.hatukb = beforeParam.hatukb;
      this.form.hatufmkb = beforeParam.hatufmkb;
      this.form.sosncd = beforeParam.sosncd;
      // クライアント処理の『検索実行』を呼出する
      this.doSearchTorihikisaki();
    }
  },

  computed: {
    // 取引先リストの数字計算
    tableListCount: function () {
      const tableListCount = this.disp.torihikisakiList?.length ?? 0;
      return tableListCount;
    },
  },

  watch: {
    // formオブジェクト内のすべての変更を監視する
    form: {
      handler: function () {
        this.$store.commit(STORE_VIEW_CHANGED, true);
      },
      deep: true,
    },
  },

  methods: {
    // ▼ --------------------------------------- 各入力イベント --------------------------------------- ▼
    doSearchTorihikisaki: function () {
      // 状態管理(ストア)の画面表示にプログレスバーの表示を設定する
      this.$store.commit(STORE_VIEW_PROGRESS, true);
      // サーバ処理の『件数チェック』を呼出する
      let self = this;
      co_post(PATH_PMSK050_CHECK, this.form, function (res) {
        if (res.status == HTTP_STATUS.OK) {
          // 件数が1001件以上の場合
          if (res.data.cnt > 1000) {
            self.initTable();
            // 状態管理(ストア)の画面表示にプログレスバーの非表示を設定する
            self.$store.commit(STORE_VIEW_PROGRESS, false);
            // エラーメッセージを設定し、以降の処理を中断する
            self.$store.commit(
              STORE_ERROR_ADDMSG,
              co_getMessage("CMS0032E", 1000)
            );
            return;
          }
          // 件数が501～1000件の場合
          else if (res.data.cnt > 500) {
            co_confirm(co_getMessage("CMS0015W", 500), function (flg) {
              // 確認ダイアログで「いいえ」押下の場合
              if (flg == DIALOG_BUTTON_LIST_KEY.CANCEL) {
                self.initTable();
                self.$store.commit(STORE_VIEW_PROGRESS, false);
                return;
              }
              self.doSearchMakerData();
            });
          }
          // 件数が0件の場合
          else if (res.data.cnt == 0) {
            self.initTable();
            self.$store.commit(STORE_VIEW_PROGRESS, false);
            return;
          } else {
            self.doSearchMakerData();
          }
        }
      });
    },

    // 検索API呼び出し
    doSearchMakerData: function () {
      const self = this;
      co_post(PATH_PMSK050_SEARCH, this.form, function (res) {
        if (res.status == HTTP_STATUS.OK) {
          self.disp.torihikisakiList = res.data.torihikisakiList;
          self.$store.commit(STORE_DATA_ADD_DATA, {
            id: "PMSK050",
            data: {
              toricdStart: self.form.toricdStart,
              toricdEnd: self.form.toricdEnd,
              torikjnm: self.form.torikjnm,
              toriknnm: self.form.toriknnm,
              hatukb: self.form.hatukb,
              hatufmkb: self.form.hatufmkb,
              sosncd: self.form.sosncd,
            },
          });
        }
      });
      this.$store.commit(STORE_VIEW_PROGRESS, false);
    },

    // 検索ボタンクリック
    clickSearchBtn: function () {
      this.doSearchTorihikisaki();
    },

    // 取引先コードリンククリック
    clickToricdLink: function (toricd) {
      co_nextView(PATH_PMST120, {
        toricd: toricd,
      });
    },

    // 戻るボタンクリック
    clickReturnBtn: function () {
      this.checkFormBlank();
      if (this.$store.getters[STORE_VIEW_HAS_CHANGED]) {
        co_confirm(co_getMessage("CMS0002W"), function (btnValue) {
          if (btnValue == DIALOG_BUTTON_LIST_KEY.OK) {
            co_backView();
          }
        });
      } else {
        co_backView();
      }
    },

    // 取消ボタンクリック
    clickCancelBtn: function () {
      this.checkFormBlank();
      let self = this;
      if (this.$store.getters[STORE_VIEW_HAS_CHANGED]) {
        co_confirm(co_getMessage("CMS0003W"), function (btnValue) {
          if (btnValue == DIALOG_BUTTON_LIST_KEY.OK) {
            // 検索条件部の全項目をブランクにする
            self.initSearch();
            // 一覧部の一覧にヘッダーのみを表示し、「件数」を0件にする
            self.initTable();
            // フォーカスを「取引先コード開始」に移動する
            self.$nextTick(function () {
              self.$refs.toricdStart.focus();
            });
          }
        });
      } else {
        // 検索条件部の全項目をブランクにする
        this.initSearch();
        // 一覧部の一覧にヘッダーのみを表示し、「件数」を0件にする
        this.initTable();
        // フォーカスを「取引先コード開始」に移動する
        this.$refs.toricdStart.focus();
      }
    },

    // 追加ボタンクリック
    clickAddBtn: function () {
      co_nextView(PATH_PMST120);
    },

    // CSV出力ボタンクリック
    clickCsvOutputBtn: function () {
      // 状態管理(ストア)のエラー情報をクリアする
      this.$store.commit(STORE_ERROR_CLEAR);
      // 状態管理(ストア)の画面表示にプログレスバーの表示を設定する
      this.$store.commit(STORE_VIEW_PROGRESS, true);
      // サーバ処理の『csv出力』を呼出する
      let self = this;
      co_downloadFile(
        PATH_PMSK050_OUTPUT,
        this.form,
        function (res, downloadHandle) {
          if (res.status == HTTP_STATUS.OK) {
            downloadHandle(res);
          } else {
            co_setError(res.data, self.$refs);
            return;
          }
          self.$store.commit(STORE_VIEW_PROGRESS, false);
        }
      );
    },

    // CSV取込ボタンクリック
    clickCsvInputBtn: function () {
      co_nextView(PATH_PMSK051);
    },

    // ▼ ---------------------------------------- サブメソッド ---------------------------------------- ▼
    // 検索条件部の初期化
    initSearch: function () {
      this.form.toricdStart = "";
      this.form.toricdEnd = "";
      this.form.torikjnm = "";
      this.form.toriknnm = "";
      this.form.hatukb = "";
      this.form.hatufmkb = "";
      this.form.sosncd = "";
    },
    // 一覧部の初期化
    initTable: function () {
      this.disp.torihikisakiList = [];
    },
    //
    checkFormBlank: function () {
      if (
        this.form.hatufmkb == "" &&
        this.form.hatukb == "" &&
        this.form.sosncd == "" &&
        this.form.toricdEnd == "" &&
        this.form.toricdStart == "" &&
        this.form.torikjnm == "" &&
        this.form.toriknnm == ""
      ) {
        this.$store.commit(STORE_VIEW_CHANGED, false);
      }
    },
  },
};
</script>
<style scoped>
.mgn-l-0px {
  margin-left: 0px !important;
}
.mgn-horizontal-0px {
  margin-top: 0px !important;
  margin-bottom: 0px !important;
}
.link-input {
  color: blue;
  text-decoration: underline;
}
.tbl-head {
  background-color: #d0d3d4;
}
.table-content-header {
  padding-top: 0px !important;
  margin-right: 190px !important;
}
</style>
