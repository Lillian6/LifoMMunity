package com.markzhengma.android.lifommunity;

import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.text.Layout;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference userRef = database.getReference("users");

    private Button homeBtn;
    private Button postBtn;
    private EditText mEmailField;
    private EditText mPasswordField;
    private EditText mEmailSignUpField;
    private EditText mPasswordSignUpField;
    private EditText mUserNameSignUpField;
    private EditText mAgeSignUpField;
    private EditText mIntroSignUpField;
    private EditText mResetPasswordField;

    private Button signUpBtn;
    private Button loginBtn;
    private Button signUpChangeBtn;
    private Button loginChangeBtn;
    private Button forgetPasswordChangeBtn;
    private Button resetPasswordBtn;
    private Button backBtn;
    private Spinner locationSpinner;
    private Spinner genderSpinner;

    private LinearLayout loginLayout;
    private LinearLayout signUpLayout;
    private LinearLayout forgetPasswordLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        mAuth = FirebaseAuth.getInstance();

        homeBtn = findViewById(R.id.home_btn);
        postBtn = findViewById(R.id.post_btn);

        loginLayout = findViewById(R.id.login_layout);
        mEmailField = findViewById(R.id.email_edit);
        mPasswordField = findViewById(R.id.password_edit);
        loginBtn = findViewById(R.id.login_btn);
        signUpChangeBtn = findViewById(R.id.signup_change_btn);
        forgetPasswordChangeBtn = findViewById(R.id.forget_password_btn);
        resetPasswordBtn = findViewById(R.id.reset_password_button);
        backBtn = findViewById(R.id.back_button);

        signUpLayout = findViewById(R.id.signup_layout);
        mEmailSignUpField = findViewById(R.id.email_signup_edit);
        mPasswordSignUpField = findViewById(R.id.password_signup_edit);
        mUserNameSignUpField = findViewById(R.id.username_signup_edit);
        mResetPasswordField = findViewById(R.id.forget_password_edit);
        genderSpinner = findViewById(R.id.gender_signup_spinner);
        mAgeSignUpField = findViewById(R.id.age_signup_edit);
        locationSpinner = findViewById(R.id.location_signup_spinner);
        mIntroSignUpField = findViewById(R.id.intro_signup_edit);
        signUpBtn = findViewById(R.id.signup_btn);
        loginChangeBtn = findViewById(R.id.login_change_btn);

        forgetPasswordLayout = findViewById(R.id.forget_password_layout);

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
        resetPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResetPasswordBtn();
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLogin();
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
        final Intent intent = new Intent(this, TabActivity.class);

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
        final Intent intent = new Intent(this, TabActivity.class);

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();

                            userRef.child(user.getUid().toString()).setValue(new User(user.getUid().toString(),
                                    mUserNameSignUpField.getText().toString(),
                                    mEmailSignUpField.getText().toString(),
                                    genderSpinner.getSelectedItem().toString(),
                                    Integer.valueOf(mAgeSignUpField.getText().toString()),
                                    locationSpinner.getSelectedItem().toString(),
                                    mIntroSignUpField.getText().toString(),
                                    0));

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

    private void setResetPasswordBtn(){
        String email = mResetPasswordField.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Enter your email!", Toast.LENGTH_SHORT).show();
            return; }

        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Check email to reset your password!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this, "Fail to send reset password email!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
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

