package com.example.broadcastreceiverexample;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Intent intent = getIntent();
		TextView tv = (TextView) findViewById(R.id.textView);
		String s = intent.getStringExtra("msg");
		if(s != null){
			tv.setText(s);
		}
	}
}
