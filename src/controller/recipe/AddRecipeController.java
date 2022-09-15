package controller.recipe;

import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.RecipeDTO;
import model.service.RecipeManager;

public class AddRecipeController implements Controller {


   @Override
   public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
      String[] ingredients = request.getParameterValues("ingredientList");
      HttpSession session = request.getSession();
      String userID = (String) session.getAttribute("memberId");

      ArrayList<String> ingredientList = new ArrayList<String>(Arrays.asList(ingredients));
            
      RecipeDTO rDto = new RecipeDTO(0, request.getParameter("menuName"),             
            userID, request.getParameter("recontent"), request.getParameter("hard"), 
            request.getParameter("time"), request.getParameter("video"), request.getParameter("recipeTitle"),
            ingredientList, request.getParameter("menuNationality"), 0);
         
      try {
         RecipeManager manager = RecipeManager.getInstance();
         manager.addRecipe(rDto);
           return "redirect:/recipe/allRecipe";   
           
           
      } catch (Exception e) {
         return "/recipe/addRecipeForm.jsp";
      }
   }
   

}