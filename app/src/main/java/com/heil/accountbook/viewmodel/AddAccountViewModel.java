package com.heil.accountbook.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.heil.accountbook.Repository;
import com.heil.accountbook.bean.AccountClass;
import com.heil.accountbook.bean.AccountItem;
import com.heil.accountbook.bean.AccountTag;

import java.util.List;

public class AddAccountViewModel extends AndroidViewModel{
    private Repository repository;

    public AddAccountViewModel(@NonNull Application application) {
        super(application);
        repository = Repository.getInstance(application);
    }

    public void loadTagData(MutableLiveData<List<AccountTag>> tagData, int classId) {
        repository.getAccountTag(tagData, classId);
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
}
