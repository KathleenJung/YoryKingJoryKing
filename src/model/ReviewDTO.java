package model;

import java.sql.Date;

public class ReviewDTO {
	

	public int getReviewid() {
		return reviewid;
	}
	public void setReviewid(int reviewid) {
		this.reviewid = reviewid;
	}
	public String getWriterid() {
		return writerid;
	}
	public void setWriterid(String writerid) {
		this.writerid = writerid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getStars() {
		return stars;
	}
	public void setStars(int stars) {
		this.stars = stars;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getRecipeid() {
		return recipeid;
	}
	public void setRecipeid(int recipeid) {
		this.recipeid = recipeid;
	}
	private int reviewid;
	private String writerid;
	private String content;
	private int stars;
	private String title;
	private int recipeid;
	
	
	public ReviewDTO() {
		
	}
	public ReviewDTO(int reviewid, String title, String content) {
		this.reviewid = reviewid;
		this.title=title;
		this.content=content;
	}
	public ReviewDTO(String title, String content, int stars) {
		this.title=title;
		this.content=content;
		this.stars=stars;
	}
	public ReviewDTO(int reviewid, String title, String content, int stars) {
		this.reviewid = reviewid;
		this.title=title;
		this.content=content;
		this.stars=stars;
	}
	public ReviewDTO(int reviewid, String title, int stars, String content) {
		this.reviewid = reviewid;
		this.title=title;
		this.stars=stars;
		this.content=content;
	}
	public ReviewDTO(int recipeid, String title, String content, int stars, int reviewid) {
		this.recipeid = recipeid;
		this.title=title;
		this.content=content;
		this.stars=stars;
		this.reviewid=reviewid;
	}
	public ReviewDTO(String writerid, String title, String content, int stars, int reviewid) {
		this.writerid=writerid;
		this.title=title;
		this.content=content;
		this.stars=stars;
		this.reviewid=reviewid;
	}
	public ReviewDTO(String writerid, String title, int stars, String content) {
		this.writerid=writerid;
		this.title=title;
		this.stars=stars;
		this.content=content;
	}
	public ReviewDTO(String writerid, int recipeid, String title, int stars, String content) {
		this.writerid=writerid;
		this.recipeid=recipeid;
		this.title=title;
		this.stars=stars;
		this.content=content;
	}
	public ReviewDTO(int reviewid, String writerid, int recipeid, String content, int stars, String title) {
		this.reviewid=reviewid;
		this.writerid=writerid;
		this.recipeid=recipeid;
		this.content=content;
		this.stars=stars;
		this.title=title;
	}
	
	public ReviewDTO(int reviewid, String writerid, String content, int stars, String title, int recipeid) {
		this.reviewid = reviewid;
		this.writerid = writerid;
		this.content = content;
		this.stars = stars;
		this.title = title;
		this.recipeid = recipeid;
	}
	public ReviewDTO(String title, int reviewid, String writerid, String content, int stars) {
		this.title = title;
		this.reviewid = reviewid;
		this.writerid = writerid;
		this.content = content;
		this.stars = stars;
	}
}
