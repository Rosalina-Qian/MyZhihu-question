/**
 * 
 */
package com.mq.zhihu.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mq.zhihu.common.JDBCUtil;
import com.mq.zhihu.entity.SupportorOppose;

/**
 * @author mq
 *
 */
@Repository
public class SupportorOpposeDAO {

	JDBCUtil util = new JDBCUtil(); 
	
	/**
	 * 查询所有支持或反对
	 * @return
	 */
	public List<SupportorOppose> queryAll() {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		rs = util.query("select * from supportoroppose");
		List<SupportorOppose> list = new ArrayList<SupportorOppose>();
		try {
			while(rs.next()){
				SupportorOppose so = new SupportorOppose();
				so.setId(rs.getInt("id"));
				so.setAnswerId(rs.getInt("AnswerID"));
				so.setUserId(rs.getInt("UserID"));
				so.setSupportOrOppose(rs.getInt("SupportOrOppose"));
				list.add(so);
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
	 * 添加支持
	 * @param so
	 */
	@SuppressWarnings("unused")
	public void addSupport(SupportorOppose so){
		ResultSet rs = null;
		rs = util.query("select count(1) as count from supportoroppose where AnswerID=? and SupportOrOppose=? and UserID=?", 
				so.getAnswerId(), so.getSupportOrOppose(), so.getUserId());
		try {
			int count = 0;
			while(rs.next()){
				count = rs.getInt("count");
				if(count == 0){
					int i = 0;
					//向supportoroppose表中添加1条支持记录
					i = util.update("insert into supportoroppose(AnswerID, UserID, SupportOrOppose)values(?,?,?)", 
							so.getAnswerId(), so.getUserId(), so.getSupportOrOppose());
					count++;
					//向supportNum表中添加1条记录
					if(i > 0 ){
						ResultSet rsOfNum = null;
						rsOfNum = util.query("select count(1) as count from supportNum where AnswerId=?", 
								so.getAnswerId());
						try{
							while(rsOfNum.next()){
								int supportnum = rsOfNum.getInt("count");
								if(supportnum == 0){
									int j = 0;
									j = util.update("insert into supportNum(AnswerId, SupportCount)values(?,?)",
											so.getAnswerId(), count);
								}else{
									int j = util.update("update supportNum set SupportCount=SupportCount+1 where AnswerId=?", 
											so.getAnswerId());	
								}
							}
						}catch (Exception e) {
							// TODO: handle exception
						}
					} else{
						System.out.println("failed");
					}
			    } else{
			    	System.out.println("该记录已经添加过了");
			    	
			    	int i = 0;
					i = util.update("delete from supportNum wherewhere AnswerID=? and SupportOrOppose=? and UserID=?",
							so.getAnswerId(), so.getSupportOrOppose(), so.getUserId());
					if(i > 0 ){
						//移除该条赞记录后，在对应的数量表里赞-1
						int j = util.update("update supportNum set SupportCount=SupportCount-1 where AnswerId=?", 
								so.getAnswerId());
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
	 * 添加反对
	 * @param so
	 */
	@SuppressWarnings("unused")
	public void addOppose(SupportorOppose so){
		ResultSet rs = null;
		rs = util.query("select count(1) as count from supportoroppose where AnswerID=? and SupportOrOppose=? and UserID=?", 
				so.getAnswerId(), so.getSupportOrOppose(), so.getUserId());
		try {
			int count = 0;
			while(rs.next()){
				count = rs.getInt("count");
				if(count == 0){
					
					int i = 0;
					//向supportoroppose表中添加1条支持记录
					i = util.update("insert into supportoroppose(AnswerID, UserID, SupportOrOppose)values(?,?,?)", 
							so.getAnswerId(), so.getUserId(), so.getSupportOrOppose());
					//查询该反对者反对时是否之前存在支持
					ResultSet rst = null;
					rst = util.query("select count(1) as spCount from supportoroppose where AnswerID=? and SupportOrOppose=? and UserID=?", 
							so.getAnswerId(), 1, so.getUserId());
					try {
						int num = 0;
						while(rst.next()){
							num = rst.getInt("spCount");
							if(num == 0){
								System.out.println("ok");
							} else{
								//若存在，则在supportNum表中对应的回答下支持数-1
								int j = util.update("update supportNum set SupportCount=SupportCount-1 where AnswerId=?", 
										so.getAnswerId());
							}
						} 
					}catch (Exception e) {
						// TODO: handle exception
					}
				} else{
					System.out.println("已反对");
				}
			}
		}catch (Exception e) {
				// TODO: handle exception
		}finally{
			util.closeAll();
		}
	}
				
}
