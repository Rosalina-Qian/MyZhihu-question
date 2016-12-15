package com.mq.zhihu.dao;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import org.springframework.stereotype.Repository;

import com.mq.zhihu.common.JDBCUtil;
import com.mq.zhihu.entity.Question;

@Repository
public class QuestionDAO {

	JDBCUtil util = new JDBCUtil(); 
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//定义格式，不显示毫秒
	
	/**
	 * 添加问题
	 * @param q
	 * @return
	 */
	public boolean addQuestion(Question q) {
		// TODO Auto-generated method stub
		int i = 0;
		i = util.update("insert into question(QuestionID, UserID, TopicID, QuestionDescription," +
				" FurtherExplanations, QuestionDate)values(?,?,?,?,?,?)", q.getQuestionId(), q.getUserId(), 
				q.getTopicId(), q.getQuestionDescription(), q.getFurtherExplanations(), q.getQuestionDate());
		if(i > 0 ){
			util.closeAll();
			return true;
		}else{
			util.closeAll();
		
			return false;
		}
		
	}
	
	/**
	 * 删除问题
	 * @param QuestionID
	 * @return
	 */
	public boolean deleteQuestion(int QuestionID) {
		// TODO Auto-generated method stub
		int i = 0;
		i = util.update("delete from question where QuestionID = ?", QuestionID);
		if(i > 0){
			util.closeAll();
			return true;
		} else{
			util.closeAll();
			return false;
		}
	}

	/**
	 * 查询所有问题
	 * @return
	 */
	public List<Question> queryAll() {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		rs = util.query("select * from question");
		List<Question> list = new ArrayList<Question>();
		df.setTimeZone(TimeZone.getTimeZone("GMT"));
		try {
			while(rs.next()){
				Question ques = new Question();
				ques.setQuestionId(rs.getInt("QuestionID"));
				ques.setUserId(rs.getInt("UserID"));
				ques.setTopicId(rs.getInt("TopicID"));
				ques.setQuestionDescription(rs.getString("QuestionDescription"));
				ques.setFurtherExplanations(rs.getString("FurtherExplanations"));
				ques.setQuestionDate(df.format(rs.getTimestamp("QuestionDate")));
				list.add(ques);
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
	
	public Question queryById(int QuestionID) {
		// TODO Auto-generated method stub
		Question ques = null;
		ResultSet rs = null;
		rs = util.query("select * from question where QuestionID=?", QuestionID);
		df.setTimeZone(TimeZone.getTimeZone("GMT"));
		try {
			while(rs.next()){
				ques = new Question();
				ques.setQuestionId(rs.getInt("QuestionID"));
				ques.setUserId(rs.getInt("UserID"));
				ques.setTopicId(rs.getInt("TopicID"));
				ques.setQuestionDescription(rs.getString("QuestionDescription"));
				ques.setFurtherExplanations(rs.getString("FurtherExplanations"));
				ques.setQuestionDate(df.format(rs.getTimestamp("QuestionDate")));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally{
			util.closeAll();
		}
		return ques;
	}

}
