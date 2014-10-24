package com.example.loaderexample;

import android.app.Activity;
import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.AsyncTaskLoader;
import android.content.Loader;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {

	private static final int LOAD_ID = 1;
	private static final String TAG = "LOADER_EXAMPLE";
	
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
		Loader<String> loader = lm.initLoader(LOAD_ID, null, new LoaderCallbacks<String>(){

			@Override
			public Loader<String> onCreateLoader(int id, Bundle args) {
				d("onCreateLoader");
				
				return new AsyncTaskLoader<String>(MainActivity.this){
					private String _data;

					@Override
					public String loadInBackground() {
						try {
							d("loadInBackGround");
							Thread.sleep(1000);
							return "ok";
						} catch (InterruptedException e) {
							return "interruped";
						}
					}
					
					@Override
					public void onStartLoading(){
						d("onStartLoading");						
						if(_data != null){
							deliverResult(_data);
						}else{
							forceLoad();
						}
					}
					
					@Override
					public void deliverResult(String data){
						_data = data;
						super.deliverResult(data);
					}
				};
				
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
}
