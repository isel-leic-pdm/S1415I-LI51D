package pt.isel.pdm.storageexample;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	private static final String TAG = "StorageExample";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		SharedPreferences pref = getSharedPreferences("my-prefs",0);		
		pref.edit()
			.putBoolean("a-boolean", true)
			.putString("name", "PDM")
			.commit();
		try {
			FileOutputStream fos = this.openFileOutput("file-name", MODE_APPEND);
			fos.write(1);
			fos.close();
		} catch (FileNotFoundException e) {
			Log.e(TAG, "file not found");
		} catch (IOException e) {
			Log.e(TAG, "IOException error"); 
		}
	}
}
