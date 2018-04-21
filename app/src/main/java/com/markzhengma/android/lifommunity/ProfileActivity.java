package com.markzhengma.android.lifommunity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.sql.Ref;

public class ProfileActivity extends Fragment {
    private Button homeBtn;
    private Button postBtn;
    private Button profileBtn;
    private Button settingBtn;
    private Button signOutBtn;
    private TextView usernameTextView;
    private TextView emailTextView;
    private TextView genderTextView;
    private TextView locationTextView;
    private TextView introTextView;

    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference userRef;
    private FirebaseUser user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.activity_profile, container, false);

        homeBtn = rootView.findViewById(R.id.home_btn);
        postBtn = rootView.findViewById(R.id.post_btn);
        profileBtn = rootView.findViewById(R.id.profile_btn);

        settingBtn = rootView.findViewById(R.id.settings_button);

        signOutBtn = rootView.findViewById(R.id.signout_act_btn);
        usernameTextView = rootView.findViewById(R.id.username_profile);
        emailTextView = rootView.findViewById(R.id.email_profile);
        genderTextView = rootView.findViewById(R.id.gender_profile);
        locationTextView = rootView.findViewById(R.id.location_profile);
        introTextView = rootView.findViewById(R.id.intro_profile);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        userRef  = database.getReference("users");
        user = mAuth.getCurrentUser();

//        setHomeBtnListener();
//        setPostBtnListener();
        setSettingBtnListener();
        setSignOutBtnListener();

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        if(user == null){
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        }else{
            DatabaseReference childRef = userRef.child(user.getUid().toString());
            childRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                        usernameTextView.setText(dataSnapshot.child("username").getValue().toString());
                        emailTextView.setText(dataSnapshot.child("email").getValue().toString());
                        genderTextView.setText(dataSnapshot.child("gender").getValue().toString());
                        locationTextView.setText(dataSnapshot.child("location").getValue().toString());
                        introTextView.setText(dataSnapshot.child("intro").getValue().toString());
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(getActivity(), "Error loading Firebase", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }


//    public void setHomeBtnListener() {
//        homeBtn.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//                loadMainActivity();
//            }
//        });
//    }
//
//    private void loadMainActivity(){
//        Intent intent = new Intent(getActivity(), MainActivity.class);
//        startActivity(intent);
//    }
//
//    public void setPostBtnListener() {
//        postBtn.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//                loadPostActivity();
//            }
//        });
//    }
//
//    private void loadPostActivity(){
//        Intent intent = new Intent(getActivity(), PostActivity.class);
//        startActivity(intent);
//    }

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
        Intent intent = new Intent(getActivity(), SettingsActivity.class);
        startActivity(intent);
    }

    private void signOutUser(){
        mAuth.signOut();
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }

}
