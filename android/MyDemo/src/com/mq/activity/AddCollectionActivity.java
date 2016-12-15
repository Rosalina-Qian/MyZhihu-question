package com.mq.activity;

import android.os.Bundle;

import com.example.mydemo.R;
import com.mq.entities.Answer;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AddCollectionActivity extends Activity implements OnClickListener{

	private RelativeLayout layout;
	private TextView addcollect, finished;
	
	Intent intent = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_collection);
		
		addcollect = (TextView) this.findViewById(R.id.add_collection);
        finished = (TextView) this.findViewById(R.id.finished);
        layout = (RelativeLayout) findViewById(R.id.pop_layout);
        
        layout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "点击窗体外部关闭窗体", Toast.LENGTH_SHORT).show();
				
			}
		});
        
        addcollect.setOnClickListener(this);
        finished.setOnClickListener(this);
	}

	@Override  
    public boolean onTouchEvent(MotionEvent event){  
        finish();  
        return true;  
    }  
      

	@Override
	public void onClick(View v) {
		
		intent = getIntent();

		final Answer answer = (Answer) intent.getSerializableExtra("answerDetail");

		switch (v.getId()) { 
        	case R.id.add_collection: 
        		intent = new Intent(AddCollectionActivity.this, CollectionActivity.class);
    		
        		intent.putExtra("collect_answer", new Answer(answer.getAnswerId(), answer.getUserId(),
					answer.getQuestionId(), answer.getAnswerContent(), answer.getAnswerDate()));
        	
        		startActivity(intent);
        	break;  
        
        	case R.id.finished:                 
        		break;  
      
        	default:  
        		break;  
        }  
        finish(); 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_collection, menu);
		return true;
	}

}
