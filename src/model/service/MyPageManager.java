package model.service;

import java.util.List;

import model.RecipeDTO;
import model.ReviewDTO;
import model.dao.MyPageDAO;

public class MyPageManager {
	
	private static MyPageManager myPageManager = new MyPageManager();
	private MyPageDAO mypageDAO;
	
	private MyPageManager() {
		try {
			mypageDAO = new MyPageDAO();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static MyPageManager getInstance() {return myPageManager; }
	

	public List<RecipeDTO> findRecipeByWriter(String writerID){
		return mypageDAO.findRecipeByWriter(writerID);
	}
	
	public List<RecipeDTO> findRecipeByBookmark(String memberID){
		return mypageDAO.findRecipeByBookmark(memberID);
	}
	
	public List<ReviewDTO> findReviewByWriter(String memberID){
		return mypageDAO.findReviewByWriter(memberID);
	}
	

}
