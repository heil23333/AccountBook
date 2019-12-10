package com.heil.accountbook.view;


import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.heil.accountbook.MainActivity;
import com.heil.accountbook.R;
import com.heil.accountbook.bean.AccountClass;
import com.heil.accountbook.bean.AccountItem;
import com.heil.accountbook.bean.AccountTag;
import com.heil.accountbook.databinding.FragmentAddAccountBinding;
import com.heil.accountbook.utils.ViewUtils;
import com.heil.accountbook.viewmodel.MainViewModel;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddAccountFragment extends Fragment {
    private FragmentAddAccountBinding binding;
    private MainViewModel viewModel;
    private MutableLiveData<List<AccountClass>> classLiveData;
    private MutableLiveData<List<AccountTag>> tagLiveData;
    private int classPosition = 0, tagPosition = 0;
    private String tagDescribe = "";

    public AddAccountFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_account, container, false);
        viewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((AppCompatActivity)getActivity()).setSupportActionBar(binding.toolbar);//不加这一行不会显示Menu哦
        if (MainActivity.navigationView != null) {
            MainActivity.navigationView.setVisibility(View.GONE);
        }
        classLiveData = new MutableLiveData<>();
        tagLiveData = new MutableLiveData<>();
        viewModel.loadClassData(classLiveData);
        final LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        classLiveData.observe(this, new Observer<List<AccountClass>>() {
            @Override
            public void onChanged(final List<AccountClass> accountClasses) {
                accountClasses.add(new AccountClass("+"));
                TagAdapter<AccountClass> classTagAdapter = new TagAdapter<AccountClass>(accountClasses) {
                    @Override
                    public View getView(FlowLayout parent, int position, AccountClass accountClass) {
                        LinearLayout linearLayout = (LinearLayout) layoutInflater.inflate(R.layout.layout_account_class, binding.classLayout, false);
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
                            final EditText editText = new EditText(getContext());
                            AlertDialog dialog = new AlertDialog.Builder(getContext())
                                    .setTitle(getResources().getString(R.string.add_class))
                                    .setView(editText)
                                    .setPositiveButton(getResources().getText(R.string.confirm), new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            ViewUtils.hideSoftKeyboard(getContext(), editText);
                                            viewModel.insertAccountClass(new AccountClass(editText.getText().toString()));
                                            viewModel.loadClassData(classLiveData);
                                        }
                                    })
                                    .setNegativeButton(getResources().getText(R.string.cancel), new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            ViewUtils.hideSoftKeyboard(getContext(), editText);
                                        }
                                    })
                                    .create();
                            dialog.show();
                        } else {
                            tagLiveData.observe(AddAccountFragment.this, new Observer<List<AccountTag>>() {
                                @Override
                                public void onChanged(final List<AccountTag> tagList) {
                                    if (tagList.size() == 0 || !tagList.get(tagList.size() -1).getTagDescribe().equals("+")) {
                                        tagList.add(new AccountTag(0, "+"));
                                    }
                                    TagAdapter<AccountTag> tagAdapter = new TagAdapter<AccountTag>(tagList) {
                                        @Override
                                        public View getView(FlowLayout parent, int position, AccountTag accountTag) {
                                            LinearLayout linearLayout = (LinearLayout) layoutInflater.inflate(R.layout.layout_account_class, binding.tagLayout, false);
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
                                                final EditText editText = new EditText(getContext());
                                                AlertDialog dialog = new AlertDialog.Builder(getContext())
                                                        .setTitle(getResources().getString(R.string.add_class))
                                                        .setView(editText)
                                                        .setPositiveButton(getResources().getText(R.string.confirm), new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                                ViewUtils.hideSoftKeyboard(getContext(), editText);
                                                                viewModel.insertAccountTag(new AccountTag(classPosition, editText.getText().toString()));
                                                                viewModel.loadTagData(tagLiveData, classPosition);
                                                            }
                                                        })
                                                        .setNegativeButton(getResources().getText(R.string.cancel), new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                                ViewUtils.hideSoftKeyboard(getContext(), editText);
                                                            }
                                                        })
                                                        .create();
                                                dialog.show();
                                            }
                                        }
                                    };
                                    binding.tagLayout.setAdapter(tagAdapter);
                                    tagAdapter.setSelectedList(0);
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
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.add_fragment_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.finish) {
            if (classPosition != -1 && tagPosition != -1 && binding.money.getText().length() > 0) {
                ViewUtils.hideSoftKeyboard(getContext(), binding.money);
                viewModel.insertAccountItem(new AccountItem(System.currentTimeMillis(), Float.valueOf(binding.money.getText().toString()),
                        classPosition, tagPosition, tagDescribe));
                Navigation.findNavController(binding.toolbar).navigateUp();//上一页
            } else {
                Toast.makeText(getContext(), getResources().getText(R.string.retry), Toast.LENGTH_LONG).show();
            }
        }
        return true;
    }
}
