package com.example.viewuriexample;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	private Button _btn1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final EditText _et1 = (EditText) findViewById(R.id.editText1);
		_btn1 = (Button) findViewById(R.id.button1);
		_btn1.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				String text = _et1.getText().toString();
				Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(text));
				startActivity(i);
			}			
		});
	}
}
