package com.markzhengma.android.lifommunity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class SettingsActivity extends AppCompatActivity {

    private EditText userNameEditText;
    private EditText ageEditText;
    private EditText introEditText;
    private Spinner locationSpinner;
    private RadioGroup genderRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        userNameEditText = (EditText) findViewById(R.id.username_edit);
        ageEditText = (EditText) findViewById(R.id.age_edit);
        introEditText = (EditText) findViewById(R.id.intro_edit);
        locationSpinner = (Spinner) findViewById(R.id.location_spinner);
        genderRadioGroup = (RadioGroup) findViewById(R.id.gender_radio_group);

        locationSpinner = (Spinner) findViewById(R.id.location_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.locations_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(adapter);


    }
}
