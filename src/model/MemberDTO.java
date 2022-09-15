package model;

public class MemberDTO {

	private String memberId;
	private String gender;
	private String m_name;
	private String job;
	private String married;
	private String familyNum;
	private String vegitarian;
	private String allergy;
	private String password;
	

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public MemberDTO() { }
	
	public void update(MemberDTO updateMem) {
		//this.memberId = updateMem.memberId;
		this.gender = updateMem.gender;
		this.m_name = updateMem.m_name;
		this.job = updateMem.job;
		this.married = updateMem.married;
		this.familyNum = updateMem.familyNum;
		this.vegitarian = updateMem.vegitarian;
		this.allergy = updateMem.allergy;
        this.password = updateMem.password;
    }

	public MemberDTO(String memberId, String gender, String m_name, String job, String married, String familyNum,
			String vegitarian, String allergy, String password) {
		super();
		this.memberId = memberId;
		this.gender = gender;
		this.m_name = m_name;
		this.job = job;
		this.married = married;
		this.familyNum = familyNum;
		this.vegitarian = vegitarian;
		//this.age = age;
		this.allergy = allergy;
		this.password = password;
	}

	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getMarried() {
		return married;
	}
	public void setMarried(String married) {
		this.married = married;
	}
	public String getAllergy() {
		return allergy;
	}
	public void setAllergy(String allergy) {
		this.allergy = allergy;
	}
	public String getFamilyNum() {
		return familyNum;
	}
	public void setFamilyNum(String familyNum) {
		this.familyNum = familyNum;
	}
	public String getVegitarian() {
		return vegitarian;
	}
	public void setVegitarian(String vegitarian) {
		this.vegitarian = vegitarian;
	}

	public String getM_name() {
		return m_name;
	}
	public void setM_name(String m_name) {
		this.m_name = m_name;
	}
	
	public boolean matchPass(String password) {
		if(password == null) {
			return false;
		}
		return this.password.equals(password);
	}
	
	public boolean isSameMember(MemberDTO member) {
		return memberId.equals(member.memberId);
	}
	
	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", gender=" + gender + ", m_name=" + m_name + ", job=" + job + ", married=" 
					+ married + ", familyNum=" + familyNum + ", vegitarian=" + vegitarian + ", allergy=" 
				+ allergy + ", password=" + password + "]";
	}

}
