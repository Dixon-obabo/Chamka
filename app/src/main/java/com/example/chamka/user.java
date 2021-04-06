package com.example.chamka;

public class user {
String name;
String email;
String phone;
String key;
user(){}

    public user(String name, String email, String phone, String key) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
