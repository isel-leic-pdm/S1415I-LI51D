package com.example.fragmentsexample;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListModel implements Serializable {
	List<ItemModel> _list = new ArrayList<ItemModel>();
	
	public ListModel(){
		
	}
	
	public List<ItemModel> getItems(){
		return _list;
	}
}
