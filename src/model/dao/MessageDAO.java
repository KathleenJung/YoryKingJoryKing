package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.MessageDTO;
import model.RecipeDTO;

public class MessageDAO {
	private JDBCUtil jdbcUtil = null;

	public MessageDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}

	/**
	 * 사용자 관리 테이블에 새로운 사용자 생성.
	 */
	//	messageid, m_content, receiverid, senderid
	public int create(MessageDTO dto) throws SQLException {
		String sql = "INSERT INTO MESSAGE (messageid, m_content, receiverid, senderid, mdate) "
				+ "VALUES (?, ?, ?, ?, ?)";		
		Object[] param = new Object[] {dto.getMessageid(), dto.getM_content(), 
				dto.getReceiverid(), dto.getSenderid()};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil 에 insert문과 매개 변수 설정

		try {				
			int result = jdbcUtil.executeUpdate();	// insert 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;			
	}

	public List<MessageDTO> findRecipeBySender(String senderid) { // 발신자로 검색
		String sql = "SELECT messageid, m_content, receiverid, senderid, mdate " 
				+ "FROM message "
				+ "where senderid=? "
				+ "ORDER BY mdate";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {senderid});		// JDBCUtil에 query문 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 실행			
			List<MessageDTO> searchList = new ArrayList<MessageDTO>();	// User들의 리스트 생성
			while (rs.next()) {
				//recipeDTO(String recipeID, String writerID, String content, int hard, String time, String menuID,String video)
				MessageDTO dto = new MessageDTO(			// User 객체를 생성하여 현재 행의 정보를 저장
						rs.getInt("messageid"),
						rs.getString("m_content"),
						rs.getString("receiverid"),
						rs.getString("senderid"),
						rs.getDate("mdate"));	
				searchList.add(dto);				// List에 User 객체 저장
			}		
			return searchList;					

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}

	public List<MessageDTO> findUserList(int currentPage, int countPerPage) throws SQLException { // 출력
		String sql = "SELECT messageid, m_content, receiverid, senderid, mdate " 
				+ "FROM MESSAGE "
				+ "ORDER BY mdate";
		jdbcUtil.setSqlAndParameters(sql, null,					// JDBCUtil에 query문 설정
				ResultSet.TYPE_SCROLL_INSENSITIVE,				// cursor scroll 가능
				ResultSet.CONCUR_READ_ONLY);						

		try {
			ResultSet rs = jdbcUtil.executeQuery();				// query 실행			
			int start = ((currentPage-1) * countPerPage) + 1;	// 출력을 시작할 행 번호 계산
			if ((start >= 0) && rs.absolute(start)) {			// 커서를 시작 행으로 이동
				List<MessageDTO> messageList = new ArrayList<MessageDTO>();	// User들의 리스트 생성
				do {
					MessageDTO dto = new MessageDTO(		// User 객체를 생성하여 현재 행의 정보를 저장
							rs.getInt("messageid"),
							rs.getString("m_content"),
							rs.getString("receiverid"),
							rs.getString("senderid"),
							rs.getDate("mdate"));	
					messageList.add(dto);							// 리스트에 User 객체 저장
				} while ((rs.next()) && (--countPerPage > 0));		
				return messageList;							
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
}
