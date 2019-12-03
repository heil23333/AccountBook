package com.heil.accountbook.viewmodel;

import android.app.Application;
import android.os.SystemClock;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.heil.accountbook.Repository;
import com.heil.accountbook.bean.AccountClass;
import com.heil.accountbook.bean.AccountItem;
import com.heil.accountbook.bean.AccountTag;
import com.heil.accountbook.database.AccountDatabase;

public class MainViewModel extends AndroidViewModel {
    Repository repository;
    AccountDatabase accountDatabase;
    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = Repository.getInstance(application);
    }

    public void insertAccountClass(View view) {
        AccountClass accountClass = new AccountClass();
        accountClass.setClassDescribe("测试");
        repository.insertAccountClass(accountClass);
    }

    public void insertAccountTag(View view) {
        AccountTag accountTag = new AccountTag();
        accountTag.setTagDescribe("测试");
        accountTag.setClassId(1);
        repository.insertAccountTag(accountTag);
    }

    public void insertAccountItem(View view) {
        AccountItem accountItem = new AccountItem(System.currentTimeMillis(), 10.5f, 1, 1, "测试");
        repository.insertAccountItem(accountItem);
    }
}
