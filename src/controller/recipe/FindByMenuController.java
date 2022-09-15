package controller.recipe;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.RecipeDTO;
import model.service.RecipeManager;

public class FindByMenuController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int menuID = Integer.parseInt(request.getParameter("menuID"));
		
		try {
			RecipeManager manager = RecipeManager.getInstance();
			List<RecipeDTO> recipeList = manager.findRecipeByMenu(menuID);
			request.setAttribute("recipeListOfMenu", recipeList);
			return "/recipe/recipeListOfMenu.jsp";
			
		}catch(Exception e) {
			return "/recipe/allRecipe.jsp";
		}
		
		
	}

}
