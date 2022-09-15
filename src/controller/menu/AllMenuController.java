package controller.menu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;

import java.util.List;

import model.MenuDTO;
import model.service.MenuManager;

public class AllMenuController implements Controller {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		MenuManager manager = MenuManager.getInstance();
		List<MenuDTO> menuList = manager.allMenuList();
		
		request.setAttribute("allMenuList", menuList);
		
		return "/menu/allMenu.jsp";
	}
}