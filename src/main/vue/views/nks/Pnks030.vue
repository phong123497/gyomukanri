<!--
//================================================================================
// ID: PNKS030
// ProjectName: コープ九州事業連合様ホストシステムリプレイス
// SystemName: 業務管理
// VueName: Pnks030
//
// <<Modification History>>
// Version| Date	 | Updated By		 | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/10 | ＡＭＣ			 | 新規作成
//================================================================================
 -->
<template>
	<div id="PNKS030">
		<!-- メインコンテンツ -->
		<main >
			<div class="contents__block--exist_return">
				<h1 class="header__block-title-with-input">
					<table>
						<colgroup>
							<col style="width: 200px;">
							<col style="width: 1330px;">
						</colgroup>
						<tbody>
							<tr>
								<td>農産企画情報入力</td>
								<td><info-message /></td>
							</tr>
						</tbody>
					</table>
				</h1>
				<div class="contents__wrap--scroll_vertical">
				<!-- エラーメッセージ -->
				<div id="error">
					<error-message />
				</div>
					<div class="contents__block-search">
						<div class="contents__block-search__main">
							<div id="E210Search" class="display--flex">
								<div class="tbl-list__wrap">
									<table class="tbl-list--transparent width-380">
										<tbody>
											<tr>
												<td class="width-80 txt--align_r"><span
													class="contents__block-search__label">企画番号</span>
												</td>
												<td class="width-110">
													<span class="contents__block-search__label">
														{{ form.cqkikano }}
													</span>
												</td>
												<td class="width-80 txt--align_r">
													<span class="contents__block-search__label">共同購入商品コード</span>
												</td>
												<td class="width-110">
													<span class="contents__block-search__label">
														{{ form.kydKonyShinCd }}
													</span>
												</td>
											</tr>
											<tr>
												<td class="width-80 txt--align_r">
													<span class="contents__block-search__label">
														商品名
													</span>
												</td>
												<td>
													<span class="contents__block-search__label">
														{{ backData.shinNm }}
													</span>
												</td>
											</tr>
											<tr>
												<td class="width-80 txt--align_r">
													<span class="contents__block-search__label">
														規格
													</span>
												</td>
												<td class="width-220">
													<span class="contents__block-search__label">
														{{ backData.stdd }}
													</span>
												</td>
											</tr>
											<tr>
												<td class="width-80 txt--align_r">
													<span class="contents__block-search__label">
														納品時容器
													</span>
												</td>
												<td class="width-180">
													<list-box class="width-180" 
														ref="delvJiYokiKbn" 
														v-bind:disabledFlg="disabledFlg"
														v-bind:listItems="delvJiYokiKbnList"
														v-bind:name="'納品時容器区分'"
														v-bind:tabindex="'1'" 
														v-model="backData.delvJiYokiKbn">
													</list-box>
													<span class="contents__block-search__label margin-left-50px">シール貼有</span>
													<div class="contents__block-checkbox">
														<check-box
															ref="sealPasteUmuKbn"
															style="display: none !important;"
															v-bind:checked="backData.sealPasteUmuKbn == '1'"
															v-bind:cdContentType="CD_CONTENT_TYPE.CONTENT"
															v-bind:disabledFlg="disabledFlg"
															v-bind:listItems="checkboxlistItems"
															v-bind:name="'シール貼付有無区分'"
															v-bind:tabindex="'2'"
															v-model="backData.sealPasteUmuKbn"/>
													</div>
												</td>
											</tr>
											<tr>
												<td class="width-80 txt--align_r">
													<span class="contents__block-search__label">資材名</span>
												</td>
												<td colspan="2">
													<text-input 
														class="width-550 txt--align_l" 
														ref="shizaiNm"
														v-bind:disabledFlg="disabledFlg"
														v-bind:maxLength="20"
														v-bind:name="'資材名'"
														v-bind:tabindex="'3'" 
														v-bind:textType="TEXTINPUT_TYPE.FULL" 
														v-model="backData.shizaiNm" />
												</td>
											</tr>
										</tbody>
									</table>
									<input type="hidden" v-model="shohziRate">
									<input type="hidden" v-model="tananoCalcKbn">
									<input type="hidden" v-model="zeinukiCalcKbn">
									<input type="hidden" v-model="zeikomiCalcKbn">
								</div>
								<div class="tbl-list__wrap">
									<table class="tbl-list--border-all_exist overflow-h">
										<thead>
											<tr>
												<th class="background"></th>
												<th class="width-100">SKU</th>
												<th class="width-100">計画点数</th>
												<th class="width-100">CQ 供給額</th>
												<th class="width-100">CQ GPR</th>
												<th class="width-100">ｴﾌ 供給額</th>
												<th class="width-100">ｴﾌ GPR</th>
											</tr>
										</thead>
										<tbody>
											<tr  v-for="(item) in backData.shohinList" v-bind:key="item">
												<td class="txt--align_c">{{ item.bmnNm }}</td>
												<td class="txt--align_c">{{ item.sku }}</td>
												<td class="txt--align_r"><span>{{ item.planPoint }}</span></td>
												<td class="txt--align_r"><span>{{ item.cqSuplyGaku }}</span></td>
												<td class="txt--align_c">{{ item.cqGrp }}</td>
												<td class="txt--align_r"><span>{{ item.fcpSuplyGaku }}</span></td>
												<td class="txt--align_c">{{ item.fcpGrp }}</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
					<div class="contents__view--bg_gray">
						<div id="E210MList" class="tbl-list__wrap">
							<table style="width:1840px !important"
								class="tbl-list--border-all_exist tbl-sticky-head wrap--overflow_hidden">
								<colgroup >
									<col style="width: 37px" />
									<col style="width: 150px" />
									<col style="width: 145px" />
									<col style="width: 247px" />
									<col style="width: 50px" />
									<col style="width: 78px" />
									<col style="width: 106px" />
									<col style="width: 86px" />
									<col style="width: 106px" />
									<col style="width: 86px" />
									<col style="width: 106px" />
									<col style="width: 106px" />
									<col style="width: 106px" />
									<col style="width: 106px" />
									<col style="width: 106px" />
									<col style="width: 75px" />
									<col style="width: 75px" />
									<col style="width: 65px" />
								</colgroup>
								<thead>
									<tr>
										<th>No</th>
										<th>バッチ</th>
										<th>メーカー</th>
										<th>産地<br>等階級
										</th>
										<th>加工</th>
										<th>特価</th>
										<th>税込価格</th>
										<th>本体価格</th>
										<th>供給単価</th>
										<th>仕入単価</th>
										<th>原料単価</th>
										<th>加工単価</th>
										<th>資材単価</th>
										<th>委託単価</th>
										<th>計画数</th>
										<th>CQ<br>GPR
										</th>
										<th>会員<br>GPR
										</th>
										<th></th>
									</tr>
								</thead>
							</table>
							<table class="tbl-list--border-all_exist wrap--overflow_hidden" style="margin-top: -2px;table-layout:fixed;!important;width:1840px !important">
								<colgroup>
									<col style="width: 37px" />
									<col style="width: 150px" />
									<col style="width: 145px" />
									<col style="width: 247px" />
									<col style="width: 50px" />
									<col style="width: 78px" />
									<col style="width: 106px" />
									<col style="width: 86px" />
									<col style="width: 106px" />
									<col style="width: 86px" />
									<col style="width: 106px" />
									<col style="width: 106px" />
									<col style="width: 106px" />
									<col style="width: 106px" />
									<col style="width: 106px" />
									<col style="width: 75px" />
									<col style="width: 75px" />
									<col style="width: 65px" />
								</colgroup>
								<tbody>
									<tr v-for="(item, index) in backData.shohinInfoList" v-bind:key="index">
										<td class="border--top_none"><span>{{ index + 1 }}</span></td>
										<td class="font-size-16px border--top_none">
											<span>{{ item.batchNm }}</span>
										</td>
										<td class="border--top_none"
											style="white-space: nowrap;overflow: hidden;">
											<list-box 
												class="font-size-16px select--meisai"
												v-bind:cdContentType="CD_CONTENT_TYPE.SHORTENED_NAME"
												v-bind:disabledFlg="disabledFlg"
												v-bind:listItems="backData.makerCboxList"
												v-bind:name="'メーカー'"
												v-bind:ref="'maker' + String((index + 1) * 1000 + 1)"
												v-bind:tabindex="String((index + 1) * 1000 + 1)"
												v-on:change="onchgMarker(index)"
												v-model="item.maker" >
											</list-box>
											<br />
											<div class="div_meisai	font-size-16px ellipsis" v-bind:ref="makerNm" v-bind:title="item.makerNm">
												<span>{{ item.makerNm }}</span>
											</div>
										</td>
										<td class="td--pad-small border--top_none">
											<list-box
												class="font-size-16px width-230" 
												v-bind:ref="'santiCd' + index"
												v-bind:cdContentType="CD_CONTENT_TYPE.SHORTENED_NAME"
												v-bind:disabledFlg="disabledFlg"
												v-bind:listItems="item.santiDataList"
												v-bind:name="'産地'"  
												v-bind:tabindex="String((index + 1) * 1000 + 2)"
												v-model="item.santiCd">
											</list-box>
											<br />
											<list-box 
												class="font-size-16px width-150"
												v-bind:ref="'touClass' + index"
												v-bind:cdContentType="CD_CONTENT_TYPE.CONTENT"
												v-bind:disabledFlg="disabledFlg"
												v-bind:listItems="item.tokaikyDataList"
												v-bind:name="'等階級'"
												v-bind:tabindex="String((index + 1) * 1000 + 3)"
												v-model="item.touClassCd" >
											</list-box>
										</td>
										<td class="border--top_none">
											<div class="contents__block-checkbox checkbox--margin-l-10">
												<check-box
													style="display: none !important;"
													v-bind:checked="item.kako == '1'"
													v-bind:cdContentType="CD_CONTENT_TYPE.SHORTENED_NAME"
													v-bind:disabled="disabledFlg"
													v-bind:listItems="checkboxlistItems"
													v-bind:name="'加工' + String(index)"
													v-bind:ref="'kikakb' + index"
													v-bind:tabindex="String((index + 1) * 1000 + 4)"
													v-model="item.kako"/>
											</div>
										</td>
										<td class="txt--align_c	td--pad-small border--top_none fontsizeLess">
											<list-box 
												class="width-65"
												v-bind:disabledFlg="disabledFlg"
												v-bind:listItems="weekTokkaList"
												v-bind:name="'特価'"
												v-bind:ref="'tokka' + index"
												v-bind:tabindex="String((index + 1) * 1000 + 5)"
												v-model="item.tokka">
											</list-box>
										</td>
										<td class="txt--align_r	td--pad-small border--top_none">
											<number-input
												class="width-100 txt--align_r my-component-class"
												v-bind:class="{ 'highlight': changeHighlight('zeikomiKakaku', index) }"
												v-bind:disabledFlg="disabledFlg"
												v-bind:maxLength="7"
												v-bind:name="'税込み価格'" 
												v-bind:ref="'zeikomiKakaku' + index"
												v-bind:tabindex="String((index + 1) * 1000 + 6)"
												v-on:change="onchgKakutanChng('zeikomiKakaku', index)"
												v-model="item.zeikomiKakaku">
											</number-input>
										</td>
										<td class="txt--align_r	border--top_none" v-bind:ref="hontaiKakaku">
											<span>{{ item.hontaiKakakuString }}</span>
										</td>
										<td class="txt--align_r	td--pad-small border--top_none">
											<number-input
												class="width-100 txt--align_r"
												v-bind:class="{ 'highlight': changeHighlight('suplyTan', index) }"
												v-bind:disabledFlg="disabledFlg"
												v-bind:name="'供給単価'" 
												v-bind:maxLength="7"
												v-bind:ref="'suplyTan' + index"
												v-bind:tabindex="String((index + 1) * 1000 + 7)"
												v-on:change="onchgKakutanChng('suplyTan', index)"
												v-model="item.suplyTan">
											</number-input>
										</td>
										<td class="txt--align_r	border--top_none" v-bind:ref="shiireTan">
											<span>{{ item.shiireTanString }}</span>
										</td>
										<td class="txt--align_r	td--pad-small border--top_none">
											<number-input 
												class="width-100 txt--align_r"
												v-bind:disabledFlg="disabledFlg"
												v-bind:name="'原料単価'" 
												v-bind:maxLength="7"
												v-bind:ref="'genryoTan' + index"
												v-bind:tabindex="String((index + 1) * 1000 + 8)"
												v-on:change="onchgKakutanChng('genryoTan', index)"
												v-model="item.genryoTan">
											</number-input>
										</td>
										<td class="txt--align_r	td--pad-small border--top_none">
											<number-input 
												class="width-100 txt--align_r"
												v-bind:disabledFlg="disabledFlg"
												v-bind:name="'加工単価'" 
												v-bind:maxLength="7"
												v-bind:ref="'kakoTan' + index"
												v-bind:tabindex="String((index + 1) * 1000 + 9)"
												v-on:change="onchgKakutanChng('kakoTan', index)"
												v-model="item.kakoTan">
											</number-input>
										</td>
										<td class="txt--align_r	td--pad-small border--top_none">
											<number-input 
												class="width-100 txt--align_r"
												v-bind:disabledFlg="disabledFlg"
												v-bind:name="'資材単価'" 
												v-bind:maxLength="7"
												v-bind:ref="'shizaiTan' + index"
												v-bind:tabindex="String((index + 1) * 1000 + 10)"
												v-on:change="onchgKakutanChng('shizaiTan', index)"
												v-model="item.shizaiTan">
											</number-input>
										</td>
										<td class="txt--align_r	td--pad-small border--top_none">
											<number-input
												class="width-100 txt--align_r"
												v-bind:disabledFlg="disabledFlg"
												v-bind:name="'委託単価'" 
												v-bind:maxLength="7"
												v-bind:ref="'itakTan' + index"
												v-bind:tabindex="String((index + 1) * 1000 + 11)"
												v-on:change="onchgKakutanChng('itakTan', index)"
												v-model="item.itakTan">
											</number-input>
										</td>
										<td class="txt--align_r	td--pad-small border--top_none">
											<number-input 
												class="width-100 txt--align_r"
												v-bind:disabledFlg="disabledFlg"
												v-bind:name="'計画数'"
												v-bind:maxLength="7"
												v-bind:ref="'planSu' + index"
												v-bind:tabindex="String((index + 1) * 1000 + 12)"
												v-model="item.planSu">
											</number-input>
										</td>
										<td class="font-size-16px txt--align_c border--top_none"
											ref="cqGrp">{{ item.cqGrp }}</td>
										<td class="font-size-16px txt--align_c border--top_none"
											ref="kaiinGrp">{{ item.kaiinGrp }}</td>
										<td class="td--pad-small border--top_none">
										<button-input
											class="contents__btn-blue-small"
											v-bind:disabled="disabledFlg"
											v-bind:name="'backButton'"
											v-bind:tabindex="String((index + 1) * 1000 + 13)"
											v-bind:value="'選択'"
											v-on:click="clickSelectBtn(index)" />
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</main>
			<div id="NSKC0130sub" class="modal" v-show="showNSKC0130sub">
				<div class="modal__bg"></div>
				<div class="modal__contents">
					<h4 class="modal__header">農産企画情報バッチ情報入力</h4>
					<hr />
					<div id="poperror">
					<error-message
						v-bind:popupFlg="true" />
					</div>
					<div class="modal__body">
					<div class="contents__block-search__main">
						<div class="tbl-list__wrap tbl-input">
						<table>
							<colgroup>
							<col style="width: 110px" />
							<col style="width: 670px" />
							</colgroup>
							<tbody>
							<tr>
								<td class="width-100 txt--align_r">メーカー</td>
								<td>
								<list-box
									class="select--meisai margin-left-10px"
									ref="maker"
									v-bind:cdContentType="CD_CONTENT_TYPE.SHORTENED_NAME"
									v-bind:listItems="backData.makerCboxList"
									v-bind:name="'メーカー'"
									v-bind:tabindex="1"
									v-on:change="onchgMarkerPopup()"
									v-model="dialogData.maker">
								</list-box>
								<span v-bind:ref="makerNm">
									{{ dialogData.makerNm }}
								</span>
								</td>
							</tr>
							<tr>
								<td class="width-100 txt--align_r">産地</td>
								<td>
								<list-box
									class="width-600 margin-left-10px"
									ref="santiCd"
									v-bind:cdContentType="CD_CONTENT_TYPE.SHORTENED_NAME"
									v-bind:listItems="dialogData.santiDataList"
									v-bind:name="'産地'"
									v-bind:tabindex="2"
									v-model="dialogData.santiCd">
								</list-box>
								</td>
							</tr>
							<tr>
								<td class="width-100 txt--align_r">等階級</td>
								<td>
								<list-box
									class="width-150 margin-left-10px"
									ref="touClass"
									v-bind:cdContentType="CD_CONTENT_TYPE.CONTENT"
									v-bind:listItems="dialogData.tokaikyDataList"
									v-bind:name="'等階級'"
									v-bind:tabindex="3"
									v-model="dialogData.touClassCd">
								</list-box>
								</td>
							</tr>
							<tr>
								<td class="width-100 txt--align_r">
									加工区分
								</td>
								<td>
								<list-box
									class="width-150 margin-left-10px"
									ref="kakoKbn"
									style="margin-right:100px;"
									v-bind:listItems="kakoKbnList"
									v-bind:name="'加工区分'"
									v-bind:tabindex="'4'"
									v-model="dialogData.kako">
								</list-box>
										週・特価
								<list-box
									class="width-130 margin-left-10px"
									ref="weekTokka"
									v-bind:listItems="weekTokkaList"
									v-bind:name="'週・特価'"
									v-bind:tabindex="'5'"
									v-model="dialogData.tokka">
								</list-box>
								</td>
							</tr>
							<tr></tr>
							</tbody>
						</table>
						<table>
							<colgroup>
							<col style="width: 110px" />
							<col style="width: 120px" />
							</colgroup>
							<tbody>
							<tr>
								<td class="txt--align_r">
									原料単価
								</td>
								<td>
								<number-input
									class="width-120 txt--align_r margin-left-10px"
									v-bind:name="'原料単価'"
									v-bind:maxLength="7"
									v-bind:ref="genryoTan"
									v-bind:tabindex="'6'"
									v-on:change="onchgKakutanChngPopup('genryoTan')"
									v-model="dialogData.genryoTan">
								</number-input>
								</td>
							</tr>
							<tr>
								<td class="txt--align_r">加工単価</td>
								<td>
								<number-input
									class="width-120 txt--align_r margin-left-10px"
									v-bind:name="'加工単価'"
									v-bind:maxLength="7"
									v-bind:ref="kakoTan"
									v-bind:tabindex="'9'"
									v-on:change="onchgKakutanChngPopup('kakoTan')"
									v-model="dialogData.kakoTan">
								</number-input>
								</td>
							</tr>
							<tr>
								<td class="txt--align_r">資材単価</td>
								<td>
								<number-input
									class="width-120 txt--align_r margin-left-10px"
									v-bind:name="'資材単価'"
									v-bind:maxLength="7"
									v-bind:ref="shizaiTan"
									v-bind:tabindex="'10'"
									v-on:change="onchgKakutanChngPopup('shizaiTan')"
									v-model="dialogData.shizaiTan">
								</number-input>
								</td>
							</tr>
							<tr>
								<td class="txt--align_r">委託単価</td>
								<td>
								<number-input
									class="width-120 txt--align_r margin-left-10px"
									v-bind:name="'委託単価'"
									v-bind:maxLength="7"
									v-bind:ref="itakTan"
									v-bind:tabindex="'12'"
									v-on:change="onchgKakutanChngPopup('itakTan')"
									v-model="dialogData.itakTan">
								</number-input>
								</td>
							</tr>
							<tr>
								<td class="txt--align_r">仕入単価</td>
								<td class="txt--align_r">
								<label class="margin-right-tanka margin-left-10px" v-bind:ref="shiireTan">
									{{ dialogData.shiireTanString }}
								</label>
								</td>
							</tr>
							</tbody>
						</table>
						<table>
							<colgroup>
							<col style="width: 110px" />
							<col style="width: 120px" />
							</colgroup>
							<tbody>
							<tr>
								<td class="txt--align_r">税込組価</td>
								<td>
								<number-input
									class="width-120 txt--align_r margin-left-10px"
									v-bind:class="{ 'highlight': changeHighlightPopup('zeikomiKakaku', 0) }"
									v-bind:name="'税込組価'"
									v-bind:maxLength="7"
									v-bind:ref="zeikomiKumika"
									v-bind:tabindex="'7'"
									v-on:change="onchgKakutanChngPopup('zeikomiKakaku')"
									v-model="dialogData.zeikomiKakaku">
								</number-input>
								</td>
							</tr>
							<tr>
								<td class="txt--align_r">税抜組価</td>
								<td class="txt--align_r">
								<label class="margin-right-tanka margin-left-10px" v-bind:ref="zeinukiKumika">
									{{ dialogData.hontaiKakakuString }}
								</label>
								</td>
							</tr>
							<tr>
								<td class="txt--align_r">供給単価</td>
								<td>
								<number-input
									class="width-120 txt--align_r margin-left-10px"
									v-bind:class="{ 'highlight': changeHighlightPopup('suplyTan', 0) }"
									v-bind:name="'供給単価'"
									v-bind:maxLength="7"
									v-bind:ref="suplyTan"
									v-bind:tabindex="'11'"
									v-on:change="onchgKakutanChngPopup('suplyTan')"
									v-model="dialogData.suplyTan">
								</number-input>
								</td>
							</tr>
							<tr></tr>
							<tr></tr>
							</tbody>
						</table>
						<table>
							<colgroup>
							<col style="width: 110px" />
							<col style="width: 100px" />
							</colgroup>
							<tbody>
							<tr>
								<td class="width-80 txt--align_r">計画総数</td>
								<td>
								<number-input
									class="width-120 txt--align_r margin-left-10px"
									v-bind:name="'計画総数'"
									v-bind:maxLength="7"
									v-bind:ref="planSosu"
									v-bind:tabindex="'8'"
									v-model="dialogData.planSu">
								</number-input>
								</td>
							</tr>
							<tr></tr>
							<tr></tr>
							<tr></tr>
							<tr></tr>
							</tbody>
						</table>
						</div>
						<div class="tbl-select">
						<label class="lbl-select">一括反映先</label>
						<table class="tbl-list--border-all_exist">
							<colgroup>
							<col style="width: 50px" />
							<col style="width: 200px" />
							</colgroup>
							<thead>
							<tr>
								<th>選択</th>
								<th>バッチ</th>
							</tr>
							</thead>
							<tbody>
							<tr v-for="(item, index) in dialogData.iktHanei" 
								v-bind:key="item" style="height: 45px;">
								<td>
								<div class="contents__block-checkbox checkbox--margin-l-10">
									<check-box
										style="display: none !important;"
										v-bind:checked="item.checked == '1'"
										v-bind:listItems="checkboxlistItems"
										v-bind:name="'選択' + String(index)"
										v-bind:ref="'sentaku' + index"
										v-on:click="iktHaneiChecked(index)"
										v-model="item.checked"/>
								</div>
								</td>
								<td v-bind:ref="batch">{{ item.batchNm }}</td>
							</tr>
							</tbody>
						</table>
						</div>
					</div>
					</div>
					<div class="contents__block-command-btn--bg_gray">
					<table width="100%">
						<tr>
						<td class="width-180 txt--align_l">
							<button-input 
								class="contents__btn-blue margin-left-50px"
								v-bind:tabindex="'12'"
								v-bind:value="'閉じる'"
								v-on:click="clickCloseBtn()">
							</button-input>
						</td>
						<td class="width-180 txt--align_l">
							<button-input 
								class="contents__btn-blue"
								v-bind:tabindex="'13'"
								v-bind:value="'取消'"
								v-on:click="clickPopCancelBtn()">
							</button-input>
						</td>
						<td></td>
						<td class="width-180">
							<button-input 
								class="contents__btn-blue margin-left-50px"
								v-bind:tabindex="'9'"
								v-bind:value="'全選択'"
								v-on:click="clickAllSelectBtn()">
							</button-input>
						</td>
						<td class="width-180">
							<button-input 
								class="contents__btn-blue" 
								v-bind:tabindex="'10'"
								v-bind:value="'全解除'"
								v-on:click="clickAllRelBtn()">
							</button-input>
						</td>
						<td class="width-130"></td>
						<td class="width-180 txt--align_r">
							<button-input 
								class="contents__btn-blue margin-right-50px"
								v-bind:tabindex="'11'"
								v-bind:value="'一括反映'"
								v-on:click="clickIktHaneiBtn()">
							</button-input>
						</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
		<footer>
			<div class="contents__block-command-btn--fullsize">
				<div class="footer-button__block">
					<div class="left-block">
						<div class="button-group">
							<button-input
									class="contents__btn-blue"
									v-bind:name="'backButton'"
									v-bind:tabindex="'13004'"
									v-bind:value="'戻る'"
									v-on:click="clickReturnBtn" />
							<button-input
									class="contents__btn-blue"
									v-bind:disabledFlg="disabledFlg"
									v-bind:name="'cancelBtn'"
									v-bind:tabindex="'13005'"
									v-bind:value="'取消'"
									v-on:click="clickCancelBtn" />
						</div>
					</div>
					<div class="center-block">
							<button-input
									class="contents__btn-blue"
									v-bind:disabledFlg="disabledFlg"
									v-bind:name="'bfShinBtn'"
									v-bind:tabindex="'13003'"
									v-bind:value="'前の商品'"
									v-on:click="clickBfShinBtn" />
							<button-input
									class="contents__btn-blue"
									v-bind:disabledFlg="disabledFlg"
									v-bind:name="'nextShinBtn'"
									v-bind:tabindex="'13002'"
									v-bind:value="'次の商品'"
									v-on:click="clickNextShinBtn" />
						<div class="button-group"></div>
					</div>
					<div class="right-block">
						<div class="button-group">
							<button-input
									class="contents__btn-blue"
									v-bind:disabledFlg="disabledFlg"
									v-bind:name="'saveBtn'"
									v-bind:tabindex="'13001'"
									v-bind:value="'保存'"
									v-on:click="clickSaveBtn" />
						</div>
					</div>
				</div>
			</div>
		</footer>
	</div>
</template>

<script>
import ButtonInput from '@/components/ButtonInput.vue';
import CheckBox from '@/components/CheckBox.vue';
import ErrorMessage from '@/components/ErrorMessage.vue';
import InfoMessage from '@/components/InfoMessage.vue';
import ListBox from '@/components/ListBox.vue';
import NumberInput from '@/components/NumberInput.vue';
import TextInput from '@/components/TextInput.vue';


import { 
	co_isEmpty 
} from '@/common/checkUtility.js';

import { 
	co_post, 
	co_put,
	co_setError, 
} from '@/common/ajaxUtility.js'

import { 
	co_confirm 
} from '@/common/dialogUtility.js';

import { 
	co_getMessage 
} from '@/common/messageUtility.js';

import {
	CD_CONTENT_TYPE,
	CNS_NKS_KAKO_KBN,
	CNS_NKS_NOHINYOKI,
	CNS_NKS_TOKUKA_KBN,
	DIALOG_BUTTON_LIST_KEY,
	HTTP_STATUS,
	NUMBERINPUT_TYPE,
	TEXTINPUT_TYPE,
} from '@/const/const.js';

import {
	STORE_DATA_GET_INHERIT_DATA,
	STORE_ERROR_ADDMSG,
	STORE_ERROR_CLEAR,
	STORE_ERROR_IS_ERRFLG,
	STORE_ERROR_SET_POPUP_FLG,
	STORE_INFO_CLEAR,
	STORE_INFO_SET_MSG,
	STORE_VIEW_CHANGED,
	STORE_VIEW_HAS_CHANGED,
	STORE_VIEW_PROGRESS,
	
} from '@/const/store.js'

import {
	PATH_PNKS030,
	PATH_PNKS030_SAVE,
	PATH_PNKS030_SEARCH_GETSANTICBOX,
	PATH_PNKS030_SEARCH_GETBFSHIN,
	PATH_PNKS030_SEARCH_GETTOKAIKYCBOX,
	PATH_PNKS030_SEARCH_GETNEXTSHIN,
} from '@/const/route.js'
	
export default {
	components: {
		'button-input': ButtonInput,
		'check-box': CheckBox,
		'error-message': ErrorMessage,
		'info-message': InfoMessage,
		'list-box': ListBox,
		'number-input': NumberInput,
		'text-input': TextInput,
	},
	data: function() {
		return {
			form:{
				cqkikano:'',			// ＣＱ企画番号
				kydKonyShinCd: '',		// 共同購入商品コード
				kyshincdStart: '',		// 共同購入商品コード開始
				kyshincdEnd: '',		// 共同購入商品コード終了
				makerCd:''				// メーカーコード
			}, 
			dialogData: {
				genryoTan:'',			// 原料単価
				zeikomiKakaku:'',		// 税込み価格
				planSu:0,				// 計画数
				kakoTan:'',				// 加工単価
				hontaiKakaku:'',		// 本体価格
				shizaiTan:'',			// 資材単価
				suplyTan:'',			// 供給単価
				itakTan:'',				// 委託単価
				shiireTan:'',			// 仕入単価
				maker:'',				// メーカー
				makerNm:'',				// メーカー名称
				santiCd:'',				// 産地
				santi:'',				// 産地名称
				touClassCd:'',			// 等階級 
				kako:'',				// 加工
				tokka:'',				// 特価
				tokaikyDataList:[],		// 等階級リスト
				santiDataList: [],		// 産地リスト
				index:'',
				hontaiKakakuString:'',	// 本体価格文字列
				shiireTanString:'',		// 仕入単価リスト
				iktHanei:[]
			},
			delvJiYokiKbnList:[
				// 納品時容器リスト
				{code: CNS_NKS_NOHINYOKI.CNS_NKS_NOHINYOKI_KOTENA, name: CNS_NKS_NOHINYOKI.CNS_NKS_NOHINYOKI_KOTENA_NAME},
				{code: CNS_NKS_NOHINYOKI.CNS_NKS_NOHINYOKI_DANBORU, name: CNS_NKS_NOHINYOKI.CNS_NKS_NOHINYOKI_DANBORU_NAME},
				{code: CNS_NKS_NOHINYOKI.CNS_NKS_NOHINYOKI_EPS, name: CNS_NKS_NOHINYOKI.CNS_NKS_NOHINYOKI_EPS_NAME},
			], 
			kakoKbnList:[
				// 加工区分リスト
				{code: CNS_NKS_KAKO_KBN.CNS_NKS_KAKO_KBN_ARI, name: CNS_NKS_KAKO_KBN.CNS_NKS_KAKO_KBN_ARI_NAME},
				{code: CNS_NKS_KAKO_KBN.CNS_NKS_KAKO_KBN_NASHI, name: CNS_NKS_KAKO_KBN.CNS_NKS_KAKO_KBN_NASHI_NAME},
			], 
			weekTokkaList:[
				// 週・特価リスト
				{code: CNS_NKS_TOKUKA_KBN.CNS_NKS_TOKUKA_KBN_TSUJYO, name: CNS_NKS_TOKUKA_KBN.CNS_NKS_TOKUKA_KBN_TSUJYO_NAME},
				{code: CNS_NKS_TOKUKA_KBN.CNS_NKS_TOKUKA_KBN_TOKUKA, name: CNS_NKS_TOKUKA_KBN.CNS_NKS_TOKUKA_KBN_TOKUKA_NAME},
			], 
			checkboxlistItems:[
				{code: 1, name:'' },
			], 
			TEXTINPUT_TYPE: TEXTINPUT_TYPE,
			iktHanei:[],
			iktHaneiRelSelect: false,
			backData:{},
			CD_CONTENT_TYPE: CD_CONTENT_TYPE,
			NUMBERINPUT_TYPE: NUMBERINPUT_TYPE,
			shohziRate:'',
			kinsokuChkKbn:'',
			zeinukiCalcKbn:'',
			zeikomiCalcKbn:'',
			disabledFlg: false,
			showNSKC0130sub: false,
		}
	},
	created: async function() {
		// 農産企画情報 商品検索画面から引継データ:
		this.form = this.$store.getters[STORE_DATA_GET_INHERIT_DATA](0)
		// プログレスバー表示
		this.$store.commit(STORE_VIEW_PROGRESS, true)
		co_post(PATH_PNKS030, this.form, this.createBackFunction)
	},
	mounted: async function() {
		await this.$nextTick();
		// フォーカス設定
		this.$refs.delvJiYokiKbn.focus()
	},
	watch: {
		backData: {
			handler: function () {
				this.$store.commit(STORE_VIEW_CHANGED, true)
			},
			deep: true
		}
	},
	methods: {
		createBackFunction:function(res){
			// エラーをクリア
			this.$store.commit(STORE_ERROR_CLEAR)
			if (res.status == HTTP_STATUS.OK) {
				const backResData = res.data
				// 伝票ヘッダ部２
				for (let i = 0; i < backResData.shohinList.length; i++){
					backResData.shohinList[i].planPoint = backResData.shohinList[i].planPoint.toLocaleString()
					backResData.shohinList[i].cqSuplyGaku = backResData.shohinList[i].cqSuplyGaku.toLocaleString()
					backResData.shohinList[i].fcpSuplyGaku = backResData.shohinList[i].fcpSuplyGaku.toLocaleString()
					backResData.shohinList[i].cqGrp = (backResData.shohinList[i].cqGrp * 100).toFixed(1) + '%'
					backResData.shohinList[i].fcpGrp = (backResData.shohinList[i].fcpGrp * 100).toFixed(1) + '%'
				}
				// 一覧部データ編集
				for (let z = 0; z < backResData.shohinInfoList.length; z++){
					// 産地リストボックス
					let santiDataList = []
					for (let j = 0; j < backResData.shohinInfoList[z].santiCboxList.length; j++){
						santiDataList.push({
						code: backResData.shohinInfoList[z].santiCboxList[j].santicd,
						ryakuName: backResData.shohinInfoList[z].santiCboxList[j].santikj,
						cdryakuMei: backResData.shohinInfoList[z].santiCboxList[j].santikj
						})
					}
					backResData.shohinInfoList[z].santiDataList = santiDataList
					
					// 等階級リストボックス
					let tokaikyDataList = []
					for (let n = 0; n < backResData.shohinInfoList[z].tokaikyCboxList.length; n++){
						tokaikyDataList.push({
							code: backResData.shohinInfoList[z].tokaikyCboxList[n].tokaikyucd,
							name: backResData.shohinInfoList[z].tokaikyCboxList[n].tokaikyukj
						})
					}
					backResData.shohinInfoList[z].tokaikyDataList = tokaikyDataList
						
					// 編集一括反応先データ
					this.iktHanei.push({
						checked: '0',
						batchNm: backResData.shohinInfoList[z].batchNm
					})
				
					//データ編集
					this.backData = backResData
					this.numberEdit(z)
					backResData.shohinInfoList[z].hontaiKakakuString = this.backData.shohinInfoList[z].hontaiKakaku.toLocaleString();
					backResData.shohinInfoList[z].shiireTanString = this.backData.shohinInfoList[z].shiireTan.toLocaleString();
				}
				let makercdDataList = []
				for (let i = 0; i < res.data.makerCboxList.length; i++) {
					makercdDataList.push({
						code: res.data.makerCboxList[i].makercd,
						ryakuName: res.data.makerCboxList[i].makercd,
						cdryakuMei: res.data.makerCboxList[i].makerkj
					})
				}
				this.form.kydKonyShinCd = res.data.kydKonyShinCd
				this.backData.makerCboxList = makercdDataList;
				this.kinsokuChkKbn = this.backData.kinsokuChkKbn
				this.zeinukiCalcKbn = this.backData.zeinukiCalcKbn
				this.zeikomiCalcKbn = this.backData.zeikomiCalcKbn
				this.shohziRate = this.backData.shohziRate
			} else {
				// 業務エラー表示
				this.$store.commit(STORE_ERROR_SET_POPUP_FLG, false);
				co_setError(res.data, this.$refs)
				this.disabledFlg = true
			}
			// プログレスバー非表示
			this.$store.commit(STORE_VIEW_PROGRESS, false)
			// 編集状態をクリア
			const createEndThis = this;
			this.$nextTick(function() {
				createEndThis.$store.commit(STORE_VIEW_CHANGED, false);
			});
		},
	
		// CQGRP
		numberEdit: function(index){
			const suplyTan = this.backData.shohinInfoList[index].suplyTan;
			const shiireTan = this.backData.shohinInfoList[index].shiireTan;
			// CQGRP  ← （供給単価－仕入単価）÷供給単価×１００％
			this.backData.shohinInfoList[index].cqGrp = (((suplyTan - shiireTan) / (suplyTan === 0 ? 1 : suplyTan)) * 100).toFixed(2) + "%";
			// 会員GRP  ← （税抜組価－供給単価）÷税抜組価×１００％
			const hontaiKakaku = this.backData.shohinInfoList[index].hontaiKakaku;
			this.backData.shohinInfoList[index].kaiinGrp = (((hontaiKakaku - suplyTan) / (hontaiKakaku === 0 ? 1 : hontaiKakaku)) * 100).toFixed(2) + "%";
		},
	
		// 選択ボタンクリック
		clickSelectBtn: function(index){
			this.showNSKC0130sub = true
			this.dialogDataChange(index)
		},
	
		dialogDataChange: function(index){
			const dialogDataChangeThis = this;
			const dialogDataList = [
					"genryoTan",
					"zeikomiKakaku",
					"genryoTan",
					"zeikomiKakaku",
					"planSu",
					"kakoTan",
					"hontaiKakaku",
					"shizaiTan",
					"suplyTan",
					"itakTan",
					"shiireTan",
					"maker",
					"makerNm",
					"santiCd",
					"santiDataList",
					"touClassCd",
					"tokaikyDataList",
					"kako",
					"tokka",
					"hontaiKakakuString",
					"shiireTanString",
					"iktHanei",
					"index",
				];
			for (const name of dialogDataList) {
				dialogDataChangeThis.dialogData[name] = dialogDataChangeThis.backData.shohinInfoList[index][name];
			}
			if(dialogDataChangeThis.dialogData.kako == '0' || dialogDataChangeThis.dialogData.kako === ""){
				dialogDataChangeThis.dialogData.kako = 0
			}else {
				dialogDataChangeThis.dialogData.kako = 1
			}
			dialogDataChangeThis.dialogData.index = index
			dialogDataChangeThis.dialogData.iktHanei = dialogDataChangeThis.iktHanei
			dialogDataChangeThis.dialogData.iktHanei[index].checked = '1'
			dialogDataChangeThis.iktHaneiRelSelect = false
		},
		// 保存ボタンクリック --------------------------------------------------------
		clickSaveBtn: function() {
			// 状態管理(ストア)のエラー情報をクリアする。
			this.$store.commit(STORE_ERROR_CLEAR)
			// MSG クリア
			this.$store.commit(STORE_INFO_CLEAR)
			this.$store.commit(STORE_VIEW_PROGRESS, true);
			this.$store.commit(STORE_ERROR_SET_POPUP_FLG, false);
			// ** チェック処理 **
			this.$refs.delvJiYokiKbn.validate();
			this.$refs.sealPasteUmuKbn.validate();
			this.$refs.shizaiNm.validate();
			// ** エラー表示制御 **
			if (this.$store.getters[STORE_ERROR_IS_ERRFLG]) {
				this.$store.commit(STORE_VIEW_PROGRESS, false);
				return;
			}
			this.$store.commit(STORE_VIEW_PROGRESS, false);
			let messageflg = false
			for (let i = 0; i < this.backData.shohinInfoList.length; i++) {
				let shohinInfo = this.backData.shohinInfoList[i]
				if(shohinInfo.zeikomiKakaku < shohinInfo.suplyTan || shohinInfo.suplyTan < shohinInfo.shiireTan){
					messageflg = true
				}
			}
			if(messageflg){
				co_confirm(co_getMessage('NKS0001W'), this.saveDataFunction)
			} else {
				this.saveDataFunction(DIALOG_BUTTON_LIST_KEY.OK)
			}
		},
	
		saveDataFunction: async function(flg){
			// プログレスバー表示
			if(flg == DIALOG_BUTTON_LIST_KEY.OK) {
				this.$store.commit(STORE_VIEW_PROGRESS, true)
				let backFormData = JSON.parse(JSON.stringify(this.backData))
				delete backFormData.makerCboxList
				delete backFormData.shohinList
				for (let i = 0; i < backFormData.shohinInfoList.length; i++) {
					delete backFormData.shohinInfoList[i].santiDataList
					delete backFormData.shohinInfoList[i].tokaikyDataList
					delete backFormData.shohinInfoList[i].shiireTanString
					delete backFormData.shohinInfoList[i].hontaiKakakuString
					delete backFormData.shohinInfoList[i].cqGrp
					delete backFormData.shohinInfoList[i].kaiinGrp
					if(backFormData.shohinInfoList[i].kako == '0' || backFormData.shohinInfoList[i].kako === ''){
						backFormData.shohinInfoList[i].kako = 0
					} else{
						backFormData.shohinInfoList[i].kako = 1
					}
					if(backFormData.sealPasteUmuKbn == '0' || backFormData.sealPasteUmuKbn === ''){
						backFormData.sealPasteUmuKbn = 0
					}else{
						backFormData.sealPasteUmuKbn = 1
					}
				}
				let self = this
				co_put(PATH_PNKS030_SAVE, backFormData, function(res){
					if (res.status == HTTP_STATUS.OK) {
						// プログレスバー非表示
						self.$store.commit(STORE_VIEW_PROGRESS, false)
						// ** メッセージ設定&表示 ** //
						self.$store.commit(STORE_ERROR_SET_POPUP_FLG, false);
						self.$store.commit(STORE_INFO_SET_MSG, co_getMessage("CMS0001I"));
						// ** 編集クリア **
						self.$store.commit(STORE_VIEW_CHANGED, false);
						self.$store.commit(STORE_VIEW_PROGRESS, false);
						self.createBackFunction(res)
					} else {
						// 業務エラー表示
						self.$store.commit(STORE_ERROR_SET_POPUP_FLG, false);
						co_setError(res.data, self.$refs);
						if (!co_isEmpty(res.data[0].errorItems) && !co_isEmpty(self.$refs[res.data[0].errorItems[0]])) {
							self.$refs['delvJiYokiKbn'].focus();
						} 
					}
					// プログレスバー非表示
					self.$store.commit(STORE_VIEW_PROGRESS, false);
				})
			}
		},
		
		// 次の商品ボタンクリック
		clickNextShinBtn: function() {
			if (this.$store.getters[STORE_VIEW_HAS_CHANGED] == true) {
				co_confirm(co_getMessage('CMS0003W'), this.nextShinFunction)
			} else {
				this.nextShinFunction(DIALOG_BUTTON_LIST_KEY.OK)
			}
		},
		nextShinFunction: async function(flg){
			if (flg == DIALOG_BUTTON_LIST_KEY.OK) {
				// プログレスバー表示
				this.$store.commit(STORE_VIEW_PROGRESS, true)
				this.iktHanei = [];
				const nextThis = this;
				co_post(PATH_PNKS030_SEARCH_GETNEXTSHIN, this.form, function(res){
					if (res.status == HTTP_STATUS.OK) {
						nextThis.createBackFunction(res)
					} else {
						nextThis.$store.commit(STORE_ERROR_SET_POPUP_FLG, false);
						nextThis.$store.commit(STORE_VIEW_PROGRESS, false)
						// 業務エラー表示
						co_setError(res.data, nextThis.$refs);
					}
					// プログレスバー非表示
					nextThis.$store.commit(STORE_VIEW_PROGRESS, false)
				})
			}
		},
		// 前の商品ボタンクリック
		clickBfShinBtn: function() {
			if (this.$store.getters[STORE_VIEW_HAS_CHANGED] == true) {
				co_confirm(co_getMessage('CMS0003W'), this.bfShinFunction)
			} else {
				this.bfShinFunction(DIALOG_BUTTON_LIST_KEY.OK)
			}
		},
		bfShinFunction: async function(flg) {
			if (flg == DIALOG_BUTTON_LIST_KEY.OK) {
				// プログレスバー表示
				this.$store.commit(STORE_VIEW_PROGRESS, true)
				this.iktHanei = [];
				const bfThis = this;
				co_post(PATH_PNKS030_SEARCH_GETBFSHIN, this.form, function(res){
					if (res.status == HTTP_STATUS.OK) {
						bfThis.createBackFunction(res)
					} else {
						bfThis.$store.commit(STORE_ERROR_SET_POPUP_FLG, false);
						bfThis.$store.commit(STORE_VIEW_PROGRESS, false)
						// 業務エラー表示
						co_setError(res.data, bfThis.$refs);
					}
					// プログレスバー非表示
					bfThis.$store.commit(STORE_VIEW_PROGRESS, false)
				})
			}
		},
			
		// 取消ボタンクリック
		clickCancelBtn: function() {
			// エラーをクリア
			this.$store.commit(STORE_ERROR_CLEAR)
			if (this.$store.getters[STORE_VIEW_HAS_CHANGED] == true) {
				co_confirm(co_getMessage('CMS0003W'), this.cancelFunc)
			} else {
				this.cancelFunc(DIALOG_BUTTON_LIST_KEY.OK)
			}
		},
		
		cancelFunc: async function(flg){
			if (flg == DIALOG_BUTTON_LIST_KEY.OK) {
				co_post(PATH_PNKS030, this.form, this.createBackFunction)
			}
		},
	
		// 戻るボタンクリック
		clickReturnBtn:function(){
			if (this.$store.getters[STORE_VIEW_HAS_CHANGED] == true) {
				co_confirm(co_getMessage('CMS0003W'), this.backFunc)
			} else {
				this.backFunc(DIALOG_BUTTON_LIST_KEY.OK)
			}
		},
		
		backFunc: function(flg){
			if (flg == DIALOG_BUTTON_LIST_KEY.OK) {
				// エラーをクリア
				this.$store.commit(STORE_ERROR_CLEAR)
				// 編集状態をクリア
				this.$store.commit(STORE_VIEW_CHANGED, false)
				this.$router.back()
			}
		},
		
		// 一括反映ボタンクリック
		clickIktHaneiBtn: function() {
			// エラーをクリア
			this.$store.commit(STORE_ERROR_CLEAR)
			// ポップアップコンテンツ部.一覧反映先が未選択の場合
			if (this.iktHaneiRelSelect){
				this.$store.commit(STORE_ERROR_SET_POPUP_FLG, true);
				this.$store.commit(STORE_ERROR_ADDMSG, co_getMessage('NKS0013E'))
				return
			}
			let checkData = this.dialogData
			// ポップアップコンテンツ部で入力項が全部未入力の場合
			if(checkData.maker === '' &&
				checkData.kako === '' &&
				checkData.tokka === '' &&
				checkData.genryoTan === '' &&
				checkData.kakoTan === '' &&
				checkData.shizaiTan === '' &&
				checkData.itakTan === '' &&
				checkData.zeikomiKakaku === '' &&
				checkData.suplyTan === '' &&
				checkData.planSu === ''){
				this.$store.commit(STORE_ERROR_SET_POPUP_FLG, true);
				this.$store.commit(STORE_ERROR_ADDMSG, co_getMessage('NKS0001E'))
				return
			}
			
			// バッチ計画係数の合計を取得
			let allPlanSu = 0;
			for (let i = 0;i < checkData.iktHanei.length; i++){
				if(checkData.iktHanei[i].checked === '1'){
					allPlanSu += Number(this.backData.shohinInfoList[i].keikakuKeisu)
				}
			}
			
			let tankaFlg;
			if(checkData.genryoTan === '' && checkData.kakoTan === '' &&
				checkData.shizaiTan === '' && checkData.itakTan === '' ){
				tankaFlg = '0'
			} else {
				tankaFlg = '1'
			}
	
			for (let j = 0; j < this.backData.shohinInfoList.length; j++){
				if (checkData.iktHanei[j].checked === '1'){
					if(checkData.maker != ''){
						let checkMaker = this.backData.shohinInfoList[j].maker
						this.backData.shohinInfoList[j].maker = checkData.maker
						this.backData.shohinInfoList[j].makerNm = checkData.makerNm
						if( checkData.maker == checkMaker ){
							if(checkData.santiCd != ''){
								this.backData.shohinInfoList[j].santiCd = checkData.santiCd
							}
							if(checkData.touClassCd != ''){
								this.backData.shohinInfoList[j].touClassCd = checkData.touClassCd
							}
						} else {
							if(checkData.santiCd != ''){
								this.backData.shohinInfoList[j].santiCd = checkData.santiCd
							} else{
								this.backData.shohinInfoList[j].santiCd = ''
							}
							if(checkData.touClassCd != ''){
								this.backData.shohinInfoList[j].touClassCd = checkData.touClassCd
							} else{
								this.backData.shohinInfoList[j].touClassCd = ''
							}
						}
					}
					if(checkData.kako == '0' || checkData.kako === ''){
						this.backData.shohinInfoList[j].kako = '0'
					}else {
						this.backData.shohinInfoList[j].kako = '1'
					}
					const shohinInfoListHaneiBtn = [
						"tokka",
						"genryoTan",
						"kakoTan",
						"shizaiTan",
						"itakTan",
						"zeikomiKakaku",
						"hontaiKakaku",
						"suplyTan",
						"shiireTan",
						"hontaiKakakuString",
					];
					for (const name of shohinInfoListHaneiBtn) {
						// 未入力の場合一覧の値変更なし 
						if(checkData[name] !== '' && checkData[name] != 0){
							this.backData.shohinInfoList[j][name] = checkData[name]
						}
					}
					
					// 一覧部の計画数を反映する。
					if (checkData.planSu != ''){
						this.backData.shohinInfoList[j].planSu = 
						((Number(checkData.planSu) * Number(this.backData.shohinInfoList[j].keikakuKeisu))
						/ Number(allPlanSu)).toFixed(1)
					}
					
					// 単価再計算
					if ( tankaFlg === '1') {
						this.backData.shohinInfoList[j].suplyTan = Math.floor(
						( Number(this.backData.shohinInfoList[j].genryoTan) + Number(this.backData.shohinInfoList[j].kakoTan) 
							+ Number(this.backData.shohinInfoList[j].shizaiTan) + Number(this.backData.shohinInfoList[j].itakTan) ) * 1.02)
					}
					this.backData.shohinInfoList[j].shiireTan = 
						Number(this.backData.shohinInfoList[j].genryoTan)
						+ Number(this.backData.shohinInfoList[j].kakoTan)
						+ Number(this.backData.shohinInfoList[j].shizaiTan)
						+ Number(this.backData.shohinInfoList[j].itakTan)
					this.backData.shohinInfoList[j].shiireTanString = Number(this.backData.shohinInfoList[j].shiireTan).toLocaleString()
				}
				this.numberEdit(j)
			}
			this.clickCloseBtn();
			this.$store.commit(STORE_INFO_SET_MSG, co_getMessage("CMS0007I" , " 一括反映"));
		},
	
		// 全選択ボタンクリック
		clickAllSelectBtn: function() {
			for (let i = 0; i < this.dialogData.iktHanei.length; i++){
				this.dialogData.iktHanei[i].checked = '1'
			}
			this.iktHaneiRelSelect = false;
		},
		
		// 全解除ボタンクリック
		clickAllRelBtn: function() {
			for (let i = 0; i < this.dialogData.iktHanei.length; i++) {
				this.dialogData.iktHanei[i].checked = '0'
				this.iktHanei[i].checked = '0'
			}
			this.iktHaneiRelSelect = true;
		},
		
		// 取消ボタンクリック(ポップアップ)
		clickPopCancelBtn: function() {
			this.dialogDataChange(this.dialogData.index)
		},
		
		// 閉じるボタンクリック
		clickCloseBtn: function(){
		// エラーをクリア
			this.$store.commit(STORE_ERROR_CLEAR)
			this.clickAllRelBtn()
			this.clickSantiCbox(this.dialogData.index)
			this.clickTokaikyCbox(this.dialogData.index)
			this.showNSKC0130sub = false
		},
	
		// 産地コンボボックスクリック
		clickSantiCbox: async function(index){
			if (index === -1){
				this.form.makerCd = this.dialogData.maker
			} else {
				this.form.makerCd = this.backData.shohinInfoList[index].maker
			}
			// エラーをクリア
			this.$store.commit(STORE_ERROR_CLEAR)
			// プログレスバー表示
			this.$store.commit(STORE_VIEW_PROGRESS, true)
			let self = this
			co_post(PATH_PNKS030_SEARCH_GETSANTICBOX, this.form, function(res){
				if (res.status == HTTP_STATUS.OK) {
					let santiDataList = [];
					for (let n = 0; n < res.data.length; n++){
						santiDataList.push({
							code: res.data[n].santicd,
							ryakuName: res.data[n].santikj,
							cdryakuMei: res.data[n].santikj
						})
					}
					if (index === -1){
						self.dialogData.santiDataList = santiDataList
					} else {
						self.backData.shohinInfoList[index].santiDataList = santiDataList
					}
				} else {
					// 業務エラー表示
					co_setError(res.data, self.$refs)
					if (!co_isEmpty(self.$refs[res.data[0].errorItems[0]])) {
						self.$nextTick(function() {
							self.$refs[res.data[0].errorItems[0]].focus();
						});
					}
				}
				// プログレスバー非表示
				self.$store.commit(STORE_VIEW_PROGRESS, false)
			})
		},
		
		// 等階級コンボボックスクリック
		clickTokaikyCbox: async function(index){
			if (index === -1){
				this.form.makerCd = this.dialogData.maker
			} else {
				this.form.makerCd = this.backData.shohinInfoList[index].maker
			}
			// エラーをクリア
			this.$store.commit(STORE_ERROR_CLEAR)
			// プログレスバー表示
			this.$store.commit(STORE_VIEW_PROGRESS, true)
			let self = this
			co_post(PATH_PNKS030_SEARCH_GETTOKAIKYCBOX, this.form, function(res){
				if (res.status == HTTP_STATUS.OK) {
					let tokaikyDataList = [];
					for(let n = 0; n < res.data.length; n++){
						tokaikyDataList.push({
							code: res.data[n].tokaikyucd,
							name: res.data[n].tokaikyukj
						})
					}
					if (index === -1){
						self.dialogData.tokaikyDataList = tokaikyDataList
					} else {
						self.backData.shohinInfoList[index].tokaikyDataList = tokaikyDataList
					}
				} else {
					// 業務エラー表示
					co_setError(res.data, self.$refs)
					if (!co_isEmpty(self.$refs[res.data[0].errorItems[0]])) {
						self.$nextTick(function() {
							self.$refs[res.data[0].errorItems[0]].focus();
						});
					}
				}
				// プログレスバー非表示
				self.$store.commit(STORE_VIEW_PROGRESS, false)
			})
		},
		
		// 消費税計算
		calculateTax: function(tank, kbn) {
			if (!tank) {
				tank = 0;
			}
			let zeiritsu = this.backData.shohziRate;
			let keikbn;
			let tmpTank;
			if (kbn == "2") {
				keikbn = this.backData.zeikomiCalcKbn;
				tmpTank = parseFloat(tank) * (1 + +zeiritsu);
			} else {
				keikbn = this.backData.zeinukiCalcKbn;
				tmpTank = parseFloat(tank) / (1 + +zeiritsu);
			}
			let taxResult;
			switch (keikbn) {
			case "1":
				taxResult = Math.floor(tmpTank);
				break;
			case "2":
				taxResult = Math.floor(tmpTank + 0.999999999);
				break;
			default:
				taxResult = Math.floor(tmpTank + 0.5);
				break;
			}
			return taxResult;
		},
			
		// 各単価変更オンチェンジ
		onchgKakutanChng: function( changeData, index ){
			// 原料単価がチェンジする場合
			let tanData = this.backData.shohinInfoList[index]
			if( changeData === 'genryoTan' ){
				this.backData.shohinInfoList[index].itakTan = Math.round(tanData.genryoTan*0.02)
				this.backData.shohinInfoList[index].suplyTan = Math.floor(
				(Number(tanData.genryoTan)
				+ Number(tanData.kakoTan)
				+ Number(tanData.shizaiTan)
				+ Number(tanData.itakTan))*1.02
				)
				this.backData.shohinInfoList[index].shiireTan = 
				Number(tanData.genryoTan)
				+ Number(tanData.kakoTan)
				+ Number(tanData.shizaiTan)
				+ Number(tanData.itakTan)
				this.backData.shohinInfoList[index].shiireTanString = this.backData.shohinInfoList[index].shiireTan.toLocaleString();
			}
			// 加工単価チェンジ、資材単価チェンジと委託単価チェンジの場合
			if( changeData === 'kakoTan' || changeData === 'shizaiTan' || changeData === 'itakTan'){
				this.backData.shohinInfoList[index].suplyTan = Math.floor(
				(Number(tanData.genryoTan)
				+ Number(tanData.kakoTan)
				+ Number(tanData.shizaiTan)
				+ Number(tanData.itakTan))*1.02
				)
				this.backData.shohinInfoList[index].shiireTan = 
				Number(tanData.genryoTan)
				+ Number(tanData.kakoTan)
				+ Number(tanData.shizaiTan)
				+ Number(tanData.itakTan)
				if(changeData === 'shizai' || changeData === 'itak'){
					this.backData.shohinInfoList[index].kako = '1'
				}
				this.backData.shohinInfoList[index].shiireTanString = this.backData.shohinInfoList[index].shiireTan.toLocaleString();
			}
		
			// 税込み価格がチェンジする場合
			if( changeData === 'zeikomiKakaku' ) {
				// 本体価格 = 消費税計算部品(一覧.税込み価格,"1")
				this.backData.shohinInfoList[index].hontaiKakaku = this.calculateTax(tanData.zeikomiKakaku, 1);
				this.backData.shohinInfoList[index].hontaiKakakuString = this.backData.shohinInfoList[index].hontaiKakaku.toLocaleString();
			}
			
			// 本体価格がチェンジする場合
			if( changeData === 'hontaiKakaku') {
				// 税込み価格 = 消費税計算部品(一覧.本体価格,"2")
				this.backData.shohinInfoList[index].zeikomiKakaku = this.calculateTax(tanData.hontaiKakaku, 2);
				this.backData.shohinInfoList[index].shiireTanString = this.backData.shohinInfoList[index].shiireTan.toLocaleString();
			}
			
			this.activeRow = index;
			this.activeRowName = changeData;
			const changeColor = this;
			this.$nextTick(function() {
				changeColor.$forceUpdate();
			});
			this.numberEdit(index)
		},
		
		// 各単価変更オンチェンジ(ポップアップ)
		onchgKakutanChngPopup: function(changeData){
			// 原料単価がチェンジする場合
			let tanData = this.dialogData
			if( changeData === 'genryoTan' ){
				this.dialogData.itakTan = Math.floor(tanData.genryoTan*0.02)
				this.dialogData.suplyTan = Math.floor(
				(Number(tanData.genryoTan)
				+ Number(tanData.kakoTan)
				+ Number(tanData.shizaiTan)
				+ Number(tanData.itakTan)) * 1.02
				)
				this.dialogData.shiireTan = 
				Number(tanData.genryoTan)
				+ Number(tanData.kakoTan)
				+ Number(tanData.shizaiTan)
				+ Number(tanData.itakTan)
				this.dialogData.shiireTanString = Number(this.dialogData.shiireTan).toLocaleString()
			}
			
			// 加工単価チェンジ、資材単価チェンジと委託単価チェンジの場合
			if( changeData === 'kakoTan' || changeData === 'shizaiTan' || changeData === 'itakTan'){
				this.dialogData.suplyTan = Math.floor(
				(Number(tanData.genryoTan)
				+ Number(tanData.kakoTan)
				+ Number(tanData.shizaiTan)
				+ Number(tanData.itakTan))*1.02
				)
				this.dialogData.shiireTan = 
				Number(tanData.genryoTan)
				+ Number(tanData.kakoTan)
				+ Number(tanData.shizaiTan)
				+ Number(tanData.itakTan)
				if(changeData === 'shizai' || changeData === 'itak'){
					this.dialogData.kako = '1'
				}
				this.dialogData.shiireTanString = Number(this.dialogData.shiireTan).toLocaleString()
			}
			
			// 税込み価格がチェンジする場合
			if( changeData === 'zeikomiKakaku' ) {
				// 本体価格 = 消費税計算部品(一覧.税込み価格,"1")
				this.dialogData.hontaiKakaku = this.calculateTax(tanData.zeikomiKakaku, 2);
				this.dialogData.hontaiKakakuString = this.dialogData.hontaiKakaku.toLocaleString();
			}
			
			// 本体価格がチェンジする場合
			if( changeData === 'hontaiKakaku') {
				// 税込み価格 = 消費税計算部品(一覧.本体価格,"2")
				this.dialogData.zeikomiKakaku = this.calculateTax(tanData.hontaiKakaku, 1);
				this.dialogData.zeikomiKakakuString = this.dialogData.zeikomiKakaku.toLocaleString();
			}
			this.activeRow = 0;
			this.activeRowName = changeData;
			const changeColorPop = this;
			this.$nextTick(function() {
				changeColorPop.$forceUpdate();
			});
		},
		
		// メーカーオンチェンジ
		onchgMarker: function(index){
			const makerThis = this;
			this.$nextTick(function() {
				if (makerThis.backData.shohinInfoList[index].maker === ''){
					makerThis.backData.shohinInfoList[index].makerNm = ''
				}
				for (let i = 0; i < makerThis.backData.makerCboxList.length; i++) {
					if (makerThis.backData.makerCboxList[i].code === makerThis.backData.shohinInfoList[index].maker) {
						makerThis.backData.shohinInfoList[index].makerNm = makerThis.backData.makerCboxList[i].cdryakuMei
					}
				}
				// エラーをクリア
				this.$store.commit(STORE_ERROR_CLEAR)
				// プログレスバー表示
				this.$store.commit(STORE_VIEW_PROGRESS, true)
				this.form.makerCd = this.backData.shohinInfoList[index].maker
				co_post(PATH_PNKS030_SEARCH_GETSANTICBOX, this.form, function(res){
					if (res.status == HTTP_STATUS.OK) {
						let santiDataList = [];
						for (let n = 0; n < res.data.length; n++){
							santiDataList.push({
								code: res.data[n].santicd,
								ryakuName: res.data[n].santikj,
								cdryakuMei: res.data[n].santikj
							})
						}
							makerThis.backData.shohinInfoList[index].santiDataList = santiDataList
					} else {
						// 業務エラー表示
						co_setError(res.data, makerThis.$refs)
						if (!co_isEmpty(self.$refs[res.data[0].errorItems[0]])) {
							makerThis.$nextTick(function() {
								makerThis.$refs[res.data[0].errorItems[0]].focus();
							});
						}
					}
				})
				co_post(PATH_PNKS030_SEARCH_GETTOKAIKYCBOX, this.form, function(res){
					if (res.status == HTTP_STATUS.OK) {
						let tokaikyDataList = [];
						for(let n = 0; n < res.data.length; n++){
							tokaikyDataList.push({
								code: res.data[n].tokaikyucd,
								name: res.data[n].tokaikyukj
							})
						}
							makerThis.backData.shohinInfoList[index].tokaikyDataList = tokaikyDataList
					} else {
						// 業務エラー表示
						co_setError(res.data, makerThis.$refs)
						if (!co_isEmpty(self.$refs[res.data[0].errorItems[0]])) {
							makerThis.$nextTick(function() {
								makerThis.$refs[res.data[0].errorItems[0]].focus();
							});
						}
					}
				})
				this.backData.shohinInfoList[index].santiCd = ''
				this.backData.shohinInfoList[index].santi = ''
				this.backData.shohinInfoList[index].touClassCd = ''
				this.backData.shohinInfoList[index].touClass = ''
				// プログレスバー非表示
				this.$store.commit(STORE_VIEW_PROGRESS, false)
			})
		},
		
		// メーカーオンチェンジ(ポップアップ)
		onchgMarkerPopup: function(){
			const makerDialogThis = this;
			this.$nextTick(function() {
				if (makerDialogThis.dialogData.maker === '') {
					makerDialogThis.dialogData.makerNm = '';
				}
				for (let i = 0; i < makerDialogThis.backData.makerCboxList.length; i++) {
					if (makerDialogThis.backData.makerCboxList[i].cd === makerDialogThis.dialogData.maker) {
						makerDialogThis.dialogData.makerNm = makerDialogThis.backData.makerCboxList[i].cdNaiyo;
					}
				}
				this.form.makerCd = this.dialogData.maker
				// エラーをクリア
				this.$store.commit(STORE_ERROR_CLEAR)
				// プログレスバー表示
				this.$store.commit(STORE_VIEW_PROGRESS, true)
				co_post(PATH_PNKS030_SEARCH_GETSANTICBOX, this.form, function(res){
					if (res.status == HTTP_STATUS.OK) {
						let santiDataList = [];
						for (let n = 0; n < res.data.length; n++){
							santiDataList.push({
								code: res.data[n].santicd,
								ryakuName: res.data[n].santikj,
								cdryakuMei: res.data[n].santikj
							})
						}
						makerDialogThis.dialogData.santiDataList = santiDataList
					} else {
						// 業務エラー表示
						co_setError(res.data, makerDialogThis.$refs)
						if (!co_isEmpty(makerDialogThis.$refs[res.data[0].errorItems[0]])) {
							makerDialogThis.$nextTick(function() {
								makerDialogThis.$refs[res.data[0].errorItems[0]].focus();
							});
						}
					}
				})
				co_post(PATH_PNKS030_SEARCH_GETTOKAIKYCBOX, this.form, function(res){
					if (res.status == HTTP_STATUS.OK) {
						let tokaikyDataList = [];
						for(let n = 0; n < res.data.length; n++){
							tokaikyDataList.push({
								code: res.data[n].tokaikyucd,
								name: res.data[n].tokaikyukj
							})
						}
						makerDialogThis.dialogData.tokaikyDataList = tokaikyDataList
					} else {
						// 業務エラー表示
						co_setError(res.data, makerDialogThis.$refs)
						if (!co_isEmpty(makerDialogThis.$refs[res.data[0].errorItems[0]])) {
							makerDialogThis.$nextTick(function() {
								makerDialogThis.$refs[res.data[0].errorItems[0]].focus();
							});
						}
					}
				})
				this.dialogData.santiCd = '';
				this.dialogData.touClassCd = '';
				// プログレスバー非表示
				this.$store.commit(STORE_VIEW_PROGRESS, false)
			});
		},
		
		// 全選択ボタンクリック
		iktHaneiChecked: function(index) {
			if (this.dialogData.iktHanei[index].checked == '0'){
				this.dialogData.iktHanei[index].checked = '1'
			} else {
				this.dialogData.iktHanei[index].checked = '0'
			}
			let iktHaneiSelect = 0;
			for (let i = 0; i < this.dialogData.iktHanei.length; i++){
				if (this.dialogData.iktHanei[i].checked == '1'){
					iktHaneiSelect += 1
				}
			}
			if( iktHaneiSelect == this.dialogData.iktHanei.length ) {
				this.iktHaneiRelSelect = false
			} else if (iktHaneiSelect == 0) {
				this.iktHaneiRelSelect = true;
			} else{
				this.iktHaneiRelSelect = false
			}
		},
	
		// 税込組合員単価＜供給単価、税込組合員単価＜仕入単価、供給単価＜仕入単価の場合は入力欄の色(#FFFFEF)を変更する。
		changeHighlight(name, index){
			if(this.activeRow == index && this.activeRowName === name){
				const tanData = this.backData.shohinInfoList[index]
				if ((Number(tanData.zeikomiKakaku) < Number(tanData.suplyTan) ||
						Number(tanData.zeikomiKakaku) < Number(tanData.shiireTan) ||
						Number(tanData.suplyTan) < Number(tanData.shiireTan)) ){
					return true;
				}
				return false;
			}
		},
		// 税込組合員単価＜供給単価、税込組合員単価＜仕入単価、供給単価＜仕入単価の場合は入力欄の色(#FFFFEF)を変更する。
		changeHighlightPopup(name){
			if(this.activeRowName === name){
				const tanData = this.dialogData
				if ((Number(tanData.zeikomiKakaku) < Number(tanData.suplyTan) ||
						Number(tanData.zeikomiKakaku) < Number(tanData.shiireTan) ||
						Number(tanData.suplyTan) < Number(tanData.shiireTan)) ){
					return true;
				}
				return false;
			}
		},
	}
}
</script>

<style type="text/css">
#PNKS030 th {
	padding-left: 0 !important;
	padding-right: 0 !important;
	background: #e8eaef !important;
	text-align: left !important;
}

#PNKS030 .div_meisai {
	height: 30px;
	padding-top: 10px;
}

#PNKS030 .overflow-h {
	overflow: hidden;
}

#PNKS030 .background {
	background: #f0f1f7;
}

#PNKS030 .font-size-16px {
	font-size: 16px;
}

#PNKS030 .margin-left-50px {
	margin-left: 50px;
}

#PNKS030 .margin-right-50px {
	margin-right: 50px;
}

#PNKS030 .margin-left-10px {
	margin-left: 10px;
}

#PNKS030 .tbl-sticky-head {
	overflow: hidden;
	position: -webkit-sticky;
	position: sticky;
	top: 0;
	z-index: 1;
}

#PNKS030 .tbl-list--border-all_exist .td--pad-small {
	padding: 2px;
}

#PNKS030 .checkbox--margin-l-10 {
	margin-left: 10px;
}

#PNKS030 #NSKC0130sub .modal__contents {
	width: 85%;
}

#PNKS030 #NSKC0130sub .contents__block-command-btn--bg_gray {
	margin: 0;
	width: 100%;
}

#PNKS030 #NSKC0130sub .margin-right-tanka {
	margin-right: 30px;
}

#PNKS030 #NSKC0130sub .tbl-input {
	display: flex;
	justify-content: flex-end;
	margin-bottom: 20px;
}

#PNKS030 #NSKC0130sub .tbl-input td {
	height: 45px;
}

#PNKS030 #NSKC0130sub .tbl-select {
	display: flex;
	justify-content: flex-end;
	padding-right: 293px;
}

#PNKS030 #NSKC0130sub .tbl-select .lbl-select {
	padding: 8px 1px;
	height: 45px;
	width: 110px;
}

#PNKS030 #NSKC0130sub .tbl-select table {
	height: 220px;
	overflow-y: auto;
	overflow-x: hidden;
}

#PNKS030 .contents__view--bg_gray {
	padding: 10px 32px;
}
#PNKS030 .footer-button__block {
    display: flex;
}
#PNKS030 .footer-button__block .left-block {
    display: inline-flex;
    width: 25%;
}
#PNKS030 .footer-button__block .center-block {
    display: inline-flex;
    width: 45%;
}
#PNKS030 .footer-button__block .right-block {
    display: inline-flex;
    justify-content: flex-end;
    width: 30%;
}
#PNKS030 .footer-button__block .left-block input[type="button"],#PNKS030 .footer-button__block .center-block input[type="button"] {
    float: left;
    margin-right: 30px;
}
#PNKS030 .tbl-list--border-all_exist td input {
    margin: 0px !important;
}
#PNKS030 .checkbox:checked + .checkbox-label[data-v-1ac2b216]::after {
    top: -5px !important;
    left: 8px !important;
}
#PNKS030 .checkbox-label[data-v-1ac2b216]::before {
    margin-top: -9px !important;
    margin-left: 26px !important;
}
#PNKS030 .highlight {
  background-color: #FFFFEF;
}
#PNKS030 #E210MList .checkbox-label{
	background: transparent !important;
	width: 20px;
}

#PNKS030 #NSKC0130sub .checkbox-label{
	background: transparent !important;
	width: 10px;
	border-right: 0px
}
#PNKS030 #NSKC0130sub .checkbox-label[data-v-1ac2b216]::before {
    margin-top: -6px !important;
    margin-left: 11px !important;
}
#PNKS030 #NSKC0130sub .checkbox-label[data-v-1ac2b216]::after {
    margin-top: -5px !important;
    margin-left: 8px !important;
}
#PNKS030 .tbl-list--border-all_exist {
	width: auto !important;
}
#PNKS030 .modal__contents {
    max-height: 100vh;
    overflow-x: auto;
}
#PNKS030 #NSKC0130sub .tbl-list--border-all_exist {
    border-spacing: 0;
    display: block;
    overflow: scroll;
    white-space: nowrap;
}
#PNKS030 .tbl-list--border-all_exist th, #PNKS030 .tbl-list--border-all_exist td {
    padding: 8px 0px !important;
}
#PNKS030 .tbl-list--border-all_exist td span {
    padding: 8px 5px !important;
}
#PNKS030 .tbl-list--border-all_exist .td--pad-small {
    padding: 2px !important;
}
#PNKS030 .fontsizeLess select{
    padding: 5px 2px !important;
}
.ellipsis {
	width: 130px;
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
}

</style>
