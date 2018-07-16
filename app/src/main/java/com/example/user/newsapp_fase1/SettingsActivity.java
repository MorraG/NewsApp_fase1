package com.example.user.newsapp_fase1;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;


public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings_main);;
    }

    private void addPreferencesFromResource(int settings_main) {
    }

    public static class NewsPreferenceFragment extends PreferenceFragment {
    }
}