package com.markzhengma.android.lifommunity;

/**
 * Created by pengbuding on 20/03/2018.
 */

public class User {
    public String uid;
    public String username;
    public String email;
    public String gender;
    public int age;
    public String location;
    public String intro;
    public int numOfFollow;



    public User(){}

    public User(String uid, String username, String email, String gender, int age, String location, String intro, int numOfFollow){
        this.uid = uid;
        this.username = username;
        this.email = email;
        this.gender = gender;
        this.age = age;
        this.location = location;
        this.intro =intro;
        this.numOfFollow=numOfFollow;
    }

}
