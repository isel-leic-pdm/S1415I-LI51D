package com.example.syncadapterexample;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyAuthenticatorService extends Service {

	private MyAuthenticator _authenticator;

	@Override
	public void onCreate(){
		_authenticator = new MyAuthenticator(this);
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		return _authenticator.getIBinder();
	}
}
