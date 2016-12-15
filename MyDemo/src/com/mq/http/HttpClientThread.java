/**
 * 
 */
package com.mq.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.util.Log;

/**
 * @author mq
 *
 */
/**
 * @author mq
 *
 */
public class HttpClientThread extends Thread{

	private static final String URLPATH="http://192.168.1.102:8080/MyZhihu/";
//	private static final String URLPATH="http://172.26.119.87:8080/MyZhihu/";
	
	private String url;
	private String username;
	private String userId;
	private String qid;
	private Integer objectId;
	private String answerContent;
	private String commentContent;
	private String category;
	private String favoritename, favoriteDescription;
	private Integer answerId, supportOrOppose;
	
//	private Object objects;
	
	public HttpClientThread(String url){
		this.url = url;
	}
	
//	public HttpClientThread(String url, Object...objects){
//		this.url = url;
//		this.objects = objects;
//	}
	
	/**
	 * 构造方法——返回取消感谢
	 * @param url
	 * @param userId
	 * @param objectId
	 */
	public HttpClientThread(String url, String userId, Integer objectId){
		this.url = url;
		this.userId = userId;
		this.objectId = objectId;
	}
	
	/**
	 * 构造方法——返回支持或反对
	 * @param url
	 * @param userId
	 * @param answerId
	 * @param supportOrOppose
	 */
	public HttpClientThread(String url, String userId, Integer answerId, Integer supportOrOppose){
		this.url = url;
		this.userId = userId;
		this.answerId = answerId;
		this.supportOrOppose = supportOrOppose;
	}
	/**
	 * 构造方法——表示感谢的时候调用
	 * @param url
	 * @param userId
	 * @param objectId
	 * @param category
	 */
	public HttpClientThread(String url, String userId, Integer objectId, String category){
		this.url = url;
		this.userId = userId;
		this.objectId = objectId;
		this.category = category;
	}
	/**
	 * 构造方法——添加收藏时调用
	 * @param url
	 * @param userId
	 * @param objectId
	 * @param category
	 * @param favoritename
	 * @param favoriteDescription
	 */
	public HttpClientThread(String url, String userId, Integer objectId, String category,
			String favoritename, String favoriteDescription){
		this.url = url;
		this.userId = userId;
		this.objectId = objectId;
		this.category = category;
		this.favoritename = favoritename;
		this.favoriteDescription = favoriteDescription;
	}
	/**
	 * 构造方法——评论时调用
	 * @param url
	 * @param userId
	 * @param objectId
	 * @param commentContent
	 * @param category
	 */
	public HttpClientThread(String url, String userId, Integer objectId, 
			String commentContent, String category){
		this.url = url;
		this.userId = userId;
		this.objectId = objectId;
		this.commentContent = commentContent;
		this.category = category;
	}
	
	/**
	 * 构造方法——添加关注时调用
	 * @param url
	 * @param qid
	 * @param userId
	 * @param category
	 */
	public HttpClientThread(String url, Integer objectId, String userId,String category){
		this.url = url;
		this.objectId = objectId;
		this.userId = userId;
		this.category = category;
	}
	
	/**
	 * 构造方法——回答时调用
	 * @param url
	 * @param username
	 * @param userId
	 * @param qid
	 * @param answerContent
	 */
	public HttpClientThread(String url,String username, String userId, String qid, 
			String answerContent){
		this.url = url;
		this.qid = qid;
		this.username = username;
		this.userId = userId;
		this.answerContent = answerContent;
	}
	
	/**
	 * 通过post方式连接服务器
	 */
	public static String acceptHttpClient(String url_path){
		String finalurl = URLPATH + url_path;
		String content = "";
		//创建HttpClient对象
		HttpClient client = new DefaultHttpClient();
		//创建HttpPost对象
		HttpPost post;
		try {
			post = new HttpPost(new URI(finalurl));
			//发送请求
			HttpResponse response = client.execute(post);
			//判断结果值类型
			if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
				//取出服务器端返回的数据类型
				HttpEntity entity=response.getEntity();
				if(entity!=null){
					content = EntityUtils.toString(entity);
					return content;
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return content;
	}
	
	/**
	 * 通过post方式提交数据到服务器
	 */
	private void doHttpClientPost() {
		String finalurl = URLPATH + url;
		//创建HttpClient对象
		HttpClient client = new DefaultHttpClient();
		//创建HttpPost对象
		HttpPost httpPost = new HttpPost(finalurl);
		//通过NameValuePair存取数据
		ArrayList<NameValuePair> list = new ArrayList<NameValuePair>();
		String oid = objectId + "";
		String aid = answerId + "";
		String so = supportOrOppose + "";
		//字符串一个一个的传
		list.add(new BasicNameValuePair("username", username));
		list.add(new BasicNameValuePair("questionId", qid));
		list.add(new BasicNameValuePair("userId", userId));
		list.add(new BasicNameValuePair("answerContent", answerContent));
		list.add(new BasicNameValuePair("category", category));
		list.add(new BasicNameValuePair("objectId", oid));
		list.add(new BasicNameValuePair("commentContent", commentContent));
		list.add(new BasicNameValuePair("favoritename", favoritename));
		list.add(new BasicNameValuePair("favoriteDescription", favoriteDescription));
		list.add(new BasicNameValuePair("answerId", aid));
		list.add(new BasicNameValuePair("supportOrOppose", so));
		
		try {
			//放置发送的数据并设置字符编码格式UTF-8
			httpPost.setEntity(new UrlEncodedFormEntity(list, HTTP.UTF_8));
			//发送请求
			HttpResponse response = client.execute(httpPost);
			//判断结果值类型
			if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
				//取出服务器端返回的数据类型
				String content = EntityUtils.toString(response.getEntity());
				Log.d("mq", content);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
			doHttpClientPost();
	}

}
