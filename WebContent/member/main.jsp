<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% request.setCharacterEncoding("UTF-8"); %>
        
<%
   String userID = (String) session.getAttribute("memberId");
%>
<!DOCTYPE HTML>
<html>
<head>
<style>
@font-face { font-family: 'yg-jalnan'; src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_four@1.2/JalnanOTF00.woff') format('woff'); font-weight: normal; font-style: normal;}
.main-title { color:#FFBB00; }
</style>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<script>
function memberView() {
   form.submit();
}

function search() {
   form.submit();
}
function memberCreate(targetUri) {
      form.action = targetUri;
      form.submit();
   }

</script>
<title>요리킹 조리킹</title>
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
   @font-face { font-family: 'yg-jalnan'; src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_four@1.2/JalnanOTF00.woff') format('woff'); font-weight: normal; font-style: normal; color:#FFBB00;}
   @font-face { font-family: 'BMJUA'; src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_one@1.0/BMJUA.woff') format('woff'); font-weight: normal; font-style: normal; }
   @font-face { font-family: 'NanumSquareRound'; src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_two@1.0/NanumSquareRound.woff') format('woff'); font-weight: normal; font-style: normal; }
   .mainTitle {font-family: 'yg-jalnan'; color:#FFBB00; font-size: xx-large;} 
</style>
   
</head>

<body>
<div class="container">
  <header class="blog-header py-3">
    <div class="row flex-nowrap justify-content-between align-items-center">
    <div class="col-4 pt-1">
      </div>
      <div class="col-4 text-center font-face">
       <% if(userID != null) {%>
           <a class="blog-header-logo text-dark" style="text-decoration:none" href="<c:url value='/member/main.jsp'/>" style="font-family: 'yg-jalnan'; color:FFBB00;"><b class="mainTitle">요리킹 조리킹</b></a>
        <% } else {%>
           <a class="blog-header-logo text-dark" style="text-decoration:none" href="<c:url value='/member/FirstMain.jsp'/>" style="font-family: 'yg-jalnan'; color:FFBB00;"><b class="mainTitle">요리킹 조리킹</b></a>
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
     <div class="jumbotron p-4 p-md-5 text-white rounded " >
    <div class="col-md-6 px-0">
      <h1 class="display-4 font-italic">Title of a longer featured blog post</h1>
      <p class="lead my-3">Multiple lines of text that form the lede, informing new readers quickly and efficiently about what’s most interesting in this post’s contents.</p>
      <p class="lead mb-0"><a href="#" class="text-white font-weight-bold">Continue reading...</a></p>
    </div>
  </div>
 </div>
   <div align="center">
   <hr>
   <%=userID %>님 환영합니다!!
   </hr><p>

   </form>
   
      <tr>
      <p> 재료명으로 검색 </p>
      <form name="form2" method="post" action="<c:url value='/recipe/findByIngredients'/>" accept-charset="UTF-8" >
        <table>
        
        <tr>
        <td><input type="checkbox" name='ingredientList' value="간장">간장 </td>
        <td><input type="checkbox" name='ingredientList' value="계란">계란</td>
        <td><input type="checkbox" name='ingredientList' value="고춧가루">고춧가루</td>
        <td><input type="checkbox" name='ingredientList' value="김가루">김가루</td>
        <td><input type="checkbox" name='ingredientList' value="김치">김치</td>
        <td><input type="checkbox" name='ingredientList' value="깨">깨</td></tr>
        
        <tr>
        <td><input type="checkbox" name='ingredientList' value="대파">대파</td>
        <td><input type="checkbox" name='ingredientList' value="딸기잼">딸기잼</td>
        <td><input type="checkbox" name='ingredientList' value="마늘">마늘</td>
        <td><input type="checkbox" name='ingredientList' value="모짜렐라 치즈">모짜렐라 치즈</td>
        <td><input type="checkbox" name='ingredientList' value="물">물</td>
        <td><input type="checkbox" name='ingredientList' value="밥">밥</td></tr>
        
        <tr>
        <td><input type="checkbox" name='ingredientList' value="설탕">설탕</td>
        <td><input type="checkbox" name='ingredientList' value="소금">소금</td>
        <td><input type="checkbox" name='ingredientList' value="스파게티 면">스파게티면</td>
        <td><input type="checkbox" name='ingredientList' value="식빵">식빵</td>        
        <td><input type="checkbox" name='ingredientList' value="식용유">식용유</td>
        <td><input type="checkbox" name='ingredientList' value="옥수수콘">옥수수콘</td></tr>
        
        <tr>
        <td><input type="checkbox" name='ingredientList' value="올리브오일">올리브오일</td>
        <td><input type="checkbox" name='ingredientList' value="체다치즈슬라이스">체다치즈슬라이스</td>
        <td><input type="checkbox" name='ingredientList' value="파마산치즈">파슬리</td>
        <td><input type="checkbox" name='ingredientList' value="페퍼론치노">페퍼론치노</td></tr>
        
        <br>
        <input type="submit" value="재료검색" class="btn btn-secondary">
     </form>
     <br>
   
   <tr id = "ap"></tr>
   </table>
   </form>
</body>
</html>