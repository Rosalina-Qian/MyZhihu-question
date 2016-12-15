package com.mq.zhihu.entity;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Article entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class Article implements java.io.Serializable {

	// Fields

	private Integer articleId;
	private Integer userId;
	private String articleTitle;
	private String articleContent;
	
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//定义格式，不显示毫秒
	private Timestamp articleDate;
	private String str_articleDate = df.format(articleDate);


	// Constructors

	/** default constructor */
	public Article() {
	}

	/** minimal constructor */
	public Article(Integer userId, String articleTitle, String articleContent) {
		this.userId = userId;
		this.articleTitle = articleTitle;
		this.articleContent = articleContent;
	}

	/** full constructor */
	public Article(Integer userId, String articleTitle, String articleContent,
			String str_articleDate) {
		this.userId = userId;
		this.articleTitle = articleTitle;
		this.articleContent = articleContent;
		this.str_articleDate = str_articleDate;
	}

	// Property accessors

	public Integer getArticleId() {
		return this.articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getArticleTitle() {
		return this.articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	public String getArticleContent() {
		return this.articleContent;
	}

	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}

	public Timestamp getArticleDate() {
		return this.articleDate;
	}

	public void setArticleDate(Timestamp articleDate) {
		this.articleDate = articleDate;
	}

	public String getStr_articleDate() {
		return str_articleDate;
	}

	public void setStr_articleDate(String str_articleDate) {
		this.str_articleDate = str_articleDate;
	}

	
}