package com.heil.accountbook.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.heil.accountbook.callback.GetAccountItemCallback;
import com.heil.accountbook.Repository;
import com.heil.accountbook.bean.AccountClass;
import com.heil.accountbook.bean.AccountItem;
import com.heil.accountbook.bean.AccountTag;
import com.tencent.mmkv.MMKV;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private Repository repository;
    private MMKV mmkv;
    MutableLiveData<List<AccountClass>> classLiveData;
    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = Repository.getInstance(application);
        mmkv = MMKV.defaultMMKV();
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
            insertAccountItem(new AccountItem(System.currentTimeMillis(), 1.0f, 1, 1, "早餐"));
            mmkv.encode("FIRST", false);
        }
    }

    public void loadClassData(MutableLiveData<List<AccountClass>> classLiveData) {
        repository.getAccountClass(classLiveData);
    }

    public void insertAccountClass(AccountClass... accountClasses) {
        repository.insertAccountClass(accountClasses);
    }

    public void insertAccountTag(AccountTag... accountTags) {
        repository.insertAccountTag(accountTags);
    }

    public void insertAccountItem(AccountItem... accountItems) {
        repository.insertAccountItem(accountItems);
    }

    public void getAccountItemData(GetAccountItemCallback callback) {
        repository.getAccountItemData(callback);
    }
}
