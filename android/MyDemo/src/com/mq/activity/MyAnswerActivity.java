package com.mq.activity;

import android.os.Bundle;

import com.example.mydemo.R;
import com.mq.http.HttpClientThread;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MyAnswerActivity extends Activity {

	private String username = "haha";
	private TextView id;
	private EditText edit_myanswer;
	private Button btn;
	
	Intent intent = null;
	
	@SuppressLint("SimpleDateFormat")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_answer);
		//弹出输入框
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
		
		id = (TextView) findViewById(R.id.id);
		edit_myanswer = (EditText) findViewById(R.id.write_myanswer);
		btn = (Button) findViewById(R.id.myanswer_btn);
		
		intent = getIntent();
		
		final Bundle ids = intent.getExtras();
		
		id.setText("问题id:"+ids.getInt("questionid") + "  发布该问题的用户Id:"+ids.getInt("userId"));
		
		final String qid = ids.getInt("questionid")+"";
		
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//向服务端传送我的回答
				new HttpClientThread("return_myanswer", username, MainActivity.USERId, qid, edit_myanswer.getText().toString()).start();
				
			}
		});
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.my_answer, menu);
		return true;
	}

}
