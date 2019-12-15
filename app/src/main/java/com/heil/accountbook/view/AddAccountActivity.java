package com.heil.accountbook.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.heil.accountbook.BaseActivity;
import com.heil.accountbook.R;
import com.heil.accountbook.bean.AccountClass;
import com.heil.accountbook.bean.AccountItem;
import com.heil.accountbook.bean.AccountTag;
import com.heil.accountbook.databinding.ActivityAddAccountBinding;
import com.heil.accountbook.utils.ViewUtils;
import com.heil.accountbook.viewmodel.AddAccountViewModel;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;

import java.util.List;

public class AddAccountActivity extends BaseActivity{
    private ActivityAddAccountBinding binding;
    private AddAccountViewModel viewModel;
    private MutableLiveData<List<AccountClass>> classLiveData;
    private MutableLiveData<List<AccountTag>> tagLiveData;
    private int classPosition = 0, tagPosition = -1;
    private String tagDescribe = "";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_account);
        viewModel = ViewModelProviders.of(this).get(AddAccountViewModel.class);
        setSupportActionBar(binding.toolbar);

        classLiveData = new MutableLiveData<>();
        tagLiveData = new MutableLiveData<>();
        viewModel.loadClassData(classLiveData);
        classLiveData.observe(this, new Observer<List<AccountClass>>() {
            @Override
            public void onChanged(final List<AccountClass> accountClasses) {
                accountClasses.add(new AccountClass("+"));
                TagAdapter<AccountClass> classTagAdapter = new TagAdapter<AccountClass>(accountClasses) {
                    @Override
                    public View getView(FlowLayout parent, int position, AccountClass accountClass) {
                        LinearLayout linearLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.layout_account_class, binding.classLayout, false);
                        TextView textView = linearLayout.findViewById(R.id.class_describe);
                        textView.setText(accountClass.getClassDescribe());
                        return linearLayout;
                    }

                    @Override
                    public void onSelected(int position, View view) {// TODO: 2019/12/8  点“+”不应该被选中
                        super.onSelected(position, view);
                        classPosition = accountClasses.get(position).getClassId();
                        if (position == (accountClasses.size() -1)) {
                            classPosition = -1;
                            final EditText editText = new EditText(AddAccountActivity.this);
                            AlertDialog dialog = new AlertDialog.Builder(AddAccountActivity.this)
                                    .setTitle(getResources().getString(R.string.add_class))
                                    .setView(editText)
                                    .setPositiveButton(getResources().getText(R.string.confirm), new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            ViewUtils.hideSoftKeyboard(AddAccountActivity.this, editText);
                                            viewModel.insertAccountClass(new AccountClass(editText.getText().toString()));
                                            viewModel.loadClassData(classLiveData);
                                        }
                                    })
                                    .setNegativeButton(getResources().getText(R.string.cancel), new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            ViewUtils.hideSoftKeyboard(AddAccountActivity.this, editText);
                                        }
                                    })
                                    .create();
                            dialog.show();
                        } else {
                            tagLiveData.observe(AddAccountActivity.this, new Observer<List<AccountTag>>() {
                                @Override
                                public void onChanged(final List<AccountTag> tagList) {
                                    if (tagList.size() == 0 || !tagList.get(tagList.size() -1).getTagDescribe().equals("+")) {
                                        tagList.add(new AccountTag(0, "+"));
                                    }
                                    TagAdapter<AccountTag> tagAdapter = new TagAdapter<AccountTag>(tagList) {
                                        @Override
                                        public View getView(FlowLayout parent, int position, AccountTag accountTag) {
                                            LinearLayout linearLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.layout_account_class, binding.tagLayout, false);
                                            TextView textView = linearLayout.findViewById(R.id.class_describe);
                                            textView.setText(accountTag.getTagDescribe());
                                            return linearLayout;
                                        }

                                        @Override
                                        public void onSelected(int position, View view) {
                                            super.onSelected(position, view);
                                            tagPosition = tagList.get(position).getTagId();
                                            tagDescribe = tagList.get(position).getTagDescribe();
                                            if (position == (tagList.size() -1)) {
                                                tagPosition = -1;
                                                final EditText editText = new EditText(AddAccountActivity.this);
                                                AlertDialog dialog = new AlertDialog.Builder(AddAccountActivity.this)
                                                        .setTitle(getResources().getString(R.string.add_class))
                                                        .setView(editText)
                                                        .setPositiveButton(getResources().getText(R.string.confirm), new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                                ViewUtils.hideSoftKeyboard(AddAccountActivity.this, editText);
                                                                viewModel.insertAccountTag(new AccountTag(classPosition, editText.getText().toString()));
                                                                viewModel.loadTagData(tagLiveData, classPosition);
                                                            }
                                                        })
                                                        .setNegativeButton(getResources().getText(R.string.cancel), new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                                ViewUtils.hideSoftKeyboard(AddAccountActivity.this, editText);
                                                            }
                                                        })
                                                        .create();
                                                dialog.show();
                                            }
                                        }
                                    };
                                    binding.tagLayout.setAdapter(tagAdapter);
                                }
                            });
                            viewModel.loadTagData(tagLiveData, classPosition);
                        }
                    }
                };
                binding.classLayout.setAdapter(classTagAdapter);
                classTagAdapter.setSelectedList(0);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_fragment_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.finish) {
            if (classPosition != -1 && tagPosition != -1 && binding.money.getText().length() > 0) {
                ViewUtils.hideSoftKeyboard(this, binding.money);
                viewModel.insertAccountItem(new AccountItem(System.currentTimeMillis(), Float.valueOf(binding.money.getText().toString()),
                        classPosition, tagPosition, tagDescribe));
                this.finish();
            } else {
                Toast.makeText(this, getResources().getText(R.string.retry), Toast.LENGTH_LONG).show();
            }
        }
        return true;
    }
}
