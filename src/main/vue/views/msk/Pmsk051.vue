1
<!-- eslint-disable no-unused-vars -->
<!--
//================================================================================
// ID: PMSK051
// ProjectName: 【マスタ】取引先マスタCSV取込
// SystemName: 取引先マスタCSV取込
// VueName: PMSK051
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/09/13 | ＡＭＣ               | 新規作成
//================================================================================
 -->
<template>
  <div>
    <!-- 取引先マスタCSV取込 -->
    <main id="PMSK051">
      <div class="contents__block--exist_return">
        <!-- 画面ヘッダ部 -->
        <h1 class="header__block-title">【マスタ】取引先マスタCSV取込</h1>
        <!-- エラーメッセージ -->
        <error-message />
        <div class="contents__wrap--scroll_vertical">
          <div class="contents__view--bg_gray">
            <table class="tbl-item--upload border-inline">
              <colgroup>
                <col style="width: 250px" />
                <col style="width: 1550px" />
              </colgroup>
              <tr class="border-b">
                <td>
                  <div class="display-flex-center">
                    取引元
                    <div class="requires">必要</div>
                  </div>
                </td>
                <td class="header__block-info--left width100">
                  <div>
                    <file-upload
                      v-bind:name="'取込元'"
                      v-bind:requireFlg="true"
                      ref="torikmMoto"
                      v-bind:fileExtensions="disp.fileExtensionList"
                      v-bind:clickUploadOrSaveBtn="clickSelectFileBtn"
                      v-bind:tabindex="'2'"
                    />
                  </div>
                </td>
              </tr>
            </table>
            <section>
              <div class="contents__block-top" style="width: 1150px">
                <div class="contents__block-top__area--float_l">
                  <h2 class="contents__h2">取込結果</h2>
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
                    <col style="width: 320px" />
                    <col style="width: 200px" />
                    <col style="width: 100px" />
                    <col style="width: 400px" />
                  </colgroup>
                  <thead>
                    <tr>
                      <th>ファイル名</th>
                      <th>取込結果</th>
                      <th>行番号</th>
                      <th>エラー内容</th>
                    </tr>
                  </thead>
                </table>
                <table class="tbl-list--border-all_exist wrap--overflow_hidden no-border">
                  <colgroup>
                    <col style="width: 320px" />
                    <col style="width: 200px" />
                    <col style="width: 100px" />
                    <col style="width: 400px" />
                  </colgroup>
                  <tbody>
                    <tr
                      v-for="(item, index) in this.disp.tableData"
                      v-bind:key="index"
                    >
                      <td class="border--top_none padding-left-6">
                        {{ item.fileNm }}
                      </td>
                      <td class="border--top_none padding-left-6">
                        {{ item.torikmReslt }}
                      </td>
                      <td class="border--top_none padding-left-6">
                        {{ item.gyoNo }}
                      </td>
                      <td class="border--top_none padding-left-6">
                        {{ item.errContents }}
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
            <div class="button-group">
              <button-input
                v-bind:name="'戻るボタン'"
                v-bind:value="'戻る'"
                v-bind:tabindex="'3'"
                class="contents__btn-blue margin-right-30"
                v-on:click="clickReturnBtn"
              />
              <button-input
                v-bind:name="'取消ボタン'"
                v-bind:value="'取消'"
                v-bind:tabindex="'4'"
                class="contents__btn-blue"
                v-on:click="clickCancelBtn"
              />
            </div>
          </div>
          <div class="center-block"></div>
          <div class="right-block">
            <div class="button-group">
              <button-input
                v-bind:name="'取込ボタン'"
                v-bind:value="'取込'"
                v-bind:tabindex="'2'"
                class="contents__btn-blue"
                v-bind:disabledFlg="disabledFlg.ImportBnt"
                v-on:click="clickImportBtn"
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
import FileUpload from "@/components/FileUpload.vue";
import { co_confirm } from "@/common/dialogUtility.js";
import { co_isEmpty } from "@/common/checkUtility.js";
import { co_nextView } from "@/common/viewUtility.js";
import { co_getMessage } from "@/common/messageUtility.js";
import { co_postFile } from "@/common/ajaxUtility.js";
import { DIALOG_BUTTON_LIST_KEY, HTTP_STATUS } from "@/const/const.js";
import {
  // STORE_VIEW_HAS_CHANGED,
  STORE_VIEW_PROGRESS,
  // STORE_VIEW_GETBACKURL,
} from "@/const/store.js";
import { PATH_PMSK050, PATH_PMSK051_READCSV } from "@/const/route.js";
export default {
  components: {
    "file-upload": FileUpload,
    "button-input": ButtonInput,
    "error-message": ErrorMessage,
  },
  computed: {
    amount: function () {
      const reg = /(\d)(?=(\d{3})+$)/g;
      const quantity = this.disp.tableData.length
        .toString()
        .replace(reg, "$1L,");
      return quantity;
    },
  },
  data: function () {
    return {
      form: {
        // 取込CSVファイル
        csvFile: null,
      },
      disabledFlg: {
        inputBnt: false,
      },
      disp: {
        // 一覧リスト
        tableData: [],
        // アップロード許可拡張子リスト
        fileExtensionList: ["csv"],
      },
    };
  },
  created: function () {
    // 「取込元」の値をブランクにする
    this.$refs.torikmMoto.clearSelectedFile();
    // 一覧部をヘッダのみ表示し、「件数」を0件にする
    this.disp.tableData == [];
    // フォーカスを「取込元」に移動する
  },
  mounted: async function() {
		// フォーカス設定
		await this.$nextTick();
		this.$refs.torikmMoto.focus();
	},
  methods: {
    // ファイルを選択ボタンクリック
    clickSelectFileBtn: function () {},
    clickImportBtn: function () {
      //  状態管理(ストア)の画面表示にプログレスバーの表示を設定する
      this.$store.commit(STORE_VIEW_PROGRESS, true);
      // 取り込んだCSVファイルを取得する
      this.form.csvFile = this.$refs.torikmMoto.getFile();
      // CSVファイルをアップロードする
      const self = this;
      // サーバ処理が正常終了した場合
      co_postFile(PATH_PMSK051_READCSV, this.form, function (res) {
        if (res.status == HTTP_STATUS.OK) {
          // 「件数」の値を設定する
          // 一覧部を設定す
          self.disp.tableData = res.data.resultList;
          // 画面表示にプログレスバーの非表示を設定する
          self.$store.commit(STORE_VIEW_PROGRESS, false);
        } else {
          // サーバから返却されたエラーメッセージを設定する
          // 画面表示にプログレスバーの非表示を設定する
          self.$store.commit(STORE_VIEW_PROGRESS, false);
        }
      });
    },
    // click - メニュー画面へ遷移する
    clickReturnBtn: function () {
      //変更がある場合
      if (!co_isEmpty(this.$refs.torikmMoto.fileName)) {
        co_confirm(co_getMessage("CMS0002W"), function (resultSelect) {
          // 確認ダイアログで「はい」押下の場合
          if (resultSelect == DIALOG_BUTTON_LIST_KEY.OK) {
            // 取引先マスタ一覧画面に遷移する
            co_nextView(PATH_PMSK050);
          } else {
            // 処理を終了する
            return;
          }
        });
      }
      //上記以外の場合
      else {
        // 取引先マスタ一覧画面に遷移する
        co_nextView(PATH_PMSK050);
      }
    },
    // click - 画面初期化する
    clickCancelBtn: function () {
      //変更がある場合
      if (!co_isEmpty(this.$refs.torikmMoto.fileName)) {
        //ダイアログの表示 コールバック関数:キャンセル実行
        let self = this;
        co_confirm(co_getMessage("CMS0003W"), function (resultSelect) {
          if (resultSelect == DIALOG_BUTTON_LIST_KEY.OK) {
            // 確認ダイアログを閉じ、処理を終了する
            //「取込元」の値をブランクにする
            self.$refs.torikmMoto.clearSelectedFile();
            // 一覧部をヘッダのみ表示し,「件数」を0件にする
            self.disp.tableData = [];
            // フォーカスを「取込元」に移動する。
            this.$nextTick(function () {
              this.$refs.torikmMoto.focus();
            });
          } else {
            // 確認ダイアログを閉じ、処理を終了する
            return;
          }
        });
      }
      //上記以外の場合
      else {
        //「取込元」の値をブランクにする。
        self.$refs.torikmMoto.clearSelectedFile();
        // 一覧部をヘッダのみ表示し、「件数」を0件にする
        self.disp.tableData = [];
        // フォーカスを「取込元」に移動する。
        this.$nextTick(function () {
          this.$refs.torikmMoto.focus();
        });
      }
    },
  },
};
</script>

<style scoped>
.width100 {
  width: 100% !important;
}
.requires {
  background-color: red;
  color: rgb(255, 255, 255);
  margin: 5px 5px;
  padding: 3px 3px 3px 3px;
  width: 50px;
  border-radius: 16px;
  font-size: 0.9rem;
  display: flex;
  align-items: center;
  justify-content: space-around;
}
.border-inline {
  border-inline: 2px solid #dedfe8;
}

.btn-display-change {
  display: flex;
  justify-content: space-between;
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

.border--top_none {
  border-top: none !important;
}

.padding-left-6 {
  padding-left: 6px;
}

.display-flex-center {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style>
