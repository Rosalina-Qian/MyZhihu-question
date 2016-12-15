package com.mq.zhihu.entity;


/**
 * Favorite entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class Favorite implements java.io.Serializable {

	// Fields

	private Integer favoriteId;
	private Integer userId;
	private String favoriteName;
	private String favoriteDescription;
	private String favoriteDate;

	// Constructors

	/** default constructor */
	public Favorite() {
	}

	/** minimal constructor */
	public Favorite(Integer userId, String favoriteName) {
		this.userId = userId;
		this.favoriteName = favoriteName;
	}

	/** full constructor */
	public Favorite(Integer userId, String favoriteName,
			String favoriteDescription, String favoriteDate) {
		this.userId = userId;
		this.favoriteName = favoriteName;
		this.favoriteDescription = favoriteDescription;
		this.favoriteDate = favoriteDate;
	}

	// Property accessors

	public Integer getFavoriteId() {
		return this.favoriteId;
	}

	public void setFavoriteId(Integer favoriteId) {
		this.favoriteId = favoriteId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getFavoriteName() {
		return this.favoriteName;
	}

	public void setFavoriteName(String favoriteName) {
		this.favoriteName = favoriteName;
	}

	public String getFavoriteDescription() {
		return this.favoriteDescription;
	}

	public void setFavoriteDescription(String favoriteDescription) {
		this.favoriteDescription = favoriteDescription;
	}

	public String getFavoriteDate() {
		return this.favoriteDate;
	}

	public void setFavoriteDate(String favoriteDate) {
		this.favoriteDate = favoriteDate;
	}

}