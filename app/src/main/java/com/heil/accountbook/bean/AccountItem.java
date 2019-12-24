package com.heil.accountbook.bean;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "account_item", foreignKeys = {
        @ForeignKey(entity = AccountClass.class, parentColumns = "id", childColumns = "account_class"),
        @ForeignKey(entity = AccountTag.class, parentColumns = "id", childColumns = "account_tag")
})
public class AccountItem {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "account_time")
    public long accountTime;

    @ColumnInfo(name = "account_money")
    public float accountMoney;

    @ColumnInfo(name = "account_class", index = true)
    public int accountClass;

    @ColumnInfo(name = "account_tag", index = true)
    public int accountTag;

    @ColumnInfo(name = "account_describe")
    public String accountDescribe;

    @ColumnInfo(name = "wallet_id", defaultValue = "-1")
    public int walletId;

    public AccountItem(long accountTime, float accountMoney, int accountClass, int accountTag, String accountDescribe, int walletId) {
        this.accountTime = accountTime;
        this.accountMoney = accountMoney;
        this.accountClass = accountClass;
        this.accountTag = accountTag;
        this.accountDescribe = accountDescribe;
        this.walletId = walletId;
    }

    public int getId() {
        return id;
    }

    public long getAccountTime() {
        return accountTime;
    }

    public void setAccountTime(long accountTime) {
        this.accountTime = accountTime;
    }

    public float getAccountMoney() {
        return accountMoney;
    }

    public void setAccountMoney(float accountMoney) {
        this.accountMoney = accountMoney;
    }

    public int getAccountClass() {
        return accountClass;
    }

    public void setAccountClass(int accountClass) {
        this.accountClass = accountClass;
    }

    public int getAccountTag() {
        return accountTag;
    }

    public void setAccountTag(int accountTag) {
        this.accountTag = accountTag;
    }

    public String getAccountDescribe() {
        return accountDescribe;
    }

    public void setAccountDescribe(String accountDescribe) {
        this.accountDescribe = accountDescribe;
    }

    public int getWalletId() {
        return walletId;
    }

    public void setWalletId(int walletId) {
        this.walletId = walletId;
    }
}
