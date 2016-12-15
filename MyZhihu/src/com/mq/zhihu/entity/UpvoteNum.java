/**
 * 
 */
package com.mq.zhihu.entity;

/**
 * @author mq
 *
 */
public class UpvoteNum {

	private Integer id;
	private Integer commentId;
	private Integer zan;
	
	public UpvoteNum() {
		// TODO Auto-generated constructor stub
	}

	public UpvoteNum(Integer id, Integer commentId, Integer zan) {
		super();
		this.id = id;
		this.commentId = commentId;
		this.zan = zan;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public Integer getZan() {
		return zan;
	}

	public void setZan(Integer zan) {
		this.zan = zan;
	}

	
}
