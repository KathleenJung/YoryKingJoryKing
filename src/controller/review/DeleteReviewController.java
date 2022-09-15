package controller.review;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.RecipeDTO;
import model.ReviewDTO;
import model.service.RecipeManager;
import model.service.ReviewManager;

public class DeleteReviewController implements Controller {

	private static final Logger log = LoggerFactory.getLogger(DeleteReviewController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		int deleteReview = Integer.parseInt(request.getParameter("deletereviewid"));
		
    	log.debug("Delete reviewDTO : {}", deleteReview);

		ReviewManager manager = ReviewManager.getInstance();	
		
	
		log.info("delete ========= " + deleteReview);
		manager.remove(deleteReview);	
		
		return "redirect:/mypage/myReview"; 	// redirect ½ÇÇà
	}
}
