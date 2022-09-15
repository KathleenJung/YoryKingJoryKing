package model;

import java.sql.Date;

public class MessageDTO {

//	messageid, m_content, receiverid, senderid
	
	private int messageid;
	private String m_content = null;
	private String receiverid = null;
	private String senderid = null;
	public Date getMdate() {
		return mdate;
	}

	public void setMdate(Date mdate) {
		this.mdate = mdate;
	}
	private Date mdate;
	
	public MessageDTO() {
		
	}
	
	public MessageDTO(int messageid, String m_content, String receiverid, String senderid, Date mdate) {
		this.messageid = messageid;
		this.m_content = m_content;
		this.receiverid = receiverid;
		this.senderid = senderid;
		this.mdate = mdate;
	}
	
	public int getMessageid() {
		return messageid;
	}
	public void setMessageid(int messageid) {
		this.messageid = messageid;
	}
	public String getM_content() {
		return m_content;
	}
	public void setM_content(String m_content) {
		this.m_content = m_content;
	}
	public String getReceiverid() {
		return receiverid;
	}
	public void setReceiverid(String receiverid) {
		this.receiverid = receiverid;
	}
	public String getSenderid() {
		return senderid;
	}
	public void setSenderid(String senderid) {
		this.senderid = senderid;
	}

}
