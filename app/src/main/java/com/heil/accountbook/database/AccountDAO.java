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
import com.heil.accountbook.bean.WalletItem;

import java.util.List;

@Dao
public interface AccountDAO {
    @Insert
    void insertAccountItem(AccountItem... accountItems);
    @Delete
    void deletAccount(AccountItem... accountItems);
    @Query("SELECT item.id, account_money, account_time, account_describe, class_describe, tag_describe " +
            "FROM account_item item JOIN account_class class JOIN account_tag tag WHERE item.account_class = class.id " +
            "AND item.account_tag = tag.id ORDER BY account_time DESC")
    DataSource.Factory<Integer, AccountItemResult> getRelAccountItem();

    /**
     * class相关
     */
    @Insert
    void insertAccountClass(AccountClass... accountClasses);
    @Query("SELECT * FROM account_class")
    List<AccountClass> getAllAccountClass();//查找所有的class
    @Delete
    void deletAccountClass(AccountClass... accountClasses);//删除所有的class

    /**
     * tag相关
     */
    @Query("SELECT * FROM account_tag WHERE class_id = :classId")
    List<AccountTag> getTagByClassId(int classId);//根据class查找对应的tag
    @Insert
    void insertAccountTag(AccountTag... accountTags);//插入账目tag

    /**
     * wallet相关
     */
    @Insert
    void insertWalletItem(WalletItem... walletItems);//插入资产
    @Query("SELECT * FROM wallet_item")
    DataSource.Factory<Integer, WalletItem> getAllWalletItem();
}
