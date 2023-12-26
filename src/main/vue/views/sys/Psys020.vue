<!--
//================================================================================
// ID: PSYS020
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// VueName: メニュー
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2022/11/25 |  AMC                 | 新規作成
//================================================================================
 -->
 <template>
	<div>
		<div class="contents__block--exist_return">
			<h1 class="header__block-title">業務管理メニュー</h1>
			<div class="contents__wrap--scroll_vertical">
				<div class="contents__view--bg_gray">
					<table class="sam-table">
						<tbody>
							<tr class="sam-area">
								<td>
									<div class="tab">
										<div v-for="(pageInfo, page) in menuPattern" v-bind:key="page">
											<button-input
													v-bind:id="'tab'+ page"
													v-bind:class="['page-button', { active: activeButton === 'tab'+ page }]"
													v-bind:name="page"
													v-bind:value="pageInfo.name"
													v-bind:tabindex="String(page * 1000)"
													v-on:click="clickPage(page)"
													v-bind:ref="'pageTab' +  page" />
										</div>
									</div>
									<div v-for="(pageInfo, page) in menuPattern" v-bind:key="page">
										<div v-bind:id="'tab'+ page +'area'" class="tabcontent">
											<table class="tab-table">
												<tbody>
													<tr class="tab-area">
														<td>
															<ul class="list">
																<li v-for="(funcInfo, funcId, index) in pageInfo.funcMap" v-bind:key="funcId">
																	<link-input
																		v-bind:id="funcId"
																		v-bind:label="funcInfo.name"
																		v-bind:tabindex="String(page * 1000 + index)"
																		v-bind:ref="funcId" 
																		v-on:click="clickFunctionLink(page, funcId, funcInfo, index)"/>
																</li>
															</ul>
														</td>
													</tr>
												</tbody>
											</table>
										</div>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</template>
<script>

import LinkInput from '@/components/LinkInput.vue'
import ButtonInput from '@/components/ButtonInput.vue'

import { co_isEmpty } from '@/common/checkUtility.js';

import { PATH_PEXT020 } from '@/const/route.js';
import { 
	STORE_VIEW_CLEAR_VIEW_PARAMS,
	STORE_MENUPTN_GET_MENU_PATTERN_INFO, 
	STORE_MENUPTN_GET_ROOT_FUNC_INFO, 
	STORE_MENUPTN_SET_ROOT_FUNC_INFO, 
	STORE_MENUPTN_CLEAR_ROOT_FUNC_INFO,
} from '@/const/store.js';


export default {
	components: {
		'link-input': LinkInput,
		'button-input': ButtonInput,
	},
	data: function() {
		return {
			PATH_PEXT020:PATH_PEXT020,
			menuPattern:null,
			rootFuncInfo:null,
			activeButton:null,
		}
	},
	created: function() {
		
		// 画面間パラメータを初期化
		this.$store.commit(STORE_VIEW_CLEAR_VIEW_PARAMS);
		
		//メニューパターン情報取得
		this.menuPattern = this.$store.getters[STORE_MENUPTN_GET_MENU_PATTERN_INFO]();
		
		//遷移元情報取得
		this.rootFuncInfo = this.$store.getters[STORE_MENUPTN_GET_ROOT_FUNC_INFO];
		
		//表示メニューパターン作成
		let pageList = Object.keys(this.menuPattern);
		
		for (let i = 0; i < pageList.length; i++) {
			
			let newFuncMap = {};
			let funcIdList = Object.keys(this.menuPattern[pageList[i]].funcMap);
			
			for (let j = 0; j < funcIdList.length; j++) {
				
				let funcInfo = this.menuPattern[pageList[i]].funcMap[funcIdList[j]];
				if (funcInfo.displayMenuFlg === "1") {
					
					newFuncMap[funcIdList[j]] = funcInfo;
				}
			}
			//newFuncMapの長さが0より大きい場合にfuncMapに代入する
			if (Object.keys(newFuncMap).length > 0) {
				
				this.menuPattern[pageList[i]].funcMap = newFuncMap;
			}
			//newFuncMapの長さが0と等しい場合にページを削除する
			if (Object.keys(newFuncMap).length == 0) {
				
				delete this.menuPattern[pageList[i]];
			}
			
		}
		
		if (Object.keys(this.menuPattern).length > 0) {
			
			let page = Object.keys(this.menuPattern)[0];
	
			//初期タブクリック設定
			if(!co_isEmpty(this.rootFuncInfo)){
				
				page = this.rootFuncInfo.page;
			}
			this.$nextTick(function() { 
				this.controlShowPage(page);	
			});
			
			//フォーカス設定
			this.$nextTick(function() {
				
				if(co_isEmpty(this.rootFuncInfo)){
					
					document.getElementById("tab" + page).focus();
				}else{
					
					let refName = this.rootFuncInfo.funcId;
					this.$refs[refName][0].focus();
					this.$store.commit(STORE_MENUPTN_CLEAR_ROOT_FUNC_INFO);
				}
			});
		}
	},
	methods: {
		
		//ページ表示/非表示制御に引数を渡す
		clickPage:function(page) {

			this.controlShowPage(page);	
			this.$nextTick(function() {
				
				document.getElementById("tab" + page).focus();
			});
			
			
		},
		//ページ表示/非表示制御
		controlShowPage:function(page) {
			
			let tabcontent = document.getElementsByClassName("tabcontent");
			for (let i = 0; i < tabcontent.length; i++) {
				
				tabcontent[i].style.display = "none";
			}
			document.getElementById('tab'+ page +'area').style.display = "block";
			//タブアクティブ制御
			this.activeButton = 'tab'+ page;
		},
		
		//遷移元情報保存
		clickFunctionLink:function(page, funcId, funcInfo) {
			
			let rootFuncInfo = {
					page: page,
					pageName: this.menuPattern[page].name,
					funcId: funcId,
					funcName: funcInfo.name,}
			this.$store.commit(STORE_MENUPTN_SET_ROOT_FUNC_INFO, rootFuncInfo);
			this.$router.push(funcInfo.path);
		}
	}
}
</script>

<style scoped>
.margin-l-20px {
	margin-left: 20px;
}

li {
	list-style: none;
	padding: 8px 10px;
	width: 520px;
}

.tab {
	overflow: hidden;
	border: 1px solid #ccc;
	background-color: #f8ebd3;
}

.tab .page-button {
	background-color: inherit;
	float: left;
	border: none;
	cursor: pointer;
	padding: 14px 16px;
	font-size: 16px;
}

.tab .page-button:hover {
	background-color: #face7e;
}

.tab .page-button.active {
	background-color: #fab230;
}

.tabcontent {
	display: none;
	padding: 6px 24px;
	border: 1px solid #ccc;
	width: 1750px;
}

.tab-table {
	height: 640px;
}

.tab-table td {
	width: 580px;
}

.tab-area {
	vertical-align: top;
}

.button-hidden {
	visibility: hidden;
}

.list {
	display: flex;
	flex-flow: column wrap;
	height: 540px;
}

input[type="button"]:focus {
  outline: solid 2px black;
  outline-offset: -2px;
}

.contents__wrap--scroll_vertical {
  height    : calc(100vh - 100px - 16px - var(--contents__block-error-size,0px));
  overflow-y: auto;
}
</style>