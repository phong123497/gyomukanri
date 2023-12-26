<!--
//================================================================================
// ID: -
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// VueName: SystemError
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/04/11 |  AMC                 | 新規作成
//================================================================================
 -->
<template>
	<main>
		<div class="contents__block--notexist_menu">
			<div class="contents__wrap--scroll_vertical">
				<div class="contents__view--bg_gray">
					<!-- エラーメッセージ -->
					<error-message />
					<div class="error__area-btn">
						<!-- ログインへ -->
						<input
							type="button"
							class="contents__btn-blue"
							value="ログインへ"
							v-on:click="clickToLogin">
					</div>
				</div>
			</div>
		</div>
	</main>
</template>

<script>
import ErrorMessage from '@/components/ErrorMessage.vue';

import { co_doLogout } from '@/common/unloadEventUtility.js';

import { PATH_LOGIN } from '@/const/route.js';

export default {
	components: {
		'error-message': ErrorMessage,
	},
	methods: {
		clickToLogin: function() {
			window.removeEventListener('beforeunload', co_doLogout);
			window.location.href = PATH_LOGIN;
		}
	}
}
</script>

<style scoped>
.contents__block--notexist_menu {
	padding-top: 150px;
}
.error__area-btn {
	margin-top: 10px;
	text-align: center;
}
</style>