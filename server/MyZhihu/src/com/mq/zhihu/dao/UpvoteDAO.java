package com.mq.zhihu.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mq.zhihu.common.JDBCUtil;
import com.mq.zhihu.entity.Upvote;

@Repository
public class UpvoteDAO {

	JDBCUtil util = new JDBCUtil();
	/**
	 * 查询所有赞
	 * @return
	 */
	public List<Upvote> queryAll() {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		rs = util.query("select * from upvote");
		List<Upvote> list = new ArrayList<Upvote>();
		try {
			while(rs.next()){
				Upvote vote = new Upvote();
				vote.setVoteId(rs.getInt("VoteId"));
				vote.setCategory(rs.getInt("Category"));
				vote.setUserId(rs.getInt("UserID"));
				vote.setObjectId(rs.getInt("ObjectID"));
				list.add(vote);
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
	 * 添加赞
	 * @param zan
	 */
	@SuppressWarnings("unused")
	public void addZan(Upvote zan){
		ResultSet rs = null;
		rs = util.query("select count(1) as count from upvote where ObjectId=? and Category=?",
				zan.getObjectId(), zan.getCategory());
		try {
			int count = 0;
			while(rs.next()){
				count = rs.getInt("count");
				if(count == 0){
					int i = 0;
					//向upvote表中添加1条赞记录
					i = util.update("insert into upvote(UserId, Category, ObjectId)values(?,?,?)", 
							zan.getUserId(), zan.getCategory(), zan.getObjectId());
					count++;
					//向upvoteNum表中添加1条记录
					if(i > 0 ){
						int j = 0;
						j = util.update("insert into upvoteNum(CommentId, Zan)values(?,?)",
								zan.getObjectId(), count);
						System.out.println("已赞, count="+count);
					} else{
						System.out.println("insert failed");
					}
			    } else{
			    	System.out.println("该记录已经添加过了");
			    	
					int j = 0;
					j = util.update("insert into upvoteNum(CommentId, Zan)values(?,?)",
							zan.getObjectId(), count);
					System.out.println("赞数："+count);
			    }
			} 
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			util.closeAll();
		}
	}
	
	/**
	 * 踩
	 * @param zan
	 */
	@SuppressWarnings("unused")
	public void addCai(Upvote zan){
		ResultSet rs = null;
		rs = util.query("select count(1) as count from upvote where ObjectId=? and Category=?",
				zan.getObjectId(), zan.getCategory());
		try {
			int count = 0;
			while(rs.next()){
				count = rs.getInt("count");
				if(count == 0){
					int i = 0;
					//向upvote表中添加1条赞记录
					i = util.update("insert into upvote(UserId, Category, ObjectId)values(?,?,?)", 
							zan.getUserId(), zan.getCategory(), zan.getObjectId());
					//查询踩时是否之前存在赞
					ResultSet rst = null;
					rst = util.query("select count(1) as zanCount from supportoroppose where ObjectId=? and Category=?", 
							zan.getObjectId(), 1);
					try {
						int num = 0;
						while(rst.next()){
							num = rs.getInt("zanCount");
							if(num == 0){
								System.out.println("ok");
							} else{
								//若存在，则在upvoteNum表中对应的评论下赞数-1
								ResultSet rsOfNum = null;
								rsOfNum = util.query("select Zan from upvoteNum where CommentId=?", 
										zan.getObjectId());
								try{
									int finalNum = 0;
									while(rsOfNum.next()){
										finalNum = rs.getInt("Zan");
										finalNum--;
										System.out.println("最后的赞数："+finalNum);
									}
								}catch (Exception e) {
									// TODO: handle exception
								}
							}
						} 
					}catch (Exception e) {
						// TODO: handle exception
					}
				} else{
					System.out.println("已踩");
				}
			}
		}catch (Exception e) {
				// TODO: handle exception
		}finally{
			util.closeAll();
		}
	}
}
