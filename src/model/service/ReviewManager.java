package model.service;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import controller.recipe.ViewRecipeController;
import model.ReviewDTO;
import model.dao.ReviewDAO;

/**
 * ����� ���� API�� ����ϴ� �����ڵ��� ���� �����ϰ� �Ǵ� Ŭ����.
 * UserDAO�� �̿��Ͽ� �����ͺ��̽��� ������ ���� �۾��� �����ϵ��� �ϸ�,
 * �����ͺ��̽��� �����͵��� �̿��Ͽ� �����Ͻ� ������ �����ϴ� ������ �Ѵ�.
 * �����Ͻ� ������ ������ ��쿡�� �����Ͻ� �������� �����ϴ� Ŭ������ 
 * ������ �� �� �ִ�.
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

	//RecipeID�� ���� �˻�
	public List<ReviewDTO> findByRecipeID
	(String recipeID, int currentPage, int countPerpage) throws SQLException {
		return reviewDAO.findByRecipeID(recipeID, currentPage, countPerpage);
	}


	//	�ۼ��� ���̵�� �˻�
	public List<ReviewDTO> findByWriterID (String writerid) throws SQLException {
		return reviewDAO.findByWriterID(writerid);
	}

	//		�޴��� ���� �˻�
	public List<ReviewDTO> findByMenu
	(String menuName, int currentPage, int countPerpage) throws SQLException {
		return reviewDAO.findByMenu(menuName, currentPage, countPerpage);
	}
	
	public ReviewDAO getReviewDAO() {
		return this.reviewDAO;
	}
}
