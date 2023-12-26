<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
	<head>
		<meta charset="UTF-8">
		<title><c:out value="${appName}" /></title>
		<style type="text/css">
    	</style>
	</head>
	<body>
		<div></div>
		<script type="text/javascript">
		function redirectToLogin() {
			window.location.href = "./login/init"; 
		}
		window.onload = redirectToLogin;
		</script>
	</body>
</html>