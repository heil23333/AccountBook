package com.heil.accountbook.view;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.heil.accountbook.R;
import com.heil.accountbook.databinding.FragmentMainBinding;
import com.heil.accountbook.viewmodel.MainViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    private MainViewModel viewModel;
    private FragmentMainBinding binding;
    private FloatingActionButton actionButton;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        binding.setLifecycleOwner(this);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        binding.setViewmodel(viewModel);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_addAccountFragment);
            }
        });
    }
}
