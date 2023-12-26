<!--
//================================================================================
// ID: PMSK020
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// VueName: 【マスタ】名称／区分管理マスタコード一覧
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/09/13 |  AMC                 | 新規作成
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
                <td>名称／区分管理マスタコード一覧</td>
              </tr>
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
              <div id="MSK020Head">
                <table>
                  <colgroup>
                    <col style="width: 140px" />
                    <col style="width: 50px" />
                    <col style="width: 380px" />
                    <col style="widows: 1210px" />
                  </colgroup>
                  <tbody>
                    <tr>
                      <td class="txt--align_r">名称区分</td>
                      <td class="txt--align_c">
                        <label>{{
                          this.form.meishoKubun == ""
                            ? "XX"
                            : this.form.meishoKubun
                        }}</label>
                      </td>
                      <td class="txt--align_l">
                        <label>{{
                          this.disp.meishoKubunNm == ""
                            ? "適当なし"
                            : this.disp.meishoKubunNm
                        }}</label>
                      </td>
                      <td></td>
                    </tr>
                    <tr>
                      <td class="txt--align_r">名称コード</td>
                      <td colspan="3" class="padding-l-5px">
                        <code-input
                          class="width-270"
                          ref="meishoCd"
                          v-model="form.meishoCd"
                          v-bind:name="'名称コード'"
                          v-bind:maxLength="20"
                          v-bind:tabindex="'1'"
                          v-bind:activeZeroPadding="false"
                          v-bind:disabledFlg="disabledFlg.search"
                          v-bind:textType="TEXTINPUT_TYPE.HALF"
                        />
                      </td>
                    </tr>
                    <tr>
                      <td class="txt--align_r">名称コード名</td>
                      <td colspan="2" class="padding-l-5px">
                        <text-input
                          class="width-390"
                          ref="meishoCdNm"
                          v-model="form.meishoCdNm"
                          v-bind:name="'名称コード名'"
                          v-bind:maxLength="20"
                          v-bind:tabindex="'2'"
                          v-bind:textType="TEXTINPUT_TYPE.FULL"
                          v-bind:disabledFlg="disabledFlg.search"
                        />
                      </td>
                      <td>
                        <button-input
                          class="contents__btn-blue"
                          v-bind:value="'検索'"
                          v-bind:name="'検索ボタン'"
                          v-bind:tabindex="'3'"
                          v-on:click="clickSearchBtn"
                          v-bind:disabledFlg="disabledFlg.search"
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
              <div class="contents__block-top__area--float_l">
                <h2 class="contents__h2">コード一覧</h2>
              </div>
              <div
                class="contents__block-top__area--float_r"
                style="padding-top: 0px"
              >
                <span>{{ tableListCount }}件</span>
              </div>
            </div>
            <div id="MSK020List" class="tbl-list__wrap">
              <table class="tbl-list--border-all_exist wrap--overflow_hidden">
                <colgroup>
                  <col style="width: 260px" />
                  <col style="width: 380px" />
                  <col style="width: 260px" />
                  <col style="width: 380px" />
                  <col style="width: 260px" />
                  <col style="width: 110px" />
                  <col style="width: 90px" />
                  <col style="width: 80px" />
                </colgroup>
                <thead>
                  <tr>
                    <th>名称コード</th>
                    <th>漢字名称</th>
                    <th>カナ名称</th>
                    <th>漢字略称</th>
                    <th>カナ略称</th>
                    <th>削除フラグ</th>
                    <th>削除対象</th>
                    <th></th>
                  </tr>
                </thead>
                <tbody>
                  <tr
                    v-for="(item, index) in this.disp.tableList"
                    v-bind:key="index"
                    v-bind:index="index"
                  >
                    <td class="txt--align_c">
                      <label>{{ item.meishoCd }}</label>
                    </td>
                    <td class="txt--align_c">
                      <label>{{ item.kanjiMeisho }}</label>
                    </td>
                    <td class="txt--align_c">
                      <label>{{ item.kanaMeisho }}</label>
                    </td>
                    <td class="txt--align_c">
                      <label>{{ item.kanjiRyakusho }}</label>
                    </td>
                    <td class="txt--align_c">
                      <label>{{ item.kanaRyakusho }}</label>
                    </td>
                    <td class="txt--align_c">
                      <label>{{ item.delFlg == 1 ? "X" : "" }}</label>
                    </td>
                    <td class="txt--align_c">
                      <div id="delTarget" class="contents__block-checkbox">
                        <check-box
                          style="display: none !important"
                          v-bind:checked="item.delFlg == '1'"
                          v-bind:cdContentType="CD_CONTENT_TYPE.SHORTENED_NAME"
                          v-bind:listItems="disp.checkboxlistItems"
                          v-bind:name="'削除対象' + index"
                          v-bind:ref="'delTarget' + index"
                          v-bind:tabindex="'4'"
                          v-model="item.delFlg"
                          v-on:click="clickDelTarget(index)"
                          v-bind:disabledFlg="item.delFlg == 1"
                        />
                      </div>
                    </td>
                    <td class="txt--align_c">
                      <button-input
                        class="shosai-button"
                        v-bind:name="'詳細ボタン'"
                        v-bind:value="'詳細'"
                        v-bind:tabindex="'5'"
                        v-on:click="clickDetailButton(item.meishoCd)"
                      />
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
                class="contents__btn-blue"
                v-bind:value="'戻る'"
                v-bind:name="'戻るボタン'"
                v-bind:tabindex="'8'"
                v-on:click="clickReturnBtn"
              />
              <button-input
                class="contents__btn-blue"
                v-bind:value="'取消'"
                v-bind:name="'取消ボタン'"
                v-bind:tabindex="'9'"
                v-on:click="clickCancelBtn"
              />
            </div>
          </div>
          <div class="center-block">
            <div class="button-group">
              <button-input
                class="contents__btn-blue"
                v-bind:value="'削除'"
                v-bind:name="'削除ボタン'"
                v-bind:tabindex="'6'"
                v-bind:disabledFlg="disabledFlg.deleteButton"
                v-on:click="clickDeleteBtn"
              />
              <button-input
                class="contents__btn-blue"
                v-bind:value="'追加'"
                v-bind:name="'追加ボタン'"
                v-bind:tabindex="'7'"
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
import ErrorMessage from "@/components/ErrorMessage.vue";
import TextInput from "@/components/TextInput.vue";
import CodeInput from "@/components/CodeInput.vue";
import ButtonInput from "@/components/ButtonInput.vue";
import CheckBox from "@/components/CheckBox.vue";
import {
  TEXTINPUT_TYPE,
  HTTP_STATUS,
  DIALOG_BUTTON_LIST_KEY,
  CD_CONTENT_TYPE,
} from "@/const/const.js";
import {
  STORE_VIEW_GET_PARAMS,
  STORE_DATA_GETDATA,
  STORE_VIEW_PROGRESS,
  STORE_ERROR_ADDMSG,
  STORE_DATA_ADD_DATA,
  STORE_ERROR_CLEAR,
} from "@/const/store.js";
import { co_post, co_moveSystemError } from "@/common/ajaxUtility.js";
import {
  PATH_PMSK020_CHECK,
  PATH_PMSK020_SEARCH,
  PATH_PMSK030,
  PATH_PMSK020_CHECK_EXIST,
  PATH_PMSK020_DELETE,
} from "@/const/route.js";
import { co_getMessage } from "@/common/messageUtility.js";
import { co_confirm, co_notice } from "@/common/dialogUtility.js";
import { co_nextView, co_backView } from "@/common/viewUtility.js";

export default {
  components: {
    "error-message": ErrorMessage,
    "text-input": TextInput,
    "code-input": CodeInput,
    "button-input": ButtonInput,
    "check-box": CheckBox,
  },
  data: function () {
    return {
      form: {
        meishoKubun: "",
        meishoCd: "",
        meishoCdNm: "",
        meishoCdList: [], // 削除用
      },
      disp: {
        // 一覧リスト
        tableList: [],
        // 削除対象
        checkboxlistItems: [
          {
            code: 1,
            name: "",
          },
        ],
        // 名称区分名
        meishoKubunNm: "",
      },
      disabledFlg: {
        // 入力エリア
        search: false,
        // 個別項目指定
        deleteButton: true,
      },
      TEXTINPUT_TYPE: TEXTINPUT_TYPE,
      CD_CONTENT_TYPE: CD_CONTENT_TYPE,
    };
  },
  created: function () {
    // パラメータを取得
    const beforeParam = this.$store.getters[STORE_VIEW_GET_PARAMS]("PMSK020");
    // 名称／区分管理マスタ区分一覧画面からの遷移の場合
    if (beforeParam && beforeParam.id == "PMSK010") {
      // レコード区分がブランクの場合
      if (beforeParam.reckb == null) {
        // システムエラーを発生し、以降の処理を中断する
        co_moveSystemError();
        return;
      }
      // 検索条件部の「名称コード」～「名称コード名」の全ての値をブランクに設定する。
      this.initSearch();

      // 一覧部の一覧にヘッダーのみを表示し、「件数」を0件にする。
      this.disp.tableList = [];

      this.$nextTick(function () {
        // 入力グループに応じて画面項目を活性制御する。
        this.disabledFlg.deleteButton = true;
        // 「名称コード」にフォーカスが移動する。
        this.$refs.meishoCd.focus();
      });
    }
    // 名称／区分管理マスタメンテ画面からの遷移の場合
    else {
      this.initSearch();
      // 遷移前の検索条件を取得し、検索条件部の「名称コード」～「名称コード名」に設定する。
      let savedData = this.$store.getters[STORE_DATA_GETDATA]("PMSK020");
      if (savedData != null) {
        this.form.meishoCd = savedData.meishoCd;
        this.form.meishoCdNm = savedData.meishoCdNm;
      }

      // 検索処理を実行する。
      this.doSearchMeishoCd();
      // 一覧部に値を設定し、「件数」を取得した一覧の件数を表示する。
      // 入力グループに応じて画面項目を活性制御する。
    }
  },
  computed: {
    // 一覧部リストの数字計算
    tableListCount: function () {
      const tableListCount = this.disp.tableList?.length ?? 0;
      return tableListCount;
    },
  },
  watch: {},
  methods: {
    // ▼ --------------------------------------- 各入力イベント --------------------------------------- ▼
    // 検索ボタンクリック
    clickSearchBtn: function () {
      // 状態管理(ストア)のエラークリア
      this.$store.commit(STORE_ERROR_CLEAR);
      // クライアント処理の『検索実行』を呼出する。
      this.doSearchMeishoCd();
    },

    // 検索実行
    doSearchMeishoCd: function () {
      // 状態管理(ストア)の画面表示にプログレスバーの表示を設定する。
      this.$store.commit(STORE_VIEW_PROGRESS, true);

      // サーバ処理の『件数チェック』を呼出する。
      let self = this;
      co_post(PATH_PMSK020_CHECK, this.form, function (res) {
        if (res.status == HTTP_STATUS.OK) {
          // 検索結果件数が1001件以上の場合
          if (res.data.cnt > 1000) {
            self.$store.commit(STORE_VIEW_PROGRESS, false);
            self.$store.commit(
              STORE_ERROR_ADDMSG,
              co_getMessage("CMS0032E", 1000)
            );
            self.disabledFlg.deleteButton = true;
          }
          // 検索結果件数501～1000件の場合は、確認ダイアログを表示する。
          else if (res.data.cnt > 500) {
            co_confirm(co_getMessage("CMS0015W", 500), function (flg) {
              // 確認ダイアログで「いいえ」押下の場合
              if (flg == DIALOG_BUTTON_LIST_KEY.CANCEL) {
                self.$store.commit(STORE_VIEW_PROGRESS, false);
                return;
              }
              self.doSearchMakerData();
              self.$store.commit(STORE_VIEW_PROGRESS, false);
              // 画面制御
              self.disabledFlg.deleteButton = false;
              self.disabledFlg.search = false;
            });
          }
          // CMS0015W検索件数が0件の場合
          else if (res.data.cnt == 0) {
            self.disp.tableList = [];
            self.$store.commit(STORE_VIEW_PROGRESS, false);
            self.$store.commit(STORE_ERROR_ADDMSG, co_getMessage("CMS0033E"));
            // 画面制御
            self.disabledFlg.deleteButton = true;
          }
          // 検索処理を実行する。
          else {
            self.doSearchMakerData();
            self.disabledFlg.deleteButton = false;
            self.disabledFlg.search = true;
          }
        }
      });
      this.$store.commit(STORE_VIEW_PROGRESS, false);
    },

    // 検索API呼び出し
    doSearchMakerData: function () {
      const self = this;
      co_post(PATH_PMSK020_SEARCH, this.form, function (res) {
        if (res.status == HTTP_STATUS.OK) {
          // meishoCdList(削除用リスト)をリセット
          self.form.meishoCdList = [];
          // 取得したデータを一覧部に設定する。
          self.disp.tableList = res.data.pmsk020ListItems;
          // 検索条件部の「名称コード」、「名称コード名」を状態管理(ストア)の画面データの検索条件に保持する。
          self.$store.commit(STORE_DATA_ADD_DATA, {
            id: "PMSK020",
            data: {
              meishoCd: self.form.meishoCd,
              meishoCdNm: self.form.meishoCdNm,
            },
          });
          // 状態管理(ストア)の画面表示にプログレスバーの非表示を設定する。
          self.$store.commit(STORE_VIEW_PROGRESS, false);
        }
      });
    },

    // 削除対象
    clickDelTarget: function (index) {
      // // 選択ボックスにチェックが入っていない場合
      if (
        this.disp.tableList[index].delFlg == "0" ||
        this.disp.tableList[index].delFlg == ""
      ) {
        this.form.meishoCdList.push(this.disp.tableList[index].meishoCd);
      } else {
        this.form.meishoCdList = this.form.meishoCdList.filter(
          (item) => item !== this.disp.tableList[index].meishoCd
        );
      }
    },

    // 詳細ボタンクリック
    clickDetailButton: function (meishoCd) {
      // パラメータを取得
      const beforeParam = this.$store.getters[STORE_VIEW_GET_PARAMS]("PMSK020");
      // 名称／区分管理マスタメンテ画面（PMSK030）に遷移する。
      if (beforeParam) {
        co_nextView(PATH_PMSK030, {
          id: "PMSK020",
          procsShubt: "2",
          reckb: beforeParam.reckb,
          meishoCd: meishoCd,
        });
      }
    },

    // 戻るボタンクリック: 「PMSK010_【マスタ】名称／区分管理マスタ区分一覧」に遷移する。
    clickReturnBtn: function () {
      co_backView();
    },

    // 取消ボタンクリック
    clickCancelBtn: function () {
      // 検索条件部の「名称コード」～「名称コード名」の全ての値をブランクに設定する
      this.initBody();

      // 一覧部の一覧にヘッダーのみを表示し、「件数」を0件にする。
      this.disp.tableList = [];

      // 入力グループに応じて画面項目を活性制御する。
      this.disabledFlg.deleteButton = true;
      this.disabledFlg.search = false;

      // 「名称コード」にフォーカスが移動する。
      this.$nextTick(function () {
        this.$refs.meishoCd.focus();
      });
    },

    // 削除ボタンクリック
    clickDeleteBtn: function () {
      // 状態管理(ストア)の画面表示にプログレスバーの表示を設定する。
      this.$store.commit(STORE_VIEW_PROGRESS, true);
      // 状態管理(ストア)のエラー情報をクリアする。
      this.$store.commit(STORE_ERROR_CLEAR);
      // ONになっている削除チェックボックスが存在するかチェックする。
      // ONになっている削除チェックボックスが存在しない場合
      if (this.form.meishoCdList.length == 0) {
        this.$store.commit(STORE_VIEW_PROGRESS, false);
      } else {
        // 確認メッセージのダイアログを表示する。
        let self = this;
        co_confirm(co_getMessage("CMS0005W"), function (flg) {
          // 確認ダイアログで「いいえ」押下の場合
          if (flg == DIALOG_BUTTON_LIST_KEY.CANCEL) {
            self.$store.commit(STORE_VIEW_PROGRESS, false);
            return;
          }
          // サーバ処理の『存在チェック』を呼出する
          co_post(PATH_PMSK020_CHECK_EXIST, self.form, function (res) {
            // 削除対象が存在しない場合
            if (res.data.exists == false) {
              self.$store.commit(STORE_VIEW_PROGRESS, false);
              self.$store.commit(
                STORE_ERROR_ADDMSG,
                co_getMessage("CMS0020E", "名称コード", "名称／区分管理")
              );
            } else {
              // サーバ処理の『削除』を呼出する
              co_post(PATH_PMSK020_DELETE, self.form, function (res) {
                if (res.status == HTTP_STATUS.OK) {
                  //  通知メッセージのダイアログを表示する。
                  co_notice(co_getMessage("CMS0003I", "削除"));
                  // 削除後、meishoCdList(削除用リスト)をリセット
                  self.form.meishoCdList = [];
                  // サーバ処理の『一覧検索』を呼出する。
                  self.doSearchMakerData();
                  self.$store.commit(STORE_VIEW_PROGRESS, false);
                } else {
                  self.$store.commit(STORE_VIEW_PROGRESS, false);
                }
              });
            }
          });
        });
      }
    },

    // 追加ボタンクリック
    clickAddBtn: function () {
      // パラメータを取得
      const beforeParam = this.$store.getters[STORE_VIEW_GET_PARAMS]("PMSK020");
      //「PMSK030_【マスタ】名称／区分管理マスタメンテ」に遷移する。
      if (beforeParam) {
        co_nextView(PATH_PMSK030, {
          id: "PMSK020",
          procsShubt: "1",
          reckb: beforeParam.reckb,
          meishoCd: "",
        });
      }
    },

    // ▼ ---------------------------------------- サブメソッド ---------------------------------------- ▼
    // 検索部初期化
    initSearch: function () {
      // パラメータを取得
      const beforeParam = this.$store.getters[STORE_VIEW_GET_PARAMS]("PMSK020");
      if (beforeParam != null) {
        this.form.meishoKubun = beforeParam.reckb;
        this.disp.meishoKubunNm = beforeParam.reckbNm;
        return;
      }
      this.form.meishoKubun = "03";
      this.disp.meishoKubunNm = "";
    },

    //
    initBody: function () {
      this.form.meishoCd = "";
      this.form.meishoCdNm = "";
    },
  },
};
</script>
<style type="text/css">
#PMSK020 .padding-l-5px {
  padding-left: 5px;
}
#PMSK020 .shosai-button {
  background: linear-gradient(90deg, #5e6adc 0%, #6e5bcf 100%);
  box-sizing: border-box;
  border: 1px solid transparent;
  border-radius: 22px;
  color: #fff;
  display: inline-block;
  height: 37px;
  vertical-align: middle;
}
#PMSK020 .shosai-button:hover {
  background: #fff;
  border: 1px solid #4158c6;
  color: #6167da;
}
#PMSK020 #delTarget .checkbox-label[data-v-1ac2b216]::before {
  top: -8px !important;
  left: -6px !important;
}
#PMSK020
  #delTarget
  .checkbox:checked
  + .checkbox-label[data-v-1ac2b216]::after {
  top: -7px !important;
  left: 3px !important;
}
</style>
