package model.service;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

import model.RecipeDTO;
import model.ReviewDTO;
import model.dao.RecipeDAO;
import model.dao.ReviewDAO;

public class RecipeManager {
		
	private static RecipeManager recipeManager = new RecipeManager();
	private RecipeDAO recipeDAO;
	private ReviewDAO reviewDAO;
	
	private RecipeManager() {
		try {
			recipeDAO = new RecipeDAO();
		}catch (Exception e) {
			e.printStackTrace();
		}
		try {
			reviewDAO = new ReviewDAO();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static RecipeManager getInstance() {return recipeManager; }
	
	public int addRecipe(RecipeDTO dto) throws Exception {
		try {
			return recipeDAO.addRecipe(dto);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public int deleteRecipe(int recipeID, String memberID) {
		try{
			return recipeDAO.deleteRecipe(recipeID, memberID);
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public int updateRecipe(RecipeDTO dto) {
		try {
			return recipeDAO.updateRecipe(dto);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public RecipeDTO viewRecipe(int recipeID) {
		try {
			return recipeDAO.viewRecipe(recipeID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<RecipeDTO> allRecipeList(){
		try {
			return recipeDAO.allRecipeList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<RecipeDTO> findRecipeByWriter(String writerID){
		return recipeDAO.findRecipeByWriter(writerID);
	}
	
	public List<RecipeDTO> findRecipeByMenu(int menuID){
		return recipeDAO.findRecipeByMenu(menuID);
		
	}
	
	public List<RecipeDTO> findRecipeByHard(String hard){
		return recipeDAO.findRecipeByHard(hard);

	}
	
	public List<RecipeDTO> findRecipeByIngredients(String[] ingredients){
		return recipeDAO.findRecipeByIngredients(ingredients);
	}
	
	
	
	

}
