package com.example.chamka;

public class transaction {
    String userid;
    String amount;
    String type;
    String timestamp;
    String status;

    transaction(){}

    public transaction(String userid, String amount, String type, String timestamp, String status) {
        this.userid = userid;
        this.amount = amount;
        this.type = type;
        this.timestamp = timestamp;
        this.status = status;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
