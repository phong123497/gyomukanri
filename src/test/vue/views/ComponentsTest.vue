<template>
  <div id="test">
    <!-- メインコンテンツ -->
    <div class="contents__block--exist_return">
    <div class="contents__wrap--scroll_vertical">
      <error-message />
      <file-upload></file-upload>
      <br />
      <input type="button" class="contents__btn-blue" value="throw ERROR" v-on:click="errorBtnClick">
      <input type="button" class="contents__btn-blue" value="validation ERROR" v-on:click="verrorBtnClick">
      <input type="button" class="contents__btn-blue" value="add data to store" v-on:click="addBtnClick">
      <br />
      <table class="tbl-item--border-row_exist">
        <tbody>
          <tr>
            <td colspan="2">
              本社・支社等区分
              <span class="require">必須</span>
            </td>
            <td>
              <div class="contents__block-radio">
                <label>
                  <input type="radio" name="honsyaKbn" value="0" v-model="form.honsyaKbn">
                  <span class="radio-dummy">本社</span>
                </label>
                <label>
                  <input type="radio" name="honsyaKbn" value="1" v-model="form.honsyaKbn">
                  <span class="radio-dummy">支社・支店・工場</span>
                </label>
                <label>
                  <input type="radio" name="honsyaKbn" value="2" v-model="form.honsyaKbn">
                  <span class="radio-dummy">その他</span>
                </label>
              </div>
            </td>
          </tr>
          <tr>
            <td colspan="2">
              事業所名
              <span class="require">必須</span>
            </td>
            <td>
              <input type="text" class="input--jigyosyo-name" value="" v-model="form.jigyosyoName">
            </td>
          </tr>
          <tr>
            <td colspan="2">
              事業所名かな
              <span class="require">必須</span>
            </td>
            <td>
              <input type="text" class="input--jigyosyo-kana" value="" v-model="form.jigyosyoKana">
            </td>
          </tr>
          <tr>
            <td colspan="2">
              日付
            </td>
            <td>
              年月日必須：
              <!-- <date-input :text="form.date1" @input="form.date1 = $event.target.value" /><br /> -->
              <!-- <date-input v-model="form.date1"
                :name="'年月日必須'"
                :requireFlg=true
                :dateType="dateType.ymd"
                ref="dateInput1"
               /><br />
              年月：
              <date-input v-model="form.date2"
                :name="'年月'"
                :dateType="dateType.ym"
                ref="dateInput2"
              /> -->
            </td>
          </tr>
          <tr>
            <td colspan="2">
              テキストインプット
            </td>
            <td>
              全角必須：
              <!-- <text-input
                class="contents__text--keyword"
                v-model="form.text1"
                :name="'テキスト全角'"
                :requireFlg=true
                :textType="textType.full"
                :maxLength=10
                :placeholder="'事業所名、住所、安定所名'"
                ref="textInput1" /><br />
              ひらがな：
              <text-input
                class="contents__text--keyword"
                v-model="form.text2"
                :name="'テキストひらがな'"
                :textType="textType.fullKana"
                ref="textInput2" /><br />
              半角：
              <text-input
                class="contents__text--keyword"
                v-model="form.text3"
                :name="'テキスト半角'"
                :textType="textType.half"
                ref="textInput3" /><br />
              <input type="button" class="contents__btn-blue" value="validate" v-on:click="textValidateClick"> -->
            </td>
          </tr>
          <tr>
            <td colspan="2">
              リストボックス確認用
            </td>
            <td>
              リストボックス（json）：
              <!-- <list-box
                v-model="form.listBox1"
                v-bind:name="'リストボックス（json）'"
                v-bind:requireFlg=true
                v-bind:jsonObject="jsonObject"
                v-bind:blankFlg="true"
                ref="listBoxSelected1">
              </list-box><br />
              リストボックス（DB）：
              <list-box
                v-model="form.listBox2"
                v-bind:name="'リストボックス（DB）'"
                v-bind:requireFlg=true
                v-bind:cdSbt="cdSbt.taisyoZigyousyo"
                v-bind:blankFlg="false"
                ref="listBoxSelected2">
              </list-box> -->
            </td>
          </tr>
          <tr>
            <td colspan="2">
              連動リストボックス確認用
            </td>
            <td>
              リストボックス大分類：
              <!-- <list-box
                v-model="form.listBoxDaiBunrui"
                v-bind:name="'リストボックス大分類'"
                v-bind:requireFlg=true
                v-bind:cdSbt="cdSbt.sangyoDaiBunrui"
                v-bind:blankFlg="true"
                ref="listBoxSelected3">
              </list-box><br />
              リストボックス中分類：
              <list-box
                v-model="form.listBoxCyuBunrui"
                v-bind:name="'リストボックス中分類'"
                v-bind:requireFlg=true
                v-bind:cdSbt="cdSbt.sangyoCyuBunrui"
                v-bind:fukuCd="form.listBoxDaiBunrui"
                v-bind:blankFlg="true"
                ref="listBoxSelected4">
              </list-box>
              リストボックス支部：
              <list-box
                v-model="form.listBoxShibu"
                v-bind:name="'リストボックス支部'"
                v-bind:requireFlg=true
                v-bind:cdSbt="cdSbt.shibu"
                v-bind:blankFlg="true"
                ref="listBoxSelected5">
              </list-box><br />
              リストボックス安定所：
              <list-box
                v-model="form.listBoxAntei"
                v-bind:name="'リストボックス安定'"
                v-bind:requireFlg=true
                v-bind:cdSbt="cdSbt.antei"
                v-bind:shibuCd="form.listBoxShibu"
                v-bind:blankFlg="true"
                ref="listBoxSelected6">
              </list-box> -->
            </td>
          </tr>
          <tr>
             <td colspan="2">
               ラジオボタン確認用
             </td>
             <td>
              ラジオボタン非活性確認用：
              <list-box
                v-model="form.listBoxRadioKakunin"
                v-bind:name="'リストボックス'"
                v-bind:jsonObject="jsonObject2"
                v-bind:blankFlg="true">
              </list-box><br />
               本社・支社：
              <radio-button
                v-model="form.radioButtonHonsyaShisya"
                v-bind:name="'ラジオボタン本社・支社'"
                v-bind:requireFlg=true
                v-bind:cdSbt="cdSbt.honsyaShisya"
                v-bind:radioGroupName="radioHonsyaShisya"
                v-bind:excludeCd="excludeCd"
                ref="radioButton1">
              </radio-button><br />
              継続雇用先：
              <radio-button
                v-model="form.radioButtonKeizokuKoyosaki"
                v-bind:name="'ラジオボタン継続雇用先'"
                v-bind:cdSbt="cdSbt.keizokuKoyosaki"
                v-bind:radioGroupName="radioKeizokuKoyosaki"
                v-bind:disableFlg=radioDisableFlg>
              </radio-button>
              <input type="button" class="contents__btn-blue" value="validate" v-on:click="radioValidateClick">
             </td>
           </tr>
           <tr>
             <td colspan="2">
               ダイアログ確認用
             </td>
             <td>
              <button type="button" class="contents__btn-blue" v-on:click="kakuninButtonClick">確認</button>
              <button type="button" class="contents__btn-blue" v-on:click="kakuninButton2Click">確認2</button>
              <button type="button" class="contents__btn-blue" v-on:click="kakuninButton3Click">確認3</button>
             </td>
           </tr>
        </tbody>
      </table>
      <!-- <table class="tbl-item--border-row_exist">
        <tbody>
          <tr>
            <td>数値入力確認用</td>
            <td>
              <number-input
                v-bind:number-type="numType.INTEGER"
                v-bind:max-length=7
                v-bind:decimal-length=2
                v-bind:name="'数値入力（必須）'"
                v-bind:placeholder="''"
                v-bind:require-flg=true
                v-model="form.num1"
                ref="numInput1"
              />
              <input type="button" class="contents__btn-blue" value="数値入力チェック" v-on:click="numValidateClick">
             </td>
          </tr>
          <tr>
            <td>コード値入力確認用</td>
            <td>
              <code-input
                v-bind:max-length=3
                v-bind:name="'コード値入力'"
                v-bind:requireFlg=true
                v-model="form.code1"
                ref="codeInput1"
              />
              <input type="button" class="contents__btn-blue" value="コード値入力チェック" v-on:click="codeValidateClick">
            </td>
          </tr>
          <tr>
            <td>郵便番号</td>
            <td>
              <postal-code-input
                v-bind:name="'郵便番号'"
                v-bind:requireFlg=true
                v-model="form.postalCode"
                ref="postalCodeInput"
              />
            </td>
          </tr>
          <tr>
            <td>電話番号</td>
            <td>
              <phone-number-input
                v-bind:name="'電話番号'"
                v-bind:requireFlg=true
                v-model="form.phoneNumber"
                ref="phoneNumberInput"
              />
            </td>
          </tr>
        </tbody>
      </table> -->
      </div>
    </div>
  </div>
</template>

<script>
import ErrorMessage from '@/components/ErrorMessage.vue';
import FileUpload from '@/components/FileUpload.vue';
import RadioButton from '@/components/RadioButton.vue';
//import TextInput from '@/components/TextInput.vue';
//import DateInput from '@/components/DateInput.vue';
import ListBox from '@/components/ListBox.vue';
//import NumberInput from '@/components/NumberInput.vue';
//import CodeInput from '@/components/CodeInput.vue';
//import PostalCodeInput from '@/components/PostalCodeInput.vue';
//import PhoneNumberInput from '@/components/PhoneNumberInput.vue';

import { co_post } from '@/common/ajaxUtility.js';
import { co_notice,  co_confirm, co_alert } from '@/common/dialogUtility.js';
import { STORE_VIEW_CHANGED, STORE_ERROR_CLEAR, STORE_VIEW_TITLE } from '@/const/store.js';
import { API_HOUMON_SEARCH_CNT } from '@/const/route.js';

import { TEXTINPUT_TYPE, DATEINPUT_TYPE, CD_SBT } from '@/const/const.js';
import * as Const from '@/const/const.js';

export default {
    data() {
        return {
            form: {
                honsyaKbn: 0,
                jigyosyoName: "aaa",
                jigyosyoKana: "",
                ageFrom: "",
                ageTo: "",
                text1: "",
                text2: "",
                text3: "TEST",
                date1: "2020/01/01",
                date2: "2020/02",
                listBox1: "0001",
                listBox2: "0002",
                listBoxDaiBunrui: "",
                listBoxCyuBunrui: "",
                listBoxShibu: "",
                listBoxAntei: "",
                listBoxRadioKakunin: "",
                radioButtonHonsyaShisya: "",
                radioButtonKeizokuKoyosaki: "",
                num1: null,
                code1: null,
                postalCode: null,
                phoneNumber: null
            },
            dateType: {
                ymd: DATEINPUT_TYPE.YMD,
                ym: DATEINPUT_TYPE.YM,
            },
            textType: {
                full: TEXTINPUT_TYPE.FULL,
                fullKana: TEXTINPUT_TYPE.HIRAGANA,
                half: TEXTINPUT_TYPE.HALF
            },
            numType: Const.NUMBERINPUT_TYPE,
            cdSbt: {
                honsyaShisya: CD_SBT.HONSHA_SHISHA,
                keizokuKoyosaki: CD_SBT.KEIZOKU_KOYOSAKI,
                taisyoZigyousyo: CD_SBT.TAISYO_ZIGYOUSYO,
                shibu: CD_SBT.SHIBU,
                antei: CD_SBT.ANTEI,
                sangyoDaiBunrui: CD_SBT.SANGYO_DAI_BUNRUI,
                sangyoCyuBunrui: CD_SBT.SANGYO_CYU_BUNRUI
            }
        }
    },
    components: {
        'error-message': ErrorMessage,
        'file-upload': FileUpload,
        'radio-button': RadioButton,
        //'text-input': TextInput,
        //'date-input': DateInput,
        'list-box': ListBox,
        //'number-input': NumberInput,
        //'code-input': CodeInput,
        //'postal-code-input': PostalCodeInput,
        //'phone-number-input': PhoneNumberInput
    },
    created: function () {
        // 初期化
        this.$store.commit(STORE_VIEW_TITLE, 'テスト画面');

        //
        let yyyy = parseInt('2020', 10);
        let array = new Array();
		// 過去５年
		for ( let i = 5; i > 0; i--) {
			array.push({cd: String(yyyy - i), cdNaiyo: String(yyyy - i)});
		}
		// 今年度
		array.push({cd: String(yyyy), cdNaiyo: String(yyyy)});
		// 次年度
		array.push({cd: String(yyyy + 1), cdNaiyo: String(yyyy + 1)});

        this.jsonObject = array; /*[
            {"cd":"01","cdNaiyo":"2020訪問対象事業所1(Json)"},
            {"cd":"02","cdNaiyo":"2020訪問対象事業所2(Json)"},
            {"cd":"03","cdNaiyo":"2020訪問対象事業所3(Json)"},
            {"cd":"04","cdNaiyo":"2020訪問対象事業所4(Json)"}
           ];*/
        // ラジオボタン非活性確認用
        this.jsonObject2 = [
            {"cd":"01","cdNaiyo":"ラジオボタン活性"},
            {"cd":"02","cdNaiyo":"ラジオボタン非活性"}
           ];

        // コードマスタ.コードの除外対象
        this.excludeCd = ['0','3'];

    },
    watch: {
        // formオブジェクト内のすべての変更を監視する
        form: {
          handler: function (/* val, old */) {
              this.$store.commit(STORE_VIEW_CHANGED, true);
          },
          deep: true
        },
        'form.listBoxRadioKakunin': {
            handler: function (val) {
                if(val == '02'){
                    this.radioDisableFlg = true;
                } else {
                    this.radioDisableFlg = false;
                }
            }
        }

    },
    methods: {
      // 「確認」ボタンクリックイベント
      kakuninButtonClick: function() {
          // TODO テスト用ダイアログ
          co_confirm('確認ボタンを押下しました。（確認）', this.callBackKakunin);
      },
      callBackKakunin: function (val) {
          if(val === 'ok') {
              window.confirm('"OK"が選択されました（戻り値 = ' + val +  '）');
          } else {
              window.confirm('"CANCEL"が選択されました（戻り値 = ' + val +  '）');
          }
      },
      // 「確認2」ボタンクリックイベント
      kakuninButton2Click: function() {
          // TODO テスト用ダイアログ
          co_alert('確認ボタン2を押下しました。（警告）', this.callBackKakunin2);
      },
      callBackKakunin2: function (val) {
          if(val === 'ok') {
              window.confirm('"OK"が選択されました（戻り値 = ' + val +  '）');
          } else {
              window.confirm('"CANCEL"が選択されました（戻り値 = ' + val +  '）');
          }
      },
      // 「確認3」ボタンクリックイベント
      kakuninButton3Click: function() {
          // TODO テスト用ダイアログ
          co_notice('確認ボタン3を押下しました。（通知）', this.callBackKakunin3);
      },
      callBackKakunin3: function (val) {
          window.confirm('"OK"が選択されました（戻り値 = ' + val +  '）');
      },
      errorBtnClick: function () {
          //this.$store.commit('add', 1);
          throw new Error('ERROR TEST');
      },
      verrorBtnClick: async function () {
          var form = {
                items: [{ val: "test" }]
          }
          await co_post(API_HOUMON_SEARCH_CNT, form);
      },
      addBtnClick: function () {
          this.$store.commit('data/addData', {id:'TEST', data:this.form});
          var obj = this.$store.getters['data/getData']('TEST');
          console.log(obj);
      },
      textValidateClick: function () {
          // エラーを初期化
          this.$store.commit(STORE_ERROR_CLEAR);
          // 入力チェック
          this.$refs.textInput1.validate();
          this.$refs.textInput2.validate();
          this.$refs.textInput3.validate();

          this.$refs.dateInput1.validate();
          this.$refs.dateInput2.validate();

          this.$refs.listBoxSelected1.validate();
          this.$refs.listBoxSelected2.validate();
          this.$refs.listBoxSelected3.validate();
          this.$refs.listBoxSelected4.validate();
          this.$refs.listBoxSelected5.validate();
          this.$refs.listBoxSelected6.validate();

          console.log(this.form);
      },
      numValidateClick: function () {
          // エラーを初期化
          this.$store.commit(STORE_ERROR_CLEAR);
          // 入力チェック
          this.$refs.numInput1.validate();
      },
      codeValidateClick: function () {
          // エラーを初期化
          this.$store.commit(STORE_ERROR_CLEAR);
          // 入力チェック
          this.$refs.codeInput1.validate();
          this.$refs.postalCodeInput.validate();
          this.$refs.phoneNumberInput.validate();
      },
      radioValidateClick: function () {
          // エラーを初期化
          this.$store.commit(STORE_ERROR_CLEAR);
          // 入力チェック
          this.$refs.radioButton1.validate();
      }
    }
}
</script>

<style scoped>
</style>