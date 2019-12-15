package com.heil.accountbook.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.heil.accountbook.R;
import com.heil.accountbook.databinding.ActivityCreateWalletBinding;
import com.heil.accountbook.viewmodel.CreateWalletViewModel;

public class CreateWalletActivity extends AppCompatActivity {
    ActivityCreateWalletBinding binding;
    CreateWalletViewModel viewModel;
    private int walletType = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_wallet);
        viewModel = ViewModelProviders.of(this).get(CreateWalletViewModel.class);

        binding.walletType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.current) {
                    walletType = 0;
                } else if (checkedId == R.id.credit) {
                    walletType = 1;
                } else {
                    walletType = 2;
                }
            }
        });
        binding.finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (walletType == -1) {
                    Toast.makeText(CreateWalletActivity.this, getString(R.string.please_select_wallet_type), Toast.LENGTH_LONG).show();
                } else {
                    String result = viewModel.createWallet(binding.walletName.getText().toString(), walletType,
                            binding.balance.getText().toString(), binding.quota.getText().toString());

                    Toast.makeText(CreateWalletActivity.this, result, Toast.LENGTH_LONG).show();
                    if (result.equals(getString(R.string.create_success))) {
                        finish();
                    }
                }
            }
        });
    }
}
