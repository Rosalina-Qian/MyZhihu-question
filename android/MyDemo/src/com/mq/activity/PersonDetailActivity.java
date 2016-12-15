package com.mq.activity;

import android.os.Bundle;

import com.example.mydemo.R;

import android.app.Activity;
import android.view.Menu;

public class PersonDetailActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_person_detail);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.person_detail, menu);
		return true;
	}

}
