package com.heil.accountbook.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AccountItemResult {
    int id;
    float account_money;
    long account_time;
    String account_describe, class_describe, tag_describe, realTime, wallet_describe;

    public AccountItemResult(int id, float account_money, long account_time, String account_describe, String class_describe, String tag_describe, String wallet_describe) {
        this.id = id;
        this.account_money = account_money;
        this.account_time = account_time;
        this.account_describe = account_describe;
        this.class_describe = class_describe;
        this.tag_describe = tag_describe;
        this.realTime = getRealTime(account_time);
        this.wallet_describe = wallet_describe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getAccount_money() {
        return account_money;
    }

    public void setAccount_money(float account_money) {
        this.account_money = account_money;
    }

    public long getAccount_time() {
        return account_time;
    }

    public void setAccount_time(long account_time) {
        this.account_time = account_time;
        this.realTime = getRealTime(account_time);
    }

    public String getAccount_describe() {
        return account_describe;
    }

    public void setAccount_describe(String account_describe) {
        this.account_describe = account_describe;
    }

    public String getClass_describe() {
        return class_describe;
    }

    public void setClass_describe(String class_describe) {
        this.class_describe = class_describe;
    }

    public String getTag_describe() {
        return tag_describe;
    }

    public void setTag_describe(String tag_describe) {
        this.tag_describe = tag_describe;
    }
    public String getRealTime() {
        return this.realTime;
    }

    public String getRealTime(long time) {
        Date date = new Date(time);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm");
        return simpleDateFormat.format(date);
    }

    public void setRealTime(String realTime) {
        this.realTime = realTime;
    }

    public String getWallet_describe() {
        return wallet_describe;
    }

    public void setWallet_describe(String wallet_describe) {
        this.wallet_describe = wallet_describe;
    }
}
