package com.mq.zhihu.entity;

/**
 * Focus entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class Focus implements java.io.Serializable {

	// Fields

	private Integer focusId;
	private Integer category;
	private Integer userId;
	private Integer objectId;

	// Constructors

	/** default constructor */
	public Focus() {
	}

	/** full constructor */
	public Focus(Integer category, Integer userId, Integer objectId) {
		this.category = category;
		this.userId = userId;
		this.objectId = objectId;
	}

	// Property accessors

	public Integer getFocusId() {
		return this.focusId;
	}

	public void setFocusId(Integer focusId) {
		this.focusId = focusId;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
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

}