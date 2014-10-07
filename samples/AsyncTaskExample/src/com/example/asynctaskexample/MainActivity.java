package com.example.asynctaskexample;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView _tv1;
	private Button _btn1;
	private int _count;
	private Button _btn2;
	private TextView _tv2;
	protected MyAsyncTask _task;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		_tv1 = (TextView) findViewById(R.id.tv1);
		_btn1 = (Button) findViewById(R.id.button1);
		_btn1.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// UI thread
				_task = new MyAsyncTask(_tv1);
				_task.execute(Integer.valueOf(5000));
			}			
		});
		_count = 0;
		_btn2 = (Button) findViewById(R.id.button2);
		_tv2 = (TextView) findViewById(R.id.tv2);
		_tv2.setText(Integer.toString(_count));
		_btn2.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				_count += 1;
				_tv2.setText(Integer.toString(_count));
				if(_task != null 
						&& _task.getStatus() != Status.FINISHED){
					_task.cancel(true);
				}				
			}			
		});
		
	}
}

class MyAsyncTask extends AsyncTask<Integer,Integer,String>{

	private static final String TAG = "MyAsyncTask";
	private TextView _tv;
	
	public MyAsyncTask(TextView tv){
		_tv = tv;
	}

	@Override
	protected String doInBackground(Integer... input) {
		// Worker thread
		try {
			for(int i = 0 ; i<=input[0].intValue() ; i+=1000){
				Thread.sleep(1000);
				if(isCancelled()){
					return "cancelled";
				}
				this.publishProgress(Integer.valueOf(i));
			}
			return input[0].toString();
		} catch (InterruptedException e) {
			Log.e(TAG, "interruped");
			return null;
		}
	}
	
	@Override
	protected void onProgressUpdate(Integer... progress){
		// UI Thread
		_tv.setText(progress[0].toString());
	}
	
	@Override
	protected 
	void onPostExecute(String result){
		// UI thread
		Log.d(TAG,"onPostExecute");
		_tv.setText(result);
	}
}


