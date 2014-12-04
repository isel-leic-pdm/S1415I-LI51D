package com.example.imageexample;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ImageView img = (ImageView)findViewById(R.id.imageView1);
		SetViewHandler svh = new SetViewHandler(Looper.getMainLooper());
		ImageHandlerThread th = new ImageHandlerThread();
		th.start();		
		ImageHandler ih = new ImageHandler(svh, th.getLooper());
		ih.fetchImage(img, "http://www.gravatar.com/avatar/e3a7f4454cf8bc6781c3bf7adcae366a?s=24&r=g&d=mm");		
	}
}

class ImageHandlerThread extends HandlerThread{

	public ImageHandlerThread() {
		super("ImageHandlerThread");		
	}	
}

class ImageHandler extends Handler{
	
	private SetViewHandler _h;

	public ImageHandler(SetViewHandler h, Looper l){
		super(l);
		_h = h;
	}
	
	public void handleMessage (Message msg){
		Data data = (Data)msg.obj; 
		URL url;
		try {
			url = new URL(data.uri);		
			HttpURLConnection c = (HttpURLConnection) url.openConnection();
			InputStream s = c.getInputStream();
			Bitmap bm = BitmapFactory.decodeStream(s);
			_h.publishImage(data.im, bm);
		} catch (MalformedURLException e) {
			
		} catch (IOException e) {
			
		}
	}
	
	public void fetchImage(ImageView im, String uri){
		Message m = obtainMessage();
		m.obj = new Data(uri,im);
		sendMessage(m);
	}
	
	static class Data{
		public final String uri;
		public final ImageView im;
		public Data(String uri, ImageView im){
			this.uri = uri;
			this.im = im;
		}
	}
}

class SetViewHandler extends Handler{
	
	public SetViewHandler(Looper l){
		super(l);
	}
	
	public void handleMessage (Message msg){
		Data data = (Data)msg.obj;
		data.im.setImageBitmap(data.bm);
	}
	
	public void publishImage(ImageView im, Bitmap bm){
		Message m = obtainMessage();
		m.obj = new Data(im,bm);
		sendMessage(m);
	}
	
	public final class Data {
		public final ImageView im;
		public final Bitmap bm;
		public Data(ImageView im, Bitmap bm) {
			this.im = im;
			this.bm = bm;
		}
	}
}
