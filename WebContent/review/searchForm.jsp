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
<title>�޴� �˻�</title>
</head>
<body>
<form name = "form" action="<c:url value='/review/search'/>" method="post" accept-charset="UTF-8">
<input type="text" name="search">
<input type="button" value="�˻�" onClick="search()">
</form>
</body>
</html>