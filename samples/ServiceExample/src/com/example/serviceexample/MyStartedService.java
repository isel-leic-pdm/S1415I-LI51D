package com.example.serviceexample;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyStartedService extends Service{

	@Override
	public IBinder onBind(Intent intent) {		
		return null;
	}

	
}

class MyIntentService extends IntentService{

	public MyIntentService() {
		super("name");		
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
		
	}
	
}
