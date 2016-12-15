package com.mq.zhihu.dao;

import org.springframework.stereotype.Repository;

import com.mq.zhihu.common.JDBCUtil;
import com.mq.zhihu.entity.Mycollection;


@Repository
public class MycollectionDAO {

	JDBCUtil util = new JDBCUtil();
	
	/**
	 * 添加收藏夹
	 * @param col
	 * @return
	 */
	public boolean addMycollection(Mycollection col) {
		// TODO Auto-generated method stub
		int i = 0;
		i = util.update("insert into collection(Category, ObjectID, CollectionDate) values(?,?,?)", 
				col.getCategory(), col.getObjectId(), col.getCollectionDate());
		if(i > 0 ){
			util.closeAll();
			return true;
		}else{
			util.closeAll();
			return false;
		}
	}
}
