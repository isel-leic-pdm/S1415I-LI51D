package com.example.fragmentsexample;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class DatePickerDialogFragment extends DialogFragment {

	@Override
	public Dialog onCreateDialog (Bundle savedInstanceState){
		DatePickerDialog d = new DatePickerDialog(getActivity(),
				null,
				2014,
				12,
				2);
		return d;
	}	
}
