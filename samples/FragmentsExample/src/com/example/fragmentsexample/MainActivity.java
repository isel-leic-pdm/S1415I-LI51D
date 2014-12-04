package com.example.fragmentsexample;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;

import static com.example.fragmentsexample.MyLog.*;
public class MainActivity 
	extends FragmentActivity
	implements ItemListFragment.Callback
	{

	private ListModel _model;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_masterdetail);
		
		_model = new ListModel();
		for(int i = 0 ; i<100 ; ++i){
			_model.getItems().add(new ItemModel(i,"name "+i));
		}
		
		FragmentManager fm = getSupportFragmentManager();				
		if(fm.findFragmentById(R.id.mainFragmentPlaceholder) == null){
			ItemListFragment f = ItemListFragment.newInstance(_model);			
			fm.beginTransaction()
				.add(R.id.mainFragmentPlaceholder, f)
				.commit();
		}
	}

	@Override
	public void onListItemClick(int position) {
		if(findViewById(R.id.detailFragmentPlaceholder) != null){
			FragmentManager fm = getSupportFragmentManager();
			ItemFragment newFrag = ItemFragment
					.newInstance(_model.getItems().get(position));
			Fragment oldFrag = fm.findFragmentById(R.id.detailFragmentPlaceholder);
			FragmentTransaction ft = fm.beginTransaction();
			if(oldFrag != null){
				ft.remove(oldFrag);
			}
			ft.add(R.id.detailFragmentPlaceholder, newFrag);
			ft.commit();
		}else{
			Intent i = new Intent(this, ItemActivity.class);
			i.putExtra("list", _model);
			i.putExtra("ix", position);
			startActivity(i);
		}
	}
}
