/**  
* @Copyright (C) 
* @Title: FeedBack.java 
* @author WenKe  
* @Package com.techmask.ressack.feedback 
* @Description: TODO(用一句话描述该文件做什么)  
* @date 2016年4月17日 下午9:09:50 
* @History 历史  
* <author>     <time>           <version>         <desc>
* 修改人        时间               版本            描叙    
*/
package com.techmask.ressack.feedback.domain;

import org.springframework.data.annotation.Id;

/** 
* @ClassName: FeedBack 
* @Description(描叙):   
* @author Wenke 
* @date 2016年4月17日 下午9:09:50  
*/
public class FeedBack {
	
	@Id
	private String feedbackId;
	private String name;
	private String email;
	private String content;
	private String createdDate;
	private String createdBy;
	private String updatedDate;
	private String updatedBy;
	public String getFeedbackId() {
		return feedbackId;
	}
	public void setFeedbackId(String feedbackId) {
		this.feedbackId = feedbackId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	@Override
	public String toString() {
		return "FeedBack [feedbackId=" + feedbackId + ", name=" + name + ", email=" + email + ", content=" + content
				+ ", createdDate=" + createdDate + ", createdBy=" + createdBy + ", updatedDate=" + updatedDate
				+ ", updatedBy=" + updatedBy + "]";
	}
	
	
	

}
