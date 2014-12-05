package com.example.locationstudy;

import android.util.Log;

public class L {
	private static final String TAG = "LOCATION_STUDY";

	public static void d(String fmt, Object ...args){
		Log.d(TAG,String.format(fmt,args));
	}
}
