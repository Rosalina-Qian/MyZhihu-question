package com.mq.zhihu.test;

import com.mq.zhihu.common.JDBCUtil;


public class test {
	
	static JDBCUtil util = new JDBCUtil();
	
	public static  void testQuestion(){
		
		int i = 0;
		
		//mysql测试数据库
		i = util.update("insert into question(UserID, TopicID, QuestionDescription, FurtherExplanations, QuestionDate) " +
				"value(1, 3, '更换数据库的时候可怕的是什么？', '敲着敲着就要更换数据库软件是一种什么样的心情，又会出现什么问题呢','2016-12-07')");
		
		//sqlserver  测试数据库
//		i = util.update("insert into question values(1, 3, 'sqlserver', '大小写','2016-12-07')");
		
		if(i > 0){
			
			System.out.println("success!");
		
		} else{
			
			System.out.println("bad job!");
		}
		
		util.closeAll();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		testQuestion();
	}
}
