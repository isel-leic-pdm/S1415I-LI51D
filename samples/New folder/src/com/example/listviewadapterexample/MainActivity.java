package com.example.listviewadapterexample;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {

	private ListView _lv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		_lv = (ListView)findViewById(R.id.listView1);
		_lv.addFooterView(new ProgressBar(this));
		CustomAdapterExample adapter = new CustomAdapterExample(this, R.layout.item_layout); 
		_lv.setAdapter(adapter);
		_lv.setOnScrollListener(adapter);		
	}
}

class CustomAdapterExample 
	extends BaseAdapter 
	implements OnScrollListener{

	private int _layout;
	private int _count = 0;
	private LayoutInflater _layoutInflater;
	private boolean _updating;	
	private int _scrollFirst;
	private int _scrollCount;

	@Override
	public int getCount() {
		return _count;
	}

	@Override
	public Object getItem(int i) {
		return getModel(i);
	}
	
	private Student getModel(int i){
		return new Student(i, String.format("Item %s",i));
	}

	@Override
	public long getItemId(int i) {
		return i;
	}

	@Override
	public View getView(int i, View view, ViewGroup parent) {
		if(view == null){
			View newView = _layoutInflater.inflate(_layout, null);
			newView.setTag(createViewHolderFor(newView));
			bindModel(getModel(i), newView.getTag());
			return newView;
		}else{
			bindModel(getModel(i), view.getTag());
			return view;
		}		
	}
	
	private void bindModel(Student student, Object viewModelObject) {
		ViewModel viewModel = (ViewModel) viewModelObject;
		viewModel.name.setText(student.name);
		viewModel.number.setText(Integer.toString(student.number));		
	}

	private ViewModel createViewHolderFor(View newView) {
		return new ViewModel(newView);
	}

	public CustomAdapterExample(Context ctx, int layout){
		_layout = layout;
		_layoutInflater = (LayoutInflater)ctx.getSystemService
			      (Context.LAYOUT_INFLATER_SERVICE);	
		_count = 16 ;
	}

	@Override
	public void onScroll(AbsListView view, int first, int count, int total) {
		d("onScroll: %s, %s, %s", first, count, total);
		_scrollFirst = first;
		_scrollCount = count;	
				
	}

	@Override
	public void onScrollStateChanged(AbsListView list, int state) {
		d("onScrollStateChanged: %s",state);
		if(state != 0) return;		
		if(_scrollFirst+_scrollCount >= _count - 2){			
			if(_updating) return;
			_updating = true;
			d("updating...");
			new AsyncTask<Void,Void,Void>(){

				@Override
				protected Void doInBackground(Void... arg0) {
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// ignore it
					}
					return null;
				}
				
				@Override
				protected void onPostExecute(Void arg){
					_count += 10;
					CustomAdapterExample.this.notifyDataSetChanged();
					_updating = false;
					d("...done");
				}
			}.execute();
		}
	}
	
	private static void d(String fmt, Object... args){
		Log.d("ListViewAdapterExample", String.format(fmt,args));
	}
}

class Student{
	public int number;
	public String name;

	Student(int number, String name){
		this.number = number;
		this.name = name;
	}	
}

class ViewModel{
	public final TextView name;
	public final TextView number;
	private static int nextId = 0; 
	
	public ViewModel(View view){
		name = (TextView) view.findViewById(R.id.itemLayoutName);
		number = (TextView) view.findViewById(R.id.itemLayoutNumber);
		TextView id = (TextView) view.findViewById(R.id.itemLayoutViewId);
		id.setText(Integer.toString(nextId++));
	}
}


