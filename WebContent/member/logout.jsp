<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>로그아웃</title>
<%
	session.invalidate();
%>

	<script>

		alert('로그아웃');

		location.href = 'FirstMain.jsp';

	</script>
</head>
<body>
</body>
</html>