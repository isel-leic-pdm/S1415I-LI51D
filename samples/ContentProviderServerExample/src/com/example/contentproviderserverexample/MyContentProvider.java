package com.example.contentproviderserverexample;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

public class MyContentProvider extends ContentProvider {

	private static final String AUTHORITY = MyContentProviderContract.Authority;
	
	private static final int ROOT_MATCH = 0;
	private static final int TODOS_COLL_MATCH = 1;
	private static final int TODOS_ITEM_MATCH = 2;

	private static final String TAG = "CONTENT_PROVIDER";
	
	private static void d(String fmt, Object...args){
		Log.d(TAG,String.format(fmt,args));
	}

	private MyOpenHelper _ds;

	@Override
	public boolean onCreate() {		
		_ds = new MyOpenHelper(getContext());
		return true; 
	}
	
	private static UriMatcher _matcher;
	
	static {
		_matcher = new UriMatcher(ROOT_MATCH);
		_matcher.addURI(AUTHORITY, "todos", TODOS_COLL_MATCH);
		_matcher.addURI(AUTHORITY, "todos/#", TODOS_ITEM_MATCH);
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		
		SQLiteDatabase db = _ds.getReadableDatabase();
		Cursor c = null;
		Context ctx = getContext();
		d(ctx.getPackageName());
		
		switch(_matcher.match(uri)){
		
		case ROOT_MATCH:			
		case TODOS_COLL_MATCH:
			d("Uri = %s, TODOS_COLL_MATCH", uri);
			c = db.query("todos", projection, 
					selection, selectionArgs, 
					null, null, sortOrder);
			
			c.setNotificationUri(
					getContext().getContentResolver(), 
					uri);
			
			return c;			
			
		case TODOS_ITEM_MATCH:
			d("Uri = %s, TODOS_ITEM_MATCH", uri);
			long id = ContentUris.parseId(uri);
			c = db.query("todos", projection, 
					"_id = ?", new String[]{Long.toString(id)},
					null, null, null);
			c.setNotificationUri(
					getContext().getContentResolver(), 
					uri);
					
			return c;
			
		default:
			return null;		
		}
		
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		SQLiteDatabase db = _ds.getWritableDatabase();
		db.insert("todos", null, values);
		getContext().getContentResolver().notifyChange(uri, null);
		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

}
