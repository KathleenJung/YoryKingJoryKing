package controller.review;

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

public class UpdateReviewFormController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(UpdateReviewController.class);

	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int reviewid = Integer.parseInt(request.getParameter("updatereviewid"));
		
		log.debug("UpdateForm Request : {}", reviewid);
		ReviewManager manager = ReviewManager.getInstance();
		ReviewDTO review = manager.findReview(reviewid);	// 사용자 정보 검색
		request.setAttribute("review", review);
		
		
		
		return "/review/updateForm.jsp";   // 검색한 사용자 정보를 update form으로 전송     
		
    }
}
