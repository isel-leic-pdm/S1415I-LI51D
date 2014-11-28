package com.example.fragmentsexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ItemListFragment extends ListFragment {
	
	private ListModel _list;

	@Override
	public void onCreate(Bundle state){
		super.onCreate(state);
		Bundle args = getArguments();
		_list = (ListModel) args.getSerializable("key");
		
		this.setListAdapter(
				new ArrayAdapter<ItemModel>(
						getActivity(),
						android.R.layout.simple_list_item_1,
						_list.getItems()));				
		
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id){
		Intent i = new Intent(getActivity(), ItemActivity.class);
		i.putExtra("list", _list);
		i.putExtra("ix", position);
		startActivity(i);
	}
	
	public static ItemListFragment newInstance(ListModel model){
		ItemListFragment f = new ItemListFragment();
		Bundle args = new Bundle();
		args.putSerializable("key", model);
		f.setArguments(args);
		return f;
	}
}
