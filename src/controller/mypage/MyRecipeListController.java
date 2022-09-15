package controller.mypage;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.RecipeDTO;
import model.service.MyPageManager;
import model.service.RecipeManager;

public class MyRecipeListController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
	    String userID = (String) session.getAttribute("memberId");

		try {
			MyPageManager manager = MyPageManager.getInstance();
			List<RecipeDTO> recipeList = manager.findRecipeByWriter(userID);
			request.setAttribute("recipeListOfWriter", recipeList);
			return "/mypage/myRecipeList.jsp";
			
		}catch(Exception e) {
			return "/mypage/home.jsp";
		}
		
	}

}
