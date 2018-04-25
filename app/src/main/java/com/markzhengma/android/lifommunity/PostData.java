package com.markzhengma.android.lifommunity;

import java.io.Serializable;

/**
 * Created by markzhengma on 3/19/18.
 */

public class PostData implements Serializable {
    public String userId;
    public String userName;
    public int imageId;
    public String time;
    public String titleText;
    public String contentText;

    public PostData(String userId, String userName, int imageId, String time, String titleText, String contentText) {
        this.userId = userId;
        this.userName = userName;
        this.imageId = imageId;
        this.time = time;
        this.titleText = titleText;
        this.contentText = contentText;
    }

    public PostData() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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