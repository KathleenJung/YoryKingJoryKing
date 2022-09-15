package controller.recipe;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.RecipeDTO;
import model.ReviewDTO;
import model.service.BookmarkManager;
import model.service.RecipeManager;
import model.service.ReviewManager;

public class ViewRecipeController  implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
	    String userID = (String) session.getAttribute("memberId");

	    
		RecipeManager manager = RecipeManager.getInstance();		
		int recipeID = Integer.parseInt(request.getParameter("recipeID"));
	    RecipeDTO dto = manager.viewRecipe(recipeID);	    
	    
	    request.setAttribute("recipeDTO", dto);
	    
//	    ¸®ºä ºÎºÐ
	    ReviewManager manager2 = ReviewManager.getInstance();
	    List<ReviewDTO> reviewList = manager2.findReviewList(recipeID);
	    request.setAttribute("reviewlist", reviewList);
	    
		return "/recipe/view.jsp";
	}

}
