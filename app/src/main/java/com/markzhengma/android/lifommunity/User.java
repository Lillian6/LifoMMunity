package com.markzhengma.android.lifommunity;

import java.util.HashMap;
import java.util.Map;

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

    public User(String uid, String username, String gender, int age, String location, String intro){
        this.uid = uid;
        this.username = username;
        this.gender = gender;
        this.age = age;
        this.location = location;
        this.intro = intro;
    }

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



    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public int getNumOfFollow() {
        return numOfFollow;
    }

    public void setNumOfFollow(int numOfFollow) {
        this.numOfFollow = numOfFollow;
    }
}
