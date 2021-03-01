package com.example.chamka;

public class loan {


    String userid;
    String amount;
    String email;
    String description;
    String timestamp;
    String phonenum;

    loan(){}
    public loan(String userid, String amount, String email, String description, String timestamp,String phonenum) {
        this.userid = userid;
        this.amount = amount;
        this.email = email;
        this.description = description;
        this.timestamp = timestamp;
        this.phonenum=phonenum;
    }

    public String getUserid() {
        return userid;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }


}
