<%@page contentType="text/html; charset=utf-8" %>
<%@page import="model.MemberDTO" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("UTF-8"); %>
<%
   MemberDTO member = (MemberDTO)request.getAttribute("member");
%>
<%
   String userID = (String) session.getAttribute("memberId");
%>
<html>
<head>
<title>사용자 관리</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<script>
function memberModify() {
   if (form.password.value == "") {
      alert("비밀번호를 입력하십시오.");
      form.password.focus();
      return false;
   }
   if (form.password.value != form.password2.value) {
      alert("비밀번호가 일치하지 않습니다.");
      form.name.focus();
      return false;
   }
   if (form.name.value == "") {
      alert("이름을 입력하십시오.");
      form.name.focus();
      return false;
   }
   form.submit();
}

function memberList(targetUri) {
   form.action = targetUri;
   form.submit();
}
</script>
<style>
ul{ list-style:none;
      text-align:center;
   }
   ul li {display:inline;
      text-transform:uppercase;
      padding0 10px; letter-spacing:10px;
      }
   ul li a {text-decoration:none; color:black;}
   ul li a:hover {text-decoration: underline; }
   
   #ap { height: 300px;
      vertical-align: bottom;
   }
@font-face { font-family: 'yg-jalnan'; src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_four@1.2/JalnanOTF00.woff') format('woff'); font-weight: normal; font-style: normal; }
@font-face { font-family: 'BMJUA'; src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_one@1.0/BMJUA.woff') format('woff'); font-weight: normal; font-style: normal; }
@font-face { font-family: 'NanumSquareRound'; src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_two@1.0/NanumSquareRound.woff') format('woff'); font-weight: normal; font-style: normal; }
b {font-family: 'yg-jalnan';}
.mainTitle {font-family: 'yg-jalnan'; color:#FFBB00; font-size: xx-large;} 
</style>
</head>
<body bgcolor=#FFFFFF text=#000000 leftmargin=0 topmargin=0 marginwidth=0 marginheight=0>
<div class="container">
 <header class="blog-header py-3">
    <div class="row flex-nowrap justify-content-between align-items-center">
    <div class="col-4 pt-1">
      </div>
      <div class="col-4 text-center font-face" style="font-family: 'yg-jalnan';color:#FFBB00;">
       <% if(userID != null) {%>
           <a class="blog-header-logo text-dark" style="text-decoration:none" href="<c:url value='/member/main.jsp'/>"><b class="mainTitle">요리킹 조리킹</b></a>
        <% } else {%>
           <a class="blog-header-logo text-dark" style="text-decoration:none" href="<c:url value='/member/FirstMain.jsp'/>"><b class="mainTitle">요리킹 조리킹</b></a>
        <% } %>  
      </div>
      <div class="col-4 d-flex justify-content-end align-items-center">
        <% if(userID != null) { //로그인 된 상태 %>
           <a class="btn btn-sm btn-outline-secondary" href="logout.jsp">LogOut</a>
        <% } else { //로그인 안 한 상태 %>
           <a class="btn btn-sm btn-outline-secondary" href="loginForm.jsp">Log In</a>
           <a class="btn btn-sm btn-outline-secondary" href="<c:url value='/member/join/form' />">Sign Up</a>
        <% } %>  
        
      </div>
    </div>
  </header>
   <hr/>
  <div class="nav-scroller py-1 mb-2">
     <nav class="navbar navbar-expand-lg navbar-light bg-light" style=" font-family: 'BMJUA';">
        <% if(userID != null) {%>
           <a class="navbar-brand" href="<c:url value='/member/main.jsp'/>">요리킹 조리킹</a>
        <% } else {%>
           <a class="navbar-brand" href="<c:url value='/member/FirstMain.jsp'/>">요리킹 조리킹</a>
        <% } %>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
       <ul class="navbar-nav">
            <li class="nav-item active">
              <a class="nav-link" href="<c:url value='/recipe/allRecipe'/>">전체레시피보기 <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="<c:url value='/menu/allMenu'/>">전체메뉴보기</a>
            </li>
            <% if(userID != null) {%>
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                   마이페이지
              </a>
              <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                 <a class="dropdown-item" href="<c:url value='/member/view'/>">회원정보</a>
                   <a class="dropdown-item" href="<c:url value='/mypage/myBookmark'/>">북마크</a>
                   <a class="dropdown-item" href="<c:url value='/mypage/myRecipe'/>">나의 레시피</a>
                   <a class="dropdown-item" href="<c:url value='/mypage/myReview'/>">나의 리뷰</a>
              </div>
            </li>
            <% } %>
       </ul>
        </div>
   </nav>
    </div>
<br>
<!-- Update Form  -->
<form name="form" method="POST" action="<c:url value='/member/update' />">
  <input type="hidden" name="memberId" value="<%=member.getMemberId()%>"/>     
  <table style="width: 100%">
   <tr>
     <td width="20"></td>
     <td>
       <table>
        <tr>
         <td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>사용자 관리 - 사용자 수정</b>&nbsp;&nbsp;</td>
        </tr>
       </table>  
       <br>     
       <table>
          <tr height="40">
         <td width="150" align="center" class="table-warning" style="font-family: 'BMJUA'">사용자 ID</td>
         <td width="250" bgcolor="ffffff" style="padding-left: 10; font-family: 'NanumSquareRound'"">
            <%= member.getMemberId() %>
         </td> 
        <tr height="40">
         <td width="150" align="center" class="table-warning" style="font-family: 'BMJUA'">성별</td>
         <td width="250" bgcolor="ffffff" style="padding-left: 10; font-family: 'NanumSquareRound'"">
         <input type="radio" style="width: 20;" name="gender" value="여성" checked>여성   
            <input type="radio" style="width: 20;" name="gender" value="남성" >남성
         </td>
        </tr>
          <tr height="40">
         <td width="150" align="center" class="table-warning" style="font-family: 'BMJUA'">닉네임</td>
         <td width="250" bgcolor="ffffff" style="padding-left: 10; font-family: 'NanumSquareRound'"">
            <input type="text" style="width: 240" name="m_name" value="<%= member.getM_name() %>">
         </td>
        </tr>   
               <tr height="40">
         <td width="150" align="center" class="table-warning" style="font-family: 'BMJUA'">직업</td>
         <td width="250" bgcolor="ffffff" style="padding-left: 10; font-family: 'NanumSquareRound'"">
            <input type="text" style="width: 240" name="job" value="<%=member.getJob()%>">
         </td>
        </tr> 
          <tr height="40">
         <td width="150" align="center" class="table-warning" style="font-family: 'BMJUA'">결혼여부</td>
         <td width="250" bgcolor="ffffff" style="padding-left: 10; font-family: 'NanumSquareRound'"">
         <input type="radio" style="width: 20;" name="married" value="no" checked>미혼
         <input type="radio" style="width: 20;" name="married" value="yes" >기혼
         </td>
        </tr> 
          <tr height="40">
         <td width="150" align="center" class="table-warning" style="font-family: 'BMJUA'">가족수</td>
         <td width="250" bgcolor="ffffff" style="padding-left: 10; font-family: 'NanumSquareRound'"">
            <input type="text" style="width: 240" name="familyNum" value="<%= member.getFamilyNum() %>">
         </td>
        </tr> 
           <tr height="40">
         <td width="150" align="center" class="table-warning" style="font-family: 'BMJUA'">채식여부</td>
         
         <td width="250" bgcolor="ffffff" style="padding-left: 10; font-family: 'NanumSquareRound'"">
            <input type="radio" style="width: 20;" name="vegitarian" value="아님" checked>아님
                 <input type="radio" style="width: 20;" name="vegitarian" value="비건">비건
            <input type="radio" style="width: 20;" name="vegitarian" value="락토">락토
            <input type="radio" style="width: 20;" name="vegitarian" value="오보">오보
            <input type="radio" style="width: 20;" name="vegitarian" value="락토오보">락토오보
         </td>
        </tr> 
         <%--<tr height="40">
         <td width="150" align="center" bgcolor="E6ECDE">나이</td>
         <td width="250" bgcolor="ffffff" style="padding-left: 10">
            <input type="text" style="width: 240;" name="age">
         </td>
        </tr>  --%>
          <tr height="40">
         <td width="150" align="center" class="table-warning" style="font-family: 'BMJUA'">알러지여부</td>
         <td width="250" bgcolor="ffffff" style="padding-left: 10; font-family: 'NanumSquareRound'"">
            <input type="radio" style="width: 20;" name="allergy" value="없음" checked>없음
               <input type="radio" style="width: 20;" name="allergy" value="있음">있음
            <input type="text" style="width: 240;" name="allergy" placeholder="있을 경우 항목을 입력해주세요.">
         </td>
        </tr> 
          <tr height="40">
         <td width="150" align="center" class="table-warning" style="font-family: 'BMJUA'">비밀번호</td>
         <td width="250" bgcolor="ffffff" style="padding-left: 10; font-family: 'NanumSquareRound'"">
            <input type="text" style="width: 240" name="password" value="<%= member.getPassword() %>">
         </td>
        </tr>
          <tr height="40">
         <td width="150" align="center" class="table-warning" style="font-family: 'BMJUA'">비밀번호 확인</td>
         <td width="250" bgcolor="ffffff" style="padding-left: 10; font-family: 'NanumSquareRound'"">
            <input type="text" style="width: 240" name="password2" value="<%= member.getPassword() %>">
         </td>
        </tr>     
       </table>
       <br>     
       <table style="width: 100%">
        <tr>
         <td align="left">
         <input type="button" value="수정" onClick="memberModify()" class="btn btn-secondary"> &nbsp;
         </td>
        </tr>
       </table>
     </td>
   </tr>
  </table>  
</form>

</div>
</body>
</html>