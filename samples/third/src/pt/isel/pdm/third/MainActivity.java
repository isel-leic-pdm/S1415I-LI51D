package pt.isel.pdm.third;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		TextView tv1 = (TextView)findViewById(R.id.text_view_1);
		tv1.setText(R.string.another_string);		
	}
}
