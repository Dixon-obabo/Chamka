package com.example.chamka;

public class deposit {

    String userid;
    String amount;
    String timestamp;
    String phonenum;
    deposit(){}
    public deposit(String userid, String amount, String timestamp, String phonenum) {
        this.userid = userid;
        this.amount = amount;
        this.timestamp = timestamp;
        this.phonenum = phonenum;
    }

    public String getUserid() {
        return userid;
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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }
}
