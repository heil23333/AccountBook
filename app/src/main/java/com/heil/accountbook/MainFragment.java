package com.heil.accountbook;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.heil.accountbook.databinding.FragmentMainBinding;
import com.heil.accountbook.viewmodel.MainViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    MainViewModel viewModel;
    FragmentMainBinding binding;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        binding = DataBindingUtil.setContentView(getActivity(), R.layout.fragment_main);
        binding.setViewmodel(viewModel);
    }
}
