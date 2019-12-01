package com.heil.accountbook.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.heil.accountbook.database.AccountDatabase;

public class MainViewModel extends AndroidViewModel {
    AccountDatabase accountDatabase;
    public MainViewModel(@NonNull Application application) {
        super(application);
        accountDatabase = AccountDatabase.getDatabase(application);
    }
}
