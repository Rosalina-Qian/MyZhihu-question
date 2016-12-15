package com.mq.zhihu.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mq.zhihu.common.JDBCUtil;
import com.mq.zhihu.entity.Userinfo;

@Repository
public class UserInfoDAO {

	JDBCUtil util = new JDBCUtil();
	
	public List<Userinfo> queryAll() {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		rs = util.query("select * from userinfo");
		List<Userinfo> list = new ArrayList<Userinfo>();
		try {
			while(rs.next()){

				Userinfo user = new Userinfo();
				user.setUserId(rs.getInt("UserID"));
				user.setUserName(rs.getString("UserName"));
				user.setUserPhoneNum(rs.getString("UserPhoneNum"));
				user.setPassWord(rs.getString("PassWord"));	
				user.setEmail(rs.getString("Email"));
				user.setSex(rs.getString("Sex"));
				user.setDescriptionInShort(rs.getString("DescriptionInShort"));
				user.setPersonnalIntroduction(rs.getString("PersonnalIntroduction"));
				user.setProfession(rs.getString("Profession"));
				user.setAddress(rs.getString("Address"));
				user.setProfessionHistory(rs.getString("ProfessionHistory"));
				user.setEducationExperience(rs.getString("EducationExperience"));
				user.setOthers(rs.getString("Others"));
				list.add(user);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally{
			util.closeAll();
		}
		return list;
	}
	
	public Userinfo queryById(int UserID) {
		// TODO Auto-generated method stub
		Userinfo user = null;
		ResultSet rs = null;
		rs = util.query("select * from userinfo where UserID=?", UserID);
		try {
			while(rs.next()){
				user = new Userinfo();
				user.setUserId(rs.getInt("UserID"));
				user.setUserName(rs.getString("UserName"));
				user.setUserPhoneNum(rs.getString("UserPhoneNum"));
				user.setPassWord(rs.getString("PassWord"));	
				user.setEmail(rs.getString("Email"));
				user.setSex(rs.getString("Sex"));
				user.setDescriptionInShort(rs.getString("DescriptionInShort"));
				user.setPersonnalIntroduction(rs.getString("PersonnalIntroduction"));
				user.setProfession(rs.getString("Profession"));
				user.setAddress(rs.getString("Address"));
				user.setProfessionHistory(rs.getString("ProfessionHistory"));
				user.setEducationExperience(rs.getString("EducationExperience"));
				user.setOthers(rs.getString("Others"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally{
			util.closeAll();
		}
		return user;
	}

}
