package com.markzhengma.android.lifommunity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class MainActivity extends Fragment {
    Button fullPostBtn;
//    LinearLayout postlistLayout;

//    int postIndex;
    ArrayList<PostData> posts;
    PostAdapter adapter;
    RecyclerView recyclerView;
    ImageView imageView;


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference postRef = database.getReference("post");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_main, container, false);

//        initialData();
//        postIndex = 0;

//        fullPostBtn = rootView.findViewById(R.id.full_post_btn);
//        postlistLayout = rootView.findViewById(R.id.postlist_layout);

//        setFullPostBtnListener();
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new PostAdapter(postRef, getContext());
        recyclerView.setAdapter(adapter);

        return rootView;
    }

//    public void setFullPostBtnListener(){
//        fullPostBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openFullPost(view);
//            }
//        });
//    }
    public void openFullPost(View view) {
        Intent intent = new Intent(getActivity(), FullPostActivity.class);//changed "this" to "getActivity()" in order to be compatible with Fragment
        startActivity(intent);
    }
}

//        postRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                String value = dataSnapshot.getValue(String.class);
//                posts = new ArrayList<PostData>();
//                posts.add(new PostData("123","myName",1,"time","Title", "Content"));
//                posts.add(new PostData("123","myName",1,"time","aaa", "bbb"));
//                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
//
//                   PostData value = dataSnapshot1.getValue(PostData.class);
//
//                    PostData post = new PostData();
//                    String name = post.getUserName();
//                    String userId= post.getUserId();
//                    int imageId = post.getImageId();
//                    String time = post.getTime();
//                    String title = post.getTitleText();
//                    String content = post.getContentText();
//                    post.setUserName(name);
//                    post.setUserId(userId);
//                    post.setImageId(imageId);
//                    post.setTime(time);
//                    post.setTitleText(title);
//                    post.setContentText(content);
//                    posts.add(post);
//
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w("Hello", "Failed to read value", error.toException());
//
//            }
//        });
//        return rootView;
//
//
//    }


//    private void initialData() {
//        posts = new ArrayList<>();
//        posts.add(new PostData("123","myName",1,"time","12", "23"));
//        posts.add(new PostData("123","myName",1,"time","aaa", "bbb"));
//        posts.add(new PostData("123","myName",1,"time","12", "23"));
//        posts.add(new PostData("123","myName",1,"time","aaa", "bbb"));
//        posts.add(new PostData("123","myName",1,"time","12", "23"));
//        posts.add(new PostData("123","myName",1,"time","aaa", "bbb"));
//    }

//    @Override
//    protected void onActivityResult(int req, int res, Intent intent){
//
//        if((req == 111 || req == 222 || req == 333) && res == RESULT_OK){
//            PostData newPost = (PostData)intent.getSerializableExtra("NEW_POST");
//
//            LinearLayout newPostLayout = new LinearLayout(this);
//            LinearLayout.LayoutParams postLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//            postLp.setMargins(0,16,0,16);
//            newPostLayout.setMinimumHeight(64);
//            if(postlistLayout != null){
//                postlistLayout.addView(newPostLayout, postLp);
//            }
//
//            LinearLayout newUserInfoLayout = new LinearLayout(this);
//            newUserInfoLayout.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1));
//            newUserInfoLayout.setOrientation(LinearLayout.VERTICAL);
//            newPostLayout.addView(newUserInfoLayout);
//
//            ImageView newUserImage = new ImageView(this);
//            LinearLayout.LayoutParams userImageLp = new LinearLayout.LayoutParams(80, 80);
//            userImageLp.setMargins(16,16,16,16);
//            newUserImage.setBackgroundColor(Color.BLUE);
//            newUserInfoLayout.addView(newUserImage, userImageLp);
//
//            TextView newUsernameTextView = new TextView(this);
//            newUsernameTextView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//            newUsernameTextView.setText("My Name");
//            newUsernameTextView.setTypeface(Typeface.DEFAULT_BOLD);
//            newUserInfoLayout.addView(newUsernameTextView);
//
//            LinearLayout newContentLayout = new LinearLayout(this);
//            newContentLayout.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 4));
//            newContentLayout.setBackgroundColor(getResources().getColor(R.color.colorGreen));
//            newContentLayout.setOrientation(LinearLayout.VERTICAL);
//            newPostLayout.addView(newContentLayout);
//
//            TextView newTitleTextView = new TextView(this);
//            newTitleTextView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//            newTitleTextView.setPadding(16, 16, 16, 16);
//            newTitleTextView.setText(newPost.titleText);
//            newTitleTextView.setTypeface(Typeface.DEFAULT_BOLD);
//            newTitleTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP,32);
//            newTitleTextView.setTextColor(getResources().getColor(R.color.postText));
//            newContentLayout.addView(newTitleTextView);
//
//            TextView newContentTextView = new TextView(this);
//            newContentTextView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//            newContentTextView.setPadding(16, 16, 16, 16);
//            newContentTextView.setText(newPost.contentText);
//            newContentTextView.setTextColor(getResources().getColor(R.color.postText));
////            newPostTextView.setId(postIndex);
////
////            postIndex ++;
//            newContentLayout.addView(newContentTextView);
//
//            LinearLayout buttonGroupLayout = new LinearLayout(this);
//            buttonGroupLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//            newContentLayout.addView(buttonGroupLayout);
//
//            Button likeButton = new Button(this);
//            likeButton.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//            likeButton.setText("like");
//            buttonGroupLayout.addView(likeButton);
//
//            Button commentButton = new Button(this);
//            commentButton.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//            commentButton.setText("comment");
//            buttonGroupLayout.addView(commentButton);
//
//        }
//    }


