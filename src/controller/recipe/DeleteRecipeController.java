package controller.recipe;

import controller.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.service.RecipeManager;

public class DeleteRecipeController  implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
	    String userID = (String) session.getAttribute("memberId");
		
		int recipeID = Integer.parseInt(request.getParameter("recipeID"));

		try {
			RecipeManager manager = RecipeManager.getInstance();
			manager.deleteRecipe(recipeID, userID);
			return "redirect:/recipe/allRecipe";
			
		} catch (Exception e) {
			return "redirect:/recipe/allRecipe";
		}
		
	}


}
