package com.example.fragmentsexample;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;

import static com.example.fragmentsexample.MyLog.*;
public class MainActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ListModel m = new ListModel();
		for(int i = 0 ; i<100 ; ++i){
			m.getItems().add(new ItemModel(i,"name "+i));
		}
		
		FragmentManager fm = getSupportFragmentManager();				
		if(fm.findFragmentById(R.id.mainFragmentPlaceholder) == null){
			ItemListFragment f = ItemListFragment.newInstance(m);			
			fm.beginTransaction()
				.add(R.id.mainFragmentPlaceholder, f)
				.commit();
		}
	}
}
