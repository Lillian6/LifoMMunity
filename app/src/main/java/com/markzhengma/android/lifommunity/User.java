package com.markzhengma.android.lifommunity;

/**
 * Created by pengbuding on 20/03/2018.
 */

public class User {
    private String userName;
    private int userId;
    private int numOfFollow;
    private boolean gender;
    private String location;
    private String intro;



    public User(){}

    public User(String userName, int userId, int numOfFollow){
        this.userName=userName;
        this.userId=userId;
        this.numOfFollow=numOfFollow;
    }

}
