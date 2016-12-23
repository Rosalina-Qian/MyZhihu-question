/**
 * 
 */
package com.mq.zhihu.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mq.zhihu.common.JDBCUtil;
import com.mq.zhihu.entity.UpvoteNum;

/**
 * @author mq
 *
 */
@Repository
public class UpvoteNumDAO {

	JDBCUtil util = new JDBCUtil();
	
	public List<UpvoteNum> queryAll(){
		ResultSet rs = null;
		rs = util.query("select * from upvoteNum");
		List<UpvoteNum> list = new ArrayList<UpvoteNum>();
		try {
			while(rs.next()){
				UpvoteNum upnum = new UpvoteNum();
				upnum.setId(rs.getInt("id"));
				upnum.setCommentUserId(rs.getInt("CommentUserId"));
				upnum.setZan(rs.getInt("Zan"));
				list.add(upnum);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			util.closeAll();
		}
		return list;
	}
}
