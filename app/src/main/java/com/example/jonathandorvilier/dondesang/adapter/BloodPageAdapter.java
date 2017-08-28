package com.example.jonathandorvilier.dondesang.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.jonathandorvilier.dondesang.fragment.FragmentListeDemand;
import com.example.jonathandorvilier.dondesang.fragment.FragmentServiceCenter;


/**
 * Created by Jonathan Dorvilier on 8/23/2017.
*/

public class BloodPageAdapter extends FragmentPagerAdapter {

    //private int tabIcons[] = {R.mipmap.ic_reservation, R.mipmap.ic_donation, R.mipmap.ic_donate};

    final int PAGE_COUNT = 2;
    private String tabTitles[] = {"Demande", "Centre de Service"};

    public BloodPageAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);

    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new FragmentListeDemand();

        } else if (position == 1) {
            return new FragmentServiceCenter();
        } else {

            return null;
        }
    }

    //return the table title
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }


    /*@Override
    public int getPageIconResId(int position) {
        return tabIcons[position];
    }*/
}


