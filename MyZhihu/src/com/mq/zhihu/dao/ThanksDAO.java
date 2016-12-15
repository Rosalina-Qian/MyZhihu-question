/**
 * 
 */
package com.mq.zhihu.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mq.zhihu.common.JDBCUtil;
import com.mq.zhihu.entity.Thanks;

/**
 * @author mq
 *
 */
@Repository
public class ThanksDAO {

	JDBCUtil util = new JDBCUtil();
	
	/**
	 * 添加感谢
	 * @param t
	 * @throws SQLException
	 */
	public void addThanks(Thanks t) throws SQLException{
		ResultSet rs = null;
		rs = util.query("select count(1) as count from thanks where UserId=? and ObjectId=?",
							t.getUserId(), t.getObjectId());
		try {
			while(rs.next()){
				int count = 0;
				count = rs.getInt("count");
				if(count == 0){
					int i = 0;
					i = util.update("insert into thanks(UserId, ObjectId, Category)" +
						"values(?,?,?)", t.getUserId(), t.getObjectId(), t.getCategory());
					if(i > 0 ){
						System.out.println("已感谢");
					}else{
						System.out.println("insert failed");
					}
				} else{
					int j = 0;
					j = util.update("delete from thanks where UserId=? and ObjectId=?", 
							t.getUserId(), t.getObjectId());
					if(j > 0 ){
						System.out.println("取消感谢");
					}else{
						System.out.println("failed");
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean deleteThanks(int uid, int oid) {
		// TODO Auto-generated method stub
		int i = 0;
		i = util.update("delete from thanks where UserId=? and ObjectId=?", uid, oid);
		if(i > 0){
			return true;
		}else{
		return false;}
	}
	
	
	public List<Thanks> queryAll() {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		rs = util.query("select * from thanks");
		List<Thanks> list = new ArrayList<Thanks>();
		try {
			while(rs.next()){
				Thanks thanks = new Thanks();
				thanks.setThanksId(rs.getInt("thanksId"));
				thanks.setCategory(rs.getInt("Category"));
				thanks.setUserId(rs.getInt("UserID"));
				thanks.setObjectId(rs.getInt("ObjectID"));
				list.add(thanks);
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
