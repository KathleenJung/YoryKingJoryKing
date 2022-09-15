package controller.recipe;

import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;

import model.RecipeDTO;
import model.dao.RecipeDAO;
import model.service.RecipeManager;

public class UpdateRecipeController implements Controller {
	int recipeID;
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//////////////////////작성자와 로그인 한 사람이 같아야만 수정 가능코드 넣어야 함/////////////////////
		if(request.getMethod().equals("GET")) {
			recipeID = Integer.parseInt(request.getParameter("recipeID"));
			RecipeManager manager = RecipeManager.getInstance();
			RecipeDTO recipe = manager.viewRecipe(recipeID);
			
			request.setAttribute("recipe", recipe);
			
			return "/recipe/updateRecipeForm.jsp";
		}
		
		String[] ingredients = request.getParameterValues("ingredientList");
		
		ArrayList<String> ingredientList = new ArrayList<String>(Arrays.asList(ingredients));
		RecipeDTO updatedRecipe = new RecipeDTO(recipeID, request.getParameter("menuName"), 				
				request.getParameter("writerID"), request.getParameter("recontent"), request.getParameter("hard"), 
				request.getParameter("time"), request.getParameter("video"), request.getParameter("recipeTitle"), 
				ingredientList, request.getParameter("menuNationality"),0);
			

		System.out.println("id=" + recipeID);
		try {
			RecipeManager manager = RecipeManager.getInstance();
			manager.updateRecipe(updatedRecipe);
	        return "redirect:/recipe/allRecipe";	
	        
	        
		} catch (Exception e) {
			return "/recipe/updateRecipeForm.jsp";
		}
		
	}

}
