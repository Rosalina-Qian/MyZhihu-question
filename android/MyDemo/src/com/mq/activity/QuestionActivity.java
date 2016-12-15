package com.mq.activity;

import android.os.Bundle;

import java.util.List;

import com.mq.adapter.QuestionAdapter;
import com.mq.entities.Question;
import com.mq.http.HttpClientThread;
import com.example.mydemo.R;
import com.mq.operation.JsonTools;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class QuestionActivity extends Activity {

	private ListView list_question;
	private QuestionAdapter adapter;
	
	Intent intent = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_question);
		
		list_question = (ListView) findViewById(R.id.list_question);
		
    	final String jsonString = HttpClientThread.acceptHttpClient("allQuestions"); 
		final List<Question> list = JsonTools.getQuestion("allQuestions", jsonString);
		
		//实例化QuestionAdapter
		adapter = new QuestionAdapter(this);
		adapter.setQuestion(list);
		list_question.setAdapter(adapter);
		
		list_question.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> av, View v, int position, long id) {
				
				intent = new Intent(QuestionActivity.this, AnswerActivity.class);

				Question question = (Question) av.getAdapter().getItem(position);
				
				intent.putExtra("question", new Question(
						question.getQuestionId(), question.getUserId(), question.getTopicId(),
						question.getQuestionDescription(), question.getFurtherExplanations(),
						question.getQuestionDate()));
				
				startActivity(intent);
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.question, menu);	
		return true;
	}

}
