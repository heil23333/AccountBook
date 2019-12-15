package com.heil.accountbook.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.heil.accountbook.MainActivity;
import com.heil.accountbook.bean.AccountItemResult;
import com.heil.accountbook.R;
import com.heil.accountbook.adapter.MyPagedListAdapter;
import com.heil.accountbook.callback.LoadedAccountData;
import com.heil.accountbook.databinding.FragmentMainBinding;
import com.heil.accountbook.viewmodel.MainViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements LoadedAccountData {

    private MainViewModel viewModel;
    private FragmentMainBinding binding;
    private MyPagedListAdapter accountItemAdapter;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        if (MainActivity.navigationView != null) {
            MainActivity.navigationView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        binding.setLifecycleOwner(this);
        viewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        binding.setViewmodel(viewModel);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), AddAccountActivity.class));
            }
        });
        accountItemAdapter = new MyPagedListAdapter(R.layout.layout_account_item);
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false));
        binding.recyclerView.setAdapter(accountItemAdapter);
        viewModel.setLoadedAccountData(this);
        if (viewModel.getAccountList() != null) {
            load();
        }
    }

    @Override
    public void load() {
        viewModel.getAccountList().observe(this, new Observer<PagedList<AccountItemResult>>() {
            @Override
            public void onChanged(PagedList<AccountItemResult> accountItemResults) {
                accountItemAdapter.submitList(accountItemResults);
            }
        });
    }
}
