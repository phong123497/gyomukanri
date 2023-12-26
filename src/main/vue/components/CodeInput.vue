<!--
//================================================================================
// ID: -
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// VueName: CodeInput
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2022/10/28 |  AMC                 | 新規作成
//================================================================================
 -->
<template>
	<input
		type="text"
		v-bind="$attrs"
		v-bind:class="['ime-inactive', {'contents--error-item': isError}, {'contents--require-item':!disabledFlg && requireFlg}]"
		v-bind:disabled="disabledFlg"
		v-bind:maxlength="maxLength == null ? false : maxLength"
		v-bind:value="modelValue"
		v-bind:tabindex="tabindex"
		v-on:blur="blurCodeInput"
		v-on:change="changeCodeInput"
		v-on:focus="focusCodeInput"
		v-on:input="inputCodeInput($event)"
		v-on:keyup="keyupCodeInput($event)"
		ref="codeInput"
	>
</template>

<script>
import {
	co_checkLength,
	co_checkHalfNumber,
	co_isEmpty,
} from '@/common/checkUtility.js';
import {co_stopPropagation} from '@/common/keyEventUtility.js';
import {co_getMessage} from '@/common/messageUtility.js';
import {co_trim} from '@/common/stringUtility.js';

import {
	STORE_ERROR_ADDMSG,
	STORE_ERROR_IS_ERRFLG
} from '@/const/store.js';

export default {
	inheritAttrs: false,
	props: {
		name: {type: String, default: ''},					// 項目名
		modelValue: {type: String, default: ''},			// 入力値
		requireFlg: {type: Boolean, default: false},		// 必須フラグ
		disabledFlg: {type: Boolean, default: false},		// 活性・非活性フラグ
		maxLength: {type: Number, default: 0},				// 最大桁数
		activeZeroPadding: {type: Boolean, default: true},    // true: パディングあり, false: パディングなし
		tabindex: {type: String, default: '0'},			// タブインデックス
	},
	emits: ['blur', 'change', 'focus', 'update:modelValue'],
	data: function() {
		return {
			isChangedValue: false,						// 変更制御フラグ
			isError: false,
		}
	},
	mounted: function() {
		/* フォーカス時の「BackSpace」「Delete」キーイベントを有効にする
		  （親でイベントをキャンセルしているので、親への伝播を阻止する） */
		this.$el.addEventListener('keydown', function(e) {
			co_stopPropagation(e, [8, 46]);
		});
	},
	computed: {
		errFlg: function() {
			return this.$store.getters[STORE_ERROR_IS_ERRFLG];
		}
	},
	watch: {
		disabledFlg: function(newValue) {
			if(newValue === true) {
				this.clearError();
			}
		},
		errFlg: function(newValue) {
			if(newValue === false) {
				this.clearError();
			}
		}
	},
	methods: {
		/** 0埋め */
		doZeroPadding: function( val ) {
			if(val && val.length < this.maxLength) {
				return (Array(this.maxLength).join('0') + val).slice(-this.maxLength);	
			} else {
				return val
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
		// フォーカスアウト時イベント
		blurCodeInput: function( event ) {
			let value = co_trim(event.target.value);
			
			// パディング処理
			if ( this.activeZeroPadding ) {
				value = this.doZeroPadding( value );
			}
			
			this.$emit('update:modelValue', value);
			
			if ( this.isChangedValue ) {
				this.$emit('change');
				this.isChangedValue = false;
			}
			
			this.$emit("blur");
		},
		// チェンジ時イベント
		changeCodeInput: function() {
			this.isChangedValue = true;
		},
		focusCodeInput: async function() {
			this.$emit("focus");
			
			// 画面の描画を待機
			await this.$nextTick();
			
			// テキストボックスを全選択
			this.$refs.codeInput.select();
			
		},
		// 入力時イベント 
		inputCodeInput: function( e ) {
			
			this.limitOnlyNumber( e );
			
			this.$emit('update:modelValue', e.target.value);
		},
		// キーアップ時イベント
		keyupCodeInput: function( e ) {
			this.limitOnlyNumber( e );
		},
		/** エラーチェック */
		validate: function() {
			
			// TRIM処理
			let value = co_trim(this.modelValue.toString());
			this.$emit('update:modelValue', value)

			// エラークリア
			this.clearError();
			
			if(this.requireFlg === true) {
				// 必須チェック
				if(co_isEmpty(value)) {
					this.$store.commit(STORE_ERROR_ADDMSG,
						co_getMessage('CMS0013E', this.name));
					this.setError();
					return
				}
			}
			
			if (! co_isEmpty(value) ){
				// コード（数字）入力チェック
				if(! co_checkLength(value, this.maxLength) ) {
					this.$store.commit(STORE_ERROR_ADDMSG,
						co_getMessage('CMS0017E', this.name, String(this.maxLength)));
					this.setError();
				}

				// 入力形式チェック（属性名が「数字のみ」）
				if (!co_checkHalfNumber(value)) {
					this.$store.commit(STORE_ERROR_ADDMSG,
						co_getMessage('CMS0010E', this.name));
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
			this.$refs.codeInput.focus();
		}
	}
}
</script>

<style scoped>
</style>