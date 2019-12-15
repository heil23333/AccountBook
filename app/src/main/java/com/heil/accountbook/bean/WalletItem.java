package com.heil.accountbook.bean;

import android.view.View;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "wallet_item")
public class WalletItem {
    @ColumnInfo(name = "_id")
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "wallet_describe")
    public String walletDescribe;
    @ColumnInfo(name = "wallet_type")
    public int walletType;
    public float quota;
    public float balance;

    public int getId() {
        return id;
    }

    public String getWalletDescribe() {
        return walletDescribe;
    }

    public void setWalletDescribe(String walletDescribe) {
        this.walletDescribe = walletDescribe;
    }

    public int getWalletType() {
        return walletType;
    }

    public void setWalletType(int walletType) {
        this.walletType = walletType;
    }

    public float getQuota() {
        return quota;
    }

    public void setQuota(float quota) {
        this.quota = quota;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public int isCredit() {
        return this.walletType == 1 ? View.VISIBLE : View.GONE;
    }

    public WalletItem(String walletDescribe, int walletType, float balance, float quota) {
        this.walletDescribe = walletDescribe;
        this.walletType = walletType;
        this.quota = quota;
        this.balance = balance;
    }
}
