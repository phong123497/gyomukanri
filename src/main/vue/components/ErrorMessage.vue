<!--
//================================================================================
// ID:-
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// VueName: ErrorMessage
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2022/10/28 |   AMC                | 新規作成
//================================================================================
 -->
<template>
	<div id="error" v-if="errFlg" class="contents__block-error" ref="errBlock">
		<p class="contents__block-error__msg">以下のエラーがあります。</p>
		<p v-for="(msg, index) in errMsg" v-bind:key="index" class="contents__block-error__msg" >・{{msg}}</p>
	</div>
</template>

<script>
import { STORE_ERROR_IS_ERRFLG, STORE_ERROR_GET_ERRMSG, STORE_ERROR_SET_POPUP_FLG, STORE_ERROR_CLEAR,
			STORE_ERROR_IS_POPUP_ERRFLG, STORE_ERROR_GET_POPUP_ERRMSG } from '@/const/store.js';

export default {
	props: {
		popupFlg: {type: Boolean, default: false},		// true : ポップアップ
	},
	created: function() {
		if (this.popupFlg) {
			this.$store.commit(STORE_ERROR_SET_POPUP_FLG, true);
			this.$store.commit(STORE_ERROR_CLEAR);

			let elm = document.getElementsByClassName('contents__wrap--scroll_vertical')[0];
			if ( elm !== undefined ) {
				elm.style.overflowY = "hidden";
			}
		}
	},
	beforeUnmount: function() {
		if (this.popupFlg) {
			this.$store.commit(STORE_ERROR_SET_POPUP_FLG, false);

			let elm = document.getElementsByClassName('contents__wrap--scroll_vertical')[0];
			if ( elm !== undefined ) {
				elm.style.overflowY = "auto";
			}
		}
	},
	computed: {
		errFlg: function() {
			if (this.popupFlg) {
				return this.$store.getters[STORE_ERROR_IS_POPUP_ERRFLG];
			} else {
				return this.$store.getters[STORE_ERROR_IS_ERRFLG];
			}
		},
		errMsg: function() {
			if (this.popupFlg) {
				return this.$store.getters[STORE_ERROR_GET_POPUP_ERRMSG];
			} else {
				return this.$store.getters[STORE_ERROR_GET_ERRMSG];
			}
		},
	},
	updated: function() {
		if ( this.errFlg && !this.popupFlg ) {
			let elm = document.getElementsByClassName('contents__wrap--scroll_vertical')[0];
			if ( elm !== undefined ) {
				elm.scrollTop = 0;
			}
		}
		if ( this.errFlg ) {
			
			//エラーメッセージの高さを取得
			let errHeight = this.$refs.errBlock.offsetHeight;
			//エラーメッセージのマージンを代入
			let errMargin = 20;
			//エラーメッセージの高さとマージンを足して--contents__block-error-heightの値を上書き
			document.documentElement.style.setProperty('--contents__block-error-height',errHeight + errMargin + "px");
		} else {
			
			//--contents__block-error-heightの値を0pxに設定
			document.documentElement.style.setProperty('--contents__block-error-height', "0px");
		}
	},
}
</script>

<style scoped>
</style>