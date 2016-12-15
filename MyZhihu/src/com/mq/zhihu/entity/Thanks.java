package com.mq.zhihu.entity;

/**
 * Thanks entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class Thanks implements java.io.Serializable {

	// Fields

	private Integer thanksId;
	private Integer userId;
	private Integer objectId;
	private Integer category;

	// Constructors

	/** default constructor */
	public Thanks() {
	}

	/** full constructor */
	public Thanks( Integer userId, Integer objectId, Integer category) {
		this.userId = userId;
		this.objectId = objectId;
		this.category = category;
	}

	// Property accessors

	public Integer getThanksId() {
		return this.thanksId;
	}

	public void setThanksId(Integer thanksId) {
		this.thanksId = thanksId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getObjectId() {
		return this.objectId;
	}

	public void setObjectId(Integer objectId) {
		this.objectId = objectId;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Thanks [thanksId=" + thanksId +  ", userId=" + userId + ", objectId=" + objectId
				+ ", category=" + category + "]";
	}

	
}