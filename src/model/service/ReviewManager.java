package model.service;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import controller.recipe.ViewRecipeController;
import model.ReviewDTO;
import model.dao.ReviewDAO;

/**
 * 사용자 관리 API를 사용하는 개발자들이 직접 접근하게 되는 클래스.
 * UserDAO를 이용하여 데이터베이스에 데이터 조작 작업이 가능하도록 하며,
 * 데이터베이스의 데이터들을 이용하여 비지니스 로직을 수행하는 역할을 한다.
 * 비지니스 로직이 복잡한 경우에는 비지니스 로직만을 전담하는 클래스를 
 * 별도로 둘 수 있다.
 */


public class ReviewManager {
	private static ReviewManager userMan = new ReviewManager();
	private ReviewDAO reviewDAO;

	private ReviewManager() {
		try {
			reviewDAO = new ReviewDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}

	public static ReviewManager getInstance() {
		return userMan;
	}

	public int create(ReviewDTO dto) throws SQLException {
		return reviewDAO.create(dto);
	}

	public int update(ReviewDTO dto) throws SQLException, ReviewNotFoundException {
		return reviewDAO.update(dto);
	}	

	public int remove(int reviewid) throws SQLException, ReviewNotFoundException {
		return reviewDAO.remove(reviewid);
	}
	

	public List<ReviewDTO> findReviewList(int recipeid) throws SQLException {
		try {
			return reviewDAO.findReviewList(recipeid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<ReviewDTO> findReviewListId(String writerid) throws SQLException {
		try {
			return reviewDAO.findReviewListId(writerid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<ReviewDTO> findReviewListByTitle(String title) throws SQLException {
		try {
			return reviewDAO.findReviewListByTitle(title);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	

	public ReviewDTO findReview(int reviewid)
			throws SQLException, ReviewNotFoundException {
		return reviewDAO.findReview(reviewid);
	}

	//RecipeID로 리뷰 검색
	public List<ReviewDTO> findByRecipeID
	(String recipeID, int currentPage, int countPerpage) throws SQLException {
		return reviewDAO.findByRecipeID(recipeID, currentPage, countPerpage);
	}


	//	작성자 아이디로 검색
	public List<ReviewDTO> findByWriterID (String writerid) throws SQLException {
		return reviewDAO.findByWriterID(writerid);
	}

	//		메뉴로 리뷰 검색
	public List<ReviewDTO> findByMenu
	(String menuName, int currentPage, int countPerpage) throws SQLException {
		return reviewDAO.findByMenu(menuName, currentPage, countPerpage);
	}
	
	public ReviewDAO getReviewDAO() {
		return this.reviewDAO;
	}
}
