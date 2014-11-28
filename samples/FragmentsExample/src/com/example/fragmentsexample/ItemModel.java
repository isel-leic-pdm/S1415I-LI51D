package com.example.fragmentsexample;

import java.io.Serializable;

public class ItemModel implements Serializable {
	private int _id;
	private String _name;
	public ItemModel(int id, String name){
		_id = id;
		_name = name;
	}
	
	public int getId(){return _id;}
	public String getName(){return _name;}
	public void setName(String n){_name = n;}
	
	public String toString(){
		return String.format("%d: %s", _id, _name);
	}

}
