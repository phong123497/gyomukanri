<!--
//================================================================================
// ID: -
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// VueName: ListBoxRel
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

import { CD_CONTENT_TYPE, REC_KB } from '@/const/const.js';
import { API_LIST_ITEMS } from '@/const/route.js';
import {
	STORE_ERROR_ADDMSG,
	STORE_ERROR_IS_ERRFLG
} from '@/const/store.js';

export default {
	inheritAttrs: false,
	props: {
		modelValue: {type: String, default: ''},
		name: {type: String, default: ''},
		requireFlg: {type: Boolean, default: false},						// true : 必須
		listItems: {type: Array, default: function() {
				return []
			}
		},																	// リスト作成用のjson
		reckb: {type: String, default: ''},								// リスト作成用のコード種別
		bumonkb: {type: String, default: ''}, 								// 分類-大・中・小
		blankFlg: {type: Boolean, default: true},							// リストの先頭の空白有無 true:空白あり、false:空白なし
		disabledFlg: { type: Boolean, default: false },					// 活性・非活性フラグ
		cdContentType: {type: Number, default: CD_CONTENT_TYPE.CONTENT },	// 1:コード内容、2:コード略称、3: コード付名称
		tabindex: {type: String, default: '0'},							// タブインデックス
	},
	data: function() {
		return {
			errorFlg: false,
			options: [],
			disp: {
				meishoList: null,
				upperValue: '',
				zok05Map: null,
				labelMap: null,
			},
		}
	},
	created: async function() {
		if (!co_isEmpty(this.reckb)) {

			// DBからデータを取得しリストを設定
			this.disp.meishoList = await this.getData();

		} else if(!co_isEmpty(this.listItems))  {

			// 親から受け取ったjson文字列でリストを設定
			this.disp.meishoList = this.listItems;

		}
		// 関連の親が居る場合はリストを初期作成しない
		if (this.bumonkb !== REC_KB.TEMPOBUNRUIDAI && this.bumonkb !== REC_KB.TEMPOBUNRUICHU && this.bumonkb !== REC_KB.TEMPOBUNRUISHO) {
			this.createList( this.disp.meishoList );
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
				let zok05MapObj = new Map();
				let labelMapObj = new Map();
				for ( let i = 0; i < val.length; i++ ) {
					// リストを追加
					this.addList( val[i], list, zok05MapObj, labelMapObj);
				}
				this.disp.zok05Map = zok05MapObj;
				this.disp.labelMap = labelMapObj;
			}

			this.options = list;

			if (!this.blankFlg && co_isEmpty(this.modelValue) && !co_isEmpty(list)) {
				// リストの先頭の選択値を初期化
				this.$emit( 'update:modelValue', list[0].key );
			}
		},
		addList: function( val , list, zok05Map, labelMap) {

			// コード内容を表示
			if ( CD_CONTENT_TYPE.CONTENT === this.cdContentType ) {
				
				list.push( {key: val.code, val: val.name} );

				labelMap.set(val.code, val.name);

				// コード略称を表示
			} else if ( CD_CONTENT_TYPE.SHORTENED_NAME === this.cdContentType ) {

				list.push( {key: val.code, val: val.ryakuName} );

				labelMap.set(val.code, val.ryakuName);

			// コード付名称を表示
			} else if ( CD_CONTENT_TYPE.CODENAME === this.cdContentType ) {

				// 部門
				if (this.reckb === REC_KB.BUMON) {

					// 属性2が空で無い場合
					if (val.zok02 != "") {

						list.push( {key: val.code, val: val.code + ':' + val.name} );

						labelMap.set(val.code, val.name);

					}

				// 大分類
				} else if (this.bumonkb === REC_KB.TEMPOBUNRUIDAI) {

					if (val.zok02 === this.disp.upperValue) {

						list.push( {key: val.code, val: val.code + ':' + val.name} );

						labelMap.set(val.code, val.name);

					}

				// 中分類
				} else if (this.bumonkb === REC_KB.TEMPOBUNRUICHU) {

					if (val.code.length == 4 && val.code.substring(0, 2) === this.disp.upperValue) {

						list.push( {key: val.code.substring(2, 4), val: val.code.substring(2, 4) + ':' + val.name} );

						labelMap.set(val.code.substring(2, 4), val.name);

					}

				// 小分類	
				} else if (this.bumonkb === REC_KB.TEMPOBUNRUISHO) {

					if (val.code.length == 6 && val.code.substring(0, 4) === this.disp.upperValue) {

						list.push( {key: val.code.substring(4, 6), val: val.code.substring(4, 6) + ':' + val.name} );

						zok05Map.set(val.code.substring(4, 6), val.zok05);	

						labelMap.set(val.code.substring(4, 6), val.name);

					}

				} else {

					list.push( {key: val.code, val: val.code + ':' + val.name} );

					labelMap.set(val.code, val.name);

				}

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
		},
		// 上位からの取得
		getModel: function() {
			return this.modelValue;
		},
		// 上位からの更新
		resetModel: async function(val) {

			this.disp.upperValue = val;
			if (this.disp.upperValue === "") {
				this.options = [];
			} else {
				// DBからデータを取得しリストを設定
				this.createList( this.disp.meishoList );
			}
		},
		getZok05value: function(val) {
			return this.disp.zok05Map.get(val);
		},
		getLabelvalue: function(val) {
			return this.disp.labelMap.get(val);
		}

	}
}
</script>

<style scoped>
</style>