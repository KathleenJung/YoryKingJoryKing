package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.service.MemberManager;
import model.MemberDTO;


public class UpdateMemberFormController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(UpdateMemberController.class);

	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String updateId = request.getParameter("memberId");
		
		log.debug("UpdateForm Request : {}", updateId);

		MemberManager manager = MemberManager.getInstance();
		MemberDTO member = manager.findMember(updateId);	// ����� ���� �˻�
		request.setAttribute("member", member);						
		
		HttpSession session = request.getSession();
		if (MemberSessionUtils.isLoginMember(updateId, session) ||
			MemberSessionUtils.isLoginMember("admin", session)) {
			// ���� �α����� ����ڰ� ���� ��� ������̰ų� �������� ��� -> ���� ����
			
			return "/member/updateForm.jsp";   // �˻��� ����� ������ update form���� ����     
		}
		
		// else (���� �Ұ����� ���) ����� ���� ȭ������ ���� �޼����� ����
		request.setAttribute("updateFailed", true);
		request.setAttribute("exception", 
			new IllegalStateException("Ÿ���� ������ ������ �� �����ϴ�."));            
		return "/member/view.jsp";	// ����� ���� ȭ������ �̵� (forwarding)
    }
}
