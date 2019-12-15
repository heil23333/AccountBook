package com.heil.accountbook;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.heil.accountbook.bean.AccountClass;
import com.heil.accountbook.bean.AccountItem;
import com.heil.accountbook.bean.AccountItemResult;
import com.heil.accountbook.bean.AccountTag;
import com.heil.accountbook.bean.WalletItem;
import com.heil.accountbook.callback.GetAccountItemCallback;
import com.heil.accountbook.database.AccountDAO;
import com.heil.accountbook.database.AccountDatabase;

import java.util.ArrayList;
import java.util.List;

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

    public void getAccountTag(MutableLiveData<List<AccountTag>> tagLiveData, int classId) {
        new GetAccountTagAsyncTask(dao, tagLiveData).execute(classId);
    }

    public void getAccountClass(MutableLiveData<List<AccountClass>> classLiveData) {
        new GetAccountClassAsyncTask(dao, classLiveData).execute();
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

    public void getAccountItemData(GetAccountItemCallback callback) {
        new GetAccountItemAsyncTask(dao, callback).execute();
    }

    public void insertWalletItem(WalletItem... walletItems) {
        new InsertWalletItem(dao).execute(walletItems);
    }

    public static class GetAccountTagAsyncTask extends AsyncTask<Integer, Void, List<AccountTag>> {
        AccountDAO dao;
        MutableLiveData<List<AccountTag>> tagLiveData;

        public GetAccountTagAsyncTask(AccountDAO dao, MutableLiveData<List<AccountTag>> tagLiveData) {
            this.dao = dao;
            this.tagLiveData = tagLiveData;
        }

        @Override
        protected List<AccountTag> doInBackground(Integer... id) {
            if (id.length == 0) {
                return new ArrayList<>();
            }
            return dao.getTagByClassId(id[0]);
        }

        @Override
        protected void onPostExecute(List<AccountTag> tagList) {
            tagLiveData.setValue(tagList);
        }
    }

    public static class GetAccountClassAsyncTask extends AsyncTask<Void, Void, List<AccountClass>> {
        AccountDAO dao;
        MutableLiveData<List<AccountClass>> classLiveData;

        public GetAccountClassAsyncTask(AccountDAO dao, MutableLiveData<List<AccountClass>> classLiveData) {
            this.dao = dao;
            this.classLiveData = classLiveData;
        }

        @Override
        protected List<AccountClass> doInBackground(Void... voids) {
            return dao.getAllAccountClass();
        }

        @Override
        protected void onPostExecute(List<AccountClass> accountClasses) {
            classLiveData.setValue(accountClasses);
        }
    }

    public static class GetAccountItemAsyncTask extends AsyncTask<Void, Void, Void> {
        AccountDAO dao;
        GetAccountItemCallback callback;
        DataSource.Factory<Integer, AccountItemResult> factory;

        public GetAccountItemAsyncTask(AccountDAO dao, GetAccountItemCallback callback) {
            this.dao = dao;
            this.callback = callback;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            factory = dao.getRelAccountItem();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            callback.gotItems(factory);
            super.onPostExecute(aVoid);
        }
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

    public static class InsertWalletItem extends AsyncTask<WalletItem, Void, Void> {
        private AccountDAO dao;

        public InsertWalletItem(AccountDAO dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(WalletItem... walletItems) {
            dao.insertWalletItem(walletItems);
            return null;
        }
    }
}
