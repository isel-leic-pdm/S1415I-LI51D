package com.example.syncadapterexample;

import android.util.Log;

public class L {
	private static final String TAG = "SYNC_ADAPTER_EXAMPLE";

	public static void d(String fmt, Object... args){
		Log.d(TAG, String.format(fmt, args));
	}	
}
