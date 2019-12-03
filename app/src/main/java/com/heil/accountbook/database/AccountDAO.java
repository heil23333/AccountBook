package com.heil.accountbook.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.heil.accountbook.bean.AccountClass;
import com.heil.accountbook.bean.AccountItem;
import com.heil.accountbook.bean.AccountTag;

import java.util.List;

@Dao
public interface AccountDAO {
    @Query("SELECT * FROM account_item")
    List<AccountItem> getAllAccountItem();
    @Insert
    void insertAccountItem(AccountItem... accountItems);
    @Delete
    void deletAccount(AccountItem... accountItems);

    @Insert
    void insertAccountClass(AccountClass... accountClasses);
    @Query("SELECT * FROM account_class")
    List<AccountClass> getAllAccountClass();
    @Delete
    void deletAccountClass(AccountClass... accountClasses);

    @Query("SELECT * FROM account_tag WHERE class_id = :classId")
    List<AccountTag> getTagByClassId(int classId);
    @Insert
    void insertAccountTag(AccountTag... accountTags);
}
