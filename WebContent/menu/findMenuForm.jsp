<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<% request.setCharacterEncoding("UTF-8"); %>
<html>
<head>
<meta charset="EUC-KR">
<script>
function search() {
	form.submit();
}
</script>
<title>메뉴 검색</title>
</head>
<body>
<form name = "form" action="<c:url value='/menu/findMenu'/>" method="post" accept-charset="UTF-8">
<input type="text" name="menu">
<input type="button" value="검색" onClick="search()">
</form>
</body>
</html>