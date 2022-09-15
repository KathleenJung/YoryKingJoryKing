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
		jdbcUtil = new JDBCUtil();	// JDBCUtil ��ü ����
	}

	/**
	 * ����� ���� ���̺� ���ο� ����� ����.
	 */
	//	messageid, m_content, receiverid, senderid
	public int create(MessageDTO dto) throws SQLException {
		String sql = "INSERT INTO MESSAGE (messageid, m_content, receiverid, senderid, mdate) "
				+ "VALUES (?, ?, ?, ?, ?)";		
		Object[] param = new Object[] {dto.getMessageid(), dto.getM_content(), 
				dto.getReceiverid(), dto.getSenderid()};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil �� insert���� �Ű� ���� ����

		try {				
			int result = jdbcUtil.executeUpdate();	// insert �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;			
	}

	public List<MessageDTO> findRecipeBySender(String senderid) { // �߽��ڷ� �˻�
		String sql = "SELECT messageid, m_content, receiverid, senderid, mdate " 
				+ "FROM message "
				+ "where senderid=? "
				+ "ORDER BY mdate";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {senderid});		// JDBCUtil�� query�� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query ����			
			List<MessageDTO> searchList = new ArrayList<MessageDTO>();	// User���� ����Ʈ ����
			while (rs.next()) {
				//recipeDTO(String recipeID, String writerID, String content, int hard, String time, String menuID,String video)
				MessageDTO dto = new MessageDTO(			// User ��ü�� �����Ͽ� ���� ���� ������ ����
						rs.getInt("messageid"),
						rs.getString("m_content"),
						rs.getString("receiverid"),
						rs.getString("senderid"),
						rs.getDate("mdate"));	
				searchList.add(dto);				// List�� User ��ü ����
			}		
			return searchList;					

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}

	public List<MessageDTO> findUserList(int currentPage, int countPerPage) throws SQLException { // ���
		String sql = "SELECT messageid, m_content, receiverid, senderid, mdate " 
				+ "FROM MESSAGE "
				+ "ORDER BY mdate";
		jdbcUtil.setSqlAndParameters(sql, null,					// JDBCUtil�� query�� ����
				ResultSet.TYPE_SCROLL_INSENSITIVE,				// cursor scroll ����
				ResultSet.CONCUR_READ_ONLY);						

		try {
			ResultSet rs = jdbcUtil.executeQuery();				// query ����			
			int start = ((currentPage-1) * countPerPage) + 1;	// ����� ������ �� ��ȣ ���
			if ((start >= 0) && rs.absolute(start)) {			// Ŀ���� ���� ������ �̵�
				List<MessageDTO> messageList = new ArrayList<MessageDTO>();	// User���� ����Ʈ ����
				do {
					MessageDTO dto = new MessageDTO(		// User ��ü�� �����Ͽ� ���� ���� ������ ����
							rs.getInt("messageid"),
							rs.getString("m_content"),
							rs.getString("receiverid"),
							rs.getString("senderid"),
							rs.getDate("mdate"));	
					messageList.add(dto);							// ����Ʈ�� User ��ü ����
				} while ((rs.next()) && (--countPerPage > 0));		
				return messageList;							
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
}
