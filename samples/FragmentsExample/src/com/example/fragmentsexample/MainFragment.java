package com.example.fragmentsexample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static com.example.fragmentsexample.MyLog.*;

public class MainFragment extends Fragment {

	public MainFragment(){
		d("MainFragment.ctor");
	}
	
	@Override
	public void onCreate(Bundle savedInstance){
		d("MainFragment.onCreate");
		super.onCreate(savedInstance);
	}
	
	@Override
	public View onCreateView(
			LayoutInflater inflater, 
			ViewGroup container, 
			Bundle savedInstanceState){
		d("MainFragment.onCreateView");
		View v = inflater.inflate(R.layout.fragment_main, container, false);
		TextView tv = (TextView) v.findViewById(R.id.textView1);
		tv.setText("Hello Fragments");
		return v;		
	}
	
}