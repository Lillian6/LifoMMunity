package com.markzhengma.android.lifommunity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class SettingsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText userNameEditText;
    EditText ageEditText;
    EditText introEditText;
    Spinner locationSpinner;
    RadioGroup genderRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        userNameEditText = findViewById(R.id.username_edit);
        ageEditText = findViewById(R.id.age_edit);
        introEditText = findViewById(R.id.intro_edit);
        locationSpinner = findViewById(R.id.location_spinner);
        genderRadioGroup = findViewById(R.id.gender_radio_group);

        locationSpinner = findViewById(R.id.location_spinner);
        locationSpinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.locations_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(adapter);
        
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
