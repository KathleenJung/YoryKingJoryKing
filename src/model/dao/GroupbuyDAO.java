package model.dao;

import java.util.List;
import java.util.ArrayList;
import java.sql.*;

import model.GroupbuyDTO;

public class GroupbuyDAO {


	private JDBCUtil jdbcUtil = new JDBCUtil();
	
	public void groupbuyrDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}

	//	글 작성
	public int create(GroupbuyDTO groupDTO) throws SQLException {
		String query = "INSERT INTO GROUPBUY VALUES (?, ?, ?, ?)";
		Object[] param = new Object[] {groupDTO.getGbid(), groupDTO.getGbContent(),
				groupDTO.getGbDeadline(), groupDTO.getWriterID() };
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
	public int update(GroupbuyDTO groupDTO) throws SQLException {
		String query = "UPDATE GROUPBUY " + "SET gbid=?, gbContent=?, gbDeadline=? WHERE writerID=?";
		Object[] param = new Object[] {groupDTO.getGbid(), groupDTO.getGbContent(),
				groupDTO.getGbDeadline(), groupDTO.getWriterID() };
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
	public int remove(String writerID) throws SQLException {
		String query = "DELETE " + "FROM GROUPBUY WHERE memberid=?";
		jdbcUtil.setSqlAndParameters(query, new Object[] { writerID });
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
	public List<GroupbuyDTO> findGbList(int currentPage, int countPerPage) throws SQLException {
		String query = "SELECT gbid, gbContent, gbDeadline, writerID " +
				"FROM GROUPBUY";
		jdbcUtil.setSqlAndParameters(query, null);
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			int start = ((currentPage-1) * countPerPage) + 1;
			List<GroupbuyDTO> gbList = null;
			if((start>=0)&&rs.absolute(start)) {
				gbList = new ArrayList<GroupbuyDTO>();
				do {
					GroupbuyDTO dto = new GroupbuyDTO();
					dto.setGbid(rs.getInt("shareid"));
					dto.setGbContent(rs.getString("shcontent"));
					dto.setGbDeadline(rs.getInt("shdeadline"));
					dto.setWriterID(rs.getString("memberid"));
					gbList.add(dto);
				} while(rs.next() && (--countPerPage > 0));
			}
			return gbList;
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
	public List<GroupbuyDTO> findBywriterID(String writerID, int currentPage, int countPerpage) throws SQLException {
		String query = "SELECT gbid, gbcontent, gbdeadline, writerID " +
				"FROM GROUPBUY " +
				"WHERE writerID=?";
		jdbcUtil.setSqlAndParameters(query, new Object[] {writerID});
		try {ResultSet rs = jdbcUtil.executeQuery();
		int start = ((currentPage-1) * countPerpage) + 1;
		List<GroupbuyDTO> contentList = null;
		if((start>=0)&&rs.absolute(start)) {
			contentList = new ArrayList<GroupbuyDTO>();
			do {
				GroupbuyDTO dto = new GroupbuyDTO();
				dto = new GroupbuyDTO();
				dto.setWriterID(writerID);
				dto.setGbid(rs.getInt("gbid"));
				dto.setGbContent(rs.getString("gbcontent"));
				dto.setGbDeadline(rs.getInt("gbdeadline"));
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

