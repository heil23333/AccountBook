package com.heil.accountbook.view;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.heil.accountbook.R;
import com.heil.accountbook.adapter.MyWalletAdapter;
import com.heil.accountbook.bean.WalletItem;
import com.heil.accountbook.callback.LoadedWalletData;
import com.heil.accountbook.databinding.FragmentWalletBinding;
import com.heil.accountbook.viewmodel.MainViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class WalletFragment extends Fragment implements LoadedWalletData {
    private FragmentWalletBinding binding;
    private MainViewModel viewModel;
    private MyWalletAdapter adapter;

    public WalletFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_wallet, container, false);
        binding.setLifecycleOwner(this);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new MyWalletAdapter(R.layout.layout_wallet_item);
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setAdapter(adapter);
        viewModel.setLoadedWalletData(this);
        if (viewModel.getWallets() != null) {
            load();
        }
        binding.toolbar.setOnClickListener(new View.OnClickListener() {// TODO: 2019/12/15 肯定是不能点这里跳进去的咯
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), CreateWalletActivity.class));
            }
        });
    }

    @Override
    public void load() {
        viewModel.getWallets().observe(this, new Observer<PagedList<WalletItem>>() {
            @Override
            public void onChanged(PagedList<WalletItem> walletItems) {
                adapter.submitList(walletItems);
            }
        });
    }
}
