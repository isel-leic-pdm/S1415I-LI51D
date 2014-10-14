package com.example.actionbarexample;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceFragment;

public class SettingsFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.settings);
        ListPreference list = (ListPreference)findPreference("list_key");
        list.setEntries(new String[]{"item 1", "item 2", "item 3", "item 4"});
        list.setEntryValues(new String[]{"key1", "key2", "key3", "key4"});
        
    }    
}
