package com.newlecture.web.entity;

import java.util.Date;

public class Notice {
	private int id;
	private String title;
	private String writerId;
	private Date regDate;
	private String hit;
	private String files;
	private String content;
	
	//기본 생성자
	public Notice() {
		
	}
	
	//오버로드 생성자 
	public Notice(int id, String title, String writerId, Date regDate, String hit, String files, String content) {

		this.id = id;
		this.title = title;
		this.writerId = writerId;
		this.regDate = regDate;
		this.hit = hit;
		this.files = files;
		this.content = content;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriterId() {
		return writerId;
	}

	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getHit() {
		return hit;
	}

	public void setHit(String hit) {
		this.hit = hit;
	}

	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Notice [id=" + id + ", title=" + title + ", writerId=" + writerId + ", regDate=" + regDate + ", hit="
				+ hit + ", files=" + files + ", content=" + content + "]";
	}

}
