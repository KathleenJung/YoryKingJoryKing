package model;

//import java.util.ArrayList;

public class IngredientsDTO {

	private String ingredientID = null;
	private String ingName = null;
	private String menuName = null;
	private String reciContent = null;
	//private ArrayList<String> recipeList = new ArrayList<String>();
	//이게 왜 있어야하는 지 나는 모르겠다.
	
	public String getIngredientID() {
		return ingredientID;
	}
	public void setIngredientID(String ingredientID) {
		this.ingredientID = ingredientID;
	}
	public String getIngName() {
		return ingName;
	}
	public void setIngName(String ingName) {
		this.ingName = ingName;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getReciContent() {
		return reciContent;
	}
	public void setReciContent(String reciContent) {
		this.reciContent = reciContent;
	}/*
		 * public ArrayList<String> getRecipeList() { return recipeList; } public void
		 * setRecipeList(ArrayList<String> recipeList) { this.recipeList = recipeList; }
		 */
	
}
