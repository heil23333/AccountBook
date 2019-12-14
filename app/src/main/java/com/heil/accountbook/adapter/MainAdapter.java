package com.heil.accountbook.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.heil.accountbook.view.MainFragment;
import com.heil.accountbook.view.WalletFragment;

public class MainAdapter extends FragmentStateAdapter {

    private Fragment[] fragments;

    public MainAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        fragments = new Fragment[] {new MainFragment(), new WalletFragment()};
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments[position];
    }

    @Override
    public int getItemCount() {
        return fragments.length;
    }
}
