<!--
//================================================================================
// ID: PMSK030
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// VueName: Pmsk030
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/09/14 | ＡＭＣ               | 新規作成
//================================================================================
 -->
<template>
	<div>
		<!-- メインコンテンツ -->
		<main id="PMSK030">
			<div class="contents__block--exist_return">
				<h1 class="header__block-title-with-input">
					<table>
						<colgroup>
							<col style="width: 300px" />
						</colgroup>
						<tbody>
							<tr>
								<td>名称／区分管理マスタメンテ</td>
							</tr>
						</tbody>
					</table>
				</h1>
				<!-- エラーメッセージ -->
				<div id="error">
					<error-message />
				</div>

				<div class="contents__body">
					<div class="contents__view--bg_white">
						<div class="contents-wrap--bg-w--pad-3per">
							<section>
								<table class="tbl-item--border-row_exist">
									<tbody>
										<tr>
											<td class="txt--align_l">
												<span class="contents__block-search__label">名称区分</span>
											</td>
											<td>
												<div class="display_flex">
													<text-input class="width-50 mgn-r-15px" ref="meishoKubun" v-bind:name="'名称区分'"
														v-bind:maxLength="2" v-bind:tabindex="'1'"
														v-bind:textType="TEXTINPUT_TYPE.HALF" 
														v-bind:disabledFlg="disabledFlg.meishoKubun"
														v-model="form.reckb" />
													<label class="width-400" for="">{{disp.reckbNm}}</label>
													<div class="display--flex mgn-r-15px">
														<input style="display: block; width: 20px; height: 18px;" v-bind:tabindex="2" type="radio" v-model="mstRadio"
															id="meisho" :value="CHECK_VALUE_MST.CHECK_MEISHO" v-bind:disabled="disabledFlg.meishoMst" v-bind:require="true" />
														<label class="mgn-r-15px" for="meisho">名称マスタ</label>
														<input style="display: block; width: 20px; height: 18px;" v-bind:tabindex="3" type="radio" v-model="mstRadio"
															id="kubun" :value="CHECK_VALUE_MST.CHECK_KUBUN" v-bind:disabled="disabledFlg.kubunKanriMst" v-bind:require="true"  />
														<label for="kubun">区分管理マスタ</label>
													</div>
												</div>
											</td>
										</tr>
										<tr>
											<td class="txt--align_l">
												<span class="contents__block-search__label">名称コード</span>
											</td>
											<td>
												<div class="display_flex">
													<text-input class="width-300 txt--align_l mgn-r-15px" ref="meishoCd" v-bind:name="'名称コード'"
														v-bind:textType="TEXTINPUT_TYPE.HALF" 
														v-model="form.meicd" v-bind:maxLength="20" v-bind:tabindex="'4'"
														v-bind:disabledFlg="disabledFlg.meishoCd" v-bind:requireFlg="true" />
													<span style="margin-left: 16px" class="">削除フラグ</span>
													<div id="delTarget" class="contents__block-checkbox">
														<check-box  
															style="display: none !important"
															v-bind:name="'削除フラグ'" ref="delFlg"
															v-bind:checked="form.delflg =='1'"
															v-bind:tabindex="'5'"
															v-bind:disabledFlg="disabledFlg.delFlg"
															v-bind:listItems="disp.delFlg"
															v-model="form.delflg" 
															v-on:click="clickCheckDel"/>
													</div>
												</div>
											</td>
										</tr>

										<!-- 7-->
										<tr>
											<td class="txt--align_l">
												<span class="contents__block-search__label">漢字名称１</span>
											</td>
											<td>
												<text-input class="width-400 txt--align_l" v-bind:name="'漢字名称１'" v-bind:maxLength="20"
													ref="kanjiMeisho1" v-bind:textType="TEXTINPUT_TYPE.FULL" v-bind:tabindex="'6'"
													v-model="form.kjnm" v-bind:disabledFlg="disabledFlg.kanjiMeisho1" v-bind:requireFlg="true" />
											</td>
										</tr>
										<!-- 8 -->
										<tr>
											<td class="txt--align_l">
												<span class="contents__block-search__label">カナ名称１</span>
											</td>
											<td>
												<text-input class="width-300 txt--align_l" v-bind:name="'カナ名称１'" v-bind:maxLength="20"
													v-bind:textType="TEXTINPUT_TYPE.HALF" ref="kanaMeisho1" v-bind:tabindex="'7'"
													v-model="form.knnm" v-bind:disabledFlg="disabledFlg.kanaMeisho1" v-bind:requireFlg="true" />
											</td>
										</tr>
										<!-- 9 -->
										<tr>
											<td class="txt--align_l">
												<span class="contents__block-search__label">漢字略称２</span>
											</td>
											<td>
												<text-input class="width-400 txt--align_l" v-bind:name="'漢字略称2'" v-bind:maxLength="20"
													ref="kanjiRyakusho2" v-bind:textType="TEXTINPUT_TYPE.FULL" v-bind:tabindex="'8'"
													v-bind:disabledFlg="disabledFlg.kanjiRyakusho2" v-model="form.kjrm" />
											</td>
										</tr>
										<!-- 10 -->
										<tr>
											<td class="txt--align_l">
												<span class="contents__block-search__label">カナ略称２</span>
											</td>
											<td>
												<text-input class="width-300 txt--align_l" v-bind:name="'カナ略称2'" v-bind:maxLength="20"
													ref="kanaRyakusho2" v-bind:textType="TEXTINPUT_TYPE.HALF" v-bind:tabindex="'9'"
													v-model="form.knrm" v-bind:disabledFlg="disabledFlg.kanaRyakusho2" />
											</td>
										</tr>
										<!-- 11 -->
										<tr>
											<td class="txt--align_l">
												<span class="contents__block-search__label">漢字名称３</span>
											</td>
											<td>
												<text-input class="width-400 txt--align_l" v-bind:name="'漢字名称3'" v-bind:maxLength="20"
													ref="kanjiMeisho3" v-bind:textType="TEXTINPUT_TYPE.FULL" v-bind:tabindex="'10'"
													v-model="form.kjrm3" v-bind:disabledFlg="disabledFlg.kanjiMeisho3" />
											</td>
										</tr>
										<!-- 12 -->
										<tr>
											<td class="txt--align_l">
												<span class="contents__block-search__label">カナ名称３</span>
											</td>
											<td>
												<text-input class="width-300 txt--align_l" v-bind:name="'カナ名称3'" v-bind:maxLength="20"
													ref="kanaMeisho3" v-bind:textType="TEXTINPUT_TYPE.HALF" v-bind:tabindex="'11'"
													v-model="form.knrm3" v-bind:disabledFlg="disabledFlg.kanaMeisho3" />
											</td>
										</tr>

										<!-- 遷移元：PMSK010の場合 -->
										<!-- 13 -->
										<!-- <div> -->
											<tr v-if="displayFlg.isPmsk010">
												<td class="txt--align_l">
													<span class="contents__block-search__label">属性１</span>
												</td>
												<td>
													<text-input class="width-300 txt--align_l" v-bind:name="'属性１(全角)'" v-bind:maxLength="10"
														ref="zokusei1" v-bind:textType="TEXTINPUT_TYPE.FULL" v-bind:tabindex="'12'" v-model="form.zok01"/>
												</td>
											</tr>
											<!-- 14 -->
											<tr v-if="displayFlg.isPmsk010">
												<td class="txt--align_l">
													<span class="contents__block-search__label">属性２</span>
												</td>
												<td>
													<text-input class="width-300 txt--align_l" v-bind:name="'属性2(全角)'" v-bind:maxLength="10"
														ref="zokusei2" v-bind:textType="TEXTINPUT_TYPE.FULL" v-bind:tabindex="'13'" v-model="form.zok02"/>
												</td>
											</tr>
											<!-- 15 -->
											<tr v-if="displayFlg.isPmsk010">
												<td class="txt--align_l">
													<span class="contents__block-search__label">属性３</span>
												</td>
												<td>
													<text-input class="width-300 txt--align_l" v-bind:name="'属性3(全角)'" v-bind:maxLength="10"
														ref="zokusei3" v-bind:textType="TEXTINPUT_TYPE.FULL" v-bind:tabindex="'14'" v-model="form.zok03" />
												</td>
											</tr>
											<!-- 16 -->
											<tr v-if="displayFlg.isPmsk010">
												<td class="txt--align_l">
													<span class="contents__block-search__label">属性４</span>
												</td>
												<td>
													<text-input class="width-300 txt--align_l" v-bind:name="'属性4(全角)'" v-bind:maxLength="10"
														ref="zokusei4" v-bind:textType="TEXTINPUT_TYPE.FULL" v-bind:tabindex="'15'" v-model="form.zok04" />
												</td>
											</tr>
											<!-- 17 -->
											<tr v-if="displayFlg.isPmsk010">
												<td class="txt--align_l">
													<span class="contents__block-search__label">属性５</span>
												</td>
												<td>
													<text-input class="width-300 txt--align_l" v-bind:name="'属性5(全角)'" v-bind:maxLength="10"
														ref="zokusei5" v-bind:textType="TEXTINPUT_TYPE.FULL" v-bind:tabindex="'16'" v-model="form.zok05"/>
												</td>
											</tr>
											<!-- 18 -->
											<tr v-if="displayFlg.isPmsk010">
												<td class="txt--align_l">
													<span class="contents__block-search__label">属性６</span>
												</td>
												<td>
													<text-input class="width-300 txt--align_l" v-bind:name="'属性6(全角)'" v-bind:maxLength="10"
														ref="zokusei6" v-bind:textType="TEXTINPUT_TYPE.FULL" v-bind:tabindex="'17'" v-model="form.zok06"/>
												</td>
											</tr>
											<!-- 19 -->
											<tr v-if="displayFlg.isPmsk010">
												<td class="txt--align_l">
													<span class="contents__block-search__label">属性７</span>
												</td>
												<td>
													<text-input class="width-300 txt--align_l" v-bind:name="'属性7(全角)'" v-bind:maxLength="10"
														ref="zokusei7" v-bind:textType="TEXTINPUT_TYPE.FULL" v-bind:tabindex="'18'" v-model="form.zok07"/>
												</td>
											</tr>
											<!-- 20 -->
											<tr v-if="displayFlg.isPmsk010">
												<td class="txt--align_l">
													<span class="contents__block-search__label">属性８</span>
												</td>
												<td>
													<text-input class="width-300 txt--align_l" v-bind:name="'属性8(全角)'" v-bind:maxLength="10"
														ref="zokusei8" v-bind:textType="TEXTINPUT_TYPE.FULL" v-bind:tabindex="'19'" v-model="form.zok08"/>
												</td>
											</tr>
											<!-- 21 -->
											<tr v-if="displayFlg.isPmsk010">
												<td class="txt--align_l">
													<span class="contents__block-search__label">属性９</span>
												</td>
												<td>
													<text-input class="width-300 txt--align_l" v-bind:name="'属性9(全角)'" v-bind:maxLength="10"
														ref="zokusei9" v-bind:textType="TEXTINPUT_TYPE.FULL" v-bind:tabindex="'20'" v-model="form.zok09"/>
												</td>
											</tr>
											<!-- 22 -->
											<tr v-if="displayFlg.isPmsk010">
												<td class="txt--align_l">
													<span class="contents__block-search__label">属性１０</span>
												</td>
												<td>
													<text-input class="width-300 txt--align_l" v-bind:name="'属性10(全角)'" v-bind:maxLength="10"
														ref="zokusei10" v-bind:textType="TEXTINPUT_TYPE.FULL" v-bind:tabindex="'21'" v-model="form.zok10"/>
												</td>
											</tr>
											<!-- 23 -->
											<tr v-if="displayFlg.isPmsk010">
												<td class="txt--align_l">
													<span class="contents__block-search__label">属性１１</span>
												</td>
												<td>
													<text-input class="width-300 txt--align_l" v-bind:name="'属性11(全角)'" v-bind:maxLength="10"
														ref="zokusei11" v-bind:textType="TEXTINPUT_TYPE.FULL" v-bind:tabindex="'22'"
														v-model="form.zok11"  />
												</td>
											</tr>
											<!-- 24 -->
											<tr v-if="displayFlg.isPmsk010">
												<td class="txt--align_l">
													<span class="contents__block-search__label">属性１２</span>
												</td>
												<td>
													<text-input class="width-300 txt--align_l" v-bind:name="'属性12(全角)'" v-bind:maxLength="10"
														ref="zokusei12" v-bind:textType="TEXTINPUT_TYPE.FULL" v-bind:tabindex="'23'"
														v-model="form.zok12"  />
												</td>
											</tr>
											<!-- 25 -->
											<tr v-if="displayFlg.isPmsk010">
												<td class="txt--align_l">
													<span class="contents__block-search__label">属性１３</span>
												</td>
												<td>
													<text-input class="width-300 txt--align_l" v-bind:name="'属性13(全角)'" v-bind:maxLength="10"
														ref="zokusei13" v-bind:textType="TEXTINPUT_TYPE.FULL" v-bind:tabindex="'24'"
														v-model="form.zok13"  />
												</td>
											</tr>
											<!-- 26 -->
											<tr v-if="displayFlg.isPmsk010">
												<td class="txt--align_l">
													<span class="contents__block-search__label">属性１４</span>
												</td>
												<td>
													<text-input class="width-300 txt--align_l" v-bind:name="'属性14(全角)'" v-bind:maxLength="10"
														ref="zokusei14" v-bind:textType="TEXTINPUT_TYPE.FULL" v-bind:tabindex="'25'"
														v-model="form.zok14"  />
												</td>
											</tr>
											<!-- 27 -->
											<tr v-if="displayFlg.isPmsk010">
												<td class="txt--align_l">
													<span class="contents__block-search__label">属性１５</span>
												</td>
												<td>
													<text-input class="width-300 txt--align_l" v-bind:name="'属性15(全角)'" v-bind:maxLength="10"
														ref="zokusei15" v-bind:textType="TEXTINPUT_TYPE.FULL" v-bind:tabindex="'26'"
														v-model="form.zok15"  />
												</td>
											</tr>
											<!-- 28 -->
											<tr v-if="displayFlg.isPmsk010">
												<td class="txt--align_l">
													<span class="contents__block-search__label">属性１６</span>
												</td>
												<td>
													<text-input class="width-300 txt--align_l" v-bind:name="'属性16(全角)'" v-bind:maxLength="10"
														ref="zokusei16" v-bind:textType="TEXTINPUT_TYPE.FULL" v-bind:tabindex="'27'"
														v-model="form.zok16"  />
												</td>
											</tr>
										<!-- </div> -->
										<!-- 遷移元：PMSK020の場合 -->
										<!-- 29 -->
										<!-- <div> -->
											<tr v-if="displayFlg.isPmsk020" class="lable_height" >
												<td >
													<label ref="zokusei1ttl" class="">  {{ disp.zokusei1ttl ? '属性１タイトル： ' : '属性１タイトル' }}</label>
												</td>
												<td>
													<label>  {{ disp.zokusei1ttl ?  disp.zokusei1ttl : '' }}</label>
												</td>
												
											</tr>
											<!-- 30 -->
											<tr  v-if="displayFlg.isPmsk020">
												<td>
													<span class="contents__block-search__label">属性１</span>
												</td>
												<td>
													<text-input class="width-300 txt--align_l" ref="zokusei1" v-model="form.zok01"
														v-bind:name="'属性１(半角)'" v-bind:maxLength="20" v-bind:textType="TEXTINPUT_TYPE.HALF"
														v-bind:tabindex="'12'" />
												</td>
											</tr>
											<!-- 31 -->
											<tr  v-if="displayFlg.isPmsk020" class="lable_height">
												<td >
													<label ref="zokusei3ttl" class="">  {{ disp.zokusei3ttl ? '属性３タイトル： ' : '属性３タイトル' }}</label>
												</td>
												<td>
													<label>  {{ disp.zokusei3ttl ?  disp.zokusei3ttl : '' }}</label>
												</td>
											</tr>
											<!-- 32 -->
											<tr  v-if="displayFlg.isPmsk020">
												<td>
													<span class="contents__block-search__label">属性２</span>
												</td>
												<td>
													<text-input class="width-300 txt--align_l" ref="zokusei2" v-model="form.zok02"
														v-bind:name="'属性2(半角)'"  v-bind:maxLength="20"
														v-bind:textType="TEXTINPUT_TYPE.HALF" v-bind:tabindex="'13'" />
												</td>
											</tr>
											<!-- 33 -->
											<tr  v-if="displayFlg.isPmsk020" class="lable_height">
												<td >
													<label ref="zokusei3ttl" class="">  {{ disp.zokusei3ttl ? '属性３タイトル： ' : '属性３タイトル' }}</label>
												</td>
												<td>
													<label>  {{ disp.zokusei3ttl ?  disp.zokusei3ttl : '' }}</label>
												</td>
											</tr>
											<!-- 34 -->
											<tr  v-if="displayFlg.isPmsk020">
												<td>
													<span class="contents__block-search__label">属性３</span>
												</td>
												<td>
													<text-input class="width-300 txt--align_l" ref="zokusei3" v-model="form.zok03"
														v-bind:name="'属性3(半角)'"  v-bind:maxLength="20"
														v-bind:textType="TEXTINPUT_TYPE.HALF" v-bind:tabindex="'14'" />
												</td>
											</tr>
											<!-- 35 -->
											<tr  v-if="displayFlg.isPmsk020" class="lable_height">
												<td >
													<label ref="zokusei4ttl" class="">  {{ disp.zokusei4ttl ? '属性４タイトル： ' : '属性４タイトル' }}</label>
												</td>
												<td>
													<label>  {{ disp.zokusei4ttl ?  disp.zokusei4ttl : '' }}</label>
												</td>
											</tr>
											<!-- 36 -->
											<tr  v-if="displayFlg.isPmsk020">
												<td>
													<span class="contents__block-search__label">属性４</span>
												</td>
												<td>
													<text-input class="width-300 txt--align_l" ref="zokusei4" v-model="form.zok04"
														v-bind:name="'属性4(半角)'"  v-bind:maxLength="20"
														v-bind:textType="TEXTINPUT_TYPE.HALF" v-bind:tabindex="'15'" />
												</td>
											</tr>
											<!-- 37 -->
											<tr  v-if="displayFlg.isPmsk020" class="lable_height">
												<td >
													<label ref="zokusei5ttl" class="">  {{ disp.zokusei5ttl ? '属性５タイトル： ' : '属性５タイトル' }}</label>
												</td>
												<td>
													<label>  {{ disp.zokusei5ttl ?  disp.zokusei5ttl : '' }}</label>
												</td>
											</tr>
											<!-- 38 -->
											<tr  v-if="displayFlg.isPmsk020">
												<td>
													<span class="contents__block-search__label">属性５</span>
												</td>
												<td>
													<text-input class="width-300 txt--align_l" ref="zokusei5" v-model="form.zok05"
														v-bind:name="'属性5(半角)'"  v-bind:maxLength="20"
														v-bind:textType="TEXTINPUT_TYPE.HALF" v-bind:tabindex="'16'" />
												</td>
											</tr>
											<!--39  -->
											<tr  v-if="displayFlg.isPmsk020" class="lable_height">
												<td >
													<label ref="zokusei6ttl" class="">  {{ disp.zokusei6ttl ? '属性６タイトル： ' : '属性６タイトル' }}</label>
												</td>
												<td>
													<label>  {{ disp.zokusei6ttl ?  disp.zokusei6ttl : '' }}</label>
												</td>
											</tr>
											<!-- 40 -->
											<tr  v-if="displayFlg.isPmsk020">
												<td>
													<span class="contents__block-search__label">属性６</span>
												</td>
												<td>
													<text-input class="width-300 txt--align_l" ref="zokusei6" v-model="form.zok06"
														v-bind:name="'属性6(半角)'"  v-bind:maxLength="20"
														v-bind:textType="TEXTINPUT_TYPE.HALF" v-bind:tabindex="'17'" />
												</td>
											</tr>
											<!-- 41 -->
											<tr  v-if="displayFlg.isPmsk020" class="lable_height">
												<td >
													<label ref="zokusei7ttl" class="">  {{ disp.zokusei7ttl ? '属性７タイトル： ' : '属性７タイトル' }}</label>
												</td>
												<td>
													<label>  {{ disp.zokusei7ttl ?  disp.zokusei7ttl : '' }}</label>
												</td>
											</tr>
											<!-- 42 -->
											<tr  v-if="displayFlg.isPmsk020">
												<td>
													<span class="contents__block-search__label">属性７</span>
												</td> 
												<td>
													<text-input class="width-300 txt--align_l" ref="zokusei7" v-model="form.zok07"
														v-bind:name="'属性7(半角)'"  v-bind:maxLength="20"
														v-bind:textType="TEXTINPUT_TYPE.HALF" v-bind:tabindex="'18'" />
												</td>
											</tr>
											<!-- 43 -->
											<tr  v-if="displayFlg.isPmsk020" class="lable_height">
												<td >
													<label ref="zokusei8ttl" class="">  {{ disp.zokusei8ttl ? '属性８タイトル： ' : '属性８タイトル' }}</label>
												</td>
												<td>
													<label>  {{ disp.zokusei8ttl ?  disp.zokusei8ttl : '' }}</label>
												</td>
											</tr>
											<!-- 44-->
											<tr  v-if="displayFlg.isPmsk020">
												<td>
													<span class="contents__block-search__label">属性８</span>
												</td>
												<td>
													<text-input class="width-300 txt--align_l" ref="zokusei8" v-model="form.zok08"
														v-bind:name="'属性8(半角)'"  v-bind:maxLength="20"
														v-bind:textType="TEXTINPUT_TYPE.HALF" v-bind:tabindex="'19'" />
												</td>
											</tr>
											<!-- 45 -->
											<tr  v-if="displayFlg.isPmsk020" class="lable_height">
												<td >
													<label ref="zokusei9ttl" class="">  {{ disp.zokusei9ttl ? '属性９タイトル： ' : '属性９タイトル' }}</label>
												</td>
												<td>
													<label>  {{ disp.zokusei9ttl ?  disp.zokusei9ttl : '' }}</label>
												</td>
											</tr>
											<!-- 46 -->
											<tr  v-if="displayFlg.isPmsk020">
												<td>
													<span class="contents__block-search__label">属性９</span>
												</td>
												<td>
													<text-input class="width-300 txt--align_l" ref="zokusei9" v-model="form.zok09"
														v-bind:name="'属性9(半角)'"  v-bind:maxLength="20"
														v-bind:textType="TEXTINPUT_TYPE.HALF" v-bind:tabindex="'20'" />
												</td>
											</tr>
											<!--47  -->
											<tr  v-if="displayFlg.isPmsk020" class="lable_height">
												<td >
													<label ref="zokusei10ttl" class="">  {{ disp.zokusei10ttl ? '属性１０タイトル： ' : '属性１０タイトル' }}</label>
												</td>
												<td>
													<label>  {{ disp.zokusei10ttl ?  disp.zokusei10ttl : '' }}</label>
												</td>
											</tr>
											<!-- 48 -->
											<tr  v-if="displayFlg.isPmsk020">
												<td>
													<span class="contents__block-search__label">属性１０</span>
												</td>
												<td>
													<text-input class="width-300 txt--align_l" ref="zokusei10" v-model="form.zok10"
														v-bind:name="'属性10(半角)'"  v-bind:maxLength="20"
														v-bind:textType="TEXTINPUT_TYPE.HALF" v-bind:tabindex="'21'" />
												</td>
											</tr>
											<!-- 49 -->
											<tr  v-if="displayFlg.isPmsk020" class="lable_height">
												<td >
													<label ref="zokusei11ttl" class="">  {{ disp.zokusei11ttl ? '属性１１タイトル： ' : '属性１１タイトル' }}</label>
												</td>
												<td>
													<label>  {{ disp.zokusei11ttl ?  disp.zokusei11ttl : '' }}</label>
												</td>
											</tr>
											<!-- 50 -->
											<tr  v-if="displayFlg.isPmsk020">
												<td>
													<span class="contents__block-search__label">属性１１</span>
												</td>
												<td>
													<text-input class="width-300 txt--align_l" ref="zokusei11" v-model="form.zok11"
														v-bind:name="'属性11(半角)'"  v-bind:maxLength="20"
														v-bind:textType="TEXTINPUT_TYPE.HALF" v-bind:tabindex="'22'" />
												</td>
											</tr>
											<!-- 51 -->
											<tr  v-if="displayFlg.isPmsk020" class="lable_height">
												<td >
													<label ref="zokusei12ttl" class="">  {{ disp.zokusei12ttl ? '属性１２タイトル： ' : '属性１２タイトル' }}</label>
												</td>
												<td>
													<label>  {{ disp.zokusei12ttl ?  disp.zokusei12ttl : '' }}</label>
												</td>
											</tr>
											<!-- 52 -->
											<tr  v-if="displayFlg.isPmsk020">
												<td>
													<span class="contents__block-search__label">属性１２</span>
												</td>
												<td>
													<text-input class="width-300 txt--align_l" ref="zokusei12" v-model="form.zok12"
														v-bind:name="'属性12(半角)'"  v-bind:maxLength="20"
														v-bind:textType="TEXTINPUT_TYPE.HALF" v-bind:tabindex="'23'" />
												</td>
											</tr>
											<!-- 53 -->
											<tr  v-if="displayFlg.isPmsk020" class="lable_height">
												<td >
													<label ref="zokusei13ttl" class="">  {{ disp.zokusei13ttl ? '属性１３タイトル： ' : '属性１３タイトル' }}</label>
												</td>
												<td>
													<label>  {{ disp.zokusei13ttl ?  disp.zokusei13ttl : '' }}</label>
												</td>
											</tr>
											<!-- 54 -->
											<tr  v-if="displayFlg.isPmsk020">
												<td>
													<span class="contents__block-search__label">属性１３</span>
												</td>
												<td>
													<text-input class="width-300 txt--align_l" ref="zokusei13" v-model="form.zok13"
														v-bind:name="'属性13(半角)'"  v-bind:maxLength="20"
														v-bind:textType="TEXTINPUT_TYPE.HALF" v-bind:tabindex="'24'" />
												</td>
											</tr>
											<!-- 55 -->
											<tr  v-if="displayFlg.isPmsk020" class="lable_height">
												<td >
													<label ref="zokusei14ttl" class="">  {{ disp.zokusei14ttl ? '属性１４タイトル： ' : '属性１４タイトル' }}</label>
												</td>
												<td>
													<label>  {{ disp.zokusei14ttl ?  disp.zokusei14ttl : '' }}</label>
												</td>
											</tr>
											<!-- 56 -->
											<tr  v-if="displayFlg.isPmsk020">
												<td>
													<span class="contents__block-search__label">属性１４</span>
												</td>
												<td>
													<text-input class="width-300 txt--align_l" ref="zokusei14" v-model="form.zok14"
														v-bind:name="'属性14(半角)'"  v-bind:maxLength="20"
														v-bind:textType="TEXTINPUT_TYPE.HALF" v-bind:tabindex="'25'" />
												</td>
											</tr>
											<!-- 57 -->
											<tr  v-if="displayFlg.isPmsk020" class="lable_height">
												<td >
													<label ref="zokusei15ttl" class="">  {{ disp.zokusei15ttl ? '属性１５タイトル： ' : '属性１５タイトル' }}</label>
												</td>
												<td>
													<label>  {{ disp.zokusei15ttl ?  disp.zokusei15ttl : '' }}</label>
												</td>
											</tr>
											<!-- 58 -->
											<tr  v-if="displayFlg.isPmsk020">
												<td>
													<span class="contents__block-search__label">属性１５</span>
												</td>
												<td>
													<text-input class="width-300 txt--align_l" ref="zokusei15" v-model="form.zok15"
														v-bind:name="'属性15(半角)'"  v-bind:maxLength="20"
														v-bind:textType="TEXTINPUT_TYPE.HALF" v-bind:tabindex="'26'" />
												</td>
											</tr>
											<!-- 59 -->
											<tr  v-if="displayFlg.isPmsk020" class="lable_height">
												<td >
													<label ref="zokusei16ttl" class="">  {{ disp.zokusei16ttl ? '属性１６タイトル： ' : '属性１６タイトル' }}</label>
												</td>
												<td>
													<label>  {{ disp.zokusei16ttl ?  disp.zokusei16ttl : '' }}</label>
												</td>
											</tr>
											<!-- 60 -->
											<tr  v-if="displayFlg.isPmsk020">
												<td>
													<span class="contents__block-search__label">属性１６</span>
												</td>
												<td>
													<text-input class="width-300 txt--align_l" ref="zokusei16" v-model="form.zok16"
														v-bind:name="'属性16(半角)'"  v-bind:maxLength="20"
														v-bind:textType="TEXTINPUT_TYPE.HALF" v-bind:tabindex="'27'" />
												</td>
											</tr>
										<!-- </div> -->
									</tbody>
								</table>
							</section>
						</div>
					</div>
				</div>
			</div>
		</main>
		<footer>
			<div class="contents__block-command-btn--fullsize">
				<div class="footer-button__block btn-display-change">
					<div class="left-block right-adjustment">
						<div class="button-group">
							<button-input class="contents__btn-blue margin-right-30" v-bind:value="'戻る'" v-bind:name="'backButton'"
								v-bind:tabindex="'28'" v-on:click="clickReturnBtn"
								v-bind:disabled="disabledFlg.backButton"/>
							<button-input class="contents__btn-blue" v-bind:disabledFlg="disabledFlg.cancelButton" v-bind:value="'取消'"
								v-bind:name="'取消ボタン'" v-bind:tabindex="'29'" v-on:click="clickCancelBtn"
								v-bind:disabled="disabledFlg.cancelButton" />
						</div>
					</div>
					<div class="width-70per"></div>
					<div class="right-block">
						<div class="button-group">
							<button-input class="contents__btn-blue" v-bind:disabledFlg="disabledFlg.saveButton" v-bind:value="'保存'"
								v-bind:name="'保存ボタン'" v-bind:tabindex="'30'" v-on:click="clickSaveBtn"
								v-bind:disabled="disabledFlg.saveButton"/> 
						</div>
					</div>
				</div>
			</div>
		</footer>
	</div>
</template>

<script>
import ButtonInput from "@/components/ButtonInput.vue";
import CheckBox from "@/components/CheckBox.vue";
import ErrorMessage from "@/components/ErrorMessage.vue";
import TextInput from "@/components/TextInput.vue";

import {
	co_post,
	co_put,
} from "@/common/ajaxUtility.js";

import { HTTP_STATUS } from "@/const/const.js";
import {
	PATH_PMSK030_KUBUN,
	PATH_PMSK030_ADD_MEISHO,
	PATH_PMSK030_UPDATE_MEISHO,
	PATH_PMSK030_ADD_KUBUN,
	PATH_PMSK030_UPDATE_KUBUN,
	PATH_PMSK030_MEISHO } from "@/const/route.js";

import { co_confirm,co_notice } from "@/common/dialogUtility.js";
import { co_getMessage } from "@/common/messageUtility.js";
import { co_backView } from "@/common/viewUtility.js";
import { PATH_SYSTEMERROR } from '@/const/route.js';

import {
	DIALOG_BUTTON_LIST_KEY,
	TEXTINPUT_TYPE
} from "@/const/const.js";

import {
	STORE_ERROR_CLEAR,
	STORE_VIEW_CHANGED,
	STORE_VIEW_GET_PARAMS,
	STORE_VIEW_HAS_CHANGED,
	STORE_ERROR_IS_ERRFLG,
	STORE_VIEW_PROGRESS,
	STORE_ERROR_ADDMSG,
} from "@/const/store.js";

const CHECK_VALUE_MST={
	CHECK_MEISHO:"1",
	CHECK_KUBUN:"2"
};

export default {
	components: {
		"button-input": ButtonInput,
		"check-box": CheckBox,
		"error-message": ErrorMessage,
		"text-input": TextInput
	},

	data: function () {
		return {
			CHECK_VALUE_MST,
			mstRadio: CHECK_VALUE_MST.CHECK_MEISHO, //ラジオ
			form: {
				reckb: "", // 名称区分
				tblId: "", // 取得元テーブル
				meicd: "", // 名称コード
				delflg: "", // 削除フラグ
				kjnm: "", // 漢字名称１
				knnm: "", // カナ名称１
				kjrm: "", // 漢字略称２
				knrm: "", // カナ略称２
				kjrm3: "", // 漢字名称３
				knrm3: "", // カナ名称３
				zok01: "", // 属性１
				zok02: "", // 属性2
				zok03: "", // 属性3
				zok04: "", // 属性4
				zok05: "", // 属性5
				zok06: "", // 属性6
				zok07: "", // 属性7
				zok08: "", // 属性8
				zok09: "", // 属性9
				zok10: "", // 属性１0
				zok11: "", // 属性１1
				zok12: "", // 属性１2
				zok13: "", // 属性１3
				zok14: "", // 属性１4
				zok15: "", // 属性１5
				zok16: "", // 属性１6
				yobidashi: "", // 呼び出し元画面
				meiCheck:"" // getRadio
			},

			disabledFlg: {
				meishoKubun: true, //名称区分
				meishoKubunNm: true, //名称区分名称
				meishoMst: true, //名称マスタ
				kubunKanriMst: true, //区分管理マスタ
				meishoCd: true, //名称コード
				delFlg: true, //削除フラグ
				name: false, // 漢字名称１ + カナ名称１ + 漢字略称2 + カナ略称2 + 漢字名称3 + カナ名称3
			},

			displayFlg: {
				isPmsk010:false,
				isPmsk020: false,
				meishoKubunNm: true, //名称区分名称
			},
			disp: {
				delFlg: [{ code: "1",name:""}], // List
				reckbNm: "", // 名称区分名称 - meishoKubunNm
				procsShubt: "", // 処理種別
				zokusei1ttl: "", //属性１(半角)タイトル
				zokusei2ttl: "", //属性2(半角)タイトル
				zokusei3ttl: "", //属性3(半角)タイトル
				zokusei4ttl: "", //属性4(半角)タイトル
				zokusei5ttl: "", //属性5(半角)タイトル
				zokusei6ttl: "", //属性6(半角)タイトル
				zokusei7ttl: "", //属性7(半角)タイトル
				zokusei8ttl: "", //属性8(半角)タイトル
				zokusei9ttl: "", //属性9(半角)タイトル
				zokusei10ttl: "", //属性10(半角)タイトル
				zokusei11ttl: "", //属性11(半角)タイトル
				zokusei12ttl: "", //属性12(半角)タイトル
				zokusei13ttl: "", //属性13(半角)タイトル
				zokusei14ttl: "", //属性14(半角)タイトル
				zokusei15ttl: "", //属性15(半角)タイトル
				zokusei16ttl: "" //属性16(半角)タイトル
			},
			TEXTINPUT_TYPE: TEXTINPUT_TYPE,
		};
	},

	created: function () {
		// ** 画面項目初期化 **
		this.getInitData();
	},

	methods: {
		checkValid:function (mode){
			switch(mode){
				case "追加変更名称":
					this.$refs.kanjiMeisho1.validate();
					this.$refs.kanaMeisho1.validate();
					this.$refs.kanjiRyakusho2.validate();
					this.$refs.kanaRyakusho2.validate();
					this.$refs.kanjiMeisho3.validate();
					this.$refs.kanaMeisho3.validate();
					break;
				case "追加変更区分":
					this.$refs.kanjiMeisho1.validate();
					this.$refs.kanaMeisho1.validate();
					this.$refs.kanjiRyakusho2.validate();
					this.$refs.kanaRyakusho2.validate();
					this.$refs.kanjiMeisho3.validate();
					this.$refs.kanaMeisho3.validate();

					this.$refs.zokusei1.validate();
					this.$refs.zokusei2.validate();
					this.$refs.zokusei3.validate();
					this.$refs.zokusei4.validate();
					this.$refs.zokusei5.validate();
					this.$refs.zokusei6.validate();
					this.$refs.zokusei7.validate();
					this.$refs.zokusei8.validate();
					this.$refs.zokusei9.validate();
					this.$refs.zokusei10.validate();
					this.$refs.zokusei11.validate();
					this.$refs.zokusei12.validate();
					this.$refs.zokusei13.validate();
					this.$refs.zokusei14.validate();
					this.$refs.zokusei15.validate();
					this.$refs.zokusei16.validate();
					break;
			}
		},
		controlFlag: function (flg) {
			switch (flg) {
				case "PMSK010追加":
					// 状態制御
					this.disabledFlg.meishoKubun = false;
					this.displayFlg.meishoKubunNm = false;
					this.disabledFlg.meishoMst = false;
					this.disabledFlg.kubunKanriMst = false;
					this.disabledFlg.meishoCd = true;
					this.disabledFlg.delFlg = true;
					this.disabledFlg.name = false;
					// 項目編集制御
					this.form.reckb = "";
					this.disp.reckbNm = "";
					this.form.meicd = "";
					this.form.kjnm = "";
					this.form.knnm = "";
					this.form.kjrm = "";
					this.form.knrm = "";
					this.form.kjrm3 = "";
					this.form.knrm3 = "";
					this.form.zok01 = "";
					this.form.zok02 = "";
					this.form.zok03 = "";
					this.form.zok04 = "";
					this.form.zok05 = "";
					this.form.zok06 = "";
					this.form.zok07 = "";
					this.form.zok08 = "";
					this.form.zok09 = "";
					this.form.zok10 = "";
					this.form.zok11 = "";
					this.form.zok12 = "";
					this.form.zok13 = "";
					this.form.zok14 = "";
					this.form.zok15 = "";
					this.form.zok16 = "";
					break;
				case "PMSK020追加名称マスタ":
					// 状態制御
					this.disabledFlg.meishoKubun = true;
					this.disabledFlg.meishoKubunNm = false;
					this.disabledFlg.meishoMst = true;
					this.disabledFlg.kubunKanriMst = true;
					this.disabledFlg.meishoCd = false;
					this.disabledFlg.delFlg = true;
					this.disabledFlg.name = false;
					// 項目編集制御
					this.form.meicd = "";
					this.form.kjnm = "";
					this.form.knnm = "";
					this.form.kjrm = "";
					this.form.knrm = "";
					this.form.kjrm3 = "";
					this.form.knrm3 = "";
					break;
				case "PMSK020追加名区分管理マスタ":
					// 状態制御
					this.disabledFlg.meishoKubun = true;
					this.disabledFlg.meishoKubunNm = false;
					this.disabledFlg.meishoMst = true;
					this.disabledFlg.kubunKanriMst = true;
					this.disabledFlg.meishoCd = false;
					this.disabledFlg.delFlg = true;
					this.disabledFlg.name = false;
					break;
				case "PMSK020変更":
					// 状態制御
					this.disabledFlg.meishoKubun = true;
					this.disabledFlg.meishoKubunNm = false;
					this.disabledFlg.meishoMst = true;
					this.disabledFlg.kubunKanriMst = true;
					this.disabledFlg.meishoCd = true;
					this.disabledFlg.name = false;
					break;
			}
		},
		clickCheckDel:function(){
			if(this.form.delflg == "0"||  this.form.delflg == "" ){
				this.form.delflg= "1"
			}else{
				this.form.delflg = "0"
			}
		},
	
		// 処理種別追加
		getDataP020WithProcsShubt1 :function( reckb, res,self){ 
			// 名称マスタか区分管理マスタ判断
			let meiKuCheck= res.data.meiCheck;
			if (meiKuCheck == "1") {
				self.mstRadio= CHECK_VALUE_MST.CHECK_MEISHO,
				self.displayFlg.isPmsk010 = false;
				self.displayFlg.isPmsk020 = false;
				self.controlFlag("PMSK020追加名称マスタ");
				self.form.reckb= reckb;
					co_post(
					PATH_PMSK030_KUBUN,
					{
						"reckb": "00",
						"meicd": reckb,
						"delflg": "0"
					},
					function (resK020AddMeiAndDel) {
						if (resK020AddMeiAndDel.status == HTTP_STATUS.OK) {
							self.disp.reckbNm = resK020AddMeiAndDel.data.kjnm;
							
						}
					}
					);
			} else {
				self.getDataP020WithProcsShubt1Kubun( reckb ,self);
			}
		},

		getDataP020WithProcsShubt1Kubun : function( reckb,self){
			self.mstRadio= CHECK_VALUE_MST.CHECK_KUBUN,
			self.displayFlg.isPmsk010 = false;
			self.displayFlg.isPmsk020 = true;
			self.controlFlag("PMSK020追加名区分管理マスタ");
			self.form.reckb = reckb;
			// 
			co_post(
					PATH_PMSK030_KUBUN,
					{
						"reckb": "00",
						"meicd": reckb,
						"delflg": "0"
					},
					function (resK020AddKuAndDel) {
						if (resK020AddKuAndDel.status == HTTP_STATUS.OK) {
							self.disp.reckbNm = resK020AddKuAndDel.data.kjnm;
							//タイトル
							self.disp.zokusei1ttl =resK020AddKuAndDel.data ?.zok01 ?? ""
							self.disp.zokusei2ttl =resK020AddKuAndDel.data ?.zok02 ?? ""
							self.disp.zokusei3ttl =resK020AddKuAndDel.data ?.zok03 ?? ""
							self.disp.zokusei4ttl =resK020AddKuAndDel.data ?.zok04 ?? ""
							self.disp.zokusei5ttl =resK020AddKuAndDel.data ?.zok05 ?? ""
							self.disp.zokusei6ttl =resK020AddKuAndDel.data ?.zok06 ?? ""
							self.disp.zokusei7ttl =resK020AddKuAndDel.data ?.zok07 ?? ""
							self.disp.zokusei8ttl =resK020AddKuAndDel.data ?.zok08 ?? ""
							self.disp.zokusei9ttl =resK020AddKuAndDel.data ?.zok09 ?? ""
							self.disp.zokusei10ttl =resK020AddKuAndDel.data ?.zok10 ?? ""
							self.disp.zokusei11ttl =resK020AddKuAndDel.data ?.zok11 ?? ""
							self.disp.zokusei12ttl =resK020AddKuAndDel.data ?.zok12 ?? ""
							self.disp.zokusei13ttl =resK020AddKuAndDel.data ?.zok13 ?? ""
							self.disp.zokusei14ttl =resK020AddKuAndDel.data ?.zok14 ?? ""
							self.disp.zokusei15ttl =resK020AddKuAndDel.data ?.zok15 ?? ""
							self.disp.zokusei16ttl =resK020AddKuAndDel.data ?.zok16 ?? ""
						}
					}
			);
			
		},
		// 処理種別変更
		getDataP020WithProcsShubt2 :function( reckb,meicd , res,self){
			// 名称マスタか区分管理マスタ判断
			let meiKuCheck= res.data.meiCheck;
			if (meicd != null && meicd != "") {
				let hasdata = false;
				//データを存在チェック
				self.form.reckb = reckb;
				self.form.meicd = meicd;
				co_post(
					PATH_PMSK030_MEISHO,
					self.form,
					function (res020Meiv) {
						if (res020Meiv.status == HTTP_STATUS.OK) {
							if (res020Meiv.data !="") {
								hasdata = true;
								if (meiKuCheck =="1" && hasdata) {
									//処理対象マスタ
									if (res020Meiv.data.tblId == "MSTMEISM") {
											self.getDataP020WithTableMSTMEISM(reckb,meicd ,self,res020Meiv);
									}else{
											self.getDataP020WithTableMSKKBNKR(reckb,meicd ,self,res020Meiv);
									}
								} else if (meiKuCheck == "2" && hasdata) {
									//処理対象区分マスタ
									if (res020Meiv.data.tblId == "MSKKBNKR") {
										self.getDataP020WithTableMSKKBNKR(reckb,meicd ,self,res020Meiv);
									}else{
										self.getDataP020WithTableMSTMEISM(reckb,meicd ,self,res020Meiv);
									}
								}
							} else {
								self.$store.commit(STORE_ERROR_ADDMSG,co_getMessage("CMS0020E","名称コード","名称／区分管理"));
							}
						}
					}
				);
			}else {
				self.$router.push({ path: PATH_SYSTEMERROR });
			}
		},

		// 処理種別変更名称
		getDataP020WithTableMSTMEISM: function(reckb,meicd ,self,res020Meiv){
			self.mstRadio= CHECK_VALUE_MST.CHECK_MEISHO,
			self.displayFlg.isPmsk010 = false;
			self.displayFlg.isPmsk020 = false;
			self.controlFlag("PMSK020変更");
			self.form.reckb = reckb;
			self.form.meicd = meicd
			co_post(
				PATH_PMSK030_KUBUN,
				{
					"reckb": "00",
					"meicd": reckb,
					"delflg": "0"
				},
				function (resK020UpDateMeiAndDel) {
					if (resK020UpDateMeiAndDel.status == HTTP_STATUS.OK) {
						self.disp.reckbNm = resK020UpDateMeiAndDel.data.kjnm;
					}
				}
			);
			self.form.delflg = res020Meiv.data.delflg;
			if (res020Meiv.data.delflg == "0"){
				self.disabledFlg.delFlg = true;
			}else{
				self.disabledFlg.delFlg = false;
			}
			self.form.kjnm = res020Meiv.data.kjnm;
			self.form.knnm = res020Meiv.data.knnm;
			self.form.kjrm = res020Meiv.data.kjrm;
			self.form.knrm = res020Meiv.data.knrm;
			self.form.kjrm3 = res020Meiv.data.kjrm3;
			self.form.knrm3 = res020Meiv.data.knrm3;
		},
		
		// 処理種別変更区分
		getDataP020WithTableMSKKBNKR: function(reckb,meicd ,self,res020Meiv){
			// 表示
			self.mstRadio= CHECK_VALUE_MST.CHECK_KUBUN,
			self.displayFlg.isPmsk010 = false;
			self.displayFlg.isPmsk020 = true;
			self.controlFlag("PMSK020変更")
			self.form.reckb = reckb;
			self.form.meicd = meicd
			self.disp.reckbNm= reckb;
			// レコード区分＝"00"
			co_post(
				PATH_PMSK030_KUBUN,
				{
						"reckb": "00",
						"meicd": reckb,
						"delflg": "0"
				},
				function (res020UpDateKuAndDel) {
					if (res020UpDateKuAndDel.status == HTTP_STATUS.OK) {
						self.disp.reckbNm = res020UpDateKuAndDel.data.kjnm;
						Object.assign(self.disp, {
							zokusei1ttl: res020UpDateKuAndDel.data?.zok01 ?? "",
							zokusei2ttl: res020UpDateKuAndDel.data?.zok02 ?? "",
							zokusei3ttl: res020UpDateKuAndDel.data?.zok03 ?? "",
							zokusei4ttl: res020UpDateKuAndDel.data?.zok04 ?? "",
							zokusei5ttl: res020UpDateKuAndDel.data?.zok05 ?? "",
							zokusei6ttl: res020UpDateKuAndDel.data?.zok06 ?? "",
							zokusei7ttl: res020UpDateKuAndDel.data?.zok07 ?? "",
							zokusei8ttl: res020UpDateKuAndDel.data?.zok08 ?? "",
							zokusei9ttl: res020UpDateKuAndDel.data?.zok09 ?? "",
							zokusei10ttl: res020UpDateKuAndDel.data?.zok10 ?? "",
							zokusei11ttl: res020UpDateKuAndDel.data?.zok11 ?? "",
							zokusei12ttl: res020UpDateKuAndDel.data?.zok12 ?? "",
							zokusei13ttl: res020UpDateKuAndDel.data?.zok13 ?? "",
							zokusei14ttl: res020UpDateKuAndDel.data?.zok14 ?? "",
							zokusei15ttl: res020UpDateKuAndDel.data?.zok15 ?? "",
							zokusei16ttl: res020UpDateKuAndDel.data?.zok16 ?? "",
						});
					}
				}
			);
			const res020UpDateKuAndNotDelCopy = { ...res020Meiv.data };
			delete res020UpDateKuAndNotDelCopy.meicd;
			delete res020UpDateKuAndNotDelCopy.reckb;
			Object.assign(self.form,res020UpDateKuAndNotDelCopy)
			if (res020UpDateKuAndNotDelCopy.delflg == "0"){
				self.disabledFlg.delFlg = true;
			}else{
				self.disabledFlg.delFlg = false;
			}
		},
		// 初期表示データ取得
		getInitData: function () {
			// 前のページから渡されたデータを取得する
			const beforeParam = this.$store.getters[STORE_VIEW_GET_PARAMS]("PMSK030")
			if(!beforeParam){
				this.$router.push({ path: PATH_SYSTEMERROR });
			}
			let id = beforeParam.id;
			let procsShubt =beforeParam.procsShubt;
			let reckb = beforeParam.reckb;
			let meicd = beforeParam.meishoCd;
			if ( id=="" || procsShubt=="" ||reckb == "") {
				// システムエラーを発生し、以降の処理を中断する。
				this.$router.push({ path: PATH_SYSTEMERROR });
			} else {
				if (id == "PMSK010") {
					if (procsShubt == "1") {
						// 表示
						this.mstRadio= CHECK_VALUE_MST.CHECK_MEISHO,
						this.displayFlg.isPmsk010 = true;
						this.displayFlg.isPmsk020 = false;
						this.controlFlag("PMSK010追加");
					} else {
						//システムエラーを発生し、処理を終了する。
						this.$router.push({ path: PATH_SYSTEMERROR });
					}
				} else if (id == "PMSK020") {
					let self = this;
					co_post(PATH_PMSK030_KUBUN,{"reckb":"00", "meicd":reckb},function (res) {
						if (res.status == HTTP_STATUS.OK) {
							//処理種別追加
							if (procsShubt == "1") {
								// 処理対象マスタ
								self.getDataP020WithProcsShubt1(reckb,res,self);

							} else if (procsShubt == "2") {
								self.getDataP020WithProcsShubt2(reckb,meicd, res, self)
							} else {
								//  システムエラーを発生し、処理を終了する。追加か変更以外場合
								self.$router.push({ path: PATH_SYSTEMERROR });
							}
						}
					});
				} else {
					// システムエラーを発生し、以降の処理を中断する。
					self.$router.push({ path: PATH_SYSTEMERROR });
				}
			}
		},
		//保存処理種別追加
		saveWithProcsShubt1: function(self,resku020){
			self.$store.commit(STORE_ERROR_CLEAR);
			let meiKuCheck= resku020.data.meiCheck;
			// 名称マスタ場合
			if(meiKuCheck =="1"){
				// ** チェック処理 **
				self.$refs.meishoCd.validate();
				self.checkValid("追加変更名称");
				if (self.$store.getters[STORE_ERROR_IS_ERRFLG]) {
						self.$store.commit(STORE_VIEW_PROGRESS,false);
						return;
				}else{
					co_put(PATH_PMSK030_ADD_MEISHO, self.form, function(resMeiAdd){
						if(resMeiAdd.status== HTTP_STATUS.CREATED){
							// 状態管理(ストア)の画面表示にプログレスバーの非表示
							self.$store.commit(STORE_VIEW_PROGRESS, false);
							//  完了メッセージのダイアログを表示し、処理を終了する。
							co_notice(co_getMessage("CMS0001I"));
							return;
						}else{
							self.$store.commit(STORE_VIEW_PROGRESS, false);
							// エラー表示
							self.$store.commit(STORE_ERROR_ADDMSG,co_getMessage("CMS0034E","名称区分"));
						}
					})

				}
				
			}else{
				// ** チェック処理 ** 
				self.$refs.meishoCd.validate();
				self.checkValid("追加変更区分");
				if (self.$store.getters[STORE_ERROR_IS_ERRFLG]) {
						self.$store.commit(STORE_VIEW_PROGRESS,false);
						return;
				}else{
					co_put(PATH_PMSK030_ADD_KUBUN, self.form, function(resKuAdd){
						if(resKuAdd.status== HTTP_STATUS.OK){
							// 状態管理(ストア)の画面表示にプログレスバーの非表示
							self.$store.commit(STORE_VIEW_PROGRESS, false);
							//  完了メッセージのダイアログを表示し、処理を終了する。
							co_notice(co_getMessage("CMS0001I"));
							return;
						}else{
							self.$store.commit(STORE_VIEW_PROGRESS, false);
							self.$store.commit(STORE_ERROR_ADDMSG,co_getMessage("CMS0034E","名称区分"));
						}
					})
				}
			}
		},
		//保存処理種別変更
		saveWithProcsShubt2: function(self,resku020){
			self.$store.commit(STORE_ERROR_CLEAR);
			let meiKuCheck= resku020.data.meiCheck;
			// ** チェック処理 **
			if (meiKuCheck== "1"){
				if (self.mstRadio =="1"){
					self.mstRadio= CHECK_VALUE_MST.CHECK_MEISHO,
					self.checkValid("追加変更名称");
					if (self.$store.getters[STORE_ERROR_IS_ERRFLG]) {
							self.$store.commit(STORE_VIEW_PROGRESS,false);
							return;
					}else {
						co_put(PATH_PMSK030_UPDATE_MEISHO, self.form, function(resMeiUpdate){
							if(resMeiUpdate.status== HTTP_STATUS.OK){
								// 状態管理(ストア)の画面表示にプログレスバーの非表示
								self.$store.commit(STORE_VIEW_PROGRESS, false);
								co_notice(co_getMessage("CMS0001I"));
								return;
							}else{
								self.$store.commit(STORE_VIEW_PROGRESS, false);
								self.$store.commit(STORE_ERROR_ADDMSG,co_getMessage("CMS0034E","名称区分"));
							}
						})
					}
				}else{
						// ** チェック処理 **
					self.mstRadio= CHECK_VALUE_MST.CHECK_KUBUN,
					self.checkValid("追加変更区分");
					if (self.$store.getters[STORE_ERROR_IS_ERRFLG]) {
							self.$store.commit(STORE_VIEW_PROGRESS,false);
							return;
					}else {
						co_put(PATH_PMSK030_UPDATE_KUBUN, self.form, function(resKuUpdate){
							if(resKuUpdate.status== HTTP_STATUS.OK){
								// 状態管理(ストア)の画面表示にプログレスバーの非表示
								self.$store.commit(STORE_VIEW_PROGRESS, false);
								co_notice(co_getMessage("CMS0001I"));
								return;
							}else{
								self.$store.commit(STORE_VIEW_PROGRESS, false);
								self.$store.commit(STORE_ERROR_ADDMSG,co_getMessage("CMS0034E","名称区分"));
							}
						})
					}
				}
			}else{
				if (self.mstRadio =="1"){
					self.mstRadio= CHECK_VALUE_MST.CHECK_MEISHO,
					self.checkValid("追加変更名称");
					if (self.$store.getters[STORE_ERROR_IS_ERRFLG]) {
							self.$store.commit(STORE_VIEW_PROGRESS,false);
							return;
					}else {
						co_put(PATH_PMSK030_UPDATE_MEISHO, self.form, function(resMeiUpdate){
							if(resMeiUpdate.status== HTTP_STATUS.OK){
								// 状態管理(ストア)の画面表示にプログレスバーの非表示
								self.$store.commit(STORE_VIEW_PROGRESS, false);
								co_notice(co_getMessage("CMS0001I"));
								return;
							}else{
								self.$store.commit(STORE_VIEW_PROGRESS, false);
								self.$store.commit(STORE_ERROR_ADDMSG,co_getMessage("CMS0034E","名称区分"));
							}
						})
					}
				}else{
						// ** チェック処理 **
					self.mstRadio= CHECK_VALUE_MST.CHECK_KUBUN,
					self.checkValid("追加変更区分");
					if (self.$store.getters[STORE_ERROR_IS_ERRFLG]) {
							self.$store.commit(STORE_VIEW_PROGRESS,false);
							return;
					}else {
						co_put(PATH_PMSK030_UPDATE_KUBUN, self.form, function(resKuUpdate){
							if(resKuUpdate.status== HTTP_STATUS.OK){
								// 状態管理(ストア)の画面表示にプログレスバーの非表示
								self.$store.commit(STORE_VIEW_PROGRESS, false);
								co_notice(co_getMessage("CMS0001I"));
								return;
							}else{
								self.$store.commit(STORE_VIEW_PROGRESS, false);
								self.$store.commit(STORE_ERROR_ADDMSG,co_getMessage("CMS0034E","名称区分"));
							}
						})
					}
				}
			}
		},
		// click - 戻るボタンクリック
		clickReturnBtn: function () {
			// 画面項目変更の場合
			if (this.$store.getters[STORE_VIEW_HAS_CHANGED]) {
				// 確認ダイアログを表示
				let self = this;

				co_confirm(co_getMessage("CMS0002W"),function (btnValue) {
					if (btnValue == DIALOG_BUTTON_LIST_KEY.OK) {
						co_backView();
						// 確認ダイアログで「いいえ」
					} else if (btnValue == DIALOG_BUTTON_LIST_KEY.CANCEL) {
						self.$store.commit(STORE_VIEW_PROGRESS,false);
						return;
					}
				});
			} else {
				co_backView();
			}
		},
		
		// click - 取消ボタンクリック
		clickCancelBtn:  function () {
			// 状態管理(ストア)の画面表示にプログレスバーの表示を設定する。
			const beforeParam = this.$store.getters[STORE_VIEW_GET_PARAMS]("PMSK030")
			let id = beforeParam.id;
			let procsShubt = beforeParam.procsShubt
			let reckb = beforeParam.reckb
			let meicd = beforeParam.meishoCd
			this.form.reckb = reckb;
			this.form.meicd = meicd;
			this.$store.commit(STORE_ERROR_CLEAR);
			this.$store.commit(STORE_VIEW_PROGRESS,true);
			// 画面の入力内容の変更の有無をチェックする - 変更が有る場合
			if (this.$store.getters[STORE_VIEW_HAS_CHANGED]) {
				let self = this;
				co_confirm(co_getMessage("CMS0003W"),function (btnValue) {
					//確認ダイアログで「いいえ」押下の場合
					if (btnValue == DIALOG_BUTTON_LIST_KEY.CANCEL) {
						self.$store.commit(STORE_VIEW_PROGRESS,false);
						return;
					}
					// 確認ダイアログで「はい」押下の場合
					if (btnValue == DIALOG_BUTTON_LIST_KEY.OK) {
						//パラメータ「画面ID」の値に応じて処理する。- 画面IDが「PMSK010(区分一覧)」の場合
						if (id =="PMSK010") {
								self.mstRadio= CHECK_VALUE_MST.CHECK_MEISHO,
								self.displayFlg.isPmsk010 = true;
								self.displayFlg.isPmsk020 = false;
								self.controlFlag("PMSK010追加");
						}
						// 上記以外の場合
						else {
							co_post(PATH_PMSK030_KUBUN,{"reckb":"00", "meicd":reckb},function (res) {
								if (res.status == HTTP_STATUS.OK) {
									//処理種別追加
									if (procsShubt == "1") {
										if (self.mstRadio =="1") {
											self.mstRadio= CHECK_VALUE_MST.CHECK_MEISHO,
											self.displayFlg.isPmsk010 = false;
											self.displayFlg.isPmsk020 = false;
											// self.form.meicd = "";
											self.form.kjnm = "";
											self.form.knnm = "";
											self.form.kjrm = "";
											self.form.knrm = "";
											self.form.kjrm3 = "";
											self.form.knrm3 = "";
											self.controlFlag("PMKS020追加名称マスタ")
											co_post(
												PATH_PMSK030_KUBUN,{"reckb": "00","meicd": reckb,"delflg": "0"},
												function (resK020AddMeiAndDel) {
													if (resK020AddMeiAndDel.status == HTTP_STATUS.OK) {
														self.disp.reckbNm = resK020AddMeiAndDel.data.kjnm;
													}
												}
												);
										} else {
											self.form.meicd = "";
											self.form.kjnm = "";
											self.form.knnm = "";
											self.form.kjrm = "";
											self.form.knrm = "";
											self.form.kjrm3 = "";
											self.form.knrm3 = "";
											self.form.zok01 = "";
											self.form.zok02 = "";
											self.form.zok03 = "";
											self.form.zok04 = "";
											self.form.zok05 = "";
											self.form.zok06 = "";
											self.form.zok07 = "";
											self.form.zok08 = "";
											self.form.zok09 = "";
											self.form.zok10 = "";
											self.form.zok11 = "";
											self.form.zok12 = "";
											self.form.zok13 = "";
											self.form.zok14 = "";
											self.form.zok15 = "";
											self.form.zok16 = "";
											self.getDataP020WithProcsShubt1Kubun( reckb ,self);
										}
										// 処理対象マスタ
										self.getDataP020WithProcsShubt1(reckb, res,self);
									} else if (procsShubt == "2") {
										self.getDataP020WithProcsShubt2(reckb,meicd, res, self)
									} 
								}
							});
						}
						// 状態管理(ストア)の画面表示にプログレスバーの非表示を設定する。
						self.$store.commit(STORE_VIEW_PROGRESS,);
						return;
					}
				});
			}
		},

		//click -保存ボタンクリック
		clickSaveBtn: function () {
			// 状態管理(ストア)の画面表示にプログレスバーの表示を設定する。
			this.$store.commit(STORE_ERROR_CLEAR);
			this.$store.commit(STORE_VIEW_PROGRESS,true);
			const beforeParam = this.$store.getters[STORE_VIEW_GET_PARAMS]("PMSK030")
			let id = beforeParam.id;
			let procsShubt = beforeParam.procsShubt
			let reckb = beforeParam.reckb
			// let meicd = beforeParam.meishoCd
			this.form.yobidashi= id;
			let self = this
			if (id == "PMSK010") {
				this.checkValid("追加変更区分");
				if (this.$store.getters[STORE_ERROR_IS_ERRFLG]) {
					this.$store.commit(STORE_VIEW_PROGRESS,false);
					return;
				}
				co_post(PATH_PMSK030_KUBUN,{"reckb":"00","meicd":this.form.reckb},function (res) {
					if (res.status == HTTP_STATUS.OK) {
						// 取得したデータが存在する
						if (res.data!= "" && res.data.tblId !="") {
							// 取得したデータの削除フラグ="1"
							if (res.data.delflg == "1") {
								// 区分管理マスタ更新
								self.form.yobidashi= id
								co_put(PATH_PMSK030_UPDATE_KUBUN, self.form, function(resKuUpdate){
									if(resKuUpdate.status== HTTP_STATUS.OK){
										// 状態管理(ストア)の画面表示にプログレスバーの非表示
										self.$store.commit(STORE_VIEW_PROGRESS, false);
										// 完了メッセージのダイアログを表示し、処理を終了する。
										co_notice(co_getMessage("CMS0001I"));
										return;
									}else{
											// 状態管理(ストア)の画面表示にプログレスバーの非表示
											self.$store.commit(STORE_VIEW_PROGRESS, false);
										self.$store.commit(STORE_ERROR_ADDMSG,co_getMessage("CMS0034E","名称区分"));
									}
								})
							}
							//上記以外の場合
							else {
								// 状態管理(ストア)の画面表示にプログレスバーの非表示
								self.$store.commit(STORE_VIEW_PROGRESS,false );
								// エラーメッセージを設定し、処理を終了する
								self.$store.commit(STORE_ERROR_ADDMSG,co_getMessage("CMS0034E","名称区分"));
								return;
							}
						}
						//＜上記以外の場合＞PMSK030entity フランス
						else {
							// 区分管理マスタ登録 
							co_put(PATH_PMSK030_ADD_KUBUN, self.form, function(resKuAdd){
									if(resKuAdd.status== HTTP_STATUS.OK){
										// 状態管理(ストア)の画面表示にプログレスバーの非表示
										self.$store.commit(STORE_VIEW_PROGRESS, false);
										//  完了メッセージのダイアログを表示し、処理を終了する。
										co_notice(co_getMessage("CMS0001I"));
										return;
									}else{
											// 状態管理(ストア)の画面表示にプログレスバーの非表示
										self.$store.commit(STORE_VIEW_PROGRESS, false);
										self.$store.commit(STORE_ERROR_ADDMSG,co_getMessage("CMS0034E","名称区分"));
									}
							})
						}
					}
				})
			}
			//上記以外の場合 PMSK020
			else {
				co_post(PATH_PMSK030_KUBUN,{"reckb":"00","meicd":reckb,"delflg":"0"},function (resku020) {
					if (resku020.status == HTTP_STATUS.OK) {
						if (resku020.data == "") {
							// 状態管理(ストア)の画面表示にプログレスバーの非表示
							self.$store.commit(STORE_VIEW_PROGRESS,false);
							//  システムエラーを発生し、以降の処理を中断する。
							self.$router.push({ path: PATH_SYSTEMERROR });
						} else {
							self.form.reckb =reckb ;
							self.$refs.meishoCd.validate();
							self.$refs.kanjiMeisho1.validate();
							self.$refs.kanaMeisho1.validate();
							if (self.$store.getters[STORE_ERROR_IS_ERRFLG]) {
								self.$store.commit(STORE_VIEW_PROGRESS,false);
									return;
							}
							// 名称マスタ(VIEW TABLE)取得
							co_post(PATH_PMSK030_MEISHO,self.form,function (recMei) {
								if (recMei.status == HTTP_STATUS.OK) {
									// 取得したデータが存在する
									if (recMei.data!= "" ) {
										// 変更場合
										if (procsShubt == "2") {
											self.saveWithProcsShubt2(self,resku020);
										}
										else{
											// 状態管理(ストア)の画面表示にプログレスバーの非表示
											self.$store.commit(STORE_VIEW_PROGRESS, false);
												// 完了メッセージのダイアログを表示し、処理を終了する。
											self.$store.commit(STORE_ERROR_ADDMSG,co_getMessage("CMS0034E","名称コード"));
											return;
										}
									}
									// データブランク追加場合
									else{
										if(procsShubt=="1"){
											self.saveWithProcsShubt1(self,resku020);
										}else{
												// 状態管理(ストア)の画面表示にプログレスバーの非表示
											self.$store.commit(STORE_VIEW_PROGRESS, false);
												// 完了メッセージのダイアログを表示し、処理を終了する。
											self.$store.commit(STORE_ERROR_ADDMSG,co_getMessage("CMS0034E","名称コード","名称／区分管理"));
											return;
										}
									}
								}
							})
						}
					}
				}
				)
			}
		}
	},

	watch: {
		// formオブジェクト内のすべての変更を監視する
		form: {
			handler: function () {
				this.$store.commit(STORE_VIEW_CHANGED,true);
			},
			deep: true
		}
	}
};
</script>

<style type="text/css">
.contents__body{
	height: calc(104vh - var(--contents__block-header-height) - var(--contents__block-footer-height) - var(--contents__block-error-height, 0px));
	overflow-y: auto;
}
.display_flex {
	display: flex;
	align-items: center;
}

.background--white {
	background-color: white !important;
}

#PMSK030 .cond__checkbox label[data-v-1ac2b216]::before { 
	margin-top: -11px !important;
	margin-left: 30px !important;
}
#PMSK030 .checkbox:checked
  + .checkbox-label[data-v-1ac2b216]::after {
  top: -7px !important;
  left: 12px !important;
}

.btn-display-change {
	display: flex;
}

.right-adjustment {
	margin-right: 7%;
}

.margin-right-30 {
	margin-right: 30px;
}

.tbl-item--border-row_exist select {
	display: inline;
}

.tbl-item--border-row_exist .contents--error-item {
	border: 2px solid #dc8585;
}
.lable_height{
	height: 53px;
}
</style>
