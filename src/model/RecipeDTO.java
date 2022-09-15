package model;

import java.util.ArrayList;

public class RecipeDTO {

	private int recipeID;
	private String menuName;
	private String writerID;
	private String recontent;
	private String hard;
	private String time;
	private int menuID;
	private String video;
	private String recipeTitle;
	private ArrayList<String> ingredientList = new ArrayList<String>();
	private String menuNationality;
	private int bookmarkNum;

	public RecipeDTO() {}
	public RecipeDTO(int recipeID, String menuName, String writerID, String recontent, 
			String hard, String time, String video, String recipeTitle, 
			ArrayList<String> ingredientList, String menuNationality, int bookmarkNum) {
		super();
		this.recipeID = recipeID;
		this.menuName = menuName;
		this.writerID = writerID;
		this.recontent = recontent;
		this.hard = hard;
		this.time = time;
		this.video = video;
		this.recipeTitle = recipeTitle;
		this.ingredientList = ingredientList;
		this.menuNationality = menuNationality;
		this.bookmarkNum = bookmarkNum;
	}
	
	public int getRecipeID() {
		return recipeID;
	}
	public void setRecipeID(int recipeID) {
		this.recipeID = recipeID;
	}
	
	public String getWriterID() {
		return writerID;
	}
	public void setWriterID(String writerID) {
		this.writerID = writerID;
	}
	
	public String getRecontent() {
		return recontent;
	}
	public void setRecontent(String recontent) {
		this.recontent = recontent;
	}
	public String getHard() {
		return hard;
	}
	public void setHard(String hard) {
		this.hard = hard;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getMenuID() {
		return menuID;
	}
	public void setMenuID(int menuID) {
		this.menuID = menuID;
	}
	public String getVideo() {
		return video;
	}
	public void setVideo(String video) {
		this.video = video;
	}
	public ArrayList<String> getIngredientList() {
		return ingredientList;
	}
	public void setIngredientList(ArrayList<String> ingredientList) {
		this.ingredientList = ingredientList;
	}
	public String getRecipeTitle() {
		return recipeTitle;
	}
	public void setRecipeTitle(String recipeTitle) {
		this.recipeTitle = recipeTitle;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuNationality() {
		return menuNationality;
	}
	public void setMenuNationality(String menuNationality) {
		this.menuNationality = menuNationality;
	}

	public int getBookmarkNum() {
		return bookmarkNum;
	}
	public void setBookmarkNum(int bookmarkNum) {
		this.bookmarkNum = bookmarkNum;
	}
	
}
