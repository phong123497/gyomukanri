<!--
//================================================================================
// ID: -
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// VueName: MessageDialog
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2022/10/28 |   AMC                | 新規作成
//================================================================================
 -->
<template>
	<div
		class="modal"
		v-on:keydown.esc="cancel"
		v-on:keydown.prevent.tab.exact="moveFirst()"
		v-on:keydown.prevent.shift.tab.exact="moveLast()"
	>
		<div class="modal__bg"></div>
		<div class="modal__contents-confirm">
			<h4 class="modal__header"></h4>
			<div class="modal__body">
				<p style="font-size: 130%; white-space:pre-wrap; word-wrap:break-word;">{{ message }}</p>
			</div>
			<div class="modal__footer">
				<button
					type="button"
					class="contents__btn-blue"
					v-on:keydown.prevent.tab.exact.stop="moveNext(item.key)"
					v-on:keydown.prevent.shift.tab.exact.stop="movePrev(item.key)"
					v-bind:ref="'refs_' + index"
					v-for="(item, index) in items"
					v-bind:key="item.key"
					v-on:click="clickButton(item.key)"
					v-bind:tabindex="'0'"
				>{{ item.val }}
				</button>
			</div>
		</div>
	</div>
</template>

<script>
import { STORE_DIALOG_DIALOG_FLG, STORE_DIALOG_GET_CALL_BACK } from '@/const/store.js';
import { DIALOG_BUTTON_LIST_KEY } from '@/const/const.js';

export default {
	props: {
		buttonList: {type: Array, default: function() {
				return []
			}
		},											// ダイアログに設定するボタンのリスト
		message: {type: String, default: ''},		// ダイアログに出力するメッセージ
		focusKey: {type: String, default: ''},		// フォーカスの設定するボタンのキー
	},
	data: function() {
		return {
			items: []
		}
	},
	created: function() {

		// リスト追加
		let list = [];
		for ( let i = 0; i < this.buttonList.length; i++ ) {
			list.push({key: this.buttonList[i].key, val: this.buttonList[i].label});
		}

		this.items = list;

	},
	mounted: function() {
		// ボタンのフォーカス設定
		this.focus(this.getKeyIndex(this.focusKey));
	},
	beforeUnmount() {
		this.items = null;
	},
	methods: {
		moveFirst: function() {
			this.focus(0);
		},
		moveLast: function() {
			this.focus(this.items.length - 1);
		},
		moveNext: function( key ) {
			let index = this.getKeyIndex(key);
			index = index + 1;

			if ( this.items[index] === undefined ) {
				index = 0;
			}

			this.focus(index);
		},
		movePrev: function( key ) {
			let index = this.getKeyIndex(key);
			index = index - 1;

			if ( this.items[index] === undefined ) {
				index = this.items.length - 1;
			}

			this.focus(index);
		},
		// キーに対応した配列の要素を取得
		getKeyIndex: function( key ) {
			for ( let i = 0; i < this.buttonList.length; i++ ) {
				if( this.buttonList[i].key === key ) {
					return i;
				}
			}
			return -1;
		},
		// フォーカス設定
		focus: function( index ) {
			if (this.$refs['refs_' + index] !== undefined) {
				this.$refs['refs_' + index][0].focus();
			}
		},
		clickButton: function(val) {
			// メッセージウィンドウを閉じる
            this.$store.commit(STORE_DIALOG_DIALOG_FLG, false);
			// コールバック関数の呼び出し
			this.$store.getters[STORE_DIALOG_GET_CALL_BACK](val);
		},
		cancel: function() {
			// ESC押下時のキーイベント
			// メッセージウィンドウを閉じる
            this.$store.commit(STORE_DIALOG_DIALOG_FLG, false);
			// コールバック関数の呼び出し
			this.$store.getters[STORE_DIALOG_GET_CALL_BACK](DIALOG_BUTTON_LIST_KEY.ESC);
		}
	}
}
</script>

<style scoped>
.modal {
	z-index: 100001;
}
</style>