package com.example.parsestudy;

import com.parse.ParseObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ParseObject testObject = new ParseObject("ClassExample2");
		testObject.put("columnName", "columnValue");
		testObject.put("ColumnName2", "columnValue2");
		testObject.saveInBackground();
		Log.d("TAG", "after saveInBackground");
	}
}
