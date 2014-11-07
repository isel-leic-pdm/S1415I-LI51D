package com.example.contentproviderclientexample2;

import com.example.contentproviderclientexample2.R;

import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends Activity implements LoaderCallbacks<Cursor> {

	private static final String TAG = "CONTENT_PROVIDER";
	private ListView _lv;
	private Uri _todos;
	private SimpleCursorAdapter _adapter;
	
	private static void d(String fmt, Object...args){
		Log.d(TAG,String.format("2:"+fmt,args));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		_lv = (ListView) findViewById(R.id.listView1);
		
		ContentResolver cr = getContentResolver();
		_todos = Uri.parse("content://com.example.contentproviderserverexample/todos");
							
		_adapter = new SimpleCursorAdapter(
				this, R.layout.list_item, null,
				new String[]{"_id","desc"},
				new int[]{R.id.textView1, R.id.textView2},
				0);
		_lv.setAdapter(_adapter);
		
		getLoaderManager().initLoader(1, null, this);		
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		d("onCreateLoader");
		return new CursorLoader(this, _todos, null, null, null, null);
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
		d("onLoadFinished");
		_adapter.swapCursor(data);
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		d("onLoaderReset");
		_adapter.swapCursor(null);		
	}
}
