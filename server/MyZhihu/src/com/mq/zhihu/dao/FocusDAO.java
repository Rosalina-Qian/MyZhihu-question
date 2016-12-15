package com.mq.zhihu.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mq.zhihu.common.JDBCUtil;
import com.mq.zhihu.entity.Focus;

@Repository
public class FocusDAO {

	JDBCUtil util = new JDBCUtil();
	
	/**
	 * 添加关注
	 * @param f
	 */
	public void addFocus(Focus f) {
		ResultSet rs = null;
		rs = util.query("select count(1) as count from focus where Category=? and UserId=? and ObjectId=?",
							f.getCategory(), f.getUserId(), f.getObjectId());
		try {
			while(rs.next()){
				int count = 0;
				count = rs.getInt("count");
				if(count == 0){
					int i = 0;
					i = util.update("insert into focus(Category, UserID, ObjectID)values(?,?,?)",
							f.getCategory(), f.getUserId(), f.getObjectId());
					if(i > 0 ){
						System.out.println("已关注");
					}else{
						System.out.println("insert failed");
					}
				} else{
					int j = 0; 
					j = util.update("delete from focus where Category=? and UserId=? and ObjectId=?", 
							f.getCategory(), f.getUserId(), f.getObjectId());
					if(j > 0 ){
						System.out.println("关注已取消");
					}else{
						System.out.println("delete failed");
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			util.closeAll();
		}
		
	}

	/**
	 * 查询所有关注
	 * @return
	 */
	public List<Focus> queryAll() {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		rs = util.query("select * from focus");
		List<Focus> list = new ArrayList<Focus>();
		try {
			while(rs.next()){
				Focus focus = new Focus();
				focus.setFocusId(rs.getInt("FocusID"));
				focus.setCategory(rs.getInt("Category"));
				focus.setUserId(rs.getInt("UserID"));
				focus.setObjectId(rs.getInt("ObjectID"));
				list.add(focus);
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
	 * 返回关注的数量
	 * @param category
	 * @param oid
	 * @return
	 */
	public int accountFocus(int category, int oid){
		ResultSet rs = null;
		rs = util.query("select count(*) as count from focus where Category=? and ObjectID=?", category, oid);
		int count = 0;
		try {
			while(rs.next()){
				
				count = rs.getInt("count");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return count;
	}
}
