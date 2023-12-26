/**
//================================================================================
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// JavaScriptName:stringUtility
//
// <<Modification History>>
// Version  | Date       | Updated By            | Content
// ---------+------------+-----------------------+--------------------------------
// 01.00.00 | 2022/10/28 |  AMC                  | 新規作成
//================================================================================
 */

/*
 * 有効なtabindex項目を取得
 * @param {Number} tabIndex 対象のtabindex
 * @return {Object} 有効なtabindex項目のリスト
 */
export function co_getTabList(tabIndex) {
	let tabList;
	if (tabIndex < 30001) {
		// 通常画面のtabIndexの場合
		tabList = co_getRangeTabList(1, 30000);
	} else {
		// 上記以外(ポップアップ画面)のtabIndexの場合
		tabList = co_getRangeTabList(30001, 32767);
	}
	return tabList;
}

/*
 * 指定のtabindexの前の有効なtabindex項目を取得
 * @param {Number} tabIndex 対象のtabindex
 * @return {Object} 有効なtabindex項目のリスト
 */
export function co_getBeforeTabList(tabIndex) {
	let tabList;
	if (tabIndex < 1 || tabIndex === 1 || tabIndex === 30001) {
		// 無効なtabIndexの場合
		tabList = [];
	} else if (tabIndex < 30001) {
		// 通常画面のtabIndexの場合
		tabList = co_getRangeTabList(1, tabIndex - 1);
	} else {
		// 上記以外(ポップアップ画面)のtabIndexの場合
		tabList = co_getRangeTabList(30001, tabIndex - 1);
	}
	return tabList;
}

/*
 * 指定のtabindexの後の有効なtabindex項目を取得
 * @param {Number} tabIndex 対象のtabindex
 * @return {Object} 有効なtabindex項目のリスト
 */
export function co_getAfterTabList(tabIndex) {
	let tabList;
	if (tabIndex < 1 || tabIndex === 30000 || tabIndex === 32767) {
		// 無効なtabIndexの場合
		tabList = [];
	} else if (tabIndex < 30001) {
		// 通常画面のtabIndexの場合
		tabList = co_getRangeTabList(tabIndex + 1, 30000);
	} else {
		// 上記以外(ポップアップ画面)のtabIndexの場合
		tabList = co_getRangeTabList(tabIndex + 1, 32767);
	}
	return tabList;
}

/*
 * 指定範囲の有効なtabindex項目を取得
 * @param {Number} startTabindex 開始のtabindex
 * @param {Number} endTabindex 終了のtabindex
 * @return {Object} 有効なtabindex項目のリスト
 */
export function co_getRangeTabList(startTabindex, endTabindex) {
	let tabList = [];
	if (startTabindex < 1 || endTabindex < 1) {
		return tabList;
	}

	// 範囲内のtabindexの非抑止、表示の項目を取得する
	document.getElementById("app")
		.querySelectorAll('a[tabindex],input[tabindex],select[tabindex],textarea[tabindex],button[tabindex],div[tabindex]')
			.forEach(
				function(e) {
					if (e.tabIndex >= startTabindex && e.tabIndex <= endTabindex && !e.disabled &&
						e.style.display !== 'none' && document.defaultView.getComputedStyle(e, null).display !== 'none' &&
						e.style.visibility !== 'hidden' && document.defaultView.getComputedStyle(e, null).visibility !== 'hidden'	) {

						let noneDisplay = co_closest(e, 'div[style*="display: none"],tr[style*="display: none"],td[style*="display: none"]');
						if (noneDisplay === null) {
							// 親が非表示でない場合
							let tabpane = co_closest(e, 'div.wj-tabpane');
							if (tabpane) {
								// タブパネルの場合
								if (tabpane.classList.contains('wj-state-active')) {
									// アクティブなタブの場合
									tabList.push(e);
								}
							} else {
								// 上記以外の場合
								tabList.push(e);
							}
						}
					}
				}
			);

	// tabindex順に並び替える
	tabList.sort(
			function(a, b) {
				return a.tabIndex - b.tabIndex;
			}
		);
	return tabList;
}

/*
 * 最初のtabindex項目を取得
 * @param {Object} tabList tabindex項目のリスト
 * @return {Object} 最初のtabindex項目
 */
export function co_getFirstTabItem(tabList) {
	if (tabList.length > 0) {
		return tabList[0];
	}
	return null;
}

/*
 * 最後のtabindex項目を取得
 * @param {Object} tabList tabindex項目のリスト
 * @return {Object} 最後のtabindex項目
 */
export function co_getLastTabItem(tabList) {
	if (tabList.length > 0) {
		return tabList[tabList.length - 1];
	}
	return null;
}

/*
 * 自分自身または親から指定されたセレクター文字列に一致する要素を取得
 * ※IE11が、closest、matchesに非対応(独自ポリフィル)
 * @param {Object} targetElement 対象の要素
 * @param {String} selector  セレクター
 * @return {Object} 結果の要素
 */
export function co_closest(targetElement, selector) {
	let element = targetElement;
	if (element.closest) {
		return element.closest(selector);
	}
	do {
		if (element.msMatchesSelector(selector)) {
			return element;
		}
		element = element.parentelementement || element.parentNode;
	} while (element !== null && element.nodeType === 1);
	return null;
}

/*
 * フォーカス設定
 * @param {Object} tabItem tabindex項目
 * @return {Boolean} true：フォーカス制御あり／false：フォーカス制御なし
 */
export function co_focusTabItem(tabItem) {
	try {
		if (tabItem !== null) {
			tabItem.focus();
			if (tabItem.select) {
				tabItem.select();
			}
			return true;
		}
	} catch (e) {
		// 処理なし
	}
	return false;
}

/*
 *	タブパネル中のtabindexがついていて、フォーカス可能な要素リストをindex順で返す
 */
export function co_getTabElementList(tabPnl) {
	let tabList = [];
	tabPnl.selectedTab.pane
	.querySelectorAll('a[tabindex],input[tabindex],select[tabindex],textarea[tabindex],button[tabindex],div[tabindex]')
		.forEach(function(e) {
					if (e.tabIndex >= 1 && e.tabIndex <= 30000 && !e.disabled &&
						e.style.display !== 'none' && document.defaultView.getComputedStyle(e, null).display !== 'none' &&
						e.style.visibility !== 'hidden' && document.defaultView.getComputedStyle(e, null).visibility !== 'hidden'	) {

						tabList.push(e);
					}
			}
		);

	// tabindex順に並び替える
	tabList.sort(function(a, b) {
			return a.tabIndex - b.tabIndex;
		}
	);
	return tabList;
}

/*
 *	次のタブパネルの先頭要素にフォーカスを移動する
 *	対象要素のindexが現在のタブパネルの最後要素のindexより小さい場合、移動しない
 */
export function co_focusNextTabFirstItem(tabPnl, e) {

	let tabList = co_getTabElementList(tabPnl);

	if (tabList.length == 0) return;

	if (e.target.tabIndex >= tabList[tabList.length - 1].tabIndex) {

		var nextIndex = tabPnl.selectedIndex + 1;
		if (nextIndex >= tabPnl.tabs.length) {
			nextIndex = 0;
		}
		tabPnl.selectedIndex = nextIndex;

		this.$nextTick(function(){
			tabList = co_getTabElementList(tabPnl);
			if(tabList[0]){
				tabList[0].focus();
			}
			e.preventDefault();
		});
	}
}

/*
 *	前のタブパネルの最後要素にフォーカスを移動する
 *	対象要素が現在のタブパネルの先頭要素でない場合、移動しない
 */
export function co_focusPreviousTabLastItem(tabPnl, e) {

	let tabList = co_getTabElementList(tabPnl);

	if (tabList.length == 0) return;

	if (e.target.tabIndex == tabList[0].tabIndex) {

		var prevIndex = tabPnl.selectedIndex - 1;
		if (prevIndex < 0 ) {
			prevIndex = tabPnl.tabs.length - 1;
		}
		tabPnl.selectedIndex = prevIndex;

		this.$nextTick(function(){
			tabList = co_getTabElementList(tabPnl);
			if(tabList[tabList.length - 1]){
				tabList[tabList.length - 1].focus();
			}
			e.preventDefault();
		});
	}
}
