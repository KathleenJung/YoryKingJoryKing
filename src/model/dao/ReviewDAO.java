package model.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.DispatcherServlet;

import java.util.ArrayList;
import java.sql.*;

import model.RecipeDTO;
import model.ReviewDTO;

public class ReviewDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);

	//	
	//	ReviewService
	//	작성자 아이디로 검색
	//	메뉴로 리뷰 검색

	//	reviewid, writerid, recipteid, content, wr_date, stars, title
	

	private JDBCUtil jdbcUtil = new JDBCUtil();

	//	글 작성
	public int create(ReviewDTO dto) throws SQLException {
		String query = "INSERT INTO REVIEW (REVIEWID, WRITERID, RECIPEID, TITLE, CONTENT, STARS) "
				+ "VALUES (REVIEW_SEQ.NEXTVAL, ?, ?, ?, ?, ?)";
		Object[] param = new Object[] {dto.getWriterid(), dto.getRecipeid(), dto.getTitle(), dto.getContent(),
				dto.getStars()};
		jdbcUtil.setSqlAndParameters(query, param);
		try {
			int result = jdbcUtil.executeUpdate();
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return 0;
	}

	//	글 수정
	public int update(ReviewDTO dto) throws SQLException {
		String query = "UPDATE REVIEW " + "SET title=?, content=? " + "WHERE reviewid=?";
		Object[] param = new Object[] {dto.getTitle(), dto.getContent(), dto.getReviewid()};
		jdbcUtil.setSqlAndParameters(query, param);
		try {
			int result = jdbcUtil.executeUpdate();
			return result;
		}catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return 0;
	}

	//	글 삭제
	public int remove(int reviewid) throws SQLException {
		String query = "DELETE FROM REVIEW WHERE REVIEWID=?";
		jdbcUtil.setSqlAndParameters(query, new Object[] { reviewid });
		try {				
			int result = jdbcUtil.executeUpdate();	// delete 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;
	}
	

	public List<ReviewDTO> findReviewList(int recipeid) throws SQLException {
		String sql = "SELECT r.title, r.reviewid, r.writerid, r.content, r.stars " 
				+ "FROM REVIEW r, RECIPE p "
				+ "WHERE r.recipeid=p.recipeid AND r.recipeid=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { recipeid });		// JDBCUtil에 query문 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 실행			
			List<ReviewDTO> reviewList = new ArrayList<ReviewDTO>();	// User들의 리스트 생성
			while (rs.next()) {
				ReviewDTO dto = new ReviewDTO(
						rs.getString("title"),
						rs.getInt("reviewid"),
						rs.getString("writerid"),
						rs.getString("content"),
						rs.getInt("stars"));
				reviewList.add(dto);				// List에 User 객체 저장
			}		
			return reviewList;					

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
	
	public List<ReviewDTO> findReviewListByTitle(String title) throws SQLException {
		String sql = "SELECT r.title, r.reviewid, r.writerid, r.content, r.stars " 
				+ "FROM REVIEW r, RECIPE p "
				+ "WHERE r.recipeid=p.recipeid AND r.title=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { title });		// JDBCUtil에 query문 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 실행			
			List<ReviewDTO> reviewList = new ArrayList<ReviewDTO>();	// User들의 리스트 생성
			while (rs.next()) {
				ReviewDTO dto = new ReviewDTO(
						rs.getString("title"),
						rs.getInt("reviewid"),
						rs.getString("writerid"),
						rs.getString("content"),
						rs.getInt("stars"));
				reviewList.add(dto);				// List에 User 객체 저장
			}		
			return reviewList;					

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
	
	public List<ReviewDTO> findReviewListId(String writerid) throws SQLException {
		String sql = "SELECT r.title, r.reviewid, r.writerid, r.content, r.stars " 
				+ "FROM REVIEW r, RECIPE p "
				+ "WHERE r.recipeid=p.recipeid AND r.writerid=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { writerid });		// JDBCUtil에 query문 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 실행			
			List<ReviewDTO> reviewList = new ArrayList<ReviewDTO>();	// User들의 리스트 생성
			while (rs.next()) {
				ReviewDTO dto = new ReviewDTO(
						rs.getString("title"),
						rs.getInt("reviewid"),
						rs.getString("writerid"),
						rs.getString("content"),
						rs.getInt("stars"));
				logger.info("-_- -_- " + dto.getTitle());
				reviewList.add(dto);				// List에 User 객체 저장
			}		
			return reviewList;					

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}



	
	//RecipeID로 리뷰 검색
	public List<ReviewDTO> findByRecipeID(String recipeID, int currentPage, int countPerpage) throws SQLException {
		String query = "SELECT reviewid, writerid, recipeid, content, rvdate, stars, title " +
				"FROM REVIEW " +
				"WHERE reviewid=?";
		jdbcUtil.setSqlAndParameters(query, new Object[] { recipeID });
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			int start = ((currentPage-1) * countPerpage) + 1;
			List<ReviewDTO> contentList = null;
			if((start>=0)&&rs.absolute(start)) {
				contentList = new ArrayList<ReviewDTO>();
				do {
					ReviewDTO dto = new ReviewDTO();
					dto.setReviewid(rs.getInt("reviewid"));
					dto.setWriterid(rs.getString("writerid"));
					dto.setRecipeid(rs.getInt("recipeid"));
					dto.setContent(rs.getString("content"));
					dto.setStars(rs.getInt("stars"));
					dto.setTitle(rs.getString("title"));
					contentList.add(dto);
				} while(rs.next() && (--countPerpage > 0));
			}
			return contentList;
		}catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return null;
	}
	public ReviewDTO findReview(int reviewid) throws SQLException {			
		String query = "SELECT reviewid, writerid, recipeid, content, stars, title FROM REVIEW WHERE reviewid=?";
		jdbcUtil.setSqlAndParameters(query, new Object[] { reviewid });
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			if (rs.next()) {						// 학생 정보 발견
				ReviewDTO dto = new ReviewDTO(		// User 객체를 생성하여 학생 정보를 저장
						reviewid,
						rs.getString("writerid"),
						rs.getInt("recipeid"),
						rs.getString("content"),
						rs.getInt("stars"),
						rs.getString("title")
						);
				return dto;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}

	//	작성자 아이디로 검색
	public List<ReviewDTO> findByWriterID(String writerid) throws SQLException {
		String sql = "SELECT title, reviewid, writerid, content, rvdate, stars " 
				+ "FROM REVIEW "
				+ "WHERE writerid=?"
				+ "ORDER BY reviewid";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {writerid});		// JDBCUtil에 query문 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 실행			
			List<ReviewDTO> reviewList = new ArrayList<ReviewDTO>();	// User들의 리스트 생성
			while (rs.next()) {
				ReviewDTO dto = new ReviewDTO(
						rs.getString("title"),
						rs.getInt("reviewid"),
						rs.getString("writerid"),
						rs.getString("content"),
						rs.getInt("stars"));
				reviewList.add(dto);				// List에 User 객체 저장
			}		
			return reviewList;					

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
	
	//	메뉴로 리뷰 검색
	public List<ReviewDTO> findByMenu(String menuName, int currentPage, int countPerpage) throws SQLException {
		String query = "SELECT reviewid, writerid, recipeid, content, rvdate, stars, title " +
				"FROM REVIEW, MENU " +
				"WHERE MENU.RECIPEID=RECIPE.RECIPEID AND menuname=?";
		jdbcUtil.setSqlAndParameters(query, new Object[] { menuName });
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			int start = ((currentPage-1) * countPerpage) + 1;
			List<ReviewDTO> contentList = null;
			if((start>=0)&&rs.absolute(start)) {
				contentList = new ArrayList<ReviewDTO>();
				do {
					ReviewDTO dto = new ReviewDTO();
					dto.setReviewid(rs.getInt("reviewid"));
					dto.setWriterid(menuName);
					dto.setRecipeid(rs.getInt("recipeid"));
					dto.setContent(rs.getString("content"));
					dto.setStars(rs.getInt("stars"));
					dto.setTitle(rs.getString("title"));
					contentList.add(dto);
				} while(rs.next() && (--countPerpage > 0));
			}
			return contentList;
		}catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return null;
	}

}
