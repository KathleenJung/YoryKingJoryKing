package controller.mypage;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.ReviewDTO;
import model.service.MyPageManager;

public class MyReviewListController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
	    String userID = (String) session.getAttribute("memberId");

		try {
			MyPageManager manager = MyPageManager.getInstance();
			List<ReviewDTO> reviewList = manager.findReviewByWriter(userID);
			System.out.println(reviewList);
			request.setAttribute("reviewlistwriter", reviewList);
			return "/mypage/myReviewList.jsp";
			
		}catch(Exception e) {
			return "/mypage/home.jsp";
		}
	}

}
