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
import com.heil.accountbook.bean.AccountItem;

import java.util.Date;

public class MyAccountItemAdapter extends PagedListAdapter<AccountItem, MyAccountItemAdapter.AccountItemViewHolder> {

    public MyAccountItemAdapter() {
        super(new DiffUtil.ItemCallback<AccountItem>() {
            @Override
            public boolean areItemsTheSame(@NonNull AccountItem oldItem, @NonNull AccountItem newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull AccountItem oldItem, @NonNull AccountItem newItem) {
                return oldItem.getAccountTime() == newItem.getAccountTime();
            }
        });
    }

    @NonNull
    @Override
    public AccountItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_account_item, parent, false);
        return new AccountItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountItemViewHolder holder, int position) {
        holder.accountItemClass.setText(String.valueOf(getItem(position).getAccountClass()));
        holder.accountItemTag.setText(String.valueOf(getItem(position).getAccountTag()));
        String time = new Date(getItem(position).getAccountTime()).toString();
        holder.accountItemTime.setText(time);
        holder.accountItemMoney.setText(String.valueOf(getItem(position).getAccountMoney()));
    }

    static class AccountItemViewHolder extends RecyclerView.ViewHolder{
        TextView accountItemClass, accountItemTag, accountItemMoney, accountItemTime;
        public AccountItemViewHolder(@NonNull View itemView) {
            super(itemView);
            accountItemClass = itemView.findViewById(R.id.accountItemClass);
            accountItemTag = itemView.findViewById(R.id.accountItemTag);
            accountItemMoney = itemView.findViewById(R.id.accountItemMoney);
            accountItemTime = itemView.findViewById(R.id.accountItemTime);
        }
    }
}
