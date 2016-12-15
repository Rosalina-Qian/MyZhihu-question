package com.mq.activity;

import android.os.Bundle;

import com.example.mydemo.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.Toast;
@SuppressLint("NewApi")
public class MainActivity extends Activity {

	public static final String USERId = "1";;
	
	PopupMenu popupMenu;
	
	Intent intent = null;
	
	Menu menu;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		popupMenu = new PopupMenu(this, findViewById(R.id.add_main));
		menu = popupMenu.getMenu();
		getMenuInflater().inflate(R.menu.main, menu);
		
		popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				
				switch(item.getItemId()){
				
				case R.id.main_menu_article:
					Toast.makeText(MainActivity.this, "写文章", Toast.LENGTH_LONG).show();
					break;
				
				case R.id.main_menu_answer:
					intent = new Intent(MainActivity.this, QuestionActivity.class);	
					startActivity(intent);
					break;
				
				case R.id.main_menu_question:
					Toast.makeText(MainActivity.this, "提问", Toast.LENGTH_LONG).show();
					break;
				
				default:
					break;
				}
				
				return false;
			}
		});
	}

	public void popupMenu(View v){
		popupMenu.show();
	}
}
