package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import controller.Controller;
import model.service.MemberManager;
import model.MemberDTO;

public class UpdateMemberController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(UpdateMemberController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
    	MemberDTO updateMem = new MemberDTO (
    			request.getParameter("memberId"),
    			request.getParameter("gender"),
        		request.getParameter("m_name"),
        		request.getParameter("job"),
        		request.getParameter("married"),
        		request.getParameter("familyNum"),
        		request.getParameter("vegitarian"),
        		request.getParameter("allergy"),
        		request.getParameter("password"));
    	
    	log.debug("Update Member : {}", updateMem);

    	MemberManager manager = MemberManager.getInstance();
		manager.update(updateMem);	
		
        return "redirect:/member/memberdetail.jsp";			
    }
}
