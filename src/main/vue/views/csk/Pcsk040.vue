<!--
//================================================================================
// ID: PCSK040
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// VueName: Pcsk040
//
// <<Modification History>>
// Version  | Date	   | Updated By		   | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/21 | ＡＭＣ			   | 新規作成
//================================================================================
 -->
 <template>
	<div class="view-area">
		<!-- 固定ヘッダエリア（共通） -->
		<main id="PCSK040">
			<div class="contents__block--exist_return">
			<table>
						<tbody>
							<tr>
								<td class="header__block-title">
									締日登録
								</td>
								<td>
									<info-message />
								</td>
							</tr>
						</tbody>
					</table>
				
				<!-- エラーメッセージ -->
				<error-message/>
				<div class="contents__wrap--scroll_vertical">
					<div class="contents__block-search">
						<div class="contents__block-search__main">
							<div class="tbl-list__wrap">
							
								<table>
									<colgroup>
										<col style="width:100px;">
										<col style="width:100px;">
										<col style="width:150px;">
									</colgroup>
									<tbody>
										<tr>
											<td class="txt--align_r">
												<span class="contents__block-search__label">年度</span>
											</td>
											<td>
												<text-input
												
														class="width-70"
														ref="nedo"
														v-bind:maxLength="4"
														v-bind:name="'年度'"
														v-bind:requireFlg="true"
														v-bind:tabindex="'1'"
														v-bind:textType="TEXTINPUT_TYPE.ALL"
														v-model="form.nedo"
												></text-input>
											</td>
											<td class="txt--align_c">
												<button-input
													class="contents__btn-blue"
													v-bind:value="'検索'"
													v-bind:disabledFlg="searchDisabledFlg"
													v-bind:name="'SearchBtn'"
													v-bind:tabindex="'2'"
													v-on:click="clickSearchBtn"
												/>
											</td>
											
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<div class="contents__view--bg_gray">
						<div class="tbl-inline-block" >
							<table class="tbl-list--border-all_exist wrap--overflow_hidden tbl-sticky-head">
								<colgroup>
									<col style="width:140px;">
									<col style="width:220px;">
									<col style="width:220px;">
									<col style="width:220px;">
									<col style="width:220px;">
								</colgroup>
								<thead>
									<tr>
										<th rowspan="2">
											計上年月
										</th>
										<th rowspan="2">
											コープ九州<br>
											月次締日
										</th>
										<th colspan="2">
											商品管理表締日
										</th>
										<th rowspan="2">
											消込照合処理日
										</th>
									</tr>
									<tr>
										<th>
											商品管理
										</th>
										<th>
											経理
										</th>
									</tr>
								</thead>
							</table>
							<table class="tbl-list--border-all_exist wrap--overflow_hidden">
								<colgroup>
									<col style="width:140px;">
									<col style="width:220px;">
									<col style="width:220px;">
									<col style="width:220px;">
									<col style="width:220px;">
								</colgroup>
								<tbody>
									<tr v-for="(item, index) in tableData" v-bind:key="index" >
										<td class="border--top_none">
										<div class="vertical-center">
											{{ item.keijoYm.substring(0, 4) + '/' + item.keijoYm.substring(4) }}
										</div>
										</td>
										<td class="border--top_none txt--align_c">
											<div class="vertical-center">
												<date-input
														class="input-field"
														v-bind:ref="'cpkyushuMonthCloseDay'+index"
														v-bind:dateType="DATEINPUT_TYPE.YMD"
														v-bind:disabledFlg="itemDisabledFlgs[index].cpkyushuMonthCloseDay"
														v-bind:name="'月次締日'"
														v-bind:tabindex="'3'"
														v-model="item.cpkyushuMonthCloseDay"/>
											</div>
										</td>
										
										<td class="border--top_none txt--align_c">
											<div class="vertical-center">
												<date-input
														class="input-field"
														v-bind:dateType="DATEINPUT_TYPE.YMD"
														v-bind:disabledFlg="itemDisabledFlgs[index].shinMngHyoCloseDayShinMng"
														v-bind:name="'商品管理'"
														v-bind:ref="'shinMngHyoCloseDayShinMng'+index"
														v-bind:tabindex="'4'"
														v-model="item.shinMngHyoCloseDayShinMng"/>
											</div>
										</td>
										<td class="border--top_none txt--align_c">
											<div class="vertical-center">
												<date-input
														class="input-field"
														v-bind:dateType="DATEINPUT_TYPE.YMD"
														v-bind:disabledFlg="itemDisabledFlgs[index].shinMngHyoCloseDayKeiri"
														v-bind:name="'経理'"
														v-bind:ref="'shinMngHyoCloseDayKeiri'+index"
														v-bind:tabindex="'5'"
														v-model="item.shinMngHyoCloseDayKeiri"/>
											</div>
										</td>
										<td class="border--top_none txt--align_c">
											<div class="vertical-center">
												<div class="row-container">
													<date-input
															class="input-field"
															
															v-bind:dateType="DATEINPUT_TYPE.YMD"
															v-bind:disabledFlg="itemDisabledFlgs[index].keshikmShogoProcsDayProcsDay"
															v-bind:name="'消込照合処理日'"
															v-bind:ref="'keshikmShogoProcsDayProcsDay'+index"
															v-bind:tabindex="'6'"
															v-model="item.keshikmShogoProcsDayProcsDay"/>
															<span class="input-field">{{ item.keshikmShogoProcsDayProcsKbn }}</span>
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
		</main>
		<footer>
			<div class="contents__block-command-btn--fullsize">
				<div class="footer-button__block">
					<div class="left-block">
						<div class="button-group">
							<button-input
									:style="{ 'margin-right': '30px' }"
									class="contents__btn-blue"
									v-bind:name="'backButton'"
									v-bind:tabindex="9"
									v-bind:value="'戻る'"
									v-on:click="clickReturnBtn" />
							<button-input
									class="contents__btn-blue"
									v-bind:name="'cancelBtn'"
									v-bind:tabindex="8"
									v-bind:value="'取消'"
									v-on:click="clickCancelBtn" />
						</div>
					</div>
					<div class="center-block">
					</div>
					<div class="right-block">
						<div class="button-group">
							<button-input
								class="contents__btn-blue"
								v-bind:disabledFlg="disabledFlg.saveBtn"
								v-bind:tabindex="'7'"
								v-bind:value="'保存'"
								v-on:click="clickSaveBtn()"/>
						</div>
					</div>
				</div>
			</div>
		</footer>
	</div>
</template>
<script>
import ButtonInput from '@/components/ButtonInput'
import DateInput from '@/components/DateInput.vue';
import ErrorMessage from '@/components/ErrorMessage.vue'
import InfoMessage from '@/components/InfoMessage.vue';
import TextInput from '@/components/TextInput'

import {
	STORE_VIEW_PROGRESS,
	STORE_DATA_GETDATA,
	STORE_ERROR_CLEAR,
	STORE_INFO_CLEAR,
	STORE_VIEW_CHANGED,
	STORE_VIEW_HAS_CHANGED,
	STORE_ERROR_ADDMSG,
	STORE_INFO_SET_MSG,
	STORE_ERROR_IS_ERRFLG,
} from '@/const/store.js'

import {
	co_post,
	co_put,
	co_setError,
} from '@/common/ajaxUtility.js';
import { 
	co_getMessage 
} from '@/common/messageUtility.js';
import { co_confirm } from '@/common/dialogUtility.js';
import { co_isEmpty } from '@/common/checkUtility.js'
import { co_backView } from "@/common/viewUtility.js";

import { 
	HTTP_STATUS,
	TEXTINPUT_TYPE,
	DIALOG_BUTTON_LIST_KEY,
	DATEINPUT_TYPE, 
} from '@/const/const.js'

import {
	PATH_PCSK040_SEARCH,
	PATH_PCSK040_UPDATA,
} from '@/const/route.js'		

export default {
	components: {
		'error-message': ErrorMessage,
		'date-input': DateInput,
		'info-message': InfoMessage,
		'button-input': ButtonInput,
		'text-input': TextInput,
	},
	data: function () {
		return {
			form: {
				nedo: '',
				closeDayList: [],
			},
			itemDisabledFlgs: [],
			checkList:[],
			tableData:[],
			yymd:'',
			checkflag:'',
			searchDisabledFlg: false,
			TEXTINPUT_TYPE: TEXTINPUT_TYPE,
			disabledFlg: {
				saveBtn: true,
			},
			DATEINPUT_TYPE: DATEINPUT_TYPE,
		}
	},
	watch: {
		// formオブジェクト内のすべての変更を監視する
		tableData: {
		handler: function () {
			this.$store.commit(STORE_VIEW_CHANGED, true);
		},
		deep: true
	}
	},
	created: function () {
		//初期化
		this.$store.commit(STORE_ERROR_CLEAR);
		this.disabledFlg.saveBtn=true;
		this.$refs.nedo.focus()
		let sData = this.$store.getters[STORE_DATA_GETDATA]('pcsk040')
		if (sData != null) {
			this.form.nedo = sData.nedo
		}
	},
	methods: {
		// click - 検索ボタン
		clickSearchBtn: async function () {
			this.tableData = [];
			// エラーをクリア
			this.$store.commit(STORE_INFO_CLEAR);
			this.$store.commit(STORE_ERROR_CLEAR)
			// 入力チェック
			this.$refs.nedo.validate()
			if (this.$store.getters[STORE_ERROR_IS_ERRFLG]) {
				// プログレスバー非表示
				this.disabledFlg.saveBtn=true;
				this.$store.commit(STORE_VIEW_PROGRESS, false)
				return
			}
			let self = this
			// プログレスバー表示
			this.$store.commit(STORE_VIEW_PROGRESS, true)
			co_post(PATH_PCSK040_SEARCH, self.form, self.searchBackFun)
		},
		//コールバック
		searchBackFun: function (res) {
			if (res.status == HTTP_STATUS.OK) {
				this.disabledFlg.saveBtn=false;
				this.checkList =this.tableData = res.data.closeDayList;
				this.yymd = res.data.ymd;
				this.checkDisabled();
				let self = this
				self.$nextTick(function () {
					self.$store.commit(STORE_VIEW_CHANGED, false)
				})
			} else {
				this.disabledFlg.saveBtn = true
				// プログレスバー非表示
				this.$store.commit(STORE_VIEW_PROGRESS, false)
				// 状態管理(ストア)のエラー情報をクリアする。
				this.$store.commit(STORE_ERROR_CLEAR)
				// MSG クリア
				this.$store.commit(STORE_INFO_CLEAR)
				// 業務エラー表示
				co_setError(res.data, this.$refs)
				const errorItem = res.data[0].errorItems[0];
				if (!co_isEmpty(this.$refs[errorItem])) {
					const self = this;
					this.$nextTick(function() {
						self.$refs[errorItem].focus();
						
					});
				}
			}
				// プログレスバー非表示
				this.$store.commit(STORE_VIEW_PROGRESS, false)
			
		},
		//活性処理
		checkDisabled: function(){
			let imem = []
				for (const tableItem of this.tableData){ 
					let disa = {
						cpkyushuMonthCloseDay:false,
							shinMngHyoCloseDayShinMng:false,
							shinMngHyoCloseDayKeiri:false,
							keshikmShogoProcsDayProcsDay:false,
					}
				if("未"!==tableItem.keshikmShogoProcsDayProcsKbn){
					disa.keshikmShogoProcsDayProcsDay= true ;
				}
				if("0"!==tableItem.moprcKbn){
					disa.cpkyushuMonthCloseDay= true ;
				}	
				if(tableItem.shinMngHyoCloseDayShinMng<this.yymd){
					disa.shinMngHyoCloseDayShinMng= true ;
				}
				if(tableItem.shinMngHyoCloseDayKeiri<this.yymd){
					disa.shinMngHyoCloseDayKeiri= true ;
				}
				imem.push(disa);
			}
			this.itemDisabledFlgs = imem;
		},
		// click - 戻るボタン
		clickReturnBtn: function () {
			if (this.$store.getters[STORE_VIEW_HAS_CHANGED]) {
				// 確認ダイアログを表示
				co_confirm(co_getMessage('CMS0003W'), function (btnValue) {
					if (btnValue == DIALOG_BUTTON_LIST_KEY.OK) {
						co_backView()
					}
				})
			} else {
				co_backView()
			}
		},
		// click - 取消ボタン
		clickCancelBtn: function () {
			if (this.$store.getters[STORE_VIEW_HAS_CHANGED]) {
			// 変更がある場合に警告メッセージを表示
			const self = this;
			co_confirm(co_getMessage('CMS0003W'), function (btnValue) {
				if (btnValue == DIALOG_BUTTON_LIST_KEY.OK) {
					self.form = {
					nedo: '',
				},
				self.$store.commit(STORE_ERROR_CLEAR);
				self.tableData = [];
				self.isCancelButtonDisabled = true;
				self.$nextTick(function () {
					// フォーカス設定
					self.$refs.nedo.focus()
					// 編集状態をクリア
					self.$store.commit(STORE_VIEW_CHANGED, false)
				})
				}
			});
			}else{
				const self = this;
				self.form = {
						nedo: '',
					},
					self.$store.commit(STORE_ERROR_CLEAR);
					self.tableData = [];
					self.isCancelButtonDisabled = true;
					self.$nextTick(function () {
						// フォーカス設定
						self.$refs.nedo.focus()
						// 編集状態をクリア
						self.$store.commit(STORE_VIEW_CHANGED, false)
					})
			}
		},
		// 保存ボタンクリック
		clickSaveBtn: function () {
			// エラーをクリア
			this.$store.commit(STORE_INFO_CLEAR);
			this.$store.commit(STORE_ERROR_CLEAR);
			// 入力チェック
			this.form.closeDayList = this.tableData;
			if (this.$store.getters[STORE_ERROR_IS_ERRFLG]) {
				// プログレスバー非表示
				this.$store.commit(STORE_VIEW_PROGRESS, false);
				return;
			}
			// 进行校验
			this.checkAll();
			// ** エラー表示制御 **
			if (this.$store.getters[STORE_ERROR_IS_ERRFLG]) {
				return;
			}
			// 保存処理
			this.updateFunction();
			// 状態管理(ストア)のエラー情報をクリアする。
			this.$store.commit(STORE_ERROR_CLEAR);
			// MSG クリア
			this.$store.commit(STORE_INFO_CLEAR);
		},
		// データ処理の更新
		updateFunction: function () {
			const self = this;
			co_put(PATH_PCSK040_UPDATA, this.form,function(res){
				if (res.status == HTTP_STATUS.CREATED) {
					self.clickSearchBtn();
					self.$store.commit(STORE_INFO_SET_MSG, co_getMessage("CMS0001I"));
					self.$store.commit(STORE_VIEW_CHANGED, false);
				} else{ 
					self.disabledFlg.saveBtn = true
					// プログレスバー非表示
					self.$store.commit(STORE_VIEW_PROGRESS, false)
					// 状態管理(ストア)のエラー情報をクリアする。
					self.$store.commit(STORE_ERROR_CLEAR)
					// MSG クリア
					self.$store.commit(STORE_INFO_CLEAR)
					// 業務エラー表示
					co_setError(res.data, self.$refs)
				}
			})
		},
		checkAll: function () {
			let index = 0;
			let self= this;
			for (const taItem of this.tableData) {
				const sinDate = taItem.shinMngHyoCloseDayKeiri ? taItem.shinMngHyoCloseDayKeiri.substring(0, 6) : '';
				const shinDate = taItem.shinMngHyoCloseDayShinMng ? taItem.shinMngHyoCloseDayShinMng.substring(0, 6) : '';
		
				if (taItem.keijoYm !== shinDate && shinDate !== '') {
					self.$refs[`shinMngHyoCloseDayShinMng${index}`][0].isError = true;
					this.checkflag= "1";
				}if (taItem.keijoYm !== sinDate && sinDate !== '') {
					self.$refs[`shinMngHyoCloseDayKeiri${index}`][0].isError = true;
					this.checkflag= "1";
				}if (taItem.shinMngHyoCloseDayShinMng > taItem.shinMngHyoCloseDayKeiri && shinDate !== '' && taItem.shinMngHyoCloseDayKeiri !== '') {
					self.$refs[`shinMngHyoCloseDayKeiri${index}`][0].isError = true;
					this.checkflag= "2";
				}  
				const maxLength = 8;
				const fieldsToCheck = [
					'cpkyushuMonthCloseDay', 'keshikmShogoProcsDayProcsDay',
					'shinMngHyoCloseDayKeiri', 'shinMngHyoCloseDayShinMng'
				];
		
				fieldsToCheck.forEach(function(fieldName) {
					if (taItem[fieldName] && taItem[fieldName].length > maxLength) {
						taItem[fieldName] = taItem[fieldName].slice(0, maxLength);
					}
				});
		
				index++;
		}
			if(this.checkflag=="1"){
				this.$store.commit(STORE_ERROR_ADDMSG, co_getMessage('CSK0009E'," 締日"," 当月日付"))
				return;
			}else if(this.checkflag=="2"){
				this.$store.commit(STORE_ERROR_ADDMSG, co_getMessage('CSK0005E'," 日付の順番"))
				return;
			}
		},
		setErrorAndCommitMessage: function (index, errorCode, message) {
			this.$store.commit(STORE_ERROR_ADDMSG, co_getMessage(errorCode, message));
			if (!this.$store.getters[STORE_ERROR_IS_ERRFLG]) {
				this.$store.commit(STORE_VIEW_PROGRESS, false);
				this.$nextTick(function() {
					this.$store.commit(STORE_INFO_SET_MSG, co_getMessage('CMS0001I'));
					this.$store.commit(STORE_VIEW_CHANGED, false);
				}.bind(this));
				return;
			}
		},
	}
}
</script>
<style scoped>
.row-container {
	display: flex;
	align-items: center;
}
 .txt--align_c {
	text-align: center; /* 水平居中 */
  }
.row-container span {
	display: flex;
	justify-content: center;
  }
 .vertical-center {
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100%;
  }
.tbl-list--border-all_exist th {
	text-align: center;
}
.tbl-sticky-head {
	position: -webkit-sticky;
	position: sticky;
	top: 0;
	z-index: 1
}
</style>