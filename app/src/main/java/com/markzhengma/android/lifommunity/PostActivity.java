package com.markzhengma.android.lifommunity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PostActivity extends AppCompatActivity {
    Button homeBtn;
    Button postBtn;
    Button profileBtn;
    EditText titleTextView;
    EditText contentTextView;
    Button submitPostBtn;

    String titleText;
    String contentText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        homeBtn = findViewById(R.id.home_btn);
        postBtn = findViewById(R.id.post_btn);
        profileBtn = findViewById(R.id.profile_btn);

        titleTextView = findViewById(R.id.post_title_edit_text);
        contentTextView = findViewById(R.id.post_content_edit_text);
        submitPostBtn = findViewById(R.id.submit_post_btn);

        setHomeBtnListener();
        setProfileBtnListener();

        setSubmitPostListener();
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


    public void setProfileBtnListener(){
        profileBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                loadProfileActivity();
            }
        });
    }

    private void loadProfileActivity(){
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    private void getPostData(){
        titleText = titleTextView.getText().toString();
        contentText = contentTextView.getText().toString();
    }

    private void setPostData(){
        PostData newPost = new PostData(titleText, contentText);
        Intent intent = new Intent();
        intent.putExtra("NEW_POST", newPost);
        setResult(RESULT_OK, intent);

        finish();
    }

    private void setSubmitPostListener(){
        submitPostBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                getPostData();
                setPostData();
            }
        });
    }
}
