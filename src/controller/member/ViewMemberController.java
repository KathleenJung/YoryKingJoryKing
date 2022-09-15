package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.MemberManager;
import model.service.MemberNotFoundException;
import model.MemberDTO;

public class ViewMemberController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
    	// �α��� ���� Ȯ��
    	if (!MemberSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/member/login/form";		// login form ��û���� redirect
        }
    	
		MemberManager manager = MemberManager.getInstance();
		String memberId = request.getParameter("memberId");
		
    	MemberDTO member = null;
		try {
			member = manager.findMember(memberId);	// ����� ���� �˻�
		} catch (MemberNotFoundException e) {				
	        return "redirect:/member/detail/from";
		}	
		
		request.setAttribute("member", member);		// ����� ���� ����				
		return "/member/memberdetail.jsp";				// ����� ���� ȭ������ �̵�
    }
}
