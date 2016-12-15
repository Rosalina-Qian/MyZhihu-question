package com.mq.zhihu.entity;

/**
 * Userinfo entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class Userinfo implements java.io.Serializable {

	// Fields

	private Integer userId;
	private String userPhoneNum;
	private String userName;
	private String passWord;
	private String email;
	private String sex;
	private String descriptionInShort;
	private String personnalIntroduction;
	private String profession;
	private String address;
	private String professionHistory;
	private String educationExperience;
	private String others;

	// Constructors

	/** default constructor */
	public Userinfo() {
	}

	/** minimal constructor */
	public Userinfo(String userPhoneNum, String userName, String passWord,
			String sex) {
		this.userPhoneNum = userPhoneNum;
		this.userName = userName;
		this.passWord = passWord;
		this.sex = sex;
	}

	/** full constructor */
	public Userinfo(String userPhoneNum, String userName, String passWord,
			String email, String sex, String descriptionInShort,
			String personnalIntroduction, String profession, String address,
			String professionHistory, String educationExperience, String others) {
		this.userPhoneNum = userPhoneNum;
		this.userName = userName;
		this.passWord = passWord;
		this.email = email;
		this.sex = sex;
		this.descriptionInShort = descriptionInShort;
		this.personnalIntroduction = personnalIntroduction;
		this.profession = profession;
		this.address = address;
		this.professionHistory = professionHistory;
		this.educationExperience = educationExperience;
		this.others = others;
	}

	// Property accessors

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserPhoneNum() {
		return this.userPhoneNum;
	}

	public void setUserPhoneNum(String userPhoneNum) {
		this.userPhoneNum = userPhoneNum;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return this.passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getDescriptionInShort() {
		return this.descriptionInShort;
	}

	public void setDescriptionInShort(String descriptionInShort) {
		this.descriptionInShort = descriptionInShort;
	}

	public String getPersonnalIntroduction() {
		return this.personnalIntroduction;
	}

	public void setPersonnalIntroduction(String personnalIntroduction) {
		this.personnalIntroduction = personnalIntroduction;
	}

	public String getProfession() {
		return this.profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getProfessionHistory() {
		return this.professionHistory;
	}

	public void setProfessionHistory(String professionHistory) {
		this.professionHistory = professionHistory;
	}

	public String getEducationExperience() {
		return this.educationExperience;
	}

	public void setEducationExperience(String educationExperience) {
		this.educationExperience = educationExperience;
	}

	public String getOthers() {
		return this.others;
	}

	public void setOthers(String others) {
		this.others = others;
	}

}