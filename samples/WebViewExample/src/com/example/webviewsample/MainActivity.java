package com.example.webviewsample;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		WebView _wv = (WebView) findViewById(R.id.webView1);
		
		String html = "<p>Bom dia,</p><p><span style=\"line-height: 1.5em; background-color: initial;\">A aula de PDM de hoje (6 de Janeiro) realiza-se no laboratório LS1.</span><br></p><p><span style=\"line-height: 1.5em; background-color: initial;\">Cumprimentos</span></p><p><span style=\"line-height: 1.5em; background-color: initial;\">Pedro Félix</span></p><p><span style=\"line-height: 1.5em; background-color: initial;\"><br></span></p>";
		
		_wv.loadDataWithBaseURL("http://thoth.cc.e.ipl.pt", 
				html, 
				"text/html",
				"utf-8",
				null);
	}
}
