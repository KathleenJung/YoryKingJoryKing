package controller.recipe;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.RecipeDTO;
import model.service.RecipeManager;

public class FindByIngredientsController  implements Controller {


	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String[] ingredients = request.getParameterValues("ingredientList");
		
		try {
			RecipeManager manager = RecipeManager.getInstance();
			List<RecipeDTO> recipeList = manager.findRecipeByIngredients(ingredients);
			request.setAttribute("recipeListOfIngredients", recipeList);
			return "/recipe/recipeListOfIngredients.jsp";
			
		}catch(Exception e) {
			return "/recipe/allRecipe.jsp";
		}
		
		
	}
	

}
