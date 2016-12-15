package com.mq.zhihu.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mq.zhihu.common.JDBCUtil;
import com.mq.zhihu.entity.Answer;

@Repository
public class AnswerDAO {

	JDBCUtil util = new JDBCUtil();
	
	//定义格式，不显示毫秒
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 添加回答
	 * @param a
	 */
	public boolean addAnswer(Answer a) {
		// TODO Auto-generated method stub
		int i = 0;
		i = util.update("insert into answer( UserID, QuestionID, AnswerContent, AnswerDate)" +
				"values(?,?,?,?)",  a.getUserId(), a.getQuestionId(), a.getAnswerContent(),
				a.getAnswerDate());
		if(i > 0 ){
			util.closeAll();
			return true;
		}else{
			util.closeAll();
		return false;
		}
		
	}
	
	/**
	 * 查询所有回答
	 * @return
	 */
	public List<Answer> queryAll() {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		rs = util.query("select * from answer");
		List<Answer> list = new ArrayList<Answer>();
		try {
			while(rs.next()){
				Answer answer = new Answer();
				answer.setAnswerId(rs.getInt("AnswerID"));
				answer.setQuestionId(rs.getInt("QuestionID"));
				answer.setUserId(rs.getInt("UserID"));
				answer.setAnswerContent(rs.getString("AnswerContent"));
				answer.setAnswerDate(df.format(rs.getTimestamp("AnswerDate")));
				list.add(answer);
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
	
	/**
	 * 根据用户id查询其所有的回答
	 * @param UserID
	 * @return
	 */
	public List<Answer> queryByUserId(int UserID) {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		rs = util.query("select * from answer where UserID=?", UserID);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Answer> list = new ArrayList<Answer>();
		try {
			while(rs.next()){
				Answer answer = new Answer();
				answer.setAnswerId(rs.getInt("AnswerID"));
				answer.setQuestionId(rs.getInt("QuestionID"));
				answer.setUserId(rs.getInt("UserID"));
				answer.setAnswerContent(rs.getString("AnswerContent"));
				answer.setAnswerDate(df.format(rs.getTimestamp("AnswerDate")));
				list.add(answer);
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

	/**
	 * 根据问题id查询匹配的所有回答
	 * @param QuestionID
	 * @return
	 * @throws SQLException 
	 */
	public int queryByQuestionId(int QuestionID) throws SQLException {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		rs = util.query("select count(1) as count from answer where QuestionID=?", QuestionID);
		int count = 0;
		while(rs.next()){
			count = rs.getInt("count");
			return count;
		}
		util.closeAll();
		return 0;
	}

}
