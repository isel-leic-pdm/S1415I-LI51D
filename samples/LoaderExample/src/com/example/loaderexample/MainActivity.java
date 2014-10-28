package com.example.loaderexample;

import android.app.Activity;
import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {

	private static final int LOAD_ID = 1;
	public static final String TAG = "LOADER_EXAMPLE";
	
	private static void d(String fmt, Object... args){
		Log.d(TAG,String.format(fmt, args));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final TextView tv = (TextView) findViewById(R.id.textView);
		
		d("onCreate: before initLoader");
		LoaderManager lm = getLoaderManager();
		Loader<String> loader = lm.initLoader(LOAD_ID, null, 
			new LoaderCallbacks<String>(){

			@Override
			public Loader<String> onCreateLoader(int id, Bundle args) {
				d("onCreateLoader");				
				return new MyLoader(MainActivity.this);				
			}

			@Override
			public void onLoadFinished(Loader<String> loader, String data) {
				d("onLoadFinished");
				tv.setText(data);				
			}

			@Override
			public void onLoaderReset(Loader<String> loader) {
				d("onLoaderReset");				
			}			
		});
		d("onCreate: after initLoader");
	}
	
	@Override
	protected void onStart(){
		d("MainActivity:onStart before super");
		super.onStart();
		d("MainActivity:onStart after super");
	}
	
	@Override
	protected void onResume(){
		d("MainActivity:onResume before super");
		super.onResume();
		d("MainActivity:onResume after super");
	}
	
	@Override
	protected void onPause(){
		d("MainActivity:onPause");
		super.onPause();
	}
	
	@Override
	protected void onStop(){
		d("MainActivity:onStop");
		super.onStop();
	}
}

class MyLoader extends AsyncTaskLoader<String>{
	
	public MyLoader(Context context) {
		super(context);		
	}

	private String _data;
	
	private static void d(String fmt, Object... args){
		Log.d(MainActivity.TAG,String.format(fmt, args));
	}

	@Override
	public String loadInBackground() {
		try {
			d("loadInBackGround");
			for(int i = 0 ; i<10 ; ++i){
				Thread.sleep(1000);
				d("loadInBackground: %d",i);
			}
			return "ok";
		} catch (InterruptedException e) {
			return "interruped";
		}
	}
	
	@Override
	protected void onStartLoading(){
		d("MyLoader:onStartLoading");						
		if(_data != null){
			deliverResult(_data);
		}else{
			forceLoad();
		}
	}
	
	@Override
	public void deliverResult(String data){
		d("MyLoader:deliverResult");
		_data = data;
		super.deliverResult(data);
	}
	
	@Override
	protected void onStopLoading(){
		d("MyLoader:onStopLoading");
		super.onStopLoading();
	}
	
	@Override
	protected boolean onCancelLoad(){
		d("MyLoader:onCancelLoad");
		return super.onCancelLoad();
	}
};
