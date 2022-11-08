package com.example.myapplication;

public class MainModel {
    String name,email,aadhar;

    MainModel()
    {

    }

    public MainModel(String name, String email, String aadhar) {
        this.name = name;
        this.email = email;
        this.aadhar = aadhar;
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

    public String getAadhar() {
        return aadhar;
    }

    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }
}
