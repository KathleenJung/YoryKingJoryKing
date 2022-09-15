package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import model.MemberDTO;

public class MemberDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	private DataSource ds;
	private static JDBCUtil jdbcUtil = null;
	
	private static MemberDAO instance = new MemberDAO();
	
	public static MemberDAO getInstance() {
		if(instance == null)
			instance = new MemberDAO();
		return instance;
	}
	
	public MemberDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil ��ü ����
	}
	
	private Connection getOracle() throws Exception {
		Context ct = new InitialContext();
		Context en = (Context)ct.lookup("java:comp/en");
		DataSource ds = (DataSource)en.lookup("jdbc/orcl");
		
		return ds.getConnection();
	}
	
	// ����� ���� �߰�
	public int create(MemberDTO mem) throws SQLException{
		String sql = "INSERT INTO memberdetail (memberId, gender, m_name, job, married, familyNum, vegitarian, allergy, password) values(?,?,?,?,?,?,?,?,?)";		
	
		Object[] param = new Object[] {mem.getMemberId(), mem.getGender(), 
			mem.getM_name(), mem.getJob(), mem.getMarried(), mem.getFamilyNum(), mem.getVegitarian(),
			mem.getAllergy(), mem.getPassword()};	
	
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
	
	public MemberDTO getMemberInfo(String memberId) throws SQLException{
		String query = "select * from memberdetail where memberId=?";
		
		jdbcUtil.setSqlAndParameters(query, new Object[] {memberId});
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			
			if(rs.next()) {
				MemberDTO mem = new MemberDTO(			// User ��ü�� �����Ͽ� ���� ���� ������ ����
						rs.getString("memberId"),
						rs.getString("gender"),
						rs.getString("m_name"),
						rs.getString("job"),
						rs.getString("married"),
						rs.getString("familyNum"),
						rs.getString("vegitarian"),
						rs.getString("allergy"),
						rs.getString("password"));
				return mem;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}
	
	// �����  ID ������  DB���� ã�� member ������ Ŭ������ �����Ͽ� ��ȯ
	public MemberDTO findMember(String memberId) throws SQLException{
		//private static 
		String query = "Select memberId, gender, m_name, job, married, allergy, familyNum, vegitarian, password From memberdetail WHERE memberId=?";
		
		jdbcUtil.setSqlAndParameters(query, new Object[] {memberId});
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			
			if(rs.next()) {
				MemberDTO mem = new MemberDTO(			// User ��ü�� �����Ͽ� ���� ���� ������ ����
						rs.getString("memberId"),
						rs.getString("gender"),
						rs.getString("m_name"),
						rs.getString("job"),
						rs.getString("married"),
						rs.getString("familyNum"),
						rs.getString("vegitarian"),
						rs.getString("allergy"),
						rs.getString("password"));
				return mem;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}
	
	public List<MemberDTO> findMemberList() throws SQLException {
        String sql = "SELECT memberId, gender, m_name, job, married, familyNum, vegitarian, allergy, password FROM memberdetail ORDER BY memberId";
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil�� query�� ����
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query ����			
			List<MemberDTO> memberList = new ArrayList<MemberDTO>();	// User���� ����Ʈ ����
			while (rs.next()) {
				MemberDTO mem = new MemberDTO(			// User ��ü�� �����Ͽ� ���� ���� ������ ����
						rs.getString("memberId"),
						rs.getString("gender"),
						rs.getString("m_name"),
						rs.getString("job"),
						rs.getString("married"),
						rs.getString("familyNum"),
						rs.getString("vegitarian"),
						rs.getString("allergy"),
						rs.getString("password"));
				memberList.add(mem);				// List�� User ��ü ����
			}		
			return memberList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
	
	/**
	 * ��ü ����� ������ �˻��� �� ���� �������� �������� ����� ����� ���� �̿��Ͽ�
	 * �ش��ϴ� ����� �������� List�� �����Ͽ� ��ȯ.
	 */
	public List<MemberDTO> findMemberList(int currentPage, int countPerPage) throws SQLException {
        String sql = "SELECT memberId, gender, m_name, job, married, familyNum, vegitarian, allergy, password FROM memberdetail ORDER BY memberId";
		jdbcUtil.setSqlAndParameters(sql, null,					// JDBCUtil�� query�� ����
				ResultSet.TYPE_SCROLL_INSENSITIVE,				// cursor scroll ����
				ResultSet.CONCUR_READ_ONLY);						
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();				// query ����			
			int start = ((currentPage-1) * countPerPage) + 1;	// ����� ������ �� ��ȣ ���
			if ((start >= 0) && rs.absolute(start)) {			// Ŀ���� ���� ������ �̵�
				List<MemberDTO> findMemberList = new ArrayList<MemberDTO>();	// User���� ����Ʈ ����
				do {
					MemberDTO mem = new MemberDTO(		// User ��ü�� �����Ͽ� ���� ���� ������ ����
							rs.getString("memberId"),
							rs.getString("gender"),
							rs.getString("m_name"),
							rs.getString("job"),
							rs.getString("married"),
							rs.getString("familyNum"),
							rs.getString("vegitarian"),
							rs.getString("allergy"),
							rs.getString("password"));
					findMemberList.add(mem);							// ����Ʈ�� User ��ü ����
				} while ((rs.next()) && (--countPerPage > 0));		
				return findMemberList;
			}
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				jdbcUtil.close();		// resource ��ȯ
			}
			return null;
		}
	
	//������ ����� ������ ����
	public int update(MemberDTO mem) throws SQLException{
		String sql = "UPDATE MEMBERDETAIL SET gender=?, m_name=?, job=?, married=?, familyNum=?, vegitarian=?, allergy=?, password=? WHERE memberId=?";
			
		Object[] param = new Object[] {mem.getGender(), mem.getM_name(), mem.getJob(), mem.getMarried(), mem.getFamilyNum(), mem.getVegitarian(),
					mem.getAllergy(), mem.getPassword(), mem.getMemberId()};				
		
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil�� update���� �Ű� ���� ����
			
		try {				
			int result = jdbcUtil.executeUpdate();	// update �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;
	}
	
	//����� ���̵� �ش��ϴ� ����ڸ� ����
	public int remove(String memberId) throws SQLException {
		String sql = "DELETE FROM memberdetail WHERE memberId=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {memberId});	// JDBCUtil�� delete���� �Ű� ���� ����

		try {				
			int result = jdbcUtil.executeUpdate();	// delete �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;
	}

	public boolean existingMember(String memberId) {
		String sql = "SELECT count(*) FROM memberdetail WHERE memberId=?";      
		jdbcUtil.setSqlAndParameters(sql, new Object[] {memberId});	// JDBCUtil�� query���� �Ű� ���� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return false;
	}

}