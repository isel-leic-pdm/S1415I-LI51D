package pt.isel.pdm.contactsexample;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView _tv1;
	private Button _button;
	private Button _button2;
	private TextView _tv2;
	private Uri _contactUri;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		_tv1 = (TextView) findViewById(R.id.tv1);
		_button = (Button) findViewById(R.id.button1);
		_button2 = (Button) findViewById(R.id.button2);
		_button2.setEnabled(false);
		_tv2 = (TextView) findViewById(R.id.tv2);
		_button.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(
						Intent.ACTION_PICK, 
						Uri.parse("content://contacts"));
				i.setType(Phone.CONTENT_TYPE);
				startActivityForResult(i, 0);				
			}			
		});
		
		_button2.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				String[] projection = {Phone.DISPLAY_NAME, Phone.NUMBER};

	            Cursor cursor = getContentResolver()
	                    .query(_contactUri, projection, null, null, null);
	            cursor.moveToFirst();

	            // Retrieve the phone number from the NUMBER column
	            int nameCol = cursor.getColumnIndex(Phone.DISPLAY_NAME);
	            int numberCol = cursor.getColumnIndex(Phone.NUMBER);
	            String msg = cursor.getString(nameCol) + ":" + cursor.getString(numberCol);
	            _tv2.setText(msg);      
			}			
		});
	}
	
	@Override
	protected void onActivityResult(int reqCode, int resCode, Intent data){
		if(reqCode == 0 && resCode == RESULT_OK){
			_contactUri = data.getData();
			_tv1.setText(_contactUri.toString());
			//Intent i = new Intent(Intent.ACTION_EDIT, data.getData());
			//startActivity(i);
			_button2.setEnabled(true);
		}
	}
}
