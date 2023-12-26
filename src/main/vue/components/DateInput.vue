<!--
//================================================================================
// ID: -
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// VueName: DateInput
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2022/10/28 |   AMC                | 新規作成
//================================================================================
 -->


<template>
	<div class="date-input" :style="switchDisplayMode">
		<input
				type="text"
				autocomplete="off"
				v-bind:value="showValue"
				v-bind:name="name"
				v-bind:class="[{'contents--error-item' : isError }, {'contents--require-item':!disabledFlg && requireFlg}]"
				v-bind:disabled="disabledFlg"
				v-bind:maxlength="dateBlurFormat.length"
				v-bind:tabindex="tabindex"
				v-on:change="changeInput"
				v-on:keydown.delete="keyControl"
				v-on:blur="blurInput($event.target.value)"
				v-on:focus="focusInput($event.target.value)"
				v-on:input="limitOnlyNumber($event)"
				v-on:keyup="limitOnlyNumber($event)"
				ref="dateInput"/>
		<v-date-picker v-model="date" :masks="masks" :is-required="isDateRequired" ref="calendar" @popoverDidShow="handleShow">
			<template v-slot="{ inputValue, togglePopover }">
				<button v-on:click="togglePopover({placement: 'bottom-end', visibility: 'hover-focus' })"
					tabindex="-1"
					v-bind:disabled="disabledFlg" >
					<img src="img/icon_calendar.png">
				</button>
				<input type="hidden" :value="inputValue"/>
			</template>
		</v-date-picker>
		
	</div>
</template>
<script>
import 'v-calendar/dist/style.css';
import moment from 'moment';
import { DatePicker } from 'v-calendar';

import { co_isEmpty } from '@/common/checkUtility.js';
import { co_getMessage } from '@/common/messageUtility.js';

import {
	DATEINPUT_TYPE,
	DATE_FORMAT,
	DATE_PARSE_ERROR
} from '@/const/const.js';
import {
	STORE_ERROR_ADDMSG,
	STORE_ERROR_IS_ERRFLG
} from '@/const/store.js';


export default {
	components: {'v-date-picker': DatePicker },
	props: {
		name: {type: String, default: ''},							// 名称
		modelValue: {type: String, default: null},					// 入力値
		requireFlg: {type: Boolean, default: false},				// 必須(true)/任意(false)
		disabledFlg: {type: Boolean, default: false},				// 非活性(true)/活性(false)
		dateType: {type: Number, default: DATEINPUT_TYPE.YMD},		// 日付形式 
		allowOmitedValue: {type: Boolean, default: false},		// 省略記述(年の先頭2桁無し)を許可するかどうか
		tabindex: {type: String, default: '0'},					// タブインデックス
	},
	emits: ['blur','change', 'focus', 'update:modelValue'],
	data() {
		return {
			showValue: null,										// 表示値
			masks: {
				title: 'YYYY年 MM月',
			},														// カレンダーの表示設定
			date: null,												// カレンダー設定用日付
			isChangedValue: false,
			isError: false,											// エラー判定
			dateBlurFormat: DATE_FORMAT.YMD_SLASH,					// 表示フォーマット(ロストフォーカス時)
			dateFocusFormat: DATE_FORMAT.YMD,						// 表示フォーマット(フォーカス時)
			isDateRequired: true,									// DatePicker制御用(date が null になったときに選択状態を外すために追加)
		};
	},
	created: function() {
		moment.updateLocale('ja', null);
		
		this.setDateFormat();
		
		if ( !co_isEmpty(this.modelValue) ) {
			this.showValue = this.parseDateformat( String(this.modelValue) );
			this.date = new Date( this.parseDateformat( String(this.modelValue) ) );
		}
	},
	computed: {
		switchDisplayMode() {
			return {
				// ** 活性非活性による表示制御 **
				// 画像の透過度
				'--opacity': this.disabledFlg ? '0.7' : '1.0',		
			}
		},
		errFlg: function() {
			return this.$store.getters[STORE_ERROR_IS_ERRFLG];
		},
	},
	watch: {
		errFlg: function(newValue) {
			if(newValue === false) {
				this.clearError();
			}
		},
		modelValue: function(val) {
			if ( co_isEmpty(val) ) {
				this.showValue = '';
				this.date = null;
				return
			}
			// date型変換可否チェック
			let strDate = this.parseDateformat(String(val));
			if ( String(new Date(strDate)) === DATE_PARSE_ERROR ) {
				this.showValue = String(val);
				this.date = null;
				return;				
			}

			this.date = new Date(strDate);
			
		},
		date: function(val) {
			if (val == null) {
				this.isDateRequired = false;
				return
			}
			
			this.isDateRequired = true;
			this.showValue = moment( val ).format( this.dateBlurFormat );
			if ( this.showValue.replaceAll('/', '') !== this.modelValue ) {
				this.$emit('update:modelValue', this.showValue.replaceAll('/', ''));
			}
		},
	},
	mounted: function() {
	},
	beforeUnmount: function() {
	},
	methods: {
		handleShow(){
			// カレンダーが表示されたときに呼ぶ
			this.$refs.calendar.adjustPageRange()
		},
		// フォーカスイン・フォーカスアウト時の日付の表示形式を設定する
		setDateFormat: function() {
			switch ( this.dateType ) {
				case DATEINPUT_TYPE.YMD:
					this.dateBlurFormat = DATE_FORMAT.YMD_SLASH;
					
					if ( this.allowOmitedValue ) {
						this.dateFocusFormat = DATE_FORMAT.YMD_OMIT;
					} else {
						this.dateFocusFormat = DATE_FORMAT.YMD;
					}
					
					break;
				case DATEINPUT_TYPE.YM:
					this.dateBlurFormat = DATE_FORMAT.YM_SLASH;
					if ( this.allowOmitedValue ) {
						this.dateFocusFormat = DATE_FORMAT.YM_OMIT;
					} else {
						this.dateFocusFormat = DATE_FORMAT.YM;
					}
					break;
			}
			
		},
		keyControl: function(e) {
			//活性の時のみ処理
			if(!this.disabledFlg){
				//活性時は親へのイベントを抑止する
				//（親でイベントをキャンセルしている）
				e.stopPropagation();
			}
		},
		// フォーカスアウト時の表示制御
		blurInput: async function(val) {

			// 非活性時
			if ( this.disabledFlg ) {
				return
			}
			
			// date型変換可否チェック
			let strDate = this.parseDateformat(String(val));
			if ( String(new Date(strDate)) === DATE_PARSE_ERROR ) {
				this.showValue = String(val);
				this.$emit('update:modelValue', this.showValue);

				return;				
			}
			
			// 画面描画の完了を待機
			await this.$nextTick();
			
			// 表示制御
			this.date = new Date(strDate);
			
			
			// 画面描画の完了を待機
			await this.$nextTick();

			this.showValue = moment( this.date ).format( this.dateBlurFormat );
			this.$emit('update:modelValue', this.showValue.replaceAll('/', ''));
			
			if ( this.isChangedValue ) {
				this.$emit('change');
				this.isChangedValue = false;
			}
			
			this.$emit('blur');
		},		
		// チェンジ時イベント
		changeInput: function() {
			this.isChangedValue = true;
		},
		// フォーカスイン時の表示制御
		focusInput: async function(val) {
			this.$emit("focus");
			
			// 表示制御
			// date型変換可否チェック
			let strDate = this.parseDateformat(String(val));
			if ( String(new Date(strDate)) === DATE_PARSE_ERROR ) {
				this.showValue = String(val);			
			} else {
				this.showValue = moment( val ).format( this.dateFocusFormat );
			}
			
			// 画面描画の完了を待機
			await this.$nextTick();
			
			this.$refs.dateInput.select();
		},
		// 日付フォーマット処理(YYYY/MM/DD形式のString型を返す)
		parseDateformat: function(strVal) {			
			// strVal を YYYYMMDD形式に補完する
			let strDate = strVal.replaceAll("/", "") ;
			switch ( this.dateType ) {
				case DATEINPUT_TYPE.YMD:
					if (  this.allowOmitedValue  ) {
						// YYMMDD形式
						if ( strDate.match(/^\d{6}$/) ) {
							strDate = "20" + strDate;
						}
					}
					
					break;
				case DATEINPUT_TYPE.YM:
					// YYYYMM形式
					if ( strDate.match(/^\d{6}$/) ) {
						strDate = strDate + "01";
					}
					
					if (  this.allowOmitedValue  ) {
						// YYMM形式
						if ( strDate.match(/^\d{4}$/) ) {
							strDate = "20" + strDate + "01";
						}
					}
					
					break;
			}
			
			if (strDate.length != DATE_FORMAT.YMD.length) {
				// String → Date変換出来なければ戻り値は何でもいい
				return "9999/99/99";
			}
			
			// strDate を YYYY/MM/DD 形式に変換
			let year = strDate.substr(0, 4);
			let month = strDate.substr(4, 2);
			let date = strDate.substr(6, 2);
			strDate = [year, month, date].join("/");

			// YYYY/MM/DD形式
			if ( strDate.match(/\d{4}\/\d{2}\/\d{2}/)) {
				return strDate;
			}
			// YYYY/MM/DD形式以外
			else {
				// String → Date変換出来なければ戻り値は何でもいい
				return "9999/99/99";
			}
			
		},
		// テキストボックスの入力制御
		limitOnlyNumber: function(e) {
			// 全角文字確定後のみ、数値以外の値をトリムする
			if ( e.isComposing ) {
				return
			} else {
				e.target.value = e.target.value.replace(/[０-９]/g, 
					function(s) {
						return String.fromCharCode(s.charCodeAt(0) - 0xFEE0);
					});
				e.target.value = e.target.value.replace(/\D/g, '');
			}
		},
		// 妥当性チェック
		validate: function() {
			
			// エラークリア
			this.clearError();

			// 必須チェック
			if ( this.requireFlg === true ) {
				if ( co_isEmpty(this.showValue) ) {
					// storeにエラー登録
					this.$store.commit(STORE_ERROR_ADDMSG, co_getMessage('CMS0013E', this.name));
					
					this.setError();
					return
				}
			}
			
			// 共通チェック
			if ( !co_isEmpty(this.showValue) ) {
				// 日付型チェック
				let strDate = this.parseDateformat(this.showValue);
				if ( String(new Date(strDate)) === DATE_PARSE_ERROR ) {
					switch ( this.dateType ) {
					case DATEINPUT_TYPE.YMD:
						// YYYYMMDD形式
						// storeにエラー登録
						this.$store.commit(STORE_ERROR_ADDMSG, co_getMessage('CMS0008E', this.name));
						
						break;
					case DATEINPUT_TYPE.YM:
						// YYYYMM形式
						// storeにエラー登録
						this.$store.commit(STORE_ERROR_ADDMSG, co_getMessage('CMS0009E', this.name));
						
						break;
					}
					// エラーをセット
					this.setError();
				}
			}
			
		},
		// エラー設定
		setError: function() {
			this.isError = true;
		},
		// エラークリア
		clearError: function() {
			this.isError = false;
		},
		// フォーカス設定
		focus: function() {
			this.$refs.dateInput.focus();
		}
	}
}
</script>

<style scoped>
.date-input {
	display: flex;
	height: 43px
}

.date-input input {
	width: 130px;
}
.date-input  button {
	background-color: rgba(0,0,0,0);
	height: 100%;
	padding: 0px;
	margin-right: 3px;
}
.date-input  button img {
	opacity: var(--opacity);
	vertical-align: middle;
}
</style>