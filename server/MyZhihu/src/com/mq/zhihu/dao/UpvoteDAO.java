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
		rs = util.query("select count(1) as count from upvote where ObjectId=? and Category=? and UserId=?",
				zan.getObjectId(), zan.getCategory(), zan.getUserId());
		try {
			int count = 0;
			while(rs.next()){
				count = rs.getInt("count");
				//如果不存在赞的记录
				if(count == 0){
					int i = 0;
					//向upvote表中添加1条赞记录
					i = util.update("insert into upvote(UserId, Category, ObjectId)values(?,?,?)", 
							zan.getUserId(), zan.getCategory(), zan.getObjectId());
					count++;
					//向upvoteNum表中添加1条记录
					if(i > 0 ){
						ResultSet rsOfNum = null;
						rsOfNum = util.query("select count(1) as count from upvoteNum where CommentUserId=?", 
								zan.getObjectId());
						try{
							while(rsOfNum.next()){
								int zannum = rsOfNum.getInt("count");
								if(zannum == 0){
									int j = util.update("insert into upvotenum(CommentUserId, Zan)values(?,?)",
											zan.getObjectId(), 1);
								}else{
									int j = util.update("update upvoteNum set Zan=Zan+1 where CommentUserId=?", 
											zan.getObjectId());	
								}
							}
						}catch (Exception e) {
							// TODO: handle exception
						}
						System.out.println("已赞,count="+count);
					} else{
						System.out.println("insert failed");
					}
			    } else{
			    	System.out.println("该记录已经添加过了");
			    	//之前存在该条记录，则将该条记录删除
					int i = 0;
					i = util.update("delete from upvote where ObjectId=? and Category=? and UserId=?",
							zan.getObjectId(), zan.getCategory(), zan.getUserId());
					if(i > 0 ){
						//移除该条赞记录后，在对应的数量表里赞-1
						int j = util.update("update upvoteNum set Zan=Zan-1 where CommentUserId=?", 
								zan.getObjectId());
					} else{
						System.out.println("failed");
					}
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
		rs = util.query("select count(1) as count from upvote where ObjectId=? and Category=? and UserId=?",
				zan.getObjectId(), zan.getCategory(), zan.getUserId());
		try {
			int count = 0;
			while(rs.next()){
				count = rs.getInt("count");
				//不存在踩的记录
				if(count == 0){
					int i = 0;
					//向upvote表中添加1条踩记录
					i = util.update("insert into upvote(UserId, Category, ObjectId)values(?,?,?)", 
							zan.getUserId(), zan.getCategory(), zan.getObjectId());
					//查询踩时是否之前存在赞
					ResultSet rst = null;
					rst = util.query("select count(1) as zanCount from upvote where ObjectId=? and Category=? and UserId=?", 
							zan.getObjectId(), 1, zan.getUserId());
					try {
						int num = 0;
						while(rst.next()){
							num = rst.getInt("zanCount");
							if(num == 0){
								//之前不存在赞记录
								System.out.println("ok");
							} else{
								//若存在，则在upvoteNum表中对应的评论下赞数-1
								int j = util.update("update upvoteNum set Zan=Zan-1 where CommentUserId=?", 
										zan.getObjectId());
							}
						} 
					}catch (Exception e) {
						// TODO: handle exception
					}
				} else{
					//在此踩时已经存在该条踩记录
					System.out.println("已踩");
					//删除该条踩记录
					int i = 0;
					i = util.update("delete from upvote where ObjectId=? and Category=? and UserId=?",
							zan.getObjectId(), zan.getCategory(), zan.getUserId());
					//查询踩时是否之前存在赞
					ResultSet rst = null;
					rst = util.query("select count(1) as zanCount from upvote where ObjectId=? and Category=? and UserId=?", 
							zan.getObjectId(), 1, zan.getUserId());
					try {
						int num = 0;
						while(rst.next()){
							num = rst.getInt("zanCount");
							if(num == 0){
								//之前不存在赞记录
								System.out.println("ok");
							} else{
								//若存在，则在upvoteNum表中对应的评论下赞数+1
								int j = util.update("update upvoteNum set Zan=Zan+1 where CommentUserId=?", 
										zan.getObjectId());	
							}
						} 
					}catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
		}catch (Exception e) {
				// TODO: handle exception
		}finally{
			util.closeAll();
		}
	}
}
