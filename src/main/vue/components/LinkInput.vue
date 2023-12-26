<!--
//================================================================================
// ID: -
// ProjectName: コープ九州事業連合様 ホストシステムリプレイス
// SystemName: 業務管理
// VueName: LinkInput
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/05/23 |   AMC                | 新規作成
//================================================================================
 -->
 <template>
	<input
		type="button"
		ref="linkInput"
        class="link-style"
		v-bind="$attrs"
		v-bind:value="label"
		v-bind:tabindex="tabindex"
		v-on:click="clickButtonInput"
	>
</template>
<script>
import { co_isEmpty } from '@/common/checkUtility.js';

export default {
	props: {
		label: {type: String, default: ''},
        href: {type: String, default: ''},
		tabindex: {type: String, default: '0'},
	},
	emits: ['click'],
	methods: {
		clickButtonInput: function() {
			this.$refs.linkInput.disabled = true;
			
			this.$nextTick(() => { 
				if (co_isEmpty(this.href)) {
					this.$emit("click");
				} else {
					this.$router.push(this.href);
				}
				this.$refs.linkInput.disabled = false;
			});
		},
		focus: function() {
			this.$refs.linkInput.focus();
		},
	}
}
</script>
<style scoped>
.link-style{
  cursor: pointer;
  border: none;
  background: none;
  color: #002080;
}
.link-style:hover{
  cursor: pointer;
  text-decoration: underline;
  background: #5e74db;
  color: #fff;
  font-weight: bold;
}
</style>