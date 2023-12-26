<!--
//================================================================================
// ID: -
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// VueName: TextAreaInput
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2022/10/28 |   AMC                | 新規作成
//================================================================================
 -->
<template>
	<textarea
		v-bind="$attrs"
		v-bind:value="modelValue"
		v-on:input="$emit('update:modelValue', $event.target.value)"
		v-on:keydown.delete="keyControl"
		v-bind:class="{'contents--error-item': errorFlg, 'contents--require-item':!disabledFlg && requireFlg}"
		v-bind:disabled="disabledFlg"
		v-bind:readonly="disabledFlg"
		v-bind:maxlength="maxLength"
		v-bind:tabindex="disabledFlg ? '-1' : tabindex"
		ref="textAreaInput"
	/>
</template>

<script>
import { co_canConvertSJIS, co_checkLength, co_isEmpty } from '@/common/checkUtility.js';
import { co_getMessage } from '@/common/messageUtility.js';
import { co_trim } from '@/common/stringUtility.js';

import { STORE_ERROR_ADDMSG, STORE_ERROR_IS_ERRFLG } from '@/const/store.js';

export default {
	inheritAttrs: false,
	props: {
		name: { type: String, default: '' },
		modelValue: { type: String, default: '' },
		requireFlg: { type: Boolean, default: false },
		disabledFlg: { type: Boolean, default: false },
		maxLength: { type: Number, default: 0 },
		tabindex: {type: String, default: '0'},			// タブインデックス
	},
	emits: ['update:modelValue'],
	data: function() {
		return {
			errorFlg: false
		}
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
				
				// SJIS変換可否チェック
				if ( !co_canConvertSJIS(value) ) {
					this.$store.commit(STORE_ERROR_ADDMSG, this.name + 'にSJIS変換不可能な文字が含まれています。')
					error = true;
				}
			}

			this.errorFlg = error;
			return !this.errorFlg;
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
			this.$refs.textAreaInput.focus();
		}
	}
}
</script>

<style scoped>
</style>