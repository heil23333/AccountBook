package com.heil.accountbook.callback;

import androidx.paging.DataSource;

import com.heil.accountbook.bean.WalletItem;

public interface GetWalletItemCallback {
    void gotWallet(DataSource.Factory<Integer, WalletItem> data);
}
