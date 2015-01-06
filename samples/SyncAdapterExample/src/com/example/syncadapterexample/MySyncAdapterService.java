package com.example.syncadapterexample;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MySyncAdapterService extends Service {

	private MySyncAdapter _adapter;

	@Override
	public void onCreate(){
		_adapter = new MySyncAdapter(this,true);
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		return _adapter.getSyncAdapterBinder();
	}
}
