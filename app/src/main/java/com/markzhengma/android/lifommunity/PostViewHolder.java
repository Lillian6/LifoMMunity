package com.markzhengma.android.lifommunity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Jieqiong on 15/04/2018.
 */


public class PostViewHolder extends RecyclerView.ViewHolder {
    private CardView cardView;
    private ImageView imageView;
    private TextView nameView;
    private TextView postTitleView;
    private TextView postContentView;
    private Button likeBtn;
    private Button commentBtn;

    private Context context;

    public PostViewHolder(View itemView, final Context context) {
        super(itemView);
        cardView = (CardView) itemView.findViewById(R.id.card_view);
        imageView = (ImageView) itemView.findViewById(R.id.profile_image);
        nameView = (TextView) itemView.findViewById(R.id.profile_name);
        postTitleView = (TextView) itemView.findViewById(R.id.post_title);
        postContentView = (TextView) itemView.findViewById(R.id.post_content);
        likeBtn = (Button) itemView.findViewById(R.id.like_btn);
        commentBtn = (Button) itemView.findViewById(R.id.comment_btn);

        this.context = context;

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Toast.makeText(context, postTitleView.getText(), Toast.LENGTH_SHORT).show();
               Intent intent = new Intent(v.getContext(), FullPostActivity.class);

                String title = postTitleView.getText().toString();
                String content = postContentView.getText().toString();

                PostData currentPost = new PostData(title,content);
                intent.putExtra(Keys.POST_KEY,currentPost);
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

    public CardView getCardView() {
        return cardView;
    }

    public void setCardView(CardView cardView) {
        this.cardView = cardView;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public TextView getNameView() {
        return nameView;
    }

    public void setNameView(TextView nameView) {
        this.nameView = nameView;
    }

    public TextView getPostTitleView() {
        return postTitleView;
    }

    public void setPostTitleView(TextView postTitleView) {
        this.postTitleView = postTitleView;
    }

    public TextView getPostContentView() {
        return postContentView;
    }

    public void setPostContentView(TextView postContentView) {
        this.postContentView = postContentView;
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

