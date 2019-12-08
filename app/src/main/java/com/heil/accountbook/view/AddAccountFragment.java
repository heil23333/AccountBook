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

import com.heil.accountbook.R;
import com.heil.accountbook.bean.AccountClass;
import com.heil.accountbook.bean.AccountItem;
import com.heil.accountbook.databinding.FragmentAddAccountBinding;
import com.heil.accountbook.utils.TimeUtils;
import com.heil.accountbook.viewmodel.MainViewModel;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddAccountFragment extends Fragment {
    private FragmentAddAccountBinding binding;
    private MainViewModel viewModel;
    private MutableLiveData<List<AccountClass>> classLiveData;

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
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((AppCompatActivity)getActivity()).setSupportActionBar(binding.toolbar);//不加这一行不会显示Menu哦
        classLiveData = new MutableLiveData<>();
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
                        if (position == (accountClasses.size() -1)) {
                            final EditText editText = new EditText(getContext());
                            AlertDialog dialog = new AlertDialog.Builder(getContext())
                                    .setTitle(getResources().getString(R.string.add_class))
                                    .setView(editText)
                                    .setPositiveButton(getResources().getText(R.string.confirm), new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            viewModel.insertAccountClass(new AccountClass(editText.getText().toString()));
                                            viewModel.loadClassData(classLiveData);
                                        }
                                    })
                                    .setNegativeButton(getResources().getText(R.string.cancel), new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {

                                        }
                                    })
                                    .create();
                            dialog.show();
                        }
                    }
                };
                binding.classLayout.setAdapter(classTagAdapter);
                classTagAdapter.setSelected(0, accountClasses.get(0));
            }
        });
        binding.classLayout.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {

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
            Navigation.findNavController(binding.toolbar).navigateUp();//上一页
            //todo 插入的还不是真实的数据
            viewModel.insertAccountItem(new AccountItem(System.currentTimeMillis(), Float.valueOf(binding.money.getText().toString()),
                    1, 1, "晚餐"));
        }
        return true;
    }
}
