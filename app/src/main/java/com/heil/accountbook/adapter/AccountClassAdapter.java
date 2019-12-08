package com.heil.accountbook.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.heil.accountbook.R;
import com.heil.accountbook.bean.AccountClass;

import java.util.List;

public class AccountClassAdapter extends RecyclerView.Adapter<AccountClassAdapter.AccountClassViewHolder> {
    List<AccountClass> accountClasses;

    public AccountClassAdapter(List<AccountClass> accountClasses) {
        this.accountClasses = accountClasses;
    }

    @NonNull
    @Override
    public AccountClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AccountClassViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_account_class, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AccountClassViewHolder holder, int position) {
        holder.className.setText(accountClasses.get(position).getClassDescribe());
    }

    @Override
    public int getItemCount() {
        return accountClasses == null ? 0 : accountClasses.size();
    }

    static class AccountClassViewHolder extends RecyclerView.ViewHolder {
        TextView className;
        public AccountClassViewHolder(@NonNull View itemView) {
            super(itemView);
            className = itemView.findViewById(R.id.class_describe);
        }
    }
}
