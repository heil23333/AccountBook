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

import java.text.SimpleDateFormat;
import java.util.Date;

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
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_account_item, parent, false);
        return new AccountItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountItemViewHolder holder, int position) {
        holder.accountItemClass.setText(getItem(position).getClass_describe());
        holder.accountItemTag.setText(getItem(position).getTag_describe());
        Date time = new Date(getItem(position).getAccount_time());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm");
        holder.accountItemTime.setText(simpleDateFormat.format(time));
        holder.accountItemMoney.setText(getItem(position).getAccount_money() + "元");
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
