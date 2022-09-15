package model.service;

import java.sql.SQLException;
import java.util.List;

import model.MenuDTO;
import model.dao.MenuDAO;

public class MenuManager {

	private static MenuManager menuManager = new MenuManager();
	private MenuDAO menuDAO;
	
	public MenuManager() {
		try {
			menuDAO = new MenuDAO();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static MenuManager getInstance() {return menuManager; }
	
	public List<MenuDTO> allMenuList(){
		try {
			return menuDAO.allMenuList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<MenuDTO> findMenu(String menu){
		try {
			return menuDAO.findMenu(menu);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
