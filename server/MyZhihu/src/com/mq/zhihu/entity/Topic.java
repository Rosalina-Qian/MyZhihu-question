package com.mq.zhihu.entity;

/**
 * Topic entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class Topic implements java.io.Serializable {

	// Fields

	private Integer topicId;
	private String topicName;
	private String topicDescription;

	// Constructors

	/** default constructor */
	public Topic() {
	}

	/** full constructor */
	public Topic(String topicName, String topicDescription) {
		this.topicName = topicName;
		this.topicDescription = topicDescription;
	}

	// Property accessors

	public Integer getTopicId() {
		return this.topicId;
	}

	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}

	public String getTopicName() {
		return this.topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public String getTopicDescription() {
		return this.topicDescription;
	}

	public void setTopicDescription(String topicDescription) {
		this.topicDescription = topicDescription;
	}

}