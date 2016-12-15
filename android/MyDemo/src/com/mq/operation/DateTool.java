/**
 * 
 */
package com.mq.operation;

import java.text.SimpleDateFormat;
import java.util.Date;
import android.annotation.SuppressLint;
import android.util.Log;

/**
 * @author mq
 *
 */
@SuppressLint("SimpleDateFormat")
public class DateTool {

	/**
	 * 获取当前系统时间
	 * @return
	 */
	public static Date getCurrntTime(){
		Date curDate = new Date(System.currentTimeMillis());
		return curDate;
	}
	/**
	 * 获取当前系统字符串日期
	 * @return
	 */
	public static String getCurrntDate(){
		//获取系统当前时间
		Date curDate = new Date(System.currentTimeMillis());
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		//将Date类型的时间转换成字符串类型
		String str_date = df.format(curDate);
		
		return str_date;
	}
		
	/**
	 * 根据时间字符串获取毫秒数
	 * @param dateStr
	 * @return
	 */
	public static long getTimeMillis(String dateStr){  
        long returnMillis = 0;
        //注意format的格式要与日期String的格式相匹配   
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        Date date = null;
        try {   
            date = df.parse(dateStr);   
            returnMillis = date.getTime();
            Log.d("mq",date.toString());   
        } catch (Exception e) {   
            e.printStackTrace();   
        }
		return returnMillis;
	}
	
	/**
	 * 两个日期相隔的天数
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static long getGapTime(String startDate) {  
	       long startTime = getTimeMillis(startDate);
	       long endTime = getTimeMillis(getCurrntDate());
	       long gapTime = endTime - startTime;
	       
	       long finalGapTime = gapTime / 1000;
	       
	       //根据时间差计算天数
//	       long days = gapTime / (1000 * 60 * 60 * 24);
	      
	       //根据时间差计算小时数
//	       long hours = gapTime / (1000 * 60 * 60);
	      
	       //根据时间差计算分钟数
//	       long minutes = gapTime / (1000 * 60);
//	       
//	       if(minutes < 60 && minutes > 0){
//	    	   return minutes;
//	       } else if(minutes > 60 || minutes ==60 && hours > 1 && hours < 24  ){
//	    	   return hours;
//	       } else if(hours > 24 || hours == 24 ){
//	    	   return days;
//	       }
		return finalGapTime;
	}
}
