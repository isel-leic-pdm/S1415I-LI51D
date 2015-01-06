package com.example.syncadapterexample;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.ContentResolver;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		AccountManager am = (AccountManager) this.getSystemService(Activity.ACCOUNT_SERVICE);
		Account account = new Account("Alice", "syncadapterexample.com");
		am.addAccountExplicitly(account, null, null);
		
		ContentResolver.setSyncAutomatically(account, "com.example.syncadapterexample", true);
		
		ContentResolver.addPeriodicSync(
				account, 
				"com.example.syncadapterexample", 
				Bundle.EMPTY, 
				2*60);
	}
}
