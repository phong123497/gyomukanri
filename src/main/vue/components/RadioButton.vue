<!--
//================================================================================
// ID: -
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// VueName: RadioButton
//
// <<Modification History>>
// Version  | Date	   | Updated By		   | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2022/10/28 |   AMC         	   | 新規作成
//================================================================================
 -->
<template>
	<div class="cond__radio" v-bind:style="styles">
		<div
			class="radio-group"
			v-for="(item, index) in items"
			v-bind:key="item.key"
		>
			<input
				class="radio"
				type="radio"
				v-bind="$attrs"
				v-bind:checked="modelValue === item.key"
				v-bind:disabled="disabledFlg"
				v-bind:id="name + String(index)"
				v-bind:name="radioGroupName"
				v-bind:ref="'refs_' + index"
				v-bind:tabindex="tabindexList[index]"
				v-bind:value="item.key"
				v-on:change="changeRadio($event)"
			/>
			<label
				class="radio-label"
				v-bind:for="name + String(index)">{{item.val}}</label>
		</div>
	</div>
</template>

<script>
import { co_post } from '@/common/ajaxUtility.js';
import { co_isEmpty } from '@/common/checkUtility.js';
import { co_getMessage } from '@/common/messageUtility.js';

import { CD_CONTENT_TYPE } from '@/const/const.js';
import { API_RADIO_ITEMS } from '@/const/route.js';
import { STORE_ERROR_ADDMSG, STORE_ERROR_IS_ERRFLG } from '@/const/store.js';

export default {
	inheritAttrs: false,
	props: {
		modelValue: {type: String, default: ''},
		name: {type: String, default: ''},
		reckb: {type: String, default: ''},								// ラジオボタン作成用のコード種別
		listItems: {type: Array, default: function() {
				return []
			}
		},																	// リスト作成用のjson
		requireFlg: {type: Boolean, default: false},						// チェックの有無
		disabledFlg: {type: Boolean, default: false},						// 活性/非活性の有無
		cdContentType: {type: Number, default: CD_CONTENT_TYPE.CONTENT },	// 1:コード内容、2:コード略称
		radioGroupName: {type: String, default: ''},						// ラジオボタンに設定するname
		tabindex: {type: String, default: '0'},							// タブインデックス
	},
	data: function() {
		return {
			errorFlg: false,
			items: {},
			focusIndex: null,
			tabindexList: [],
		}
	},
	created: async function() {
		if ( this.reckb != '' ) {
			// DBからデータを取得しリストを設定
			this.createList(await this.getData());

		} else {
			// 親から受け取ったjson文字列でリストを設定
			this.createList(this.listItems);
		}
		
		this.setTabindexList();

		// フォーカスの遅延設定
		let that = this;
		this.$nextTick(function() {
			if (that.focusIndex !== null) {
				that.focus(that.focusIndex);
			}
		});
	},
	beforeUnmount: function() {
		this.items = null;
	},
	computed: {
		styles: function() {
			let backgroundColor = '#FFFFFF';
			let borderColor = '#CCCCCC';
			if (this.errorFlg) { 
				backgroundColor = '#FCEFF1';
				borderColor = '#DC8585';
			}
			if (this.requireFlg) { 
				backgroundColor = '#FCFFE1';
			}
			if (this.disabledFlg) {
				backgroundColor =  '#F0F0F0'
			}

			return {
				'--back-ground-color': backgroundColor,
				'--border-color': borderColor,
			}
		},	
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
		},
		reckb: async function() {
			// DBからデータを取得しリストを設定
			this.createList(await this.getData());
		},
		listItems: function() {
			// 親から受け取ったjson文字列でリストを設定
			this.createList(this.listItems);
		}
	},
	methods: {
		changeRadio: async function(event) {
			
			this.$emit('update:modelValue', event.target.value);
			await this.$nextTick();
			this.setTabindexList();
		},
		setTabindexList: function() {
			this.tabindexList = [];
			for (var i = 0; i < this.items.length; i++) {
				// 全項目が未選択時は同じtabindex
				if (co_isEmpty(this.modelValue)) {
					this.tabindexList.push(this.tabindex);
					continue;
				}
				
				// 選択時は選択項目のみtabindexを設定する
				if (this.items[i].key == this.modelValue) {
					this.tabindexList.push(this.tabindex);
				} else {
					this.tabindexList.push("-1");
				}
			}
		},
		// コードマスタからリストの値を取得
		getData: function() {

			// ajaxにてサーバーと通信
			let params = { reckb: this.reckb };
			return co_post( API_RADIO_ITEMS, params );
		},
		createList: function( val ) {

			let list = [];

			for ( let i = 0; i < val.length; i++ ) {

				// コード内容を表示
				if ( CD_CONTENT_TYPE.CONTENT === this.cdContentType ) {

					list.push( {key: val[i].code, val: val[i].name} );

				// コード略称を表示
				} else if ( CD_CONTENT_TYPE.SHORTENED_NAME === this.cdContentType ) {

					list.push( {key: val[i].code, val: val[i].ryakuName} );
				}
			}

			this.items = list;
		},
		// 妥当性チェック
		validate: function() {

			let error = false;
			// 必須チェック
			if( this.requireFlg === true ) {
				if( co_isEmpty(this.modelValue) ) {
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
		focus: function( index ) {
			if ( index === undefined ) {
				index = 0;
			}
			if (this.$refs['refs_' + index] !== undefined) {
				this.$refs['refs_' + index][0].focus();
				this.focusIndex = null;
			} else {
				this.focusIndex = index;
			}
		}
	}
}
</script>

<style scoped>
.cond__radio label {
	padding: 5px 10px 5px 5px;
}
input[type="radio"] {
	display: inline-block;
}
.radio-group {
	display: inline-block;
	padding: 5px 0px
}
.radio {
	outline-offset: 5px;
}
.radio-label {
	position: relative;
}
.radio-label::before {
	background-color:var(--back-ground-color);
	border: 2px solid var(--border-color);
	border-radius: 50%;
	content: "";
	display: block;
	height: 16px;
	position: absolute;
	width: 16px;
	top: 8px;
	left: -20px;
}
/* 選択時 */
.radio:checked + .radio-label::after {
	background: #5e74db;
	border-radius: 50%;
	content: "";
	display: block;
	height: 12px;
	opacity: 1;
	position: absolute;
	width: 12px;
	top: 12px;
	left: -16px;
}
</style>