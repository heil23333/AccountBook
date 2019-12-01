package com.heil.accountbook.bean;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "account_tag",
        foreignKeys = {
                @ForeignKey(
                        entity = AccountClass.class,
                        parentColumns = "id",
                        childColumns = "class_id"
                )
        }
)
public class AccountTag {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int tagId;

    @ColumnInfo(name = "class_id")
    public int classId;

    @ColumnInfo(name = "tag_describe")
    public String tagDescribe;
}
