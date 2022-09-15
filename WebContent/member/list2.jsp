<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>사용자 관리</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body bgcolor=#FFFFFF text=#000000 leftmargin=0 topmargin=0 marginwidth=0 marginheight=0>
<br>
<table style="width:100%">
  <tr>
  	<td width="20"></td>
    <td><a href="<c:url value='/member/logout' />">로그아웃(&nbsp;${curMemberId}&nbsp;)</a></td>
  </tr>
  <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
  <tr>
	<td width="20"></td>
	<td>
	  <table>
		<tr>
		  <td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>사용자 관리 - 리스트</b>&nbsp;&nbsp;</td>
		</tr>
	  </table>  
	  <br>		  
	  <table style="background-color: YellowGreen">
		<tr>
		  <td width="190" align="center" bgcolor="E6ECDE" height="22">사용자 ID</td>
		  <td width="200" align="center" bgcolor="E6ECDE">성별</td>
		  <td width="200" align="center" bgcolor="E6ECDE">닉네임</td>
		  <td width="200" align="center" bgcolor="E6ECDE">직업</td>
		  <td width="200" align="center" bgcolor="E6ECDE">결혼여부</td>
		  <td width="200" align="center" bgcolor="E6ECDE">가족수</td>
		  <td width="200" align="center" bgcolor="E6ECDE">채식여부</td>
		  <td width="200" align="center" bgcolor="E6ECDE">알러지여부</td>
		  <td width="200" align="center" bgcolor="E6ECDE">비밀번호</td>
		  <td width="200" align="center" bgcolor="E6ECDE">비밀번호2</td>
		</tr>
<%-- 
	if (userList != null) {	
	  Iterator<User> userIter = userList.iterator();
	
	  //사용자 리스트를 클라이언트에게 보여주기 위하여 출력.
	  while ( userIter.hasNext() ) {
		User user = (User)userIter.next();
--%>	  	
	  <c:forEach var="member" items="${memberList}">  			  	
  		<tr>
		  <td width="190" align="center" bgcolor="ffffff" height="20">
		  	${user.userId}       <%-- <%=user.getUserId()%> --%>
		  </td>
		  <td width="200" bgcolor="ffffff" style="padding-left: 10">
			<a href="<c:url value='/user/view'>
					   <c:param name='userId' value='${user.userId}'/>
			 		 </c:url>">
			  ${user.name}</a>	 <%-- <%=user.getName()%></a> --%>
		  </td>
		  <td width="200" align="center" bgcolor="ffffff" height="20">
		    ${user.email}        <%-- <%=user.getEmail()%> --%>
		  </td>
		  <td width="200" align="center" bgcolor="ffffff" height="20">
			<a href="<c:url value='/community/view'>
					   <c:param name='commId' value='${user.commId}'/>
			 		 </c:url>">		
			${user.commName}</a>
		  </td>
		</tr>
	  </c:forEach> 
<%--
	  }
	}
--%>	 
	  </table>	  	 
	  <br>   
	  <a href="<c:url value='/user/register/form' />">사용자 추가</a>
	  <br>   
	  <a href="<c:url value='/community/list' />">커뮤니티 목록</a>
	</td>
  </tr>
</table>  
</body>
</html>