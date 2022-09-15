package model.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookmarkDAO {
	static Connection conn = null;
	static PreparedStatement pstmt = null;
	static ResultSet rs = null;
	
	public BookmarkDAO() {	// 생성자 
		// JDBC 드라이버 로딩 및 등록
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");	
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}	
	}
	
	private static Connection getConnection() {
		String url = "jdbc:oracle:thin:@202.20.119.117:1521:orcl";	
		String user = "dbp0209";
		String passwd = "dbp0209";

		// DBMS와의 연결 획득
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, passwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}	 
		return conn;
	}
	
	public static int getBookmarkNum(int recipeID) {
		conn = getConnection();
		String sql = "select count(*) as total "
				 + "from bookmark " 
				 + "where recipeid = ?";
		
			
		int bookmarkNum = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, recipeID);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				bookmarkNum = rs.getInt("total");
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if (rs != null) {
				try { rs.close(); } 
				catch (SQLException ex) { ex.printStackTrace(); }
			}
			
			if (pstmt != null) {
				try { pstmt.close();	} 
				catch (SQLException ex) { ex.printStackTrace(); }
			}
			if (conn != null) {
				try { conn.close(); } 
				catch (SQLException ex) { ex.printStackTrace(); }
			}
		}
		return bookmarkNum;
	}
	
	public static boolean existingBookmark(int recipeID, String memberID) {
	      conn = getConnection();
	      String sql = "select count(*) as c from bookmark where recipeId=? and memberID=? ";
	      int result = 0;
	      try {      
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setInt(1, recipeID);         
	         pstmt.setString(2, memberID);
	         
	         rs = pstmt.executeQuery();
	         
	         if(rs.next()){     
	            result = rs.getInt("c");
	         }
	       
	      } catch (Exception ex) {
	         ex.printStackTrace();
	         
	      }finally {      
	         
	         if (pstmt != null) {
	            try { pstmt.close();   } 
	            catch (SQLException ex) { ex.printStackTrace(); }
	         }
	         if (conn != null) {
	            try { conn.close(); } 
	            catch (SQLException ex) { ex.printStackTrace(); }
	         }
	      }   
	      System.out.println(result);

	      if(result > 0) return true;
	      else return false;
	   }
	public static int addBookmark(int recipeID, String memberID) {
		conn = getConnection();
		
		String sql = "insert into bookmark(recipeID, memberID) values(?, ?)";
		System.out.println("recipeID:" + recipeID + ", memberID: " + memberID);
		
		int result = 0;
		try {		
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, recipeID);			
			pstmt.setString(2, memberID);
			
			result = pstmt.executeUpdate();
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}finally {		
			
			if (pstmt != null) {
				try { pstmt.close();	} 
				catch (SQLException ex) { ex.printStackTrace(); }
			}
			if (conn != null) {
				try { conn.close(); } 
				catch (SQLException ex) { ex.printStackTrace(); }
			}
		}	
		return result;
	}
	
	public static int deleteBookmark(int recipeID, String memberID) {
		conn = getConnection();
		
		String sql = "delete from bookmark where recipeID=? and memberID=?";
		
		int result = 0;
		try {				
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, recipeID);
			pstmt.setString(2, memberID);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			if (rs != null) {
				try { rs.close(); } 
				catch (SQLException ex) { ex.printStackTrace(); }
			}
			
			if (pstmt != null) {
				try { pstmt.close();	} 
				catch (SQLException ex) { ex.printStackTrace(); }
			}
			if (conn != null) {
				try { conn.close(); } 
				catch (SQLException ex) { ex.printStackTrace(); }
			}
		}		
		return result;
	}
	
}
