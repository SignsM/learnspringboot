package com.signs123.baiyunbbs.bean;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

public class User implements Serializable {

    private int uId;

    private String uName;

    private String uPass;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate regTime;

    private int gender;

    public User() {
    }

    public User(int uId, String uName, String uPass, LocalDate regTime, int gender) {
        this.uId = uId;
        this.uName = uName;
        this.uPass = uPass;
        this.regTime = regTime;
        this.gender = gender;
    }

    public User(String uName) {
        this.uName = uName;
    }

    public User(String uName, String uPass) {
        this.uName = uName;
        this.uPass = uPass;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuPass() {
        return uPass;
    }

    public void setuPass(String uPass) {
        this.uPass = uPass;
    }

    public LocalDate getRegTime() {
        return regTime;
    }

    public void setRegTime(LocalDate regTime) {
        this.regTime = regTime;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "User{" +
                "uId=" + uId +
                ", uName='" + uName + '\'' +
                ", uPass='" + uPass + '\'' +
                ", regTime=" + regTime +
                ", gender=" + gender +
                '}';
    }
}
