package com.markzhengma.android.lifommunity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

/**
 * Created by Jieqiong on 15/04/2018.
 */


public class PostViewHolder extends RecyclerView.ViewHolder {
    private CardView cardView;
    private ImageView imageView;
    private TextView nameView;
    private TextView postTitleView;
    private TextView postContentView;
    private TextView postTimeView;
    private Button likeBtn;
    private Button commentBtn;

    private Context context;
    public PostData postObject;

    public PostViewHolder(View itemView, final Context context) {
        super(itemView);
        cardView = (CardView) itemView.findViewById(R.id.card_view);
        imageView = (ImageView) itemView.findViewById(R.id.profile_image);
        nameView = (TextView) itemView.findViewById(R.id.profile_name);
        postTitleView = (TextView) itemView.findViewById(R.id.post_title);
        postTimeView = itemView.findViewById(R.id.post_time);
        likeBtn = (Button) itemView.findViewById(R.id.like_btn);
        commentBtn = (Button) itemView.findViewById(R.id.comment_btn);

        this.context = context;

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//               Toast.makeText(context, postTitleView.getText(), Toast.LENGTH_SHORT).show();
               Intent intent = new Intent(v.getContext(), FullPostActivity.class);

                intent.putExtra(Keys.POST_KEY, postObject);
                context.startActivity(intent);
            }
        });
    }

//    public void bind(final PostData post) {
//        nameView.setText(post.userName);
//        postTitleView.setText(post.titleText);
//        postContentView.setText(post.contentText);
//
//        cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, post.getTitleText(), Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(v.getContext(), FullPostActivity.class);
//                context.startActivity(intent);
//            }
//        });
//
//    }

    public void createPostObject(String userId, String userName, String imageId, String time, String titleText, String contentText){
        postObject = new PostData(userId, userName, imageId, time, titleText, contentText);
    }

    public CardView getCardView() {
        return cardView;
    }

    public void setCardView(CardView cardView) {
        this.cardView = cardView;
    }

    //public ImageView getImageView() {
        //return userImageView;
    //}

    public void setImageView(String imageId) {
        Picasso.get().load(imageId).into(imageView);
    }

    public TextView getNameView() {
        return nameView;
    }

    public void setNameView(String name) {
        nameView.setText(name);
    }

    public TextView getPostTitleView() {
        return postTitleView;
    }

    public void setPostTitleView(String title) {
        postTitleView.setText(title);
    }

    public TextView getPostContentView() {
        return postContentView;
    }

    public void setPostContentView(String content) {
        postContentView.setText(content);
    }

    public TextView getPostTimeView(){
        return postTimeView;
    }

    public void setPostTimeView(String time){
        postTimeView.setText(time);
    }

    public Button getLikeBtn() {
        return likeBtn;
    }

    public void setLikeBtn(Button likeBtn) {
        this.likeBtn = likeBtn;
    }

    public Button getCommentBtn() {
        return commentBtn;
    }

    public void setCommentBtn(Button commentBtn) {
        this.commentBtn = commentBtn;
    }

}

