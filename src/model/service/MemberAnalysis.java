package model.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.MemberDTO;
import model.dao.MemberDAO;

// an example business class
public class MemberAnalysis {
	private MemberDAO dao;
	
	public MemberAnalysis() {}
	
	public MemberAnalysis(MemberDAO dao) {
		super();
		this.dao = dao;
	}

	// an example business method
	public List<MemberDTO> recommendFriends(String memberId) throws Exception {
		MemberDTO thisuser = dao.findMember(memberId);
		if (thisuser == null) {
			throw new Exception("invalid user ID!");
		}
		
		List<MemberDTO> friends = new ArrayList<MemberDTO>();
		
		List<MemberDTO> userList = dao.findMemberList(1, 10000);
		Iterator<MemberDTO> userIter = userList.iterator();		
		while (userIter.hasNext()) {
			MemberDTO user = (MemberDTO)userIter.next();
			
			if (user.getMemberId().equals(memberId)) continue;		
		}
		return friends;
	}

}
