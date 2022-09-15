
<%@page import="model.RecipeDTO" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.sql.*" %>

<% request.setCharacterEncoding("UTF-8"); %>

<%
   String userID = (String) session.getAttribute("memberId");
%>
<%
   RecipeDTO recipe = (RecipeDTO)request.getAttribute("recipeDTO");
%>
<html>
<head>
<title>레시피수정</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<script>
function memberCreate(targetUri) {
   form.action = targetUri;
   form.submit();
}
function recipeCreate() {
   if (form.recipeTitle.value == "") {
      alert("제목을 입력하십시오.");
      form.recipeTitle.focus();
      return false;
   } 
   if (form.menuName.value == "") {
      alert("메뉴명을 입력하십시오.");
      form.recipeTitle.focus();
      return false;
   } 
   if (form.hard.value == "") {
      alert("난이도를 입력하십시오.");
      form.hard.focus();
      return false;
   }
   if (form.reci_content.value == "") {
      alert("내용을 입력하십시오.");
      form.reci_content.focus();
      return false;
   }
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
   .mainTitle {font-family: 'yg-jalnan'; color:#FFBB00; font-size: xx-large;} 
@font-face { font-family: 'yg-jalnan'; src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_four@1.2/JalnanOTF00.woff') format('woff'); font-weight: normal; font-style: normal; }
@font-face { font-family: 'BMJUA'; src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_one@1.0/BMJUA.woff') format('woff'); font-weight: normal; font-style: normal; }
@font-face { font-family: 'NanumSquareRound'; src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_two@1.0/NanumSquareRound.woff') format('woff'); font-weight: normal; font-style: normal; }
b {font-family: 'yg-jalnan';}
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
           <a class="btn btn-sm btn-outline-secondary" href="<c:url value='/member/loginForm.jsp' />">Log In</a>
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
<!-- registration form  -->
<form name="form" method="POST" action="<c:url value='/recipe/updateRecipe' />"  accept-charset="UTF-8">
  <table style="width: 100%">
    <tr>
      <td width="20"></td>
     <td>
       <table>
        <tr>
          <td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>레시피 수정</b>&nbsp;&nbsp;</td>
        </tr>
       </table>  
       <br>     
       
       <table>
          <tr height="40">
         <td width="150" align="center" class="table-warning" style="font-family: 'BMJUA'">메뉴명</td>
         <td width="250" bgcolor="ffffff" style="padding-left: 10; font-family: 'NanumSquareRound'">
            <input type="text" style="width: 240;" name="menuName" value='${recipe.menuName }'> 
         </td>
        </tr>
          <tr height="40">
         <td width="150" align="center" class="table-warning" style="font-family: 'BMJUA'">레시피 제목</td>
         <td width="250" bgcolor="ffffff" style="padding-left: 10; font-family: 'NanumSquareRound'">
            <input type="text" style="width: 240" name="recipeTitle" value='${recipe.recipeTitle }'>
         </td>
        </tr>
        <tr height="40">
         <td width="150" align="center" class="table-warning" style="font-family: 'BMJUA'">분류</td>
         <td width="250" bgcolor="ffffff" style="padding-left: 10; font-family: 'NanumSquareRound'">
            <input type="text" style="width: 240" name="menuNationality"  placeholder="한식/중식/일식/양식" value='${recipe.menuNationality }'>
         </td>
        </tr>
        <tr height="40">
         <td width="150" align="center" class="table-warning" style="font-family: 'BMJUA'">재료</td>
         <td width="250" bgcolor="ffffff" style="padding-left: 10; font-family: 'NanumSquareRound'">
            <input type="checkbox" name='ingredientList' value="간장">간장
           <input type="checkbox" name='ingredientList' value="계란">계란
           <input type="checkbox" name='ingredientList' value="고춧가루">고춧가루
           <input type="checkbox" name='ingredientList' value="김가루">김가루
           <input type="checkbox" name='ingredientList' value="김치">김치
           <input type="checkbox" name='ingredientList' value="깨">깨
           <input type="checkbox" name='ingredientList' value="대파">대파
           <input type="checkbox" name='ingredientList' value="딸기잼">딸기잼
           <input type="checkbox" name='ingredientList' value="마늘">마늘
           <input type="checkbox" name='ingredientList' value="모짜렐라 치즈">모짜렐라 치즈
           <input type="checkbox" name='ingredientList' value="물">물
           <input type="checkbox" name='ingredientList' value="밥">밥
           <input type="checkbox" name='ingredientList' value="설탕">설탕
           <input type="checkbox" name='ingredientList' value="소금">소금
           <input type="checkbox" name='ingredientList' value="스파게티 면">스파게티면
           <input type="checkbox" name='ingredientList' value="식빵">식빵        
           <input type="checkbox" name='ingredientList' value="식용유">식용유
           <input type="checkbox" name='ingredientList' value="옥수수콘">옥수수콘
           <input type="checkbox" name='ingredientList' value="올리브오일">올리브오일
           <input type="checkbox" name='ingredientList' value="체다치즈슬라이스">체다치즈슬라이스
           <input type="checkbox" name='ingredientList' value="파마산치즈">파슬리
           <input type="checkbox" name='ingredientList' value="페퍼론치노">페퍼론치노
           <input type="checkbox" name='ingredientList' value="testIngre">testIngre
         </td>
        </tr>
        
          <tr height="40">
         <td width="150" align="center" class="table-warning" style="font-family: 'BMJUA'">난이도</td>
         <td width="250" bgcolor="ffffff" style="padding-left: 10; font-family: 'NanumSquareRound'">
            <input type="text" style="width: 240" name="hard" placeholder="상/중/하" value='${recipe.hard }'>
         </td>
        </tr>
          <tr height="40">
         <td width="150" align="center" class="table-warning" style="font-family: 'BMJUA'">소요 시간</td>
         <td width="250" bgcolor="ffffff" style="padding-left: 10; font-family: 'NanumSquareRound'">
            <input type="text" style="width: 240" name="time" placeholder="00분" value='${recipe.time }'>
         </td>
        </tr>
        <tr height="40">
         <td width="150" align="center" class="table-warning" style="font-family: 'BMJUA'">유튜브 영상</td>
         <td width="250" bgcolor="ffffff" style="padding-left: 10; font-family: 'NanumSquareRound'">
            <textarea  style="width: 240" name="video"> ${recipe.video }
            </textarea>
         </td>
        </tr>
        <tr height="40">
         <td width="150" align="center" class="table-warning" style="font-family: 'BMJUA'">내용</td>
         <td width="250" bgcolor="ffffff" style="padding-left: 10; font-family: 'NanumSquareRound'">
            <textarea  style="width: 240" name="recontent"> ${recipe.recontent }
            </textarea>
         </td>
        </tr>
        
       </table>
       <br>     
       <table style="width: 100%">
        <tr>
         <td align="left">
         <input type="submit" value="수정" class="btn btn-secondary"> &nbsp;
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