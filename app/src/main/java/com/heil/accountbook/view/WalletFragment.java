package com.heil.accountbook.view;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.heil.accountbook.R;
import com.heil.accountbook.databinding.FragmentWalletBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class WalletFragment extends Fragment {
    private FragmentWalletBinding binding;

    public WalletFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_wallet, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.toolbar.setOnClickListener(new View.OnClickListener() {// TODO: 2019/12/15 肯定是不能点这里跳进去的咯
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), CreateWalletActivity.class));
            }
        });
    }
}
