<!--
//================================================================================
// ID: -
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// VueName: PostalCodeInput
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
		class="input--postal-code ime-inactive"
		v-bind="$attrs"
		v-bind:class="{'contents--error-item': errorFlg, 'contents--require-item':!disabledFlg && requireFlg}"
		v-bind:maxlength="maxLength"
		v-bind:value="value"
		v-bind:disabled="disabledFlg"
		v-bind:tabindex="tabindex"
		v-on:input="$emit('input', $event.target.value)"
		ref="postalCodeInput"
	/>
</template>

<script>
import {co_isEmpty, co_checkLength} from '@/common/checkUtility.js';
import {co_stopPropagation} from '@/common/keyEventUtility.js';
import {co_getMessage} from '@/common/messageUtility.js';
import {co_trim} from '@/common/stringUtility.js';

import {MAX_LENGTH_POSTAL_CODE} from '@/const/const.js';
import {STORE_ERROR_ADDMSG, STORE_ERROR_IS_ERRFLG} from '@/const/store.js';

export default {
	inheritAttrs: false,
	props: {
		name: {type: String, default: ''},				// 項目名
		value: {type: String, default: ''},			// 入力値
		requireFlg: {type: Boolean, default: false},	// 必須フラグ
		disabledFlg: {type: Boolean, default: false},	// 活性・非活性フラグ
		tabindex: {type: String, default: '0'},		// タブインデックス
	},
	data: function() {
		return {
			errorFlg: false,
			maxLength: MAX_LENGTH_POSTAL_CODE
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
		// 妥当性チェック
		validate: function() {
			// TRIM処理
			let value = co_trim(this.value);
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
				if(!co_checkLength(value, MAX_LENGTH_POSTAL_CODE)) {
					this.$store.commit(
						STORE_ERROR_ADDMSG,
						co_getMessage(
							'CMS0016E',
							this.name,
							MAX_LENGTH_POSTAL_CODE
						)
					);
					error = true;
				}

				// 郵便番号チェック
				if(value.length !== MAX_LENGTH_POSTAL_CODE || (value.match(/-/g) || []).length !== 1) {
					this.$store.commit(
						STORE_ERROR_ADDMSG,
						co_getMessage('CMS0011E', this.name)
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
			this.$refs.postalCodeInput.focus();
		}
	}
}
</script>

<style scoped>
</style>