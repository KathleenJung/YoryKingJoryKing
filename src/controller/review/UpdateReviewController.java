package controller.review;

import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import controller.Controller;
import controller.DispatcherServlet;
import model.service.RecipeManager;
import model.service.ReviewManager;
import model.RecipeDTO;
import model.ReviewDTO;

public class UpdateReviewController implements Controller {
	private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {


		ReviewManager manager = ReviewManager.getInstance();
		


		int reviewid = Integer.parseInt(request.getParameter("updatereviewid"));

		ReviewDTO updateReview = new ReviewDTO(
				reviewid,
				request.getParameter("title"),
				Integer.parseInt(request.getParameter("stars")),
				request.getParameter("content")
				);    

		manager.update(updateReview);			
		return "redirect:/mypage/myReview"; 
	}
}


