package com.markzhengma.android.lifommunity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.sql.Ref;

public class ProfileActivity extends AppCompatActivity {
    Button homeBtn;
    Button postBtn;
    Button profileBtn;
    Button settingBtn;
    Button signOutBtn;
    TextView usernameTextView;
    TextView emailTextView;
    TextView genderTextView;
    TextView locationTextView;
    TextView introTextView;

    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference userRef;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        homeBtn = findViewById(R.id.home_btn);
        postBtn = findViewById(R.id.post_btn);
        profileBtn = findViewById(R.id.profile_btn);

        settingBtn = findViewById(R.id.settings_button);

        signOutBtn = findViewById(R.id.signout_act_btn);
        usernameTextView = findViewById(R.id.username_profile);
        emailTextView = findViewById(R.id.email_profile);
        genderTextView = findViewById(R.id.gender_profile);
        locationTextView = findViewById(R.id.location_profile);
        introTextView = findViewById(R.id.intro_profile);

        setHomeBtnListener();
        setPostBtnListener();
        setSettingBtnListener();
        setSignOutBtnListener();
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        userRef  = database.getReference("users");
        user = mAuth.getCurrentUser();

        if(user == null){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }else{
            DatabaseReference childRef = userRef.child(user.getUid().toString());
            childRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    //usernameTextView.setText(dataSnapshot.child("username").getValue().toString());
                    //emailTextView.setText(dataSnapshot.child("email").getValue().toString());
                    //genderTextView.setText(dataSnapshot.child("gender").getValue().toString());
                    //locationTextView.setText(dataSnapshot.child("location").getValue().toString());
                    //introTextView.setText(dataSnapshot.child("intro").getValue().toString());
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(ProfileActivity.this, "Error loading Firebase", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }


    public void setHomeBtnListener() {
        homeBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                loadMainActivity();
            }
        });
    }

    private void loadMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void setPostBtnListener() {
        postBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                loadPostActivity();
            }
        });
    }

    private void loadPostActivity(){
        Intent intent = new Intent(this, PostActivity.class);
        startActivity(intent);
    }

    public void setSettingBtnListener() {
        settingBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                loadSettingsActivity();
            }
            });
    }


    public void setSignOutBtnListener(){
        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOutUser();

            }
        });
    }


    public void loadSettingsActivity(){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    private void signOutUser(){
        mAuth.signOut();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

}
