package com.heil.accountbook;

import android.content.Context;
import android.os.AsyncTask;

import com.heil.accountbook.bean.AccountClass;
import com.heil.accountbook.bean.AccountItem;
import com.heil.accountbook.bean.AccountTag;
import com.heil.accountbook.database.AccountDAO;
import com.heil.accountbook.database.AccountDatabase;

public class Repository {
    private Context context;
    private AccountDAO dao;
    private static Repository INSTANCE;

    private Repository(Context context) {
        this.context = context;
        dao = AccountDatabase.getDatabase(context).getAccountDAO();
    }
    public static Repository getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new Repository(context);
        }
        return INSTANCE;
    }

    public void insertAccountClass(AccountClass... accountClasses) {
        new InsertAccountClass(dao).execute(accountClasses);
    }

    public void insertAccountTag(AccountTag... accountTags) {
        new InsertAccountTag(dao).execute(accountTags);
    }

    public void insertAccountItem(AccountItem... accountItems) {
        new InsertAccountItem(dao).execute(accountItems);
    }

    public static class InsertAccountItem extends AsyncTask<AccountItem, Void, Void> {
        AccountDAO dao;

        public InsertAccountItem(AccountDAO dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(AccountItem... accountItems) {
            dao.insertAccountItem(accountItems);
            return null;
        }
    }

    public static class InsertAccountTag extends AsyncTask<AccountTag, Void, Void> {
        private AccountDAO dao;

        InsertAccountTag(AccountDAO dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(AccountTag... accountTags) {
            dao.insertAccountTag(accountTags);
            return null;
        }
    }

    public static class InsertAccountClass extends AsyncTask<AccountClass, Void, Void> {
        private AccountDAO dao;

        InsertAccountClass(AccountDAO dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(AccountClass... accountClasses) {
            dao.insertAccountClass(accountClasses);
            return null;
        }
    }
}
