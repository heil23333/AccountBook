package com.heil.accountbook.callback;

import androidx.paging.DataSource;

import com.heil.accountbook.bean.AccountItemResult;

public interface GetAccountItemCallback {
    void gotItems(DataSource.Factory<Integer, AccountItemResult> data);
}
