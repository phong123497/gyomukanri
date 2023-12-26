<!--
//================================================================================
// ID: -
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// VueName: CodeSelector
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2022/12/06 |  AMC                 | 新規作成
//================================================================================
 -->
<template>
	<div>
		<div class="code-wrapper">
			<input
				type="text"
				autocomplete="off"
				class="code"
				:style="codeShowWidth"
				v-model="showValue"
				v-bind:class="[{'contents--error-item': isError}, {'contents--require-item':!disabledFlg && requireFlg}]"
				v-bind:name="name"
				v-bind:disabled="disabledFlg"
				v-bind:maxLength="codeMaxLength"				
				v-bind:tabindex="tabindex"
				v-on:keydown.delete="keydownDeleteControl"
				v-on:blur="blurCodeInput()"
				v-on:change="changeCodeInput($event.target.value)"
				v-on:focus="focusCodeInput()"
				v-on:input="inputCodeInput($event)"
				v-on:keyup="keyupCodeInput($event)"
				ref="codeText"
			/>
		</div>
		<list-box
			class="listbox"
			:style="listboxShowWidth"
			v-model="selectedValue"
			v-bind:class="[{'contents--error-item': isError}, {'contents--require-item':!disabledFlg && requireFlg}]"
			v-bind:name="name"
			v-bind:listItems="form.listItems"
			v-bind:reckb="reckb"
			v-bind:bumonkb="bumonkb"
			v-bind:disabledFlg="disabledFlg"
			v-bind:blankFlg="true"
			v-bind:cdContentType="cdContentType"
			v-bind:tabindex="'-1'"
			v-on:focus="focusListBox()"
			v-on:blur="blurListBox()"
			v-on:change="changeListBox($event.target.value)"
			ref="itemList"
		/>
	</div>
</template>

<script>
import ListBox from '@/components/ListBoxRel.vue';

import {
	co_checkDigitEqualed,
	co_checkHalfNumber,
	co_isEmpty,
} from '@/common/checkUtility.js';
import { co_getMessage } from '@/common/messageUtility.js';
import { co_trim } from '@/common/stringUtility.js';

import { CD_CONTENT_TYPE } from '@/const/const.js';
import {
	STORE_ERROR_ADDMSG,
	STORE_ERROR_IS_ERRFLG,
} from '@/const/store.js';

export default {
	components: {
		'list-box': ListBox
	},
	props: {
		modelValue: {type:String, default: ''},							// コ―ド入力値
		name: {type: String, default: ''},									// 項目名
		listItems: {type: Array, default: function() {
			return []
		}},																	// リストボックスに設定するObject
		reckb: {type: String, default: ''}, 								// 名称マスタ.登録区分
		bumonkb: {type: String, default: ''}, 								// 分類-大・中・小
		requireFlg: {type: Boolean, default: false},						// 必須チェックの有無
		disabledFlg: {type: Boolean, default: false},						// true:非活性、false:活性
		activeZeroPadding: {type: Boolean, default: true},					// true: 左0パディングあり、 false: 左0パディングなし
		codeMaxLength: {type: Number,default: 1},							// 最大桁数(コ―ド)
		codeShowLength: {type: Number, default: 1},						// 表示桁数(コ―ド)
		listnocShowLength: {type: Number, default: 1},						// 表示桁数(リストボックス)
		cdContentType: {type: Number, default:CD_CONTENT_TYPE.CONTENT},	// 1:コード内容、2:コード略称
		tabindex: {type: String, default: '0'},							// タブインデックス
	},
	emits: ['blur', 'change', 'focus', 'update:modelValue'],
	data: function() {
		return {
			showValue: null,
			selectedValue : null,
			form: {
				listItems: this.listItems,  // リストオブジェクト
			},													
			isError: false,								// エラー判定
		}
	},
	created: function() {
	},
	computed: {
		// コ―ドの表示幅
		codeShowWidth() {
			return {
				// 計算式：フォントサイズ(px) * 表示桁数 + (padding幅 + border幅) * 2(左右)
				'--code-show-width': String(13 * this.codeShowLength + (10 + 2) * 2) + 'px'
			}
		},
		// リストボックスの表示幅
		listboxShowWidth() {
			return {
				// 計算式：フォントサイズ(px) * 表示桁数 + (padding幅 + border幅) * 2(左右)
				'--listbox-show-width': String(13 * this.listnocShowLength + (10 + 2) * 2) + 'px'
			}
		},
		// エラー状態管理
		errFlg: function() {
			return this.$store.getters[STORE_ERROR_IS_ERRFLG];
		}
	},
	watch: {
		modelValue: function(val) {
			this.showValue = this.doZeroPadding( val );
			this.selectedValue = this.doZeroPadding( val );
		},
		errFlg: function(newValue) {
			if(newValue === false) {
				this.clearError();
			}
		}
	},
	methods: {
		// ▼ --------------------------------------- 各入力イベント --------------------------------------- ▼
		// blur - CodeInput
		blurCodeInput: function() {
			this.$emit('blur');
		},
		// blur - ListBox
		blurListBox: function(event){
			event.target.tabIndex = -1;
						
			this.$emit('blur');
		},
		// change - CodeInput
		changeCodeInput: function(val) {
			this.clearError();
			// 入力値のゼロパディング時の同値対策として意図的に更新
			this.$emit('update:modelValue', val);

			this.$nextTick(function() {
				this.$emit('update:modelValue', this.doZeroPadding( val ));
				this.$emit('change');
			});
			if (co_checkDigitEqualed(val, this.codeMaxLength) && !this.containedList(val)) {
				this.setError();
			} 
		},
		// change - ListBox
		changeListBox : function( val ) {
			this.clearError();
			// 入力値のゼロパディング時の同値対策として意図的に更新
			this.$emit('update:modelValue', val);

			this.$nextTick(function() {
				this.$emit('update:modelValue', this.doZeroPadding( val ));
				this.$emit('change');
			});
		},
		// focus - CodeInput
		focusCodeInput: async function() {
			this.$emit("focus");

			// 画面の描画を待機
			await this.$nextTick();

			// テキストボックスを全選択
			this.$refs.codeText.select();
		},
		// focus - ListBox
		focusListBox: function(event){
			this.$emit("focus");
			event.target.tabIndex = Number(this.tabindex) + 1; 
		},
		// input - CodeInput 
		inputCodeInput: function( e ) {
			this.limitOnlyNumber( e );
		},
		// keydown - 
		keydownDeleteControl: function(e) {
			//活性の時のみ処理
			if(!this.disabledFlg){
				//活性時は親へのイベントを抑止する
				//（親でイベントをキャンセルしている）
				e.stopPropagation();
			}
		},
		// keyup - CodeInput
		keyupCodeInput: function( e ) {
			this.limitOnlyNumber( e );
		},
		// ▼ ---------------------------------------- サブメソッド ---------------------------------------- ▼
		// リストボックス(key)に含まれているかを検証
		// true: 含まれている、 false: 含まれていない
		containedList: function(val) {

			// リストのアイテムを取得
			let items = this.$refs.itemList.options;

			for(let index in items) {
				// 数値と文字列の曖昧対策===
				if(val === items[index].key) {
					return true;
				}
			}
			
			return false;
		},
		// パディング制御
		doZeroPadding: function(value) {
			if ( this.activeZeroPadding ) {
				if (! co_isEmpty( value )) {
					if(value.length > this.codeMaxLength){
						value = value.substr( 0, this.codeMaxLength);
					}
					return value.padStart(this.codeMaxLength, '0');
				} 
			}
			return value;
		},
		// 入力制御 - 数値のみ(全角数字も含む)
		limitOnlyNumber: function(e) {
			// 全角文字確定後のみ、数値以外の値をトリムする
			//if ( e.isComposing ) {
			if ( !isNaN(e.target.value) ) {
				return
			} else if(e.target.value == '０'){
				e.target.value == '0';
			} else {
				e.target.value = e.target.value.replace(/[０-９]/g, 
					function(s) {
						return String.fromCharCode(s.charCodeAt(0) - 0xFEE0);
					});
				e.target.value = e.target.value.replace(/\D/g, '');
			}
		},
		// ▼ ---------------------------------------- 共有メソッド ---------------------------------------- ▼
		// 妥当性チェック
		validate: function() {

			// エラーの初期化
			this.clearError();

			let value = co_trim(this.modelValue.toString());
			
			// 必須チェック
			if(this.requireFlg) {
				if ( co_isEmpty(value) ) {
					this.$store.commit(STORE_ERROR_ADDMSG, co_getMessage('CMS0013E', this.name));
					this.setError();
					return
				}
			}

			if ( !co_isEmpty(value) ) {
				// List選択以外の値
				if(!this.containedList(value)) {
					this.$store.commit(STORE_ERROR_ADDMSG, 
						co_getMessage('CMS0046E', this.name));
					this.setError();
				}

				// -- コードテキストチェック --
				// コード（数字）入力チェック
				if(!(co_checkDigitEqualed(value, this.codeMaxLength))) {
					this.$store.commit(STORE_ERROR_ADDMSG,
						co_getMessage('CMS0017E', this.name, String(this.codeMaxLength)));
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
		// エラー表示設定
		setError: function() {
			this.isError = true;
		},
		// エラー表示消去
		clearError: function() {
			this.isError = false;
		},
		// フォーカス設定
		focus: function() {
			this.$refs.codeText.focus();
		},
		// 上位からの更新
		resetModel: function(val) {
			
			// 入力値クリア
			this.$emit('update:modelValue', "");
			// リストボックス操作
			this.$refs.itemList.resetModel(val)

		},
		getZok05value: function(val) {
			return this.$refs.itemList.getZok05value(val);
		},
		getListLabelvalue: function(val) {
			return this.$refs.itemList.getLabelvalue(val);
		}
	}
}
</script>

<style scoped>
.code-wrapper {
	float: left;
}
.code {
	height: 43px;
	margin-right: 5px;
	padding: 5px 10px;
	text-align: left;
	width: var(--code-show-width);
}
.listbox {
	width: var(--listbox-show-width)
}
</style>