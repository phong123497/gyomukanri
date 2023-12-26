<!--
//================================================================================
// ID: -
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// VueName: ButtonInput
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/02/13 |   AMC                | 新規作成
//================================================================================
 -->
<template>
	<input
		type="button"
		ref="buttonInput"
		v-bind="$attrs"
		v-bind:name="name"
		v-bind:value="value"
		v-bind:disabled="disabledFlg"
		v-bind:tabindex="tabindex"
		v-on:click="clickButtonInput"
	>
</template>
<script>

export default {
	props: {
		name: {type: String, default: ''},
		value: {type: String, default: ''},
		disabledFlg: {type: Boolean, default: false},
		tabindex: {type: String, default: '0'},
	},
	emits: ['click'],
	methods: {
		clickButtonInput: function() {
			this.$refs.buttonInput.disabled = true;
			
			this.$nextTick(() => { 
				this.$emit("click");
				this.$refs.buttonInput.disabled = false;
			});
		},
		focus: function() {
			this.$refs.buttonInput.focus();
		},
	}
}
</script>
<style scoped>
</style>