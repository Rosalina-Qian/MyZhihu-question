/**
 * 
 */
package com.mq.adapter;

import java.util.List;

import com.mq.activity.MainActivity;
import com.mq.activity.MyAnswerActivity;
import com.mq.entities.Focus;
import com.mq.entities.Question;
import com.mq.http.HttpClientThread;
import com.mq.operation.JsonTools;
import com.example.mydemo.R;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * @author mq
 *
 */
public class QuestionAdapter extends BaseAdapter{

	private List<Question> list;
	private Context context;
	private LayoutInflater inflater;
	private boolean flag;
	
	public QuestionAdapter(Context context) {
		this.context = context;
		inflater = LayoutInflater.from(context);
	}
	
	public QuestionAdapter(Context context, List<Question> list) {
		this.context = context;
		this.list = list;
		inflater = LayoutInflater.from(context);
	}
	
	public void setQuestion(List<Question> question){
		this.list = question;
	}

	//获取Question的总条数
	@Override
	public int getCount() {
		return list.size();
	}

	//获取对应位置的Question对象
	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	//获取位置
	@Override
	public long getItemId(int position) {
		return position;
	}

	//获取视图信息
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		 Holder holder = null;
		
		if(convertView == null){
			convertView = inflater.inflate(R.layout.item_question, null);
			holder = new Holder(convertView);
			convertView.setTag(holder);
		} else{
			holder = (Holder) convertView.getTag();
		}
		
		final Question question = list.get(position);
		
		holder.topicId.setText(question.getTopicId().toString());
		holder.questionDescription.setText(question.getQuestionDescription());
		
		//绑定“写回答”的监听事件
		holder.writrAnswer.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(context, MyAnswerActivity.class);
				
				Bundle bundle = new Bundle();
				bundle.putInt("questionid", question.getQuestionId());
				bundle.putInt("userId", question.getUserId());
				intent.putExtras(bundle);
				
				context.startActivity(intent);
			}
		});
		
		//统计该问题的关注数量
		String focusString = HttpClientThread.acceptHttpClient("allFocus");
		List<Focus> listFocus = JsonTools.getFocus("allFocus", focusString);
		
		if(listFocus != null){
			int questionCount = 0;
			for(int i = 0; i < listFocus.size(); i++){
				Focus focus = listFocus.get(i);
				if(question.getQuestionId().equals(focus.getObjectId()) && focus.getCategory().equals(0)){
					questionCount++;
				}
			}
			holder.questionCount.setText(""+questionCount+"关注");
		} else{
			holder.questionCount.setText("");
		}
		
		//绑定“关注问题”的监听事件
		//点击一次关注，点击两次取消
		flag = false;
		if(flag){
			holder.concernquestion.setText("已关注");
			holder.concernquestion.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Integer objectId = question.getQuestionId();
					String category = "0"; //0代表问题
					new HttpClientThread("return_focus", objectId, MainActivity.USERId, category).start();
				}
			});
		}else{
			flag = true;
			holder.concernquestion.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Integer objectId = question.getQuestionId();
					String category = "0"; //0代表问题
					new HttpClientThread("return_focus", objectId, MainActivity.USERId, category).start();
					
				}
			});
			
		}
		
		
		//忽略问题
		holder.question_neglect.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				convertView.setVisibility(View.GONE);
			}
		});
		
		return convertView;
	}
	
	
	/**
	 * 初始化item_question组件
	 * @author mq
	 *
	 */
	class Holder{
		
		private TextView topicId, questionDescription;
		private TextView writrAnswer;
		private TextView questionCount;
		private TextView concernquestion, question_neglect;
		
		public  Holder(View v){
			
			topicId = (TextView) v.findViewById(R.id.topicId);
			questionDescription = (TextView) v.findViewById(R.id.questionDescription);
			writrAnswer = (TextView) v.findViewById(R.id.question_writeAnswer);
			questionCount = (TextView) v.findViewById(R.id.questionCount);
			concernquestion = (TextView) v.findViewById(R.id.question_concernquestion);
			question_neglect = (TextView) v.findViewById(R.id.question_neglect);
		}
	}
	
}
