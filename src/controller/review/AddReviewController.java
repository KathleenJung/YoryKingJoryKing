package controller.review;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import controller.Controller;
import model.RecipeDTO;
import model.ReviewDTO;
import model.service.RecipeManager;
import model.service.ReviewManager;

public class AddReviewController implements Controller {

	private static final Logger log = (Logger) LoggerFactory.getLogger(AddReviewController.class);


	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("memberId");
		
		RecipeManager manager1 = RecipeManager.getInstance();
		
		int recipeid = Integer.parseInt(request.getParameter("addrecipeid"));
		RecipeDTO dto = manager1.viewRecipe(recipeid);	    
	    
	    request.setAttribute("recipeDTO", dto);
		

		ReviewDTO addReview = new ReviewDTO(
				userid,
				recipeid,
				request.getParameter("title"),
				Integer.parseInt(request.getParameter("stars")),
				request.getParameter("content")
				);


		log.debug("Create Review : {}", addReview);
		
		try {
			ReviewManager manager = ReviewManager.getInstance();
			manager.create(addReview);
	         return "redirect:/mypage/myReview";  	// redirect ½ÇÇà
	           
	           
	      } catch (Exception e) {
	         return "/review/addForm.jsp";
	      }
	

	}


}
