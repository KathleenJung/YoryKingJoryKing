package model;

public class ShareDTO {
	
//	shareid, memberid, shcontent, shdeadline
//	shcomid, shc_content, memberid, shareid
	private int shareid;
	public int getShareid() {
		return shareid;
	}
	public void setShareid(int shareid) {
		this.shareid = shareid;
	}
	public String getMemberid() {
		return memberid;
	}
	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}
	public String getShcontent() {
		return shcontent;
	}
	public void setShcontent(String shcontent) {
		this.shcontent = shcontent;
	}
	public int getShdeadline() {
		return shdeadline;
	}
	public void setShdeadline(int shdeadline) {
		this.shdeadline = shdeadline;
	}
	public String getShcomid() {
		return shcomid;
	}
	public void setShcomid(String shcomid) {
		this.shcomid = shcomid;
	}
	public String getShc_content() {
		return shc_content;
	}
	public void setShc_content(String shc_content) {
		this.shc_content = shc_content;
	}
	private String memberid = null;
	private String shcontent = null;
	private int shdeadline;
	private String shcomid = null;
	private String shc_content = null;
}