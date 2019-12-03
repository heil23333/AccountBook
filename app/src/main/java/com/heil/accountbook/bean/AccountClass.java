package com.heil.accountbook.bean;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "account_class")
public class AccountClass {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    public int classId;

    @ColumnInfo(name = "class_describe")
    public String classDescribe;

    public int getClassId() {
        return classId;
    }

    public String getClassDescribe() {
        return classDescribe;
    }

    public void setClassDescribe(String classDescribe) {
        this.classDescribe = classDescribe;
    }

}
