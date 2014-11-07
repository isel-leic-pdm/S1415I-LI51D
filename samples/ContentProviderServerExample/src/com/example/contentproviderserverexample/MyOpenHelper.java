package com.example.contentproviderserverexample;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

class MyOpenHelper extends SQLiteOpenHelper{

	private static final String TAG = "CONTENT_PROVIDER";

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
