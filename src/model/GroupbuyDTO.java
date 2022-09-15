package model;

public class GroupbuyDTO {
	
//	groupbuy : gbid, gbcontent, gbdeadline, writerid
//	gbcomment : gbcomid, gbccontent, gb_applicantid, gbid

	private int gbid;
	public int getGbid() {
		return gbid;
	}
	public void setGbid(int gbid) {
		this.gbid = gbid;
	}
	public String getGbContent() {
		return gbContent;
	}
	public void setGbContent(String gbContent) {
		this.gbContent = gbContent;
	}
	public int getGbDeadline() {
		return gbDeadline;
	}
	public void setGbDeadline(int gbDeadline) {
		this.gbDeadline = gbDeadline;
	}
	public String getWriterID() {
		return writerID;
	}
	public void setWriterID(String writerID) {
		this.writerID = writerID;
	}
	public int getGbComId() {
		return gbComId;
	}
	public void setGbComId(int gbComId) {
		this.gbComId = gbComId;
	}
	public String getGbCContent() {
		return gbCContent;
	}
	public void setGbCContent(String gbCContent) {
		this.gbCContent = gbCContent;
	}
	public String getGbApplicantid() {
		return gbApplicantid;
	}
	public void setGbApplicantid(String gbApplicantid) {
		this.gbApplicantid = gbApplicantid;
	}
	private String gbContent = null;
	private int gbDeadline;
	private String writerID = null;
	private int gbComId;
	private String gbCContent = null;
	private String gbApplicantid = null;
	
	
}
