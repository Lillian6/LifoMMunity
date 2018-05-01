package com.markzhengma.android.lifommunity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jieqiong on 15/04/2018.
 */

public class PostAdapter extends RecyclerView.Adapter<PostViewHolder> {
    private List<PostData> posts;
    private Context context;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference postRef = database.getReference("post");


    public PostAdapter(List<PostData> posts, Context context) {
        this.posts = posts;
        this.context = context;
    }

    public PostAdapter(DatabaseReference thePosts, final Context context) {
        this.context = context;
        posts = new ArrayList<>();
        thePosts.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                PostData theData = dataSnapshot.getValue(PostData.class);
                posts.add(0, theData);
                notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                notifyDataSetChanged();
            }
        });
    }

    public PostAdapter() {
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_post, parent, false);
        return new PostViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
//        holder.bind(post);

//        posts = new ArrayList<>();
//        postRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for(DataSnapshot child : dataSnapshot.getChildren()){
//                    posts.add(new PostData(
//                            child.child("userId").getValue().toString(),
//                            child.child("userName").getValue().toString(),
//                            child.child("imageId").getValue().toString(),
//                            child.child("time").getValue().toString(),
//                            child.child("titleText").getValue().toString(),
//                            child.child("contentText").getValue().toString()
//                    ));
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//

//            }
        PostData post = posts.get(position);
        holder.setPostTitleView(post.titleText);
        holder.setPostTimeView(post.time );
        holder.setNameView(post.userName);
        holder.createPostObject(post.userId, post.userName, post.imageId, post.time, post.titleText, post.contentText);
    }

    @Override
    public int getItemCount() {
        int arr = 0;
        try {
            if (posts.size() == 0) {
                arr = 0;
            } else {
                arr = posts.size();
            }
        } catch (Exception e) {

        }
            return arr;
        }
}
