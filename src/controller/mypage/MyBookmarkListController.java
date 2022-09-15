package controller.mypage;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.RecipeDTO;
import model.service.MyPageManager;

public class MyBookmarkListController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		HttpSession session = request.getSession();
	    String userID = (String) session.getAttribute("memberId");

		try {
			MyPageManager manager = MyPageManager.getInstance();
			List<RecipeDTO> recipeList = manager.findRecipeByBookmark(userID);
			request.setAttribute("recipeListOfBookmark", recipeList);
			return "/mypage/myBookmarkList.jsp";
			
		}catch(Exception e) {
			return "/mypage/home.jsp";
		}
		
	}

}
