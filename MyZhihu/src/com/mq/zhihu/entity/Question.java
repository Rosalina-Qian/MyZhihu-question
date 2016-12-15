package com.mq.zhihu.entity;


/**
 * Question entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class Question implements java.io.Serializable {

	// Fields

	private Integer questionId;
	private Integer userId;
	private Integer topicId;
	private String questionDescription;
	private String furtherExplanations;
	private String questionDate;
	
	// Constructors

	/** default constructor */
	public Question() {
	}

	/** minimal constructor */
	public Question(Integer userId, Integer topicId,
			String questionDescription, String questionDate) {
		this.userId = userId;
		this.topicId = topicId;
		this.questionDescription = questionDescription;
		this.questionDate = questionDate;
	}

	/** full constructor */
	public Question(Integer userId, Integer topicId,
			String questionDescription, String furtherExplanations,
			String questionDate) {
		this.userId = userId;
		this.topicId = topicId;
		this.questionDescription = questionDescription;
		this.furtherExplanations = furtherExplanations;
		this.questionDate = questionDate;
	}

	// Property accessors

	public Integer getQuestionId() {
		return this.questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getTopicId() {
		return this.topicId;
	}

	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}

	public String getQuestionDescription() {
		return this.questionDescription;
	}

	public void setQuestionDescription(String questionDescription) {
		this.questionDescription = questionDescription;
	}

	public String getFurtherExplanations() {
		return this.furtherExplanations;
	}

	public void setFurtherExplanations(String furtherExplanations) {
		this.furtherExplanations = furtherExplanations;
	}

	public String getQuestionDate() {
		return this.questionDate;
	}

	public void setQuestionDate(String questionDate) {
		this.questionDate = questionDate;
	}
	

}