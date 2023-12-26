<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
	<head>
		<meta charset="UTF-8">
		<title><c:out value="${appName}" /></title>
		<style type="text/css">
		<!--
		  a
		, body
		, button
		, div
		, header, main, nav, section, footer
		, h1, h2, h3, h4
		, input
		, label
		, p
		, select
		, span
		, table, thead, tbody, tr, th, td
		, textarea
		, ul, li {
			font-family: Meiryo, sans-serif;
		}

		body {
			font-size: 13.5px;
			height   : 100vh;
			margin   : 0;
		}

		button {
			font-size: 13.5px;
			border   : 0;
		}

		div, h1, nav {
			margin: 0;
		}

		input[type="button"] {
			font-size: 13.5px;
		}

		input[type="text"]
		, input[type="password"] {
		  background   : #fff;
		  border       : 2px solid #cbcdd9;
		  border-radius: 5px;
		  box-sizing   : border-box;
		  display      : inline-block;
		  font-size    : 13.5px;
		  padding      : 6px 10px;
		}

		table {
			border-collapse: collapse;
		}

		th, td {
			box-sizing: border-box;
		}

		.header__block-info {
		  background     : #f9f9f9;
			display        : flex;
			justify-content: space-between;
			padding        : 4px 6px;
		}

		.header__block-info--left {
			display    : flex;
			margin-left: 20px;
		}

		.header__block-info--left__icon {
			height: 30px;
		}

		.header__block-info--left__title {
			margin: 9px 0 0 5px;
		}

		.contents__block-error {
			background   : #fceff1;
			border       : 2px solid #dc8585;
			color        : #c2010e;
			font-size    : 80%;
			margin-bottom: 10px;
			padding      : 0 16px;
		}

		.contents__block-error__msg {
			font-weight : bold;
			padding-left: 30px;
			position    : relative;
		}

		.contents__block-error__msg:nth-of-type(1):before {
			border       : 1px solid #c2010e;
			border-radius: 50%;
			content      : "！";
			font-size    : 130%;
			height       : 18px;
			margin-left  : -25px;
			position     : absolute;
			text-align   : center;
			top          : -3px;
			width        : 18px;
		}

		.tbl-item--border-row_exist {
			margin: 0 1%;
			width : 98%;
		}

		.tbl-item--border-row_exist tr {
			border-bottom: 2px solid #dedfe8;
			border-top   : 2px solid #dedfe8;
		}

		.tbl-item--border-row_exist td {
			min-height: 40px;
			padding   : 4px 8px;
		}

		.tbl-item--border-row_exist td:nth-of-type(odd) {
			background: #f0f1f7;
			width     : 18%;
		}

		.tbl-item--border-row_exist td:nth-of-type(even) {
			background: #fff;
		}

		.tbl-item--border-row_exist input[type="text"] {
			border      : 2px solid #cbcdd9;
			display     : inline-block;
		}

		.contents__btn-blue {
			background    : linear-gradient(90deg, #5e6adc 0%, #6e5bcf 100%);
			box-sizing    : border-box;
			border        : 1px solid transparent;
			border-radius : 22px;
			color         : #fff;
			display       : inline-block;
			height        : 37px;
			min-width     : 105px;
			padding       : 8px 16px;
			vertical-align: middle;
		}

		.contents__btn-blue:hover {
			background: #fff;
			border    : 1px solid #4158c6;
			color     : #6167da;
			cursor    : pointer;
		}

		.contents__btn-blue:active {
			border     : 2px solid #4158c6;
			box-shadow : inset 1px 1px 4px #ccc;
			font-weight: bold;
			padding    : 8px 14px 6px 16px;
		}

		.require {
			background   : red;
			border-radius: 16px;
			color        : #ffffff;
			float        : right;
			padding      : 3px 20px 2px;
			vertical-align: middle;
			margin-top: -3px;
		}

		.view-area {
			background: #f7f8fa;
			min-height: 100vh;
		}

		.header__block-title {
			background   : #fff;
			border-bottom: 2px solid #eaeaea;
			font-size    : 18.66px;
			font-weight  : inherit;
			padding      : 14px 40px 12px;
		}

		.error__area-input {
			padding      : 60px 0 20px;
		}

		.error__area-input-wrap {
			margin: 0 auto;
			padding: 10px 5%;
		}

		.error__area-btn {
			margin-top: 10px;
			text-align: center;
		}

		-->
		</style>
	</head>
	<body>
		<div class="view-area">
			<div class="header__block-info">
				<div class="header__block-info--left">
					<img src="${pageContext.request.contextPath}/img/icon_jeed.png" class="header__block-info--left__icon">
					<span class="header__block-info--left__title"><c:out value="${appName}" /></span>
				</div>
			</div>
			<h1 class="header__block-title">システムエラー</h1>
			<div class="error__area-input">
				<div class="error__area-input-wrap">
					<div class="contents__block-error">
						<p class="contents__block-error__msg">予期しないエラーが発生しました。</p>
					</div>
					<div class="error__area-btn">
						<input type="button" class="contents__btn-blue" value="ログインへ" onClick="location.href='${pageContext.request.contextPath}/login/init'" />
					</div>
				</div>
			</div>
		</div>
	</body>
</html>