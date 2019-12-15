package com.heil.accountbook.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.heil.accountbook.bean.WalletItem;
import com.heil.accountbook.databinding.LayoutWalletItemBinding;

public class MyWalletAdapter extends PagedListAdapter<WalletItem, MyWalletAdapter.WalletItemViewHolder> {
    private int layoutId;

    public MyWalletAdapter(int layoutId) {
        super(new DiffUtil.ItemCallback<WalletItem>() {
            @Override
            public boolean areItemsTheSame(@NonNull WalletItem oldItem, @NonNull WalletItem newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull WalletItem oldItem, @NonNull WalletItem newItem) {
                return oldItem.walletDescribe.equals(newItem.walletDescribe);
            }
        });
        this.layoutId = layoutId;
    }

    @NonNull
    @Override
    public WalletItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutWalletItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), layoutId, parent, false);
        return new WalletItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull WalletItemViewHolder holder, int position) {
        LayoutWalletItemBinding binding = (LayoutWalletItemBinding) holder.dataBinding;
        binding.setResult(getItem(position));
    }

    static class WalletItemViewHolder extends RecyclerView.ViewHolder{
        ViewDataBinding dataBinding;
        public WalletItemViewHolder(ViewDataBinding dataBinding) {
            super(dataBinding.getRoot());
            this.dataBinding = dataBinding;
        }
    }
}
