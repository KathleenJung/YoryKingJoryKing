package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.MemberDTO;
import model.service.ExistingMemberException;
import model.service.MemberManager;

public class JoinController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(JoinController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	MemberDTO member = new MemberDTO(
			request.getParameter("memberId"),
			request.getParameter("gender"),
    		request.getParameter("m_name"),
    		request.getParameter("job"),
    		request.getParameter("married"),
    		request.getParameter("familyNum"),
    		request.getParameter("vegitarian"),
    		request.getParameter("allergy"),
    		request.getParameter("password"));
    		
    		
        log.debug("Create Member : {}", member);

		try {
			MemberManager manager = MemberManager.getInstance();
			manager.create(member);
	        return "redirect:/member/login/form";		
	        
		} catch (ExistingMemberException e) {		// 예외 발생 시 회원가입 form으로 forwarding
            request.setAttribute("joinFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("member", member);
			return "/member/main/form";
		}
    }
}
