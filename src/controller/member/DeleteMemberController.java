package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.MemberDTO;
import model.service.MemberManager;

public class DeleteMemberController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(DeleteMemberController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		String deleteId = request.getParameter("memberId");
    	log.debug("Delete Member : {}", deleteId);

    	MemberManager manager = MemberManager.getInstance();		
		HttpSession session = request.getSession();	
	
		if ((MemberSessionUtils.isLoginMember("admin", session) && 	// �α����� ����ڰ� �������̰� 	
			 !deleteId.equals("admin"))							// ���� ����� �Ϲ� ������� ���, 
			   || 												// �Ǵ� 
			(!MemberSessionUtils.isLoginMember("admin", session) &&  // �α����� ����ڰ� �����ڰ� �ƴϰ� 
			  MemberSessionUtils.isLoginMember(deleteId, session))) { // �α����� ����ڰ� ���� ����� ��� (�ڱ� �ڽ��� ����)
				
			manager.remove(deleteId);				// ����� ���� ����
			if (MemberSessionUtils.isLoginMember("admin", session))	// �α����� ����ڰ� ������ 	
				return "redirect:/member/list";		// ����� ����Ʈ�� �̵�
			else 									// �α����� ����ڴ� �̹� ������
				return "redirect:/member/logout";		// logout ó��
		}
		
		/* ������ �Ұ����� ��� */
		MemberDTO memberDto = manager.findMember(deleteId);	// ����� ���� �˻�
		request.setAttribute("memberDto", memberDto);						
		request.setAttribute("deleteFailed", true);
		String msg = (MemberSessionUtils.isLoginMember("admin", session)) 
				   ? "�ý��� ������ ������ ������ �� �����ϴ�."		
				   : "Ÿ���� ������ ������ �� �����ϴ�.";													
		request.setAttribute("exception", new IllegalStateException(msg));            
		return "/member/view.jsp";		// ����� ���� ȭ������ �̵� (forwarding)	
	}
}
