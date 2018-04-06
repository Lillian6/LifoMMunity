package com.markzhengma.android.lifommunity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    Button homeBtn;
    Button postBtn;
    Button profileBtn;
    LinearLayout postlistLayout;


    int postIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeBtn = findViewById(R.id.home_btn);
        postBtn = findViewById(R.id.post_btn);
        profileBtn = findViewById(R.id.profile_btn);
        postlistLayout = findViewById(R.id.postlist_layout);

        setPostBtnListener();
        setProfileBtnListener();

        postIndex = 0;

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("refer");

        myRef.setValue("Hello, World!");

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
        startActivityForResult(intent, 111);
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

    @Override
    protected void onActivityResult(int req, int res, Intent intent){

        if((req == 111 || req == 222 || req == 333) && res == RESULT_OK){
            PostData newPost = (PostData)intent.getSerializableExtra("NEW_POST");

            LinearLayout newPostLayout = new LinearLayout(this);
            LinearLayout.LayoutParams postLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            postLp.setMargins(0,16,0,16);
            newPostLayout.setMinimumHeight(64);
            if(postlistLayout != null){
                postlistLayout.addView(newPostLayout, postLp);
            }

            LinearLayout newUserInfoLayout = new LinearLayout(this);
            newUserInfoLayout.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1));
            newUserInfoLayout.setOrientation(LinearLayout.VERTICAL);
            newPostLayout.addView(newUserInfoLayout);

            ImageView newUserImage = new ImageView(this);
            LinearLayout.LayoutParams userImageLp = new LinearLayout.LayoutParams(80, 80);
            userImageLp.setMargins(16,16,16,16);
            newUserImage.setBackgroundColor(Color.BLUE);
            newUserInfoLayout.addView(newUserImage, userImageLp);

            TextView newUsernameTextView = new TextView(this);
            newUsernameTextView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            newUsernameTextView.setText("My Name");
            newUsernameTextView.setTypeface(Typeface.DEFAULT_BOLD);
            newUserInfoLayout.addView(newUsernameTextView);

            LinearLayout newContentLayout = new LinearLayout(this);
            newContentLayout.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 4));
            newContentLayout.setBackgroundColor(getResources().getColor(R.color.colorGreen));
            newContentLayout.setOrientation(LinearLayout.VERTICAL);
            newPostLayout.addView(newContentLayout);

            TextView newTitleTextView = new TextView(this);
            newTitleTextView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            newTitleTextView.setPadding(16, 16, 16, 16);
            newTitleTextView.setText(newPost.titleText);
            newTitleTextView.setTypeface(Typeface.DEFAULT_BOLD);
            newTitleTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP,32);
            newTitleTextView.setTextColor(getResources().getColor(R.color.postText));
            newContentLayout.addView(newTitleTextView);

            TextView newContentTextView = new TextView(this);
            newContentTextView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            newContentTextView.setPadding(16, 16, 16, 16);
            newContentTextView.setText(newPost.contentText);
            newContentTextView.setTextColor(getResources().getColor(R.color.postText));
//            newPostTextView.setId(postIndex);
//
//            postIndex ++;
            newContentLayout.addView(newContentTextView);

            LinearLayout buttonGroupLayout = new LinearLayout(this);
            buttonGroupLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            newContentLayout.addView(buttonGroupLayout);

            Button likeButton = new Button(this);
            likeButton.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            likeButton.setText("like");
            buttonGroupLayout.addView(likeButton);

            Button commentButton = new Button(this);
            commentButton.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            commentButton.setText("comment");
            buttonGroupLayout.addView(commentButton);

        }
    }
}
