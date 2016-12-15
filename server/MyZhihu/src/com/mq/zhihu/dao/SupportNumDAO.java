package com.mq.zhihu.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mq.zhihu.common.JDBCUtil;
import com.mq.zhihu.entity.SupportNum;

@Repository
public class SupportNumDAO {

	JDBCUtil util = new JDBCUtil();
	
	/**
	 * 查询所有赞数
	 * @return
	 */
	public List<SupportNum> queryAll() {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		rs = util.query("select * from supportNum");
		List<SupportNum> list = new ArrayList<SupportNum>();
		try {
			while(rs.next()){
				SupportNum spCount = new SupportNum();
				spCount.setId(rs.getInt("id"));
				spCount.setAnswerId(rs.getInt("AnswerId"));
				spCount.setSupportCount(rs.getInt("SupportCount"));
				list.add(spCount);
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
