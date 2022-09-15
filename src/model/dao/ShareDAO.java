package model.dao;

import java.util.List;
import java.util.ArrayList;
import java.sql.*;

import model.ShareDTO;

public class ShareDAO {



	private JDBCUtil jdbcUtil = new JDBCUtil();
	//	shareid, memberid, shcontent, shdeadline

	//	글 작성
	public int create(ShareDTO shareDTO) throws SQLException {
		String query = "INSERT INTO SHARING VALUES (?, ?, ?, ?)";
		Object[] param = new Object[] {shareDTO.getShareid(), shareDTO.getMemberid(),
				shareDTO.getShcontent(), shareDTO.getShdeadline() };
		jdbcUtil.setSqlAndParameters(query, param);
		try {
			int result = jdbcUtil.executeUpdate();
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return 0;
	}

	//	글 수정
	public int update(ShareDTO shareDTO) throws SQLException {
		String query = "UPDATE SHARING " + "SET shareid=?, shdeadline=?, shcontent=? WHERE memberid=?";
		Object[] param = new Object[] {shareDTO.getShareid(), shareDTO.getShdeadline(),
				shareDTO.getShcontent(), shareDTO.getMemberid()};
		jdbcUtil.setSqlAndParameters(query, param);
		try {
			int result = jdbcUtil.executeUpdate();
			return result;
		}catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return 0;
	}

	//	글 삭제
	public int remove(String memberid) throws SQLException {
		String query = "DELETE " + "FROM REVIEW WHERE memberid=?";
		jdbcUtil.setSqlAndParameters(query, new Object[] { memberid });
		try {				
			int result = jdbcUtil.executeUpdate();	// delete 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;
	}

	//	글 보기
	public List<ShareDTO> findSharingList(int currentPage, int countPerPage) throws SQLException {
		String query = "SELECT shareid, shcontent, shdeadline, memberid " +
				"FROM SHARING";
		jdbcUtil.setSqlAndParameters(query, null);
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			int start = ((currentPage-1) * countPerPage) + 1;
			List<ShareDTO> sharingList = null;
			if((start>=0)&&rs.absolute(start)) {
				sharingList = new ArrayList<ShareDTO>();
				do {
					ShareDTO dto = new ShareDTO();
					dto.setShareid(rs.getInt("shareid"));
					dto.setShcontent(rs.getString("shcontent"));
					dto.setShdeadline(rs.getInt("shdeadline"));
					dto.setMemberid(rs.getString("memberid"));
					sharingList.add(dto);
				} while(rs.next() && (--countPerPage > 0));
			}
			return sharingList;
		}catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return null;
	}

	//	작성자 아이디로 검색
	public List<ShareDTO> findBymemberID(String memberID, int currentPage, int countPerpage) throws SQLException {
		String query = "SELECT shareid, shcontent, shdeadline, memberID " +
				"FROM SHARING " +
				"WHERE memberID=?";
		jdbcUtil.setSqlAndParameters(query, new Object[] { memberID });
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			int start = ((currentPage-1) * countPerpage) + 1;
			List<ShareDTO> contentList = null;
			if((start>=0)&&rs.absolute(start)) {
				contentList = new ArrayList<ShareDTO>();
				do {
					ShareDTO dto = new ShareDTO();
					dto = new ShareDTO();
					dto.setMemberid(memberID);
					dto.setShareid(rs.getInt("shareid"));
					dto.setShcontent(rs.getString("shcontent"));
					dto.setShdeadline(rs.getInt("shdeadline"));
					contentList.add(dto);
				} while(rs.next() && (--countPerpage > 0));
			}
			return contentList;
		}catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return null;
	}


}
