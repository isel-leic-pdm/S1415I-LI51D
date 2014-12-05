package com.example.locationstudy;

import java.io.IOException;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView _tv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		_tv = (TextView) findViewById(R.id.textView);
		
		LocationManager lm = 
				(LocationManager) 
				this.getSystemService(Context.LOCATION_SERVICE);
		
		L.d("Geocoder present: %b", Geocoder.isPresent());
		
		for(String pn : lm.getAllProviders()){
			L.d("# %s #", pn);
			LocationProvider p = lm.getProvider(pn);
			L.d("acc = %d, pwr = %d, sat = %b, cell = %b, net = %b, alt = %b, bea = %b, speed = %b",
					p.getAccuracy(),
					p.getPowerRequirement(),
					p.requiresSatellite(),
					p.requiresCell(),
					p.requiresNetwork(),
					p.supportsAltitude(),
					p.supportsBearing(),
					p.supportsSpeed());					

		}
		
		Geocoder gc = new Geocoder(this);
		
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, new MyListener("gps", _tv, gc));
		lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, new MyListener("network", _tv, gc));
		
		
	}
}

class MyListener implements LocationListener {
	
	private final String _name;
	private final TextView _tv;
	private final Geocoder _gc;

	MyListener(String name, TextView tv, Geocoder gc){
		_name = name;
		_tv = tv;
		_gc = gc;
	}
	
	@Override
	public void onLocationChanged(Location location) {
		String s = String.format("[%s]onLocationChanged: lat=%f, long=%f, acc=%f, alt=%f, speed=%f, bea=%f",
				 _name,
				 location.getLatitude(),
				 location.getLongitude(),
				 location.getAccuracy(),
				 location.getAltitude(),
				 location.getSpeed(),
				 location.getBearing()				 
				 );
		_tv.setText(s);
				
		try {
			List<Address> addresses = _gc.getFromLocation(location.getLatitude(), location.getLongitude(), 10);
			for(Address addr : addresses){
				L.d("%s", addr.getAddressLine(0));
			}
		} catch (IOException e) {
			L.d("Exception %s",e.getMessage());
		}
		 
	}

	@Override
	public void onStatusChanged(String provider, int status,
			Bundle extras) {
		L.d("[%s]onStatusChanger: status=%d",_name, status);				
	}

	@Override
	public void onProviderEnabled(String provider) {
		L.d("[%s]onProviderEnabled: %s", _name, provider);
	}

	@Override
	public void onProviderDisabled(String provider) {
		L.d("[%s]onProviderDisabled: %s", _name, provider);
	}			
}
