package com.heil.accountbook.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.heil.accountbook.bean.AccountItemResult;
import com.heil.accountbook.bean.WalletItem;
import com.heil.accountbook.callback.GetAccountItemCallback;
import com.heil.accountbook.Repository;
import com.heil.accountbook.bean.AccountClass;
import com.heil.accountbook.bean.AccountItem;
import com.heil.accountbook.bean.AccountTag;
import com.heil.accountbook.callback.GetWalletItemCallback;
import com.heil.accountbook.callback.LoadedAccountData;
import com.heil.accountbook.callback.LoadedWalletData;
import com.tencent.mmkv.MMKV;

public class MainViewModel extends AndroidViewModel implements GetAccountItemCallback, GetWalletItemCallback {
    private Repository repository;
    private MMKV mmkv;
    private LiveData<PagedList<AccountItemResult>> accountList;
    private LoadedAccountData loadedAccountData;
    private LoadedWalletData loadedWalletData;
    private LiveData<PagedList<WalletItem>> wallets;

    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = Repository.getInstance(application);
        mmkv = MMKV.defaultMMKV();
        getAccountItemData(this);
        getWalletItemData(this);
        if (mmkv.decodeBool("FIRST", true)) {
            AccountClass accountClass = new AccountClass("吃喝");
            insertAccountClass(accountClass);
            AccountTag[] accountTags = new AccountTag[5];
            accountTags[0] = new AccountTag(1, "早餐");
            accountTags[1] = new AccountTag(1, "午餐");
            accountTags[2] = new AccountTag(1, "晚餐");
            accountTags[3] = new AccountTag(1, "零食");
            accountTags[4] = new AccountTag(1, "宵夜");
            insertAccountTag(accountTags);
            insertAccountItem(new AccountItem(System.currentTimeMillis(), 1.0f, 1, 1, "早餐", -1));
            mmkv.encode("FIRST", false);
        }
    }

    public LiveData<PagedList<WalletItem>> getWallets() {
        return wallets;
    }

    public void setLoadedWalletData(LoadedWalletData loadedWalletData) {
        this.loadedWalletData = loadedWalletData;
    }

    public void setLoadedAccountData(LoadedAccountData loadedAccountData) {
        this.loadedAccountData = loadedAccountData;
    }

    public LiveData<PagedList<AccountItemResult>> getAccountList() {
        return accountList;
    }

    public void insertAccountClass(AccountClass... accountClasses) {//插入账目分类
        repository.insertAccountClass(accountClasses);
    }

    public void insertAccountTag(AccountTag... accountTags) {//插入账目标签
        repository.insertAccountTag(accountTags);
    }

    public void insertAccountItem(AccountItem... accountItems) {//插入账目
        repository.insertAccountItem(accountItems);
    }

    public void getAccountItemData(GetAccountItemCallback callback) {
        repository.getAccountItemData(callback);
    }

    @Override
    public void gotItems(DataSource.Factory<Integer, AccountItemResult> data) {
        accountList = new LivePagedListBuilder<>(data, 10).build();
        if (loadedAccountData != null) {
            loadedAccountData.load();
        }
    }

    public void getWalletItemData(GetWalletItemCallback callback) {
        repository.getWalletItemData(callback);
    }

    @Override
    public void gotWallet(DataSource.Factory<Integer, WalletItem> data) {
        wallets = new LivePagedListBuilder<>(data, 10).build();
        if (loadedWalletData != null) {
            loadedWalletData.load();
        }
    }
}
