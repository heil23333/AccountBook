package com.heil.accountbook;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.heil.accountbook.adapter.MainAdapter;
import com.heil.accountbook.databinding.ActivityMainBinding;
import com.heil.accountbook.utils.ViewUtils;
import com.heil.accountbook.view.AddAccountActivity;
import com.heil.accountbook.view.CreateWalletActivity;

public class MainActivity extends FragmentActivity {
    public static BottomNavigationView navigationView;
    private ActivityMainBinding binding;
    private int currentFragment = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        navigationView = binding.navView;
        ViewUtils.setStateBarColor(this, R.color.colorPrimary);
        binding.mainContent.setAdapter(new MainAdapter(this));
        binding.mainContent.setCurrentItem(0);
        binding.mainContent.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                currentFragment = position;
                binding.navView.setSelectedItemId(position == 0 ? R.id.account : R.id.wallet);
            }
        });
        binding.navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                binding.mainContent.setCurrentItem(menuItem.getItemId() == R.id.account ? 0 : 1);
                return true;
            }
        });
        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, currentFragment == 0 ? AddAccountActivity.class : CreateWalletActivity.class));
            }
        });
    }
}
