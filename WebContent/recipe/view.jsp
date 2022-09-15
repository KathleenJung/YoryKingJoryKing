<%@page import="model.RecipeDTO" %>
<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.sql.*" %>
<%
   RecipeDTO recipe = (RecipeDTO)request.getAttribute("recipeDTO");
   
   String userID = (String) session.getAttribute("memberId");
%>

<html>
<head>
<title>레시피 조회</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<script>
function recipeRemove() {
   return confirm("정말 삭제하시겠습니까?");      
}
function reviewRemove() {
	   return confirm("리뷰를 정말 삭제하시겠습니까?");      
	}
function memberCreate(targetUri) {
   form.action = targetUri;
   form.submit();
}
function existingBookmark(){
	   BookmarkDAO dao = new BookmarkDAO();
	   fact = dao.existingBookmark(<%=recipe.getRecipeID() %>, userID);
	   alert('confirm');
	   
	   
	      if(fact == true){ //북마크 지정한 상태
	         confirm('이미 지정된 북마크입니다.');
	         return false;
	      }
	   
	      if(fact==false){ //북마크 누른 적 없는 레시피면
	         confirm('기존에 지정되지 않은 북마크입니다.');
	         return false;
	      }
	   
	   
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
nav {font-family: 'BMJUA';}
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
     <nav class="navbar navbar-expand-lg navbar-light bg-light"  style="font-family: 'BMJUA';">
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
  <table style="width:100%">
    <tr>
     <td width="20"></td>
     <td>
       <table>
        <tr>
         <td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>레시피 자세히 보기</b>&nbsp;&nbsp;</td>
        </tr>
       </table>  
       <br>            
        <table>
        
          <tr>
         <td width="120" align="center" class="table-warning" style="font-family: 'BMJUA'">작성자 ID</td>
         <td width="470" bgcolor="ffffff" style="padding-left: 10; font-family: 'NanumSquareRound'">
            <%=recipe.getWriterID() %>
         </td>
        </tr>
        <tr>
         <td width="120" align="center" class="table-warning" style="font-family: 'BMJUA'">메뉴명</td>
         <td width="470" bgcolor="ffffff" style="padding-left: 10; font-family: 'NanumSquareRound'">
            <%=recipe.getMenuName()%>
         </td>
        </tr>
        <tr>
         <td width="120" align="center" class="table-warning" style="font-family: 'BMJUA'">분류</td>
         <td width="470" bgcolor="ffffff" style="padding-left: 10; font-family: 'NanumSquareRound'">
            <%=recipe.getMenuNationality()%>
         </td>
        </tr>
        <tr>
         <td width="120" align="center" class="table-warning" style="font-family: 'BMJUA'">제목</td>
         <td width="470" bgcolor="ffffff" style="padding-left: 10; font-family: 'NanumSquareRound'">
            <%=recipe.getRecipeTitle()%>
         </td>
        </tr>
        <tr>
         <td width="120" align="center" class="table-warning" style="font-family: 'BMJUA'">스크랩 수</td>
         <td width="470" bgcolor="ffffff" style="padding-left: 10; font-family: 'NanumSquareRound'">
           <%=recipe.getBookmarkNum()%>
         </td>
        </tr>
        <tr>
         <td width="120" align="center" class="table-warning" style="font-family: 'BMJUA'">재료</td>
         <td width="470" bgcolor="ffffff" style="padding-left: 10; font-family: 'NanumSquareRound'">
            <%=recipe.getIngredientList()%>
         </td>
        </tr>
        <tr>
         <td width="120" align="center" class="table-warning" style="font-family: 'BMJUA'">내용</td>
         <td width="470" bgcolor="ffffff" style="padding-left: 10; font-family: 'NanumSquareRound'">
            <%=recipe.getRecontent()%>
         </td>
        </tr>
        <tr>
         <td width="120" align="center" class="table-warning" style="font-family: 'BMJUA'">난이도</td>
         <td width="470" bgcolor="ffffff" style="padding-left: 10; font-family: 'NanumSquareRound'">
            <%=recipe.getHard()%>
         </td>
        </tr>
        <tr>
         <td width="120" align="center" class="table-warning" style="font-family: 'BMJUA'">소요시간</td>
         <td width="470" bgcolor="ffffff" style="padding-left: 10; font-family: 'NanumSquareRound'">
            <%=recipe.getTime()%>
         </td>
        </tr>
        <tr>
         <td width="120" align="center" class="table-warning" style="font-family: 'BMJUA'">참고링크</td>
         <td width="470" bgcolor="ffffff" style="padding-left: 10; font-family: 'NanumSquareRound'">
            <a href=<%=recipe.getVideo()%>><%=recipe.getVideo()%></a>
         </td>
        </tr>
       </table>
       <% if(userID != null){
         if(userID.equals(recipe.getWriterID())) { %>
        <a href="<c:url value='/recipe/updateRecipe'>
                 <c:param name='recipeID' value="${recipeDTO.recipeID}"/>
              </c:url>">수정</a> &nbsp;
        <a href="<c:url value='/recipe/delete' >
               <c:param name='recipeID' value="${recipeDTO.recipeID}"/>
              </c:url>" onclick="return recipeRemove();" style="font-family: 'NanumSquareRound';">삭제</a> &nbsp;
            <%}%>
         <a href="<c:url value='/bookmark/addBookmark'>
                 <c:param name='recipeID' value="${recipeDTO.recipeID}"/>
              </c:url>" onClick="existingBookmark();" style="font-family: 'NanumSquareRound';">북마크 추가</a> &nbsp;
         <a href="<c:url value='/bookmark/deleteBookmark'>
                 <c:param name='recipeID' value="${recipeDTO.recipeID}"/>
              </c:url>" onClick="existingBookmark();" style="font-family: 'NanumSquareRound';">북마크 삭제</a> &nbsp;

      <%}%>
      
        <a href="<c:url value='/recipe/allRecipe' />">목록</a>    &nbsp;<br>
        
     </table>         
      
      
           <br>
<table style="width:100%">
  <tr>
   <td width="20"></td>
   <td>   
     <table>
      <tr>
        <td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>전체 리뷰 목록</b>&nbsp;&nbsp;</td>
      </tr>      
     </table>
<p><p>
     <table class="table">
  <thead class="table-warning">
    <tr style="font-family: 'NanumSquareRound';">
      <td scope="col">리뷰 ID</td>
      <td scope="col">제목</td>
      <td scope="col">작성자</td>
      <td scope="col">별점</td>
      <td scope="col">내용</td>
      
    </tr>
  </thead>
  <tbody>
  <c:forEach var="review" items="${reviewlist}">     
  
    <tr style="font-family: 'NanumSquareRound';">
      <th scope="row">${review.reviewid}</th>
      <td> ${review.title}</td>
      <td>${review.writerid}</td>
      <td>${review.stars}</td>
      <td>${review.content}</td>
    </tr>
    
   
   </c:forEach> 
  </tbody>
</table>

		<form name = "form" action="<c:url value='/review/search'/>" method="post" accept-charset="UTF-8">
         <input type="text" name="title">
         <input type="button" value="검색" onClick="search()" class="btn btn-secondary">
      </form>
     
     
     <% if(userID != null) {%>
     <form name="form3" method="post" action="
     <c:url value='/review/create/form'>
               <c:param name='addrecipeid' value="${recipeDTO.recipeID}"/>
              </c:url>">
      <input type="submit" value="리뷰 추가" style="font-family: 'NanumSquareRound';" class="btn btn-secondary">
     </form>   
     <% } %>
   </td>
  </tr>
</table>  
</div>
</body>
</html>