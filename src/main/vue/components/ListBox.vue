<!--
//================================================================================
// ID: -
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// VueName: ListBox
//
// <<Modification History>>
// Version  | Date	     | Updated By		   | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2022/10/28 |   AMC         	   | 新規作成
//================================================================================
 -->
<template>
	<select
		v-bind="$attrs"
		v-bind:value="modelValue"
		v-bind:class="{'contents--error-item': errorFlg, 'contents--require-item':!disabledFlg && requireFlg}"
		v-bind:disabled="disabledFlg"
		v-bind:tabindex="tabindex"
		v-on:change="$emit('update:modelValue', $event.target.value)"
		v-on:blur="$emit('blur', $event)"
		v-on:keydown="$emit('keydown', $event)"
		ref="selectListBox"
	>
		<option
			v-for="option in options"
			v-bind:value="option.key"
			v-bind:key="option.key"
			v-bind:selected="modelValue === option.key">
			{{ option.val }}
		</option>
	</select>
</template>

<script>
import { co_post } from '@/common/ajaxUtility.js';
import { co_isEmpty } from '@/common/checkUtility.js';
import { co_getMessage } from '@/common/messageUtility.js';

import { CD_CONTENT_TYPE } from '@/const/const.js';
import { API_LIST_ITEMS } from '@/const/route.js';
import {
	STORE_ERROR_ADDMSG,
	STORE_ERROR_IS_ERRFLG
} from '@/const/store.js';

export default {
	inheritAttrs: false,
	props: {
		name: {type: String, default: ''},
		modelValue: {type: String, default: ''},
		reckb: {type: String, default: ''},				// リスト作成用のコード種別
		listItems: {type: Array, default: function() {
				return []
			}
		},													// リスト作成用のjson
		blankFlg: {type: Boolean, default: true},			// リストの先頭の空白有無 true:空白あり、false:空白なし
		requireFlg: {type: Boolean, default: false},		// true : 必須
		disabledFlg: { type: Boolean, default: false },	// 活性・非活性フラグ
		cdContentType: {type: Number, default: CD_CONTENT_TYPE.CONTENT },	// 1:コード内容、2:コード略称、3: コード付名称
		tabindex: {type: String, default: '0'},			// タブインデックス
	},
	data: function() {
		return {
			errorFlg: false,
			options: []
		}
	},
	created: async function() {
		if (!co_isEmpty(this.reckb)) {
			// DBからデータを取得しリストを設定
			this.createList( await this.getData() );

		} else if(!co_isEmpty(this.listItems))  {
			// 親から受け取ったjson文字列でリストを設定
			this.createList( this.listItems );
		}
	},
	beforeUnmount: function() {
		this.options = null;
	},
	computed: {
		errFlg: function() {
			return this.$store.getters[STORE_ERROR_IS_ERRFLG];
		}
	},
	watch: {
		reckb: async function() {
			// DBからデータを取得しリストを設定
			this.createList( await this.getData() );
		},
		disabledFlg: function( val ) {
			if( val ) {
				// エラークリア
				this.clearError();
			}
		},
		errFlg: function(newValue) {
			if(newValue === false) {
				this.clearError();
			}
		},
		listItems: function() {
			// 親から受け取ったjson文字列でリストを設定
			this.createList(this.listItems);
		}
	},
	methods: {
		// コードマスタからリストの値を取得
		getData: function() {

			// ajaxにてサーバーと通信
			let params = { reckb: this.reckb };
			return co_post( API_LIST_ITEMS, params );
		},
		// リスト作成
		createList: function( val ) {

			let list = [];

			if ( this.blankFlg ) {
				// 先頭に空白を追加
				list.push( {key: '', val: ''} );
			}

			if ( val != null ) {
				for ( let i = 0; i < val.length; i++ ) {
					// リストを追加
					this.addList( val[i], list);
				}
			}

			this.options = list;

			if (!this.blankFlg && co_isEmpty(this.modelValue) && !co_isEmpty(list)) {
				// リストの先頭の選択値を初期化
				this.$emit( 'update:modelValue', list[0].key );
			}
		},
		addList: function( val , list) {

			// コード内容を表示
			if ( CD_CONTENT_TYPE.CONTENT === this.cdContentType ) {

				list.push( {key: val.code, val: val.name} );

			// コード略称を表示
			} else if ( CD_CONTENT_TYPE.SHORTENED_NAME === this.cdContentType ) {

				list.push( {key: val.code, val: val.ryakuName} );
			// コード付名称を表示
			} else if ( CD_CONTENT_TYPE.CODENAME === this.cdContentType ) {

				list.push( {key: val.code, val: val.code + ':' + val.name} );
			}
		},
		// 妥当性チェック
		validate: function() {

			let error = false;
			// 必須チェック
			if ( this.requireFlg === true ) {
				if ( co_isEmpty(this.modelValue) ) {
					this.$store.commit( STORE_ERROR_ADDMSG, co_getMessage('CMS0013E', this.name) );
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
			this.$refs.selectListBox.focus();
		}
	}
}
</script>

<style scoped>
</style>