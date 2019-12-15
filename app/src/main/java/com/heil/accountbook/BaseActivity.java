package com.heil.accountbook;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.heil.accountbook.utils.ViewUtils;

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.setStateBarColor(this, R.color.colorPrimary);
    }
}
