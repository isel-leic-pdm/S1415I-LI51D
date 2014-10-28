package com.example.sqliteexample;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	private static final String TAG = "SQLITE_EXAMPLE";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		SQLiteDatabase db = new MyOpenHelper(this).getWritableDatabase();
		Cursor c = null;
		try{
			c = db.query("todos", null, "desc = ?", 
					new String[]{"learn SQL"}, null, null, null);
			int idIx = c.getColumnIndex("_id");
			int descIx = c.getColumnIndex("desc");
			while(c.moveToNext()){
				d("_id = %d, desc = %s",
						c.getInt(idIx),
						c.getString(descIx)
						);
			}
		}finally{
			if(c!=null) c.close();
		}
		ContentValues values = new ContentValues();
		values.put("desc", "learn SQL");
		
		db.insert("todos", null, values);
	}
	
	private static void d(String fmt, Object...args){
		Log.d(TAG,String.format(fmt, args));
	}
}

class MyOpenHelper extends SQLiteOpenHelper{

	private static final String TAG = "SQLITE_EXAMPLE";

	public MyOpenHelper(Context context) {
		super(context, "todos.db", null, 1);		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.d(TAG, "MyOpenHelper.onCreate");
		db.execSQL("create table todos (_id integer primary key autoincrement, desc text)");		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
				
	}
	
}
