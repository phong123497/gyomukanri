<!--
//================================================================================
// ID: -
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// VueName: CheckBox
//
// <<Modification History>>
// Version  | Date	     | Updated By		     | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2022/10/28 |  AMC                  | 新規作成
//================================================================================
 -->
<template>
	<div class="cond__checkbox" v-bind:style="styles">
		<div
			class="checkbox-group"
			v-for="(item, index) in items"
			v-bind:key="item.key"
		>
			<input
				class="checkbox"
				type="checkbox"
				v-bind="$attrs"
				v-bind:disabled="disabledFlg"
				v-bind:id="name + String(index)"
				v-bind:ref="'refs_' + index"
				v-bind:tabindex="tabindex"
				v-bind:value="item.key"
				v-model="checkedItems"
				v-on:keydown.prevent.right="moveNext(index)"
				v-on:keydown.prevent.left="movePrev(index)"
				v-on:keydown.enter="moveNext(index)"
			/>
			<label
				class="checkbox-label"
				v-bind:for="name + String(index)">{{item.val}}</label>
		</div>
	</div>
</template>

<script>
import { co_post } from '@/common/ajaxUtility.js';
import { co_getMessage } from '@/common/messageUtility.js';
import { co_isEmpty } from '@/common/checkUtility.js';

import { CD_CONTENT_TYPE } from '@/const/const.js';
import { API_CHECKBOX_ITEMS } from '@/const/route.js';
import { STORE_ERROR_ADDMSG, STORE_ERROR_IS_ERRFLG } from '@/const/store.js';

export default {
	inheritAttrs: false,
	props: {
		name: {type: String, default: ''},									// 項目名
		modelValue: {type: String, default: ''},								// 親コンポーネント値
		reckb: {type: String, default: ''},								// チェックボックス作成用のコード種別
		listItems: {type: Array, default: function() {
				return []
			}
		},													// リスト作成用のjson
		requireFlg: {type: Boolean, default: false},						// 必須チェックの有無
		disabledFlg: {type: Boolean, default: false},						// true:非活性、false:活性
		cdContentType: {type: Number, default: CD_CONTENT_TYPE.CONTENT },	// 1:コード内容、2:コード略称
		tabindex: {type: String, default: '0'},							// タブインデックス
	},
	data: function() {
		return {
			errorFlg: false,			// チェックエラーフラグ
			items: {},					// DB取得アイテム保存用
			checkedItems: [],			// チェック状況保存用
			focusIndex: null			// フォーカスインデックス
		}
	},
	created: async function() {
		if (!co_isEmpty(this.reckb)) {
			// DBからデータを取得
			this.createList(await this.getData());
		} else if(!co_isEmpty(this.listItems))  {
			// 親から受け取ったjson文字列でリストを設定
			this.createList( this.listItems );
		}
		
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
		this.checkedItems = null;
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
		//画面側値更新時（画面プログラムから直設定）
		modelValue: function(){

			if( co_isEmpty(this.modelValue) ){
				this.checkedItems = [];
			}
			else{
				let arrayVal = this.modelValue.split(',');
				this.checkedItems = arrayVal;
			}
		},
		reckb: async function() {
			// DBからデータを取得しリストを設定
			this.createList(await this.getData());
		},
		//コンポーネント側チェック時
		checkedItems: function(){
			let commaStr = this.checkedItems.join(',');
			this.$emit('update:modelValue', commaStr);
		},
		disabledFlg: function( val ) {
			//非活性時初期化
			if( val ) {
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
		moveNext: function( index ) {
			// →押下時のキーイベント
			index = index + 1;
			this.focus(index);
		},
		movePrev: function( index ) {
			// ←押下時のキーイベント
			index = index - 1;
			this.focus(index);

		},
		// コードマスタからリストの値を取得
		getData: function() {

			// ajaxにてサーバーと通信
			let params = { reckb: this.reckb };
			return co_post( API_CHECKBOX_ITEMS, params );
		},
		// リスト作成
		createList: function( val ) {

			let list = [];
			for ( let i = 0; i < val.length; i++ ) {

				let name = ''
				if (this.cdContentType === CD_CONTENT_TYPE.CONTENT) {
					// コ―ド内容
					name = val[i].name;
				}
				if (this.cdContentType === CD_CONTENT_TYPE.SHORTENED_NAME) {
					// コ―ド略称
					name = val[i].ryakuName;
				}

				list.push( {key: val[i].code, val: name} );
			}
			this.items = list;

			// 親から設定されたカンマ区切りのコードを初期値として設定
			if( co_isEmpty(this.modelValue) ){
				this.checkedItems = [];
			}
			else{
				let arrayVal = this.modelValue.split(',');
				this.checkedItems = arrayVal;
			}
		},
		// 妥当性チェック
		validate: function() {

			let error = false;
			// 必須チェック
			if( this.requireFlg == true ) {
				//値の要素数で判定
				if( co_isEmpty(this.modelValue) ) {
					this.$store.commit( STORE_ERROR_ADDMSG, co_getMessage('CMS0013E', this.name) );
					error = true;
				}
			}

			this.errorFlg = error;
			return !this.errorFlg;
		},
		//エラー表示設定
		setError: function() {
			this.errorFlg = true;
		},
		//エラー表示消去
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
.cond__checkbox label{
	padding: 5px 10px 5px 5px;
}
input[type="checkbox"] {
    display: inline-block;
}
.checkbox-group {
	display: inline-block;
}
.checkbox {
  outline-offset: 5px;
}

.checkbox-label {
  position: relative;
}

.checkbox-label::before {
  content: "";
  width: 15px;
  height: 15px;
  background-color:var(--back-ground-color);
  border: 2px solid var(--border-color);
  border-radius: 4px;
  position: absolute;
  top: 5px;
  left: -23px;
  margin-top:3px;
  margin-left:4px;
}
/* チェック済み */
.checkbox:checked + .checkbox-label::after {
  content: "";
  width: 7px;
  height: 14px;
  border-bottom: 3px solid #5e74db;
  border-right : 3px solid #5e74db;
  transform    : rotate(40deg); 
  position: absolute;
  top: 6px;
  left: -14px;
}
</style>