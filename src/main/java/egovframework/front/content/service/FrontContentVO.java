package egovframework.front.content.service;

import java.util.Date;

public class FrontContentVO {

	private int contentId;
	
	private String title;
	
	private String content;
	
	private String registDttm; 
	
	private String registId;
	
	private String updateDttm;
	
	private String updateId;

	public int getContentId() {
		return contentId;
	}

	public void setContentId(int contentId) {
		this.contentId = contentId;
	}
	
	public String getTitle(){
		return title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegistDttm() {
		return registDttm;
	}

	public void setRegistDttm(String registDttm) {
		this.registDttm = registDttm;
	}

	public String getRegistId() {
		return registId;
	}

	public void setRegistId(String registId) {
		this.registId = registId;
	}

	public String getUpdateDttm() {
		return updateDttm;
	}

	public void setUpdateDttm(String updateDttm) {
		this.updateDttm = updateDttm;
	}

	public String getUpdateId() {
		return updateId;
	}

	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}
	
}

