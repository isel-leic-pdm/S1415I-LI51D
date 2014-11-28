package com.example.fragmentsexample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ItemFragment extends Fragment {
	
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstance){
		MyLog.d("ItemFragment.onCreateView");
		View v = inflater.inflate(R.layout.fragment_item, parent, false);
		TextView tv = (TextView) v.findViewById(R.id.textView1);
		Bundle b = getArguments();
		ItemModel model = (ItemModel) b.getSerializable("item");
		tv.setText(model.toString());
		return v;		
	}
	
	public static ItemFragment newInstance(ItemModel model){
		ItemFragment f = new ItemFragment();
		Bundle b = new Bundle();
		b.putSerializable("item", model);
		f.setArguments(b);
		MyLog.d("ItemFragment.newInstance "+model);
		return f;
	}

}
