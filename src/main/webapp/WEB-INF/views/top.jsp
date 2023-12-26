<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset=utf-8>
		<meta http-equiv=X-UA-Compatible content="IE=edge">
		<meta name=viewport content="width=device-width,initial-scale=1">
		<meta name="_csrf" content="${_csrf.token}"/>
		<meta name="_csrf_header" content="${_csrf.headerName}"/>
		<title><c:out value="${appName}" /></title>
		<link href=${pageContext.request.contextPath}/view/css/index.css rel=preload as=style>
		<link href=${pageContext.request.contextPath}/view/js/chunk-vendors.js rel=preload as=script>
		<link href=${pageContext.request.contextPath}/view/js/index.js rel=preload as=script>
		<link href=${pageContext.request.contextPath}/view/css/chunk-vendors.css rel=stylesheet>
		<link href=${pageContext.request.contextPath}/view/css/index.css rel=stylesheet>
	</head>
	<body>
		<div id="app" toppage="${show_component}"></div>
		<script src=${pageContext.request.contextPath}/view/js/chunk-vendors.js></script>
		<script src=${pageContext.request.contextPath}/view/js/index.js></script>
	</body>
</html>