package com.mq.zhihu.entity;

/**
 * Mycollection entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class Mycollection implements java.io.Serializable {

	// Fields

	private Integer collectionId;
	private Integer category;
	private Integer objectId;
	private String collectionDate;

	// Constructors

	/** default constructor */
	public Mycollection() {
	}

	/** minimal constructor */
	public Mycollection(Integer category, Integer objectId) {
		this.category = category;
		this.objectId = objectId;
	}


	// Property accessors

	public Integer getCollectionId() {
		return this.collectionId;
	}

	public void setCollectionId(Integer collectionId) {
		this.collectionId = collectionId;
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

	public String getCollectionDate() {
		return this.collectionDate;
	}

	public void setCollectionDate(String collectionDate) {
		this.collectionDate = collectionDate;
	}

}