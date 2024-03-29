package com.heil.accountbook.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.heil.accountbook.bean.AccountClass;
import com.heil.accountbook.bean.AccountItem;
import com.heil.accountbook.bean.AccountTag;
import com.heil.accountbook.bean.WalletItem;

@Database(entities = {AccountItem.class, AccountClass.class, AccountTag.class, WalletItem.class}, version = 1, exportSchema = false)
public abstract class AccountDatabase extends RoomDatabase {
    private static volatile AccountDatabase INSTANCE;
    public abstract AccountDAO getAccountDAO();

    public synchronized static AccountDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AccountDatabase.class, "account").build();
        }
        return INSTANCE;
    }
}
