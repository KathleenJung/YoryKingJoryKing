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
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}
	
	private Connection getOracle() throws Exception {
		Context ct = new InitialContext();
		Context en = (Context)ct.lookup("java:comp/en");
		DataSource ds = (DataSource)en.lookup("jdbc/orcl");
		
		return ds.getConnection();
	}
	
	// 사용자 정보 추가
	public int create(MemberDTO mem) throws SQLException{
		String sql = "INSERT INTO memberdetail (memberId, gender, m_name, job, married, familyNum, vegitarian, allergy, password) values(?,?,?,?,?,?,?,?,?)";		
	
		Object[] param = new Object[] {mem.getMemberId(), mem.getGender(), 
			mem.getM_name(), mem.getJob(), mem.getMarried(), mem.getFamilyNum(), mem.getVegitarian(),
			mem.getAllergy(), mem.getPassword()};	
	
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
	
	public MemberDTO getMemberInfo(String memberId) throws SQLException{
		String query = "select * from memberdetail where memberId=?";
		
		jdbcUtil.setSqlAndParameters(query, new Object[] {memberId});
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			
			if(rs.next()) {
				MemberDTO mem = new MemberDTO(			// User 객체를 생성하여 현재 행의 정보를 저장
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
	
	// 사용자  ID 정보를  DB에서 찾아 member 도메인 클래스에 저장하여 반환
	public MemberDTO findMember(String memberId) throws SQLException{
		//private static 
		String query = "Select memberId, gender, m_name, job, married, allergy, familyNum, vegitarian, password From memberdetail WHERE memberId=?";
		
		jdbcUtil.setSqlAndParameters(query, new Object[] {memberId});
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			
			if(rs.next()) {
				MemberDTO mem = new MemberDTO(			// User 객체를 생성하여 현재 행의 정보를 저장
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
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil에 query문 설정
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 실행			
			List<MemberDTO> memberList = new ArrayList<MemberDTO>();	// User들의 리스트 생성
			while (rs.next()) {
				MemberDTO mem = new MemberDTO(			// User 객체를 생성하여 현재 행의 정보를 저장
						rs.getString("memberId"),
						rs.getString("gender"),
						rs.getString("m_name"),
						rs.getString("job"),
						rs.getString("married"),
						rs.getString("familyNum"),
						rs.getString("vegitarian"),
						rs.getString("allergy"),
						rs.getString("password"));
				memberList.add(mem);				// List에 User 객체 저장
			}		
			return memberList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
	
	/**
	 * 전체 사용자 정보를 검색한 후 현재 페이지와 페이지당 출력할 사용자 수를 이용하여
	 * 해당하는 사용자 정보만을 List에 저장하여 반환.
	 */
	public List<MemberDTO> findMemberList(int currentPage, int countPerPage) throws SQLException {
        String sql = "SELECT memberId, gender, m_name, job, married, familyNum, vegitarian, allergy, password FROM memberdetail ORDER BY memberId";
		jdbcUtil.setSqlAndParameters(sql, null,					// JDBCUtil에 query문 설정
				ResultSet.TYPE_SCROLL_INSENSITIVE,				// cursor scroll 가능
				ResultSet.CONCUR_READ_ONLY);						
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();				// query 실행			
			int start = ((currentPage-1) * countPerPage) + 1;	// 출력을 시작할 행 번호 계산
			if ((start >= 0) && rs.absolute(start)) {			// 커서를 시작 행으로 이동
				List<MemberDTO> findMemberList = new ArrayList<MemberDTO>();	// User들의 리스트 생성
				do {
					MemberDTO mem = new MemberDTO(		// User 객체를 생성하여 현재 행의 정보를 저장
							rs.getString("memberId"),
							rs.getString("gender"),
							rs.getString("m_name"),
							rs.getString("job"),
							rs.getString("married"),
							rs.getString("familyNum"),
							rs.getString("vegitarian"),
							rs.getString("allergy"),
							rs.getString("password"));
					findMemberList.add(mem);							// 리스트에 User 객체 저장
				} while ((rs.next()) && (--countPerPage > 0));		
				return findMemberList;
			}
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				jdbcUtil.close();		// resource 반환
			}
			return null;
		}
	
	//기존의 사용자 정보를 수정
	public int update(MemberDTO mem) throws SQLException{
		String sql = "UPDATE MEMBERDETAIL SET gender=?, m_name=?, job=?, married=?, familyNum=?, vegitarian=?, allergy=?, password=? WHERE memberId=?";
			
		Object[] param = new Object[] {mem.getGender(), mem.getM_name(), mem.getJob(), mem.getMarried(), mem.getFamilyNum(), mem.getVegitarian(),
					mem.getAllergy(), mem.getPassword(), mem.getMemberId()};				
		
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil에 update문과 매개 변수 설정
			
		try {				
			int result = jdbcUtil.executeUpdate();	// update 문 실행
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
	
	//사용자 아이디에 해당하는 사용자를 삭제
	public int remove(String memberId) throws SQLException {
		String sql = "DELETE FROM memberdetail WHERE memberId=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {memberId});	// JDBCUtil에 delete문과 매개 변수 설정

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

	public boolean existingMember(String memberId) {
		String sql = "SELECT count(*) FROM memberdetail WHERE memberId=?";      
		jdbcUtil.setSqlAndParameters(sql, new Object[] {memberId});	// JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return false;
	}

}