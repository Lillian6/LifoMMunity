package com.markzhengma.android.lifommunity;

import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    private Button homeBtn;
    private Button postBtn;
    private EditText mEmailField;
    private EditText mPasswordField;
    private EditText mEmailSignUpField;
    private EditText mPasswordSignUpField;
    private Button signUpBtn;
    private Button loginBtn;
    private Button signUpChangeBtn;
    private Button loginChangeBtn;
    private Button forgetPasswordChangeBtn;
    private Spinner locationSpinner;
    private Spinner genderSpinner;

    private LinearLayout loginLayout;
    private LinearLayout signUpLayout;
    private LinearLayout forgetPasswordLayout;

    private EditText userName;
    private EditText password;
    private Button login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        mEmailField = findViewById(R.id.username_edit);
        mPasswordField = findViewById(R.id.password_edit);
        signUpBtn = findViewById(R.id.signup_btn);
        loginBtn = findViewById(R.id.login_btn);
        homeBtn = findViewById(R.id.home_btn);
        postBtn = findViewById(R.id.post_btn);
        loginLayout = findViewById(R.id.login_layout);
        signUpLayout = findViewById(R.id.signup_layout);
        forgetPasswordLayout = findViewById(R.id.forget_password_layout);
        signUpChangeBtn = findViewById(R.id.signup_change_btn);
        loginChangeBtn = findViewById(R.id.login_change_btn);
        mEmailSignUpField = findViewById(R.id.username_signup_edit);
        mPasswordSignUpField = findViewById(R.id.password_signup_edit);
        forgetPasswordChangeBtn = findViewById(R.id.forget_password_btn);
        genderSpinner = findViewById(R.id.gender_signup_spinner);
        locationSpinner = findViewById(R.id.location_signup_spinner);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInUser();
            }
        });
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();
            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                loadMainActivity();
            }
        });
        postBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                loadPostActivity();
            }
        });
        signUpChangeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSignUp();
            }
        });
        loginChangeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLogin();
            }
        });
        forgetPasswordChangeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showForgetPassword();
            }
        });

        createAdapter();
    }

    private void createAdapter(){
        ArrayAdapter<CharSequence> locationAdapter = ArrayAdapter.createFromResource(this,
                R.array.locations_array, android.R.layout.simple_spinner_item);
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(locationAdapter);

        ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(this,
                R.array.genders_array, android.R.layout.simple_spinner_item);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(genderAdapter);
    }

    private void signInUser(){
        String email = mEmailField.getText().toString();
        String password = mPasswordField.getText().toString();
        final Intent intent = new Intent(this, ProfileActivity.class);

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(LoginActivity.this, "Welcome, " + user.getEmail(), Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                        } else {
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
        });

    }

    private void createUser() {
        String email = mEmailSignUpField.getText().toString();
        String password = mPasswordSignUpField.getText().toString();
        final Intent intent = new Intent(this, ProfileActivity.class);

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(LoginActivity.this, "New user: " + user.getEmail() + ", welcome!", Toast.LENGTH_SHORT).show();

                            startActivity(intent);
                        } else {
                            Toast.makeText(LoginActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    private void showForgetPassword(){
        LinearLayout.LayoutParams forgetPasswordParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 7.0f);
        LinearLayout.LayoutParams signUpParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 0.0f);
        LinearLayout.LayoutParams loginParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 0f);
        signUpLayout.setLayoutParams(signUpParam);
        loginLayout.setLayoutParams(loginParam);
        forgetPasswordLayout.setLayoutParams(forgetPasswordParam);

    }

    private void setForgetPasswordBtn(){


    }

    private void loadMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void loadPostActivity(){
        Intent intent = new Intent(this, PostActivity.class);
        startActivity(intent);
    }

    private void showSignUp(){
        LinearLayout.LayoutParams signUpParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 7.0f);
        LinearLayout.LayoutParams loginParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 0f);
        LinearLayout.LayoutParams forgetPasswordParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 0f);
        signUpLayout.setLayoutParams(signUpParam);
        loginLayout.setLayoutParams(loginParam);
        forgetPasswordLayout.setLayoutParams(forgetPasswordParam);


    }
    private void showLogin(){
        LinearLayout.LayoutParams signUpParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 0f);
        LinearLayout.LayoutParams loginParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 7.0f);
        LinearLayout.LayoutParams forgetPasswordParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 0f);
        signUpLayout.setLayoutParams(signUpParam);
        loginLayout.setLayoutParams(loginParam);
        forgetPasswordLayout.setLayoutParams(forgetPasswordParam);

    }

}

