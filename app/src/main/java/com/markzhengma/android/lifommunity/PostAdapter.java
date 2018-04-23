package com.markzhengma.android.lifommunity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Jieqiong on 15/04/2018.
 */

public class PostAdapter extends RecyclerView.Adapter<PostViewHolder> {
    private List<PostData> posts;
    private Context context;

    public PostAdapter(List<PostData> posts, Context context) {
        this.posts = posts;
        this.context = context;
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
        PostData post = posts.get(position);
        holder.bind(post);

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }


}
//
//    public PostAdapter(Query ref) {
//        super(PostData.class, R.layout.card_view_post, PostViewHolder.class, ref);
//    }
//
//    @Override
//    protected void populateViewHolder(PostViewHolder viewHolder, PostData post, int position) {
//        viewHolder.bind(post);
//    }
//
//    @Override
//    protected void onBindViewHolder(@NonNull PostViewHolder holder, int position, @NonNull PostData model) {
//
//    }
//
//    @NonNull
//    @Override
//    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return null;
//    }
//}



