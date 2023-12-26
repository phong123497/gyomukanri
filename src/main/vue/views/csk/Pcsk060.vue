<!--
//================================================================================
// ID: PCSK060
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// VueName: Pcsk060
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/08/18 | ＡＭＣ               | 新規作成
//================================================================================
 -->
<template>

	<div class="view-area">
		<!-- 固定ヘッダエリア（共通） -->
		
		<main id="PCSK060">
		
			<div class="contents__block--exist_return">
				<h1 class="header__block-title">粗利益管理表出力</h1>
				<!-- エラーメッセージ -->
				<error-message />
					<div class="contents__wrap--scroll_vertical">
						<div class="padding-15px">
							<table>
								<colgroup>
									<col style="width:150px;">
									<col style="width:250px;">
								</colgroup>
								<tbody>
									<tr>
										<td class="txt--align_r height-43px">
											<span class="contents__block-search__label">種別</span>
										</td>
										<td>
											<div class="contents__block-radio">
												<radio-button
													ref="shubt"
													v-bind:disabledFlg="disabledFlg.shu"
													v-bind:listItems="shubt.radioButtonListItems"
													v-bind:name="'種別'"
													v-bind:radioGroupName="'shubt'"
													v-bind:requireFlg="true"
													v-bind:tabindex="'1'"
													v-model="form.shubt"/>
											</div>
										</td>
									</tr>
									<tr>
										<td class="txt--align_r height-43px">
											<span class="contents__block-search__label">管理体系区分</span>
										</td>
										<td>
											<div class="contents__block-radio">
												<radio-button
												ref="mngTaikeiKbn"
												v-bind:disabledFlg="disabledFlg.mng"
												v-bind:listItems="mngTaikeiKbn.radioButtonListItems"
												v-bind:name="'管理体系区分'"
												v-bind:radioGroupName="'mngTaikeiKbn'"
												v-bind:requireFlg="true"
												v-bind:tabindex="'3'"
												v-model="form.mngTaikeiKbn"
												v-on:click="clickMngTaikeiKbn"/>
											</div>
										</td>
									</tr>
								</tbody>
							</table>
							<table class="mgn-l-258px">
								<colgroup>
									<col style="width:150px;">
									<col style="width:150px;">
									<col style="width:150px;">
									<col style="width:150px;">
								</colgroup>
								<tbody class="cond__checkbox">
									<tr>
										<td class="height-43px">
											<check-box
												ref="mutempoFood"
												v-bind:disabledFlg="disabledFlg.checkbox"
												v-bind:listItems="mutempoFoodList"
												v-bind:name="'無店舗食品'"
												v-bind:tabindex="'3'"
												v-model="form.mutempoFood"
												v-on:click="clickMutempoFood"/>
										</td>
										<td>
											<check-box
												ref="nosanShinDept"
												v-bind:disabledFlg="disabledFlg.checkbox"
												v-bind:listItems="nosanShinDeptList"
												v-bind:name="'農産商品部'"
												v-bind:tabindex="'4'"
												v-model="form.nosanShinDept"
												v-on:click="clickMutempoFoodOne"/>
										</td>
										<td>
											<check-box
												ref="suisanShinDept"
												v-bind:disabledFlg="disabledFlg.checkbox"
												v-bind:listItems="suisanShinDeptList"
												v-bind:name="'水産商品部'"
												v-bind:tabindex="'5'"
												v-model="form.suisanShinDept"
												v-on:click="clickMutempoFoodOne"/>
										</td>
										<td>
											<check-box
												ref="chikusanShinDept"
												v-bind:disabledFlg="disabledFlg.checkbox"
												v-bind:listItems="chikusanShinDeptList"
												v-bind:name="'畜産商品部'"
												v-bind:tabindex="'6'"
												v-model="form.chikusanShinDept"
												v-on:click="clickMutempoFoodOne"/>
										</td>
									</tr>
									<tr>
										<td class="height-43px"></td>
										<td>
											<check-box
												ref="nippaiShinDept"
												v-bind:disabledFlg="disabledFlg.checkbox"
												v-bind:listItems="nippaiShinDeptList"
												v-bind:name="'日配商品部'"
												v-bind:tabindex="'7'"
												v-model="form.nippaiShinDept"
												v-on:click="clickMutempoFoodOne"/>
										</td>
										<td>
											<check-box
												ref="foodShinDept"
												v-bind:disabledFlg="disabledFlg.checkbox"
												v-bind:listItems="foodShinDeptList"
												v-bind:name="'食品商品部'"
												v-bind:tabindex="'8'"
												v-model="form.foodShinDept"
												v-on:click="clickMutempoFoodOne"/>
										</td>
										<td>
											<check-box
												ref="shinHombu"
												v-bind:disabledFlg="disabledFlg.checkbox"
												v-bind:listItems="shinHombuList"
												v-bind:name="'商品本部'"
												v-bind:tabindex="'9'"
												v-model="form.shinHombu"
												v-on:click="clickMutempoFoodOne"/>
										</td>
									</tr>
									<tr>
										<td class="height-43px">
											<check-box
												ref="mutempoHifood"
												v-bind:disabledFlg="disabledFlg.checkbox"
												v-bind:listItems="mutempoHifoodList"
												v-bind:name="'無店舗非食品'"
												v-bind:tabindex="'10'"
												v-model="form.mutempoHifood"/>
										</td>
										<td>
											<check-box
												ref="lifeAndSeiky"
												v-bind:disabledFlg="disabledFlg.checkbox"
												v-bind:listItems="lifeAndSeikyList"
												v-bind:name="'くらしと生協'"
												v-bind:tabindex="'11'"
												v-model="form.lifeAndSeiky"/>
										</td>
										<td>
											<check-box
												ref="scroll"
												v-bind:listItems="scrollList"
												v-bind:disabledFlg="disabledFlg.checkbox"
												v-bind:name="'スクロール'"
												v-bind:tabindex="'12'"
												v-model="form.scroll"/>
										</td>
										<td>
											<check-box
												ref="klim"
												v-bind:disabledFlg="disabledFlg.checkbox"
												v-bind:listItems="klimList"
												v-bind:name="'クリム'"
												v-bind:tabindex="'13'"
												v-model="form.klim"/>
										</td>
									</tr>
									<tr>
										<td class="height-43px">
											<check-box
												ref="tempo"
												v-bind:disabledFlg="disabledFlg.checkbox"
												v-bind:listItems="tempoList"
												v-bind:name="'店舗'"
												v-bind:tabindex="'14'"
												v-model="form.tempo"/>
										</td>
										<td>
											<check-box
												ref="tempoYodohin"
												v-bind:disabledFlg="disabledFlg.checkbox"
												v-bind:listItems="tempoYodohinList"
												v-bind:name="'店舗用度品'"
												v-bind:tabindex="'15'"
												v-model="form.tempoYodohin"/>
										</td>
									</tr>
								</tbody>
							</table>
							<table>
								<colgroup>
									<col style="width:150px;">
									<col style="width:600px;">
								</colgroup>
							<tbody>
								<tr>
									<td class="txt--align_r">
										<span class="contents__block-search__label">期間</span>
									</td>
									<td>
									
										<list-box class="width-100" 
													ref="nendo" 
													v-bind:blankFlg="false"
													v-bind:cdContentType="CD_CONTENT_TYPE.CONTENT"
													v-bind:disabledFlg="disabledFlg.listbox"
													v-bind:listItems="nendoList"
													v-bind:requireFlg="true"
													v-bind:name="'運用日の年度'"
													v-bind:tabindex="'16'" 
													v-model="form.nendo"
													v-on:change="onchgNendo">
										</list-box>
										<span class="contents__block-search__label"> {{ spacePlaceholder }} 年度</span>
										<list-box class="width-100" 
													ref="monthlyFrom" 
													v-bind:blankFlg="false"
													v-bind:cdContentType="CD_CONTENT_TYPE.CONTENT"
													v-bind:disabledFlg="disabledFlg.listbox"
													v-bind:listItems="monthlyList"
													v-bind:name="'運用日の月度'"
													v-bind:requireFlg="true"
													v-bind:tabindex="'17'" 
													v-model="form.monthlyFrom">
										</list-box>
										<span class="contents__block-search__label">月</span>
										<span class="contents__block-search__label">～</span>
										<list-box class="width-100" 
													ref="monthlyTo" 
													v-bind:blankFlg="false"
													v-bind:cdContentType="CD_CONTENT_TYPE.CONTENT"
													v-bind:disabledFlg="disabledFlg.listbox"
													v-bind:listItems="monthlyList"
													v-bind:name="'運用日の月度'"
													v-bind:requireFlg="true"
													v-bind:tabindex="'18'" 
													v-model="form.monthlyTo">
										</list-box>
										<span class="contents__block-search__label">月</span>
									</td>
								</tr>
							</tbody>
							</table>
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
								class="contents__btn-blue"
								v-bind:name="'backButton'"
								v-bind:tabindex="'21'"
								v-bind:value="'戻る'"
								v-on:click="clickReturnBtn" 
							/>
							<button-input
								class="contents__btn-blue"
								v-bind:disabledFlg="disabledFlg.btn"
								v-bind:name="'cancelButton'"
								v-bind:tabindex="'22'"
								v-bind:value="'取消'"
								v-on:click="clickCancelBtn" 
							/>
						</div>
					</div>
					<div class="center-block">
						<div class="button-group">
							<button-input
								class="contents__btn-blue"
								v-bind:disabledFlg="disabledFlg.btn"
								v-bind:name="'pdfOutputButton'"
								v-bind:tabindex="'19'"
								v-bind:value="'PDF出力'"
								v-on:click="clickPdfOutputBtn"
							/>
							<button-input
								class="contents__btn-blue"
								v-bind:disabledFlg="disabledFlg.btn"
								v-bind:name="'csvOutputButton'"
								v-bind:tabindex="'20'"
								v-bind:value="'CSV出力'"
								v-on:click="clickCsvOutputBtn"
							/>
						</div>
					</div>
				</div>
			</div>
						
		</footer>
	</div>
</template>
<script>
import { Core, PdfExport } from "@grapecity/activereports";
import RadioButton from '@/components/RadioButton.vue';
import ButtonInput from '@/components/ButtonInput.vue';
import CheckBox from '@/components/CheckBox.vue';
import ListBox from '@/components/ListBox.vue';
import ErrorMessage from '@/components/ErrorMessage.vue';

import { 
	co_downloadFile, 
	co_post,
	co_setError
} from '@/common/ajaxUtility.js';
import { co_isEmpty } from '@/common/checkUtility.js';

import {
	HTTP_STATUS,
	CD_CONTENT_TYPE,
} from '@/const/const.js';

import {
	PATH_PCSK060_SEARCH,
	PATH_PCSK060_PCSK06001PRINTCSV,
	PATH_PCSK060_PCSK06001PRINTPDF,
	PATH_PCSK060_CHANGE_NENDO,
} from '@/const/route.js'


import {
	STORE_ERROR_CLEAR,
	STORE_ERROR_IS_ERRFLG,
	STORE_USER_GET_INFO,
	STORE_INFO_CLEAR,
	STORE_VIEW_PROGRESS,
} from '@/const/store.js';

export default {
	components: {
		'button-input': ButtonInput,
		'radio-button': RadioButton,
		'check-box': CheckBox,
		'list-box': ListBox,
		'error-message': ErrorMessage,
	},
	
	data: function () {
		return {
			form: {
				//種別	
				shubt: "1",
				//管理体系区分	
				mngTaikeiKbn: "1",
				//無店舗食品	
				mutempoFood:"",
				//農産商品部	
				nosanShinDept:"",
				//水産商品部													
				suisanShinDept:"",
				//畜産商品部
				chikusanShinDept:"",
				//日配商品部	
				nippaiShinDept:"",
				//食品商品部	
				foodShinDept:"",
				//商品本部	
				shinHombu:"",
				//無店舗非食品	
				mutempoHifood:"",
				//くらしと生協	
				lifeAndSeiky:"",
				//スクロール	
				scroll:"",
				//クリム	
				klim:"",
				//店舗
				tempo:"",
				//店舗用度品
				tempoYodohin:"",
				//年度
				nendo: '',
				//月_FROM
				monthlyFrom: '',
				//月_TO	
				monthlyTo: '',
				
			},
			//種別	
			shubt:{
				// ラジオボタンデータリスト
				radioButtonListItems: [
					{code: "1", name: "管理"},
					{code: "2", name: "財務"},
				],
			},
			//管理体系区分		
			mngTaikeiKbn:{
				// ラジオボタンデータリスト
				radioButtonListItems: [
					{code: "1", name: "全体"},
					{code: "2", name: "個別選択"},
				],
			},
			mutempoFoodList: [
				{ code: "1", name: "無店舗食品" },
			],
			nosanShinDeptList: [
				{ code: "9501", name: "農産商品部" },
			],
			suisanShinDeptList: [
				{ code: "9502", name: "水産商品部" },
			],
			chikusanShinDeptList: [
				{ code: "9503", name: "畜産商品部" },
			],
			nippaiShinDeptList: [
				{ code: "9504", name: "日配商品部" },
			],
			foodShinDeptList: [
				{ code: "9505", name: "食品商品部" },
			],
			shinHombuList: [
				{ code: "9500", name: "商品本部" },
			],
			mutempoHifoodList: [
				{ code: "9380", name: "無店舗非食品" },
				],
			lifeAndSeikyList: [
				{ code: "9385", name: "くらしと生協" },
			],
			scrollList: [
				{ code: "9386", name: "スクロール" },
			],
			klimList: [
				{ code: "9331", name: "クリム" },
			],
			tempoList: [
				{ code: "9410", name: "店舗" },
			],
			tempoYodohinList: [
				{ code: "9612", name: "店舗用度品" },
			],
				//活性
			disabledFlg: {
				//種別
				shu: false,
				//管理
				mng: false,
				//チェックボックス
				checkbox:true,
			},
			tantoshaInfo : '',
			nendoList:[],
			monthlyList:[],
			errorMessage: '',
			searchdisabledFlg:false,
			spacePlaceholder: "\u00A0",
			CD_CONTENT_TYPE: CD_CONTENT_TYPE
		}
	},
	
	created: function () {
		this.initMethod();
	},
	mounted: async function(){
		// ** フォーカス設定 **
		await this.$nextTick();
		this.$refs.shubt.focus();
	},
	methods: {
		initMethod: function () {
			//初期化
			this.$store.commit(STORE_ERROR_CLEAR);
			this.$store.commit(STORE_VIEW_PROGRESS, true);
			co_post(PATH_PCSK060_SEARCH, this.form, this.searchBackFun)
		},
		//検索に戻す関数
		searchBackFun: function (res) {
			this.form.shubt = "1";
			this.form.mngTaikeiKbn = "1";
			this.chgMngTaikeiKbn("1");
			// プログレスバー非表示
			this.$store.commit(STORE_VIEW_PROGRESS,false)
			if (res.status == HTTP_STATUS.OK) {
				if (res.data.nendoList && Array.isArray(res.data.nendoList)) {
					this.nendoList = res.data.nendoList.map(function(item) {
						return {
							code: item.nendo,
							name: item.nendo,
						};
					});
				}
				if (res.data.monthlyList && Array.isArray(res.data.monthlyList)) {
					this.monthlyList = res.data.monthlyList.map(function(item) {
							return {
								code: item.kym,
								name: item.kym.substr(-2)
							};
						});
				}
				this.form.nendo= res.data.nendo;
				this.form.monthlyFrom= res.data.monthlyFrom;
				this.form.monthlyTo= res.data.monthlyTo;
				this.$refs.shubt.focus();
			}else {
				//戻るボタン以外非活性
				this.disabledFlg.checkbox=true;
				this.disabledFlg.listbox=true;
				this.disabledFlg.btn=true;
				this.disabledFlg.shu=true;
				this.disabledFlg.mng=true;
				this.$store.commit(STORE_VIEW_PROGRESS,false)
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
		},
		// click管理体系区分
		clickMngTaikeiKbn(event) {
			this.chgMngTaikeiKbn(event.target.value);
		},
		// 管理体系区分変更
		chgMngTaikeiKbn(value) {
			if (value == '1') {
				//「全体」を選択する場合、管理体系区分のすべてのチェックボックスが非活性になる。
				this.disabledFlg.checkbox=true;
				this.form.mutempoFood = "";
				this.form.nosanShinDept = "";
				this.form.suisanShinDept = "";
				this.form.chikusanShinDept = "";
				this.form.foodShinDept = "";
				this.form.nippaiShinDept = "";
				this.form.shinHombu = "";
				this.form.mutempoHifood = "";
				this.form.lifeAndSeiky = "";
				this.form.scroll = "";
				this.form.klim = "";
				this.form.tempo = "";
				this.form.tempoYodohin = "";
			}else{
				this.disabledFlg.checkbox = false;
			}
		},
		// click農産商品部～商品本部のチェックボックス
		clickMutempoFood(event) {
			if (event.target.checked) {
				this.form.nosanShinDept = "9501";
				this.form.suisanShinDept = "9502";
				this.form.chikusanShinDept = "9503";
				this.form.nippaiShinDept = "9504";
				this.form.foodShinDept = "9505";
				this.form.shinHombu = "9500";
			}else{
				this.form.nosanShinDept = "";
				this.form.suisanShinDept = "";
				this.form.chikusanShinDept = "";
				this.form.foodShinDept = "";
				this.form.nippaiShinDept = "";
				this.form.shinHombu = "";
			}
		},
		// click無店舗食品
		clickMutempoFoodOne(event) {
			if (event.target.checked) {
				if (this.allCheckboxesSelected(event.target.value)) {
					this.form.mutempoFood = "1";
				}
			}else{
				this.form.mutempoFood = "";
			}
		},
		// 農産商品部～商品本部のチェックボックスがすべて選択されているかどうかを判断する
		allCheckboxesSelected(value) {
			return (
				("9501" == value || this.form.nosanShinDept == "9501") &&
				("9502" == value || this.form.suisanShinDept =="9502") &&
				("9503" == value || this.form.chikusanShinDept =="9503") &&
				("9504" == value || this.form.nippaiShinDept =="9504") &&
				("9505" == value || this.form.foodShinDept =="9505") &&
				("9500" == value || this.form.shinHombu =="9500")
			);
		},
		// 年度変更
		onchgNendo(event) {
			this.$store.commit(STORE_VIEW_PROGRESS,true)
			let self = this;

			this.$nextTick(function(){
				co_post(PATH_PCSK060_CHANGE_NENDO, {nendo:event.target.value}, function(res) {
					self.$store.commit(STORE_VIEW_PROGRESS,false)
					if (res.status == HTTP_STATUS.OK) {
						if (res.data.monthlyList && Array.isArray(res.data.monthlyList)) {
							self.monthlyList = res.data.monthlyList.map(function(item) {
									return {
										code: item.kym,
										name: item.kym.substr(-2)
									};
								});
						}
						self.form.monthlyFrom= res.data.monthlyFrom;
						self.form.monthlyTo= res.data.monthlyTo;
						self.$refs.shubt.focus();
					}else {
						//戻るボタン以外非活性
						self.disabledFlg.checkbox=true;
						self.disabledFlg.listbox=true;
						self.disabledFlg.btn=true;
						self.disabledFlg.shu=true;
						self.disabledFlg.mng=true;
						// 業務エラー表示
						co_setError(res.data, self.$refs)
						const errorItem = res.data[0].errorItems[0];
						if (!co_isEmpty(self.$refs[errorItem])) {
							const self = self;
							self.$nextTick(function() {
								self.$refs[errorItem].focus();
							});
						}
					}
				});
			});
		},
		// click - 取消ボタン
		clickCancelBtn: function () {
			this.$nextTick(function() {
				this.initMethod();
			});
		},
		//click - 戻るボタン
		clickReturnBtn: function () {
			this.$store.commit(STORE_INFO_CLEAR)
			this.$router.back()
		},
		//csv出力ボタン
		clickCsvOutputBtn: function() {
			
			this.$store.commit(STORE_ERROR_CLEAR);
			this.$store.commit(STORE_VIEW_PROGRESS, true);
			// ** チェック処理 **
			this.$refs.shubt.validate();
			this.$refs.mngTaikeiKbn.validate();
			this.$refs.nendo.validate();
			this.$refs.monthlyFrom.validate();
			this.$refs.monthlyTo.validate();
			// ** エラー表示制御 **
			if (this.$store.getters[STORE_ERROR_IS_ERRFLG]) {
				this.$store.commit(STORE_VIEW_PROGRESS,false);
				return;
			}
			// CSVファイルをダウンロードするリクエスト
			const self = this;
			co_downloadFile(PATH_PCSK060_PCSK06001PRINTCSV, this.form, function(res, downloadHandle) {
				self.$store.commit(STORE_VIEW_PROGRESS,false);
				
				if (res.status != HTTP_STATUS.OK) {
					// 異常終了
					co_setError(res.data, self.$refs)
					if (!co_isEmpty(this.$refs[res.data[0].errorItems[0]])) {
						self.$refs[res.data[0].errorItems[0]].focus();
						//戻るボタン以外非活性
						this.disabledFlg.checkbox=true;
						this.disabledFlg.listbox=true;
						this.disabledFlg.btn=true;
						this.disabledFlg.shu=true;
						this.disabledFlg.mng=true;
					}
					return;
				}
				// CSVダウンロード
				downloadHandle(res);
			});
		},
		//pdf出力ボタン
		clickPdfOutputBtn: function() {
			this.$store.commit(STORE_ERROR_CLEAR);
			this.$store.commit(STORE_VIEW_PROGRESS, true);
			// ** チェック処理 **
			this.$refs.shubt.validate();
			this.$refs.mngTaikeiKbn.validate();
			this.$refs.nendo.validate();
			this.$refs.monthlyFrom.validate();
			this.$refs.monthlyTo.validate();
			// ** エラー表示制御 **
			if (this.$store.getters[STORE_ERROR_IS_ERRFLG]) {
				this.$store.commit(STORE_VIEW_PROGRESS,false)
				return;
			}
			co_post(PATH_PCSK060_PCSK06001PRINTPDF, this.form, this.pdfBackFun);
		},
		//PDF戻り関数
		pdfBackFun: function (res) {
			if (res.status == HTTP_STATUS.OK) {	
				this.outPutPDF(res.data)
			}else {
				this.$store.commit(STORE_VIEW_PROGRESS,false)
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
		},
		outPutPDF: async function(excelJsonData) {
						
			// 帳票定義体JSONデータ取得
			const reportResponse = await fetch("reports/csk/PCSK06001.rdlx-json");
			const report = await reportResponse.json();
			
			// 帳票データを帳票定義体に入れる
			report.DataSources[0].ConnectionProperties.ConnectString = "jsondata=" + JSON.stringify(excelJsonData);
			
			// リネームする
			const ARJS = Core;
			const PDF = PdfExport;
			
			// 帳票設定
			const settings = {
				info:{
					title:'Invoice_green_ipa',
					author:'コープ九州',
				},
				pdfVersion:'1.7',
			};
			ARJS.FontStore.registerFonts('reports/fonts/fontsConfig.json');
			
			// 帳票定義体をロードする
			const pageReport = new ARJS.PageReport({ language: "ja" });
			await pageReport.load(report);
			
			// 帳票をダウンロードする
			const pageDocument = await pageReport.run();
			const result = await PDF.exportDocument(pageDocument, settings);
			// 帳票のyyyyMMddHHmmss日付を取得
			const opt = { 
				year: 'numeric', 
				month: '2-digit', 
				day: '2-digit',   
				hour: 'numeric',
				hour12: false,  
				minute: 'numeric', 
				second: 'numeric', 
				fractionalSecondDigits: 3,
			}
			const yyyyMMddHHmmss = new Intl.DateTimeFormat('jp', opt).format(new Date())
			.replaceAll("/", "")
			.replaceAll(":", "")
			.replaceAll(" ", "")
			.replaceAll(".", "");
			this.tantoshaInfo = this.$store.getters[STORE_USER_GET_INFO]
			const userId = this.tantoshaInfo.tansCd;
			// 帳票の名を構成する
			// ダウンロードする 
			if (this.form.shubt == "1") {
				result.download("粗利益管理表（管理）_" +`${userId}`+"_"+`${yyyyMMddHHmmss}`);
			}else{
				result.download("粗利益管理表（財務）_" +`${userId}`+"_"+`${yyyyMMddHHmmss}`);
			}
			this.$store.commit(STORE_VIEW_PROGRESS, false);
		},
	}
}
</script>
<style scoped>
.footer-button__block .left-block {
	display: inline-flex;
	width: 25%;
}
.footer-button__block .left-block input[type="button"],
.footer-button__block .center-block input[type="button"] {
  float: left;
  margin-right: 30px;
}
.footer-button__block .center-block {
	display: inline-flex;
	width: 45%;
}
.mgn-l-258px {
	margin-left: 258px;
}

.padding-15px {
	padding: 15px;
}

.height-43px{
	height: 43px;
}

</style>
