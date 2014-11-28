package com.example.fragmentsexample;

import android.util.Log;

public class MyLog {

	private static final String TAG = "FRAGMENTS_EXAMPLE";

	public static void d(String fmt, Object...args){
		Log.d(TAG,String.format(fmt,args));
	}
}
