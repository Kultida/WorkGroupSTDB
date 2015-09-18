package com.example.gnomerock.mytemplate1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by gnomerock on 9/17/15.
 */
public class DummyFragment extends Fragment {



    public static DummyFragment newInstance(){
        DummyFragment fragment = new DummyFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dummy_fragment,container,false);
        return view;
    }
}
