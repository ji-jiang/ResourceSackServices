package com.techmask.ressack.resourcemanager.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;

public class Resource {
	@Id
	private String id;
	private String title;
	private String desc;
	private String tags;
	private String origUrl;
	private String downloadUrl;
	private String downloadPassword;
	private String paymentType;
	private Integer paymentAmount;
	private String category;
	private String subCategory;
	private String status;
	private Date createdDate;
	private String createdBy;
	private Date updatedDate;
	private String updatedBy;
	private Integer ownerId;
	private String ownerName;
	private Integer weight;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getOrigUrl() {
		return origUrl;
	}
	public void setOrigUrl(String origUrl) {
		this.origUrl = origUrl;
	}
	public String getDownloadUrl() {
		return downloadUrl;
	}
	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}
	public String getDownloadPassword() {
		return downloadPassword;
	}
	public void setDownloadPassword(String downloadPassword) {
		this.downloadPassword = downloadPassword;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public Integer getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(Integer paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Integer getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	
	

}
