package model.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.RecipeDTO;
import model.ReviewDTO;

public class MyPageDAO {
	private JDBCUtil jdbcUtil= new JDBCUtil();
	
	public List<RecipeDTO> findRecipeByWriter(String writerID) { //작성자로 레시피 검색
		String sql ="SELECT r.recipeTitle, m.menuName, r.hard, r.time, r.writerID, r.video, r.recipeID, m.nationality "
     		   + "FROM recipe r, menu m "
     		   + "where r.menuID = m.menuID and r.writerID = ?"
     		   + "ORDER BY recipeID";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {writerID});		// JDBCUtil에 query문 설정
					
		int bookmarkNum = 0;
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 실행			
			List<RecipeDTO> writerRecipeList = new ArrayList<RecipeDTO>();	// User들의 리스트 생성
			while (rs.next()) {
				bookmarkNum = BookmarkDAO.getBookmarkNum(rs.getInt("recipeID"));
				RecipeDTO dto = new RecipeDTO(		
					rs.getInt("recipeID"),
					rs.getNString("menuName"),
					rs.getString("writerID"),
					null,
					rs.getString("hard"),
					rs.getString("time"),
					rs.getString("video"),
					rs.getString("recipeTitle"), 
					null,
					rs.getString("nationality"),
					bookmarkNum);
				writerRecipeList.add(dto);			
			}		
			return writerRecipeList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();	
		}
		return null;
	}

	
	public List<RecipeDTO> findRecipeByBookmark(String memberID) { //작성자로 레시피 검색
		String sql ="SELECT r.recipeTitle, m.menuName, r.hard, r.time, r.writerID, r.video, r.recipeID, m.nationality "
     		   + "FROM recipe r, menu m, bookmark b "
     		   + "where r.menuID = m.menuID and r.recipeID=b.recipeID and b.memberID=?"
     		   + "ORDER BY recipeID";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {memberID});		// JDBCUtil에 query문 설정
					
		int bookmarkNum = 0;
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 실행			
			List<RecipeDTO> bookmarkRecipeList = new ArrayList<RecipeDTO>();	// User들의 리스트 생성
			while (rs.next()) {
				bookmarkNum = BookmarkDAO.getBookmarkNum(rs.getInt("recipeID"));
				RecipeDTO dto = new RecipeDTO(		
					rs.getInt("recipeID"),
					rs.getNString("menuName"),
					rs.getString("writerID"),
					null,
					rs.getString("hard"),
					rs.getString("time"),
					rs.getString("video"),
					rs.getString("recipeTitle"), 
					null,
					rs.getString("nationality"),
					bookmarkNum);
				bookmarkRecipeList.add(dto);			
			}		
			return bookmarkRecipeList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();	
		}
		return null;
	}
	
	public List<ReviewDTO> findReviewByWriter(String memberID) { //작성자로 레시피 검색
		String sql ="SELECT recipeid, title, content, stars, reviewid FROM review where writerid=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {memberID});		// JDBCUtil에 query문 설정
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 실행			
			List<ReviewDTO> reviewList = new ArrayList<ReviewDTO>();	// User들의 리스트 생성
			while (rs.next()) {
				ReviewDTO dto = new ReviewDTO(		
					rs.getInt("recipeID"),
					rs.getString("title"),
					rs.getString("content"),
					rs.getInt("stars"),
					rs.getInt("reviewid"));
				reviewList.add(dto);			
			}		
			return reviewList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();	
		}
		return null;
	}
}
