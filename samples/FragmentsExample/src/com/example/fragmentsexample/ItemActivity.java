package com.example.fragmentsexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

public class ItemActivity extends FragmentActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ViewPager pager = new ViewPager(this);	
		pager.setId(R.id.viewPager);
		setContentView(pager);
		
		Intent i = getIntent();
		final ListModel list = (ListModel)i.getExtras().getSerializable("list");
		int ix = i.getExtras().getInt("ix",0);
		MyLog.d("ItemActivity.oncreate "+ix);
		
		pager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()){
			@Override
			public Fragment getItem(int pos) {
				Fragment f = ItemFragment.newInstance(list.getItems().get(pos));
				return f;
			}

			@Override
			public int getCount() {
				return list.getItems().size();
			}			
		});
		pager.setCurrentItem(ix);	
		
	}
}
