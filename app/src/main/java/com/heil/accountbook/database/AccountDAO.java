package com.heil.accountbook.database;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.heil.accountbook.bean.AccountClass;
import com.heil.accountbook.bean.AccountItem;
import com.heil.accountbook.bean.AccountItemResult;
import com.heil.accountbook.bean.AccountTag;

import java.util.List;

@Dao
public interface AccountDAO {
    @Query("SELECT * FROM account_item")
    DataSource.Factory<Integer, AccountItem> getAllAccountItem();
    @Insert
    void insertAccountItem(AccountItem... accountItems);
    @Delete
    void deletAccount(AccountItem... accountItems);

    @Query("SELECT item.id, account_money, account_time, account_describe, class_describe, tag_describe FROM account_item item JOIN account_class class JOIN account_tag tag WHERE item.account_class = class.id AND item.account_tag = tag.id")
    DataSource.Factory<Integer, AccountItemResult> getRelAccountItem();

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
