package com.heil.accountbook;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.heil.accountbook.adapter.MainAdapter;
import com.heil.accountbook.databinding.ActivityMainBinding;
import com.heil.accountbook.utils.ViewUtils;

public class MainActivity extends FragmentActivity {
    public static BottomNavigationView navigationView;
    private ActivityMainBinding binding;

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
    }
}
