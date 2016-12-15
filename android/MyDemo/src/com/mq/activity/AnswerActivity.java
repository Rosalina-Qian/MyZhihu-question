package com.mq.activity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import com.mq.adapter.AnswerAdapter;
import com.mq.entities.Answer;
import com.mq.entities.Comment;
import com.mq.entities.Focus;
import com.mq.entities.Question;
import com.mq.http.HttpClientThread;
import com.example.mydemo.R;
import com.mq.operation.JsonTools;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class AnswerActivity extends Activity {

	private TextView title, detail;
	private TextView count_focus, count_selfcomment;
	private Button focus_question, invite_answer, my_answer;
	private ListView answers;
	private AnswerAdapter adapter;
	private boolean flag;
	Intent intent = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_answer);
		
		bindView();
		
		//获取从QuestionActivity传过来的question对象
		intent = getIntent();
		final Question question = (Question) intent.getSerializableExtra("question");
		
		title.setText(""+question.getQuestionDescription()+"\n该问题对应的id:"+question.getQuestionId());
		detail.setText(""+question.getFurtherExplanations());
		
		getFocusCount();
		
		getCommentCount();
		
		getAnswerByQid();

	}
	
	/**
	 * UI组件初始化与事件绑定
	 */
	public void bindView(){
		title = (TextView) findViewById(R.id.question_title);
		detail = (TextView) findViewById(R.id.questiondescription);
		count_focus = (TextView) findViewById(R.id.count_focus);
		count_selfcomment = (TextView) findViewById(R.id.count_selfcomment);
		focus_question = (Button) findViewById(R.id.focus_question);
		invite_answer = (Button) findViewById(R.id.invite_answer);
		my_answer = (Button) findViewById(R.id.my_answer);
		answers = (ListView) findViewById(R.id.answers);
		
		//"邀请回答"监听事件
		invite_answer.setOnClickListener(new MyListener());
		//"写我的回答"监听事件
		my_answer.setOnClickListener(new MyListener());
		//获取从QuestionActivity传过来的question对象
		intent = getIntent();
		final Question question = (Question) intent.getSerializableExtra("question");
		flag = false;
		//"关注问题"监听事件
		if(flag){
			focus_question.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					focus_question.setText("关注");
					
					Integer objectId = question.getQuestionId();
					String category = "0"; //0代表问题
					//向服务器返回关注问题
					new HttpClientThread("return_focus", objectId, MainActivity.USERId, category).start();
				}
			});
		}else{
			flag = true;
			focus_question.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					focus_question.setText("已关注");
					
					Integer objectId = question.getQuestionId();
					String category = "0"; //0代表问题
					//向服务器返回关注问题
					new HttpClientThread("return_focus", objectId, MainActivity.USERId, category).start();
				}
			});
		}
		
	}

	/**
	 * 统计问题所对应的的关注数量
	 */
	public void getFocusCount(){
		//获取从QuestionActivity传过来的question对象
		intent = getIntent();
		final Question question = (Question) intent.getSerializableExtra("question");
		//从服务器获取所有的关注
		String focusString = HttpClientThread.acceptHttpClient("allFocus");
		List<Focus> listFocus = JsonTools.getFocus("allFocus", focusString);
				
		//统计该问题的关注数量
		if(listFocus != null){
			int questionCount = 0;
			for(int i = 0; i < listFocus.size(); i++){
				Focus focus = listFocus.get(i);
				if(question.getQuestionId().equals(focus.getObjectId()) && focus.getCategory().equals(0)){
					questionCount++;
				}
			}
			count_focus.setText(""+questionCount);
			//查询关注该问题的用户
			count_focus.setOnClickListener(new MyListener());
		}
	}
	
	/**
	 * 统计该问题对应的所有的评论数
	 */
	public void getCommentCount(){
		//获取从QuestionActivity传过来的question对象
		intent = getIntent();
		final Question question = (Question) intent.getSerializableExtra("question");
		String commentString = HttpClientThread.acceptHttpClient("allComments");
		List<Comment> listcomment = JsonTools.getComment("allComments", commentString);
		
		//统计该问题的评论数量
		if(listcomment != null){
			int commentCount = 0;
			for(int i = 0; i < listcomment.size(); i++){
				Comment comment = listcomment.get(i);
				if(question.getQuestionId().equals(comment.getObjectId()) && comment.getCategory().equals(0)){
					commentCount++;
				}
			}
			count_selfcomment.setText(""+commentCount);
			//查询对该问题做出的评论
			count_selfcomment.setOnClickListener(new MyListener());
		}
	}
	
	/**
	 * 查询对应问题下的所有回答
	 */
	public void getAnswerByQid(){
		//获取从QuestionActivity传过来的question对象
		intent = getIntent();
		final Question question = (Question) intent.getSerializableExtra("question");
		//获取服务端传输的“所有回答”
		final String jsonString = HttpClientThread.acceptHttpClient("allAnswers"); 
		final List<Answer> list = JsonTools.getAnswer("allAnswers", jsonString);
				
		//通过问题id查询对应问题的所有回答
		if(list != null){
			final List<Answer> listByQid = new ArrayList<Answer>();
			
			for(int i = 0; i < list.size(); i++){
				Answer answer = list.get(i);
				if(question.getQuestionId().equals(answer.getQuestionId())){
					Answer ans = new Answer();
					
					ans.setAnswerId(answer.getAnswerId());
					ans.setQuestionId(answer.getQuestionId());
					ans.setUserId(answer.getUserId());
					ans.setAnswerContent(answer.getAnswerContent());
					ans.setAnswerDate(answer.getAnswerDate());
					
					listByQid.add(ans);
				} 
			}
		
			//实例化AnswerAdapter
			adapter = new AnswerAdapter(this);
			//将listByQid添加到adapter
			adapter.setAnswer(listByQid);
			answers.setAdapter(adapter);
			
			answers.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> av, View v, int location, long id) {
					
					intent = new Intent(AnswerActivity.this, AnswerDetailActivity.class);

					Answer simple_answer = (Answer) av.getAdapter().getItem(location);

					intent.putExtra("answer", new Answer(simple_answer.getAnswerId(), simple_answer.getUserId(),
							simple_answer.getQuestionId(), simple_answer.getAnswerContent(), simple_answer.getAnswerDate()));
					intent.putExtra("questiontitle", question.getQuestionDescription());
					
					startActivity(intent);
				}
			});
		} 
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.comment, menu);
		return true;
	}
	
	/**
	 * 内部监听类
	 * @author mq
	 *
	 */
	private class MyListener implements OnClickListener {

		Intent intent = null;
		@Override
		public void onClick(View v) {
			//获取从QuestionActivity传过来的question对象
			intent = getIntent();
			final Question question = (Question) intent.getSerializableExtra("question");

			if(v.getId() == R.id.count_focus){
				//查询关注该问题的所有用户
				
				
			} else if(v.getId() == R.id.count_selfcomment){
				//查询该问题下的所有评论数
				
				
			} else if(v.getId() == R.id.my_answer){
				//撰写我的回答
				
				intent = new Intent(AnswerActivity.this, MyAnswerActivity.class);
				
				Bundle bundle = new Bundle();
				bundle.putInt("questionid", question.getQuestionId());
				//此userId为发布问题的用户id
				bundle.putInt("userId", question.getUserId());
				intent.putExtras(bundle);
				
				startActivity(intent);
			}
		}
		
	}
}
