package model.dao;

import java.util.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.MenuDTO;

public class MenuDAO {

	private static JDBCUtil jdbcUtil= new JDBCUtil();
	
	//기존재 메뉴 검사
	public static boolean existingMenu(String menuName) throws SQLException {
			String sql = "SELECT count(*) FROM menu WHERE menuName=?";      
			jdbcUtil.setSqlAndParameters(sql, new Object[] {menuName});	// JDBCUtil에 query문과 매개 변수 설정

			try {
				ResultSet rs = jdbcUtil.executeQuery();		// query 실행
				if (rs.next()) {
					int count = rs.getInt(1);
					return (count == 1 ? true : false);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				jdbcUtil.close();		// resource 반환
			}
			return false;
	}
	public static int findMenuID(String menuName) {
		String sql = "SELECT menuID FROM menu WHERE menuName=?";      
		jdbcUtil.setSqlAndParameters(sql, new Object[] {menuName});	// JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			if (rs.next()) {
				return rs.getInt("menuID");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return -1;
		
	}
	public static int addMenu(String menuName, String nationality) { //menuDAO에서 가져올 수 있음
		String sql = "insert into menu(menuID, menuName, nationality) values(menu_seq.nextval, ?, ?)";			
		jdbcUtil.setSqlAndParameters(sql, new Object[] {menuName, nationality});
		try {				
			int result = jdbcUtil.executeUpdate();
			return result;	// insert 문 실행
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		
		return 0;
	}
	public List<MenuDTO> allMenuList() throws SQLException{
		String sql = "SELECT menuName, menuID FROM menu";
		jdbcUtil.setSqlAndParameters(sql, null);
		try {
			ResultSet rs = jdbcUtil.executeQuery();					
			List<MenuDTO> menuList = new ArrayList<MenuDTO>();	
			while (rs.next()) {
				MenuDTO dto = new MenuDTO(			
						rs.getString("menuName"),
						rs.getInt("menuID"));
				menuList.add(dto);
		}
			return menuList; 
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			jdbcUtil.close();
		}
		return null;
	}
	
	public List<MenuDTO> findMenu(String menu){
		
			String sql ="SELECT menuName, menuID FROM menu WHERE menuName like ?";
			jdbcUtil.setSqlAndParameters(sql, new Object[] {"%"+menu+"%"});
			try {
				ResultSet rs = jdbcUtil.executeQuery();			// query 실행			
				List<MenuDTO> NameMenuList = new ArrayList<MenuDTO>();	
				while (rs.next()) {
					MenuDTO dto = new MenuDTO(		
						rs.getString("menuName"),
						rs.getInt("menuID"));	
					NameMenuList.add(dto);				
				}		
				return NameMenuList;					
				
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				jdbcUtil.close();		// resource 반환
			}
			return null;
	
	}
	
}
