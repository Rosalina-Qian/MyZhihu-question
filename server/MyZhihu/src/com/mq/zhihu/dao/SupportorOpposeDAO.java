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
		rs = util.query("select count(1) as count from supportoroppose where AnswerID=? and SupportOrOppose=?", so.getAnswerId(), so.getSupportOrOppose());
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
						int j = 0;
						j = util.update("insert into supportNum(AnswerId, SupportCount)values(?,?)",
								so.getAnswerId(), count);
						System.out.println("已支持, count="+count);
					} else{
						System.out.println("insert failed");
					}
			    } else{
			    	System.out.println("该记录已经添加过了");
			    	
					int j = 0;
					j = util.update("insert into supportNum(AnswerId, SupportCount)values(?,?)",
							so.getAnswerId(), count);
					System.out.println("支持数："+count);
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
		rs = util.query("select count(1) as count from supportoroppose where AnswerID=? and SupportOrOppose=?", 
				so.getAnswerId(), so.getSupportOrOppose());
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
					rst = util.query("select count(1) as spCount from supportoroppose where AnswerID=? and SupportOrOppose=?", 
							so.getAnswerId(), 1);
					try {
						int num = 0;
						while(rst.next()){
							num = rs.getInt("spCount");
							if(num == 0){
								System.out.println("ok");
							} else{
								//若存在，则在supportNum表中对应的回答下支持数-1
								ResultSet rsOfNum = null;
								rsOfNum = util.query("select SupportCount from supportNum where AnswerId=?", 
										so.getAnswerId());
								try{
									int finalNum = 0;
									while(rsOfNum.next()){
										finalNum = rs.getInt("SupportCount");
										finalNum--;
										System.out.println("最后的支持数："+finalNum);
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
