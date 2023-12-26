<!--
//================================================================================
// ID: -
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// VueName: NumberInput
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/10/28 |   AMC                | 新規作成
//================================================================================
 -->
<template>
	<div class="inputnumber" :style="switchBgColor">
		<input 
			type='tel'
			autocomplete="off"
			v-model="showValue"
			v-bind="$attrs"
			v-bind:class="[{'contents--error-item':isError}, {'contents--require-item':!disabledFlg && requireFlg}]"
			v-bind:name="name"
			v-bind:maxlength="maxLength"
			v-bind:disabled="disabledFlg"
			v-bind:tabindex="tabindex"
			v-on:change="changeNumberInput"
			v-on:focus="focusNumberInput"
			v-on:blur="blurNumberInput"
			v-on:input="inputCodeInput($event)"
			v-on:keyup="keyupCodeInput($event)"
			ref="NumberInput"
		/>
		<span v-if="unit" class="unit">{{ unit }}</span>
	</div>
</template>

<script>
import {NUMBERINPUT_TYPE} from '@/const/const.js';

import {co_checkLength, co_isEmpty} from '@/common/checkUtility.js';
import {co_stopPropagation} from '@/common/keyEventUtility.js';
import {co_getMessage} from '@/common/messageUtility.js';

import {STORE_ERROR_ADDMSG, STORE_ERROR_IS_ERRFLG} from '@/const/store.js';

export default {
	inheritAttrs: false,
	props: {
		name: {String, default: ''},								// 項目名
		modelValue: {type:Number, default: 0},						// 入力値
		requireFlg: {type: Boolean, default: false},				// 必須フラグ
		disabledFlg: {type: Boolean, default: false},				// 活性・非活性フラグ
		maxLength: {Number, default: 0},							// 最大桁数
		maxVal: {Type: Number, default: null},						// 入力可能な最大値
		minVal: {Type: Number, default: null},						// 入力可能な最小値
		decimalLength: {type: Number, default: 0},					// 小数の桁数
		numberType: {String, default: NUMBERINPUT_TYPE.INTEGER},	// 書式
		unit: {type: String, default: ''},							// 単位名
		zeroReplaceBlank: {type: Boolean, default: false},		// 0のみの入力時表示する(false)/表示しない(true)
		tabindex: {type: String, default: '0'},					// タブインデックス
	},
	emits: ['blur', 'change', 'focus', 'update:modelValue'],
	data: function() {
		return {
			showValue: null,
			isChangedValue: false,
			isError: false,
			min: this.minVal,
			max: this.maxVal,
		}
	},
	mounted: function() {
		/* フォーカス時の「BackSpace」「Delete」キーイベントを有効にする
		  （親でイベントをキャンセルしているので、親への伝播を阻止する） */
		this.$el.addEventListener('keydown', function(e) {
			co_stopPropagation(e, [8, 46]);
		});
	},
	created: function() {
		
		// 表示値設定
		this.setShowValue(this.modelValue);
		
		if(this.maxLength > 0) {
			let overValue = 10 ** (this.maxLength - this.decimalLength);

			// 最大桁数から最大値を設定（「maxVal」プロパティ未設定時のみの処理）
			if(this.maxVal === null) {
				// 小数値
				if(this.decimalLength > 0) {
					this.max = overValue - (0.1 ** this.decimalLength);
				}
				// 整数値
				else if(this.decimalLength === 0) {
					this.max = overValue - 1;
				}
			}

			// 最大桁数から最小値を設定（「minVal」プロパティ未設定時のみの処理）
			if(this.minVal === null) {
				overValue = 0 - overValue;

				// 小数値
				
				if(this.decimalLength > 0) {
					this.min = overValue + (0.1 ** this.decimalLength);
				}
				// 整数値
				else if(this.decimalLength === 0) {
					this.min = overValue + 1;
				}
			}
		}
	},
	computed: {
		switchBgColor() {
			return {
				// ** 活性非活性による表示制御 **
				// 背景色
				'--input-bgcolor': this.disabledFlg ? '#f0f0f0' : '#fff',		
			}
		},
		errFlg: function() {
			return this.$store.getters[STORE_ERROR_IS_ERRFLG];
		}
	},
	watch: {
		disabledFlg: function(val) {
			if(val) {
				this.clearError();
			}
		},
		errFlg: function(newValue) {
			if(newValue === false) {
				this.clearError();
			}
		},
		modelValue: function(val) {
			// 画面処理で設定された値をフォーマットするために実装
			this.setShowValue(val);
		}
	},
	methods: {
		// 表示値を設定する
		setShowValue: function( val ) {
			
			// 数値以外そのまま設定する
			if ( co_isEmpty(val)){
				this.showValue = '';
				return;
			}
			if ( Number.isNaN(Number(val)) ) {
				this.showValue = val;
				return;
			}
			if ( this.zeroReplaceBlank && val == '0' ) {
				this.showValue = '';
				return;
			}
			
			// 表示値を設定
			switch ( this.numberType ) {
				// 整数値のみ
				case NUMBERINPUT_TYPE.INTEGER: {
					let intVal = Number(val).toFixed(0)
					this.showValue = Number(intVal).toLocaleString('ja');
					break;
				}
				// 整数＋小数(カンマなし, ゼロ省略なし)
				case NUMBERINPUT_TYPE.NON_COMMA_FULL_INDICATE:{
					this.showValue = Number(val).toFixed(this.decimalLength);
					break;
				}
				// 整数＋小数(カンマあり, ゼロ省略なし)
				case NUMBERINPUT_TYPE.COMMA_FULL_INDICATE:{
					let intVal = Number(val).toFixed(this.decimalLength).split('.')[0]
					let decimalVal = Number(val).toFixed(this.decimalLength).split('.')[1];
					
					// 整数部カンマ編集
					intVal = Number(intVal).toLocaleString('ja');
					
					this.showValue = intVal + '.' + decimalVal;
					break;
				}
				// 整数＋小数(カンマなし, ゼロ省略あり)
				case NUMBERINPUT_TYPE.NON_COMMA_ZERO_DECIMAL_OMMIT:{
					this.showValue = Number(val).toLocaleString(
							"ja", {
								"useGrouping": false,
								maximumFractionDigits: this.decimalLength });
					break;
				}
				// 整数＋小数(カンマあり, ゼロ省略あり)
				case NUMBERINPUT_TYPE.COMMA_ZERO_DECIMAL_OMMIT:{
					this.showValue = Number(val).toLocaleString(
							"ja", { maximumFractionDigits: this.decimalLength });
					break;
				}
			} 
		},
		// テキストボックスの入力制御
		limitOnlyNumber: function(e) {
			// 全角文字確定後のみ、数値以外の値をトリムする
			if ( e.isComposing ) {
				return
			} else {
				// FIX: マイナス(-)も入力可能になるように対応する
				e.target.value = e.target.value.replace(/[０-９]/g, 
					function(s) {
						return String.fromCharCode(s.charCodeAt(0) - 0xFEE0);
					});
        this.showValue = e.target.value.replace(/[^-0-9.]/g, '');
			}
		},
		// 桁数チェック
		isDigitOverflow: function( strVal ) {
			let valArr = strVal.replace('-', '').split('.');
			let valArrCnt = valArr.length;
			
			// 整数値
			if(valArrCnt == 1) {
				if ( !co_checkLength(valArr[0], this.maxLength - this.decimalLength) ){
					return true
				}
			}
			// 小数値
			if(valArrCnt == 2) {
				// 整数部の桁チェック
				if ( !co_checkLength(valArr[0], this.maxLength - this.decimalLength) ){
					return true
				}

				// 小数部の桁チェック
				if( !co_checkLength(valArr[1], this.decimalLength) ) {
					return true
				}
			}
			
			return false;
		},
		// フォーカスイン時イベント
		focusNumberInput: async function( event ) {
			this.$emit("focus");
			this.showValue = event.target.value.replaceAll(',', '');
			
			// 画面描画の完了を待機
			await this.$nextTick();
			
			this.$refs.NumberInput.select();
		},
		// チェンジ時イベント
		changeNumberInput: function() {
			this.isChangedValue = true;
		},
		// フォーカスアウト時イベント
		blurNumberInput: async function( event ) {
			this.setShowValue(event.target.value);
			
			// 画面描画の完了を待機
			await this.$nextTick();
			
			// 項目値設定処理
			let value = null;
			if (co_isEmpty(this.showValue)) {
				if (this.zeroReplaceBlank) {
					value = 0;
				} else {
					value = null;
				}
			} else {
				value = Number(this.showValue.replaceAll(',', ''));
				if (Number.isNaN(value)) {
					value = this.showValue;
				}
			}
			this.$emit('update:modelValue', value);
			
			await this.$nextTick();
			
			if ( this.isChangedValue ) {
				this.$emit('change');
				this.isChangedValue = false;
			}
			
			this.$emit("blur");
		},
		// 入力時イベント 
		inputCodeInput: function( e ) {
			// 入力制御
			this.limitOnlyNumber( e );
		},
		// キーアップ時イベント
		keyupCodeInput: function( e ) {
			// 入力制御
			this.limitOnlyNumber( e );
		},
		// 妥当性チェック
		validate: function() {
			
			let val = this.modelValue == null ? null : String(this.modelValue);
			
			this.clearError();
			
			// 必須チェック
			if(this.requireFlg === true) {
				if(co_isEmpty(val)) {
					this.$store.commit(STORE_ERROR_ADDMSG,
						co_getMessage('CMS0013E', this.name));
					this.setError();
					return
				}
			}

			if(!co_isEmpty(val)) {
				
				// 桁数チェック
				if( this.isDigitOverflow(val) ) {
					this.$store.commit(
						STORE_ERROR_ADDMSG,
						co_getMessage('CMS0017E', this.name, this.maxLength));
					this.setError();
				}
				
				// 入力形式チェック
				let valArr = val.replace('-', '').split('.');
				let valArrCnt = valArr.length;
				if(!val.match(/^[-]?([1-9]\d*|0)(\.\d+)?$/) || valArrCnt > 2) {
					// 整数値
					if(this.decimalLength == 0) {
						this.$store.commit(
							STORE_ERROR_ADDMSG,
							co_getMessage('CMS0006E', this.name)
						);
					}
					// 小数値
					else if(this.decimalLength > 0) {
						this.$store.commit(
								STORE_ERROR_ADDMSG,
								co_getMessage('CMS0007E', this.name , this.decimalLength)
						);
					}
					this.setError();
				}

				// 最大入力桁チェック
				if(this.modelValue < this.min || this.modelValue > this.max) {
					this.$store.commit(
							STORE_ERROR_ADDMSG,
							co_getMessage('CMS0015E', this.name,
							String(this.min), String(this.max))
					);
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
			this.$refs.NumberInput.focus();
		}
	}
}
</script>

<style scoped>
.inputnumber input {
    border-radius: 5px;
    box-sizing: border-box;
    display: inline-block;
    font-size: 18px;
    padding: 6px 10px;
}
.inputnumber {
	display: flex;
}
.unit {
	margin : auto 5px;
}
</style>