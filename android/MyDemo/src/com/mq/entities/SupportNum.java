/**
 * 
 */
package com.mq.entities;

/**
 * @author mq
 *
 */
public class SupportNum {

	private Integer id;
	private Integer answerId;
	private Integer supportCount;
	
	public SupportNum() {
		// TODO Auto-generated constructor stub
	}

	public SupportNum(Integer id, Integer answerId, Integer supportCount) {
		super();
		this.id = id;
		this.answerId = answerId;
		this.supportCount = supportCount;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAnswerId() {
		return answerId;
	}

	public void setAnswerId(Integer answerId) {
		this.answerId = answerId;
	}

	public Integer getSupportCount() {
		return supportCount;
	}

	public void setSupportCount(Integer supportCount) {
		this.supportCount = supportCount;
	}

	@Override
	public String toString() {
		return "SupportNum [id=" + id + ", answerId=" + answerId
				+ ", supportCount=" + supportCount + "]";
	}

	
}
