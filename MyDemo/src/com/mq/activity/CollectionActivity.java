package com.mq.activity;

import android.os.Bundle;

import com.example.mydemo.R;
import com.mq.entities.Answer;
import com.mq.http.HttpClientThread;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CollectionActivity extends Activity {

	private EditText collectionName, collectionContent;
	private TextView finished;
	private Button returned;
	
	Intent intent = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_collection);
		
		collectionName = (EditText) findViewById(R.id.collectionName);
		collectionContent = (EditText) findViewById(R.id.collectionContent);
		finished = (TextView) findViewById(R.id.collected_ok);
		returned = (Button) findViewById(R.id.cancel_collection);
		
		intent = getIntent();

		final Answer answer = (Answer) intent.getSerializableExtra("collect_answer");
		
		finished.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				String favoriteName = collectionName.getText().toString();
				String favoriteDescription = collectionContent.getText().toString();
				String category = "1";
				
				//将内容传回服务器
				new HttpClientThread("return_addCollection", MainActivity.USERId, answer.getUserId(), category, favoriteName, favoriteDescription).start();
			}
		});
		
		returned.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				//返回上一个页面
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.collection, menu);
		return true;
	}

}
