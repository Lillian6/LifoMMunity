package com.markzhengma.android.lifommunity;

import java.io.Serializable;

/**
 * Created by markzhengma on 3/19/18.
 */

public class PostData implements Serializable {
    public String titleText;
    public String contentText;
    public String uid;
    public User user;

    public PostData(String titleText, String contentText){
        this.titleText = titleText;
        this.contentText = contentText;
    }

    public String getTitleText() {
        return titleText;
    }

    public void setTitleText(String titleText) {
        this.titleText = titleText;
    }

    public String getContentText() {
        return contentText;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }
}
