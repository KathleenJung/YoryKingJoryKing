package controller.recipe;
import java.util.*;
import controller.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.service.RecipeManager;
import model.RecipeDTO;
public class AllRecipeController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		RecipeManager manager = RecipeManager.getInstance();
		List<RecipeDTO> recipeList = manager.allRecipeList();
		
		request.setAttribute("allRecipeList", recipeList);
		
		
		return "/recipe/allRecipe.jsp";
	}
	

}
