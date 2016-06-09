package com.programme.activity;

import com.programme.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ErrorActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.error_activity);
	}
	public void restart(View view){
			Intent intent = new Intent(getApplicationContext(), MainActivity.class);
			startActivity(intent);
	}
	
	@Override
	protected void onPause(){
		super.onPause();
		finish();
	}
}
