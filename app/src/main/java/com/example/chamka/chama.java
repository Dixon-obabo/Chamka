package com.example.chamka;

public class chama {
    String chamaname;
    String timestamp;
    String id;
    String balance;
    String mcount;
    String admin;

    chama(){};

    public chama(String chamaname, String timestamp, String id, String balance, String mcount,String admin) {
        this.chamaname = chamaname;
        this.timestamp = timestamp;
        this.id = id;
        this.balance = balance;
        this.mcount = mcount;
        this.admin=admin;
    }

    public String getChamaname() {
        return chamaname;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public void setChamaname(String chamaname) {
        this.chamaname = chamaname;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getMcount() {
        return mcount;
    }

    public void setMcount(String mcount) {
        this.mcount = mcount;
    }
}
