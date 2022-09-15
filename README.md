![header](https://capsule-render.vercel.app/api?type=wave&color=auto&height=200&section=header&text=요리킹조리킹&fontSize=50)

# 목차
1. 프로젝트 소개
2. 개발 기간
3. 개발한 부분
4. 기능
5. 소감

# 프로젝트 소개
 ‘요리킹 조리킹’은 가지고 있는 재료로 만들 수 있는 메뉴 추천과 메뉴 레시피를 제공 해줍니다. 남는 식재료 만으로 요리할 수 있는 레시피를 조회하여 추가적인 구매를 줄이고 효율적으로 식재료를 사용할 수 있습니다.

# 개발 기간
2019.09.18 ~ 2019.12.22 (4개월)

# 개발한 부분
 DB 설계에 참여하고, 레시피에 대한 리뷰 관련 부분 백엔드 개발을 맡았습니다.
1) Review DTO / DAO / Manager 구현
	- model/ReviewDTO.java
	- model.dao/ReviewDAO.java
	- model.service/ReviewManager.java

2) 마이페이지 나의 리뷰 보기
	- MyPageManager.java -> findReviewByWriter
	- controller.mypage/MyReviewListController.java
	- mypage/myReviewList.jsp

3) 마이페이지 나의 리뷰 수정
	- controller.review/UpdateReviewController.java
	- controller.review/UpdateReviewFormController.java
	- review/updateForm.jsp

4) 마이페이지 나의 리뷰 삭제
	- controller.review/DeleteReviewController.java

5) 레시피에 따른 리뷰 보기
	- controller.recipe/ViewRecipeController.java 리뷰 부분
	- recipe/view.jsp 리뷰 부분 233~286Line

6) 레시피에 따른 리뷰 추가
	- controller.review/AddReviewController.java
	- review/addForm.jsp

7) 레시피에 따른 리뷰 제목으로 검색
	- controller.review/SerachController.java
	- review/search.jsp
	- review/searchForm.jsp
  
# 소감
 선수 과목인 자바스크립트를 수강하지 않아서 처음에는 막막하고 어려웠지만 팀원들의 격려와 도움을 받아서 무사히 잘 완성할 수 있었습니다. 특히 제가 지은 프로젝트 이름이 채택되었을 때의 기쁨을 잊지 못할 것 같습니다. 첫 팀 프로젝트가 완성된 것을 보니 뿌듯했고, 앞으로는 선수 과목을 수강하지 않았다고 겁먹고 고민하기보다는 그 공백만큼을 따라잡기 위해서 열심히 노력하면 해낼 수 있다는 생각이 들었습니다.
