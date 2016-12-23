package com.mq.zhihu.dao;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mq.zhihu.common.JDBCUtil;
import com.mq.zhihu.entity.Comment;

@Repository
public class CommentDAO {

	JDBCUtil util = new JDBCUtil();
	
	/**
	 * 添加评论
	 * @param c
	 * @return
	 */
	public boolean addComment(Comment c) {
		// TODO Auto-generated method stub
		int i = 0;
		i = util.update("insert into comment(Category, ObjectID, UserID," +
				" CommentContent, CommentTime)values(?,?,?,?,?)", c.getCategory(), 
				c.getObjectId(), c.getUserId(), c.getCommentContent(), c.getCommentTime());
		if(i > 0 ){
			util.closeAll();
			return true;
		}else{
			util.closeAll();
			return false;
		}
		
	}
	
	/**
	 * 根据Id删除评论
	 * @param CommentID
	 * @return
	 */
	public boolean deleteComment(int CommentID) {
		// TODO Auto-generated method stub
		int i = 0;
		i = util.update("delete from comment where CommentID = ?", CommentID);
		if(i > 0){
			util.closeAll();
			return true;
		}else{
			util.closeAll();
			return false;}
	}

	/**
	 * 查询所有评论
	 * @return
	 */
	public List<Comment> queryAll() {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		rs = util.query("select * from comment");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//定义格式，不显示毫秒
		List<Comment> list = new ArrayList<Comment>();
		try {
			while(rs.next()){
				Comment com = new Comment();
				com.setCommentId(rs.getInt("CommentID"));
				com.setCategory(rs.getInt("Category"));
				com.setObjectId(rs.getInt("ObjectID"));
				com.setUserId(rs.getInt("UserID"));
				com.setCommentContent(rs.getString("CommentContent"));
				com.setCommentTime(df.format(rs.getTimestamp("CommentTime")));
				list.add(com);
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
	 * 查询回复
	 * @return
	 */
	public List<Comment> queryOthers() {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		rs = util.query("SELECT DISTINCT FIRST.ObjectID,FIRST.UserID,FIRST.CommentTime,FIRST.CommentContent from comment FIRST,comment SECOND where FIRST.ObjectID=SECOND.UserID and FIRST.Category=5");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//定义格式，不显示毫秒
		List<Comment> list = new ArrayList<Comment>();
		try {
			while(rs.next()){
				Comment com = new Comment();
//				com.setCategory(5);
				com.setObjectId(rs.getInt("ObjectID"));
				com.setUserId(rs.getInt("UserID"));
				com.setCommentContent(rs.getString("CommentContent"));
				com.setCommentTime(df.format(rs.getTimestamp("CommentTime")));
				list.add(com);
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
}
