package model.service;

import java.sql.SQLException;
import java.util.List;

import model.MemberDTO;
import model.dao.MemberDAO;

public class MemberManager {
	private static MemberManager memberManager = new MemberManager();
	private MemberDAO memberDAO;
	private MemberAnalysis memberAanlysis;
	
	private MemberManager() {
		try {
			memberDAO = new MemberDAO();
			memberAanlysis = new MemberAnalysis(memberDAO);
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static MemberManager getInstance() {
		return memberManager;
	}
	
	public int create(MemberDTO mem) throws SQLException, ExistingMemberException {
		if (memberDAO.existingMember(mem.getMemberId()) == true) {
			throw new ExistingMemberException(mem.getMemberId() + "�� �����ϴ� ���̵��Դϴ�.");
		}
		return memberDAO.create(mem);
	}
		
	public int update(MemberDTO mem) throws SQLException {
		return memberDAO.update(mem);
	}

	
	public int remove(String memberId) throws SQLException {
		return memberDAO.remove(memberId);
	}
	
	public MemberDTO findMember(String memberId) 
		throws SQLException, MemberNotFoundException {
		MemberDTO member = memberDAO.findMember(memberId);
		
		if (member == null) {
			throw new MemberNotFoundException(memberId + "�� �������� �ʴ� ���̵��Դϴ�.");
		}		
		return member;
	}
	
	public List<MemberDTO> findMemberList() throws SQLException {
		return memberDAO.findMemberList();
	}

	public List<MemberDTO> findMemberList(int currentPage, int countPerPage)
		throws SQLException {
		return memberDAO.findMemberList(currentPage, countPerPage);
	}	

	public boolean login(String memberId, String password)
		throws SQLException, MemberNotFoundException, PasswordMismatchException {
		MemberDTO mem = findMember(memberId);

		if (!mem.matchPass(password)) {
			throw new PasswordMismatchException("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
		}
		return true;
	}

	public List<MemberDTO> makeFriends(String memberId) throws Exception {
		return memberAanlysis.recommendFriends(memberId);
	}
	
	public MemberDAO getMemberDAO() {
		return this.memberDAO;
	}


}