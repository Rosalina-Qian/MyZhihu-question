package com.mq.operation;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mq.entities.Answer;
import com.mq.entities.Comment;
import com.mq.entities.Focus;
import com.mq.entities.Question;
import com.mq.entities.SupportNum;
import com.mq.entities.SupportorOppose;

/**
 * @author mq
 *
 */
public class JsonTools {

	public JsonTools() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 获取所有问题
	 * @param key
	 * @param jsonString
	 * @return
	 */
	public static List<Question> getQuestion(String key, String jsonString){
		List<Question> list = new ArrayList<Question>();
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			JSONArray jsonArray = jsonObject.getJSONArray(key);
			for(int i = 0; i < jsonArray.length(); i++){
				JSONObject questionObject = jsonArray.getJSONObject(i);
				
				Question question = new Question();
				question.setQuestionId(questionObject.getInt("questionId"));
				question.setUserId(questionObject.getInt("userId"));
				question.setTopicId(questionObject.getInt("topicId"));
				question.setQuestionDescription(questionObject.getString("questionDescription"));
				question.setFurtherExplanations(questionObject.getString("furtherExplanations"));
				question.setQuestionDate(questionObject.getString("questionDate"));
			
				list.add(question);	
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 获取一个问题对象
	 * @param key
	 * @param jsonString
	 * @return
	 */
	public static Question Question(String key, String jsonString){
		Question question = new Question();
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			JSONObject questionObject = jsonObject.getJSONObject("question");
			question.setQuestionId(questionObject.getInt("QuestionID"));
			question.setUserId(questionObject.getInt("UserID"));
			question.setTopicId(questionObject.getInt("TopicID"));
			question.setQuestionDescription(questionObject.getString("questionDescription"));
			question.setFurtherExplanations(questionObject.getString("FurtherExplanations"));
			question.setQuestionDate(questionObject.getString("QuestionDate"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return question;
	}
	
	/**
	 * 获取所有评论
	 * @param key
	 * @param jsonString
	 * @return
	 */
	public static List<Comment> getComment(String key, String jsonString){
		List<Comment> list = new ArrayList<Comment>();
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			JSONArray jsonArray = jsonObject.getJSONArray(key);
			for(int i = 0; i < jsonArray.length(); i++){
				JSONObject commentObject = jsonArray.getJSONObject(i);
				
				Comment comment = new Comment();
				comment.setCommentId(commentObject.getInt("commentId"));
				comment.setCategory(commentObject.getInt("category"));
				comment.setUserId(commentObject.getInt("userId"));
				comment.setObjectId(commentObject.getInt("objectId"));
				comment.setCommentContent(commentObject.getString("commentContent"));
				comment.setCommentTime(commentObject.getString("commentTime"));
				
				list.add(comment);	
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 获取所有回答
	 * @param key
	 * @param jsonString
	 * @return
	 */
	public static List<Answer> getAnswer(String key, String jsonString){
		List<Answer> list = new ArrayList<Answer>();
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			JSONArray jsonArray = jsonObject.getJSONArray(key);
			for(int i = 0; i < jsonArray.length(); i++){
				JSONObject answerObject = jsonArray.getJSONObject(i);
				
				Answer answer = new Answer();
				answer.setAnswerId(answerObject.getInt("answerId"));
				answer.setQuestionId(answerObject.getInt("questionId"));
				answer.setUserId(answerObject.getInt("userId"));
				answer.setAnswerContent(answerObject.getString("answerContent"));
				answer.setAnswerDate(answerObject.getString("answerDate"));
				
				list.add(answer);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 获取所有关注
	 * @param key
	 * @param jsonString
	 * @return
	 */
	public static List<Focus> getFocus(String key, String jsonString){
		List<Focus> list = new ArrayList<Focus>();
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			JSONArray jsonArray = jsonObject.getJSONArray(key);
			for(int i = 0; i < jsonArray.length(); i++){
				JSONObject focusObject = jsonArray.getJSONObject(i);
				
				Focus focus = new Focus();
				focus.setFocusId(focusObject.getInt("focusId"));
				focus.setCategory(focusObject.getInt("category"));
				focus.setUserId(focusObject.getInt("userId"));
				focus.setObjectId(focusObject.getInt("objectId"));
				
				list.add(focus);	
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 获取所有的支持和反对
	 * @param key
	 * @param jsonString
	 * @return
	 */
	public static List<SupportorOppose> getSupportorOppose(String key, String jsonString){
		List<SupportorOppose> list = new ArrayList<SupportorOppose>();
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			JSONArray jsonArray = jsonObject.getJSONArray(key);
			for(int i = 0; i < jsonArray.length(); i++){
				JSONObject sooObject = jsonArray.getJSONObject(i);
				
				SupportorOppose so = new SupportorOppose();
				so.setId(sooObject.getInt("id"));
				so.setAnswerId(sooObject.getInt("answerId"));
				so.setUserId(sooObject.getInt("userId"));
				so.setSupportOrOppose(sooObject.getInt("supportOrOppose"));
				
				list.add(so);	
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 返回支持数
	 * @param key
	 * @param jsonString
	 * @return
	 */
	public static List<SupportNum> getSupportNum(String key, String jsonString){
		List<SupportNum> list = new ArrayList<SupportNum>();
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			JSONArray jsonArray = jsonObject.getJSONArray(key);
			for(int i = 0; i < jsonArray.length(); i++){
				JSONObject spNumObject = jsonArray.getJSONObject(i);
				
				SupportNum spNum = new SupportNum();
				spNum.setId(spNumObject.getInt("id"));
				spNum.setAnswerId(spNumObject.getInt("answerId"));
				spNum.setSupportCount(spNumObject.getInt("supportCount"));
				
				list.add(spNum);	
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
