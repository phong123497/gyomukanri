<!--
//================================================================================
// ID: -
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// VueName: PhoneNumberInput
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
		class="input--tel-num ime-inactive"
		v-bind="$attrs"
		v-bind:class="{'contents--error-item': errorFlg, 'contents--require-item':!disabledFlg && requireFlg}"
		v-bind:maxlength="maxLength"
		v-bind:value="modelValue"
		v-bind:disabled="disabledFlg"
		v-bind:tabindex="tabindex"
		v-on:input="$emit('input', $event.target.value)"
		v-on:blur="blurInput($event.target.value)"
		ref="phoneNumberInput"
	/>
</template>

<script>
import {co_isEmpty, co_checkLength} from '@/common/checkUtility.js';
import {co_stopPropagation} from '@/common/keyEventUtility.js';
import {co_getMessage} from '@/common/messageUtility.js';
import {co_trim} from '@/common/stringUtility.js';

import {MAX_LENGTH_PHONE_NUMBER} from '@/const/const.js';
import {STORE_ERROR_ADDMSG, STORE_ERROR_IS_ERRFLG} from '@/const/store.js';

export default {
	inheritAttrs: false,
	props: {
		name: {type: String, default: ''},				// 項目名
		modelValue: {type: String, default: ''}, // 入力値
		requireFlg: {type: Boolean, default: false},	// 必須フラグ
		disabledFlg: {type: Boolean, default: false},	// 活性・非活性フラグ
		tabindex: {type: String, default: '0'},		// タブインデックス
	},
	emits: ['blur', 'change', 'focus', 'update:modelValue'],
	data: function() {
		return {
			errorFlg: false,
			maxLength: MAX_LENGTH_PHONE_NUMBER
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
		disabledFlg: function(val) {
			if(val) {
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
		blurInput: function(value) {
			this.$emit('update:modelValue', value);
			this.$emit('blur');
		},
		// 妥当性チェック
		validate: function() {
			// TRIM処理
			let value = co_trim(this.modelValue);
			this.$emit('input', value)

			let error = false;
			if(co_isEmpty(value)) {
				// 必須チェック
				if(this.requireFlg === true) {
					this.$store.commit(
						STORE_ERROR_ADDMSG,
						co_getMessage('CMS0013E', this.name)
					);

					error = true;
				}
			} else {
				// 桁数チェック
				if(!(co_checkLength(value, MAX_LENGTH_PHONE_NUMBER))) {
					this.$store.commit(
						STORE_ERROR_ADDMSG,
						co_getMessage(
							'CMS0017E',
							this.name,
							MAX_LENGTH_PHONE_NUMBER
						)
					);

					error = true;
				}

				// 電話番号（FAX）チェック
				if (!value.match(/^[0-9-]+$/)) {
					this.$store.commit(
						STORE_ERROR_ADDMSG,
						co_getMessage('CMS0012E', this.name)
					);

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
			this.$refs.phoneNumberInput.focus();
		}
	}
}
</script>

<style scoped>
.input--tel-num {
	display: inline-block;
	margin-right: 10px;
}
</style>