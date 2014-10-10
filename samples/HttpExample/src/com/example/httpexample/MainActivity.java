package com.example.httpexample;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView _tv1;
	private ListView _listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		_tv1 = (TextView) findViewById(R.id.tv1);
		_listView = (ListView) findViewById(R.id.listView1);
		ThothClasses c = new ThothClasses(){
			@Override
			protected void onPostExecute(ThothClass[] result){
				if(result == null){
					_tv1.setText("error");
				}else{
					List<Map<String,String>> list = 
							new LinkedList<Map<String,String>>();
					for(int i = 0 ; i<result.length ; ++i){
						Map<String,String> map = new HashMap<String,String>();
						map.put("name", result[i].name);
						map.put("semester", result[i].semester);
						map.put("teacher", result[i].teacher);
						list.add(map);						
					}
					SimpleAdapter ad = new SimpleAdapter(
							MainActivity.this,
							list,
							R.layout.item_layout,
							new String[]{"name","teacher","semester"},
							new int[]{R.id.item_name, R.id.item_teacher, R.id.item_semester}
							);
					_listView.setAdapter(ad);
				}
			}
		};
		c.execute();
	}
}

class ThothClass {
	public String name;
	public String semester;
	public String teacher;
}

class ThothClasses extends AsyncTask<Void,Void,ThothClass[]>{
	@Override
	protected ThothClass[] doInBackground(Void... arg0) {
		try{
			URL url = new URL("http://thoth.cc.e.ipl.pt/api/v1/classes");
			HttpURLConnection c = (HttpURLConnection)url.openConnection();				
			try{
				InputStream is = c.getInputStream();				
				String data = readAllFrom(is);
				return parseFrom(data);
			} catch (JSONException e) {
				return null;
			}finally{
				c.disconnect();
			}
		}catch(IOException e){
			return null;
		}		
	}
	
	private ThothClass[] parseFrom(String s) throws JSONException{
		JSONObject root = new JSONObject(s);
		JSONArray jclasses = root.getJSONArray("classes");
		ThothClass[] classes = new ThothClass[jclasses.length()];
		for(int i = 0 ; i<jclasses.length() ; ++i){
			JSONObject jclass = jclasses.getJSONObject(i);
			ThothClass clazz = new ThothClass();
			clazz.name = jclass.getString("fullName");
			clazz.semester = jclass.getString("lectiveSemesterShortName");
			clazz.teacher = jclass.getString("mainTeacherShortName");
			classes[i] = clazz;
		}
		return classes;
	}
	
	private String readAllFrom(InputStream is){
		Scanner s = new Scanner(is);
		try{
			s.useDelimiter("\\A");
			return s.hasNext() ? s.next() : null;
		}finally{
			s.close();
		}
	}
}
