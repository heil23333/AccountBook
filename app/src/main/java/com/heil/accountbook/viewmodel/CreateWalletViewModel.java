package com.heil.accountbook.viewmodel;

import android.app.Application;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.heil.accountbook.R;
import com.heil.accountbook.Repository;
import com.heil.accountbook.bean.WalletItem;

public class CreateWalletViewModel extends AndroidViewModel {
    private Repository repository;

    public CreateWalletViewModel(@NonNull Application application) {
        super(application);
        repository = Repository.getInstance(application);
    }

    private void insertWalletItem(WalletItem... walletItems) {
        repository.insertWalletItem(walletItems);
    }

    public String createWallet(String walletName, int walletType, String balance, String quota) {
        float balanceF, quotaF;
        if (!TextUtils.isEmpty(walletName) && !TextUtils.isEmpty(balance) ) {
            balanceF = Float.valueOf(balance);
            if ((walletType == 0 || walletType == 2) && balanceF >= 0) {
                WalletItem walletItem = new WalletItem(walletName, walletType, balanceF, 0);
                insertWalletItem(walletItem);
            }else{
                if (TextUtils.isEmpty(quota)) {
                    return getApplication().getString(R.string.quota_cant_empt);
                }
                quotaF = Float.valueOf(quota);
                if (walletType == 1 && balanceF >=0 && quotaF >= balanceF) {
                    WalletItem walletItem = new WalletItem(walletName, walletType, balanceF, quotaF);
                    insertWalletItem(walletItem);
                } else {
                    if (balanceF <0 || quotaF < 0) {
                        return getApplication().getString(R.string.number_must_positive);
                    } else {
                        return getApplication().getString(R.string.balance_cant_over_quota);
                    }
                }
            }
        } else {
            return getApplication().getString(R.string.name_and_balance_cant_empt);
        }
        return getApplication().getString(R.string.create_success);
    }
}
