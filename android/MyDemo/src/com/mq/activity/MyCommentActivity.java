package com.mq.activity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import com.mq.adapter.CommentAdapter;
import com.mq.entities.Answer;
import com.mq.entities.Comment;
import com.mq.http.HttpClientThread;
import com.mq.http.HttpUtils;
import com.example.mydemo.R;
import com.mq.operation.JsonTools;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

@SuppressLint("NewApi")
public class MyCommentActivity extends Activity {

//	private LinearLayout div_topbar_allcomments, div_bottombar_allcomments; 
	private ListView list_comments;
	private EditText edit_mycomment;
	private Button cancel_comment, add_comment;
	
	private CommentAdapter adapter;
	
	Intent intent = null;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_comment);
		
		ActionBar actionbar = getActionBar();
		actionbar.hide();
		
//		div_topbar_allcomments = (LinearLayout) findViewById(R.id.div_topbar_allcomments);
//		div_bottombar_allcomments = (LinearLayout) findViewById(R.id.div_bottombar_allcomments);
		
		list_comments = (ListView) findViewById(R.id.list_comments);
		edit_mycomment = (EditText) findViewById(R.id.edit_mycomment);
		cancel_comment = (Button) findViewById(R.id.cancel_comment);
		add_comment = (Button) findViewById(R.id.add_comment);
		
		CommentAdapterByAid();
		
		cancel_comment.setOnClickListener(new MyListener());
		add_comment.setOnClickListener(new MyListener());
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_comment, menu);
		return true;
	}
	
	/**
	 * 评论列表适配器
	 */
	public void CommentAdapterByAid(){
		
		intent = getIntent();
		Answer answer = (Answer) intent.getSerializableExtra("answerDetail");
		
		final String jsonString = HttpClientThread.acceptHttpClient("allComments");
		final List<Comment> list = JsonTools.getComment("allComments", jsonString);

		if(list != null){
			List<Comment> listByAid = new ArrayList<Comment>();
			for(int i = 0; i < list.size(); i++){
				Comment comment = list.get(i);
				if(answer.getAnswerId().equals(comment.getObjectId()) && comment.getCategory().equals(1)){
					Comment com = new Comment();
					com.setUserId(comment.getUserId());
					com.setObjectId(comment.getObjectId());
					com.setCategory(comment.getCategory());
					com.setCommentContent(comment.getCommentContent());
					com.setCommentTime(comment.getCommentTime());
					listByAid.add(com);
				}
			}

			adapter = new CommentAdapter(this);
			adapter.setComment(listByAid);
			list_comments.setAdapter(adapter);
		}
	}
	
	
	private class MyListener implements OnClickListener {
		
		@Override
		public void onClick(View v) {
			
			Intent intent = getIntent();
			
			Answer answer = (Answer) intent.getSerializableExtra("answerDetail");
			
			if(v.getId() == R.id.add_comment){
				
				String category = "1";
				new HttpClientThread("return_replyComment", MainActivity.USERId, answer.getUserId(), edit_mycomment.getText().toString(), category).start();

			} else if(v.getId() == R.id.cancel_comment){
				//返回上一次呢个
			}
			
		}
		
	}

}
