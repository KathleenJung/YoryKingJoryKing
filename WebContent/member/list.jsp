<%@page contentType="text/html; charset=utf-8" %>
<%@page import="java.util.*" %>
<%@page import="model.MemberDTO" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
//주의: 아래와 같은  scriptlet은 생략하고 EL로 구현하는 것이 바람직함 (list2.jsp 참조)
	@SuppressWarnings("unchecked") 
	List<MemberDTO> memberList = (List<MemberDTO>)request.getAttribute("memberList");
	String curMemberId = (String)request.getAttribute("curMemberId");
%>
<html>
<head>
<title>회원 리스트</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</head>
<body bgcolor=#FFFFFF text=#000000 leftmargin=0 topmargin=0 marginwidth=0 marginheight=0>
<br>
<table style="width:100%">
  <tr>
  	<td width="20"></td>
    <td><a href="logout.jsp">로그아웃(&nbsp;<%=curMemberId%>&nbsp;)</a></td>
  </tr>
   <tr>
  	<td width="20"></td>
    <td><a href="main.jsp">홈페이지</a></td>
  </tr>
  <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
  <tr>
	<td width="20"></td>
	<td>
	  <table>
		<tr>
		  <td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>회원리스트</b>&nbsp;&nbsp;</td>
		</tr>
	  </table>  
	  <br>		  
	  <table style="background-color: YellowGreen">
		<tr>
		  <td width="190" align="center" bgcolor="E6ECDE" height="22">사용자 ID</td>
		  <td width="200" align="center" bgcolor="E6ECDE">닉네임</td>
		</tr>
<%
// 주의: 아래와 같은 scriptlet은 생략하고 <c:for-each>와 EL로 구현하는 것이 바람직함 (list2.jsp 참조)
	if (memberList != null) {	
	  Iterator<MemberDTO> memberIter = memberList.iterator();
	
	  //사용자 리스트를 클라이언트에게 보여주기 위하여 출력.
	  while ( memberIter.hasNext() ) {
		  MemberDTO mem = (MemberDTO)memberIter.next();
%>		  	
		<tr>
		  <td width="190" align="center" bgcolor="ffffff" height="20">
			<%=mem.getMemberId()%>
		  </td>
		  <td width="200" bgcolor="ffffff" style="padding-left: 10">
			<a href="<c:url value='/member/view'>
					   <c:param name='memberId' value='<%=mem.getMemberId()%>'/>
			 		 </c:url>">
			  <%=mem.getM_name()%></a>
		  </td>
		</tr>
<%
	  }
	}
%>	  	
	  </table>	  	 
	  <br>   
	  <a href="<c:url value='/member/join/form' />">가입하기</a>
	</td>
  </tr>
</table>  
</body>
</html>