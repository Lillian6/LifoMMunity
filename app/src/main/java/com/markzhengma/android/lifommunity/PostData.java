package com.markzhengma.android.lifommunity;

import java.io.Serializable;

/**
 * Created by markzhengma on 3/19/18.
 */

public class PostData implements Serializable {
    public String titleText;
    public String contentText;

    public PostData(String titleText, String contentText){
        this.titleText = titleText;
        this.contentText = contentText;
    }
}
