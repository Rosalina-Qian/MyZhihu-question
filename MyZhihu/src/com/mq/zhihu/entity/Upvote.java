package com.mq.zhihu.entity;

/**
 * Focus entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class Upvote implements java.io.Serializable {

	// Fields

	private Integer voteId;
	private Integer category;
	private Integer userId;
	private Integer objectId;

	// Constructors

	/** default constructor */
	public Upvote() {
	}

	/** full constructor */
	public Upvote(Integer category, Integer userId, Integer objectId) {
		this.category = category;
		this.userId = userId;
		this.objectId = objectId;
	}

	// Property accessors

	public Integer getCategory() {
		return category;
	}

	public Integer getVoteId() {
		return voteId;
	}

	public void setVoteId(Integer voteId) {
		this.voteId = voteId;
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