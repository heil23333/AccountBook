package com.heil.accountbook.callback;

import androidx.paging.DataSource;

import com.heil.accountbook.bean.AccountItem;

public interface GetAccountItemCallback {
    void gotItems(DataSource.Factory<Integer, AccountItem> data);
}
