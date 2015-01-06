package com.example.parsestudy;
import com.parse.Parse;

import android.app.Application;


public class TheApplication extends Application {

	@Override
	public void onCreate(){		
		Parse.enableLocalDatastore(this);		 
		Parse.initialize(this, toedit, toedit);
	}
}
