package com.mq.activity;

import android.os.Bundle;

import com.mq.entities.Answer;
import com.mq.entities.SupportNum;
import com.mq.http.HttpClientThread;
import com.mq.operation.JsonTools;

import java.util.List;

import com.example.mydemo.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class AnswerDetailActivity extends Activity {

	private TextView title;
	private TextView answerdetail, answerdate;
	private ImageButton votebtn;
	private Button comment_channel, focusUser;
	private TextView help_channel, thank_channel, collect_channel;
	private TextView voteNum, username_title;
	
	private View focususer_layout;
	
	Intent intent = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_answer_detail);
		
		LayoutInflater inflater = LayoutInflater.from(AnswerDetailActivity.this);
		focususer_layout = inflater.inflate(R.layout.person, null);
		
		RelativeLayout mainlayout = (RelativeLayout) findViewById(R.id.person_box);
		RelativeLayout layout = (RelativeLayout) focususer_layout.findViewById(R.id.focusperson_layout);
		
		mainlayout.addView(layout);
		
		bindView();

		intent = getIntent();
		
		title.setText(""+intent.getStringExtra("questiontitle"));
		final Answer answer = (Answer) intent.getSerializableExtra("answer");
		
		answerdetail.setText(""+answer.getAnswerContent());
		answerdate.setText("创建于 "+answer.getAnswerDate());
		username_title.setText(""+answer.getUserId());
		
		getSupportNum();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.comment_detail, menu);
		return true;
	}

	//UI组件初始化与事件绑定
	public void bindView(){
		title = (TextView) findViewById(R.id.questiontitle);
		answerdetail = (TextView) findViewById(R.id.answerdetail);
		answerdate = (TextView) findViewById(R.id.answerDate);
		help_channel = (TextView) findViewById(R.id.help_channel);
		thank_channel = (TextView) findViewById(R.id.thank_channel);
		collect_channel = (TextView) findViewById(R.id.collect_channel);
		voteNum = (TextView) findViewById(R.id.voteNum);
		comment_channel = (Button) findViewById(R.id.comment_channel);
		votebtn = (ImageButton) findViewById(R.id.vote_channel);
		focusUser = (Button) findViewById(R.id.focus_user);
		username_title = (TextView) findViewById(R.id.username_title);
		
		help_channel.setOnClickListener(new MyListener());
		thank_channel.setOnClickListener(new MyListener());
		collect_channel.setOnClickListener(new MyListener());
		comment_channel.setOnClickListener(new MyListener());
		votebtn.setOnClickListener(new MyListener());
		focusUser.setOnClickListener(new MyListener());
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
						
			intent = getIntent();
			final Answer answer = (Answer) intent.getSerializableExtra("answer");
			
			if(v.getId() == R.id.vote_channel){
				//支持或者反对
				
				popupDialog();
				
			} else if(v.getId() == R.id.help_channel){
				//没有帮助
				
				if(help_channel.isSelected()){
					help_channel.setSelected(false);
				}else{
					help_channel.setSelected(true);
				}
			
			} else if(v.getId() == R.id.thank_channel){
				//感谢
				
				if(thank_channel.isSelected()){
					//取消点亮，取消感谢
					thank_channel.setSelected(false);
					String category = "1";//1表示回答
					new HttpClientThread("return_ExpressThanks", MainActivity.USERId, answer.getUserId(), category).start();
				}else{
					//被点亮时表示感谢，并向服务器传送表示感谢
					thank_channel.setSelected(true);
					String category = "1";//1表示回答
					new HttpClientThread("return_ExpressThanks", MainActivity.USERId, answer.getUserId(), category).start();
				}	
			
			} else if(v.getId() == R.id.collect_channel){
				//跳转到收藏
				
				intent = new Intent(AnswerDetailActivity.this, AddCollectionActivity.class);
				
				intent.putExtra("answerDetail", new Answer(answer.getAnswerId(), answer.getUserId(),
						answer.getQuestionId(), answer.getAnswerContent(), answer.getAnswerDate()));
					
				startActivity(intent);
				
				
			} else if(v.getId() == comment_channel.getId()){
				//跳转到评论
				
				intent = new Intent(AnswerDetailActivity.this, MyCommentActivity.class);
				
				intent.putExtra("answerDetail", new Answer(answer.getAnswerId(), answer.getUserId(),
						answer.getQuestionId(), answer.getAnswerContent(), answer.getAnswerDate()));
				
				startActivity(intent);
			} else if(v.getId() == R.id.focus_user){
				//关注用户

				Integer objectId = answer.getUserId();
				String category = "5"; //0代表问题
				
				new HttpClientThread("return_focus", objectId, MainActivity.USERId, category).start();
			}
		}
		
	}
	
	/**
	 * 为答案投票
	 */
	public void popupDialog(){
		
		intent = getIntent();
		final Answer answer = (Answer) intent.getSerializableExtra("answer");
		
		AlertDialog.Builder builder = new AlertDialog.Builder(AnswerDetailActivity.this);
		LayoutInflater inflater = getLayoutInflater();
		View view = inflater.inflate(R.layout.vote_dialog, null);
		builder.setView(view);
		
		final TextView agree = (TextView) view.findViewById(R.id.vote_agree);
		final TextView disagree = (TextView) view.findViewById(R.id.vote_disagree);
		
		//支持事件的监听并返回给服务器
		agree.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				agree.setSelected(true);
				disagree.setSelected(false);
				//1代表支持
				new HttpClientThread("return_support", MainActivity.USERId, 
						answer.getAnswerId(), 1).start();
				getSupportNum();
			}
		});
		
		//反对事件的监听并返回给服务器
		disagree.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				disagree.setSelected(true);
				agree.setSelected(false);
				//0代表反对
				new HttpClientThread("return_oppose", MainActivity.USERId, answer.getAnswerId(), 0).start();
				getSupportNum();
			}
		});
		
		AlertDialog dialog = builder.create();
		dialog.show();
	}
	
	/**
	 * 获取支持数
	 * @return
	 */
	public void getSupportNum(){
		
		intent = getIntent();
		final Answer answer = (Answer) intent.getSerializableExtra("answer");
		
		String sPNumString = HttpClientThread.acceptHttpClient("supportNum"); 
		final List<SupportNum> listsPNum= JsonTools.getSupportNum("supportNum", sPNumString);
		
		//返回支持数量
		if(listsPNum != null){
			int count_vote = 0;
			for(int i = 0; i < listsPNum.size(); i++){
				SupportNum spNum = listsPNum.get(i);
				if(spNum.getAnswerId().equals(answer.getAnswerId())){
					count_vote++;
				}
			}
			voteNum.setText(""+count_vote);
		} else{
			voteNum.setText("");
		}
	}
	
}
