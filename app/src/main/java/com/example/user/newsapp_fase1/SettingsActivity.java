package com.example.user.newsapp_fase1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

    }

    public static class NewsPreferenceFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener {
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.settings_main);
            Preference oldest = findPreference(getString(R.string.settings_order_by_oldest_value));
            bindPreferenceSummaryToValue(oldest);
        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            String stringValue = newValue.toString();
            preference.setSummary(getLabelGivenValue(stringValue));
            return true;
        }

        private void bindPreferenceSummaryToValue(Preference preference) {
            preference.setOnPreferenceChangeListener(this);
            SharedPreferences preferences =
                    PreferenceManager.getDefaultSharedPreferences(preference.getContext());
            String preferenceString = preferences.getString(preference.getKey(), "");
            onPreferenceChange(preference, preferenceString);
        }
        //Helper method to retireve the label from the selected value
        private String getLabelGivenValue(String value) {
            String result = "All Sections";
            String values[] = getResources().getStringArray(R.array.settings_order_by_values);
            for (int i = 0; i < values.length; i++) {
                if (value.equals(values[i])) {
                    result = getResources().getStringArray(R.array.settings_order_by_labels)[i];
                }
            }
            return result;
        }
    }
}