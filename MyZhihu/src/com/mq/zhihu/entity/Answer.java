package com.mq.zhihu.entity;

/**
 * Answer entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class Answer implements java.io.Serializable {

	// Fields

	private Integer answerId;
	private Integer userId;
	private Integer questionId;
	private String answerContent;
	private String answerDate;
	
	// Constructors

	/** default constructor */
	public Answer() {
	}

	/** full constructor */
	public Answer(Integer userId, Integer questionId, String answerContent,
			String answerDate) {
		this.userId = userId;
		this.questionId = questionId;
		this.answerContent = answerContent;
		this.answerDate = answerDate;
	}

	// Property accessors

	public Integer getAnswerId() {
		return this.answerId;
	}

	public void setAnswerId(Integer answerId) {
		this.answerId = answerId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getQuestionId() {
		return this.questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public String getAnswerContent() {
		return this.answerContent;
	}

	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}

	public String getAnswerDate() {
		return this.answerDate;
	}

	public void setAnswerDate(String answerDate) {
		this.answerDate = answerDate;
	}

	
}