package com.mq.entities;

@SuppressWarnings("serial")
public class Comment implements java.io.Serializable {

	// Fields

	private Integer commentId;
	private Integer category;
	private Integer objectId;
	private Integer userId;
	private String commentContent;
	private String commentTime;

	// Constructors

	/** default constructor */
	public Comment() {
	}

	/** minimal constructor */
	public Comment(Integer category, Integer objectId, Integer userId) {
		this.category = category;
		this.objectId = objectId;
		this.userId = userId;
	}

	/** full constructor */
	public Comment(Integer category, Integer objectId, Integer userId,
			String commentContent, String commentTime) {
		this.category = category;
		this.objectId = objectId;
		this.userId = userId;
		this.commentContent = commentContent;
		this.commentTime = commentTime;
	}

	// Property accessors

	public Integer getCommentId() {
		return this.commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public Integer getCategory() {
		return this.category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public Integer getObjectId() {
		return this.objectId;
	}

	public void setObjectId(Integer objectId) {
		this.objectId = objectId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getCommentContent() {
		return this.commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public String getCommentTime() {
		return this.commentTime;
	}

	public void setCommentTime(String commentTime) {
		this.commentTime = commentTime;
	}

	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", category=" + category + ", objectId=" + objectId + ", userId="
				+ userId + ", commentContent=" + commentContent + ", commentTime=" + commentTime + "]";
	}

}