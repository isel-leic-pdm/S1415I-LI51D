package com.example.contentproviderexample;

import android.support.v7.app.ActionBarActivity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends ActionBarActivity {

	private ListView _listView;
	private ContentResolver _cr;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		_listView = (ListView) findViewById(R.id.listView1);
		
		_cr = getContentResolver();
		
		Uri uri = ContactsContract.Data.CONTENT_URI;
		String[] projection = null;
		String selection = 
				String.format("%s = 'Alice' and %s = 'vnd.android.cursor.item/contact_event'",
						ContactsContract.Contacts.DISPLAY_NAME,
						ContactsContract.Data.MIMETYPE
						);				
		String[] selectionArgs = null;
		String sortOrder = null;
		Cursor cursor = _cr.query(
				uri, 
				projection, 
				selection, 
				selectionArgs, 
				sortOrder);
		
		for(String name : cursor.getColumnNames()){
			Log.d("CONTACTS","col="+name);
		}
		
		String cols[] = new String[]{
				ContactsContract.Contacts.DISPLAY_NAME,
				ContactsContract.Contacts.PHOTO_THUMBNAIL_URI,
				ContactsContract.Data.MIMETYPE
		};
		int widgets[] = new int[]{
				R.id.itemName,
				R.id.photoView,
				R.id.itemBd
		};
		
		SimpleCursorAdapter adapter = 
				new SimpleCursorAdapter(
						this, 
						R.layout.contact_item, 
						cursor, 
						cols, widgets,0);
		_listView.setAdapter(adapter);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
