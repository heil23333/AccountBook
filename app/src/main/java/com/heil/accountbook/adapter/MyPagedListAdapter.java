package com.heil.accountbook.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.heil.accountbook.bean.AccountItemResult;
import com.heil.accountbook.databinding.LayoutAccountItemBinding;

public class MyPagedListAdapter extends PagedListAdapter<AccountItemResult, MyPagedListAdapter.AccountItemViewHolder> {

    private int layoutId;

    public MyPagedListAdapter(int layoutId) {
        super(new DiffUtil.ItemCallback<AccountItemResult>() {
            @Override
            public boolean areItemsTheSame(@NonNull AccountItemResult oldItem, @NonNull AccountItemResult newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull AccountItemResult oldItem, @NonNull AccountItemResult newItem) {
                return oldItem.getAccount_time() == newItem.getAccount_time();
            }
        });
        this.layoutId = layoutId;
    }

    @NonNull
    @Override
    public AccountItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutAccountItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), layoutId, parent, false);
        return new AccountItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountItemViewHolder holder, int position) {
        LayoutAccountItemBinding binding = (LayoutAccountItemBinding) holder.dataBinding;
        binding.setResult(getItem(position));
    }

    static class AccountItemViewHolder extends RecyclerView.ViewHolder{
        ViewDataBinding dataBinding;
        public AccountItemViewHolder(ViewDataBinding dataBinding) {
            super(dataBinding.getRoot());
            this.dataBinding = dataBinding;
        }
    }
}
