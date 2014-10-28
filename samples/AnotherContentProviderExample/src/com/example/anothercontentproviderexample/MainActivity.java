package com.example.anothercontentproviderexample;

import java.util.Calendar;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	protected static final int PICK_REQUEST = 1;
	private static final String TAG = "ANOTHER_CONTENT_PROVIDER_EXAMPLE";
	private Button _btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		_btn = (Button)findViewById(R.id.button1);
		_btn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent i = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
				startActivityForResult(i, PICK_REQUEST);				
			}			
		});
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		if(resultCode == RESULT_OK && requestCode == PICK_REQUEST){
			Uri contact = data.getData();
			ContentResolver cr = getContentResolver();
			Cursor c = cr.query(contact, null, null, null, null);
			if(!c.moveToNext()){
				return;
			}
			String id = c.getString(c.getColumnIndex(ContactsContract.Contacts._ID));
			c = cr.query(
					ContactsContract.RawContacts.CONTENT_URI, 
					null,
					ContactsContract.RawContacts.CONTACT_ID + "= ?",
					new String[] {id},
					null);
			if(!c.moveToNext()){
				return;
			}
			String rawId = c.getString(c.getColumnIndex(ContactsContract.RawContacts._ID));
							
			
			ContentValues row = new ContentValues();
			row.put(ContactsContract.Data.RAW_CONTACT_ID, rawId);
			row.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Event.CONTENT_ITEM_TYPE);
			row.put(ContactsContract.CommonDataKinds.Event.START_DATE, "2015-1-1");			
			row.put(ContactsContract.CommonDataKinds.Event.TYPE, ContactsContract.CommonDataKinds.Event.TYPE_BIRTHDAY);
			
			cr.insert(ContactsContract.Data.CONTENT_URI, row);
			
		}
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
