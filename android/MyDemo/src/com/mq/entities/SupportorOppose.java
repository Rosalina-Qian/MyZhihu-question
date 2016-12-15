package com.mq.entities;

@SuppressWarnings("serial")
public class SupportorOppose implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer userId;
	private Integer answerId;
	private Integer supportOrOppose;

	

	public SupportorOppose() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SupportorOppose(Integer id, Integer userId, Integer answerId,
			Integer supportOrOppose) {
		super();
		this.id = id;
		this.userId = userId;
		this.answerId = answerId;
		this.supportOrOppose = supportOrOppose;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getAnswerId() {
		return answerId;
	}

	public void setAnswerId(Integer answerId) {
		this.answerId = answerId;
	}

	public Integer getSupportOrOppose() {
		return this.supportOrOppose;
	}

	public void setSupportOrOppose(Integer supportOrOppose) {
		this.supportOrOppose = supportOrOppose;
	}

}