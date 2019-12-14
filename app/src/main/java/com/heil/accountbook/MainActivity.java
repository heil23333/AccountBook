package com.heil.accountbook;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.heil.accountbook.adapter.MainAdapter;
import com.heil.accountbook.databinding.ActivityMainBinding;

public class MainActivity extends FragmentActivity {
    public static BottomNavigationView navigationView;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        navigationView = binding.navView;

        binding.mainContent.setAdapter(new MainAdapter(this));
        binding.mainContent.setCurrentItem(0);
        binding.navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                binding.mainContent.setCurrentItem(menuItem.getItemId() == R.id.account ? 0 : 1);
                return true;
            }
        });
    }
}
