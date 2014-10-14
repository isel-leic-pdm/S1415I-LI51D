package com.example.actionbarexample;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class AppSettingsActivity extends PreferenceActivity{

	@Override
	protected void onCreate(Bundle appState){
		super.onCreate(appState);
		
		getFragmentManager().beginTransaction()
        	.replace(android.R.id.content, new SettingsFragment())
        	.commit();
	}	
}


