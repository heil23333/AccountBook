package com.heil.accountbook.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.heil.accountbook.R;
import com.heil.accountbook.bean.AccountItemResult;

public class MyAccountItemAdapter extends PagedListAdapter<AccountItemResult, MyAccountItemAdapter.AccountItemViewHolder> {

    public MyAccountItemAdapter() {
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
    }

    @NonNull
    @Override
    public AccountItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AccountItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_account_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AccountItemViewHolder holder, int position) {
        holder.accountItemTag.setText(getItem(position).getTag_describe());
        holder.accountItemClass.setText(getItem(position).getClass_describe());
        holder.accountItemMoney.setText(String.valueOf(getItem(position).getAccount_money()));
        holder.accountItemTime.setText(String.valueOf(getItem(position).getRealTime()));
    }

    static class AccountItemViewHolder extends RecyclerView.ViewHolder{
        TextView accountItemTag, accountItemClass, accountItemMoney, accountItemTime;
        public AccountItemViewHolder(View view) {
            super(view);
            accountItemTag = view.findViewById(R.id.accountItemTag);
            accountItemClass = view.findViewById(R.id.accountItemClass);
            accountItemMoney = view.findViewById(R.id.accountItemMoney);
            accountItemTime = view.findViewById(R.id.accountItemTime);
        }
    }
}
