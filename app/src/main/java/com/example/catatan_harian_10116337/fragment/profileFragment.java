package com.example.catatan_harian_10116337.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.catatan_harian_10116337.R;
import com.example.catatan_harian_10116337.activity.MainActivity;

/**
 * 10116337 Gery Gunawan AKB IF-3
 */
public class profileFragment extends Fragment {

    private MainActivity mainActivity;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

public void onViewCreated(@NonNull View view, @NonNull Bundle savedInstanceState){
        mainActivity = (MainActivity) getActivity();
        mainActivity.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
}
}