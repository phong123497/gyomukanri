<!--
//================================================================================
// ID: -
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// VueName: TextInput
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2022/10/28 |   AMC                | 新規作成
//================================================================================
 -->
<template>
	<input
		type="text"
		v-bind="$attrs"
		v-bind:value="modelValue"
		v-on:input="$emit('update:modelValue', $event.target.value)"
		v-on:keydown.delete="keyControl"
		v-on:blur="$emit('blur', $event)"
		v-on:keydown="$emit('keydown', $event)"
		v-bind:placeholder="placeholder == null ? '' : placeholder"
		v-bind:class="[{'contents--error-item': errorFlg}, imeMode ? 'ime-active' : 'ime-inactive', {'contents--require-item':!disabledFlg && requireFlg}]"
		v-bind:disabled="disabledFlg"
		v-bind:maxlength="maxLength"
		v-bind:tabindex="disabledFlg ? '-1' : tabindex"
		ref="textInput"
	/>
</template>

<script>
import {
	co_canConvertSJIS,
	co_checkLength,
	co_isEmpty
} from '@/common/checkUtility.js';
import { co_getMessage } from '@/common/messageUtility.js';
import { co_trim } from '@/common/stringUtility.js';

import { TEXTINPUT_TYPE } from '@/const/const.js';
import {
	STORE_ERROR_ADDMSG,
	STORE_ERROR_IS_ERRFLG
} from '@/const/store.js';

export default {
	inheritAttrs: false,
	props: {
		name: { type: String, default: '' },
		modelValue: { type: String, default: '' },
		requireFlg: { type: Boolean, default: false },
		disabledFlg: { type: Boolean, default: false },	// 活性・非活性フラグ
		maxLength: { type: Number, default: 0 },
		textType: { type: Number, default: TEXTINPUT_TYPE.ALL },
		placeholder: { type: String, default: null },
		tabindex: {type: String, default: '0'},			// タブインデックス
	},
	data: function() {

		return {
			errorFlg: false,
			imeMode: (this.textType == TEXTINPUT_TYPE.ALL || this.textType == TEXTINPUT_TYPE.FULL || this.textType == TEXTINPUT_TYPE.HIRAGANA) ? true : false,
		}
	},
	computed: {
		errFlg: function() {
			return this.$store.getters[STORE_ERROR_IS_ERRFLG];
		}
	},
	watch: {
		disabledFlg: function( val ) {
			if( val ) {
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
		keyControl: function(e) {
			//活性の時のみ処理
			if(!this.disabledFlg){
				//活性時は親へのイベントを抑止する
				//（親でイベントをキャンセルしている）
				e.stopPropagation();
			}
		},
		// 妥当性チェック
		validate: function() {
			// TRIM処理
			let value = co_trim(this.modelValue);
			this.$emit('update:modelValue', value)

			let error = false;

			// 必須チェック
			if ( this.requireFlg === true ) {
				if ( co_isEmpty(value) ) {
					this.$store.commit(STORE_ERROR_ADDMSG, co_getMessage('CMS0013E', this.name));
					error = true;
				}
			}


			if ( !co_isEmpty(value) ) {
				// 最大入力文字チェック
				if ( !co_checkLength(value, this.maxLength) ) {
					this.$store.commit(STORE_ERROR_ADDMSG, co_getMessage('CMS0016E', this.name, String(this.maxLength)));
					error = true;
				}

				// 属性チェック
				switch (this.textType) {
				// 全て
				case TEXTINPUT_TYPE.ALL:
					break;

				// 全角のみ
				case TEXTINPUT_TYPE.FULL:
					// 「半角英数記号」が設定されていた場合エラー
					if ( !value.match(/^[^\\x01-\x7e\uFF61-\uFF9F !"#$%&'()*+,\-./]+$/) ) {
						this.$store.commit(STORE_ERROR_ADDMSG, co_getMessage('CMS0004E', this.name));
						error = true;
					}
					break;

				// 全角ひらがなのみ
				case TEXTINPUT_TYPE.HIRAGANA:
					// 「全角ひらがな」以外が設定されていた場合エラー
					if ( !value.match(/^[\u3041-\u309f\u3000・ー]+$/) ) {
						this.$store.commit(STORE_ERROR_ADDMSG, co_getMessage('CMS0005E', this.name));
						error = true;
					}
					break;

				// 半角のみ
				case TEXTINPUT_TYPE.HALF:
					// 「半角英数記号」以外が設定されていた場合エラー
					if ( !this.isMatchHalf(value) ) {
						this.$store.commit(STORE_ERROR_ADDMSG, co_getMessage('CMS0001E', this.name));
						error = true;
					}
					break;

				// 半角英数字
				case TEXTINPUT_TYPE.HALF_ALPHA_NUM:
					// 「半角英数」以外が設定されていた場合エラー
					if ( !value.match(/^[0-9a-zA-Z]+$/) ) {
						this.$store.commit(STORE_ERROR_ADDMSG, co_getMessage('CMS0003E', this.name));
						error = true;
					}
					break;

				// 半角数字
				case TEXTINPUT_TYPE.HALF_NUM:
					// 「半角数字」以外が設定されていた場合エラー
					if ( !value.match(/^[0-9]+$/) ) {
						this.$store.commit(STORE_ERROR_ADDMSG, co_getMessage('CMS0010E', this.name));
						error = true;
					}
					break;

				// 半角カタカナ
				case TEXTINPUT_TYPE.HALF_KATAKANA:
					// 「半角カタカナ」以外が設定されていた場合エラー
					if ( !value.match(/^[ｦ-ﾟ]*$/) ) {
						this.$store.commit(STORE_ERROR_ADDMSG, co_getMessage('CMS0002E', this.name));
						error = true;
					}
					break;
				}
				
				// SJIS変換可否チェック
				if ( !co_canConvertSJIS(value) ) {
					this.$store.commit(STORE_ERROR_ADDMSG, this.name + 'にSJIS変換不可能な文字が含まれています。')
					error = true;
				}
			}

			this.errorFlg = error;
			return !this.errorFlg;
		},
		// 半角のみか
		isMatchHalf : function(str) {
			return str.match(/^[\x20-\x7e\uFF61-\uFF9F]+$/);
		},
		// エラー設定
		setError: function() {
			this.errorFlg = true;
		},
		// エラークリア
		clearError: function() {
			this.errorFlg = false;
		},
		// フォーカス設定
		focus: function() {
			this.$refs.textInput.focus();
		}
	}
}
</script>

<style scoped>
</style>