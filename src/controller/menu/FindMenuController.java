package controller.menu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import controller.Controller;
import model.MenuDTO;
import model.service.MenuManager;

public class FindMenuController implements Controller { //메뉴명으로 메뉴검색

	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		String menu = request.getParameter("menu");
	
		try {
			MenuManager manager = MenuManager.getInstance();
			List<MenuDTO> NameMenuList = manager.findMenu(menu);
			request.setAttribute("NameMenuList", NameMenuList);
			return "/menu/findMenu.jsp";
		}catch(Exception e) {
			return "/menu/allMenu.jsp";
		}
    }
}
