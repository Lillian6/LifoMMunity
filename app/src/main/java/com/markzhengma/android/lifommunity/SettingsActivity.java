package com.markzhengma.android.lifommunity;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class SettingsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText userNameEditText;
    private EditText ageEditText;
    private EditText introEditText;
    private Spinner locationSpinner;
    private RadioGroup genderRadioGroup;
    private Button saveButton;
    private FirebaseDatabase database;
    private DatabaseReference userRef;
    private FirebaseUser user;
    private FirebaseAuth mAuth;
    private RadioButton radioButton;
    private String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        userNameEditText = findViewById(R.id.username_edit);
        ageEditText = findViewById(R.id.age_edit);
        introEditText = findViewById(R.id.intro_edit);
        locationSpinner = findViewById(R.id.location_spinner);
        genderRadioGroup = findViewById(R.id.gender_radio_group);
        saveButton = findViewById(R.id.save_button);
        database = FirebaseDatabase.getInstance();
        userRef = database.getReference();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        locationSpinner = findViewById(R.id.location_spinner);
        locationSpinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.locations_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(adapter);




        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioButton = findViewById(genderRadioGroup.getCheckedRadioButtonId());
                gender = radioButton.getText().toString();
                final Map<String, Object> map = new HashMap<>();
                map.put("username",userNameEditText.getText().toString());
                map.put("age", Integer.parseInt(ageEditText.getText().toString()));
                map.put("gender",gender);
                map.put("location", locationSpinner.getSelectedItem().toString());
                map.put("intro", introEditText.getText().toString());
                userRef.child("users").child(user.getUid().toString()).updateChildren(map);
                final Intent intent = new Intent(SettingsActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
        
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
