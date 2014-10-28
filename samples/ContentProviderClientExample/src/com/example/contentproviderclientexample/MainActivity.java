package com.example.contentproviderclientexample;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	private static final String TAG = "CONTENT_PROVIDER";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ContentResolver cr = getContentResolver();
		Cursor c = cr.query(
				Uri.parse("content://com.example.contentproviderserverexample/todos"),
				null, null, null, null);
		Log.d(TAG, Integer.toString(c.getCount()));
	}
}
