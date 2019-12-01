package com.heil.accountbook.bean;

import androidx.annotation.NonNull;
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

    @ColumnInfo(name = "account_class")
    public int accountClass;

    @ColumnInfo(name = "account_tag")
    public int accountTag;
}
