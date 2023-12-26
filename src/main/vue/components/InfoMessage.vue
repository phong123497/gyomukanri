<!--
//================================================================================
// ID:-
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// VueName: InfoMessage
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/05/26 | AMC                  | 新規作成
//================================================================================
 -->
 <template>
	<p id="info" v-if="infoFlg" class="contnts__block-info">{{ infoMsg }}</p>
</template>

<script>
import {
	STORE_INFO_CLEAR,
	STORE_INFO_SET_POPUP_FLG,
	STORE_INFO_GET_MSG,
	STORE_INFO_IS_INFOFLG,
 } from '@/const/store.js';

export default {
	props: {
		popupFlg: {type: Boolean, default: false},		// true : ポップアップ
	},
	created: function() {
		this.$store.commit(STORE_INFO_SET_POPUP_FLG, this.popupFlg);
	},
	beforeUnmount: function() {
		this.$store.commit(STORE_INFO_CLEAR);
	},
	computed: {
		infoFlg: function() {
			return this.$store.getters[STORE_INFO_IS_INFOFLG];
		},
		infoMsg: function() {
			return this.$store.getters[STORE_INFO_GET_MSG];
		}
	},
}
</script>

<style scoped>
.contnts__block-info {
	font-weight: bold;
	margin-block-start: 0em;
    margin-block-end: 0em
}
</style>