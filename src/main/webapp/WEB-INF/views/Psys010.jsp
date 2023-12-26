<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<title><c:out value="${appName}" /></title>
<style type="text/css">
<!--
a, body, button, div, header, main, nav, section, footer, h1, h2, h3, h4,
	input, label, p, select, span, table, thead, tbody, tr, th, td,
	textarea, ul, li {
	font-family: Meiryo, sans-serif;
}

body {
	font-size: 13.5px;
	height: 100vh;
	margin: 0;
}

button {
	font-size: 13.5px;
	border: 0;
}

div, h1, nav {
	margin: 0;
}

input[type="button"] {
	font-size: 13.5px;
}

input[type="text"], input[type="password"] {
	background: #fff;
	border: 2px solid #cbcdd9;
	border-radius: 5px;
	box-sizing: border-box;
	display: inline-block;
	font-size: 13.5px;
	padding: 6px 10px;
	background-color: #FCFFE1 !important;
}

table {
	border-collapse: collapse;
}

th, td {
	box-sizing: border-box;
}

.contents--require-item {
	background-color: #FCFFE1;
}

.header__block-info {
	background: #f9f9f9;
	display: flex;
	justify-content: space-between;
	padding: 4px 6px;
}

.header__block-info--left {
	display: flex;
	margin-left: 20px;
}

.header__block-info--left__icon {
	height: 50px;
}

.header__block-info--left__title {
	margin: 10px 0 0 5px;
	font-size: 24px;
}

.contents__block-error {
	background: #fceff1;
	border: 2px solid #dc8585;
	color: #c2010e;
	font-size: 80%;
	margin-bottom: 10px;
	padding: 0 16px;
	width: 90%;
	margin: auto;
	display: none;
}

.contents__block-error__msg {
	font-weight: bold;
	padding-left: 30px;
	position: relative;
}

.contents__block-error__msg:nth-of-type(1):before {
	border: 1px solid #c2010e;
	border-radius: 50%;
	content: "！";
	font-size: 130%;
	height: 18px;
	margin-left: -25px;
	position: absolute;
	text-align: center;
	top: -3px;
	width: 18px;
}

.tbl-item--border-row_exist {
	margin: 0 1%;
	width: 98%;
}

.tbl-item--border-row_exist tr {
	border-bottom: 2px solid #dedfe8;
	border-top: 2px solid #dedfe8;
}

.tbl-item--border-row_exist td {
	min-height: 40px;
	padding: 4px 8px;
}

.tbl-item--border-row_exist td:nth-of-type(odd) {
	background: #f0f1f7;
	width: 18%;
}

.tbl-item--border-row_exist td:nth-of-type(even) {
	background: #fff;
}

.tbl-item--border-row_exist input[type="text"] {
	border: 2px solid #cbcdd9;
	display: inline-block;
}

.contents__btn-blue {
	background: linear-gradient(90deg, #5e6adc 0%, #6e5bcf 100%);
	box-sizing: border-box;
	border: 1px solid transparent;
	border-radius: 22px;
	color: #fff;
	display: inline-block;
	height: 37px;
	min-width: 105px;
	padding: 8px 16px;
	vertical-align: middle;
}

.contents__btn-blue:hover {
	background: #fff;
	border: 1px solid #4158c6;
	cursor: pointer;
	color: #6167da;
}

.contents__btn-blue:active {
	border: 2px solid #4158c6;
	box-shadow: inset 1px 1px 4px #ccc;
	font-weight: bold;
	padding: 8px 14px 6px 16px;
}

.require {
	background: red;
	border-radius: 16px;
	color: #ffffff;
	float: right;
	padding: 3px 20px 2px;
	vertical-align: middle;
	margin-top: -3px;
}

.view-area {
	background: #f7f8fa;
	min-height: 100vh;
}

.header__block-title {
	background: #fff;
	border-bottom: 2px solid #eaeaea;
	font-size: 18.66px;
	font-weight: inherit;
	padding: 5px 40px 5px;
	height: 44px;
	line-height: 44px;
}

.login__area-input {
	border-bottom: 2px solid #eaeaea;
	padding: 60px 0 20px;
}

.login__area-input-wrap {
	margin: 0 auto;
	width: 33%;
}

.login__introduction {
	font-size: 130%;
	margin-bottom: 2px;
}

.login__area-input .tbl-item--border-row_exist td {
	width: 50%;
}

.login__area-btn {
	margin-top: 10px;
	text-align: center;
}

.login__area-info {
	padding: 20px 0;
}

.login__area-info-title {
	font-size: 110%;
	text-align: center;
}

.login__area-info-display {
	border: 2px solid #eaeaea;
	box-sizing: border-box;
	height: calc(100vh - 55.99px - 285.6px - 45px - 46.95px - 51.8px);
	margin: 0 auto;
	padding: 6px;
	width: 70%;
	overflow-y: auto;
}

#OLCN01010M input[type="password"], #OLCN01010M input[type="text"] {
	width: 100%;
}

#btnLogin {
	width: 150px;
}

.focusable_hidden {
	position: absolute;
	left: -999;
	width: 0px;
	opacity: 0;
}

.ime-inactive {
	ime-mode: inactive;
}
-->
</style>

</head>
<body>
	<div class="view-area">
		<div class="header__block-info">
			<div class="header__block-info--left">
				<img src="${pageContext.request.contextPath}/img/icon_logo.png"
					class="header__block-info--left__icon"> <span
					class="header__block-info--left__title"><c:out value="${appName}" /></span>
			</div>
		</div>
		<h1 class="header__block-title">ログイン</h1>
		<main id="PSYS010">

		<div class="login__area-input">

			<div class="contents__block-error" id="error_area">
				<p class="contents__block-error__msg" id="error_msg">
					<c:out value="${errorMsg}" />
				</p>
			</div>

			<div class="login__area-input-wrap">

				<p class="login__introduction">●担当者コードとパスワードを入力してください。</p>

				<form:form modelAttribute="LoginForm"
					action="${pageContext.request.contextPath}/login"
					id="frm">
					<table class="tbl-item--border-row_exist">
						<tbody>
							<tr>
								<td>担当者コード</td>
								<td><form:input type="text" id="userId" path="userId" class="ime-inactive contents--require-item"
										value="" tabindex="2"></form:input></td>
							</tr>
							<tr>
								<td>パスワード</td>
								<td><input type="password" id="password" name="password" class="contents--require-item"
									value="" maxlength="64" tabindex="3" autocomplete="on"></td>
							</tr>
						</tbody>
					</table>
					<div class="login__area-btn">
						<input type="submit" id="btnLogin" class="contents__btn-blue" value="ログイン" tabindex="4"/>
						<input type="button" id="btnClear" class="contents__btn-blue" value="クリア" tabindex="5" />
					</div>
				</form:form>

				<!-- フォーカスガード -->
				<input type="text" class="focusable_hidden" tabindex="1"
					onfocus="document.getElementById('btnClear').focus();" /> <input
					type="text" class="focusable_hidden" tabindex="6"
					onfocus="document.getElementById('userId').focus();" />

			</div>
		</div>
		<div class="login__area-info">
			<h2 class="login__area-info-title">お知らせ</h2>
			<div class="login__area-info-display">
				<p>
					<c:out value="${information.oshiraseHtml}" escapeXml="false" />
				</p>
			</div>
		</div>
		</main>
	</div>


	<script type="text/javascript">
		// メインウィンドウを閉じる
		if (opener && !opener.closed && opener.mainWindowClose) {
			opener.mainWindowClose();
		}

		// サーバーメッセージ
		<c:if test="${showErrorMsg}">
			let errArea = document.getElementById("error_area");
			errArea.style.display = "block";
		</c:if>

		//初期フォーカス
		document.getElementById('userId').focus();

		//ログインボタン押下処理
		document.getElementById("frm").addEventListener('submit', function clickLogin(event) {
			let userid = document.getElementById('userId');
			if (userid.value == '') {
				
				showErrMsg('ユーザIDが入力されていません。入力してください。');
				userid.focus();
				event.preventDefault();
				return false;
			}

			let password = document.getElementById('password');
			if (password.value == '') {
				
				showErrMsg('パスワードが入力されていません。入力してください。');
				password.focus();
				event.preventDefault();
				return false;
			}

			document.getElementById('btnLogin').disabled = true;
			return true;
		});

		function showErrMsg(msg) {
			let errArea = document.getElementById("error_area");
			errArea.style.display = "block";

			let errMsg = document.getElementById("error_msg");
			errMsg.innerText = msg;
		}

		// クリアボタン押下処理
		document.getElementById("btnClear").addEventListener("click", function clickTest() {
			
			document.getElementById('userId').value = '';
			document.getElementById('password').value = '';
		});

		// ユーザーID キーダウンイベント
		document.getElementById("userId").addEventListener("keydown", function(event) {
			if (event.keyCode == 13) {
				
				event.preventDefault();
				document.getElementById('password').focus();
			}
		});
		
		// パスワード キーダウンイベント
		document.getElementById("password").addEventListener("keydown", function(event) {
			if (event.keyCode == 13) {
				
				event.preventDefault();
				document.getElementById('btnLogin').focus();
			}
		});
	</script>
</body>
</html>