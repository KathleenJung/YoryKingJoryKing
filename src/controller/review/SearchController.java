package controller.review;

import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.ReviewDTO;
import model.service.ReviewManager;

public class SearchController implements Controller {

	public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {


		String title = request.getParameter("title");

		try {
			ReviewManager manager = ReviewManager.getInstance();
			List<ReviewDTO> list = manager.findReviewListByTitle(title);
			request.setAttribute("reviewlist", list);
			return "/review/search.jsp";
		}catch(Exception e) {
			return "/recipe/allRecipe.jsp";
		}
	}


}

