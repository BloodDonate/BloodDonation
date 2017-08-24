package com.example.jonathandorvilier.dondesang.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jonathandorvilier.dondesang.R;

public class DonationFragment extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;

public static DonationFragment newInstance(int page){

    Bundle args = new Bundle();
    args.putInt(ARG_PAGE, page);
    DonationFragment fragment = new DonationFragment();
    fragment.setArguments(args);
    return fragment;
}
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_donation_fragment,parent,false);

        return v;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }
}
