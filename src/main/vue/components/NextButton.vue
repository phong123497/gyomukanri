<!--
//================================================================================
// ID: -
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// VueName: NextButton
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/06/22 |   AMC                | 新規作成
//================================================================================
 -->
<template>
	<div style="display: inline-block;">
		<button-input
			class="contents__btn-blue"
			v-if="isNextButtonFlg"
			v-bind:value="'次へ'"
			v-bind:name="name"
			v-bind:disabled="disabledFlg"
			v-bind:tabindex="tabindex"
			v-on:click="clickNext"/>
		<button-input
			class="contents__btn-blue"
			v-if="!isNextButtonFlg"
			v-bind:value="'再入力'"
			v-bind:name="name"
			v-bind:disabled="disabledFlg"
			v-bind:tabindex="tabindex"
			v-on:click="clickReinput"/>
	</div>
</template>
<script>
import ButtonInput from '@/components/ButtonInput.vue';

export default {
	components: {
		"button-input": ButtonInput,
	},
	props: {
		name: {type: String, default: ''},
		disabledFlg: {type: Boolean, default: false},
		isNextButtonFlg: {type: Boolean, default: true},
		clickNext: {type: Function, default: function(){}},
		clickReinput: {type: Function, default: function(){}},
		tabindex: {type: String, default: '0'},
	},
}
</script>
<style scoped>
</style>