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
	private Integer commentUserId;
	private Integer zan;
	
	public UpvoteNum() {
		// TODO Auto-generated constructor stub
	}

	public UpvoteNum(Integer id, Integer commentUserId, Integer zan) {
		super();
		this.id = id;
		this.commentUserId = commentUserId;
		this.zan = zan;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCommentUserId() {
		return commentUserId;
	}

	public void setCommentUserId(Integer commentUserId) {
		this.commentUserId = commentUserId;
	}

	public Integer getZan() {
		return zan;
	}

	public void setZan(Integer zan) {
		this.zan = zan;
	}

	
}
