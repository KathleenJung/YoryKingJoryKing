package controller.bookmark;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.RecipeDTO;
import model.service.BookmarkManager;
import model.service.RecipeManager;

public class DeleteBookmarkController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
	    String userID = (String) session.getAttribute("memberId");

	    
	    BookmarkManager manager = BookmarkManager.getInstance();
	    
	    //레시피에 대한 북마크 삭제	    
	    manager.deleteBookmark(Integer.parseInt(request.getParameter("recipeID")), userID);
	  
	    RecipeManager view_manager = RecipeManager.getInstance();		
		int recipeID = Integer.parseInt(request.getParameter("recipeID"));
	    RecipeDTO dto = view_manager.viewRecipe(recipeID);	    
	    
	    request.setAttribute("recipeDTO", dto);
	    
		return "/recipe/view.jsp";
	}

}
