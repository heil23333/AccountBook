package com.heil.accountbook.view;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.heil.accountbook.bean.AccountItemResult;
import com.heil.accountbook.callback.GetAccountItemCallback;
import com.heil.accountbook.R;
import com.heil.accountbook.adapter.MyAccountItemAdapter;
import com.heil.accountbook.bean.AccountItem;
import com.heil.accountbook.databinding.FragmentMainBinding;
import com.heil.accountbook.viewmodel.MainViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements GetAccountItemCallback {

    private MainViewModel viewModel;
    private FragmentMainBinding binding;
    private MyAccountItemAdapter accountItemAdapter;
    private LiveData<PagedList<AccountItemResult>> accountList;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        binding.setLifecycleOwner(this);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        binding.setViewmodel(viewModel);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_addAccountFragment);
            }
        });
        accountItemAdapter = new MyAccountItemAdapter();
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false));
        binding.recyclerView.setAdapter(accountItemAdapter);
        viewModel.getAccountItemData(this);
    }

    @Override
    public void gotItems(DataSource.Factory<Integer, AccountItemResult> data) {
        accountList = new LivePagedListBuilder<>(data, 10).build();
        accountList.observe(this, new Observer<PagedList<AccountItemResult>>() {
            @Override
            public void onChanged(PagedList<AccountItemResult> accountItems) {
                accountItemAdapter.submitList(accountItems);
            }
        });
    }
}
