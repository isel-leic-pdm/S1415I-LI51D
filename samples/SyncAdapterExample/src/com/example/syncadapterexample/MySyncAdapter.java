package com.example.syncadapterexample;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.SyncResult;
import android.os.Bundle;

public class MySyncAdapter extends AbstractThreadedSyncAdapter{

	public MySyncAdapter(Context context, boolean autoInitialize) {
		super(context, autoInitialize);		
	}

	@Override
	public void onPerformSync(
			Account account, 
			Bundle extras, 
			String authority,
			ContentProviderClient provider, 
			SyncResult syncResult) {
		
		L.d("MySyncAdapter.onPerformSync");
		
	}

}
